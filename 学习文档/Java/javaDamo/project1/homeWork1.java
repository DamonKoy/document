package ruqi.test.fengzhiquan.project1;

import java.util.Scanner;

public class homeWork1 {
    public static void main(String[] args){
        scoreCal();
        sumCal();
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
}

