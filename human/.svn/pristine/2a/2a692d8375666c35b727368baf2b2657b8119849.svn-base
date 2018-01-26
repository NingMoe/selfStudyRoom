package com.human.nologin.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.coupon.entity.Coupon;
import com.human.coupon.entity.StudentCoup;
import com.human.coupon.entity.StudentRes;
import com.human.utils.CoupUtil;
import com.human.utils.HttpClientUtil;

@Controller
@RequestMapping("/free/coupon/")
public class FreeCouponController {
    
    @Autowired
    private CoupUtil coupUtil;
    
    
    @RequestMapping(value="getCoupon")
    @ResponseBody
    public Map<String,Object> getCoupon(String studentCode){
        return coupUtil.getCoup(studentCode, null,"41");
    }
    
    @RequestMapping(value="view")
    public ModelAndView toSeekerDetail(String openid,String schoolid){
        ModelAndView mav = new ModelAndView("/coupon/coupon");
        List<Coupon> coupons = null;
        List<Coupon> wsyList = new ArrayList<Coupon>();
        List<Coupon> ysyList = new ArrayList<Coupon>();
        Map<String,Object> res= coupUtil.getStudentCode(openid, schoolid);
        try{
            if(res.get("flag").toString().equals("true")){
                if(res.get("Data")==null){
                    mav.addObject("isBind", "0");
                    return mav;
                }
                StudentRes ss = (StudentRes) res.get("Data"); 
                mav.addObject("studentRes", ss);
                String studentCode = ss.getStudentcode();
                if(StringUtils.isNotEmpty(studentCode)){
                    mav.addObject("studentCode", studentCode);
                    Map<String,Object> result = coupUtil.getCoup(studentCode, null,schoolid);
                    if(result.get("flag").toString().equals("true")){
                        StudentCoup sc = (StudentCoup) result.get("Data"); 
                        coupons = sc.getCouponList();
                        if(coupons!=null && coupons.size()>0){
                            for(Coupon c:coupons){
                                if(c.getVoucherValue().equals("0")){
                                    continue;
                                }
                                SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String da = c.getEndDate();
                                da = da.replace("T", " ");
                                Date d = smf.parse(da);
                                Calendar c1 = Calendar.getInstance();
                                c1.setTime(d);
                                c1.add(Calendar.MONTH,1);
                                Date tarDate = c1.getTime();
                                if(tarDate.after(new Date(System.currentTimeMillis())) && c.getStatus().equals("1")){
                                    wsyList.add(c);
                                    continue;
                                }
                                if(c.getStatus().equals("2")){
                                    ysyList.add(c);
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            
        }
        if(wsyList!=null && wsyList.size()>0){
            System.out.println(wsyList.size());
        }else{
            System.out.println("无数据1");
        }
        
        if(ysyList!=null && wsyList.size()>0){
            System.out.println(ysyList.size());
        }else{
            System.out.println("无数据2");
        }
        
        mav.addObject("wsyList", wsyList);
        mav.addObject("ysyList", ysyList);
        return mav;
    }
    
    
    @RequestMapping(value="unbind")
    @ResponseBody
    public Map<String,Object> unbind(String openid,String schoolid){
        return coupUtil.unBindOpenId(openid, schoolid);
    }
    
    
    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        /*String s1 = "2017-09-11T23:59:00";
        s1 = s1.replace("T", " ");
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = smf.parse(s1);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d);
        c1.add(Calendar.MONTH,-1);
        Date tarDate = c1.getTime();
        System.out.println(tarDate.toLocaleString());
        System.out.println(tarDate.before(new Date(System.currentTimeMillis())));
        System.out.println(tarDate.after(new Date(System.currentTimeMillis())));*/
        
        //String s =HttpClientUtil.httpPostJson("http://xuban.t.staff.xdf.cn/Api/WindowPeriod_StudentPackageApi/Save","[{\"Id\": null,\"nSchoolId\": 25,\"sWindowPeriodId\": \"a63bcd4b-e5f5-05c0-37c4-8fcde76d6665\",\"sStageId\": \"e72bbf39-6f1c-416f-9b2c-70c4aced7a9a\",\"sStageName\": \"卡萨丁后方可\",\"sStudentCode\": null,\"sStudentName\": \"ceshi\",\"sMobile\": \"18210111111\",\"sContinuedClassCode\": \"YB001\",\"sContinuedClassName\": \"YB001\",\"dtCreateDate\": \"2017-8-10\",\"sCreateUserId\": \"wpapi\", \"dtModifyDate\": null, \"sModifyUserId\": null,\"bIsDelete\": false, \"dtDeleteDate\": null, \"sDeleteUserId\": null, \"ObjectState\": 0}]","mJxpCnhtsj5_2BjQKj4KlkclgUuxRz9e-8Xi4werAAKsJArVi2chzswrPJwEJU8PsfKeOqKRpoAI8ni_ZEvFTs9fHchO5viZkQbK4233hnPLGTnyUw9Iq60S0x0xUdgIpl-4QI7yKalXcQJJ4w8CP06pn-Knni5UwV3IYfrnpHs44unGiKxRFmvHmMxxDEmmrOyOCsv5BIJGqQYqmMpoM468vHB2orat1YWzg1dDzVitvIJ7ZuGxFJtxo65qY3Fhyh7TpD4e1VZg9zDKVt57JD0HVyb_Pk-cs2kGzec4cF4Gj3PeTRMq5X8tn3DPYjk323UwBFojWgcX4BCdmsL6IA");
        String s =HttpClientUtil.httpPostJson("http://xuban.t.staff.xdf.cn/api/DeleteStudentPackageForApi/ApiBatchDelete","[{\"Id\": null,\"nSchoolId\": 25,\"sWindowPeriodId\": \"a63bcd4b-e5f5-05c0-37c4-8fcde76d6665\",\"sStageId\": \"e72bbf39-6f1c-416f-9b2c-70c4aced7a9a\",\"sStageName\": \"卡萨丁后方可\",\"sStudentCode\": null,\"sStudentName\": \"ceshi\",\"sMobile\": \"18210111111\",\"sContinuedClassCode\": \"YB001\",\"sContinuedClassName\": \"YB001\",\"dtCreateDate\": \"2017-8-10\",\"sCreateUserId\": \"wpapi\", \"dtModifyDate\": null, \"sModifyUserId\": null,\"bIsDelete\": false, \"dtDeleteDate\": null, \"sDeleteUserId\": null, \"ObjectState\": 0}]","mJxpCnhtsj5_2BjQKj4KlkclgUuxRz9e-8Xi4werAAKsJArVi2chzswrPJwEJU8PsfKeOqKRpoAI8ni_ZEvFTs9fHchO5viZkQbK4233hnPLGTnyUw9Iq60S0x0xUdgIpl-4QI7yKalXcQJJ4w8CP06pn-Knni5UwV3IYfrnpHs44unGiKxRFmvHmMxxDEmmrOyOCsv5BIJGqQYqmMpoM468vHB2orat1YWzg1dDzVitvIJ7ZuGxFJtxo65qY3Fhyh7TpD4e1VZg9zDKVt57JD0HVyb_Pk-cs2kGzec4cF4Gj3PeTRMq5X8tn3DPYjk323UwBFojWgcX4BCdmsL6IA");
        
        System.out.println(s);

        
    }
}
