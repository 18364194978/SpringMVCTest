package com.lanyus.authority.entity;

/**
 * Created by Administrator on 2018/2/17 0017.
 */
public class Authority {
    private String menu_id;
    private String buttons;
    private String checked;
    private String expanded;
    private String icon_cls;
    private String leaf;
    private String menu_code;
    private String menu_name;
    private String parent_id;
    private String sort_order;
    private String url;
    private String is_select;

    public String getMenu_id() {
        return menu_id;
    }
    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }
    public String getButtons() {
        return buttons;
    }
    public void setButtons(String buttons) {
        this.buttons= buttons;
    }
    public String getChecked() {
        return checked;
    }
    public void setChecked(String checked) {
        this.checked= checked;
    }
    public String getExpanded() {
        return expanded;
    }
    public void setExpanded(String expanded) {
        this.expanded= expanded;
    }
    public String getIcon_cls() {
        return icon_cls;
    }
    public void setIcon_cls(String icon_cls) {
        this.icon_cls = icon_cls;
    }
    public String getLeaf() {
        return leaf;
    }
    public void setLeaf(String leaf) {
        this.leaf= leaf;
    }
    public String getMenu_code() {
        return menu_code;
    }
    public void setMenu_code(String menu_code) {
        this.menu_code= menu_code;
    }
    public String getMenu_name() {
        return menu_name;
    }
    public void setMenu_name(String menu_name) {
        this.menu_name= menu_name;
    }
    public String getParent_id() {
        return parent_id;
    }
    public void setParent_id(String parent_id) {
        this.parent_id= parent_id;
    }
    public String getSort_order() {
        return sort_order;
    }
    public void setSort_order(String order) {
        this.sort_order= sort_order;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url= url;
    }
    public String getIs_select() {
        return is_select;
    }
    public void setIs_select(String is_select) {
        this.is_select= is_select;
    }
}
