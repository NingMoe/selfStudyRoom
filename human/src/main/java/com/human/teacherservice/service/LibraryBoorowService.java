package com.human.teacherservice.service;

import java.util.List;
import com.human.teacherservice.entity.LibBorrowList;
import com.human.teacherservice.entity.LibBorrowListDto;
import com.human.utils.PageView;

public interface LibraryBoorowService {

    /**
     * 分页获取借阅记录
     * @param libBorrowList
     * @param pageView
     * @return
     */
    PageView query(LibBorrowList libBorrowList, PageView pageView);

    /**
    * 查找已超时并未归还的图书(用于邮件发送)
    * @param map
    * @return
    */
    List<LibBorrowListDto> selectByBorrowTime(String borrowTime);
    
    /**
     * 图书馆提醒功能
     */
    void sendNodifyMailAndSms();
   
}
