/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import static com.ateikon.common.Atk_sql.of_trasformaURL;
import com.ateikon.internet.eprogen.domain.Vist_vetro;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_costanti;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_posts;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_articoli_datiextra;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_articoli_img;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_colori_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_cp_collezioni;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_elettrificazioni;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_etichette;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_famiglia;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_mont;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_vetro;
import com.ateikon.internet.eprogen.web.form.SchedaArticolo;
import com.ateikon.internet.eprogen.web.interceptor.GeoIPInterceptor;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
@Controller
@RequestMapping("/scheda-{dsfam}-{cdtipo}/{cdfam}/**")
//@SessionAttributes(types=SchedaArticolo.class)
public class SchedaArticoloController {

    private Log log = LogFactory.getLog(this.getClass());
    //private List<String> DEFAULT_CDCLAS_A = Arrays.asList("L");
    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, null, new StringTrimmerEditor(true));
    }

    private void getReferenceData(SchedaArticolo scheda, HttpServletRequest request, Model model, String cdvisttp, String cdvistfam) {

        //menu data
        Map pars = new HashMap();
        pars.put("fgweb", "S");

        ShopUser user = null;
        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        vistosiShopManager.addCdclasFilter(pars, request);
//        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
//            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli listino
//        } else {
//            pars.put("cdclas_aList", VistosiShopManager.DEFAULT_CDCLAS_A); //filtro solo articoli listino
//        }
        WebUtils.setSessionAttribute(request, "famigliaFilter", vistosiShopManager.getVist_famigliaByKey(cdvistfam));
        pars.put("cdvistfam", cdvistfam);
        //estraggo tutti i tipi delle famiglia, la scheda è solo per famiglia
        //model.addAttribute("tipologie", vistosiShopManager.findVist_tipi(pars));
        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());

        WebUtils.setSessionAttribute(request, "tipologiaFilter", vistosiShopManager.getVist_tipiByKey(cdvisttp));
        pars.put("cdvisttp", cdvisttp);
        /*
         * Vist_tipi f_tipologia =
         * (Vist_tipi)WebUtils.getSessionAttribute(request, "tipologiaFilter");
         * if(f_tipologia!=null){ pars.put("cdvisttp",
         * f_tipologia.getCdvisttp()); }
         */
        /*
         * Vist_famiglia f_famiglia =
         * (Vist_famiglia)WebUtils.getSessionAttribute(request,
         * "famigliaFilter"); if(f_famiglia!=null){ pars.put("cdvistfam",
         * f_famiglia.getCdvistfam()); }
         */
        Vist_colori_vetro f_colore = (Vist_colori_vetro) WebUtils.getSessionAttribute(request, "coloreFilter");
        if (f_colore != null) {
            pars.put("cdvistcolv", f_colore.getCdvistcolv());
        }
        Vist_finit_vetro f_finvetro = (Vist_finit_vetro) WebUtils.getSessionAttribute(request, "finvetroFilter");
        if (f_finvetro != null) {
            pars.put("cdvistfinv", f_finvetro.getCdvistfinv());
        }
        Vist_finit_mont f_finit = (Vist_finit_mont) WebUtils.getSessionAttribute(request, "finituraFilter");
        if (f_finit != null) {
            pars.put("cdvistfinm", f_finit.getCdvistfinm());
        }

        //model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        //model.addAttribute("famiglie", vistosiShopManager.findVist_famiglia(pars));
        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));

        model.addAttribute("collezioni", vistosiShopManager.getVist_cp_collezioni());

        //model.addAttribute("colori", vistosiShopManager.findVist_colori_vetro(pars));
        log.debug("modifica select colori");
        pars.put("ismenu", true);
        model.addAttribute("colori", vistosiShopManager.findVist_vetro(pars));
        model.addAttribute("elettrificazioni", vistosiShopManager.findVist_elettrificazioni(pars));
        model.addAttribute("partmet", vistosiShopManager.findVist_finit_mont(pars));

        model.addAttribute("stati", vistosiShopManager.getAvailableStates());

        //articoli
//        String cdalias =
        Map artPars = new HashMap();
        artPars.put("cdvisttp", pars.get("cdvisttp"));
        artPars.put("cdvistfam", pars.get("cdvistfam"));
        List<Mrp_arch_articoli> articoli = vistosiShopManager.selectMrp_arch_articoliByPars(pars);
        ArrayList<Mrp_arch_articoli> galleryArticoli = new ArrayList();

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        String path = "images/articoli/foto/";

        String cddesigner = null;

        for (Mrp_arch_articoli mrp_arch_articoli : articoli) {

            if (cddesigner == null && mrp_arch_articoli.getCddesigner() != null) {
                cddesigner = mrp_arch_articoli.getCddesigner();
            }
            try {
                String realPath = WebUtils.getRealPath(ctx.getServletContext(), path + mrp_arch_articoli.getCdartm().replace('/', '-') + ".jpg");
                log.debug(realPath);
                File f = new File(realPath);
                if (f.exists()) {
                    log.debug("esiste");
                    galleryArticoli.add(mrp_arch_articoli);
                }
            } catch (FileNotFoundException ex) {
            }
        }

        log.debug("cddesigner: " + cddesigner);
        if (cddesigner != null) {
            model.addAttribute("designer", vistosiShopManager.getDesigner(cddesigner));
        }

        for (Mrp_arch_articoli mrp_arch_articoli : galleryArticoli) {

            mrp_arch_articoli.setVist_tipi(vistosiShopManager.getVist_tipiByKey(mrp_arch_articoli.getCdvisttp()));
            mrp_arch_articoli.setVist_famiglia(vistosiShopManager.getVist_famigliaByKey(mrp_arch_articoli.getCdvistfam()));
            mrp_arch_articoli.setVist_var1(vistosiShopManager.getVist_var1ByKey(mrp_arch_articoli.getCdvistv1()));
            mrp_arch_articoli.setVist_var1(vistosiShopManager.getVist_var1ByKey(mrp_arch_articoli.getCdvistv1()));
            mrp_arch_articoli.setVist_var2(vistosiShopManager.getVist_var2ByKey(mrp_arch_articoli.getCdvistv2()));
            mrp_arch_articoli.setVist_var3(vistosiShopManager.getVist_var3ByKey(mrp_arch_articoli.getCdvistv3()));
            mrp_arch_articoli.setVist_finit_mont(vistosiShopManager.getVist_finit_montByKey(mrp_arch_articoli.getCdvistfinm()));
            mrp_arch_articoli.setVist_colori_vetro(vistosiShopManager.getVist_colori_vetroByKey(mrp_arch_articoli.getCdvistcolv()));
            mrp_arch_articoli.setVist_finit_vetro(vistosiShopManager.getVist_finit_vetroByKey(mrp_arch_articoli.getCdvistfinv()));

            if ((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
                    || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US"))) {

                Vist_elettrificazioni elet = vistosiShopManager.getVist_elettrificazioniByKey(mrp_arch_articoli.getCdvistelet());
                if (elet != null && elet.getCdul() != null) {
                    Vist_elettrificazioni altElet = vistosiShopManager.getVist_elettrificazioniByKey(elet.getCdul());
                    mrp_arch_articoli.setVist_elettrificazioni(altElet);
                }
            } else {
                mrp_arch_articoli.setVist_elettrificazioni(vistosiShopManager.getVist_elettrificazioniByKey(mrp_arch_articoli.getCdvistelet()));
            }

        }
//        for (Mrp_arch_articoli mrp_arch_articoli : galleryArticoli) {
//
//        }

        //model.addAttribute("articoliO", articoli);
        model.addAttribute("articoli", galleryArticoli);

        //technews
        Map techPars = new HashMap();
        techPars.put("cdvistfam", cdvistfam);
        techPars.put("cdvisttp", cdvisttp);
        techPars.put("postDate", new Date());

        List<Ep_posts> techNews = vistosiShopManager.getTechNews(techPars);
        model.addAttribute("techNewsList", techNews != null ? techNews.subList(0, Math.min(techNews.size(), 4)) : techNews);

    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("cdtipo") String cdvisttp, @PathVariable("cdfam") String cdvistfam,
            @RequestParam(value = "cdarti", required = false) String cdarti,
            @RequestParam(value = "cdartiric", required = false) String cdartiric,
            HttpServletRequest request, Model model) {

        SchedaArticolo scheda = new SchedaArticolo();

        //scheda.setArticolo(vistosiShopManager.getMrp_arch_articoliByKey(cdarti));
        model.addAttribute("scheda", scheda);

        Vist_famiglia famiglia = vistosiShopManager.getVist_famigliaByKey(cdvistfam);
        model.addAttribute("famiglia", famiglia);
        WebUtils.setSessionAttribute(request, "famigliaFilter", famiglia);

        getReferenceData(scheda, request, model, cdvisttp, cdvistfam);

        String cdalias = StringUtils.rightPad(cdvistfam, 5) + cdvisttp + "%";
        Map pars = new HashMap();
        pars.put("cdalias", cdalias);

        Cookie ckViewOff = WebUtils.getCookie(request, "filter_off");
        pars.put("fgpromo", ((ckViewOff != null && "S".equals(ckViewOff.getValue())) ? "S" : null));

        List<Mrp_arch_stato> availableStates = vistosiShopManager.getAvailableStates();
        List<String> statiFilter = new ArrayList<String>();
        for (Mrp_arch_stato mrp_arch_stato : availableStates) {
            String cookieName = "view-stato_" + mrp_arch_stato.getCdstato();

            Cookie ckView = WebUtils.getCookie(request, cookieName);
            if (ckView != null && "S".equals(ckView.getValue())) {
                statiFilter.add(mrp_arch_stato.getCdstato());
            }
        }
        if (!statiFilter.isEmpty()) {
            pars.put("statiFilterList", statiFilter);
        }
        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            pars.put("statiEscludedList", Collections.singletonList("ZEE"));
        }

        log.debug("cdalias: " + cdalias);
        log.debug("pars: " + pars);
        vistosiShopManager.addCdclasFilter(pars, request);

        List<Mrp_arch_articoli> modelli = vistosiShopManager.getModelliDis(pars);
        //shrink models list
        List<Mrp_arch_articoli> modellidis = new ArrayList<Mrp_arch_articoli>();
        if (modelli.size() > 1) {

            Mrp_arch_articoli artSeed = modelli.get(0);

//            String vvar1 = null;
//            String vvar2 = null;
//            String vvar3 = null;
//            String vcolv = null;
//            String vfinv = null;
//            String vfinm = null;
//            String velet = null;
//            String vist_filedis = null;
            for (int i = 1; i < modelli.size(); i++) {

                Mrp_arch_articoli mrp_arch_articoli = modelli.get(i);
                log.debug(mrp_arch_articoli.getVist_filedis());
                log.debug(artSeed.getVist_filedis());
                if (StringUtils.equalsIgnoreCase(mrp_arch_articoli.getVist_filedis(), artSeed.getVist_filedis())) {

                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv1(), mrp_arch_articoli.getCdvistv1())) {
                        artSeed.setCdvistv1(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv2(), mrp_arch_articoli.getCdvistv2())) {
                        artSeed.setCdvistv2(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv3(), mrp_arch_articoli.getCdvistv3())) {
                        artSeed.setCdvistv3(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistcolv(), mrp_arch_articoli.getCdvistcolv())) {
                        artSeed.setCdvistcolv(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinv(), mrp_arch_articoli.getCdvistfinv())) {
                        artSeed.setCdvistfinv("ign");
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinm(), mrp_arch_articoli.getCdvistfinm())) {
                        artSeed.setCdvistfinm(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistelet(), mrp_arch_articoli.getCdvistelet())) {
                        artSeed.setCdvistelet(null);
                    }

                } else {

                    modellidis.add(artSeed);
                    artSeed = mrp_arch_articoli;

                }

                if (i == modelli.size() - 1) {
                    modellidis.add(artSeed);
                }
            }
        } else {
            modellidis = modelli;
        }
        model.addAttribute("modellidis", modellidis);

        model.addAttribute("modelli", vistosiShopManager.getModelli(pars));

        if (cdarti != null) {
            Mrp_arch_articoli articolo = vistosiShopManager.getMrp_arch_articoliByKey(cdarti);
            model.addAttribute("articolo", articolo);

        }
        if (cdartiric != null) {
            model.addAttribute("isRicambio", vistosiShopManager.isRicambio(cdartiric));
            //model.addAttribute("ricambio",vistosiShopManager.getMrp_arch_articoliByKey(cdartiric));
            if (StringUtils.isNotEmpty(cdartiric)) {
                model.addAttribute("ricambio", vistosiShopManager.getRicambio(cdartiric));
            }
        }

        return "scheda";
    }

    @RequestMapping(value = "articolo.*", method = RequestMethod.GET)
    public String getArticolo(
            @PathVariable("cdtipo") String cdvisttp, @PathVariable("cdfam") String cdvistfam,
            @RequestParam(value = "cdvistv1", required = true) String cdvistv1,
            @RequestParam(value = "cdvistv2", required = true) String cdvistv2,
            @RequestParam(value = "cdvistv3", required = true) String cdvistv3,
            @RequestParam(value = "cdvistfinm", required = false) String cdvistfinm,
            @RequestParam(value = "cdvistfinv", required = false) String cdvistfinv,
            @RequestParam(value = "cdvistcolv", required = false) String cdvistcolv,
            @RequestParam(value = "cdvistelet", required = false) String cdvistelet,
            @RequestParam(value = "vist_filedis", required = false) String vist_filedis,
            @RequestParam(value = "cdartiric", required = false) String cdartiric,
            @RequestParam(value = "firstReq", required = false, defaultValue = "false") Boolean firstReq,
            HttpServletRequest request, Model model) {

        log.debug("firstReq=" + firstReq);
        Map pars = new HashMap();

        ShopUser user = null;
        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        vistosiShopManager.addCdclasFilter(pars, request);
//        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
//            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli listino
//
//            log.debug("filtro base: " + user.getCdclas_aFilterBase());
//        } else {
//            if(GeoIPInterceptor.getCountry(request).equals("US")){
//                pars.put("cdclas_aList", VistosiShopManager.DEFAULT_CDCLAS_A_US); //filtro solo articoli listino
//            }else{
//                pars.put("cdclas_aList", VistosiShopManager.DEFAULT_CDCLAS_A); //filtro solo articoli listino
//            }
//        }

        SchedaArticolo scheda = new SchedaArticolo();

        pars.put("cdvisttp", cdvisttp);
        pars.put("cdvistfam", cdvistfam);
        pars.put("cdvistv1", cdvistv1);
        pars.put("cdvistv2", cdvistv2);
        pars.put("cdvistv3", cdvistv3);

        //options
        log.debug("cdvistfinm=" + cdvistfinm);
        log.debug("cdvistfinv=" + cdvistfinv);
        log.debug("cdvistcolv=" + cdvistcolv);
        log.debug("cdvistelet=" + cdvistelet);
        log.debug("cdvistv1=" + cdvistv1);
        pars.put("cdvistfinm", cdvistfinm);
        pars.put("cdvistfinv", cdvistfinv);
        pars.put("cdvistcolv", cdvistcolv);

        pars.put("cdvistelet", cdvistelet);
        
        if (((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
                || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US")))
                && StringUtils.isNotBlank(cdvistelet)) {

                Vist_elettrificazioni vist_elettrificazioniAlternative = vistosiShopManager.getVist_elettrificazioniAlternative(cdvistelet);
                if(vist_elettrificazioniAlternative!=null){
                    pars.put("cdvistelet", vist_elettrificazioniAlternative.getCdvistelet());
                }            
        }
        
        
//        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && StringUtils.isNotEmpty(cdvistelet)) {
//            if(user.getIsSpecList()){
//                Vist_elettrificazioni vist_elettrificazioniAlternative = vistosiShopManager.getVist_elettrificazioniAlternative(cdvistelet);
//                if(vist_elettrificazioniAlternative!=null){
//                    pars.put("cdvistelet", vist_elettrificazioniAlternative.getCdvistelet());
//                }
//            }else{
//                /*Vist_elettrificazioni vist_elettrificazioniAlternative = vistosiShopManager.getVist_elettrificazioniAlternative(cdvistelet);
//                if(vist_elettrificazioniAlternative!=null){
//                    pars.put("cdvistelet", vist_elettrificazioniAlternative.getCdvistelet());
//                } */               
//            }
//        }

        
        pars.put("vist_filedis", vist_filedis);
        pars.put("fgweb", "S");

        Cookie ckViewOff = WebUtils.getCookie(request, "filter_off");
        pars.put("fgpromo", ((ckViewOff != null && "S".equals(ckViewOff.getValue())) ? "S" : null));

        List<Mrp_arch_stato> availableStates = vistosiShopManager.getAvailableStates();
        List<String> statiFilter = new ArrayList<String>();
        for (Mrp_arch_stato mrp_arch_stato : availableStates) {
            String cookieName = "view-stato_" + mrp_arch_stato.getCdstato();

            Cookie ckView = WebUtils.getCookie(request, cookieName);
            if (ckView != null && "S".equals(ckView.getValue())) {
                statiFilter.add(mrp_arch_stato.getCdstato());
            }
        }
        if (!statiFilter.isEmpty()) {
            pars.put("statiFilterList", statiFilter);
        }
        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            pars.put("statiEscludedList", Collections.singletonList("ZEE"));
        }

        List<Vist_vetro> vist_vetro = vistosiShopManager.findVist_vetro(pars);

        for (Vist_vetro vist_vetro_item : vist_vetro) {
            Map stpars = new HashMap(pars);
            if (vist_vetro_item.getCol().getCdvistcolv() != null) {
                stpars.put("cdvistcolv", vist_vetro_item.getCol().getCdvistcolv());
            } else {
                stpars.remove("cdvistcolv");
            }
            if (vist_vetro_item.getFin().getCdvistfinv() != null) {
                stpars.put("cdvistfinv", vist_vetro_item.getFin().getCdvistfinv());
            } else {
                stpars.remove("cdvistfinv");
            }

            stpars.put("fgpromo", "S");
            int countOff = vistosiShopManager.countOfferta(stpars);
            if (countOff > 0) {
                if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                    Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                    vist_vetro_item.setStato(statoOff);
                }
            } else {
                vistosiShopManager.addCdclasFilter(stpars, request);
                List<Mrp_arch_stato> stlist = vistosiShopManager.selectDistinctByPars(stpars);

                if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                    vist_vetro_item.setStato(stlist.get(0));
                }
            }
        }
        scheda.setVetro(vist_vetro);

        //scheda.setColori_vetro(vistosiShopManager.findVist_colori_vetro(pars));
        log.debug("elet filters: " + pars);
        List<Vist_elettrificazioni> elettrificazioni = vistosiShopManager.findVist_elettrificazioni(pars);

        if ((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
                || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US"))) {

            String cdulPrec = null;
            for (Iterator<Vist_elettrificazioni> it = elettrificazioni.iterator(); it.hasNext();) {
                Vist_elettrificazioni vist_elettrificazioni = it.next();
                if (cdulPrec != null && cdulPrec.equals(vist_elettrificazioni.getCdul())) {
                    it.remove();
                } else {
                    cdulPrec = vist_elettrificazioni.getCdul();

                    Map stpars = new HashMap(pars);
                    stpars.put("cdvistelet", vist_elettrificazioni.getCdvistelet());

                    stpars.put("fgpromo", "S");
                    int countOff = vistosiShopManager.countOfferta(stpars);
                    if (countOff > 0) {
                        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                            Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                            vist_elettrificazioni.setStato(statoOff);
                        }
                    } else {
                        vistosiShopManager.addCdclasFilter(stpars, request);
                        List<Mrp_arch_stato> stlist = vistosiShopManager.selectDistinctByPars(stpars);

                        if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                            vist_elettrificazioni.setStato(stlist.get(0));
                        }
                    }
                }

            }
        } else {
            for (Vist_elettrificazioni vist_elettrificazioni : elettrificazioni) {
                Map stpars = new HashMap(pars);
                stpars.put("cdvistelet", vist_elettrificazioni.getCdvistelet());

                stpars.put("fgpromo", "S");
                int countOff = vistosiShopManager.countOfferta(stpars);
                if (countOff > 0) {
                    if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                        Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                        vist_elettrificazioni.setStato(statoOff);
                    }
                } else {
                    vistosiShopManager.addCdclasFilter(stpars, request);
                    List<Mrp_arch_stato> stlist = vistosiShopManager.selectDistinctByPars(stpars);

                    if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                        vist_elettrificazioni.setStato(stlist.get(0));
                    }
                }
            }
        }

        scheda.setElettrificazioni(elettrificazioni);

        List<Vist_finit_mont> vist_finit_mont = vistosiShopManager.findVist_finit_mont(pars);

        for (Vist_finit_mont vist_finit_mont_item : vist_finit_mont) {
            Map stpars = new HashMap(pars);
            stpars.put("cdvistfinm", vist_finit_mont_item.getCdvistfinm());

            stpars.put("fgpromo", "S");
            int countOff = vistosiShopManager.countOfferta(stpars);
            if (countOff > 0) {
                if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                    Mrp_arch_stato statoOff = new Mrp_arch_stato(51, 153, 51);
                    vist_finit_mont_item.setStato(statoOff);
                }
            } else {
                vistosiShopManager.addCdclasFilter(stpars, request);
                List<Mrp_arch_stato> stlist = vistosiShopManager.selectDistinctByPars(stpars);

                if (!stlist.isEmpty() && stlist.size() == 1 && "S".equals(stlist.get(0).getVist_fgrgb())) {
                    vist_finit_mont_item.setStato(stlist.get(0));
                }
            }
        }
        scheda.setFinit_mont(vist_finit_mont);

        scheda.setFinit_vetro(vistosiShopManager.findVist_finit_vetro(pars));
        scheda.setVarianti1(vistosiShopManager.findVist_var1(pars));
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("xmlcontent");
//        mav.addObject("sampleContentList", "doh!");

        if (!firstReq) {

            if (((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
                    || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US")))
                    && StringUtils.isNotBlank(cdvistelet)) {
                Vist_elettrificazioni eletSpec = vistosiShopManager.getVist_elettrificazioniByKey(cdvistelet);

                log.debug("eletSpec: " + eletSpec);
                Map ulPars = new HashMap();
                ulPars.putAll(pars);

                pars.put("cdvistelet", eletSpec.getCdul());

                pars.put("cdclas_aList", (user != null) ? user.getCdclas_aFilterBase() : vistosiShopManager.DEFAULT_CDCLAS_A_US);

                List<Mrp_arch_articoli> arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);

                if (!arts.isEmpty()) {
                    scheda.setArticoli(arts);
                    if (arts.size() == 1) {

                        if (StringUtils.isBlank(cdvistcolv) && StringUtils.isBlank(cdvistfinv)) {

                            Vist_elettrificazioni altElet = vistosiShopManager.getVist_elettrificazioniAlternative(cdvistelet);
                            pars.put("cdvistelet", (altElet != null) ? altElet.getCdvistelet() : cdvistelet);

                            List<Mrp_arch_articoli> eu_arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);

                            if (eu_arts.size() > 1) {
                                scheda.setArticoli(eu_arts);
                            } else {
                                scheda.setArticolo(arts.get(0));
                                log.debug("get etichette");
                                //scheda.setEtichette(vistosiShopManager.getEtichette(scheda.getArticolo().getCdarti()));
                                log.debug("get ricambi");
                                scheda.setRicambi(vistosiShopManager.getRicambi(scheda.getArticolo().getCdarti()));
                                //scheda.setRicambio(vistosiShopManager.getMrp_arch_articoliByKey(cdartiric));
                                if (StringUtils.isNotEmpty(cdartiric)) {
                                    scheda.setRicambio(vistosiShopManager.getRicambio(scheda.getArticolo().getCdarti(), cdartiric));
                                }
                            }
                        } else {
                            scheda.setArticolo(arts.get(0));
                            //scheda.setEtichette(vistosiShopManager.getEtichette(scheda.getArticolo().getCdarti()));
                            scheda.setRicambi(vistosiShopManager.getRicambi(scheda.getArticolo().getCdarti()));
                            //scheda.setRicambio(vistosiShopManager.getMrp_arch_articoliByKey(cdartiric));
                            if (StringUtils.isNotEmpty(cdartiric)) {
                                scheda.setRicambio(vistosiShopManager.getRicambio(scheda.getArticolo().getCdarti(), cdartiric));
                            }
                        }
                    }
                } else {
                    log.debug("per filtro UL articolo non trovato, vengono aggiunti i filtri listino europa");
                    Vist_elettrificazioni altElet = vistosiShopManager.getVist_elettrificazioniAlternative(cdvistelet);
                    pars.put("cdvistelet", (altElet != null) ? altElet.getCdvistelet() : cdvistelet);

                    pars.put("cdclas_aList", (user != null) ? user.getCdclas_aFilter() : vistosiShopManager.DEFAULT_CDCLAS_A_EUUS);

                    arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);
                    scheda.setArticoli(arts);
                    if (arts.size() == 1) {
                        scheda.setArticolo(arts.get(0));
                        log.debug("get etichette");
                        //scheda.setEtichette(vistosiShopManager.getEtichette(scheda.getArticolo().getCdarti()));
                        log.debug("get ricambi");
                        scheda.setRicambi(vistosiShopManager.getRicambi(scheda.getArticolo().getCdarti()));
                        //scheda.setRicambio(vistosiShopManager.getMrp_arch_articoliByKey(cdartiric));
                        if (StringUtils.isNotEmpty(cdartiric)) {
                            scheda.setRicambio(vistosiShopManager.getRicambio(scheda.getArticolo().getCdarti(), cdartiric));
                        }
                    }
                }

            } else {
                if(scheda.getElettrificazioni().size()==1) {
                    pars.put("cdvistelet", scheda.getElettrificazioni().get(0).getCdvistelet());
                }
                List<Mrp_arch_articoli> arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);
                scheda.setArticoli(arts);
                if (arts.size() == 1) {
                    scheda.setArticolo(arts.get(0));
                    log.debug("get etichette");
                    //scheda.setEtichette(vistosiShopManager.getEtichette(scheda.getArticolo().getCdarti()));
                    log.debug("get ricambi");
                    scheda.setRicambi(vistosiShopManager.getRicambi(scheda.getArticolo().getCdarti()));
                    //scheda.setRicambio(vistosiShopManager.getMrp_arch_articoliByKey(cdartiric));
                    if (StringUtils.isNotEmpty(cdartiric)) {
                        scheda.setRicambio(vistosiShopManager.getRicambio(scheda.getArticolo().getCdarti(), cdartiric));
                    }
                }

            }

        }        

        if (scheda.getArticolo() != null) {

            WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

            if (scheda.getArticolo().getCdvistv1() != null) {
                scheda.getArticolo().setVist_var1(vistosiShopManager.getVist_var1ByKey(scheda.getArticolo().getCdvistv1()));
            }
            if (scheda.getArticolo().getCdvistv2() != null) {
                scheda.getArticolo().setVist_var2(vistosiShopManager.getVist_var2ByKey(scheda.getArticolo().getCdvistv2()));
            }
            if (scheda.getArticolo().getCdvistv3() != null) {
                scheda.getArticolo().setVist_var3(vistosiShopManager.getVist_var3ByKey(scheda.getArticolo().getCdvistv3()));
            }
            if (scheda.getArticolo().getCdvistelet() != null) {
                scheda.getArticolo().setVist_elettrificazioni(vistosiShopManager.getVist_elettrificazioniByKey(scheda.getArticolo().getCdvistelet()));
            }
            
            //collezione
            Vist_cp_collezioni vist_cp_collezioniByKey = vistosiShopManager.getVist_cp_collezioniByKey(scheda.getArticolo().getCdvistccol());
            scheda.setVist_cp_collezioni(vist_cp_collezioniByKey);

            //ricerca modelli
            Vist_famiglia vist_fam = vistosiShopManager.getVist_famigliaByKey(scheda.getArticolo().getCdvistfam());

            log.debug("filedis >>>" + scheda.getArticolo().getVist_filedis());

            String path_modello = "fileresources/models";
            String path_3D = path_modello + "/3D/";
            //String nome_modello = vist_fam.getCdvistfam_mPad() + scheda.getArticolo().getCdvisttp() + scheda.getArticolo().getCdvistv1Pad() + scheda.getArticolo().getCdvistv2Pad() + scheda.getArticolo().getCdvistv3Pad() +"-";
            String nome_modello = scheda.getArticolo().getVist_filedis();
            //igs
            String igs = path_3D + nome_modello + ".zip";
            try {
                String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), igs);
                File f = new File(path_to_filemodel);
                log.debug(path_to_filemodel);
                scheda.getArticolo().setModel3D_igs(igs);
                if (f.exists()) {
                    scheda.getArticolo().setModel3D_igsExists(Boolean.TRUE);
                }
            } catch (FileNotFoundException ex) {
                log.error("file " + igs + " non trovato");
            }
            //eprt
//            String eprt = path_3D + nome_modello + ".EPRT";
//            try {
//                String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), eprt);
//                File f = new File(path_to_filemodel);
//                log.debug(path_to_filemodel);
//                scheda.getArticolo().setModel3D_eprt(eprt);
//                if (f.exists()) {
//                    scheda.getArticolo().setModel3D_eprtExists(Boolean.TRUE);
//                }
//            } catch (FileNotFoundException ex) {
//                log.error("file " + eprt + " non trovato");
//            }
            //easm
            String easm = path_3D + nome_modello + ".EASM";
            try {
                String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), easm);
                File f = new File(path_to_filemodel);
                log.debug(path_to_filemodel);
                scheda.getArticolo().setModel3D_easm(easm);
                if (f.exists()) {
                    scheda.getArticolo().setModel3D_easmExists(Boolean.TRUE);
                }
            } catch (FileNotFoundException ex) {
                log.error("file " + easm + " non trovato");
            }

            String path_2D = path_modello + "/2D/";
            //dwg cm
            RequestContext rc = new RequestContext(request);
            String dwg_vers = "cm/";
            //if ("en".equals(rc.getLocale().getLanguage())) {
            if ((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
                    || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US"))) {
                dwg_vers = "po/";
            }
            String dwg = path_2D + dwg_vers + nome_modello + ".dwg";
            try {
                String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), dwg);
                File f = new File(path_to_filemodel);
                log.debug(path_to_filemodel);
                scheda.getArticolo().setModel2D_dwg(dwg);
                if (f.exists()) {
                    scheda.getArticolo().setModel2D_dwgExists(Boolean.TRUE);
                }
            } catch (FileNotFoundException ex) {
                log.error("file " + dwg + " non trovato");
            }

            //file certificati
            Vist_articoli_datiextra datiExtra = vistosiShopManager.getDatiExtraByCdartm(scheda.getArticolo().getCdartm());
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
                            scheda.getArticolo().setVist_articoli_img(vist_articoli_img);
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

                            String realPath = WebUtils.getRealPath(ctx.getServletContext(), path_cert + nomefile);

                            File f = new File(realPath);
                            if (f.exists()) {
                                Map<String, String> certMap = new HashMap<String, String>();
                                certMap.put("img", nomeimg);
                                certMap.put("file", path_cert + nomefile);

                                scheda.getArticolo().getCertList().addLast(certMap);
                            } else {
                                //TODO???
                            }
                        }
                    }

                    //file classe energetica
                    String fieldName = "arwCertificazione" + 10;
                    String nomefile = BeanUtils.getSimpleProperty(datiExtra, fieldName);

                    if (StringUtils.isNotBlank(nomefile)) {
                        scheda.getArticolo().setEnergyClass(nomefile);
                    }

                    scheda.getArticolo().setEletDatiExtraMap(vistosiShopManager.getDatiExtraLampMap(datiExtra, ctx));

                    //check esistenza specsheet                   
                    if (vistosiShopManager.checkSpecsheetExists(scheda.getArticolo(), ctx, rc)) {
                        scheda.getArticolo().setSpecsheetExists(true);
                    }

                    //etichette dalla datiextra invece della vist_etichette
                    String pathimg = "images/articoli/dati/";
                    List<String> etichetteDatiExtra = new ArrayList<String>();
                    for (int i = 1; i < 10; i++) {

                        try {
                            String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbolo" + i);

                            nomeimg = StringUtils.substringBetween(nomeimg, "\\", ".");
                            nomeimg = nomeimg + ".gif";
                            String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                            File f = new File(realPath);
                            if (f.exists()) {
                                etichetteDatiExtra.add(pathimg + nomeimg);
                            }
                        } catch (FileNotFoundException ex) {
                            //log.debug("not exists");
                        }
                    }
                    scheda.setEtichetteDatiExtra(etichetteDatiExtra);

//
//                    HashMap<String, String> tipoLampadine = new HashMap<String, String>();
//                    tipoLampadine.put("A", "ALO");
//                    tipoLampadine.put("F", "FL");
//                    tipoLampadine.put("L", "LED");
//                    tipoLampadine.put("E", "ES");
//                    tipoLampadine.put("I", "IOD");
//
//                    String rootRes = "/images/articoli/specsheetres/";
//                    String pathimg = rootRes + "dati/";
//                    //path immagini lampadine
//                    String pathlampimg = rootRes + "lampadine/";
//
//                    Map elettDatiExtra = new HashMap();
//                    ArrayList<Map> lampadine = new ArrayList<Map>();
//
//                    //lampadina principale
//                    int[] idxs = {1, 2, 7, 8};
//                    for (int pos : idxs) {
//
//                        Map lamp = new HashMap();
//
//                        try {
//                            String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + pos);
//
//                            flag = StringUtils.trimToEmpty(flag);
//
//                            if (StringUtils.equals(flag, "S")) {
//
//                                String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + pos);
//                                String[] split = nomeimg.split("\\\\");
//                                String acronimoLampada = tipoLampadine.get(split[0]) + ".jpg";
//                                nomeimg = split[1];
//
//                                String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(), pathimg + acronimoLampada);
//                                File fAcronimo = new File(realPathAcronimo);
//
//                                String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
//                                File f = new File(realPath);
//
//                                if (fAcronimo.exists()) {
//                                    lamp.put("acro", pathimg + acronimoLampada);
//                                }
//                                if (f.exists()) {
//                                    lamp.put("img", pathlampimg + nomeimg);
//                                }
//
//                                //descrizione
//                                //String tipoAttacco = BeanUtils.getSimpleProperty(datiExtra, "arwTipoAttacco" + i); 
//                                //TODO sostituirlo con l'icona della lampadina quando le passeranno
//                                String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + pos);
//                                String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + pos);
//                                //String voltaggio = BeanUtils.getSimpleProperty(datiExtra, "arwVoltaggio" + i);
//
//                                String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza);
//                                descrizione = StringUtils.trim(descrizione);
//
//                                if (StringUtils.isNotBlank(descrizione)) {
//                                    lamp.put("descr", descrizione);
//                                }
//
//                                lampadine.add(lamp);
//                            }
//                        } catch (FileNotFoundException ex) {
//                            //log.debug("not exists");
//                        } catch (IllegalAccessException ex) {
//                            Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (InvocationTargetException ex) {
//                            Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (NoSuchMethodException ex) {
//                            Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//
//                    if (!lampadine.isEmpty()) {
//
//                        elettDatiExtra.put("main",  (ArrayList<Map>)lampadine.clone());
//
//                        scheda.setEletDatiExtraMap(elettDatiExtra);
//
//                        int[] altIdxs = {3, 4, 9, 10, 5, 6, 11, 12};
//
//                        int counter = 1;
//                        boolean alt = false;
//                        boolean idxPresent = false;
//
//                        lampadine.clear();
//
//                        for (int bidx : altIdxs) {
//
//                            if (bidx == 5) {
//                                counter = 2;
//                            }
//
//                            Map lamp = new HashMap();
//
//                            try {
//                                String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + bidx);
//
//                                flag = StringUtils.trimToEmpty(flag);
//
//                                if (StringUtils.equals(flag, "S")) {
//
//                                    String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + bidx);
//                                    String[] split = nomeimg.split("\\\\");
//                                    String acronimoLampada = tipoLampadine.get(split[0]) + ".jpg";
//                                    nomeimg = split[1];
//
//                                    String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(), pathimg + acronimoLampada);
//                                    File fAcronimo = new File(realPathAcronimo);
//
//                                    String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
//                                    File f = new File(realPath);
//
//                                    if (fAcronimo.exists()) {
//                                        lamp.put("acro", pathimg + acronimoLampada);
//                                    }
//
//                                    if (f.exists()) {
//                                        lamp.put("img", pathlampimg + nomeimg);
//                                    }
//
//                                    //descrizione
//                                    String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + bidx);
//                                    String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + bidx);
//                                    String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza);
//                                    descrizione = StringUtils.trim(descrizione);
//
//                                    if (StringUtils.isNotBlank(descrizione)) {
//                                        lamp.put("descr", descrizione);
//                                    }
//
//                                    lampadine.add(lamp);
//
//                                    if (!lampadine.isEmpty()) {
//
//                                        elettDatiExtra.put("alt" + counter, (ArrayList<Map>)lampadine.clone());
//                                        lampadine.clear();
//                                    }
//
//                                }
//                            } catch (FileNotFoundException ex) {
//                                //log.debug("not exists");
//                            } catch (IllegalAccessException ex) {
//                                Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (InvocationTargetException ex) {
//                                Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (NoSuchMethodException ex) {
//                                Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        }
//
//                    }
                } catch (FileNotFoundException ex) {

                } catch (IllegalAccessException ex) {
                    Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        //technews
        Map techPars = new HashMap();
        techPars.put("cdvistfam", cdvistfam);
        techPars.put("cdvisttp", cdvisttp);
        if (StringUtils.isNotEmpty(cdvistelet)) {
            model.addAttribute("cdvisteletFilter", cdvistelet);
            techPars.put("cdvistelet", cdvistelet);
        }
        techPars.put("postDate", new Date());

        List<Ep_posts> techNews = vistosiShopManager.getTechNews(techPars);
        scheda.setTechNewsList(techNews != null ? techNews.subList(0, Math.min(techNews.size(), 4)) : techNews);

        model.addAttribute("scheda", scheda);

        return "articolo";

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
                    Logger.getLogger(SchedaArticoloController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return url;
    }

}
