package project3;

import java.util.HashMap;
import java.util.Scanner;

public class test2 {
    /*
    HashMap实现
     */
    public static class HashMapTest {
        HashMap<String, String> info = new HashMap<>();
        Scanner input = new Scanner(System.in);
        public static void main(String[] args) {
        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.setInfo();
        hashMapTest.enterName();
        }

        public void setInfo(){

            while (true) {
                System.out.println("请输入员工姓名：“xxx”，输入“exit”为停止输入：");
                String name = input.next();
                //输入结束，退出登记簿编辑
                if ("exit".equals(name)) {
                    break;
                }
                System.out.println("请输入员工信息（包括年龄、入职时间和员工状态）：");
                String property = input.next();
                info.put(name, property);
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
            return info.get(name);
        }
    }

}
