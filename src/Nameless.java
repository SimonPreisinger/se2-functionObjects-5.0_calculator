public class Nameless extends AbstractClass implements SomeInterface {

    Nameless(){
        super("initialzeString");
    };




    SomeInterface makePrintable() {
        int i = 23;
        SomeInterface result = new SomeInterface() {
            @Override
            public void InterfaceMethod2() {
                System.out.println(i);
            }
        };

        return result;
    };

    SomeInterface someInterface = makePrintable();

    public static void main(String... args){




        new AbstractClass("hans") {
            @Override
            void AbstractClassMethod1() {

            }

            @Override
            void AbstractClassMethod2() {

            }
        };

        new SomeInterface(){
            @Override
            public void InterfaceMethod1() {

            }

            @Override
            public void InterfaceMethod2() {

            }
        };

    }

    @Override
    void AbstractClassMethod1() {

    }

    @Override
    void AbstractClassMethod2() {

    }

    @Override
    public void InterfaceMethod2() {

    }
}
