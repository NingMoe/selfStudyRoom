package com.human.manager.service;

import java.util.List;

import com.human.manager.entity.HrUser;

public interface HrUserService {
    public List<HrUser> getUsersByCompanyIdFromAd(String companyId);
    
    public void refreshUsersByCompanyId(String companyId);
    
}
