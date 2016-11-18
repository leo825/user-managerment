package sys.org.dao;

import sys.basic.idao.IBaseDao;
import sys.org.model.Position;
import java.util.List;

public interface IPositionDao extends IBaseDao<Position> {
	public List<Position> find();
	public Position loadBySn(String sn);
	/**
	 * 获取某个组织中存在的所有岗位列表
	 * @param orgId
	 * @return
	 */
	public List<Position> listByOrg(int orgId);
}
