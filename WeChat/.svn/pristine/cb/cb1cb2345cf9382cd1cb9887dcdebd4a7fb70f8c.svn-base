package com.biostime.app.setting.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biostime.app.setting.repository.ForeignOARepository;
import com.biostime.app.setting.service.ForeignOaService;

@Service
public class ForeignOaServiceImpl implements ForeignOaService {
	
	private static final Logger log = Logger.getLogger(ForeignOaServiceImpl.class);
	
	@Autowired
	ForeignOARepository foreignOARepository;

	public List<Map<String, Object>> GetOAPersonnelInfo(String workcode,String date){
		try {
			List<Map<String, Object>> ret = foreignOARepository.getOAPersonnelInfo(workcode, date);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("GetOAPersonnelInfo error------>"+e.getMessage());
			return null;
		}
	}
	
	public List<Map<String, Object>> GetOASegmentInfo(){
		try {
			List<Map<String, Object>> ret = foreignOARepository.getOASegmentInfo();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("GetOASegmentInfo error------>"+e.getMessage());
			return null;
		}
	}
	
	public List<Map<String, Object>> GetOADepartmentInfo(){
		try {
			List<Map<String, Object>> ret = foreignOARepository.getOADepartmentInfo();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("GetOADepartmentInfo error------>"+e.getMessage());
			return null;
		}
	}
	
	public Map<String, String> getInterfacepurview(final String loginid,final String password){
		return foreignOARepository.getInterfacepurview(loginid, password);
	}
}
