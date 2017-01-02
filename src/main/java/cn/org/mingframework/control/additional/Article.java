package cn.org.mingframework.control.additional;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article implements Serializable{
	private Long id;
	private String articleId;
	private String title;
	private int piccount;
	private String byUser;
	private int downloaded;
	private int grade;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="article_id",length=50)
	public String getArticleId() {
		return articleId;
	}
	
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	@Column(name="title", length=100)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="p_count")
	public int getPiccount() {
		return piccount;
	}
	
	public void setPiccount(int piccount) {
		this.piccount = piccount;
	}
	
	@Column(name="by_user", length=50)
	public String getByUser() {
		return byUser;
	}
	
	public void setByUser(String byUser) {
		this.byUser = byUser;
	}
	
	public int getDownloaded() {
		return downloaded;
	}
	
	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
