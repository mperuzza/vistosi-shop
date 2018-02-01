/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.Vist_vetro;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.DesignerWithBLOBs;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_costanti;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_articoli_datiextra;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_articoli_img;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_elettrificazioni;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_famiglia;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_mont;
import com.ateikon.internet.eprogen.web.interceptor.GeoIPInterceptor;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import static com.ateikon.internet.eprogen.web.spring.SpecSheet.ROOT_RES;
import com.ateikon.internet.generic.domain.BaseTableBean;
import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author rpin
 */
@Controller
public class SpecSheetModello {

    @Autowired
    //private JdbcTemplate jdbcTemplate;
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }
    @Autowired
    protected MessageSource messageSource;
    String siteRoot = "";
    public static final String ROOT_RES = "/images/articoli/specsheetres/";
    private static final String ARROW_ICON = "freccia300.jpg";
    private String slash = System.getProperty("file.separator");
    BaseFont baseFont = null;
    BaseFont baseFontBold = null;
    Font fontListino = null;
    Font fontLogo = null;
    Font fontHeader = null;
    Font fontTitoloNota = null;
    Font fontNota = null;
    Font fontCategoria = null;
    java.text.NumberFormat numFormat = null;
    java.text.NumberFormat przFormat = null;

    HashMap<String, String> tipoLampadine = new HashMap<String, String>();
    String selCdvistelet = null;

    String pathimg = ROOT_RES + "dati/";
    //path immagini lampadine
    String pathlampimg = ROOT_RES + "lampadine/";

    Locale locale = new Locale("it");

//    float widthBody = 440f;
//    float widthRight = 120f;
    //int widthPage = 560;
    @RequestMapping(value = "/fileresources/model_specsheet/{qpars}")
    public void createPDF(@PathVariable("qpars") String qpars, HttpServletRequest request, HttpServletResponse response) throws Exception {

        siteRoot = WebUtils.getRealPath(request.getSession().getServletContext(), "/");
        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        //Locale locale = RequestContextUtils.getLocale(request);
        
        String fontDir = WebUtils.getRealPath(request.getSession().getServletContext(),"font");

        locale = RequestContextUtils.getLocale(request);

        //SM|cdclas_a|cdvisttp|cdvistfam|cdvistv1|cdvistv2|cdvistv3|cdvistelet
        String[] splittedPars = StringUtils.splitByWholeSeparatorPreserveAllTokens(qpars, "|");
        String cdclas_a = splittedPars[1];
        String cdvisttp = splittedPars[2];
        String cdvistfam = splittedPars[3];
        String cdvistv1 = splittedPars[4];
        String cdvistv2 = splittedPars[5];
        String cdvistv3 = splittedPars[6];
        String cdvistelet = splittedPars[7];

        selCdvistelet = null;

        //String cdartm = (String) request.getParameter("cdartm");
        //baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        baseFont = BaseFont.createFont(fontDir + slash + "HelveticaNeueLTStd-Roman.otf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        baseFontBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);

        fontListino = new Font(baseFont, 6);
        fontLogo = new Font(baseFont, 8);
        fontHeader = new Font(baseFont, 7);
        fontTitoloNota = new Font(baseFont, 8);
        fontNota = new Font(baseFont, 7);
        fontCategoria = new Font(baseFont, 14);

        numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=\"SM" + cdvisttp + cdvistfam + ".pdf\"");

        Document document = new Document();
        document.setPageSize(PageSize.A4);
        document.setMargins(24, 24, 68, 50);
        document.setMarginMirroring(true);

        document.left(0f);

        document.getPageSize();
        numFormat.setGroupingUsed(true);
        numFormat.setMaximumFractionDigits(2);
        numFormat.setMinimumFractionDigits(0);

        przFormat.setGroupingUsed(true);
        przFormat.setMaximumFractionDigits(2);
        przFormat.setMinimumFractionDigits(2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        tipoLampadine.put("A", "ALO");
        tipoLampadine.put("F", "FL");
        tipoLampadine.put("L", "LED");
        tipoLampadine.put("E", "ES");
        tipoLampadine.put("I", "IOD");

        try {

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            /**
             * *************************************
             *
             * recupero dati per popolare il PDF
             *
             **************************************
             */
            String testo = "";
            Vist_famiglia famiglia = vistosiShopManager.getVist_famigliaByKey(cdvistfam);

            Map pars = new HashMap();
            ShopUser user = null;
            if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }

            if (StringUtils.isNotBlank(cdclas_a)) {
                if (vistosiShopManager.DEFAULT_CDCLAS_A.contains(cdclas_a)) {
                    pars.put("cdclas_aList", vistosiShopManager.DEFAULT_CDCLAS_A);
                } else if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(cdclas_a)) {
                    pars.put("cdclas_aList", vistosiShopManager.DEFAULT_CDCLAS_A_US);
                }

            } else {
                vistosiShopManager.addCdclasFilter(pars, request);
            }

            vistosiShopManager.addCdrepaFilter(pars, request);
            pars.put("fgweb", "S");
            pars.put("cdvisttp", cdvisttp);
            pars.put("cdvistfam", cdvistfam);
            pars.put("cdvistv1", StringUtils.trimToNull(cdvistv1));
            pars.put("cdvistv2", StringUtils.trimToNull(cdvistv2));
            //pars.put("cdvistv3", StringUtils.trimToNull(cdvistv3));

            List<Mrp_arch_articoli> arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);

            List<Mrp_arch_articoli> artsOrderedByCdclasa = new ArrayList<Mrp_arch_articoli>(arts);
            BeanComparator compCdclasa = new BeanComparator("cdclas_a");
            Collections.sort(artsOrderedByCdclasa, compCdclasa);
            Mrp_arch_articoli artFallback = artsOrderedByCdclasa.get(0);
            Mrp_arch_articoli art = artFallback;

            List<Vist_elettrificazioni> vist_elettrificazioni = vistosiShopManager.findVist_elettrificazioni(pars);
            //sort by country descr
            BeanComparator comp = new BeanComparator("dsextvistelet");
            Collections.sort(vist_elettrificazioni, comp);
            
            
            pars.put("vist_filedis", art.getVist_filedis());
            List<Vist_vetro> vist_vetro = vistosiShopManager.findVist_vetro(pars);
            List<Vist_finit_mont> vist_finit_mont = vistosiShopManager.findVist_finit_mont(pars);

            //String cdvistelet = findPreferred(art, vist_elettrificazioni, request, true);
            pars.put("cdvistelet", cdvistelet);

            //response.setHeader("Content-Disposition", "attachment;filename=\"SG " + cdclas_a + " " + cdvisttp + " " + cdvistfam + " " + cdvistv1 + " " + cdvistv2 + " " + cdvistv3 + " " + cdvistelet + ".pdf\"");
            response.setHeader("Content-Disposition", "attachment;filename=\"SM " + cdclas_a + " " + cdvisttp + " " + cdvistfam + " " + cdvistv1 + " " + cdvistv2 + " " + cdvistv3 + ".pdf\"");
            //vistosiShopManager.addCdrepaFilter(pars, request);
            //List<Mrp_arch_articoli> artsByElet = vistosiShopManager.selectMrp_arch_articoliByPars(pars);

            //Mrp_arch_articoli art = artsByElet.get(0);

//            }
//            Mrp_arch_articoli art = vistosiShopManager.getArticoloByCdartm(cdartm);
            Vist_articoli_datiextra datiExtra = vistosiShopManager.getDatiExtraByCdartm(art.getCdartm());

            PageEventHelper event = new PageEventHelper(famiglia, art, ctx, locale);
            writer.setPageEvent(event);

            document.open();

            /**
             * ***********************************
             *
             * page 1 solo se ci sono le foto
             *
             */
            buildGalleryPage(art, request, document, writer, famiglia, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdclas_a, artFallback, vist_vetro, vist_finit_mont, datiExtra, arts);

            /**
             * ***********************************
             *
             * page 2
             *
             */
            event.setDataPage(Boolean.TRUE);
            buildDataPage(art, request, document, writer, famiglia, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdclas_a, artFallback, vist_vetro, vist_finit_mont, datiExtra, arts);

            /**
             * ***********************************
             *
             * page 3 solo se i disegni delle versioni alternative sono più di
             * tot
             *
             */
            //  PdfAction action = PdfAction.javaScript(Utilities.readFileToString(rootImg + "js\\eprogen3d.js"), writer);
            // writer.setOpenAction(action);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    private void buildGalleryPage(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer, Vist_famiglia famiglia, String cdvisttp, String cdvistv1, String cdvistv2, String cdvistv3, String cdclas_a, Mrp_arch_articoli artFallback, List<Vist_vetro> vist_vetro, List<Vist_finit_mont> vist_finit_mont, Vist_articoli_datiextra datiExtra, List<Mrp_arch_articoli> arts) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

        PdfPCell cell;
        Paragraph paragraph;

        PdfPTable tableContainer = new PdfPTable(2);
        float[] columnWidth = {65, 35};
        tableContainer.setWidthPercentage(100);
        tableContainer.setWidths(columnWidth);

        tableContainer.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tableContainer.getDefaultCell().setBorderWidth(0f);
        tableContainer.getDefaultCell().setPadding(.0f);

        Map artPars = new HashMap();
        vistosiShopManager.addCdclasFilter(artPars, request);
        vistosiShopManager.addCdrepaFilter(artPars, request);
        artPars.put("cdvisttp", art.getCdvisttp());
        artPars.put("cdvistfam", art.getCdvistfam());
        artPars.put("fgweb", "S");
        artPars.put("ismenu", true);
        List<Mrp_arch_articoli> articoli = vistosiShopManager.selectMrp_arch_articoliByPars(artPars);
        //ArrayList<Mrp_arch_articoli> galleryArticoli = new ArrayList();
        ArrayList<String> galleryThumb = new ArrayList();
        String path = "images/articoli/foto/scheda_prodotto/";
        
//        String filename = art.getCdvistfam() + art.getCdvisttp() + StringUtils.trimToEmpty(art.getCdvistv1())
//                                                                 + StringUtils.trimToEmpty(art.getCdvistv2())
//                                                                 + StringUtils.trimToEmpty(art.getCdvistv3());
        String filename = art.getVist_filedis();

        
        for (int i = 1; i < 5; i++) {
            try {
                String fname = filename + i + ".jpg";
                String realPath = WebUtils.getRealPath(ctx.getServletContext(), path + fname);
                File f = new File(realPath);
                if (f.exists()) {
                    galleryThumb.add(fname);
                }
            } catch (FileNotFoundException ex) {
            }            
        }
        
//        for (Mrp_arch_articoli mrp_arch_articoli : articoli) {
//
//            try {
//                String realPath = WebUtils.getRealPath(ctx.getServletContext(), path + mrp_arch_articoli.getCdartm().replace('/', '-') + ".jpg");
//                File f = new File(realPath);
//                if (f.exists()) {
//                    galleryArticoli.add(mrp_arch_articoli);
//                }
//            } catch (FileNotFoundException ex) {
//            }
//        }


        PdfPTable tableDescrFamiglia = new PdfPTable(1);
        tableDescrFamiglia.setWidthPercentage(100);
        //tableDescrFamiglia.setWidths(new float[]{1.2f, 0.7f, 1.2f});
        PdfPCell defaultCell = tableDescrFamiglia.getDefaultCell();
        defaultCell.setPadding(0f);
        defaultCell.setPaddingTop(0f);
        defaultCell.setPaddingBottom(5f);
        defaultCell.setBorderWidth(0);
        defaultCell.setVerticalAlignment(Element.ALIGN_TOP);
//        defaultCell.setUseAscender(true);
//        defaultCell.setUseDescender(false);

        String fg_eurusa = "";
        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
            fg_eurusa = "U";
        } else if (vistosiShopManager.DEFAULT_CDCLAS_A.contains(art.getCdclas_a())) {
            fg_eurusa = "E";
        }
        String qrCodeLink = "http://www.vistosi.it/download-area/download-2d-3d.html?cdvistfam=" + art.getCdvistfam() + "&cdvisttp=" + art.getCdvisttp() + "&fg_eur_usa=" + fg_eurusa;

        if (art.getCdvistv1() != null) {
            qrCodeLink += "&cdvistv1=" + art.getCdvistv1();
        }
        if (art.getCdvistv2() != null) {
            qrCodeLink += "&cdvistv2=" + art.getCdvistv2();
        }
        if (art.getCdvistv3() != null) {
            qrCodeLink += "&cdvistv3=" + art.getCdvistv3();
        }

        String descrFamiglia = StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(famiglia, "dsvistfam" + getAtkLangsfx(locale.getLanguage())));

        Chunk famLink = new Chunk(descrFamiglia, new Font(baseFont, 16));
        famLink.setAction(new PdfAction(new URL(qrCodeLink)));
        //descrizione famiglia
        paragraph = new Paragraph();
        paragraph.setLeading(13f);
        paragraph.add(famLink);
        
        cell = tableDescrFamiglia.getDefaultCell();
        cell.addElement(paragraph);
        //defaultCell.setPaddingLeft(20f);
        tableDescrFamiglia.addCell(cell);

        //descrizione designer
        String designerDescription = "";
        if (art.getCddesigner() != null) {
            DesignerWithBLOBs designer = vistosiShopManager.getDesigner(art.getCddesigner());
            if (designer != null && StringUtils.isNotBlank(designer.getDsdesigner())) {
                designerDescription += "Design " + designer.getDsdesigner().replaceAll("\\s+", " ") + "\n";
            }
        }
        paragraph = new Paragraph(designerDescription, new Font(baseFont, 7));
        defaultCell.setPaddingTop(0f);
        tableDescrFamiglia.addCell(paragraph);
        tableContainer.addCell(tableDescrFamiglia);

        //tipologia
        PdfPTable tableDescrModello = new PdfPTable(1);
        tableDescrModello.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
        tableDescrModello.getDefaultCell().setUseAscender(true);
        tableDescrModello.getDefaultCell().setUseDescender(false);

        String version = "CE";
        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
            version = "UL";
        }
        paragraph = new Paragraph(descrFamiglia + " " + art.getCdvisttp() + (art.getCdvistv1() != null ? " " + art.getCdvistv1() : "") + (art.getCdvistv2() != null ? " " + art.getCdvistv2() : "") + (art.getCdvistv3() != null ? " " + art.getCdvistv3() : ""), new Font(baseFont, 12));
        paragraph.setLeading(10f);
        cell = tableDescrModello.getDefaultCell();
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(.2f);
        cell.setPaddingLeft(3f);
        cell.setPaddingRight(5);
        cell.setPaddingTop(2f);
        cell.setPaddingBottom(23f);
        cell.addElement(paragraph);
        tableDescrModello.addCell(cell);

        //tableHeader.getDefaultCell().setPaddingTop(6f);
        tableContainer.addCell(tableDescrModello);        
        //tableContainer.addCell("--");

        PdfPTable tableLeft = new PdfPTable(1);
        tableLeft.setExtendLastRow(false);
        //cover
        String realPath = WebUtils.getRealPath(ctx.getServletContext(), path + filename + ".jpg");
        File f = new File(realPath);
        if (f.exists()) {
            //if (!galleryArticoli.isEmpty()) {
            cell = new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setBorderWidth(0f);
            cell.setPadding(0f);
            cell.setPaddingTop(13f);
            cell.setPaddingRight(5f);
            //Mrp_arch_articoli gart = galleryArticoli.get(0);
            Image img = Image.getInstance(realPath);
            img.setAlignment(Image.TEXTWRAP | Image.ALIGN_LEFT);

            cell.addElement(img);

            tableLeft.addCell(cell);
        } else {
            
            if(galleryThumb.isEmpty()) return;
            
            tableLeft.addCell("");
        }

        //descrizione estesa famiglia
        String description = StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(famiglia, "dsextvistfam" + getAtkLangsfx(locale.getLanguage())));
        paragraph = new Paragraph(description, new Font(baseFont, 10));
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorderWidth(0f);
        cell.setPadding(0f);
        cell.setPaddingRight(5f);
        cell.setPaddingTop(15f);
        cell.addElement(paragraph);
        
//        Paragraph paragraphM = new Paragraph("MADE IN ITALY", new Font(baseFont, 10));
//        paragraphM.setSpacingBefore(10);
//        paragraphM.setAlignment(Element.ALIGN_LEFT);        
//        cell.addElement(paragraphM);
//        
        tableLeft.addCell(cell);

        tableContainer.addCell(tableLeft);

        PdfPTable tableRight = new PdfPTable(1);

        tableRight.setExtendLastRow(false);
        tableRight.setWidthPercentage(100);

        int i = 0;
        if (galleryThumb.size() > 1) {

//            for (int i = 1; i < Math.min(galleryArticoli.size(), 5); i++) {
//                Mrp_arch_articoli gart = galleryArticoli.get(i);
            for (String fname : galleryThumb) {
                
                cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setBorderWidth(0f);
                cell.setPadding(0f);
                //cell.setPaddingTop((i==0)?13f:5f);
                cell.setPaddingTop(0f);
                cell.setPaddingLeft(5f);
                cell.setFixedHeight(150f);

                Image img = Image.getInstance(WebUtils.getRealPath(ctx.getServletContext(), path + fname));
                img.setAlignment(Image.TEXTWRAP | Image.ALIGN_LEFT);

                cell.addElement(img);

                tableRight.addCell(cell);

                //String descrFamiglia = StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(famiglia, "dsvistfam" + getAtkLangsfx(locale.getLanguage())));
                Paragraph dida = new Paragraph(descrFamiglia + " " + art.getCdvisttp() + (art.getCdvistv1() != null ? " " + art.getCdvistv1() : "") + (art.getCdvistv2() != null ? " " + art.getCdvistv2() : "") + (art.getCdvistv3() != null ? " " + art.getCdvistv3() : ""), new Font(baseFont, 8));
                dida.setAlignment(Element.ALIGN_LEFT);
                cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setBorderWidth(0f);
                cell.setPadding(0f);
                cell.setPaddingLeft(5f);
                cell.setPaddingTop(0f);
                cell.setPaddingBottom(5f);
                cell.addElement(dida);

                tableRight.addCell(cell);
                
                i++;

            }

//            cell = new PdfPCell();
//            //cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.addElement(tableRight);
            cell = new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setBorderWidth(0f);
            cell.setPadding(0f);
            cell.setPaddingTop(13f);
            cell.addElement(tableRight);
            tableContainer.addCell(cell);
        } else {
            tableContainer.addCell("");
        }

        document.add(tableContainer);

        document.newPage();

    }

    private void buildDataPage(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer, Vist_famiglia famiglia, String cdvisttp, String cdvistv1, String cdvistv2, String cdvistv3, String cdclas_a, Mrp_arch_articoli artFallback, List<Vist_vetro> vist_vetro, List<Vist_finit_mont> vist_finit_mont, Vist_articoli_datiextra datiExtra, List<Mrp_arch_articoli> arts) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, DocumentException, IOException {

        PdfPTable tableContainer = null;
        PdfPTable tableBody = null;
        PdfPTable tableRight = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        //float[] columnWidth = {420f, 140f};
        tableContainer = new PdfPTable(1);
        //tableContainer.setTotalWidth(columnWidth);
        tableContainer.setWidthPercentage(100);
        //tableContainer.setLockedWidth(true);
        //tableContainer.setExtendLastRow(true);
        tableContainer.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableContainer.getDefaultCell().setBorderWidth(0f);
        tableContainer.getDefaultCell().setPadding(.0f);

        tableBody = new PdfPTable(2);
        tableBody.setWidthPercentage(100);
        float[] columnWidth = {50, 50};
        tableBody.setWidths(columnWidth);
        //            tableBody.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableBody.getDefaultCell().setBorderWidth(0f);
        tableBody.getDefaultCell().setPadding(0f);
        tableBody.getDefaultCell().setPaddingBottom(3);

        //disegno tecnico
        PdfPTable disegno = getDisegnoDim(art, request, document, writer);
        cell = new PdfPCell(disegno);
        cell.setBorderWidth(0f);
        cell.setRowspan(2);
        tableBody.addCell(cell);
        
        
        //tipologia
        PdfPTable tableDescrModello = new PdfPTable(1);
        tableDescrModello.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
        tableDescrModello.getDefaultCell().setUseAscender(true);
        tableDescrModello.getDefaultCell().setUseDescender(false);

        String descrFamiglia = StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(famiglia, "dsvistfam" + getAtkLangsfx(locale.getLanguage())));
        
        String version = "CE";
        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
            version = "UL";
        }
        paragraph = new Paragraph(descrFamiglia + " " + art.getCdvisttp() + (art.getCdvistv1() != null ? " " + art.getCdvistv1() : "") + (art.getCdvistv2() != null ? " " + art.getCdvistv2() : "") + (art.getCdvistv3() != null ? " " + art.getCdvistv3() : ""), new Font(baseFont, 12));
        cell = tableDescrModello.getDefaultCell();
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthLeft(.2f);
        cell.setPaddingLeft(3f);
        cell.setPaddingRight(5);
        cell.setPaddingTop(2f);
        cell.setPaddingBottom(23f);
        cell.addElement(paragraph);
        tableDescrModello.addCell(cell);

        //tableHeader.getDefaultCell().setPaddingTop(6f);
        tableBody.addCell(tableDescrModello);          

        //colonna di dx con dati articolo
        tableRight = new PdfPTable(1);
        tableRight.setWidthPercentage(100);
        tableRight.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableRight.getDefaultCell().setBorderWidth(0f);
        tableRight.getDefaultCell().setPaddingTop(0);
        tableRight.getDefaultCell().setPaddingRight(0);
        tableRight.getDefaultCell().setPaddingBottom(4);
        tableRight.getDefaultCell().setPaddingLeft(10);
        tableRight.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableRight.setExtendLastRow(true);

        //certificazioni (marcature)
        cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorderWidth(0f);
        cell.setPadding(0f);
        cell.setPaddingTop(5f);
        cell.setPaddingLeft(5);
        PdfPTable marcature = getMarcature(art, datiExtra, request, document, writer);
        cell.addElement(marcature);
        tableRight.addCell(cell);

        if (datiExtra != null) {

            List<Map> led = new ArrayList<>();
            List<Map> notLed = new ArrayList<>();

            String prevCdvistelet = "";

            for (Mrp_arch_articoli art_item : arts) {

                if (!prevCdvistelet.equals(art_item.getCdvistelet())) {

                    Vist_articoli_datiextra datiExtra_item = vistosiShopManager.getDatiExtraByCdartm(art_item.getCdartm());

                    if (datiExtra_item != null) {
                        String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa1");
                        flag = StringUtils.trimToEmpty(flag);
                        String tipo_attacco = BeanUtils.getSimpleProperty(datiExtra_item, "arwTipoAttacco1");
                        tipo_attacco = StringUtils.trimToEmpty(tipo_attacco);

                        if ("S".equals(flag)) {

                            Map m = new HashMap<String, BaseTableBean>();
                            m.put("art", art_item);
                            m.put("ext", datiExtra_item);

                            if ("LED".equals(tipo_attacco)) {
                                led.add(m);
                            } else {
                                notLed.add(m);
                            }

                            prevCdvistelet = art_item.getCdvistelet();
                        }
                    }

                }
            }

            PdfPTable elettrificazioni = new PdfPTable(1);
            elettrificazioni.setWidthPercentage(100);
            elettrificazioni.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            elettrificazioni.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            elettrificazioni.getDefaultCell().setPadding(0f);
            elettrificazioni.setExtendLastRow(true);
            cell = new PdfPCell(new Paragraph(messageSource.getMessage("specsheetmodel.title.elet", null, "ELETTRIFICAZIONI", locale).toUpperCase(), new Font(baseFont, 8)));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingTop(5f);
            cell.setPaddingBottom(5f);
            cell.setPaddingLeft(0);
            elettrificazioni.addCell(cell);

            for (Map mapItem : notLed) {

                cell = new PdfPCell();
                PdfPTable lampadine = getLampadine((Mrp_arch_articoli) mapItem.get("art"), (Vist_articoli_datiextra) mapItem.get("ext"), request, document, writer);
                cell.setBorderWidth(0f);
                cell.setPadding(0f);
                cell.setPaddingBottom(0f);
                cell.addElement(lampadine);
                elettrificazioni.addCell(cell);

            }
            if (elettrificazioni.size() == 1) {
                elettrificazioni.deleteLastRow();
            }

            int notLedRows = elettrificazioni.size();

//            cell = new PdfPCell(new Paragraph(messageSource.getMessage("specsheetgen.title.eletled", null, "ELETTRIFICAZIONI LED", locale).toUpperCase(), new Font(baseFont, 8)));
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setPaddingLeft(0);
//            cell.setPaddingTop(5f);
//            cell.setPaddingBottom(5f);
//            elettrificazioni.addCell(cell);

            for (Map mapItem : led) {

                cell = new PdfPCell();
                PdfPTable lampadine = getLampadine((Mrp_arch_articoli) mapItem.get("art"), (Vist_articoli_datiextra) mapItem.get("ext"), request, document, writer);
                cell.setBorderWidth(0f);
                cell.setPaddingLeft(0);
                cell.addElement(lampadine);
                elettrificazioni.addCell(cell);

            }
            if (elettrificazioni.size() - notLedRows == 1) {
                elettrificazioni.deleteLastRow();
            }

            cell = new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setBorderWidth(0f);
            cell.setPadding(0f);
            cell.setPaddingLeft(5);
            cell.addElement(elettrificazioni);
            tableRight.addCell(cell);
        }
        
        PdfPTable dimQr = new PdfPTable(2);
        dimQr.setWidthPercentage(100);
        dimQr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        dimQr.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        dimQr.getDefaultCell().setPadding(0f);
        //dimQr.setExtendLastRow(true);        
        
        //dimensioni articolo
        PdfPTable dimensioni = getDimensioni(art, request, document, writer);
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(0f);
        cell.setPaddingTop(15f);
        cell.addElement(dimensioni);
        dimQr.addCell(cell);   
        
        //QR code
        cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorderWidth(0f);
        cell.setPadding(0f);
        cell.addElement(getQR(art, datiExtra, famiglia, request, document, writer));        
        dimQr.addCell(cell);   

        cell = new PdfPCell();
        cell.setBorderWidth(0f);
        cell.setPadding(0f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);  
        cell.addElement(dimQr);
        tableRight.addCell(cell);        

        tableBody.addCell(tableRight);
        tableContainer.addCell(tableBody);

        //riga finiture del diffusore
        PdfPTable finiture = new PdfPTable(1);
        finiture.setWidthPercentage(100);
        finiture.getDefaultCell().setBorderWidth(0f);
        finiture.addCell(getFinituraDiffusore(art, vist_vetro, request, document, writer));

        cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorderWidth(0f);
        cell.setPadding(0f);
        cell.addElement(finiture);

        tableContainer.addCell(cell);

        if (!vist_finit_mont.isEmpty() && vist_finit_mont.get(0).getCdvistfinm() != null) {

            //riga montature
            PdfPTable montature = new PdfPTable(1);
            montature.setWidthPercentage(100);
            montature.getDefaultCell().setBorderWidth(0f);
            montature.addCell(getFinituraMontatura(art, vist_finit_mont, request, document, writer));

            cell = new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setBorderWidth(0f);
            cell.setPadding(0f);
            cell.addElement(montature);

            tableContainer.addCell(cell);

        }

        //riga versioni disponibili + QRcode
        PdfPTable versioni = new PdfPTable(1);
        versioni.setWidthPercentage(100);
        float[] versioniColumnWidth = {85, 15};
        //versioni.setWidths(versioniColumnWidth);
        versioni.getDefaultCell().setBorderWidth(0f);
        versioni.getDefaultCell().setPadding(0f);
        versioni.addCell(getVersioniAlternative(art, request, document, writer));

        //versioni.addCell("---");

        cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorderWidth(0f);
        cell.setPadding(0f);
        cell.addElement(versioni);

        tableContainer.addCell(cell);

//
////        PdfPTable tableHeader = getHeader(famiglia, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdclas_a, request, document, writer);
////        tableBody.addCell(tableHeader);
//        tableBody.getDefaultCell().setBorderWidth(0.2f);
//        tableBody.getDefaultCell().setPaddingBottom(0);
//        //PdfPTable tableDisegno = getDisegno(art, artFallback, request, document, writer);
//        //tableBody.addCell(tableDisegno);
//
//        PdfPTable tableBodycentral = new PdfPTable(2);
//        //float[] columnWidthTableBodycentral = {widthBody * 0.45f, widthBody * 0.55f};
//        tableBodycentral.setWidthPercentage(100);
//        tableBodycentral.setWidths(new float[]{0.40f, 0.60f});
//        tableBodycentral.getDefaultCell().setBorderWidth(0f);
//        //tableBodycentral.setTotalWidth(columnWidthTableBodycentral);
//
////        PdfPTable finiture = new PdfPTable(1);
////        finiture.setWidthPercentage(100);
////        finiture.getDefaultCell().setBorderWidth(0f);
////        finiture.addCell(getFinituraDiffusore(art, vist_vetro, request, document, writer));
//
//        if (!vist_finit_mont.isEmpty() && vist_finit_mont.get(0).getCdvistfinm() != null) {
//            finiture.addCell(getFinituraMontatura(art, vist_finit_mont, request, document, writer));
//        }
//
//        cell = new PdfPCell();
//        cell.setBorderWidth(0f);
//        cell.addElement(finiture);
//        tableBodycentral.addCell(cell);
//
//
//        tableBody.getDefaultCell().setBorderWidth(.0f);
//        tableBody.addCell(tableBodycentral);
//
//
//        PdfPTable tableRightBottom = new PdfPTable(1);
//        tableRightBottom.setWidthPercentage(100);
//        tableRightBottom.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//
//
//        cell = new PdfPCell();
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setPadding(0f);
//        cell.addElement(getQR(art, datiExtra, famiglia, request, document, writer));
//        tableRightBottom.addCell(cell);
//        tableRight.getDefaultCell().setPaddingLeft(0);
//        tableRight.getDefaultCell().setPaddingBottom(0);
//
//        cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//        cell.setBorderWidth(0f);
//        cell.addElement(tableRightBottom);
//        tableRight.addCell(cell);
        //tableContainer.addCell(tableRight);
        tableContainer.completeRow();

        document.add(tableContainer);
    }

//    @RequestMapping(value = "/specsheet/**")
//    public void createPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String pathInfo = request.getPathInfo();
//
//        createPDF(StringUtils.substringAfter(pathInfo, "/specsheet/"), request, response);
//    }
    public PdfPTable getHeader(Vist_famiglia vist_famiglia, Mrp_arch_articoli art, WebApplicationContext ctx, /*String cdvisttp, String cdvistv1, String cdvistv2, String cdvistv3, String cdclas_a,HttpServletRequest request,*/ Document document, PdfWriter writer, Boolean dataPage) throws DocumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        //WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        PdfPTable tableHeader = null;
        PdfPTable tableLogo = null;
        PdfPTable tableRiepilogo = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        //float[] columnWidth = {600, 220};
        //float[] columnWidth = {65, 35};
        float[] columnWidthLogo = {100};
        float[] columnWidthRiepilogo = {200, 200, 200};

        tableHeader = new PdfPTable(2);
        //tableHeader.setTotalWidth(columnWidth);
        
        if (dataPage) {
            float[] columnWidth = {50, 50};
            tableHeader.setWidths(columnWidth);
        } else {
            float[] columnWidth = {65, 35};
            tableHeader.setWidths(columnWidth);
        }        
        //tableHeader.setWidths(columnWidth);
        tableHeader.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableHeader.getDefaultCell().setBorderWidth(0f);
        //tableHeader.getDefaultCell().setBorderWidthBottom(1f);
        tableHeader.getDefaultCell().setPadding(0f);
        tableHeader.getDefaultCell().setPaddingBottom(3f);

        /**
         * *
         * logo Vistosi
         */
        tableLogo = new PdfPTable(1);
        //tableLogo.setTotalWidth(columnWidthLogo);
//        tableLogo.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableLogo.getDefaultCell().setBorderWidth(0f);
        tableLogo.getDefaultCell().setPadding(0f);
        tableLogo.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        Image img = null;

        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "logo_generica.png");
        img = Image.getInstance(realPath);
        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_LEFT);

        cell = new PdfPCell();
        cell.setFixedHeight(37.5f);
        cell.setBorderWidth(0f);
        cell.setBorderWidthBottom(.5f);
        cell.setPadding(0);
        cell.setPaddingBottom(5f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.addElement(img);
        tableLogo.addCell(cell);

        tableHeader.addCell(cell);

        if (dataPage) {
            /**
             * *
             * logo CSQ
             */
            img = null;

            realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "csq-iqnet.jpg");
            img = Image.getInstance(realPath);
            img.setAlignment(Image.TEXTWRAP | Image.ALIGN_RIGHT);
            //img.scalePercent(5);

            cell = new PdfPCell(img, true);
            cell.setFixedHeight(25f);
            cell.setBorderWidth(0f);
            cell.setBorderWidthBottom(.5f);
            cell.setPaddingBottom(5f);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableHeader.addCell(cell);
        }else{
            /**
             * made in italy
             */
            paragraph = new Paragraph("MADE IN ITALY", new Font(baseFont, 12));
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            cell = new PdfPCell();
            cell.setFixedHeight(25f);
            cell.setBorderWidth(0f);
            cell.setBorderWidthBottom(.5f);
            cell.setPaddingBottom(5f);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.addElement(paragraph);            
            tableHeader.addCell(cell);            
        }

//        PdfPTable tableDescrFamiglia = new PdfPTable(1);
//        tableDescrFamiglia.setWidthPercentage(100);
//        //tableDescrFamiglia.setWidths(new float[]{1.2f, 0.7f, 1.2f});
//        PdfPCell defaultCell = tableDescrFamiglia.getDefaultCell();
//        defaultCell.setPadding(0f);
//        defaultCell.setPaddingTop(3f);
//        defaultCell.setPaddingBottom(3f);
//        defaultCell.setBorderWidth(0);
//        defaultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        defaultCell.setUseAscender(false);
//        defaultCell.setUseDescender(true);
//
//        String fg_eurusa = "";
//        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
//            fg_eurusa = "U";
//        } else if (vistosiShopManager.DEFAULT_CDCLAS_A.contains(art.getCdclas_a())) {
//            fg_eurusa = "E";
//        }
//        String qrCodeLink = "http://www.vistosi.it/download-area/download-2d-3d.html?cdvistfam=" + art.getCdvistfam() + "&cdvisttp=" + art.getCdvisttp() + "&fg_eur_usa=" + fg_eurusa;
//
//        if (art.getCdvistv1() != null) {
//            qrCodeLink += "&cdvistv1=" + art.getCdvistv1();
//        }
//        if (art.getCdvistv2() != null) {
//            qrCodeLink += "&cdvistv2=" + art.getCdvistv2();
//        }
//        if (art.getCdvistv3() != null) {
//            qrCodeLink += "&cdvistv3=" + art.getCdvistv3();
//        }
//
//        String descrFamiglia = StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_famiglia, "dsvistfam" + getAtkLangsfx(locale.getLanguage())));
//
//        Chunk famLink = new Chunk(descrFamiglia, new Font(baseFont, 16));
//        famLink.setAction(new PdfAction(new URL(qrCodeLink)));
//        //descrizione famiglia
//        paragraph = new Paragraph();
//        paragraph.add(famLink);
//        //defaultCell.setPaddingLeft(20f);
//        tableDescrFamiglia.addCell(paragraph);

//        //descrizione designer
//        String designerDescription = "";
//        if (art.getCddesigner() != null) {
//            DesignerWithBLOBs designer = vistosiShopManager.getDesigner(art.getCddesigner());
//            if (designer != null && StringUtils.isNotBlank(designer.getDsdesigner())) {
//                designerDescription += "Design " + designer.getDsdesigner().replaceAll("\\s+", " ") + "\n";
//            }
//        }
//        paragraph = new Paragraph(designerDescription, new Font(baseFont, 7));
//        defaultCell.setPaddingTop(0f);
//        tableDescrFamiglia.addCell(paragraph);
//
//        tableHeader.addCell(tableDescrFamiglia);

//        //tipologia
//        PdfPTable tableDescrModello = new PdfPTable(1);
//        tableDescrModello.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
//        tableDescrModello.getDefaultCell().setUseAscender(true);
//        tableDescrModello.getDefaultCell().setUseDescender(false);
//
//        String version = "CE";
//        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
//            version = "UL";
//        }
//        paragraph = new Paragraph(descrFamiglia + " " + art.getCdvisttp() + (art.getCdvistv1() != null ? " " + art.getCdvistv1() : "") + (art.getCdvistv2() != null ? " " + art.getCdvistv2() : "") + (art.getCdvistv3() != null ? " " + art.getCdvistv3() : ""), new Font(baseFont, 12));
//        cell = tableDescrModello.getDefaultCell();
//        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        cell.setBorderWidthTop(0);
//        cell.setBorderWidthRight(0);
//        cell.setBorderWidthBottom(0);
//        cell.setBorderWidthLeft(.2f);
//        cell.setPaddingLeft(3f);
//        cell.setPaddingRight(5);
//        cell.setPaddingTop(2f);
//        cell.setPaddingBottom(0f);
//        cell.addElement(paragraph);
//        tableDescrModello.addCell(cell);
//
//        tableHeader.getDefaultCell().setPaddingTop(6f);
//        tableHeader.addCell(tableDescrModello);

//        tableHeader.completeRow();

        return tableHeader;

    }

    public PdfPTable getCertificazioni(HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

        PdfPTable tableRight = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        tableRight = new PdfPTable(1);
        tableRight.setWidthPercentage(100);
        tableRight.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableRight.getDefaultCell().setBorderWidth(1f);
//        tableRight.getDefaultCell().setBackgroundColor(BaseColor.YELLOW);

        Image img = null;

        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "csq-iqnet.jpg");
        img = Image.getInstance(realPath);
        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_LEFT);
        //img.scalePercent(5);

        cell = new PdfPCell(img, true);
        cell.setFixedHeight(25f);
        cell.setBorderWidth(0f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableRight.addCell(cell);

        tableRight.completeRow();

        return tableRight;

    }

    public PdfPTable getQR(Mrp_arch_articoli art, Vist_articoli_datiextra datiExtra, Vist_famiglia vist_famiglia, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        table = new PdfPTable(1);
        //table.setWidthPercentage(100);
        //table.setWidths(new int[]{1, 2});
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setBorderWidth(0f);

        cell = new PdfPCell();
        cell.setBorderWidth(0f);
        cell.setPaddingLeft(0f);
        cell.setPaddingRight(2f);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

//        PdfPTable text = new PdfPTable(1);
//        text.getDefaultCell().setBorderWidth(0f);
//        paragraph = new Paragraph("QRCODE", new Font(baseFontBold, 8));
//        text.addCell(paragraph);
        paragraph = new Paragraph(messageSource.getMessage("specsheetmodel.text.download", null, "download risorse", locale), new Font(baseFont, 7));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(paragraph);
        table.addCell(cell);

        String fg_eurusa = "";
        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
            fg_eurusa = "U";
        } else if (vistosiShopManager.DEFAULT_CDCLAS_A.contains(art.getCdclas_a())) {
            fg_eurusa = "E";
        }
        String qrCodeLink = "http://www.vistosi.it/download-area/download-2d-3d.html?cdvistfam=" + art.getCdvistfam() + "&cdvisttp=" + art.getCdvisttp() + "&fg_eur_usa=" + fg_eurusa;

        if (art.getCdvistv1() != null) {
            qrCodeLink += "&cdvistv1=" + art.getCdvistv1();
        }
        if (art.getCdvistv2() != null) {
            qrCodeLink += "&cdvistv2=" + art.getCdvistv2();
        }
        if (art.getCdvistv3() != null) {
            qrCodeLink += "&cdvistv3=" + art.getCdvistv3();
        }

        BarcodeQRCode qrcode = new BarcodeQRCode(qrCodeLink, 1, 1, null);
        Image qr_image = qrcode.getImage();
        qr_image.setAnnotation(new Annotation(0, 0, 0, 0, qrCodeLink));

//        Image img = null;
//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "csq-iqnet.jpg");
//        img = Image.getInstance(realPath);
//        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_LEFT);
        //img.scalePercent(5);
        cell = new PdfPCell(qr_image, false);
        //cell.setFixedHeight(25f);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorderWidth(0f);
        cell.setPadding(5f);
        cell.setPaddingLeft(0f);
        cell.setPaddingRight(0f);
        table.addCell(cell);

//        String path_modello = "fileresources/models";
//        String nome_modello = art.getVist_filedis();
//        String portalURL = getPortalUrl();
//        String www = "http://www.vistosi";
//        if (locale.getLanguage().equals("it")) {
//            www += ".it";
//        } else if (locale.getLanguage().equals("ru")) {
//            www += ".ru";
//        } else {
//            www += ".com";
//        }
//        String shopURL = www + "/" + (getPortalUrl().contains("test") ? "shop_test" : "shop");
//
//        art.setVist_var1(vistosiShopManager.getVist_var1ByKey(art.getCdvistv1()));
//        art.setVist_var2(vistosiShopManager.getVist_var2ByKey(art.getCdvistv2()));
//        art.setVist_var3(vistosiShopManager.getVist_var3ByKey(art.getCdvistv3()));
//
//        ShopUser user = null;
//        if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
//            user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        }
//
//        if ((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
//                || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US"))) {
//
//            Vist_elettrificazioni elet = vistosiShopManager.getVist_elettrificazioniByKey(art.getCdvistelet());
//            if (elet != null && elet.getCdul() != null) {
//                Vist_elettrificazioni altElet = vistosiShopManager.getVist_elettrificazioniByKey(elet.getCdul());
//                art.setVist_elettrificazioni(altElet);
//            }
//        } else {
//            art.setVist_elettrificazioni(vistosiShopManager.getVist_elettrificazioniByKey(art.getCdvistelet()));
//        }
//
//        String descrFile = "";
//
//        try {
//            descrFile = BeanUtils.getSimpleProperty(vist_famiglia, "dsvistfam" + getAtkLangsfx(locale.getLanguage())) + " " + art.getCdvisttp()
//                    + " " + (art.getCdvistv1() != null && art.getVist_var1() != null ? (BeanUtils.getSimpleProperty(art.getVist_var1(), "dsextvistv1" + getAtkLangsfx(locale.getLanguage()))).toUpperCase() : "")
//                    + " " + (art.getCdvistv2() != null && art.getVist_var2() != null ? (BeanUtils.getSimpleProperty(art.getVist_var2(), "dsextvistv2" + getAtkLangsfx(locale.getLanguage()))).toUpperCase() : "")
//                    + " " + (art.getCdvistv3() != null && art.getVist_var3() != null ? (BeanUtils.getSimpleProperty(art.getVist_var3(), "dsextvistv3" + getAtkLangsfx(locale.getLanguage()))).toUpperCase() : "")
//                    + " " + (art.getCdvistelet() != null && art.getVist_elettrificazioni() != null ? (BeanUtils.getSimpleProperty(art.getVist_elettrificazioni(), "dsextvistelet" + getAtkLangsfx(locale.getLanguage()))).toUpperCase() : "");
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvocationTargetException ex) {
//            Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchMethodException ex) {
//            Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String path_2D = path_modello + "/2D/";
//        //dwg cm
//        String dwg_vers = "cm/";
//        if ("en".equals(locale.getLanguage())) {
//            dwg_vers = "po/";
//            art.setTiporisorsa2D_dwg(com.ateikon.common.Mrp_arch_articoli.MOD2D_DWG_PO);
//        }
////        if ((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
////                || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US"))) {
////            dwg_vers = "po/";
////        }
//
//        String dwg = path_2D + dwg_vers + nome_modello + ".dwg";
//        String m2DLabel = messageSource.getMessage("modelli_2D", null, "Modelli 2D", locale);
//        Chunk ck = new Chunk(messageSource.getMessage("specsheetgen.text.download2D", null, "disegno 2D (file DWG)", locale));
//        try {
//            String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), dwg);
//            File f = new File(path_to_filemodel);
//            if (f.exists()) {
//
//                String dURL = shopURL + "/logdown";
//
//                ck.setAction(new PdfAction(new URL(dURL + "?file_req=" + dwg + "&nome_modello=" + nome_modello + "&cdvistelet=" + art.getCdvistelet() + "&tiporisorsa=" + art.getTiporisorsa2D_dwg() + "&dsfile=" + m2DLabel + " " + descrFile)));
//            }
//        } catch (FileNotFoundException ex) {
//        }
//        paragraph = new Paragraph("", new Font(baseFont, 6));
//        paragraph.add(ck);
//        text.addCell(paragraph);
        //verifica esistenza 3D
//        String path_3D = path_modello + "/3D/";
//        String m3DLabel = messageSource.getMessage("modelli_3D", null, "Modelli 3D", locale);
//        //String igs = path_3D + nome_modello + ".zip";
//        ck = new Chunk(messageSource.getMessage("specsheetgen.text.download3D", null, "disegno 3D (file EASM)", locale));
//        try {
//            //igs
////            String igs = path_3D + nome_modello + (art.isLed() ? art.getCdvistelet() : "") + ".zip";
////            String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), igs);
////            File f = new File(path_to_filemodel);
////
////
////            if (f.exists()) {
////                //ck.setAction(new PdfAction(new URL(portalURL + "download/" + igs + "?f=" + igs)));
////
////                dURL += "&file_req=" + igs 
////                        + "&dsfile=" + m3DLabel 
////                        + "&nome_modello=" + nome_modello
////                        + "&cdvistelet="+ art.getCdvistelet()
////                        + "&tiporisorsa=" + art.getTiporisorsa3D_igs()
////                        + " " + descrFile;
////
////            }
//
////            if (dURL.contains("file_req")) {
////                dURL += "&";
////            }
//            //String easm = path_3D + nome_modello + ".EASM";
//            String easm = path_3D + nome_modello + (art.isLed() ? art.getCdvistelet() : "") + ".EASM";
////            try {
//            String dURL = shopURL + "/logdown?"; //&nome_modello=" + nome_modello + "&cdvistelet="+ art.getCdvistelet() + "&tiporisorsa=" + art.getTiporisorsa3D_easm();
//            String path_to_filemodel = WebUtils.getRealPath(ctx.getServletContext(), easm);
//            File f = new File(path_to_filemodel);
//            if (f.exists()) {
//                dURL += "&file_req=" + easm
//                        + "&dsfile=" + m3DLabel
//                        + "&nome_modello=" + nome_modello
//                        + "&cdvistelet=" + art.getCdvistelet()
//                        + "&tiporisorsa=" + art.getTiporisorsa3D_easm()
//                        + " " + descrFile;
//                //ck.setAction(new PdfAction(new URL(portalURL + "download/" + easm + "?f=" + easm)));
//            }
////            } catch (FileNotFoundException ex) {
////            }
//
//            if (dURL.contains("file_req")) {
//                ck.setAction(new PdfAction(new URL(dURL)));
//            }
//        } catch (FileNotFoundException ex) {
//        }
//        paragraph = new Paragraph("", new Font(baseFont, 6));
//        paragraph.add(ck);
//        text.addCell(paragraph);
        //verifica esistenza file istruzioni di montaggio
//        ck = new Chunk(messageSource.getMessage("specsheetgen.text.istrmont", null, "istruzioni montaggio (file PDF)", locale));
//        String istrLabel = messageSource.getMessage("istruzioni_montaggio", null, "Istruzioni di montaggio", locale);
//        String path_techsheet = "fileresources/assembling_instructions/";
//        if (datiExtra != null) {
//            String techsheet = datiExtra.getArwFileSchedaTec();
//            if (StringUtils.isNotEmpty(techsheet)) {
//                try {
//
//                    techsheet = StringUtils.substringAfterLast(techsheet, "\\");
//
//                    String path_to_techsheet = WebUtils.getRealPath(ctx.getServletContext(), path_techsheet + techsheet);
//                    File f = new File(path_to_techsheet);
//                    Vist_articoli_img vist_articoli_img = new Vist_articoli_img();
//                    vist_articoli_img.setPathschtec(techsheet);
//                    if (f.exists()) {
//
//                        String dURL = shopURL + "/logdown";
//
//                        ck.setAction(new PdfAction(new URL(dURL + "?file_req=" + path_techsheet + techsheet + "&dsfile=" + istrLabel + " " + descrFile + "&nome_modello=" + nome_modello + "&cdvistelet=" + art.getCdvistelet() + "&tiporisorsa=" + art.getTiporisorsaIstruzioni())));
//                        //ck.setAction(new PdfAction(new URL(portalURL + "download/" + techsheet + "?f=" + path_techsheet + techsheet)));
//                    }
//                } catch (Exception ex) {
//                }
//            }
//        }
//
//        paragraph = new Paragraph("", new Font(baseFont, 6));
//        paragraph.add(ck);
//        text.addCell(paragraph);
//        table.addCell(text);
        //table.completeRow();
        return table;

    }

    public PdfPTable getDisegno(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable tableDisegno = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {380};

        tableDisegno = new PdfPTable(1);
        tableDisegno.setWidthPercentage(100);
        PdfPCell defaultCell = tableDisegno.getDefaultCell();
        defaultCell.setBorder(PdfPCell.RECTANGLE);
        defaultCell.setBorderWidth(.0f);
        defaultCell.setPadding(0);
        defaultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        defaultCell.setVerticalAlignment(Element.ALIGN_CENTER);

        Map<String, Object> mapRes = getResourcesPath(art, request, false, "pdf");
        String realPath = (String) mapRes.get("realPath");

        /*realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".pdf");

         //File f = new File(realPath);
         if (!new File(realPath).exists()) { 
            
         filename = StringUtils.stripEnd(filename, art.getCdvistelet());
         realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".pdf");
                     
         if(!new File(realPath).exists()){
         realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".pdf");
         }
         }*/
//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".pdf");
        FileInputStream fis = new FileInputStream(realPath);

        PdfReader readerDis = new PdfReader(fis);
        PdfImportedPage pdfDis = writer.getImportedPage(readerDis, 1);

        Image img = Image.getInstance(pdfDis);
//        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_CENTER);
        img.setAlignment(Image.ALIGN_MIDDLE);

        cell = new PdfPCell(img, true);
        cell.setFixedHeight(440);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.addElement(img);

        tableDisegno.addCell(cell);

        fis.close();

//        tableDisegno.completeRow();
        return tableDisegno;

    }

//    public PdfPTable getDisegno(Mrp_arch_articoli art, Mrp_arch_articoli artFallback, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//
//        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
//        RequestContext rc = new RequestContext(request);
//
//        PdfPTable tableDisegno = null;
//        PdfPCell cell = null;
//        Paragraph paragraph = null;
//
//        float[] columnWidth = {380};
//
//        tableDisegno = new PdfPTable(1);
//        tableDisegno.setWidthPercentage(100);
//        PdfPCell defaultCell = tableDisegno.getDefaultCell();
//        defaultCell.setBorder(PdfPCell.RECTANGLE);
//        defaultCell.setBorderWidth(.0f);
//        defaultCell.setPadding(0);
//        defaultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        defaultCell.setVerticalAlignment(Element.ALIGN_CENTER);
//
//        Map<String, Object> mapRes = getResourcesPath(art, request, false, "pdf");
//        String realPath = (String) mapRes.get("realPath");
//
//        /*realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".pdf");
//
//         //File f = new File(realPath);
//         if (!new File(realPath).exists()) { 
//            
//         filename = StringUtils.stripEnd(filename, art.getCdvistelet());
//         realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".pdf");
//                     
//         if(!new File(realPath).exists()){
//         realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".pdf");
//         }
//         }*/
////        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".pdf");
//        FileInputStream fis = new FileInputStream(realPath);
//
//        PdfReader readerDis = new PdfReader(fis);
//        PdfImportedPage pdfDis = writer.getImportedPage(readerDis, 1);
//
//        Image img = Image.getInstance(pdfDis);
////        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_CENTER);
//        img.setAlignment(Image.ALIGN_MIDDLE);
//
//        cell = new PdfPCell(img, true);
//        cell.setFixedHeight(410);
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setVerticalAlignment(Element.ALIGN_CENTER);
//        cell.setPaddingBottom(10);
//        cell.addElement(img);
//
//        tableDisegno.addCell(cell);
//
//        fis.close();
//
////        tableDisegno.completeRow();
//        //sovrappone la descrizione della tipologia elettrificazione raffigurata nel disegno tecnico
//        float leftSpacing = 5f;
//        float iconWidth = 7f;
//        float signWidth = 15f;
//        PdfPTable disegnoDescrInner = new PdfPTable(3);
//        Chunk chunk = new Chunk(messageSource.getMessage("specsheetgen.text.disegnodida", null, "QUESTA IMMAGINE SI RIFERISCE ALLA VERSIONE ", locale).toUpperCase(), new Font(baseFont, 7));
//        float widthPoint = chunk.getWidthPoint();
//        disegnoDescrInner.setTotalWidth(new float[]{widthPoint + leftSpacing, iconWidth, signWidth});
//        disegnoDescrInner.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
//        disegnoDescrInner.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//        disegnoDescrInner.getDefaultCell().setPaddingTop(4f);
//        disegnoDescrInner.getDefaultCell().setPaddingBottom(4f);
//        disegnoDescrInner.getDefaultCell().setBorderWidth(.0f);
//        disegnoDescrInner.getDefaultCell().setBorderWidthTop(.25f);
//        disegnoDescrInner.getDefaultCell().setBorderWidthLeft(.25f);
//        disegnoDescrInner.getDefaultCell().setUseAscender(true);
//        disegnoDescrInner.getDefaultCell().setUseDescender(false);
//        disegnoDescrInner.addCell(new Phrase(chunk));
//
//        Vist_articoli_datiextra datiExtra;
//        if ((boolean) mapRes.get("requested")) {
//            datiExtra = vistosiShopManager.getDatiExtraByCdartm(art.getCdartm());
//            selCdvistelet = art.getCdvistelet();
//        } else {
//            datiExtra = vistosiShopManager.getDatiExtraByCdartm(artFallback.getCdartm());
//            selCdvistelet = artFallback.getCdvistelet();
//        }
//
//        if (datiExtra != null) {
//            String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa1");
//            flag = StringUtils.trimToEmpty(flag);
//
//            if (StringUtils.equals(flag, "S")) {
//                String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco1");
//                String[] split = nomeimg.split("\\\\");
//                String acronimoLampada = (tipoLampadine.get(split[0]) != null ? tipoLampadine.get(split[0]) : split[0]) + ".jpg";
//                nomeimg = split[1];
//
//                String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(), pathimg + acronimoLampada);
//                File fAcronimo = new File(realPathAcronimo);
//
//                if (fAcronimo.exists()) {
//                    Image arrow = Image.getInstance(WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + ARROW_ICON));
//                    //arrow.scalePercent(50f);
//                    arrow.setAlignment(Image.TEXTWRAP | Image.ALIGN_CENTER);
//                    PdfPTable imgIcon = new PdfPTable(1);
//                    cell = new PdfPCell(arrow, true);
//                    cell.setPaddingTop(4f);
//                    cell.setPaddingRight(0f);
//                    cell.setPaddingBottom(0f);
//                    cell.setPaddingLeft(2f);
//                    cell.setBorderWidth(0f);
//                    imgIcon.addCell(cell);
//
//                    cell = new PdfPCell(imgIcon);
//                    //cell.setFixedHeight(5);
//                    cell.setBorderWidthTop(.25f);
//                    cell.setBorderWidthRight(.0f);
//                    cell.setBorderWidthBottom(.0f);
//                    cell.setBorderWidthLeft(.0f);
//                    cell.setPaddingTop(0f);
//                    cell.setPaddingRight(0f);
//                    cell.setPaddingBottom(0f);
//                    cell.setPaddingLeft(0f);
//                    cell.setUseAscender(true);
//                    cell.setUseDescender(false);
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    //cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    //cell.setBackgroundColor(BaseColor.CYAN);
//                    disegnoDescrInner.addCell(cell);
//
//                    Image image = Image.getInstance(realPathAcronimo);
//                    image.setAlignment(Image.TEXTWRAP | Image.ALIGN_CENTER);
//                    cell = new PdfPCell(image, true);
//                    cell.setFixedHeight(10);
//                    cell.setBorderWidthTop(.25f);
//                    cell.setBorderWidthRight(.0f);
//                    cell.setBorderWidthBottom(.0f);
//                    cell.setBorderWidthLeft(.0f);
//                    cell.setPaddingTop(0f);
//                    cell.setPaddingRight(2f);
//                    cell.setPaddingBottom(0f);
//                    cell.setPaddingLeft(2f);
//                    cell.setUseAscender(true);
//                    cell.setUseDescender(false);
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    disegnoDescrInner.addCell(cell);
//
////                    cell = new PdfPCell(new Phrase("*", new Font(baseFont, 8)));
////                    cell.setPadding(0f);
////                    cell.setPaddingRight(2f);
////                    cell.setBorderWidthTop(.25f);
////                    cell.setBorderWidthRight(.0f);
////                    cell.setBorderWidthBottom(.0f);
////                    cell.setBorderWidthLeft(.0f);
////                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    disegnoDescrInner.addCell(cell);                     
//                }
//            }
//
//        }
//
//        disegnoDescrInner.setTotalWidth(widthPoint + leftSpacing + iconWidth + signWidth);
//        disegnoDescrInner.writeSelectedRows(0, -1, document.leftMargin() + 420f - (widthPoint + leftSpacing + iconWidth + signWidth) - 7, document.getPageSize().getHeight() - 456,
//                writer.getDirectContent());
//
//        return tableDisegno;
//
//    }
    private String findPreferred(Mrp_arch_articoli art, List<Vist_elettrificazioni> vist_elettrificazioni, HttpServletRequest request, boolean byLang) throws FileNotFoundException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        List<String> possibleFilenameList = new ArrayList<String>();
        String filename = art.getVist_filedis();
        possibleFilenameList.add(filename);

        String suffix = "";

        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
            suffix = "UL";
            possibleFilenameList.add(0, filename + suffix);
        }

        for (String file : possibleFilenameList) {

            //String pdf = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + ".pdf");
            //String xls = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + locale.getLanguage() : "") + ".xlsx");
            if (checkResourcesExists(ctx.getServletContext(), file, byLang, locale.getLanguage())) {
                return vist_elettrificazioni.get(0).getCdvistelet();
            }
        }

        for (Vist_elettrificazioni elettrificazione : vist_elettrificazioni) {

            String file = filename + elettrificazione.getCdvistelet() + suffix;
            //String pdf = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + ".pdf");
            //String xls = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + locale.getLanguage() : "") + ".xls");

            if (checkResourcesExists(ctx.getServletContext(), file, byLang, locale.getLanguage())) {
                return elettrificazione.getCdvistelet();
            }
        }

        return art.getCdvistelet();
    }

    private boolean checkResourcesExists(ServletContext ctx, String file, boolean byLang, String lang) throws FileNotFoundException {

        String pdf = WebUtils.getRealPath(ctx, ROOT_RES + "/risorse/" + file + ".pdf");
        String xls = WebUtils.getRealPath(ctx, ROOT_RES + "/risorse/" + file + (byLang ? "_" + lang : "") + ".xls");

        if (new File(pdf).exists() && new File(xls).exists()) {
            return true;
        }

        return false;
    }

    private Map<String, Object> getResourcesPath(Mrp_arch_articoli art, HttpServletRequest request, boolean byLang, String ext) throws FileNotFoundException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        String[] noFallback = new String[]{"it", "en"};
        List<String> noFallbackList = Arrays.asList(noFallback);

        List<String> possibleFilenameList = new ArrayList<String>();
        String filename = art.getVist_filedis();
        possibleFilenameList.add(filename);

        if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {

            possibleFilenameList.add(0, filename + "UL");
            possibleFilenameList.add(0, filename + art.getCdvistelet() + "UL");
        } else {
            filename += art.getCdvistelet();
            possibleFilenameList.add(0, filename);
        }

        Map result = new HashMap();
        String realPath = null;

        for (int i = 0; i < possibleFilenameList.size(); i++) {
            String file = possibleFilenameList.get(i);

            realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + locale.getLanguage() : "") + "." + ext);

            if (new File(realPath).exists()) {
                result.put("realPath", realPath);

                if (i == 0) {
                    result.put("requested", true);
                } else {
                    result.put("requested", false);
                }
                break;
            }

            //if xlsx e != en e !=it -> check en
            if (StringUtils.equals(ext, "xlsx") && !noFallbackList.contains(locale.getLanguage())) {
                realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + "en" : "") + "." + ext);

                if (new File(realPath).exists()) {
                    result.put("realPath", realPath);

                    //override con l'inglese
                    locale = new Locale("en");

                    if (i == 0) {
                        result.put("requested", true);
                    } else {
                        result.put("requested", false);
                    }

                    break;
                }
            }
        }
//        for (String file : possibleFilenameList) {
//
//            realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + locale.getLanguage() : "") + "." + ext);
//
//            if (new File(realPath).exists()) {
//                break;
//            }
//
//        }
        return result;
    }

    public PdfPTable getDisegnoDim(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        PdfPTable tableCnt = new PdfPTable(1);
        tableCnt.setWidthPercentage(100);
        tableCnt.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableCnt.getDefaultCell().setBorderWidth(.0f);
        PdfPCell cellCnt = new PdfPCell();
        cellCnt.setBorderWidth(.2f);
        cellCnt.setPaddingTop(2);
        cellCnt.setPaddingRight(5);
        cellCnt.setPaddingBottom(4);
        cellCnt.setPaddingLeft(5);

        table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorder(PdfPCell.NO_BORDER);
        defaultCell.setPaddingTop(2);
        defaultCell.setPaddingRight(0);
        defaultCell.setPaddingBottom(2);
        defaultCell.setPaddingLeft(0);
        defaultCell.setHorizontalAlignment(Element.ALIGN_LEFT);

//        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("dimensioni", null, locale).toUpperCase(), new Font(baseFontBold, 8));
//        table.addCell(paragraph1);
        //String realPath = WebUtils.getRealPath(ctx.getServletContext(), "/images/articoli/disegnitecnici/" + art.getVist_filedis() + ".jpg");
        String realPath = WebUtils.getRealPath(ctx.getServletContext(), "/images/articoli/disegnitecnici/scheda_prodotto/" + art.getVist_filedis() + ".pdf");
        
        FileInputStream fis = new FileInputStream(realPath);

        PdfReader readerDis = new PdfReader(fis);
        PdfImportedPage pdfDis = writer.getImportedPage(readerDis, 1);

        //Image img = Image.getInstance(realPath);
        Image img = Image.getInstance(pdfDis);        
        

        img.setAlignment(Image.ALIGN_CENTER);

        cell = new PdfPCell(img, true);
        cell.setFixedHeight(315);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(0);      
        
        cell.addElement(img);

        table.addCell(cell);
        
        fis.close();

        //versioni alternative
//        String cdalias = StringUtils.rightPad(art.getCdvistfam(), 5) + art.getCdvisttp() + "%";
//        Map pars = new HashMap();
//        pars.put("cdalias", cdalias);
//
//        Cookie ckViewOff = WebUtils.getCookie(request, "filter_off");
//        pars.put("fgpromo", ((ckViewOff != null && "S".equals(ckViewOff.getValue())) ? "S" : null));
//
//        List<Mrp_arch_stato> availableStates = vistosiShopManager.getAvailableStates();
//        List<String> statiFilter = new ArrayList<String>();
//        for (Mrp_arch_stato mrp_arch_stato : availableStates) {
//            String cookieName = "view-stato_" + mrp_arch_stato.getCdstato();
//
//            Cookie ckView = WebUtils.getCookie(request, cookieName);
//            if (ckView != null && "S".equals(ckView.getValue())) {
//                statiFilter.add(mrp_arch_stato.getCdstato());
//            }
//        }
//        if (!statiFilter.isEmpty()) {
//            pars.put("statiFilterList", statiFilter);
//        }
//        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
//            pars.put("statiEscludedList", Collections.singletonList("ZEE"));
//        }
//
//        vistosiShopManager.addCdclasFilter(pars, request);
//        List<Mrp_arch_articoli> modelli = vistosiShopManager.getModelliDis(pars);
//        //shrink models list
//        List<Mrp_arch_articoli> modellidis = new ArrayList<Mrp_arch_articoli>();
//        if (modelli.size() > 1) {
//
//            Mrp_arch_articoli artSeed = modelli.get(0);
//
//            for (int i = 1; i < modelli.size(); i++) {
//
//                Mrp_arch_articoli mrp_arch_articoli = modelli.get(i);
//                if (StringUtils.equalsIgnoreCase(mrp_arch_articoli.getVist_filedis(), artSeed.getVist_filedis())) {
//
//                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv1(), mrp_arch_articoli.getCdvistv1())) {
//                        artSeed.setCdvistv1(null);
//                    }
//                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv2(), mrp_arch_articoli.getCdvistv2())) {
//                        artSeed.setCdvistv2(null);
//                    }
//                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv3(), mrp_arch_articoli.getCdvistv3())) {
//                        artSeed.setCdvistv3(null);
//                    }
//                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistcolv(), mrp_arch_articoli.getCdvistcolv())) {
//                        artSeed.setCdvistcolv(null);
//                    }
//                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinv(), mrp_arch_articoli.getCdvistfinv())) {
//                        artSeed.setCdvistfinv("ign");
//                    }
//                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinm(), mrp_arch_articoli.getCdvistfinm())) {
//                        artSeed.setCdvistfinm(null);
//                    }
//                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistelet(), mrp_arch_articoli.getCdvistelet())) {
//                        artSeed.setCdvistelet(null);
//                    }
//
//                } else {
//
//                    modellidis.add(artSeed);
//                    artSeed = mrp_arch_articoli;
//
//                }
//
//                if (i == modelli.size() - 1) {
//                    modellidis.add(artSeed);
//                }
//            }
//        } else {
//            modellidis = modelli;
//        }
//        if (!modellidis.isEmpty() && modellidis.size() > 1) {
//            defaultCell.setPaddingBottom(0);
//            table.addCell(new Paragraph(messageSource.getMessage("versioni.alt", null, locale).toUpperCase(), new Font(baseFontBold, 8)));
//            table.addCell(new Paragraph(messageSource.getMessage("versioni.alt.linkalert", null, locale), new Font(baseFont, 6)));
//
//            String vers = "";
//
//            String fg_eurusa = "";
//            if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
//                fg_eurusa = "U";
//            } else if (vistosiShopManager.DEFAULT_CDCLAS_A.contains(art.getCdclas_a())) {
//                fg_eurusa = "E";
//            }
//            String www = "http://www.vistosi";
//            if (locale.getLanguage().equals("it")) {
//                www += ".it";
//            } else if (locale.getLanguage().equals("ru")) {
//                www += ".ru";
//            } else {
//                www += ".com";
//            }
//
//            String versAltLink = www + "/download-area/download-2d-3d.html?cdvistfam=" + art.getCdvistfam() + "&cdvisttp=" + art.getCdvisttp() + "&fg_eur_usa=" + fg_eurusa;
//
//            //Vist_famiglia vist_famiglia = vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam());
//            //www += "/shop/scheda-" + vist_famiglia.getDsvistfam() + "-" + art.getCdvisttp() + "/" + art.getCdvistfam() + "?";
//            List<Chunk> versList = new ArrayList<>();
//            for (Mrp_arch_articoli mrp_arch_articoli : modellidis) {
//                if (!StringUtils.equals(art.getCdvistv1(), mrp_arch_articoli.getCdvistv1())
//                        || !StringUtils.equals(art.getCdvistv2(), mrp_arch_articoli.getCdvistv2())
//                        || !StringUtils.equals(art.getCdvistv3(), mrp_arch_articoli.getCdvistv3())) {
//                    String filedis = mrp_arch_articoli.getVist_filedis();
//
//                    String artLink = versAltLink;
//
//                    if (mrp_arch_articoli.getCdvistv1() != null) {
//                        artLink += "&cdvistv1=" + mrp_arch_articoli.getCdvistv1();
//                    }
//                    if (mrp_arch_articoli.getCdvistv2() != null) {
//                        artLink += "&cdvistv2=" + mrp_arch_articoli.getCdvistv2();
//                    }
//                    if (mrp_arch_articoli.getCdvistv3() != null) {
//                        artLink += "&cdvistv3=" + mrp_arch_articoli.getCdvistv3();
//                    }
//
//                    filedis = StringUtils.remove(filedis, StringUtils.rightPad(art.getCdvistfam(), 5) + art.getCdvisttp());
//                    filedis = StringUtils.substringBefore(filedis, "-");
//                    filedis = StringUtils.trim(filedis);
//                    if (StringUtils.isNotBlank(filedis)) {
//                        vers += filedis + ", ";
//                        Chunk ck = new Chunk(filedis);
//                        ck.setAction(new PdfAction(new URL(artLink)));
//                        versList.add(ck);
//                        versList.add(new Chunk(", "));
//                    }
//                }
//            }
//
//            Paragraph pVersionsLinks = new Paragraph("", new Font(baseFont, 7));
//            if (versList.size() > 0) {
//                versList.remove(versList.size() - 1);
//            }
//            pVersionsLinks.addAll(versList);
//
//            //table.addCell(new Paragraph(StringUtils.removeEnd(vers, ", "), new Font(baseFont, 7)));
//            table.addCell(pVersionsLinks);
//
//        } else {
//            table.addCell(new Paragraph(" ", new Font(baseFontBold, 8)));
//            table.addCell(new Paragraph(" ", new Font(baseFont, 7)));
//        }
//        cellCnt.addElement(table);
//        tableCnt.addCell(cellCnt);
        return table;

    }

    public PdfPTable getVersioniAlternative(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;

        PdfPTable tableCnt = new PdfPTable(1);
        tableCnt.setWidthPercentage(100);
        tableCnt.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableCnt.getDefaultCell().setBorderWidth(.0f);
        PdfPCell cellCnt = new PdfPCell();
        cellCnt.setBorderWidth(.2f);
        cellCnt.setPaddingTop(0);
        cellCnt.setPaddingRight(5);
        cellCnt.setPaddingBottom(0);
        cellCnt.setPaddingLeft(5);

        table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorder(PdfPCell.NO_BORDER);
        defaultCell.setPaddingTop(2);
        defaultCell.setPaddingRight(0);
        defaultCell.setPaddingBottom(2);
        defaultCell.setPaddingLeft(0);
        defaultCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        //versioni alternative
        String cdalias = StringUtils.rightPad(art.getCdvistfam(), 5) + art.getCdvisttp() + "%";
        Map pars = new HashMap();
        pars.put("cdalias", cdalias);

        Cookie ckViewOff = WebUtils.getCookie(request, "filter_off");
        pars.put("fgpromo", ((ckViewOff != null && "S".equals(ckViewOff.getValue())) ? "S" : null));

        List<Mrp_arch_stato> availableStates = vistosiShopManager.getAvailableStates();
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
            pars.put("statiEscludedList", Collections.singletonList("ZEE"));
        }

        vistosiShopManager.addCdclasFilter(pars, request);
        List<Mrp_arch_articoli> modelli = vistosiShopManager.getModelliDis(pars);
        //shrink models list
        List<Mrp_arch_articoli> modellidis = new ArrayList<Mrp_arch_articoli>();
        if (modelli.size() > 1) {

            Mrp_arch_articoli artSeed = modelli.get(0);

            for (int i = 1; i < modelli.size(); i++) {

                Mrp_arch_articoli mrp_arch_articoli = modelli.get(i);
                if (StringUtils.equalsIgnoreCase(mrp_arch_articoli.getVist_filedis(), artSeed.getVist_filedis())) {

                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv1(), mrp_arch_articoli.getCdvistv1())) {
                        artSeed.setCdvistv1(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv2(), mrp_arch_articoli.getCdvistv2())) {
                        artSeed.setCdvistv2(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv3(), mrp_arch_articoli.getCdvistv3())) {
                        artSeed.setCdvistv3(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistcolv(), mrp_arch_articoli.getCdvistcolv())) {
                        artSeed.setCdvistcolv(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinv(), mrp_arch_articoli.getCdvistfinv())) {
                        artSeed.setCdvistfinv("ign");
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinm(), mrp_arch_articoli.getCdvistfinm())) {
                        artSeed.setCdvistfinm(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistelet(), mrp_arch_articoli.getCdvistelet())) {
                        artSeed.setCdvistelet(null);
                    }

                } else {

                    modellidis.add(artSeed);
                    artSeed = mrp_arch_articoli;

                }

                if (i == modelli.size() - 1) {
                    modellidis.add(artSeed);
                }
            }
        } else {
            modellidis = modelli;
        }

        if (!modellidis.isEmpty() && modellidis.size() > 1) {
            defaultCell.setPaddingBottom(0);
            table.addCell(new Paragraph(messageSource.getMessage("versioni.altre.disp", null, "altre versioni disponibili", locale).toUpperCase(), new Font(baseFont, 8)));
            table.addCell(new Paragraph(messageSource.getMessage("versioni.alt.linkalert", null, locale), new Font(baseFont, 6)));

            String vers = "";

            String fg_eurusa = "";
            if (vistosiShopManager.DEFAULT_CDCLAS_A_US.contains(art.getCdclas_a())) {
                fg_eurusa = "U";
            } else if (vistosiShopManager.DEFAULT_CDCLAS_A.contains(art.getCdclas_a())) {
                fg_eurusa = "E";
            }
            String www = "http://www.vistosi";
            if (locale.getLanguage().equals("it")) {
                www += ".it";
            } else if (locale.getLanguage().equals("ru")) {
                www += ".ru";
            } else {
                www += ".com";
            }

            String versAltLink = www + "/download-area/download-2d-3d.html?cdvistfam=" + art.getCdvistfam() + "&cdvisttp=" + art.getCdvisttp() + "&fg_eur_usa=" + fg_eurusa;

            //Vist_famiglia vist_famiglia = vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam());
            //www += "/shop/scheda-" + vist_famiglia.getDsvistfam() + "-" + art.getCdvisttp() + "/" + art.getCdvistfam() + "?";
            PdfPTable tableVersioniDis = new PdfPTable(7);
            //tableVersioniDis.setWidthPercentage(100);
            tableVersioniDis.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tableVersioniDis.getDefaultCell().setBorderWidth(0f);
            tableVersioniDis.getDefaultCell().setPadding(0f);
            tableVersioniDis.getDefaultCell().setPaddingTop(3f);
            tableVersioniDis.getDefaultCell().setPaddingBottom(3f);

            String descrFam = art.getCdvistfam() + " " + art.getCdvisttp() + " ";

            List<Chunk> versList = new ArrayList<>();
            for (Mrp_arch_articoli mrp_arch_articoli : modellidis) {

                PdfPTable blockTable = new PdfPTable(1);
                blockTable.getDefaultCell().setBorderWidth(0f);
                blockTable.getDefaultCell().setPadding(0f);

                if ((!StringUtils.equals(art.getCdvistv1(), mrp_arch_articoli.getCdvistv1())
                        || !StringUtils.equals(art.getCdvistv2(), mrp_arch_articoli.getCdvistv2()))
                        && StringUtils.isBlank(mrp_arch_articoli.getCdvistv3())) {
                    //|| !StringUtils.equals(art.getCdvistv3(), mrp_arch_articoli.getCdvistv3())) {
                    String filedis = mrp_arch_articoli.getVist_filedis();

                    String realPath = WebUtils.getRealPath(ctx.getServletContext(), "/images/articoli/disegnitecnici/" + filedis + ".jpg");

                    File f = new File(realPath);
                    if (f.exists()) {
                        Image img = Image.getInstance(realPath);
                        img.setAlignment(Image.ALIGN_LEFT);

                        cell = new PdfPCell(img, true);
                        cell.setBorder(PdfPCell.NO_BORDER);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setVerticalAlignment(Element.ALIGN_CENTER);
                        cell.setPaddingBottom(1);
                        cell.addElement(img);

                        blockTable.addCell(cell);
                    } else {
                        blockTable.addCell(" ");
                    }

                    String artLink = versAltLink;

                    if (mrp_arch_articoli.getCdvistv1() != null) {
                        artLink += "&cdvistv1=" + mrp_arch_articoli.getCdvistv1();
                    }
                    if (mrp_arch_articoli.getCdvistv2() != null) {
                        artLink += "&cdvistv2=" + mrp_arch_articoli.getCdvistv2();
                    }
                    if (mrp_arch_articoli.getCdvistv3() != null) {
                        artLink += "&cdvistv3=" + mrp_arch_articoli.getCdvistv3();
                    }

                    filedis = StringUtils.remove(filedis, StringUtils.rightPad(art.getCdvistfam(), 5) + art.getCdvisttp());
                    filedis = StringUtils.substringBefore(filedis, "-");
                    filedis = StringUtils.trim(filedis);
                    if (StringUtils.isNotBlank(filedis)) {
                        //vers += filedis + ", ";
                        Chunk ck = new Chunk(descrFam + filedis, new Font(baseFont, 7));
                        ck.setAction(new PdfAction(new URL(artLink)));
                        blockTable.addCell(new Paragraph(ck));
                        //versList.add(ck);
                        //versList.add(new Chunk(", "));
                    }

                    tableVersioniDis.addCell(blockTable);
                }
            }

            tableVersioniDis.completeRow();
            table.addCell(tableVersioniDis);

        } else {
            table.addCell(new Paragraph(" ", new Font(baseFont, 8)));
            table.addCell(new Paragraph(" ", new Font(baseFont, 7)));
        }

//        cellCnt.addElement(table);
//        tableCnt.addCell(cellCnt);        
        return table;
    }

    public PdfPTable getFinituraDiffusore(Mrp_arch_articoli art, List<Vist_vetro> vist_vetro, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        String pathimg = "/images/articoli/vetro/";

        //float[] columnWidth = {130, 250};
        table = new PdfPTable(10);
        table.setWidthPercentage(100);
        //table.setWidths(new int[]{1, 1, 1});
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setPadding(0f);

        cell = new PdfPCell();
        cell.setBorderWidth(.0f);
        cell.setPaddingTop(10f);
        cell.setPaddingRight(0);
        cell.setPaddingBottom(10f);
        cell.setPaddingLeft(0);
        cell.setColspan(10);
        cell.setUseAscender(true);
        cell.setUseDescender(false);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("col.vetro", null, locale).toUpperCase(), new Font(baseFont, 8));
        cell.addElement(paragraph1);
        table.addCell(cell);

        table.getDefaultCell().setColspan(1);

        for (Vist_vetro vetro : vist_vetro) {

            PdfPTable blockTable = new PdfPTable(1);
            blockTable.getDefaultCell().setBorderWidth(0f);
            blockTable.getDefaultCell().setPadding(0f);

            Image img = null;
            try {

                String nomeimg = art.getCdvistfam() + "_" + art.getCdvisttp() + "_" + StringUtils.trimToEmpty(art.getCdvistv1()) + "_"
                        + StringUtils.trimToEmpty(vetro.getCol().getCdvistcolv()) + StringUtils.trimToEmpty(vetro.getFin().getCdvistfinv()) + ".jpg";
                String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                File f = new File(realPath);
                if (f.exists()) {
                    img = Image.getInstance(realPath);
                } else {
                    String[] comp = StringUtils.splitByWholeSeparatorPreserveAllTokens(nomeimg, "_");

                    String newFilename = comp[0] + "_" + comp[1] + "__" + comp[3];
                    String path1 = StringUtils.replace(realPath, nomeimg, newFilename);
                    f = new File(path1);
                    if (f.exists()) {
                        img = Image.getInstance(path1);
                    } else {
                        newFilename = comp[0] + "___" + comp[3];

                        String path2 = StringUtils.replace(realPath, nomeimg, newFilename);
                        f = new File(path2);
                        if (f.exists()) {
                            img = Image.getInstance(path2);
                        }
                    }

                }
                if (img == null) {
                    realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + "dummy.jpg");
                    f = new File(realPath);
                    if (f.exists()) {
                        img = Image.getInstance(realPath);
                    }
                }

            } catch (FileNotFoundException ex) {
                //log.debug("not exists");
            }

            if (img != null) {
                //img.scalePercent(50);
                PdfPTable imgTable = new PdfPTable(1);
                PdfPCell pdfPCell = new PdfPCell();
                pdfPCell.setBorderWidth(.0f);
                pdfPCell.setPadding(.0f);
                pdfPCell.setPaddingRight(5f);
                pdfPCell.setColspan(1);
                pdfPCell.addElement(img);
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                imgTable.addCell(pdfPCell);

                blockTable.addCell(imgTable);
            }

            cell = new PdfPCell();
            //cell.setFixedHeight(25);
            cell.setBorderWidth(.0f);
            //cell.setColspan(2);
            cell.setPaddingTop(5f);
            cell.setPaddingRight(0f);
            cell.setPaddingBottom(5f);
            cell.setPaddingLeft(0f);
            cell.setUseAscender(true);
            cell.setUseDescender(false);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            String finitura = StringUtils.trimToEmpty(vetro.getCol().getCdvistcolv());
            if (vetro.getFin() != null && StringUtils.isNotBlank(vetro.getFin().getCdvistfinv())) {
                finitura += "/" + vetro.getFin().getCdvistfinv();
            }
            cell.addElement(new Paragraph(finitura, new Font(baseFont, 8)));
            blockTable.addCell(cell);

            table.addCell(blockTable);

        }

        table.completeRow();
        return table;

    }

    public PdfPTable getFinituraMontatura(Mrp_arch_articoli art, List<Vist_finit_mont> vist_finit_mont, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        String pathimg = "/images/articoli/montature/";

        //float[] columnWidth = {190, 190};
        table = new PdfPTable(10);
        table.setWidthPercentage(100);
        //table.setWidths(new int[]{1, 1, 1});
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setPadding(0f);

        if (!vist_finit_mont.isEmpty()) {
            cell = new PdfPCell();
            cell.setBorderWidth(.0f);
            cell.setPaddingTop(10f);
            cell.setPaddingRight(0);
            cell.setPaddingBottom(10f);
            cell.setPaddingLeft(0);
            cell.setColspan(10);
            cell.setUseAscender(true);
            cell.setUseDescender(false);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);

            Paragraph paragraph1 = new Paragraph(messageSource.getMessage("fin.mont", null, locale).toUpperCase(), new Font(baseFont, 8));
            cell.addElement(paragraph1);
            table.addCell(cell);

            table.getDefaultCell().setColspan(1);

            for (Vist_finit_mont montatura : vist_finit_mont) {

                PdfPTable blockTable = new PdfPTable(1);
                blockTable.getDefaultCell().setBorderWidth(0f);
                blockTable.getDefaultCell().setPadding(0f);

                Image img = null;
                try {
                    String nomeimg = montatura.getCdvistfinm() + ".jpg";
                    String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                    File f = new File(realPath);
                    if (f.exists()) {
                        img = Image.getInstance(realPath);
                    } else {
                        realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + "dummy.jpg");
                        f = new File(realPath);
                        if (f.exists()) {
                            img = Image.getInstance(realPath);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }

                if (img != null) {
                    //img.scalePercent(50);
                    PdfPTable imgTable = new PdfPTable(1);
                    PdfPCell pdfPCell = new PdfPCell();
                    pdfPCell.setBorderWidth(.0f);
                    pdfPCell.setPadding(.0f);
                    pdfPCell.setPaddingRight(5f);
                    pdfPCell.setColspan(1);
                    pdfPCell.addElement(img);
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    imgTable.addCell(pdfPCell);

                    blockTable.addCell(imgTable);
                } 

                cell = new PdfPCell();
                //cell.setFixedHeight(30);
                cell.setBorderWidth(.0f);
                //cell.setColspan(2);
                cell.setPaddingTop(5f);
                cell.setPaddingRight(0f);
                cell.setPaddingBottom(10f);
                cell.setPaddingLeft(0f);
                cell.setUseAscender(true);
                cell.setUseDescender(false);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                String finitura = StringUtils.trimToEmpty(montatura.getCdvistfinm());
                cell.addElement(new Paragraph(finitura, new Font(baseFont, 8)));
                blockTable.addCell(cell);

                table.addCell(blockTable);
            }

            table.completeRow();
        }

        return table;

    }

    public PdfPTable getLampadine(Mrp_arch_articoli art, Vist_articoli_datiextra datiExtra, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {190, 190};
        float defaultImageCellHeight = 15f;
        float defaultImageLampadinaCellPadding = 1f;

        table = new PdfPTable(5);
        table.setWidthPercentage(100);
        //table.setWidths(new float[]{.1f, .1f, .1f, .5f});
        table.setWidths(new int[]{4, 4, 5, 8, 28});
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setPadding(0f);
        table.getDefaultCell().setPaddingRight(1f);
        table.getDefaultCell().setPaddingLeft(4f);
        //table.getDefaultCell().setBackgroundColor(BaseColor.DARK_GRAY);
        //table.getDefaultCell().setFixedHeight(11);

        cell = new PdfPCell();
        cell.setFixedHeight(13f);
        cell.setBorderWidth(.0f);
        cell.setColspan(6);
        cell.setPadding(0f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

//        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("lampadine", null, locale).toUpperCase() + ":", new Font(baseFontBold, 8));
//        cell.addElement(paragraph1);
//        table.addCell(cell);
        //creazione mappa img iniziali
        if (datiExtra != null) {
            Image img = null;

            //lampadina principale
            //int start = 1;
            //for (int i = start; i <= start + 4; i += 2) {
            int[] idxs = {1, 2, 7, 8};
            for (int pos : idxs) {
                try {
                    String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + pos);

                    flag = StringUtils.trimToEmpty(flag);

                    if (StringUtils.equals(flag, "S")) {

                        PdfPCell pdfPCell;

//                        Image arrow = Image.getInstance(WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + ARROW_ICON));
//
//                        if (StringUtils.equals(art.getCdvistelet(), selCdvistelet)) {
//                            pdfPCell = new PdfPCell(arrow, true);
//                        } else {
//                            pdfPCell = new PdfPCell(new Phrase("", new Font(baseFont, 8)));
//                        }
//                        pdfPCell.setPadding(0f);
//                        pdfPCell.setBorderWidth(.0f);
//                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                        table.addCell(pdfPCell);

                        pdfPCell = new PdfPCell();
                        //pdfPCell.setFixedHeight(13);
                        pdfPCell.setBorderWidth(0f);
                        String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + pos);
                        String[] split = nomeimg.split("\\\\");
                        String acronimoLampada = (tipoLampadine.get(split[0]) != null ? tipoLampadine.get(split[0]) : split[0]) + ".jpg";
                        nomeimg = split[1];

                        String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(), pathimg + acronimoLampada);
                        File fAcronimo = new File(realPathAcronimo);

                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
                        File f = new File(realPath);

                        if (pos > 1) {
                            pdfPCell = new PdfPCell(new Paragraph("+", new Font(baseFont, 6)));
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPadding(0);
                            pdfPCell.setPaddingTop(0f);
                            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            table.addCell(pdfPCell);
                        } else if (fAcronimo.exists()) {
                            img = Image.getInstance(realPathAcronimo);
                            if (img != null) {
                                pdfPCell = new PdfPCell();
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                pdfPCell.setFixedHeight(defaultImageCellHeight);
                                pdfPCell.setPadding(defaultImageLampadinaCellPadding);
                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                                pdfPCell.setBackgroundColor(BaseColor.CYAN);
                                pdfPCell.addElement(img);
                                pdfPCell.setBorderWidth(0f);
                                table.addCell(pdfPCell);
                            }
                        } else {
                            table.addCell("");
                        }

                        if (f.exists()) {
                            img = Image.getInstance(realPath);

                            if (img != null) {
                                pdfPCell = new PdfPCell();
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                pdfPCell.setFixedHeight(defaultImageCellHeight);
                                pdfPCell.setPadding(defaultImageLampadinaCellPadding);
                                //pdfPCell.setPaddingLeft(5f);
//                                pdfPCell.setBackgroundColor(BaseColor.YELLOW);
                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                pdfPCell.addElement(img);
                                pdfPCell.setBorderWidth(0f);
                                table.addCell(pdfPCell);
                            }

                        } else {
                            pdfPCell = new PdfPCell(new Phrase(""));
                            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            pdfPCell.setFixedHeight(defaultImageCellHeight);
                            pdfPCell.setPadding(defaultImageLampadinaCellPadding);
                            pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                            pdfPCell.setBackgroundColor(BaseColor.GREEN);
                            pdfPCell.setBorderWidth(0f);
                            table.addCell(pdfPCell);
                        }
                        //descrizione
                        //String tipoAttacco = BeanUtils.getSimpleProperty(datiExtra, "arwTipoAttacco" + i); 
                        //TODO sostituirlo con l'icona della lampadina quando le passeranno
                        String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + pos);
                        String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + pos);
                        String voltaggio = BeanUtils.getSimpleProperty(datiExtra, "arwVoltaggio" + pos);

                        /*String descrizione = StringUtils.trimToEmpty(tipoAttacco)
                         + " " + StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza)
                         + " " + StringUtils.trimToEmpty(voltaggio);*/
                        String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza);
                        descrizione = StringUtils.trim(descrizione);

                        if (StringUtils.isNotBlank(descrizione)) {
                            pdfPCell = new PdfPCell(new Paragraph(descrizione, new Font(baseFont, 6)));
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPadding(0);
                            pdfPCell.setPaddingTop(4f);
                            pdfPCell.setPaddingRight(2f);
                            pdfPCell.setNoWrap(true);
                            table.addCell(pdfPCell);
                        } else {
                            table.addCell("");
                        }

                        if (StringUtils.isNotBlank(voltaggio)) {
                            pdfPCell = new PdfPCell(new Paragraph(voltaggio, new Font(baseFont, 6)));
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPadding(0);
                            //pdfPCell.setPaddingTop(4f);
                            if (voltaggio.length() > 10) {
                                pdfPCell.setPaddingTop(0f);
                            } else {
                                pdfPCell.setPaddingTop(4f);
                            }
                            pdfPCell.setPaddingRight(2f);
                            pdfPCell.setNoWrap(false);
                            table.addCell(pdfPCell);
                        } else {
                            table.addCell("");
                        }

                        Vist_elettrificazioni elettrificazione = vistosiShopManager.getVist_elettrificazioniByKey(art.getCdvistelet());
                        String dsextvistelet = StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(elettrificazione, "dsextvistelet" + getAtkLangsfx(locale.getLanguage())));

                        if (StringUtils.isNotBlank(dsextvistelet)) {
                            pdfPCell = new PdfPCell(new Paragraph(dsextvistelet, new Font(baseFont, 6)));
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPadding(0);
                            if (dsextvistelet.length() > 10) {
                                pdfPCell.setPaddingTop(0f);
                            } else {
                                pdfPCell.setPaddingTop(4f);
                            }
//                            pdfPCell.setBackgroundColor(BaseColor.CYAN);
                            pdfPCell.setNoWrap(false);
                            table.addCell(pdfPCell);
                        } else {
                            table.addCell("");
                        }

                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }
            }

            //separo le due tabelle
//            Paragraph space = new Paragraph("");
//            PdfPCell spaceCell = new PdfPCell(space);
//            spaceCell.setColspan(5);
//            spaceCell.setFixedHeight(1f);
//            spaceCell.setBorderWidth(0f);
//            spaceCell.setPadding(0);
//            spaceCell.setBackgroundColor(BaseColor.CYAN);
//            table.addCell(spaceCell);
            //alternative
//            boolean empty = true;
//            PdfPTable innerTable = new PdfPTable(4);
//            innerTable.setWidthPercentage(100);
//            innerTable.setWidths(new float[]{2, 2, 3, 14});
//            innerTable.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
//            innerTable.getDefaultCell().setBorderWidth(0f);
//            innerTable.getDefaultCell().setPaddingTop(0);
//            innerTable.getDefaultCell().setPaddingRight(0);
//            innerTable.getDefaultCell().setPaddingBottom(0);
//            innerTable.getDefaultCell().setPaddingLeft(4);
//            innerTable.getDefaultCell().setUseAscender(true);
//            innerTable.getDefaultCell().setUseDescender(false);
            //innerTable.getDefaultCell().setFixedHeight(11);
//            cell = new PdfPCell();
//            //cell.setFixedHeight(11);
//            cell.setBorderWidth(0f);
//            cell.setColspan(4);
//            cell.setPadding(2);
//            cell.setUseAscender(true);
//            cell.setUseDescender(false);
//            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell.addElement(new Paragraph(messageSource.getMessage("lampadine.alt", null, locale).toUpperCase() + ":", new Font(baseFontBold, 8)));
////            innerTable.getDefaultCell().setPadding(0f); 
//            innerTable.addCell(cell);
//            int[] altIdxs = {3, 4, 9, 10, 5, 6, 11, 12};
//            boolean alt = false;
//            boolean idxPresent = false;
//            for (int bidx : altIdxs) {
//
//                try {
//                    String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + bidx);
//
//                    flag = StringUtils.trimToEmpty(flag);
//
//                    if (StringUtils.equals(flag, "S")) {
//
//                        PdfPCell pdfPCell = new PdfPCell();
//                        pdfPCell.setFixedHeight(13);
//                        pdfPCell.setBorderWidth(0f);
//                        pdfPCell.setPadding(0f);
//                        pdfPCell.setPaddingLeft(10);
//                        //pdfPCell.setPaddingBottom(2);
//
//                        String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + bidx);
//                        String[] split = nomeimg.split("\\\\");
//                        String acronimoLampada = (tipoLampadine.get(split[0]) != null ? tipoLampadine.get(split[0]) : split[0]) + ".jpg";
//                        nomeimg = split[1];
//
//                        String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(), pathimg + acronimoLampada);
//                        File fAcronimo = new File(realPathAcronimo);
//
//                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
//                        File f = new File(realPath);
//
//                        if (3 != bidx) {
//                            if (5 != bidx) {
//                                pdfPCell = new PdfPCell(new Paragraph("+", new Font(baseFont, 6)));
//                                pdfPCell.setBorderWidth(0f);
//                                pdfPCell.setPaddingTop(3f);
//                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                                innerTable.addCell(pdfPCell);
//                            } else if (fAcronimo.exists()) {
//                                img = Image.getInstance(realPathAcronimo);
//                                if (img != null) {
//                                    pdfPCell = new PdfPCell();
//                                    pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                                    pdfPCell.setFixedHeight(defaultImageCellHeight);
//                                    pdfPCell.setPadding(defaultImageLampadinaCellPadding);
////                                    pdfPCell.setBackgroundColor(BaseColor.CYAN);
//                                    pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                                    pdfPCell.setImage(img);
//                                    pdfPCell.setBorderWidth(0f);
//                                    innerTable.addCell(pdfPCell);
//                                }
//                            } else {
//                                innerTable.addCell("");
//                            }
//                        } else if (fAcronimo.exists()) {
//                            img = Image.getInstance(realPathAcronimo);
//                            if (img != null) {
//                                pdfPCell = new PdfPCell();
//                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                                pdfPCell.setFixedHeight(defaultImageCellHeight);
//                                pdfPCell.setPadding(defaultImageLampadinaCellPadding);
////                                pdfPCell.setBackgroundColor(BaseColor.CYAN);
//                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                                pdfPCell.setImage(img);
//                                pdfPCell.setBorderWidth(0f);
//                                innerTable.addCell(pdfPCell);
//                            }
//                        } else {
//                            innerTable.addCell("");
//                        }
//
//                        if (f.exists()) {
//                            img = Image.getInstance(realPath);
//
//                            if (img != null) {
//                                pdfPCell = new PdfPCell();
//                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                                pdfPCell.setFixedHeight(defaultImageCellHeight);
//                                pdfPCell.setPadding(defaultImageLampadinaCellPadding);
//
//                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                                pdfPCell.addElement(img);
//                                pdfPCell.setBorderWidth(0f);
//                                innerTable.addCell(pdfPCell);
//                            }
//
//                        } else {
//                            pdfPCell = new PdfPCell(new Phrase(""));
//                            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                            pdfPCell.setFixedHeight(defaultImageCellHeight);
//                            pdfPCell.setPadding(defaultImageLampadinaCellPadding);
//                            pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                            pdfPCell.setBorderWidth(0f);
//                            innerTable.addCell(pdfPCell);
//                        }
//                        //descrizione
//                        String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + bidx);
//                        String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + bidx);
//                        String voltaggio = BeanUtils.getSimpleProperty(datiExtra, "arwVoltaggio" + bidx);
//                        String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza);
//                        descrizione = StringUtils.trim(descrizione);
//
//                        if (StringUtils.isNotBlank(descrizione)) {
//                            pdfPCell = new PdfPCell(new Paragraph(descrizione, new Font(baseFont, 6)));
//                            pdfPCell.setBorderWidth(0f);
//                            pdfPCell.setPaddingTop(4f);
//                            pdfPCell.setNoWrap(true);
//                            innerTable.addCell(pdfPCell);
//                            empty = false;
//                        }
//
//                        if (StringUtils.isNotBlank(voltaggio)) {
//                            pdfPCell = new PdfPCell(new Paragraph(voltaggio, new Font(baseFont, 6)));
//                            pdfPCell.setBorderWidth(0f);
//                            pdfPCell.setPaddingTop(4f);
//                            pdfPCell.setNoWrap(true);
//                            innerTable.addCell(pdfPCell);
//                        } else {
//                            innerTable.addCell("");
//                        }
//
//                    }
//                } catch (FileNotFoundException ex) {
//                    //log.debug("not exists");
//                }
//
//                if (bidx == 10) {
//                    //separo le due tabelle
//                    Paragraph space1 = new Paragraph("");
//                    PdfPCell spaceCell1 = new PdfPCell(space1);
//                    spaceCell1.setColspan(4);
//                    spaceCell1.setFixedHeight(2f);
////                    spaceCell1.setBackgroundColor(BaseColor.RED);
//                    spaceCell1.setBorderWidth(0f);
//                    innerTable.addCell(spaceCell1);
//                }
//                if (bidx == 12) {
//                    alt = true;
//                }
//            }
//            if (alt) {
//                cell = new PdfPCell(new Paragraph("", new Font(baseFont, 6)));
//                cell.setPadding(0f);
//                cell.setBorderWidth(0f);
//                cell.setColspan(2);
//                table.addCell(cell);
//                cell = new PdfPCell();
//                cell.setPadding(0);
//                cell.setColspan(4);
//                if (empty) {
//                    cell.setBorderWidth(0f);
//                    cell.addElement(new Phrase(""));
//                } else {
//                    cell.setBorderWidth(0f);
//                    cell.addElement(innerTable);
//                }
//                table.addCell(cell);
//            }
            table.completeRow();

            return table;
        } else {
            return null;
        }

    }

    public PdfPTable getMarcature(Mrp_arch_articoli art, Vist_articoli_datiextra datiExtra, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {190, 190};

        PdfPTable tableCnt = new PdfPTable(2);
        tableCnt.setWidthPercentage(100);
        //tableCnt.setWidths(new float[]{0.5f, 0.5f});
        tableCnt.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tableCnt.getDefaultCell().setPaddingTop(.0f);
        tableCnt.getDefaultCell().setPaddingRight(.0f);
        tableCnt.getDefaultCell().setPaddingBottom(15f);
        tableCnt.getDefaultCell().setPaddingLeft(0);
        tableCnt.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableCnt.getDefaultCell().setBackgroundColor(BaseColor.MAGENTA);
        //tableCnt.getDefaultCell().setColspan(2);

        
        if (datiExtra != null) {

            Image img = null;
            
            
            /* 
            * dati tecnici
            */
            //tableCnt.getDefaultCell().setColspan(1);
            table = new PdfPTable(7);
            //table.setWidthPercentage(100);
            //table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table.getDefaultCell().setPadding(0f);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);      
            
            cell = new PdfPCell();
            //cell.setFixedHeight(15);
            cell.setBorderWidth(.0f);
            cell.setPadding(.0f);
            cell.setPaddingBottom(8f);
            cell.setColspan(7);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);

            Paragraph paragraph1 = new Paragraph(messageSource.getMessage("technical.data", null, locale).toUpperCase() + ":", new Font(baseFont, 8));
            //Paragraph paragraph1 = new Paragraph(messageSource.getMessage("certifications", null, locale).toUpperCase() + ":", new Font(baseFont, 8));
            cell.addElement(paragraph1);
            table.addCell(cell);
            
            String pathimg = ROOT_RES + "dati/";

            for (int i = 1; i < 7; i++) {

                try {
                    String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbolo" + i);

                    nomeimg = StringUtils.substringBetween(nomeimg, "\\", ".");
                    nomeimg = nomeimg + ".jpg";
                    String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                    File f = new File(realPath);
                    if (f.exists()) {
                        img = Image.getInstance(realPath);

                        if (img != null) {
                            //img.scalePercent(50);
                            PdfPCell pdfPCell = new PdfPCell(img);
                            pdfPCell.setFixedHeight(15);
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPadding(0f);
                            pdfPCell.setPaddingRight(3f);
                            pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            pdfPCell.addElement(img);
                            table.addCell(pdfPCell);
                        }

                    } else {
                        //TODO???
                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }
            }

            table.completeRow();
            tableCnt.addCell(table);
            
            /* 
            * certificazioni
            */                        
            table = new PdfPTable(7);
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            table.getDefaultCell().setPadding(0f);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);      

            cell = new PdfPCell();
            //cell.setFixedHeight(15);
            cell.setBorderWidth(.0f);
            cell.setPadding(.0f);
            cell.setPaddingBottom(8f);
            cell.setColspan(7);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);            
            
            paragraph1 = new Paragraph(messageSource.getMessage("certifications", null, locale).toUpperCase() + ":", new Font(baseFont, 8));
            cell.addElement(paragraph1);
            table.addCell(cell);            
            
            for (int i = 1; i < 5; i++) {

                try {

                    String fieldName = "arwCertificazione" + i;
                    String nomefile = BeanUtils.getSimpleProperty(datiExtra, fieldName);

                    if (StringUtils.isNotBlank(nomefile)) {
                        //String nomeimg = vistosiShopManager.getCertImageName(fieldName);
                        String nomeimg = StringUtils.containsIgnoreCase(nomefile, "PENDING") ? StringUtils.substringBeforeLast(nomefile, ".") : vistosiShopManager.getCertImageName(fieldName);

                        nomeimg = nomeimg + ".jpg";
                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                        File f = new File(realPath);
                        if (f.exists()) {
                            img = Image.getInstance(realPath);

                            if (img != null) {
                                //img.scalePercent(50);
                                PdfPCell pdfPCell = new PdfPCell();
                                pdfPCell.setFixedHeight(15);
                                pdfPCell.setBorderWidth(0f);
                                pdfPCell.setPadding(0f);
                                pdfPCell.setPaddingRight(3f);
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                pdfPCell.addElement(img);
                                table.addCell(pdfPCell);
                            }

                        } else {
                            //TODO???
                        }
                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }
            }

            table.completeRow();

            tableCnt.addCell(table);

            return tableCnt;
        } else {
            return null;
        }

    }

    public PdfPTable getDimensioni(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {380};

        PdfPTable tableCnt = new PdfPTable(1);
        tableCnt.setWidthPercentage(100);
        tableCnt.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableCnt.getDefaultCell().setBorderWidth(.0f);
        PdfPCell cellCnt = new PdfPCell();
        cellCnt.setBorderWidth(0);
        cellCnt.setPaddingTop(0);
        cellCnt.setPaddingRight(5);
        cellCnt.setPaddingBottom(10);
        cellCnt.setPaddingLeft(5);

        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{50, 50});
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorder(PdfPCell.NO_BORDER);
        defaultCell.setPaddingTop(3);
        defaultCell.setPaddingRight(5);
        defaultCell.setPaddingBottom(3);
        defaultCell.setPaddingLeft(0);
        defaultCell.setUseAscender(true);
        defaultCell.setUseDescender(false);
        defaultCell.setNoWrap(true);
        defaultCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("volume", null, locale).toUpperCase(), new Font(baseFont, 7));
        table.addCell(paragraph1);

        Paragraph paragraph2 = new Paragraph("Mc " + numFormat.format(art.getVlunlt()), new Font(baseFont, 7));
        defaultCell.setPaddingRight(0);
        table.addCell(paragraph2);

        Paragraph paragraph3 = new Paragraph(messageSource.getMessage("peso.lordo", null, locale).toUpperCase(), new Font(baseFont, 7));
        defaultCell.setPaddingRight(5);
        table.addCell(paragraph3);

        Paragraph paragraph4 = new Paragraph("Kg " + numFormat.format(art.getNrpeso_l()), new Font(baseFont, 7));
        defaultCell.setPaddingRight(0);
        table.addCell(paragraph4);

        Paragraph paragraph5 = new Paragraph(messageSource.getMessage("peso.netto", null, locale).toUpperCase(), new Font(baseFont, 7));
        defaultCell.setPaddingRight(5);
        table.addCell(paragraph5);

        Paragraph paragraph6 = new Paragraph("Kg " + numFormat.format(art.getNrpeso_n()), new Font(baseFont, 7));
        defaultCell.setPaddingRight(0);
        table.addCell(paragraph6);
        table.completeRow();

        cellCnt.addElement(table);
        tableCnt.addCell(cellCnt);

        return tableCnt;

    }

    public PdfPTable getNote(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable tableNote = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        tableNote = new PdfPTable(1);
        tableNote.setWidthPercentage(100);
        tableNote.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableNote.getDefaultCell().setPadding(0f);
        tableNote.getDefaultCell().setBorderWidth(0f);

        Map<String, Object> mapRes = getResourcesPath(art, request, true, "xlsx");
        String realPath = (String) mapRes.get("realPath");

        FileInputStream file = new FileInputStream(new File(realPath));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i < 15; i++) {

            String valore = "";
            String numero = "";

            if (sheet.getRow(i) != null) {
                if (sheet.getRow(i).getCell(0) != null) {
                    String nota = (String) sheet.getRow(i).getCell(0).getStringCellValue();
                    if (StringUtils.isNotBlank(nota) && StringUtils.contains(nota, "=")) {
                        String[] splitted = StringUtils.split(nota, "=");
                        numero = splitted[0];
                        valore = splitted[1];
                    } else {
                        continue;
                    }

                } else {
                    continue;
                }

            } else {
                continue;
            }

            if (valore == null) {
                valore = "";
            } else {
                valore = valore.replaceAll("\n", "");
            }
            if (numero == null) {
                numero = "";
            }

            cell = new PdfPCell();
            //cell.setFixedHeight(80);
            cell.setBorderWidth(.0f);
            paragraph = new Paragraph(numero, fontTitoloNota);
            cell.addElement(paragraph);
            paragraph = new Paragraph(valore, fontNota);
            cell.addElement(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableNote.addCell(cell);
        }

        tableNote.completeRow();

        return tableNote;

    }

    public PdfPTable get3D(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPTable table3D = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {125};

        table = new PdfPTable(1);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);

        table3D = new PdfPTable(1);
        table3D.setWidthPercentage(100);
        table3D.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table3D.getDefaultCell().setBorderWidth(0f);

        //PdfReader reader = new PdfReader(new FileInputStream(new File(rootImg + "APCLOTHP\\APCLOTHP.PDF")));
        //PdfImportedPage pdfDis = writer.getImportedPage(reader, 1);
//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + art.getVist_filedis() + "/" + art.getVist_filedis() + "3D_" + locale + ".pdf");
//
//        PdfReader readerDis = new PdfReader(realPath);
//        PdfImportedPage pdfDis = writer.getImportedPage(readerDis, 1);
//
//        Image img = Image.getInstance(pdfDis);   
//        img.scalePercent(90);
        int lx = 459;
        int lY = 436;

        Rectangle rect = new Rectangle(lx, lY, lx + 115, lY + 150);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(0.2f);
        rect.setBorderColor(new BaseColor(0x33, 0x33, 0x33));
        document.add(rect);

        Map<String, Object> mapRes = getResourcesPath(art, request, false, "U3D");
        String realPath = (String) mapRes.get("realPath");

//        String filename = art.getVist_filedis();
//
//        if ("UL".equals(art.getCdclas_a())) {
//            filename += "UL";
//        }
//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".U3D");
//        File f = new File(realPath);
//        if (!f.exists()) {
//            realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".U3D");
//        }
        File f = new File(realPath);
        if (f.exists()) {
            //if (realPath!=null) {

            cell = new PdfPCell();
            cell.setFixedHeight(150);
            cell.setBorderWidth(.0f);
//        cell.addElement(img);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setPaddingLeft(6);
            cell.addElement(new Paragraph("Click to activate (only in Acrobat Reader)", new Font(baseFont, 6)));
            table3D.addCell(cell);

//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".U3D");
            PdfStream stream3D = new PdfStream(new FileInputStream(realPath), writer);
            stream3D.put(PdfName.TYPE, new PdfName("3D"));
            stream3D.put(PdfName.SUBTYPE, new PdfName("U3D"));
            stream3D.flateCompress();
            PdfIndirectObject streamObject = writer.addToBody(stream3D);
            stream3D.writeLength();

            PdfDictionary dict3D = new PdfDictionary();
            dict3D.put(PdfName.TYPE, new PdfName("3DView"));
            dict3D.put(new PdfName("XN"), new PdfString("Default"));
            dict3D.put(new PdfName("IN"), new PdfString("Unnamed"));
            dict3D.put(new PdfName("MS"), PdfName.M);
            dict3D.put(new PdfName("C2W"), new PdfArray(new float[]{-1, 0, 0, 0, 0, 1, 0, 1, 0, 0, -1, 0}));
            dict3D.put(PdfName.CO, new PdfNumber(1));

            PdfIndirectObject dictObject = writer.addToBody(dict3D);

            PdfAnnotation annot = new PdfAnnotation(writer, rect);
            annot.put(PdfName.CONTENTS, new PdfString("3D Model"));
            annot.put(PdfName.SUBTYPE, new PdfName("3D"));
            annot.put(PdfName.TYPE, PdfName.ANNOT);
            annot.put(new PdfName("3DD"), streamObject.getIndirectReference());
            annot.put(new PdfName("3DV"), dictObject.getIndirectReference());
            PdfAppearance ap = writer.getDirectContent().createAppearance(rect.getWidth(), rect.getHeight());
            annot.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, ap);
            annot.setPage();

            writer.addAnnotation(annot);
        }

        cell = new PdfPCell();
        cell.setFixedHeight(150);
        cell.setBorderWidth(0f);
        cell.addElement(table3D);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(cell);

        return table;

    }

    private String getAtkLangsfx(String s_locale) {

        if ("it".equals(s_locale)) {
            return "";
        } else if ("en".equals(s_locale)) {
            return "_eng";
        } else if ("de".equals(s_locale)) {
            return "_ted";
        } else if ("es".equals(s_locale)) {
            return "_spa";
        } else if ("fr".equals(s_locale)) {
            return "_fra";
        } else {
            return "";
        }
    }

    private String getPortalUrl() {

        String url = "/";

        Ep_costanti cost = vistosiShopManager.getEpCostanti("ep.portal_url");

        if (cost != null && StringUtils.isNotBlank(cost.getCostvalue())) {
            url = cost.getCostvalue();
        }

        return url;
    }

    //Event helper per il footer
    public class PageEventHelper extends PdfPageEventHelper {

        private Locale locale;
        private WebApplicationContext ctx;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        private Vist_famiglia famiglia;
        private Mrp_arch_articoli art;

        private Boolean dataPage = false;

        public Boolean getDataPage() {
            return dataPage;
        }

        public void setDataPage(Boolean dataPage) {
            this.dataPage = dataPage;
        }

        public void onEndPage(PdfWriter writer, Document document) {

            try {
                /**
                 * *************************************************************
                 *
                 * header
                 *
                 */
                PdfPTable head = getHeader(famiglia, art, ctx, document, writer, dataPage);
//        tableBody.addCell(tableHeader);

                //int headWidths[] = {70, 30};
                //head.setWidths(headWidths);
//head.setTotalWidth(document.getPageSize().width()-document.leftMargin()-document.rightMargin());
                head.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
//                head.writeSelectedRows(0, -1, document.leftMargin(), document.getPageSize().height() - 75 + head.getTotalHeight(),
//                        writer.getDirectContent());
                head.writeSelectedRows(0, -1, document.leftMargin(), document.getPageSize().getHeight() - (document.topMargin() - 5) + head.getTotalHeight(),
                        writer.getDirectContent());

                /**
                 * *************************************************************
                 * footer
                 * ************************************************************
                 */
                PdfPTable foot = new PdfPTable(2);
                float[] columnWidth = {70, 30};
                foot.setWidths(columnWidth);
                foot.getDefaultCell().setBorder(0);
                foot.getDefaultCell().setBorderWidthTop(.5f);
                foot.getDefaultCell().setPadding(0f);
                foot.getDefaultCell().setPaddingTop(4f);
                foot.getDefaultCell().setBorderWidth(0f);
                foot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                Paragraph paragraph = new Paragraph();
                paragraph.add(new Chunk("Vetreria Vistosi S.r.l.   Tel. +39 041 5903480   E-mail: info@vistosi.it", new Font(baseFont, 6, Font.NORMAL)));
                foot.addCell(paragraph);

                paragraph = new Paragraph();
                Chunk siteLink = new Chunk("www.vistosi.it", new Font(baseFont, 6, Font.NORMAL));
                siteLink.setAction(new PdfAction(new URL("http://www.vistosi.it")));
                paragraph.add(siteLink);
                foot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                foot.addCell(paragraph);

//                foot.setTotalWidth(document.getPageSize().width()-document.leftMargin()-document.rightMargin());
                foot.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
//foot.getDefaultCell().setBorderWidthTop(1f);
//foot.getDefaultCell().setBorderColorTop(new BaseColor(243, 148, 47));
//foot.getDefaultCell().setBorderColorTop(new BaseColor(0, 0, 0));

//foot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
/*if (viewPrice) {
foot.addCell(new Paragraph((locale.equals(Locale.ITALIAN) ? "I prezzi sono da intendersi al netto dell'IVA" : "All indicated prices are intended without VAT"), new Font(bfHelv35, 10)));
} else {
foot.addCell("");
}*/
                foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(),
                        writer.getDirectContent());
            } catch (DocumentException ex) {
                Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(SpecSheetModello.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public PageEventHelper(Vist_famiglia famiglia, Mrp_arch_articoli art, WebApplicationContext ctx, Locale locale) {

            this.famiglia = famiglia;
            this.art = art;
            this.locale = locale;
            this.ctx = ctx;

        }
    }

}