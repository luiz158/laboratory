/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.demoiselle.portal.evento.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.pgxp.util.converter.Data;
import br.com.pgxp.util.exception.PGXPException;

/**
 *
 * @author 70744416353
 */
public class CSV<pojo extends Serializable> {

    // private final Class<pojo> pojozz;
    public CSV() {
        // pojozz = (Class<pojo>) ((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    public List<pojo> toEntity(BufferedReader in, Class<?> cls) throws ParseException, FileNotFoundException, InstantiationException, IllegalAccessException, PGXPException {
    	
    	
    	StringTokenizer colunas;
        List<pojo> lista = new ArrayList<pojo>();
        List<String> registros = new ArrayList<String>();
        List<String> atributos = new ArrayList<String>();
        String str;
        int cont = 0;
        try {
            while (in.ready()) {
                str = in.readLine();
                if (cont < 1) {
                    colunas = new StringTokenizer(str, ";");
                    atributos = Util.stringTokenizerToList(colunas);
                } else {
                    StringTokenizer st = new StringTokenizer(str, ";");
                    registros = Util.stringTokenizerToList(st);
                    lista.add(organizaRegistro(registros, cls, atributos));
                }
                cont++;
            }
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public List<pojo> toEntity(String caminhoArquivo, Class<?> cls) throws ParseException,
                                                                           FileNotFoundException,
                                                                           InstantiationException,
                                                                           IllegalAccessException,
                                                                           PGXPException {

        BufferedReader in = null;
        in = new BufferedReader(new FileReader(caminhoArquivo));
        return toEntity(in, cls);
        		

    }

    @SuppressWarnings("unchecked")
	private pojo organizaRegistro(List<String> registro, Class<?> cls, List<String> atributos)
            throws PGXPException, InstantiationException, IllegalAccessException {

        String nmAtributoNaClasse = null;
        Class<?> tipoAtributo = null;
        final pojo pj = (pojo) cls.newInstance();

        try {

            int t = 0;

            for (int x = 0; x < atributos.size(); x++) {

                nmAtributoNaClasse = Util.getNomeAtributoFromColuna(cls, atributos.get(t));
                if (nmAtributoNaClasse != null && !nmAtributoNaClasse.isEmpty()) {
                    tipoAtributo = cls.getDeclaredField(nmAtributoNaClasse).getType();
                    Method m = cls.getDeclaredMethod(Util.criaSetNomeMetodo(nmAtributoNaClasse), tipoAtributo);

                    if (registro.size() > x) {
                        if (tipoAtributo.equals(Integer.class)) {
                            m.invoke(pj, Integer.parseInt(registro.get(x)));
                        } else if (tipoAtributo.equals(Date.class)) {
                            m.invoke(pj, Data.converterData("yyyyMMdd", registro.get(x)));
                        } else if (tipoAtributo.equals(String.class)) {
                            m.invoke(pj, registro.get(x));
                        }
                    }
                }
                t++;
            }

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Logger.getLogger(CSV.class.getName()).log(Level.INFO, pj.toString());
        return pj;
    }
}
