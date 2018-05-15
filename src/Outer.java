public class Outer {
    static class Nested{
        public String param = "outer String";
        private void NestedMethod(){
            Outer.StaticOuterMethod();
        }
    }

    class Inner{
        public String param = "inner String";
        private void InnerMethod(){System.out.println("Inner called");
        Outer.this.OuterMehtod(this);}

    }
    private void OuterMehtod (Inner inner){
    }
    static void StaticOuterMethod(){
        System.out.println("staticOuterMethod");
    }


    public static void main(String... args){
        Outer outer = new Outer();
        Outer.Nested nested = new Outer.Nested();
        Outer.Inner inner = outer.new Inner();
        inner.InnerMethod();
        nested.NestedMethod();


    }



}
