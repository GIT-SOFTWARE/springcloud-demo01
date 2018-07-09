package com.biostime.app.timer;



import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.biostime.app.setting.repository.InvoiceRepository;
import com.biostime.exception.base.ServiceException;
import com.biostime.util.PropUtil;
import com.biostime.utils.WechatWebUtil;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;


@Component
@Transactional(rollbackFor=Exception.class)
public class InvoiceTimer {
	//是否进行同步
	private static final boolean INVOICESYSC = Boolean.parseBoolean(PropUtil.getProp("invoiceSysc"));
	private static Logger log = LogUtil.getLogger(InvoiceTimer.class);
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Scheduled(cron="0 0/10 * * * ?") //需要注意@Scheduled这个注解，它可配置多个属性：cron\fixedDelay\fixedRate
	public void queryReceiveBill() throws ServiceException {
		LogUtil.info(log, LogFormat.PLAIN, "queryReceiveBill INVOICESYSC:" + INVOICESYSC);
		if(INVOICESYSC){
			try{
				List<Map<String, String>> list = invoiceRepository.queryReceiveBill();
				LogUtil.info(log, LogFormat.PLAIN, "queryReceiveBill list:" + list);
				if(list!=null){
					for(Map<String, String> map:list){
						String billno = map.get("BILLNO");
						String code = map.get("CODE");
						WechatWebUtil util = new WechatWebUtil();
						LogUtil.info(log, LogFormat.PLAIN, "billno:" + billno + ",code:"+code);
						String ret = util.sendMessage(code, "您的"+billno+"单据的发票财务已经签收！");
						LogUtil.info(log, LogFormat.PLAIN, "queryReceiveBill ret:" + ret);
						if("ok".equals(ret)){
							invoiceRepository.updateInvoiceReceive(billno);
						}
						
					}
				}
			}catch(Exception e){
				LogUtil.error(log, LogFormat.PLAIN, "queryReceiveBill error:" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}
	}

	@Scheduled(cron="0 0/10 * * * ?") //需要注意@Scheduled这个注解，它可配置多个属性：cron\fixedDelay\fixedRate
	public void queryPayBill() throws ServiceException {
		if(INVOICESYSC){
			try{
				List<Map<String, String>> list = invoiceRepository.queryPayBill();
				LogUtil.info(log, LogFormat.PLAIN, "queryPayBill list:" + list);
				if(list!=null){
					for(Map<String, String> map:list){
						String billno = map.get("BILLNO");
						String code = map.get("BILLMAKERCODE");
						String ret = WechatWebUtil.sendMessage(code, "您的"+billno+"单据已经付款成功！");
						if("ok".equals(ret)){
							invoiceRepository.updateInvoicePay(billno);
						}
						
					}
				}
			}catch(Exception e){
				LogUtil.error(log, LogFormat.PLAIN, "invoiceSysc error:" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}
	}
}
