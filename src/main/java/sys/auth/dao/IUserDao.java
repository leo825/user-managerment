package sys.auth.dao;

import java.util.List;

import sys.auth.model.Role;
import sys.auth.model.User;
import sys.auth.model.UserRole;
import sys.basic.idao.IBaseDao;
import sys.basic.model.Pager;

public interface IUserDao extends IBaseDao<User> {

    /**
     * 获取用户的所有角色信息
     *
     * @param userId
     * @return
     */
    public List<Role> listUserRoles(int userId);

    /**
     * 获取用户的所有角色的id
     *
     * @param userId
     * @return
     */
    public List<Integer> listUserRoleIds(int userId);

    /**
     * 获取用户的所有组的id
     *
     * @param userId
     * @return
     */
    public List<Integer> listUserGroupIds(int userId);

    /**
     * 根据用户和角色获取用户角色的关联对象
     *
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole loadUserRole(int userId, int roleId);

    /**
     * 根据用户名获取用户对象
     *
     * @param username
     * @return
     */
    public User loadByUsername(String username);

    /**
     * 根据角色id获取用户列表
     *
     * @param roleId
     * @return
     */
    public List<User> listRoleUsers(int roleId);

    /**
     * 获取某个组中的用户对象
     *
     * @param gid
     * @return
     */
    public List<User> listGroupUsers(int gid);

    /**
     * 添加用户角色对象
     *
     * @param user
     * @param role
     */
    public void addUserRole(User user, Role role);

    /**
     * 删除用户的角色信息
     *
     * @param uid
     */
    public void deleteUserRoles(int uid);

    /**
     * 删除用户的组信息
     *
     * @param gid
     */
    public void deleteUserGroups(int gid);

    public Pager<User> findUser();

    /**
     * 删除用户角色对象
     *
     * @param uid
     * @param rid
     */
    public void deleteUserRole(int uid, int rid);

    /**
     * 删除用户组对象
     *
     * @param uid
     * @param gid
     */
    public void deleteUserGroup(int uid, int gid);

}
