/* Clientcide Copyright (c) 2006-2009, http://www.clientcide.com/wiki/cnet-libraries#license*/

//Contents: Clientcide, Class.ToElement, dbug, StyleWriter, StickyWin, StickyWin.Modal, StickyWin.UI, StickyWin.Alert, StickyWin.Confirm, StickyWin.Prompt, Confirmer, StickyWin.Ajax, Form.Request.Prompt, Element.Forms.Compat, Modalizer.Compat, Waiter.Compat

//This lib: http://www.clientcide.com/js/build.php?excludeLibs[]=mootools-core&excludeLibs[]=mootools-more&require[]=Clientcide&require[]=StickyWin.Modal&require[]=StickyWin.Confirm&require[]=StickyWin.Prompt&require[]=Confirmer&require[]=Form.Request.Prompt&require[]=Element.Forms.Compat&require[]=Modalizer.Compat&require[]=Waiter.Compat&compression=none

/*
Script: Clientcide.js
	The Clientcide namespace.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
var Clientcide = {
	version: '%build%',
	assetLocation: "http://github.com/anutron/clientcide/raw/master/Assets",
	setAssetLocation: function(baseHref) {
		Clientcide.assetLocation = baseHref;
		if (Clientcide.preloaded) Clientcide.preLoadCss();
	},
	preLoadCss: function(){
		if (window.StickyWin && StickyWin.ui) StickyWin.ui();
		if (window.StickyWin && StickyWin.pointy) StickyWin.pointy();
		Clientcide.preloaded = true;
		return true;
	},
	preloaded: false
};
(function(){
	if (!window.addEvent) return;
	var preload = function(){
		if (window.dbug) dbug.log('preloading clientcide css');
		if (!Clientcide.preloaded) Clientcide.preLoadCss();
	};
	window.addEvent('domready', preload);
	window.addEvent('load', preload);
})();
setCNETAssetBaseHref = Clientcide.setAssetLocation;/*
Script: ToElement.js
	Defines the toElement method for a class.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
Class.ToElement = new Class({
	toElement: function(){
		return this.element;
	}
});
var ToElement = Class.ToElement;/*
Script: dbug.js
	A wrapper for Firebug console.* statements.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
var dbug = {
	logged: [],	
	timers: {},
	firebug: false, 
	enabled: false, 
	log: function() {
		dbug.logged.push(arguments);
	},
	nolog: function(msg) {
		dbug.logged.push(arguments);
	},
	time: function(name){
		dbug.timers[name] = new Date().getTime();
	},
	timeEnd: function(name){
		if (dbug.timers[name]) {
			var end = new Date().getTime() - dbug.timers[name];
			dbug.timers[name] = false;
			dbug.log('%s: %s', name, end);
		} else dbug.log('no such timer: %s', name);
	},
	enable: function(silent) { 
		var con = window.firebug ? firebug.d.console.cmd : window.console;

		if((!!window.console && !!window.console.warn) || window.firebug) {
			try {
				dbug.enabled = true;
				dbug.log = function(){
						try {
							(con.debug || con.log).apply(con, arguments);
						} catch(e) {
							console.log(Array.slice(arguments));
						}
				};
				dbug.time = function(){
					con.time.apply(con, arguments);
				};
				dbug.timeEnd = function(){
					con.timeEnd.apply(con, arguments);
				};
				if(!silent) dbug.log('enabling dbug');
				for(var i=0;i<dbug.logged.length;i++){ dbug.log.apply(con, dbug.logged[i]); }
				dbug.logged=[];
			} catch(e) {
				dbug.enable.delay(400);
			}
		}
	},
	disable: function(){ 
		if(dbug.firebug) dbug.enabled = false;
		dbug.log = dbug.nolog;
		dbug.time = function(){};
		dbug.timeEnd = function(){};
	},
	cookie: function(set){
		var value = document.cookie.match('(?:^|;)\\s*jsdebug=([^;]*)');
		var debugCookie = value ? unescape(value[1]) : false;
		if((!$defined(set) && debugCookie != 'true') || ($defined(set) && set)) {
			dbug.enable();
			dbug.log('setting debugging cookie');
			var date = new Date();
			date.setTime(date.getTime()+(24*60*60*1000));
			document.cookie = 'jsdebug=true;expires='+date.toGMTString()+';path=/;';
		} else dbug.disableCookie();
	},
	disableCookie: function(){
		dbug.log('disabling debugging cookie');
		document.cookie = 'jsdebug=false;path=/;';
	}
};

(function(){
	var fb = !!window.console || !!window.firebug;
	var con = window.firebug ? window.firebug.d.console.cmd : window.console;
	var debugMethods = ['debug','info','warn','error','assert','dir','dirxml'];
	var otherMethods = ['trace','group','groupEnd','profile','profileEnd','count'];
	function set(methodList, defaultFunction) {
		for(var i = 0; i < methodList.length; i++){
			dbug[methodList[i]] = (fb && con[methodList[i]])?con[methodList[i]]:defaultFunction;
		}
	};
	set(debugMethods, dbug.log);
	set(otherMethods, function(){});
})();
if ((!!window.console && !!window.console.warn) || window.firebug){
	dbug.firebug = true;
	var value = document.cookie.match('(?:^|;)\\s*jsdebug=([^;]*)');
	var debugCookie = value ? unescape(value[1]) : false;
	if(window.location.href.indexOf("jsdebug=true")>0 || debugCookie=='true') dbug.enable();
	if(debugCookie=='true')dbug.log('debugging cookie enabled');
	if(window.location.href.indexOf("jsdebugCookie=true")>0){
		dbug.cookie();
		if(!dbug.enabled)dbug.enable();
	}
	if(window.location.href.indexOf("jsdebugCookie=false")>0)dbug.disableCookie();
}/*
Script: StyleWriter.js

Provides a simple method for injecting a css style element into the DOM if it's not already present.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/

var StyleWriter = new Class({
	createStyle: function(css, id) {
		window.addEvent('domready', function(){
			try {
				if (document.id(id) && id) return;
				var style = new Element('style', {id: id||''}).inject($$('head')[0]);
				if (Browser.Engine.trident) style.styleSheet.cssText = css;
				else style.set('text', css);
			}catch(e){dbug.log('error: %s',e);}
		}.bind(this));
	}
});/*
Script: StickyWin.js

Creates a div within the page with the specified contents at the location relative to the element you specify; basically an in-page popup maker.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/


var StickyWin = new Class({
	Binds: ['destroy', 'hide', 'togglepin', 'esc'],
	Implements: [Options, Events, StyleWriter, Class.ToElement],
	options: {
//		onDisplay: $empty,
//		onClose: $empty,
//		onDestroy: $empty,
		closeClassName: 'closeSticky',
		pinClassName: 'pinSticky',
		content: '',
		zIndex: 10000,
		className: '',
//		id: ... set above in initialize function
/*  	these are the defaults for Element.position anyway
		************************************************
		edge: false, //see Element.position
		position: 'center', //center, corner == upperLeft, upperRight, bottomLeft, bottomRight
		offset: {x:0,y:0},
		relativeTo: document.body, */
		width: false,
		height: false,
		timeout: -1,
		allowMultipleByClass: true,
		allowMultiple: true,
		showNow: true,
		useIframeShim: true,
		iframeShimSelector: '',
		destroyOnClose: false,
		closeOnClickOut: false,
		closeOnEsc: false,
		getWindowManager: function(){ return StickyWin.WM; }
	},

	css: '.SWclearfix:after {content: "."; display: block; height: 0; clear: both; visibility: hidden;}'+
		 '.SWclearfix {display: inline-table;} * html .SWclearfix {height: 1%;} .SWclearfix {display: block;}',
	
	initialize: function(options){
		this.options.inject = this.options.inject || {
			target: document.body,
			where: 'bottom'
		};
		this.setOptions(options);
		this.windowManager = this.options.getWindowManager();
		this.id = this.options.id || 'StickyWin_'+new Date().getTime();
		this.makeWindow();
		if (this.windowManager) this.windowManager.add(this);

		if (this.options.content) this.setContent(this.options.content);
		if (this.options.timeout > 0) {
			this.addEvent('onDisplay', function(){
				this.hide.delay(this.options.timeout, this);
			}.bind(this));
		}
		//add css for clearfix
		this.createStyle(this.css, 'StickyWinClearFix');
		if (this.options.closeOnClickOut || this.options.closeOnEsc) this.attach();
		if (this.options.destroyOnClose) this.addEvent('close', this.destroy);
		if (this.options.showNow) this.show();
	},
	attach: function(attach){
		var method = $pick(attach, true) ? 'addEvents' : 'removeEvents';
		var events = {};
		if (this.options.closeOnClickOut) events.click = this.esc;
		if (this.options.closeOnEsc) events.keyup = this.esc;
		document[method](events);
	},
	esc: function(e) {
		if (e.key == "esc") this.hide();
		if (e.type == "click" && this.element != e.target && !this.element.hasChild(e.target)) this.hide();
	},
	makeWindow: function(){
		this.destroyOthers();
		if (!document.id(this.id)) {
			this.win = new Element('div', {
				id:		this.id
			}).addClass(this.options.className).addClass('StickyWinInstance').addClass('SWclearfix').setStyles({
			 	display:'none',
				position:'absolute',
				zIndex:this.options.zIndex
			}).inject(this.options.inject.target, this.options.inject.where).store('StickyWin', this);			
		} else this.win = document.id(this.id);
		this.element = this.win;
		if (this.options.width && $type(this.options.width.toInt())=="number") this.win.setStyle('width', this.options.width.toInt());
		if (this.options.height && $type(this.options.height.toInt())=="number") this.win.setStyle('height', this.options.height.toInt());
		return this;
	},
	show: function(suppressEvent){
		this.showWin();
		if (!suppressEvent) this.fireEvent('display');
		if (this.options.useIframeShim) this.showIframeShim();
		this.visible = true;
		return this;
	},
	showWin: function(){
		if (this.windowManager) this.windowManager.focus(this);
		if (!this.positioned) this.position();
		this.win.show();
	},
	hide: function(suppressEvent){
		if ($type(suppressEvent) == "event" || !suppressEvent) this.fireEvent('close');
		this.hideWin();
		if (this.options.useIframeShim) this.hideIframeShim();
		this.visible = false;
		return this;
	},
	hideWin: function(){
		this.win.setStyle('display','none');
	},
	destroyOthers: function() {
		if (!this.options.allowMultipleByClass || !this.options.allowMultiple) {
			$$('div.StickyWinInstance').each(function(sw) {
				if (!this.options.allowMultiple || (!this.options.allowMultipleByClass && sw.hasClass(this.options.className))) 
					sw.retrieve('StickyWin').destroy();
			}, this);
		}
	},
	setContent: function(html) {
		if (this.win.getChildren().length>0) this.win.empty();
		if ($type(html) == "string") this.win.set('html', html);
		else if (document.id(html)) this.win.adopt(html);
		this.win.getElements('.'+this.options.closeClassName).each(function(el){
			el.addEvent('click', this.hide);
		}, this);
		this.win.getElements('.'+this.options.pinClassName).each(function(el){
			el.addEvent('click', this.togglepin);
		}, this);
		return this;
	},
	position: function(options){
		this.positioned = true;
		this.setOptions(options);
		this.win.position({
			allowNegative: $pick(this.options.allowNegative, this.options.relativeTo != document.body),
			relativeTo: this.options.relativeTo,
			position: this.options.position,
			offset: this.options.offset,
			edge: this.options.edge
		});
		if (this.shim) this.shim.position();
		return this;
	},
	pin: function(pin) {
		if (!this.win.pin) {
			dbug.log('you must include element.pin.js!');
			return this;
		}
		this.pinned = $pick(pin, true);
		this.win.pin(pin);
		return this;
	},
	unpin: function(){
		return this.pin(false);
	},
	togglepin: function(){
		return this.pin(!this.pinned);
	},
	makeIframeShim: function(){
		if (!this.shim){
			var el = (this.options.iframeShimSelector)?this.win.getElement(this.options.iframeShimSelector):this.win;
			this.shim = new IframeShim(el, {
				display: false,
				name: 'StickyWinShim'
			});
		}
	},
	showIframeShim: function(){
		if (this.options.useIframeShim) {
			this.makeIframeShim();
			this.shim.show();
		}
	},
	hideIframeShim: function(){
		if (this.shim) this.shim.hide();
	},
	destroy: function(){
		if (this.windowManager) this.windowManager.remove(this);
		if (this.win) this.win.destroy();
		if (this.options.useIframeShim && this.shim) this.shim.destroy();
		if (document.id('modalOverlay')) document.id('modalOverlay').destroy();
		this.fireEvent('destroy');
	}
});

StickyWin.Stacker = new Class({
	Implements: [Options, Events],
	Binds: ['click'],
	instances: [],
	options: {
		zIndexBase: 9000
	},
	initialize: function(options) {
		this.setOptions(options);
		this.zIndex = this.options.zIndex;
	},
	add: function(sw) {
		this.instances.include(sw);
		$(sw).addEvent('mousedown', this.click);
	},
	click: function(e) {
		this.instances.each(function(sw){
			var el = $(sw);
			if (el == e.target || el.hasChild($(e.target))) this.focus(sw);
		}, this);
	},
	focus: function(instance){
		if (this.focused == instance) return;
		this.focused = instance;
		if (instance) this.instances.erase(instance).push(instance);
		this.instances.each(function(current, i){
			$(current).setStyle('z-index', this.options.zIndexBase + i);
		}, this);
		this.focused = instance;
	},
	remove: function(sw) {
		this.instances.erase(sw);
		$(sw).removeEvent('click', this.click);
	}
});
StickyWin.WM = new StickyWin.Stacker();/*
Script: StickyWin.Modal.js

This script extends StickyWin and StickyWin.Fx classes to add Mask functionality.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
StickyWin.Modal = new Class({

	Extends: StickyWin,

	options: {
		modalize: true,
		maskOptions: {
			style: {
				'background-color':'#333',
				opacity:0.8
			}
		},
		hideOnClick: true,
		getWindowManager: function(){ return StickyWin.ModalWM; }
	},

	initialize: function(options) {
		this.options.maskTarget = this.options.maskTarget || document.body;
		this.setOptions(options);
		this.mask = new Mask(this.options.maskTarget, this.options.maskOptions).addEvent('click', function() {
			if (this.options.hideOnClick) this.hide();
		}.bind(this));
		this.parent(options);
	},

	show: function(showModal){
		if ($pick(showModal, this.options.modalize)) this.mask.show();
		this.parent();
	},

	hide: function(hideModal){
		if ($pick(hideModal, true)) this.mask.hide();
		this.parent();
	}

});

StickyWin.ModalWM = new StickyWin.Stacker({
	zIndexBase: 11000
});
if (StickyWin.Fx) StickyWin.Fx.Modal = StickyWin.Modal; /*
Script: StickyWin.ui.js

Creates an html holder for in-page popups using a default style.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
StickyWin.UI = new Class({
	Implements: [Options, Class.ToElement, StyleWriter],
	options: {
		width: 300,
		css: "div.DefaultStickyWin {font-family:verdana; font-size:11px; line-height: 13px;position: relative;}"+
			"div.DefaultStickyWin div.top{-moz-user-select: none;-khtml-user-select: none;}"+
			"div.DefaultStickyWin div.top_ul{background:url({%baseHref%}full.png) top left no-repeat; height:30px; width:15px; float:left}"+
			"div.DefaultStickyWin div.top_ur{position:relative; left:0px !important; left:-4px; background:url({%baseHref%}full.png) top right !important; height:30px; margin:0px 0px 0px 15px !important; margin-right:-4px; padding:0px}"+
			"div.DefaultStickyWin h1.caption{clear: none !important; margin:0px !important; overflow: hidden; padding:0 !important; font-weight:bold; color:#555; font-size:14px !important; position:relative; top:8px !important; left:5px !important; float: left; height: 22px !important;}"+
			"div.DefaultStickyWin div.middle, div.DefaultStickyWin div.closeBody {background:url({%baseHref%}body.png) top left repeat-y; margin:0px 20px 0px 0px !important;	margin-bottom: -3px; position: relative;	top: 0px !important; top: -3px;}"+
			"div.DefaultStickyWin div.body{background:url({%baseHref%}body.png) top right repeat-y; padding:8px 23px 8px 0px !important; margin-left:5px !important; position:relative; right:-20px !important; z-index: 1;}"+
			"div.DefaultStickyWin div.bottom{clear:both;}"+
			"div.DefaultStickyWin div.bottom_ll{background:url({%baseHref%}full.png) bottom left no-repeat; width:15px; height:15px; float:left}"+
			"div.DefaultStickyWin div.bottom_lr{background:url({%baseHref%}full.png) bottom right; position:relative; left:0px !important; left:-4px; margin:0px 0px 0px 15px !important; margin-right:-4px; height:15px}"+
			"div.DefaultStickyWin div.closeButtons{text-align: center; background:url({%baseHref%}body.png) top right repeat-y; padding: 4px 30px 8px 0px; margin-left:5px; position:relative; right:-20px}"+
			"div.DefaultStickyWin a.button:hover{background:url({%baseHref%}big_button_over.gif) repeat-x}"+
			"div.DefaultStickyWin a.button {background:url({%baseHref%}big_button.gif) repeat-x; margin: 2px 8px 2px 8px; padding: 2px 12px; cursor:pointer; border: 1px solid #999 !important; text-decoration:none; color: #000 !important;}"+
			"div.DefaultStickyWin div.closeButton{width:13px; height:13px; background:url({%baseHref%}closebtn.gif) no-repeat; position: absolute; right: 0px; margin:10px 15px 0px 0px !important; cursor:pointer;top:0px}"+
			"div.DefaultStickyWin div.dragHandle {	width: 11px;	height: 25px;	position: relative;	top: 5px;	left: -3px;	cursor: move;	background: url({%baseHref%}drag_corner.gif); float: left;}",
		cornerHandle: false,
		cssClass: '',
		buttons: [],
		cssId: 'defaultStickyWinStyle',
		cssClassName: 'DefaultStickyWin',
		closeButton: true
/*	These options are deprecated:
		closeTxt: false,
		onClose: $empty,
		confirmTxt: false,
		onConfirm: $empty	*/
	},
	initialize: function() {
		var args = this.getArgs(arguments);
		this.setOptions(args.options);
		this.legacy();
		var css = this.options.css.substitute({baseHref: this.options.baseHref || Clientcide.assetLocation + '/stickyWinHTML/'}, /\\?\{%([^}]+)%\}/g);
		if (Browser.Engine.trident4) css = css.replace(/png/g, 'gif');
		this.createStyle(css, this.options.cssId);
		this.build();
		if (args.caption || args.body) this.setContent(args.caption, args.body);
	},
	getArgs: function(){
		return StickyWin.UI.getArgs.apply(this, arguments);
	},
	legacy: function(){
		var opt = this.options; //saving bytes
		//legacy support
		if (opt.confirmTxt) opt.buttons.push({text: opt.confirmTxt, onClick: opt.onConfirm || $empty});
		if (opt.closeTxt) opt.buttons.push({text: opt.closeTxt, onClick: opt.onClose || $empty});
	},
	build: function(){
		var opt = this.options;

		var container = new Element('div', {
			'class': opt.cssClassName
		});
		if (opt.width) container.setStyle('width', opt.width);
		this.element = container;
		this.element.store('StickyWinUI', this);
		if (opt.cssClass) container.addClass(opt.cssClass);
		

		var bodyDiv = new Element('div').addClass('body');
		this.body = bodyDiv;
		
		var top_ur = new Element('div').addClass('top_ur');
		this.top_ur = top_ur;
		this.top = new Element('div').addClass('top').adopt(
				new Element('div').addClass('top_ul')
			).adopt(top_ur);
		container.adopt(this.top);
		
		if (opt.cornerHandle) new Element('div').addClass('dragHandle').inject(top_ur, 'top');
		
		//body
		container.adopt(new Element('div').addClass('middle').adopt(bodyDiv));
		//close buttons
		if (opt.buttons.length > 0){
			var closeButtons = new Element('div').addClass('closeButtons');
			opt.buttons.each(function(button){
				if (button.properties && button.properties.className){
					button.properties['class'] = button.properties.className;
					delete button.properties.className;
				}
				var properties = $merge({'class': 'closeSticky'}, button.properties);
				new Element('a').addEvent('click', button.onClick || $empty)
					.appendText(button.text).inject(closeButtons).set(properties).addClass('button');
			});
			container.adopt(new Element('div').addClass('closeBody').adopt(closeButtons));
		}
		//footer
		container.adopt(
			new Element('div').addClass('bottom').adopt(
					new Element('div').addClass('bottom_ll')
				).adopt(
					new Element('div').addClass('bottom_lr')
			)
		);
		if (this.options.closeButton) container.adopt(new Element('div').addClass('closeButton').addClass('closeSticky'));
		return this;
	},
	setCaption: function(caption) {
		this.caption = caption;
		if (!this.h1) {
			this.makeCaption(caption);
		} else {
			if (document.id(caption)) this.h1.adopt(caption);
			else this.h1.set('html', caption);
		}
		return this;
	},
	makeCaption: function(caption) {
		if (!caption) return this.destroyCaption();
		var opt = this.options;
		this.h1 = new Element('h1').addClass('caption');
		if (opt.width) this.h1.setStyle('width', (opt.width-(opt.cornerHandle?55:40)-(opt.closeButton?10:0)));
		this.setCaption(caption);
		this.top_ur.adopt(this.h1);
		if (!this.options.cornerHandle) this.h1.addClass('dragHandle');
		return this;
	},
	destroyCaption: function(){
		if (this.h1) {
			this.h1.destroy();
			this.h1 = null;
		}
		return this;
	},
	setContent: function(){
		var args = this.getArgs.apply(this, arguments);
		var caption = args.caption;
		var body = args.body;
		this.setCaption(caption);
		if (document.id(body)) this.body.empty().adopt(body);
		else this.body.set('html', body);
		return this;
	}
});
StickyWin.UI.getArgs = function(){
	var input = $type(arguments[0]) == "arguments"?arguments[0]:arguments;
	if (Browser.Engine.presto && 1 === input.length) input = input[0];

	var cap = input[0], bod = input[1];
	var args = Array.link(input, {options: Object.type});
	if (input.length == 3 || (!args.options && input.length == 2)) {
		args.caption = cap;
		args.body = bod;
	} else if (($type(bod) == 'object' || !bod) && cap && $type(cap) != 'object'){
		args.body = cap;
	}
	return args;
};

StickyWin.ui = function(caption, body, options){
	return document.id(new StickyWin.UI(caption, body, options));
};
/*
Script: StickyWin.Alert.js
	Defines StickyWin.Alert, a simple little alert box with a close button.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
StickyWin.Alert = new Class({
	Implements: Options,
	Extends: StickyWin.Modal,
	options: {
		destroyOnClose: true,
		modalOptions: {
			modalStyle: {
				zIndex: 11000
			}
		},
		zIndex: 110001,
		uiOptions: {
			width: 250,
			buttons: [
				{text: 'Ok'}
			]
		},
		getWindowManager: $empty
	},
	initialize: function(caption, message, options) {
		this.message = message;
		this.caption = caption;
		this.setOptions(options);
		this.setOptions({
			content: this.build()
		});
		this.parent(options);
	},
	makeMessage: function() {
		return new Element('p', {
			'class': 'errorMsg SWclearfix',
			styles: {
				margin: 0,
				minHeight: 10
			},
			html: this.message
		});
	},
	build: function(){
		return StickyWin.ui(this.caption, this.makeMessage(), this.options.uiOptions);
	}
});

StickyWin.Error = new Class({
	Extends: StickyWin.Alert, 
	makeMessage: function(){
		var message = this.parent();
		new Element('img', {
			src: (this.options.baseHref || Clientcide.assetLocation + '/simple.error.popup') + '/icon_problems_sm.gif',
			'class': 'bang clearfix',
			styles: {
				'float': 'left',
				width: 30,
				height: 30,
				margin: '3px 5px 5px 0px'
			}
		}).inject(message, 'top');
		return message;
	}
});

StickyWin.alert = function(caption, message, options) {
	if ($type(options) == "string") options = {baseHref: options};
	return new StickyWin.Alert(caption, message, options);
};

StickyWin.error = function(caption, message, options) {
	return new StickyWin.Error(caption, message, options);
};/*
Script: StickyWin.Confirm.js
	Defines StickyWin.Conferm, a simple confirmation box with an ok and a close button.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
StickyWin.Confirm = new Class({
	Extends: StickyWin.Alert,
	options: {
		uiOptions: {
			width: 250
		}
	},
	build: function(callback){
		this.setOptions({
			uiOptions: {
				buttons: [
					{text: 'Cancel'},
					{
						text: 'Ok', 
						onClick: callback || function(){
							this.fireEvent('confirm');
						}.bind(this)
					}
				]
			}
		});
		return this.parent();
	}
});

StickyWin.confirm = function(caption, message, callback, options) {
	return new StickyWin.Confirm(caption, message, options).addEvent('confirm', callback);
};/*
Script: StickyWin.Prompt.js
	Defines StickyWin.Prompt, a little prompt box with an input as well as ok close buttons.	
License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/

StickyWin.Prompt = new Class({
	Extends: StickyWin.Confirm,
	options: {
		defaultValue: ''
	},
	initialize: function(message, header, options){
		this.addEvent('display', function(){
			this.input.select();
		}.bind(this));
		this.parent.apply(this, arguments);
	},
	makeMessage: function(){
		this.input = new Element('input', {
			value: this.options.defaultValue,
			type: 'text',
			id: 'foo',
			styles: {
				width: '100%'
			},
			events: {
				keyup: function(e) {
					if (e.key == 'enter') {
						this.fireEvent('confirm', this.input.get('value'));
						this.hide();
					}
				}.bind(this)
			}
		});
		return new Element('div').adopt(this.parent()).adopt(this.input);
	},
	build: function(){
		return this.parent(function(){
			this.fireEvent('confirm', this.input.get('value'));
		}.bind(this));
	}
});

StickyWin.prompt = function(caption, message, callback, options) {
	return new StickyWin.Prompt(caption, message, options).addEvent('confirm', callback);
};/*
Script: Confirmer.js
	Fades a message in and out for the user to tell them that some event (like an ajax save) has occurred.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
var Confirmer = new Class({
	Implements: [Options, Events],
	options: {
		reposition: true, //for elements already in the DOM
		//if position = false, just fade
		positionOptions: {
			relativeTo: false,
			position: 'upperRight', //see <Element.Position>
			offset: {x:-225,y:0},
			zIndex: 9999
		},
		msg: 'your changes have been saved', //string or dom element
		msgContainerSelector: '.body',
		delay: 250,
		pause: 1000,
		effectOptions:{
			duration: 500
		},
		prompterStyle:{
			padding: '2px 6px',
			border: '1px solid #9f0000',
			backgroundColor: '#f9d0d0',
			fontWeight: 'bold',
			color: '#000',
			width: 210
		}
//	onComplete: $empty
	},
	initialize: function(options){
			this.setOptions(options);
			this.options.positionOptions.relativeTo = document.id(this.options.positionOptions.relativeTo) || document.body;
			this.prompter = (document.id(this.options.msg))?document.id(this.options.msg):this.makePrompter(this.options.msg);
			if (this.options.reposition){
				this.prompter.setStyles({
					position: 'absolute',
					display: 'none',
					zIndex: this.options.positionOptions.zIndex
				});
				if (!Browser.Engine.trident4) this.prompter.setStyle('opacity',0);
			} else if (!Browser.Engine.trident4) this.prompter.setStyle('opacity',0);
			else this.prompter.setStyle('visibility','hidden');
			if (!this.prompter.getParent()){
				window.addEvent('domready', function(){
					this.prompter.inject(document.body);
				}.bind(this));
			}
		try {
			this.msgHolder = this.prompter.getElement(this.options.msgContainerSelector);
			if (!this.msgHolder) this.msgHolder = this.prompter;
		} catch(e){dbug.log(e)}
	},
	makePrompter: function(msg){
		return new Element('div').setStyles(this.options.prompterStyle).appendText(msg);
	},
	prompt: function(options){
		if (!this.paused)this.stop();
		var msg = (options)?options.msg:false;
		options = $merge(this.options, {saveAsDefault: false}, options||{});
		if (document.id(options.msg) && msg) this.msgHolder.empty().adopt(options.msg);
		else if (!document.id(options.msg) && options.msg) this.msgHolder.empty().appendText(options.msg);
		if (!this.paused) {
			if (options.reposition) this.position(options.positionOptions);
			(function(){
				this.timer = this.fade(options.pause);
			}).delay(options.delay, this);
		}
		if (options.saveAsDefault) this.setOptions(options);
		return this;
	},
	fade: function(pause){
		this.paused = true;
		pause = $pick(pause, this.options.pause);
		if (!this.fx && !Browser.Engine.trident4)
			this.fx = new Fx.Tween(this.prompter, $merge({property: 'opacity'}, this.options.effectOptions));
		if (this.options.reposition) this.prompter.setStyle('display','block');
		if (!Browser.Engine.trident4){
			this.prompter.setStyle('visibility','visible');
			this.fx.start(0,1).chain(function(){
				this.timer = (function(){
					this.fx.start(0).chain(function(){
						if (this.options.reposition) this.prompter.hide();
						this.paused = false;
					}.bind(this));
				}).delay(pause, this);
			}.bind(this));
		} else {
			this.prompter.setStyle('visibility','visible');
			this.timer = (function(){
				this.prompter.setStyle('visibility','hidden');
				this.fireEvent('onComplete');
				this.paused = false;
			}).delay(pause+this.options.effectOptions.duration, this);
		}
		return this;
	},
	stop: function(){	
		this.paused = false;
		$clear(this.timer);
		if (this.fx) this.fx.set(0);
		if (this.options.reposition) this.prompter.hide();
		return this;
	},
	position: function(positionOptions){
		this.prompter.position($merge(this.options.positionOptions, positionOptions));
		return this;
	}
});
/*
Script: StickyWin.Ajaxjs

Adds ajax functionality to all the StickyWin classes.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
(function(){
	var SWA = function(extend){
		return {
			Extends: extend,
			options: {
				//onUpdate: $empty,
				url: '',
				showNow: false,
				requestOptions: {
					method: 'get',
					evalScripts: true
				},
				wrapWithUi: false, 
				caption: '',
				uiOptions:{},
				handleResponse: function(response){
					var responseScript = "";
					this.Request.response.text.stripScripts(function(script){	responseScript += script; });
					if (this.options.wrapWithUi) response = StickyWin.ui(this.options.caption, response, this.options.uiOptions);
					this.setContent(response);
					this.show();
					if (this.evalScripts) $exec(responseScript);
					this.fireEvent('update');
				}
			},
			initialize: function(options){
				var showNow;
				if (options && options.showNow) {
					showNow = true;
					options.showNow = false;
				}
				this.parent(options);
				this.evalScripts = this.options.requestOptions.evalScripts;
				this.options.requestOptions.evalScripts = false;
				this.createRequest();
				if (showNow) this.update();
			},
			createRequest: function(){
				this.Request = new Request(this.options.requestOptions).addEvent('onSuccess',
					this.options.handleResponse.bind(this));
			},
			update: function(url, options){
				this.Request.setOptions(options).send({url: url||this.options.url});
				return this;
			}
		};
	};
	try {	StickyWin.Ajax = new Class(SWA(StickyWin)); } catch(e){}
	try {	StickyWin.Modal.Ajax = new Class(SWA(StickyWin.Modal)); } catch(e){}
})();/*
Script: Form.Request.Prompt.js
	Prompts the user with the contents of a form (retrieved via ajax) and updates a DOM element with the result of the submission.

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
(function(){
	
	var prompter = function(ext){
		return {
			Extends: ext,
			options: {
				//onUpdate: $empty,
				stickyWinToUse: StickyWin.Modal,
				stickyWinOptions: {},
				caption: 'Update Info',
				useUi: true,
				stickyWinUiOptions: {
					width: 500
				},
				useSpinner: true
			},
			initialize: function(form, update, options){
				this.setOptions(options);
				this.update = document.id(update);
				this.makeStickyWin(form);
				this.swin.addEvent('close', function(){
					if (this.request && this.request.spinner) this.request.spinner.hide();
				});
				this.addEvent('success', this.hide.bind(this));
			},
			makeStickyWin: function(form){
				if (document.id(form)) form = document.id(form);
				this.swin = new this.options.stickyWinToUse({
					content: this.options.useUi?StickyWin.ui(this.options.caption, form, this.options.stickyWinUiOptions):form,
					showNow: false
				});
				this.element = this.swin.win.getElement('form');
				this.initAfterUpdate();
			},
			hide: function(){
				this.swin.hide();
				return this;
			},
			prompt: function(){
				this.swin.show();
				return this;
			},
			initAfterUpdate: function(){
				this.setOptions({
					requestOptions: {
						useSpinner: this.options.useWaiter || this.options.useSpinner,
						spinnerTarget: document.id(this),
						spinnerOptions: {
							layer: {
								styles: {
									zIndex: 10001
								}
							}
						}
					}
				});
				this.makeRequest();
				this.attach();
				document.id(this).store('form.request', this);
			}
		};
	};
	
	Form.Request.Prompt = new Class(prompter(Form.Request));
	if (Form.Request.Append) Form.Request.Append.Prompt = new Class(prompter(Form.Request.Append));
	
	
	var ajaxPrompter = function(ext) {
		return {
			Extends: ext,
			options: {
				stickyWinToUse: StickyWin.Modal.Ajax
			},
			makeStickyWin: function(formUrl){
				if (this.swin) return this.swin;
				this.swin = new this.options.stickyWinToUse($merge({
					showNow: false,
					requestOptions: this.options.requestOptions,
					onHide: function(){
						this.win.empty();
					},
					url: formUrl,
					handleResponse: function(response) {
						var responseScript = "";
						this.swin.Request.response.text.stripScripts(function(script){	responseScript += script; });
						var content = this.options.useUi?StickyWin.ui(this.options.caption, response, this.options.stickyWinUiOptions):response;
						this.swin.setContent(content);
						if (this.options.requestOptions.evalScripts) $exec(responseScript);
						this.element = this.swin.win.getElement('form');
						this.initAfterUpdate();
						this.swin.show();
					}.bind(this)
				}, this.options.stickyWinOptions));
				return this.swin;
			},
			prompt: function(){
				this.makeStickyWin().update();
				return this;
			}
		};
	};

	Form.Request.AjaxPrompt = new Class(ajaxPrompter(Form.Request.Prompt));
	if (Form.Request.Append) Form.Request.Append.AjaxPrompt = new Class(ajaxPrompter(Form.Request.Append.Prompt));
})();/*

Element Property: inputValue {#Element-Properties:inputValue}
-------------------------------------------------------------

### Setter:

Sets the value of the form Element.

#### Syntax:

	myElement.set('inputValue', value);

#### Arguments:

1. value - (*mixed*) a *string*, *boolean*, or *array*, depending on the type of input.

#### Returns:

* (*element*) This Element.

#### Notes:

* When this setter is used on a standard text input or textarea, it just proxies *myInput.set('value', value);*
* When used on a radio or checkbox, you can pass in a *boolean* to check or uncheck it, or pass in a *string* and if the string matches the name, the input will be checked
* When used on a select, you can pass in a *string* for the option to check (unchecking all others) or an *array* of strings to check more than one (for multi-selects) 

#### Examples:

	document.id('mySelect').set('inputValue', 'option1'); //option with value 'option1' is selected
	document.id('myMultiSelect').set('inputValue', ['option1', 'option2']); 
		//options w/ values 'option1' and 'option2' are selected
	document.id('myRadio').set('inputValue', true); //the radio is checked
	document.id('myRadio').set('inputValue', 'red'); //if the radio's name is 'red', it is checked
	document.id('myCheckbox').set('inputValue', true); //the checkbox is checked
	document.id('myCheckbox').set('inputValue', 'red'); //if the checkbox's name is 'red', it is checked
	document.id('myTextArea').set('inputValue', 'foo'); //the value of the textarea is 'foo'
	document.id('myTextInput').set('inputValue', 'foo'); //the value of the text input is 'foo'

### Getter:	
	
Returns the value of the input.

#### Syntax:

	myElement.get('inputValue');

#### Returns:

* (*mixed*) Depending on the type of object, it may return a *string*, *boolean*, or *array*

#### Notes:

* When this getter is used on a standard text input or textarea, it just proxies *myInput.get('value');*
* When used on a checkbox, if the input is checked it will return the *true*, other wise *false*
* When used on a radio it will return the value of the radio that is checked that shares the same name or *null* if none are.
* When used on a select list it will return the value (a *string*) of the selected option or its text value if the value is not defined
* When used on a multi-select it will always return an *array* of the values (*strings*) of the selected options (or their text values if value is not defined)

*/

Element.Properties.inputValue = {

	get: function(){
		 switch(this.get('tag')) {
		 	case 'select':
				vals = this.getSelected().map(function(op){ 
					var v = $pick(op.get('value'),op.get('text')); 
					return (v=="")?op.get('text'):v;
				});
				return this.get('multiple')?vals:vals[0];
			case 'input':
				switch(this.get('type')) {
					case 'checkbox':
						return this.get('checked')?this.get('value'):false;
					case 'radio':
						var checked;
						if (this.get('checked')) return this.get('value');
						document.id(this.getParent('form')||document.body).getElements('input').each(function(input){
							if (input.get('name') == this.get('name') && input.get('checked')) checked = input.get('value');
						}, this);
						return checked||null;
				}
		 	case 'input': case 'textarea':
				return this.get('value');
			default:
				return this.get('inputValue');
		 }
	},

	set: function(value){
		switch(this.get('tag')){
			case 'select':
				this.getElements('option').each(function(op){
					var v = $pick(op.get('value'), op.get('text'));
					if (v=="") v = op.get('text');
					op.set('selected', $splat(value).contains(v));
				});
				break;
			case 'input':
				if (['radio','checkbox'].contains(this.get('type'))) {
					this.set('checked', $type(value)=="boolean"?value:$splat(value).contains(this.get('value')));
					break;
				}
			case 'textarea': case 'input':
				this.set('value', value);
				break;
			default:
				this.set('inputValue', value);
		}
		return this;
	},

	erase: function() {
		switch(this.get('tag')) {
			case 'select':
				this.getElements('option').each(function(op) {
					op.erase('selected');
				});
				break;
			case 'input':
				if (['radio','checkbox'].contains(this.get('type'))) {
					this.set('checked', false);
					break;
				}
			case 'input': case 'textarea':
				this.set('value', '');
				break;
			default:
				this.set('inputValue', '');
		}
		return this;
	}

};var Modalizer = new Class({
	defaultModalStyle: {
		display:'block',
		position:'fixed',
		top:0,
		left:0,	
		'z-index':5000,
		'background-color':'#333',
		opacity:0.8
	},
	setModalOptions: function(options){
		this.modalOptions = $merge({
			width:(window.getScrollSize().x),
			height:(window.getScrollSize().y),
			elementsToHide: 'select, embed' + (Browser.Engine.trident ? '': ', object'),
			hideOnClick: true,
			modalStyle: {},
			updateOnResize: true,
			layerId: 'modalOverlay',
			onModalHide: $empty,
			onModalShow: $empty
		}, this.modalOptions, options);
		return this;
	},
	layer: function(){
		if (!this.modalOptions.layerId) this.setModalOptions();
		return document.id(this.modalOptions.layerId) || new Element('div', {id: this.modalOptions.layerId}).inject(document.body);
	},
	resize: function(){
		if (this.layer()) {
			this.layer().setStyles({
				width:(window.getScrollSize().x),
				height:(window.getScrollSize().y)
			});
		}
	},
	setModalStyle: function (styleObject){
		this.modalOptions.modalStyle = styleObject;
		this.modalStyle = $merge(this.defaultModalStyle, {
			width:this.modalOptions.width,
			height:this.modalOptions.height
		}, styleObject);
		if (this.layer()) this.layer().setStyles(this.modalStyle);
		return(this.modalStyle);
	},
	modalShow: function(options){
		this.setModalOptions(options);
		this.layer().setStyles(this.setModalStyle(this.modalOptions.modalStyle));
		if (Browser.Engine.trident4) this.layer().setStyle('position','absolute');
		this.layer().removeEvents('click').addEvent('click', function(){
			this.modalHide(this.modalOptions.hideOnClick);
		}.bind(this));
		this.bound = this.bound||{};
		if (!this.bound.resize && this.modalOptions.updateOnResize) {
			this.bound.resize = this.resize.bind(this);
			window.addEvent('resize', this.bound.resize);
		}
		if ($type(this.modalOptions.onModalShow)  == "function") this.modalOptions.onModalShow();
		this.togglePopThroughElements(0);
		this.layer().setStyle('display','block');
		return this;
	},
	modalHide: function(override, force){
		if (override === false) return false; //this is internal, you don't need to pass in an argument
		this.togglePopThroughElements(1);
		if ($type(this.modalOptions.onModalHide) == "function") this.modalOptions.onModalHide();
		this.layer().setStyle('display','none');
		if (this.modalOptions.updateOnResize) {
			this.bound = this.bound||{};
			if (!this.bound.resize) this.bound.resize = this.resize.bind(this);
			window.removeEvent('resize', this.bound.resize);
		}
		return this;
	},
	togglePopThroughElements: function(opacity){
		if (Browser.Engine.trident4 || (Browser.Engine.gecko && Browser.Platform.mac)) {
			$$(this.modalOptions.elementsToHide).each(function(sel){
				sel.setStyle('opacity', opacity);
			});
		}
	}
});/*
Script: Waiter.Compat.js

License:
	http://www.clientcide.com/wiki/cnet-libraries#license
*/
var Waiter = new Class({
	
	Extends: Spinner,
	
	options: {
		baseHref: 'http://www.cnet.com/html/rb/assets/global/waiter/',
		containerProps: {
			styles: {
				position: 'absolute',
				'text-align': 'center'
			},
			'class':'waiterContainer'
		},
		containerPosition: {},
		msg: false,
		msgProps: {
			styles: {
				'text-align': 'center',
				fontWeight: 'bold'
			},
			'class':'waiterMsg'
		},
		img: {
			src: 'waiter.gif',
			styles: {
				width: 24,
				height: 24
			},
			'class':'waiterImg'
		},
		layer:{
			styles: {
				width: 0,
				height: 0,
				position: 'absolute',
				zIndex: 999,
				display: 'none',
				opacity: 0.9,
				background: '#fff'
			},
			'class': 'waitingDiv'
		},
		useIframeShim: true,
		fxOptions: {},
		injectWhere: null
//	iframeShimOptions: {},
//	onShow: $empty
//	onHide: $empty
	},

	render: function(){
		this.parent();
		this.waiterContainer = this.element.set(this.options.containerProps);
		if (this.msgContainer) this.msgContainer = this.content.set(this.options.msgProps);
		
		if (this.options.img) this.waiterImg = document.id(this.options.img.id) || new Element('img', $merge(this.options.img, {
			src: this.options.baseHref + this.options.img.src
		})).inject(this.img);
		this.element.set(this.options.layer);
	},
	place: function(){
		this.inject.apply(this, arguments);
	},
	reset: function(){
		return this.hide();
	},
	start: function(element){
		return this.show();
	},
	stop: function(callback){
		return this.hide();
	}
});

if (window.Request) {
	Request = Class.refactor(Request, {
		options: {
			useWaiter: false,
			waiterOptions: {},
			waiterTarget: false
		},
		initialize: function(options){
			if (options) {
				if (options.useWaiter) options.useSpinner = options.useWaiter;
				if (options.waiterOptions) options.spinnerOptions = options.waiterOptions;
				if (options.waiterTarget) options.spinnerTarget = options.waiterTarget;
			}
			this.previous(options);
		}
	});
}

Element.Properties.waiter = {

	set: function(options){
		return this.set('spinner', options);
	},

	get: function(options){
		return this.get('spinner', options);
	}

};

Element.implement({

	wait: function(options){
		return this.spin(options);
	},
	
	release: function(){
		return this.unspin(options);
	}

});