package com.hackerrank.stocktrade.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode
@Data
@Builder
@Entity
@Table(name = "User")
public class UserDto {

  @Id
  private Long id;
  @Column
  private String name;

  public UserDto() {
  }

  public UserDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}