package cn.xdf.pay.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayInputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import cn.xdf.pay.util.WechatPayUtil.HttpClientUtil;

/**
 * xml解析工具类
 * @author liuwei63
 *
 */
public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		if(null == strxml || "".equals(strxml)) {
			return null;
		}		
		Map m = new HashMap();		
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}			
			m.put(k, v);
		}		
		//关闭流
		in.close();		
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}		
		return sb.toString();
	}
	
	/**
	 * 获取xml编码字符集
	 * @param strxml
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
		InputStream in = HttpClientUtil.String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String)doc.getProperty("encoding");
	}
	
	/**
	 * 商户处理后同步返回给微信参数
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	 public static String setXml(String return_code,String return_msg){  
	        return "<xml><return_code><![CDATA["+return_code+"]]></return_code><return_msg><![CDATA["+return_msg+"]]></return_msg></xml>";  
	 }
	 
	/**
	 * 解析xml
	 * @param xml
	 * @return
	 */
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static Map parseXmlToMap(String xml) {  
	        Map retMap = new HashMap();  
	        try {  
	            StringReader read = new StringReader(xml);  
	            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
	            InputSource source = new InputSource(read);  
	            // 创建一个新的SAXBuilder  
	            SAXBuilder sb = new SAXBuilder();  
	            // 通过输入源构造一个Document  
	            Document doc = (Document) sb.build(source);  
	            Element root = doc.getRootElement();// 指向根节点  
	            List<Element> es = root.getChildren();  
	            if (es != null && es.size() != 0) {  
	                for (Element element : es) {  
	                    retMap.put(element.getName(), element.getText());  
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return retMap;  
	    }  
	 
	/**
	 * 解析xml
	 * @param xml
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map parseInputStreamToMap(InputStream ist) {
		Map retMap = new HashMap();
		try {
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(ist);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	} 
}
