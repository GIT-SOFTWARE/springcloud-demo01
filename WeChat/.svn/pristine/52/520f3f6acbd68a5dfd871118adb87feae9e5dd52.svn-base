package com.biostime.utils;


import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



public class XmlUtil {
	
	private static Logger log = LogUtil.getLogger(XmlUtil.class);
	
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	
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
		Node node = doc.getElementsByTagName(nodeName).item(index).getFirstChild();
		if(node!=null){
			return node.getNodeValue();
		}else{
			return null;
		}
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
		return doc.getElementsByTagName(nodeName).item(index).getAttributes().getNamedItem("id").getNodeValue();
	}
	/**
	 * 
	 * 
	 * 功能描述：格式化XML文件
	 * 
	 * @param xmlString
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月22日
	 *
	 */
	public static Document parseXMLDocument(String xmlString) { 
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
			LogUtil.error(log, LogFormat.PLAIN, "errorMessage="+e.getMessage());
			e.printStackTrace();
		} finally{
			if(sr!=null){
				sr.close();
			}
		}
		return null;
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
	public static String toString(Document doc) { 
		ByteArrayOutputStream  bos = null;
		try {
			TransformerFactory  tf  =  TransformerFactory.newInstance();    
			Transformer t = tf.newTransformer();    
			t.setOutputProperty("encoding","GBK");
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
	 * 功能描述：xml doc转string
	 * 
	 * @param doc
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月22日
	 *
	 */
	public static String utfToString(Document doc) { 
		ByteArrayOutputStream  bos = null;
		try {
			TransformerFactory  tf  =  TransformerFactory.newInstance();    
			Transformer t = tf.newTransformer();    
			t.setOutputProperty("encoding","UTF-8");
			bos =  new  ByteArrayOutputStream();   
			t.transform(new DOMSource(doc), new StreamResult(bos));
		} catch (TransformerException e) {
			LogUtil.error(log, LogFormat.PLAIN, "errorMessage="+e.getMessage());
			e.printStackTrace();
		}
		return bos.toString();   
	} 
	
	public static void main(String[] args){
		try {
			//getStockInfo("1506000005,1506000006,1506000007,1506000008,1507010001,1511020008,1511020009,1511020010,1511020011,1511020012,1511020013,1512000001,1512000002,1512000003,1512000004");
			
			//getUsersByDate("2013-12-22");
			
			/*FileWriter fw = new FileWriter("D:\\c.csv"); 
			fw.write(sb.toString());
			fw.close();*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
