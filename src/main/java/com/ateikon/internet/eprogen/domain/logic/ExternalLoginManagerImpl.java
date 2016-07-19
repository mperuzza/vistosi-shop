/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.logic;

import com.ateikon.internet.eprogen.dao.pgmr.ArchagenDAO;
import com.ateikon.internet.eprogen.dao.pgmr.ArchclieDAO;
import com.ateikon.internet.eprogen.dao.pgmr.ArchentiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_subutenteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_utenteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_filtro_articoliDAO;
import com.ateikon.internet.eprogen.domain.pgmr.Archagen;
import com.ateikon.internet.eprogen.domain.pgmr.Archclie;
import com.ateikon.internet.eprogen.domain.pgmr.Archenti;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_subutente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utenteExample;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_filtro_articoli;
import com.ateikon.internet.eprogen.web.security.Ep_utentePasswordEncoder;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author emiliano
 */
public class ExternalLoginManagerImpl extends BaseManagerImpl implements ExternalLoginManager{
    
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private Ep_utenteDAO ep_utenteDAO;

    public void setEp_utenteDAO(Ep_utenteDAO ep_utenteDAO) {
        this.ep_utenteDAO = ep_utenteDAO;
    }

    public Ep_utenteDAO getEp_utenteDAO() {
        return ep_utenteDAO;
    }

    @Autowired
    private Vist_filtro_articoliDAO vist_filtro_articoliDAO;

    public void setVist_filtro_articoliDAO(Vist_filtro_articoliDAO vist_filtro_articoliDAO) {
        this.vist_filtro_articoliDAO = vist_filtro_articoliDAO;
    }

    @Autowired
    private ArchclieDAO archclieDAO;

    public void setArchclieDAO(ArchclieDAO archclieDAO) {
        this.archclieDAO = archclieDAO;
    }

    @Autowired
    private ArchentiDAO archentiDAO;

    public void setArchentiDAO(ArchentiDAO archentiDAO) {
        this.archentiDAO = archentiDAO;
    }

    @Autowired
    private ArchagenDAO archagenDAO;

    public void setArchagenDAO(ArchagenDAO archagenDAO) {
        this.archagenDAO = archagenDAO;
    }
    
    @Autowired
    private Ep_subutenteDAO ep_subutenteDAO;

    public void setEp_subutenteDAO(Ep_subutenteDAO ep_subutenteDAO) {
        this.ep_subutenteDAO = ep_subutenteDAO;
    }
    
    




    public Archclie getArchlieByKey(String tkclie, String cdazie){

        Archclie clie = new Archclie();
        clie.setTkclie(tkclie);
        clie.setCdazie(cdazie);
        clie = archclieDAO.selectByPrimaryKey(clie);

        return clie;
    }

    public Archenti getArchentiByKey(String cdente){

        return archentiDAO.selectByPrimaryKey(cdente);

    }

    public Archagen getArchagenByKey(String cdagen, String cdazie){

        Archagen age = new Archagen();
        age.setCdagen(cdagen);
        age.setCdazie(cdazie);
        age = archagenDAO.selectByPrimaryKey(age);

        return age;
    }

    public List<String> getCdclas_aByTkclie(Archclie clie){

        return vist_filtro_articoliDAO.getCdclas_aByTkclie(clie);
    }

    public List<Vist_filtro_articoli> getCdclas_aByCdlist(String cdlist){

        return vist_filtro_articoliDAO.getCdclas_aByCdlist(cdlist);
    }


    public Ep_utente getUser(String username, String password, String cdazie, String cddipa){

        Ep_utenteExample ex = new Ep_utenteExample();
        Ep_utenteExample.Criteria crit = ex.createCriteria();

        crit.andUser_nameEqualTo(username.toUpperCase());

        if(StringUtils.isNotEmpty(cdazie)){
            crit.andCdazieEqualTo(cdazie);
        }
        if(StringUtils.isNotEmpty(cddipa)){
            crit.andCddipaEqualTo(cddipa);
        }

        List<Ep_utente> users = ep_utenteDAO.selectByExample(ex);

        if(users.size() < 1) {
            log.debug("user not found");
            return null;

        }else{
            Ep_utente user = users.get(0);

            Ep_utentePasswordEncoder pe = new Ep_utentePasswordEncoder();

            if(pe.isPasswordValid(user.getPassword(), password, "atkciao")){
                return user;
            }else{
                return null;
            }

            //EpUser epUser = new EpUser(user.getUser_name().toUpperCase(), user.getPassword(), true, true, true, true, getAuthorities(user), user);

            //setup filter for "articoli" based on cdclas_a (codice startegico)
//            if(StringUtils.isNotBlank(user.getTkclie())){
//                Archclie clie = new Archclie();
//                clie.setTkclie(user.getTkclie());
//                clie.setCdazie(user.getCdazie());
//                clie = archclieDAO.selectByPrimaryKey(clie);
//
//                Cliente cliente = new Cliente();
//                cliente.setArchclie(clie);
//                cliente.setArchenti(archentiDAO.selectByPrimaryKey(clie.getCdente()));
//
//
//                epUser.setCliente(cliente);
//
//            }

        }

        //return null;


    }
    
    public Ep_subutente getSubutente(Long tksubutente){
        
        return ep_subutenteDAO.selectByPrimaryKey(tksubutente);
        
    }

}
