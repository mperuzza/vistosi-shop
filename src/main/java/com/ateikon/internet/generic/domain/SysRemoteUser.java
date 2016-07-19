/*
 * SysRemoteUser.java
 *
 * Created on 25 maggio 2005, 16.07
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.ateikon.internet.generic.domain;

import java.util.Date;

/**
 *
 * @author Emiliano
 */
public class SysRemoteUser extends BaseTableBean{
    
    /** Creates a new instance of SysRemoteUser */
    public SysRemoteUser() {
    }

    /**
     * Holds value of property user_id.
     */
    private Integer user_id;

    /**
     * Getter for property user_id.
     * @return Value of property user_id.
     */
    public Integer getUser_id() {

        return this.user_id;
    }

    /**
     * Setter for property user_id.
     * @param user_id New value of property user_id.
     */
    public void setUser_id(Integer user_id) {

        this.user_id = user_id;
    }

    /**
     * Holds value of property address.
     */
    private String address;

    /**
     * Getter for property address.
     * @return Value of property address.
     */
    public String getAddress() {

        return this.address;
    }

    /**
     * Setter for property address.
     * @param address New value of property address.
     */
    public void setAddress(String address) {

        this.address = address;
    }

    /**
     * Holds value of property time_received.
     */
    private Date time_received;

    /**
     * Holds value of property dsaddress.
     */
    private String dsaddress;

    /**
     * Getter for property time_received.
     * @return Value of property time_received.
     */
    public Date getTime_received() {

        return this.time_received;
    }

    /**
     * Setter for property time_received.
     * @param time_received New value of property time_received.
     */
    public void setTime_received(Date time_received) {

        this.time_received = time_received;
    }

    /**
     * Getter for property dsaddress.
     * @return Value of property dsaddress.
     */
    public String getDsaddress() {

        return this.dsaddress;
    }

    /**
     * Setter for property dsaddress.
     * @param dsaddress New value of property dsaddress.
     */
    public void setDsaddress(String dsaddress) {

        this.dsaddress = dsaddress;
    }
    
}
