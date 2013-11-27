Demoiselle Query Filter
=======================

O componente Demoiselle Query Filter (demoiselle-queryfilter) é um wrapper para a construção de queries da JPA 2.0.
Ele disponibiliza duas ferramentas principais: Uma API para *Criação de consultas* e um API para *Configuração de consultas* via anotações. 

> Em próximas versões, seram disponibilizadas três implementações para as Queries: Critéria, JPAQL e SQL(native query)

Iniciando com o Demoiselle Query Filter
--------------------------------

Neste exemplo será demonstrado como criar uma pesquisa simples utilizando objetos anotados e como complementar a consulta com a API do 
componente ou criá-la sem o uso de anotações. 

###1. Criando o modelo.
	
Não faz parte a API do *demoiselle-queryfilter*, mas precisamos de uma Entity. Para esse howto, vamos considerar a entidade Bookmarks
	
```java
@Entity
public class Bookmark {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private String link;
	
	@Column
	private Boolean active;
	
	//getters and Setters	
}
```	

###2. Consultas anotadas, os @QueryFilter

Os **@QueryFilter** são classes que contém as anotações que configuram como será a pesquisa. Criaremos duas classes para exemplificar: 
**CommonFilter** e **BookmarksFilter**.
Elas obrigatoriamente terão a anotação **@QueryFilter**, mas outras anotações de classe são permitidas, como as definem o escopo.

>O uso de classes anotadas é opcional, a API de consultas não exige o uso delas sendo possível construir queries diretamente com o EasyQuery. 


```java
@Named
@RequestScoped
@QueryFilter
public class CommonFilter implements Serializable {
	
    @TextSearch(strategy = TextSearchStyle.LikeIgnoreCase)
    private String filter;

	//getters and Setters	
}
```

```java
@Named
@RequestScoped
@QueryFilter(orderByAsc = "description")
public class BookmarksFilter implements Serializable {
	
	@And
	private Long id;
  
    @And
    private String description;
    
    @And(attributeNames = {"description", "link"}, ignoreCase = true )
    private String pattern;

	//getters and Setters	
}
```
> Dica: Cuidado com os tipos primitivos `int, long, boolean`, eles não podem ser `null`, logo seus valores default serão considerados nas queries. 

Na anotação @QueryFilter poderemos definir os critérios de ordenação da consulta, tanto Crescente com Decrescente. 

As propriedades da classe poderão conter, nesta versão, quatro anotações: `@And`, `@Or`, `@FirstResult` ou `@MaxResult`. 
As anotações `@And` e `@Or` possuem configurações adicionais que definem como a condição será construída. Por exemplo: *Equals, Like, Betweem, In*.

As anotações `@FirstResult` ou `@MaxResult` não possuem configurações adicionais. Uma coisa, no entando, é importante notar: Diferente de `@And` e `@Or`,
 `@FirstResult` e `@MaxResult` só podem aparecer uma vez no objeto QueryFilter.   

> O comportamento padrão será *LIKE* para Strings e *Equals* para os demais.

> Em operações com *BETWEEN*, o campo pode ser do tipo List, Set, Array ou Range. Claro que os tipos List, Set, Array deverão conter apenas duas posições. O tipo Range auxilia 
na definição dessa informação encapsulamento os dois parâmetros em um só objeto. Dê preferência ao tipo Range sempre que possível. 

No primeiro exemplo, `CommonFilter`, a anotação `@TextSearch(strategy = TextSearchStyle.LikeIgnoreCase)` define que na query será feito um `LIKE` em todos os 
campos do tipo `String` do Bean. O bean será definido no momento da criação da query. Esse é portanto um objeto bem genérico.

No segundo caso, `BookmarksFilter`, os filtros são muito mais específicos. No campos `id` e `description` serão criadas cláusulas para os campos `id` e `description`
da entidade Bookmark individualmente e separadas por *AND*. Já no caso da propriedade `pattern`,  serão adicionadas duas clausulas separadas por *OR*. 
Neste último casoo ficaria assim: 

```java
SELECT b FROM Bookmark b 
 WHERE LOWER(b.description) LIKE :pattern 
    OR LOWER(b.link) LIKE :pattern 
 ORDER BY b.description
```

Note que além das cláusulas *AND* ou *OR*, a configuração `ignoreCase` adicionou o *LOWER* sobre os parâmetros e o `orderByAsc` adicionou o *ORDER BY*.

Claro que podem haver outras cláusulas na query. Elas seriam devidamente somadas.   
	

###3. Formatando e executando consultas 
Para formatar e executar as consultas utilizaremos a API do componente. Os objetos principais são o **EasyQuery** e o **EasyQueryManager**, 
que representam uma *Query* e um *Fábrica de Queries*, respectivamente. O *EasyQuery* pode ser instanciado diretamente, mas é recomentado que ele seja
obtido através de sua fábrica.
 
O local onde serão executadas as queries depende da arquitetura do projeto. Havendo acesso ao EntityManager da JPA, a query poderá ser executada.
Recomendamos, no entanto, que elas sejam feitas nos componentes de negócio. 
A seguir, alguns exemplos de utilização. 

```java
//Exemplo como na Demoiselle, utilizando anotações para definir a query
@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {
	
	@Inject
	private EntityManager em;
	
	@Inject
  	private EasyQueryManager<Bookmark> qm;
	
	public List<Bookmark> findAll(CommonFilter filter) throws QueryFilterException {
	  
	  EasyQuery<Bookmark> q = qm.createQuery();
	  
	  //Adiciona os filtros anotados à query
	  q.addAnnotatedFilter(filter);
	  
	  //Complementa a query com uma regra de negócio
	  q.andEquals("active", true);
	  
	  //Obtém o resultado
	  return q.getResultList();
	}

	//demais métodos	
}
```

Note o que foi feito: 

1) O método `addAnnotatedFilter(Object)` adicionou todos os filtros **preenchidos** do objeto à query

2) O método `andEquals(String, Object)` complementou a query definindo que apenas *Bookmarks Ativos* serão retornados. Um **AND** simples

3) O método `getResultList()` obtém o resultado



```java
//Exemplo Sem o uso de objetos anotados
@BusinessController
public class BookmarkBC {
	
	@Inject
	private EntityManager em;
	
	@Inject
  	private EasyQueryManager<Bookmark> qm;
	
	public List<Bookmark> search(CommonFilter filter) throws QueryFilterException {
	  
	  EasyQuery<Bookmark> q = qm.createQuery();
	  
	  //Somente bookmarks ativos são retornados
	  q.andEquals("active", true);
	  
	  //Os Bookmarks são filtrados com LIKE em "description"
	  q.andStringLike("description", filter.getFilter());
	  
	  //Obtém o resultado
	  return q.getResultList();
	}

	//demais métodos	
}
```

Note o que foi feito:

1) O método `andEquals(String, Object)` define que apenas **Bookmarks Ativos** serão retornados.

2) O método `andStringLike(String, Object)` adicionou uma condição de **LIKE** para a propriedade `description`

3) O método `getResultList()` obtém o resultado


A classe **EasyQuery** fornece uma extensa lista de operações para a criação de queries. Segue alguns exemplos. 

<dl>
	<dt>Obtenção de Resultado e Paginação</dt>
	<dd>getResultList()</dd>
	<dd>getSingleResult()</dd>
	<dd>setFirstResult(int)</dd>
	<dd>setMaxResults(int)</dd>
	
	<dt>Adição de cláusulas</dt>
	<dd>andEquals(String, Object)</dd>
	<dd>andNotEquals(String, Object)</dd>
	<dd>andGreaterThan(String, Object)</dd>
	<dd>andGreaterOrEqualTo(String, Object)</dd>
	<dd>andLessThan(String, Object)</dd>
	<dd>andLessOrEqualTo(String, Object)</dd>
	<dd>andBetween(String, Object, Object)</dd>
	<dd>andIsNull(String)</dd>
	<dd>orEquals(String, Object...)</dd>
	
	<dt>Joins e Fetchs</dt>
	<dd>innerJoin(String);</dd>
	<dd>leftJoin(String)</dd>
	<dd>innerJoinFetch(String)</dd>
	<dd>leftJoinFetch(String)</dd>
	
	<dt>Ordenação</dt>
	<dd>orderByAsc(String)</dd>
	<dd>orderByDesc(String)</dd>
</dl>


###4. O CDI e o produtor de **EasyQueryManager**

O  **Prodepa Easy Query Filter** não está vinculado ao CDI, mas os usuários deste framework podem utilizá-lo sem problemas. 
Aqui vão algumas dicas.

1) Construa um método **Produtor** para a **EasyQueryManager** utilizando **InjectionPoints**

Com o uso de `Produces`, será possível contruír diversas *fábricas de queries*. Um exemplo clássico seria uma fábrica para cada **persistence-unit**

```java
@javax.enterprise.inject.Produces
public EasyQueryManager getEasyQueryManager(InjectionPoint injectionPoint) {
    
  ParameterizedType type = (ParameterizedType) injectionPoint.getType();
    
  return new EasyQueryManager((Class) type.getActualTypeArguments()[0], em);
}	
```

2) Ao injetar a **EasyQueryManager**, use *ParameterizedTypes*

Quando injetar a instância da fábrica, não esquiça de definir para qual tipo serão contruídas as queries.  

```java
@Inject
private EasyQueryManager<Bookmark> qm;
```

###5. Escreva menos com as Fluent Interfaces

Escrever menos é importante ? Achamos que sim e nos preocupamos em disponibilizar **FluentInterfaces** na API de consultas.


```java
@Inject
private EasyQueryManager<Bookmark> qm;

qm.createQuery().addAnnotatedFilter(filter).getResultList();
	  
```

```java
@Inject
private EasyQueryManager<Bookmark> qm;

qm.createQuery().andEquals("active", true).andStringLike("description", filter.getFilter()).getResultList();
```
 
**TODO Descrever o uso de paginacao**


