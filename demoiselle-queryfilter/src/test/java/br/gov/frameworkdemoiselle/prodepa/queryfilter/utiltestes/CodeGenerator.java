package br.gov.frameworkdemoiselle.prodepa.queryfilter.utiltestes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Address;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Car;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Cat;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Certification;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Color;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Dog;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.DummyEntity;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.EmbeddedIdDummy;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.EntityEmbeddedId;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Manufacturer;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.NickName;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Person;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Product;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Song;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.SongType;

public class CodeGenerator {
    public static final String PERSON01_NAME = "John";

    public static final String PERSON01_NICKNAME = "Little John";
    public static final String PERSON02_NAME = "Mary";

    public static final String PERSON03_NAME = "Others attributes will be null";
    public static final String PERSON04_NAME = "Others attributes will be null";
    public static final String PERSON05_NAME = "joseph";
    public static final String PERSON06_NAME = "paul";
    public static final String NICKNAME_A_NAME = "NickName A";
    public static final String NICKNAME_B_NAME = "NickName B";

    public static final String STREET_NAME_A = "Street A";
    public static final String STREET_NAME_B = "Street B";

    public static void generateData(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Person> query = entityManager.createQuery("select p from Person p", Person.class);

        // EclipseLink was invoking two times
        if(query.getResultList().isEmpty()){
            generatePersons(entityManagerFactory);
            generateAddresses(entityManagerFactory);
            generateManufacturers(entityManagerFactory);
            generateProducts(entityManagerFactory);
            generateNickName(entityManagerFactory);
            generateDummyEntity(entityManagerFactory);
            generateCars(entityManagerFactory);
            generateDogs(entityManagerFactory);
            generateCats(entityManagerFactory);
            generateCertifications(entityManagerFactory);
            generateSongs(entityManagerFactory);
            generateEntityEmbeddedId(entityManagerFactory);
            createRelationShipBetweenEntities(entityManagerFactory);
        }
    }

    private static void generateEntityEmbeddedId(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        EmbeddedIdDummy embeddedId1 = new EmbeddedIdDummy(1, "01 Key 1");
        EmbeddedIdDummy embeddedId2 = new EmbeddedIdDummy(2, "02 Key 2");
        EmbeddedIdDummy embeddedId3 = new EmbeddedIdDummy(3, "03 Key 3");

        entityManager.persist(new EntityEmbeddedId(embeddedId1, "A"));
        entityManager.persist(new EntityEmbeddedId(embeddedId2, "B"));
        entityManager.persist(new EntityEmbeddedId(embeddedId3, "C"));

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    private static void generateSongs(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // real songs names. check it out. ;)
        Song song01 = new Song(1, createCalendar(1, 1, 2001), "Sing Out", "Ron Kenoly", 10, 10L, 10f, 10d, createNewDate(1, 1, 1999), SongType.PRAISE);
        Song song02 = new Song(2, createCalendar(1, 1, 2002), "Alive", "P.O.D", 20, 20L, 20f, 20d, createNewDate(1, 1, 2000), SongType.ROCK);
        Song song03 = new Song(3, createCalendar(1, 1, 2003), "Let it Roll", "Group 1 Crew", 30, 30L, 30f, 30d, createNewDate(1, 1, 2001), SongType.HIP_HOP);
        Song song04 = new Song(4, createCalendar(1, 1, 2004), "We Live", "Superchick", 40, 40L, 40f, 40d, createNewDate(1, 1, 2002), SongType.POP);
        Song song05 = new Song(5, createCalendar(1, 1, 2005), "Holy Night", "BarlowGirl", 50, 50L, 50f, 50d, createNewDate(1, 1, 2003), SongType.ROCK);
        Song song06 = new Song(6, createCalendar(1, 1, 2006), "Unbreakable", "Fireflight", 60, 60L, 60f, 60d, createNewDate(1, 1, 2004), SongType.ROCK);
        Song song07 = new Song(7, createCalendar(1, 1, 2007), "Made To Love", "TobyMac", 70, 70L, 70f, 70d, createNewDate(1, 1, 2006), SongType.POP);
        Song song08 = new Song(8, createCalendar(1, 1, 2008), "Three Wooden Crosses", "Randy Travis", 80, 80L, 80f, 80d, createNewDate(1, 1, 2007), SongType.COUNTRY);
        Song song09 = new Song(9, createCalendar(1, 1, 2009), "East to West", "Casting Crowns", 90, 90L, 90f, 90d, createNewDate(1, 1, 2008), SongType.POP);
        Song song10 = new Song(10, createCalendar(1, 1, 2010), "I Need A Miracle", "Third Day", 100, 100L, 100f, 100d, createNewDate(1, 1, 2009), SongType.POP);
        Song song11 = new Song(11, createCalendar(1, 1, 2011), "Breath Into Me", "Red", 110, 110L, 110f, 110d, createNewDate(1, 1, 2010), SongType.ROCK);
        Song song12 = new Song(12, createCalendar(1, 1, 2012), "Let Go", "Red", 110, 110L, 110f, 110d, createNewDate(1, 1, 2010), SongType.ROCK);
        Song song13 = new Song(13, createCalendar(1, 1, 2013), "Gave It All Away", "Red", 110, 110L, 110f, 110d, createNewDate(1, 1, 2010), SongType.ROCK);
        Song song14 = new Song(14, createCalendar(1, 1, 2014), "Break Me Down", "Red", 110, 110L, 110f, 110d, createNewDate(1, 1, 2010), SongType.ROCK);


        entityManager.persist(song01);
        entityManager.persist(song02);
        entityManager.persist(song03);
        entityManager.persist(song04);
        entityManager.persist(song05);
        entityManager.persist(song06);
        entityManager.persist(song07);
        entityManager.persist(song08);
        entityManager.persist(song09);
        entityManager.persist(song10);
        entityManager.persist(song11);
        entityManager.persist(song12);
        entityManager.persist(song13);
        entityManager.persist(song14);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    private static Calendar createCalendar(int day, int month, int year) {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(createNewDate(day, month, year));

        return dateOfBirth;
    }

    private static void generateCertifications(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Certification certification1 = new Certification(1, "SCJP");
        Certification certification2 = new Certification(2, "SCWCD");
        Certification certification3 = new Certification(3, "OCBCD");
        Certification certification4 = new Certification(4, "OCJPAD");

        entityManager.persist(certification1);
        entityManager.persist(certification2);
        entityManager.persist(certification3);
        entityManager.persist(certification4);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateCats(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Cat cat1 = new Cat(1, "Freud");
        Cat cat2 = new Cat(2, "Marx");

        entityManager.persist(cat1);
        entityManager.persist(cat2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateDogs(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Dog dog1 = new Dog(1, "Ice", 13.00d, createNewDate(1, 1, 2009), 13.00f, 13L, 13);
        Dog dog2 = new Dog(2, "Fire", 5.00d, createNewDate(1, 1, 2010), 5.00f, 5L, 5);
        Dog dog3 = new Dog(3, "Thunder", 6.00d, createNewDate(1, 1, 2011), 4.00f, 6L, 7);
        Dog dog4 = new Dog(4, "Earth", 2.00d, createNewDate(1, 1, 2012), 2.00f, 2L, 3);

        entityManager.persist(dog1);
        entityManager.persist(dog2);
        entityManager.persist(dog3);
        entityManager.persist(dog4);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateManufacturers(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.persist(new Manufacturer(1, "Company A"));
        entityManager.persist(new Manufacturer(2, "Company B"));
        entityManager.persist(new Manufacturer(3, "Company C"));


        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateProducts(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.persist(new Product(1, "Product A"));
        entityManager.persist(new Product(2, "Product B"));
        entityManager.persist(new Product(3, "Product C"));


        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateDummyEntity(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(new DummyEntity(1, "A"));
        entityManager.persist(new DummyEntity(2, "B"));
        entityManager.persist(new DummyEntity(3, "C"));
        entityManager.persist(new DummyEntity(4, "D"));
        entityManager.persist(new DummyEntity(5, "E"));
        entityManager.persist(new DummyEntity(6, "F"));
        entityManager.persist(new DummyEntity(7, "G"));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateNickName(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(new NickName(1, NICKNAME_A_NAME, "anything", 1d, 1L, 1F, new BigDecimal(1), createNewDate(1, 1, 2001), createCalendar(1, 1, 2001), true));
        entityManager.persist(new NickName(2, NICKNAME_B_NAME, "something", 2d, 2L, 2F, new BigDecimal(2), createNewDate(2, 2, 2002), createCalendar(2, 2, 2002), false));
        entityManager.persist(new NickName(3, "NickName C", null, 3d, 3L, 3F, new BigDecimal(3), createNewDate(3, 3, 2003), createCalendar(3, 3, 2003), true));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateCars(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Car car1 = new Car(1, "Dark Horse", new Color(1, "White"), new BigDecimal(20.0));
        Car car2 = new Car(2, "Blue Arrow", new Color(2, "Red"), new BigDecimal(30.0));
        Car car3 = new Car(3, "Yellow Submarine", null, new BigDecimal(30.0));

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void createRelationShipBetweenEntities(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Person person01 = entityManager.find(Person.class, 1);
        Person person02 = entityManager.find(Person.class, 2);
        Person person06 = entityManager.find(Person.class, 6);

        Address address01 = entityManager.find(Address.class, 1);
        Address address02 = entityManager.find(Address.class, 2);

        Car car1 = entityManager.find(Car.class, 1);
        Car car2 = entityManager.find(Car.class, 2);
        Car car3 = entityManager.find(Car.class, 3);

        Cat cat1 = entityManager.find(Cat.class, 1);
        Cat cat2 = entityManager.find(Cat.class, 2);

        Certification certification1 = entityManager.find(Certification.class, 1);
        Certification certification2 = entityManager.find(Certification.class, 2);

        Dog dog1 = entityManager.find(Dog.class, 1);
        Dog dog2 = entityManager.find(Dog.class, 2);
        Dog dog3 = entityManager.find(Dog.class, 3);
        Dog dog6 = entityManager.find(Dog.class, 4);

        Manufacturer manufacturer1 = entityManager.find(Manufacturer.class, 1);
        Manufacturer manufacturer2 = entityManager.find(Manufacturer.class, 2);

        Color color1 = entityManager.find(Color.class, 1);
        Color color2 = entityManager.find(Color.class, 2);

        Product product1 = entityManager.find(Product.class, 1);
        Product product2 = entityManager.find(Product.class, 2);
        Product product3 = entityManager.find(Product.class, 3);

        NickName nickName1 = entityManager.find(NickName.class, 1);
        NickName nickName2 = entityManager.find(NickName.class, 2);
        NickName nickName3 = entityManager.find(NickName.class, 3);

        person01.setAddress(address01);
        person01.setCar(car1);

        person02.setAddress(address02);
        person02.setCar(car2);

        dog1.setPerson(person01);
        dog2.setPerson(person01);

        person01.getDogs().add(dog1);
        person01.getDogs().add(dog2);

        dog3.setPerson(person02);
        person02.getDogs().add(dog3);

        cat1.setPerson(person01);
        cat2.setPerson(person01);

        person01.getCats().add(cat1);
        person01.getCats().add(cat2);

        certification1.setPerson(person02);
        certification2.setPerson(person02);

        person02.getCertifications().add(certification1);
        person02.getCertifications().add(certification2);

        person06.getDogs().add(dog6);
        dog6.setPerson(person06);
        person06.setCar(car3);
        car3.setPerson(person06);

        color1.setManufacturer(manufacturer1);
        color2.setManufacturer(manufacturer2);

        manufacturer1.getProducts().add(product1);
        manufacturer2.getProducts().add(product2);
        manufacturer2.getProducts().add(product3);

        product1.getNickNames().add(nickName1);
        product2.getNickNames().add(nickName2);
        product2.getNickNames().add(nickName3);

        Collection<DummyEntity>  dummyEntityCollection = new ArrayList<DummyEntity>();
        dummyEntityCollection.add(entityManager.find(DummyEntity.class, 1));
        dummyEntityCollection.add(entityManager.find(DummyEntity.class, 2));
        dummyEntityCollection.add(entityManager.find(DummyEntity.class, 3));
        dummyEntityCollection.add(entityManager.find(DummyEntity.class, 4));
        dummyEntityCollection.add(entityManager.find(DummyEntity.class, 5));
        dummyEntityCollection.add(entityManager.find(DummyEntity.class, 6));
        dummyEntityCollection.add(entityManager.find(DummyEntity.class, 7));

        List<DummyEntity> dummyEntityList = new ArrayList<DummyEntity>();
        dummyEntityList.add(entityManager.find(DummyEntity.class, 1));
        dummyEntityList.add(entityManager.find(DummyEntity.class, 2));
        dummyEntityList.add(entityManager.find(DummyEntity.class, 3));
        dummyEntityList.add(entityManager.find(DummyEntity.class, 4));
        dummyEntityList.add(entityManager.find(DummyEntity.class, 5));
        dummyEntityList.add(entityManager.find(DummyEntity.class, 6));
        dummyEntityList.add(entityManager.find(DummyEntity.class, 7));

        Set<DummyEntity> dummyEntitySet = new HashSet<DummyEntity>();
        dummyEntitySet.add(entityManager.find(DummyEntity.class, 1));
        dummyEntitySet.add(entityManager.find(DummyEntity.class, 2));
        dummyEntitySet.add(entityManager.find(DummyEntity.class, 3));
        dummyEntitySet.add(entityManager.find(DummyEntity.class, 4));
        dummyEntitySet.add(entityManager.find(DummyEntity.class, 5));
        dummyEntitySet.add(entityManager.find(DummyEntity.class, 6));
        dummyEntitySet.add(entityManager.find(DummyEntity.class, 7));

        nickName1 = entityManager.find(NickName.class, 1);
        nickName2 = entityManager.find(NickName.class, 2);

        nickName1.setJustCollection(dummyEntityCollection);
        nickName1.setJustList(dummyEntityList);
        nickName1.setJustSet(dummyEntitySet);

        nickName2.setJustCollection(dummyEntityCollection);
        nickName2.setJustList(dummyEntityList);
        nickName2.setJustSet(dummyEntitySet);

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generateAddresses(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Date buildingDate1 = createNewDate(1, 1, 1980);
        Address address1 = new Address(1, STREET_NAME_A, 34, true, false, buildingDate1);

        Date buildingDate2 = createNewDate(1, 1, 1990);
        Address address2 = new Address(2, STREET_NAME_B, 4, false, true, buildingDate2);

        entityManager.persist(address1);
        entityManager.persist(address2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void generatePersons(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Date firstJobDate1 = createNewDate(1, 1, 2015);
        Date firstSoccerMatchDate1 = createNewDate(1, 1, 2013);

        Date birthDay1 = createNewDate(1, 1, 2001);
        Calendar birthDayPerson01 = Calendar.getInstance();
        birthDayPerson01.setTime(birthDay1);
        Calendar firstKissPerson01 = Calendar.getInstance();
        firstKissPerson01.setTime(birthDay1);
        firstKissPerson01.set(Calendar.YEAR, 2011);

        Person person1 = new Person(1, PERSON01_NAME, PERSON01_NICKNAME, 33, birthDayPerson01, firstKissPerson01, 10.00f, 10.00d, 1928371L, 10, 20L, firstJobDate1, firstSoccerMatchDate1, true, false);

        Date birthDay2 = createNewDate(1, 1, 2002);
        Date firstJobDate2 = createNewDate(1, 1, 2016);
        Date firstSoccerMatchDate2 = createNewDate(1, 1, 2015);

        Calendar birthDayPerson02 = Calendar.getInstance();
        birthDayPerson02.setTime(birthDay2);
        Calendar firstKissPerson02 = Calendar.getInstance();
        firstKissPerson02.setTime(birthDay2);
        firstKissPerson02.set(Calendar.YEAR, 2011);

        Person person2 = new Person(2, PERSON02_NAME, "Hello Mary", 44, birthDayPerson02, firstKissPerson02, 11.00f, 11.00d, 98723487L, 20, 30L, firstJobDate2, firstSoccerMatchDate2, false, true);

        Person person3 = new Person();
        person3.setId(3);
        person3.setName(PERSON03_NAME);

        Person person4 = new Person();
        person4.setId(4);
        person4.setName(PERSON04_NAME);

        Person person5 = new Person();
        person5.setId(5);
        person5.setName(PERSON05_NAME);

        Person person6 = new Person();
        person6.setId(6);
        person6.setName(PERSON06_NAME);

        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(person3);
        entityManager.persist(person4);
        entityManager.persist(person5);
        entityManager.persist(person6);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static Date createNewDate(int day, int month, int year) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatter.parse("" + day + "/" + month + "/" + year);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}