package ruqi.test.fengzhiquan.project3;

public class OrArrayList {

    private int index;
    private String[] orList = new String[3];

    public String get(int num) {
        return orList[num];
    }

    public void add(String content){
        this.orList[index] = content;
        this.AutoScaling();
        this.index++;
    }

    public int len() {
        return index;
    }

    public void AutoScaling(){
        if (this.index >= this.orList.length-1){
            String[] AutoList = new String[orList.length*2];
            for (int i=0; i <= this.index; i++) {
                AutoList[i] = this.orList[i];
            }
        this.orList = AutoList;
        }
    }
}

