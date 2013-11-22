package br.gov.frameworkdemoiselle.prodepa.queryfilter.util;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model.Person;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.util.ReflectionHelper;

public class ReflectionHelperTest {

  @Test
  public void testGetThreadContextClassLoader() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetConstructor() {
    fail("Not yet implemented");
  }

  @Test
  public void testNewInstance() {
    fail("Not yet implemented");
  }

  @Test
  public void testIsClassPresent() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetFields() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetFieldsWithAnnotation() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetMethodsWithAnnotation() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetFieldsWithType() {
    List<Field> fields = ReflectionHelper.getFieldsWithType(Person.class, String.class);
    
    assertEquals(fields.size(), 2);
    
    assertEqualsName(fields, Arrays.asList("name","nickName"));
  }

  private void assertEqualsName(List<Field> fields, List<String> names) {
    List<String> fieldNames = new ArrayList<String>();
    
    for (Field field : fields) {
      fieldNames.add(field.getName());
    }
    
    assertEquals(fieldNames, names);
    
  }
  
}
