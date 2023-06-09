package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.eprogen.domain.pgmr.Utenti;
import com.ateikon.internet.eprogen.domain.pgmr.UtentiExample;
import java.util.List;

public class UtentiDAOImpl extends SqlMapBaseDao implements UtentiDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public UtentiDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public int countByExample(UtentiExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_utenti.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public int deleteByExample(UtentiExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_utenti.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public int deleteByPrimaryKey(Long tkutente) {
        Utenti key = new Utenti();
        key.setTkutente(tkutente);
        int rows = getSqlMapClientTemplate().delete("pgmr_utenti.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void insert(Utenti record) {
        getSqlMapClientTemplate().insert("pgmr_utenti.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void insertSelective(Utenti record) {
        getSqlMapClientTemplate().insert("pgmr_utenti.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    @SuppressWarnings("unchecked")
    public List<Utenti> selectByExample(UtentiExample example) {
        List<Utenti> list = getSqlMapClientTemplate().queryForList("pgmr_utenti.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public Utenti selectByPrimaryKey(Long tkutente) {
        Utenti key = new Utenti();
        key.setTkutente(tkutente);
        Utenti record = (Utenti) getSqlMapClientTemplate().queryForObject("pgmr_utenti.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public int updateByExampleSelective(Utenti record, UtentiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_utenti.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public int updateByExample(Utenti record, UtentiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_utenti.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public int updateByPrimaryKeySelective(Utenti record) {
        int rows = getSqlMapClientTemplate().update("pgmr_utenti.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public int updateByPrimaryKey(Utenti record) {
        int rows = getSqlMapClientTemplate().update("pgmr_utenti.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.utenti
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private static class UpdateByExampleParms extends UtentiExample {
        private Object record;

        public UpdateByExampleParms(Object record, UtentiExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}