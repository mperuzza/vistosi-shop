/*
 * User.java
 *
 * Created on 03.08.2004
 */

package com.ateikon.internet.generic.domain;

import com.ateikon.internet.generic.domain.BaseTableBean;
import java.util.Date;

/**
 *
 * @author  Emiliano Armellin
 */
public class BaseUser extends BaseTableBean implements java.io.Serializable {

    /** Holds value of property cdutente. */
    private String cdutente;

    /** Holds value of property dsutente. */
    private String dsutente;

    private String username;

    private String password;
    
    /** Holds value of property livello. */
    private int livello;
    
    /**
     * Holds value of property email.
     */
    private String email;

    /**
     * Holds value of property cdgput.
     */
    private String cdgput;

    /**
     * Holds value of property cognome.
     */
    private String cognome;

    /**
     * Holds value of property nome.
     */
    private String nome;
    
    /** Creates new User */
    public BaseUser() {
    }

    /** Getter for property cdutente.
     * @return Value of property cdutente.
     */
    public String getCdutente() {
        return this.cdutente;
    }

    /** Setter for property cdutente.
     * @param cdutente New value of property cdutente.
     */
    public void setCdutente(String cdutente) {
        this.cdutente = cdutente;
    }

    /** Getter for property dsutente.
     * @return Value of property dsutente.
     */
    public String getDsutente() {
        return this.dsutente;
    }

    /** Setter for property dsutente.
     * @param dsutente New value of property dsutente.
     */
    public void setDsutente(String dsutente) {
        this.dsutente = dsutente;
    }
    /** Getter for property username.
     * @return Value of property username.
     */
    public String getUsername() {
        return this.username;
    }

    /** Setter for property username.
     * @param idutente New value of property username.
     */
    public void setUsername(String un) {
        this.username = un;
    }    

    /** Getter for property pwd.
     * @return Value of property pwd.
     */
    public String getPassword() {
        return this.password;
    }

    /** Setter for property pwd.
     * @param idutente New value of property pwd.
     */
    public void setPassword(String p) {
        this.password = p;
    }    

    /** Getter for property livello.
     * @return Value of property livello.
     *
     */
    public int getLivello() {
        return this.livello;
    }
    
    /** Setter for property livello.
     * @param livello New value of property livello.
     *
     */
    public void setLivello(int livello) {
        this.livello = livello;
    }
    
    /**
     * Getter for property email.
     * @return Value of property email.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Setter for property email.
     * @param email New value of property email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for property cdgput.
     * @return Value of property cdgput.
     */
    public String getCdgput() {

        return this.cdgput;
    }

    /**
     * Setter for property cdgput.
     * @param cdgput New value of property cdgput.
     */
    public void setCdgput(String cdgput) {

        this.cdgput = cdgput;
    }

    /**
     * Getter for property cognome.
     * @return Value of property cognome.
     */
    public String getCognome() {

        return this.cognome;
    }

    /**
     * Setter for property cognome.
     * @param cognome New value of property cognome.
     */
    public void setCognome(String cognome) {

        this.cognome = cognome;
    }

    /**
     * Getter for property nome.
     * @return Value of property nome.
     */
    public String getNome() {

        return this.nome;
    }

    /**
     * Setter for property nome.
     * @param nome New value of property nome.
     */
    public void setNome(String nome) {

        this.nome = nome;
    }

    
}
