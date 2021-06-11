package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl implements ResourcesService {

  @Autowired
  private TradeRepository tradeRepository;

  @Override
  public void eraseAllTrades() {
    tradeRepository.deleteAll();
  }
}