package ruqi.test.fengzhiquan.project2;

public class Qin extends Knights {
    public Qin(String name, int age, char sex) {
        super(name, age, sex);
    }

    @Override
    public void elemental_kill() {
        System.out.print("擅长风元素，使用元素战技风压箭，");
    }

    @Override
    public void element_burst() {
        System.out.println("释放元素爆发蒲公英之风。");
    }
}
