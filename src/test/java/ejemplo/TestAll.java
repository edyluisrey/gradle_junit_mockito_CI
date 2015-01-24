package ejemplo;

import org.junit.*;

public class TestAll {

  @Test public void testJava() {
    HolaJava o = new HolaJava();
    assert "Hola, Mundo! (en Java)".equals(o.saluda("Mundo"));
  }

  /*@Test public void testGroovy() {
    HolaGroovy o = new HolaGroovy();
    assert “Hola, Mundo! (en Groovy)”.equals(o.saluda(“Mundo”));
  }*/

}
