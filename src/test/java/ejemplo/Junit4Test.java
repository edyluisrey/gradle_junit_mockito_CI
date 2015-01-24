package ejemplo;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Junit4Test extends Assert {
  String name;
  int age;
 
  public Junit4Test (String name, int age) {
    this.name = name;
    this.age =  age;
  }
 
  @Parameters
  public static Collection<Object[]>  data() {
    Object[][] data = new  Object[][]{{"Parameterized test1", 10}, 
     {"Parameterized test2", 20}};
    return Arrays.asList(data);
  }
 
  @Test
  public void testOut() {
    System.out.println(name + " " + age);
  }
}
