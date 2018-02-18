package com.lanyus.authority.controller;

import core.CommenBaseController;
import com.lanyus.authority.service.AuthorityService;
import com.lanyus.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/17 0017.
 */
@Controller
@RequestMapping("/spring/sys/authority")
public class AuthorityController extends CommenBaseController{

    @Resource
    private AuthorityService authorityService;

    @RequestMapping("/getAuthority")
    public void getAuthority(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String globalRoleId = request.getParameter("globalRoleId");
        String node = request.getParameter("node");
        if (!"root".equals(node)) {
            return;
        }
        List resultList = new ArrayList();
        if (Tools.isNotEmpty(globalRoleId)) {
            String[] role_ids = globalRoleId.split(",");
            resultList = authorityService.getMenuList(role_ids);
        }
        writeJSON(response, resultList);
    }

}
