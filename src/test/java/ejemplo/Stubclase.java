package ejemplo;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class Stubclase {

	@Test
	public void test() {
		//se pueden hacer mock de clases concretas, no solo interfaces  
		LinkedList mockedList = mock(LinkedList.class);  
		  
		//stubbing  
		when(mockedList.get(0)).thenReturn("first");  //cuando es get(0) que returne firts
		when(mockedList.get(1)).thenThrow(new RuntimeException());  
		
		//stubbing usando anyInt() argument matcher  
		when(mockedList.get(anyInt())).thenReturn("element");  
		
		//stubbing usando hamcrest (libreria de matchers) (digamos que isValid() devuelve tu propio matcher):  
		//when(mockedList.contains(argThat(isValid()))).thenReturn("element"); //
		
		//imprime "first"  
		System.out.println(mockedList.get(0));  
		  
		//lanza runtime exception  
		//System.out.println(mockedList.get(1));  
		  
		//imprime "null" porque no se ha hecho stubbing de get(999)  
		//System.out.println(mockedList.get(999));  
		
		//imprime "element"  
		//System.out.println(mockedList.get(999)); 
		
		verify(mockedList).get(0); // este s equivalente al assert
		   
		//tambien se puede verificar usando argument matchers  
		verify(mockedList).get(anyInt());
        
		
		//usando mock   
		mockedList.add("once");  
		  
		mockedList.add("twice");  
		mockedList.add("twice");  
		  
		mockedList.add("three times");  
		mockedList.add("three times");  
		mockedList.add("three times");  
		//las dos verificaciones siguientes trabajan de la misma manera (times(1) se usa por defecto)  
		verify(mockedList).add("once");  
		verify(mockedList, times(1)).add("once");  
		  
		//verificacion de numero exacto de invaciones  
		verify(mockedList, times(2)).add("twice");  
		verify(mockedList, times(3)).add("three times");  
		  
		//verificacion utilizando never. never() es un alias de times(0)  
		verify(mockedList, never()).add("never happened");  
		  
		//verificacion utilizando atLeast()/atMost()  
		verify(mockedList, atLeastOnce()).add("three times");  
		//verify(mockedList, atLeast(2)).add("five times");  
		verify(mockedList, atMost(5)).add("three times");
		
		
		// excption*/
		doThrow(new RuntimeException()).when(mockedList).clear();  
	     
		//la siguiente llamada lanza RuntimeException:  
		//mockedList.clear();
		
		//Verificaciones en orden********************
		List firstMock = mock(List.class);  
		List secondMock = mock(List.class);  
		   
		//usando mocks  
		firstMock.add("was called first");  
		secondMock.add("was called second");  
		   
		//creamos un objeto inOrder, pasando los mocks que necesitan verificarse en orden  
		InOrder inOrder = Mockito.inOrder(firstMock, secondMock);  
		   
		//verficamos que firstMock ha sido invocado antes que secondMock  
		inOrder.verify(firstMock).add("was called first");  
		inOrder.verify(secondMock).add("was called second");
		
	}

}
