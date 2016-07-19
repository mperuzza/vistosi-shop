package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.SysRemoteUser;

public interface SysRemoteUserDao {

  List getItems(Map pars) throws DataAccessException;
  
  SysRemoteUser getItem(Integer id) throws DataAccessException;
  
  void insertItem(SysRemoteUser item) throws DataAccessException;
  
  int updateItem(SysRemoteUser item) throws DataAccessException;

  int deleteItem(Integer id) throws DataAccessException;    

}
