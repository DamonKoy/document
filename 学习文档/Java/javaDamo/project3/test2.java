package ruqi.test.fengzhiquan.project3;

import java.util.HashMap;
import java.util.Scanner;

public class test2 {
    /*
    HashMap实现
     */
    public static class HashMapTest {
        HashMap<String, SetProperty> info = new HashMap<>();
        Scanner input = new Scanner(System.in);

        public static void main(String[] args) {
        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.setInfo();
        hashMapTest.enterName();
        }

        public void setInfo(){

            while (true) {
                SetProperty setProperty = new SetProperty();
                System.out.println("请输入员工姓名：“xxx”，输入“exit”为停止输入：");
                String name = input.next();
                //输入结束，退出登记簿编辑
                if ("exit".equals(name)) {
                    break;
                }
                System.out.println("请输入员年龄：");
                String age = input.next();
                setProperty.setAge(Integer.parseInt(age));
                System.out.println("请输入员工入职时间：");
                String enterTime = input.next();
                setProperty.setEnterTime(enterTime);
                System.out.println("请输入员状态：");
                String status = input.next();
                setProperty.setStatus(status);
                info.put(name, setProperty);
                System.out.println(info);
            }
            //登记完成，把登记名称写入数据库
            System.out.println("登记完成，把登记名称存入数据库");
        }

        public void enterName(){
            System.out.println("请输入你要查找的员工姓名");
            String name = input.next();
            System.out.println("该员工信息如下："+getProperty(name));
        }

        /**
         * @param name 姓名
         * @return 属性
         */
        public String getProperty(String name) {
            return info.get(name).printInfo();
        }
    }

}
