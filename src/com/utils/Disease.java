package com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Disease {

	private static Map<String, ArrayList<String>> diseaseMap = new HashMap<String, ArrayList<String>>();

	static {
		//malaria
		ArrayList<String> malariaList = new ArrayList<>();
		malariaList.add("shivering");
		malariaList.add("fever");
		malariaList.add("cold");
		diseaseMap.put("Malaria",malariaList);


		//dengue
		ArrayList<String> dengueList = new ArrayList<>();
		dengueList.add("fever");
		dengueList.add("headache");
		dengueList.add("muscle pain");
		dengueList.add("vomiting");
		dengueList.add("nausea");
		diseaseMap.put("Dengue",dengueList);

		//typhoid

		ArrayList<String> typhoidList = new ArrayList<>();
		typhoidList.add("poor appetite");
		typhoidList.add("headache");
		typhoidList.add("fever");
		typhoidList.add("diarrhea");
		diseaseMap.put("Typhoid",typhoidList);

	}


	public static ArrayList<String> getDiseaseSymptoms(String disease){
		return diseaseMap.get(disease);
	}
	
	public static String getFormattedDiseaseSymptoms(String disease){
		
		ArrayList<String> symptoms = diseaseMap.get(disease);
		String result = "<ul>";
		for(String symptom: symptoms){
			result+="<li>"+symptom+"</li>";
		}
		result+="</ul>";
		return result;
	}

	public static ArrayList<String> findDisease(ArrayList<String> symptoms){

		ArrayList<String> diseaseList = new ArrayList<>();
		for (Map.Entry<String, ArrayList<String>> entry : diseaseMap.entrySet())
		{

			String diseaseName = entry.getKey();
			ArrayList<String> diseaseSymptoms = entry.getValue();
			int count = 0;
			for(String symptom :symptoms){
				if(diseaseSymptoms.contains(symptom.toLowerCase())){
					count++;
				}
				if(count>=2){
					diseaseList.add(diseaseName);
					count=0;
					break;
				}
			}
		}
		return diseaseList;
	}
}
