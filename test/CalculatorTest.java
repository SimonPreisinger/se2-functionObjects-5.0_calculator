import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class CalculatorTest {

    Calculator SUT = new Calculator();

    @Test (timeout = 1_000)
    public void plus(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(7);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","2","+"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void minus(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(3);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","2","-"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void divide(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(3);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"6","2","/"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void multiply(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(10);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","2","*"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void modulo(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(1);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"29","4","mod"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void max(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(5);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","2","max"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void invertPositive(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(-5);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","+/-"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void invertNegative(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(5);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"-5","+/-"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void swap(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(2);
        want.push(5);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","2","swap"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void duplicate(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(5);
        want.push(5);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","dup"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void store(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(5);

        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","store"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void recall(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(5);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5","store"});
        SUT.run(new Stack<>(), new String[]{"recall"});
        //assert
        Assert.assertEquals(want,have);
    }

    @Test (timeout = 1_000)
    public void pow(){
        //arrange
        SUT = new Calculator();
        Stack<Integer> want = new Stack<>();
        // act
        want.push(25);
        Stack<Integer> have = SUT.run(new Stack<>(), new String[]{"5", "2", "pow"});
        //assert
        Assert.assertEquals(want,have);
    }

}