package com.human.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/** 
 * 取得给定汉字串的首字母串,即声母串 
 * Title: ChineseCharToEn 
 * @date 2004-02-19 注：只支持GB2312字符集中的汉字 
 */  
public final class ChineseCharToEn {  
    private final static int[] li_SecPosValue = { 1601, 1637, 1833, 2078, 2274,  
            2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,  
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };  
    private final static String[] lc_FirstLetter = { "a", "b", "c", "d", "e",  
            "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "w", "x", "y", "z" };  
  
    /** 
     * 取得给定汉字串的首字母串,即声母串 
     * @param str 给定汉字串 
     * @return 声母串 
     */  
    public static String getAllFirstLetter(String str) {  
        if (str == null || str.trim().length() == 0) {  
            return "";  
        }  
  
        String _str = "";  
        for (int i = 0; i < str.length(); i++) {  
            _str = _str + getFirstLetter(str.substring(i, i + 1));  
        }  
  
        return _str;  
    }  
  
    /** 
     * 取得给定汉字的首字母,即声母 
     * @param chinese 给定的汉字 
     * @return 给定汉字的声母 
     */  
    public static String getFirstLetter(String chinese) {  
    	if (chinese == null || chinese.trim().length() == 0) {  
            return "";  
        }  
    	if("重庆市".equals(chinese)){
    		return "c";
    	}
    	if("衢州市".equals(chinese)){
    		return "q";
    	}
    	if("亳州市".equals(chinese)){
    		return "b";
    	}
    	if("濮阳市".equals(chinese)){
    		return "p";
    	}
    	if("漯河市".equals(chinese)){
    		return "l";
    	}
    	if("泸州市".equals(chinese)){
    		return "l";
    	}
    	if("儋州市".equals(chinese)){
    		return "d";
    	}
        chinese = conversionStr(chinese, "GB2312", "ISO8859-1");  
  
        if (chinese.length() > 1) // 判断是不是汉字  
        {  
            int li_SectorCode = (int) chinese.charAt(0); // 汉字区码  
            int li_PositionCode = (int) chinese.charAt(1); // 汉字位码  
            li_SectorCode = li_SectorCode - 160;  
            li_PositionCode = li_PositionCode - 160;  
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码  
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {  
                for (int i = 0; i < 23; i++) {  
                    if (li_SecPosCode >= li_SecPosValue[i]  
                            && li_SecPosCode < li_SecPosValue[i + 1]) {  
                        chinese = lc_FirstLetter[i];  
                        break;  
                    }  
                }  
            } else // 非汉字字符,如图形符号或ASCII码  
            {  
                chinese = conversionStr(chinese, "ISO8859-1", "GB2312");  
                chinese = chinese.substring(0, 1);  
            }  
        }  
  
        return chinese;  
    }  
  
    /** 
     * 字符串编码转换 
     * @param str 要转换编码的字符串 
     * @param charsetName 原来的编码 
     * @param toCharsetName 转换后的编码 
     * @return 经过编码转换后的字符串 
     */  
    private static String conversionStr(String str, String charsetName,String toCharsetName) {  
        try {  
            str = new String(str.getBytes(charsetName), toCharsetName);  
        } catch (UnsupportedEncodingException ex) {  
            System.out.println("字符串编码转换异常：" + ex.getMessage());  
        }  
        return str;  
    }  
    
    public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("天津市");
		list.add("上海市");
		list.add("重庆市");
		list.add("邯郸市");
		list.add("石家庄市");
		list.add("保定市");
		list.add("张家口市");
		list.add("承德市");
		list.add("唐山市");
		list.add("廊坊市");
		list.add("沧州市");
		list.add("衡水市");
		list.add("邢台市");
		list.add("秦皇岛市");
		list.add("朔州市");
		list.add("忻州市");
		list.add("太原市");
		list.add("大同市");
		list.add("阳泉市");
		list.add("晋中市");
		list.add("长治市");
		list.add("晋城市");
		list.add("临汾市");
		list.add("吕梁市");
		list.add("运城市");
		list.add("沈阳市");
		list.add("铁岭市");
		list.add("大连市");
		list.add("鞍山市");
		list.add("抚顺市");
		list.add("本溪市");
		list.add("丹东市");
		list.add("锦州市");
		list.add("营口市");
		list.add("阜新市");
		list.add("辽阳市");
		list.add("朝阳市");
		list.add("盘锦市");
		list.add("葫芦岛市");
		list.add("长春市");
		list.add("吉林市");
		list.add("延边朝鲜族自治州");
		list.add("四平市");
		list.add("通化市");
		list.add("白城市");
		list.add("辽源市");
		list.add("松原市");
		list.add("白山市");
		list.add("哈尔滨市");
		list.add("齐齐哈尔市");
		list.add("鸡西市");
		list.add("牡丹江市");
		list.add("七台河市");
		list.add("佳木斯市");
		list.add("鹤岗市");
		list.add("双鸭山市");
		list.add("绥化市");
		list.add("黑河市");
		list.add("大兴安岭地区");
		list.add("伊春市");
		list.add("大庆市");
		list.add("南京市");
		list.add("无锡市");
		list.add("镇江市");
		list.add("苏州市");
		list.add("南通市");
		list.add("扬州市");
		list.add("盐城市");
		list.add("徐州市");
		list.add("淮安市");
		list.add("连云港市");
		list.add("常州市");
		list.add("泰州市");
		list.add("宿迁市");
		list.add("舟山市");
		list.add("衢州市");
		list.add("杭州市");
		list.add("湖州市");
		list.add("嘉兴市");
		list.add("宁波市");
		list.add("绍兴市");
		list.add("温州市");
		list.add("丽水市");
		list.add("金华市");
		list.add("台州市");
		list.add("合肥市");
		list.add("芜湖市");
		list.add("蚌埠市");
		list.add("淮南市");
		list.add("马鞍山市");
		list.add("淮北市");
		list.add("铜陵市");
		list.add("安庆市");
		list.add("黄山市");
		list.add("滁州市");
		list.add("阜阳市");
		list.add("宿州市");
		list.add("巢湖市");
		list.add("六安市");
		list.add("亳州市");
		list.add("池州市");
		list.add("宣城市");
		list.add("福州市");
		list.add("厦门市");
		list.add("宁德市");
		list.add("莆田市");
		list.add("泉州市");
		list.add("漳州市");
		list.add("龙岩市");
		list.add("三明市");
		list.add("南平市");
		list.add("鹰潭市");
		list.add("新余市");
		list.add("南昌市");
		list.add("九江市");
		list.add("上饶市");
		list.add("抚州市");
		list.add("宜春市");
		list.add("吉安市");
		list.add("赣州市");
		list.add("景德镇市");
		list.add("萍乡市");
		list.add("菏泽市");
		list.add("济南市");
		list.add("青岛市");
		list.add("淄博市");
		list.add("德州市");
		list.add("烟台市");
		list.add("潍坊市");
		list.add("济宁市");
		list.add("泰安市");
		list.add("临沂市");
		list.add("滨州市");
		list.add("东营市");
		list.add("威海市");
		list.add("枣庄市");
		list.add("日照市");
		list.add("莱芜市");
		list.add("聊城市");
		list.add("商丘市");
		list.add("郑州市");
		list.add("安阳市");
		list.add("新乡市");
		list.add("许昌市");
		list.add("平顶山市");
		list.add("信阳市");
		list.add("南阳市");
		list.add("开封市");
		list.add("洛阳市");
		list.add("济源市");
		list.add("焦作市");
		list.add("鹤壁市");
		list.add("濮阳市");
		list.add("周口市");
		list.add("漯河市");
		list.add("驻马店市");
		list.add("三门峡市");
		list.add("武汉市");
		list.add("襄阳市");
		list.add("鄂州市");
		list.add("孝感市");
		list.add("黄冈市");
		list.add("黄石市");
		list.add("咸宁市");
		list.add("荆州市");
		list.add("宜昌市");
		list.add("恩施土家族苗族自治州");
		list.add("神农架林区");
		list.add("十堰市");
		list.add("随州市");
		list.add("荆门市");
		list.add("仙桃市");
		list.add("天门市");
		list.add("潜江市");
		list.add("岳阳市");
		list.add("长沙市");
		list.add("湘潭市");
		list.add("株洲市");
		list.add("衡阳市");
		list.add("郴州市");
		list.add("常德市");
		list.add("益阳市");
		list.add("娄底市");
		list.add("邵阳市");
		list.add("湘西土家族苗族自治州");
		list.add("张家界市");
		list.add("怀化市");
		list.add("永州市");
		list.add("广州市");
		list.add("汕尾市");
		list.add("阳江市");
		list.add("揭阳市");
		list.add("茂名市");
		list.add("惠州市");
		list.add("江门市");
		list.add("韶关市");
		list.add("梅州市");
		list.add("汕头市");
		list.add("深圳市");
		list.add("珠海市");
		list.add("佛山市");
		list.add("肇庆市");
		list.add("湛江市");
		list.add("中山市");
		list.add("河源市");
		list.add("清远市");
		list.add("云浮市");
		list.add("潮州市");
		list.add("东莞市");
		list.add("兰州市");
		list.add("金昌市");
		list.add("白银市");
		list.add("天水市");
		list.add("嘉峪关市");
		list.add("武威市");
		list.add("张掖市");
		list.add("平凉市");
		list.add("酒泉市");
		list.add("庆阳市");
		list.add("定西市");
		list.add("陇南市");
		list.add("临夏回族自治州");
		list.add("甘南藏族自治州");
		list.add("成都市");
		list.add("攀枝花市");
		list.add("自贡市");
		list.add("绵阳市");
		list.add("南充市");
		list.add("达州市");
		list.add("遂宁市");
		list.add("广安市");
		list.add("巴中市");
		list.add("泸州市");
		list.add("宜宾市");
		list.add("资阳市");
		list.add("内江市");
		list.add("乐山市");
		list.add("眉山市");
		list.add("凉山彝族自治州");
		list.add("雅安市");
		list.add("甘孜藏族自治州");
		list.add("阿坝藏族羌族自治州");
		list.add("德阳市");
		list.add("广元市");
		list.add("贵阳市");
		list.add("遵义市");
		list.add("安顺市");
		list.add("黔南布依族苗族自治州");
		list.add("黔东南苗族侗族自治州");
		list.add("铜仁地区");
		list.add("毕节地区");
		list.add("六盘水市");
		list.add("黔西南布依族苗族自治州");
		list.add("海口市");
		list.add("三亚市");
		list.add("五指山市");
		list.add("琼海市");
		list.add("儋州市");
		list.add("文昌市");
		list.add("万宁市");
		list.add("东方市");
		list.add("澄迈县");
		list.add("定安县");
		list.add("屯昌县");
		list.add("临高县");
		list.add("白沙黎族自治县");
		list.add("昌江黎族自治县");
		list.add("乐东黎族自治县");
		list.add("陵水黎族自治县");
		list.add("保亭黎族苗族自治县");
		list.add("琼中黎族苗族自治县");
		list.add("西双版纳傣族自治州");
		list.add("德宏傣族景颇族自治州");
		list.add("昭通市");
		list.add("昆明市");
		list.add("大理白族自治州");
		list.add("红河哈尼族彝族自治州");
		list.add("曲靖市");
		list.add("保山市");
		list.add("文山壮族苗族自治州");
		list.add("玉溪市");
		list.add("楚雄彝族自治州");
		list.add("普洱市");
		list.add("临沧市");
		list.add("怒江傈傈族自治州");
		list.add("迪庆藏族自治州");
		list.add("丽江市");
		list.add("海北藏族自治州");
		list.add("西宁市");
		list.add("海东地区");
		list.add("黄南藏族自治州");
		list.add("海南藏族自治州");
		list.add("果洛藏族自治州");
		list.add("玉树藏族自治州");
		list.add("海西蒙古族藏族自治州");
		list.add("西安市");
		list.add("咸阳市");
		list.add("延安市");
		list.add("榆林市");
		list.add("渭南市");
		list.add("商洛市");
		list.add("安康市");
		list.add("汉中市");
		list.add("宝鸡市");
		list.add("铜川市");
		list.add("防城港市");
		list.add("南宁市");
		list.add("崇左市");
		list.add("来宾市");
		list.add("柳州市");
		list.add("桂林市");
		list.add("梧州市");
		list.add("贺州市");
		list.add("贵港市");
		list.add("玉林市");
		list.add("百色市");
		list.add("钦州市");
		list.add("河池市");
		list.add("北海市");
		list.add("拉萨市");
		list.add("日喀则地区");
		list.add("山南地区");
		list.add("林芝地区");
		list.add("昌都地区");
		list.add("那曲地区");
		list.add("阿里地区");
		list.add("银川市");
		list.add("石嘴山市");
		list.add("吴忠市");
		list.add("固原市");
		list.add("中卫市");
		list.add("塔城地区");
		list.add("哈密地区");
		list.add("和田地区");
		list.add("阿勒泰地区");
		list.add("克孜勒苏柯尔克孜自治州");
		list.add("博尔塔拉蒙古自治州");
		list.add("克拉玛依市");
		list.add("乌鲁木齐市");
		list.add("石河子市");
		list.add("昌吉回族自治州");
		list.add("五家渠市");
		list.add("吐鲁番地区");
		list.add("巴音郭楞蒙古自治州");
		list.add("阿克苏地区");
		list.add("阿拉尔市");
		list.add("喀什地区");
		list.add("图木舒克市");
		list.add("伊犁哈萨克自治州");
		list.add("呼伦贝尔市");
		list.add("呼和浩特市");
		list.add("包头市");
		list.add("乌海市");
		list.add("乌兰察布市");
		list.add("通辽市");
		list.add("赤峰市");
		list.add("鄂尔多斯市");
		list.add("巴彦淖尔市");
		list.add("锡林郭勒盟");
		list.add("兴安盟");
		list.add("阿拉善盟");
		list.add("澳门特别行政区");
		list.add("北京市");
		
		for(String s:list){
			System.out.println(s+"--"+getFirstLetter(s));
		}
	}
  
}  