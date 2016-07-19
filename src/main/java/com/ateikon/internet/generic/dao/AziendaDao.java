package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Azienda;

public interface AziendaDao {

  List getItems(Map pars) throws DataAccessException;
  
  Azienda getItem(String cd) throws DataAccessException;
  
  void insertItem(Azienda item) throws DataAccessException;
  
  int updateItem(Azienda item) throws DataAccessException;

  int deleteItem(String cd) throws DataAccessException;    

}
