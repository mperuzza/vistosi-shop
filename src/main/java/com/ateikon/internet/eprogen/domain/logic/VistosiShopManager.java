/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.logic;

import com.ateikon.internet.eprogen.domain.Vist_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.*;
import com.ateikon.internet.generic.domain.DTPaginatedList;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;

/**
 *
 * @author emiliano
 */
public interface VistosiShopManager extends BaseManager{
    
    public static List<String> DEFAULT_CDCLAS_A = Arrays.asList(new String[]{"L", "LO", "LL"});
    public static List<String> DEFAULT_CDCLAS_A_EUUS = Arrays.asList(new String[]{"L", "LO", "LL", "UL", "ULL"});
    public static List<String> DEFAULT_CDCLAS_A_US = Arrays.asList(new String[]{"UL", "ULL"});
    
    List<Vist_tipi> getVist_tipi();
    List<Vist_tipi> findVist_tipi(Map pars);
    Vist_tipi getVist_tipiByKey(String cdvisttp);
    List<Vist_cp_collezioni> getVist_cp_collezioni();
    Vist_cp_collezioni getVist_cp_collezioniByKey(String cdvistccol);
    List<Vist_famiglia> getVist_famiglia(Map pars);
    Vist_famiglia getVist_famigliaByKey(String cd);
    List<Vist_famiglia> findVist_famiglia(Map pars);
    List<Vist_elettrificazioni> getVist_elettrificazioni();
    List<Vist_colori_vetro> getVist_colori_vetro();
    Vist_colori_vetro getVist_colori_vetroByKey(String cd);
    List<Vist_colori_vetro> findVist_colori_vetro(Map pars);
    List<Vist_finit_mont> getVist_finit_mont();
    Vist_finit_mont getVist_finit_montByKey(String cd);
    List<Vist_finit_mont> findVist_finit_mont(Map pars);
    List<Vist_finit_vetro> findVist_finit_vetro(Map pars);
    List<Vist_cp_collezioni> findVist_cp_collezioni(Map pars);

    List<Vist_var1> findVist_var1(Map pars);
    List<Vist_var2> findVist_var2(Map pars);
    List<Vist_var3> findVist_var3(Map pars);
    List<Vist_elettrificazioni> findVist_elettrificazioni(Map pars);
    
    Vist_elettrificazioni getVist_elettrificazioniByKey(String cd);

    DTPaginatedList selectArticoliByExamplePag(Map pars, String orderByClause, String direction, int pageNumber, int pageSize);
    DTPaginatedList selectFamiglieByExamplePag(Map pars, String orderByClause, String direction, int pageNumber, int pageSize);
    DTPaginatedList selectTipiByExamplePag(Map pars, String orderByClause, String direction, int pageNumber, int pageSize);

    DTPaginatedList selectArticoliUlByExamplePag(Map pars, String orderByClause, String direction, int pageNumber, int pageSize);
    
    Mrp_arch_articoli getMrp_arch_articoliByKey(String cd);
    List<Mrp_arch_articoli> selectMrp_arch_articoliByPars(Map pars);
    List<Mrp_arch_articoli> getModelli(Map pars);

    Cliente getCliente(String tkclie, String cdazie);
    List<Cliente> getClienteByCdagen(String cdagen, String cdazie);
    Lis_listino getListinoByKey(String cdlist);

    CarrelloItemForm getCarrello(Long tkordi);
    void saveCarrelloNote(CarrelloItemForm obj);
    void saveCarrelloTestNote(CarrelloItemForm obj);

    Mrp_file_giacenza getMrp_file_giacenzaByKey(String cdarti, String cdvar, Long tkmaga);
    List<Vist_etichette> getEtichette(String cdartm);

    List<Mrp_arch_articoli> getRicambi(String cdfinito);

    List<String> getCdclas_aByTkclie(Archclie clie);
    

    List<Cms_promozioni> getPromoByTkclie(String tkclie);

    DTPaginatedList selectCliePag(Map pars, String orderByClause, String direction, int pageNumber, int pageSize);

    Boolean isRicambio(String cdarti_ric);

    List<Vist_filtro_articoli> getCdclas_aByCdlist(String cdlist);

    Vist_finit_vetro getVist_finit_vetroByKey(String cd);

    List<Vist_vetro> findVist_vetro(Map pars);

    Vist_var1 getVist_var1ByKey(String cdvistv1);

    Vist_var2 getVist_var2ByKey(String cdvistv2);

    Vist_var3 getVist_var3ByKey(String cdvistv3);

    List<Unitalocale> getUnitalocali(Map pars);

    Vist_elettrificazioni getVist_elettrificazioniAlternative(String cd);

    Mrp_arch_articoli getRicambio(String cdarti, String cdarti_ric);

    Mrp_arch_articoli getRicambio(String cdarti_ric);

    Vist_articoli_ricambi getDatiRicambio(String cdarti, String cdarti_ric);

    List<Cms_promozioni> getActivePromo(String tkclie, String cdagen);

    List<Designer> getDesigner();

    DesignerWithBLOBs getDesigner(String cddesigner);

    String getSchedaTec(String cdvisttp, String cdvistfam, String cdvistv1, String cdvistv2, String cdvistv3, String cdvistelet);

    Mrp_arch_articoli getArticoloByCdartm(String cdartm);

    List<Mrp_arch_articoli> searchArticoliByPars(Map pars);

    List<Mrp_arch_articoli> searchArticoliByPars(Map pars, int pageNumber, int pagesize);

    DTPaginatedList selectCollezioniByExamplePag(Map pars, String orderByClause, String direction, int pageNumber, int pageSize);

    List<Mrp_arch_stato> getAvailableStates();

    List<Mrp_arch_stato> selectDistinctByPars(Map pars);

    List<Mrp_arch_articoli> getModelliDis(Map pars);

    List<Mrp_arch_articoli_ul> searchArticoliUlByPars(Map pars, int pageNumber, int pagesize);

    int countOfferta(Map pars);

    Vist_offerte getVist_offerteByCdarti(String cdarti);
    
    List<Cliente> findResellers(Map pars);
    
    List<Cliente> findResellersByUniloc(Map pars);

    Map getCondizioniVendita(Cliente cliente, Long tkutente);

    void saveCarrello(CarrelloItemForm cart) throws IllegalAccessException, InvocationTargetException;

    void saveCarrelloCondition(CarrelloItemForm cart) throws IllegalAccessException, InvocationTargetException;

    Ep_utente getClieUser(String tkclie);

    Ep_costanti getEpCostanti(String costvalue);

    Vist_articoli_datiextra getDatiExtraByCdartm(String cdartm);

    String getCertImageName(String field);

    List<Ep_posts> getTechNews(Map pars);

    List<Vist_tipi> getTipiAlt(String cdarti, String cdvisttp);

    Map getDatiExtraLampMap(Vist_articoli_datiextra datiExtra, WebApplicationContext ctx);

    void addCdclasFilter(Map pars, HttpServletRequest request);
    
    void addCdrepaFilter(Map pars, HttpServletRequest request);

    boolean checkSpecsheetExists(Mrp_arch_articoli art, WebApplicationContext ctx, RequestContext rc);

}
