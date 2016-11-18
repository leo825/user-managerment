package sys.auth.dao;

import sys.auth.model.Role;
import sys.basic.idao.IBaseDao;

import java.util.List;


public interface IRoleDao extends IBaseDao<Role> {
    public List<Role> listRole();

    public void deleteRoleUsers(int rid);
}
