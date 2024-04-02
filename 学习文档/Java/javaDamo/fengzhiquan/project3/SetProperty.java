package ruqi.test.fengzhiquan.project3;


public class SetProperty {

    private int age;
    private String enterTime;
    private String status;

    public SetProperty(int age, String enterTime, String status) {
        this.age = age;
        this.enterTime = enterTime;
        this.status = status;
    }

    public SetProperty(){

    }

    public String printInfo(){
        return "年龄"+this.age+",入职时间为"+this.enterTime+","+this.status+"状态。";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
