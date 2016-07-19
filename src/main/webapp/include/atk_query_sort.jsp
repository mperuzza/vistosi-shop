<script language="JavaScript">
    function atk_query_sort(){
        
        elDivSort       = document.getElementById("atk_query_sort");
        elDivSort.style.visibility = 'hidden';
        
        atk_pagina(1, 1);

    }
    function atk_query_annulla_sort(){
        elDivSort       = document.getElementById("atk_query_sort");
        elDivSort.style.visibility = 'hidden';
    }
    function atk_query_sort_criterio(posizione){
        el_rif  = document.getElementById("fgasc" + posizione);
        el_     = document.getElementById("atk_campo_sort_type" + posizione);
        
        if (el_rif.checked) {
            el_.value = 'ASC';
        } else {
            el_.value = 'DESC';
        }
    }
</script>

<div id="atk_query_sort" style="visibility:hidden">
    <div id="atk_query_title">Valori in ordine di...</div>
    <table class="atk_query" width="100%">
        <tr>
            <th>
                colonna
            </th>
            <th>
                ascendente
            </th>
        </tr>
    <%
        String[] campi_sort = null;
        String[] campi_sort_type = null;
        String[] campi_sort_name    = null;
        
        if (request.getParameterValues("atk_campo_sort")!=null)             campi_sort      = request.getParameterValues("atk_campo_sort");
        if (request.getParameterValues("atk_campo_sort_type")!=null)        campi_sort_type = request.getParameterValues("atk_campo_sort_type");
        
        
        if (campi_sort != null) {
            str_parametri.resetOrderValue();
            for (int i=0; i < campi_sort.length; i++) {
                 str_parametri.addOrderValue(campi_sort[i] ,campi_sort_type[i]);
            }
        }
        
        for (int i=0;i <= 10;i++) {
            String [][] criterio;
            
            if (i<str_parametri.getNumOrderValue()){
                criterio = str_parametri.getOrderValue(i);
                %>
                <tr>
                    <td>
                        <%out.print(html.getDropdown(str_parametri, "atk_campo_sort", criterio[0][0], "class=\"sl\""));%>
                    </td>
                    <td>
                        <%
                        str_html = new Str_html();

                        str_html.name       = "fgasc";
                        str_html.value      = "S";
                        str_html.id         = "fgasc" + i;
                        if (criterio[0][1].equals("ASC")) {
                            str_html.checked    = "checked";
                        }
                        str_html.onClick    = "atk_query_sort_criterio(" + i + ");";
                        out.println(html.getCheckbox(str_html));
                        %>
                        <input type="hidden" id="atk_campo_sort_type<%=i%>" name="atk_campo_sort_type" value="<%=criterio[0][1]%>"/>
                    </td>
                </tr>
                <%
            } else {
                %>
                <tr>
                    <td>
                        <% out.print(html.getDropdown(str_parametri, "atk_campo_sort", "", "class=\"sl\""));%>  
                    </td>
                    <td>
                        <%
                        str_html = new Str_html();

                        str_html.name       = "fgasc";
                        str_html.value      = "S";
                        str_html.id         = "fgasc" + i;
                        str_html.checked    = "checked";
                        str_html.onClick    = "atk_query_sort_criterio(" + i + ");";
                        out.println(html.getCheckbox(str_html));
                        %>
                        <input type="hidden" id="atk_campo_sort_type<%=i%>" name="atk_campo_sort_type" value="ASC"/>
                    </td>
                </tr>
                <%
                
            }
        }
    %>
    </table>
    <table>
        <tr>
            <td align="left">
                <input type="button" value="conferma" name="atk_conferma_sort" onclick="atk_query_sort();" class="bt"/>
            </td>
            <td align="right">
                <input type="button" value="annulla" name="atk_annulla_sort" onclick="atk_query_annulla_sort();" class="bt"/>
            </td>
        </tr>
    </table>
    </div> 

