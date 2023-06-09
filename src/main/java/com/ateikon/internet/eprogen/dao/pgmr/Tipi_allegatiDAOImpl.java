package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Tipi_allegati;
import com.ateikon.internet.eprogen.domain.pgmr.Tipi_allegatiExample;
import com.ateikon.internet.eprogen.domain.pgmr.Tipi_allegatiKey;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Tipi_allegatiDAOImpl extends SqlMapClientDaoSupport implements Tipi_allegatiDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Tipi_allegatiDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int countByExample(Tipi_allegatiExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_tipi_allegati.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByExample(Tipi_allegatiExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_tipi_allegati.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByPrimaryKey(Tipi_allegatiKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_tipi_allegati.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insert(Tipi_allegati record) {
        getSqlMapClientTemplate().insert("pgmr_tipi_allegati.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insertSelective(Tipi_allegati record) {
        getSqlMapClientTemplate().insert("pgmr_tipi_allegati.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    @SuppressWarnings("unchecked")
    public List<Tipi_allegati> selectByExample(Tipi_allegatiExample example) {
        List<Tipi_allegati> list = getSqlMapClientTemplate().queryForList("pgmr_tipi_allegati.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Tipi_allegati selectByPrimaryKey(Tipi_allegatiKey key) {
        Tipi_allegati record = (Tipi_allegati) getSqlMapClientTemplate().queryForObject("pgmr_tipi_allegati.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExampleSelective(Tipi_allegati record, Tipi_allegatiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_tipi_allegati.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExample(Tipi_allegati record, Tipi_allegatiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_tipi_allegati.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByPrimaryKeySelective(Tipi_allegati record) {
        int rows = getSqlMapClientTemplate().update("pgmr_tipi_allegati.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByPrimaryKey(Tipi_allegati record) {
        int rows = getSqlMapClientTemplate().update("pgmr_tipi_allegati.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table pgmr.tipi_allegati
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private static class UpdateByExampleParms extends Tipi_allegatiExample {
        private Object record;

        public UpdateByExampleParms(Object record, Tipi_allegatiExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}