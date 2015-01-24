package ejemplo;
import java.util.Date;

/** Clase de ejemplo para demo Gradle en SG
 *
 * @author Enrique Zamudio
 */
public class HolaJava {

  private Date creado=new Date();

  /** Devuelve un saludo a la persona que se indique.
 * @param quien La persona a quien hay que saludar
 * @return Una cadena saludando a la persona indicada. */
  public String saluda(String quien) {
    return String.format("Hola, %s! (en Java)", quien);
  }

}
