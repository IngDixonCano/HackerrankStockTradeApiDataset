package com.hackerrank.stocktrade.controller;

import java.sql.Timestamp;
import java.util.List;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

  @Autowired
  private TradeService tradeService;

  @PostMapping
  public ResponseEntity addTrade(@RequestBody Trade trade) {
    Trade savedTrade = tradeService.addTrade(trade);
    if (savedTrade != null) {
      return new ResponseEntity<>(savedTrade, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>("Unable to add trade.", HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity getTrade(@PathVariable Long id) {
    Trade trade = tradeService.getTrade(id);
    if (trade != null) {
      return ResponseEntity.ok().body(trade);
    } else {
      return new ResponseEntity<>("No Trade details found for given input.", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping()
  public ResponseEntity getAllTrades() {
    List<Trade> trades = tradeService.getAllTrades();
    return ResponseEntity.ok().body(trades);
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity getTradesByUserId(@PathVariable Long userId) {
    List<Trade> trades = tradeService.getTradesByUserId(userId);
    if (!CollectionUtils.isEmpty(trades)) {
      return ResponseEntity.ok().body(trades);
    } else {
      return new ResponseEntity<>("No Trade details found.", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/stocks/{stockSymbol}?type={tradeType}&start={startDate}&end={endDate}")
  public ResponseEntity getTradesByStockSymbolAndTradeTypeAndDatesBetween(@PathVariable String stockSymbol, @PathVariable String tradeType,
      @PathVariable Timestamp startDate, @PathVariable Timestamp endDate) {
    List<Trade> trades = tradeService.getTradesByStockSymbolAndTradeTypeAndDatesBetween(stockSymbol, tradeType, startDate, endDate);
    if (!CollectionUtils.isEmpty(trades)) {
      return ResponseEntity.ok().body(trades);
    } else {
      return new ResponseEntity<>("No Trade details found.", HttpStatus.NOT_FOUND);
    }
  }

}
