package org.demoiselle.applet_customizada;

import br.gov.frameworkdemoiselle.certificate.applet.action.AbstractAppletExecute;
import br.gov.frameworkdemoiselle.certificate.applet.certificate.ICPBrasilCertificate;
import br.gov.frameworkdemoiselle.certificate.signer.factory.PKCS7Factory;
import br.gov.frameworkdemoiselle.certificate.signer.pkcs7.PKCS7Signer;
import br.gov.frameworkdemoiselle.policy.engine.factory.PolicyFactory;
import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AuthProvider;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends AbstractAppletExecute {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	@Override
	public void execute(KeyStore keystore, String alias, int policyselected,
			Applet applet) {
		try {

			/* Carregando o conteudo a ser assinado */
			String documento = AbstractAppletExecute.getFormField(applet,
					"mainForm", "documento");
			
			if (documento.length() == 0) {
				JOptionPane.showMessageDialog(applet,
						"Por favor, escolha um documento para assinar",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String user_home = System.getProperty("user.home");
			File file = new File(documento);
			String doc = documento;

			logger.info("user.home......: {}", user_home);
			logger.info("parm documento......: {}", doc);

			String canonicalPath = file.getCanonicalPath();
			logger.info("canonicalPath...........: {}", canonicalPath);

			String pathAbs = file.getAbsolutePath();
			logger.info("pathAbs...........: {}", pathAbs);

			String path = file.getPath();
			logger.info("Path...........: {}", path);

			byte[] content = readContent(canonicalPath);

			/* Parametrizando o objeto doSign */

			PKCS7Signer signer = PKCS7Factory.getInstance().factoryDefault();
			signer.setCertificates(keystore.getCertificateChain(alias));
			signer.setPrivateKey((PrivateKey) keystore.getKey(alias, null));

			switch (policyselected) {

			case 0: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RB_CADES_1_0);
				break;
			}

			case 1: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RB_CADES_1_1);
				break;
			}

			case 2: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RB_CADES_2_0);
				break;
			}

			case 3: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RB_CADES_2_1);
				break;
			}

			case 4: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RT_CADES_1_0);
				break;
			}

			case 5: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RT_CADES_1_1);
				break;
			}

			case 6: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RT_CADES_2_0);
				break;
			}

			case 7: {
				signer.setSignaturePolicy(PolicyFactory.Policies.AD_RT_CADES_2_1);
				break;
			}

			}

			signer.setAttached(true);

			/* Realiza a assinatura do conteudo */

			logger.info("Efetuando a  assinatura do conteudo");

			byte[] signed = signer.doSign(content);
			/* Grava o conteudo assinado no disco */

			writeContent(signed, documento.concat(".p7s"));
			/* Valida o conteudo */

			logger.info("Efetuando a validacao da assinatura.");
			boolean checked = signer.check(content, signed);

			if (checked) {
				logger.info("A assinatura e valida.");
				JOptionPane.showMessageDialog(applet,
						"O arquivo foi assinado e validado com sucesso.",
						"Mensagem", JOptionPane.INFORMATION_MESSAGE);
			} else {
				logger.info("A assinatura nao e valida!");
			}

			/* Exibe alguns dados do certificado */

			ICPBrasilCertificate certificado = super.getICPBrasilCertificate(
					keystore, alias, false);
			AbstractAppletExecute.setFormField(applet, "mainForm", "cpf",
					certificado.getCpf());
			AbstractAppletExecute.setFormField(applet, "mainForm", "nome",
					certificado.getNome());
			AbstractAppletExecute.setFormField(applet, "mainForm",
					"nascimento", certificado.getDataNascimento());
			AbstractAppletExecute.setFormField(applet, "mainForm", "email",
					certificado.getEmail());
		} catch (KeyStoreException | NoSuchAlgorithmException
				| UnrecoverableKeyException | IOException e) {
			JOptionPane.showMessageDialog(applet, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			logger.info("Efetuando logout no provider.");
			AuthProvider ap = null;
			if (keystore != null) {
				if (keystore.getProvider().getClass().getName()
						.equalsIgnoreCase("sun.security.mscapi.SunMSCAPI")) {
					logger.info("É necessário desabilitar a camada de acesso SunMSCAPI");
				} else {
					ap = (AuthProvider) keystore.getProvider();
				}
			}

			if (ap != null) {
				try {
					ap.logout();
				} catch (LoginException e) {
					JOptionPane.showMessageDialog(applet, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	@Override
	public void cancel(KeyStore keystore, String alias, int policyselected,
			Applet applet) {
		/* Seu codigo customizado aqui... */

	}

	private byte[] readContent(String arquivo) {
		
		byte[] result = null;
		try {
			File file = new File(arquivo);
			FileInputStream is = new FileInputStream(file);
			result = new byte[(int) file.length()];
			is.read(result);
			is.close();
		} catch (IOException ex) {
			logger.info(ex.getMessage());
		}
		return result;
	}

	private void writeContent(byte[] conteudo, String arquivo) {
		try {
			File file = new File(arquivo);
			FileOutputStream os = new FileOutputStream(file);
			os.write(conteudo);
			os.flush();
			os.close();
		} catch (IOException ex) {
			logger.info(ex.getMessage());
		}
	}
}