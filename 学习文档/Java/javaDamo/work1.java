import java.awt.im.spi.InputMethod;
import java.util.Scanner;

/*
1、计算分数评级
        键盘录入一个学生成绩（用int表示），自动输出该同学的成绩评级。
        学生的分数取值范围[0,100]
        分数大于等于90的，评级"优秀"；
        分数大于等于80，小于90的，评级"良好"；
        分数大于等于70，小于80的，评级"中等"；
        分数大于等于60，小于70的，评级"及格"；
        分数小于60的，评级"不及格"；
 */
public class work1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你的分数:");
        int num = input.nextInt();
        if(num>100 || num<0){
            System.out.println("请重新输入0-100的整数");
            num = input.nextInt();
        }else if(num>=90){
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
}


