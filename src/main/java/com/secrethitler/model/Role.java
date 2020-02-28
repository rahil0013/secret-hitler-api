package com.secrethitler.model;

import com.secrethitler.model.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 60)
  private RoleType type;

  public Role() {

  }

  public Role(RoleType type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RoleType getType() {
    return type;
  }

  public void setType(RoleType type) {
    this.type = type;
  }
}