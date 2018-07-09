package com.biostime.app.setting.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.biostime.app.setting.bean.Invoice;
import com.biostime.app.setting.bean.InvoiceElec;
import com.biostime.repository.impl.BaseRepositoryOracleImpl;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

@SuppressWarnings("rawtypes")
@Repository
public class InvoiceRepository extends BaseRepositoryOracleImpl {
	private final static Logger logger = LogUtil.getLogger(InvoiceRepository.class);


	public Map<String,Object> saveInvoiceList(List<Invoice> invoices){
		String sql = "insert into nc63.bd_invoice@linknc(expressno,billno,sentday) values(?,?,?)";
		int ret = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		List<String[]> failnos = new ArrayList<String[]>();
		for(Invoice invoice:invoices){
			String expressno = invoice.getExpressno();
			String billno = invoice.getBillno();
			String sendday = invoice.getSentday();
			try {
				executeSql(sql,new Object[]{expressno,billno,sendday});
				ret++;
			} catch (Exception e) {
				LogUtil.info(logger, LogFormat.PLAIN, "saveInvoiceList:"+e.getMessage());
				String[] ss = new String[]{billno,expressno};
				failnos.add(ss);
			}
		}
		map.put("success", ret);
		map.put("failnos", failnos);
		return map;
	}
	
	public Map<String,Object> updateInvoiceList(List<Invoice> invoices){
		
		String updateSql = "update nc63.bd_invoice@linknc set receiveday=?,receiver=? where billno=?";
		String insertSql = "insert into nc63.bd_invoice@linknc(receiveday,receiver,billno) values(?,?,?)";
		int ret = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> failnos = new ArrayList<String>();
		for(Invoice invoice:invoices){
			String receiveday = invoice.getReceiveday();
			String billno = invoice.getBillno();
			String receiver = invoice.getReceiver();
			
			Map<String, String> in = queryByBillno(billno);
			if(in==null){
				executeSql(insertSql,new Object[]{receiveday,receiver,billno});
				ret++;
			}else{
				String re = in.get("RECEIVER");
				if(StringUtils.isBlank(re)){
					//未签收
					executeSql(updateSql,new Object[]{receiveday,receiver,billno});
					ret++;
				}else{
					//已签收
					failnos.add(billno);
				}
			}
			
//			try {
//				executeSql(insertSql,new Object[]{receiveday,receiver,billno});
//				failnos.add(billno);
//			} catch (Exception e) {
//				LogUtil.info(logger, LogFormat.PLAIN, "updateInvoiceList:"+e.getMessage());
//				executeSql(updateSql,new Object[]{receiveday,receiver,billno});
//				ret++;
//			}
			
		}
		map.put("success", ret);
		map.put("failnos", failnos);
		return map;
	}
	
	public Map<String,Object> updateInvoiceElecList(List<InvoiceElec> invoices){
		String sql = "insert into nc63.bd_invoice_elec@linknc(invoice,billno,day,receiver) values(?,?,?,?)";
		int ret = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		List<String[]> failnos = new ArrayList<String[]>();
		for(InvoiceElec invoice:invoices){
			String in = invoice.getInvoice();
			String billno = invoice.getBillno();
			String sendday = invoice.getDay();
			String receiver = invoice.getReceiver();
			try {
				executeSql(sql,new Object[]{in,billno,sendday,receiver});
				ret++;
			} catch (Exception e) {
				String[] ss = new String[]{in,billno};
				failnos.add(ss);
				LogUtil.info(logger, LogFormat.PLAIN, "updateInvoiceElecList:"+e.getMessage());
			}
		}
		map.put("success", ret);
		map.put("failnos", failnos);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> queryMyBill(final String code) {
		return (List<Map<String, String>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, String>> doInHibernate(Session session) throws HibernateException {
				String sql = "select t1.billno billno,t2.sentday sentday,t2.receiveday receiveday,t1.approveday approveday from nc63.v_billfor_qjgl@linknc t1,nc63.bd_invoice@linknc t2 where t1.billno=t2.billno(+) and t1.billmakercode=:code order by t2.billno";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("code", code);
				List<Map<String, String>> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return list;
			}
		});
	}
	
	
	public Map<String, String> queryByBillno(final String billno) {
		return (Map<String, String>)getHibernateTemplate().execute(new HibernateCallback() {
			public Map<String, String> doInHibernate(Session session) throws HibernateException {
				String sql = "select * from  nc63.bd_invoice@linknc t where t.billno=:billno";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("billno", billno);
				Map<String, String> invoice = (Map<String, String>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
				return invoice;
			}
		});
	}
	
	
	public List<Map<String, String>> queryReceiveBill() {
		return (List<Map<String, String>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, String>> doInHibernate(Session session) throws HibernateException {
				String sql = "select t1.billno,t3.code from nc63.bd_invoice@linknc t1,nc63.er_bxzb@linknc t2,nc63.bd_psndoc@linknc t3 where t1.billno=t2.djbh and t2.jkbxr=t3.pk_psndoc and t1.receiver is not null and t1.receivemessage is null";
				SQLQuery query = session.createSQLQuery(sql);
				List<Map<String, String>> invoice = (List<Map<String, String>>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return invoice;
			}
		});
	}
	
	public int updateInvoiceReceive(String billno){
		String sql = "update nc63.bd_invoice@linknc set receivemessage='Y' where billno=?";
		int ret = 0;
		try {
			executeSql(sql,new Object[]{billno});
			ret++;
		} catch (Exception e) {
			LogUtil.info(logger, LogFormat.PLAIN, "updateInvoiceReceive:"+e.getMessage());
		}
		return ret;
	}
	
	
	public List<Map<String, String>> queryPayBill() {
		return (List<Map<String, String>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, String>> doInHibernate(Session session) throws HibernateException {
				String sql = "select t1.billno,t1.billmakercode from nc63.v_billfor_qjglmessage@linknc t1,nc63.bd_invoice@linknc t2 where t1.billno=t2.billno and t2.ispay is null";
				SQLQuery query = session.createSQLQuery(sql);
				List<Map<String, String>> invoice = (List<Map<String, String>>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return invoice;
			}
		});
	}
	
	public int updateInvoicePay(String billno){
		String sql = "update nc63.bd_invoice@linknc set ispay='Y' where billno=?";
		int ret = 0;
		try {
			executeSql(sql,new Object[]{billno});
			ret++;
		} catch (Exception e) {
			LogUtil.info(logger, LogFormat.PLAIN, "updateInvoicePay:"+e.getMessage());
		}
		return ret;
	}
	
	
	public List<Map<String, String>> getInvoiceAuth(){
		return (List<Map<String, String>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, String>> doInHibernate(Session session) throws HibernateException {
				String sql = "select code from nc63.qjglaccess_hsy@linknc where accesstype=1";
				SQLQuery query = session.createSQLQuery(sql);
				List<Map<String, String>> invoice = (List<Map<String, String>>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				LogUtil.info(logger, LogFormat.PLAIN, "getInvoiceAuth:"+invoice);
				return invoice;
			}
		});
	}
}
