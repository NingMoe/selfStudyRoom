package com.human.ielts.dao;

import com.human.ielts.entity.IeltsStudentBook;

public interface IeltsStudentBookDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsStudentBook record);

    int insertSelective(IeltsStudentBook record);

    IeltsStudentBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsStudentBook record);

    int updateByPrimaryKey(IeltsStudentBook record);

    /**
     * 删除id关联的教材
     * @param id
     * @return
     */
    public int deleteByStudentId(Integer id);

    /**
     * 删除book_id关联的教材
     * @param id
     * @return
     */
    public int deleteByBookId(Integer id);
}