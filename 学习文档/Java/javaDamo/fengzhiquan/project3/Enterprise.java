package ruqi.test.fengzhiquan.project3;


public class Enterprise {

    private String name;
    private String createTime;
    private String legalPerson;

    public Enterprise(String name, String createTime, String legalPerson) {
        this.name = name;
        this.createTime = createTime;
        this.legalPerson = legalPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
}
