package com.human.manager.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;

@Controller
@RequestMapping("/manager/hrOrg/")
public class HrOrganizationController {

    @Resource
    private UserDeptService udService;
    
    @RequestMapping("getUserOrg")
    @ResponseBody
    public List<HrOrganization> getUserOrg(String companyId) {
        UserDept ud =new UserDept();
        ud.setUserId(Common.getMyUser().getUserid());
        ud.setCompanyId(companyId);
        return udService.getUserOrg(ud);
    }
}
