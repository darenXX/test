package com.etnetera.hr.data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * Edit /without lambok library for better readable and management
 * added new @Column
 * 
 * @author Etnetera
 *
 */
@Entity
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;


	@Column(nullable = false)
	@ElementCollection(targetClass=String.class)
	private List<String> version;

	@Column
	private Date deprecationDate;

	// Hope it just have rank 0-10 or 100 :) For defined use Enumeration
	@Column
	private Integer hypeLevel;

	public JavaScriptFramework() {
	}

	public JavaScriptFramework(String name) {
		this.name = name;
	}

	public JavaScriptFramework(Long id, String name, List<String> version, Date deprecationDate, Integer hypeLevel) {
		this.id = id;
		this.name = name;
		this.version = version;
		this.deprecationDate = deprecationDate;
		this.hypeLevel = hypeLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getVersion() {
		return version;
	}

	public void setVersion(List<String> version) {
		this.version = version;
	}

	public Date getDeprecationDate() {
		return deprecationDate;
	}

	public void setDeprecationDate(Date deprecationDate) {
		this.deprecationDate = deprecationDate;
	}

	public Integer getHypeLevel() {
		return hypeLevel;
	}

	public void setHypeLevel(Integer hypeLevel) {
		this.hypeLevel = hypeLevel;
	}

	@Override
	public String toString() {
		return "JavaScriptFramework [id=" + id + ", name=" + name + "]";
	}

}
