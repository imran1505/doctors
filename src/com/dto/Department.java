package com.dto;

import java.util.HashMap;
import java.util.Map;

public enum Department {
	ENT(1, "ent"), ORTHO(2, "ortho"), PHYSICIAN(3, "physician");

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