package com.biostime.util;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biostime.app.settings.entity.DlOaInterfaceLog;
import com.biostime.app.settings.entity.DlOaInterfaceLogId;
import com.biostime.app.settings.repository.LogsRepository;
import com.biostime.exception.base.RepositoryException;
import com.biostime.exception.base.ServiceException;
import com.biostime.utils.DateUtil;

/**
 * 
 * 类功能描述：DB日志工具类
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:huanglong2@biostime.com">huanglong2</a>
 * @version DEALER 5.0
 * @since 2016年7月29日
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class LogsUtil{
	
	private static Logger log = LogUtil.getLogger(LogsUtil.class);
	
	@Autowired
	LogsRepository logsRepository;
	
	public enum Level{  
		TRACE(1), DEBUG(2),INFO(3),ERROR(4);  
		private int value;
		private Level(int value) {
			this.value = value;
		}
		public int value() {
			return this.value;
		}
	}
	
	
	
	/**
	 * 功能描述：写OA日志
	 * @param oaApplyId
	 * @param interfaceName
	 * @param inParam
	 * @param handleResult
	 * @param handleMessage
	 * @param applyDate
	 * @return
	 * @throws RepositoryException 
	 * @author HL
	 * @version DEALER 4.0
	 * @since 2016年1月27日
	 */
	public int addOaLog(String oaApplyId,String interfaceName,String inParam,String handleResult,String handleMessage,String applyDate) {
		try{
			DlOaInterfaceLogId oaInterfaceLog = new DlOaInterfaceLogId();
			oaInterfaceLog.setOaApplyId(oaApplyId);
			oaInterfaceLog.setInterfaceName(interfaceName);
			oaInterfaceLog.setInParam(inParam);
			oaInterfaceLog.setHandleResult(handleResult);
			oaInterfaceLog.setHandleMessage(handleMessage);
			oaInterfaceLog.setApplyTime(applyDate);
			oaInterfaceLog.setInsertTime(DateUtil.getCurrentDate());
			DlOaInterfaceLog d = new DlOaInterfaceLog(oaInterfaceLog);
			logsRepository.save(d);
		}catch(Exception e){
			error("addOaLog error:" + e.getMessage(),"SYS");
		}
		return 1;
	}
	
	
	
	/**
	 * 
	 * 功能描述：通用日志方法
	 * 
	 * @param msg 日志
	 * @param createBy 创建人 
	 * @author HL
	 * @version DEALER 5.0
	 * @since 2015年9月11日
	 *
	 */
	public void trace(String msg,String createBy){
		logs(msg,createBy,Level.TRACE,null);
	};
	
	public void debug(String msg,String createBy){
		logs(msg,createBy,Level.DEBUG,null);
	};
	
	public void info(String msg,String createBy){
		logs(msg,createBy,Level.INFO,null);
	};
	
	public void error(String msg,String createBy){
		logs(msg,createBy,Level.ERROR,null);
	};
	
	
	public void trace(String msg,String createBy,String remark){
		logs(msg,createBy,Level.TRACE,remark);
	};
	
	public void debug(String msg,String createBy,String remark){
		logs(msg,createBy,Level.DEBUG,remark);
	};
	
	public void info(String msg,String createBy,String remark){
		logs(msg,createBy,Level.INFO,remark);
	};
	
	public void error(String msg,String createBy,String remark){
		logs(msg,createBy,Level.ERROR,remark);
	};
	
	public void logs(String msg,String createBy,Level level,String remark){
		try{
			StackTraceElement traceInfo = null;
			String method = "";
			int rowNum = 0;
			traceInfo = getTraceInfo();
			if(traceInfo!=null){
				method = traceInfo.getClassName()+"."+traceInfo.getMethodName();
				rowNum = traceInfo.getLineNumber();
				logsRepository.executeSql("insert into DL_DEALER_LOGS\n" + 
						"  (ID,\n" + 
						"   LOG_MSG,\n" + 
						"   LOG_OPERATOR,\n" + 
						"   LOG_LEVEL,\n" + 
						"   LOG_TIME,\n" + 
						"   LOG_TYPE,\n" + 
						"   LOG_METHOD,\n" + 
						"   LOG_ROWNUM,LOG_REMARK) VALUES (SEQ_DEALER_LOGS.Nextval, ?, ?, ?, SYSDATE, ?, ?, ?, ?)",
						new Object[]{msg,createBy,level.value,"JAVA",method,rowNum,StringUtils.trimToEmpty(remark)});
			}else{
				LogUtil.error(log, LogFormat.PLAIN, "LogsUtil traceInfo is null,msg = " + msg);
			}
		}catch(Exception e){
			LogUtil.error(log, LogFormat.PLAIN, "LogsUtil error:" +e.getMessage());
		}
	}


	/**
	 * 
	 * 功能描述：获取堆栈中的信息
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2016年7月28日
	 */
	public StackTraceElement getTraceInfo() {
		StackTraceElement[] temp=Thread.currentThread().getStackTrace();
		if(temp.length>3){
			for(StackTraceElement e:temp){
				if(e.getClassName().startsWith("com.biostime")&&e.getClassName().indexOf("LogsUtil")==-1&&e.getLineNumber()!=-1){
					return e;
				}
			}
		}
		return null;
	};
	
	
	private void logs(String msg,String createBy,Level level){
		logs(msg, createBy, level, null);
	}

	
	
	
	
	
	/**
	 * 功能描述：通用日志方法
	 * @param e  异常
	 * @param createBy 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2016年7月29日
	 */
	public void error(Exception error,String createBy){
		try{
			String msg = StringUtils.defaultIfEmpty(error.getMessage(),String.valueOf(error));
			String method = "";
			int rowNum = 0;
			StackTraceElement[] temp= error.getStackTrace();
			for(StackTraceElement traceInfo:temp){
				if(traceInfo.getClassName().startsWith("com.biostime")&&traceInfo.getClassName().indexOf("LogsUtil")==-1&&traceInfo.getLineNumber()!=-1){
					method += traceInfo.getClassName()+"."+traceInfo.getMethodName()+":"+traceInfo.getLineNumber()+"\r\n";
					if(rowNum==0){
						rowNum = traceInfo.getLineNumber();//行数已堆栈第一条为准
					}
					if(method.length()>300){//数据库字段长度500，这里只获取一部分的堆栈信息
						break;
					}
				}
			}
			logsRepository.executeSql("insert into DL_DEALER_LOGS\n" + 
					"  (ID,\n" + 
					"   LOG_MSG,\n" + 
					"   LOG_OPERATOR,\n" + 
					"   LOG_LEVEL,\n" + 
					"   LOG_TIME,\n" + 
					"   LOG_TYPE,\n" + 
					"   LOG_METHOD,\n" + 
					"   LOG_ROWNUM,LOG_REMARK) VALUES (SEQ_DEALER_LOGS.Nextval, ?, ?, ?, SYSDATE, ?, ?, ?, ?)",
					new Object[]{msg,createBy,Level.ERROR.value,"JAVA",method,rowNum,null});
		}catch(Exception e){
			LogUtil.error(log, LogFormat.PLAIN, "LogsUtil error:" +e.getMessage());
		}
	};
	
	/**
	 * 功能描述：通用日志方法(默认创建人)
	 * @param e  异常
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2016年7月29日
	 */
	public void error(Exception e){
		error(e,"SYS");
	};
	
}
