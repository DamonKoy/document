package project3;

import java.util.ArrayList;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args){
        /*
        String[] worker = new String[3];
        int cursors =0;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("请输入员工姓名：“xxx”，输入“exit”为停止输入：");
            String name = input.next();
            //输入结束，退出登记簿编辑
            if("exit".equals(name)){
                break;
            }
            worker[cursors] = name;
            cursors++;
        }
        //登记完成，把登记名称写入数据库
        System.out.println("登记完成，把登记名称存入数据库");
        for(int i=0;i<cursors;i++){
            System.out.println("当天入职员工："+worker[i]);
        }
         */

        // 使用ArryList动态数组实现

        ArrayList<String> worker = new ArrayList<>(3);
        int cursors =0;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("请输入员工姓名：“xxx”，输入“exit”为停止输入：");
            String name = input.next();
            //输入结束，退出登记簿编辑
            if("exit".equals(name)){
                break;
            }
            worker.add(name);
            cursors++;
        }
        //登记完成，把登记名称写入数据库
        System.out.println("登记完成，把登记名称存入数据库");
        for(int i=0;i<cursors;i++){
            System.out.println("当天入职员工："+worker.get(i));
        }

        System.out.println("当天所有入职员工："+worker);
    }





}
