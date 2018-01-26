package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsBookInfo;

public interface IeltsBookInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsBookInfo record);

    int insertSelective(IeltsBookInfo record);

    IeltsBookInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsBookInfo record);

    int updateByPrimaryKey(IeltsBookInfo record);

    /**
     * 初始化教材
     * @return
     */
    public List<IeltsBookInfo> selectAllBook();

    /**
     * 分页获取教材信息
     * @param map
     * @return
     */
    public List<IeltsBookInfo> query(Map<Object, Object> map);

    /**
     * 学员id获取
     * @param student_id
     * @return
     */
    public List<IeltsBookInfo> selectByStudentId(Integer student_id);

    /**
     * 通过书籍名称获取信息
     * @param mapparams
     * @return
     */
    public IeltsBookInfo selectByBookName(Map<String, Object> mapparams);
}