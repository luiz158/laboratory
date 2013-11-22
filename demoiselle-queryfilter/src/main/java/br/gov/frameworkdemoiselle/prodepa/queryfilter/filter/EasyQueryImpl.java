package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.exception.EasyQueryFilterException;

@SuppressWarnings("unchecked")
public class EasyQueryImpl<T> implements EasyQuery<T> {

	private Class<T> beanClass;

	private EntityManager em;

	private Query query;

	private List<Condition> andConditions;

	private List<Condition> orConditions;

	private List<String> ascOrderByConditions;

	private List<String> descOrderByConditions;

	private Map<String, Object> params;

	private String rootAlias;

	private List<String> joins;

	private Class<T> returnClass;

	private String[] resultColumns;

	// Controles
	private Integer conditionsCount;

	private static final Boolean toLowerCaseDefaultValeu = true;

	public EasyQueryImpl(Class<T> beanClass, EntityManager em) {
		super();

		this.beanClass = beanClass;
		this.em = em;

		this.rootAlias = "_" + beanClass.getSimpleName().toLowerCase();

		this.joins = new ArrayList<String>();
		
		this.andConditions = new ArrayList<EasyQueryImpl<T>.Condition>();
		this.orConditions = new ArrayList<EasyQueryImpl<T>.Condition>();

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
		this.joins.add(" INNER JOIN " + this.rootAlias + "." + joinName);
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
		// TODO Auto-generated method stub
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

		// TODO Alguma coisa aki ?

	}

	private String fillStringQuery() {

		StringBuilder q = new StringBuilder();

		buildSelect(q);
		
		buildFrom(q);
		
		buildJoins(q);
		
		buidAnds(q);

		buildOrs(q);

		buildOrderes(q);

		System.out.println(q.toString());
		return q.toString();
	}

	private void buildSelect(StringBuilder q) {
		if (this.returnClass == null && this.resultColumns == null) {

			q.append("SELECT " + this.rootAlias);

		} else {

			if (this.returnClass != null) {

				// Somente a classe especial foi definida >> delegar para a JPA createQuery("", Class)

			} else {
				// As colunas do retorno foram definidas
				q.append("SELECT ");
				String v = "";
				for (String col : this.resultColumns) {
					q.append(col + v);
					v = ",";
				}
			}
		}
	}

	private void buildFrom(StringBuilder q) {
		q.append(" FROM " + this.beanClass.getSimpleName() + " " + this.rootAlias);
	}

	private void buildJoins(StringBuilder q) {
		for (String join : this.joins) {
			
			q.append(" " + join);
			
		}
	}
	
	private void buidAnds(StringBuilder q) {

		for (Condition c : andConditions) {
			if (this.conditionsCount == 0) {
				q.append(" WHERE ");
			} else {
				q.append(" AND ");
			}

			// TODO e as JOINS ?

			q.append(this.rootAlias + c.getAttribute() + " " + c.getType().getOperator() + " :" + c.getAttribute());
			this.conditionsCount++;
		}

	}

	private void buildOrs(StringBuilder q) {
		for (Condition c : orConditions) {
			if (this.conditionsCount == 0) {
				q.append(" WHERE ");
			} else {
				q.append(" AND ");
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

}
