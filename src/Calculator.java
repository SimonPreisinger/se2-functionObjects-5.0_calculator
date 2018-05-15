/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 3183 MHz, 16000 MByte RAM)
 **/
import java.util.Stack;

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
    public Stack<Integer> run(Stack<Integer> numbers, String... commands) {
        for(String arg: commands)
            switch(arg) {
                case "+":
                    int sum = numbers.pop();
                    sum += numbers.pop();
                    numbers.push(sum);
                    break;
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
                default:
                    numbers.push(Integer.parseInt(arg));
                    break;
            }
        return numbers;
    }

    public static void main(String... args) {
        System.out.println(new Calculator().run(new Stack<>(), args));
    }

}
