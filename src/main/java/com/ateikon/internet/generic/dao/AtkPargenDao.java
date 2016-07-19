package com.ateikon.internet.generic.dao;


import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.AtkPargen;
import java.util.Map;

public interface AtkPargenDao {

  AtkPargen getAtkPargen(String parametro) throws DataAccessException;
  
  AtkPargen getAtkPargen(Map pars) throws DataAccessException;
}
