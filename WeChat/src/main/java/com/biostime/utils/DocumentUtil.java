package com.biostime.utils;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DocumentUtil {
	
	final static String charset = "UTF-8";
	//final static String charset = "GBK";
	
	/**
	 * 
	 * 
	 * 功能描述：格式化XML文件
	 * 
	 * @param xmlString
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @throws Exception 
	 * @since 2013年11月22日
	 *
	 */
	public  static Document parseXMLDocument(String xmlString) throws Exception { 
		if (xmlString == null) { 
			throw new IllegalArgumentException(); 
		}
		StringReader sr= null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			sr = new StringReader(xmlString);
			InputSource is = new InputSource(sr);
			return db.parse(is);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		} finally{
			if(sr!=null){
				sr.close();
			}
		}
	}
	
	/**
	 * 
	 * 
	 * 功能描述：xml doc转string
	 * 
	 * @param doc
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月22日
	 *
	 */
	public static String toString(Logger log,Document doc) { 
		ByteArrayOutputStream  bos = null;
		try {
			TransformerFactory  tf  =  TransformerFactory.newInstance();    
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding",charset);
			bos =  new  ByteArrayOutputStream();   
			t.transform(new DOMSource(doc), new StreamResult(bos));
		} catch (TransformerException e) {
			LogUtil.error(log, LogFormat.PLAIN, "errorMessage="+e.getMessage());
			e.printStackTrace();
		}
		return bos.toString();
	}
	
	
	/**
	 * 
	 * 
	 * 功能描述：从doc中获取某个节点的值
	 * 
	 * @param doc
	 * @param index
	 * @param nodeName
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月22日
	 *
	 */
	public static String getValue(Document doc,int index,String nodeName){
		NodeList nodeList = doc.getElementsByTagName(nodeName);
		return getValue(index, nodeList);
	}

	/**
	 * 
	 * 
	 * 功能描述：从Element中获取某个节点的值
	 * 
	 * @param doc
	 * @param index
	 * @param nodeName
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月22日
	 *
	 */
	public static String getValue(Element e,int index,String nodeName){
		NodeList nodeList = e.getElementsByTagName(nodeName);
		return getValue(index, nodeList);
	}
	
	private static String getValue(int index, NodeList nodeList) {
		String retString = "";
		if(nodeList!=null){
			Node node = nodeList.item(index);
			
			if(node!=null){
				Node fistNode = node.getFirstChild();
				if(fistNode!=null){
					retString = fistNode.getNodeValue();
				}else{
					retString = "";
				}
			}else{
				retString = "";
			}
		}
		return retString;
	}
	
	/**
	 * 
	 * 
	 * 功能描述：从doc中获取某个节点的值
	 * 
	 * @param doc
	 * @param index
	 * @param nodeName
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月22日
	 *
	 */
	public static String getValue(Element oneNode,String nodeName){
		String retString = "";
		
		NodeList nodeList = oneNode.getElementsByTagName(nodeName);
		if(nodeList!=null){
			Node node = nodeList.item(0);
			
			if(node!=null){
				Node fistNode = node.getFirstChild();
				if(fistNode!=null){
					retString = fistNode.getNodeValue();
				}else{
					retString = "";
				}
			}else{
				retString = "";
			}
		}
		return retString;
	}
	
	/**
	 * 
	 * 
	 * 功能描述：从doc中获取某个节点的属性值
	 * 
	 * @param doc
	 * @param index
	 * @param nodeName
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月22日
	 *
	 */
	public static String getAttr(Document doc,int index,String nodeName,String attrName){
		return doc.getElementsByTagName(nodeName).item(index).getAttributes().getNamedItem(attrName).getNodeValue();
	}
	
	/**
	 * 
	 * 功能描述：替换HTML特殊字符
	 * 
	 * @param content
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2014年3月10日
	 *
	 */
	public static String htmlSpecialChars(String content) {
		if(StringUtils.isBlank(content)){
			return "";
		}
		content = content.replaceAll("&", "&amp;");
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
		content = content.replaceAll("\"", "&quot;");
		content = content.replaceAll("&mdash;", "");
		return content;
	}
	
	/**
	 * 
	 * 功能描述：替换xml特殊字符
	 * 
	 * @param content
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2014年3月10日
	 *
	 */
	public static String xmlSpecialChars(String content) {
		if(StringUtils.isBlank(content)){
			return "";
		}
		content = content.replaceAll("\r","");
		content = content.replaceAll("\n","");
		content = content.replaceAll("\t","");
		content = content.replaceAll(">(\\s)+<","><");
		return content;
	}
	
	/**
	 * 
	* 方法描述: 将xml解析成map对象
	*
	* @param xml
	* @return
	* @throws Exception
	* @author w1025-test10
	* @createDate 2016-10-9 下午3:20:48
	 */
	public static Map<String, String> readStringXmlOut(String xml) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        org.dom4j.Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            org.dom4j.Element rootElt = doc.getRootElement(); // 获取根节点

            @SuppressWarnings("unchecked")
			Iterator<Element> iter = rootElt.elementIterator(); // 获取根节点下的子节点
            // 遍历子节点
            while (iter.hasNext()) {
            	org.dom4j.Element nodeElt = (org.dom4j.Element) iter.next();
                map.put(nodeElt.getName(), nodeElt.getTextTrim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("将xml格式数据解析成map格式出错");
        }
        return map;
    }
}
