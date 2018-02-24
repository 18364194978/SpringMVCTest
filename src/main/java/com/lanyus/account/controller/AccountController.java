package com.lanyus.account.controller;

import core.CommenBaseController;
import com.lanyus.account.entity.Dept;
import com.lanyus.account.entity.Company;
import com.lanyus.account.entity.Region;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2018/2/24.
 */
@Controller
@RequestMapping("/spring/account")
public class AccountController extends CommenBaseController {

    /**
     * 地区列表读取
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getRegionList")
    @ResponseBody
    public void getRegionList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer firstResult = Integer.valueOf(request.getParameter("start"));
        Integer maxResults = Integer.valueOf(request.getParameter("limit"));

//        Map<String, Object> param = new HashMap<>();
//        param.put("dept_name", dept_name);
//        int count = deptService.findDeptCount(param);
//        param.put("start", firstResult);
//        param.put("limit", maxResults);
//
//        param.put("user_id", ""+ ((UUser) request.getSession().getAttribute(
//                SESSION_SYS_USER)).getUser_id());
//
//
//        List<UDept> deptList = deptService.findPage(param);
//        ListView<UDept> forestryListView = new ListView<>();
//        forestryListView.setData(deptList);
//        forestryListView.setTotalRecord((long)count);
//        writeJSON(response, forestryListView);
    }
}
