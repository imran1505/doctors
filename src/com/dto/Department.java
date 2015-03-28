package com.dto;

import java.util.HashMap;
import java.util.Map;

public enum Department {
	ENT(1, "ENT"), ORTHO(2, "Orthopedics"), PHYSICIAN(3, "Physician") , SURGERY(4, "Surgery"),
	SKIN(5, "SKIN");

	private int departmentId;
	private String departmentName;
	
	private static Map<String, Department> nameDepartmentMapping = new HashMap<String, Department>();
	
	static{
		for(Department department : Department.values()){
			nameDepartmentMapping.put(department.getDepartmentName(),department);
		}
	}
	
	private Department(int departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public static Department getDeparmentByName(String departmentName){
		return nameDepartmentMapping.get(departmentName);
	}
	
}
