/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_iva_assofisc;
import com.ateikon.function.F_listino;
import com.ateikon.function.F_ordven;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.*;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import com.ateikon.structure.Str_iva_assofisc;
import com.ateikon.structure.Str_listino;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
@Controller
@RequestMapping("/carrelloItem-{cdarti}/**")
//@SessionAttributes(types=CarrelloItemForm.class)
@SessionAttributes("carrelloItem")
public class CarrelloItemFormController extends BaseAnnotationController {

    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }

    @InitBinder
    @Override
    public void initBinder(HttpServletRequest request, WebDataBinder binder) {
        binder.registerCustomEditor(String.class, null, new StringTrimmerEditor(true));

        log.debug("request.getLocale(): " + request.getLocale());

        NumberFormat nf = NumberFormat.getNumberInstance(request.getLocale());
        binder.registerCustomEditor(Double.class, null, new CustomNumberEditor(Double.class, nf, true));
        binder.registerCustomEditor(Float.class, null, new CustomNumberEditor(Float.class, nf, true));
        binder.registerCustomEditor(BigDecimal.class, null,
                new CustomNumberEditor(BigDecimal.class, nf, true));

        binder.registerCustomEditor(Integer.class, null,
                new CustomNumberEditor(Integer.class, null, true));

        binder.registerCustomEditor(Short.class, null,
                new CustomNumberEditor(Short.class, null, true));

        NumberFormat nf2 = NumberFormat.getIntegerInstance();
        nf2.setGroupingUsed(false);
        binder.registerCustomEditor(Long.class, null,
                new CustomNumberEditor(Long.class, nf2, true));
        binder.registerCustomEditor(null, "ord_positito.qtatot",
                new CustomNumberEditor(BigDecimal.class, nf2, true));

        binder.registerCustomEditor(byte[].class,
                new ByteArrayMultipartFileEditor());
        /*
         * SimpleDateFormat dateFormat = new
         * SimpleDateFormat(getText("date.format", request.getLocale()));
         * dateFormat.setLenient(false); binder.registerCustomEditor(Date.class,
         * null, new CustomDateEditor(dateFormat, true));
         */
    }

    private void referenceData(HttpServletRequest request, CarrelloItemForm obj, Model model, Cliente cliente) {

        ShopUser user = null;

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        Double quantita = 0.0;
        int li_ = 0;
        Atk_sql atk_sql = new Atk_sql();
        long tkordi = 0;

        try {

            atk_sql.dbConnection();
            log.debug("1");
            atk_sql.profil = (user != null) ? user.getUserDB().getProfil() : "PGMR";
            atk_sql.cdazie = (user != null) ? user.getUserDB().getCdazie() : "01";
            atk_sql.cddipa = (user != null) ? user.getUserDB().getCddipa() : "0000";

            log.debug("2");
            F_iva_assofisc f_iva_assofisc = new F_iva_assofisc();
            F_listino f_listino = new F_listino();
            F_ordven f_ordven = new F_ordven();

            //        if (tipo_listino.equals("CATALOGO")){
            //           f_listino = (F_listino) (new F_cat_listino());
            //        } else {
            //           f_listino = new F_listino();
            //        }
            log.debug("3");
            //atk_sql.setProfilo((Atk_sql) ord_test);
            //atk_sql.setProfilo((Atk_sql) ord_positito);
            //atk_sql.setProfilo((Atk_sql) cat_note);
            //atk_sql.setProfilo((Atk_sql) cat_wish_list);
            //atk_sql.setProfilo((Atk_sql) f_ordven );
            atk_sql.setProfilo((Atk_sql) f_iva_assofisc);
            atk_sql.setProfilo((Atk_sql) f_listino);
            atk_sql.setProfilo((Atk_sql) f_ordven);

            String cdstato = "PR";
            if (AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")) {
                cdstato = "PN";
            } else if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                cdstato = "WL";
            }
            log.debug(cdstato);

            Object s_tkordi = WebUtils.getSessionAttribute(request, "tkordi");
            if (s_tkordi != null) {
                tkordi = (Long) s_tkordi;
                log.debug("tkordi 1: " + tkordi);
            } else {
                if (AuthorityUtils.userHasAuthority("ROLE_CLIE")) {
                    tkordi = f_ordven.getCarrello(user.getUserDB().getTkutente(), user.getUserDB().getTkclie(), cdstato);
                    log.debug("tkordi 2: " + tkordi);
                }
            }
            //quantita = f_ordven.getQuantita_in_carrello( user.getUserDB().getTkutente(), user.getUserDB().getTkclie(), obj.getArticolo().getCdarti(), cdstato) ;
            if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                if(tkordi>0) quantita = f_ordven.getQuantita_in_carrello(obj.getArticolo().getCdarti(), tkordi);
                else quantita = 0.0;
            }else{
                quantita = f_ordven.getQuantita_in_carrello(obj.getArticolo().getCdarti(), tkordi);
            }
            log.debug("qta in cart: " + quantita);

            // IVA - ASSOFISCAL
            double prezzo = 0;
            double prezzo_netto = 0;
            String ls_sconto = "";
            
            if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                
                Str_iva_assofisc lstr_iva = new Str_iva_assofisc();

                lstr_iva.cdarti = obj.getArticolo().getCdarti();
                lstr_iva.cdiva_t = cliente.getArchclie().getCdiva();
                lstr_iva.cdfisc_t = cliente.getArchclie().getCdfisc();

                li_ = f_iva_assofisc.exec(lstr_iva);
                log.debug("5");
                if (li_ != 0) {
                    throw new Exception("Errore Calcolo IVA.");
                }



                // *** CALCOLO PREZZO

                Str_listino str_listino = new Str_listino();

                str_listino = f_listino.calcolaPrezzo(cliente.getArchclie().getCdlist(), cliente.getArchclie().getTkclie(), obj.getArticolo().getCdarti(), quantita);
                log.debug("6");

                double prezzo_pub = 0;
                prezzo = str_listino.impuni;
                prezzo_netto = str_listino.impuninetto;
                ls_sconto = str_listino.descr_sconti;
                Lis_listino lis_listino = vistosiShopManager.getListinoByKey(cliente.getArchclie().getCdlist());
                model.addAttribute("sconto", prezzo - prezzo_netto);

                if (prezzo_netto > 0) {

                    prezzo_pub = prezzo_netto;

                    if (lis_listino.getIvato().equals("N")) {
                        prezzo_pub += prezzo_pub * lstr_iva.aliquo / 100;
                    }
                }
                log.debug("7");

            }

            model.addAttribute("prezzo", prezzo);
            model.addAttribute("prezzo_netto", prezzo_netto);


            model.addAttribute("ls_sconto", ls_sconto);
            model.addAttribute("quantita", quantita);

            Cookie ckVp = WebUtils.getCookie(request, "view_net_price");

            model.addAttribute("sub_tot", (ckVp != null && "S".equals(ckVp.getValue()) && !AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")) ? quantita * prezzo_netto : quantita * prezzo);
            model.addAttribute("tkordi", tkordi);

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

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request,
            @PathVariable(value = "cdarti") String cdarti,
            @RequestParam(value = "cdartirif", required = false) String cdartirif,
            Model model) {

        CarrelloItemForm form = new CarrelloItemForm();

        Cliente cliente = null;
        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            cliente = vistosiShopManager.getCliente(user.getUserDB().getTkclie(), user.getUserDB().getCdazie());
        }


        if (cdarti != null) {

            form.setArticolo(vistosiShopManager.getMrp_arch_articoliByKey(cdarti));
            try {
                //TODO prezzi e disponibilità
                referenceData(request, form, model, cliente);

                form.setOrd_test(new Web_ord_test());

                form.getOrd_test().setTkordi((Long) model.asMap().get("tkordi"));
                form.setOrd_positito(new Web_ord_positito());
                Double qta_in_cart = (Double) model.asMap().get("quantita");

                if (qta_in_cart > 0) {
                    form.getOrd_positito().setQtatot(new BigDecimal(qta_in_cart));
                }

                if (StringUtils.isNotBlank(cdartirif)) {

                    log.debug("cdartirif: " + cdartirif);
                    form.getOrd_positito().setCdartirif(cdartirif);
                }
                long tkmaga = 1;
                form.getArticolo().setMrp_file_giacenza(vistosiShopManager.getMrp_file_giacenzaByKey(form.getArticolo().getCdarti(), "STD", tkmaga));
                //form.getOrd_positito().setFgpromo(form.getArticolo().getFgpromo());
                log.debug("form.getOrd_positito().getFgpromo(): " + form.getOrd_positito().getFgpromo());

                if ("S".equals(form.getArticolo().getFgpromo())) {
                    Vist_offerte vist_offerte = vistosiShopManager.getVist_offerteByCdarti(cdarti);
                    if (vist_offerte != null) {
                        form.getArticolo().setVist_offerte(vist_offerte);
                    }
                }



                log.debug(form.getArticolo());
            } catch (Exception ex) {
                log.debug(ex.getMessage());
            }

            model.addAttribute("isRicambio", vistosiShopManager.isRicambio(cdarti));
        }



        model.addAttribute("carrelloItem", form);
        return "carrelloItem";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(HttpServletRequest request,
            @ModelAttribute("carrelloItem") CarrelloItemForm form, BindingResult result, Model model, SessionStatus status) {

        RequestContext rc = new RequestContext(request);
        Cliente cliente = null;
        ShopUser user = null;
        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            cliente = vistosiShopManager.getCliente(user.getUserDB().getTkclie(), user.getUserDB().getCdazie());
        }
        
        form.setArticolo(vistosiShopManager.getMrp_arch_articoliByKey(form.getArticolo().getCdarti()));
        log.debug("submit: " + form.getArticolo());
        UriTemplate template = new UriTemplate("/carrelloItem-{cdarti}/");
        Map<String, String> uriVariables = new HashMap<String, String>();
        uriVariables.put("cdarti", form.getArticolo().getCdarti());

        referenceData(request, form, model, cliente);

        if (StringUtils.isNotBlank(form.getArticolo().getCdarti())) {
            model.addAttribute("isRicambio", vistosiShopManager.isRicambio(form.getArticolo().getCdarti()));
        }

        if (request.getParameter("delete") != null) {
            return "redirect:" + template.expand(uriVariables).toString();

        } else {
            log.debug(form.getOrd_test().getTkordi());
            if (!result.hasErrors() && (form.getOrd_positito().getQtatot() == null || form.getOrd_positito().getQtatot().doubleValue() <= 0)) {
                result.rejectValue("ord_positito.qtatot", "errors.qta", new Object[]{new DefaultMessageSourceResolvable(new String[]{"positito.qta"}), ""}, "La quantità deve essere un valore >= 0");
            }

            if (result.hasErrors()) {
                return "carrelloItem";

            } else {

                log.debug("insert row");
                log.debug("form.getOrd_positito().getCdartirif(): " + form.getOrd_positito().getCdartirif());
                Atk_sql atk_sql = new Atk_sql();

                try {

                    atk_sql.dbConnection();

                    atk_sql.profil = (user != null) ? user.getUserDB().getProfil():"PGMR";
                    atk_sql.cdazie = (user != null) ? user.getUserDB().getCdazie():"01";
                    atk_sql.cddipa = (user != null) ? user.getUserDB().getCddipa():"0000";
                    log.debug("init f_ordven");
                    F_ordven f_ordven = new F_ordven();
                    atk_sql.setProfilo((Atk_sql) f_ordven);

                    long tkordi = form.getOrd_test().getTkordi();

                    if (tkordi <= 0) {
                        String cdstato = "PR";
                        //log.debug(user.getAuthorities());
                        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                            cdstato = "WL";
                            tkordi = f_ordven.getDef_provvisorio(cdstato);
                            request.getSession().setAttribute("tkordi", tkordi);
                        }else{
                            if (AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")) {
                                cdstato = "PN";
                            }
                            log.debug(cdstato);
                            tkordi = f_ordven.getDef_provvisorio(user.getUserDB().getTkutente(), user.getUserDB().getTkclie(), cdstato);
                        }                      
//                        else if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
//                            cdstato = "WL";
//                            tkordi = f_ordven.getDef_provvisorio(cdstato);
//                            request.getSession().setAttribute("tkordi", tkordi);
//                        }
                    }

                    log.debug("tkordi: " + tkordi);
                    double adb_impuninetto = (Double) model.asMap().get("prezzo_netto");
                    log.debug("adb_impuninetto: " + adb_impuninetto);

                    String cdtins = "0000000002"; //Q.tà, Pezzi = 1
                    String fg_annulla_sconti = "N";
                    String fg_tpcalcimp = "L";   // L=listino; S=Ricalcolo in base agli sconti; I=Ricalcolo in base all'importo;
                    long f_tkmatricola = 0;
                    long ll_tkposi = f_ordven.aggiungi_articolo(tkordi, form.getArticolo().getCdarti(), cdtins, 1, 0.0, 0.0, 0.0, form.getOrd_positito().getQtatot().doubleValue(), (cliente!=null && cliente.getArchclie().getSconto1() != null) ? cliente.getArchclie().getSconto1().doubleValue() : 0, (cliente!=null && cliente.getArchclie().getSconto2() != null) ? cliente.getArchclie().getSconto2().doubleValue() : 0, (cliente!=null && cliente.getArchclie().getSconto3() != null) ? cliente.getArchclie().getSconto3().doubleValue() : 0, adb_impuninetto, fg_annulla_sconti, fg_tpcalcimp, f_tkmatricola, form.getOrd_positito().getCdartirif(), form.getArticolo().getFgpromo());

                    log.debug("tkposi: " + ll_tkposi);

                    if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                        saveMessage(request, getText("wishlist.item.saved", new Object[]{}, "Quantità in wishlist salvata", rc.getLocale()));
                    }else{
                        saveMessage(request, getText("cart.item.saved", new Object[]{}, "Quantità in carrello salvata", rc.getLocale()));
                    }
                    
                    
                } catch (Exception ex_page) {
                    log.error(ex_page);
                    saveErrorMsg(request, ex_page.getMessage());
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

                status.setComplete();

                return "redirect:" + template.expand(uriVariables).toString();
            }
        }
    }
}
