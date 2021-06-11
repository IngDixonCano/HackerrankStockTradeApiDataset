package com.hackerrank.stocktrade.dto;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode
@Data
@Builder
@Entity
@Table(name = "Trade")
public class TradeDto {
  @Id
  private Long id;
  @Column
  private String type;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")

  private UserDto user;
  @Column
  private String symbol;
  @Column
  private Integer shares;
  @Column
  private Float price;
  @Column(updatable = false)
  private Timestamp timestamp;

  public TradeDto() {
  }

  public TradeDto(Long id, String type, UserDto user, String symbol, Integer quantity, Float price,
      Timestamp timestamp) {
    this.id = id;
    this.type = type;
    this.user = user;
    this.symbol = symbol;
    this.shares = quantity;
    this.price = price;
    this.timestamp = timestamp;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public UserDto getUser() {
    return this.user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public String getSymbol() {
    return this.symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Integer getShares() {
    return this.shares;
  }

  public void setShares(Integer shares) {
    this.shares = shares;
  }

  public Float getPrice() {
    return this.price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Timestamp getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}