/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 3183 MHz, 16000 MByte RAM)
 **/
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/** Commandline calculator.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-05-15
 */
public class Calculator {
    /** Interprets commands operating on the number stack.
     * @param numbers Number stack.
     * @param commands Commands.
     * @return Same as numbers.
     * @throws IllegalArgumentException if a command is unknown.
     * @throws IllegalStateException if the stack underruns.
     * @throws IllegalStateException if redo is the first command.
     * @throws IllegalStateException if undo is the first command.
     */

    // unused -> System.out.println(42)
    // $ -> System.out.println(42) // $ == unused
    // key null oder impossible String = Zahl (push)

        BiFunction<Integer, Integer, Integer> divide = Integer::divideUnsigned;

        BiFunction<Integer, Integer, Integer> modulo;




    public Stack<Integer> run(Stack<Integer> numbers, String... commands) {

        final Map<String, Consumer<String>> map;
        map = new HashMap<>();
        map.put("+", x -> numbers.push(numbers.pop() + numbers.pop()));
        map.put("-", y -> numbers.push(-numbers.pop() + numbers.pop()));
        map.put("/", x -> {int a = numbers.pop(), b = numbers.pop(); numbers.push(b/a);});
        map.put("*", x -> numbers.push(numbers.pop() * numbers.pop()));
        map.put("mod", x -> {int a = numbers.pop(), b = numbers.pop(); numbers.push(b % a);});
        map.put("max", x -> numbers.push(Math.max( numbers.pop(), numbers.pop())));
        map.put("+/-", x -> numbers.push(numbers.pop() * (-1)));
        map.put("swap", x -> { int old = numbers.pop(),older = numbers.pop(); numbers.push(old); numbers.push(older);
            });
        map.put("dup", x -> numbers.push(numbers.firstElement()));
        map.put("store", x -> map.put("saved", y -> numbers.pop()));
        map.put("recall", $ -> map.get("saved"));

        for(String arg: commands)
        {

            // tests schreiben
            map.getOrDefault(arg, z -> numbers.push(Integer.parseInt(z))).accept(arg);

                // "-" ... x y => ... (x-y)
                // "/" ... x y => ... (x/y)
                // "*" ... x y => ... (x*y)
                // "mod" ... x y => ... (x%y)
                // "max" ... x y => ... (larger of x and y)
                // "+/-" ... x => ... -x
                // "swap" ... x y => ... y x
                // "dup" ... x => ... x x
                // "store" ... x => ... (saves x)
                // "recall" ... => ... x (recalls stored x)
                // --- fuer Fortgeschrittene:-)
                // "pow" ... x y => ... (x hoch y)
                // "gcd" ... x y => ... (ggT von x und y)
                // "." letztes Kommando wiederholen
                // "^" letztes Kommando rueckgaengig machen

        }
        return numbers;
    }

    public static void main(String... args) {
        System.out.println(new Calculator().run(new Stack<>(), args));

    }

}
