package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.eprogen.domain.pgmr.Tipo_finitura;
import com.ateikon.internet.eprogen.domain.pgmr.Tipo_finituraExample;
import com.ateikon.internet.eprogen.domain.pgmr.Tipo_finituraKey;
import java.util.List;

public class Tipo_finituraDAOImpl extends SqlMapBaseDao implements Tipo_finituraDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Tipo_finituraDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int countByExample(Tipo_finituraExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_tipo_finitura.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByExample(Tipo_finituraExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_tipo_finitura.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByPrimaryKey(Tipo_finituraKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_tipo_finitura.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insert(Tipo_finitura record) {
        getSqlMapClientTemplate().insert("pgmr_tipo_finitura.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insertSelective(Tipo_finitura record) {
        getSqlMapClientTemplate().insert("pgmr_tipo_finitura.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    @SuppressWarnings("unchecked")
    public List<Tipo_finitura> selectByExample(Tipo_finituraExample example) {
        List<Tipo_finitura> list = getSqlMapClientTemplate().queryForList("pgmr_tipo_finitura.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Tipo_finitura selectByPrimaryKey(Tipo_finituraKey key) {
        Tipo_finitura record = (Tipo_finitura) getSqlMapClientTemplate().queryForObject("pgmr_tipo_finitura.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExampleSelective(Tipo_finitura record, Tipo_finituraExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_tipo_finitura.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExample(Tipo_finitura record, Tipo_finituraExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_tipo_finitura.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByPrimaryKeySelective(Tipo_finitura record) {
        int rows = getSqlMapClientTemplate().update("pgmr_tipo_finitura.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByPrimaryKey(Tipo_finitura record) {
        int rows = getSqlMapClientTemplate().update("pgmr_tipo_finitura.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private static class UpdateByExampleParms extends Tipo_finituraExample {
        private Object record;

        public UpdateByExampleParms(Object record, Tipo_finituraExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}