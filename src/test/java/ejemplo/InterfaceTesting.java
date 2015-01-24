package ejemplo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import interfaces.MyInterface;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class InterfaceTesting {
    public MyInterface myInterface;

    public InterfaceTesting(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    @Test
    public final void testMyMethod_True() {
        assertTrue(myInterface.myMethod(true));
    }

    @Test
    public final void testMyMethod_False() {
        assertFalse(myInterface.myMethod(false));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                    new Object[]{new MyClass1()},
                    new Object[]{new MyClass1()}
        );
    }
}
