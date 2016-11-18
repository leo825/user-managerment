package sys.auth.service;

import sys.auth.model.MenuResources;
import sys.dto.LeftMenuDto;
import sys.dto.TreeDto;

import java.util.List;


public interface IMenuResService {

    public void add(MenuResources mr);

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

    public void initMenuResources(String[] packages);

}
