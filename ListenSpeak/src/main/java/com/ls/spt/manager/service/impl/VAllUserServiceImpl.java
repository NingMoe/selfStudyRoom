package com.ls.spt.manager.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ls.spt.manager.dao.VAllUserDao;
import com.ls.spt.manager.entity.VAllUser;
import com.ls.spt.manager.service.VAllUserService;

@Service
public class VAllUserServiceImpl implements VAllUserService {
    
    @Resource
    private VAllUserDao vAllUserDao;

    /**
     * 验证手机号是否正确
     * @param vau
     * @return
     */
    public VAllUser queryUser(VAllUser vau) {
        if (vau==null || StringUtils.isEmpty(vau.getPhone())) {
            return null;
        }
        
        try {
            VAllUser vAllUser = vAllUserDao.queryUser(vau);
            return vAllUser;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
