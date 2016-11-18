package sys.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Controller资源的操作方法类，用来确定某个资源的操作所对应的方法
 *
 * @author Konghao
 */
@Entity
@Table(name = "t_controller_oper")
public class ControllerOper {
    private int id;
    private String sn;
    private String methodName;
    private String name;
    private int indexPos;
    /**
     * 资源id
     */
    private int rid;
    /*
     * 资源对象的sn
     */
    private String rsn;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 资源的标识，默认就通过ADD,DELETE,UPDATE,READ
     *
     * @return
     */
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 资源的方法，一个操作默认会对应多个方法add|addInput
     * 在初始化的时候，可以根据方法的名称来确定，如add开头就是ADD，其他没有声明的都是READ
     *
     * @return
     */
    @Column(name = "method_name")
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        if (this.methodName == null || "".equals(this.methodName)) {
            this.methodName = methodName;
        } else {
            if (this.methodName.indexOf(methodName) >= 0) {
                //原有的className已经包含了，就直接返回
                return;
            }
            this.methodName += "|" + methodName;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 方法的索引位置
     * 默认情况:0-->CREATE|ADD,1-->READ,2-->UPDATE,3-->DELETE其他，又开发人员手动指定
     *
     * @return
     */
    public int getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(int indexPos) {
        this.indexPos = indexPos;
    }

    /**
     * 所对应的资源id
     *
     * @return
     */
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRsn() {
        return rsn;
    }

    public void setRsn(String rsn) {
        this.rsn = rsn;
    }
}
