package cn.org.mingframework.model.entity.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "SYS_Role",
	uniqueConstraints = {@UniqueConstraint(columnNames = { "name"})})
public class Role implements Serializable {
	private Long id;
	private String name;
	private List<UserRole> userRoles = new ArrayList<UserRole>();
	private List<GroupRole> groupRoles = new ArrayList<GroupRole>();
	private List<RolePermission> permissions = new ArrayList<RolePermission>();
	
	private int version;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@OneToMany(mappedBy = "role")
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(mappedBy = "role")
	public List<GroupRole> getGroupRoles() {
		return groupRoles;
	}

	public void setGroupRoles(List<GroupRole> groupRoles) {
		this.groupRoles = groupRoles;
	}

	@OneToMany(mappedBy = "role")
	public List<RolePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<RolePermission> permissions) {
		this.permissions = permissions;
	}
	
	//^^^^^^^^^^^Getter &  Setter^^^^^^^^^^^^^^
	
	public void addPermission(RolePermission permission){
		this.getPermissions().add(permission);
	}
	
	public void addPermission(String permission){
		RolePermission rolePermission = new RolePermission(this, permission);
		this.getPermissions().add(rolePermission);
	}
	
	public void addPermissions(Collection<String> permissions){
		for(String permission : permissions){
			addPermission(permission);
		}
	}
	
	public Collection<String> getPermissionList(){
		List<String> permissionList = new ArrayList<String>();
		for(RolePermission permission : this.getPermissions()){
			permissionList.add(permission.getPermission());
		}
		return permissionList;
	}
}
