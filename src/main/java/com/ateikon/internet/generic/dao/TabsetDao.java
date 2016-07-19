package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Tabset;

public interface TabsetDao {

  List getItems(Map pars) throws DataAccessException;
  
  Tabset getItem(String id) throws DataAccessException;
  
  void insertItem(Tabset item) throws DataAccessException;
  
  int updateItem(Tabset item) throws DataAccessException;

  int deleteItem(String id) throws DataAccessException;    

}
