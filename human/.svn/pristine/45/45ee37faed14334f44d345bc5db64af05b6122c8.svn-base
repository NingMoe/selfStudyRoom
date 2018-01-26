package com.human.nologin.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.binding.entity.WechatTeacherBinding;
import com.human.binding.service.WechatBindingService;
import com.human.front.entity.MenuDept;
import com.human.front.entity.MenuUser;
import com.human.front.entity.WxTeacherMenu;
import com.human.front.entity.WxTeacherModule;
import com.human.front.service.WxTeacherMenuService;
import com.human.utils.BindingConstants;
import com.human.utils.Common;
import com.human.utils.Constants;
import com.human.utils.QRCodeUtil;

@Controller
@RequestMapping("/wechat/binding/")
public class WxTeacherController {
	
	private final  Logger logger = LogManager.getLogger(WxTeacherController.class);
	
	@Value("${oss.fileurl}")
    private  String fileurl;
	
	@Value("${humanServer}")
	private  String humanServer;
	
	
	@Autowired
    private WxTeacherMenuService menuService;
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value="toTeacherCenter")
	public ModelAndView toTeacherCenter(HttpServletRequest request,HttpServletResponse response){
	    HttpSession session = request.getSession();
        String email = (String)session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        logger.info(email+"进来的时间:"+System.currentTimeMillis());
        logger.info("进入教师中心");
	    ModelAndView mav = new ModelAndView("/nologin/teacher_center");
	    List<WxTeacherModule> modules = menuService.getAllModules();
	    List<WxTeacherMenu> allMenus = menuService.getAllMenus(email);
	    Map<String,List<WxTeacherMenu>> map = new HashMap<String,List<WxTeacherMenu>>();
	    for(WxTeacherModule mo:modules){
	        String key =  mo.getName()+"-"+mo.getSort();
	        String moName = mo.getName();
	        for(WxTeacherMenu m:allMenus){
	            if(m.getCategory().equals(moName)){
	                Object o = map.get(key);
	                if(o==null){
	                    List<WxTeacherMenu> nmList = new ArrayList<WxTeacherMenu>();
	                    nmList.add(m);
	                    map.put(key, nmList);
	                }else{
	                    List<WxTeacherMenu> nmList = (List<WxTeacherMenu>)o;
                        nmList.add(m);
                        map.put(key, nmList);
	                }
	            }
	        }
	    }
	    
	    List<Map.Entry<String,List<WxTeacherMenu>>> list = new ArrayList<Map.Entry<String,List<WxTeacherMenu>>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,List<WxTeacherMenu>>>() {
           @Override
            public int compare(Entry<String, List<WxTeacherMenu>> o1, Entry<String, List<WxTeacherMenu>> o2) {
                String key1= o1.getKey();
                String key2= o2.getKey();
                return Integer.valueOf(key1.substring(key1.indexOf("-")+1)).compareTo(Integer.valueOf(key2.substring(key2.indexOf("-")+1)))  ;
            }
        });
        logger.info(email+"出来的时间:"+System.currentTimeMillis());
	    mav.addObject("menuMap", list);
	    mav.addObject("fileurl", fileurl);
        return mav;
        
    }
	
	
	
	/**
	 * 进入内推页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="toInsideUrl")
    public ModelAndView toInsideRe(HttpServletRequest request){
	    HttpSession session = request.getSession();
        String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        String indexUrl = humanServer+"front/home/jump.html";
        String enrollUrl= indexUrl+"?"+Constants.NEITUIREN+"="+email_addr;//二维码链接;
        ModelAndView mav = new ModelAndView("/nologin/inside_url");
        mav.addObject("enrollUrl", enrollUrl);
        return mav;
        
    }
	
	@ResponseBody
    @RequestMapping(value="getInsideQr")
    public void payByQRCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
        logger.info("生成二维码");
        try{
            String enrollUrl = request.getParameter("enrollUrl");
            BufferedImage bufferedImage=QRCodeUtil.createImage(enrollUrl);
            //生成二维码QRCode图片  
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());    
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
	
	private boolean isHasPrivilege(WechatTeacherBinding wtb,WxTeacherMenu menu){
	    if(menu.getFilter().equals("1")){
	        return true;
	    }
	    if(menu.getFilter().equals("2")){
	        List<MenuDept> depts = menuService.getDeptsByMenuId(menu.getId());
	        for(MenuDept md:depts){
	            if(md.getDeptId().equals(wtb.getDept())){
	                return true;
	            }
	        }
        }
	    if(menu.getFilter().equals("3")){
            List<MenuUser> users = menuService.getUsersByMenuId(menu.getId());
            for(MenuUser md:users){
                if(md.getUserId().equals(wtb.getEmail_addr())){
                    return true;
                }
            }
        }
	    return false;
	}
}
