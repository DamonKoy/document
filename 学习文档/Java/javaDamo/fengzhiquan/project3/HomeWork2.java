package ruqi.test.fengzhiquan.project3;

public class HomeWork2 {
    public static void main(String[] args){
        OrArrayList slist = new OrArrayList();
        slist.add("top1");
        slist.add("top2");
        slist.add("top3");
        System.out.println("当前元素数量="+slist.len());
        System.out.println("取index为"+2+"的元素得到:"+slist.get(2));
        slist.add("top4");
        slist.add("top5");
        System.out.println("当前元素数量="+slist.len());
        System.out.println("取index为"+4+"的元素得到:"+slist.get(4));
        slist.add("top6");
        slist.add("top7");
        slist.add("top8");
        System.out.println("当前元素数量="+slist.len());
        System.out.println("取index为"+7+"的元素得到:"+slist.get(7));
    }

}
