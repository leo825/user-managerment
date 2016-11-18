package sys.auth.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;
import sys.auth.dao.IRoleDao;
import sys.auth.model.Role;
import sys.basic.dao.BaseDao;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public List<Role> listRole() {
		return this.list("from Role");
	}

	@Override
	public void deleteRoleUsers(int rid) {
		this.updateByHql("delete UserRole ur where ur.role.id=?",rid);
	}


}
