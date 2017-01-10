package cn.org.mingframework.model.entity.system.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "SYS_DB_Entity")
public class EntityDescription implements Serializable {
	private Long id;
	private String entityName;
	private String className;
	private String entityDescription;
	private List<FieldDescription> fieldDescription = new ArrayList<FieldDescription>();

	private int version;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 40)
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Column(length = 150)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(length = 100)
	public String getEntityDescription() {
		return entityDescription;
	}

	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
	}

	@OneToMany(mappedBy = "entity")
	public List<FieldDescription> getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(List<FieldDescription> fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
