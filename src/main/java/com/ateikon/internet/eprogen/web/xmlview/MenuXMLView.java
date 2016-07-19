/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import com.ateikon.internet.eprogen.domain.pgmr.MenuItem;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
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
public class MenuXMLView implements View{

    private Log log = LogFactory.getLog(this.getClass());

    public String getContentType() {
        return "text/xml; charset=ISO-8859-1";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        doc.setRootElement(root);

        String dslinea = "";
        Element linea = null;
        List<MenuItem> result = (List<MenuItem>)model.get("theList");
		for (MenuItem elem : result) {

            if(!StringUtils.equals(dslinea, elem.getDslinea())){
                dslinea = elem.getDslinea();
                linea = root.addElement("linea").addAttribute("cd", elem.getCdlinea().toString());
                linea.addElement("description").addText(elem.getDslinea());
                linea.addElement("countart").addText(elem.getCxlinea().toString());
            }

            Element collezione = linea.addElement("collezione").addAttribute("cd", elem.getCdcollezione().toString());

            collezione.addElement("description").addText(elem.getDscollezione());
            collezione.addElement("countart").addText(elem.getCxcoll().toString());

        }

        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");

        Writer writer = response.getWriter();
        writer.write(doc.asXML());

    }




}
