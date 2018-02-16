/**
 * 首页
 */
Ext.define('Ext.app.Home', {
	extend : 'Ext.form.Panel',
	initComponent : function() {
		Ext.apply(this, {
			//autoScroll : true,
			defaults : {
				defaults : {
					ui : 'light',
					closable : false
				}
			},
			items : [ {
				id : 'c1',
				items : [ {
					id : 'p1',
					// title : '变电站基础信息维护系统',
					style : 'padding:100px; line-height:22px;',
					html : '<p align="center"><img src = ' + appBaseUri + '/static/leaflet/images/roof.jpg width = "960" height = "340"/></p>' +
					'<p align="center">山东容弗新信息科技有限公司</p><p align="center">电话：0531-88872188，传真：0531-88870103</p><p align="center">地址：山东省济南市高新区新泺大街2008号银荷大厦D-402</p>'
				} ]
			} ],
			isReLayout : false
		});
		this.callParent(arguments);
	}
});
