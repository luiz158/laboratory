package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import java.util.List;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.exception.EasyQueryFilterException;

public interface EasyQuery<T> {

  
  /*
   * Configure Query Result
   */
  
  public void setResultColumns(String[] columns);
  
  /*
   * get Result methods   
   */
  
  /**
   * Will use the EntityManager.getResultList() method to return the data.
   *
   * @return a list of objects
   */
  public List<T> getResultList();

  /**
   * Will use the EntityManager.getSingleResult() method to return the data. <br/> <br/>
   * <p/>
   * <b> The JPA might raise the NonUniqueResultException or the NoResultException</b>
   *
   * @return just one object
   */
  public T getSingleResult();

  
  /*
   * Add Predicates Methods   
   */
  /**
   * TODO fazer descrição
   * 
   * @param filter
   * @return
   * @throws EasyQueryFilterException
   */
  public EasyQuery<T> addAnnotatedFilter(Object filter) throws EasyQueryFilterException;
  
  /**
   * Method that uses the "=" of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name = 'Joseph'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andEquals("person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andEquals(String attributeName, Object value);

  /**
   * Method that uses the "=" of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name = 'Joseph'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like: <br/>
   * <code>andEquals(true, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> andEquals(boolean toLowerCase, String attributeName, Object value);

  /**
   * Method that uses the "OR" of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name = 'Joseph' OR p.name = 'Mary' OR p.name = 'Mark'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>orEquals("person.dog.name", "Minhoca", "Pipoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param values        to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> orEquals(String attributeName, Object... values);

  /**
   * Method that uses the "OR" of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name = 'Joseph' OR p.name = 'Mary' OR p.name = 'Mark'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>orEquals(true, "person.dog.name", "Minhoca", "Pipoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param values        to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> orEquals(boolean toLowerCase, String attributeName, Object... values);

  /**
   * Method that uses "OR" combined with "AND" expression of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p<br/>
   * where p.name = 'Joseph' OR p.name = 'Mary'<br/>
   * and p.age = 33 or p.age = 43</code> <br/><br/>
   * <p/>
   * To create the query above would be created with the methods: <br/>
   * <code>
   * easyCriteria.orEquals(1, "name", "Joseph").orEquals(1, "name", "Mary").orEquals(2, "age", "33").orEquals(2, "age", "43");
   * </code><br/><br/>
   * For each group of "ors" an index is used. <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>orEquals(1, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985  <br/><br/>
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param index         the grouped or order
   * @param attributeName the class attribute name
   * @param values        to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> orEquals(int index, String attributeName, Object... values);

  /**
   * Method that uses "OR" combined with "AND" expression of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p<br/>
   * where p.name = 'Joseph' OR p.name = 'Mary'<br/>
   * and p.age = 33 or p.age = 43</code> <br/><br/>
   * <p/>
   * To create the query above would be created with the methods: <br/>
   * <code>
   * easyCriteria.orEquals(1, "name", "Joseph").orEquals(1, "name", "Mary").orEquals(2, "age", "33").orEquals(2, "age", "43");
   * </code><br/><br/>
   * For each group of "ors" an index is used. <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>orEquals(true, 1, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985  <br/><br/>
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param index         the grouped or order
   * @param attributeName the class attribute name
   * @param values        to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> orEquals(boolean toLowerCase, int index, String attributeName, Object... values);

  /**
   * Method that uses the "<>" of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name <> 'Joseph'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andNotEquals("person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andNotEquals(String attributeName, Object value);

  /**
   * Method that uses the "<>" of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name <> 'Joseph'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andNotEquals(true, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> andNotEquals(boolean toLowerCase, String attributeName, Object value);

  /**
   * Method that uses the "<>" with OR of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>orNotEquals("person.dog.name", "Minhoca", "Pipoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param values        to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> orNotEquals(String attributeName, Object... values);

  /**
   * Method that uses the "<>" with OR of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>orNotEquals(true, "person.dog.name", "Minhoca", "Pipoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param values        to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> orNotEquals(boolean toLowerCase, String attributeName, Object... values);

  /**
   * Method that uses the ">" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight > 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andGreaterThan("person.dog.age", 33)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andGreaterThan(String attributeName, Object value);

  /**
   * Method that uses the ">" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight > 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andGreaterThan(true, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> andGreaterThan(boolean toLowerCase, String attributeName, Object value);

  /**
   * Method that uses the ">=" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight >= 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andGreaterOrEqualTo("person.dog.age", 33)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andGreaterOrEqualTo(String attributeName, Object value);

  /**
   * Method that uses the ">=" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight >= 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andGreaterOrEqualTo(true, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> andGreaterOrEqualTo(boolean toLowerCase, String attributeName, Object value);

  /**
   * Method that uses the "<" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andLessThan("person.dog.age", 33)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andLessThan(String attributeName, Object value);

  /**
   * Method that uses the "<" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andLessThan(true, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> andLessThan(boolean toLowerCase, String attributeName, Object value);

  /**
   * Method that uses the "<=" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andLessOrEqualTo("person.dog.age", 33)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andLessOrEqualTo(String attributeName, Object value);

  /**
   * Method that uses the "<=" of the JPQL with Double attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.weight < 10.4d</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andLessOrEqualTo(true, "person.dog.name", "Minhoca")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> andLessOrEqualTo(boolean toLowerCase, String attributeName, Object value);


  /**
   * Will do a inner join with a class relationship. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p inner join p.dogs d</code> <br/><br/>
   * <p/>
   * <b>If your implementation is OpenJPA, use the setDistinctTrue(), vote this bug: https://issues.apache.org/jira/browse/OPENJPA-2333</b>
   *
   * Be careful when using different kind of joins (INNER and LEFT in the same query).
   * Some implementations has some problems with it, even with JPQL.
   *
   * @param joinName the relationship to be joined
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> innerJoin(String joinName);

  /**
   * Will do a left join with a class relationship. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p left join p.dogs d</code> <br/><br/>
   * <p/>
   * <b>If your implementation is OpenJPA, use the setDistinctTrue(), vote this bug: https://issues.apache.org/jira/browse/OPENJPA-2333</b>
   *
   * Be careful when using different kind of joins (INNER and LEFT in the same query).
   * Some implementations has some problems with it, even with JPQL.
   *
   * @param joinName the relationship to be joined
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> leftJoin(String joinName);

  /**
   * Will do a inner join fetch with a class relationship.A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p inner join fetch p.dogs d</code> <br/><br/>
   *
   * Be careful when using different kind of joins (INNER and LEFT in the same query).
   * Some implementations has some problems with it, even with JPQL.
   *
   * @param joinName the relationship to be joined
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> innerJoinFetch(String joinName);

  /**
   * Will do a inner join fetch with a class relationship.A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p inner join fetch p.dogs d</code> <br/><br/>
   *
   * Be careful when using different kind of joins (INNER and LEFT in the same query).
   * Some implementations has some problems with it, even with JPQL.
   *
   * @param joinName the relationship to be joined
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> leftJoinFetch(String joinName);

  /**
   * Will use the distinct word in the query. A JPQL like bellow might be created: <br/><br/>
   * <code>select distinct p from Person p</code> <br/><br/>
   *
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> setDistinctTrue();

  /**
   * Method that uses the "between" of the JPQL with object attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.age between 19 and 29</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andBetween("person.dog.age", 33, 34)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param attributeName the class attribute name
   * @param valueA        the first number
   * @param valueB        the last number
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andBetween(String attributeName, Object valueA, Object valueB);

  /**
   * Method that uses the "between" of the JPQL with object attributes. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.age between 19 and 29</code> <br/><br/>
   * <p/>
   * You can use this method with a double, float, integer, long, date, calendar, string<br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andBetween(true, "person.dog.name", "M", "O")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param valueA        the first number
   * @param valueB        the last number
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> andBetween(boolean toLowerCase, String attributeName, Object valueA, Object valueB);

  /**
   * Will check if a field is null.<br/><br/> A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name is null</code> <br/><br/>
   * <p/>
   * <b> The JPA will raise exception if this method is used with a Collection </b>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andIsNull("person.dog.name")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param attributeName the class attribute name
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andIsNull(String attributeName);

  /**
   * Will check if a field is not null.<br/><br/> A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name is not null</code> <br/><br/>
   * <p/>
   * <b> The JPA will raise exception if this method is used with a Collection </b>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andIsNotNull("person.dog.name")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param attributeName the class attribute name
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andIsNotNull(String attributeName);

  /**
   * Will check if a Collection is empty.<br/><br/>A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.dogs is empty</code> <br/><br/>
   * <p/>
   * This method should be used with java.util.Collection, java.util.Set and java.util.List <br/>
   * <p/>
   * <b> The JPA may raise exception if this method is used with a non collection attribute or bring unexpected results </b>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andCollectionIsEmpty("person.dogs")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <br/><br/>
   * If you are using EclipseLink prior to version 2.5, use setDistinctTrue() as workaround of this bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=386354<br/>
   *
   *
   * @param collectionName the class Collection name
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andCollectionIsEmpty(String collectionName);

  /**
   * Will check if a Collection is not empty.<br/><br/>A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.dogs is not empty</code> <br/><br/>
   * <p/>
   * This method should be used with java.util.Collection, java.util.Set and java.util.List <br/>
   * <p/>
   * <b> The JPA may raise exception if this method is used with a non collection attribute or bring unexpected results </b>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andCollectionIsNotEmpty("person.dogs")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   * <br/><br/>
   * If you are using EclipseLink prior to version 2.5, use setDistinctTrue() as workaround of this bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=386354<br/>
   *
   * @param collectionName the class Collection name
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andCollectionIsNotEmpty(String collectionName);

  /**
   * Will do a String Like. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name like 'Mar%'</code> <br/><br/>
   * <p/>
   * <b> You must add the % symbol like:</b>
   * <ul>
   * <li>Mar%</li>
   * <li>%ar</li>
   * <li>%ar%</li>
   * </ul>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringLike("person.dogs.name", "%Minhoca%")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param attributeName the class attribute name
   * @param value         value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringLike(String attributeName, String value);

  /**
   * Will do a String Like. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name like 'Mar%'</code> <br/><br/>
   * <p/>
   * <b> You must add the % symbol like:</b>
   * <ul>
   * <li>Mar%</li>
   * <li>%ar</li>
   * <li>%ar%</li>
   * </ul>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringLike(true, "person.dogs.name", "%Minhoca%")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringLike(boolean toLowerCase, String attributeName, String value);

  /**
   * Will do a String Not Like. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name not like 'Mar%'</code> <br/><br/>
   * <p/>
   * <b> You must add the % symbol like:</b>
   * <ul>
   * <li>Mar%</li>
   * <li>%ar</li>
   * <li>%ar%</li>
   * </ul>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringNotLike("person.dogs.name", "%Minhoca%")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param attributeName the class attribute name
   * @param value         value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringNotLike(String attributeName, String value);

  /**
   * Will do a String Not Like. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name not like 'Mar%'</code> <br/><br/>
   * <p/>
   * <b> You must add the % symbol like:</b>
   * <ul>
   * <li>Mar%</li>
   * <li>%ar</li>
   * <li>%ar%</li>
   * </ul>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringNotLike(true,"person.dogs.name", "%Minhoca%")</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param value         value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringNotLike(boolean toLowerCase,String attributeName, String value);

  /**
   * Will do a String in. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name in ('A', 'B')</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringIn("person.dogs.name", names)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param attributeName the class attribute name
   * @param values        value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringIn(String attributeName, List<String> values);

  /**
   * Will do a String in. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name in ('A', 'B')</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringIn(true, "person.dogs.name", names)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param values        value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringIn(boolean toLowerCase, String attributeName, List<String> values);

  /**
   * Will do a String not in. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name not in ('A', 'B')</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringNotIn("person.dogs.name", names)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param attributeName the class attribute name
   * @param values        value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringNotIn(String attributeName, List<String> values);

  /**
   * Will do a String not in. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p where p.name not in ('A', 'B')</code> <br/><br/>
   * <p/>
   * You could use it to do conditions with joins like:<br/>
   * <code>andStringNotIn(true, "person.dogs.name", names)</code><br/>
   * A join must be created before using this method with joins: {@link #innerJoin(String)} {@link #leftJoin(String)} {@link #innerJoinFetch(String)} {@link #leftJoinFetch(String)}
   *
   * @param toLowerCase   will lower case of the String
   * @param attributeName the class attribute name
   * @param values        value to be used as parameter
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> andStringNotIn(boolean toLowerCase, String attributeName, List<String> values);

  
  public EasyQuery<T> orNotIn(String attributeName, List<?> values);
  
  public EasyQuery<T> orNotIn(boolean toLowerCase, String attributeName, List<?> values);
  
  public EasyQuery<T> orIn(String attributeName, List<?> values);
  
  public EasyQuery<T> orIn(boolean toLowerCase, String attributeName, List<?> values);
  
  /**
   * Will order your query result. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p order by p.name asc</code> <br/><br/>
   *
   * @param attributeName the attribute name
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> orderByAsc(String attributeName);

  /**
   * Will order your query result. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p order by p.name desc</code> <br/><br/>
   *
   * @param attributeName the attribute name
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> orderByDesc(String attributeName);

  /**
   * Set the first Result of the query result
   *
   * @param firstResult the first result index
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> setFirstResult(int firstResult);

  /**
   * Set the max result to be returned
   *
   * @param maxResults the max results to be returned
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> setMaxResults(int maxResults);

  /**
   * Method that will use "AND" separated with "OR" expression of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p<br/>
   * where p.name = 'Joseph' and p.age = 33<br/>
   * or p.name = 'Mary' and p.age = 43</code> <br/><br/>
   * <p/>
   * The query above would be created with the methods: <br/>
   * <code>
   * easyCriteria.addAndSeparatedByOr(1, "name", "Joseph").addAndSeparatedByOr(1, "age", "33").addAndSeparatedByOr(2, "name", "Mary").addAndSeparatedByOr(2, "age", "43");
   * </code><br/><br/>
   * For each group of "ors" an index is used. <br/><br/>
   * <p/>
   * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985 <br/><br/>
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/><br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param index         the grouped or order
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   */
  public EasyQuery<T> addAndSeparatedByOr(int index, String attributeName, Object value);

  /**
   * Method that will use "AND" separated with "OR" expression of the JPQL. A JPQL like bellow might be created: <br/><br/>
   * <code>select p from Person p<br/>
   * where p.name = 'Joseph' and p.age = 33<br/>
   * or p.name = 'Mary' and p.age = 43</code> <br/><br/>
   * <p/>
   * The query above would be created with the methods: <br/>
   * <code>
   * easyCriteria.addAndSeparatedByOr(1, "name", "Joseph").addAndSeparatedByOr(1, "age", "33").addAndSeparatedByOr(2, "name", "Mary").addAndSeparatedByOr(2, "age", "43");
   * </code><br/><br/>
   * For each group of "ors" an index is used. <br/><br/>
   * <p/>
   * If your implementation is Hibernate, you should not use attribute long, use Long instead. There is a open bug, please vote for it: https://hibernate.onjira.com/browse/HHH-7985 <br/><br/>
   * <p/>
   * This method can be used with any kind of "simple" attribute.<br/><br/>
   * <b>This method should not be used with
   * relationships (OneToOne, OneToMany, ManyToOne, ManyToMany), ElementCollection, etc.</b>
   *
   * @param toLowerCase   will lower case of the String
   * @param index         the grouped or order
   * @param attributeName the class attribute name
   * @param value         to be used in the query
   * @return the current EasyCriteria instance
   *
   * @throws IllegalArgumentException if the value is not String an exception will be thrown
   */
  public EasyQuery<T> addAndSeparatedByOr(boolean toLowerCase, int index, String attributeName, Object value);

  /**
   * Method that will count the query result,
   * but it ignores the values defined in setMaxResults and setFirstResult.
   * <br/>
   * To do a count with setMaxResults and setFirstResult it would be necessary to do a subquery, and subqueries are not supported <b>yet</b>.
   * <br/>
   * But this is not a problem at all. You could just do the query with max and first result and later:
   * <code> easyCriteria.setMaxResults(maxResults);
   * List<Person> persons = easyCriteria.getResultList();
   *
   * if(persons >= maxResults){
   *    count = easyCriteria.count();
   * } else{
   *    count = person.size();
   * }
   * </code>
   *
   *
   * @return total of results of the created criteria
   */
  public Long count();

  
}
