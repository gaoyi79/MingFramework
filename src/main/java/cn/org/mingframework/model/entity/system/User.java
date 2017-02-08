package cn.org.mingframework.model.entity.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "SYS_User",
	uniqueConstraints = {@UniqueConstraint(columnNames = { "username"})})
public class User implements Serializable {
	private Long id;
	private String userName;
	private String password;
	private String salt;
	private int hashIterations;
	private Boolean locked = Boolean.FALSE;
	private List<UserGroup> loginGroups = new ArrayList<UserGroup>();
	private List<UserRole> userRoles = new ArrayList<UserRole>();
	private int version;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 20)
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(length = 150)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length = 100)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<UserGroup> getLoginGroups() {
		return loginGroups;
	}

	public void setLoginGroups(List<UserGroup> loginGroups) {
		this.loginGroups = loginGroups;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	@Version
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public void addRole(Role role){
		UserRole userRole = new UserRole(this, role);
		
		this.userRoles.add(userRole);
	}
	
	public void addRoles(Collection<Role> roles){
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for(Role role : roles){
			UserRole userRole = new UserRole(this, role);
			userRoles.add(userRole);
		}
		this.userRoles.addAll(userRoles);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + "]";
	}

	public String getCredentialsSalt(){
		return getSalt() + this.getUserName() + this.getSalt();
	}
}
