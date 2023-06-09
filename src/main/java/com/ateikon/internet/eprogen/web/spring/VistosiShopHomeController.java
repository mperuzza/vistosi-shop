/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utente;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_famiglia;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_tipi;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
@Controller
//@RequestMapping("/app/**")
public class VistosiShopHomeController {

    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }

    private void getMenu(Model model, HttpServletRequest request) {


        model.addAttribute("collezioni", vistosiShopManager.getVist_cp_collezioni());

        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));

        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());

        model.addAttribute("elettrificazioni", vistosiShopManager.getVist_elettrificazioni());

        model.addAttribute("colori", vistosiShopManager.getVist_colori_vetro());

        model.addAttribute("partmet", vistosiShopManager.getVist_finit_mont());

    }

    @RequestMapping("/index")
    public String getIndex(HttpServletRequest request, Model model) {

        //ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        request.getSession().removeAttribute("collezioneFilter");
        request.getSession().removeAttribute("tipologiaFilter");
        request.getSession().removeAttribute("famigliaFilter");
        request.getSession().removeAttribute("coloreFilter");
        request.getSession().removeAttribute("finvetroFilter");
        request.getSession().removeAttribute("finituraFilter");
        //model.addAttribute("collezioni", vistosiShopManager.getVist_cp_collezioni());
        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        Map pars = new HashMap();
        Cookie ckViewOff = WebUtils.getCookie(request, "filter_off");
        pars.put("fgpromo", ((ckViewOff != null && "S".equals(ckViewOff.getValue())) ? "S" : null));
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
        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            List<String> newList = Collections.singletonList("ZEE");
            pars.put("statiEscludedList", newList);

            List<String> tipoList = new ArrayList<String>();
            //nascondo la categoria ALTRO agli utenti non visibili
            tipoList.add("PB");
            pars.put("tipoFilterList", tipoList);
        }
        model.addAttribute("tipologieThumb", vistosiShopManager.findVist_tipi(pars));
        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request); 
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));
        model.addAttribute("collezioni", vistosiShopManager.getVist_cp_collezioni());


        model.addAttribute("hideFilter", new Boolean(true));

        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("promo", vistosiShopManager.getActivePromo(user.getUserDB().getTkclie(), user.getCliente().getArchclie().getCdagen()));

            if (user.isRealCustomer()
                    || AuthorityUtils.userHasAuthority("ROLE_AGEN")
                    || AuthorityUtils.userHasAuthority("ROLE_CAPO")
                    || AuthorityUtils.userHasAuthority("ROLE_ISPE")
                    || AuthorityUtils.userHasAuthority("ROLE_GEST")
                    || AuthorityUtils.userHasAuthority("ROLE_ADMIN")) {

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
                model.addAttribute("condizioni", vistosiShopManager.getCondizioniVendita(user.getCliente(), tkutente));
            }
        }
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

//    @RequestMapping("/session/logout.*")
//    public String logoutSession( HttpServletRequest request, Model model){
//
//        log.debug("call logout");
//
//        return "redirect:/static/j_spring_security_logout";
//
//    }
    @RequestMapping("/403")
    public String accessDenied(HttpServletRequest request, Model model) {

        log.debug("call 403");


        if (AuthorityUtils.userHasAuthority("ROLE_AGEN")) {
            //retrieve i clienti dell'agente per farglielo scegliere
            ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
        }
//        if(AuthorityUtils.userHasAuthority("ROLE_ADMIN")){
//            //retrieve i clienti dell'agente per farglielo scegliere
//            ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
//        }

        model.addAttribute("disableMenu", true);

        return "403";

    }

    @RequestMapping("/404")
    public String pageNotFound(HttpServletRequest request, Model model) {

        log.debug("call 404");


        model.addAttribute("disableMenu", true);

        return "404";

    }

    @RequestMapping("/changeClie")
    public String changeClie(HttpServletRequest request, Model model) {

//        if(AuthorityUtils.userHasAuthority("ROLE_AGEN")){
//            //retrieve i clienti dell'agente per farglielo scegliere
//            ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
//        }
//        if(AuthorityUtils.userHasAuthority("ROLE_ADMIN")){
//            //retrieve i clienti dell'agente per farglielo scegliere
//            ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
//        }

        model.addAttribute("disableMenu", true);

        return "403";

    }

    @RequestMapping("/promo")
    public String promo(HttpServletRequest request, Model model) {

        ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(AuthorityUtils.userHasAuthority("ROLE_AGEN")){
//            //retrieve i clienti dell'agente per farglielo scegliere
//
//            model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
//        }

        model.addAttribute("promo", vistosiShopManager.getActivePromo(user.getUserDB().getTkclie(), user.getCliente().getArchclie().getCdagen()));

        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);         
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));

        return "promo";

    }

    @RequestMapping("/conditions")
    public String conditions(HttpServletRequest request, Model model) {

        ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map pars = new HashMap();
        pars.put("fgweb", "S");
        pars.put("cdclas_aList", user.getCdclas_aFilter()); //filtro solo articoli listino

        Vist_tipi tipo = (Vist_tipi) WebUtils.getSessionAttribute(request, "tipologiaFilter");

        if (tipo != null) {
            pars.put("cdvisttp", tipo.getCdvisttp());
        }


        Vist_famiglia fam = (Vist_famiglia) WebUtils.getSessionAttribute(request, "famigliaFilter");

        if (fam != null) {
            pars.put("cdvistfam", fam.getCdvistfam());
        }

        model.addAttribute("tipologie", vistosiShopManager.findVist_tipi(pars));
        model.addAttribute("famiglie", vistosiShopManager.findVist_famiglia(pars));

        //build condizioni map
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
        model.addAttribute("condizioni", vistosiShopManager.getCondizioniVendita(user.getCliente(), tkutente));




        return "condizioni";

    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound() {
        log.debug("pagina non trovata");
        return "footer.jsp";
    }

    @RequestMapping("/images/articoli/vetro/*")
    public View disTec(HttpServletRequest request, HttpServletResponse response, Model model) {

//        log.debug("controller disTec");
//        log.debug(request.getPathInfo());
//        log.debug(request.getRemoteAddr());
//        log.debug(request.getRemoteHost());
//        log.debug(request.getServerName());
//        log.debug(request.getRequestURI());
//        log.debug(request.getRequestURL());

        String url = request.getRequestURL().toString();

        //log.debug(request.getContextPath());
        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        try {
            String path = WebUtils.getRealPath(ctx.getServletContext(), request.getPathInfo());
            //log.debug(path);
            File f = new File(path);
            if (f.exists()) {
                //log.debug("esiste");
                //return "redirect:http://"+request.getRemoteHost()+"/"+request.getContextPath()+"/static"+request.getPathInfo();
                return new RedirectView("/static" + request.getPathInfo(), true);
            } else {
                //log.debug("non esiste");
                //strip 
                String filename = WebUtils.extractFilenameFromUrlPath(url);
                String[] comp = StringUtils.splitByWholeSeparatorPreserveAllTokens(filename, "_");
//                for (String string : comp) {
//                    log.debug(string);
//                }

                String newFilename = comp[0] + "_" + comp[1] + "__" + comp[3];//StringUtils.replaceOnce(filename, comp[1], "");
                //log.debug("newFilename:" + newFilename);
                String path1 = StringUtils.replace(path, filename, newFilename);
//                log.debug(path1);
                f = new File(path1);
                if (f.exists()) {
                    return new RedirectView("/static" + StringUtils.replace(request.getPathInfo(), filename, newFilename), true);
                } else {
                    newFilename = comp[0] + "___" + comp[3];

                    String path2 = StringUtils.replace(path, filename, newFilename);
//                    log.debug(path2);
                    f = new File(path2);
                    if (f.exists()) {
                        return new RedirectView("/static" + StringUtils.replace(request.getPathInfo(), filename, newFilename), true);
                    }
                }


            }
        } catch (FileNotFoundException ex) {
            //log.debug("not exists");
        }

        return new RedirectView("/static/images/articoli/vetro/thumb-nd.png", true);
    }

    @RequestMapping(value = {"/images/articoli/elettrificazioni/*", "/images/articoli/montature/*"})
    public View checkImg(HttpServletRequest request, HttpServletResponse response, Model model) {

        String url = request.getRequestURL().toString();

        //log.debug(request.getContextPath());
        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        try {
            String path = WebUtils.getRealPath(ctx.getServletContext(), request.getPathInfo());
            //log.debug(path);
            File f = new File(path);
            if (f.exists()) {
                //log.debug("esiste");
                //return "redirect:http://"+request.getRemoteHost()+"/"+request.getContextPath()+"/static"+request.getPathInfo();
                return new RedirectView("/static" + request.getPathInfo(), true);
            }
        } catch (FileNotFoundException ex) {
            //log.debug("not exists");
        }

        return new RedirectView("/static/images/articoli/thumb-nd.png", true);


    }

    @RequestMapping(value = "/checkCdartm.*", method = RequestMethod.GET)
    public String create(@RequestParam(value = "cdartm", required = true) String cdartm, HttpServletRequest request, HttpServletResponse response,
            Model model) {

        Map res = new HashMap();

        Mrp_arch_articoli art = vistosiShopManager.getArticoloByCdartm(cdartm);

        if (art != null) {
            model.addAttribute("articolo", art);
        } else {
            model.addAttribute("error", "not found");
        }

        return "articolo";
    }
//    @RequestMapping("/setclie")
//    public String setClie(HttpServletRequest request, Model model,
//                          @RequestParam(value="tkclie",required=true) String tkclie){
//
//        ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//
//
//
//        return "";
//    }
//    private void searchArticoli(Map pars, HttpServletRequest request, Model model){
//
//        int page = ServletRequestUtils.getIntParameter(request, "page", 1);
//        String sort = ServletRequestUtils.getStringParameter(request, "sort", "dsarti");
//        String dir = ServletRequestUtils.getStringParameter(request, "dir", "desc");
//        int pageSize = 22;
//        model.addAttribute("theList", vistosiShopManager.selectByExamplePag(pars,sort, dir, page, pageSize));
//    }
//
//    @RequestMapping(value="/**/tipologia/{cdvisttp}", method=RequestMethod.GET)
//    public String findByTipo(@PathVariable("cdvisttp") String cdvisttp,
//                           Model model,
//                           HttpServletRequest request){
//
//        log.debug("search per tipologia: " + cdvisttp);
//
//
//        return findByFam(cdvisttp, null, model, request);
//    }
//
//
//    @RequestMapping(value="/colore/{cdcol}", method=RequestMethod.GET)
//    public String findByColore(@PathVariable("cdcol") String cdvistcolv,
//                           Model model,
//                           HttpServletRequest request){
//
//        log.debug("search per colore: " + cdvistcolv);
//
//
//        return findByFam(cdvistcolv, null, model, request);
//    }
//
//    @RequestMapping(value="/**/met/{cdmet}", method=RequestMethod.GET)
//    public String findByMet(@PathVariable("cdvisttp") String cdvisttp,
//                           Model model,
//                           HttpServletRequest request){
//
//        log.debug("search per metallo: " + cdvisttp);
//
//
//        return findByFam(cdvisttp, null, model, request);
//    }
//
//
//
//    @RequestMapping(value="/**/tipologia/{cdvisttp}/famiglia/{cdfam}", method=RequestMethod.GET)
//    public String findByFam(@PathVariable("cdvisttp") String cdvisttp,
//                            @PathVariable("cdfam") String cdvistfam,
//                           Model model,
//                           HttpServletRequest request){
//
//        log.debug("search per famiglia: " + cdvistfam);
//        Map pars = new HashMap();
//        pars.put("cdvisttp", cdvisttp);
//        pars.put("cdvistfam", cdvistfam);
//
//        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
//        model.addAttribute("famiglie", vistosiShopManager.findVist_famiglia(pars));
//        model.addAttribute("colori", vistosiShopManager.findVist_colori_vetro(pars));
//        model.addAttribute("partmet", vistosiShopManager.findVist_finit_mont(pars));
//
//        //model.addAttribute("tipologiaFilter", vistosiShopManager.getVist_tipiByKey(cdvisttp));
//
//        WebUtils.setSessionAttribute(request, "tipologiaFilter", vistosiShopManager.getVist_tipiByKey(cdvisttp));
//        //model.addAttribute("famigliaFilter", vistosiShopManager.getVist_famigliaByKey(cdvistfam));
//        WebUtils.setSessionAttribute(request, "famigliaFilter", vistosiShopManager.getVist_famigliaByKey(cdvistfam));
//
//        searchArticoli(pars, request, model);
//
//
//        return "index";
//    }
}
