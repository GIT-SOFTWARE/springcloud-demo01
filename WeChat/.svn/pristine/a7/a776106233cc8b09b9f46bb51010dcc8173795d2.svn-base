/*
、 *  文件创建时间： 2013-08-06
 *  文件创建者: wuzhixiong
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.wechat.respository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.biostime.app.policyandnews.entity.Notice;
import com.biostime.entity.base.Member;
import com.biostime.exception.base.RepositoryException;
import com.biostime.repository.impl.BaseRepositoryOracleImpl;
import com.biostime.utils.DateUtil;
import com.biostime.utils.weixin.util.WeixinUtilForMenber;


/**
 * 
 * 类功能描述：Repository模版
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author hehaujun
 * @version 1.0
 * @since 2013-09-06
 *
 */
@Repository
public class MemberRepository extends BaseRepositoryOracleImpl<Notice, Long> {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDealerMemberMap() {
		return (List<Map<String, Object>>)this.getHibernateTemplate().execute(new HibernateCallback<List<Map<String, Object>>>() {
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				StringBuffer sql = new StringBuffer();	
				sql.append("select 'J' || a.mobile userid,\n"+
						"a.mobile,a.name,b.dealer_name position\n"+
						"  FROM dealer_plat.dl_contact a\n" +
						"  left join dealer_plat.dl_dealer b on a.dealer_id = b.id\n" + 
						"  where a.rowid in (\n" +
						"				select min(t.rowid)\n"+
						"				from dealer_plat.dl_contact t\n"+
						"				where t.type = 0 and t.dealer_id is not null\n"+
						"						and t.name is not null and length(t.mobile) = 11 and t.position=1\n"+
						"				group by mobile)\n"+
						"	and b.status = 1"
						);
				SQLQuery query = session.createSQLQuery(sql.toString());
				List<Map<String, Object>> map = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return map;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Member> getDealerMemberList() {
		return (List<Member>)this.getHibernateTemplate().execute(new HibernateCallback<List<Member>>() {
			public List<Member> doInHibernate(Session session) throws HibernateException {
				StringBuffer sql = new StringBuffer();
				sql.append("select 'J' || a.mobile userid,\n"+
						"a.mobile,a.name,b.dealer_name position\n"+
						"  FROM dealer_plat.dl_contact a\n" +
						"  left join dealer_plat.dl_dealer b on a.dealer_id = b.id\n" + 
						"  where a.rowid in (\n" +
						"				select min(t.rowid)\n"+
						"				from dealer_plat.dl_contact t\n"+
						"				where t.type = 0 and t.dealer_id is not null\n"+
						"						and t.name is not null and length(t.mobile) = 11 and t.position=1\n"+
						"				group by mobile)\n"+
						"	and b.status = 1"
						);
				Query query = session.createSQLQuery(sql.toString())
						.addScalar("userid", Hibernate.STRING)
						.addScalar("name", Hibernate.STRING)
						.addScalar("mobile",Hibernate.STRING)
						.addScalar("position",Hibernate.STRING)
						.setResultTransformer(Transformers.aliasToBean(Member.class));
				return query.list();
			}
		});
	}
	

}