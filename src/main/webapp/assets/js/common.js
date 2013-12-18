//Examine the browser
var userAgent = navigator.userAgent.toLowerCase();
var is_opera  = (userAgent.indexOf('opera') != -1);
var is_saf    = ((userAgent.indexOf('applewebkit') != -1) || (navigator.vendor == 'Apple Computer, Inc.'));
var is_webtv  = (userAgent.indexOf('webtv') != -1);
var is_ie     = ((userAgent.indexOf('msie') != -1) && (!is_opera) && (!is_saf) && (!is_webtv));
var is_ie4    = ((is_ie) && (userAgent.indexOf('msie 4.') != -1));
var is_moz    = ((navigator.product == 'Gecko') && (!is_saf));
var is_kon    = (userAgent.indexOf('konqueror') != -1);
var is_ns     = ((userAgent.indexOf('compatible') == -1) && (userAgent.indexOf('mozilla') != -1) && (!is_opera) && (!is_webtv) && (!is_saf));
var is_ns4    = ((is_ns) && (parseInt(navigator.appVersion) == 4));
var is_mac    = (userAgent.indexOf('mac') != -1);

//Global registers
var currentblogid;
var currentstarid;
var currentcommentid;
var switchcomandmes;

//Get absolute path, fix bug of IE when base path is set
var gotourl = location.href;
var absbaseurl;
var r_identifier=gotourl.lastIndexOf("/read.php/");
if (r_identifier!=-1) {
	absbaseurl = gotourl.substr(0, r_identifier)+"/";
} else {
	r_identifier=gotourl.lastIndexOf("/post/");
	if (r_identifier!=-1) {
		absbaseurl = gotourl.substr(0, r_identifier)+"/";
	} else absbaseurl = '';
}

//Is Ajax supported?
if ((is_ie & !is_ie4) || is_moz || is_saf || is_opera) var shutajax=0;
else var shutajax=1;

//For firefox, tell Firefox not to display the content you input in last session
if (is_moz) {
	var tmp_c=document.getElementById('v_content');
	if (tmp_c) tmp_c.value='';
}

//Show/Hide a DIV
function showhidediv(id){
  try{
    var panel=document.getElementById(id);
    if(panel){
      if(panel.style.display=='none'){
        panel.style.display='block';
      }else{
        panel.style.display='none';
      }
    }
  }catch(e){}
}

function addhtml (id, htmlcode, uniqueid) {
	var panel=document.getElementById(id);
	var hiddenpannel=document.getElementById(uniqueid);
	if(panel){
		hiddenpannel.value='';
		hiddenpannel.value=panel.innerHTML;
		panel.innerHTML=hiddenpannel.value+htmlcode;
		hiddenpannel.value+=htmlcode;
	}
}

function showadminreply (divid) {
	switchcomandmes='reply';
	var cleanid=divid.replace('com_', '');
	var inputcontent="<form action='"+absbaseurl+"admin.php?go=reply_addadminreply_"+cleanid+"' method='post' id='formadminreply"+cleanid+"'>";
	inputcontent+=jslang[0]+"<br/><textarea cols='66' rows='3' name='adminreplycontent' id='adminreplycontent"+cleanid+"'></textarea><br/>";
	inputcontent+="<input type='button' onclick=\"ajax_adminreply('"+cleanid+"'); return false;\" value='"+jslang[1]+"' class='button'/> <input type='reset' value='"+jslang[2]+"' class='button'/> <input type='button' value='"+jslang[3]+"' onclick=\"hideadminreply('"+divid+"');\" class='button'/></form>";
	document.getElementById(divid).innerHTML=inputcontent;
	document.getElementById(divid).style.display='block';
}

function showadminreplyformessage (divid) {
	switchcomandmes='message';
	var cleanid=divid.replace('com_', '');
	var inputcontent="<form action='"+absbaseurl+"admin.php?go=message_addadminreply_"+cleanid+"' method='post' id='formadminreply"+cleanid+"'>";
	inputcontent+=jslang[0]+"<br/><textarea cols='66' rows='3' name='adminreplycontent' id='adminreplycontent"+cleanid+"'></textarea><br/>";
	inputcontent+="<input type='button' onclick=\"ajax_adminreply('"+cleanid+"'); return false;\" value='"+jslang[1]+"' class='button'/> <input type='reset' value='"+jslang[2]+"' class='button'/> <input type='button' value='"+jslang[3]+"'  onclick=\"hideadminreply('"+divid+"');\" class='button'/></form>";
	document.getElementById(divid).innerHTML=inputcontent;
	document.getElementById(divid).style.display='block';
}

function hideadminreply (divid) {
	document.getElementById(divid).innerHTML='';
	document.getElementById(divid).style.display='none';
}

function showdelblog(blogid) {
	var urldel=absbaseurl+"admin.php?go=entry_deleteblog_"+blogid+'';
	if(confirm(jslang[4])){
		window.location=urldel;
	}
	else {
		return;
	}
}

function comfirmurl(urldel) {
	if(confirm(jslang[5])){
		window.location=absbaseurl+urldel;
	}
	else {
		return;
	}
}

function showdeladminreply(repid) {
	var urldel=absbaseurl+"admin.php?go=reply_deladminreply_"+repid+'';
	if(confirm(jslang[6])){
		if (shutajax==1) window.location=urldel;
		else {
			urldel+="&ajax=on";
			currentcommentid=repid;
			makeRequest(urldel, 'quickdeladminreply', 'GET', null);
		}
	}
	else {
		return;
	}
}

function showdeladminreplyformessage(repid) {
	var urldel=absbaseurl+"admin.php?go=message_deladminreply_"+repid+'';
	if(confirm(jslang[7])){
		if (shutajax==1) window.location=urldel;
		else {
			urldel+="&ajax=on";
			currentcommentid=repid;
			makeRequest(urldel, 'quickdeladminreply', 'GET', null);
		}
	}
	else {
		return;
	}
}

function showdelreply(repid, blogid) {
	var urldel=absbaseurl+"admin.php?go=reply_delreply_"+repid+'-'+blogid;
	if(confirm(jslang[8])){
		if (shutajax==1) window.location=urldel;
		else {
			urldel+="&ajax=on";
			currentcommentid=repid;
			makeRequest(urldel, 'quickdelreply', 'GET', null);
		}
	}
	else {
		return;
	}
}

function showdelreplyformessage(repid) {
	var urldel=absbaseurl+"admin.php?go=message_delreply_"+repid;
	if(confirm(jslang[9])){
		if (shutajax==1) window.location=urldel;
		else {
			urldel+="&ajax=on";
			currentcommentid=repid;
			makeRequest(urldel, 'quickdelreply', 'GET', null);
		}
	}
	else {
		return;
	}
}


/* -- AJAX RELATED FUNCTIONS -- */
//Starring
function dostar(blogid) {
	if (shutajax==0) {
		gourl=absbaseurl+"admin.php?go=star&ajax=on&blogid="+blogid;
		currentblogid=blogid;
		currentstarid="starid"+blogid;
		makeRequest(gourl, 'starblog', 'GET', null);
	} else {
		gourl=absbaseurl+"admin.php?go=star&ajax=off&blogid="+blogid;
		window.location=gourl;
	}
}

//Comment and Message
function ajax_submit (job) {
	var stat_html = document.getElementById('stat_html').checked ? 1 : 0;
	var stat_ubb = document.getElementById('stat_ubb').checked ? 1 : 0;
	var stat_emot = document.getElementById('stat_emot').checked ? 1 : 0;
	var stat_property = document.getElementById('stat_property').checked ? 1 : 0;
	var v_replier = document.getElementById('v_replier').value+' ';
	var v_repurl = document.getElementById('v_repurl').value;
	var v_repemail = document.getElementById('v_repemail').value;
	var v_content = document.getElementById('v_content').value+' ';
	var v_id = document.getElementById('v_id').value;
	var v_security_plus;
	if (document.getElementById('v_security')) {
		var v_security = document.getElementById('v_security').value;
		if (v_security=='') {
			alert(jslang[10]);
			return false;
		}
		v_security_plus="&v_security="+v_security;
	} else {
		v_security_plus='';
	}
	if (v_replier=='' || v_content==''|| v_replier==' ' || v_content==' ' ) {
		alert(jslang[11]);
		return false;
	}
	document.getElementById('btnSubmit').value=jslang[12];
	document.getElementById('btnSubmit').disabled='disabled';
	if (shutajax==0) { //Currently for IE, Safari, Mozilla and Opera
		v_replier = blogencode(v_replier);
		v_repurl = blogencode(v_repurl);
		v_repemail = blogencode(v_repemail);
		v_content = blogencode(v_content);
		v_id = blogencode(v_id);
		var postData = "unuse=unuse&v_id="+v_id+"&v_replier="+v_replier+"&v_repurl="+v_repurl+"&v_repemail="+v_repemail+"&v_content="+v_content+"&stat_html="+stat_html+"&stat_ubb="+stat_ubb+"&stat_emot="+stat_emot+"&stat_property="+stat_property+v_security_plus;
		var gourl=absbaseurl+"visit.php?ajax=on&job="+job;
		makeRequest(gourl, 'quickreply', 'POST', postData);
	} else {
		document.getElementById('visitorinput').submit();
	}
}

//Ctrl+Enter key submitting (Textarea)
function ctrlenterkey (eventobject){
	if(eventobject.ctrlKey && eventobject.keyCode==13) {
		document.getElementById("btnSubmit").click();
	}
}

//Admin reply
function ajax_adminreply (commentid) {
	if (shutajax==0) {
		var admid='adminreplycontent'+commentid;
		var adminreplycontent = blogencode(document.getElementById(admid).value);
		var postData = "unuse=unuse&adminreplycontent="+adminreplycontent;
		var gourl=absbaseurl+"admin.php?ajax=on&go="+switchcomandmes+"_addadminreply_"+commentid;
		currentcommentid=commentid;
		makeRequest(gourl, 'quickadminreply', 'POST', postData);
	} else {
		var admid='formadminreply'+commentid;
		document.getElementById(admid).submit();
	}
}

function ajax_adminreply_edit (commentid, rptype) {
	switchcomandmes=rptype;
	ajax_adminreply(commentid);
}
//encode string
function blogencode (str) {
	str=encodeURIComponent(str);
	if (is_moz) str=str.replace(/%0A/g, "%0D%0A"); //In IE, a new line is encoded as \r\n, while in Mozilla it's \n
	return str;
}

//Avatar Selection
function changeavatar (slname, area) {
	var current=document.getElementById(slname);
	var realvalue=current.options[current.selectedIndex].value;
	var areashow=document.getElementById(area);
	if (areashow) {
		if (realvalue!='' && realvalue!=null) {
			areashow.innerHTML="<img src='images/avatars/"+realvalue+"' alt=''/>";
		}
		else {
			areashow.innerHTML=jslang[13];
		}
	}
}

//Insert Emots
function insertemot (emotcode) {
	var current=document.getElementById('v_content');
	var emot="[emot]"+emotcode+"[/emot]";
	if (current) {
		document.getElementById('v_content').focus();
		if (current.value!='' && current.value!=null) {
			current.value+=emot;
		}
		else {
			current.value=emot;
		}
	}
}

// Show/Hide Sidebar
function showHideSidebar(){
  try{
    var objSidebar=document.getElementById("sidebar");
    var objContent=document.getElementById("content");
    if(objSidebar.className!="sidebar-hide"){
      objSidebar.className="sidebar-hide";
      objSidebar.style.display="none";
      objContent.className="content-wide";
	  setCookie('sidebaroff', 1,null, null, null, false);
    }else{
      objSidebar.className="sidebar";
      objSidebar.style.display="block";
      objContent.className="content";
	  setCookie('sidebaroff', 0,null, null, null, false);
    }
  }catch(e){}
}

function loadSidebar(){
  try{
    var objSidebar=document.getElementById("sidebar");
    var objContent=document.getElementById("content");
	var sidebaroff=getCookie ('sidebaroff');
    if(sidebaroff==1){
      objSidebar.className="sidebar-hide";
      objSidebar.style.display="none";
      objContent.className="content-wide";
    }else{
      objSidebar.className="sidebar";
      objSidebar.style.display="block";
      objContent.className="content";
    }
  }catch(e){}
}




//Media Link
function playmedia(strID,strType,strURL,intWidth,intHeight) {
	var objDiv=document.getElementById(strID);
	if (!objDiv) return false;
	if (objDiv.style.display!='none') {
		objDiv.innerHTML='';
		objDiv.style.display='none';
	} else {
		objDiv.innerHTML=makemedia(strType,strURL,intWidth,intHeight);
		objDiv.style.display='block';
	}
}

//Media Build
function makemedia (strType,strURL,intWidth,intHeight) {
	var strHtml;
	switch(strType) {
		case 'wmp':
			strHtml="<object width='"+intWidth+"' height='"+intHeight+"' classid='CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6'><param name='url' value='"+strURL+"'/><embed width='"+intWidth+"' height='"+intHeight+"' type='application/x-mplayer2' src='"+strURL+"'></embed></object>";
			break;
		case 'swf':
			strHtml="<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' width='"+intWidth+"' height='"+intHeight+"'><param name='movie' value='"+strURL+"'/><param name='quality' value='high' /><embed src='"+strURL+"' quality='high' type='application/x-shockwave-flash' width='"+intWidth+"' height='"+intHeight+"'></embed></object>";
			break;
		case 'flv':
			strHtml="<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='"+intWidth+"' height='"+intHeight+"' id='FLVPlayer'><param name='movie' value='images/others/FLVPlayer.swf' /><param name='quality' value='high' /><param name='salign' value='lt' /><param name='scale' value='noscale' /><param name='wmode' value='transparent' /><param name='FlashVars' value='&MM_ComponentVersion=1&skinName=images/others/Skin_1&streamName="+strURL+"&autoPlay=true&autoRewind=false' /><embed src='images/others/FLVPlayer.swf' flashvars='&MM_ComponentVersion=1&skinName=images/others/Skin_1&streamName="+strURL+"&autoPlay=true&autoRewind=false' scale='noscale' name='FLVPlayer' salign='LT' quality='high' type='application/x-shockwave-flash' width='"+intWidth+"' height='"+intHeight+"' pluginspage='http://www.macromedia.com/go/getflashplayer' wmode='transparent'></embed></object>";
			break;
		case 'real':
			strHtml="<object classid='clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA' width='"+intWidth+"' height='"+intHeight+"'><param name='src' value='"+absbaseurl+"inc/realplay.php?link="+strURL+"' /><param name='controls' value='Imagewindow' /><param name='console' value='clip1' /><param name='autostart' value='true' /><embed src='"+absbaseurl+"inc/realplay.php?link="+strURL+"' type='audio/x-pn-realaudio-plugin' autostart='true' console='clip1' controls='Imagewindow' width='"+intWidth+"' height='"+intHeight+"'></embed></object><br/><object classid='clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA' width='"+intWidth+"' height='44'><param name='src' value='"+absbaseurl+"inc/realplay.php?link="+strURL+"' /><param name='controls' value='ControlPanel' /><param name='console' value='clip1' /><param name='autostart' value='true' /><embed src='"+absbaseurl+"inc/realplay.php?link="+strURL+"' type='audio/x-pn-realaudio-plugin' autostart='true' console='clip1' controls='ControlPanel' width='"+intWidth+"' height='44'></embed></object>";
			break;
	}
	return strHtml;
}

//Font size control
function doZoom(size) {
	document.getElementById('zoomtext').style.fontSize=size+'px';
}


//Cookie
function setCookie(name,value,expiry,path,domain,secure) {
	var nameString = name + "=" + value;
	var expiryString = (expiry == null) ? "" : " ;expires = "+ expiry.toGMTString();
	var pathString = (path == null) ? "" : " ;path = "+ path;
	var domainString = (path == null) ? "" : " ;domain = "+ domain;
	var secureString = (secure) ?";secure" :"";
	document.cookie = nameString + expiryString + pathString + domainString + secureString;
}

function getCookie (name) {
	var CookieFound = false;
	var start = 0;
	var end = 0;
	var CookieString = document.cookie;
	var i = 0;

	while (i <= CookieString.length) {
		start = i ;
		end = start + name.length;
		if (CookieString.substring(start, end) == name){
			CookieFound = true;
			break;
		}
		i++;
	}

	if (CookieFound){
		start = end + 1;
		end = CookieString.indexOf(";",start);
		if (end < start) end = CookieString.length;
		return unescape(CookieString.substring(start, end));
	}
	return "";
}

function deleteCookie(name) {
	var expires = new Date();
	expires.setTime (expires.getTime() - 1);
	setCookie( name , "Delete Cookie", expires,null,null,false);
}