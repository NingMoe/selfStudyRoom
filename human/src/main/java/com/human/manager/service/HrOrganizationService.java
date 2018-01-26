package com.human.manager.service;

import java.util.List;

import com.human.manager.entity.HrOrganization;
import com.human.utils.PageView;

public interface HrOrganizationService {
    public PageView findAllOrg(PageView pageView, HrOrganization hrOrganization);
    
    public List<HrOrganization> findOrgByCondition(HrOrganization hrOrganization);
    
    public List<HrOrganization> getOrgsByCompanyIdFromAd(String companyId);
    
    public void refreshOrgsByCompanyId(String companyId);
    
}
