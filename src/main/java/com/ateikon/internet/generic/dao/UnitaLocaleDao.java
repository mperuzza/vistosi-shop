package com.ateikon.internet.generic.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.UnitaLocale;

public interface UnitaLocaleDao {

    UnitaLocale getUnitaLocale(String cdente, String cdusul) throws DataAccessException;

    List getUnitaLocali(String cdente) throws DataAccessException;
    
    void updateUnitaLocali(UnitaLocale uniloc) throws DataAccessException;  
    
    void insertItem(UnitaLocale item) throws DataAccessException;

    int updateItem(UnitaLocale item) throws DataAccessException;

    int deleteItem(UnitaLocale item) throws DataAccessException;    
    
}
