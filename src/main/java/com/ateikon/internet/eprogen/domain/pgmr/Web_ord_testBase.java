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
public class Web_ord_testBase extends BaseTableBean{

    public Web_ord_testBase() {
        this.web_ord_test_note = new Web_ord_test_note();
    }




    private Web_ord_test_note web_ord_test_note;

    public Web_ord_test_note getWeb_ord_test_note() {
        return web_ord_test_note;
    }

    public void setWeb_ord_test_note(Web_ord_test_note web_ord_test_note) {
        this.web_ord_test_note = web_ord_test_note;
    }



}
