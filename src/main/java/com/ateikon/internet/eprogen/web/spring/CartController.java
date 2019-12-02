/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.common.Atk_sql;
import static com.ateikon.common.Atk_sql.of_trasformaURL;
import com.ateikon.function.F_ordven;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.*;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
@Controller
@SessionAttributes("cart")
public class CartController extends BaseAnnotationController {

    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }

    private CarrelloItemForm getBaseCart(HttpServletRequest request) {

        ShopUser user = null;

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        //Cliente cliente = vistosiShopManager.getCliente(user.getUserDB().getTkclie(), user.getUserDB().getCdazie());
        long tkordi = 0;

        Object s_tkordi = WebUtils.getSessionAttribute(request, "tkordi");
        if (s_tkordi != null) {
            tkordi = (Long) s_tkordi;
            return vistosiShopManager.getCarrello(tkordi);
        }

        Atk_sql atk_sql = new Atk_sql();

        try {

            atk_sql.dbConnection();

            atk_sql.profil = (user != null) ? user.getUserDB().getProfil() : "PGMR";
            atk_sql.cdazie = (user != null) ? user.getUserDB().getCdazie() : "01";
            atk_sql.cddipa = (user != null) ? user.getUserDB().getCddipa() : "0000";

            F_ordven f_ordven = new F_ordven();
            atk_sql.setProfilo((Atk_sql) f_ordven);

            String cdstato = "PR";
            if (AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")) {
                cdstato = "PN";
            }
            tkordi = f_ordven.getCarrello(user.getUserDB().getTkutente(), user.getUserDB().getTkclie(), cdstato);

            if (tkordi > 0) { //esiste un ordine provvisorio

                return vistosiShopManager.getCarrello(tkordi);

            }

        } catch (Exception ex_page) {
            //log.error(ex_page);
            try {
                atk_sql.m_connection.rollback();
            } catch (Exception ex_page_sql) {

                log.error(ex_page_sql);

            }
        } finally {

            try {
                atk_sql.m_connection.commit();
                atk_sql.closeConnection();
            } catch (Exception _e) {
                log.error(_e.getMessage());
            }

        }

        return new CarrelloItemForm();

    }

    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String forceCart(@RequestParam(value = "tkordi", required = false) Long tkordi,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {

        request.getSession().removeAttribute("tkordi");

        ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long tkutente = user.getUserDB().getTkutente();

        if (tkordi != null) {

            CarrelloItemForm cart = vistosiShopManager.getCarrello(tkordi);

            log.debug(cart.getOrd_test().getCdstato());
            log.debug(cart.getOrd_test().getTkutente());
            log.debug(tkutente);
            log.debug(cart != null);
            log.debug(cart.getOrd_test() != null);
            log.debug("PR".equals(cart.getOrd_test().getCdstato()));
            log.debug(cart.getOrd_test().getTkutente() == tkutente);
            if (cart != null && cart.getOrd_test() != null
                    && "PR".equals(cart.getOrd_test().getCdstato())
                    && tkutente.equals(cart.getOrd_test().getTkutente())) {

                request.getSession().setAttribute("tkordi", tkordi);

                return "redirect:/fullCart";
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "redirect:/404";
            }
        } else {
            return "redirect:/404";
        }

    }

    @RequestMapping(value = "/miniCart", method = RequestMethod.GET)
    public String getShortCart(HttpServletRequest request, Model model) {

        model.addAttribute("minicart", getBaseCart(request));

        return "miniCart";
    }

    private void referenceData(HttpServletRequest request, CarrelloItemForm cart, Model model, ShopUser user) {

        RequestContext rc = new RequestContext(request);

        long tkmaga = 1;

        if (cart.getOrd_posititos() != null) {
            for (Web_ord_positito ord_positito : cart.getOrd_posititos()) {

                Mrp_arch_articoli art = vistosiShopManager.getMrp_arch_articoliByKey(ord_positito.getCdarti());

                art.setVist_famiglia(vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam()));
                art.setVist_colori_vetro(vistosiShopManager.getVist_colori_vetroByKey(art.getCdvistcolv()));
                art.setVist_finit_mont(vistosiShopManager.getVist_finit_montByKey(art.getCdvistfinm()));
                art.setVist_finit_vetro(vistosiShopManager.getVist_finit_vetroByKey(art.getCdvistfinv()));
                art.setVist_elettrificazioni(vistosiShopManager.getVist_elettrificazioniByKey(art.getCdvistelet()));
                if (art.getCdvistv1() != null) {
                    art.setVist_var1(vistosiShopManager.getVist_var1ByKey(art.getCdvistv1()));
                }
                if (art.getCdvistv2() != null) {
                    art.setVist_var2(vistosiShopManager.getVist_var2ByKey(art.getCdvistv2()));
                }
                if (art.getCdvistv3() != null) {
                    art.setVist_var3(vistosiShopManager.getVist_var3ByKey(art.getCdvistv3()));
                }
                art.setMrp_file_giacenza(vistosiShopManager.getMrp_file_giacenzaByKey(art.getCdarti(), "STD", tkmaga));

                if ("S".equals(art.getFgpromo())) {
                    Vist_offerte vist_offerte = vistosiShopManager.getVist_offerteByCdarti(art.getCdarti());
                    if (vist_offerte != null) {
                        art.setVist_offerte(vist_offerte);
                    }
                }

                if (art.getCdvisttp() != null) {
                    art.setVistTipiAlt(vistosiShopManager.getTipiAlt(art.getCdarti(), art.getCdvisttp()));
                }

                //ricerca file download
                if (art != null) {

                    WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

                    //ricerca modelli
                    Vist_famiglia vist_fam = vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam());

                    log.debug("filedis >>>" + art.getVist_filedis());

                    String path_modello = "fileresources/models";
                    String path_3D = path_modello + "/3D/";
                    //String nome_modello = vist_fam.getCdvistfam_mPad() + art.getCdvisttp() + art.getCdvistv1Pad() + art.getCdvistv2Pad() + art.getCdvistv3Pad() +"-";
                    String nome_modello = art.getVist_filedis();
                    //igs
                    //String igs = path_3D + nome_modello + ".zip";
//                    String igs = path_3D + nome_modello + (art.isLed() ? art.getCdvistelet() : "") + ".zip";
//                    try {
//                        String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), igs);
//                        File f = new File(path_to_filemodel);
//                        log.debug(path_to_filemodel);
//                        art.setModel3D_igs(igs);
//                        if (f.exists()) {
//                            art.setModel3D_igsExists(Boolean.TRUE);
//                        }
//                    } catch (FileNotFoundException ex) {
//                        log.error("file " + igs + " non trovato");
//                    }
                    //eprt
//                    String eprt = path_3D + nome_modello + ".EPRT";
//                    try {
//                        String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), eprt);
//                        File f = new File(path_to_filemodel);
//                        log.debug(path_to_filemodel);
//                        art.setModel3D_eprt(eprt);
//                        if (f.exists()) {
//                            art.setModel3D_eprtExists(Boolean.TRUE);
//                        }
//                    } catch (FileNotFoundException ex) {
//                        log.error("file " + eprt + " non trovato");
//                    }
                    //easm
                    //String easm = path_3D + nome_modello + ".EASM";
                    String easm = path_3D + nome_modello + (art.isLed() ? art.getCdvistelet() : "") + ".EASM";
                    try {
                        String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), easm);
                        File f = new File(path_to_filemodel);
                        log.debug(path_to_filemodel);
                        art.setModel3D_easm(easm);
                        if (f.exists()) {
                            art.setModel3D_easmExists(Boolean.TRUE);
                        }
                    } catch (FileNotFoundException ex) {
                        log.error("file " + easm + " non trovato");
                    }

                    String path_2D = path_modello + "/2D/";
                    //dwg cm
                    String dwg_vers = "cm/";
                    if ("en".equals(rc.getLocale().getLanguage())) {
                        dwg_vers = "po/";
                        art.setTiporisorsa2D_dwg(com.ateikon.common.Mrp_arch_articoli.MOD2D_DWG_PO);
                    }
                    String dwg = path_2D + dwg_vers + nome_modello + ".dwg";
                    try {
                        String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), dwg);
                        File f = new File(path_to_filemodel);
                        log.debug(path_to_filemodel);
                        art.setModel2D_dwg(dwg);
                        if (f.exists()) {
                            art.setModel2D_dwgExists(Boolean.TRUE);
                        }
                    } catch (FileNotFoundException ex) {
                        log.error("file " + dwg + " non trovato");
                    }

                    //techNews
                    Map techPars = new HashMap();
                    techPars.put("cdvistfam", art.getCdvistfam());
                    techPars.put("cdvisttp", art.getCdvisttp());
                    if (StringUtils.isNotEmpty(art.getCdvistelet())) {
                        techPars.put("cdvistelet", art.getCdvistelet());
                    }
                    techPars.put("postDate", new Date());

                    art.setTechNewsList(vistosiShopManager.getTechNews(techPars));

                    //file certificati
                    Vist_articoli_datiextra datiExtra = vistosiShopManager.getDatiExtraByCdartm(art.getCdartm());
                    String path_cert = "fileresources/cert/";

                    if (datiExtra != null) {

                        try {

                            String path_techsheet = "fileresources/assembling_instructions/";
                            String techsheet = datiExtra.getArwFileSchedaTec();
                            if (StringUtils.isNotEmpty(techsheet)) {
                                try {

                                    techsheet = StringUtils.substringAfterLast(techsheet, "\\");

                                    String path_to_techsheet = WebUtils.getRealPath(ctx.getServletContext(), path_techsheet + techsheet);
                                    File f = new File(path_to_techsheet);
                                    log.debug(path_to_techsheet);
                                    Vist_articoli_img vist_articoli_img = new Vist_articoli_img();
                                    vist_articoli_img.setPathschtec(techsheet);
                                    if (f.exists()) {
                                        vist_articoli_img.setPathschtecExists(Boolean.TRUE);
                                    }
                                    art.setVist_articoli_img(vist_articoli_img);
                                } catch (Exception ex) {
                                    log.error("file techsheet non trovato");
                                }
                            }

                            for (int i = 1; i < 5; i++) {

                                String fieldName = "arwCertificazione" + i;
                                String nomefile = BeanUtils.getSimpleProperty(datiExtra, fieldName);

                                if (StringUtils.isNotBlank(nomefile)) {
                                    String nomeimg = vistosiShopManager.getCertImageName(fieldName);

                                    if (StringUtils.contains(nomefile, "/")) {
                                        nomefile = StringUtils.substringAfterLast(nomefile, "/");
                                        //nomefile = nomefile + ".pdf";
                                    }
                                    if (StringUtils.contains(nomefile, "\\")) {
                                        nomefile = StringUtils.substringAfterLast(nomefile, "\\");
                                    }

                                    try {
                                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), path_cert + nomefile);

                                        File f = new File(realPath);
                                        if (f.exists()) {
                                            Map<String, String> certMap = new HashMap<String, String>();
                                            certMap.put("img", nomeimg);
                                            certMap.put("file", path_cert + nomefile);

                                            art.getCertList().addLast(certMap);
                                        } else {
                                            //TODO???
                                        }
                                    } catch (FileNotFoundException ex) {
                                        //log.debug("not exists");
                                    }
                                }
                            }

                            //file classe energetica
                            String fieldName = "arwCertificazione" + 10;
                            String nomefile = BeanUtils.getSimpleProperty(datiExtra, fieldName);

                            if (StringUtils.isNotBlank(nomefile)) {
                                art.setEnergyClass(nomefile);
                            }

                            art.setEletDatiExtraMap(vistosiShopManager.getDatiExtraLampMap(datiExtra, ctx));

                            //check esistenza specsheet
//                            String realPathPdf = WebUtils.getRealPath(ctx.getServletContext(), SpecSheet.ROOT_RES + "/risorse/" + nome_modello + ".pdf");
//                            String realPathU3D = WebUtils.getRealPath(ctx.getServletContext(), SpecSheet.ROOT_RES + "/risorse/" + nome_modello + ".U3D");
//                            String realPathXlsx = WebUtils.getRealPath(ctx.getServletContext(), SpecSheet.ROOT_RES + "/risorse/" + nome_modello + "_" + rc.getLocale().getLanguage() + ".xlsx");
//
//                            File fPdf = new File(realPathPdf);
//                            File fU3D = new File(realPathU3D);
//                            File fXlsx = new File(realPathXlsx);
//
//                            if(fPdf.exists() && fU3D.exists() && fXlsx.exists()){
                            if (vistosiShopManager.checkSpecsheetExists(art, ctx, rc)) {
                                art.setSpecsheetExists(true);
                            }
                            //check esistenza specsheet modello                
                            if (vistosiShopManager.checkSpecsheetModelExists(art, ctx, rc)) {
                                art.setSpecsheetModelExists(true);
                            }

                            //} catch (FileNotFoundException ex) {
                            //log.debug("not exists");
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }

                Map stpars = new HashMap();
                stpars.put("cdarti", art.getCdarti());

                stpars.put("fgpromo", "S");
                int countOff = vistosiShopManager.countOfferta(stpars);
                if (countOff > 0) {
                    if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                        Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                        art.setStato(statoOff);
                    }
                } else {
                    vistosiShopManager.addCdclasFilter(stpars, request);
                    List<Mrp_arch_stato> stlist = vistosiShopManager.selectDistinctByPars(stpars);

                    if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                        art.setStato(stlist.get(0));
                    }
                }

                ord_positito.setArticolo(art);

                if (StringUtils.isNotBlank(ord_positito.getCdartirif())) {

                    art.setDatiRicambio(vistosiShopManager.getDatiRicambio(ord_positito.getCdartirif(), ord_positito.getCdarti()));

                    Mrp_arch_articoli artrif = vistosiShopManager.getMrp_arch_articoliByKey(ord_positito.getCdartirif());

                    artrif.setVist_famiglia(vistosiShopManager.getVist_famigliaByKey(artrif.getCdvistfam()));
                    artrif.setVist_colori_vetro(vistosiShopManager.getVist_colori_vetroByKey(artrif.getCdvistcolv()));
                    artrif.setVist_finit_mont(vistosiShopManager.getVist_finit_montByKey(artrif.getCdvistfinm()));
                    artrif.setVist_finit_vetro(vistosiShopManager.getVist_finit_vetroByKey(art.getCdvistfinv()));
                    artrif.setVist_elettrificazioni(vistosiShopManager.getVist_elettrificazioniByKey(artrif.getCdvistelet()));

                    ord_positito.setArticolorif(artrif);
                }
            }
        }

        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);
        vistosiShopManager.addToggleStateZEEFilter(fpars, request);
        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));
        model.addAttribute("collezioni", vistosiShopManager.getVist_cp_collezioni());
        model.addAttribute("stati", vistosiShopManager.getAvailableStates());
        //model.addAttribute("promo", vistosiShopManager.getPromoByTkclie(user.getUserDB().getTkclie()));
        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {

            //tkutente da verificare 
            //  se utente realCustomer >> verifico il tkutente dello user attivo
            //  se !realCustomer devo verificare il tkutente del cliente associato all'utente (se presente)
            Long tkutente = user.getUserDB().getTkutente();
            if (!user.isRealCustomer()) {
                Ep_utente clieUser = vistosiShopManager.getClieUser(user.getCliente().getArchclie().getTkclie());
                if (clieUser != null) {
                    tkutente = clieUser.getTkutente();
                } else {
                    tkutente = -1l;
                }
            }

            Map condizioni = vistosiShopManager.getCondizioniVendita(user.getCliente(), tkutente);
            if (!condizioni.isEmpty()) {
                cart.setCondc(condizioni);
                cart.setCondcAccepted((Boolean) condizioni.get("accepted"));
            }

            model.addAttribute("promo", vistosiShopManager.getActivePromo(user.getUserDB().getTkclie(), user.getCliente().getArchclie().getCdagen()));
        }

    }

    @RequestMapping(value = "/fullCart", method = RequestMethod.GET)
    public String getFullCart(HttpServletRequest request,
            @RequestParam(value = "azione", required = false) String azione,
            @RequestParam(value = "tkposi_del", required = false) Long tkposi_del,
            Model model) {

        RequestContext rc = new RequestContext(request);

        ShopUser user = null;

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        //Cliente cliente = vistosiShopManager.getCliente(user.getUserDB().getTkclie(), user.getUserDB().getCdazie());
        if ("delete".equals(azione)) {

            if (tkposi_del != null) {

                Atk_sql atk_sql = new Atk_sql();

                try {

                    atk_sql.dbConnection();

                    atk_sql.profil = (user != null) ? user.getUserDB().getProfil() : "PGMR";
                    atk_sql.cdazie = (user != null) ? user.getUserDB().getCdazie() : "01";
                    atk_sql.cddipa = (user != null) ? user.getUserDB().getCddipa() : "0000";

                    F_ordven f_ordven = new F_ordven();
                    atk_sql.setProfilo((Atk_sql) f_ordven);

                    long tkordi = 0;

                    Object s_tkordi = WebUtils.getSessionAttribute(request, "tkordi");
                    if (s_tkordi != null) {
                        tkordi = (Long) s_tkordi;
                    } else {
                        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                            String cdstato = "PR";
                            if (AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")) {
                                cdstato = "PN";
                            }
                            tkordi = f_ordven.getCarrello(user.getUserDB().getTkutente(), user.getUserDB().getTkclie(), cdstato);
                        }
                    }

                    if (tkordi > 0) { //esiste un ordine provvisorio
                        int tot_rec = f_ordven.cancella_riga(tkordi, tkposi_del);

                        if (tot_rec > 0) {

                            f_ordven.m_connection.commit();

                            model.addAttribute("message", getText("cart.item.deleted", new Object[]{}, "Riga Cancellata", rc.getLocale()));

                        } else {

                            f_ordven.m_connection.rollback();
                            model.addAttribute("message", getText("cart.item.nodeleted", new Object[]{}, "Errore: nessun Articolo Cancellato", rc.getLocale()));

                        }

                    }

                } catch (Exception ex_page) {
                    log.error(ex_page);
                    try {
                        atk_sql.m_connection.rollback();
                    } catch (Exception ex_page_sql) {

                        log.error(ex_page_sql);

                    }
                } finally {

                    try {
                        atk_sql.m_connection.commit();
                        atk_sql.closeConnection();
                    } catch (Exception _e) {
                        log.error(_e.getMessage());
                    }

                }

            }

        } else if ("delete_all".equals(azione)) {
        }

//    if (azione.equals("delete")){
//
//        long r_tkposi_del = 0;
//
//        if (request.getParameter("tkposi_del") != null) r_tkposi_del = Long.parseLong(request.getParameter("tkposi_del"));
//
//        tot_rec = f_ordven.cancella_riga( tkordi, r_tkposi_del );
//
//        if (tot_rec > 0) {
//
//            f_ordven.m_connection.commit();
//
//            message = "Riga Cancellata\n";
//        }else {
//            f_ordven.m_connection.rollback();
//            message = "Errore: nessun Articolo Cancellato";
//        }
//    }
//
//
//
//
//
//    if (azione.equals("delete_all")){
//
//
//        tot_rec = f_ordven.cancella_righe( tkordi );
//
//        if (tot_rec > 0) {
//
//            f_ordven.m_connection.commit();
//
//            message = "Carrello Svuotato\n";
//        }else {
//            f_ordven.m_connection.rollback();
//            message = "Errore: nessun Articolo Cancellato!";
//        }
//
//    }
        CarrelloItemForm cart = getBaseCart(request);

        referenceData(request, cart, model, user);

        model.addAttribute("cart", cart);

        return "fullCart";
    }

    @RequestMapping(value = "/fullCart", method = RequestMethod.POST)
    public String processSubmit(HttpServletRequest request,
            @ModelAttribute("cart") CarrelloItemForm form, BindingResult result, Model model, SessionStatus status) throws IllegalAccessException, InvocationTargetException {

//        log.debug(form);
        ShopUser user = null;

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        if (user.isRealCustomer() && !form.getCondcAccepted()) {
            result.rejectValue("condcAccepted", "errors.mustaccept.condc", "Le condizioni di vendita devono essere accettate");
        }

        if (result.hasErrors()) {
            //referenceData(request, form, model, user);
            return "fullCart";

        } else {
            //salvataggio note
            vistosiShopManager.saveCarrello(form);

            RequestContext rc = new RequestContext(request);

            request.getSession().setAttribute("message", getText("cart.saved", new Object[]{}, "Carrello salvato", rc.getLocale()));

//        log.debug("gotodest: " + request.getParameter("gotodest"));
            boolean goon = ServletRequestUtils.getBooleanParameter(request, "gotodest", false);

            if (goon) {
                return "redirect:catalogo_dest_merce.jsp";
            } else {
                return "redirect:fullCart";
            }
        }

    }

    @ModelAttribute(value = "portalUrl")
    private String getPortalUrl() {

        String url = "/";

        Ep_costanti cost = vistosiShopManager.getEpCostanti("ep.portal_url");

        if (cost != null && StringUtils.isNotBlank(cost.getCostvalue())) {
            url = cost.getCostvalue();
        }

        return url;
    }

    @ModelAttribute(value = "eprogenUrl")
    private String getEprogenUrl() {

        String url = "/";

        Ep_costanti cost = vistosiShopManager.getEpCostanti("ep.eprogen_url");

        if (cost != null && StringUtils.isNotBlank(cost.getCostvalue())) {
            url = cost.getCostvalue();
        } else {
            String portalUrl = getPortalUrl();

            if (portalUrl != null) {
                try {
                    //url = StringUtils.replace(portalUrl, "portal", "eprogen");
                    url = of_trasformaURL(portalUrl, "portal", "eprogen");
                } catch (Exception ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return url;
    }
}
