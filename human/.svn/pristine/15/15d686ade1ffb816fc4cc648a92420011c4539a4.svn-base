package com.human.jw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.ClassNo;
import com.human.basic.entity.ClassNotice;
import com.human.basic.entity.DicData;
import com.human.basic.entity.XdfClassInfo;
import com.human.basic.service.XdfClassInfoService;
import com.human.jw.dao.JwDao;
import com.human.jw.dao.JwJyzUserDao;
import com.human.jw.dao.JwXdfClassInfoDao;
import com.human.jw.entity.JwJyzUser;
import com.human.jw.service.JwService;
import com.human.jw.service.JwXdfClassInfoService;
import com.human.utils.Common;
import com.human.utils.HttpClientUtil;
import com.human.utils.PageIndex;
import com.human.utils.PageView;
@Service
public class JwXdfClassInfoServiceImpl implements JwXdfClassInfoService{
    
    @Autowired
    private JwXdfClassInfoDao jwxdfClassInfoDao;
    
    @Resource
    private JwService jwService;
    
    @Resource 
    private XdfClassInfoService xdfClassInfoService;
    
    @Autowired
    private JwJyzUserDao jyzUserDao;
    
    @Autowired
    private JwDao jwDao;
    
    @Value("${jw.tclassurl}")
    private  String tclassurl;
    
    @Override
    public PageView query(PageView pageView, XdfClassInfo xci,List<DicData> xq) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        try{
            map.put("paging", pageView);
            String author=Common.getMyUser().getAuthorities().toString();
            String email = Common.getAuthenticatedUsername();
            List<JwJyzUser> jus = jyzUserDao.selectByEmail(email);
            if(author.indexOf("ROLE_gz_view")==-1&&author.indexOf("ROLE_cz_view")==-1){
                if(jus.size()==0){
                    if(author.indexOf("ROLE_one6_edit")==-1&&author.indexOf("ROLE_one6_stu_bj")==-1&&author.indexOf("ROLE_one6_manger_edit")==-1){
                        String teacher=Common.getMyUser().getName();
                        xci.setsAllTeacherName(teacher);
                    } 
                }
            }else{
                if(author.indexOf("ROLE_gz_view")>-1){
                    xci.setView("gz");
                }else if(author.indexOf("ROLE_cz_view")>-1){
                    xci.setView("cz");
                }
            }
            String jyzs = "";
            for(JwJyzUser jju:jus){
                jyzs += StringUtils.isEmpty(jyzs)?jju.getJyz():","+jju.getJyz();
            }
            if(StringUtils.isNotEmpty(jyzs)){
                xci.setArea(jyzs);
            }
            Integer isGz = jwDao.isGaozhongManager(email);
            if(isGz!=null && isGz>0){
                xci.setArea("高中数学");
            }
            map.put("t", xci);
            List<XdfClassInfo> list = jwxdfClassInfoDao.query(map);
            //查询最大班级数量
           /* for (XdfClassInfo xdfClassInfo : list) {
                String sClassCode=xdfClassInfo.getsClassCode();
                List<Map<String, Object>> studentlList = jwService.getStudentInfoClass(sClassCode);
                int nCurrentNum=studentlList.size();
                String codeIndex="";
                for (Map<String, Object> studentlMap : studentlList) {
                   String code= (String) studentlMap.get("Code");
                   if(codeIndex.indexOf(code)>-1){
                       nCurrentNum--;
                   }
                    codeIndex+=code;
                }
                xdfClassInfo.setnCurrentCount(String.valueOf(nCurrentNum));
            }*/
            pageView.setRecords(list);
           }catch(Exception e){
             e.printStackTrace();
           }        
          return pageView;
      }

    @Override
    public XdfClassInfo selectByprimary(String sClassCode) {
        // TODO Auto-generated method stub
        return jwxdfClassInfoDao.selectByPrimaryKey(sClassCode);
    }

    @Override
    public void updateForMap(Map<String, Object> xdf) {
        // TODO Auto-generated method stub
        jwxdfClassInfoDao.updateForMap(xdf);
    }

    @Override
    public void insertForClass(String sClassCode) {
        // TODO Auto-generated method stub
        jwxdfClassInfoDao.insertForClass(sClassCode);
    }

    @Override
    public Map<String, Object> selectByprimaryForRm(String sClassCode) {
        // TODO Auto-generated method stub
        return jwxdfClassInfoDao.selectByprimaryForRm(sClassCode);
    }

    @Override
    public Map<String, Object> initClassCode(String sClassCode) {
        String[] classCode = sClassCode.split(",");
        Map<String, Object> result = new HashMap();
        try {
          for (String cCode : classCode) {
            String jstr = HttpClientUtil.httpGetRequest(this.tclassurl + "?nSchoolID=25&sClassCodes=" + cCode, null);
            ClassNotice notice = new ClassNotice();
            notice.setReplyMax("10");
            notice.setReplyTimes("0");
            notice.setBodyJson("[{\"nSchoolId\":25,\"sCode\":\"" + sClassCode + "\"}]");
            notice.setRouteKey("AAAAA");
            String getBodyJson = notice.getBodyJson();
            List<ClassNo> GetJson = (List<ClassNo>)JSON.parseArray(getBodyJson, ClassNo.class);

            for (ClassNo c : GetJson) {
              System.out.println(c.getnSchoolId());
              if (c.getnSchoolId().equals("25"))
              {
                String state = JSON.parseObject(jstr).get("State").toString();
                if ("1".equals(state)) {
                  List<XdfClassInfo> list = JSON.parseArray(JSON.parseObject(jstr).getString("Data"), XdfClassInfo.class);
                  for (XdfClassInfo xdfClassInfo : list) {
                    xdfClassInfo.setRouteKey(notice.getRouteKey());
                    xdfClassInfo.setnAudit(c.getnAudit());
                    this.xdfClassInfoService.syncClassInfo(xdfClassInfo);
                  }
                }
              }
            }
          }
          result.put("flag", Boolean.valueOf(true));
          result.put("message", "成功");
        }
        catch (Exception e) {
          result.put("flag", Boolean.valueOf(false));
          result.put("message", "失败");
        }
        return result;
      }

    @Override
    public List<XdfClassInfo> queryTotalNum() {
        // TODO Auto-generated method stub
        List<XdfClassInfo> list=jwxdfClassInfoDao.queryTotalNum();
        try {
        for (XdfClassInfo x : list) {
            String sClassCode=x.getsClassCode();
            List<Map<String, Object>>  studentlList = jwService.getStudentInfoClass(sClassCode);
            int nCurrentNum=studentlList.size();
            String codeIndex="";
            for (Map<String, Object> studentlMap : studentlList) {
               String code= (String) studentlMap.get("Code");
               if(codeIndex.indexOf(code)>-1){
                   nCurrentNum--;
               }
                codeIndex+=code;
            }
            String currentNum=String.valueOf(nCurrentNum);
            x.setnCurrentCount(currentNum);
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int updateNCurrentNum(List<XdfClassInfo> list) {
      int num=  jwxdfClassInfoDao.updateNCurrentNum(list);
        return num;
    }

}
