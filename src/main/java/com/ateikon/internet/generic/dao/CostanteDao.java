package com.ateikon.internet.generic.dao;


import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Costante;
import java.util.Map;

public interface CostanteDao {

    Costante getCostante(String costname) throws DataAccessException;
    
    Costante getCostante(Map pars) throws DataAccessException;
    
    Costante getCostante(Map pars, boolean usePrefix) throws DataAccessException;

}
