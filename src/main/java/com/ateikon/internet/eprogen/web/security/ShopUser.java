/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.security;

import com.ateikon.internet.eprogen.domain.pgmr.Cliente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

/**
 *
 * @author emiliano
 */
public class ShopUser extends User{

    private Log log = LogFactory.getLog(this.getClass());

    private String[] americas = new String[]{"LUS", "LCA"};

    private Ep_utente userDB;

    public Ep_utente getUserDB() {
        return userDB;
    }

    public void setUserDB(Ep_utente userDB) {
        this.userDB = userDB;
    }

    private List<String> cdclas_aFilter; //filtro codice strategico determinato in base al cliente

    public List<String> getCdclas_aFilter() {
        return cdclas_aFilter;
    }

    public void setCdclas_aFilter(List<String> cdclas_aFilter) {
        this.cdclas_aFilter = cdclas_aFilter;
        
        //copia di backup del filtro base per le ricerche articoli dei clienti america
        this.cdclas_aFilterBase = new ArrayList<String>(cdclas_aFilter);
        
    }
    
    private List<String> cdclas_aFilterBase; //filtro per la ricerca articoli al primo step per gli utenti america

    public List<String> getCdclas_aFilterBase() {
        return cdclas_aFilterBase;
    }

    public void setCdclas_aFilterBase(List<String> cdclas_aFilterBase) {
        this.cdclas_aFilterBase = cdclas_aFilterBase;
    }
    
    


    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    private boolean realCustomer;

    public boolean isRealCustomer() {
        return realCustomer;
    }

    public void setRealCustomer(boolean realCustomer) {
        this.realCustomer = realCustomer;
    }
    
    



    public ShopUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities, Ep_utente ep_user) throws IllegalArgumentException {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setUserDB(ep_user);
        setIsSpecList(Boolean.FALSE);
    }

    public void addAuthority(GrantedAuthority newRole){

        GrantedAuthority[] autorities = this.getAuthorities();

        GrantedAuthority[] na = (GrantedAuthority[])ArrayUtils.add(autorities, newRole);

        //Set aut = AuthorityUtils.authorityArrayToSet(autorities);

        //aut.add(newRole);

//        for (Object object : aut) {
//
//        }
        for (int i = 0; i < na.length; i++) {
            GrantedAuthority grantedAuthority = na[i];
            log.debug(grantedAuthority.getAuthority());
        }
        //log.debug(aut.toArray(new GrantedAuthority[0]));
        //log.debug(aut.toArray(new GrantedAuthority[0]));
        //this.setAuthorities((GrantedAuthority[])aut.toArray(new GrantedAuthority[] {}));
//        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
//        authList.add(newRole);
//
//        for (GrantedAuthority grantedAuthority : authList) {
//
//        }


        this.setAuthorities(na);
    }

    private Boolean isSpecList;

    public Boolean getIsSpecList() {
        return isSpecList;
    }

    public void setIsSpecList(Boolean isSpecList) {
        this.isSpecList = isSpecList;
    }

    


}
