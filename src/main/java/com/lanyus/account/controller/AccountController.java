package com.lanyus.account.controller;

import core.CommenBaseController;
import com.lanyus.account.entity.Dept;
import com.lanyus.account.entity.Company;
import com.lanyus.account.entity.Region;
import core.util.IDUtils;
import com.lanyus.account.service.AccountService;
import core.extjs.ExtJSBaseParameter;
import core.extjs.ListView;

import com.lanyus.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2018/2/24.
 */
@Controller
@RequestMapping("/spring/account")
public class AccountController extends CommenBaseController {
    @Resource
    private AccountService accountService;

    /**
     * 地区列表读取
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getRegionList")
    @ResponseBody
    public void getRegionList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer firstResult = Integer.valueOf(request.getParameter("start"));
        Integer maxResults = Integer.valueOf(request.getParameter("limit"));
        String region_name = request.getParameter("region_name");
        List<Region> resultList = new ArrayList();
        Map<String, Object> param = new HashMap<String, Object>();
        if (Tools.isEmpty(region_name)) {
            param.put("region_name", region_name);
        }
        int resultCount = accountService.findRegionCount(param);
        param.put("start", firstResult);
        param.put("limit", maxResults);
        resultList = accountService.finRegionList(param);
        ListView<Region> listView = new ListView<Region>();
        listView.setData(resultList);
        listView.setTotalRecord(resultCount);
        writeJSON(response, listView);
    }

    /**
     * 新增/编辑部门
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/saveCreateRegion")
    public void saveUpdateDept( HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExtJSBaseParameter parameter = new ExtJSBaseParameter();
        /*首先查看是否该单位下有无该部门是否已存在*/
        Map<String, Object> param = new HashMap<>();
        String regionname = request.getParameter("region_name");
        param.put("region_name", regionname);
        String isCreate = request.getParameter("isCreate");
        List<Region> exist = accountService.checkRegionList(param);
        if (exist.size() > 0) {
            parameter.setSuccess(false);
            parameter.setMessage("该地区已存在，请确认后重新输入");
            writeJSON(response, parameter);
            return;
        }
        Map<String, Object> param2 = new HashMap<>();
        String region_name = request.getParameter("region_name");
        String uuid = IDUtils.uuid();
        param2.put("region_name",region_name);
        param2.put("region_id",uuid);
        accountService.addRegion(param2);
        parameter.setSuccess(true);
        writeJSON(response, parameter);
    }
}
