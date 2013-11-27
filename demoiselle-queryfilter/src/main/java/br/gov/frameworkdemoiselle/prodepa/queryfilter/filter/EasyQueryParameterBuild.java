package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.And;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.FirstResult;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.MaxResult;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.Or;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.TextSearch;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.AndConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.OrConditionType;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.TextSearchStyle;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.exception.EasyQueryFilterException;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.stereotype.QueryFilter;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.types.Range;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.Property;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.ReflectionHelper;

public class EasyQueryParameterBuild<T> {
  
  private Class<?> beanClass;
  private Class<?> filterClass;
  
  private EasyQuery<T> criteria;
  
  public EasyQueryParameterBuild(EasyQuery<T> criteria, Class<?> beanClass) {
    super();
    this.beanClass = beanClass;
    this.criteria = criteria;
  }

  public void addFilters(Object filter) throws EasyQueryFilterException {
    
    //TODO Planejar estrategia de CACHE urgentemente

    filterClass = ReflectionHelper.getOriginalClassFromJavassist(filter);
    
    QueryFilter queryFilterAnnot = filterClass.getAnnotation(QueryFilter.class);
    
    if(queryFilterAnnot == null) {
      
      throw new EasyQueryFilterException("O filtro não está anotado com @QueryFilter.");
      
    } else {
      
      List<Field> fields = ReflectionHelper.getFields(filterClass);
      
      //Adicionar os filtros
      for (Field filterField : fields) {
        
        if(isNotNull(filter, filterField)) {

          TextSearch textSearch = filterField.getAnnotation(TextSearch.class);
          
          //A Busca será feita sobre os campos String do objeto
          if(textSearch != null) {
            
            makeTextSearchCondition(filter, filterField, textSearch.strategy());
            
          } else {
            //TODO Se não houver anotacoes ? Fazer o que ?
            
            if(filterField.isAnnotationPresent(FirstResult.class) 
                    || filterField.isAnnotationPresent(MaxResult.class)) {
              continue;
            }
            
            //Validacoes de combinações ilegais de anotações 
            if(filterField.isAnnotationPresent(And.class) && filterField.isAnnotationPresent(Or.class)) {
              throw new EasyQueryFilterException("Conbinação ilegal de anotações: Não use And e OR no mesmo campo. ");
            }
            
            if(filterField.isAnnotationPresent(And.class)) {
              
              And and = filterField.getAnnotation(And.class);
              
              if(and.attributeNames().length == 0) {
                makeAndCondition(and.conditionType(), and.ignoreCase(), filterField.getName(), filter, filterField);
              } else {
                for(String attribute : and.attributeNames()) {
                  makeAndCondition(and.conditionType(), and.ignoreCase(), attribute, filter, filterField);
                }
              }
              
              
            } else if(filterField.isAnnotationPresent(Or.class)) {
              
              Or or = filterField.getAnnotation(Or.class);
              
              if(or.attributeNames().length == 0) {
                makeOrCondition(or.conditionType(), or.ignoreCase(), filterField.getName(), filter, filterField);
              } else {
                for(String attribute : or.attributeNames()) {
                  makeOrCondition(or.conditionType(), or.ignoreCase(), attribute, filter, filterField);
                }
              }
            } else {
              if(isTextMemberClass(filterField.getClass())) {
               
                makeAndCondition(AndConditionType.LIKE, true, filterField.getName(), filter, filterField);
                
              } else {

                makeAndCondition(AndConditionType.EQUAL, true, filterField.getName(), filter, filterField);
                
              }
              
            }
            
          }
        }
      }
      
      /*
       * Ordenacao
       */
      
      for(String order : queryFilterAnnot.orderByAsc()) {
        if(StringUtils.isNotEmpty(order)) {
          criteria.orderByAsc(order);
        }
      }
      
      for(String order : queryFilterAnnot.orderByDesc()) {
        if(StringUtils.isNotEmpty(order)) {
          criteria.orderByDesc(order);
        }
      }
      
      /*
       * Paginacao
       * 
       */
      //Firstresult
      List<Field> firstResultAnnts = ReflectionHelper.getFieldsWithAnnotation(filter.getClass(), FirstResult.class);
      if(firstResultAnnts.size() > 0) {
        if(firstResultAnnts.size() > 1) {
          throw new EasyQueryFilterException("Existe mais que um campo para FirstResult em "+ filter.getClass().getName());
        }
        
        Object value = Property.getPropertyValue(filter, firstResultAnnts.get(0).getName());
        if(value != null) {
          criteria.setFirstResult((Integer.parseInt(value.toString())));
        }
      }
      
      //Maxtresult
      List<Field> maxResultAnnts = ReflectionHelper.getFieldsWithAnnotation(filter.getClass(), MaxResult.class);
      if(maxResultAnnts.size() > 0) {
        if(maxResultAnnts.size() > 1) {
          throw new EasyQueryFilterException("Existe mais que um campo para MaxResult em "+ filter.getClass().getName());
        }
        
        Object value = Property.getPropertyValue(filter, maxResultAnnts.get(0).getName());
        if(value != null) {
          criteria.setMaxResults((Integer.parseInt(value.toString())));
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void makeAndCondition(AndConditionType type, Boolean toLowerCase, String attributeName, Object filter, Field fieldFilter) throws EasyQueryFilterException {

    //TODO O AndConditionType default será LIKE para String e Equals para os DEMAIS ?
    
    Class<?> targetClassType = Property.getField(this.beanClass, attributeName).getType();
    if(targetClassType == null) {
      throw new EasyQueryFilterException("O campo \""+attributeName+"\" não existem em "+ this.beanClass.getName());
    }
    
    Boolean isTextField = isTextMemberClass(targetClassType);
    
    if(toLowerCase) {
      if(!isTextField) {
        toLowerCase = false;
      }
    }
    
    Object value = Property.getPropertyValue(filter, fieldFilter.getName());
    

    if (AndConditionType.EQUAL.equals(type)) {
      criteria.andEquals(toLowerCase, attributeName, value);
    } else

    if (AndConditionType.NOT_EQUALS.equals(type)) {
      criteria.andNotEquals(toLowerCase, attributeName, value);
    } else 
      
    if (AndConditionType.GT.equals(type)) {
      criteria.andGreaterThan(toLowerCase, attributeName, value);
    } else

    /*if (AndConditionType.STRING_GREATER_THAN.equals(type)) {
      criteria.andGreaterThan(toLowerCase, attributeName, (String) value);
    } else

    if (AndConditionType.CALENDAR_GREATER_THAN.equals(type)) {
      criteria.andGreaterThan(toLowerCase, attributeName, (Calendar) value);
    } else

    if (AndConditionType.DATE_GREATER_THAN.equals(type)) {
      criteria.andGreaterThan(toLowerCase, attributeName, (Date) value);
    } else*/

    if (AndConditionType.GE.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, value);
    } else

   /* if (AndConditionType.STRING_GREATER_OR_EQUAL_TO.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, (String) value);
    } else

    if (AndConditionType.CALENDAR_GREATER_OR_EQUAL_TO.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, (Calendar) value);
    } else

    if (AndConditionType.DATE_GREATER_OR_EQUAL_TO.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, (Date) value);
    } else*/

    if (AndConditionType.LT.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, value);
    } else

    /*if (AndConditionType.STRING_LESS_THAN.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, (String) value);
    } else

    if (AndConditionType.CALENDAR_LESS_THAN.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, (Calendar) value);
    } else

    if (AndConditionType.DATE_LESS_THAN.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, (Date) value);
    } else*/

    if (AndConditionType.LE.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, value);
    } else

    /*if (AndConditionType.STRING_LESS_OR_EQUAL_TO.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, (String) value);
    } else

    if (AndConditionType.CALENDAR_LESS_OR_EQUAL_TO.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, (Calendar) value);
    } else

    if (AndConditionType.DATE_LESS_OR_EQUAL_TO.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, (Date) value);
    } else*/

    if (AndConditionType.BETWEEN.equals(type)) {
      
      Object[] values;

      if(value instanceof Range) {
        Range r = (Range) value;
        values = new Object[]{r.getStart(), r.getEnd()};
      } else  if(value instanceof Collection) {
        values = ((Collection) value).toArray();
      } else {
        values = (Object[]) value;
      }
      criteria.andBetween(toLowerCase, attributeName, values[0], values[1]);
    } else

    /*if (AndConditionType.STRING_BETWEEN.equals(type)) {
      Object[] values = (Object[]) value;
      criteria.andBetween(toLowerCase, attributeName, values[0].toString(), values[1].toString());
    } else

    if (AndConditionType.CALENDAR_BETWEEN.equals(type)) {
      Object[] values = (Object[]) value;
      criteria.andBetween(toLowerCase, attributeName, (Calendar) values[0], (Calendar) values[1]);
    } else

    if (AndConditionType.DATE_BETWEEN.equals(type)) {
      Object[] values = (Object[]) value;
      criteria.andBetween(toLowerCase, attributeName, (Date) values[0], (Date) values[1]);
    } else*/

    if (AndConditionType.IS_NULL.equals(type)) {
      criteria.andIsNull(attributeName);
    } else

    if (AndConditionType.IS_NOT_NULL.equals(type)) {
      criteria.andIsNotNull(attributeName);
    } else

    if (AndConditionType.IS_EMPTY.equals(type)) {
      criteria.andCollectionIsEmpty(attributeName);
    } else

    if (AndConditionType.IS_NOT_EMPTY.equals(type)) {
      criteria.andCollectionIsNotEmpty(attributeName);
    } else

    /*if (AndConditionType.SET_IS_EMPTY.equals(type)) {
      criteria.andCollectionIsEmpty(attributeName);
    } else

    if (AndConditionType.SET_IS_NOT_EMPTY.equals(type)) {
      criteria.andCollectionIsNotEmpty(attributeName);
    } else

    if (AndConditionType.COLLECTION_IS_EMPTY.equals(type)) {
      criteria.andCollectionIsEmpty(attributeName);
    } else

    if (AndConditionType.COLLECTION_IS_NOT_EMPTY.equals(type)) {
      criteria.andCollectionIsNotEmpty(attributeName);
    } else*/

    if (AndConditionType.LIKE.equals(type)) {
      criteria.andStringLike(toLowerCase, attributeName, "%" + value + "%");
    } else

    if (AndConditionType.NOT_LIKE.equals(type)) {
      criteria.andStringNotLike(toLowerCase, attributeName, "%" + value + "%");
    } else

    if (AndConditionType.LIKE_STARTS_WITH.equals(type)) {
      criteria.andStringLike(toLowerCase, attributeName, value + "%");
    } else

    if (AndConditionType.LIKE_ENDS_WITH.equals(type)) {
      criteria.andStringLike(toLowerCase, attributeName, "%"+ value);
    } else
    
   /* if (AndConditionType.STRING_IN.equals(type)) {
      criteria.andStringLike(toLowerCase, attributeName, value + "%");
    } else*/
      
    if (AndConditionType.IN.equals(type)) {
      //TODO 
      
      if(isTextField) {
        
        criteria.andStringIn(toLowerCase, attributeName, castFromList(value, String.class));
        
      } else {
        //??????
      }
      
    } else 
    
    if (AndConditionType.NOT_IN.equals(type)) {
      //TODO
      if(isTextField) {

        criteria.andStringNotIn(toLowerCase, attributeName, castFromList(value, String.class));
        
      } else {
        //??????
      }
      
      
    } else 
    
    {
      criteria.andStringIn(attributeName, (List) value); // STRING_IN

    }
  }
  
  public void makeOrCondition(OrConditionType type, Boolean toLowerCase, String attributeName, Object filter, Field fieldFilter) throws EasyQueryFilterException {

    //TODO O AndConditionType default será LIKE para String e Equals para os DEMAIS ?
    
    Object value = Property.getPropertyValue(filter, fieldFilter.getName());
    
    /*if(value.getClass().isArray()) {
      Object[] params = (Object[]) value;
      for (Object object : params) {
        
        criteria.o(index, attributeName, value)
        
      }
      
    } else if(value instanceof Collection) { 
      Collection[] params = (Collection[]) value;
      
      for (Collection collection : params) {
        
      }
      
    } else {*/
    
      if (OrConditionType.EQUAL.equals(type)) {

        criteria.orEquals(toLowerCase, attributeName, value);
        
      } else
      if (OrConditionType.NOT_EQUAL.equals(type)) {
        criteria.orNotEquals(toLowerCase, attributeName, value);
      }
      
    //}
    
    //criteria.add
    
    
    /*
    if (OrConditionType.GT.equals(type)) {
      criteria.orGreaterThan(toLowerCase, attributeName, value);
    } else

    if (OrConditionType.STRING_GREATER_THAN.equals(type)) {
      criteria.andGreaterThan(toLowerCase, attributeName, (String) value);
    } else

    if (OrConditionType.CALENDAR_GREATER_THAN.equals(type)) {
      criteria.andGreaterThan(toLowerCase, attributeName, (Calendar) value);
    } else

    if (OrConditionType.DATE_GREATER_THAN.equals(type)) {
      criteria.andGreaterThan(toLowerCase, attributeName, (Date) value);
    } else

    if (OrConditionType.GE.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, value);
    } else

    if (OrConditionType.STRING_GREATER_OR_EQUAL_TO.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, (String) value);
    } else

    if (OrConditionType.CALENDAR_GREATER_OR_EQUAL_TO.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, (Calendar) value);
    } else

    if (OrConditionType.DATE_GREATER_OR_EQUAL_TO.equals(type)) {
      criteria.andGreaterOrEqualTo(toLowerCase, attributeName, (Date) value);
    } else

    if (OrConditionType.LT.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, value);
    } else

    if (OrConditionType.STRING_LESS_THAN.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, (String) value);
    } else

    if (OrConditionType.CALENDAR_LESS_THAN.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, (Calendar) value);
    } else

    if (OrConditionType.DATE_LESS_THAN.equals(type)) {
      criteria.andLessThan(toLowerCase, attributeName, (Date) value);
    } else

    if (OrConditionType.LE.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, value);
    } else

    if (OrConditionType.STRING_LESS_OR_EQUAL_TO.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, (String) value);
    } else

    if (OrConditionType.CALENDAR_LESS_OR_EQUAL_TO.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, (Calendar) value);
    } else

    if (OrConditionType.DATE_LESS_OR_EQUAL_TO.equals(type)) {
      criteria.andLessOrEqualTo(toLowerCase, attributeName, (Date) value);
    } else

    if (OrConditionType.BETWEEN.equals(type)) {
      Object[] values = (Object[]) value;
      criteria.andBetween(toLowerCase, attributeName, (Number) values[0], (Number) values[1]);
    } else

    if (OrConditionType.STRING_BETWEEN.equals(type)) {
      Object[] values = (Object[]) value;
      criteria.andBetween(toLowerCase, attributeName, values[0].toString(), values[1].toString());
    } else

    if (OrConditionType.CALENDAR_BETWEEN.equals(type)) {
      Object[] values = (Object[]) value;
      criteria.andBetween(toLowerCase, attributeName, (Calendar) values[0], (Calendar) values[1]);
    } else

    if (OrConditionType.DATE_BETWEEN.equals(type)) {
      Object[] values = (Object[]) value;
      criteria.andBetween(toLowerCase, attributeName, (Date) values[0], (Date) values[1]);
    } else

    if (OrConditionType.IS_NULL.equals(type)) {
      criteria.andIsNull(attributeName);
    } else

    if (OrConditionType.IS_NOT_NULL.equals(type)) {
      criteria.andIsNotNull(attributeName);
    } else

    if (OrConditionType.LIST_IS_EMPTY.equals(type)) {
      criteria.andCollectionIsEmpty(attributeName);
    } else

    if (OrConditionType.LIST_IS_NOT_EMPTY.equals(type)) {
      criteria.andCollectionIsNotEmpty(attributeName);
    } else

    if (OrConditionType.SET_IS_EMPTY.equals(type)) {
      criteria.andCollectionIsEmpty(attributeName);
    } else

    if (OrConditionType.SET_IS_NOT_EMPTY.equals(type)) {
      criteria.andCollectionIsNotEmpty(attributeName);
    } else

    if (OrConditionType.COLLECTION_IS_EMPTY.equals(type)) {
      criteria.andCollectionIsEmpty(attributeName);
    } else

    if (OrConditionType.COLLECTION_IS_NOT_EMPTY.equals(type)) {
      criteria.andCollectionIsNotEmpty(attributeName);
    } else

    if (OrConditionType.STRING_LIKE.equals(type)) {
      criteria.andStringLike(toLowerCase, attributeName, "%" + value + "%");
    } else

    if (OrConditionType.STRING_NOT_LIKE.equals(type)) {
      criteria.andStringNotLike(toLowerCase, attributeName, "%" + value + "%");
    } else

    if (OrConditionType.STRING_LIKE_STARTS_WITH.equals(type)) {
      criteria.andStringLike(toLowerCase, attributeName, "%" + value);
    } else

    if (OrConditionType.STRING_LIKE_ENDS_WITH.equals(type)) {
      criteria.andStringLike(toLowerCase, attributeName, value + "%");
    } else {

      criteria.andStringIn(attributeName, (List) value); // STRING_IN

    }*/
  }
  
  
  //TODO
  private void makeTextSearchCondition(Object filter, Field fieldFilter, TextSearchStyle style) throws EasyQueryFilterException {
   
    List<Field> fields = ReflectionHelper.getFieldsWithType(beanClass, String.class);
    int i = 0;
    for (Field field : fields) {
      
      //TODO Essa configuracao pode ser mais apurada
      //TODO Ver estrategia melhor para essas decisoes dentro do loop
      
      if(style.equals(TextSearchStyle.Like)) {
        
        criteria.addAndSeparatedByOr(i, field.getName(), getStringValue(filter, fieldFilter.getName(), style));
        
      } else if(style.equals(TextSearchStyle.LikeIgnoreCase)) {
        
        criteria.addAndSeparatedByOr(true, i, field.getName(), getStringValue(filter, fieldFilter.getName(), style));
        
      //Esses caso nao são suportados pelo EasyCriteria
      } else if(style.equals(TextSearchStyle.NotLike)) {
        
        criteria.andStringNotLike(field.getName(), getStringValue(filter, fieldFilter.getName(), style));
        
      } else if(style.equals(TextSearchStyle.NotLikeIgnoreCase)) {
        
        criteria.andStringNotLike(true, field.getName(), getStringValue(filter, fieldFilter.getName(), style));
        
      }
      
      i++;
    }
  }
  
  private String getStringValue(Object filter, String propertyName, TextSearchStyle style) throws EasyQueryFilterException {
    
    String value = (String) Property.getPropertyValue(filter, propertyName);
    
    if(style.equals(TextSearchStyle.LikeIgnoreCase)) {
      value = value.toUpperCase();
    }
    
    return value;
  }
  
  
  private Boolean isNotNull(Object filter, Field field) {
    return !isNull(filter, field);
  }
  
  private Boolean isNull(Object filter, Field field) {
    
    if(field.getName().equals("serialVersionUID")) {
      return true;
    }
    
    try {
      
      Object val = Property.getPropertyValue(filter, field.getName());
      
      if(field.getType().equals(String.class)) {
        if(val == null || val.equals("")) {
          return true;
        } else {
          return false;
        }
      }
      
      return val == null;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return true;
    } catch (EasyQueryFilterException e) {
      e.printStackTrace();
      return true;
    }
  }
  
  private Boolean isTextMemberClass(Class type) {
    if(type.equals(String.class)
            || type.equals(char.class)
            || type.equals(Character.class)
            ) {
      return true;
    } else {
      return false;
    }
  }
  
  private <T> List<T> castFromList(Object value, Class<T> listType) {
    try {

      List<T> list = (List<T>) value;
      
      return list;
      
    } catch (Exception e) {
      throw new IllegalArgumentException("O valor passado deveria ser uma lista.");
    }
  }
}
