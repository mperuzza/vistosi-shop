/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import com.ateikon.internet.eprogen.domain.pgmr.Foto_sezioni;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.servlet.View;

/**
 *
 * @author emiliano
 */
public class CataloghiXMLView implements View{

    private Log log = LogFactory.getLog(this.getClass());

    public String getContentType() {
        return "text/xml; charset=ISO-8859-1";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("book");
        root.addAttribute("pagewidth", "475");
        root.addAttribute("pageheight", "537");
        root.addAttribute("maxloaded", "10");
        root.addAttribute("navigation", "true");
        root.addAttribute("bggradient", "false");
        root.addAttribute("pagesounds", "false");
        root.addAttribute("hidebuttons", "fullscreen help mute settings");
        
        doc.setRootElement(root);

        Integer cdcollezione = null;
        Element chapternode = null;
        List<String> cdcollsArray = new ArrayList();
        List<Foto_sezioni> result = (List<Foto_sezioni>)model.get("theList");
		for (Foto_sezioni elem : result) {

            if(cdcollezione==null || cdcollezione.intValue() != elem.getCdcollezione().intValue()){
                
                String marker = "f";
                cdcollezione = elem.getCdcollezione();
                if(cdcollsArray.contains(cdcollezione+"")){
                    marker = "";
                }
                cdcollsArray.add(cdcollezione+"");
                chapternode = root.addElement("chapter").addAttribute("anchor", marker + "cdcoll" + elem.getCdcollezione().toString());
                chapternode.addElement("dscollezione", elem.getDscollezione());
            }

            Element fotosezione = chapternode.addElement("page").addAttribute("anchor", "cdart" + elem.getCdarticolo());

            String imgPath = "img/catalogo/catalogo pagine singole/" + elem.getFoto();
            fotosezione.addElement("img").addAttribute("src", imgPath);

        }

        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");

        Writer writer = response.getWriter();
        writer.write(doc.asXML());
    }




}
