package project2;

public class Test {
    public static void main(String[] args){
        Kely K = new Kely("可莉", 16, '女');
        Qin Q = new Qin("琴", 18, '女');
        Youla Y = new Youla("优菈", 17, '女');

        K.printInfo();
        K.elemental_kill();
        K.element_burst();

        Q.printInfo();
        Q.elemental_kill();
        Q.element_burst();

        Y.printInfo();
        Y.elemental_kill();
        Y.element_burst();
    }
}
