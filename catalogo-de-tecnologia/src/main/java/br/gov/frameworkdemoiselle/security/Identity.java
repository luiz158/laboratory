package br.gov.frameworkdemoiselle.security;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author escritorio
 */
@Named
@SessionScoped
public class Identity implements User {

    private static final long serialVersionUID = 8003651916557123604L;
    private Map<Object, Object> attribute;

    /*
     * TODO A Funcionalidade do gravação de Cookie não esta funcionando corretamente.
     */
    public Identity() {
        attribute = new HashMap<Object, Object>();
    }

    public String getUsuario() {
        return (String) (attribute.get("username") == null ? "" : attribute.get("username"));
    }

    public void setUsuario(String usuario) {
        attribute.put("username", usuario);
    }

    public String getSenha() {
         return (String) (attribute.get("password") == null ? "" : attribute.get("password"));
    }

    public void setSenha(String senha) {
         attribute.put("password", senha);
    }

    @Override
    public String getId() {
        return (String) (attribute.get("username") == null ? "" : attribute.get("username"));
    }

    @Override
    public Object getAttribute(Object key) {
        return attribute.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        attribute.put(key, value);
    }
}
