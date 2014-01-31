/*
 * Demoiselle Framework
 * Copyright (C) 2014 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.component.audit.dashboard.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.Usuario;
import br.gov.frameworkdemoiselle.component.audit.dashboard.persistence.RecursoDAO;
import br.gov.frameworkdemoiselle.component.audit.dashboard.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.component.audit.dashboard.util.CriptografiaUtil;
import br.gov.frameworkdemoiselle.component.audit.dashboard.util.Trololo;
import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.mail.internal.Config;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

/**
 * 
 * @author SERPRO
 * 
 */
@BusinessController
public class SecurityBC implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private Mail mailer;

    @Inject
    private RecursoDAO recursoDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private Config config;

    @Inject
    private ResourceBundle rb;

    @Inject
    private TrailBC trailBC;

    @Startup
    public void bootstrap() {
        try {

        	List<Long> ids = new ArrayList<Long>();
        	for(Trail trail : trailBC.findAll()){
        		ids.add(trail.getIdaudit());
        	}

        	trailBC.delete(ids);

            Trololo tro = new Trololo();

            for (int i = 0; i < 3000; i++) {
//                trailBC.insert(new Trail(null, tro.getSistema(), tro.getUsuario(), "id", tro.getPerfil(), tro.getOque(), tro.getComo(), tro.getOnde(), tro.getQuando(), "br.gov.frameworkdemoiselle.serpro.audit", tro.getObjetoSerial(), null, null));
            }

        } catch (Exception e) {
            Logger.getLogger(SecurityBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *
     * @param aminesia
     * @param senhaatual
     * @param senhanova
     */
    public void alteraSenha(String aminesia, String senhanova) throws Exception {
        usuarioDAO.updatePassWithAminesia(aminesia, senhanova);
    }

    public void enviarMensagemLembrandoSenha(Usuario usuario) throws Exception {
        enviarMensagemLembrandoSenha(usuario.getEmail());
    }

    public void enviarMensagemLembrandoSenha(String destinatario) throws Exception {
        Usuario usuario = usuarioDAO.findByEmail(destinatario);
        String senha = CriptografiaUtil.getCodigoMd5("" + System.currentTimeMillis());
        usuario.setAminesia(senha);
        usuario.setSenha(senha.substring(21, 27));
        usuarioDAO.update(usuario);

        mailer.to(destinatario)
                .from("")
                .body().text(rb.getString("email.aminesia.texto", usuario.getUsuario(), usuario.getSenha(), "" + usuario.getAminesia()))
                .subject("")
                .send();

    }
}
