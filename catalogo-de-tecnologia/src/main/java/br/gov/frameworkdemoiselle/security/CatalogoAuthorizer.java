package br.gov.frameworkdemoiselle.security;

import br.gov.frameworkdemoiselle.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author 70744416353
 */
public class CatalogoAuthorizer implements Authorizer {

    @Inject
    private Identity identity;

    @Inject
    private ResourceBundle rb;

    @Override
    public boolean hasRole(String role) throws Exception {
        try {
            return role.equals(identity.getAttribute("role"));
        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("controle.acesso.tem.papel.excecao"), ex);
        }
    }

    @Override
    public boolean hasPermission(String resource, String operation) throws Exception {
        try {

            Map<Integer, Integer> recursoOperacoes = (Map<Integer, Integer>) identity.getAttribute("RecursosOperacoes");

            List<String> operacoes = new ArrayList<String>();
            operacoes.add(operation);

            Integer recurso = Resources.getResource(resource);
            Integer operacao = Operations.getOperation(operacoes);

            for (Map.Entry<Integer, Integer> entry : recursoOperacoes.entrySet()) {
                if (recurso == entry.getKey() && operacao == entry.getValue()) {
                    return true;
                }
            }

            return false;

        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("controle.acesso.tem.permissao.excecao"), ex);
        }
    }

}
