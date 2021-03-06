package com.lanyus.account.service.Impl;

import com.lanyus.account.service.AccountService;
import com.lanyus.account.dao.AccountMapper;
import com.lanyus.account.entity.Region;
import com.lanyus.account.entity.Company;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
@Service
public class AccountServiceImpl implements  AccountService{

    @Resource AccountMapper accountMapper;

    @Override
    public List<Region> finRegionList(Map<String,Object> param){
//        if(!"1".equals(param.get("user_id"))){
//            UUser uuser=usersMapper.select_userById(param);
//
//            param.put("company_id",Tools.isEmpty(uuser.getCompany_id())?"xxxx":uuser.getCompany_id());
//        }
        return accountMapper.getRegionList(param);
    }
    @Override
    public List<Region> checkRegionList(Map<String,Object> param){
        return accountMapper.checkRegionList(param);
    }
    @Override
    public int findRegionCount(Map<String,Object>param){
        //        if(!"1".equals(param.get("user_id"))){
//            UUser uuser=usersMapper.select_userById(param);
//
//            param.put("company_id",Tools.isEmpty(uuser.getCompany_id())?"xxxx":uuser.getCompany_id());
//        }
        return accountMapper.getRegionCount(param);
    }
    @Override
    public void addRegion(Map<String,Object> region){
        accountMapper.addRegion(region);
    }
    @Override
    public void editRegion(Map<String,Object> region){
        accountMapper.editRegion(region);
    }
    @Override
    public void delRegion(Map<String,Object> region){
        accountMapper.delRegion(region);
    }
    @Override
    public List<Company> getCompanyList(Map<String,Object> param){
        return accountMapper.getCompanyList(param);
    }
    @Override
    public int getCompanyCount(Map<String,Object>param){
        return accountMapper.getCompanyCount(param);
    }

}
