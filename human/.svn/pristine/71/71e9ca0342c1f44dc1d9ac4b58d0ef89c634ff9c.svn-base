package com.human.teacherservice.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.teacherservice.entity.LibBorrowList;
import com.human.teacherservice.entity.LibBorrowListDto;

@Repository
public interface LibBorrowListDao {
    int deleteByPrimaryKey(Integer borrow_id);

    int insert(LibBorrowList record);

    int insertSelective(LibBorrowList record);

    LibBorrowList selectByPrimaryKey(Integer borrow_id);

    int updateByPrimaryKeySelective(LibBorrowList record);

    int updateByPrimaryKey(LibBorrowList record);

    /**
     * 分页获取借阅记录
     * @param map
     * @return
     */
    public List<LibBorrowList> query(Map<Object, Object> map);

    /**
     * 获取看的最多的书
     * @param school_id
     * @return
     */
    public List<LibBorrowList> getBookCountBySchoolId(String school_id);
    
    /**
     * 获取看书最多的人
     * @param school_id
     * @return
     */
    public List<LibBorrowList> getPeopleCountBySchoolId(String school_id);

    /**
     * 获取用户借阅记录
     * @param school_id
     * @param valueOf
     * @return
     */
    public List<LibBorrowList> selectByUserId(Map<String, Object> map);
    
    /**
     * 获取正在借阅的书籍
     * @param school_id
     * @param valueOf
     * @return
     */
    public List<LibBorrowList> selectByOnTime(Map<String, Object> map);
    
    /**
     * 查找已超时并未归还的图书(用于邮件发送)
     * @param map
     * @return
     */
    public List<LibBorrowListDto> selectByBorrowTime(String borrowTime);
    
    /**
     * 查找已超时并未归还的图书(用于短信发送)
     * @param map
     * @return
     */
    public List<LibBorrowListDto> selectForSmsByBorrowTime(String borrowTime);
    
}