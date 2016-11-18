package sys.auth.dao;

import sys.auth.model.MenuResources;
import sys.basic.idao.IBaseDao;
import sys.dto.LeftMenuDto;
import sys.dto.TreeDto;

import java.util.List;

/**
 * 菜单资源对象的接口
 *
 * @author Konghao
 */
public interface IMenuResDao extends IBaseDao<MenuResources> {

    public void add(MenuResources mr, String psn);

    /**
     * 根据菜单的位置和父类Menu的sn获取所有的菜单资源对象
     *
     * @param psn
     * @param pos
     * @return
     */
    public List<MenuResources> listModelMenuByType(String psn, int pos);

    /**
     * 获取顶部的菜单资源对象
     *
     * @return
     */
    public List<MenuResources> listTopMenu();

    /**
     * 获取左边导航的菜单资源对象
     *
     * @return
     */
    public List<LeftMenuDto> listLeftNav();

    public List<TreeDto> tree();

    public List<MenuResources> listByParent(Integer pid);

    public MenuResources loadBySn(String sn);

}
