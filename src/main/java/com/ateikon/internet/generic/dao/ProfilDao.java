package com.ateikon.internet.generic.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ateikon.internet.generic.domain.Profil;

public interface ProfilDao {

  Profil getProfil() throws DataAccessException;
}
