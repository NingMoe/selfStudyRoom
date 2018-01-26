package com.human.activity.service;

import java.util.List;
import java.util.Map;
import com.human.activity.entity.Product;

public interface ProductService {
    /**
     * 通过活动Id查询商品
     * @param activtiyId
     * @return
     */
    List<Product> selectByActivtiyId(Long activtiyId);
    /**
     * 校验商品数目
     * @param productId
     * @param newTotal
     * @return
     */
    Map<String, Object> checkProductNumber(Long productId,Long newTotal);
}
