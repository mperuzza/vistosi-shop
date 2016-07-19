/*
 * PrintWishlist.java
 *
 * Created on 27 giugno 2006, 10.13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.pdf;

import com.ateikon.internet.eprogen.domain.pgmr.Utenti;
import com.ateikon.internet.eprogen.domain.pgmr.WishlistPosiRowHead;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_posi;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_test;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.Image;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.pdf.PdfContentByte;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfPageEventHelper;
//import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author emi
 */
public class PrintWishlist {
    
    private Log log = LogFactory.getLog(this.getClass());
    
    private String slash = System.getProperty("file.separator");
    
    private BaseFont bfHelv55Roman;
    
    private BaseFont bfHelv35;
    
    private BaseFont bfHelv75;

    private String fontDir;

    public void setFontDir(String fontDir) {
        this.fontDir = fontDir;
    }

    public String getFontDir() {
        return fontDir;
    }

    
    
    /** Creates a new instance of PrintWishlist */
    public PrintWishlist() {
    }
    
    protected void setFonts(String fontDir) throws DocumentException, IOException{
        
        bfHelv55Roman = BaseFont.createFont(fontDir + slash + "HelveticaNeue-Roman.otf", BaseFont.CP1252, BaseFont.EMBEDDED);
        bfHelv35 = BaseFont.createFont(fontDir + slash + "HelveticaThn.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
        bfHelv75 = BaseFont.createFont(fontDir + slash + "HelveticaBd___-.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
    }
 
    protected Document newDocument() {
            return new Document(PageSize.A4, 24, 24, 95, 50);
    }
    
    protected void prepareWriter(Map model, PdfWriter writer, ServletContext ctx, Locale locale) 
        throws DocumentException {

        /*String logo = (String)model.get("logo");*/
        Wishlist_test wl = (Wishlist_test)model.get("test");
        
        //BaseFont[] fonts = new BaseFont{bfHelv35, bfHelv75};
        
        
        
        PageEventHelper event = new PageEventHelper(wl.getDslist(), ctx, locale);
        //event.setPrintFooter(true);

        writer.setPageEvent(event);

    }    
    
    protected void buildPdfDocument(Map model,
                                    Document document,
                                    PdfWriter writer,
                                    ServletContext ctx,
                                    Locale locale)
    throws Exception{    
        
        
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(98);
        int headerWidths[] = {100};
        table.setWidths(headerWidths);    
        
        table.getDefaultCell().setBorder(0);
        table.getDefaultCell().setBorderWidthTop(1);
        table.getDefaultCell().setPaddingTop(5f);
        table.getDefaultCell().setPaddingBottom(5f);
        
        List<WishlistPosiRowHead> rows = (List<WishlistPosiRowHead>)model.get("rows");

        DecimalFormat df = new DecimalFormat("#,###.00");
        NumberFormat  nf = NumberFormat.getInstance();
        
        for (Iterator itr=rows.iterator(); itr.hasNext(); ){
            WishlistPosiRowHead a = (WishlistPosiRowHead)itr.next();
            PdfPTable artTable = new PdfPTable(3);

            artTable.setWidthPercentage(100);
            int artHeaderWidths[] = {20, 55, 25};
            artTable.setWidths(artHeaderWidths);
            artTable.getDefaultCell().setPadding(2f);
            artTable.getDefaultCell().setBorder(0);

            //table.addCell("");
            Image image = Image.getInstance(ctx.getRealPath("img") + slash + "full" + slash + a.getCdarticolo() + ".png");
            image.scalePercent(95);
            artTable.addCell(image);
            

            PdfPTable descrTable = new PdfPTable(3);
            descrTable.setWidthPercentage(100);
            int descrTableWidths[] = {60, 20, 20};
            descrTable.setWidths(descrTableWidths);
            descrTable.getDefaultCell().setPadding(2f);
            descrTable.getDefaultCell().setBorder(0);

            PdfPCell c = new PdfPCell(new Paragraph(a.getCdarticolo() + " " + a.getDsarticolo(), new Font(bfHelv55Roman, 10, Font.BOLD)));
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setBorder(0);
            descrTable.addCell(c);

            descrTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            descrTable.addCell("qta");
            descrTable.addCell("prezzo");

            descrTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            descrTable.getDefaultCell().setColspan(1);
            //sub rows
            List posiList = a.getPosis();
            for (Iterator it = posiList.iterator(); it.hasNext();) {
                Wishlist_posi posi = (Wishlist_posi)it.next();

                PdfPCell f = new PdfPCell(new Paragraph(posi.getCdfinitura() + " " + posi.getDsfinitura(), new Font(bfHelv55Roman, 10, Font.NORMAL)));
                f.setBorder(0);
                descrTable.addCell(f);

                PdfPCell q = new PdfPCell(new Paragraph(nf.format(posi.getQta()), new Font(bfHelv55Roman, 10, Font.NORMAL)));
                q.setBorder(0);
                q.setHorizontalAlignment(Element.ALIGN_RIGHT);
                descrTable.addCell(q);

                if((Boolean)model.get("viewprice")){
                    double prz = (posi.getPrezzocliente()!=null)?posi.getPrezzocliente():0;
                    prz = (prz*posi.getQta());
                    if(posi.getSconto1()!= null && posi.getSconto1()>0) prz -= prz * (posi.getSconto1()/100);
                    if(posi.getSconto2()!= null && posi.getSconto2()>0) prz -= prz * (posi.getSconto2()/100);

                    if(posi.getSpese()!=null && posi.getSpese()>0)   prz += prz * (posi.getSpese()/100);
                    if(posi.getTasse()!=null && posi.getTasse()>0)   prz += prz * (posi.getTasse()/100);

                    PdfPCell p = new PdfPCell(new Paragraph(df.format(prz), new Font(bfHelv55Roman, 10, Font.NORMAL)));
                    p.setBorder(0);
                    p.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    descrTable.addCell(p);
                }else{
                    descrTable.addCell("");
                }

            }
            artTable.addCell(descrTable);



            //artTable.addCell("");

            Image imageDisTect = Image.getInstance(ctx.getRealPath("img") + slash + "disegni tecnici piccoli" + slash + "art_" + a.getCdarticolo() + ".jpg");
            //imageDisTect.scalePercent(95);
            artTable.addCell(imageDisTect);

            //artTable.addCell(new Phrase(a.getCdarticolo(), new Font(bfHelv55Roman, 15, Font.BOLD)));
            //artTable.addCell(new Phrase("Qt: " + a.getQta(),  new Font(bfHelv55Roman, 15, Font.BOLD)));
            
            //artTable.getDefaultCell().setColspan(2);
            
            //artTable.addCell(new Phrase(a.getCdvar() + (a.getVariante()!=null?" " + (locale.equals(Locale.ITALIAN)?a.getVariante().getDsvar():a.getVariante().getDseng()) :""),  new Font(bfHelv55Roman, 11, Font.BOLD)));

            //artTable.addCell(new Phrase(((locale.equals(Locale.ITALIAN))?a.getDsarti():a.getDseng()),  new Font(bfHelv35, 10)));
            
            table.addCell(artTable);
            
            
        }
        
        document.add(table);
    }
    
    public Map createPdf(Map model, ServletContext ctx, Locale locale){
        
        int rc = 0;
        Map retValue = new HashMap();

        Wishlist_test wl = (Wishlist_test)model.get("test");
        Utenti user = (Utenti)model.get("user");
        
        String fname = StringUtils.replaceChars("ws" + wl.getId() + "_" + user.getTkutente(), '\\', '_');
        fname = StringUtils.replaceChars(fname, '/', '_');
        fname = StringUtils.replaceChars(fname, '"', '_');
        fname = StringUtils.replaceChars(fname, "|:*?<>", "_") + "_" + ((new Date()).getTime()) + ".pdf";
        String fpath = ctx.getRealPath("pdf") + slash;
        
        Document document = newDocument();
        
        try {
            
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fpath+fname));

            this.fontDir = ctx.getRealPath("font");
            log.debug(this.fontDir);
            setFonts(this.fontDir);
            
            log.debug(locale);
            
            prepareWriter(model, writer, ctx, locale);
            document.open();
            buildPdfDocument(model, document, writer, ctx, locale);
            document.close();

            rc = 1;
        } catch (FileNotFoundException ex) {
            log.error(ex.getMessage());
            rc = -1;
        } catch (IOException ex) {
            log.error(ex.getMessage());
            rc = -2;
        } catch (DocumentException ex) {
            log.error(ex.getMessage());
            rc = -3;
        } catch (Exception ex) {
            log.debug(ex);
            rc = -4;
        }        
        
        retValue.put("filename", fname);
        retValue.put("filepath", fpath);
        retValue.put("rc", new Integer(rc));
        
        return retValue;
        
    }
    
    public class PageEventHelper extends PdfPageEventHelper{
        
        private String wlname = "";
        
        private Locale locale;
        
        private ServletContext ctx;
        
        private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        public void onStartPage(PdfWriter writer, Document document) {
            
            try {
                
                /****
                 * head
                 *****/
                PdfPTable head = new PdfPTable(2);
                
                head.getDefaultCell().setBorder(0);
                
//                PdfPCell c1 = new PdfPCell(new Phrase("GESSI FOR ARCHITECT", new Font(bfHelv75, 15)));
                PdfPCell c1 = new PdfPCell(new Phrase(this.wlname, new Font(bfHelv75, 13)));
                c1.setBorder(0);
                c1.setBorderWidthBottom(1f);
                c1.setBorderColorBottom(new BaseColor(243, 148, 47));
                c1.setColspan(2);
                c1.setHorizontalAlignment(Element.ALIGN_LEFT);
                c1.setPaddingRight(10f);
                c1.setPaddingBottom(5f);
                
                head.addCell(c1);
                
                head.getDefaultCell().setPaddingTop(5f);
                head.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                head.getDefaultCell().setPaddingLeft(10f);
                //head.addCell(new Phrase(this.wlname, new Font(bfHelv35, 13)));
                head.addCell("");
                head.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                head.getDefaultCell().setPaddingRight(10f);
                head.addCell(new Phrase((locale.equals(Locale.ITALIAN)?"Lista creata il ": "Wishlist created ") + dateFormat.format(new Date()), new Font(bfHelv35, 13)));
                
                int headWidths[] = {50, 50};
                head.setWidths(headWidths);
                head.setTotalWidth(document.getPageSize().getWidth()-document.leftMargin()-document.rightMargin());
                head.writeSelectedRows(0, -1, document.leftMargin(), document.getPageSize().getHeight() - 75 + head.getTotalHeight(),
                        writer.getDirectContent()); 
                
                
                
                /***************************************************************
                 * logo
                 **************************************************************/
                Image img = Image.getInstance(ctx.getRealPath("img/logo.jpg"));
                img.scalePercent(25);
                img.setAbsolutePosition(document.leftMargin()+450, document.getPageSize().getTop()-50);

                PdfContentByte cbu = writer.getDirectContentUnder();
                cbu.saveState();
                cbu.addImage(img);
                cbu.restoreState();
                                
                
                
                /***************************************************************
                 * footer
                 **************************************************************/

                PdfPTable foot = new PdfPTable(1);

                foot.setTotalWidth(document.getPageSize().getWidth()-document.leftMargin()-document.rightMargin());
                foot.getDefaultCell().setBorder(0);
                foot.getDefaultCell().setPaddingRight(10f);
                foot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                foot.getDefaultCell().setBorderWidthTop(1f);
                foot.getDefaultCell().setBorderColorTop(new BaseColor(243, 148, 47));
                
                foot.addCell(new Paragraph("Pag. " + writer.getPageNumber(), new Font(bfHelv35, 13)));
                foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(),
                        writer.getDirectContent());                
                
            } catch (DocumentException ex) {
                log.debug(ex.getMessage());
            } catch (MalformedURLException ex) {
                log.debug(ex.getMessage());
            } catch (IOException ex) {
                log.debug(ex.getMessage());
            } 
            
            
        }

        public PageEventHelper(String wlname, ServletContext ctx, Locale locale) {
            
            this.wlname = wlname;
            this.locale = locale;
            this.ctx    = ctx; 
            
        }
        
        
    }
    
}
