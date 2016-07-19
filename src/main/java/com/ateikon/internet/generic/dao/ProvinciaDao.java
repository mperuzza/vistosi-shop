package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Provincia;

public interface ProvinciaDao {

  List getProvincie() throws DataAccessException;
  
  Provincia getProvincia(String cdprov) throws DataAccessException;

}
