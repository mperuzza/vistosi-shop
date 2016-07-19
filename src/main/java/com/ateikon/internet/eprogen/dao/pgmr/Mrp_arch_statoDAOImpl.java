package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_statoExample;
import java.util.List;
import java.util.Map;

public class Mrp_arch_statoDAOImpl extends SqlMapBaseDao implements Mrp_arch_statoDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public Mrp_arch_statoDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public int countByExample(Mrp_arch_statoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_mrp_arch_stato.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public int deleteByExample(Mrp_arch_statoExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_mrp_arch_stato.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public int deleteByPrimaryKey(String cdstato) {
        Mrp_arch_stato key = new Mrp_arch_stato();
        key.setCdstato(cdstato);
        int rows = getSqlMapClientTemplate().delete("pgmr_mrp_arch_stato.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public void insert(Mrp_arch_stato record) {
        getSqlMapClientTemplate().insert("pgmr_mrp_arch_stato.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public void insertSelective(Mrp_arch_stato record) {
        getSqlMapClientTemplate().insert("pgmr_mrp_arch_stato.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    @SuppressWarnings("unchecked")
    public List<Mrp_arch_stato> selectByExample(Mrp_arch_statoExample example) {
        List<Mrp_arch_stato> list = getSqlMapClientTemplate().queryForList("pgmr_mrp_arch_stato.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public Mrp_arch_stato selectByPrimaryKey(String cdstato) {
        Mrp_arch_stato key = new Mrp_arch_stato();
        key.setCdstato(cdstato);
        Mrp_arch_stato record = (Mrp_arch_stato) getSqlMapClientTemplate().queryForObject("pgmr_mrp_arch_stato.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public int updateByExampleSelective(Mrp_arch_stato record, Mrp_arch_statoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_mrp_arch_stato.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public int updateByExample(Mrp_arch_stato record, Mrp_arch_statoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_mrp_arch_stato.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public int updateByPrimaryKeySelective(Mrp_arch_stato record) {
        int rows = getSqlMapClientTemplate().update("pgmr_mrp_arch_stato.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    public int updateByPrimaryKey(Mrp_arch_stato record) {
        int rows = getSqlMapClientTemplate().update("pgmr_mrp_arch_stato.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.mrp_arch_stato
     *
     * @ibatorgenerated Mon Jul 18 09:59:37 CEST 2011
     */
    private static class UpdateByExampleParms extends Mrp_arch_statoExample {
        private Object record;

        public UpdateByExampleParms(Object record, Mrp_arch_statoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    
    public List<Mrp_arch_stato> selectDistinctByPars(Map pars) {
        List<Mrp_arch_stato> list = getSqlMapClientTemplate().queryForList("pgmr_mrp_arch_stato.getStatiArtGroup", pars);
        return list;
    }    
}