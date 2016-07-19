package com.ateikon.util;

public class HTML {

   public HTML () {
      
   }
    
   
    public static final String getDropdown(java.sql.ResultSet rs, String name, String value, String style) throws Exception
    {
        String ls = "";
        
        String ls_codice = "";
        String ls_descrizione = "";

        java.sql.ResultSetMetaData rsmd  = rs.getMetaData();

        int numberOfColumns = rsmd.getColumnCount();

        ls = "<select name=\"" + name + "\" " + style  + ">";
        ls += "<option value=\"\"></option>";
        while (rs!=null && rs.next()){
            
            ls_codice = rs.getString(1);
            ls_descrizione = "";
            int i = 0;
            
            for (i=2; i <= numberOfColumns; i++) {
                if (rs.getObject(i)!=null) ls_descrizione += ((i==2)?"":" ") + rs.getString(i).trim();
            }
            ls += "<option value=\"" + ls_codice + "\"" ;
            if(ls_codice.equals(value)) ls += " selected" ;
            ls += ">";
                    
            ls += StringParsToHTML.escapeHTMLTextbox(ls_descrizione) + "</option>";

        }

        ls += "</select>";

        
        return ls;
    }




    public static final String getDropdown(com.ateikon.structure.Str_html astr) throws Exception
    {
        String ls = "";
        
        String ls_codice = "";
        String ls_descrizione = "";

        if (astr.css.indexOf("in_ro") >= 0) astr.readOnly = "readOnly";


        ls = "<select name=\"" + astr.name + "\"";
        if (!astr.style.equals("")     ) ls  += " style=\""+astr.style+"\"";
        if (!astr.css.equals("")       ) ls  += " class=\""+astr.css+"\"";
        if (!astr.readOnly.equals("")  ) ls  += " readOnly=\""+astr.readOnly+"\"";
        if (!astr.onFocus.equals("")   ) ls  += " onFocus=\""+astr.onFocus+"\"";
        if (!astr.onBlur.equals("")    ) ls  += " onBlur=\""+astr.onBlur+"\"";
        if (!astr.onChange.equals("")  ) ls  += " onChange=\""+astr.onChange+"\"";
        ls += ">";
        ls += "<option value=\"\"></option>";


        for (int i=0; astr.vec_value != null && i<astr.vec_value.size(); i++){
            
            ls_codice      = (String)astr.vec_value.elementAt(i);
            ls_descrizione = (String)astr.vec_descr.elementAt(i);

            ls_descrizione = text_box ( ls_descrizione );

            ls += "<option value=\"" + ls_codice + "\"" ;
            if(ls_codice.equals(astr.value)) ls += " selected" ;
            ls += ">";
            ls += ls_descrizione + "</option>";


        }

        ls += "</select>";
        
        return ls;
    }


    public static final String getText_box(com.ateikon.structure.Str_html astr) throws Exception {
        
    
        String ls = "";
        
        String ls_value  = text_box(astr.value);

        if (astr.css.indexOf("in_ro") >= 0) astr.readOnly = "readOnly";

        if (astr.type.equals("") ) astr.type = "text";


        ls  = "<input";
        ls += " type=\""+astr.type+"\"";
        ls += " name=\""+astr.name+"\"";
        ls += " value=\""+ls_value+"\"";
        
        if (!astr.id.equals("")          ) ls  += " id=\""+astr.id+"\"";
        if (!astr.size.equals("")        ) ls  += " size=\""+astr.size+"\"";
        if (!astr.maxlength.equals("")   ) ls  += " maxlength=\""+astr.maxlength+"\"";
        if (!astr.style.equals("")       ) ls  += " style=\""+astr.style+"\"";
        if (!astr.css.equals("")         ) ls  += " class=\""+astr.css+"\"";
        if (!astr.readOnly.equals("")    ) ls  += " readOnly=\""+astr.readOnly+"\"";
        if (!astr.disabled.equals("")    ) ls  += " disabled";
        if (!astr.tabindex.equals("")  ) ls  += " tabindex=\""+astr.tabindex+"\"";
        if (!astr.autocomplete.equals("")) ls  += " autocomplete=\"off\"";
        

        if (!astr.onFocus.equals("")   ) ls  += " onFocus=\""+astr.onFocus+"\"";
        if (!astr.onBlur.equals("")    ) ls  += " onBlur=\""+astr.onBlur+"\"";
        if (!astr.onKeydown.equals("") ) ls  += " onKeydown=\""+astr.onKeydown+"\"";
        if (!astr.onKeyup.equals("")   ) ls  += " onKeyup=\""+astr.onKeyup+"\"";
        if (!astr.onChange.equals("")  ) ls  += " onChange=\""+astr.onChange+"\"";
        if (!astr.onDblClick.equals("")) ls  += " onDblClick=\""+astr.onDblClick+"\"";

        ls += "/>";

        
        return ls;
    }


    public static final String getCheckbox(com.ateikon.structure.Str_html astr) throws Exception {
        
    
        String ls = "";
        
        String ls_value  = text_box(astr.value);

        if (astr.css.indexOf("in_ro") >= 0) astr.readOnly = "readOnly";

        if (astr.type.equals("") ) astr.type = "text";


        ls  = "<input";
        ls += " type=\"checkbox\"";
        ls += " name=\""+astr.name+"\"";
        ls += " value=\""+ls_value+"\"";
        
        if (!astr.id.equals("")        ) ls  += " id=\""+astr.id+"\"";
        if (!astr.checked.equals("")   ) ls  += " "+astr.checked+"";
        if (!astr.style.equals("")     ) ls  += " style=\""+astr.style+"\"";
        if (!astr.css.equals("")       ) ls  += " class=\""+astr.css+"\"";
        if (!astr.readOnly.equals("")  ) ls  += " readOnly=\""+astr.readOnly+"\"";

        if (!astr.onKeydown.equals("") ) ls  += " onKeydown=\""+astr.onKeydown+"\"";
        if (!astr.onKeyup.equals("")   ) ls  += " onKeyup=\""+astr.onKeyup+"\"";
        if (!astr.onChange.equals("")  ) ls  += " onChange=\""+astr.onChange+"\"";
        if (!astr.onClick.equals("")   ) ls  += " onClick=\""+astr.onClick+"\"";
        if (!astr.onDblClick.equals("")) ls  += " onDblClick=\""+astr.onDblClick+"\"";
        

        ls += "/>";

        
        return ls;
    }


    public static final String getText_area(com.ateikon.structure.Str_html astr) throws Exception {
        
    
        String ls = "";
        
        String ls_value  = text_box(astr.value);

        if (astr.css.indexOf("in_ro") >= 0) astr.readOnly = "readOnly";


        ls  = "<textarea";
        ls += " name=\""+astr.name+"\"";
        
        if (!astr.id.equals("")        ) ls  += " id=\""+astr.id+"\"";
        if (!astr.rows.equals("")      ) ls  += " rows=\""+astr.rows+"\""; //avendramin20080201
        if (!astr.cols.equals("")      ) ls  += " cols=\""+astr.cols+"\""; //avendramin20080201
        if (!astr.style.equals("")     ) ls  += " style=\""+astr.style+"\"";
        if (!astr.css.equals("")       ) ls  += " class=\""+astr.css+"\"";
        if (!astr.readOnly.equals("")  ) ls  += " readOnly=\""+astr.readOnly+"\"";
        if (!astr.onChange.equals("")  ) ls  += " onChange=\""+astr.onChange+"\"";
        
        ls += " >"+ls_value; //avendramin20080201
        
        ls += "</textarea>";

        
        return ls;
    }




    public static final String getHidden(com.ateikon.structure.Str_html astr) throws Exception {
        
    
        String ls = "";
        
        String ls_value  = text_box(astr.value);

        if (astr.id.equals("") ) astr.id = astr.name;


        ls  = "<input";
        ls += " type=\"hidden\"";
        ls += " name=\""+astr.name+"\"";
        ls += " value=\""+ls_value+"\"";
        ls += " id=\""+astr.id+"\"";
        ls += "/>";

        
        return ls;
    }



    public static final String getButton(com.ateikon.structure.Str_html astr) throws Exception {
        
    
        String ls = "";
        
        String ls_value  = text_box(astr.value);

        if (astr.css.indexOf("in_ro") >= 0) astr.readOnly = "readOnly";


        ls  = "<input";
        ls += " type=\""+astr.type+"\"";
        ls += " name=\""+astr.name+"\"";
        ls += " value=\""+ls_value+"\"";
        
        if (!astr.id.equals("")        ) ls  += " id=\""+astr.id+"\"";
        if (!astr.style.equals("")     ) ls  += " style=\""+astr.style+"\"";
        if (!astr.css.equals("")       ) ls  += " class=\""+astr.css+"\"";

        if (!astr.onFocus.equals("")   ) ls  += " onFocus=\""+astr.onFocus+"\"";
        if (!astr.onBlur.equals("")    ) ls  += " onBlur=\""+astr.onBlur+"\"";
        if (!astr.onKeydown.equals("") ) ls  += " onKeydown=\""+astr.onKeydown+"\"";
        if (!astr.onKeyup.equals("")   ) ls  += " onKeyup=\""+astr.onKeyup+"\"";
        if (!astr.onClick.equals("")   ) ls  += " onClick=\""+astr.onClick+"\"";
        if (!astr.onDblClick.equals("")) ls  += " onDblClick=\""+astr.onDblClick+"\"";
        
        

        ls += "/>";

        
        return ls;
    }


    // Inizio -- label: avendramin20080131
	
    public static final String getRadio(com.ateikon.structure.Str_html astr) throws Exception {
        
    
        String ls = "";
        
        String ls_value  = text_box(astr.value);

        if (astr.css.indexOf("in_ro") >= 0) astr.readOnly = "readOnly";

        if (astr.type.equals("") ) astr.type = "text";


        ls  = "<input";
        ls += " type=\"radio\"";
        ls += " name=\""+astr.name+"\"";
        ls += " value=\""+ls_value+"\"";
        
        if (!astr.id.equals("")        ) ls  += " id=\""+astr.id+"\"";
        if (!astr.checked.equals("")   ) ls  += " "+astr.checked+"";
        if (!astr.style.equals("")     ) ls  += " style=\""+astr.style+"\"";
        if (!astr.css.equals("")       ) ls  += " class=\""+astr.css+"\"";
        if (!astr.readOnly.equals("")  ) ls  += " readOnly=\""+astr.readOnly+"\"";
        if (!astr.tabindex.equals("")  ) ls  += " tabindex=\""+astr.tabindex+"\"";

        if (!astr.onKeydown.equals("") ) ls  += " onKeydown=\""+astr.onKeydown+"\"";
        if (!astr.onKeyup.equals("")   ) ls  += " onKeyup=\""+astr.onKeyup+"\"";
        if (!astr.onChange.equals("")  ) ls  += " onChange=\""+astr.onChange+"\"";
        if (!astr.onClick.equals("")   ) ls  += " onClick=\""+astr.onClick+"\"";

        ls += "/>";

        
        return ls;
    }


    // Fine -- label: avendramin20080131








    public static final String text_box(String value) throws Exception
    {
        return StringParsToHTML.escapeHTMLTextbox(value);
    }

    public static final String text(String value) throws Exception
    {
        return StringParsToHTML.escapeHTML(value);
    }

    public static final String hidden(String value) throws Exception
    {
        return StringParsToHTML.escapeHTMLHidden(value);
    }

    public static final String js(String value) throws Exception
    {
        return StringParsToHTML.escapeJS(value);
    }

    public static final String url(String value) throws Exception
    {
        return StringParsToHTML.escapeRequest(value);
    }

}
