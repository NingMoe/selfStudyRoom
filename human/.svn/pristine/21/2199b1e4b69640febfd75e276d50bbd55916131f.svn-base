package com.human.bpm.dao;

import java.util.List;

import com.human.bpm.entity.ActCustomComment;

public interface ActCustomCommentDao {
    
    void insert(ActCustomComment record);

    int updateByPrimaryKeySelective(ActCustomComment record);
    
    ActCustomComment getLoseComment(String flowCode); 
    
    List<ActCustomComment> getLoseNodeComments(String flowCode,String loseNode); 

}