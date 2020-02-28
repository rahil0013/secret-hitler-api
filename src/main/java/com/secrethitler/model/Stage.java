package com.secrethitler.model;

import com.secrethitler.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stages")
public class Stage extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 40)
  private String stageName;

  @NotBlank
  @Size(max = 40)
  private String stageType;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "policy_id", nullable = false)
  private Policy policy;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "player_id", nullable = false)
  private Player player;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStageName() {
    return stageName;
  }

  public void setStageName(String stageName) {
    this.stageName = stageName;
  }

  public String getStageType() {
    return stageType;
  }

  public void setStageType(String stageType) {
    this.stageType = stageType;
  }

  public Policy getPolicy() {
    return policy;
  }

  public void setPolicy(Policy policy) {
    this.policy = policy;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
