package ejemplo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
public class ListasTest {

	@Test
	public void test() {	
		//creacion de mock  
		List mockedList = mock(List.class);  
		  
		//utilizando el mock object  
		mockedList.add("one");  
		mockedList.clear();  
		  
		//verificacion  
		verify(mockedList).add("one");  
		verify(mockedList).clear();
	}

}
