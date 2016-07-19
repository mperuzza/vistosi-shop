/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import com.ateikon.internet.eprogen.domain.pgmr.Foto_sezioni;
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
public class AmbientazioniXMLView implements View{

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
        List<Foto_sezioni> result = (List<Foto_sezioni>)model.get("theList");
		for (Foto_sezioni elem : result) {

            if(cdcollezione==null || cdcollezione.intValue() != elem.getCdcollezione().intValue()){
                cdcollezione = elem.getCdcollezione();
                collezionenode = root.addElement("collezione").addAttribute("cd", elem.getCdcollezione().toString());
                collezionenode.addElement("dscollezione").addText(elem.getDscollezione());
            }

            Element fotosezione = collezionenode.addElement("fotosezione");

            fotosezione.addElement("foto").addText(elem.getFoto());
            fotosezione.addElement("cdarticolo").addText(elem.getCdarticolo());
            fotosezione.addElement("cdcollezione").addText(elem.getCdcollezione().toString());

        }

        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");

        Writer writer = response.getWriter();
        writer.write(doc.asXML());
    }




}
