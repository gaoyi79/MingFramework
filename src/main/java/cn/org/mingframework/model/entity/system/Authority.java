package cn.org.mingframework.model.entity.system;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Authority implements Serializable {
	private Long id;
	private String name;
	private int mask;
}
