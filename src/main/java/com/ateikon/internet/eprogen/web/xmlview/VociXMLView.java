/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import com.ateikon.internet.eprogen.domain.pgmr.VoceSelezioneItem;
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
public class VociXMLView implements View{

    private Log log = LogFactory.getLog(this.getClass());

    public String getContentType() {
        return "text/xml; charset=ISO-8859-1";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        doc.setRootElement(root);

        Integer cdvoce = null;
        Element vocenode = null;
        List<VoceSelezioneItem> result = (List<VoceSelezioneItem>)model.get("theList");
		for (VoceSelezioneItem elem : result) {

            if(cdvoce==null || cdvoce.intValue() != elem.getVoce().getCdvoce().intValue()){
                cdvoce = elem.getVoce().getCdvoce();
                vocenode = root.addElement("voce");
                vocenode.addElement("cdvoce").addText(elem.getVoce().getCdvoce().toString());
                vocenode.addElement("dsvoce").addText(elem.getVoce().getDsvoce());
            }

            Element articolo = vocenode.addElement("articolo");

            articolo.addElement("cdarticolo").addText(elem.getArticolo().getCdarticolo());
            articolo.addElement("dsarticolo").addText(elem.getArticolo().getDsarticolo());
            articolo.addElement("cdcollezione").addText(elem.getArticolo().getCdcollezione().toString());

        }

        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");

        Writer writer = response.getWriter();
        writer.write(doc.asXML());
    }




}
