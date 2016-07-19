<%

if (!seStesso.equals("style.jsp")){

    %></td><%
    %></tr><%
    
    
        if (par_visu_foot.equals("S")){
    
            %><tr height="100"><%
                %><td><%
                    %><%@include file="epFoot.jsp" %><%
                %></td><%
            %></tr><%
        }
    
    
    %></table><%

}

    // *** Chiudo la connessione al DB
    //     


}catch (Exception ex_page ){

    System.err.println();
    System.err.println();
    System.err.println("********************************************************");
    System.err.println("    Exception  "+seStesso);
    System.err.println("********************************************************");
    System.err.println();
    System.err.println();
    
    ex_page.printStackTrace();

    System.err.println();
    System.err.println();
    System.err.println("********************************************************");

    try {
        atk_sql.m_connection.rollback();
    }catch (Exception ex_page_sql){ 

        
    }
    
    %><%= ex_page %><%


}finally {

    try {
        atk_sql.closeConnection();
    }catch (Exception _e){ }


}



%>



