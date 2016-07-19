/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emiliano
 */
public class Wishlist {

    private Wishlist_test test;

    private List<Wishlist_posi> posis;

    public List<Wishlist_posi> getPosis() {
        return posis;
    }

    public void setPosis(List<Wishlist_posi> posis) {
        this.posis = posis;
    }

    public Wishlist_test getTest() {
        return test;
    }

    public void setTest(Wishlist_test test) {
        this.test = test;
    }

    public Wishlist() {

        test = new Wishlist_test();
        posis = new ArrayList<Wishlist_posi>();
    }



}
