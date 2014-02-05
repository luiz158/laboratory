package br.gov.frameworkdemoiselle.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author escritorio
 */
public class CriptografiaUtil {

	/**
     *
     * @param mesnagem
     * @return
     */
    public static String getCodigoMd5(String mesnagem) {
		return stringHexa(gerarHash(mesnagem, "MD5"));
	}

	/**
     *
     * @param frase
     * @param algoritmo
     * @return
     */
    public static byte[] gerarHash(String frase, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private static String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0) {
                s.append('0');
            }
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}

    private CriptografiaUtil() {
    }


}
