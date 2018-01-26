package com.human.rank.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.rank.dao.RankClassesDao;
import com.human.rank.entity.RankClasses;
import com.human.rank.service.RankClassesService;
import com.human.utils.ExcelUtil;
import com.human.utils.HttpClientUtil;
import com.human.utils.PageView;

@Service
public class RankClassesServiceImpl implements RankClassesService {
    
    @Resource
    private RankClassesDao rankClassesDao;

    /**
     * 分页获取班级信息
     * @param page
     * @param rankClasses
     * @return
     */
    public PageView query(PageView page, RankClasses rankClasses) {
      //验证参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        
        try {
            map.put("paging", page); 
            map.put("t", rankClasses);
            
            List<RankClasses> list = rankClassesDao.query(map);
            page.setRecords(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 新增班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> insert(RankClasses rankClasses) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(rankClasses == null){
            map.put("flag", false);
            map.put("message", "班号不能为空");
            return map;
        }
        
        if(rankClasses.getRankinfo_id() == null){
            map.put("flag", false);
            map.put("message", "规则id不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(rankClasses.getClass_code())){
            map.put("flag", false);
            map.put("message", "班号不能为空");
            return map;
        }
        
        try {
            rankClassesDao.insertSelective(rankClasses);
            map.put("flag", true);
            map.put("message", "新增成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增班级异常："+e);
        }
        
        return map;
    }

    /**
     * 修改班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> update(RankClasses rankClasses) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(rankClasses == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        if(rankClasses.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(rankClasses.getClass_code())){
            map.put("flag", false);
            map.put("message", "班号不能为空");
            return map;
        }
        
        try {
            rankClassesDao.updateByPrimaryKeySelective(rankClasses);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改班级异常："+e);
        }
        
        return map;
    }

    /**
     * 查询班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> select(RankClasses rankClasses) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(rankClasses == null){
            map.put("flag", false);
            map.put("message", "班级id不能为空");
            return map;
        }
        
        if(rankClasses.getId() == null){
            map.put("flag", false);
            map.put("message", "班级id不能为空");
            return map;
        }
        
        try {
            RankClasses rankClasses1 = rankClassesDao.selectByPrimaryKey(rankClasses.getId());
            if(rankClasses1 == null){
                map.put("flag", false);
                map.put("message", "没有查询到班级信息");
            }else{
                map.put("flag", true);
                map.put("message", "查询成功");
                map.put("rankClasses", rankClasses1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询班级异常："+e);
        }
        
        return map;
    }

    /**
     * 删除班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> delete(RankClasses rankClasses) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(rankClasses == null){
            map.put("flag", false);
            map.put("message", "班级id不能为空");
            return map;
        }
        
        if(rankClasses.getId() == null){
            map.put("flag", false);
            map.put("message", "班级id不能为空");
            return map;
        }
        
        try {
            rankClassesDao.deleteByPrimaryKey(rankClasses.getId());
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除班级异常："+e);
        }
        return map;
    }

    /**
     * 批量删除班级
     * @param ids
     * @return
     */
    public Map<String, Object> deleteselect(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> list = new ArrayList<Integer>();
        String[] idarray = ids.split(",");
        try {
            for(String id :idarray){
                list.add(Integer.valueOf(id));
            }
            int i = rankClassesDao.deleteSelectById(list);
            map.put("flag", true);
            map.put("message", "成功删除"+i+"条");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除班级异常："+e);
            return map;
        }
        return map;
    }

    /**
     * 删除全部班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> deleteall(RankClasses rankClasses) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(rankClasses == null){
            map.put("flag", false);
            map.put("message", "规则id不能为空");
            return map;
        }
        
        if(rankClasses.getRankinfo_id() == null){
            map.put("flag", false);
            map.put("message", "规则id不能为空");
            return map;
        }
        
        try {
            int i = rankClassesDao.deleteSelectByRankinfoId(rankClasses.getRankinfo_id());
            map.put("flag", true);
            map.put("message", "成功删除"+i+"条");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除班级异常："+e);
        }
        
        return map;
    }

    /**
     * 批量导入班级
     * @param rankClasses
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String,Object> result = new HashMap<String,Object>();
        ExcelUtil<RankClasses> ex=new ExcelUtil<RankClasses>(1,0);
        String rankinfo_id_string = request.getParameter("rankinfo_id");
        Integer rankinfo_id = null;
        if(StringUtils.isEmpty(rankinfo_id_string)){
            map.put("flag", false);
            map.put("message", "规则id不能为空");
            return map;
        }
        
        try {
            rankinfo_id = Integer.valueOf(rankinfo_id_string);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "规则id异常："+e);
            return map;
        }
        
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile multiFile = multiRequest.getFile("file");
            result=ex.checkAccount(multiFile,RankClasses.class);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "读取上传的excel异常");
            return map;
        }
        if(null!=result&&result.get("flag").toString().equals("false")){
            map.put("flag", false);
            map.put("message", result.get("errorMessage"));
            return map;
        } 
        List<RankClasses> list=(List<RankClasses>) result.get("list");
        if(list == null || list.size()==0){
            result.put("flag", false);
            result.put("message", "表格数据不能为空！");
            return result;
        }
        
        try {
            int i = 0;
            for(RankClasses ci : list){
                if(ci == null){
                    map.put("flag", false);
                    map.put("message", "表格第"+i+"行数据为空");
                    return map;
                }
                
                if(StringUtils.isEmpty(ci.getClass_code())){
                    map.put("flag", false);
                    map.put("message", "表格第"+i+"行第一列班号不能为空");
                    return map;
                }
                ci.setRankinfo_id(rankinfo_id);
                rankClassesDao.insertSelective(ci);
                i++;
            }
            map.put("flag", true);
            map.put("message", "上传成功："+i+"条记录");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "excel上传异常");
        }
        
        return map;
    }

    /**
     * 导出班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> download(RankClasses rankClasses, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("rankinfo_id", rankClasses.getRankinfo_id());
            
            List<Map<String,Object>>  maplist = rankClassesDao.selectByRankinfoId(mapparam);
            ExcelUtil<RankClasses> ex=new ExcelUtil<RankClasses>();
            String path = request.getSession().getServletContext()
                    .getRealPath("/static/temp/");
            ex.writeExcel(path+"downloadrankclassesfile.xlsx", RankClasses.class, maplist, response, "排行榜导出班级信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (IOException e) {
            map.put("flag", false);
            map.put("message", "导出失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 前端获取排行榜信息
     * @param id
     * @param rank_num
     * @param rank_lastnum
     * @return
     */
    public Map<String, Object> selectclasses(Integer id, Integer rank_num, Integer rank_lastnum) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(id == null){
            map.put("flag", false);
            map.put("message", "规则id不能为空");
            return map;
        }
        
        if(rank_num == null){
            map.put("flag", false);
            map.put("message", "显示条数不能为空");
            return map;
        }
        
        if(rank_lastnum == null){
            map.put("flag", false);
            map.put("message", "班级剩余人数不能为空");
            return map;
        }
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("rankinfo_id", id);
            mapparam.put("rank_num", rank_num);
            mapparam.put("rank_lastnum", rank_lastnum);
            
           List<RankClasses> list = rankClassesDao.selectclasses(mapparam);
           map.put("flag", true);
           map.put("message", "获取信息成功");
           map.put("list", list);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取班级信息异常："+e);
        }
        
        return map;
    }
    
    /**
     * 通过班号获取班级具体信息
     * 
     * @param classcode 班级code
     * @param num 最小人数
     * @return
     */
    public List<RankClasses> getClassesNum() {
        List<RankClasses> classInfolist = new ArrayList<RankClasses>();// 返回学生对象
        List<RankClasses> list = rankClassesDao.selectClassesInfo();
        if(list == null || list.size() <= 0){
            return classInfolist;
        }
        String classcode = "";
        for(RankClasses r : list){
            classcode += "," + r.getClass_code();
        }
        System.out.println("排行榜刷新班级"+classcode);
        if(StringUtils.isEmpty(classcode)){
            return null;
        }
        String getTeacherUrl = "http://wxapidata.xdf.cn/1/wechat/class_details";// 获取班级信息接口地址
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("schoolId", "25");
        params.put("classCodes", classcode.substring(1));
        try {
            String result = HttpClientUtil.httpGetRequest(getTeacherUrl, null, params);
            if(StringUtils.isEmpty(result)){
                return null;
            }
            JSONArray jsonArray = new JSONArray(result);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jo = jsonArray.getJSONObject(i);
                    RankClasses classInfo = new RankClasses();
                    classInfo.setNow_count(getJSONObjectInteger(jo, "studetnCurrentCount"));
                    if (jo.has("classCode") && !jo.isNull("classCode")) {
                        classInfo.setClass_code(getJSONObjectString(jo, "classCode"));
                        classInfo.setNow_count(getJSONObjectInteger(jo, "studetnCurrentCount"));
                        classInfo.setUpdate_time(new Date());
                        classInfolist.add(classInfo);
                    }
                }
            }
            //去除重复班号的
            Set<RankClasses> ts = new HashSet<RankClasses>();  
            ts.addAll(classInfolist);
            classInfolist.clear();
            classInfolist.addAll(ts);
            for(RankClasses c : classInfolist){
                rankClassesDao.updateByClasscode(c);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return classInfolist;
    }
    
 // 获取jsonobject中jsonname的返回值
    public static String getJSONObjectString(JSONObject jo, String jsonname) {
        String s = "";
        if (jo.has(jsonname) && !jo.isNull(jsonname)) {
            try {
                s = jo.getString(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    // 获取jsonarray中jsonname的返回值
    public static Integer getJSONObjectInteger(JSONObject jo, String jsonname) {
        Integer s = 0;
        if (jo.has(jsonname) && !jo.isNull(jsonname)) {
            try {
                s = jo.getInt(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

}
