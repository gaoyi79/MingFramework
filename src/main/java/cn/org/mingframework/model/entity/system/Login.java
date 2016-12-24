package cn.org.mingframework.model.entity.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Login implements Serializable {
	private Long id;
	private String userName;
	private String password;
	private Set<LoginGroup> loginGroups = new HashSet<LoginGroup>();
	private int verison;
	
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
	
	@Version
	public int getVerison() {
		return verison;
	}
	
	public void setVerison(int verison) {
		this.verison = verison;
	}

	@OneToMany(mappedBy = "login", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<LoginGroup> getGroups() {
		return loginGroups;
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
		Login other = (Login) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setGroups(Set<LoginGroup> loginGroups) {
		this.loginGroups = loginGroups;
	}
}
