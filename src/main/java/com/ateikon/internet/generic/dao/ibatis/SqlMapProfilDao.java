package com.ateikon.internet.generic.dao.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ateikon.internet.generic.domain.Profil;
import com.ateikon.internet.generic.dao.ProfilDao;

public class SqlMapProfilDao extends SqlMapClientDaoSupport implements ProfilDao {

    public Profil getProfil() throws DataAccessException {
        return (Profil)getSqlMapClientTemplate().queryForObject("getProfil", null);
    }    
   
}
