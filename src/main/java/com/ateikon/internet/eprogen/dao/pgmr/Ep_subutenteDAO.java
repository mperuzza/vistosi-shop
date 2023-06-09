package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Ep_subutente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_subutenteExample;
import java.util.List;

public interface Ep_subutenteDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    int countByExample(Ep_subutenteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    int deleteByExample(Ep_subutenteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    int deleteByPrimaryKey(Long tksubutente);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    void insert(Ep_subutente record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    void insertSelective(Ep_subutente record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    List<Ep_subutente> selectByExample(Ep_subutenteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    Ep_subutente selectByPrimaryKey(Long tksubutente);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    int updateByExampleSelective(Ep_subutente record, Ep_subutenteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    int updateByExample(Ep_subutente record, Ep_subutenteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    int updateByPrimaryKeySelective(Ep_subutente record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_subutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    int updateByPrimaryKey(Ep_subutente record);
}