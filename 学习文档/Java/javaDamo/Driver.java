public class Driver {
    public static void main(String[] args){
        String name = "志权";
        int age = 18;
        int driverYears = 3;
        System.out.println("我是如祺专车司机" + name + ",今年"+ age + "," + "驾龄" + driverYears + "年。");

        if (driverYears>5){
            System.out.println("司机年龄满足要求,年龄："+driverYears);
            }
        else {
            System.out.println("司机年龄不满足要求,年龄："+driverYears);
        }
    }
}