package student.model;

import sys.org.model.Org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 班级对象
 *
 * @author Konghao
 */
@Entity
@Table(name = "t_classroom")
public class Classroom {
    public static String ZZLX = "BJ";
    public static int MANAGER_TYPE = 0;

    private int id;
    private String name;
    private String grade;
    /**
     * 班级的状态:-1表示毕业，0表示在校
     */
    private int status;
    /**
     * 0表示正常班级
     * 1表示选修课班级
     */
    private int type;
    private int orderNum;
    /**
     * 组织机构中的父类的名称，冗余
     */
    private String pname;
    /**
     * 组织机构中父类的id，冗余
     */
    private int pid;
    private Org org;


    @Id
    @GeneratedValue
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "oid")
    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }


    @Column(name = "order_num")
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
