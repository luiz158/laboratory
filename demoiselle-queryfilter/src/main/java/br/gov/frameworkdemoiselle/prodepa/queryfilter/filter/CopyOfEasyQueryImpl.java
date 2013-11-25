package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.exception.EasyQueryFilterException;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.StringUtil;

/**
 * 
 * TODO Descrever essa classe
 * 
 * TODO Refatorar essa classe para que ela fique mais simples
 * 
 * @author thiago
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class CopyOfEasyQueryImpl<T> implements EasyQuery<T> {

	private Class<T> beanClass;

	private EntityManager em;

	private Query query;
	
	private StringBuilder bQuery;

	private List<Condition> andConditions;

	private List<Condition> orConditions;

	private List<String> ascOrderByConditions;

	private List<String> descOrderByConditions;

	private Map<String, Object> params;

	private String rootAlias;

	private List<String> joins;
	private Map<String, String> joinsPaths;

	private Class<T> returnClass;

	private String[] resultColumns;

	// Controles
	private Integer conditionsCount;
	private Boolean distinctTrue = false;
	private String distinctON = null; //TODO Procurar forma de se fazer isso.

	private static final Boolean toLowerCaseDefaultValeu = true;

	public CopyOfEasyQueryImpl(Class<T> beanClass, EntityManager em) {
		super();

		this.beanClass = beanClass;
		this.em = em;

		this.rootAlias = "_" + beanClass.getSimpleName().toLowerCase();

		this.joins = new ArrayList<String>();
		this.joinsPaths = new HashMap<String, String>();
		
		this.andConditions = new ArrayList<CopyOfEasyQueryImpl<T>.Condition>();
		this.orConditions = new ArrayList<CopyOfEasyQueryImpl<T>.Condition>();

		this.ascOrderByConditions = new ArrayList<String>();
		this.descOrderByConditions = new ArrayList<String>();

		this.params = new HashMap<String, Object>();

		this.conditionsCount = 0;

	}

	public void setResultClass(Class<?> resultClass) {
		this.returnClass = returnClass;
	}

	public void setResultColumns(String[] columns) {
		// TODO Deve haver um modo melhor para converter String[] para List<String>
		this.resultColumns = columns;
	}

	public List<T> getResultList() {
		// TODO Caso não haja resultSetColumns ou ???, o resultado será simples
		buildQuery();
		
		return (List<T>) query.getResultList();
	}

	public T getSingleResult() {
		// TODO Caso não haja resultSetColumns ou ???, o resultado será simples
		buildQuery();
		return (T) query.getSingleResult();
	}

	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	public EasyQuery<T> addAnnotatedFilter(Object filter) throws EasyQueryFilterException {
	  
	  new EasyQueryParameterBuild<T>(this, beanClass).addFilters(filter);
		
	  return this;
	}

	/**
	 * AND Clausules
	 */

	public EasyQuery<T> andEquals(String attributeName, Object value) {
		return andEquals(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andEquals(boolean toLowerCase, String attributeName, Object value) {
		andConditions.add(new Condition(AndConditionType.EQUAL, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andNotEquals(String attributeName, Object value) {
		return andNotEquals(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andNotEquals(boolean toLowerCase, String attributeName, Object value) {
		andConditions.add(new Condition(AndConditionType.NOT_EQUALS, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andGreaterThan(String attributeName, Object value) {
		return andGreaterThan(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andGreaterThan(boolean toLowerCase, String attributeName, Object value) {
		andConditions.add(new Condition(AndConditionType.GT, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andGreaterOrEqualTo(String attributeName, Object value) {
		return andGreaterOrEqualTo(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andGreaterOrEqualTo(boolean toLowerCase, String attributeName, Object value) {
		andConditions.add(new Condition(AndConditionType.GE, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andLessThan(String attributeName, Object value) {
		return andLessThan(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andLessThan(boolean toLowerCase, String attributeName, Object value) {
		andConditions.add(new Condition(AndConditionType.LT, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andLessOrEqualTo(String attributeName, Object value) {
		return andLessOrEqualTo(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andLessOrEqualTo(boolean toLowerCase, String attributeName, Object value) {
		andConditions.add(new Condition(AndConditionType.LE, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andBetween(String attributeName, Object valueA, Object valueB) {
		return andBetween(toLowerCaseDefaultValeu, attributeName, valueA, valueB);
	}

	public EasyQuery<T> andBetween(boolean toLowerCase, String attributeName, Object valueA, Object valueB) {
		andConditions.add(new Condition(AndConditionType.BETWEEN, attributeName, valueA, valueB, toLowerCase));
		return this;
	}

	public EasyQuery<T> andIsNull(String attributeName) {
		andConditions.add(new Condition(AndConditionType.IS_NULL, attributeName, null, null));
		return this;
	}

	public EasyQuery<T> andIsNotNull(String attributeName) {
		andConditions.add(new Condition(AndConditionType.IS_NOT_NULL, attributeName, null, null));
		return this;
	}

	public EasyQuery<T> andCollectionIsEmpty(String collectionName) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> andCollectionIsNotEmpty(String collectionName) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> andStringLike(String attributeName, String value) {
		return andStringLike(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andStringLike(boolean toLowerCase, String attributeName, String value) {
		andConditions.add(new Condition(AndConditionType.LIKE, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andStringNotLike(String attributeName, String value) {
		return andStringNotLike(toLowerCaseDefaultValeu, attributeName, value);
	}

	public EasyQuery<T> andStringNotLike(boolean toLowerCase, String attributeName, String value) {
		andConditions.add(new Condition(AndConditionType.NOT_LIKE, attributeName, value, toLowerCase));
		return this;
	}

	public EasyQuery<T> andStringIn(String attributeName, List<String> values) {
		return andStringIn(toLowerCaseDefaultValeu, attributeName, values);
	}

	public EasyQuery<T> andStringIn(boolean toLowerCase, String attributeName, List<String> values) {
		// TODO Acho que falta algo aki
		andConditions.add(new Condition(AndConditionType.IN, attributeName, values, toLowerCase));
		return this;
	}

	public EasyQuery<T> andStringNotIn(String attributeName, List<String> values) {
		return andStringNotIn(toLowerCaseDefaultValeu, attributeName, values);
	}

	public EasyQuery<T> andStringNotIn(boolean toLowerCase, String attributeName, List<String> values) {
		andConditions.add(new Condition(AndConditionType.NOT_IN, attributeName, values, toLowerCase));
		return this;
	}

	public EasyQuery<T> addAndSeparatedByOr(int index, String attributeName, Object value) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> addAndSeparatedByOr(boolean toLowerCase, int index, String attributeName, Object value) {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * OR Clausules
	 */

	public EasyQuery<T> orEquals(String attributeName, Object... values) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> orEquals(boolean toLowerCase, String attributeName, Object... values) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> orEquals(int index, String attributeName, Object... values) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> orEquals(boolean toLowerCase, int index, String attributeName, Object... values) {
		// TODO Auto-generated method stub
		// orConditions.put(OrConditionType.EQUAL, new Condition(attributeName, value));

		return this;
	}

	public EasyQuery<T> orNotEquals(String attributeName, Object... values) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> orNotEquals(boolean toLowerCase, String attributeName, Object... values) {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * JOINS Clausules
	 */

	public EasyQuery<T> innerJoin(String joinName) {
		// Acho que será o
		this.joins.add(" INNER JOIN " + this.rootAlias + "." + joinName + " " + StringUtil.castToAliasName(joinName));
		
		this.joinsPaths.put(joinName, StringUtil.castToAliasName(joinName));
		
		return this;
	}

	public EasyQuery<T> leftJoin(String joinName) {
		this.joins.add(" LEFT JOIN " + this.rootAlias + "." + joinName);
		return this;
	}

	public EasyQuery<T> innerJoinFetch(String joinName) {
		this.joins.add(" INNER JOIN FETCH " + this.rootAlias + "." + joinName);
		return this;
	}

	public EasyQuery<T> leftJoinFetch(String joinName) {
		this.joins.add(" LEFT JOIN FETCH " + this.rootAlias + "." + joinName);
		return this;
	}


	/**
	 * ORDER Clausules
	 */

	public EasyQuery<T> orderByAsc(String attributeName) {
		ascOrderByConditions.add(attributeName);
		return this;
	}

	public EasyQuery<T> orderByDesc(String attributeName) {
		descOrderByConditions.add(attributeName);
		return this;
	}

	/**
	 * OTHER Clausules
	 */

	public EasyQuery<T> setDistinctTrue() {
		this.distinctTrue = true;
		return this;
	}

	public EasyQuery<T> setFirstResult(int firstResult) {
		// TODO Auto-generated method stub
		return this;
	}

	public EasyQuery<T> setMaxResults(int maxResults) {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * Build Query
	 */

	private void buildQuery() {

		query = em.createQuery(fillStringQuery());
		putParams();
		// TODO Alguma coisa mais aki ?
	}

	private String fillStringQuery() {

		this.bQuery = new StringBuilder();

		buildSelect();
		
		buildFrom();
		
		buildJoins();
		
		buidAnds();

		buildOrs();

		buildOrderes(this.bQuery);

		System.out.println(this.bQuery.toString());
		return this.bQuery.toString();
	}

	private void buildSelect() {
		if (this.returnClass == null && this.resultColumns == null) {

			if(this.distinctTrue) {
				this.bQuery.append("SELECT DISTINCT " + this.rootAlias);
			} else {
				this.bQuery.append("SELECT " + this.rootAlias);
			}

		} else {

			if (this.returnClass != null) {

				// Somente a classe especial foi definida >> delegar para a JPA createQuery("", Class)

			} else {
				// As colunas do retorno foram definidas
				if(this.distinctTrue) {
					this.bQuery.append("SELECT DISTINCT ");
				} else {
					this.bQuery.append("SELECT ");
				}
				String v = "";
				for (String col : this.resultColumns) {
					this.bQuery.append(col + v);
					v = ",";
				}
			}
		}
	}

	private void buildFrom() {
		this.bQuery.append(" FROM " + this.beanClass.getSimpleName() + " " + this.rootAlias);
	}

	private void buildJoins() {
		for (String join : this.joins) {
			
			this.bQuery.append(" " + join);
			
		}
	}
	
	private void buidAnds() {

		//TODO refatorar!!!!!!!!!!!!!!!!!
		
		for (Condition c : andConditions) {
			if (this.conditionsCount == 0) {
				this.bQuery.append(" WHERE ");
			} else {
				this.bQuery.append(" AND ");
			}

			// TODO e as JOINS ?
			
			if(c.getAttribute().contains(".")) {
				
				//TODO BUG isso não está funcionando para joins em multiplos níveis
				String join = c.getAttribute().split("\\.")[0];
				
				if(!this.joinsPaths.containsKey(join)) {
					//TODO throw - o join é invalido 
				}
				
				if(c.getType().equals(AndConditionType.BETWEEN)) {
					this.bQuery.append(c.getAttribute().replace(join, this.joinsPaths.get(join)) + " " + c.getType().getOperator() + " :" + StringUtil.castToParamName(c.getAttribute()) + "_A AND " + StringUtil.castToParamName(c.getAttribute()) + "_B");
				} else {
					this.bQuery.append(c.getAttribute().replace(join, this.joinsPaths.get(join)) + " " + c.getType().getOperator() + " :" + StringUtil.castToParamName(c.getAttribute()));
				}
				
				
			} else {
				
				if(c.getType().equals(AndConditionType.BETWEEN)) {
					this.bQuery.append(this.rootAlias +"."+ c.getAttribute() + " " + c.getType().getOperator() + " :" + c.getAttribute() + "_A AND :" +  c.getAttribute() + "_B");
				} else {
					this.bQuery.append(this.rootAlias +"."+ c.getAttribute() + " " + c.getType().getOperator() + " :" + c.getAttribute());
				}
				
			}
			
			if(c.getType().equals(AndConditionType.BETWEEN)) {
				this.params.put(StringUtil.castToParamName(c.getAttribute())+ "_A", c.getValue());
				this.params.put(StringUtil.castToParamName(c.getAttribute())+ "_B", c.getValueB());
			} else {
				this.params.put(c.getAttribute().replace(".", "_"), c.getValue()); //TODO Pode ser que sejam valorA e valorB
				
			}
			
			this.conditionsCount++;
		}

	}

	private void buildOrs() {
		
		//TODO Não implementado ainda
		
		for (Condition c : orConditions) {
			if (this.conditionsCount == 0) {
				this.bQuery.append(" WHERE ");
			} else {
				this.bQuery.append(" AND ");
			}
			this.conditionsCount++;
		}
	}

	private void buildOrderes(StringBuilder q) {
		for (String o : ascOrderByConditions) {

		}
		for (String o : ascOrderByConditions) {

		}
	}
	
	private void putParams() {
		for(String p : this.params.keySet()) {
			
			//TODO Tratar tipo: Data, etc
			query.setParameter(p, this.params.get(p));
		}
	}

	private class Condition {

		private AndConditionType type;

		private String attribute;

		private Object value;

		private Object valueB;

		private Boolean toLowerCase;

		public Condition(AndConditionType type, String attribute, Object value, Boolean toLowerCase) {
			super();
			this.toLowerCase = toLowerCase;
			this.attribute = attribute;
			this.value = value;
			this.type = type;
		}

		public Condition(AndConditionType type, String attribute, Object value, Object valueB, Boolean toLowerCase) {
			super();
			this.toLowerCase = toLowerCase;
			this.attribute = attribute;
			this.value = value;
			this.type = type;
		}

		public Boolean getToLowerCase() {
			return toLowerCase;
		}

		public String getAttribute() {
			return attribute;
		}

		public Object getValue() {
			return value;
		}

		public AndConditionType getType() {
			return type;
		}

		public Object getValueB() {
			return valueB;
		}
	}

	@Override
	public String toString() {
		
		return super.toString();
	}


	@Override
	public EasyQuery<T> orNotIn(String attributeName, List<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyQuery<T> orNotIn(boolean toLowerCase, String attributeName, List<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyQuery<T> orIn(String attributeName, List<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyQuery<T> orIn(boolean toLowerCase, String attributeName, List<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
