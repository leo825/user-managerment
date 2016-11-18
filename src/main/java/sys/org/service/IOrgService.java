package sys.org.service;

import sys.basic.model.Pager;
import sys.dto.TreeDto;
import sys.org.model.Org;

import java.util.List;


public interface IOrgService {
    public void add(Org org);

    public void add(Org org, Integer pid);

    public void update(Org org);

    public void delete(int id);

    public Org load(int id);

    public Pager<Org> findByParent(Integer pid, Integer typeId);

    /**
     * 根据组织类型来生成这颗树，如果tid为null，就获取所有的组织
     *
     * @return
     */
    public List<TreeDto> tree();

    /**
     * 添加某个组织机构可以管理的子组织的id，这个子组织的id其实就是可以管理的所有子节点id
     *
     * @param orgId
     * @param cid
     */
    public void addRule(int orgId, int cid);

    /**
     * 添加一组子组织机构
     *
     * @param orgId
     * @param cids
     */
    public void addRule(int orgId, Integer[] cids);

    /**
     * 删除子组织
     *
     * @param orgId
     * @param cid
     */
    public void deleteRule(int orgId, int cid);

    /**
     * 获取某个组织下面的所在子组织id
     * 需要进行判断，如果组织类型是NO_TYPE-->return null
     * 如果组织类型是DEFAULT_TYPE-->返回该组织的所有子节点
     * 如果组织类型是ALL_TYPE-->返回所有组织
     * 如果组织类型是DEF_TYPE-->获取所有子组织下的子节点
     *
     * @param id
     * @return
     */
    public List<Integer> listAllChildIdsByOrg(int id);

    public List<Org> listAllChildByOrg(int id);

    /**
     * 获取某个子组织下的所有的组织，也需要根据类型来进行获取
     *
     * @param id
     * @return
     */
    public List<TreeDto> listAllChildTreeByOrg(int id);

    public List<Integer> listManagerRuleIds(int orgId);

    /**
     * 根据一个组织机构类型的编号获取父类的所有的组织机构节点，并且生成一棵树
     *
     * @param sn
     * @return
     */
    public List<TreeDto> listParentTreeByOrgType(String sn);
}
