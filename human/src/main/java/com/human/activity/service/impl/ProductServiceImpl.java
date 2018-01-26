package com.human.activity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.human.activity.dao.ProductDao;
import com.human.activity.entity.Product;
import com.human.activity.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
    
    @Resource  
    private ProductDao  productDao ;

    @Override
    public List<Product> selectByActivtiyId(Long activtiyId) {      
        return productDao.selectByActivtiyId(activtiyId);
    }

    @Override
    public Map<String, Object> checkProductNumber(Long productId, Long newTotal) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Product product=productDao.selectByPrimaryKey(productId);
            Long saleTotal=product.getSaleTotal();
            //判断新商品数目数是不是0
            if(newTotal!=0){
                if(newTotal<saleTotal){
                    map.put("flag", false);
                    map.put("message", "新商品数目不能小于已购买的商品数目之和!"); 
                    return map;
                } 
            }
            map.put("flag", true);     
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "校验商品数目失败!");
        }
        return map;
    }
    
    
    
}
