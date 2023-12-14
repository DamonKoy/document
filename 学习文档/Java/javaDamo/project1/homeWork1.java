/*
1、计算分数评级
键盘录入一个学生成绩（用int表示），自动输出该同学的成绩评级。
学生的分数取值范围[0,100]
分数大于等于90的，评级"优秀"；
分数大于等于80，小于90的，评级"良好"；
分数大于等于70，小于80的，评级"中等"；
分数大于等于60，小于70的，评级"及格"；
分数小于60的，评级"不及格"；

2、计算2+4+6+…+1000的值。

3、制作一个加减乘除计算器。
通过键盘输入数值、运算符。键盘输入“=”打印出计算结果。输出计算结果后，通过键盘输入决定进入下一轮计算或者退出计算器
*/


package project1;

import java.util.Scanner;

public class homeWork1 {
    public static void main(String[] args){
        scoreCal();
        sumCal();
        calculator();
    }


    public static void scoreCal() {


        Scanner input = new Scanner(System.in);
        System.out.println("请输入你的分数:");
        int num = input.nextInt();
        while (num>100 || num<0){
            System.out.println("请输入你的分数,0-100的整数:");
            num = input.nextInt();
        }
        if(num>=90){
            System.out.println("分数:"+ num +",优秀");
        }else if(num>=80){
            System.out.println("分数:"+ num +",良好");
        }else if(num>=70){
            System.out.println("分数:"+ num +",中等");
        }else if(num>=60){
            System.out.println("分数:"+ num +",及格");
        }else{
            System.out.println("分数:"+ num +",不及格");
        }
    }

    public static void sumCal(){
        int i = 1;
        int sum = 0;
        for (i=2; i<=1000; i=i+2){
            sum = sum + i;
        }
        System.out.println("2+4+6+…+1000的值为"+sum);
    }



    public static void calculator() {
        Scanner input = new Scanner(System.in);
        double result = 0;
        String operator = "";
        boolean continueCalculation = true;

        while (continueCalculation) {
            System.out.println("请输入一个数值:");
            double num = input.nextDouble();
            if (operator.equals("")) {
                result = num;
            } else {
                switch (operator) {
                    case "+":
                        result += num;
                        break;
                    case "-":
                        result -= num;
                        break;
                    case "*":
                        result *= num;
                        break;
                    case "/":
                        if (num != 0) {
                            result /= num;
                        } else {
                            System.out.println("除数不能为0");
                        }
                        break;
                }
            }

            System.out.println("请输入一个运算符(+ - * / =):");
            operator = input.next();

            while (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/") && !operator.equals("=")) {
                System.out.println("无效的运算符，请重新输入(+ - * / =):");
                operator = input.next();
            }

            if (operator.equals("=")) {
                System.out.println("结果为: " + result);
                System.out.println("输入任何非'exit'字符进行下一轮计算，或者输入'exit'退出计算器:");
                String next = input.next();
                if (next.equals("exit")) {
                    continueCalculation = false;
                } else {
                    operator = "";
                }
            }
        }
    }

}


