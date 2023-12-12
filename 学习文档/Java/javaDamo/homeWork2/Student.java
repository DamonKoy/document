package homeWork2;

public class Student {
    public static void main(String[] args){
        Student s1 = new Student();
//        s1.name; 无法进行调用，变量被private私有了，如果换成public共有的关键字才可以被调用
        s1.setName("POP");
        s1.setAge(24);
        s1.setSex('男');
        System.out.println("名字："+s1.getName()+",今年"+s1.getAge()+",性别"+ s1.getSex());
    }

    private String name;
    private int age;
    private char sex;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        if (age>18){
            this.age=18;
        }else {
            this.age=age;
        }
    }

    public char getSex(){
        return this.sex;
    }

    public void setSex(char sex){
        this.sex=sex;
    }
}
