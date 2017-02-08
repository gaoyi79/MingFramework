package cn.org.mingframework.dao.interfaces;

import java.util.Collection;

import cn.org.mingframework.model.entity.system.Role;
import cn.org.mingframework.model.entity.system.User;

public interface SecurityService {
	//User
	public User findByUserName(String userName);
	public User createUser(User user);
	public User updateUser(User user);
	public void deleteUser(User user);
	public void deleteUser(String userName);
	public User addRole(User user, Role role);
	public User addRoles(User user, Collection<Role> role);
	
	//Role
	public Role createRole(Role role);
	public Role updateRole(Role role);
	public Role addPermission(Role role, String permission);
	public Role addPermissions(Role role, Collection<String> permissions);
	public Collection<Role> getRoles(User user);
	public Collection<Role> getRoles(String userName);
	public Collection<String> getRolesString(User user);
	public Collection<String> getRolesString(String userName);
	
	//Permission
	public Collection<String> getPermissions(Role role);
	public Collection<String> getPermissions(User user);
	public Collection<String> getPermissions(String userName);
}
