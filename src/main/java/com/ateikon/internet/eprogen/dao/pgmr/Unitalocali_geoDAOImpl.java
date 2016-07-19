package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.eprogen.domain.pgmr.Unitalocali_geo;
import com.ateikon.internet.eprogen.domain.pgmr.Unitalocali_geoExample;
import java.util.List;

public class Unitalocali_geoDAOImpl extends SqlMapBaseDao implements Unitalocali_geoDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public Unitalocali_geoDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public int countByExample(Unitalocali_geoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_unitalocali_geo.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public int deleteByExample(Unitalocali_geoExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_unitalocali_geo.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public int deleteByPrimaryKey(String cdunil) {
        Unitalocali_geo key = new Unitalocali_geo();
        key.setCdunil(cdunil);
        int rows = getSqlMapClientTemplate().delete("pgmr_unitalocali_geo.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public void insert(Unitalocali_geo record) {
        getSqlMapClientTemplate().insert("pgmr_unitalocali_geo.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public void insertSelective(Unitalocali_geo record) {
        getSqlMapClientTemplate().insert("pgmr_unitalocali_geo.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    @SuppressWarnings("unchecked")
    public List<Unitalocali_geo> selectByExample(Unitalocali_geoExample example) {
        List<Unitalocali_geo> list = getSqlMapClientTemplate().queryForList("pgmr_unitalocali_geo.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public Unitalocali_geo selectByPrimaryKey(String cdunil) {
        Unitalocali_geo key = new Unitalocali_geo();
        key.setCdunil(cdunil);
        Unitalocali_geo record = (Unitalocali_geo) getSqlMapClientTemplate().queryForObject("pgmr_unitalocali_geo.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public int updateByExampleSelective(Unitalocali_geo record, Unitalocali_geoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_unitalocali_geo.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public int updateByExample(Unitalocali_geo record, Unitalocali_geoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_unitalocali_geo.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public int updateByPrimaryKeySelective(Unitalocali_geo record) {
        int rows = getSqlMapClientTemplate().update("pgmr_unitalocali_geo.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    public int updateByPrimaryKey(Unitalocali_geo record) {
        int rows = getSqlMapClientTemplate().update("pgmr_unitalocali_geo.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.unitalocali_geo
     *
     * @ibatorgenerated Thu Sep 30 15:50:54 CEST 2010
     */
    private static class UpdateByExampleParms extends Unitalocali_geoExample {
        private Object record;

        public UpdateByExampleParms(Object record, Unitalocali_geoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}