package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Listino_cliente;
import com.ateikon.internet.eprogen.domain.pgmr.Listino_clienteExample;
import com.ateikon.internet.eprogen.domain.pgmr.Listino_clienteKey;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Listino_clienteDAOImpl extends SqlMapClientDaoSupport implements Listino_clienteDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Listino_clienteDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int countByExample(Listino_clienteExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_listino_cliente.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByExample(Listino_clienteExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_listino_cliente.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByPrimaryKey(Listino_clienteKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_listino_cliente.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insert(Listino_cliente record) {
        getSqlMapClientTemplate().insert("pgmr_listino_cliente.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insertSelective(Listino_cliente record) {
        getSqlMapClientTemplate().insert("pgmr_listino_cliente.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    @SuppressWarnings("unchecked")
    public List<Listino_cliente> selectByExample(Listino_clienteExample example) {
        List<Listino_cliente> list = getSqlMapClientTemplate().queryForList("pgmr_listino_cliente.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Listino_cliente selectByPrimaryKey(Listino_clienteKey key) {
        Listino_cliente record = (Listino_cliente) getSqlMapClientTemplate().queryForObject("pgmr_listino_cliente.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExampleSelective(Listino_cliente record, Listino_clienteExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_listino_cliente.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExample(Listino_cliente record, Listino_clienteExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_listino_cliente.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByPrimaryKeySelective(Listino_cliente record) {
        int rows = getSqlMapClientTemplate().update("pgmr_listino_cliente.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByPrimaryKey(Listino_cliente record) {
        int rows = getSqlMapClientTemplate().update("pgmr_listino_cliente.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table pgmr.listino_cliente
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private static class UpdateByExampleParms extends Listino_clienteExample {
        private Object record;

        public UpdateByExampleParms(Object record, Listino_clienteExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}