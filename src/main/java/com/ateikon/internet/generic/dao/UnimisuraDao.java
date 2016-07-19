package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Unimisura;

public interface UnimisuraDao {

  List getItems() throws DataAccessException;
  
  Unimisura getItem(String cd) throws DataAccessException;
  
  void insertItem(Unimisura item) throws DataAccessException;
  
  int updateItem(Unimisura item) throws DataAccessException;

  int deleteItem(String cd) throws DataAccessException;    

}
