package com.ls.spt.manager.dao;

import com.ls.spt.manager.entity.VAllUser;

public interface VAllUserDao {

    /**
     * 验证姓名手机号是否正确
     * @param vau
     * @return
     */
    public VAllUser queryUser(VAllUser vau);
}