package ruqi.test.fengzhiquan.project2;

public class Youla extends Knights {
    public Youla(String name, int age, char sex) {
        super(name, age, sex);
    }

    @Override
    public void elemental_kill() {
        System.out.print("擅长冰元素，使用元素战技冰朝的涡旋，");
    }

    @Override
    public void element_burst() {
        System.out.println("并释放元素爆凝浪之光剑。");
    }
}
