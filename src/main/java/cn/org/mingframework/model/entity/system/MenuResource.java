package cn.org.mingframework.model.entity.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "Menu")
public class MenuResource extends Resource implements Serializable {
	private MenuResource parent;
	private List<MenuResource> children = new ArrayList<MenuResource>();
	
	@ManyToOne
	@JoinColumn(name = "parentid")
	public MenuResource getParent() {
		return parent;
	}
	
	public void setParent(MenuResource parent) {
		this.parent = parent;
	}
	
	@OneToMany//(mappedBy = "parent")
	@JoinColumn(name = "parentid")
	public List<MenuResource> getChildren() {
		return children;
	}
	
	public void setChildren(List<MenuResource> children) {
		this.children = children;
	}
	
	
}
