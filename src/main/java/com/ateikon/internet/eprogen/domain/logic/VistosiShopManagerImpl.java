/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.domain.logic;

import com.ateikon.internet.eprogen.dao.pgmr.ArchclieDAO;
import com.ateikon.internet.eprogen.dao.pgmr.ArchentiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.ArchrubricaDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Archrubrica_condc_noteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Archrubrica_tipoDAO;
import com.ateikon.internet.eprogen.dao.pgmr.AssofiscalDAO;
import com.ateikon.internet.eprogen.dao.pgmr.ClieattiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.CliegrpattiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Cms_promozioniDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Cms_promozioni_clienteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.DesignerDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_costantiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_postsDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_utenteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Lis_listinoDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Mrp_arch_articoliDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Mrp_arch_articoli_ulDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Mrp_arch_statoDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Mrp_file_giacenzaDAO;
import com.ateikon.internet.eprogen.dao.pgmr.UnitalocaliDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_articoli_datiextraDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_articoli_imgDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_articoli_ricambiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_colori_vetroDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_condc_accDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_condc_acc_posiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_condc_attiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_condc_grpatti_noteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_condc_grpatti_parDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_elettrificazioniDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_famigliaDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_finit_montDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_finit_vetroDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_tipiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_var1DAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_var2DAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_var3DAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_cp_collezioniDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_etichetteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_filtro_articoliDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_nazioni_refDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_offerteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Vist_semilavoratiDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Web_ord_posi_noteDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Web_ord_posititoDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Web_ord_testDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Web_ord_test_noteDAO;
import com.ateikon.internet.eprogen.domain.Vist_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.*;
import com.ateikon.internet.eprogen.web.interceptor.GeoIPInterceptor;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import com.ateikon.internet.eprogen.web.spring.SpecSheet;
import com.ateikon.internet.generic.domain.DTPaginatedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
public class VistosiShopManagerImpl extends BaseManagerImpl implements VistosiShopManager {

    private Log log = LogFactory.getLog(this.getClass());
    private String[] cdrepa_escl_List = new String[] { "10", "18" };
    private String[] statiEscludedList = new String[] { "ZEE" };
    private String[] tipoFilterList = new String[] { "PB" };

    private Map<String, String> certImageMap;
    private String[][] certImageValue = { { "arwCertificazione1", "CE" }, { "arwCertificazione2", "ENEC" },
            { "arwCertificazione3", "ETL" }, { "arwCertificazione4", "GOST" } };

    @Autowired
    private Vist_tipiDAO vist_tipiDAO;

    public void setVist_tipiDAO(Vist_tipiDAO vist_tipiDAO) {
        this.vist_tipiDAO = vist_tipiDAO;
    }

    @Autowired
    private Vist_famigliaDAO vist_famigliaDAO;

    public void setVist_famigliaDAO(Vist_famigliaDAO vist_famigliaDAO) {
        this.vist_famigliaDAO = vist_famigliaDAO;
    }

    @Autowired
    private Vist_colori_vetroDAO vist_colori_vetroDAO;

    public void setVist_colori_vetroDAO(Vist_colori_vetroDAO vist_colori_vetroDAO) {
        this.vist_colori_vetroDAO = vist_colori_vetroDAO;
    }

    @Autowired
    private Vist_finit_montDAO vist_finit_montDAO;

    public void setVist_finit_montDAO(Vist_finit_montDAO vist_finit_montDAO) {
        this.vist_finit_montDAO = vist_finit_montDAO;
    }

    @Autowired
    private Vist_finit_vetroDAO vist_finit_vetroDAO;

    public void setVist_finit_vetroDAO(Vist_finit_vetroDAO vist_finit_vetroDAO) {
        this.vist_finit_vetroDAO = vist_finit_vetroDAO;
    }

    @Autowired
    private Mrp_arch_articoliDAO mrp_arch_articoliDAO;

    public void setMrp_arch_articoliDAO(Mrp_arch_articoliDAO mrp_arch_articoliDAO) {
        this.mrp_arch_articoliDAO = mrp_arch_articoliDAO;
    }

    @Autowired
    private Mrp_arch_articoli_ulDAO mrp_arch_articoli_ulDAO;

    public void setMrp_arch_articoli_ulDAO(Mrp_arch_articoli_ulDAO mrp_arch_articoli_ulDAO) {
        this.mrp_arch_articoli_ulDAO = mrp_arch_articoli_ulDAO;
    }

    @Autowired
    private Vist_var1DAO vist_var1DAO;

    public void setVist_var1DAO(Vist_var1DAO vist_var1DAO) {
        this.vist_var1DAO = vist_var1DAO;
    }

    @Autowired
    private Vist_var2DAO vist_var2DAO;

    public void setVist_elettrificazioniDAO(Vist_elettrificazioniDAO vist_elettrificazioniDAO) {
        this.vist_elettrificazioniDAO = vist_elettrificazioniDAO;
    }

    @Autowired
    private Vist_var3DAO vist_var3DAO;

    public void setVist_var2DAO(Vist_var2DAO vist_var2DAO) {
        this.vist_var2DAO = vist_var2DAO;
    }

    @Autowired
    private Vist_elettrificazioniDAO vist_elettrificazioniDAO;

    public void setVist_var3DAO(Vist_var3DAO vist_var3DAO) {
        this.vist_var3DAO = vist_var3DAO;
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
    private UnitalocaliDAO unitalocaliDAO;

    public void setUnitalocaliDAO(UnitalocaliDAO unitalocaliDAO) {
        this.unitalocaliDAO = unitalocaliDAO;
    }

    @Autowired
    private AssofiscalDAO assofiscalDAO;

    public void setAssofiscalDAO(AssofiscalDAO assofiscalDAO) {
        this.assofiscalDAO = assofiscalDAO;
    }

    @Autowired
    private Lis_listinoDAO lis_listinoDAO;

    public void setLis_listinoDAO(Lis_listinoDAO lis_listinoDAO) {
        this.lis_listinoDAO = lis_listinoDAO;
    }

    @Autowired
    private Web_ord_testDAO web_ord_testDAO;

    public void setWeb_ord_testDAO(Web_ord_testDAO web_ord_testDAO) {
        this.web_ord_testDAO = web_ord_testDAO;
    }

    @Autowired
    private Web_ord_posititoDAO web_ord_posititoDAO;

    public void setWeb_ord_posititoDAO(Web_ord_posititoDAO web_ord_posititoDAO) {
        this.web_ord_posititoDAO = web_ord_posititoDAO;
    }

    @Autowired
    private Mrp_file_giacenzaDAO mrp_file_giacenzaDAO;

    public void setMrp_file_giacenzaDAO(Mrp_file_giacenzaDAO mrp_file_giacenzaDAO) {
        this.mrp_file_giacenzaDAO = mrp_file_giacenzaDAO;
    }

    @Autowired
    private Vist_cp_collezioniDAO vist_cp_collezioniDAO;

    public void setVist_cp_collezioniDAO(Vist_cp_collezioniDAO vist_cp_collezioniDAO) {
        this.vist_cp_collezioniDAO = vist_cp_collezioniDAO;
    }

    @Autowired
    private Web_ord_test_noteDAO web_ord_test_noteDAO;

    public void setWeb_ord_test_noteDAO(Web_ord_test_noteDAO web_ord_test_noteDAO) {
        this.web_ord_test_noteDAO = web_ord_test_noteDAO;
    }

    @Autowired
    private Web_ord_posi_noteDAO web_ord_posi_noteDAO;

    public void setWeb_ord_posi_noteDAO(Web_ord_posi_noteDAO web_ord_posi_noteDAO) {
        this.web_ord_posi_noteDAO = web_ord_posi_noteDAO;
    }

    @Autowired
    private Vist_etichetteDAO vist_etichetteDAO;

    public void setVist_etichetteDAO(Vist_etichetteDAO vist_etichetteDAO) {
        this.vist_etichetteDAO = vist_etichetteDAO;
    }

    @Autowired
    private Vist_articoli_imgDAO vist_articoli_imgDAO;

    public void setVist_articoli_imgDAO(Vist_articoli_imgDAO vist_articoli_imgDAO) {
        this.vist_articoli_imgDAO = vist_articoli_imgDAO;
    }

    @Autowired
    private Vist_articoli_ricambiDAO vist_articoli_ricambiDAO;

    public void setVist_articoli_ricambiDAO(Vist_articoli_ricambiDAO vist_articoli_ricambiDAO) {
        this.vist_articoli_ricambiDAO = vist_articoli_ricambiDAO;
    }

    @Autowired
    private Vist_filtro_articoliDAO vist_filtro_articoliDAO;

    public void setVist_filtro_articoliDAO(Vist_filtro_articoliDAO vist_filtro_articoliDAO) {
        this.vist_filtro_articoliDAO = vist_filtro_articoliDAO;
    }

    @Autowired
    private Cms_promozioniDAO cms_promozioniDAO;

    public void setCms_promozioniDAO(Cms_promozioniDAO cms_promozioniDAO) {
        this.cms_promozioniDAO = cms_promozioniDAO;
    }

    @Autowired
    private Cms_promozioni_clienteDAO cms_promozioni_clienteDAO;

    public void setCms_promozioni_clienteDAO(Cms_promozioni_clienteDAO cms_promozioni_clienteDAO) {
        this.cms_promozioni_clienteDAO = cms_promozioni_clienteDAO;
    }

    @Autowired
    private Vist_semilavoratiDAO vist_semilavoratiDAO;

    public void setVist_semilavoratiDAO(Vist_semilavoratiDAO vist_semilavoratiDAO) {
        this.vist_semilavoratiDAO = vist_semilavoratiDAO;
    }

    @Autowired
    private DesignerDAO designerDAO;

    public DesignerDAO getDesignerDAO() {
        return designerDAO;
    }

    public void setDesignerDAO(DesignerDAO designerDAO) {
        this.designerDAO = designerDAO;
    }

    @Autowired
    private Mrp_arch_statoDAO mrp_arch_statoDAO;

    public Mrp_arch_statoDAO getMrp_arch_statoDAO() {
        return mrp_arch_statoDAO;
    }

    public void setMrp_arch_statoDAO(Mrp_arch_statoDAO mrp_arch_statoDAO) {
        this.mrp_arch_statoDAO = mrp_arch_statoDAO;
    }

    @Autowired
    private Vist_offerteDAO vist_offerteDAO;

    public Vist_offerteDAO getVist_offerteDAO() {
        return vist_offerteDAO;
    }

    public void setVist_offerteDAO(Vist_offerteDAO vist_offerteDAO) {
        this.vist_offerteDAO = vist_offerteDAO;
    }

    @Autowired
    private ArchrubricaDAO archrubricaDAO;

    public ArchrubricaDAO getArchrubricaDAO() {
        return archrubricaDAO;
    }

    public void setArchrubricaDAO(ArchrubricaDAO archrubricaDAO) {
        this.archrubricaDAO = archrubricaDAO;
    }

    @Autowired
    private Archrubrica_tipoDAO archrubrica_tipoDAO;

    public Archrubrica_tipoDAO getArchrubrica_tipoDAO() {
        return archrubrica_tipoDAO;
    }

    public void setArchrubrica_tipoDAO(Archrubrica_tipoDAO archrubrica_tipoDAO) {
        this.archrubrica_tipoDAO = archrubrica_tipoDAO;
    }

    @Autowired
    private Archrubrica_condc_noteDAO archrubrica_condc_noteDAO;

    public Archrubrica_condc_noteDAO getArchrubrica_condc_noteDAO() {
        return archrubrica_condc_noteDAO;
    }

    public void setArchrubrica_condc_noteDAO(Archrubrica_condc_noteDAO archrubrica_condc_noteDAO) {
        this.archrubrica_condc_noteDAO = archrubrica_condc_noteDAO;
    }

    @Autowired
    private ClieattiDAO clieattiDAO;

    public ClieattiDAO getClieattiDAO() {
        return clieattiDAO;
    }

    public void setClieattiDAO(ClieattiDAO clieattiDAO) {
        this.clieattiDAO = clieattiDAO;
    }

    @Autowired
    private CliegrpattiDAO cliegrpattiDAO;

    public CliegrpattiDAO getCliegrpattiDAO() {
        return cliegrpattiDAO;
    }

    public void setCliegrpattiDAO(CliegrpattiDAO cliegrpattiDAO) {
        this.cliegrpattiDAO = cliegrpattiDAO;
    }

    @Autowired
    private Vist_condc_grpatti_noteDAO vist_condc_grpatti_noteDAO;

    public Vist_condc_grpatti_noteDAO getVist_condc_grpatti_noteDAO() {
        return vist_condc_grpatti_noteDAO;
    }

    public void setVist_condc_grpatti_noteDAO(Vist_condc_grpatti_noteDAO vist_condc_grpatti_noteDAO) {
        this.vist_condc_grpatti_noteDAO = vist_condc_grpatti_noteDAO;
    }

    @Autowired
    private Vist_condc_accDAO vist_condc_accDAO;

    public Vist_condc_accDAO getVist_condc_accDAO() {
        return vist_condc_accDAO;
    }

    public void setVist_condc_accDAO(Vist_condc_accDAO vist_condc_accDAO) {
        this.vist_condc_accDAO = vist_condc_accDAO;
    }

    @Autowired
    private Vist_condc_acc_posiDAO vist_condc_acc_posiDAO;

    public Vist_condc_acc_posiDAO getVist_condc_acc_posiDAO() {
        return vist_condc_acc_posiDAO;
    }

    public void setVist_condc_acc_posiDAO(Vist_condc_acc_posiDAO vist_condc_acc_posiDAO) {
        this.vist_condc_acc_posiDAO = vist_condc_acc_posiDAO;
    }

    @Autowired
    private Vist_nazioni_refDAO vist_nazioni_refDAO;

    public Vist_nazioni_refDAO getVist_nazioni_refDAO() {
        return vist_nazioni_refDAO;
    }

    public void setVist_nazioni_refDAO(Vist_nazioni_refDAO vist_nazioni_refDAO) {
        this.vist_nazioni_refDAO = vist_nazioni_refDAO;
    }

    @Autowired
    private Ep_utenteDAO ep_utenteDAO;

    public void setEp_utenteDAO(Ep_utenteDAO ep_utenteDAO) {
        this.ep_utenteDAO = ep_utenteDAO;
    }

    @Autowired
    private Ep_costantiDAO ep_costantiDAO;

    public void setEp_costantiDAO(Ep_costantiDAO ep_costantiDAO) {
        this.ep_costantiDAO = ep_costantiDAO;
    }

    @Autowired
    private Vist_articoli_datiextraDAO vist_articoli_datiextraDAO;

    public void setVist_articoli_datiextraDAO(Vist_articoli_datiextraDAO vist_articoli_datiextraDAO) {
        this.vist_articoli_datiextraDAO = vist_articoli_datiextraDAO;
    }

    @Autowired
    private Ep_postsDAO ep_postsDAO;

    public void setEp_postsDAO(Ep_postsDAO ep_postsDAO) {
        this.ep_postsDAO = ep_postsDAO;
    }

    @Autowired
    private Vist_condc_grpatti_parDAO vist_condc_grpatti_parDAO;

    public void setVist_condc_grpatti_parDAO(Vist_condc_grpatti_parDAO vist_condc_grpatti_parDAO) {
        this.vist_condc_grpatti_parDAO = vist_condc_grpatti_parDAO;
    }

    @Autowired
    private Vist_condc_attiDAO vist_condc_attiDAO;

    public void setVist_condc_attiDAO(Vist_condc_attiDAO vist_condc_attiDAO) {
        this.vist_condc_attiDAO = vist_condc_attiDAO;
    }

    public List<Vist_tipi> getVist_tipi() {
        /*
         * Vist_tipiExample ex = new Vist_tipiExample();
         * ex.setOrderByClause("dsvisttp");
         *
         * return vist_tipiDAO.selectByExample(ex);
         */

        Map pars = new HashMap();
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        return findVist_tipi(pars);
    }

    public List<Vist_famiglia> getVist_famiglia(Map pars) {
        /*
         * Vist_famigliaExample ex = new Vist_famigliaExample();
         * ex.setOrderByClause("dsvistfam");
         *
         * return vist_famigliaDAO.selectByExample(ex);
         */
        // Map pars = new HashMap();
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        List<Vist_famiglia> list = findVist_famiglia(pars);
        pars.put("fgpromo", "S");

        boolean userHasAuthority = AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS");

        Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);

        for (Vist_famiglia vist_famiglia : list) {

            pars.put("cdvistfam", vist_famiglia.getCdvistfam());

            int countOff = mrp_arch_articoliDAO.countOfferta(pars);
            if (countOff > 0 && !userHasAuthority) {
                // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                vist_famiglia.setStato(statoOff);
                // }
            } else {
                List<Mrp_arch_stato> stlist = selectDistinctByPars(pars);

                if (!stlist.isEmpty()) { // && stlist.size() == 1) { //&& "S".equals(stlist.get(0).getVist_fgrgb())) {
                    vist_famiglia.setStato(stlist.get(0));
                }
            }
        }

        return list;
    }

    public List<Vist_elettrificazioni> getVist_elettrificazioni() {
        Vist_elettrificazioniExample ex = new Vist_elettrificazioniExample();
        ex.setOrderByClause("dsvistelet");

        return vist_elettrificazioniDAO.selectByExample(ex);
    }

    public List<Vist_colori_vetro> getVist_colori_vetro() {
        Vist_colori_vetroExample ex = new Vist_colori_vetroExample();
        ex.setOrderByClause("dsvistcolv");

        return vist_colori_vetroDAO.selectByExample(ex);
    }

    public List<Vist_finit_mont> getVist_finit_mont() {
        Vist_finit_montExample ex = new Vist_finit_montExample();
        ex.setOrderByClause("dsvistfinm");

        return vist_finit_montDAO.selectByExample(ex);
    }

    public List<Mrp_arch_articoli> searchArticoli(Map pars) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Vist_tipi> findVist_tipi(Map pars) {

        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");
        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            pars.put("statiEscludedList", statiEscludedList);
            pars.put("tipoFilterList", tipoFilterList);
        }

        return vist_tipiDAO.find(pars);
    }

    public List<Vist_famiglia> findVist_famiglia(Map pars) {

        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");
        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            pars.put("statiEscludedList", statiEscludedList);
        }

        return vist_famigliaDAO.find(pars);
    }

    public List<Vist_cp_collezioni> findVist_cp_collezioni(Map pars) {
        return vist_cp_collezioniDAO.find(pars);
    }

    public Vist_tipi getVist_tipiByKey(String cdvisttp) {
        return vist_tipiDAO.selectByPrimaryKey(cdvisttp);
    }

    public Vist_famiglia getVist_famigliaByKey(String cd) {
        return vist_famigliaDAO.selectByPrimaryKey(cd);
    }

    public List<Vist_colori_vetro> findVist_colori_vetro(Map pars) {
        return vist_colori_vetroDAO.find(pars);
    }

    public List<Vist_finit_mont> findVist_finit_mont(Map pars) {
        return vist_finit_montDAO.find(pars);
    }

    public List<Vist_finit_vetro> findVist_finit_vetro(Map pars) {
        return vist_finit_vetroDAO.find(pars);
    }

    public Vist_colori_vetro getVist_colori_vetroByKey(String cd) {
        return vist_colori_vetroDAO.selectByPrimaryKey(cd);
    }

    public Vist_finit_mont getVist_finit_montByKey(String cd) {
        return vist_finit_montDAO.selectByPrimaryKey(cd);
    }

    public Vist_finit_vetro getVist_finit_vetroByKey(String cd) {
        return vist_finit_vetroDAO.selectByPrimaryKey(cd);
    }

    public List<Vist_cp_collezioni> getVist_cp_collezioni() {

        /*
         * Vist_cp_collezioniExample ex = new Vist_cp_collezioniExample();
         * ex.setOrderByClause("dsvistccol");
         */
        Map pars = new HashMap();
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        return vist_cp_collezioniDAO.find(pars);
    }

    public Vist_cp_collezioni getVist_cp_collezioniByKey(String cdvistccol) {
        return vist_cp_collezioniDAO.selectByPrimaryKey(cdvistccol);
    }

    public DTPaginatedList selectArticoliByExamplePag(Map pars, String orderByClause, String direction, int pageNumber,
            int pageSize) {

        // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // } else {
        // pars.put("cdclas_aList", DEFAULT_CDCLAS_A); //filtro solo articoli listino
        // }
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        List<Mrp_arch_articoli> searchByPars = null;
        List<Mrp_arch_articoli> count = null;

        count = mrp_arch_articoliDAO.searchByPars(pars);
        int size = count.size();

        if (pageSize < 0) {
            searchByPars = mrp_arch_articoliDAO.searchByPars(pars);
        } else {
            searchByPars = mrp_arch_articoliDAO.searchByPars(pars, pageNumber, pageSize);
        }

        DTPaginatedList pl = new DTPaginatedList(size, searchByPars, pageNumber, pageSize, null, orderByClause,
                SortOrderEnum.fromName(direction + "ending"));
        return pl;
    }

    public DTPaginatedList selectFamiglieByExamplePag(Map pars, String orderByClause, String direction, int pageNumber,
            int pageSize) {

        log.debug("selectFamiglieByExamplePag");
        log.debug(pars);

        int size = this.vist_famigliaDAO.countFind(pars);

        List<Vist_famiglia> list = this.vist_famigliaDAO.find(pars, orderByClause + " " + direction, pageNumber,
                pageSize);

        for (Vist_famiglia vist_famiglia : list) {

            Map stpars = new HashMap();
            stpars.put("cdvistfam", vist_famiglia.getCdvistfam());
            stpars.put("cdvisttp", pars.get("cdvisttp"));
            stpars.put("dsvistccol", pars.get("dsvistccol"));
            stpars.put("cdclas_aList", pars.get("cdclas_aList"));

            stpars.put("fgpromo", "S");
            stpars.put("fgweb", "S");
            if (pars.containsKey("statiEscludedList")) {
                stpars.put("cdstato_escl_List", pars.get("statiEscludedList"));
            }

            int countOff = mrp_arch_articoliDAO.countOfferta(stpars);
            if (countOff > 0 && !AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                vist_famiglia.setStato(statoOff);
                // }
            } else {
                stpars.put("statiFilterList", pars.get("statiFilterList"));
                List<Mrp_arch_stato> stlist = selectDistinctByPars(stpars);

                if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                    vist_famiglia.setStato(stlist.get(0));
                }
            }

        }

        DTPaginatedList pl = new DTPaginatedList(size, list, pageNumber, pageSize, null, orderByClause,
                SortOrderEnum.fromName(direction + "ending"));
        return pl;
    }

    public DTPaginatedList selectCollezioniByExamplePag(Map pars, String orderByClause, String direction,
            int pageNumber, int pageSize) {

        log.debug("selectCollezioniByExamplePag");
        
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        int size = 0;// this.vist_famigliaDAO.countFind(pars);

        List<Mrp_arch_articoli> list = this.vist_famigliaDAO.findColl(pars, orderByClause + " " + direction, pageNumber,
                pageSize);

        for (Mrp_arch_articoli mrp_arch_articoli : list) {

            Map stpars = new HashMap();
            stpars.put("cdvistfam", mrp_arch_articoli.getVist_famiglia().getCdvistfam());
            stpars.put("cdvisttp", mrp_arch_articoli.getCdvisttp());
            stpars.put("dsvistccol", pars.get("dsvistccol"));
            stpars.put("cdclas_aList", pars.get("cdclas_aList"));

            stpars.put("fgpromo", "S");
            stpars.put("fgweb", "S");
            if (pars.containsKey("statiEscludedList")) {
                stpars.put("cdstato_escl_List", pars.get("statiEscludedList"));
            }
            int countOff = mrp_arch_articoliDAO.countOfferta(stpars);
            if (countOff > 0 && !AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                mrp_arch_articoli.getVist_famiglia().setStato(statoOff);
                // }
            } else {
                stpars.put("statiFilterList", pars.get("statiFilterList"));
                List<Mrp_arch_stato> stlist = selectDistinctByPars(stpars);

                if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                    mrp_arch_articoli.getVist_famiglia().setStato(stlist.get(0));
                }
            }
        }

        DTPaginatedList pl = new DTPaginatedList(size, list, pageNumber, pageSize, null, orderByClause,
                SortOrderEnum.fromName(direction + "ending"));
        return pl;
    }

    public DTPaginatedList selectTipiByExamplePag(Map pars, String orderByClause, String direction, int pageNumber,
            int pageSize) {

        log.debug("selectTipiByExamplePag");
        log.debug(pars);

        int size = this.vist_tipiDAO.countFind(pars);

        List<Vist_tipi> list = this.vist_tipiDAO.find(pars, orderByClause + " " + direction, pageNumber, pageSize);

        for (Vist_tipi vist_tipi : list) {

            Map stpars = new HashMap();
            stpars.put("cdvisttp", vist_tipi.getCdvisttp());
            stpars.put("cdvistfam", pars.get("cdvistfam"));
            stpars.put("dsvistccol", pars.get("dsvistccol"));
            stpars.put("cdclas_aList", pars.get("cdclas_aList"));

            stpars.put("fgpromo", "S");
            stpars.put("fgweb", "S");
            if (pars.containsKey("statiEscludedList")) {
                stpars.put("cdstato_escl_List", pars.get("statiEscludedList"));
            }
            int countOff = mrp_arch_articoliDAO.countOfferta(stpars);
            if (countOff > 0 && !AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                vist_tipi.setStato(statoOff);
                // }
            } else {
                stpars.put("statiFilterList", pars.get("statiFilterList"));
                List<Mrp_arch_stato> stlist = selectDistinctByPars(stpars);

                if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                    vist_tipi.setStato(stlist.get(0));
                }
            }

        }

        DTPaginatedList pl = new DTPaginatedList(size, list, pageNumber, pageSize, null, orderByClause,
                SortOrderEnum.fromName(direction + "ending"));
        return pl;
    }
    // aggiunta per ricerca rapida

    public DTPaginatedList selectArticoliUlByExamplePag(Map pars, String orderByClause, String direction,
            int pageNumber, int pageSize) {

        // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // } else {
        // pars.put("cdclas_aList", DEFAULT_CDCLAS_A); //filtro solo articoli listino
        // }
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        List<Mrp_arch_articoli_ul> searchByPars = null;
        List<Mrp_arch_articoli_ul> count = null;

        count = mrp_arch_articoli_ulDAO.searchByPars(pars);
        int size = count.size();

        if (pageSize < 0) {
            searchByPars = mrp_arch_articoli_ulDAO.searchByPars(pars);
        } else {
            searchByPars = mrp_arch_articoli_ulDAO.searchByPars(pars, pageNumber, pageSize);
        }

        DTPaginatedList pl = new DTPaginatedList(size, searchByPars, pageNumber, pageSize, null, orderByClause,
                SortOrderEnum.fromName(direction + "ending"));
        return pl;
    }

    public Mrp_arch_articoli getMrp_arch_articoliByKey(String cd) {

        return mrp_arch_articoliDAO.selectByPrimaryKey(cd);

    }

    public List<Mrp_arch_articoli> selectMrp_arch_articoliByPars(Map pars) {

        Mrp_arch_articoliExample example = new Mrp_arch_articoliExample();
        Mrp_arch_articoliExample.Criteria crit = example.createCriteria();
        crit.andFgwebEqualTo("S");
        if (pars.get("cdclas_aList") != null) {
            List<String> cdclas_aList = (List<String>) pars.get("cdclas_aList");
            if (cdclas_aList.size() > 0) {
                crit.andCdclas_aIn(cdclas_aList);
            }
        }
        if (pars.get("cdrepa_escl_List") != null) {
            try {
                List<String> cdrepa_escl_List = (List<String>) pars.get("cdrepa_escl_List");
                if (cdrepa_escl_List.size() > 0) {
                    crit.andCdrepaNotIn(cdrepa_escl_List);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (pars.get("statiEscludedList") != null) {
            try {
                List<String> statiEscludedList = (List<String>) pars.get("statiEscludedList");
                if (statiEscludedList.size() > 0) {
                    crit.andCdstatoNotIn(statiEscludedList);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (pars.get("cdvisttp") != null) {
            crit.andCdvisttpEqualTo((String) pars.get("cdvisttp"));
        }
        if (pars.get("cdvistfam") != null) {
            crit.andCdvistfamEqualTo((String) pars.get("cdvistfam"));
        }
        if (pars.get("cdvistcolv") != null) {
            crit.andCdvistcolvEqualTo((String) pars.get("cdvistcolv"));
        }
        if (pars.get("cdvistfinm") != null) {
            crit.andCdvistfinmEqualTo((String) pars.get("cdvistfinm"));
        }
        if (pars.get("vist_filedis") != null) {
            crit.andVist_filedisEqualTo((String) pars.get("vist_filedis"));
        }
        if (pars.get("fgpromo") != null) {
            crit.andFgpromoEqualTo((String) pars.get("fgpromo"));
        }
        //
        // la finitura del vetro non è più gestita indipendente
        // ora è inclusa nel colore vetro
        /*
         * if (pars.containsKey("cdvistfinv") && !"ign".equals(pars.get("cdvistfinv")))
         * { if (pars.get("cdvistfinv") != null) { crit.andCdvistfinvEqualTo((String)
         * pars.get("cdvistfinv")); } else if (pars.get("cdvistcolv") != null) {
         * crit.andCdvistfinvIsNull(); } }
         */
        if (pars.get("cdvistelet") != null) {
            crit.andCdvisteletEqualTo((String) pars.get("cdvistelet"));
        }
        if (pars.containsKey("cdvistv1")) {
            if (pars.get("cdvistv1") != null) {
                crit.andCdvistv1EqualTo((String) pars.get("cdvistv1"));
            } else {
                crit.andCdvistv1IsNull();
            }
        }
        if (pars.containsKey("cdvistv2")) {
            if (pars.get("cdvistv2") != null) {
                crit.andCdvistv2EqualTo((String) pars.get("cdvistv2"));
            } else {
                crit.andCdvistv2IsNull();
            }
        }
        if (pars.containsKey("cdvistv3")) {
            if (pars.get("cdvistv3") != null) {
                crit.andCdvistv3EqualTo((String) pars.get("cdvistv3"));
            } else {
                crit.andCdvistv3IsNull();
            }
        }

        example.setOrderByClause("cdvistelet");

        List list = this.mrp_arch_articoliDAO.selectByExample(example);

        return list;
    }

    public List<Vist_elettrificazioni> findVist_elettrificazioni(Map pars) {
        return vist_elettrificazioniDAO.find(pars);
    }

    public List<Vist_var1> findVist_var1(Map pars) {
        return vist_var1DAO.find(pars);
    }

    public Vist_var1 getVist_var1ByKey(String cdvistv1) {
        return vist_var1DAO.selectByPrimaryKey(cdvistv1);
    }

    public List<Vist_var2> findVist_var2(Map pars) {
        return vist_var2DAO.find(pars);
    }

    public Vist_var2 getVist_var2ByKey(String cdvistv2) {
        return vist_var2DAO.selectByPrimaryKey(cdvistv2);
    }

    public List<Vist_var3> findVist_var3(Map pars) {
        return vist_var3DAO.find(pars);
    }

    public Vist_var3 getVist_var3ByKey(String cdvistv3) {
        return vist_var3DAO.selectByPrimaryKey(cdvistv3);
    }

    public List<Mrp_arch_articoli> getModelli(Map pars) {

        pars.put("cdrepa_escl_List", cdrepa_escl_List);

        // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // } else {
        // pars.put("cdclas_aList", DEFAULT_CDCLAS_A); //filtro solo articoli listino
        // }
        pars.put("fgweb", "S");
        return mrp_arch_articoliDAO.getModelli(pars);
    }

    public List<Mrp_arch_articoli> getModelliDis(Map pars) {

        pars.put("cdrepa_escl_List", cdrepa_escl_List);

        // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // } else {
        // pars.put("cdclas_aList", DEFAULT_CDCLAS_A); //filtro solo articoli listino
        // }
        pars.put("fgweb", "S");
        return mrp_arch_articoliDAO.getModelliDis(pars);
    }

    public Cliente getCliente(String tkclie, String cdazie) {

        Cliente cliente = new Cliente();

        ArchclieKey keyClie = new ArchclieKey();
        keyClie.setTkclie(tkclie);
        keyClie.setCdazie(cdazie);

        cliente.setArchclie(archclieDAO.selectByPrimaryKey(keyClie));
        if (cliente.getArchclie() != null) {
            cliente.setArchenti(archentiDAO.selectByPrimaryKey(cliente.getArchclie().getCdente()));
        }

        return cliente;
    }

    public List<Cliente> getClienteByCdagen(String cdagen, String cdazie) {

        ArchclieExample ex = new ArchclieExample();
        ex.createCriteria().andCdagenEqualTo(cdagen).andCdazieEqualTo(cdazie);

        List<Archclie> archClieList = archclieDAO.selectByExample(ex);
        List<Cliente> clieList = new ArrayList<Cliente>();

        for (Archclie archclie : archClieList) {

            Cliente clie = new Cliente();
            clie.setArchclie(archclie);
            clie.setArchenti(archentiDAO.selectByPrimaryKey(clie.getArchclie().getCdente()));
            clieList.add(clie);

        }

        // sort by country name
        BeanComparator comp = new BeanComparator("archenti.ragcog");
        Collections.sort(clieList, comp);

        return clieList;
    }

    public Lis_listino getListinoByKey(String cdlist) {
        return lis_listinoDAO.selectByPrimaryKey(cdlist);
    }

    public CarrelloItemForm getCarrello(Long tkordi) {

        CarrelloItemForm cart = new CarrelloItemForm();

        Web_ord_test test = web_ord_testDAO.selectByPrimaryKey(tkordi);

        Web_ord_test_noteExample exT = new Web_ord_test_noteExample();
        exT.createCriteria().andTkordiEqualTo(test.getTkordi()).andRigaEqualTo(10).andTiponotaEqualTo("CLIENTE");

        List<Web_ord_test_note> noteT = web_ord_test_noteDAO.selectByExample(exT);

        if (noteT != null && noteT.size() > 0) {
            test.setWeb_ord_test_note(noteT.get(0));
        }

        cart.setOrd_test(test);

        Web_ord_posititoExample wopEx = new Web_ord_posititoExample();
        wopEx.createCriteria().andTkordiEqualTo(tkordi);
        wopEx.setOrderByClause("fgpromo, nrposi");

        List<Web_ord_positito> posititos = web_ord_posititoDAO.selectByExample(wopEx);

        for (Web_ord_positito web_ord_positito : posititos) {

            Web_ord_posi_noteExample exP = new Web_ord_posi_noteExample();
            exP.createCriteria().andTkordiEqualTo(cart.getOrd_test().getTkordi())
                    .andTkposiEqualTo(web_ord_positito.getTkposi()).andRigaEqualTo(10).andTiponotaEqualTo("CLIENTE");

            List<Web_ord_posi_note> noteP = web_ord_posi_noteDAO.selectByExample(exP);

            if (noteP != null && noteP.size() > 0) {
                web_ord_positito.setWeb_ord_posi_note(noteP.get(0));
            }

        }

        cart.setOrd_posititos(posititos);

        return cart;
    }

    public Vist_elettrificazioni getVist_elettrificazioniByKey(String cd) {
        return vist_elettrificazioniDAO.selectByPrimaryKey(cd);
    }

    public Vist_elettrificazioni getVist_elettrificazioniAlternative(String cd) {

        Vist_elettrificazioniExample ex = new Vist_elettrificazioniExample();
        ex.createCriteria().andCdulEqualTo(cd).andCdvisteletNotEqualTo(cd);

        List<Vist_elettrificazioni> elettrificazioni = vist_elettrificazioniDAO.selectByExample(ex);

        if (elettrificazioni != null && elettrificazioni.size() > 0) {
            return elettrificazioni.get(0);
        } else {
            return null;
        }
    }

    public Mrp_file_giacenza getMrp_file_giacenzaByKey(String cdarti, String cdvar, Long tkmaga) {
        Mrp_file_giacenza k = new Mrp_file_giacenza();
        k.setCdarti(cdarti);
        k.setCdvar(cdvar);
        k.setTkmaga(tkmaga);

        return mrp_file_giacenzaDAO.selectByPrimaryKey(k);
    }

    public void saveCarrello(CarrelloItemForm cart) throws IllegalAccessException, InvocationTargetException {

        ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.isRealCustomer()) {
            saveCarrelloCondition(cart);
        }
        saveCarrelloTestNote(cart);
    }

    public void saveCarrelloCondition(CarrelloItemForm cart) throws IllegalAccessException, InvocationTargetException {

        if (!(Boolean) cart.getCondc().get("accepted")) {
            Vist_condc_acc vist_condc_acc = new Vist_condc_acc();

            vist_condc_acc.setTkutente(cart.getOrd_test().getTkutente());
            vist_condc_acc.setDtaccettazione(new Date());

            vist_condc_accDAO.insert(vist_condc_acc);

            Map condc = cart.getCondc();

            String[] tipoNota = new String[] { "GENERALI" }; // new String[]{"CONDF", "ACCF", "SERV", "CONDL"};

            for (String tipo : tipoNota) {

                if (condc.get(tipo) != null) {
                    List<BaseCondcNote> condPosi = (List<BaseCondcNote>) condc.get(tipo);

                    for (BaseCondcNote baseCondcNote : condPosi) {

                        Vist_condc_acc_posi vist_condc_acc_posi = new Vist_condc_acc_posi();

                        BeanUtils.copyProperties(vist_condc_acc_posi, baseCondcNote);

                        vist_condc_acc_posi.setTkcondcacc(vist_condc_acc.getTkcondcacc());

                        vist_condc_acc_posiDAO.insert(vist_condc_acc_posi);

                    }
                }
            }

        }

    }

    public void saveCarrelloTestNote(CarrelloItemForm obj) {
        Web_ord_test test = obj.getOrd_test();

        web_ord_testDAO.updateByPrimaryKey(test);

        saveCarrelloNote(obj);
    }

    public void saveCarrelloNote(CarrelloItemForm obj) {

        Web_ord_test test = obj.getOrd_test();

        log.debug("***************************");
        log.debug(test.getWeb_ord_test_note());

        if (test.getWeb_ord_test_note().getTkordi() != null
                || StringUtils.isNotBlank(test.getWeb_ord_test_note().getTesto())) {
            Web_ord_test_note notaT = test.getWeb_ord_test_note();

            if (notaT.getTkordi() == null) {
                notaT.setTkordi(test.getTkordi());

                web_ord_test_noteDAO.insert(notaT);
            } else {
                web_ord_test_noteDAO.updateByPrimaryKey(notaT);
            }

        }

        List<Web_ord_positito> posititos = obj.getOrd_posititos();

        for (Web_ord_positito web_ord_positito : posititos) {

            if (web_ord_positito.getWeb_ord_posi_note().getTkposi() != null
                    || StringUtils.isNotBlank(web_ord_positito.getWeb_ord_posi_note().getTesto())) {

                Web_ord_posi_note notaP = web_ord_positito.getWeb_ord_posi_note();

                if (notaP.getTkordi() == null) {
                    notaP.setTkordi(web_ord_positito.getTkordi());
                }

                if (notaP.getTkposi() == null) {

                    notaP.setTkposi(web_ord_positito.getTkposi());

                    web_ord_posi_noteDAO.insert(notaP);
                } else {
                    web_ord_posi_noteDAO.updateByPrimaryKey(notaP);
                }

            }

        }

    }

    public List<Vist_etichette> getEtichette(String cdartm) {

        Vist_articoli_img art_img = vist_articoli_imgDAO.selectByPrimaryKey(cdartm);

        List<Vist_etichette> etichette = new ArrayList<Vist_etichette>();

        if (art_img != null) {
            if (StringUtils.isNotBlank(art_img.getCdvistet1())) {
                Vist_etichette e = vist_etichetteDAO.selectByPrimaryKey(art_img.getCdvistet1());
                log.debug("after get cdvistet1");
                if (e != null) {
                    etichette.add(e);
                }
            }
            if (StringUtils.isNotBlank(art_img.getCdvistet2())) {
                Vist_etichette e = vist_etichetteDAO.selectByPrimaryKey(art_img.getCdvistet2());
                if (e != null) {
                    etichette.add(e);
                }
            }
            if (StringUtils.isNotBlank(art_img.getCdvistet3())) {
                Vist_etichette e = vist_etichetteDAO.selectByPrimaryKey(art_img.getCdvistet3());
                if (e != null) {
                    etichette.add(e);
                }
            }
            if (StringUtils.isNotBlank(art_img.getCdvistet4())) {
                Vist_etichette e = vist_etichetteDAO.selectByPrimaryKey(art_img.getCdvistet4());
                if (e != null) {
                    etichette.add(e);
                }
            }
            if (StringUtils.isNotBlank(art_img.getCdvistet5())) {
                Vist_etichette e = vist_etichetteDAO.selectByPrimaryKey(art_img.getCdvistet5());
                if (e != null) {
                    etichette.add(e);
                }
            }
        }

        log.debug("after get cdvistet1");
        return etichette;
    }

    public List<Mrp_arch_articoli> getRicambi(String cdfinito) {

        Vist_articoli_ricambiExample ex = new Vist_articoli_ricambiExample();
        ex.createCriteria().andCdartiEqualTo(cdfinito);
        ex.setOrderByClause("numv");
        List<Vist_articoli_ricambi> ricList = vist_articoli_ricambiDAO.selectByExample(ex);

        List<Mrp_arch_articoli> ricambi = new ArrayList<Mrp_arch_articoli>();

        for (Vist_articoli_ricambi vist_articoli_ricambi : ricList) {
            Mrp_arch_articoliExample aEx = new Mrp_arch_articoliExample();
            aEx.createCriteria().andCdartiEqualTo(vist_articoli_ricambi.getCdarti_ric());
            List<Mrp_arch_articoli> ricArt = mrp_arch_articoliDAO.selectByExample(aEx);

            for (Mrp_arch_articoli mrp_arch_articoli : ricArt) {

                mrp_arch_articoli.setVist_tipi(getVist_tipiByKey(mrp_arch_articoli.getCdvisttp()));
                mrp_arch_articoli.setVist_famiglia(getVist_famigliaByKey(mrp_arch_articoli.getCdvistfam()));
                mrp_arch_articoli.setVist_var1(getVist_var1ByKey(mrp_arch_articoli.getCdvistv1()));
                mrp_arch_articoli.setVist_var1(getVist_var1ByKey(mrp_arch_articoli.getCdvistv1()));
                mrp_arch_articoli.setVist_var2(getVist_var2ByKey(mrp_arch_articoli.getCdvistv2()));
                mrp_arch_articoli.setVist_var3(getVist_var3ByKey(mrp_arch_articoli.getCdvistv3()));
                mrp_arch_articoli.setVist_finit_mont(getVist_finit_montByKey(mrp_arch_articoli.getCdvistfinm()));
                mrp_arch_articoli.setVist_colori_vetro(getVist_colori_vetroByKey(mrp_arch_articoli.getCdvistcolv()));
                // mrp_arch_articoli.setVist_finit_vetro(getVist_finit_vetroByKey(mrp_arch_articoli.getCdvistfinv()));
                mrp_arch_articoli
                        .setVist_elettrificazioni(getVist_elettrificazioniByKey(mrp_arch_articoli.getCdvistelet()));
                if (StringUtils.isNotBlank(mrp_arch_articoli.getCdvistseml())) {
                    mrp_arch_articoli
                            .setVist_semilavorati(getVist_semilavoratiByKey(mrp_arch_articoli.getCdvistseml()));
                }
                mrp_arch_articoli.setRicambio(true);
                // overwrite con il nome immagine del ricambio
                mrp_arch_articoli.setDatiRicambio(vist_articoli_ricambi);

                ricambi.add(mrp_arch_articoli);
            }
        }

        return ricambi;
    }

    public Mrp_arch_articoli getRicambio(String cdarti_ric) {

        Mrp_arch_articoli art = mrp_arch_articoliDAO.selectByPrimaryKey(cdarti_ric);
        Vist_articoli_ricambiExample ex = new Vist_articoli_ricambiExample();
        ex.createCriteria().andCdarti_ricEqualTo(cdarti_ric);

        List<Vist_articoli_ricambi> ric = vist_articoli_ricambiDAO.selectByExample(ex);
        if (!ric.isEmpty()) {
            art.setDatiRicambio(ric.get(0));
        }

        return art;
    }

    public Mrp_arch_articoli getRicambio(String cdarti, String cdarti_ric) {

        Mrp_arch_articoli art = mrp_arch_articoliDAO.selectByPrimaryKey(cdarti_ric);
        Vist_articoli_ricambiKey k = new Vist_articoli_ricambiKey();
        k.setCdarti(cdarti);
        k.setCdarti_ric(cdarti_ric);
        art.setDatiRicambio(vist_articoli_ricambiDAO.selectByPrimaryKey(k));

        return art;
    }

    public Vist_articoli_ricambi getDatiRicambio(String cdarti, String cdarti_ric) {

        Vist_articoli_ricambiKey k = new Vist_articoli_ricambiKey();
        k.setCdarti(cdarti);
        k.setCdarti_ric(cdarti_ric);
        return vist_articoli_ricambiDAO.selectByPrimaryKey(k);

    }

    public List<String> getCdclas_aByTkclie(Archclie clie) {
        return vist_filtro_articoliDAO.getCdclas_aByTkclie(clie);
    }

    public List<Cms_promozioni> getPromoByTkclie(String tkclie) {

        Cms_promozioni_clienteExample exPc = new Cms_promozioni_clienteExample();
        exPc.createCriteria().andTkclieEqualTo(tkclie);
        exPc.setOrderByClause("dtinse");

        List<Cms_promozioni_cliente> cliePromo = cms_promozioni_clienteDAO.selectByExample(exPc);

        List<Cms_promozioni> promo = new ArrayList<Cms_promozioni>();

        for (Cms_promozioni_cliente cms_promozioni_cliente : cliePromo) {

            Cms_promozioni p = cms_promozioniDAO.selectByPrimaryKey(cms_promozioni_cliente.getTkpromo());

            promo.add(p);

        }

        return promo;
    }

    public List<Cms_promozioni> getActivePromo(String tkclie, String cdagen) {

        Cms_promozioni_clienteExample exPc = new Cms_promozioni_clienteExample();
        exPc.createCriteria().andTkclieEqualTo(tkclie);
        exPc.or(exPc.createCriteria().andCdagenEqualTo(cdagen));
        exPc.setOrderByClause("dtinse");

        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        Date time = cal.getTime();

        List<Cms_promozioni_cliente> cliePromo = cms_promozioni_clienteDAO.selectByExample(exPc);

        List<Cms_promozioni> promo = new ArrayList<Cms_promozioni>();

        for (Cms_promozioni_cliente cms_promozioni_cliente : cliePromo) {

            if (cms_promozioni_cliente.getTkpromo() != null) {

                Cms_promozioni p = cms_promozioniDAO.selectByPrimaryKey(cms_promozioni_cliente.getTkpromo());

                Date dtinva = p.getDtinva();
                Date dtfiva = p.getDtfiva();
                boolean after = dtinva.before(time);
                boolean before = dtfiva.after(time);

                if (after && before) {
                    if (p != null) {
                        promo.add(p);
                    }
                }
            }

        }

        return promo;
    }

    public DTPaginatedList selectCliePag(Map pars, String orderByClause, String direction, int pageNumber,
            int pageSize) {
        int size = this.archclieDAO.countCliePag(pars);

        List list = this.archclieDAO.selectCliePag(pars, orderByClause + " " + direction, pageNumber, pageSize);

        DTPaginatedList pl = new DTPaginatedList(size, list, pageNumber, pageSize, null, orderByClause,
                SortOrderEnum.fromName(direction + "ending"));
        return pl;
    }

    public Boolean isRicambio(String cdarti_ric) {
        Vist_articoli_ricambi ric = new Vist_articoli_ricambi();
        ric.setCdarti_ric(cdarti_ric);

        return vist_articoli_ricambiDAO.countByRic(ric) > 0;

    }

    public List<Vist_filtro_articoli> getCdclas_aByCdlist(String cdlist) {

        return vist_filtro_articoliDAO.getCdclas_aByCdlist(cdlist);
    }

    public List<Vist_vetro> findVist_vetro(Map pars) {

        pars.put("cdrepa_escl_List", cdrepa_escl_List);

        List<Vist_vetro> finvetro = mrp_arch_articoliDAO.findVetro(pars);

        if (finvetro.size() == 1) {

            if (finvetro.get(0).getCol().getCdvistcolv() == null && finvetro.get(0).getFin().getCdvistfinv() == null) {
                finvetro.remove(finvetro.get(0));
            }
        }

        for (Vist_vetro vist_vetro : finvetro) {

            if (vist_vetro.getCol().getCdvistcolv() != null) {

                Vist_colori_vetro colv = getVist_colori_vetroByKey(vist_vetro.getCol().getCdvistcolv());
                if (colv != null) {
                    vist_vetro.setCol(colv);
                }
            }
            if (vist_vetro.getFin().getCdvistfinv() != null) {

                Vist_finit_vetro finv = getVist_finit_vetroByKey(vist_vetro.getFin().getCdvistfinv());
                if (finv != null) {
                    vist_vetro.setFin(finv);
                }
            }

        }

        // sort by country descr
        BeanComparator comp = new BeanComparator("dsvistvetro");
        Collections.sort(finvetro, comp);

        return finvetro;
    }

    public List<Unitalocale> getUnitalocali(Map pars) {
        return unitalocaliDAO.searchUnitalocali(pars);
    }

    public Vist_semilavorati getVist_semilavoratiByKey(String cd) {
        return vist_semilavoratiDAO.selectByPrimaryKey(cd);
    }

    public List<Designer> getDesigner() {

        // DesignerExample ex = new DesignerExample();
        // ex.setOrderByClause("dtinse");
        // return designerDAO.selectByExampleWithoutBLOBs(ex);
        return designerDAO.getArchivio(null);
    }

    public DesignerWithBLOBs getDesigner(String cddesigner) {

        return designerDAO.selectByPrimaryKey(cddesigner);
    }

    public String getSchedaTec(String cdvisttp, String cdvistfam, String cdvistv1, String cdvistv2, String cdvistv3,
            String cdvistelet) {

        String path = null;
        Map pars = new HashMap();
        pars.put("cdvisttp", cdvisttp);
        pars.put("cdvistfam", cdvistfam);
        pars.put("cdvistv1", cdvistv1);
        pars.put("cdvistv2", cdvistv2);
        pars.put("cdvistv3", cdvistv3);
        pars.put("cdvistelet", cdvistelet);
        pars.put("cdrepa_escl_List", Arrays.asList(cdrepa_escl_List));
        pars.put("fgweb", "S");

        try {

            path = vist_articoli_imgDAO.getSchedatec(pars);

        } catch (Exception e) {
        }

        return path;
    }

    public Mrp_arch_articoli getArticoloByCdartm(String cdartm) {

        Mrp_arch_articoliExample ex = new Mrp_arch_articoliExample();

        ex.createCriteria().andCdartmEqualTo(cdartm);

        List<Mrp_arch_articoli> artList = mrp_arch_articoliDAO.selectByExample(ex);

        if (!artList.isEmpty() && artList.size() <= 2) {
            return artList.get(0);
        } else {
            return null;
        }

    }

    public List<Mrp_arch_articoli> searchArticoliByPars(Map pars) {

        return mrp_arch_articoliDAO.searchByPars(pars, 0, -1);
    }

    public List<Mrp_arch_articoli> searchArticoliByPars(Map pars, int pageNumber, int pagesize) {

        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // } else {
        // pars.put("cdclas_aList", DEFAULT_CDCLAS_A); //filtro solo articoli listino
        // }
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        if (pagesize < 0) {
            return mrp_arch_articoliDAO.searchByPars(pars);
        } else {
            return mrp_arch_articoliDAO.searchByPars(pars, pageNumber, pagesize);
        }

    }

    public List<Mrp_arch_articoli_ul> searchArticoliUlByPars(Map pars, int pageNumber, int pagesize) {

        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // } else {
        // pars.put("cdclas_aList", DEFAULT_CDCLAS_A); //filtro solo articoli listino
        // }
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        if (pagesize < 0) {
            return mrp_arch_articoli_ulDAO.searchByPars(pars);
        } else {
            return mrp_arch_articoli_ulDAO.searchByPars(pars, pageNumber, pagesize);
        }

    }

    public Mrp_arch_stato getMrp_arch_statoByKey(String cd) {
        return mrp_arch_statoDAO.selectByPrimaryKey(cd);
    }

    public List<Mrp_arch_stato> getAvailableStates() {

        Mrp_arch_statoExample ex = new Mrp_arch_statoExample();

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            ex.createCriteria().andVist_fgrgbEqualTo("S");
        } else {
            ex.createCriteria().andVist_fgrgbEqualTo("S").andCdstatoNotEqualTo("ZEE");
        }

        ex.setOrderByClause("dsstato");

        return mrp_arch_statoDAO.selectByExample(ex);
    }

    public List<Mrp_arch_stato> selectDistinctByPars(Map pars) {

        log.debug("pars filtro per gli stati" + pars);
        log.debug(SecurityContextHolder.getContext().getAuthentication());
        log.debug(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        // if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
        // ShopUser user = (ShopUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli
        // listino
        // } else {
        // pars.put("cdclas_aList", DEFAULT_CDCLAS_A); //filtro solo articoli listino
        // }
        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            pars.put("cdstato_escl_List", Collections.singletonList("ZEE"));
        }
        pars.put("cdrepa_escl_List", cdrepa_escl_List);
        pars.put("fgweb", "S");

        return mrp_arch_statoDAO.selectDistinctByPars(pars);

    }

    public int countOfferta(Map pars) {
        return mrp_arch_articoliDAO.countOfferta(pars);
    }

    public Vist_offerte getVist_offerteByCdarti(String cdarti) {

        Vist_offerteExample ex = new Vist_offerteExample();

        ex.createCriteria().andCdartiEqualTo(cdarti);

        List<Vist_offerte> itemList = vist_offerteDAO.selectByExample(ex);

        if (!itemList.isEmpty() && itemList.size() == 1) {
            return itemList.get(0);
        } else {
            return null;
        }
    }

    public List<Cliente> findResellers(Map pars) {

        return archclieDAO.findResellers(pars);
    }

    public List<Cliente> findResellersByUniloc(Map pars) {

        return archclieDAO.findResellersByUniloc(pars);
    }

    public Ep_utente getClieUser(String tkclie) {

        Ep_utenteExample ex = new Ep_utenteExample();
        ex.createCriteria().andTkclieEqualTo(tkclie);

        List<Ep_utente> itemList = ep_utenteDAO.selectByExample(ex);

        if (!itemList.isEmpty() && itemList.size() == 1) {
            return itemList.get(0);
        } else {
            return null;
        }
    }

    public Ep_costanti getEpCostanti(String costvalue) {

        Ep_costantiExample ex = new Ep_costantiExample();
        ex.createCriteria().andCostnameEqualTo(costvalue);

        List<Ep_costanti> itemList = ep_costantiDAO.selectByExample(ex);

        if (!itemList.isEmpty() && itemList.size() == 1) {
            return itemList.get(0);
        } else {
            return null;
        }
    }

    public Map getCondizioniVendita(Cliente cliente, Long tkutente) {

        Map condcMap = new HashMap();
        String[] tipoNota;

        Calendar dtulag_condc_default = Calendar.getInstance();
        dtulag_condc_default.set(Calendar.YEAR, 2018);
        dtulag_condc_default.set(Calendar.MONTH, Calendar.NOVEMBER);
        dtulag_condc_default.set(Calendar.DAY_OF_MONTH, 26);
        dtulag_condc_default.set(Calendar.HOUR_OF_DAY, 0);
        dtulag_condc_default.set(Calendar.MINUTE, 0);
        dtulag_condc_default.set(Calendar.SECOND, 0);
        dtulag_condc_default.set(Calendar.MILLISECOND, 0);

        Archrubrica_tipoExample archrubrica_tipoExample = new Archrubrica_tipoExample();

        archrubrica_tipoExample.createCriteria().andCdrubr_tipo_mEqualTo("CLIEGEFF");

        List<Archrubrica_tipo> archrubrica_tipos = archrubrica_tipoDAO.selectByExample(archrubrica_tipoExample);

        if (!archrubrica_tipos.isEmpty() && archrubrica_tipos.size() == 1) {
            Archrubrica_tipo archRubrTipo = archrubrica_tipos.get(0);

            ArchrubricaExample archrubricaExample = new ArchrubricaExample();
            archrubricaExample.createCriteria().andCdenteEqualTo(cliente.getArchclie().getCdente())
                    .andTkrubr_tipoEqualTo(archRubrTipo.getTkrubr_tipo()).andDtannullatoIsNull();
            List<Archrubrica> archrubricas = archrubricaDAO.selectByExample(archrubricaExample);

            if (!archrubricas.isEmpty() && archrubricas.size() == 1) {

                Archrubrica archrubrica = archrubricas.get(0);

                condcMap.put("condcTableValue", archrubrica);

                tipoNota = new String[] { "GENERALI" };

                for (String tipo : tipoNota) {

                    Archrubrica_condc_noteExample archrubrica_condc_noteExample = new Archrubrica_condc_noteExample();
                    archrubrica_condc_noteExample.createCriteria().andTkrubrEqualTo(archrubrica.getTkrubr())
                            .andTpnotaEqualTo(tipo);
                    archrubrica_condc_noteExample.setOrderByClause("nrposi, tkrubr_condc_n");

                    if (archrubrica_condc_noteDAO.countByExample(archrubrica_condc_noteExample) > 0) {
                        condcMap.put(tipo, archrubrica_condc_noteDAO.selectByExample(archrubrica_condc_noteExample));
                    }

                }

                if (!condcMap.isEmpty()) {
                    condcMap.put("type", "assigned");

                    Date dtulagCondc = archrubrica.getDtulag_condc();
                    if (dtulagCondc == null) {
                        dtulagCondc = dtulag_condc_default.getTime();
                    }
                    condcMap.put("dtulag_condc", dtulagCondc);
                    Vist_condc_acc lastCondcAcc = vist_condc_accDAO.getLastCondcAcc(tkutente);

                    if ((lastCondcAcc != null && dtulagCondc == null)
                            || (dtulagCondc != null && lastCondcAcc != null && lastCondcAcc.getDtaccettazione() != null
                                    && lastCondcAcc.getDtaccettazione().compareTo(dtulagCondc) > 0)) {
                        condcMap.put("accepted", true);
                        condcMap.put("lastCondcAcc", lastCondcAcc);
                    } else {
                        condcMap.put("accepted", false);
                    }

                    /*
                     * //condizioni commerciali ClieattiKey clieattiKey = new ClieattiKey();
                     * clieattiKey.setCdatti(archrubrica.getCdatti());
                     * clieattiKey.setCdazie(archrubrica.getCdazie()); Clieatti clieatti =
                     * clieattiDAO.selectByPrimaryKey(clieattiKey); if
                     * (StringUtils.isNotBlank(clieatti.getCdgrpatti())) {
                     * Vist_condc_grpatti_parExample vist_condc_grpatti_parExample = new
                     * Vist_condc_grpatti_parExample();
                     * vist_condc_grpatti_parExample.createCriteria().andCdgrpattiEqualTo(clieatti.
                     * getCdgrpatti()); List<Vist_condc_grpatti_par> vist_condc_grpatti_pars =
                     * vist_condc_grpatti_parDAO.selectByExample(vist_condc_grpatti_parExample);
                     * 
                     * LinkedHashMap<String, Vist_condc_grpatti_par> condcTable =
                     * getCondcTable(vist_condc_grpatti_pars);
                     * 
                     * condcMap.put("condcTable", condcTable); condcMap.put("condcTableValue",
                     * archrubrica); }
                     */

                }
                /*
                 * 
                 * if (condcMap.isEmpty()) {
                 * 
                 * //condizioni di default //recupero cdgrpatti ClieattiExample clieattiExample
                 * = new ClieattiExample();
                 * clieattiExample.createCriteria().andCdattiEqualTo(cliente.getArchclie().
                 * getCdatti()); List<Clieatti> clieattis =
                 * clieattiDAO.selectByExample(clieattiExample);
                 * 
                 * if (!clieattis.isEmpty() && clieattis.size() == 1) {
                 * 
                 * Clieatti clieatti = clieattis.get(0);
                 * 
                 * if (StringUtils.isNotBlank(clieatti.getCdgrpatti())) {
                 * 
                 * tipoNota = new String[]{"SERV", "CONDL"};
                 * 
                 * for (String tipo : tipoNota) { Vist_condc_grpatti_noteExample
                 * vist_condc_grpatti_noteExample = new Vist_condc_grpatti_noteExample();
                 * vist_condc_grpatti_noteExample.createCriteria().andCdgrpattiEqualTo(clieatti.
                 * getCdgrpatti()) .andTpnotaEqualTo(tipo);
                 * vist_condc_grpatti_noteExample.setOrderByClause("nrposi,tkcondc_grpa_n");
                 * 
                 * if (vist_condc_grpatti_noteDAO.countByExample(vist_condc_grpatti_noteExample)
                 * > 0) { condcMap.put(tipo,
                 * vist_condc_grpatti_noteDAO.selectByExample(vist_condc_grpatti_noteExample));
                 * }
                 * 
                 * }
                 * 
                 * if (!condcMap.isEmpty()) { condcMap.put("type", "default_cat");
                 * 
                 * Cliegrpatti cliegrpatti =
                 * cliegrpattiDAO.selectByPrimaryKey(clieatti.getCdgrpatti()); Date dtulagCondc
                 * = (cliegrpatti != null) ? cliegrpatti.getDtulag_condc() : null;
                 * 
                 * condcMap.put("dtulag_condc", dtulagCondc);
                 * 
                 * Vist_condc_acc lastCondcAcc = vist_condc_accDAO.getLastCondcAcc(tkutente);
                 * 
                 * if ((lastCondcAcc != null && dtulagCondc == null) || (dtulagCondc != null &&
                 * lastCondcAcc != null && lastCondcAcc.getDtaccettazione() != null &&
                 * lastCondcAcc.getDtaccettazione().compareTo(dtulagCondc) > 0)) {
                 * condcMap.put("accepted", true); condcMap.put("lastCondcAcc", lastCondcAcc); }
                 * else { condcMap.put("accepted", false); }
                 * 
                 * //condizioni commerciali Vist_condc_grpatti_parExample
                 * vist_condc_grpatti_parExample = new Vist_condc_grpatti_parExample();
                 * vist_condc_grpatti_parExample.createCriteria().andCdgrpattiEqualTo(clieatti.
                 * getCdgrpatti()); List<Vist_condc_grpatti_par> vist_condc_grpatti_pars =
                 * vist_condc_grpatti_parDAO.selectByExample(vist_condc_grpatti_parExample);
                 * 
                 * LinkedHashMap<String, Vist_condc_grpatti_par> condcTable =
                 * getCondcTable(vist_condc_grpatti_pars);
                 * 
                 * condcMap.put("condcTable", condcTable); condcMap.put("condcTableValue",
                 * archrubrica);
                 * 
                 * }
                 * 
                 * } else { //per nazioni ref Map pars = new HashMap(); pars.put("tkclie",
                 * cliente.getArchclie().getTkclie()); pars.put("cdazie",
                 * cliente.getArchclie().getCdazie()); pars.put("fseleg", "S");
                 * pars.put("dtfval", new Date()); List<Unitalocale> unilocs =
                 * getUnitalocali(pars);
                 * 
                 * if (!unilocs.isEmpty() && unilocs.size() == 1) {
                 * 
                 * Unitalocale sedeLegale = unilocs.get(0);
                 * 
                 * if (sedeLegale.getUnitalocali() != null &&
                 * StringUtils.isNotBlank(sedeLegale.getUnitalocali().getCdnazi())) {
                 * Vist_nazioni_refExample vist_nazioni_refExample = new
                 * Vist_nazioni_refExample();
                 * vist_nazioni_refExample.createCriteria().andCdnaziEqualTo(sedeLegale.
                 * getUnitalocali().getCdnazi()) .andCdruoloEqualTo("GRPATTI"); //aggiungere
                 * parametro ruolo per la select in base a indicazioni che darà Andrea
                 * List<Vist_nazioni_ref> nazionis =
                 * vist_nazioni_refDAO.selectByExample(vist_nazioni_refExample);
                 * 
                 * if (!nazionis.isEmpty()) {
                 * 
                 * Vist_nazioni_ref nazione = nazionis.get(0);
                 * 
                 * if (StringUtils.isNotBlank(nazione.getCdgrpatti())) {
                 * 
                 * tipoNota = new String[]{"SERV", "CONDL"};
                 * 
                 * for (String tipo : tipoNota) { Vist_condc_grpatti_noteExample
                 * vist_condc_grpatti_noteExample = new Vist_condc_grpatti_noteExample();
                 * vist_condc_grpatti_noteExample.createCriteria().andCdgrpattiEqualTo(nazione.
                 * getCdgrpatti()) .andTpnotaEqualTo(tipo);
                 * vist_condc_grpatti_noteExample.setOrderByClause("nrposi,tkcondc_grpa_n");
                 * 
                 * if (vist_condc_grpatti_noteDAO.countByExample(vist_condc_grpatti_noteExample)
                 * > 0) { condcMap.put(tipo,
                 * vist_condc_grpatti_noteDAO.selectByExample(vist_condc_grpatti_noteExample));
                 * }
                 * 
                 * }
                 * 
                 * if (!condcMap.isEmpty()) { condcMap.put("type", "default_cat_naz");
                 * 
                 * Cliegrpatti cliegrpatti =
                 * cliegrpattiDAO.selectByPrimaryKey(nazione.getCdgrpatti()); Date dtulagCondc =
                 * (cliegrpatti != null) ? cliegrpatti.getDtulag_condc() : null;
                 * 
                 * condcMap.put("dtulag_condc", dtulagCondc);
                 * 
                 * Vist_condc_acc lastCondcAcc = vist_condc_accDAO.getLastCondcAcc(tkutente);
                 * 
                 * if ((lastCondcAcc != null && dtulagCondc == null) || (dtulagCondc != null &&
                 * lastCondcAcc != null && lastCondcAcc.getDtaccettazione() != null &&
                 * lastCondcAcc.getDtaccettazione().compareTo(dtulagCondc) > 0)) {
                 * condcMap.put("accepted", true); condcMap.put("lastCondcAcc", lastCondcAcc); }
                 * else { condcMap.put("accepted", false); }
                 * 
                 * Vist_condc_grpatti_parExample vist_condc_grpatti_parExample = new
                 * Vist_condc_grpatti_parExample();
                 * vist_condc_grpatti_parExample.createCriteria().andCdgrpattiEqualTo(nazione.
                 * getCdgrpatti()); List<Vist_condc_grpatti_par> vist_condc_grpatti_pars =
                 * vist_condc_grpatti_parDAO.selectByExample(vist_condc_grpatti_parExample);
                 * 
                 * LinkedHashMap<String, Vist_condc_grpatti_par> condcTable =
                 * getCondcTable(vist_condc_grpatti_pars);
                 * 
                 * condcMap.put("condcTable", condcTable); condcMap.put("condcTableValue",
                 * archrubrica);
                 * 
                 * }
                 * 
                 * }
                 * 
                 * } }
                 * 
                 * }
                 * 
                 * }
                 * 
                 * }
                 * 
                 * }
                 */
            }
        }

        return condcMap;

    }

    private LinkedHashMap<String, Vist_condc_grpatti_par> getCondcTable(
            List<Vist_condc_grpatti_par> vist_condc_grpatti_pars) {
        LinkedHashMap<String, Vist_condc_grpatti_par> condcTable = new LinkedHashMap<String, Vist_condc_grpatti_par>();
        for (Vist_condc_grpatti_par vist_condc_grpatti_par : vist_condc_grpatti_pars) {

            String nomeCampo = vist_condc_grpatti_par.getCampo();
            if ("fido".equals(nomeCampo)) {
                nomeCampo = "fidorich";
            }

            condcTable.put(nomeCampo, vist_condc_grpatti_par);

        }
        return condcTable;
    }

    public Vist_articoli_datiextra getDatiExtraByCdartm(String cdartm) {

        Vist_articoli_datiextraExample ex = new Vist_articoli_datiextraExample();

        ex.createCriteria().andArwCodiceArtEqualTo(cdartm);

        List<Vist_articoli_datiextra> artList = vist_articoli_datiextraDAO.selectByExample(ex);

        if (!artList.isEmpty() && artList.size() == 1) {
            return artList.get(0);
        } else {
            return null;
        }

    }

    public String getCertImageName(String field) {

        if (certImageMap == null) {
            certImageMap = new HashMap<String, String>();
            for (int i = 0; i < certImageValue.length; i++) {
                certImageMap.put(certImageValue[i][0], certImageValue[i][1]);
            }
        }
        return certImageMap.get(field);

    }

    public List<Ep_posts> getTechNews(Map pars) {
        return ep_postsDAO.getTechNews(pars);
    }

    public List<Vist_tipi> getTipiAlt(String cdarti, String cdvisttp) {

        Map pars = new HashMap();
        pars.put("cdarti", cdarti);
        pars.put("cdvisttp", cdvisttp);

        return vist_tipiDAO.getTipiAlt(pars);
    }

    public Map getDatiExtraLampMap(Vist_articoli_datiextra datiExtra, WebApplicationContext ctx) {

        HashMap<String, String> tipoLampadine = new HashMap<String, String>();
        tipoLampadine.put("A", "ALO");
        tipoLampadine.put("F", "FL");
        tipoLampadine.put("L", "LED");
        tipoLampadine.put("E", "ES");
        tipoLampadine.put("I", "IOD");

        String rootRes = "/images/articoli/specsheetres/";
        String pathimg = rootRes + "dati/";
        // path immagini lampadine
        String pathlampimg = rootRes + "lampadine/";

        Map elettDatiExtra = new HashMap();
        ArrayList<Map> lampadine = new ArrayList<Map>();

        // lampadina principale
        int[] idxs = { 1, 2, 7, 8 };
        for (int pos : idxs) {

            Map lamp = new HashMap();

            try {
                String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + pos);

                flag = StringUtils.trimToEmpty(flag);

                if (StringUtils.equals(flag, "S")) {

                    String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + pos);
                    if (nomeimg != null) {
                        String[] split = nomeimg.split("\\\\");
                        String acronimoLampada = (tipoLampadine.get(split[0]) != null ? tipoLampadine.get(split[0])
                                : split[0]) + ".jpg";
                        nomeimg = split[1];

                        String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(),
                                pathimg + acronimoLampada);
                        File fAcronimo = new File(realPathAcronimo);

                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
                        File f = new File(realPath);

                        if (fAcronimo.exists()) {
                            lamp.put("acro", pathimg + acronimoLampada);
                        }
                        if (f.exists()) {
                            lamp.put("img", pathlampimg + nomeimg);
                        }
                    }

                    // descrizione
                    // TODO sostituirlo con l'icona della lampadina quando le passeranno
                    String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + pos);
                    String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + pos);
                    String tipoAttacco = BeanUtils.getSimpleProperty(datiExtra, "arwTipoAttacco" + pos);
                    // String voltaggio = BeanUtils.getSimpleProperty(datiExtra, "arwVoltaggio" +
                    // i);

                    String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(StringUtils.upperCase(potenza))
                            + " " + StringUtils.trimToEmpty(tipoAttacco);
                    descrizione = StringUtils.trim(descrizione);

                    if (StringUtils.isNotBlank(descrizione)) {
                        lamp.put("descr", descrizione);
                    }

                    lampadine.add(lamp);
                }
            } catch (FileNotFoundException ex) {
                // log.debug("not exists");
            } catch (IllegalAccessException ex) {
                log.error(ex);
            } catch (InvocationTargetException ex) {
                log.error(ex);
            } catch (NoSuchMethodException ex) {
                log.error(ex);
            }
        }

        if (!lampadine.isEmpty()) {

            elettDatiExtra.put("main", (ArrayList<Map>) lampadine.clone());

            int[] altIdxs = { 3, 4, 9, 10, 5, 6, 11, 12 };

            int counter = 1;
            boolean alt = false;
            boolean idxPresent = false;

            lampadine.clear();

            for (int bidx : altIdxs) {

                if (bidx == 5) {
                    counter = 2;
                }

                Map lamp = new HashMap();

                try {
                    String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + bidx);

                    flag = StringUtils.trimToEmpty(flag);

                    if (StringUtils.equals(flag, "S")) {

                        String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + bidx);
                        if (nomeimg != null) {
                            String[] split = nomeimg.split("\\\\");
                            String acronimoLampada = tipoLampadine.get(split[0]) + ".jpg";
                            nomeimg = split[1];

                            String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(),
                                    pathimg + acronimoLampada);
                            File fAcronimo = new File(realPathAcronimo);

                            String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
                            File f = new File(realPath);

                            if (fAcronimo.exists()) {
                                lamp.put("acro", pathimg + acronimoLampada);
                            }

                            if (f.exists()) {
                                lamp.put("img", pathlampimg + nomeimg);
                            }
                        }

                        // descrizione
                        String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + bidx);
                        String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + bidx);
                        String tipoAttacco = BeanUtils.getSimpleProperty(datiExtra, "arwTipoAttacco" + bidx);
                        String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x"
                                + StringUtils.trimToEmpty(StringUtils.upperCase(potenza)) + " " + StringUtils.trimToEmpty(tipoAttacco);
                        descrizione = StringUtils.trim(descrizione);

                        if (StringUtils.isNotBlank(descrizione)) {
                            lamp.put("descr", descrizione);
                        }

                        lampadine.add(lamp);

                        if (!lampadine.isEmpty()) {

                            elettDatiExtra.put("alt" + counter, (ArrayList<Map>) lampadine.clone());
                            lampadine.clear();
                        }

                    }
                } catch (FileNotFoundException ex) {
                    // log.debug("not exists");
                } catch (IllegalAccessException ex) {
                    log.error(ex);
                } catch (InvocationTargetException ex) {
                    log.error(ex);
                } catch (NoSuchMethodException ex) {
                    log.error(ex);
                }

            }

        }

        return elettDatiExtra;

    }

    public void addCdclasFilter(Map pars, HttpServletRequest request) {

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            pars.put("cdclas_aList", user.getCdclas_aFilter()); // filtro solo articoli listino

        } else if (GeoIPInterceptor.getCountry(request).equals("US")) {
            pars.put("cdclas_aList", VistosiShopManager.DEFAULT_CDCLAS_A_EUUS); // filtro eu + us
        } else {
            pars.put("cdclas_aList", VistosiShopManager.DEFAULT_CDCLAS_A); // filtro solo articoli eu
        }
    }

    public void addToggleStateZEEFilter(Map pars, HttpServletRequest request) {

        Mrp_arch_stato statoZEE = getMrp_arch_statoByKey("ZEE");
        String cookieNameZEE = "toggle-stato_" + statoZEE.getCdstato();

        Cookie ckViewZEE = WebUtils.getCookie(request, cookieNameZEE);
        if (ckViewZEE != null && "S".equals(ckViewZEE.getValue())) {
            if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                pars.put("statiEscludedList", Collections.singletonList(statoZEE.getCdstato()));
                pars.put("cdstato_escl_List", Collections.singletonList(statoZEE.getCdstato()));
            }
        } else {
            pars.put("statiEscludedList", Collections.singletonList(statoZEE.getCdstato()));
            pars.put("cdstato_escl_List", Collections.singletonList(statoZEE.getCdstato()));
        }
    }

    public void addCdrepaFilter(Map pars, HttpServletRequest request) {

        pars.put("cdrepa_escl_List", Arrays.asList(cdrepa_escl_List));
    }

    public boolean checkSpecsheetExists(Mrp_arch_articoli art, WebApplicationContext ctx, RequestContext rc) {

        String[] noFallback = new String[] { "it", "en" };
        List<String> noFallbackList = Arrays.asList(noFallback);
        File fXlsxFallback = null;

        try {

            List<String> possibleFilenameList = new ArrayList<String>();
            String filename = art.getVist_filedis();
            // possibleFilenameList.add(filename);

            if ("UL".equals(art.getCdclas_a()) || "ULL".equals(art.getCdclas_a()) || "LOU".equals(art.getCdclas_a())) {

                // possibleFilenameList.add(0, filename + "UL");
                possibleFilenameList.add(0, filename + art.getCdvistelet() + "UL");
            } else {
                filename += art.getCdvistelet();
                possibleFilenameList.add(0, filename);
            }

            for (String file : possibleFilenameList) {

                String realPathPdf = WebUtils.getRealPath(ctx.getServletContext(),
                        SpecSheet.ROOT_RES + "/risorse/" + file + ".pdf");
                String realPathU3D = WebUtils.getRealPath(ctx.getServletContext(),
                        SpecSheet.ROOT_RES + "/risorse/" + file + ".U3D");
                String realPathXlsx = WebUtils.getRealPath(ctx.getServletContext(),
                        SpecSheet.ROOT_RES + "/risorse/" + file + "_" + rc.getLocale().getLanguage() + ".xlsx");
                if (!noFallbackList.contains(rc.getLocale().getLanguage())) {
                    String realPathXlsxFallback = WebUtils.getRealPath(ctx.getServletContext(),
                            SpecSheet.ROOT_RES + "/risorse/" + file + "_" + "en" + ".xlsx");
                    fXlsxFallback = new File(realPathXlsxFallback);
                }

                File fPdf = new File(realPathPdf);
                File fU3D = new File(realPathU3D);
                File fXlsx = new File(realPathXlsx);

                if (fPdf.exists() /* && fU3D.exists() */ && (fXlsx.exists()
                        || (fXlsxFallback != null && fXlsxFallback.exists()))) {
                    return true;
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(VistosiShopManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean checkSpecsheetModelExists(Mrp_arch_articoli art, WebApplicationContext ctx, RequestContext rc) {

        try {
            String realPathPdf = WebUtils.getRealPath(ctx.getServletContext(),
                    "/images/articoli/disegnitecnici/scheda_prodotto/" + art.getVist_filedis() + ".pdf");

            File fPdf = new File(realPathPdf);

            if (fPdf.exists()) {
                return true;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(VistosiShopManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
