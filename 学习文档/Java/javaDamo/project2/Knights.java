package project2;



public abstract class Knights {
    public abstract void elemental_kill();

    public abstract void element_burst();


    private String name;
    private int age;
    private char sex;

    public Knights(String name,int age,char sex){
        this.name=name;
        this.age=age;
        this.sex=sex;

    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public char getSex(){
        return this.sex;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public void setSex(char sex){
        this.sex=sex;
    }

    public void printInfo(){
        System.out.println("名叫"+ this.getName()+"，芳龄"+this.getAge()+"，性别"+this.getSex());
    }
}
