/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Oracle Corporation Java 1.8.0_121, Linux i386 4.15.4
 * bluna: Intel Core i7-5600U/2600 MHz, 4 Core(s), 15936 MByte RAM
 */

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/** Testklasse fuer Taschenrechner.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-12
 *
 */
@RunWith(Parameterized.class) public class MegaCalculator2000Test {

    /** Generiert Testfaelle.
     * @return Testfaelle.
     * Jedes Arrays enthaelt zwei Elemente: 1. String mit Kommandozeilenargumenten,
     * 2. Erwartete Ausgabe oder null, wenn die Kommandozeile unzulaessig ist.
     */
    @Parameters public static Iterable<Object[]> generate() {
        return Arrays.asList(new Object[][] {
                // numbers
                { "1", "[1]" },                   // 0
                { "1 2", "[1, 2]" },              // 1
                // +
                { "1 2 +", "[3]" },               // 2
                // invalid
                { "", null },                     // 3
                { "invalid", null },              // 4
                { "+", null },                    // 5
                // Mende
                // -
                {"1 1 -", "[0]"},                 // 6
                {"1 2 -", "[-1]"},                // 7
                {"-2 2 -", "[-4]"},               // 8
                // /
                {"4 2 /", "[2]"},                // 9
                {"6 -3 /", "[-2]"},               // 10
                // *
                {"2 2 *", "[4]"},                 // 11
                // mod
                {"6 4 mod ", "[2]"},              // 12
                {"6 3 mod", "[0]"},               // 13
                // max
                {"4 2 max", "[4]"},               // 14
                // ±
                {"1 ±", "[-1]"},                  // 15
                // swap
                {"4 2 swap", "[2, 4]"},           // 16
                // dub
                {"3 dub", "[3, 3]"},              // 17
                // store and recall
                {"3 5 store", "[3]"},             // 18
                {"5 store recall", "[5]"},        // 19
                {"5 store 2 + recall", "[7, 5]"}, // 20
                // pro section:
                // pow
                {"2 10 pow", "[1024]"},           // 21
                // gcd
                {"16 12 gcd", "[4]"},             // 22
                {"9 16 gcd", "[1]"},              // 23
                // last command again .
                {"2 2 2 pow .", "[16]"},          // 24
                {"2 2 3 pow .", "[64]"},          // 25
                // undo last command ^
                {"2 3 + ^", "[2 3]"},             // 26

        });
    }

    /** String mit allen Kommandozeilenargumenten, getrennt mit Whitespace. */
    @Parameter(0) public String commandlineArgs;

    /** Erwartete Ausgabe. */
    @Parameter(1) public String wantOuput;

    /** Test einer zulaessigen Eingabe.
     * @throws IOException cannot happen :-)
     */
    @Test public void testValid() throws IOException {
        final PrintStream systemOut = System.out;
        if(wantOuput != null)
            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream fakeSystemOut = new PrintStream(outputStream)) {
                System.setOut(fakeSystemOut);
                MegaCalculator2000.main(commandlineArgs.split("\\s+"));
                fakeSystemOut.flush();
                assertEquals(wantOuput, new String(outputStream.toByteArray()).trim());
            } finally {
                System.setOut(systemOut);
            }
    }

    /** Test einer unzulaessigen Eingabe.
     * @throws IOException cannot happen :-)
     */
    @Test(expected = RuntimeException.class) public void testInvalid() throws IOException {
        final PrintStream systemOut = System.out;
        if(wantOuput == null)
            MegaCalculator2000.main(commandlineArgs.split("\\s+"));
        else
            throw new RuntimeException();
    }

}
