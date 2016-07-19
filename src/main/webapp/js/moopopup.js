/*
  moopop: unobtrusive javascript popups via late binding using mootools 1.2

  copyright (c) 2007-2008 by gonchuki - http://blog.gonchuki.com

  version:	1.1
  released: June 23, 2008

  This work is licensed under a Creative Commons Attribution-Share Alike 3.0 License.
    http://creativecommons.org/licenses/by-sa/3.0/
*/

var moopop={width:0,height:0,captureByRel:function(attrVal,parent){this.capture((parent||document).getElements('a[rel*='+(attrVal||'popup')+']'));},capture:function(el,width,height){if($defined(width)&&$defined(height)){this.width=width;this.height=height;}
switch($type(el)){case'string':el=$$(el);case'element':case'array':$splat(el).each(this.add_pop_to,this);}
this.width=null;this.height=null;},add_pop_to:function(el){el.addEvent('click',function(e){e.stop();this.popup(el);}.bind(this));var size=el.get('rel').match(/\[(\d+),\s*(\d+)/)||['',this.width,this.height];var resizable=el.get('rel').match(/,(r)/)||[];if(size[1])el.store('popupprops','width='+size[1]+', height='+size[2]+(resizable[1]?', scrollbars=yes, resizable=yes':''));},popup:function(el){window.open(el.get('href'),el.get('name')||'',el.retrieve('popupprops')||'');}};window.addEvent('domready',function(){moopop.captureByRel('popup');});