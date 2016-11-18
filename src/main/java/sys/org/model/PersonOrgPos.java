package sys.org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人员组织岗位对应关系表，这张表里面存储了人员和组织和岗位的对应关系
 *
 * @author KongHao
 */
@Entity
@Table(name = "t_person_org_pos")
public class PersonOrgPos {
    /**
     * 关系标识
     */
    private int id;
    /**
     * 人员id，这里也可以存储实体类，但是不太建议
     */
    private int personId;
    /**
     * 组织的id
     */
    private int orgId;
    /**
     * 岗位的id
     */
    private int posId;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "person_id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Column(name = "org_id")
    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    @Column(name = "pos_id")
    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }
}
