package sys.org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 组织对象，该表可以生成完整的组织树
 * 根据组织类型来具体存储实际中存在的组织
 * 昭通学院-->XX
 * 校本部-->FX
 * 南校区-->FX
 *
 * @author KongHao
 */
@Entity
@Table(name = "t_org")
public class Org {
    /**
     * 组织机构的id
     */
    private int id;
    /**
     * 组织机构的名称
     */
    private String name;
    /**
     * 组织机构所属类型的id，此处不要使用ManyToOne
     */
    private int typeId;
    /**
     * 组织机构所属类型的名称，冗余
     */
    private String typeName;
    /**
     * 组织机构的排序号
     */
    private int orderNum;
    /**
     * 组织机构的父亲组织
     */
    private Org parent;
    /**
     * 管理类型
     */
    private int managerType;
    /**
     * 组织机构的地址
     */
    private String address;
    /**
     * 组织机构的电话
     */
    private String phone;
    /**
     * 扩展属性1，用于在针对某些特殊的组织存储相应的信息
     */
    private String att1;
    /**
     * 扩展属性2，用于在针对某些特殊的组织存储相应的信息
     */
    private String att2;
    /**
     * 扩展属性3，用于在针对某些特殊的组织存储相应的信息
     */
    private String att3;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty(message = "组织机构的名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tid")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Column(name = "tname")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(name = "order_num")
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "manager_type")
    public int getManagerType() {
        return managerType;
    }

    public void setManagerType(int managerType) {
        this.managerType = managerType;
    }

    @ManyToOne
    @JoinColumn(name = "pid")
    public Org getParent() {
        return parent;
    }

    public void setParent(Org parent) {
        this.parent = parent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAtt1() {
        return att1;
    }

    public void setAtt1(String att1) {
        this.att1 = att1;
    }

    public String getAtt2() {
        return att2;
    }

    public void setAtt2(String att2) {
        this.att2 = att2;
    }

    public String getAtt3() {
        return att3;
    }

    public void setAtt3(String att3) {
        this.att3 = att3;
    }

    @Override
    public boolean equals(Object obj) {
        Org o = (Org) obj;
        return this.id == o.getId();
    }

    public Org() {
    }

    public Org(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
