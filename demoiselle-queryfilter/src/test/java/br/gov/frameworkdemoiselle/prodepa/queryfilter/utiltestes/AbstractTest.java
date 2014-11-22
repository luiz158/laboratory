package br.gov.frameworkdemoiselle.prodepa.queryfilter.utiltestes;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public abstract class AbstractTest {

    private static final String PU_OPENJPA = "QueryTesterOpenJPA";
    private static final String PU_HIBERNATE = "QueryTesterHibernate";
    private static final String PU_ECLIPSELINK = "QueryTesterEclipseLink";

    protected static void createEntityManagerFactoryForHibernate() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_HIBERNATE);
    }

    protected static void createEntityManagerFactoryForEclipseLink() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_ECLIPSELINK);
    }

    protected static void createEntityManagerFactoryForOpenJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_OPENJPA);
    }

    @BeforeClass
    public static void createPersistenceUnit() {
        if (getEntityManagerFactory() == null) {
                createEntityManagerFactoryForHibernate();
//            createEntityManagerFactoryForEclipseLink();
//            createEntityManagerFactoryForOpenJPA();
        }

        generateData();
    }

    @AfterClass
    public static void closePersistenceUnit() {
        if(getEntityManagerFactory().isOpen()){
            getEntityManagerFactory().close();
        }

        entityManagerFactory = null;
    }

    protected static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    protected static void generateData() {
        if(!getEntityManagerFactory().isOpen()){
            createEntityManagerFactoryForHibernate();
        }

        CodeGenerator.generateData(getEntityManagerFactory());
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory != null){
            return entityManagerFactory;
        }

        return null;
    }

    @Before
    public void beforeTest() {
        if(entityManager == null){
            entityManager = getEntityManagerFactory().createEntityManager();
        }
    }

    @After
    public void finishTest() {
        entityManager.close();
    }
    
    protected <T> List<T> getListFromJPQL(String query, Class<T> classToUse) {
      return getListFromJPQL(query, classToUse, null);
    }
    
    protected <T> List<T> getListFromJPQL(String query, Class<T> classToUse, Map<String, Object> parameters) {
      EntityManager em = getEntityManager();

      TypedQuery<T> typedQuery = em.createQuery(query, classToUse);

      if (parameters != null) {
        populateQueryParameters(typedQuery, parameters);
      }

      return typedQuery.getResultList();
    }

    protected <T> void populateQueryParameters(TypedQuery<T> query, Map<String, Object> parameters) {

      for (Map.Entry<String, Object> entry : parameters.entrySet()) {
        query.setParameter(entry.getKey(), entry.getValue());
      }
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected boolean isEclipseLink() {
        return getEntityManager().toString().contains("eclipse");
    }

    protected boolean isOpenJPA() {
        return getEntityManager().toString().contains("openjpa");
    }
}