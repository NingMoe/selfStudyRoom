package com.human.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.human.activity.entity.Product;

@Repository
public interface ProductDao {

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);
    /**
     * 通过活动Id查询商品
     * @param activtiyId
     * @return
     */
    List<Product> selectByActivtiyId(Long activtiyId);

}