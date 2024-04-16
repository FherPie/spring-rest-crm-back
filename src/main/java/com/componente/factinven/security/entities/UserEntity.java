package com.componente.factinven.security.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.componente.factinven.security.constants.EntityName;
import com.componente.factinven.security.constants.TableName;
import com.componente.factinven.security.enums.AccountStatus;
import com.componente.factinven.security.enums.RoleAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = EntityName.USER)
@Table(name = TableName.USER, uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_username", columnNames = {"username"})
})
public class UserEntity extends IDedEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleAuthority role;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}
