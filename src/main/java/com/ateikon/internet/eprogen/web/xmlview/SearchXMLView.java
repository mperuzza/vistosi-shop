/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import com.ateikon.internet.eprogen.domain.pgmr.Articoli;
import java.io.Writer;
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
public class SearchXMLView implements View{

    private Log log = LogFactory.getLog(this.getClass());

    public String getContentType() {
        return "text/xml; charset=ISO-8859-1";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        doc.setRootElement(root);

        Integer cdcollezione = null;
        Element collezionenode = null;

        List<Articoli> result = (List<Articoli>)model.get("theList");
        Element countnode = root.addElement("countNode").addAttribute("id", "countnode").addText(""+result.size());

        for (Articoli elem : result) {

            if(cdcollezione==null || cdcollezione.intValue() != elem.getCdcollezione().intValue()){
                cdcollezione = elem.getCdcollezione();
                collezionenode = root.addElement("collezione");
                collezionenode.addElement("cdcollezione").addText(elem.getCdcollezione().toString());
                collezionenode.addElement("cdlinea").addText(elem.getCdlinea().toString());
                collezionenode.addElement("dslinea").addText(elem.getDslinea());
                collezionenode.addElement("dscollezione").addText(elem.getDscollezione());
            }

            Element artnode = collezionenode.addElement("articolo");
            artnode.addElement("cdarticolo").addText(elem.getCdarticolo());
            artnode.addElement("dsarticolo").addText(elem.getDsarticolo());
            artnode.addElement("cdcollezione").addText(elem.getCdcollezione().toString());
        }


        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");

        Writer writer = response.getWriter();
        writer.write(doc.asXML());
    }




}
