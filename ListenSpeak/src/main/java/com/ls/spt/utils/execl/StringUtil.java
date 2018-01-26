package com.ls.spt.utils.execl;

import java.util.Collection;

/**
 * @author xdwang
 * 
 * @create 2015年5月13日 上午11:19:29
 * 
 * @version 1.0
 * 
 * @description 字符串工具类
 * 
 */
public class StringUtil {
    
    /**
     * 
     * @description 判断是否为空，或空字符串
     * @author Yubin
     * @create 2015年12月6日下午12:36:02
     * @version 1.0
     * @param str
     * @return
     */
    public static boolean isEmptyOrBlank(String str){
        if (StringUtil.isEmpty(str) || StringUtil.isBlank(str)) {
            return true;
        }
        return false;
    }

	/**
	 * @description 判断字符串是否为空
	 * @author xdwang
	 * @create 2015年11月21日下午1:51:45
	 * @version 1.0
	 * @param str
	 *            字符串源
	 * @return 为空返回true
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str.trim()));
	}

	/**
	 * @description 判断字符串是否为非空
	 * @author xdwang
	 * @create 2015年11月21日下午1:52:39
	 * @version 1.0
	 * @param str
	 *            字符串源
	 * @return 为非空返回true
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * @description 是否为空字符串
	 * @author xdwang
	 * @create 2015年11月21日下午1:52:56
	 * @version 1.0
	 * @param str
	 *            字符串源
	 * @return 为空则返回true
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @description 是否为非空字符串
	 * @author xdwang
	 * @create 2015年11月21日下午1:52:56
	 * @version 1.0
	 * @param str
	 *            字符串源
	 * @return 为非空则返回true
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * @description 比较两个String 是否相同 此方法无须在外面判定为空的情况
	 * @author xdwang
	 * @create 2015年11月21日下午2:02:17
	 * @version 1.0
	 * @param a
	 *            字符串1
	 * @param b
	 *            字符串2
	 * @return equal则返回true
	 */
	public static boolean compare(String a, String b) {
		if (isEmpty(a) && isEmpty(b)) {
			return true;
		} else if (!isEmpty(a) && !isEmpty(b)) {
			return (a.equals(b));
		} else {
			return false;
		}
	}

	/**
	 * @description 将字符串 倒置
	 * @author xdwang
	 * @create 2015年11月21日下午2:02:50
	 * @version 1.0
	 * @param src
	 *            字符串源
	 * @return 倒置后的字符串
	 */
	public static String reverse(String src) {
		StringBuffer buffer;
		if (isEmpty(src)) {
			return src;
		}
		buffer = new StringBuffer(src);
		buffer.reverse();
		return buffer.toString();
	}

	/**
	 * @description 去掉前面的相关字符
	 * @author xdwang
	 * @create 2015年11月21日下午2:03:22
	 * @version 1.0
	 * @param soure
	 *            字符串源
	 * @param trimStr
	 *            去掉字符串
	 * @return 去掉后的结果字符串
	 */
	public static String trimStart(String soure, String trimStr) {
		if (isEmpty(soure) || isEmpty(trimStr)) {
			return soure;
		}
		if (soure.indexOf(trimStr) == 0) {
			return soure.substring(trimStr.length(), soure.length());
		}
		return null;
	}

	/**
	 * @description 去掉后面的相关字符
	 * @author xdwang
	 * @create 2015年11月21日下午2:04:11
	 * @version 1.0
	 * @param soure
	 *            字符串源
	 * @param trimStr
	 *            去掉字符串
	 * @return 去掉后的结果字符串
	 */
	public static String trimEnd(String soure, String trimStr) {
		if (isEmpty(soure) || isEmpty(trimStr)) {
			return soure;
		}
		if (soure.lastIndexOf(trimStr) == soure.length() - 1) {
			return soure.substring(0, soure.length() - trimStr.length());
		}
		return null;
	}

	/**
	 * @description 去掉前后字符串
	 * @author xdwang
	 * @create 2015年11月21日下午2:04:11
	 * @version 1.0
	 * @param soure
	 *            字符串源
	 * @param trimStr
	 *            去掉前后字符串
	 * @return 去掉后的结果字符串
	 */
	public static String trimStartAndEnd(String soure, String trimStr) {
		if (isEmpty(soure) || isEmpty(trimStr)) {
			return soure;
		}
		return trimEnd(trimStart(soure, trimStr), trimStr);
	}

	/**
	 * @description 反编码
	 * @author xdwang
	 * @create 2015年11月21日下午2:59:30
	 * @version 1.0
	 * @param src
	 *            字符串源
	 * @return 反编码后的字符串
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0;
		int pos = 0;
		char ch;
		// CSOFF: Magic
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		// CSOFF: Magic
		return tmp.toString();
	}

	/**
	 * 
	 * @description 第一个字母小写
	 * @author xdwang
	 * @create 2015年11月21日下午2:57:12
	 * @version 1.0
	 * @param soure
	 *            字符串源
	 * @return 返回第一个字母小写的字符串
	 */
	public static String toLowerCaseOfFristChar(String soure) {
		if (isEmpty(soure)) {
			return soure;
		}
		return soure.replaceFirst(soure.substring(0, 1), soure.substring(0, 1).toLowerCase());
	}

	/**
	 * @description 第一个字母大写
	 * @author xdwang
	 * @create 2015年11月21日下午2:01:10
	 * @version 1.0
	 * @param soure
	 *            待操作的字符串
	 * @return 返回第一个字母大写的字符串
	 */
	public static String toUpperCaseOfFristChar(String soure) {
		if (isEmpty(soure)) {
			return soure;
		}
		return soure.replaceFirst(soure.substring(0, 1), soure.substring(0, 1).toUpperCase());
	}

	/**
	 * 将集合之间添加分隔符
	 * 
	 * @param strings
	 *            字符串集合
	 * @param splitStr
	 *            分隔符
	 * @return 1;2;3
	 */
	public static String addSplitStr(Collection<String> strings, String splitStr) {
		StringBuilder builder = new StringBuilder();
		for (String string : strings) {
			builder.append(string + splitStr);
		}
		if (strings.size() != 0) {
			return builder.toString().substring(0, builder.toString().length() - 1);
		}
		return "";
	}

	/**
	 * 将集合之间添加分隔符
	 * 
	 * @param strings
	 *            字符串集合
	 * @param splitStr
	 *            分隔符
	 * @return 1;2;3
	 */
	public static String addSplitStr(String[] strings, String splitStr) {
		StringBuilder builder = new StringBuilder();
		for (String string : strings) {
			builder.append(string + splitStr);
		}
		if (strings.length != 0) {
			return builder.toString().substring(0, builder.toString().length() - 1);
		}
		return "";
	}

}
