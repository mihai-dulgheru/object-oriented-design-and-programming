package ro.ase.seminar7;

public class TestSingleton {
    public static void main(String[] args) {
        MySingleton singleton1 = MySingleton.getInstance();
        singleton1.doSomething();

        MySingleton singleton2 = MySingleton.getInstance();
        singleton2.doSomething();
    }
}
