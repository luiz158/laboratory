/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.persistence;

import br.gov.frameworkdemoiselle.serpro.audit.domain.Trilha;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class TrilhaDAO extends JPACrud<Trilha, Long> {

//    @Inject
//    @Name("demoiselle-audit")
//    private EntityManager entityManager;

    public TrilhaDAO() {
//        Map properties = new HashMap();
//
//        properties.put("JDBC_DRIVER", "oracle.jdbc.OracleDriver");
//        properties.put("JDBC_URL", "jdbc:oracle:thin:@localhost:1521:ORCL");
//        properties.put("JDBC_USER", "user-name");
//        properties.put("JDBC_PASSWORD", "password");
//
//        entityManager = Persistence.createEntityManagerFactory("demoiselle-audit", properties).createEntityManager();
    }
}
