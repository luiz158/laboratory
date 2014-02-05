package br.gov.frameworkdemoiselle.constant;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

/**
 *
 * @author 70744416353
 */
@Configuration(prefix = "frameworkdemoiselle")
public class CatalogoConfig {

    @Name(value = "aminesia.remetente")
    private String remetente;

    @Name(value = "aminesia.assunto")
    private String assunto;

    @Name(value = "aminesia.url")
    private String url;

    /**
     *
     * @return
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     *
     * @return
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

}
