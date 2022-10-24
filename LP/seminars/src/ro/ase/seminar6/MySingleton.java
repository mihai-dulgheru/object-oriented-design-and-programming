package ro.ase.seminar6;

public class MySingleton {
    private static MySingleton instance;

    private MySingleton() {
        System.out.println("Instance created!");
    }

    public static MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("I am doing something here...");
    }
}
