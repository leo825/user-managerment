package sys.org.dao;

import sys.basic.idao.IBaseDao;
import sys.dto.OrgTypeDto;
import sys.dto.OrgTypeRuleDto;
import sys.org.model.OrgType;

import java.util.List;


public interface IOrgTypeDao extends IBaseDao<OrgType> {
	/**
	 * 获取所有的组织类型
	 * @return
	 */
	public List<OrgType> list();
	
	public OrgType loadBySn(String sn);
	/**
	 * 添加组织类型规则
	 * @param pid
	 * @param cid
	 * @param num
	 */
	public void addOrgTypeRule(int pid, int cid, int num);
	/**
	 * 删除组织类型
	 * @param pid
	 * @param cid
	 */
	public void deleteOrgTypeRule(int pid, int cid);
	/**
	 * 更新组织类型规则，确切的说就是更新组织之间的关系的数量
	 * @param pid
	 * @param cid
	 * @param num
	 */
	public void updateOrgTypeRule(int pid, int cid, int num);
	/**
	 * 根据父亲id获取该组织的所有可以管理的子节点
	 * @param pid
	 * @return
	 */
	public List<OrgType> listByRule(int pid);
	/**
	 * 根据父节点获取子结点，子节点是个DTO对象，里面存储了父节点的名称、子节点的名称和数量
	 * @param pid
	 * @return
	 */
	public List<OrgTypeDto> listChildType(int pid);
	/**
	 * 根据组织或者该组织类型的所有的可以添加为子类型的节点
	 * @param id
	 * @return
	 */
	public List<OrgTypeRuleDto> listOrgTypeRuleByOrg(int id);
	
	/**
	 * 根据父id和子id获取两个之间的数量
	 * @param pid
	 * @param cid
	 * @return
	 */
	public int loadOrgRuleNum(int pid, int cid);
	
}
