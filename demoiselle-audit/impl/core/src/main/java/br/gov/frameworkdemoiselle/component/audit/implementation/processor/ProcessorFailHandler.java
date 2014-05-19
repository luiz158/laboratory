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
package br.gov.frameworkdemoiselle.component.audit.implementation.processor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import br.gov.frameworkdemoiselle.component.audit.AuditException;
import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import br.gov.frameworkdemoiselle.component.audit.implementation.AuditConfig;
import br.gov.frameworkdemoiselle.component.audit.implementation.qualifier.AuditProcessorFail;
import br.gov.frameworkdemoiselle.component.audit.implementation.util.Util;
import br.gov.frameworkdemoiselle.util.Beans;

/**
 *
 * @author SERPRO
 *
 */

@ApplicationScoped
public class ProcessorFailHandler {

	private final AuditConfig config = Beans.getReference(AuditConfig.class);

    /**
     *
     * @param trail
     */
    protected void onFailProcessor(@Observes @AuditProcessorFail Trail trail) {

		String folder = config.getFolderFailObjects();

		if(folder == null || folder.isEmpty() || "".equals(folder)){
			folder = Util.getFolderPathDefault();
		}

		String fileName = File.separatorChar + System.currentTimeMillis()+""+trail.toString().hashCode()+ "trail" + ".ser";

		ObjectOutput output = null;
		OutputStream os = null;

		try {
			File dir = new File(folder);

			if(!dir.exists()){
				dir.mkdirs();
			}

			os = new FileOutputStream(folder + fileName);
			OutputStream buffer = new BufferedOutputStream(os);

			try{
				output = new ObjectOutputStream(buffer);
				output.writeObject(trail);
				Logger.getLogger(ProcessorFailHandler.class.getName()).log(Level.INFO, "Object Trail wrote into file '" + folder + fileName + "'");
			}
			catch(IOException e){
				throw new AuditException("Fail writing Trail object into file '" + folder + fileName + "', message error :" + e.getMessage());
			}
		}
		catch (IOException e) {
			throw new AuditException("Fail writing Trail object into file '" + folder + fileName + "', message error :" + e.getMessage());
		}
		finally{

			if(output != null){
				try{
					output.close();
				}
				catch(IOException e){
					throw new AuditException("Fail writing Trail object into file '" + folder + fileName + "', message error :" + e.getMessage());
				}
			}

			if(os != null){
				try{
					os.close();
				}
				catch(IOException e){
					throw new AuditException("Fail writing Trail object into file '" + folder + fileName + "', message error :" + e.getMessage());
				}
			}
		}

	}
    
}
