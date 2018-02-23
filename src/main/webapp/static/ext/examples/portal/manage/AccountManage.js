Ext.namespace('Ext.spring.dataConfiguration.accountManage');//自定一个命名空间，防止同名方法发生冲突
Ext.onReady(function () {
    Ext.tip.QuickTipManager.init();

    Ext.define('Forestry.app.accountManage',{
        extend:'Ext.panel.Panel',
        id:'accountmanage_panel',
        sgrid:null,
        sgrid2:null,
        initComponent:function () {
            var me = this;
            // me.sgrid =
            // me.sgrid2 =
            Ext.apply(this,{
                layout:'border',
                items:[
                    me.sgrid,
                    me.sgrid2
                ]
            });
            this.callParent(arguments);//继承（extend）父类的方法，主要作用是为了覆盖 , 而同时又不是全部都覆盖
        }
    });
});