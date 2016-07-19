package com.ateikon.internet.generic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Nazione;

public interface NazioneDao {

  List getNazioni() throws DataAccessException;
  
  Nazione getNazione(String cdnazione) throws DataAccessException;
  
  void insertItem(Nazione item) throws DataAccessException;
  
  int updateItem(Nazione item) throws DataAccessException;

  int deleteItem(String cd) throws DataAccessException;    

}
