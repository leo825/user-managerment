package sys.org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 组织机构类型对象，用来设定系统中存在哪些组织类型
 * 如：对于学校而言，可能存在：学校，分校，校长办，行政部门，教学部门，专业，班级
 *
 * @author KongHao
 */
@Entity
@Table(name = "t_org_type")
public class OrgType {
    /**
     * 类型的标识
     */
    private int id;
    /**
     * 类型的名称
     */
    private String name;
    /**
     * 类型的sn序号
     */
    private String sn;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty(message = "组织机构类型名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "组织机构标识不能为空")
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
