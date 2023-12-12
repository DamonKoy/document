package homeWork2;

public class Derived extends Base1 {
    int a = 20;

    public void test() {
        // 调用子类Derived的属性a
        System.out.println(a);
        // 调用父类Base1的属性a
        System.out.println(super.a);
    }

    public void testA() {
        System.out.println("调用子类中的testA方法");
    }

    public void testB() {
        // 调用子类中的testA方法
        this.testA();
        // 调用父类中的testA方法
        super.testA();
    }

    public static void main(String[] args) {
        Derived dervied = new Derived();
        dervied.testB();
    }
}
