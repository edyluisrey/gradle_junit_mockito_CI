package ejemplo;

import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FibonacciTest {
    private int input;
    private int expected;
    private Fibonacci sut;

    public FibonacciTest(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {1, 1},
                {3, 2},
                {4, 3},
                {5, 5},
        });
    }

    @Before
    public void setUp() {
        this.sut = new Fibonacci();
    }

    @Test
    public void testFibonacci() {
        assertEquals(expected, sut.fibonacci(input));
    }
}