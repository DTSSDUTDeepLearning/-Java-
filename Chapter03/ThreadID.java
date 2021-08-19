package Chapter03;

public class ThreadID {

    public static void main(String[] args) {
        Thread t1 = new Thread();
        System.out.println("t1 Id is "+t1.getId());
        Thread t2 = new Thread();
        System.out.println("t1 Id is "+t2.getId());
    }
}
