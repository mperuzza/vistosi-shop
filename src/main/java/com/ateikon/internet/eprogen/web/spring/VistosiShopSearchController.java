/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.ImgShop;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.*;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import com.ateikon.internet.eprogen.web.utils.SimplePermutator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.comparators.TransformingComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
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
@RequestMapping("/search*")
public class VistosiShopSearchController {

    private Log log = LogFactory.getLog(this.getClass());
    private int pageSize = -1;
    private static final String NULL_PAR = "-";
    //private List<String> DEFAULT_CDCLAS_A = Arrays.asList("L");
    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }

    private void searchArticoli(Map pars, HttpServletRequest request, Model model) {

        int page = ServletRequestUtils.getIntParameter(request, "page", 1);
        String sort = ServletRequestUtils.getStringParameter(request, "sort", "dsarti");
        String dir = ServletRequestUtils.getStringParameter(request, "dir", "desc");
        vistosiShopManager.addCdclasFilter(pars, request);
        model.addAttribute("theList", vistosiShopManager.selectArticoliByExamplePag(pars, sort, dir, page, pageSize));
    }

    private void searchFamiglie(Map pars, HttpServletRequest request, Model model) {

        log.debug("search by famiglie");

        int page = ServletRequestUtils.getIntParameter(request, "page", 1);
        String sort = ServletRequestUtils.getStringParameter(request, "sort", "dsarti");
        String dir = ServletRequestUtils.getStringParameter(request, "dir", "desc");
        vistosiShopManager.addCdclasFilter(pars, request);
        vistosiShopManager.addToggleStateZEEFilter(pars, request);        
          
        model.addAttribute("theList", vistosiShopManager.selectFamiglieByExamplePag(pars, sort, dir, page, pageSize));
        model.addAttribute("famView", true);
    }

    private void searchCollezioni(Map pars, HttpServletRequest request, Model model) {

        log.debug("search by collezioni");

        int page = ServletRequestUtils.getIntParameter(request, "page", 1);
        String sort = ServletRequestUtils.getStringParameter(request, "sort", "dsarti");
        String dir = ServletRequestUtils.getStringParameter(request, "dir", "desc");
        vistosiShopManager.addCdclasFilter(pars, request);
        vistosiShopManager.addToggleStateZEEFilter(pars, request);
        model.addAttribute("theList", vistosiShopManager.selectCollezioniByExamplePag(pars, sort, dir, page, pageSize));
        model.addAttribute("colView", true);
    }

    private void searchTipi(Map pars, HttpServletRequest request, Model model) {

        log.debug("search by tipi");

        int page = ServletRequestUtils.getIntParameter(request, "page", 1);
        String sort = ServletRequestUtils.getStringParameter(request, "sort", "dsarti");
        String dir = ServletRequestUtils.getStringParameter(request, "dir", "desc");
        vistosiShopManager.addCdclasFilter(pars, request);
        vistosiShopManager.addToggleStateZEEFilter(pars, request);
        model.addAttribute("theList", vistosiShopManager.selectTipiByExamplePag(pars, sort, dir, page, pageSize));
        model.addAttribute("tipiView", true);
    }

    private void search(Map pars, HttpServletRequest request, Model model) {

        if (pars.get("cdvisttp") != null) {
            searchFamiglie(pars, request, model);
        } else if (pars.get("cdvistfam") != null) {
            searchTipi(pars, request, model);
        } else if (pars.get("dsvistccol") != null) {
            searchCollezioni(pars, request, model);
        }


    }

//    @RequestMapping(value="/famiglia/{cdfam}/colore/{cdcol}", method=RequestMethod.GET)
//    public String findByFamCol(@PathVariable("cdvisttp") String cdvisttp,
//                            @PathVariable("cdfam") String cdvistfam,
//                            @PathVariable("cdcol") String cdvistcolv,
//                           Model model,
//                           HttpServletRequest request){
//
//        log.debug("search per famiglia-colore: " + cdvistfam);
//
//
//        return findByPars(cdvisttp, cdvistfam, cdvistcolv, null, model, request);
//    }
//     @RequestMapping(value="/colore/{cdcol}", method=RequestMethod.GET)
//    public String findByColore(
//                            @PathVariable("cdvisttp") String cdvisttp,
//                            @PathVariable("cdcol") String cdvistcolv,
//                           Model model,
//                           HttpServletRequest request){
//
//        log.debug("search per colore: " + cdvistcolv);
//
//
//        return findByPars(cdvisttp, null, cdvistcolv, null, model, request);
//    }
    @RequestMapping(value = "/met/{cdmet}", method = RequestMethod.GET)
    public String findByMet(@PathVariable("cdvisttp") String cdvisttp,
            @PathVariable("cdmet") String cdvistfinm,
            Model model,
            HttpServletRequest request) {

        log.debug("search per metallo: " + cdvisttp);


        return findByPars(cdvisttp, null, null, null, null, cdvistfinm, null, model, request);
    }

    @RequestMapping(value = "/tipologia/{cdvisttp}/famiglia/{cdvistfam}", method = RequestMethod.GET)
    public String findByTipoFam(@PathVariable("cdvisttp") String cdvisttp,
            @PathVariable("cdvistfam") String cdvistfam,
            @RequestParam(value = "dsvistccol", required = false) String dsvistccol,
            @RequestParam(value = "cdvistcolv", required = false) String cdvistcolv,
            @RequestParam(value = "cdvistfinv", required = false) String cdvistfinv,
            @RequestParam(value = "cdvistfinm", required = false) String cdvistfinm,
            @RequestParam(value = "cdvistelet", required = false) String cdvistelet,
            Model model,
            HttpServletRequest request) {

        log.debug("search per tipologia: " + cdvisttp);
        log.debug("e per famiglia: " + cdvistfam);

        model.addAttribute("rootFilter", cdvisttp);

        if (NULL_PAR.equals(cdvisttp)) {
            cdvisttp = null;
        }
        if (NULL_PAR.equals(cdvistfam)) {
            cdvistfam = null;
        }

        return findByPars(cdvisttp, cdvistfam, dsvistccol, cdvistcolv, cdvistfinv, cdvistfinm, cdvistelet, model, request);
    }

    @RequestMapping(value = "/tipologia/{cdvisttp}/collezione/{dsvistccol}/famiglia/{cdvistfam}**", method = RequestMethod.GET)
    public String findBy(@PathVariable("cdvisttp") String cdvisttp,
            @PathVariable("cdvistfam") String cdvistfam,
            @PathVariable("dsvistccol") String dsvistccol,
            @RequestParam(value = "cdvistcolv", required = false) String cdvistcolv,
            @RequestParam(value = "cdvistfinv", required = false) String cdvistfinv,
            @RequestParam(value = "cdvistfinm", required = false) String cdvistfinm,
            @RequestParam(value = "cdvistelet", required = false) String cdvistelet,
            Model model,
            HttpServletRequest request) {

        log.debug("search per tipologia: " + cdvisttp);
        log.debug("e per famiglia: " + cdvistfam);

        model.addAttribute("rootFilter", cdvisttp);

        model.addAttribute("coll", dsvistccol);
        model.addAttribute("fam", cdvistfam);

        if (NULL_PAR.equals(cdvisttp)) {
            cdvisttp = null;
        }
        if (NULL_PAR.equals(dsvistccol)) {
            dsvistccol = null;
        }
        if (NULL_PAR.equals(cdvistfam)) {
            cdvistfam = null;
        }

        return findByPars(cdvisttp, cdvistfam, dsvistccol, cdvistcolv, cdvistfinv, cdvistfinm, cdvistelet, model, request);
    }

    @RequestMapping(value = "/famiglia/{cdvistfam}/tipologia/{cdvisttp}", method = RequestMethod.GET)
    public String findByFamTipo(@PathVariable("cdvisttp") String cdvisttp,
            @PathVariable("cdvistfam") String cdvistfam,
            @RequestParam(value = "dsvistccol", required = false) String dsvistccol,
            @RequestParam(value = "cdvistcolv", required = false) String cdvistcolv,
            @RequestParam(value = "cdvistfinv", required = false) String cdvistfinv,
            @RequestParam(value = "cdvistfinm", required = false) String cdvistfinm,
            @RequestParam(value = "cdvistelet", required = false) String cdvistelet,
            Model model,
            HttpServletRequest request) {

        log.debug("search per famiglia: " + cdvistfam);
        log.debug("e per tipologia: " + cdvisttp);

        model.addAttribute("rootFilter", cdvistfam);

        return findByPars(cdvisttp, cdvistfam, dsvistccol, cdvistcolv, cdvistfinv, cdvistfinm, cdvistelet, model, request);
    }

    @RequestMapping(value = "*famiglia/{cdvistfam}", method = RequestMethod.GET)
    public String findByFam(@PathVariable("cdvistfam") String cdvistfam,
            @RequestParam(value = "dsvistccol", required = false) String dsvistccol,
            @RequestParam(value = "cdvistcolv", required = false) String cdvistcolv,
            @RequestParam(value = "cdvistfinv", required = false) String cdvistfinv,
            @RequestParam(value = "cdvistfinm", required = false) String cdvistfinm,
            @RequestParam(value = "cdvistelet", required = false) String cdvistelet,
            Model model,
            HttpServletRequest request) {

        log.debug("search per famiglia: " + cdvistfam);

        model.addAttribute("rootFilter", cdvistfam);

        return findByPars(null, cdvistfam, dsvistccol, cdvistcolv, cdvistfinv, cdvistfinm, cdvistelet, model, request);
    }

    @RequestMapping(value = "*tipologia/{cdvisttp}", method = RequestMethod.GET)
    public String findByTipo(@PathVariable("cdvisttp") String cdvisttp,
            @RequestParam(value = "dsvistccol", required = false) String dsvistccol,
            @RequestParam(value = "cdvistcolv", required = false) String cdvistcolv,
            @RequestParam(value = "cdvistfinv", required = false) String cdvistfinv,
            @RequestParam(value = "cdvistfinm", required = false) String cdvistfinm,
            @RequestParam(value = "cdvistelet", required = false) String cdvistelet,
            Model model,
            HttpServletRequest request) {

        log.debug("search per tipologia: " + cdvisttp);
        log.debug("search per tipologia cdvistcolv: " + cdvistcolv);
        log.debug("search per tipologia cdvistfinm: " + cdvistfinm);

        model.addAttribute("rootFilter", cdvisttp);

        return findByPars(cdvisttp, null, dsvistccol, cdvistcolv, cdvistfinv, cdvistfinm, cdvistelet, model, request);
    }

    @RequestMapping(value = "/immagininontrovate", method = RequestMethod.GET)
    public String findImagesNotFound(Model model,
            HttpServletRequest request) {

        List<ImgShop> thumb_tipfam = new ArrayList<ImgShop>();


        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        String path = "";

        HashMap pars = new HashMap();

        pars.put("fgweb", "S");

        List<Vist_tipi> tipologie = vistosiShopManager.findVist_tipi(pars);


        for (Vist_tipi vist_tipi : tipologie) {

            pars.put("cdvisttp", StringUtils.trimToNull(vist_tipi.getCdvisttp()));

            List<Vist_famiglia> famiglia = vistosiShopManager.findVist_famiglia(pars);

            for (Vist_famiglia vist_famiglia : famiglia) {

                String nome_immagine = vist_famiglia.getCdvistfam_m() + "-" + vist_tipi.getCdvisttp_m() + ".jpg";
                try {
                    path = "images/famiglie/";
                    String realPath = WebUtils.getRealPath(ctx.getServletContext(), path + nome_immagine);
                    File f = new File(realPath);
                    if (!f.exists()) {
                        ImgShop img = new ImgShop();
                        img.setNome_immagine(nome_immagine);
                        img.setPath_previsto(path);
                        thumb_tipfam.add(img);
                    }
                } catch (FileNotFoundException ex) {
                }

                String cdalias = StringUtils.rightPad(vist_famiglia.getCdvistfam(), 5) + vist_tipi.getCdvisttp() + "%";
                Map modpars = new HashMap();
                modpars.put("cdalias", cdalias);
                modpars.put("fgweb", "S");
                vistosiShopManager.addCdclasFilter(modpars, request);
                List<Mrp_arch_articoli> modelli = vistosiShopManager.getModelli(modpars);

                for (Mrp_arch_articoli modello : modelli) {

                    nome_immagine = vist_famiglia.getCdvistfam_mPad() + vist_tipi.getCdvisttp_m() + modello.getCdvistv1Pad() + modello.getCdvistv2Pad() + modello.getCdvistv3Pad() + "-.jpg";
                    try {
                        path = "images/articoli/disegnitecnici/";
                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), path + nome_immagine);
                        File f = new File(realPath);
                        if (!f.exists()) {
                            ImgShop img = new ImgShop();
                            img.setNome_immagine(nome_immagine);
                            img.setPath_previsto(path + " e thumb");
                            thumb_tipfam.add(img);
                        }
                    } catch (FileNotFoundException ex) {
                    }
                }

                Map artPars = new HashMap();
                artPars.put("cdvisttp", vist_tipi.getCdvisttp());
                artPars.put("cdvistfam", vist_famiglia.getCdvistfam());
                artPars.put("fgweb", "S");
                List<Mrp_arch_articoli> articoli = vistosiShopManager.selectMrp_arch_articoliByPars(artPars);
                ArrayList<Mrp_arch_articoli> galleryArticoli = new ArrayList();

                path = "images/articoli/foto/";

                for (Mrp_arch_articoli mrp_arch_articoli : articoli) {

                    nome_immagine = mrp_arch_articoli.getCdartm().replace('/', '-') + ".jpg";
                    try {

                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), path + nome_immagine);
                        File f = new File(realPath);
                        if (!f.exists()) {
                            ImgShop img = new ImgShop();
                            img.setNome_immagine(nome_immagine);
                            img.setPath_previsto(path);
                            thumb_tipfam.add(img);
                        }
                    } catch (FileNotFoundException ex) {
                    }
                }

            }
        }


        model.addAttribute("immagini_tipfam", thumb_tipfam);



        return "imgnfList";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String searchHome(Model model,
            HttpServletRequest request) {


        return "redirect:/index";
    }

    private String findByPars(String cdvisttp,
            String cdvistfam,
            String dsvistccol,
            String cdvistcolv,
            String cdvistfinv,
            String cdvistfinm,
            String cdvistelet,
            Model model,
            HttpServletRequest request) {

        HashMap pars = new HashMap();



        pars.put("fgweb", "S");
        //ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vistosiShopManager.addCdclasFilter(pars, request);
        vistosiShopManager.addToggleStateZEEFilter(pars, request);
//        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
//            ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli listino
//        } else {
//            pars.put("cdclas_aList", VistosiShopManager.DEFAULT_CDCLAS_A); //filtro solo articoli listino
//        }
        pars.put("cdvisttp", StringUtils.trimToNull(cdvisttp));
        pars.put("cdvistfam", StringUtils.trimToNull(cdvistfam));
        pars.put("dsvistccol", StringUtils.trimToNull(dsvistccol));
        pars.put("cdvistcolv", StringUtils.trimToNull(cdvistcolv));
        pars.put("cdvistfinv", StringUtils.trimToNull(cdvistfinv));
        pars.put("cdvistfinm", StringUtils.trimToNull(cdvistfinm));
        pars.put("cdvistelet", StringUtils.trimToNull(cdvistelet));

        Cookie ckViewOff = WebUtils.getCookie(request, "filter_off");
        pars.put("fgpromo", ((ckViewOff != null && "S".equals(ckViewOff.getValue())) ? "S" : null));


        HashMap menuPars = (HashMap) pars.clone();
        String rootFilter = (String) model.asMap().get("rootFilter");
//        if(StringUtils.equals(rootFilter, cdvisttp)){
//            menuPars.remove("cdvistfam");
//            model.addAttribute("tipologie", vistosiShopManager.findVist_tipi(pars));
//            model.addAttribute("famiglie", vistosiShopManager.findVist_famiglia(menuPars));
//        }else
//        if(StringUtils.equals(rootFilter, cdvistfam)){
//            menuPars.remove("cdvisttp");
//            model.addAttribute("tipologie", vistosiShopManager.findVist_tipi(menuPars));
//            model.addAttribute("famiglie", vistosiShopManager.findVist_famiglia(pars));
//        }

        //model.addAttribute("tipologie", vistosiShopManager.findVist_tipi(pars));
        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        //model.addAttribute("famiglie", vistosiShopManager.findVist_famiglia(pars));
        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);   
        vistosiShopManager.addToggleStateZEEFilter(fpars, request);
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));
        model.addAttribute("collezioni", vistosiShopManager.getVist_cp_collezioni());
        //model.addAttribute("collezioni", vistosiShopManager.findVist_cp_collezioni(pars));
        //model.addAttribute("colori", vistosiShopManager.findVist_colori_vetro(pars));
        log.debug("modifica select colori");
        pars.put("ismenu", true);
        //MPERUZZA 20121115
        model.addAttribute("elettrificazioni", vistosiShopManager.findVist_elettrificazioni(pars));
        //fine
        model.addAttribute("colori", vistosiShopManager.findVist_vetro(pars));
        model.addAttribute("partmet", vistosiShopManager.findVist_finit_mont(pars));

        List<Mrp_arch_stato> availableStates = vistosiShopManager.getAvailableStates();
        model.addAttribute("stati", availableStates);

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

        //if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
//            pars.put("statiEscludedList", Collections.singletonList("ZEE"));
        //}


        if (StringUtils.isNotBlank(cdvisttp)) {
            WebUtils.setSessionAttribute(request, "tipologiaFilter", vistosiShopManager.getVist_tipiByKey(cdvisttp));
        } else {
            request.getSession().removeAttribute("tipologiaFilter");
        }
        if (StringUtils.isNotBlank(cdvistfam)) {
            WebUtils.setSessionAttribute(request, "famigliaFilter", vistosiShopManager.getVist_famigliaByKey(cdvistfam));
        } else {
            request.getSession().removeAttribute("famigliaFilter");
        }
        if (StringUtils.isNotBlank(dsvistccol)) {
            WebUtils.setSessionAttribute(request, "collezioneFilter", dsvistccol); //vistosiShopManager.getVist_cp_collezioniByKey(cdvistccol));
        } else {
            request.getSession().removeAttribute("collezioneFilter");
        }
        if (StringUtils.isNotBlank(cdvistcolv)) {
            WebUtils.setSessionAttribute(request, "coloreFilter", vistosiShopManager.getVist_colori_vetroByKey(cdvistcolv));
        } else {
            request.getSession().removeAttribute("coloreFilter");
        }
        //MPERUZZA 20121115
        if (StringUtils.isNotBlank(cdvistelet)) {
            WebUtils.setSessionAttribute(request, "eletFilter", vistosiShopManager.getVist_elettrificazioniByKey(cdvistelet));
        } else {
            request.getSession().removeAttribute("eletFilter");
        }
        //fine
        if (StringUtils.isNotBlank(cdvistfinv)) {
            WebUtils.setSessionAttribute(request, "finvetroFilter", vistosiShopManager.getVist_finit_vetroByKey(cdvistfinv));
        } else {
            request.getSession().removeAttribute("finvetroFilter");
        }
        if (StringUtils.isNotBlank(cdvistfinm)) {
            WebUtils.setSessionAttribute(request, "finituraFilter", vistosiShopManager.getVist_finit_montByKey(cdvistfinm));
        } else {
            request.getSession().removeAttribute("finituraFilter");
        }

        //searchArticoli(pars, request, model);
        search(pars, request, model);

        return "index";
    }

    @RequestMapping(value = "*articoliAutoComp", method = RequestMethod.GET)
    public String findArticoliByCdmDs(
            @RequestParam(value = "omni", required = true) String omni,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response) {

        Map pars = new HashMap();

        RequestContext rc = new RequestContext(request);

        String[] sTokens = StringUtils.split(omni);

        if (sTokens.length > 1) {

            List<String> searchStrings = new ArrayList<String>();

            SimplePermutator p = new SimplePermutator(sTokens.length);

            do {
                log.debug(p.toString());

                int[] el = p.elementi;

                String search = "";
                for (int i : el) {
                    search += "%" + sTokens[i - 1];
                }
                search += "%";
                log.debug(search);

                searchStrings.add(search);

            } while (p.prossima());

            pars.put("omniList", searchStrings);

        } else {
            pars.put("omniList", Arrays.asList("%" + omni + "%"));
        }

        log.debug(rc.getLocale().getLanguage());
        
        if (rc.getLocale().getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            pars.put("langSfx", "_eng");
            pars.put("locale", "_"+rc.getLocale().getLanguage());
            model.addAttribute("atkLangSfx", "_eng");
        }
        if (rc.getLocale().getLanguage().equals("de")) {
            pars.put("langSfx", "_ted");
            pars.put("locale", "_"+rc.getLocale().getLanguage());
            model.addAttribute("atkLangSfx", "_ted");
        }
        if (rc.getLocale().getLanguage().equals("es")) {
            pars.put("langSfx", "_spa");
            pars.put("locale", "_"+rc.getLocale().getLanguage());
            model.addAttribute("atkLangSfx", "_spa");
        }
        if (rc.getLocale().getLanguage().equals("fr")) {
            pars.put("langSfx", "_fra");
            pars.put("locale", "_"+rc.getLocale().getLanguage());
            model.addAttribute("atkLangSfx", "_fra");
        }

        ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vistosiShopManager.addCdclasFilter(pars, request);
        
        List<Mrp_arch_articoli> articoliList = new ArrayList<Mrp_arch_articoli>();

        if (user.getIsSpecList()) {

            List<Mrp_arch_articoli_ul> articoliUlList = vistosiShopManager.searchArticoliUlByPars(pars, 1, 20);

            for (Mrp_arch_articoli_ul art : articoliUlList) {

                Mrp_arch_articoli artL = vistosiShopManager.getMrp_arch_articoliByKey(art.getCdarti());

                artL.setVist_famiglia(vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam()));
                artL.setVist_tipi(vistosiShopManager.getVist_tipiByKey(art.getCdvisttp()));
                artL.setVist_colori_vetro(vistosiShopManager.getVist_colori_vetroByKey(art.getCdvistcolv()));
                artL.setVist_finit_mont(vistosiShopManager.getVist_finit_montByKey(art.getCdvistfinm()));
                artL.setVist_finit_vetro(vistosiShopManager.getVist_finit_vetroByKey(art.getCdvistfinv()));
                artL.setVist_elettrificazioni(vistosiShopManager.getVist_elettrificazioniByKey(art.getCdvistelet()));
                if (artL.getCdvistv1() != null) {
                    artL.setVist_var1(vistosiShopManager.getVist_var1ByKey(art.getCdvistv1()));
                }
                if (artL.getCdvistv2() != null) {
                    artL.setVist_var2(vistosiShopManager.getVist_var2ByKey(art.getCdvistv2()));
                }
                if (artL.getCdvistv3() != null) {
                    artL.setVist_var3(vistosiShopManager.getVist_var3ByKey(art.getCdvistv3()));
                }

                articoliList.add(artL);
            }


        } else {


            articoliList = vistosiShopManager.searchArticoliByPars(pars, 1, 20);

            for (Mrp_arch_articoli art : articoliList) {

                art.setVist_famiglia(vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam()));
                art.setVist_tipi(vistosiShopManager.getVist_tipiByKey(art.getCdvisttp()));
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

            }
            
        }

        if (articoliList.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        //sort by description
        Transformer transformer = new Transformer() {
              public Object transform(Object input) {
                   return ((String)input).toLowerCase();
              }
        };   
        Comparator comparator = new TransformingComparator(transformer);
        BeanComparator comp = new BeanComparator("dsestesa" + (pars.get("langSfx")!=null?(String)pars.get("langSfx"):""),comparator);
        Collections.sort(articoliList, comp);

        model.addAttribute("articoliList", articoliList);

        return "ajaxsearch";
    }
}
