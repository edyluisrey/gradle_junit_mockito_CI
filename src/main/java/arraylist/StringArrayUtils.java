package arraylist;

import java.util.Vector;

/** 
 * Clase con métodos de utilidad sobre Arrays de cadenas de caracteres 
 */  
public class StringArrayUtils {  
      
    // Atributos de la clase  
    private java.util.Vector<String> elements;  
      
    /** 
     * Constructor  
     */  
    public StringArrayUtils(String[] data){  
        // Verificamos que la lista tenga valores  
        if ((data == null) || (data.length == 0))  
            throw new IllegalArgumentException();  
          
        this.elements = new Vector<String>();  
        for (String element : data){  
            elements.addElement(element);  
        }  
    }  
      
    /** 
     * @return Devuelve la cadena que tiene más caracteres 
     */  
    public String getMaxLength(){  
        String max = "";  
          
        for (String element : elements){  
            if (element.length() > max.length()){  
                max = element;  
            }  
        }  
          
        return max;  
    }  
      
    /** 
     * @return Devuelve la suma de la longitud de todas las cadenas 
     */  
    public int getTotalLength(){  
        int total = 0;  
          
        for (String d : elements){  
            total += d.length();  
        }  
          
        return total;     
    }  
      
    /** 
     * @param searched Cadena buscada 
     * @return Devuelve la posición de un elemento dentro del array 
     * @throws java.util.NoSuchElementException Si el elemento no existe en la lista 
     */  
    public int getIndexOf(String searched) throws java.util.NoSuchElementException {  
        int pos = 0;  
          
        // Comprobamos que el argumento sea válido  
        if (searched == null){  
            throw new IllegalArgumentException();  
        }  
          
        // Recorremos la información hasta encontrar el elemento  
        for (String d : elements){  
            if (d.equals(searched)){  
                return pos;  
            }  
            pos++;  
        }  
          
        // El elemento no existia, lanzamos la excepción  
        throw new java.util.NoSuchElementException(searched);     
    }  
}  