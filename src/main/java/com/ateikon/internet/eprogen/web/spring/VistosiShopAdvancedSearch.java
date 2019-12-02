/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_costanti;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_articoli_ul;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_articoli_img;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_famiglia;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import com.ateikon.internet.eprogen.web.utils.SimplePermutator;
import com.ateikon.internet.generic.domain.DTPaginatedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class VistosiShopAdvancedSearch {

    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
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

    private void referenceData(Model model, HttpServletRequest request) {

        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);
        vistosiShopManager.addToggleStateZEEFilter(fpars, request);    
        
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));

    }

    @RequestMapping("/findArticoli")
    public String findArticoli(Model model,
            @RequestParam(value = "descr", required = false) String descr,
            @RequestParam(value = "cdvisttp", required = false) String cdvisttp,
            @RequestParam(value = "cdvistfam", required = false) String cdvistfam,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (descr != null) {
            Map pars = new HashMap();

            RequestContext rc = new RequestContext(request);

            int pageSize = 20;
            String sort = ServletRequestUtils.getStringParameter(request, "sort", "dsarti");
            String dir = ServletRequestUtils.getStringParameter(request, "dir", "desc");

            if (StringUtils.isNotBlank(cdvistfam) && (!"-".equals(cdvistfam))) {
                pars.put("cdvistfam", cdvistfam);
            }
            if (StringUtils.isNotBlank(cdvisttp) && (!"-".equals(cdvisttp))) {
                pars.put("cdvisttp", cdvisttp);
            }

            String[] sTokens = StringUtils.split(descr);

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
                pars.put("omniList", Arrays.asList("%" + descr + "%"));
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

            List<Mrp_arch_articoli> articoliList = new ArrayList<Mrp_arch_articoli>();
            List<Mrp_arch_articoli_ul> articoliUlList = new ArrayList<Mrp_arch_articoli_ul>();
            DTPaginatedList theList = null;
            DTPaginatedList theListNew = null;

            if (user.getIsSpecList()) {

                vistosiShopManager.addCdclasFilter(pars, request);
                theListNew = vistosiShopManager.selectArticoliUlByExamplePag(pars, sort, dir, page, pageSize);
                articoliUlList = theListNew.getList();

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

                    //AGGIUNTA MPERUZZA 20121114

                    WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

                    String path_techsheet = "fileresources/assembling_instructions/";
                    String techsheet = vistosiShopManager.getSchedaTec(artL.getCdvisttp(), artL.getCdvistfam(), artL.getCdvistv1(), artL.getCdvistv2(), artL.getCdvistv3(), artL.getCdvistelet());
                    if (StringUtils.isNotEmpty(techsheet)) {
                        try {
                            String path_to_techsheet = WebUtils.getRealPath(ctx.getServletContext(), path_techsheet + techsheet);
                            File f = new File(path_to_techsheet);
                            log.debug(path_to_techsheet);
                            Vist_articoli_img vist_articoli_img = new Vist_articoli_img();
                            vist_articoli_img.setPathschtec(techsheet);
                            if (f.exists()) {
                                vist_articoli_img.setPathschtecExists(Boolean.TRUE);
                            }
                            artL.setVist_articoli_img(vist_articoli_img);
                        } catch (Exception ex) {
                            log.error("file techsheet non trovato");
                        }
                    }
                    //ricerca modelli
                    Vist_famiglia vist_fam = vistosiShopManager.getVist_famigliaByKey(artL.getCdvistfam());

                    log.debug("filedis >>>" + artL.getVist_filedis());


                    String path_modello = "fileresources/models";
                    String path_3D = path_modello + "/3D/";
                    //String nome_modello = vist_fam.getCdvistfam_mPad() + artL.getCdvisttp() + artL.getCdvistv1Pad() + artL.getCdvistv2Pad() + artL.getCdvistv3Pad() +"-";
                    String nome_modello = artL.getVist_filedis();
                    //igs
                    //String igs = path_3D + nome_modello + ".zip";
//                    String igs = path_3D + nome_modello + (artL.isLed()?art.getCdvistelet():"") + ".zip";
//                    try {
//                        String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), igs);
//                        File f = new File(path_to_filemodel);
//                        log.debug(path_to_filemodel);
//                        artL.setModel3D_igs(igs);
//                        if (f.exists()) {
//                            artL.setModel3D_igsExists(Boolean.TRUE);
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
//                        artL.setModel3D_eprt(eprt);
//                        if (f.exists()) {
//                            artL.setModel3D_eprtExists(Boolean.TRUE);
//                        }
//                    } catch (FileNotFoundException ex) {
//                        log.error("file " + eprt + " non trovato");
//                    }
                    //easm
                    //String easm = path_3D + nome_modello + ".EASM";
                    String easm = path_3D + nome_modello + (artL.isLed()?art.getCdvistelet():"") +".EASM";
                    try {
                        String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), easm);
                        File f = new File(path_to_filemodel);
                        log.debug(path_to_filemodel);
                        artL.setModel3D_easm(easm);
                        if (f.exists()) {
                            artL.setModel3D_easmExists(Boolean.TRUE);
                        }
                    } catch (FileNotFoundException ex) {
                        log.error("file " + easm + " non trovato");
                    }

                    String path_2D = path_modello + "/2D/";
                    //dwg cm
                    String dwg_vers = "cm/";
                    if ("en".equals(rc.getLocale().getLanguage())) {
                        dwg_vers = "po/";
                        artL.setTiporisorsa2D_dwg(com.ateikon.common.Mrp_arch_articoli.MOD2D_DWG_PO);
                    }
                    String dwg = path_2D + dwg_vers + nome_modello + ".dwg";
                    try {
                        String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), dwg);
                        File f = new File(path_to_filemodel);
                        log.debug(path_to_filemodel);
                        artL.setModel2D_dwg(dwg);
                        if (f.exists()) {
                            artL.setModel2D_dwgExists(Boolean.TRUE);
                        }
                    } catch (FileNotFoundException ex) {
                        log.error("file " + dwg + " non trovato");
                    }

                    //FINE
                    articoliList.add(artL);
                }
                int fullListSize = theListNew.getFullListSize();
                theList = new DTPaginatedList(fullListSize, articoliList, page, pageSize, null, sort, SortOrderEnum.fromName(dir + "ending"));

            } else {

                vistosiShopManager.addCdclasFilter(pars, request);
                theList = vistosiShopManager.selectArticoliByExamplePag(pars, sort, dir, page, pageSize);
                articoliList = theList.getList();

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

                    //AGGIUNTA MPERUZZA 20121114

                    WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

                    String path_techsheet = "fileresources/assembling_instructions/";
                    String techsheet = vistosiShopManager.getSchedaTec(art.getCdvisttp(), art.getCdvistfam(), art.getCdvistv1(), art.getCdvistv2(), art.getCdvistv3(), art.getCdvistelet());
                    if (StringUtils.isNotEmpty(techsheet)) {
                        try {
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
                    //ricerca modelli
                    Vist_famiglia vist_fam = vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam());

                    log.debug("filedis >>>" + art.getVist_filedis());


                    String path_modello = "fileresources/models";
                    String path_3D = path_modello + "/3D/";
                    //String nome_modello = vist_fam.getCdvistfam_mPad() + art.getCdvisttp() + art.getCdvistv1Pad() + art.getCdvistv2Pad() + art.getCdvistv3Pad() +"-";
                    String nome_modello = art.getVist_filedis();
                    //igs
                    //String igs = path_3D + nome_modello + ".zip";
//                    String igs = path_3D + nome_modello + (art.isLed()?art.getCdvistelet():"") + ".zip";
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
                    String easm = path_3D + nome_modello + (art.isLed()?art.getCdvistelet():"") +".EASM";
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

                    //FINE

                }

            }

            if (articoliList.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }

            model.addAttribute("artLst", articoliList);
            model.addAttribute("theList", theList);

        }
        
//        model.addAttribute("disableMenu", true);
//        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
//        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia());
        
        referenceData(model,request);
        
        return "advancedSearch";
    }
}
