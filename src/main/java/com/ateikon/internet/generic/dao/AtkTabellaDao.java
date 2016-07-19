package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.AtkTabella;

public interface AtkTabellaDao {

  List getItems(Map pars) throws DataAccessException;
  
  AtkTabella getItem(String cdtabella, String dsoggetto) throws DataAccessException;
  
  void insertItem(AtkTabella item) throws DataAccessException;
  
  int updateItem(AtkTabella item) throws DataAccessException;

  int deleteItem(String cdtabella, String dsoggetto) throws DataAccessException;    

}
