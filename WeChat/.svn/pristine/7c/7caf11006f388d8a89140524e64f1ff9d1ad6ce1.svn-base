package com.biostime.utils;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.biostime.exception.base.ServiceException;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;
import net.sf.json.JSONObject;

public class WechatWebUtil {
	
	private static Logger log = LogUtil.getLogger(WechatWebUtil.class);
	 
	final static String url = "https://qywx.biostime.com/api/wxmsg/WxMessage.asmx?WSDL";  
	final static String SOAPAction ="https://qywx.biostime.com/api/wxmsg/WxMessage.asmx?WSDL";
	
	
    
	public static void main(String[] args){
		try {
			WechatWebUtil service = new WechatWebUtil();
			String account="12125";
			String billno = "BX170804520";
			String message="您的"+billno+"单据的发票财务已经签收！";
			String returnMessage =service.sendMessage(account, message);
			System.out.println("返回信息："+returnMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String sendMessage(String account,String message) throws ServiceException{

		String userName="admgr";
		String password=EncryptUtil.md5("Biostime123").toUpperCase();
		String toparty ="";
		String agentid="0";
		String safe="0";
		String sendTime ="";
		
		String returnMessage="";
        
        try {  
            // 设置SOAP内容格式  
            String soap ="<?xml version=\"1.0\" encoding=\"utf-8\"?> "+
					"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
					"<soap:Header>"+
					    "<MSoapHeader xmlns=\"http://tempuri.org/\">"+
					      "<Uname>"+userName+"</Uname>"+
					      "<Password>"+password+"</Password>"+
					    "</MSoapHeader>"+
					  "</soap:Header>"+
					  "<soap:Body>"+
					    "<SendTextMsg xmlns=\"http://tempuri.org/\">"+
					      "<touser>"+account+"</touser>"+
					      "<toparty>"+toparty+"</toparty>"+
					      "<agentid>"+agentid+"</agentid>"+
					      "<content>"+message+"</content>"+
					      "<safe>"+safe+"</safe>"+
					      "<SendTime>"+sendTime+"</SendTime>"+
					    "</SendTextMsg>"+
					  "</soap:Body>"+
					"</soap:Envelope>"; 
            InputStream is = new ByteArrayInputStream(soap.getBytes("UTF-8"));  
            // 创建message对象  
            MessageFactory mf =MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);  
            SOAPMessage soapMessage = mf.createMessage(null, is);  
            // 设置SOAPAction  
            soapMessage.getMimeHeaders().addHeader("SOAPAction",SOAPAction);  
            //设置请求字符格式  
            soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "utf-8");  
            // 获取envelope  
            SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();  
            // 设置  header  KytSoapHeader 用户名和密码  
            SOAPHeader header = envelope.getHeader();  
            SOAPElement headerElement = header.addChildElement(envelope  
                    .createName("KytSoapHeader","", "http://tempuri.org/"));  
            headerElement.addChildElement("UserName").addTextNode(userName);  
            headerElement.addChildElement("PassWord").addTextNode(password);  
            //设置Body  
            SOAPBody body = envelope.getBody();  
              
            // 保存Message  
            soapMessage.saveChanges();  
  
            //开始访问接口  
            //创建连接对象  
            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();  
            //发送请求  
            SOAPMessage reply = connection.call(soapMessage, url);  
            //关闭连接对象  
            connection.close();  
  
            //访问结束开始处理结果  
            //获取节点 KYTResults  
            org.w3c.dom.Node node = reply.getSOAPBody().getFirstChild().getFirstChild().getFirstChild();  
              
            System.out.println("Node Name:"+node.getLocalName()+"\n");  
            /*System.out.println("Node Text:"+node.getTextContent());  */
              
            //System.out.println(((SOAPElement)reply.getSOAPBody().getChildElements().next()).getTextContent());  
  
            System.out.println("\nResponse Content:");  
            //将读取的内容读出来  
            ByteArrayOutputStream out= new ByteArrayOutputStream();  
            reply.writeTo(out);  
            InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(out.toByteArray()), "utf-8");  
            BufferedReader br = new BufferedReader(isr);  
            while (br.ready()) {  
                //String inputLine;  
            	String msg =br.readLine().replaceAll("><", ">\n<");
                returnMessage=returnMessage+msg;
            }  
            br.close();  
            
            Document doc = parseXMLDocument(returnMessage);
            String errmsg ="";
            errmsg =DocumentUtil.getValue(doc,0,"SendTextMsgResult");
            System.out.println("errmsg:"+errmsg);
            
            if(StringUtils.isNotEmpty(errmsg)){
	            JSONObject resultJSON = JSONObject.fromObject(errmsg);  
	            Map mapp = (Map)JSONObject.toBean(resultJSON, Map.class);  
	            returnMessage =mapp.get("errmsg").toString();
	            System.out.println("map返回值："+mapp.get("errmsg"));  
	            System.out.println("map返回值："+mapp.get("errcode"));  
           }else{
        	   returnMessage="接口没有收到返回信息";
           }
            
        } catch (Exception e) {  
            e.printStackTrace();
        }
        
        return returnMessage;
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
	 * @throws Exception 
	 * @since 2013年11月22日
	 *
	 */
	public static Document parseXMLDocument(String xmlString) throws Exception { 
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
			throw new Exception(e.getMessage()); 
		} finally{
			if(sr!=null){
				sr.close();
			}
		}
	}
	
}
