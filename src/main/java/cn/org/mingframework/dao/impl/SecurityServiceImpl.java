package cn.org.mingframework.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.mingframework.dao.interfaces.SecurityService;
import cn.org.mingframework.dao.repository.RoleRepository;
import cn.org.mingframework.dao.repository.UserRepository;
import cn.org.mingframework.model.entity.system.Role;
import cn.org.mingframework.model.entity.system.User;
import cn.org.mingframework.model.entity.system.UserRole;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public void deleteUser(String userName) {
		deleteUser(userRepository.findByUserName(userName));
	}

	public User addRole(User user, Role role) {
		user.addRole(role);
		return updateUser(user);
	}

	public User addRoles(User user, Collection<Role> roles) {
		user.addRoles(roles);
		return updateUser(user);
	}

	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	public Role addPermission(Role role, String permission) {
		role.addPermission(permission);
		return roleRepository.save(role);
	}

	public Role addPermissions(Role role, Collection<String> permissions) {
		role.addPermissions(permissions);
		return roleRepository.save(role);
	}

	public Collection<String> getRolesString(User user) {
		Set<String> roles = new HashSet<String>();
		List<UserRole> userRoles = user.getUserRoles();
		for(UserRole userRole : userRoles){
			roles.add(userRole.getRole().getName());
		}
		return roles;
	}

	public Collection<String> getRolesString(String userName) {
		return getRolesString(userRepository.findByUserName(userName));
	}

	public Collection<Role> getRoles(User user) {
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> userRoles = user.getUserRoles();
		for(UserRole userRole : userRoles){
			roles.add(userRole.getRole());
		}
		return roles;
	}

	public Collection<Role> getRoles(String userName) {
		return getRoles(userRepository.findByUserName(userName));
	}

	public Collection<String> getPermissions(Role role) {
		List<String> permissions = new ArrayList<String>();
		return role.getPermissionList();
	}

	public Collection<String> getPermissions(User user) {
		Collection<Role> roles = getRoles(user);
		Set<String> permissions = new HashSet<String>();
		for(Role role : roles){
			permissions.addAll(getPermissions(role));
		}
		return permissions;
	}

	public Collection<String> getPermissions(String userName) {
		return getPermissions(userRepository.findByUserName(userName));
	}

}
