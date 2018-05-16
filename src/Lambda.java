import java.util.function.*;

public class Lambda implements N,S {


    public void MethodForLambda(){
        System.out.println("MethodForLambda");
    }

    public static void main(String... args){
        //Standard
        Runnable r = () -> System.out.println("test"); // no extra method needed
        r.run();

        // liefert Wert
        Supplier<String> supplierLambda = () -> new String("sdf");
        System.out.println(supplierLambda.get());

        // liefert Boolean
        BooleanSupplier booleanSupplier = () -> {boolean bool = true;
        return bool;};
        System.out.println(booleanSupplier.getAsBoolean());

        // verarbeitet Wert
        int y = 3;
        Consumer<Integer> consumerLambda = (x) -> {System.out.println(x + x); };
        consumerLambda.accept(y);

        // verarbeitet 2 Werte
        int a = 10, b = 20;
        BiConsumer<Integer, Integer> biConsumerLambda = (c,d) -> System.out.println(a + b);
        biConsumerLambda.accept(a,b);

        // verarbeitet Wert und liefert Neuen Wert gleichen Types
        int f = 32;                                        //       (e + e);
        UnaryOperator<Integer> unaryOperatorLambda = (e) -> {return (e + e);};
        System.out.println(unaryOperatorLambda.apply(f));

        // verarbeitet Wert und liefert Neuen Wert anderen Types
        double g = 65.123d;
        Function<Double, Integer> functionLambda = (h) -> {return h.intValue();};
        System.out.println(functionLambda.apply(g));

        // kombiniert zwei Werte zu neuem Wert gleichen Types
        int i=10, j=30;
        BinaryOperator<Integer> binaryOperatorLambda = (k,l) -> {return k + l; };
        System.out.println(binaryOperatorLambda.apply(i,j));

        // kombiniert zwei Werte zu neuem Wert anderen Types
        double m = 3.414d;
        float n = 5.678f;
        BiFunction<Double, Float, Integer> biFunctionLambda = (o, p) -> {
            return o.intValue() + p.intValue(); };
        System.out.println(biFunctionLambda.apply(m,n));

        // klassifiziert einen Wert
        int u = 4;
        Predicate<Integer> predicateLambda = (z) -> {
            return (y > 3)? true:false; };
        System.out.println(predicateLambda.test(u));

        // klassifiziert eine Kombinatin Zweier Werte
        int ab = 12; double cd = 24.42d;
        BiPredicate<Integer, Double> biPredicateLambda = (ef,gh) -> {return (ab>cd)?true:false;};
        System.out.println(biPredicateLambda.test(ab,cd));

        Runnable action = () -> {System.out.println("action");};

        // Kurzschreibweise Lambda
        UnaryOperator<Integer> supplier = (Integer t) -> {return t + 1;};
                               supplier = (Integer t) -> t + 1;
                               supplier = (t) -> t + 1;
                               supplier = t -> t + 1;
                               System.out.println(supplier.apply(8));
        // Polytype
        N polyN = (au) ->  { // N == int Interface
            return au ;
        };
        S polyS = (au) -> { // S == string Interface
            return au;
        };

        polyN.method(3);
        // polyS.method(3); // not possible because string
        polyS.method("drei");

    }

    @Override
    public int method(int x) {
        return 0;
    }

    @Override
    public String method(String x) {
        return null;
    }
}
interface N { int method(int x); }
interface S { String method(String x); }

