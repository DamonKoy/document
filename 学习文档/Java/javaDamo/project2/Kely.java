package project2;

public class Kely extends Knights {
    public Kely(String name, int age, char sex){
        super(name,age,sex);
    }

    @Override
    public void elemental_kill(){
        System.out.print("擅长火元素，使用元素战技蹦蹦炸弹，");
    }

    @Override
    public void element_burst() {
        System.out.println("释放元素爆发红红火花。");
    }
}
