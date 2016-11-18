package sys.org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 岗位的对象，用来确定某个人员所属的岗位
 * 存储的就是岗位的名称
 * 副校长，校长，处长，副处长，科长，普通员工
 *
 * @author KongHao
 */
@Entity
@Table(name = "t_position")
public class Position {
    /**
     * 岗位的标识
     */
    private int id;
    /**
     * 岗位的名称
     */
    private String name;
    /**
     * 岗位的sn
     */
    private String sn;
    /**
     * 岗位的是否具备管理功能，目前意义不大
     */
    private int manager;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty(message = "岗位名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "岗位的标识不能为空")
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }
}
