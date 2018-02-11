package cn.xdf.selfStudyRoom.web.manager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.xdf.selfStudyRoom.domain.entity.Resources;
import cn.xdf.selfStudyRoom.service.ResourcesService;
import cn.xdf.selfStudyRoom.utils.CommonUtil;


/**
 * 资源管理
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/manager/resource/")
public class ResourcesController {
	
	private  final  Logger logger = LogManager.getLogger(ResourcesController.class);
	
	@Resource
	private ResourcesService resourceService;
	
	/**
     * 进入资源管理页面
     * @return
     */
    @GetMapping("/index")
    public String index(){       
        return "/view/manager/resources/index";
    }
    
        
    /**
     * 查询资源
     * @return
     */
    @RequestMapping(value="queryResource")
    @ResponseBody
    public List<Resources> queryResource(){
    	logger.info("查询资源");
        return  resourceService.findAll();
    }
    
    /**
     * 跳到新增页面
     * @return
     */
    @RequestMapping(value = "addUI")
    public String addUI(Resources resources,Model model) {
    	model.addAttribute("resource", resources);
        return "/view/manager/resources/add";
    }
    
    
    /**
     * 保存新增
     * @param model
     * @param role
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Map<String, Object> add(Resources resources) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Resources> oldResource = resourceService.selectByKey(resources);
            if (oldResource.size()>0) {
                map.put("flag", false);
                map.put("message", "对不起，资源key重复，请重新输入!");
            }else {
                Long userId=CommonUtil.getMyUser().getUserId();
                resources.setCreateUserId(userId);
                resourceService.add(resources);
                map.put("flag", true);
                map.put("message", "资源添加成功!");
            }
        }catch (Exception e) {
            logger.error("Manager role is add error !", e);
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
    
    
    /**
     * 进入修改页面
     * @param resources
     * @return
     */
    @RequestMapping(value="toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav = new ModelAndView("/view/manager/resources/edit");
        Resources resource = resourceService.getById(id);
        mav.addObject("resource", resource);
        return mav;
    }
    
    /**
     * 更新修改的信息
     * @param model
     * @param resources
     * @return
     */
    @RequestMapping(value="update")
    @ResponseBody
    public Map<String, Object> updateResources(Resources resource){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            resource.setUpdateUserId(CommonUtil.getMyUser().getUserId());
            resourceService.modify(resource);
            map.put("flag", true);
            map.put("message", "资源编辑成功!");
        } catch (Exception e) {
            logger.error("Manager role is update error !", e);
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 删除
     * @param model
     * @param videoTypeId
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> deleteById(Resources resource) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            resource.setUpdateUserId(CommonUtil.getMyUser().getUserId());
            map.put("flag", true);
            map.put("message", "删除成功！");
            resourceService.delete(resource);
        } catch (Exception e) {
            map.put("flag", false);
            map.put("message", "删除失败！");
            logger.error("Manager resources delete id error ", e);
        }
        return map;
    }
    
}
