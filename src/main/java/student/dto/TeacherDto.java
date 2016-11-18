package student.dto;

public class TeacherDto {
    private int id;
    private String name;
    private String sfzh;
    private String zc;
    private String phone;
    private int sex;
    private String address;
    private int personId;
    private int posId;
    private String posName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public TeacherDto() {
    }

    public TeacherDto(int id, String name, String sfzh, String zc, String phone,
                      int sex, String address, int personId, int posId, String posName) {
        super();
        this.id = id;
        this.name = name;
        this.sfzh = sfzh;
        this.zc = zc;
        this.phone = phone;
        this.sex = sex;
        this.address = address;
        this.personId = personId;
        this.posId = posId;
        this.posName = posName;
    }
}
