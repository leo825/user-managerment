package sys.dto;

public class PersonDto {
    private int pid;
    private String name;
    private String sfzh;
    private int sex;
    private String phone;
    private String posName;
    private int posId;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public PersonDto() {
    }

    public PersonDto(int pid, String name, String sfzh, int sex,
                     String phone, String posName, int posId) {
        super();
        this.pid = pid;
        this.name = name;
        this.sfzh = sfzh;
        this.sex = sex;
        this.phone = phone;
        this.posName = posName;
        this.posId = posId;
    }


}
