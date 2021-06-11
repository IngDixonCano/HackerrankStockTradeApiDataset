package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.service.ResourcesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

  @Autowired
  private ResourcesService resourcesService;

  @DeleteMapping()
  public ResponseEntity<String> eraseAllTrades() {
    this.resourcesService.eraseAllTrades();
    return new ResponseEntity<>("All Trades erased!,", HttpStatus.OK);
  }
    
}
