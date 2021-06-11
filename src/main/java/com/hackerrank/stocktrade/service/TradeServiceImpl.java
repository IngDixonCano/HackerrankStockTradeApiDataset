package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.dto.TradeDto;
import com.hackerrank.stocktrade.dto.UserDto;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  @Override
  public Trade addTrade(Trade trade) {
    Trade responseTrade = new Trade();

    UserDto userDto = new UserDto();
    userDto.setId(trade.getUser().getId());
    userDto.setName(trade.getUser().getName());

    TradeDto tradeDto = new TradeDto();
    tradeDto.setId(trade.getId());
    tradeDto.setType(trade.getType());
    tradeDto.setSymbol(trade.getSymbol());
    tradeDto.setShares(trade.getShares());
    tradeDto.setPrice(trade.getPrice());
    tradeDto.setTimestamp(trade.getTimestamp());

    TradeDto tradeObj = tradeRepository.findById(tradeDto.getId()).orElse(null);
    if (tradeObj != null) {
      return null;
    }
    TradeDto savedTradeDto = tradeRepository.save(tradeDto);
    mapTradeDtoToTrade(savedTradeDto, responseTrade, new User());
    return responseTrade;
  }

  @Override
  public Trade getTrade(Long tradeId) {
    Trade responseTrade = null;
    TradeDto tradeDto = tradeRepository.findById(tradeId).orElse(null);
    if (tradeDto != null) {
      responseTrade = new Trade();
      User user = new User();
      mapTradeDtoToTrade(tradeDto, responseTrade, user);
    }
    return responseTrade;
  }

  @Override
  public List<Trade> getAllTrades() {
    List<TradeDto> tradeDtoList = tradeRepository.findAll(Sort.by("id"));
    return createResponse(tradeDtoList);
  }

  @Override
  public List<Trade> getTradesByUserId(Long userId) {
    List<TradeDto> tradeDtoList = tradeRepository.findByUserId(userId, Sort.by("id"));
    return createResponse(tradeDtoList);
  }

  @Override
  public List<Trade> getTradesByStockSymbolAndTradeTypeAndDatesBetween(String stockSymbol, String tradeType,
      Timestamp startDate, Timestamp endDate) {

    List<TradeDto> tradeDtoList = tradeRepository.findBySymbolAndTypeAndTimestampBetween(stockSymbol, tradeType,
        startDate, endDate, Sort.by("id"));
    return createResponse(tradeDtoList);
  }

  private List<Trade> createResponse(List<TradeDto> tradeDtoList) {
    final List<Trade> trades = new ArrayList<>();
    if (!CollectionUtils.isEmpty(tradeDtoList)) {
      tradeDtoList.forEach(tradeDto -> {
        final Trade trade = new Trade();
        final User user = new User();
        mapTradeDtoToTrade(tradeDto, trade, user);
        trades.add(trade);
      });
    }
    return trades;
  }

  private void mapTradeDtoToTrade(TradeDto tradeDto, Trade trade, User user) {
    user.setId(tradeDto.getUser().getId());
    user.setName(tradeDto.getUser().getName());

    trade.setId(tradeDto.getId());
    trade.setPrice(tradeDto.getPrice());
    trade.setUser(user);
    trade.setShares(tradeDto.getShares());
    trade.setSymbol(tradeDto.getSymbol());
    trade.setType(tradeDto.getType());
    trade.setTimestamp(tradeDto.getTimestamp());

  }
}