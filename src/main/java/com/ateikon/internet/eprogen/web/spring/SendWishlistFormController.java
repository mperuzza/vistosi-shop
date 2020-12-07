/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.common.Atk_sql;
import static com.ateikon.common.Atk_sql.of_trasformaURL;
import com.ateikon.function.F_eprogen_replace;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;
import com.ateikon.internet.eprogen.domain.web.FormSendWishlist;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import com.ateikon.internet.eprogen.web.utils.ValidationUtils;
import com.ateikon.internet.generic.service.MailEngine;
import com.ateikon.internet.utils.RequestUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.mail.MailException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;

/**
 *
 * @author emiliano
 */
@Controller
@RequestMapping(value = "/wishlist")
@SessionAttributes("formSendWishlist")
public class SendWishlistFormController extends BaseFormController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private VistosiShopManager vistosiShopManager;
    @Autowired
    private MailEngine mailEngine = null;

    public MailEngine getMailEngine() {
        return mailEngine;
    }

    public void setMailEngine(MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }

    // @RequestMapping(value = {"/temp"}, method = RequestMethod.GET)
    // public String getTemp(HttpServletRequest request, Model model) {
    //
    // RequestContext rc = new RequestContext(request);
    //
    // String templateName = "temp.vm";
    //
    // Locale locale = rc.getLocale();
    // Map mailModel = new HashMap();
    //
    // CarrelloItemForm cart = new CarrelloItemForm();
    //
    // Mrp_arch_articoli a = new Mrp_arch_articoli();
    //
    // Vist_famiglia f = new Vist_famiglia();
    // f.setDsvistfam("giogali");
    // f.setDsvistfam_eng("giogalieng");
    //
    // a.setVist_famiglia(f);
    //
    // Web_ord_positito p = new Web_ord_positito();
    //
    // p.setArticolo(a);
    // cart.setOrd_posititos(Collections.singletonList(p));
    //
    // mailModel.put("cart", cart);
    // String s_locale = locale.getLanguage();
    // mailModel.put("s_locale", s_locale);
    //
    // if("it".equals(s_locale)){
    // mailModel.put("atkLangSfx", "");
    // }else
    // if("en".equals(s_locale)){
    // mailModel.put("atkLangSfx", "_eng");
    // }else
    // if("de".equals(s_locale)){
    // mailModel.put("atkLangSfx", "_ted");
    // }else
    // if("es".equals(s_locale)){
    // mailModel.put("atkLangSfx", "_spa");
    // }else
    // if("fr".equals(s_locale)){
    // mailModel.put("atkLangSfx", "_fra");
    // }else mailModel.put("atkLangSfx", "");
    //
    // ;
    // mailModel.put("resources", messageSource);
    // mailModel.put("noArgs", new Object[]{});
    // mailModel.put("languageId", "dsvistfam");
    // mailModel.put("number", new NumberTool());
    // mailModel.put("date", new DateTool());
    // mailModel.put("render", new RenderTool());
    // mailModel.put("applicationURL", RequestUtil.getAppURL(request));
    //
    //
    // mailModel.put("sender", "pippo");
    //
    //
    // String object = "Vistosi wishlist";
    //
    //
    // String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
    // templateName, mailModel);
    // log.debug(body);
    //
    // return "send_wishlist";
    // }
    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String getForm(
            @RequestParam(value = "storelocator", required = false, defaultValue = "false") Boolean storeLocator,
            HttpServletRequest request, Model model) throws Exception {

        long tkordi = 0;
        CarrelloItemForm cart = null;
        Object s_tkordi = WebUtils.getSessionAttribute(request, "tkordi");
        if (s_tkordi != null) {
            tkordi = (Long) s_tkordi;
            cart = vistosiShopManager.getCarrello(tkordi);
        }

        model.addAttribute("cart", cart);

        FormSendWishlist form = new FormSendWishlist();

        form.setWishlistId(tkordi);

        model.addAttribute("formSendWishlist", form);

        model.addAttribute("storeLocator", storeLocator);

        return "send_wishlist";
    }

    public void validate(FormSendWishlist form, BindingResult result, HttpServletRequest request) {

        if (StringUtils.isBlank(form.getSender())) {
            result.rejectValue("sender", "errors.required", new Object[] { "Email" }, "Mittente richiesto");
        } else {
            if (!ValidationUtils.validateEmail(form.getSender())) {
                result.rejectValue("sender", "errors.invalid", new Object[] { "Email" }, "Indirizzo mail non valido");
            }
        }

        if (StringUtils.isBlank(form.getDestinatari())) {
            result.rejectValue("destinatari", "errors.required", new Object[] { "Email" }, "Destinatari richiesti");
        } else {

            String emailStrings = form.getDestinatari().trim();
            String[] a_email = StringUtils.split(emailStrings, ',');

            if (a_email == null) {
                result.rejectValue("destinatari", "errors.required", new Object[] { "Email" }, "Destinatari richiesti");
            }

            a_email = StringUtils.stripAll(a_email);

            for (String email : a_email) {

                if (!ValidationUtils.validateEmail(email)) {

                    result.rejectValue("destinatari", "errors.invalid", new Object[] { "Email" },
                            "Indirizzo mail non valido");

                    break;
                }

            }

            form.setToList(new ArrayList<String>(Arrays.asList(a_email)));

            // for (String string : a_email) {
            // log.debug(string);
            // }
        }

    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
    public String sendWishlist(@ModelAttribute("formSendWishlist") FormSendWishlist form, BindingResult result,
            HttpServletRequest request) throws Exception {

        RequestContext rc = new RequestContext(request);

        validate(form, result, request);
        if (result.hasErrors()) {
            return "send_wishlist";
        }

        Map model = new HashMap();

        CarrelloItemForm cart = vistosiShopManager.getCarrello(form.getWishlistId());
        long tkmaga = 1;
        if (cart.getOrd_posititos() != null) {
            for (Web_ord_positito ord_positito : cart.getOrd_posititos()) {

                Mrp_arch_articoli art = vistosiShopManager.getMrp_arch_articoliByKey(ord_positito.getCdarti());

                art.setVist_famiglia(vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam()));
                art.setVist_colori_vetro(vistosiShopManager.getVist_colori_vetroByKey(art.getCdvistcolv()));
                art.setVist_finit_mont(vistosiShopManager.getVist_finit_montByKey(art.getCdvistfinm()));
                // art.setVist_finit_vetro(vistosiShopManager.getVist_finit_vetroByKey(art.getCdvistfinv()));
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

                // ricerca file download
                if (art != null) {

                    WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

                    // ricerca modelli
                    Vist_famiglia vist_fam = vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam());

                    log.debug("filedis >>>" + art.getVist_filedis());

                    String path_modello = "fileresources/models";
                    String path_3D = path_modello + "/3D/";
                    // String nome_modello = vist_fam.getCdvistfam_mPad() + art.getCdvisttp() +
                    // art.getCdvistv1Pad() + art.getCdvistv2Pad() + art.getCdvistv3Pad() +"-";
                    String nome_modello = art.getVist_filedis();
                    // igs
                    // String igs = path_3D + nome_modello + ".zip";
                    // String igs = path_3D + nome_modello + (art.isLed() ? art.getCdvistelet() :
                    // "") + ".zip";
                    // try {
                    // String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(),
                    // igs);
                    // File f = new File(path_to_filemodel);
                    // log.debug(path_to_filemodel);
                    // art.setModel3D_igs(igs);
                    // if (f.exists()) {
                    // art.setModel3D_igsExists(Boolean.TRUE);
                    // }
                    // } catch (FileNotFoundException ex) {
                    // log.error("file " + igs + " non trovato");
                    // }
                    // eprt
                    // String eprt = path_3D + nome_modello + ".EPRT";
                    // try {
                    // String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(),
                    // eprt);
                    // File f = new File(path_to_filemodel);
                    // log.debug(path_to_filemodel);
                    // art.setModel3D_eprt(eprt);
                    // if (f.exists()) {
                    // art.setModel3D_eprtExists(Boolean.TRUE);
                    // }
                    // } catch (FileNotFoundException ex) {
                    // log.error("file " + eprt + " non trovato");
                    // }
                    // easm
                    // String easm = path_3D + nome_modello + ".EASM";
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
                    // dwg cm
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

                    // file certificati
                    Vist_articoli_datiextra datiExtra = vistosiShopManager.getDatiExtraByCdartm(art.getCdartm());
                    String path_cert = "fileresources/cert/";

                    if (datiExtra != null) {

                        try {

                            String path_techsheet = "fileresources/assembling_instructions/";
                            String techsheet = datiExtra.getArwFileSchedaTec();
                            if (StringUtils.isNotEmpty(techsheet)) {
                                try {
                                    techsheet = StringUtils.substringAfterLast(techsheet, "\\");

                                    String path_to_techsheet = WebUtils.getRealPath(ctx.getServletContext(),
                                            path_techsheet + techsheet);
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
                                    }
                                    if (StringUtils.contains(nomefile, "\\")) {
                                        nomefile = StringUtils.substringAfterLast(nomefile, "\\");
                                    }

                                    try {
                                        String realPath = WebUtils.getRealPath(ctx.getServletContext(),
                                                path_cert + nomefile);

                                        File f = new File(realPath);
                                        if (f.exists()) {
                                            Map<String, String> certMap = new HashMap<String, String>();
                                            certMap.put("img", nomeimg);
                                            certMap.put("file", path_cert + nomefile);

                                            art.getCertList().addLast(certMap);
                                        } else {
                                            // TODO???
                                        }
                                    } catch (FileNotFoundException ex) {
                                        // log.debug("not exists");
                                    }
                                }
                            }

                            // file classe energetica
                            String fieldName = "arwCertificazione" + 10;
                            String nomefile = BeanUtils.getSimpleProperty(datiExtra, fieldName);

                            if (StringUtils.isNotBlank(nomefile)) {
                                art.setEnergyClass(nomefile);
                            }

                            art.setEletDatiExtraMap(vistosiShopManager.getDatiExtraLampMap(datiExtra, ctx));

                            // check esistenza specsheet
                            // String realPathPdf = WebUtils.getRealPath(ctx.getServletContext(),
                            // SpecSheet.ROOT_RES + "/risorse/" + nome_modello + ".pdf");
                            // String realPathU3D = WebUtils.getRealPath(ctx.getServletContext(),
                            // SpecSheet.ROOT_RES + "/risorse/" + nome_modello + ".U3D");
                            // String realPathXlsx = WebUtils.getRealPath(ctx.getServletContext(),
                            // SpecSheet.ROOT_RES + "/risorse/" + nome_modello + "_" +
                            // rc.getLocale().getLanguage() + ".xlsx");
                            //
                            // File fPdf = new File(realPathPdf);
                            // File fU3D = new File(realPathU3D);
                            // File fXlsx = new File(realPathXlsx);
                            //
                            // if(fPdf.exists() && fU3D.exists() && fXlsx.exists()){
                            if (vistosiShopManager.checkSpecsheetExists(art, ctx, rc)) {

                                art.setSpecsheetExists(true);

                            }

                            // } catch (FileNotFoundException ex) {

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

                vistosiShopManager.addToggleStateZEEFilter(stpars, request);

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

                    art.setDatiRicambio(
                            vistosiShopManager.getDatiRicambio(ord_positito.getCdartirif(), ord_positito.getCdarti()));

                    Mrp_arch_articoli artrif = vistosiShopManager
                            .getMrp_arch_articoliByKey(ord_positito.getCdartirif());

                    artrif.setVist_famiglia(vistosiShopManager.getVist_famigliaByKey(artrif.getCdvistfam()));
                    artrif.setVist_colori_vetro(vistosiShopManager.getVist_colori_vetroByKey(artrif.getCdvistcolv()));
                    artrif.setVist_finit_mont(vistosiShopManager.getVist_finit_montByKey(artrif.getCdvistfinm()));
                    // artrif.setVist_finit_vetro(vistosiShopManager.getVist_finit_vetroByKey(art.getCdvistfinv()));
                    artrif.setVist_elettrificazioni(
                            vistosiShopManager.getVist_elettrificazioniByKey(artrif.getCdvistelet()));

                    ord_positito.setArticolorif(artrif);
                }
            }
        }

        /*
         * String cdlingua = "0"; RequestContext rc = new RequestContext(request);
         * Locale locale = rc.getLocale(); List<TabLingue> tabLingues =
         * jpqlJpaController.findEntities(TabLingue.class, Filter.equal("codiceiso",
         * locale.toString())); if(!Utils.isEmpty(tabLingues)){ cdlingua =
         * tabLingues.get(0).getCodicelingua(); }
         */
        // String cdlingua = GeoIPInterceptor.getCountry(request).getCodicelingua();
        // List<TabLingue> tabLingues = jpqlJpaController.findEntities(TabLingue.class,
        // Filter.equal("codicelingua", cdlingua));
        // Integer languageId = 0;
        // if (!Utils.isEmpty(tabLingues)) {
        // languageId = tabLingues.get(0).getIdrecord();
        // }
        //
        // model.put("languageId", languageId);
        Map mailModel = new HashMap();
        mailModel.put("cart", cart);

        List<Mrp_arch_stato> availableStates = vistosiShopManager.getAvailableStates();
        mailModel.put("legenda", availableStates);

        List<String> dest = form.getToList();

        // if (form.isSendSelf()) {
        //
        // if (epUtente != null && StringUtils.isNotBlank(epUtente.getEmail()) &&
        // !form.getToList().contains(epUtente.getEmail())) {
        //
        // form.getToList().add(epUtente.getEmail());
        // }
        // }
        String templateName = "share_wishlist.vm";

        ShopUser user = null;

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        Locale locale = rc.getLocale();
        mailModel.put("locale", locale);
        String s_locale = locale.getLanguage();
        mailModel.put("s_locale", s_locale);

        if ("it".equals(s_locale)) {
            mailModel.put("atkLangSfx", "");
        } else if ("en".equals(s_locale)) {
            mailModel.put("atkLangSfx", "_eng");
        } else if ("de".equals(s_locale)) {
            mailModel.put("atkLangSfx", "_ted");
        } else if ("es".equals(s_locale)) {
            mailModel.put("atkLangSfx", "_spa");
        } else if ("fr".equals(s_locale)) {
            mailModel.put("atkLangSfx", "_fra");
        } else {
            mailModel.put("atkLangSfx", "");
        }

        mailModel.put("resources", messageSource);
        mailModel.put("noArgs", new Object[] {});
        // ${eprogenUrl}epRichiesta_risorse_pubblica_form.jsp?origine_richiesta=PUBBLICA&lang=${rc.locale}&cdling=${cdling}
        String cdling = (String) WebUtils.getSessionAttribute(request, "cdling");
        mailModel.put("cdling", cdling);
        mailModel.put("eprogenUrl", getEprogenUrl());
        mailModel.put("portalUrl", getPortalUrl());
        mailModel.put("downloadUrl",
                getEprogenUrl() + "epRichiesta_risorse_pubblica_form.jsp?origine_richiesta=PUBBLICA&lang=" + s_locale
                        + "&cdling=" + cdling);
        // mailModel.put("number", new NumberTool());
        // mailModel.put("date", new DateTool());
        // mailModel.put("render", new RenderTool());
        mailModel.put("applicationURL", RequestUtil.getAppURL(request));

        Atk_sql atk_sql = new Atk_sql();

        try {

            atk_sql.dbConnection();

            atk_sql.profil = (user != null) ? user.getUserDB().getProfil() : "PGMR";
            atk_sql.cdazie = (user != null) ? user.getUserDB().getCdazie() : "01";
            atk_sql.cddipa = (user != null) ? user.getUserDB().getCddipa() : "0000";

            F_eprogen_replace f_eprogen_replace = new F_eprogen_replace();
            atk_sql.setProfilo((Atk_sql) f_eprogen_replace);

            HashMap of_setPar_BANNER = f_eprogen_replace.of_setPar_BANNER(null, cdling);
            // tkordi = f_ordven.getCarrello(user.getUserDB().getTkutente(),
            // user.getUserDB().getTkclie(), cdstato);

            mailModel.put("banner", of_setPar_BANNER.get("${banner}"));

        } catch (Exception ex_page) {
            // log.error(ex_page);
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

        mailModel.put("sender", form.getSender());

        if (form.getNote() != null && StringUtils.isNotBlank(form.getNote().trim())) {
            mailModel.put("note", form.getNote().trim());
        }

        String object = "Vistosi wishlist";

        try {
            getMailEngine().sendHtmlMessage((String[]) form.getToList().toArray(new String[form.getToList().size()]),
                    null, (form.getSender() != null) ? form.getSender() : "info@vistosi.it", object, templateName,
                    mailModel, "atknotifiche@gmail.com");

            // String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
            // templateName, mailModel);
            // log.debug(body);
            // messaggioFacade.store(sender, object, form.getToList(), body, attachments);
            saveMessage(request, getText("wishlist.sent", RequestContextUtils.getLocale(request)));
            model.put("status", "sent");
        } catch (MailException ex) {
            ex.printStackTrace();
            // errors.reject("errors.sendmail","Errore invio mail");
            model.put("error", "Errore invio mail (mailex)");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            // errors.reject("errors.sendmail","Errore invio mail");
            model.put("error", "Errore invio mail (messex)");
        }

        // inviteService.sendInvite(account, form.getInvitees(),
        // form.getInvitationText());
        // FlashMap.setSuccessMessage("Your invitations have been sent");
        return "redirect:/wishlist";
    }

    private String getPortalUrl() {

        String url = "/";

        Ep_costanti cost = vistosiShopManager.getEpCostanti("ep.portal_url");

        if (cost != null && StringUtils.isNotBlank(cost.getCostvalue())) {
            url = cost.getCostvalue();
        }

        return url;
    }

    private String getEprogenUrl() {

        String url = "/";

        Ep_costanti cost = vistosiShopManager.getEpCostanti("ep.eprogen_url");

        if (cost != null && StringUtils.isNotBlank(cost.getCostvalue())) {
            url = cost.getCostvalue();
        } else {
            String portalUrl = getPortalUrl();

            if (portalUrl != null) {
                try {
                    // url = StringUtils.replace(portalUrl, "portal", "eprogen");
                    url = of_trasformaURL(portalUrl, "portal", "eprogen");
                } catch (Exception ex) {
                    Logger.getLogger(SendWishlistFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return url;
    }
}
