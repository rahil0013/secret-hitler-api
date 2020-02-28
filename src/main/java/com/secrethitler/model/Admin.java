package com.secrethitler.model;

import com.secrethitler.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admins", uniqueConstraints = {
@UniqueConstraint(columnNames = {
"username"
}),
@UniqueConstraint(columnNames = {
"email"
})
})
public class Admin extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 40)
  private String name;

  @NotBlank
  @Size(max = 15)
  private String username;

  @NaturalId
  @NotBlank
  @Size(max = 40)
  @Email
  private String email;

  @NotBlank
  @Size(max = 100)
  private String password;

  @Column(name = "role_id")
  private Long roleId;

  @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Game> games = new ArrayList<>();

  public Admin() {
  }

  public Admin(Long id) {
    this.id = id;
  }

  public Admin(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public List<Game> getGames() {
    return games;
  }

  public void setGames(List<Game> games) {
    this.games = games;
  }
}