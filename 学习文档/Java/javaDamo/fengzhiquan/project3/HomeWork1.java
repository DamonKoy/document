package ruqi.test.fengzhiquan.project3;

import java.util.HashMap;
import java.util.Scanner;

public class HomeWork1 {
    public static void main(String[] args){

        HashMap<String, Enterprise> enterpriseInfo = new HashMap<>();
        enterpriseInfo.put("奇异科技有限公司", new Enterprise("奇异科技有限公司", "2015-10-1", "奇异博士"));
        enterpriseInfo.put("顾凯股份有限公司", new Enterprise("顾凯股份有限公司", "2022-4-19", "凯爹"));
        enterpriseInfo.put("欢乐农场有限公司", new Enterprise("欢乐农场有限公司", "1993-5-6", "莹香"));

        Scanner input = new Scanner(System.in);
        System.out.println("请您输入公司名称，进行查找对应的法人信息");
        String inputEnterpriseName = input.next();
        try{
            System.out.println("名称为"+inputEnterpriseName+"的企业，法人是:"+enterpriseInfo.get(inputEnterpriseName).getLegalPerson());
        }catch (NullPointerException e) {
            System.out.println("查找不到对应的法人信息");
        }
    }
}
