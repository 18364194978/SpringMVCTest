Ext.namespace('Ext.spring.dataConfiguration.manage.CompanyManage');//自定一个命名空间，防止同名方法发生冲突
/**
 * Created by xie on 2018/3/8.
 */
Ext.onReady(function () {
    Ext.tip.QuickTipManager.init();
    Ext.define('App.CompanyManagementWindow', {
        extend: 'Ext.window.Window',
        constructor: function (config) {
            config = config || {};
            Ext.apply(config, {
                title: '公司信息',
                width: 400,
                height: 150,
                bodyPadding: '10 5',
                modal: true,
                layout: 'fit',
                items: [{
                    xtype: 'form',
                    fieldDefaults: {
                        labelAlign: 'left',
                        labelWidth: 50,
                        anchor: '100%'
                    },
                    items: [{
                        xtype: 'textfield',
                        name: 'item_type',
                        id: 'item_type',
                        hidden:true
                    },{
                        xtype: 'textfield',
                        name: 'company_id',
                        id: 'company_id',
                        hidden:true
                    }, {
                        xtype: 'textfield',
                        name: 'company_name',
                        fieldLabel: '公司',
                        emptyText: '请输入公司',
                        allowBlank: false,
                        maxLength: 30
                    }],
                    buttons: ['->', {
                        text: '保存',
                        iconCls: 'icon-save',
                        width: 80,
                        handler: function (btn, eventObj) {
                            var window = btn.up('window');
                            regionIsCreate = true;
                            var form = window.down('form').getForm();
                            if (form.isValid()) {
                                window.getEl().mask('数据保存中，请稍候...');
                                var vals = form.getValues();
                                Ext.Ajax.request({
                                    url: appBaseUri + '/spring/account/saveCreateRegion',
                                    params: {
                                        region_name: vals['region_name'],
                                        region_id: vals['region_id'],
                                        isCreate: regionIsCreate
                                    },
                                    method: 'POST',
                                    success: function (response) {
                                        window.getEl().unmask();
                                        if (response.responseText != '') {
                                            var res = Ext.JSON.decode(response.responseText);
                                            if (res.success) {
                                                window.close();
                                                globalObject.msgTip('操作成功！');
                                                Ext.getCmp('region_grid').getStore().reload();
                                            } else {
                                                globalObject.msgTip(res.message);
                                            }
                                        }
                                    },
                                    failure: function (response) {
                                        window.getEl().unmask();
                                        globalObject.errTip('操作失败！');
                                    }
                                });
                            }
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-cancel',
                        width: 80,
                        handler: function () {
                            this.up('window').close();
                        }
                    }]
                }]
            });
            App.RegionManagementWindow.superclass.constructor.call(this, config);
        }
    });
    Ext.define('Forestry.app.manage.CompanyManage',{
        extend: 'Ext.grid.Panel',
        region: 'center',
        initComponent: function (){
            var me = this;
            Ext.define('ModelList', {
                extend: 'Ext.data.Model',
                idProperty: 'company_id',
                fields: [{
                    name: 'company_id'
                }, 'company_name','region_id','region_name']
            });
            var store = Ext.create('Ext.data.Store', {
                model: 'ModelList',
                remoteSort: true,
                pageSize: globalPageSize,
                proxy: {
                    type: 'ajax',
                    url: appBaseUri + '/spring/account/getCompanyList',
                    extraParams: me.extraParams || null,
                    reader: {
                        type: 'json',
                        root: 'data',
                        totalProperty: 'totalRecord',
                        successProperty: "success"
                    }
                },
                sorters: [{
                    property: 'company_id',
                    direction: 'DESC'
                }]
            });
            var rownum = new Ext.grid.RowNumberer({
                header: '序号',
                flex: 0.05,
                align: 'center'
            });
            var columns = [rownum, {
                text: "ID",
                xtype: "hidden",
                dataIndex: 'company_id',
                flex: 0.05
            }, {
                text: "地区名称",
                align: 'center',
                dataIndex: 'region_name',
                flex: 0.6
            },{
                text: "公司名称",
                align: 'center',
                dataIndex: 'company_name',
                flex: 0.6
            }, {
                text: '操作项', dataIndex: 'oper', sortable: false, flex: 0.20,
                renderer: function (value, cellmeta, record, rowIndex, columnIndex, store) {
                    return "<span class='tip_info' onclick='Ext.spring.dataConfiguration.manage.CompanyManage.region_edit()'>修改</span>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "<span class='tip_danger' " +
                        "onclick='Ext.spring.dataConfiguration.manage.CompanyManage.region_del()'>删除</span>";
                }
            }];
            var ttoolbar = Ext.create('Ext.toolbar.Toolbar', {
                items: [{
                    xtype: 'textfield',
                    id: 'company_name_query',
                    name: 'company_name_query',
                    hideLabel: true,
                    fieldLabel: '公司名称',
                    maxLength: 100,
                    emptyText: '请输入公司'
                }, {
                    xtype: 'button',
                    text: '搜索',
                    iconCls: 'icon-search',
                    width: '15%',
                    maxWidth: 60,
                    handler: function (btn, eventObj) {
                        var searchParams = {
                            company_name: Ext.getCmp('company_name_query').getValue()
                        };
                        Ext.apply(store.proxy.extraParams, searchParams);
                        store.reload();
                    }
                }, '->',
                    {
                        xtype: 'button',
                        text: '新增公司',
                        iconCls: 'icon-add',
                        width: '25%',
                        maxWidth: 100,
                        handler: function (btn, eventObj) {
                            var win = new App.CompanyManagementWindow({
                                hidden: true
                            });
                            var form = win.down('form').getForm();
                            form.findField("item_type").setValue("add");
                            win.show();
                        }
                    }

                ]
            });
            Ext.apply(this, {
                id: 'company_grid',
                store: store,
                columns: columns,
                tbar: ttoolbar,
                bbar: Ext.create('Ext.PagingToolbar', {
                    store: store,
                    displayInfo: true
                })
            });
            store.loadPage(1);
            this.callParent(arguments);
        }
    });
    // 修改的按钮函数
    Ext.spring.dataConfiguration.manage.CompanyManage.region_edit = function () {
        var record = Ext.getCmp('region_grid').getSelectionModel().getLastSelected();
        //以window的名字.DeptManagementWindow
        var win = new App.RegionManagementWindow({
            hidden: true
        });
        var form = win.down('form').getForm();
        form.loadRecord(record);
        form.findField("item_type").setValue("edit");
        regionIsCreate = true;
        win.show();
    }
    // //删除的按钮函数
    Ext.spring.dataConfiguration.manage.CompanyManage.region_del = function () {
        var record = Ext.getCmp('region_grid').getSelectionModel().getLastSelected();
        globalObject.confirmTip('请确认，是否执行删除操作？', function(btn) {
            if (btn == 'yes') {
                var id = record.get('region_id');
                Ext.Ajax.request({
                    url : appBaseUri + '/spring/account/delRegion',
                    params : {
                        region_id : id
                    },
                    success : function(response) {
                        if (response.responseText != '') {
                            var res = Ext.JSON.decode(response.responseText);
                            if (res.success) {
                                globalObject.msgTip('操作成功！');
                                Ext.getCmp('region_grid').getStore().reload();
                            } else {
                                globalObject.errTip('操作失败！' + res.msg);
                            }
                        }
                    }
                });
            }
        });
    }
});