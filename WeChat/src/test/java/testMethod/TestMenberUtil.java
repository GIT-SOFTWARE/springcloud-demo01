package testMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.biostime.entity.base.Member;
import com.biostime.utils.DateUtil;
import com.biostime.utils.weixin.util.WeixinUtilForMenber;

/**
 * 
 * 类功能描述：成员管理测试类
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:12624HL@biostime.com">12624HL</a>
 * @version DEALER 5.0
 * @since 2017年1月9日
 */
public class TestMenberUtil {
	
	//经销商部门ID	
	String dealerDeptId = "10964";

	@Test
	public void test0(){
		String reg = "^[1-9][0-9]{1,25}$";
		System.out.println("开始");
        String s = "23a4524524524242544";
        System.out.println(s.matches(reg));

	}

	@Test
	public void testListDepartment(){
		JSONObject json = WeixinUtilForMenber.listDepartment(dealerDeptId);
		System.out.println(json.toString());
	}
	
	
	@Test
	public void testCreateDepartment(){
		JSONObject json = WeixinUtilForMenber.createDepartment(dealerDeptId,"9999","测试经销商部门");
		System.out.println(json.toString());
	}
	
	@Test
	public void testUpdateDepartment(){
		JSONObject json = WeixinUtilForMenber.updateDepartment("9999","测试经销商部门"+DateUtil.getCurrentStringDate());
		System.out.println(json.toString());
	}
	
	@Test
	public void testDeleteDepartment(){
		JSONObject json = WeixinUtilForMenber.deleteDepartment("9999");
		System.out.println(json.toString());
	}
	
	@Test
	public void testCreateMember(){
		Member m = new Member();
		m.setUserid("T001");
		m.setName("黄龙");
		List<Integer> dept = new ArrayList<Integer>();
		dept.add(9999);
		m.setDepartment(dept);
		m.setPosition("测试职位");
		m.setMobile("1364008299");
		m.setGender(0);
		m.setEmail("327409476@qq.com");
		m.setEnable("1");
		JSONObject json = WeixinUtilForMenber.createMember(m);
		System.out.println(json.toString());
	}
	
	@Test
	public void testDeleteMember(){
		JSONObject json = WeixinUtilForMenber.deleteMember("T001");
		System.out.println(json.toString());
	}
	
	
	@Test
	public void testUpdateMember(){
		Member m = new Member();
		m.setUserid("T001");
		m.setName("黄龙T");
		List<Integer> dept = new ArrayList<Integer>();
		dept.add(9999);
		m.setDepartment(dept);
		m.setPosition("测试职位T");
		m.setMobile("13640082111");
		m.setGender(1);
		m.setEmail("321456@qq.com");
		m.setEnable("0");
		JSONObject json = WeixinUtilForMenber.updateMember(m);
		System.out.println(json.toString());
	}
	
	
//	@Test
//	public void testListDeptMember(){
//		JSONObject json = WeixinUtilForMenber.listDeptMember(dealerDeptId);
//		List<Member> list = (List<Member>) JSONArray.toCollection(json.getJSONArray("userlist"), Member.class);
//		System.out.println(list.size());
//		for(int i=0;i<10;i++){
//			if(list.get(i)!=null){
//				System.out.println(list.get(i).getUserid() + "-" +list.get(i).getName());
//			}
//		}
//	}
	
}
