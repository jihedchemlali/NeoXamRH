/*
@license
dhtmlxScheduler.Net v.4.0.0 Professional Evaluation

This software is covered by DHTMLX Evaluation License. Contact sales@dhtmlx.com to get Commercial or Enterprise license. Usage without proper license is prohibited.

(c) Dinamenta, UAB.
*/
Scheduler.plugin(function(e){!function(){function t(){i=!1,e.callEvent("onAfterSchedulerResize",[]),i=!0}e.config.container_autoresize=!0,e.config.month_day_min_height=90,e.config.min_grid_size=25,e.config.min_map_size=400;var a=e._pre_render_events,i=!0,n=0,r=0;e._pre_render_events=function(t,l){if(!e.config.container_autoresize||!i)return a.apply(this,arguments);var o=this.xy.bar_height,d=this._colsS.heights,s=this._colsS.heights=[0,0,0,0,0,0,0],_=this._els.dhx_cal_data[0];if(t=this._table_view?this._pre_render_events_table(t,l):this._pre_render_events_line(t,l),
this._table_view)if(l)this._colsS.heights=d;else{var c=_.firstChild;if(c.rows){for(var u=0;u<c.rows.length;u++){if(s[u]++,s[u]*o>this._colsS.height-this.xy.month_head_height){var h=c.rows[u].cells,p=this._colsS.height-this.xy.month_head_height;1*this.config.max_month_events!==this.config.max_month_events||s[u]<=this.config.max_month_events?p=s[u]*o:(this.config.max_month_events+1)*o>this._colsS.height-this.xy.month_head_height&&(p=(this.config.max_month_events+1)*o);for(var v=0;v<h.length;v++)h[v].childNodes[1].style.height=p+"px";
s[u]=(s[u-1]||0)+h[0].offsetHeight}s[u]=(s[u-1]||0)+c.rows[u].cells[0].offsetHeight}s.unshift(0),c.parentNode.offsetHeight<c.parentNode.scrollHeight&&!c._h_fix}else if(t.length||"visible"!=this._els.dhx_multi_day[0].style.visibility||(s[0]=-1),t.length||-1==s[0]){var m=(c.parentNode.childNodes,(s[0]+1)*o+1);r!=m+1&&(this._obj.style.height=n-r+m-1+"px"),m+="px",_.style.top=this._els.dhx_cal_navline[0].offsetHeight+this._els.dhx_cal_header[0].offsetHeight+parseInt(m,10)+"px",_.style.height=this._obj.offsetHeight-parseInt(_.style.top,10)-(this.xy.margin_top||0)+"px";
var b=this._els.dhx_multi_day[0];b.style.height=m,b.style.visibility=-1==s[0]?"hidden":"visible",b=this._els.dhx_multi_day[1],b.style.height=m,b.style.visibility=-1==s[0]?"hidden":"visible",b.className=s[0]?"dhx_multi_day_icon":"dhx_multi_day_icon_small",this._dy_shift=(s[0]+1)*o,s[0]=0}}return t};var l=["dhx_cal_navline","dhx_cal_header","dhx_multi_day","dhx_cal_data"],o=function(t){n=0;for(var a=0;a<l.length;a++){var i=l[a],o=e._els[i]?e._els[i][0]:null,d=0;switch(i){case"dhx_cal_navline":case"dhx_cal_header":
d=parseInt(o.style.height,10);break;case"dhx_multi_day":d=o?o.offsetHeight-1:0,r=d;break;case"dhx_cal_data":var s=e.getState().mode;if(d=o.childNodes[1]&&"month"!=s?o.childNodes[1].offsetHeight:Math.max(o.offsetHeight-1,o.scrollHeight),"month"==s){if(e.config.month_day_min_height&&!t){var _=o.getElementsByTagName("tr").length;d=_*e.config.month_day_min_height}t&&(o.style.height=d+"px")}else if("year"==s)d=190*e.config.year_y;else if("agenda"==s){if(d=0,o.childNodes&&o.childNodes.length)for(var c=0;c<o.childNodes.length;c++)d+=o.childNodes[c].offsetHeight;
d+2<e.config.min_grid_size?d=e.config.min_grid_size:d+=2}else if("week_agenda"==s){for(var u,h,p=e.xy.week_agenda_scale_height+e.config.min_grid_size,v=0;v<o.childNodes.length;v++){h=o.childNodes[v];for(var c=0;c<h.childNodes.length;c++){for(var m=0,b=h.childNodes[c].childNodes[1],g=0;g<b.childNodes.length;g++)m+=b.childNodes[g].offsetHeight;u=m+e.xy.week_agenda_scale_height,u=1!=v||2!=c&&3!=c?u:2*u,u>p&&(p=u)}}d=3*p}else if("map"==s){d=0;for(var x=o.querySelectorAll(".dhx_map_line"),c=0;c<x.length;c++)d+=x[c].offsetHeight;
d+2<e.config.min_map_size?d=e.config.min_map_size:d+=2}else if(e._gridView)if(d=0,o.childNodes[1].childNodes[0].childNodes&&o.childNodes[1].childNodes[0].childNodes.length){for(var x=o.childNodes[1].childNodes[0].childNodes[0].childNodes,c=0;c<x.length;c++)d+=x[c].offsetHeight;d+=2,d<e.config.min_grid_size&&(d=e.config.min_grid_size)}else d=e.config.min_grid_size;if(e.matrix&&e.matrix[s]){if(t)d+=0,o.style.height=d+"px";else{d=0;for(var y=e.matrix[s],f=y.y_unit,k=0;k<f.length;k++)d+=y._section_height[f[k].key];
}d-=1}("day"==s||"week"==s||e._props&&e._props[s])&&(d+=2)}d+=1,n+=d}e._obj.style.height=n+"px",t||e.updateView()},d=function(){if(!e.config.container_autoresize||!i)return!0;var a=e.getState().mode;if(!a)return!0;var n=document.documentElement.scrollTop;if(o(),e.matrix&&e.matrix[a]||"month"==a){var r=window.requestAnimationFrame||window.setTimeout;r(function(){o(!0),document.documentElement.scrollTop=n,t()},1)}else t()};e.attachEvent("onBeforeViewChange",function(){var t=e.config.container_autoresize;
if(e.xy.$original_scroll_width||(e.xy.$original_scroll_width=e.xy.scroll_width),e.xy.scroll_width=t?0:e.xy.$original_scroll_width,e.matrix)for(var a in e.matrix){var i=e.matrix[a];i.$original_section_autoheight||(i.$original_section_autoheight=i.section_autoheight),t?i.section_autoheight=!1:i.section_autoheight=i.$original_section_autoheight}return!0}),e.attachEvent("onViewChange",d),e.attachEvent("onXLE",d),e.attachEvent("onEventChanged",d),e.attachEvent("onEventCreated",d),e.attachEvent("onEventAdded",d),
e.attachEvent("onEventDeleted",d),e.attachEvent("onAfterSchedulerResize",d),e.attachEvent("onClearAll",d),e.attachEvent("onBeforeExpand",function(){return i=!1,!0}),e.attachEvent("onBeforeCollapse",function(){return i=!0,!0})}()});