package sys.org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 组织机构类型的规则表，用来确定组织之间的关系
 *
 * @author KongHao
 */
@Entity
@Table(name = "t_org_type_rule")
public class OrgTypeRule {
    /**
     * 规则标识
     */
    private int id;
    /**
     * 规则的父id
     */
    private int pid;
    /**
     * 规则的子id
     */
    private int cid;
    /**
     * 两者之间的数量，如果为-1表示可以有无限多个
     */
    private int num;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
