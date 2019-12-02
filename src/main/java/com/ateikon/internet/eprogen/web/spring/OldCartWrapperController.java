/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Unitalocale;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
@Controller
public class OldCartWrapperController {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }


    @RequestMapping(value="/catalogo_dest_merce.*")
    public String dispatchDestinazioneMerce(Model model, HttpServletRequest request){

        ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.debug("dest merce controller");
        //model.addAttribute("cart", getBaseCart());

        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        
        
        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);
        vistosiShopManager.addToggleStateZEEFilter(fpars, request);     
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));


        //Costante cdusul_dm = autocompleteManager.getCostante("cdusul_dm");
        //if(cdusul_dm!=null && StringUtils.isNotBlank(cdusul_dm.getCostvalue())) pars.put("cdusul", cdusul_dm.getCostvalue());
        Map pars = new HashMap();
        pars.put("tkclie", user.getUserDB().getTkclie());
        pars.put("cdazie", user.getUserDB().getCdazie());
        pars.put("fseleg", "N");
        pars.put("dtfval", new Date());
        List<Unitalocale> unilocs = vistosiShopManager.getUnitalocali(pars);

        model.addAttribute("unitalocali", unilocs);
        
        return "catalogo_dest_merce";
    }

    @RequestMapping(value="/catalogo_ordine_fine.*")
    public String dispatchOrdineFine(Model model, HttpServletRequest request){

        log.debug("dest merce controller");
        //model.addAttribute("cart", getBaseCart());
        
        
        model.addAttribute("tipologie", vistosiShopManager.getVist_tipi());
        
        Map fpars = new HashMap();
        vistosiShopManager.addCdclasFilter(fpars, request);
        vistosiShopManager.addToggleStateZEEFilter(fpars, request);
        model.addAttribute("famiglie", vistosiShopManager.getVist_famiglia(fpars));

        return "catalogo_ordine_fine";
    }

}
