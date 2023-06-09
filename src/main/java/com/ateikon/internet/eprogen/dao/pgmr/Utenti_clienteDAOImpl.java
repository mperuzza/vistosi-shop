package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Utenti_clienteExample;
import com.ateikon.internet.eprogen.domain.pgmr.Utenti_clienteKey;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Utenti_clienteDAOImpl extends SqlMapClientDaoSupport implements Utenti_clienteDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public Utenti_clienteDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public int countByExample(Utenti_clienteExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_utenti_cliente.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public int deleteByExample(Utenti_clienteExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_utenti_cliente.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public int deleteByPrimaryKey(Utenti_clienteKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_utenti_cliente.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public void insert(Utenti_clienteKey record) {
        getSqlMapClientTemplate().insert("pgmr_utenti_cliente.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public void insertSelective(Utenti_clienteKey record) {
        getSqlMapClientTemplate().insert("pgmr_utenti_cliente.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    @SuppressWarnings("unchecked")
    public List<Utenti_clienteKey> selectByExample(Utenti_clienteExample example) {
        List<Utenti_clienteKey> list = getSqlMapClientTemplate().queryForList("pgmr_utenti_cliente.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public int updateByExampleSelective(Utenti_clienteKey record, Utenti_clienteExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_utenti_cliente.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    public int updateByExample(Utenti_clienteKey record, Utenti_clienteExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_utenti_cliente.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.utenti_cliente
     *
     * @ibatorgenerated Wed May 27 11:42:59 CEST 2009
     */
    private static class UpdateByExampleParms extends Utenti_clienteExample {
        private Object record;

        public UpdateByExampleParms(Object record, Utenti_clienteExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}