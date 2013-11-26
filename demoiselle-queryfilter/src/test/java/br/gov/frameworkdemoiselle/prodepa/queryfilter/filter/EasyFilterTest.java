package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Address;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Car;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Dog;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.EmbeddedIdDummy;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.EntityEmbeddedId;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Manufacturer;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Person;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Product;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Song;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.SongType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.utiltestes.AbstractTest;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.utiltestes.CodeGenerator;

public class EasyFilterTest extends AbstractTest {

	
	@Test
	public void isCreatingCriteria() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		assertNotNull(easyCriteria);
	}
	
	@Test
	public void testFindAll() {

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));

	}

	@Test
	public void testSingleResult() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.id = 1", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("id", 1);

		Person person = easyCriteria.getSingleResult();

		assertTrue(personsFromJPQL.size() == 1);

		assertTrue(personsFromJPQL.get(0).equals(person));
	}

	@Test
	public void assertDataInDataBase() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p", Person.class);
		assertTrue(personsFromJPQL.size() > 0);
	}

	@Test
	public void isListingAll() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isGettingSingleResult() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.id = 1", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andEquals("id", 1);

		Person person = easyCriteria.getSingleResult();

		assertTrue(personsFromJPQL.size() == 1);

		assertTrue(personsFromJPQL.get(0).equals(person));
	}

	@Test
	public void isAddingOneWhereFloatEquals() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight = 10", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("weight", 10f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isOrFloatInWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight = 10 or p.weight = 11",
				Person.class);
		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orIn("weight", Arrays.asList(10f, 11f));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneWhereDoubleEquals() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.height = 11", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("height", 11d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneOrDoubleIN() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.height = 10 or p.height = 11 ",
				Person.class);
		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orIn("height", Arrays.asList(10d, 11d));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneWhereLongEquals() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.socialSecurityNumber = 1928371",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("socialSecurityNumber", 1928371L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneOrLongIN() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.socialSecurityNumber = 1928371 or p.socialSecurityNumber = 98723487",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orIn("socialSecurityNumber", Arrays.asList(1928371L, 98723487L));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneWhereIntegerEquals() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.clothesInCloset = 44",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("clothesInCloset", 44);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));

	}

	@Test
	public void isAddingOneOrIntegerIN() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.clothesInCloset = 44 or p.clothesInCloset = 33", Person.class);
		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orIn("clothesInCloset", Arrays.asList(44, 33));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));

	}

	@Test
	public void isWhereNotEqualsWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name <> 'Anna'", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andNotEquals("name", "Anna");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isOrNotEqualsWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.name <> 'Anna' or p.name <> 'Mary'", Person.class);

		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orNotEquals("name", "Anna", "Mary");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingSequentialWhereIntegerEquals() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.shoesInCloset = 10 and p.clothesInCloset = 33", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("shoesInCloset", 10).andEquals("clothesInCloset", 33);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneWhereEqualsString() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name = '"
				+ CodeGenerator.PERSON01_NAME + "'", Person.class);

		assertTrue(personsFromJPQL.size() == 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("name", CodeGenerator.PERSON01_NAME);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneOrStringIN() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name = '"
				+ CodeGenerator.PERSON01_NAME + "' or p.name = '" + CodeGenerator.PERSON02_NAME + "'", Person.class);

		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orIn("name", Arrays.asList(CodeGenerator.PERSON01_NAME, CodeGenerator.PERSON02_NAME));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingSequentialWhereEqualsString() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name = '"
				+ CodeGenerator.PERSON01_NAME + "'" + "and p.nickName = '" + CodeGenerator.PERSON01_NICKNAME + "'",
				Person.class);

		assertTrue(personsFromJPQL.size() == 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("name", CodeGenerator.PERSON01_NAME).andEquals("nickName",
				CodeGenerator.PERSON01_NICKNAME);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneWhereDateEquals() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstJobDate = formatter.parse("1/1/2015");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstJobDate", firstJobDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate = :firstJobDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("firstJobDate", firstJobDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneOrDateIN() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstJobDate = formatter.parse("1/1/2015");
		Date secondJobDate = formatter.parse("1/1/2016");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstJobDate", firstJobDate);
		parameters.put("secondJobDate", secondJobDate);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.firstJobDate = :firstJobDate or p.firstJobDate = :secondJobDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orIn("firstJobDate", Arrays.asList(firstJobDate, secondJobDate));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneWhereCalendarEquals() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", dateOfBirth);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate = :birthDayDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("birthDayDate", dateOfBirth);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneOrCalendarIn() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(date);

		Date date2 = formatter.parse("1/1/2002");
		Calendar dateOfBirth2 = Calendar.getInstance();
		dateOfBirth2.setTime(date2);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", dateOfBirth);
		parameters.put("dateOfBirth2", dateOfBirth2);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.birthDayDate = :birthDayDate or p.birthDayDate = :dateOfBirth2",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orIn("birthDayDate", Arrays.asList(dateOfBirth, dateOfBirth2));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingSequentialWhereDateEquals() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstJobDate = formatter.parse("1/1/2015");
		Date firstSoccerMatchDate = formatter.parse("1/1/2013");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstJobDate", firstJobDate);
		parameters.put("firstSoccerMatchDate", firstSoccerMatchDate);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.firstJobDate = :firstJobDate and p.firstSoccerMatchDate = :firstSoccerMatchDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("firstJobDate", firstJobDate).andEquals("firstSoccerMatchDate", firstSoccerMatchDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingSequentialWhereCalendarEquals() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date birthDay = formatter.parse("1/1/2001");
		Date kissDate = formatter.parse("1/1/2011");

		Calendar birthDayCalendar = Calendar.getInstance();
		birthDayCalendar.setTime(birthDay);

		Calendar firstKiss = Calendar.getInstance();
		firstKiss.setTime(kissDate);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDay", birthDayCalendar);
		parameters.put("firstKiss", firstKiss);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.birthDayDate = :birthDay and p.firstKissDate = :firstKiss",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("birthDayDate", birthDayCalendar).andEquals("firstKissDate", firstKiss);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneWhereEqualsBoolean() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.brazilian = true", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("brazilian", true);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingOneOrEqualsBoolean() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.brazilian = true or p.brazilian = false", Person.class);

		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orEquals("brazilian", true, false);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingSequentialWhereEqualsBoolean() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.brazilian = true and p.japanese = false", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("brazilian", true).andEquals("japanese", false);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingObjectWhereEquals() {

		Address address = new Address();
		address.setId(1);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("address", address);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.address = :address",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("address", address);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingObjectOrEquals() {

		Address address = new Address();
		address.setId(1);

		Address address2 = new Address();
		address2.setId(2);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("address", address);
		parameters.put("address2", address2);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.address = :address or p.address = :address2", Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 1);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orEquals("address", address, address2);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingSequentialObjectsWhereEquals() {

		Address address = new Address();
		address.setId(1);
		Car car = new Car();
		car.setId(1);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("address", address);
		parameters.put("car", car);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.address = :address and p.car = :car", Person.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("address", address).andEquals("car", car);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isAddingSequentialWhereEquals() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date buildingDate = formatter.parse("1/1/1990");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("buildingDate", buildingDate);

		List<Address> addressesFromJPQL = getListFromJPQL(
				"select a from Address a where a.isOld = false and a.isYellow = true and a.id = 2 and a.streetName = 'Street B' and a.buildingDate = :buildingDate",
				Address.class, parameters);
		assertTrue(addressesFromJPQL.size() > 0);

		EasyQuery<Address> easyCriteria = new EasyQueryImpl<Address>(Address.class, getEntityManager());

		easyCriteria.andEquals("id", 2).andEquals("buildingDate", buildingDate).andEquals("isOld", false)
				.andEquals("streetName", "Street B").andEquals("isYellow", true);

		List<Address> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(addressesFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(addressesFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDoubleGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.height > 10.50d", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan("height", 10.50d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDoubleGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.height >= 11.00d", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("height", 11.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDoubleLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.height < 11.00d", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan("height", 11.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDoubleLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.height <= 11.00d", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("height", 11.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isFloatGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight > 10.50f", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan("weight", 10.50f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isFloatGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight >= 11.00f", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("weight", 11.00f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isFloatLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight < 11.00f", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan("weight", 11.00f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isFloatLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.weight <= 10.50f", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("weight", 10.50f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLongGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned > 20",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan("totalBooksOwned", 20L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLongGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned >= 20",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("totalBooksOwned", 20L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLongLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned < 30",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan("totalBooksOwned", 30L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLongLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.totalBooksOwned <= 20",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("totalBooksOwned", 20L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isIntegerGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset > 10",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan("shoesInCloset", 10);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isIntegerGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset >= 10",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("shoesInCloset", 10);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isIntegerLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset < 20",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan("shoesInCloset", 20);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isIntegerLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset <= 20",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("shoesInCloset", 20);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isStringGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name > 'John'", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan("name", "John");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isStringGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name >= 'John'", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("name", "John");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isStringLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name < 'John'", Person.class);

		assertTrue(personsFromJPQL.size() == 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan("name", "John");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isStringLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name <= 'John'", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("name", "John");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDateGreaterThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstJobDate = formatter.parse("01/01/2015");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstJobDate", firstJobDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate > :firstJobDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan("firstJobDate", firstJobDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDateGreaterOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstJobDate = formatter.parse("01/01/2015");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstJobDate", firstJobDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate >= :firstJobDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("firstJobDate", firstJobDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDateLessThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstJobDate = formatter.parse("01/01/2015");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstJobDate", firstJobDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate < :firstJobDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() == 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan("firstJobDate", firstJobDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDateLessOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstJobDate = formatter.parse("01/01/2015");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstJobDate", firstJobDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.firstJobDate <= :firstJobDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("firstJobDate", firstJobDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isCalendarGreaterThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar birthDayDate = Calendar.getInstance();
		birthDayDate.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", birthDayDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate > :birthDayDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan("birthDayDate", birthDayDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isCalendarGreaterOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar birthDayDate = Calendar.getInstance();
		birthDayDate.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", birthDayDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate >= :birthDayDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("birthDayDate", birthDayDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isCalendarLessThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar birthDayDate = Calendar.getInstance();
		birthDayDate.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", birthDayDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate < :birthDayDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() == 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan("birthDayDate", birthDayDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isCalendarLessOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar birthDayDate = Calendar.getInstance();
		birthDayDate.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", birthDayDate);

		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate <= :birthDayDate",
				Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("birthDayDate", birthDayDate);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isIntegerBetweenWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset between 9 and 21",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween("shoesInCloset", 9, 21);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLongBetweenWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.totalBooksOwned between 19 and 31", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween("totalBooksOwned", 19L, 31L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isStringBetweenWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name between 'A' and 'L'",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andBetween("name", "A", "L");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDateBetweenWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date valueA = formatter.parse("1/1/2014");
		Date valueB = formatter.parse("1/1/2017");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("valueA", valueA);
		parameters.put("valueB", valueB);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.firstJobDate between :valueA and :valueB", Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween("firstJobDate", valueA, valueB);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isCalendarBetweenWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateA = formatter.parse("5/5/2000");
		Date dateB = formatter.parse("5/5/2003");
		Calendar valueA = Calendar.getInstance();
		valueA.setTime(dateA);
		Calendar valueB = Calendar.getInstance();
		valueB.setTime(dateB);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("valueA", valueA);
		parameters.put("valueB", valueB);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.birthDayDate between :valueA and :valueB", Person.class, parameters);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween("birthDayDate", valueA, valueB);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDoubleBetweenWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.height between 9.00d and 12.00d", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween("height", 9.00d, 12.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isFloatBetweenWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where p.weight between 9.00f and 12.00f", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween("weight", 9.00f, 12.00f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isNullWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.nickName is null", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria1 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria1.andIsNull("nickName");
		assertTrue(personCriteria1.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria1.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate is null", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria2 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria2.andIsNull("birthDayDate");
		assertTrue(personCriteria2.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria2.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset is null", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria3 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria3.andIsNull("shoesInCloset");
		assertTrue(personCriteria3.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria3.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.car is null", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria4 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria4.andIsNull("car");
		assertTrue(personCriteria4.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria4.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isNotNullWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.nickName is not null",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria1 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria1.andIsNotNull("nickName");
		assertTrue(personCriteria1.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria1.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.birthDayDate is not null", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria2 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria2.andIsNotNull("birthDayDate");
		assertTrue(personCriteria2.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria2.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.shoesInCloset is not null", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria3 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria3.andIsNotNull("shoesInCloset");
		assertTrue(personCriteria3.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria3.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.car is not null", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria4 = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria4.andIsNotNull("car");
		assertTrue(personCriteria4.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria4.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isListIsEmptyWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.dogs is empty", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andCollectionIsEmpty("dogs");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isCollectionIsEmptyWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.cats is empty", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andCollectionIsEmpty("cats");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isSetIsEmptyWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.certifications is empty",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andCollectionIsEmpty("certifications");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isListIsNotEmptyWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.dogs is not empty", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andCollectionIsNotEmpty("dogs");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isCollectionIsNotEmptyWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.cats is not empty", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andCollectionIsNotEmpty("cats");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isSetIsNotEmptyWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.certifications is not empty",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andCollectionIsNotEmpty("certifications");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isStringLikeWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name like 'M%'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringLike("name", "M%");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.name like '%y'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringLike("name", "%y");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isStringNotLikeWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name not like 'M%'",
				Person.class);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringNotLike("name", "M%");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where p.name not like '%y'", Person.class);

		personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringNotLike("name", "%y");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isStringInWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name in ('John', 'Mary')",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Mary");

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringIn("name", names);
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isStringNotInWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where p.name not in ('John', 'Mary')",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Mary");

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringNotIn("name", names);
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isOrderByWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p order by p.shoesInCloset asc, p.name desc", Person.class);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.orderByAsc("shoesInCloset").orderByDesc("name");

		List<Person> easyCriteriaResult = personCriteria.getResultList();

		assertTrue(easyCriteriaResult.size() == personsFromJPQL.size());
		assertTrue(easyCriteriaResult.containsAll(personsFromJPQL));

		for (int i = 0; i < personsFromJPQL.size(); i++) {
			assertEquals(personsFromJPQL.get(i), easyCriteriaResult.get(i));
		}
	}

	@Test
	public void isInnerJoinWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("dogs");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isInnerJoinWithDistinctWorking() {
		List<Person> personsFromJPQLWithOutDistinct = getListFromJPQL("select p from Person p join p.dogs d",
				Person.class);

		List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p join p.dogs d", Person.class);

		assertTrue(personsFromJPQL.size() > 0);
		assertTrue(personsFromJPQL.size() < personsFromJPQLWithOutDistinct.size());

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("dogs");
		easyCriteria.setDistinctTrue();

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertNotEquals(personsFromJPQLWithOutDistinct.size(), easyCriteriaResult.size());

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLeftJoinWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p left join p.dogs d",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("dogs");
		easyCriteria.setDistinctTrue();

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLeftJoinWithDistinctWorking() {
		List<Person> personsFromJPQLWithOutDistinct = getListFromJPQL("select p from Person p left join p.dogs d",
				Person.class);

		List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p left join p.dogs d",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);
		assertTrue(personsFromJPQL.size() < personsFromJPQLWithOutDistinct.size());

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("dogs");
		easyCriteria.setDistinctTrue();

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertNotEquals(personsFromJPQLWithOutDistinct.size(), easyCriteriaResult.size());

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isInnerJoinFetchWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p inner join fetch p.dogs",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoinFetch("dogs");
		easyCriteria.setDistinctTrue();

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isLeftJoinFetchWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select distinct p from Person p left join fetch p.dogs",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("dogs");
		easyCriteria.setDistinctTrue();

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isPaginationWorking() {
		EntityManager em = getEntityManager();

		TypedQuery<Person> query = em.createQuery("select d from Person d", Person.class);
		query.setFirstResult(1);
		query.setMaxResults(1);

		List<Person> personsFirstResult = query.getResultList();
		query.setFirstResult(2);
		query.setMaxResults(1);

		List<Person> personsSecondResult = query.getResultList();
		assertFalse(personsFirstResult.get(0).equals(personsSecondResult.get(0)));
		assertFalse(personsSecondResult.containsAll(personsFirstResult));

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.setFirstResult(1);
		easyCriteria.setMaxResults(1);

		List<Person> persons = easyCriteria.getResultList();
		assertEquals(persons.size(), personsFirstResult.size());
		assertTrue(persons.containsAll(personsFirstResult));

		easyCriteria.setFirstResult(2);
		easyCriteria.setMaxResults(1);
		persons = easyCriteria.getResultList();

		assertEquals(persons.size(), personsSecondResult.size());
		assertTrue(persons.containsAll(personsSecondResult));
	}

	@Test
	public void isWhereInnerJoinWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d inner join d.person p where p.name = 'John'",
				Dog.class);
		assertTrue(dogsFromJPQL.size() > 0);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andEquals("person.name", "John");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalStateException.class)
	public void isExceptionThrownWhenJoinsIsEmpty() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andEquals("dogs.name", "Dark");
	}

	@Test(expected = IllegalStateException.class)
	public void isExceptionThrownWhenMultipleJoinsIsEmpty() {
		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.andEquals("color.manufacturer", "Dark");
	}

	@Test(expected = IllegalStateException.class)
	public void isExceptionThrownWhenJoinIsNotFound() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car");
		easyCriteria.andEquals("dogs.name", "Dark");
	}

	@Test(expected = IllegalStateException.class)
	public void isExceptionThrownWhenMultipleJoinIsNotFound() {
		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andEquals("color.manufacturer", "Dark");
	}

	@Test
	public void isWhereLeftJoinWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p left join p.dogs d where d.name = 'Fire'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		// Workaround for OpenJPA
		if (isOpenJPA()) {
			easyCriteria.setDistinctTrue();
		}

		easyCriteria.leftJoin("dogs");
		easyCriteria.andEquals("dogs.name", "Fire");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isWhereInnerJoinFetchWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.name = 'Fire'",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		if (isOpenJPA()) {
			easyCriteria.setDistinctTrue();
		}

		easyCriteria.innerJoinFetch("dogs");
		easyCriteria.andEquals("dogs.name", "Fire");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isWhereJoinNotEqualsWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.name <> 'Fire'",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		if (isOpenJPA()) {
			easyCriteria.setDistinctTrue();
		}

		easyCriteria.innerJoinFetch("dogs");
		easyCriteria.andNotEquals("dogs.name", "Fire");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(easyCriteriaResult.containsAll(personsFromJPQL));
	}

	@Test
	public void isWhereLeftJoinFetchWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p, IN(p.dogs) d  where d.name = 'Dark'",
				Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("dogs");
		easyCriteria.andEquals("dogs.name", "Dark");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinIntegerGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.toysTotal > 5",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		// Workaround for OpenJPA
		if (isOpenJPA()) {
			easyCriteria.setDistinctTrue();
		}

		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterThan("dogs.toysTotal", 5);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinIntegerGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.toysTotal >= 5", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterOrEqualTo("dogs.toysTotal", 5);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinIntegerLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.toysTotal < 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessThan("dogs.toysTotal", 13);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinIntegerLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.toysTotal <= 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessOrEqualTo("dogs.toysTotal", 13);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinLongGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d where d.fleasTotal > 5",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		// Workaround for OpenJPA
		if (isOpenJPA()) {
			easyCriteria.setDistinctTrue();
		}

		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterThan("dogs.fleasTotal", 5L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinLongGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.fleasTotal >= 5", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterOrEqualTo("dogs.fleasTotal", 5L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinLongLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.fleasTotal < 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessThan("dogs.fleasTotal", 13L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinLongLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.fleasTotal <= 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessOrEqualTo("dogs.fleasTotal", 13L);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDoubleGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.weight > 5", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterThan("dogs.weight", 5.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDoubleGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.weight >= 5", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterOrEqualTo("dogs.weight", 5.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDoubleLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.weight < 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessThan("dogs.weight", 13.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDoubleLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.weight <= 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessOrEqualTo("dogs.weight", 13.00d);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinFloatGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.hairSize > 5", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterThan("dogs.hairSize", 5.00f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinFloatGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.hairSize >= 5", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterOrEqualTo("dogs.hairSize", 5.00f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinFloatLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.hairSize < 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessThan("dogs.hairSize", 13.00f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinFloatLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.hairSize <= 13", Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessOrEqualTo("dogs.hairSize", 13.00f);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDateGreaterThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfBirth = formatter.parse("19/10/2005");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("dateOfBirth", dateOfBirth);

		List<Person> personFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.dateOfBirth > :dateOfBirth", Person.class,
				parameters);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterThan("dogs.dateOfBirth", dateOfBirth);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDateGreaterOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfBirth = formatter.parse("19/10/2005");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("dateOfBirth", dateOfBirth);

		List<Person> personFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.dateOfBirth >= :dateOfBirth", Person.class,
				parameters);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andGreaterOrEqualTo("dogs.dateOfBirth", dateOfBirth);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDateLessThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfBirth = formatter.parse("19/10/2005");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("dateOfBirth", dateOfBirth);

		List<Person> personFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.dateOfBirth < :dateOfBirth", Person.class,
				parameters);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessThan("dogs.dateOfBirth", dateOfBirth);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDateLessOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfBirth = formatter.parse("01/01/2009");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("dateOfBirth", dateOfBirth);

		List<Person> personFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.dateOfBirth <= :dateOfBirth", Person.class,
				parameters);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andLessOrEqualTo("dogs.dateOfBirth", dateOfBirth);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinCalendarGreaterThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", dateOfBirth);

		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.birthDayDate > :birthDayDate", Dog.class, parameters);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterThan("person.birthDayDate", dateOfBirth);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinCalendarGreaterOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", dateOfBirth);

		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.birthDayDate >= :birthDayDate", Dog.class, parameters);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterOrEqualTo("person.birthDayDate", dateOfBirth);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinCalendarLessThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", dateOfBirth);

		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.birthDayDate < :birthDayDate", Dog.class, parameters);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessThan("person.birthDayDate", dateOfBirth);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinCalendarLessOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("birthDayDate", dateOfBirth);

		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.birthDayDate <= :birthDayDate", Dog.class, parameters);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessOrEqualTo("person.birthDayDate", dateOfBirth);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinStringGreaterThanWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name > 'Mary'", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterThan("person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinStringGreaterOrEqualToWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name >= 'Mary'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterOrEqualTo("person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinStringLessThanWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name < 'Mary'", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessThan("person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinStringLessOrEqualToWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name <= 'Mary'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessOrEqualTo("person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinIntegerBetweenWorking() {
		List<Dog> personsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.clothesInCloset between 30 and 35", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween("person.clothesInCloset", 30, 35);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinLongBetweenWorking() {
		List<Dog> personsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.totalBooksOwned between 19 and 31", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween("person.totalBooksOwned", 19L, 31L);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDoubleBetweenWorking() {
		List<Dog> personsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.height between 9.00 and 12.00", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween("person.height", 9.00d, 12.00d);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinFloatBetweenWorking() {
		List<Dog> personsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.weight between 9.00 and 12.00", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween("person.weight", 9.00f, 12.00f);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinStringBetweenWorking() {
		List<Dog> personsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.name between 'A' and 'L'", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween("person.name", "A", "L");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinDateBetweenWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date valueA = formatter.parse("1/1/2008");
		Date valueB = formatter.parse("1/1/2010");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("valueA", valueA);
		parameters.put("valueB", valueB);

		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.dogs d where d.dateOfBirth between :valueA and :valueB",
				Person.class, parameters);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		// Workaround for OpenJPA
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("dogs");
		easyCriteria.andBetween("dogs.dateOfBirth", valueA, valueB);

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinCalendarBetweenWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateA = formatter.parse("1/1/2000");
		Date dateB = formatter.parse("5/5/2002");
		Calendar valueA = Calendar.getInstance();
		valueA.setTime(dateA);
		Calendar valueB = Calendar.getInstance();
		valueB.setTime(dateB);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("valueA", valueA);
		parameters.put("valueB", valueB);

		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.birthDayDate between :valueA and :valueB", Dog.class,
				parameters);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween("person.birthDayDate", valueA, valueB);

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());
		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinAttributeNullWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.nickName is null",
				Car.class);

		EasyQuery<Car> carCriteria1 = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		carCriteria1.innerJoin("person");
		carCriteria1.andIsNull("person.nickName");
		
		assertTrue(carCriteria1.getResultList().size() == carsFromJPQL.size());
		assertTrue(carCriteria1.getResultList().containsAll(carsFromJPQL));

		carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.birthDayDate is null", Car.class);

		EasyQuery<Car> carCriteria2 = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		carCriteria2.innerJoin("person");
		carCriteria2.andIsNull("person.birthDayDate");
		assertTrue(carCriteria2.getResultList().size() == carsFromJPQL.size());
		assertTrue(carCriteria2.getResultList().containsAll(carsFromJPQL));

		carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.clothesInCloset is null", Car.class);

		EasyQuery<Car> carCriteria3 = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		carCriteria3.innerJoin("person");
		carCriteria3.andIsNull("person.clothesInCloset");
		assertTrue(carCriteria3.getResultList().size() == carsFromJPQL.size());
		assertTrue(carCriteria3.getResultList().containsAll(carsFromJPQL));

		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.car is null", Dog.class);

		EasyQuery<Dog> dogCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		dogCriteria.innerJoin("person");
		dogCriteria.andIsNull("person.car");
		assertTrue(dogCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(dogCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinAttributeNotNullWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.nickName is not null",
				Car.class);

		EasyQuery<Car> carCriteria1 = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		carCriteria1.innerJoin("person");
		carCriteria1.andIsNotNull("person.nickName");
		
		assertTrue(carCriteria1.getResultList().size() == carsFromJPQL.size());
		assertTrue(carCriteria1.getResultList().containsAll(carsFromJPQL));

		carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.birthDayDate is not null", Car.class);

		EasyQuery<Car> carCriteria2 = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		carCriteria2.innerJoin("person");
		carCriteria2.andIsNotNull("person.birthDayDate");
		assertTrue(carCriteria2.getResultList().size() == carsFromJPQL.size());
		assertTrue(carCriteria2.getResultList().containsAll(carsFromJPQL));

		carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.clothesInCloset is not null",
				Car.class);

		EasyQuery<Car> carCriteria3 = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		carCriteria3.innerJoin("person");
		carCriteria3.andIsNotNull("person.clothesInCloset");
		assertTrue(carCriteria3.getResultList().size() == carsFromJPQL.size());
		assertTrue(carCriteria3.getResultList().containsAll(carsFromJPQL));

		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.car is not null",
				Dog.class);

		EasyQuery<Dog> dogCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		dogCriteria.innerJoin("person");
		dogCriteria.andIsNotNull("person.car");
		assertTrue(dogCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(dogCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinListIsEmptyWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.dogs is empty", Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andCollectionIsEmpty("person.dogs");

		assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
	}

	@Test
	public void isJoinCollectionIsEmptyWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.cats is empty", Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andCollectionIsEmpty("person.cats");

		assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
	}

	@Test
	public void isJoinSetIsEmptyWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.certifications is empty",
				Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andCollectionIsEmpty("person.certifications");

		assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
	}

	@Test
	public void isJoinListIsNotEmptyWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.dogs is not empty",
				Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.innerJoin("person");
		//easyCriteria.andJoinListIsNotEmpty("person", "dogs");

		assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
	}

	@Test
	public void isJoinCollectionIsNotEmptyWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c join c.person p where p.cats is not empty",
				Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andCollectionIsNotEmpty("person.cats");

		assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
	}

	@Test
	public void isJoinSetIsNotEmptyWorking() {
		List<Car> carsFromJPQL = getListFromJPQL(
				"select c from Car c join c.person p where p.certifications is not empty", Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andCollectionIsNotEmpty("person.certifications");

		assertTrue(easyCriteria.getResultList().size() == carsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(carsFromJPQL));
	}

	@Test
	public void isJoinStringLikeWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name like 'M%'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringLike("person.name", "M%");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

		dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name like '%y'", Dog.class);

		easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringLike("person.name", "%y");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinStringNotLikeWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name not like 'M%'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringNotLike("person.name", "M%");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

		dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where p.name not like '%y'", Dog.class);

		easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());

		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("person");
		easyCriteria.andStringNotLike("person.name", "%y");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinStringInWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.name in ('Mary', 'John')", Dog.class);

		List<String> names = new ArrayList<String>();
		names.add("Mary");
		names.add("John");

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringIn("person.name", names);
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinStringNotInWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where p.name not in ('Mary', 'John')", Dog.class);

		List<String> names = new ArrayList<String>();
		names.add("Mary");
		names.add("John");

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringNotIn("person.name", names);
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isBigDecimalGreaterThanWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight > 20", Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());

		easyCriteria.andGreaterThan("weight", new BigDecimal(20));

		List<Car> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isBigDecimalGreaterOrEqualToWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight >= 20", Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo("weight", new BigDecimal(20));

		List<Car> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isBigDecimalLessThanWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight < 30", Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());

		easyCriteria.andLessThan("weight", new BigDecimal(30));

		List<Car> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isBigDecimalLessOrEqualToWorking() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight <= 30", Car.class);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());

		easyCriteria.andLessOrEqualTo("weight", new BigDecimal(30));

		List<Car> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinBigDecimalGreaterThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight > 20",
				Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car");

		easyCriteria.andGreaterThan("car.weight", new BigDecimal(20));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinBigDecimalGreaterOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight >= 20",
				Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car");

		easyCriteria.andGreaterOrEqualTo("car.weight", new BigDecimal(20));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinBigDecimalLessThanWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight < 30",
				Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car");

		easyCriteria.andLessThan("car.weight", new BigDecimal(30));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isJoinBigDecimalLessOrEqualToWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car c where c.weight <= 30",
				Person.class);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car");

		easyCriteria.andLessOrEqualTo("car.weight", new BigDecimal(30));

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isTwoOrIdsWorking() {
		List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.id = 1 or s.id = 2", Song.class);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.orEquals("id", 1).orEquals("id", 2);

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isTwoOrAttributesWorking() {
		List<Song> songsFromJPQL = getListFromJPQL("select s from Song s where s.id = 1 or s.length = 20", Song.class);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.orEquals("id", 1).orEquals("length", 20);

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isThreeOrAttributesWorking() {
		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where s.id = 1 or s.length = 40 or s.artist = 'Group 1 Crew'", Song.class);

		assertTrue(songsFromJPQL.size() == 3);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.orEquals("id", 1).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isThreeOrAttributesWithOneRepeatedWorking() {
		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where (s.id = 1) or (s.id = 2) or (s.length = 40) or (s.artist = 'Group 1 Crew')",
				Song.class);

		assertTrue(songsFromJPQL.size() == 4);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.orEquals("id", 1, 2).orEquals("length", 40).orEquals("artist", "Group 1 Crew");

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isOrAndOneEqualsWorking() {
		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where s.artist = 'Red' and (s.id = 11 or s.id = 12 or s.id = 13)", Song.class);

		assertTrue(songsFromJPQL.size() == 3);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.andEquals("artist", "Red").orEquals("id", 11, 12, 13);

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isOrAndTwoEqualsWorking() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", SongType.ROCK);

		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where s.artist = 'Red' and s.type = :type and (s.id = 11 or s.id = 12 or s.id = 13)",
				Song.class, parameters);

		assertTrue(songsFromJPQL.size() == 3);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 11, 12, 13);

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isDifferentOrAndTwoEqualsWorking() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", SongType.ROCK);

		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where s.artist = 'Red' and s.type = :type and (s.name = 'Breath Into Me' or s.id = 12)",
				Song.class, parameters);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.andEquals("artist", "Red").andEquals("type", SongType.ROCK).orEquals("id", 12)
				.orEquals("name", "Breath Into Me");

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isTwoOrGroupedInsideAndWithIntStringWorking() {
		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where (s.id = 1 or s.id = 2) and (s.name = 'Sing Out' or s.name = 'Alive')",
				Song.class);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.orEquals("id", "1")
					.orEquals("id", "2")
					.orEquals(2, "name", "Sing Out")
					.orEquals(2, "name", "Alive");

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void isTwoOrGroupedInsideAndWithMoreAttributesWorking() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", SongType.PRAISE);

		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where (s.totalDownloads = 20 or s.weight = 10.00) and (s.price = 20.00 or s.type = :type)",
				Song.class, parameters);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.orEquals("totalDownloads", 20L).orEquals("weight", 10.00f).orEquals(2, "price", 20.00d)
				.orEquals(2, "type", SongType.PRAISE);

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void addAndSeparatedByOr() {
		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where (s.id = 1 and s.name = 'Sing Out') or (s.id = 2 and s.name = 'Alive')",
				Song.class);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(1, "name", "Sing Out")
				    .addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(2, "name", "Alive");

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void addAndSeparatedByOrOtherParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", SongType.PRAISE);

		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where (s.totalDownloads = 20 and s.price = 20.00) "
									   +"or (s.weight = 10.00 and s.type = :type)",
				Song.class, parameters);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.addAndSeparatedByOr(1, "totalDownloads", 20L)
					.addAndSeparatedByOr(1, "price", 20.00d)
					.addAndSeparatedByOr(2, "weight", 10.00f)
					.addAndSeparatedByOr(2, "type", SongType.PRAISE);

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void addOrWithDecimal() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight = 20 or c.weight = 30", Car.class);

		assertTrue(carsFromJPQL.size() == 3);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());

		easyCriteria.orEquals("weight", new BigDecimal(20), new BigDecimal(30));

		List<Car> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test
	public void addBetweenWithDecimal() {
		List<Car> carsFromJPQL = getListFromJPQL("select c from Car c where c.weight between 19 and 31", Car.class);

		assertTrue(carsFromJPQL.size() == 3);

		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());

		easyCriteria.andBetween("weight", new BigDecimal(20), new BigDecimal(30));

		List<Car> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(carsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(carsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isWithErrorWithNotAllowedAttributeGreaterThan() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andGreaterThan("car", new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isWithErrorWithNotAllowedAttributeGreaterOrEqualTo() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andGreaterOrEqualTo("car", new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isWithErrorWithNotAllowedAttributeLessThan() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andLessThan("car", new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isWithErrorWithNotAllowedAttributeLessOrEqualTo() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andLessOrEqualTo("car", new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isWithErrorWithNotAllowedAttributeBetween() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andBetween("car", 1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownClassInnerJoin() {
		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());
		easyCriteria.innerJoin("invalidClass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownClassLeftJoin() {
		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());
		easyCriteria.leftJoin("invalidClass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownClassInnerFetchJoin() {
		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());
		easyCriteria.innerJoinFetch("invalidClass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownClassLeftFetchJoin() {
		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());
		easyCriteria.leftJoinFetch("invalidClass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownJoinAttributeGreaterThan() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoinFetch("dogs");
		easyCriteria.andGreaterThan("dogs.person", "Fire");
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownJoinAttributeGreaterOrEqualTo() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car");
		easyCriteria.andGreaterOrEqualTo("car.person", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownJoinAttributeLessThan() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car");
		easyCriteria.andLessThan("car.person", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnUnknownJoinAttributeLessOrEqualTo() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car");
		easyCriteria.andLessOrEqualTo("car.person", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnNotAllowedCollectionIsEmpty() {
		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.andCollectionIsEmpty("person");
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnNotAllowedCollectionIsNotEmpty() {
		EasyQuery<Car> easyCriteria = new EasyQueryImpl<Car>(Car.class, getEntityManager());
		easyCriteria.andCollectionIsNotEmpty("person");
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorOnJoinBetweenNotAllowedType() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car");
		easyCriteria.andBetween("car.person", null, null);
	}

	@Test
	public void isEqualsLowerCaseWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andEquals("name", CodeGenerator.PERSON01_NAME.toLowerCase());

		assertEquals(0, easyCriteria.getResultList().size());

		easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase());

		assertNotEquals(0, easyCriteria.getResultList().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isEqualsLowerCaseThrowingException() {
		boolean toLowerCase = true;
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andEquals(toLowerCase, "name", 123);
	}

	@Test
	public void isOrEqualsWithLowerCaseWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orEquals("name", CodeGenerator.PERSON01_NAME.toLowerCase(),
								      CodeGenerator.PERSON02_NAME.toLowerCase());
		assertEquals(0, easyCriteria.getResultList().size());

		easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orEquals(true, "name", CodeGenerator.PERSON01_NAME.toLowerCase(),
											 CodeGenerator.PERSON02_NAME.toLowerCase());
		assertEquals(2, easyCriteria.getResultList().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isOrEqualsWithLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.orEquals(true, "name", 123, 123, 123);
	}

	@Test
	public void isOrEqualsWithIndexLowerCaseWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.orEquals(1, "name", CodeGenerator.PERSON01_NAME.toLowerCase())
					.orEquals(2, "name", CodeGenerator.PERSON02_NAME.toLowerCase());

		assertEquals(0, easyCriteria.getResultList().size());

		easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.orEquals(1, "id", 1).orEquals(1, "id", 2)
				.orEquals(2, "name", CodeGenerator.PERSON01_NAME.toLowerCase())
				.orEquals(true, 2, "name", CodeGenerator.PERSON02_NAME.toLowerCase());

		assertEquals(1, easyCriteria.getResultList().size());

		easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.orEquals(1, "id", 1).orEquals(1, "id", 2)
				.orEquals(true, 2, "name", CodeGenerator.PERSON01_NAME.toLowerCase())
				.orEquals(true, 2, "name", CodeGenerator.PERSON02_NAME.toLowerCase());

		assertEquals(2, easyCriteria.getResultList().size());
	}

	@Test
	public void isNotAndEqualsWithLowerCaseWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andEquals("name", CodeGenerator.PERSON02_NAME);
		assertEquals(1, easyCriteria.getResultList().size());

		easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		TypedQuery<Person> query = getEntityManager().createQuery("select p from Person p", Person.class);

		easyCriteria.andNotEquals("name", CodeGenerator.PERSON02_NAME.toLowerCase());
		assertEquals(query.getResultList().size(), easyCriteria.getResultList().size());

		easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andNotEquals(true, "name", CodeGenerator.PERSON02_NAME);
		assertEquals(query.getResultList().size() - 1, easyCriteria.getResultList().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isAndNotEqualsWithLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andNotEquals(true, "name", 123);
	}

	@Test
	public void isOrNotEqualsLowerCaseWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.orNotEquals("name", CodeGenerator.PERSON01_NAME);
		List<Person> resultList = easyCriteria.getResultList();

		easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.orNotEquals(true, "name", CodeGenerator.PERSON01_NAME);

		assertEquals(easyCriteria.getResultList().size(), resultList.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void isOrNotEqualsLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.orNotEquals(true, "name", 123);
	}

	@Test
	public void isStringGreaterThanLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where LOWER(p.name) > LOWER('John')",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan(true, "name", "John");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isStringGreaterThanLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterThan(true, "name", 123);
	}

	@Test
	public void isStringGreaterOrEqualToLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where LOWER(p.name) >= 'john'",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo(true, "name", "john");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isStringGreaterOrEqualToLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andGreaterOrEqualTo(true, "name", 123);
	}

	@Test
	public void isStringLessThanLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where LOWER(p.name) < 'john'",
				Person.class);

		assertTrue(personsFromJPQL.size() == 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan(true, "name", "John");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isStringLessThanLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andLessThan(true, "name", 123);
	}

	@Test
	public void isStringLessOrEqualToLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) <= 'john'",
				Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andLessOrEqualTo(true, "name", "John");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());
		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isStringLessOrEqualToLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.andLessOrEqualTo(true, "name", 123);
	}

	@Test
	public void isStringBetweenLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where lower(p.name) between 'a' and 'l'", Person.class);

		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween(true, "name", "A".toLowerCase(), "L".toLowerCase());

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isStringBetweenLowerCaseExceptionParameter1Working() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween(true, "name", 123, "L");
	}

	@Test(expected = IllegalArgumentException.class)
	public void isStringBetweenLowerCaseExceptionParameter2Working() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		easyCriteria.andBetween(true, "name", "L", 123);
	}

	@Test
	public void isStringLikeLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) like 'm%'",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringLike(true, "name", "m%");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) like '%y'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringLike(true, "name", "%y");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isStringNotLikeLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) not like 'm%'",
				Person.class);

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringNotLike(true, "name", "m%");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));

		personsFromJPQL = getListFromJPQL("select p from Person p where lower(p.name) not like '%y'", Person.class);

		personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringNotLike(true, "name", "%y");
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isStringInLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where lower(p.name) in ('john', 'mary')", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Mary");

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringIn(true, "name", names);
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isStringNotInLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p where LOWER(p.name) not in ('john', 'mary')", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Mary");

		EasyQuery<Person> personCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		personCriteria.andStringNotIn(true, "name", names);
		assertTrue(personCriteria.getResultList().size() == personsFromJPQL.size());
		assertTrue(personCriteria.getResultList().containsAll(personsFromJPQL));
	}

	@Test
	public void isWhereInnerJoinLowerCaseLowerCaseWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d inner join d.person p where lower(p.name) = 'john'", Dog.class);
		assertTrue(dogsFromJPQL.size() > 0);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andEquals(true, "person.name", "John");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isWhereInnerJoinLowerCaseLowerCaseExceptionWorking() {
		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andEquals(true, "person.name", 123);
	}

	@Test
	public void isWhereJoinNotEqualsLowerCaseWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p join p.dogs d where lower(d.name) <> 'fire'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());

		if (isOpenJPA()) {
			easyCriteria.setDistinctTrue();
		}

		easyCriteria.innerJoinFetch("dogs");
		easyCriteria.andNotEquals(true, "dogs.name", "Fire");

		List<Person> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(easyCriteriaResult.containsAll(personsFromJPQL));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isWhereJoinNotEqualsLowerCaseExceptionWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoinFetch("dogs");
		easyCriteria.andNotEquals(true, "dogs.name", 123);
	}

	@Test
	public void isJoinStringGreaterThanLowerCaseWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) > 'mary'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterThan(true,"person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isJoinStringGreaterThanLowerCaseExceptionWorking() {
		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterThan(true, "person.name", 123);
	}

	@Test
	public void isJoinStringGreaterOrEqualToLowerCaseWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) >= 'mary'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterOrEqualTo(true, "person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isJoinStringGreaterOrEqualToLowerCaseExceptionWorking() {
		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andGreaterOrEqualTo(true, "person.name", 123);
	}

	@Test
	public void isJoinStringLessThanLowerCaseWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) < 'mary'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessThan(true, "person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isJoinStringLessThanLowerCaseExceptionWorking() {
		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessThan(true, "person.name", 123);
	}

	@Test
	public void isJoinStringLessOrEqualToLowerCaseWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) <= 'mary'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessOrEqualTo(true, "person.name", "Mary");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(dogsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(dogsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isJoinStringLessOrEqualToLowerCaseExceptionWorking() {
		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andLessOrEqualTo(true, "person.name", 1234);
	}

	@Test
	public void isJoinStringBetweenLowerCaseWorking() {
		List<Dog> personsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where lower(p.name) between 'a' and 'l'", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween(true, "person.name", "a", "l");

		List<Dog> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isJoinStringBetweenLowerCaseExceptionParameter1Working() {
		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween(true, "person.name", 1, "l");
	}

	@Test(expected = IllegalArgumentException.class)
	public void isJoinStringBetweenLowerCaseExceptionParameter2Working() {
		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andBetween(true, "person.name", "a", 2);
	}

	@Test
	public void isJoinStringLikeLowerCaseWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) like 'm%'",
				Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringLike(true, "person.name", "M%");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

		dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) like '%y'", Dog.class);

		easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringLike(true, "person.name", "%y");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinStringNotLikeExceptionWorking() {
		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where lower(p.name) not like 'm%'", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringNotLike(true, "person.name", "M%");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));

		dogsFromJPQL = getListFromJPQL("select d from Dog d join d.person p where lower(p.name) not like '%y'",
				Dog.class);

		easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());

		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("person");
		easyCriteria.andStringNotLike(true, "person.name", "%y");
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinStringInCaseWorking() {
		List<String> names = new ArrayList<String>();
		names.add("Mary");
		names.add("John");

		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where lower(p.name) in ('mary', 'john')", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringIn(true, "person.name", names);
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void isJoinStringNotInExceptionWorking() {
		List<String> names = new ArrayList<String>();
		names.add("Mary");
		names.add("John");

		List<Dog> dogsFromJPQL = getListFromJPQL(
				"select d from Dog d join d.person p where lower(p.name) not in ('mary', 'john')", Dog.class);

		EasyQuery<Dog> easyCriteria = new EasyQueryImpl<Dog>(Dog.class, getEntityManager());
		easyCriteria.innerJoin("person");
		easyCriteria.andStringNotIn(true, "person.name", names);
		assertTrue(easyCriteria.getResultList().size() == dogsFromJPQL.size());
		assertTrue(easyCriteria.getResultList().containsAll(dogsFromJPQL));
	}

	@Test
	public void addAndSeparatedByOrLowerCase() {
		List<Song> songsFromJPQL = getListFromJPQL(
				"select s from Song s where (s.id = 1 and lower(s.name) = 'sing out') or (s.id = 2 and lower(s.name) = 'alive')",
				Song.class);

		assertTrue(songsFromJPQL.size() == 2);

		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(true, 1, "name", "Sing Out".toLowerCase())
				    .addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(true, 2, "name", "Alive".toLowerCase());

		List<Song> easyCriteriaResult = easyCriteria.getResultList();

		assertEquals(songsFromJPQL.size(), easyCriteriaResult.size());

		assertTrue(songsFromJPQL.containsAll(easyCriteriaResult));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addAndSeparatedByOrLowerCaseException() {
		EasyQuery<Song> easyCriteria = new EasyQueryImpl<Song>(Song.class, getEntityManager());

		easyCriteria.addAndSeparatedByOr(1, "id", 1).addAndSeparatedByOr(true, 1, "name", 123123)
				.addAndSeparatedByOr(2, "id", 2).addAndSeparatedByOr(true, 2, "name", "Alive");
	}

	/*
	 * @Test(expected = IllegalArgumentException.class) public void
	 * isThrowingExceptionWhenPassingWrongArgumentToFabric(){
	 * EasyCriteriaFactory.createQueryCriteria(getEntityManager(), Person.class, new InvalidEasyCriteriaClass()); }
	 */

	@Test
	public void isMultipleJoin1LevelWorking() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.car ca join ca.color co",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car.color");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoin2LevelsWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p join p.car ca join ca.color co join co.manufacturer nanu", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car.color.manufacturer");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoin3LevelsWorking() {
		String query = "select p from Person p " + "join p.car ca " + "join ca.color co "
				+ "join co.manufacturer manu " + "join manu.products pro";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car.color.manufacturer.products");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoin4LevelsWorking() {
		String query = "select p from Person p " + "join p.car ca " + "join ca.color co "
				+ "join co.manufacturer manu " + "join manu.products pro " + "join pro.nickNames ni  ";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car.color.manufacturer.products.nickNames");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinFetch1LevelWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoinFetch("car.color");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleJoinFetch2LevelsWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoinFetch("car.color.manufacturer");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleJoinFetch3LevelsWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoinFetch("car.color.manufacturer.products");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleJoinFetch4LevelsWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoinFetch("car.color.manufacturer.products.nickNames");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleLeftJoin1LevelWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p left join p.car ca left join ca.color co", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("car.color");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoin2LevelsWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p left join p.car ca left join ca.color co left join co.manufacturer nanu",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("car.color.manufacturer");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoin3LevelsWorking() {
		String query = "select p from Person p " + "left join p.car ca " + "left join ca.color co "
				+ "left join co.manufacturer manu " + "left join manu.products pro";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("car.color.manufacturer.products");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoin4LevelsWorking() {
		String query = "select distinct p from Person p " + "left join p.car ca " + "left join ca.color co "
				+ "left join co.manufacturer manu " + "left join manu.products pro " + "left join pro.nickNames ni  ";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.leftJoin("car");
		easyCriteria.leftJoin("car.color");
		easyCriteria.leftJoin("car.color.manufacturer");
		easyCriteria.leftJoin("car.color.manufacturer.products");
		easyCriteria.leftJoin("car.color.manufacturer.products.nickNames");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleMixedJoin4LevelsWorking() {
		String query = "select distinct p from Person p " + "inner join p.car ca " + "left join ca.color co "
				+ "left join co.manufacturer manu " + "left join manu.products pro " + "left join pro.nickNames ni  ";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("car");
		easyCriteria.leftJoin("car.color");
		easyCriteria.leftJoin("car.color.manufacturer");
		easyCriteria.leftJoin("car.color.manufacturer.products");
		easyCriteria.leftJoin("car.color.manufacturer.products.nickNames");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoinFetch1LevelWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car.color");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleLeftJoinFetch2LevelsWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car.color.manufacturer");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleLeftJoinFetch3LevelsWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car.color.manufacturer.products");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleLeftJoinFetch4LevelsWorking() {
		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoinFetch("car.color.manufacturer.products.nickNames");

		List<Person> result = easyCriteria.getResultList();
		assertTrue(result.size() > 0);
	}

	@Test
	public void isMultipleJoinWhereWorking() {  //TODO Bom Teste
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p join p.car ca join ca.color co where co.name = 'Red'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car.color");
		easyCriteria.andEquals("car.color.name", "Red");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoinLevel2WhereWorking() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select p from Person p left join p.car ca join ca.color co where co.name = 'Red'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("car");
		easyCriteria.innerJoin("car.color");
		easyCriteria.andEquals("car.color.name", "Red");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoinLevel3WhereWorking() {
		String query = "select p from Person p " + "left join p.car ca " + "left join ca.color co "
				+ "left join co.manufacturer manu " + "where manu.name = 'Company A'";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("car.color.manufacturer");
		easyCriteria.andEquals("car.color.manufacturer.name", "Company A");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoinLevel3LowerCaseWhereWorking() {
		String query = "select p from Person p " + "left join p.car ca " + "left join ca.color co "
				+ "left join co.manufacturer manu " + "where lower(manu.name) = 'company b'";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("car.color.manufacturer");
		easyCriteria.andEquals(true, "car.color.manufacturer.name", "Company B".toLowerCase());

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleLeftJoinLevel1LowerCaseWhereWorking() {
		String query = "select p from Person p " + "join p.car c " + "where lower(c.name) = lower('Dark Horse')";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("car");
		easyCriteria.andEquals(true, "car.name", "Dark Horse".toLowerCase());

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithEqualsInCollectionsWorking() {
		String query = "select m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name = 'NickName B'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andEquals("products.nickNames.name", "NickName B");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithEqualsLowerCaseInCollectionsWorking() {
		String query = "select m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) = lower('NickName B')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andEquals(true, "products.nickNames.name", "NickName B".toLowerCase());

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithIntegerGreaterThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id > 1";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.id", 1);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithIntegerGreaterOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id >= 1";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.id", 1);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithIntegerLessThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id < 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.id", 2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithIntegerLessOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id <= 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.id", 2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDoubleGreaterThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDouble > 1d";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.justDouble", 1d);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDoubleGreaterOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDouble >= 1d";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justDouble", 1d);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDoubleLessThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDouble < 2d";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justDouble", 2d);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDoubleLessOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDouble <= 2d";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.justDouble", 2d);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithLongGreaterThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justLong > 1";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.justLong", 1L);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithLongGreaterOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justLong >= 1";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justLong", 1L);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithLongLessThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justLong < 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justLong", 2L);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithLongLessOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justLong <= 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.justLong", 2L);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithFloatGreaterThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justFloat > 1F";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.justFloat", 1F);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithFloatGreaterOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justFloat >= 1F";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justFloat", 1F);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithFloatLessThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justFloat < 2F";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justFloat", 2F);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithFloatLessOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justFloat <= 2F";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.justFloat", 2F);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithBigDecimalGreaterThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBigDecimal > 1";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.justBigDecimal", new BigDecimal(1));

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithBigDecimalGreaterOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBigDecimal >= 1";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(1));

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithBigDecimalLessThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBigDecimal < 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justBigDecimal", new BigDecimal(2));

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithBigDecimalLessOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBigDecimal <= 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.justBigDecimal", new BigDecimal(2));

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDateGreaterThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date justDate = formatter.parse("2/2/2002");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justDate", justDate);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDate > :justDate";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.justDate", justDate);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDateGreaterOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date justDate = formatter.parse("2/2/2002");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justDate", justDate);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDate >= :justDate";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justDate", justDate);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDateLessThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date justDate = formatter.parse("2/2/2002");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justDate", justDate);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDate < :justDate";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justDate", justDate);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDateLessOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date justDate = formatter.parse("2/2/2002");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justDate", justDate);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDate <= :justDate";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.justDate", justDate);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCalendarGreaterThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("2/2/2002");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendar", justCalendar);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar > :justCalendar";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.justCalendar", justCalendar);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCalendarGreaterOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("2/2/2002");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendar", justCalendar);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar >= :justCalendar";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justCalendar", justCalendar);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCalendarLessThanWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("2/2/2002");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendar", justCalendar);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar < :justCalendar";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justCalendar", justCalendar);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCalendarLessOrEqualToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("2/2/2002");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendar", justCalendar);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar <= :justCalendar";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.justCalendar", justCalendar);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringGreaterThanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name > '" + CodeGenerator.NICKNAME_B_NAME + "'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringGreaterOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name >= '" + CodeGenerator.NICKNAME_B_NAME + "'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringLessThanWorking() {
		String query = "select distinct m from Manufacturer m join m.products p join p.nickNames n "
				       + "where n.name < '" + CodeGenerator.NICKNAME_B_NAME + "'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringLessOrEqualToWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name <= '" + CodeGenerator.NICKNAME_B_NAME + "'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithEqualsBooleanWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBoolean = true";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andEquals("products.nickNames.justBoolean", true);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithIntegerBetweenWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id between 1 and 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.id", 1, 2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithLongBetweenWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justLong between 1 and 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.justLong", 1l, 2l);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDoubleBetweenWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDouble between 1d and 2d";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.justDouble", 1d, 2d);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithFloatBetweenWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justFloat between 1f and 2f";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.justFloat", 1f, 2f);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithBigDecimalBetweenWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBigDecimal between 1f and 2f";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringBetweenWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name between 'NickName A' and 'NickName B'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria
				.andBetween("products.nickNames.name", CodeGenerator.NICKNAME_A_NAME, CodeGenerator.NICKNAME_B_NAME);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCalendarBetweenWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Date date2 = formatter.parse("2/2/2002");
		Calendar justCalendar2 = Calendar.getInstance();
		justCalendar2.setTime(date2);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendarA", justCalendar);
		parameters.put("justCalendarB", justCalendar2);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar between :justCalendarA and :justCalendarB";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.justCalendar", justCalendar, justCalendar2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithDateBetweenToWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date justDate = formatter.parse("1/1/2001");
		Date justDate2 = formatter.parse("2/2/2002");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justDateA", justDate);
		parameters.put("justDateB", justDate2);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDate between :justDateA and :justDateB";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.justDate", justDate, justDate2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithIsNullAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justString is null";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andIsNull("products.nickNames.justString");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithIsNotNullAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justString is not null";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andIsNotNull("products.nickNames.justString");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithListIsEmptyAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justList is empty";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsEmpty("products.nickNames.justList");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithListIsNotEmptyAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justList is not empty";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsNotEmpty("products.nickNames.justList");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithSetIsEmptyAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justSet is empty";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsEmpty("products.nickNames.justSet");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithSetIsNotEmptyAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justSet is not empty";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsNotEmpty("products.nickNames.justSet");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCollectionIsEmptyAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCollection is empty";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsEmpty("products.nickNames.justCollection");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCollectionIsNotEmptyAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCollection is not empty";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsNotEmpty("products.nickNames.justCollection");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringLikeAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name like '% B'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringLike("products.nickNames.name", "% B");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringNotLikeAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name not like '% B'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotLike("products.nickNames.name", "% B");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringLikeLowerCaseAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) like '% b'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringLike(true, "products.nickNames.name", "% B".toLowerCase());

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringNotLikeLowerCaseAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) not like '% b'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotLike(true, "products.nickNames.name", "% B".toLowerCase());

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringInAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name in ('NickName A', 'NickName B')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A");
		names.add("NickName B");

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringIn("products.nickNames.name", names);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringNotInAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name not in ('NickName A', 'NickName B')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A");
		names.add("NickName B");

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotIn("products.nickNames.name", names);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringInLowerCaseAttributeWorking() {
		String query = "select distinct m from Manufacturer m " 
					+ "join m.products p " 
					+ "join p.nickNames n "
					+ "where lower(n.name) in ('nickname a', 'nickname b')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A".toLowerCase());
		names.add("NickName B".toLowerCase());

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringIn(true, "products.nickNames.name", names);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithStringNotInLowerCaseAttributeWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) not in ('nickname a', 'nickname b')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A");
		names.add("NickName B");

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotIn(true, "products.nickNames.name", names);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithCollectionTestIsEmptyAttributeWorking() {
		String query = "select distinct p from Product p " + "join p.nickNames n " + "join n.justCollection j "
				+ "where j.autoRelationship is empty";
		List<Product> personsFromJPQL = getListFromJPQL(query, Product.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Product> easyCriteria = new EasyQueryImpl<Product>(Product.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("nickNames.justCollection");
		easyCriteria.andCollectionIsEmpty("nickNames.justCollection.autoRelationship");

		List<Product> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrIntegerWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id = 1 or n.id = 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.id", 1, 2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrLongWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justLong = 1 or n.justLong = 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justLong", 1L, 2L);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrDoubleWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDouble = 1d or n.justDouble = 2d";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justDouble", 1d, 2d);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrFloatWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justFloat = 1f or n.justFloat = 2f";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justFloat", 1f, 2f);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrBigDecimalWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBigDecimal = 1 or n.justBigDecimal = 2";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justBigDecimal", new BigDecimal(1), new BigDecimal(2));

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrStringWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name = 'NickName A' or n.name = 'NickName B'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.name", "NickName A", "NickName B");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrStringLowerCaseWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) = 'nickname a' or lower(n.name) = 'nickname b'";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals(true, "products.nickNames.name", "NickName A".toLowerCase(), "NickName B".toLowerCase());

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrDateWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date justDate = formatter.parse("1/1/2001");
		Date justDate2 = formatter.parse("2/2/2002");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justDateA", justDate);
		parameters.put("justDateB", justDate2);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDate = :justDateA or n.justDate = :justDateB";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justDate", justDate, justDate2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrCalendarWorking() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Date date2 = formatter.parse("2/2/2002");
		Calendar justCalendar2 = Calendar.getInstance();
		justCalendar2.setTime(date2);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendarA", justCalendar);
		parameters.put("justCalendarB", justCalendar2);

		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar = :justCalendarA or n.justCalendar = :justCalendarB";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrWithAndWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isMultipleJoinWithOrWithAndLowerCaseWorking() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and lower(n.name) = 'nickname a') or (n.id = 2 and lower(n.name) = 'nickname b')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isCountWithoutParametersWorking() {
		String query = "select count(m) from Manufacturer m ";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isExecutingQueryAndCountWithoutParametersWorking() {
		String query = "select count(m) from Manufacturer m ";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());

		assertSame(personsFromJPQL.get(0), easyCriteria.count());
		List<Manufacturer> resultList = easyCriteria.getResultList();

		assertEquals(resultList.size(), easyCriteria.count().intValue());
	}

	@Test
	public void isCountingAndGettingResultWithParameters1() {
		List<Person> personsFromJPQL = getListFromJPQL(
				"select distinct p from Person p join p.car ca join ca.color co where co.name = 'Red'", Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("car.color");
		easyCriteria.andEquals("car.color.name", "Red");

		assertEquals(personsFromJPQL.size(), easyCriteria.count().intValue());

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isCountingAndGettingResultWithParameters2() {
		String query = "select p from Person p " + "left join p.car ca " + "left join ca.color co "
				+ "left join co.manufacturer manu " + "where manu.name = 'Company A'";
		List<Person> personsFromJPQL = getListFromJPQL(query, Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.leftJoin("car.color.manufacturer");
		easyCriteria.andEquals("car.color.manufacturer.name", "Company A");

		assertEquals(personsFromJPQL.size(), easyCriteria.count().intValue());

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(result));
	}

	@Test
	public void isCountingAndGettingResultWithParameters3() {
		String query = "select count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id > 1";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.id", 1);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters4() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDouble >= 1d";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justDouble", 1d);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters5() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justLong < 2";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justLong", 2L);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters6() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justFloat <= 2F";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.justFloat", 2F);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters7() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBigDecimal > 1";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterThan("products.nickNames.justBigDecimal", new BigDecimal(1));

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters8() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date justDate = formatter.parse("2/2/2002");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justDate", justDate);

		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justDate >= :justDate";

		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andGreaterOrEqualTo("products.nickNames.justDate", justDate);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters9() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("2/2/2002");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendar", justCalendar);

		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar < :justCalendar";

		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessThan("products.nickNames.justCalendar", justCalendar);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters10() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name <= '" + CodeGenerator.NICKNAME_B_NAME + "'";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andLessOrEqualTo("products.nickNames.name", CodeGenerator.NICKNAME_B_NAME);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters11() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justBoolean = true";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andEquals("products.nickNames.justBoolean", true);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters12() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Date date2 = formatter.parse("2/2/2002");
		Calendar justCalendar2 = Calendar.getInstance();
		justCalendar2.setTime(date2);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendarA", justCalendar);
		parameters.put("justCalendarB", justCalendar2);

		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar between :justCalendarA and :justCalendarB";

		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andBetween("products.nickNames.justCalendar", justCalendar, justCalendar2);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters13() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justString is null";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andIsNull("products.nickNames.justString");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters14() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justString is not null";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andIsNotNull("products.nickNames.justString");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters15() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justList is not empty";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsNotEmpty("products.nickNames.justList");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters16() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justSet is empty";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andCollectionIsEmpty("products.nickNames.justSet");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters17() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name like '% B'";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringLike("products.nickNames.name", "% B");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters18() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name not like '% B'";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotLike("products.nickNames.name", "% B");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters19() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) like '% b'";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringLike(true, "products.nickNames.name", "% B".toLowerCase());

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters20() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) not like '% b'";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotLike(true, "products.nickNames.name", "% B".toLowerCase());

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters21() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name in ('NickName A', 'NickName B')";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A");
		names.add("NickName B");

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringIn("products.nickNames.name", names);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters22() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.name not in ('NickName A', 'NickName B')";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A");
		names.add("NickName B");

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotIn("products.nickNames.name", names);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters23() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) in ('nickname a', 'nickname b')";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A".toLowerCase());
		names.add("NickName B".toLowerCase());

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringIn(true, "products.nickNames.name", names);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters24() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) not in ('nickname a', 'nickname b')";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		List<String> names = new ArrayList<String>();
		names.add("NickName A");
		names.add("NickName B");

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.andStringNotIn(true, "products.nickNames.name", names);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters25() {
		String query = "select distinct count(p) from Product p " + "join p.nickNames n " + "join n.justCollection j "
				+ "where j.autoRelationship is empty";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Product> easyCriteria = new EasyQueryImpl<Product>(Product.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("nickNames.justCollection");
		easyCriteria.andCollectionIsEmpty("nickNames.justCollection.autoRelationship");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters26() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.id = 1 or n.id = 2";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.id", 1, 2);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters27() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where lower(n.name) = 'nickname a' or lower(n.name) = 'nickname b'";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals(true, "products.nickNames.name", "NickName A".toLowerCase(), "NickName B".toLowerCase());

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters28() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Date date2 = formatter.parse("2/2/2002");
		Calendar justCalendar2 = Calendar.getInstance();
		justCalendar2.setTime(date2);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendarA", justCalendar);
		parameters.put("justCalendarB", justCalendar2);

		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where n.justCalendar = :justCalendarA or n.justCalendar = :justCalendarB";

		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class, parameters);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters29() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isCountingAndGettingResultWithParameters30() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and lower(n.name) = 'nickname a') or (n.id = 2 and lower(n.name) = 'nickname b')";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isAbleToDoTheSameQuerySeveralTimes() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());
		assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
		assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
		assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
		assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
		assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
		assertTrue(personsFromJPQL.containsAll(easyCriteria.getResultList()));
	}

	@Test
	public void isAbleToDoTheSameCountSeveralTimes() {
		String query = "select distinct count(m) from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
		List<Long> personsFromJPQL = getListFromJPQL(query, Long.class);
		assertTrue(personsFromJPQL.get(0) > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
		assertEquals(personsFromJPQL.get(0), easyCriteria.count());
	}

	@Test
	public void isAbleToDoTheSameQueryAndCountSeveralTimes() {
		String query = "select distinct m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				     + "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
		
		List<Manufacturer> personsFromQuery = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromQuery.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.setDistinctTrue();
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromQuery.size(), result.size());

		String countQuery = "select distinct count(m) from Manufacturer m " + "join m.products p "
						  + "join p.nickNames n "
						  + "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B')";
		
		List<Long> personsFromTotal = getListFromJPQL(countQuery, Long.class);
		assertTrue(personsFromTotal.get(0) > 0);

		assertEquals(personsFromTotal.get(0), easyCriteria.count());
		assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
		assertEquals(personsFromTotal.get(0), easyCriteria.count());
		assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
		assertEquals(personsFromTotal.get(0), easyCriteria.count());
		assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
		assertEquals(personsFromTotal.get(0), easyCriteria.count());
		assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
		assertEquals(personsFromTotal.get(0), easyCriteria.count());
		assertTrue(personsFromQuery.containsAll(easyCriteria.getResultList()));
	}

	@Test
	public void isOrderByWithMultipleJoinWorking01() {
		List<Person> personsFromJPQL = getListFromJPQL("select p from Person p join p.dogs d order by d.name",
				Person.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Person> easyCriteria = new EasyQueryImpl<Person>(Person.class, getEntityManager());
		easyCriteria.innerJoin("dogs");
		easyCriteria.orderByAsc("dogs.name");

		List<Person> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());

		for (int i = 0; i < personsFromJPQL.size(); i++) {
			assertEquals(personsFromJPQL.get(i), result.get(i));
		}
	}

	@Test
	public void isOrderByWithMultipleJoinWorking02() {
		String query = "select m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and n.name = 'NickName A') or (n.id = 2 and n.name = 'NickName B') "
				+ "order by n.name DESC";
		List<Manufacturer> personsFromQuery = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromQuery.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(1, "products.nickNames.name", "NickName A")
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(2, "products.nickNames.name", "NickName B");
		easyCriteria.orderByDesc("products.nickNames.name");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromQuery.size(), result.size());

		for (int i = 0; i < personsFromQuery.size(); i++) {
			assertEquals(personsFromQuery.get(i), result.get(i));
		}
	}

	@Test
	public void isOrderByWithMultipleJoinWorking03() {
		String query = "select m from Manufacturer m " + "join m.products p " + "join p.nickNames n "
				+ "where (n.id = 1 and lower(n.name) = 'nickname a') or (n.id = 2 and lower(n.name) = 'nickname b') "
				+ "order by n.name desc";
		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.addAndSeparatedByOr(1, "products.nickNames.id", 1)
				.addAndSeparatedByOr(true, 1, "products.nickNames.name", "NickName A".toLowerCase())
				.addAndSeparatedByOr(2, "products.nickNames.id", 2)
				.addAndSeparatedByOr(true, 2, "products.nickNames.name", "NickName B".toLowerCase());
		easyCriteria.orderByDesc("products.nickNames.name");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());

		for (int i = 0; i < personsFromJPQL.size(); i++) {
			assertEquals(personsFromJPQL.get(i), result.get(i));
		}
	}

	@Test
	public void isOrderByWithMultipleJoinWorking04() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("1/1/2001");
		Calendar justCalendar = Calendar.getInstance();
		justCalendar.setTime(date);

		Date date2 = formatter.parse("2/2/2002");
		Calendar justCalendar2 = Calendar.getInstance();
		justCalendar2.setTime(date2);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("justCalendarA", justCalendar);
		parameters.put("justCalendarB", justCalendar2);

		String query = "select m from Manufacturer m " 
					   + "join m.products p " 
					   + "join p.nickNames n "
					   + "where n.justCalendar = :justCalendarA or n.justCalendar = :justCalendarB " 
					   + "order by n.name desc";

		List<Manufacturer> personsFromJPQL = getListFromJPQL(query, Manufacturer.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<Manufacturer> easyCriteria = new EasyQueryImpl<Manufacturer>(Manufacturer.class, getEntityManager());
		easyCriteria.innerJoin("products.nickNames");
		easyCriteria.orEquals("products.nickNames.justCalendar", justCalendar, justCalendar2);
		easyCriteria.orderByDesc("products.nickNames.name");

		List<Manufacturer> result = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), result.size());

		for (int i = 0; i < personsFromJPQL.size(); i++) {
			assertEquals(personsFromJPQL.get(i), result.get(i));
		}
	}

	@Test
	public void isEqualsCompositeKeyWorking() {
		EmbeddedIdDummy idDummy = new EmbeddedIdDummy(1, "01 Key 1");

		if (isOpenJPA()) {
			// there is a OpenJPA bug for this:
			// https://issues.apache.org/jira/browse/OPENJPA-1806
			return;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idDummy", idDummy);
		String query = "select e from EntityEmbeddedId e where e.id = :idDummy ";
		List<EntityEmbeddedId> personsFromJPQL = getListFromJPQL(query, EntityEmbeddedId.class, parameters);
		assertTrue(personsFromJPQL.size() > 0);

		EasyQuery<EntityEmbeddedId> easyCriteria = new EasyQueryImpl<EntityEmbeddedId>(EntityEmbeddedId.class,
				getEntityManager());
		easyCriteria.andEquals("id", idDummy);

		List<EntityEmbeddedId> resultList = easyCriteria.getResultList();

		assertEquals(personsFromJPQL.size(), resultList.size());
		assertTrue(personsFromJPQL.containsAll(resultList));
	}

	/*
	 * public void isCreatingAbstractClasses(){ new EasyCriteriaFactory(){}; new PredicateCreator(){}; new
	 * EntityPathHelper(){}; } private <T> EasyQuery<T> createCriteria(Class<T> classToUse) { EntityManager em =
	 * getEntityManager(); return EasyCriteriaFactory.createQueryCriteria(em, classToUse); } protected <T> List<T>
	 * getListFromJPQL(String query, Class<T> classToUse) { return getListFromJPQL(query, classToUse, null); } private
	 * <T> List<T> getListFromJPQL(String query, Class<T> classToUse, Map<String, Object> parameters) { EntityManager em
	 * = getEntityManager(); TypedQuery<T> typedQuery = em.createQuery(query, classToUse); if (parameters != null) {
	 * populateQueryParameters(typedQuery, parameters); } return typedQuery.getResultList(); } private <T> void
	 * populateQueryParameters(TypedQuery<T> query, Map<String, Object> parameters) { for (Map.Entry<String, Object>
	 * entry : parameters.entrySet()) { query.setParameter(entry.getKey(), entry.getValue()); } } protected static void
	 * generateData() { CodeGenerator.generateData(getEntityManagerFactory()); }
	 */
}
