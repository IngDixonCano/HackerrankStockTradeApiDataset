package com.hackerrank.stocktrade.service;

import java.sql.Timestamp;
import java.util.List;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeService {

  Trade addTrade(Trade trade);

  Trade getTrade(Long tradeId);

  List<Trade> getAllTrades();

  List<Trade> getTradesByUserId(Long userId);

  List<Trade> getTradesByStockSymbolAndTradeTypeAndDatesBetween(String stockSymbol, String tradeType,
      Timestamp startDate, Timestamp endDate);
}