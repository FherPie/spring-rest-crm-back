package com.componente.config.security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.componente.config.security.emuns.RoleEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
@Entity(name="user_entity")
public class UserEntity {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	@Column(name="first_name",nullable=false)
    private String firstName;
	@Column(name="last_name",nullable=false)
    private String lastName;
	@Column(name="username",nullable=true)
    private String username;
	@Column(name="email",nullable=false, unique = true)
    private String email;
	@Column(name="password",nullable=false)
    private String password;
	@Column(name="authority",nullable=false)
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roles;
    
	public UserEntity(int id, String username, String email, String password, List<RoleEnum> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
    
    
    
    
    
}
