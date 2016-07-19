/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.web.security.Ep_utentePasswordEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author emiliano
 */
@Controller
//@RequestMapping("/app/**")
public class DecrController {

    private Log log = LogFactory.getLog(this.getClass());

    @RequestMapping("/dec")
    public String decrypt(@RequestParam(value="p",required=false) String p, Model model){

        Ep_utentePasswordEncoder pe = new Ep_utentePasswordEncoder();

        if(p!=null){
            pe.isPasswordValid(p.replace(" ", "+"), "", "atkciao");
        }


        return "decr";
    }

    @RequestMapping("/enc")
    public String encode(@RequestParam(value="p",required=false) String p, Model model){

        Ep_utentePasswordEncoder pe = new Ep_utentePasswordEncoder();

        if(p!=null){
            log.error(pe.encodePassword(p, "atkciao"));
        }


        return "decr";
    }


}
