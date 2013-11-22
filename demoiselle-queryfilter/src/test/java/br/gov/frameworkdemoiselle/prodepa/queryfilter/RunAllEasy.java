package br.gov.frameworkdemoiselle.prodepa.queryfilter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.filter.EasyFilterEclipseLinkTest;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.filter.EasyFilterHibernateTest;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.filter.EasyFilterOpenJPATest;

@RunWith(Suite.class)
// this other matters
@SuiteClasses({
        EasyFilterOpenJPATest.class,
        EasyFilterHibernateTest.class,
        EasyFilterEclipseLinkTest.class,
})
public class RunAllEasy {

}