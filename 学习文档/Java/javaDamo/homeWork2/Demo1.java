package homeWork2;

public class Demo1 {
    public static void main(String[] args){
        Student student = new Student();
//        子类可以继承父类的所有方法
        student.setAge(20);
//        子类可以继承父类的所有属性
        System.out.println("我也是:"+student.getAge()+"岁");
//        如果父类中有private关键字私有化的属性，只属于父类，子类不能继承。
//        如果想继承父类的私有化属性，可以让父类提供pulic的get、set方法。
    }
}

//学生(派生类; Person的子类)继承了人的所有关系
//public class Student extends Person{
//
//}



