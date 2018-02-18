/**
 * 主布局
 */
Ext.Ajax.timeout = 60000;
// 使用动态加载组件
Ext.Loader.setConfig({
    enabled : true
});
// Ext.require:用到哪些组件,然后就预先加载,多余不用加载的组件
Ext.require([ 'Ext.util.History', 'Ext.ux.statusbar.StatusBar', 'Ext.app.PortalPanel', 'Ext.ux.TabScrollerMenu', 'Ext.state.*', 'Ext.window.MessageBox', 'Ext.tip.*' ]);

var mainTab, globalPageSize = 20, // 全局分页大小
    globalDateColumnWidth = 160;// 全局时间列宽度

Ext.onReady(function() {
    // 初始化QuickTips就是为了使带有data-qtip属性的html标签能够在鼠标移上去的时候显示其内容。作用类似于HTML标签的title的功能。
    Ext.QuickTips.init();
    // History浏览器前进后退功能
    Ext.History.init();
    var tokenDelimiter = ':';
    // var mainPortal = Ext.create('Forestry.app.process.MyTask', {暂时屏蔽
    //     id : 'tab44',
    //     title : "待处理消息",
    //     layout : 'fit',
    //     autoScroll : true
    // });

    // mainTab = Ext.create('Ext.TabPanel', {暂时屏蔽
    //     region : 'center',
    //     margins : '2 0 0 0',
    //     deferredRender : false,
    //     activeTab : 0,
    //     plugins : Ext.create('Ext.ux.TabCloseMenu', {
    //         closeTabText : '关闭面板',
    //         closeOthersTabsText : '关闭其他',
    //         closeAllTabsText : '关闭所有'
    //     }),
    //     // items : [ mainPortal ],暂时屏蔽
    //     listeners : {
    //         tabchange : onTabChange,
    //         afterrender : onAfterRender
    //     }
    // });

    var menuTreeStore = Ext.create('Ext.data.TreeStore', {
        // autoLoad : true,
        proxy : {
            type : 'ajax',
            url : appBaseUri + '/spring/sys/authority/getAuthority?globalRoleId=' + role_ids,
            reader : {
                type : 'json',
                root : 'children'
            }
        }
    });
    // console.log(menuTreeStore,0)
    menuTreeStore.load({
        callback:function (records,options,success) {
            console.log(records,options,success)
        }
    })

    var treeFilterField = Ext.create('Ext.form.field.Trigger', {
        width : '100%',
        emptyText : '功能查找...',
        trigger1Cls : 'x-form-clear-trigger',
        onTrigger1Click : function() {
            treeFilterField.setValue('');
            menuTreeStore.clearFilter(true);
        },
        listeners : {
            'keyup' : {
                element : 'el',
                fn : function(e) {
                    var regex = new RegExp(Ext.String.escapeRegex(treeFilterField.getValue()), 'i');
                    menuTreeStore.clearFilter(true);
                    menuTreeStore.filter(new Ext.util.Filter({
                        filterFn : function(item) {
                            return regex.test(item.get('text'));
                        }
                    }));
                }
            }
        }
    });

    var treePanel = Ext.create('Ext.tree.Panel', {
        id : 'menuTree',
        region : 'west',
        split : true,
        title : '功能导航',
        width : 220,
        stateId : 'appnav',
        stateful : true,
        margins : '2 0 0 0',
        collapsible : true,
        animCollapse : true,
        xtype : 'treepanel',
        rootVisible : false,
        store : menuTreeStore,
        // tbar : [ treeFilterField ],
        listeners : {
            'itemclick' : function(e, record) {
                if (record.data.leaf) {
                    globalObject.openTab(record.data.id, record.data.text, record.raw.url, {
                        cButtons : record.raw.buttons ? record.raw.buttons.split(',') : [],
                        cName : record.raw.menuCode,
                        cParams : record.raw.menuConfig
                    });
                }
            }
        }
    });

    var viewport = Ext.create('Ext.Viewport', {
        layout : 'border',
        items : [ {
            region : 'north',
            xtype : 'container',
            height : 50,
            id : 'app-header',
            layout : {
                type : 'hbox',
                align : 'middle'
            },
            defaults : {
                xtype : 'component'
            },
            items : [ {
                html : '<img src = ' + appBaseUri + '/static/img/logo.png width="150" height="45" />',
                width : 150
            }, {
                id : 'app-header-title',
                html : appName,
                width : 250
            }, {
                html : '欢迎您，' + 'admin',
                style : 'text-align:center;font-size:14px;',
                flex : 1
            }, {
                width : 180
            }, {
                width : 120,
                xtype : 'button',
                text : '个人中心',
                icon : appBaseUri + '/static/ext/examples/shared/icons/fam/user.gif',
                menu : [ {
                    text : '修改密码',
                    iconCls : 'icon_key',
                    handler : function() {
                        globalObject.openWindow('修改密码', 'profile.ChangePassword', 300);
                    }
                }/*, '-', {
				 text : '切换主题',
				 handler : function() {
				 function getQueryParam(name, queryString) {
				 var match = RegExp(name + '=([^&]*)').exec(queryString || location.search);
				 return match && decodeURIComponent(match[1]);
				 }

				 function hasOption(opt) {
				 var s = window.location.search;
				 var re = new RegExp('(?:^|[&?])' + opt + '(?:[=]([^&]*))?(?:$|[&])', 'i');
				 var m = re.exec(s);

				 return m ? (m[1] === undefined ? true : m[1]) : false;
				 }

				 var scriptTags = document.getElementsByTagName('script'), defaultTheme = 'neptune', defaultRtl = false, i = scriptTags.length, requires = [ 'Ext.toolbar.Toolbar', 'Ext.form.field.ComboBox', 'Ext.form.FieldContainer', 'Ext.form.field.Radio'

				 ], defaultQueryString, src, theme, rtl;

				 while (i--) {
				 src = scriptTags[i].src;
				 if (src.indexOf('include-ext.js') !== -1) {
				 defaultQueryString = src.split('?')[1];
				 if (defaultQueryString) {
				 defaultTheme = getQueryParam('theme', defaultQueryString) || defaultTheme;
				 defaultRtl = getQueryParam('rtl', defaultQueryString) || defaultRtl;
				 }
				 break;
				 }
				 }

				 Ext.themeName = theme = getQueryParam('theme') || defaultTheme;

				 rtl = getQueryParam('rtl') || defaultRtl;

				 if (rtl.toString() === 'true') {
				 requires.push('Ext.rtl.*');
				 Ext.define('Ext.GlobalRtlComponent', {
				 override : 'Ext.AbstractComponent',
				 rtl : true
				 });
				 }

				 Ext.require(requires);

				 Ext.getBody().addCls(Ext.baseCSSPrefix + 'theme-' + Ext.themeName);

				 if (Ext.isIE6 && theme === 'neptune') {
				 Ext.Msg.show({
				 title : 'Browser Not Supported',
				 msg : 'The Neptune theme is not supported in IE6.',
				 buttons : Ext.Msg.OK,
				 icon : Ext.Msg.WARNING
				 });
				 }

				 if (hasOption('nocss3')) {
				 Ext.supports.CSS3BorderRadius = false;
				 Ext.getBody().addCls('x-nbr x-nlg');
				 }
				 function setParam(param) {
				 var queryString = Ext.Object.toQueryString(Ext.apply(Ext.Object.fromQueryString(location.search), param));
				 location.search = queryString;
				 }

				 function removeParam(paramName) {
				 var params = Ext.Object.fromQueryString(location.search);

				 delete params[paramName];

				 location.search = Ext.Object.toQueryString(params);
				 }

				 var toolbar = Ext.widget({
				 xtype : 'toolbar',
				 border : true,
				 rtl : false,
				 id : 'options-toolbar',
				 floating : true,
				 fixed : true,
				 preventFocusOnActivate : true,
				 draggable : {
				 constrain : true
				 },
				 items : [ {
				 xtype : 'combo',
				 rtl : false,
				 width : 170,
				 labelWidth : 45,
				 fieldLabel : '主题',
				 displayField : 'name',
				 valueField : 'value',
				 labelStyle : 'cursor:move;',
				 margin : '0 5 0 0',
				 store : Ext.create('Ext.data.Store', {
				 fields : [ 'value', 'name' ],
				 data : [ {
				 value : 'classic',
				 name : 'Classic'
				 }, {
				 value : 'gray',
				 name : 'Gray'
				 }, {
				 value : 'neptune',
				 name : 'Neptune'
				 } ]
				 }),
				 value : theme,
				 listeners : {
				 select : function(combo) {
				 var theme = combo.getValue();
				 if (theme !== defaultTheme) {
				 setParam({
				 theme : theme
				 });
				 } else {
				 removeParam('theme');
				 }
				 }
				 }
				 }, {
				 xtype : 'button',
				 rtl : false,
				 hidden : !(Ext.repoDevMode || location.href.indexOf('qa.sencha.com') !== -1),
				 enableToggle : true,
				 pressed : rtl,
				 text : 'RTL',
				 margin : '0 5 0 0',
				 listeners : {
				 toggle : function(btn, pressed) {
				 if (pressed) {
				 setParam({
				 rtl : true
				 });
				 } else {
				 removeParam('rtl');
				 }
				 }
				 }
				 }, {
				 xtype : 'tool',
				 type : 'close',
				 rtl : false,
				 handler : function() {
				 toolbar.destroy();
				 }
				 } ],

				 // Extra constraint margins within default constrain region of parentNode
				 constraintInsets : '0 -' + (Ext.getScrollbarSize().width + 4) + ' 0 0'
				 });
				 toolbar.show();
				 toolbar.alignTo(document.body, Ext.optionsToolbarAlign || 'tr-tr', [ (Ext.getScrollbarSize().width + 4) * (Ext.rootHierarchyState.rtl ? 1 : -1), -(document.body.scrollTop || document.documentElement.scrollTop) ]);

				 var constrainer = function() {
				 toolbar.doConstrain();
				 };

				 Ext.EventManager.onWindowResize(constrainer);
				 toolbar.on('destroy', function() {
				 Ext.EventManager.removeResizeListener(constrainer);
				 });
				 }
				 }*/, '-', {
                    text : '安全退出',
                    handler : function() {
                        top.location.href = appBaseUri + '/dnw/user/logout';
                    }
                } ]
            } ]
        }, treePanel,
            // mainTab,暂时屏蔽
            {
            region : 'south',
            border : false,
            items : [ Ext.create('Ext.ux.StatusBar', {
                border : false,
                text : '',
                style : 'background:#006569;',
                defaults : {
                    style : 'color:#fff;'
                },
                items : [ '->','版权所有&nbsp;&nbsp;2017&nbsp;&nbsp;','-','山东容弗新信息科技有限公司' , '->', '->' ]
            }) ]
        } ]
    });

    function onTabChange(tabPanel, tab) {
        var tabs = [], ownerCt = tabPanel.ownerCt, oldToken, newToken;

        tabs.push(tab.id);
        tabs.push(tabPanel.id);

        while (ownerCt && ownerCt.is('tabpanel')) {
            tabs.push(ownerCt.id);
            ownerCt = ownerCt.ownerCt;
        }

        newToken = tabs.reverse().join(tokenDelimiter);

        oldToken = Ext.History.getToken();

        if (oldToken === null || oldToken.search(newToken) === -1) {
            Ext.History.add(newToken);
        }
    }

    function onAfterRender() {
        Ext.History.on('change', function(token) {
            var parts, tabPanel, length, i;

            if (token) {
                parts = token.split(tokenDelimiter);
                length = parts.length;

                for (i = 0; i < length - 1; i++) {
                    Ext.getCmp(parts[i]).setActiveTab(Ext.getCmp(parts[i + 1]));
                }
            }
        });

        var activeTab1 = mainTab.getActiveTab(), activeTab2 = activeTab1;

        onTabChange(activeTab1, activeTab2);
    }
});

var globalObject = new Object();

// 打开tab
globalObject.openTab = function(tabId, tabTitle, tab, config) {
    // console.log(config);
    var _tab = mainTab.getComponent('tab' + tabId);
    if (!_tab) {
        mainTab.setLoading('Loading...');
        _tab = Ext.create('Ext.panel.Panel', {
            //closable : true,
            id : 'tab' + tabId,
            title : tabTitle,
            layout : 'fit',
            autoScroll : true,
            border : false,
            items : typeof (tab) == 'string' ? Ext.create('Forestry.app.' + tab, config) : tab
        });
        mainTab.removeAll();//2017-09-11修改为tab标签单开只显示一个
        mainTab.add(_tab);
        mainTab.setLoading(false);
    }
    mainTab.setActiveTab(_tab);
}

// 打开window
globalObject.openWindow = function(winTitle, win, winWidth, config) {
    Ext.create('Ext.window.Window', {
        autoShow : true,
        modal : true,
        title : winTitle,
        id : win,
        resizable : false,
        width : winWidth || 300,
        items : typeof (win) == 'string' ? Ext.create('Forestry.app.' + win, config) : win
    });
}

// 关闭tab
globalObject.closeTab = function(tabId) {
    var tab = mainTab.getActiveTab();
    tab.close();
    if (tabId != undefined) {
        mainTab.setActiveTab(tabId);
    }
};

// 刷新ActiveTab下的gridpanel
globalObject.listReload = function() {
    if (mainTab.getActiveTab().down('gridpanel'))
        mainTab.getActiveTab().down('gridpanel').getStore().reload();
}

// 成功提示
globalObject.msgTip = function(msg) {
    function createBox(t, s) {
        return '<div class="msg"><h3>' + t + '</h3><p>' + s + '</p></div>';
    }

    var msgCt;
    if (!msgCt) {
        msgCt = Ext.DomHelper.insertFirst(document.body, {
            id : 'msg-div'
        }, true);
    }
    var m = Ext.DomHelper.append(msgCt, createBox('系统信息', msg), true);
    m.hide();
    m.slideIn('t').ghost("t", {
        delay : 2000,
        remove : true
    });
};

// 错误提示
globalObject.errTip = function(msg, e) {
    Ext.MessageBox.show({
        title : '出错信息',
        msg : msg,
        buttons : Ext.MessageBox.OK,
        animateTarget : e,
        icon : Ext.MessageBox.ERROR
    });
};

// 一般提示
globalObject.infoTip = function(msg, e) {
    Ext.MessageBox.show({
        title : '系统信息',
        msg : msg,
        buttons : Ext.MessageBox.OK,
        animateTarget : e,
        icon : Ext.MessageBox.INFO
    });
};

// 选择性提示
globalObject.confirmTip = function(msg, fn, buttons, e) {
    Ext.MessageBox.show({
        title : '系统信息',
        msg : msg,
        buttons : buttons || Ext.MessageBox.YESNO,
        animateTarget : e,
        fn : fn
    });
};

// 控制台日志
globalObject.log = function(obj) {
    if (window.console) {
        console.log(obj);
    }
}

// 拥有指定权限
globalObject.haveAction = function(name) {
    return Ext.Array.contains(idata.myInfo.actions, name);
}

// 拥有指定按钮
globalObject.haveActionMenu = function(items, name) {
    if (items && items.length > 0)
        return Ext.Array.contains(items, name)
    return false;
}

// 拥有指定角色
globalObject.haveRole = function(name) {
    return Ext.Array.contains(idata.myInfo.roles, name);
}

// 执行指定Action
globalObject.run = function(url, params, itemStore) {
    Ext.Ajax.request({
        url : url,
        params : params,
        success : function(response) {
            if (response.responseText != '') {
                var res = Ext.JSON.decode(response.responseText);
                if (res.success) {
                    globalObject.msgTip('操作成功！');
                    if (itemStore)
                        itemStore.reload();
                } else
                    globalObject.errTip(res.msg);
            }
        }
    });
}

// 拥有指定列
globalObject.haveColumn = function(cName) {
    var columns = idata.myInfo.roleColumns[cName];
    this.have = function(columnName) {
        if (columns == undefined)
            return -2;
        return Ext.Array.contains(columns, columnName);
    }
}

function StringBuffer() {
    this._strings_ = new Array();
}

StringBuffer.prototype.append = function(str) {
    this._strings_.push(str);
};

StringBuffer.prototype.toString = function() {
    return this._strings_.join("");
};
/*时间格式化*/
globalObject.formatDate = function(time){
    if(Ext.isEmpty(time)){
        return "";
    }
    var date = new Date();
    date.setTime(time);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    return year + "-"
        + (month < 10?'0':'') + month + "-"
        + (day < 10?'0':'') + day + " "
        + (hour < 10?'0':'') + hour + ":"
        + (minute < 10?'0':'') + minute + ":"
        + (second < 10?'0':'') + second;
}

Ext.Ajax.on('requestexception', function(conn, response, options) {
    if (response.status == '999') {
        setTimeout(function(){
            //延时避免被failure回调函数中的aler覆盖
            top.Ext.MessageBox.alert('提示', '由于长时间未操作,空闲会话已超时;您将被强制重定向到登录页面!', function() {
                /*parent.location.href = '${contextPath}/login.jsp';*/
                parent.location.href = '../../login.jsp';
            });
        },200);
    }else if (response.status == '998') {
        setTimeout(function(){
            //延时避免被failure回调函数中的aler覆盖
            top.Ext.MessageBox.alert('提示', '您的会话连接由于在其它窗口上被注销而失效,系统将把您强制重定向到登录界面.', function() {
                /*parent.location.href = '${contextPath}/login.jsp';*/
                parent.location.href = '../../login.jsp';
            });
        },200);
    }else if (response.status == -1) {
        setTimeout(function(){
            //延时避免被failure回调函数中的aler覆盖
            top.Ext.MessageBox.alert('提示', '请求失败,超时或服务器无响应.', function() {
            });
        },200);
    }else{
        //parent.showException(response.responseText);
        top.Ext.MessageBox.alert('提示', '系统发生错误:'+response.status+'，请联系管理员.', function() {
        });
    }
});