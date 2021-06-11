package com.hackerrank.stocktrade.repository;

import java.sql.Timestamp;
import java.util.List;

import com.hackerrank.stocktrade.dto.TradeDto;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeDto, Long> {

  List<TradeDto> findByUserId(Long userId, Sort sort);

  List<TradeDto> findBySymbolAndTypeAndTimestampBetween(String symbol, String type, Timestamp startDate,
      Timestamp endDate, Sort sort);

  List<TradeDto> findBySymbolAndPriceLessThanAndTimestampBetween(String symbol, Float price, Timestamp startDate,
      Timestamp endDate, Sort sort);

}
