package com.human.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.basic.entity.MailParam;
import com.human.basic.entity.MailTem;

@Repository
public interface MailTempDao {

    /**
     * 分页查询邮件模版
     * @param map
     * @return
     */
    List<MailTem> query(Map<String, Object> map);

    /**
     * 查询模版参数
     * @param sp
     * @return
     */
    List<MailParam> queryParam(MailParam mp);

    /**
     * 保存邮件模版
     * @param st
     * @return
     */
    int add(MailTem st);

    /**
     * 根据主键ID获取邮件模版
     * @param id
     * @return
     */
    MailTem queryById(Long id);

    /**
     * 编辑邮件模版
     * @param st
     * @return
     */
    int edit(MailTem st);

    /**
     * 禁用邮件模版
     * @param paraMap
     * @return
     */
    int delTemp(Map<String, Object> paraMap);


}
