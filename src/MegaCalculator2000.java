/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 3183 MHz, 16000 MByte RAM)
 **/

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.*;

/** Commandline calculator.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-05-15
 */
public class MegaCalculator2000 {

    /** Interprets commands operating on the number stack.
     * @param numbers Number stack.
     * @param commands Commands.
     * @return Same as numbers.
     * @throws IllegalArgumentException if a command is unknown.
     * @throws IllegalStateException if the stack underruns.
     * @throws IllegalStateException if redo is the first command.
     * @throws IllegalStateException if undo is the first command.
     */


    Consumer<String> consumer = x -> System.out.println(x);


    Consumer<String> methodref = MegaCalculator2000::print;


    private static void print(String s) {
        System.out.println(s);
    }


    // unused -> System.out.println(42)
    // $ -> System.out.println(42) // $ == unused
    // key null oder impossible String = Zahl (push)

    Function<Stack<Integer>, Consumer<String>> swapper = MegaCalculator2000::swap;
    BiFunction<Double, Double, Double> power = (v, v1) -> Math.pow(v1, v);

    private static Consumer<String> swap(Stack<Integer> numbers) {
        return (x) -> {
            int fst = numbers.pop();
            int snd = numbers.pop();
            numbers.push(fst);
            numbers.push(snd);
        };
    }


    BiFunction<Integer, Integer, Integer> divider = (i1, i12) -> {
        Integer result = i12 / i1;
        return result;
    };

    public Stack<Integer> run(Stack<Integer> numbers, String... commands) {




        final Map<String, Consumer<String>> map;

        map = new HashMap<>();
        map.put("+", x -> numbers.push(numbers.pop() + numbers.pop()));
        map.put("-", y -> numbers.push(-numbers.pop() + numbers.pop()));

        map.put("/", i -> numbers.push( divider.apply(numbers.pop(), numbers.pop())));
        // map.put("/", x -> {int a = numbers.pop(), b = numbers.pop(); numbers.push(b/a);});
        map.put("*", x -> numbers.push(numbers.pop() * numbers.pop()));
        map.put("mod", x -> {int a = numbers.pop(), b = numbers.pop(); numbers.push(b % a);});
        map.put("max", x -> numbers.push(Math.max( numbers.pop(), numbers.pop())));
        map.put("±", x -> numbers.push(numbers.pop() * (-1)));
        map.put("swap", swapper.apply(numbers));
        map.put("dub", x -> numbers.push(numbers.firstElement()));
        map.put("store", x -> map.put("saved", y -> numbers.firstElement()));
        map.put("recall", $ -> map.get("saved"));
        map.put("pow", x -> numbers.push( power.apply(numbers.pop().doubleValue(),numbers.pop().doubleValue()).intValue()));
        //map.put("pow", x -> numbers.push(
        //        ( this.pow.apply(numbers.pop().doubleValue(), numbers.pop().doubleValue())).intValue()));
        map.put("gcd", x -> numbers.push(numbers.pop()));
        // last command again .
        // {"2 2 2 pow .", "[16]"},
        // {"2 2 3 pow .", "[64]"},
        map.put(".", x -> numbers.push(numbers.pop()));
        // undo last command ^
        // {"2 3 + ^", "[2 3]"},
        map.put("^", x -> {int fst = numbers.pop(), snd = numbers.pop();
        });
        for(String arg: commands)
        {
            map.getOrDefault(arg, z -> {if(arg.equals(""))
            {
                System.out.println("invalid arg");
                throw new RuntimeException();

            }
            else
                numbers.push(Integer.parseInt(z));

            }).accept(arg);
            //map.getOrDefault(arg, z -> numbers.push(Integer.parseInt(z))).accept(arg);
        }
        return numbers;
    }


    public static void main(String... args) {
        System.out.println(new MegaCalculator2000().run(new Stack<>(), args));

    }

    void invalid(Supplier<Throwable> bombMaker) throws Throwable{
        throw bombMaker.get();
    }

}


