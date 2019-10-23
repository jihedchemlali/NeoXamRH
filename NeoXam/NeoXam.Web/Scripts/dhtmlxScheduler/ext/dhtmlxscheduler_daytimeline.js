/*
@license
dhtmlxScheduler.Net v.4.0.0 Professional Evaluation

This software is covered by DHTMLX Evaluation License. Contact sales@dhtmlx.com to get Commercial or Enterprise license. Usage without proper license is prohibited.

(c) Dinamenta, UAB.
*/
Scheduler.plugin(function(e){!function(){e._inited_multisection_copies||(e.attachEvent("onEventIdChange",function(e,t){var a=this._multisection_copies;if(a&&a[e]&&!a[t]){var i=a[e];delete a[e],a[t]=i}}),e._inited_multisection_copies=!0),e._register_copies_array=function(e){for(var t=0;t<e.length;t++)this._register_copy(e[t])},e._register_copy=function(e){this._multisection_copies[e.id]||(this._multisection_copies[e.id]={});var t=e[this._get_section_property()],a=this._multisection_copies[e.id];a[t]||(a[t]=e);
},e._get_copied_event=function(t,a){if(!this._multisection_copies[t])return null;if(this._multisection_copies[t][a])return this._multisection_copies[t][a];var i=this._multisection_copies[t];if(e._drag_event&&e._drag_event._orig_section&&i[e._drag_event._orig_section])return i[e._drag_event._orig_section];var n=1/0,r=null;for(var l in i)i[l]._sorder<n&&(r=i[l],n=i[l]._sorder);return r},e._clear_copied_events=function(){this._multisection_copies={}},e._restore_render_flags=function(t){for(var a=this._get_section_property(),i=0;i<t.length;i++){
var n=t[i],r=e._get_copied_event(n.id,n[a]);if(r)for(var l in r)0===l.indexOf("_")&&(n[l]=r[l])}};var t=e.createTimelineView;e.createTimelineView=function(a){function i(){var t=new Date(e.getState().date),i=e.date[u+"_start"](t);i=e.date.date_part(i);var n=[],r=e.matrix[u];r.y_unit=n,r.order={};for(var l=0;l<a.days;l++)n.push({key:+i,label:i}),r.order[r.y_unit[l].key]=l,i=e.date.add(i,1,"day")}function n(e){var t={};for(var a in e)t[a]=e[a];return t}function r(e,t){t.setDate(1),t.setFullYear(e.getFullYear()),
t.setMonth(e.getMonth()),t.setDate(e.getDate())}function l(t){for(var a=[],i=0;i<t.length;i++){var n=d(t[i]);if(e.isOneDayEvent(n))s(n),a.push(n);else{for(var r=new Date(Math.min(+n.end_date,+e._max_date)),l=new Date(Math.max(+n.start_date,+e._min_date)),_=[];+r>+l;){var u=d(n);u.start_date=l,u.end_date=new Date(Math.min(+c(u.start_date),+r)),l=c(l),s(u),a.push(u),_.push(u)}o(_,n)}}return a}function o(e,t){for(var a=!1,i=!1,n=0,r=e.length;r>n;n++){var l=e[n];a=+l._w_start_date==+t.start_date,i=+l._w_end_date==+t.end_date,
l._no_resize_start=l._no_resize_end=!0,a&&(l._no_resize_start=!1),i&&(l._no_resize_end=!1)}}function d(t){var a=e.getEvent(t.event_pid);return a&&a.isPrototypeOf(t)?(t=e._copy_event(t),delete t.event_length,delete t.event_pid,delete t.rec_pattern,delete t.rec_type):t=e._lame_clone(t),t}function s(t){if(!t._w_start_date||!t._w_end_date){var a=e.date,i=t._w_start_date=new Date(t.start_date),n=t._w_end_date=new Date(t.end_date);t[h]=+a.date_part(t.start_date),t._count||(t._count=1),t._sorder||(t._sorder=0);
var r=n-i;t.start_date=new Date(e._min_date),_(i,t.start_date),t.end_date=new Date(+t.start_date+r),i.getTimezoneOffset()!=n.getTimezoneOffset()&&(t.end_date=new Date(t.end_date.valueOf()+6e4*(i.getTimezoneOffset()-n.getTimezoneOffset())))}}function _(e,t){t.setMinutes(e.getMinutes()),t.setHours(e.getHours())}function c(t){var a=e.date.add(t,1,"day");return a=e.date.date_part(a)}if("days"!=a.render)return void t.apply(this,arguments);var u=a.name,h=a.y_property="timeline-week"+u;a.y_unit=[],a.render="bar",
a.days=a.days||7,t.call(this,a),e.templates[u+"_scalex_class"]=function(){},e.templates[u+"_scaley_class"]=function(){},e.templates[u+"_scale_label"]=function(t,a,i){return e.templates.day_date(a)},e.date[u+"_start"]=function(t){return t=e.date.week_start(t),t=e.date.add(t,a.x_step*a.x_start,a.x_unit)},e.date["add_"+u]=function(t,i){return e.date.add(t,i*a.days,"day")};var p=e._renderMatrix;e._renderMatrix=function(e,t){e&&i(),p.apply(this,arguments)};var v=e.checkCollision;e.checkCollision=function(t){
if(t[h]){var t=n(t);delete t[h]}return v.apply(e,[t])},e.attachEvent("onBeforeDrag",function(t,a,i){var n=i.target||i.srcElement,r=e._getClassName(n);if("resize"==a)r.indexOf("dhx_event_resize_end")<0?e._w_line_drag_from_start=!0:e._w_line_drag_from_start=!1;else if("move"==a&&r.indexOf("no_drag_move")>=0)return!1;return!0});var m=e["mouse_"+u];e["mouse_"+u]=function(t){var a;if(this._drag_event&&(a=this._drag_event._move_delta),void 0===a&&"move"==e._drag_mode){var i=e.matrix[this._mode],n={y:t.y
};e._resolve_timeline_section(i,n);var r=t.x-i.dx,l=new Date(n.section);_(e._timeline_drag_date(i,r),l);var o=e._drag_event,d=this.getEvent(this._drag_id);d&&(o._move_delta=(d.start_date-l)/6e4,this.config.preserve_length&&t._ignores&&(o._move_delta=this._get_real_event_length(d.start_date,l,i),o._event_length=this._get_real_event_length(d.start_date,d.end_date,i)))}var t=m.apply(e,arguments);if(e._drag_mode&&"move"!=e._drag_mode){var s=null;s=e._drag_event&&e._drag_event["timeline-week"+u]?new Date(e._drag_event["timeline-week"+u]):new Date(t.section),
t.y+=Math.round((s-e.date.date_part(new Date(e._min_date)))/(6e4*this.config.time_step)),"resize"==e._drag_mode&&(t.resize_from_start=e._w_line_drag_from_start)}else if(e._drag_event){var c=Math.floor(Math.abs(t.y/(1440/e.config.time_step)));c*=t.y>0?1:-1,t.y=t.y%(1440/e.config.time_step);var h=e.date.date_part(new Date(e._min_date));h.valueOf()!=new Date(t.section).valueOf()&&(t.x=Math.floor((t.section-h)/864e5),t.x+=c)}return t},e.attachEvent("onEventCreated",function(t,a){return e._events[t]&&delete e._events[t][h],
!0}),e.attachEvent("onBeforeEventChanged",function(t,a,i,n){return e._events[t.id]&&delete e._events[t.id][h],!0});var b=e._update_timeline_section;e._update_timeline_section=function(t){var a,i;this._mode==u&&(a=t.event,a&&(i=e._get_copied_event(a.id,e.date.day_start(new Date(a.start_date.valueOf()))),i&&(t.event._sorder=i._sorder,t.event._count=i._count)));b.apply(this,arguments);a&&i&&(i._count=a._count,i._sorder=a._sorder)};var g=e.render_view_data;e.render_view_data=function(t,a){return this._mode==u&&t&&(t=l(t),
e._restore_render_flags(t)),g.apply(e,[t,a])};var x=e.get_visible_events;e.get_visible_events=function(){if(this._mode==u){this._clear_copied_events(),e._max_date=e.date.date_part(e.date.add(e._min_date,a.days,"day"));var t=x.apply(e,arguments);return t=l(t),e._register_copies_array(t),t}return x.apply(e,arguments)};var y=e.addEventNow;e.addEventNow=function(t){if(e.getState().mode==u)if(t[h]){var a=new Date(t[h]);r(a,t.start_date),r(a,t.end_date)}else{var i=new Date(t.start_date);t[h]=+e.date.date_part(i);
}return y.apply(e,arguments)};var f=e._render_marked_timespan;e._render_marked_timespan=function(){return e._mode!=u?f.apply(this,arguments):void 0}}}()});