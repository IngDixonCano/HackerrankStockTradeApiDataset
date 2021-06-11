package com.hackerrank.stocktrade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {

  @Autowired
  private TradeService tradeService;

  @GetMapping("/{stockSymbol}/price?start={startDate}&end={endDate}")
  public ResponseEntity getStockByStockSymbolAndPrice(@PathVariable String stockSymbol, @PathVariable String tradeType,
      @PathVariable Timestamp startDate, @PathVariable Timestamp endDate) {

    // The item 7 is bad on his definition.
    List<Trade> trades = null;

    if (!CollectionUtils.isEmpty(trades)) {
      return ResponseEntity.ok().body(trades);
    } else {
      return new ResponseEntity<>("There are no trades in the given date range.", HttpStatus.NOT_FOUND);
    }
  }
    
}
