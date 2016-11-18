package sys.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 菜单资源
 *
 * @author Konghao
 */
@Table(name = "t_menu_res")
@Entity
public class MenuResources implements SystemResources, Comparable<MenuResources> {
    public static final String RES_TYPE = "menu";
    private int id;
    private String name;
    private String sn;
    private int menuPos;
    private String href;
    private String icon;
    private int orderNum;
    private String psn;
    private int display;
    private MenuResources parent;


    public MenuResources(int id, String name, String sn, int menuPos,
                         String href, String icon, int orderNum, String psn, int display) {
        super();
        this.id = id;
        this.name = name;
        this.sn = sn;
        this.menuPos = menuPos;
        this.href = href;
        this.icon = icon;
        this.orderNum = orderNum;
        this.psn = psn;
        this.display = display;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 菜单的名称，中文名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 菜单的sn，不能重复，将来要通过这个sn自动生成页面的超链接，然后为超链接增加一个属性auth_sn，
     * 值就是sn
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
     * 菜单所在的位置
     *
     * @return
     */
    @Column(name = "menu_pos")
    public int getMenuPos() {
        return menuPos;
    }

    public void setMenuPos(int menuPos) {
        this.menuPos = menuPos;
    }

    /**
     * 菜单的超链接
     *
     * @return
     */
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 菜单的图标
     *
     * @return
     */
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 是否显示菜单，1表示显示，-1表示不显示
     *
     * @return
     */
    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    /**
     * 菜单的排序号
     *
     * @return
     */
    @Column(name = "order_num")
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 菜单的父类sn，方便初始化的时候做操作
     *
     * @return
     */
    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    /**
     * 菜单的父类菜单，在授权的时候比较方便
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "pid")
    public MenuResources getParent() {
        return parent;
    }

    public void setParent(MenuResources parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(MenuResources o) {
        return (this.orderNum > o.orderNum) ? 1 : ((this.orderNum == o.orderNum) ? 0 : -1);
    }

    public MenuResources() {
    }

}
