/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.logic;

import com.ateikon.internet.eprogen.dao.pgmr.Costanti_commDAO;

/**
 *
 * @author emiliano
 */
public class BaseManagerImpl implements BaseManager{


    private Costanti_commDAO costanti_commDAO;

    public void setCostanti_commDAO(Costanti_commDAO costanti_commDAO) {
        this.costanti_commDAO = costanti_commDAO;
    }




    public String getCostvalue(String costname) {
        return costanti_commDAO.getCostante(costname);
    }


}
