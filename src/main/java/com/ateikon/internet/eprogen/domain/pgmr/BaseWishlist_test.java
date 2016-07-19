/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

/**
 *
 * @author emiliano
 */
public class BaseWishlist_test extends BaseTableBean{

    private String ragsoc;

    public String getRagsoc() {
        return ragsoc;
    }

    public void setRagsoc(String ragsoc) {
        this.ragsoc = ragsoc;
    }

    

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



}
