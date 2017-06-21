/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.security;

import com.ateikon.internet.eprogen.dao.pgmr.ArchagenDAO;
import com.ateikon.internet.eprogen.dao.pgmr.ArchclieDAO;
import com.ateikon.internet.eprogen.dao.pgmr.ArchentiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_subutenteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_utenteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_filtro_articoliDAO;
import com.ateikon.internet.eprogen.domain.pgmr.Archagen;
import com.ateikon.internet.eprogen.domain.pgmr.Archclie;
import com.ateikon.internet.eprogen.domain.pgmr.Cliente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_subutente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utenteExample;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_filtro_articoli;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.security.util.AuthorityUtils;

/**
 *
 * @author emiliano
 */
public class ShopUserDetailsService implements UserDetailsService {

    private Log log = LogFactory.getLog(this.getClass());
    private Ep_utenteDAO ep_utenteDAO;

    public void setEp_utenteDAO(Ep_utenteDAO ep_utenteDAO) {
        this.ep_utenteDAO = ep_utenteDAO;
    }
    private Vist_filtro_articoliDAO vist_filtro_articoliDAO;

    public void setVist_filtro_articoliDAO(Vist_filtro_articoliDAO vist_filtro_articoliDAO) {
        this.vist_filtro_articoliDAO = vist_filtro_articoliDAO;
    }
    private ArchclieDAO archclieDAO;

    public void setArchclieDAO(ArchclieDAO archclieDAO) {
        this.archclieDAO = archclieDAO;
    }
    private ArchentiDAO archentiDAO;

    public void setArchentiDAO(ArchentiDAO archentiDAO) {
        this.archentiDAO = archentiDAO;
    }
    private ArchagenDAO archagenDAO;

    public void setArchagenDAO(ArchagenDAO archagenDAO) {
        this.archagenDAO = archagenDAO;
    }
    @Autowired
    private Ep_subutenteDAO ep_subutenteDAO;

    public void setEp_subutenteDAO(Ep_subutenteDAO ep_subutenteDAO) {
        this.ep_subutenteDAO = ep_subutenteDAO;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        log.debug("call custom load user by name");

        Ep_utenteExample ex = new Ep_utenteExample();
        ex.createCriteria().andUser_nameEqualTo(username.toUpperCase());

        List<Ep_utente> users = ep_utenteDAO.selectByExample(ex);

        if (users.size() < 1) {
            log.debug("user not found");
            throw new UsernameNotFoundException(username + "not found");

        } else {
            Ep_utente user = users.get(0);

            log.debug(user);

            ShopUser shopUser = new ShopUser(user.getUser_name().toUpperCase(), user.getPassword(), true, true, true, true, getAuthorities(user), user);

            //setup filter for "articoli" based on cdclas_a (codice strategico)
            if (StringUtils.isNotBlank(user.getTkclie())) {
                Archclie clie = new Archclie();
                clie.setTkclie(user.getTkclie());
                clie.setCdazie(user.getCdazie());
                clie = archclieDAO.selectByPrimaryKey(clie);

                Cliente cliente = new Cliente();
                cliente.setArchclie(clie);
                cliente.setArchenti(archentiDAO.selectByPrimaryKey(clie.getCdente()));


                shopUser.setCdclas_aFilter(vist_filtro_articoliDAO.getCdclas_aByTkclie(clie));

                if(shopUser.AMERICA_CDLISTS.contains(cliente.getArchclie().getCdlist())){
                    // se il cliente è listino America aggiungo il filtro per listino Europa
                    //shopUser.setCdclas_aFilterBase(shopUser.getCdclas_aFilter()); //salvo filtro base per ricerche articoli dei clienti america

                    List<Vist_filtro_articoli> filtro_list = vist_filtro_articoliDAO.getCdclas_aByCdlist("L03");

                    List<String> cdclas_aFilter = shopUser.getCdclas_aFilter();
                    for (Vist_filtro_articoli vist_filtro_articoli : filtro_list) {
                        cdclas_aFilter.add(vist_filtro_articoli.getCdclas_a());
                    }

                    shopUser.setIsSpecList(Boolean.TRUE);
                } else {
                    shopUser.setIsSpecList(Boolean.FALSE);
                }

                shopUser.setCliente(cliente);

//                if ((AuthorityUtils.userHasAuthority("ROLE_CLIE") || AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE"))
//                        && (!AuthorityUtils.userHasAuthority("ROLE_FORN")
//                        && !AuthorityUtils.userHasAuthority("ROLE_CAPO")
//                        && !AuthorityUtils.userHasAuthority("ROLE_AGEN")
//                        && !AuthorityUtils.userHasAuthority("ROLE_ISPE")
//                        && !AuthorityUtils.userHasAuthority("ROLE_GEST"))) {
//                    shopUser.setRealCustomer(true);
//                }else{
//                    shopUser.setRealCustomer(false);
//                }

            }

            return shopUser;
        }


    }

    private GrantedAuthority[] getAuthorities(Ep_utente user) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new GrantedAuthorityImpl("ROLE_USER"));
        if (StringUtils.equals(user.getFgadmin(), "S")) {
            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }
        if (StringUtils.isNotBlank(user.getTkclie())) {
            authList.add(new GrantedAuthorityImpl("ROLE_CLIE"));
        }
        if (user.getTksubutente() != null) {
            authList.add(new GrantedAuthorityImpl("ROLE_SUBUTENTE"));

            Ep_subutente subutente = ep_subutenteDAO.selectByPrimaryKey(user.getTksubutente());

            if (StringUtils.isNotBlank(subutente.getTkclie())) {
                user.setTkclie(subutente.getTkclie());
                authList.add(new GrantedAuthorityImpl("ROLE_CLIE"));
            }

        }
        if (StringUtils.isNotBlank(user.getTkforn())) {
            authList.add(new GrantedAuthorityImpl("ROLE_FORN"));
        }
        if (StringUtils.isNotBlank(user.getCdagen())) {

            Archagen age = new Archagen();
            age.setCdagen(user.getCdagen());
            age.setCdazie(user.getCdazie());
            age = archagenDAO.selectByPrimaryKey(age);

            if (StringUtils.equals(age.getCdagen(), age.getCdcapo())) {
                authList.add(new GrantedAuthorityImpl("ROLE_CAPO"));
            } else {
                authList.add(new GrantedAuthorityImpl("ROLE_AGEN"));
            }
        }
        if (StringUtils.isNotBlank(user.getCdispe())) {
            authList.add(new GrantedAuthorityImpl("ROLE_ISPE"));
        }
        if (StringUtils.isNotBlank(user.getCdutente())) {
            authList.add(new GrantedAuthorityImpl("ROLE_GEST"));
        }
        return authList.toArray(new GrantedAuthority[]{});
    }

    private GrantedAuthority[] getAuthorities(boolean isAdmin) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        authList.add(new GrantedAuthorityImpl("ROLE_USER"));
        if (isAdmin) {
            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }
        return authList.toArray(new GrantedAuthority[]{});
    }
}
