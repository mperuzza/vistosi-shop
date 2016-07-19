package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Ente;

public interface EnteDao {

  List getItems(Map pars) throws DataAccessException;
  
  Ente getItem(Map pars) throws DataAccessException;

}
