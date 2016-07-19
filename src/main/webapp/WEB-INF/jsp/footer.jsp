</div>
<table class="footer">
    <tr>
        <td>&copy; Vistosi | Powered by
            <a href="http://www.ateikon.com" target="_blank">Ateikon Internet & Multimedia</a><br/>
            <a href="<c:url value="/" />"><< <spring:message code="home" text="Home"/></a>
        </td>
        <td>
            <ul class="socialIcons">
                <li><a href="https://twitter.com/VetreriaVistosi" target="_blank"><img src="<c:url value='/static/images/s_icon5.png'/>" alt=""></a></li>
                <li><a href="http://www.youtube.com/user/VetreriaVistosi" target="_blank"><img src="<c:url value='/static/images/s_icon4.png'/>" alt=""></a></li>
                <li><a href="http://www.pinterest.com/vetreriavistosi/" target="_blank"><img src="<c:url value='/static/images/s_icon3.png'/>" alt=""></a></li>
                <li><a href="https://www.facebook.com/VetreriaVistosi" target="_blank"><img src="<c:url value='/static/images/s_icon2.png'/>" alt=""></a></li>
                <li><a href="http://www.linkedin.com/company/2643566?goback=.fcs_GLHD_vistosi_false_*2_*2_*2_*2_*2_*2_*2_*2_*2_*2_*2_*2&amp;trk=ncsrch_hits" target="_blank"><img src="<c:url value='/static/images/s_icon1.png'/>" alt=""></a></li>
            </ul>
        </td>
    </tr>
</table>


</div>
<script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-3053424-1']);
    _gaq.push(['_trackPageview']);

    (function() {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();

</script> 
<%--script type="text/javascript">
	var _iub = _iub || [];
	_iub.csConfiguration = {
		cookiePolicyId: 806976,
		siteId: 322879,
		lang: <c:choose><c:when test="${rc.locale.language=='it'}">"it"</c:when><c:otherwise>"en"</c:otherwise></c:choose>,
                banner: {
                backgroundColor: "#BCBCBC",
                fontSize: "12px",
                textColor: "#fff"
                }
	};
	(function (w, d) {
		var loader = function () { var s = d.createElement("script"), tag = d.getElementsByTagName("script")[0]; s.src = "//cdn.iubenda.com/cookie_solution/iubenda_cs.js"; tag.parentNode.insertBefore(s, tag); };
		if (w.addEventListener) { w.addEventListener("load", loader, false); } else if (w.attachEvent) { w.attachEvent("onload", loader); } else { w.onload = loader; }
	})(window, document);
</script--%>   

</body>

</html>
