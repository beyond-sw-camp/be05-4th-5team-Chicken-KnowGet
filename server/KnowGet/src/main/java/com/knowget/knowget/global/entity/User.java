package com.knowget.knowget.global.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_idx")
	private Long idx;

	@Column(name = "user_id", nullable = false, unique = true)
	private String id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "phone", nullable = false, unique = true)
	private String phone;

	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "company", nullable = false)
	private String company;

	@Column(name = "department", nullable = false)
	private String department;

	@Column(name = "delete_yn")
	@ColumnDefault("false")
	private Boolean deleteYn;

	@Column(name = "admin_yn")
	@ColumnDefault("false")
	private Boolean adminYn;

	@Builder
	public User(String id, String name, String password, String phone, String email, String company,
		String department) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.company = company;
		this.department = department;
		this.deleteYn = false;
		this.adminYn = false;
	}

	public void updateDeleteYn() {
		this.deleteYn = true;
	}

}
