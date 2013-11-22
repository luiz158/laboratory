/*
 * Copyright (c) Empresa de Processamento de Dados do Estado do Par�,
 * http://www.pa.muiraquita.quita.gov.br/, 1968-2008. Todos os direitos reservados. 
 * Software produzido pelo setor de tecnologia e inova��o (GTI).
 * 
 * A redistribui��o e utiliza��o � restrita ao uso interno da PRODEPA em
 * suas depend�ncias, salvo cedido a alguma outra entidade da esfera municipal,
 * estadual ou federal, o que deve ser uma permiss�o fornecida por escrito, 
 * com caracter�sticas legais, registrado no sistema de protocolo do Estado do
 * Par�.
 * 
 * Java, o mascote Duke e todas as variantes do logo de Java da Sun s�o direitos
 * atribuidos � Sun Microsystems. Ao pioneirismo em inventar e promulgar (e
 * padronizar) a linguagem java e seu ambiente da Sun e ao James Gosling, aqui
 * ficam nossos sinceros agradecimentos. Ao pioneirismo de Dennis Ritchie e Bjarne 
 * Stroustrup, da AT&T, por inventar as linguagens C e C++, predecessoras do java,
 * tamb�m ficam nossos sinceros agradecimentos.
 */
package br.gov.frameworkdemoiselle.prodepa.queryfilter.util;


import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.exception.EasyQueryFilterException;

public final class Property {

  /** Objeto interno de manipulacao beanUtils */
  private static transient PropertyUtilsBean pub = new PropertyUtilsBean();
  
  private static transient Logger log = LoggerFactory.getLogger(Property.class);
  
  private static Class<?>[] classesNumber = {Byte.class, Short.class, Integer.class,
    Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class};
  
  /**
   * Retorna as propriedades que uma classe tem.
   * Aten��o: apenas constar� nesta lista as propriedades que sejam realmente PROPRIEDADES
   * por defini��o, ou seja, campos acess�veis, encapsulados que tenham m�todos "get" e "set"
   * p�blicos devidamente declarados.
   * @param clazz classe a ser analizada
   * @return lista de propriedades acess�veis
   */
  public static List<String> getProperties(Class<?> clazz) {
    List<String> internalFields = new ArrayList<String>();
    // Captura o descritor de propriedades da classe informada
    PropertyDescriptor[] properties = pub.getPropertyDescriptors(clazz);
    for (PropertyDescriptor property: properties) {
      if (!property.getName().equals("class")) {
        internalFields.add(property.getName());
      }
    }
    return internalFields;
  }

  /**
   * Retorna as propriedades que uma classe tem.
   * Aten��o: apenas constar� nesta lista as propriedades que sejam realmente PROPRIEDADES
   * por defini��o, ou seja, campos acess�veis, encapsulados que tenham m�todos "get" e "set"
   * p�blicos devidamente declarados.
   * @param obj objecto a ser analizado
   * @return lista de propriedades acess�veis
   */
  public static List<String> getProperties(Object obj) {
    List<String> internalFields = new ArrayList<String>();
    // Captura o descritor de propriedades da classe informada
    PropertyDescriptor[] properties = pub.getPropertyDescriptors(obj.getClass());
    for (PropertyDescriptor property: properties) {
      if (pub.isReadable(obj, property.getName())) {
        internalFields.add(property.getName());
      }
    }
    return internalFields;
  }  
  
  /**
   * Retorna um {@link List} contendo os nomes das propriedades declaradas pela 
   * classe ou interface. Isto inclui propriedades public, private, protected e default,  
   * mas excl�i propriedades herdadas. 
   * 
   * @param clazz classe alvo
   * 
   * @return List<String> lista com os nomes das propriedades. 
   * */
  public static List<String> getFields(Class<?> clazz) {
    ArrayList<String> list = new ArrayList<String>();
    for (Field field: clazz.getDeclaredFields()) {
       list.add(field.getName());
    }
    return list;
  }
  
  /**
   * Obt�m o valor de uma determinada propriedade no objeto informado usando a classe 
   * {@link PropertyUtils} do projeto commons da Apache.
   * @param bean objeto a ser manipulado
   * @param nomePropriedade propriedade a ser obtida do bean
   * @return inst�ncia referida do bean pelo nomePropriedade
   * @throws EasyQueryFilterException 
   * @throws InternalException caso a propriedade n�o exista e/ou n�o esteja acess�vel (SecurityError)
   */
  public static Object getPropertyValue(Object bean, String nomePropriedade) throws EasyQueryFilterException {
    ////checkMandatoryProperties(bean, nomePropriedade);
    try {
      return PropertyUtils.getProperty(bean, nomePropriedade);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new EasyQueryFilterException("Acesso ilegal � propriedade " + nomePropriedade, e);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      throw new EasyQueryFilterException("Erro ao invocar o alvo " + nomePropriedade, e);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
      log.error("M�todo inexistente no alvo " + bean.getClass() + ": " + nomePropriedade);
      throw new EasyQueryFilterException("M�todo inexistente no alvo " + bean.getClass() + ": " + nomePropriedade, e);      
    }
  }
  
  /**
   * Atribui o valor de uma determinada propriedade no objeto informado.
   * @param bean objeto a ser manipulado
   * @param nomePropriedade propriedade a ser setada
   * @param valor novo valor da propriedade
   * @throws EasyQueryFilterException 
   * @throws InternalException caso a propriedade n�o exista e/ou n�o esteja acess�vel
   */
  public static void setFieldValue(Object bean, String nomePropriedade, Object valor) throws EasyQueryFilterException {
    ////checkMandatoryProperties(bean, nomePropriedade);
    try {
      Field field;
      field = bean.getClass().getDeclaredField(nomePropriedade);
      field.setAccessible(true);
      field.set(bean, valor);
    } catch (NoSuchFieldException e) {
      log.error("Field inexistente no alvo objeto " + bean.getClass().getName() + ": " + nomePropriedade);
      throw new EasyQueryFilterException("Field inexistente no alvo objeto " + bean.getClass().getName() + ": " + nomePropriedade, null);
    } catch (Exception e) {
      log.error("Erro ao setar field: " + nomePropriedade);
      e.printStackTrace();
    }      
  }
  
  /**
   * Obt�m um objeto {@link Field} baseado objeto informado.
   * @param clazz classe a ser manipulado
   * @param fieldName propriedade a ser obtida do bean
   * @return um objeto Field representando a propriedade informada.
   * @throws EasyQueryFilterException 
   * @throws InternalException caso a propriedade n�o exista e/ou n�o esteja acess�vel (SecurityError)
   */
  public static Field getField(Class<?> clazz, String fieldName) throws EasyQueryFilterException {
    Field field;
    try {
      field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field;
    } catch (NoSuchFieldException e) {
      Class<?> clazzSuper = clazz.getSuperclass();
      if (clazzSuper != null) {
        return getField(clazzSuper, fieldName);
      }
      else {
        log.error("Field inexistente no alvo " + clazz.getSimpleName() + ": " + fieldName);
        throw new EasyQueryFilterException("O objeto fonte estava nulo", null);
      }
    } catch (Exception e) {
      log.error("Erro ao setar field: " + fieldName);
      throw new EasyQueryFilterException("Erro ao acessar o campo '" + fieldName + "'", e);
    }
  }
  
  /**
   * Obt�m um objeto {@link Field} baseado objeto informado.
   * @param bean objeto a ser manipulado
   * @param fieldName propriedade a ser obtida do bean
   * @return um objeto Field representando a propriedade informada.
   * @throws EasyQueryFilterException 
   * @throws InternalException caso a propriedade n�o exista e/ou n�o esteja acess�vel (SecurityError)
   */
  public static Field getField(Object bean, String fieldName) throws EasyQueryFilterException {
    Field field;
    field = getField(bean.getClass(), fieldName);
    field.setAccessible(true);
    return field;
  }
  
  /**
   * Obt�m o valor de uma determinada propriedade no objeto informado.
   * @param bean objeto a ser manipulado
   * @param nomePropriedade propriedade a ser obtida do bean
   * @return inst�ncia referida do bean pelo nomePropriedade
   * @throws EasyQueryFilterException 
   * @throws InternalException caso a propriedade n�o exista e/ou n�o esteja acess�vel (SecurityError)
   */
  public static Object getFieldValue(Object bean, String nomePropriedade) throws EasyQueryFilterException {
    //checkMandatoryProperties(bean, nomePropriedade);
    try {
      return getField(bean, nomePropriedade).get(bean);
    } catch (Exception e) {
      log.error("Erro ao setar field: " + nomePropriedade);
      throw new EasyQueryFilterException("Erro Generico", e);
    }      
  }

  /**
   * Retorna as anota��es que temos em um field de um objeto.
   * @param bean Objeto alvo
   * @param fieldName Nome do field que queremos as anota��es
   * @return Vetor de anota��es
   * @throws EasyQueryFilterException 
   */
  public static Annotation[] getFieldAnnotations(Object bean, String fieldName) throws EasyQueryFilterException {
    //checkMandatoryProperties(bean, fieldName);
    return getField(bean, fieldName).getAnnotations();
  }

  /**
   * Retorna uma anota��o que temos em um field de um objeto.
   * @param <T> qualquer interface que extenda a interface {@link Annotation}
   * @param bean Objeto alvo
   * @param fieldName Nome do field que queremos as anota��es
   * @param annotationClass Nome da anota��o procurada
   * @return Inst�ncia da anota��o
   * @throws EasyQueryFilterException 
   */
  public static <T extends Annotation> T getFieldAnnotation(Object bean, String fieldName, Class<T> annotationClass) throws EasyQueryFilterException {
    //checkMandatoryProperties(bean, fieldName);
    return getField(bean, fieldName).getAnnotation(annotationClass);
  }
  
  /**
   * Retorna uma anota��o que temos em um field de um objeto.
   * @param <T> qualquer interface que extenda a interface {@link Annotation} 
   * @param bean Objeto alvo
   * @param field Nome do field que queremos as anota��es
   * @param annotationClass Nome da anota��o procurada
   * @return Inst�ncia da anota��o
   */
  public static <T extends Annotation> T getFieldAnnotation(Object bean, Field field, Class<T> annotationClass) {
    //checkMandatoryProperties(bean, field.getName());
    return field.getAnnotation(annotationClass);
  }

  
  /**
   * Define um valor para o atributo de um bean.
   * 
   * Este m�todo � "semi-inteligente": faz a convers�o entre propriedade e valor, contanto
   * que sejam num�ricos
   *  
   * @param bean inst�ncia a ser manipulada
   * @param nomePropriedade propriedade a ser setada no bean 
   * @param valor valor a ser setado na propriedade passada para o bean
   * @throws EasyQueryFilterException 
   * 
   * @throws InternalError se n�o for poss�vel acessar a propriedade, 
   * se a propriedade n�o existir ou se a propriedade n�o possuir os metodos de acesso (get/set)
   */
  public static void setPropertyValue(Object bean, String nomePropriedade, Object valor) throws EasyQueryFilterException {
    //checkMandatoryProperties(bean, nomePropriedade);
    try {
      if (bean == null) {
        throw new EasyQueryFilterException("Ao tentar escrever em um campo: O objeto fonte estava nulo", null);
      }
      Class<?> classeDestino = getClassType(bean, nomePropriedade);

      if (valor == null) {
        log.trace("Destino: " + nomePropriedade + "[" + classeDestino.getName() + "], Fonte: " + nomePropriedade + ", VALOR NULO");
        PropertyUtils.setProperty(bean, nomePropriedade, null);
        return;
      }
      
      Class<?> classeFonte = valor.getClass(); 
      
      if (classeDestino == null) {
        log.trace("Destino: " + nomePropriedade + ", Fonte: " + bean + ", (Classe Desconhecida): " + valor);
        PropertyUtils.setProperty(bean, nomePropriedade, valor);
        return;
      }
      
      log.trace("Destino: " + nomePropriedade + "[" + classeDestino.getName() + "], Fonte: " + nomePropriedade + "[" + valor.getClass().getName() + "], valor: " + valor);
      
      if (checkNumber(classeDestino, classeFonte)) {
        log.trace("Convers�o entre num�ricos");
        PropertyUtils.setProperty(bean, nomePropriedade, convertNumberToTargetClass((Number)valor, classeDestino));
      }
      else if (classeDestino == Character.class && classeFonte == String.class) {
        log.trace("DestType: CHARACTER e FontType: STRING, fazendo convers�o");
        if (((String)valor).length() > 1) {
          log.warn("A convers�o aproveitou apenas o 1o. caracter '" + ((String)valor).toCharArray()[0] + "'");
        }
        PropertyUtils.setProperty(bean, nomePropriedade, ((String)valor).toCharArray()[0]);
      }
      else {
        PropertyUtils.setProperty(bean, nomePropriedade, valor);
      }
    } catch (IllegalArgumentException e) {
      throw new EasyQueryFilterException("Acesso ilegal � propriedade " + nomePropriedade, e);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new EasyQueryFilterException("Acesso ilegal � propriedade " + nomePropriedade, e);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      throw new EasyQueryFilterException("Erro ao invocar o alvo " + nomePropriedade, e);
    } catch (NoSuchMethodException e) {
      log.warn("M�todo inexistente no alvo (provavelmente field): " + nomePropriedade);
      throw new EasyQueryFilterException("Erro ao invocar o alvo " + nomePropriedade, e);
    } catch (Exception e) {
      e.printStackTrace();
      throw new EasyQueryFilterException("Erro ao invocar o alvo " + nomePropriedade, e);
    }
  }
  
  /**
   * Retorna a classe java que representa o tipo da propriedade em quest�o.
   * 
   * @param bean objeto que contem a propriedade a ser setada
   * @param nomePropriedade nome da propriedade a ser setada
   * 
   * @return a classe da propriedade em quest�o.
   * @throws EasyQueryFilterException 
   * 
   * @throws InternalError se n�o for poss�vel acessar a propriedade, 
   * se a propriedade n�o existir ou se a propriedade n�o possuir os metodos de acesso (get/set)
   * */
  public static Class<?> getClassType(Object bean, String nomePropriedade) throws EasyQueryFilterException {
    //checkMandatoryProperties(bean, nomePropriedade);
    try {
      log.trace("Tentando obter a classe da propriedade " + nomePropriedade + " no bean " + bean);
      return PropertyUtils.getPropertyType(bean, nomePropriedade);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new EasyQueryFilterException("Acesso ilegal � propriedade " + nomePropriedade, e);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      throw new EasyQueryFilterException("Erro ao invocar o alvo " + nomePropriedade, e);
    } catch (NoSuchMethodException e) {
      throw new EasyQueryFilterException("M�todo inexistente " + nomePropriedade, e);
    }

  }
  
  public static boolean checkNumber(Class<?>... targetClass) {
    boolean check = false;
    for (Class<?> classTargetParam: targetClass) {
      check = false;
      for (Class<?> classTargetLoop: classesNumber) {
        if (classTargetParam == classTargetLoop) {
          check = check || true; 
          break;
        }
      }
      if (!check) {
        return false;
      }
    }
    return true;
  }
  
  public static Number convertNumberToTargetClass(Number number, Class<?> targetClass) throws EasyQueryFilterException {

    checkIntegrity(number, targetClass);
    
    if (targetClass.isInstance(number)) {
      return number;
    } 
    else if (targetClass.equals(Byte.class)) {
      long value = number.longValue();
      if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
        raiseOverflowException(number, targetClass);
      }
      return new Byte(number.byteValue());
    } 
    else if (targetClass.equals(Short.class)) {
      long value = number.longValue();
      if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
        raiseOverflowException(number, targetClass);
      }
      return new Short(number.shortValue());
    } 
    else if (targetClass.equals(Integer.class)) {
      long value = number.longValue();
      if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
        raiseOverflowException(number, targetClass);
      }
      return new Integer(number.intValue());
    } 
    else if (targetClass.equals(Long.class)) {
      return new Long(number.longValue());
    } 
    else if (targetClass.equals(Float.class)) {
      return new Float(number.floatValue());
    } 
    else if (targetClass.equals(Double.class)) {
      return new Double(number.doubleValue());
    } 
    else if (targetClass.equals(BigInteger.class)) {
      return BigInteger.valueOf(number.longValue());
    } 
    else if (targetClass.equals(BigDecimal.class)) {
      // utilizar BigDecimal(String) aqui, para se precaver de imprevisibilidades
      // ao inv�s do uso de BigDecimal(double)
      // (veja o javadoc de BigDecimal para detalhes)
      return new BigDecimal(number.toString());
    } 
    else {
      throw new EasyQueryFilterException(
              "N�o � poss�vel converter o n�mero [" + number
              + "] do tipo [" + number.getClass().getName()
              + "] para a classe do tipo [" + targetClass.getName() + "]: overflow", null);
    }
  }
  
  private static void checkIntegrity(Number number, Class<?> targetClass) throws EasyQueryFilterException {
    if (number == null) { 
      throw new EasyQueryFilterException( "Para fazer convers�es � necess�rio informar um Number", null);
    }
    if (targetClass == null) {
      throw new EasyQueryFilterException( "Para fazer convers�es � necess�rio informar a classe alvo", null);
    }
  }
  
  private static void raiseOverflowException(Number number, Class<?> targetClass) throws EasyQueryFilterException {
    throw new EasyQueryFilterException( 
            "N�o � poss�vel converter o n�mero [" + number
            + "] do tipo [" + number.getClass().getName()
            + "] para a classe do tipo [" + targetClass.getName() + "]: overflow", null);
  }
  
}
