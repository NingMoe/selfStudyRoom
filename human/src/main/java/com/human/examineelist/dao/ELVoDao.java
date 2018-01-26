package com.human.examineelist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.examineelist.entity.ELVo;
import com.human.examineelist.entity.ExamineeList;
import com.human.examineelist.entity.ExamineeList2;
import com.human.stuexam.entity.StuExam;
import com.human.utils.PageView;

@Repository
public interface ELVoDao {
    
    String queryTime(ELVo data);
    
    int insertInto(ELVo data);

    

}