
/**

    Attnzione la width del iframe deve 
    essere impostata al 100%

*/



var if_prec;

function show_iframe(iframeName){

    if (if_prec){
        
        var iframeEl = parent.document.getElementById? parent.document.getElementById(if_prec): parent.document.all? parent.document.all[if_prec]: null;
        iframeEl.style.height = "0px";
        iframeEl.style.width = "100%";

        el_tab = document.getElementById(if_prec+'_tab');
        el_tab.className = '';
    }


    setIframeHeight(iframeName);
    setIframeWidth(iframeName);

    // evidenzio il tab

    el_tab = document.getElementById(iframeName+'_tab');
    el_tab.className = 'current';

    if_prec = iframeName;
}





function getDocHeight(doc) {
  
  var docHt = 0, sh, oh;
  if (doc.height) {
	docHt = doc.height;
  }else if (doc.body) {
	if (doc.body.scrollHeight) {docHt = sh = doc.body.scrollHeight;}
    if (doc.body.offsetHeight) {docHt = oh = doc.body.offsetHeight;}
    if (sh && oh) {docHt = Math.max(sh, oh);}
  }
  return docHt;
}

function setIframeHeight(iframeName) {
	
  var iframeWin = parent.window.frames[iframeName];
  var iframeEl = parent.document.getElementById? parent.document.getElementById(iframeName): parent.document.all? parent.document.all[iframeName]: null;
  if ( iframeEl && iframeWin ) {
        
  	iframeEl.style.height = "auto"; // helps resize (for some) if new doc shorter than previous  
    var docHt = getDocHeight(iframeWin.document);
    // need to add to height to be sure it will all show
    if (docHt){
        if ((docHt*1)<300){
            docHt = 300;
        }

        iframeEl.style.height = docHt + 30 + "px";
    }
  }
}
function getDocWidth(doc) {
  var docWt = 0, sh, oh, cw;
  if (doc.width) docWt = doc.width;
  else if (doc.body) {
    if (doc.body.scrollWidth) sh = doc.body.scrollWidth;
    if (doc.body.offsetWidth) oh = doc.body.offsetWidth;
	if (doc.body.clientWidth) cw = doc.body.clientWidth;

    // if (sh && oh && cw) docWt = Math.max(Math.max(sh, oh), cw);
    docWt = Math.max(Math.max(sh, oh), cw);
	//if (sh && oh) docWt = Math.max(sh, oh);
  }
  return docWt;
}

function setIframeWidth(iframeName) {
//return
  try{
	if(is_ie){ //non funziona su FF
	var iframeWin = parent.frames[iframeName].window;

	var iframeEl = parent.document.getElementById? parent.document.getElementById(iframeName): parent.document.all? parent.document.all[iframeName]: null;

	if ( iframeEl && iframeWin ) {
	//iframeEl.style.width = "auto"; // helps resize (for some) if new doc shorter than previous  
	var docWt = getDocWidth(iframeWin.document);
	// need to add to width to be sure it will all show
	if (docWt) iframeEl.style.width = docWt + "px";
	}
	}
  }catch(e){}
}

