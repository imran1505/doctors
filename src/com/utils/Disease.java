package com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dto.Department;

public class Disease {

	private static Map<String, ArrayList<String>> diseaseMap = new HashMap<String, ArrayList<String>>();
	private static Map<String, Department> diseaseDepartmentMap = new HashMap<String, Department>();

	static {
		//malaria
		ArrayList<String> malariaList = new ArrayList<>();
		malariaList.add("shivering");
		malariaList.add("fever");
		malariaList.add("cold");
		diseaseMap.put("Malaria",malariaList);
		diseaseDepartmentMap.put("Malaria",Department.PHYSICIAN);


		//dengue
		ArrayList<String> dengueList = new ArrayList<>();
		dengueList.add("fever");
		dengueList.add("headache");
		dengueList.add("muscle pain");
		dengueList.add("vomiting");
		dengueList.add("nausea");
		diseaseMap.put("Dengue",dengueList);
		diseaseDepartmentMap.put("Dengue",Department.PHYSICIAN);

		//typhoid

		ArrayList<String> typhoidList = new ArrayList<>();
		typhoidList.add("poor appetite");
		typhoidList.add("headache");
		typhoidList.add("fever");
		typhoidList.add("diarrhea");
		diseaseMap.put("Typhoid",typhoidList);
		diseaseDepartmentMap.put("Typhoid",Department.PHYSICIAN);

		//measles

		ArrayList<String> measlesList = new ArrayList<>();
		measlesList.add("red eyes");
		measlesList.add("runny nose");
		measlesList.add("fever");
		measlesList.add("red rashses");
		diseaseMap.put("Measles",measlesList);
		diseaseDepartmentMap.put("Measles",Department.PHYSICIAN);
		

		//cholera

		ArrayList<String> choleraList = new ArrayList<>();
		choleraList.add("severe diarrhea");
		choleraList.add("vomiting");
		choleraList.add("leg cramps");
		diseaseMap.put("Cholera",choleraList);
		diseaseDepartmentMap.put("Cholera",Department.PHYSICIAN);

		//mumps

		ArrayList<String> mumpsList = new ArrayList<>();
		mumpsList.add("sore muscles");
		mumpsList.add("fever");
		mumpsList.add("headache");
		mumpsList.add("fatigue");
		diseaseMap.put("Mumps",mumpsList);
		diseaseDepartmentMap.put("Mumps",Department.PHYSICIAN);

		//influenza

		ArrayList<String> influenzaList = new ArrayList<>();
		influenzaList.add("body aches");
		influenzaList.add("fever");
		influenzaList.add("headache");
		influenzaList.add("sore throat");
		influenzaList.add("shivering");
		influenzaList.add("coughing");
		diseaseMap.put("Influenza",influenzaList);
		diseaseDepartmentMap.put("Influenza",Department.PHYSICIAN);



		//scurvy

		ArrayList<String> scurvyList = new ArrayList<>();
		scurvyList.add("weakness");
		scurvyList.add("anemia");
		scurvyList.add("gum disease");
		scurvyList.add("bleeding mucus membranes");
		diseaseMap.put("Scurvy",scurvyList);
		diseaseDepartmentMap.put("Scurvy",Department.PHYSICIAN);

		//yellow fever

		ArrayList<String> yellowfeverList = new ArrayList<>();
		yellowfeverList.add("weakness");
		yellowfeverList.add("anemia");
		yellowfeverList.add("gum disease");
		yellowfeverList.add("bleeding mucus membranes");
		diseaseMap.put("Yellow Fever",yellowfeverList);
		diseaseDepartmentMap.put("Yellow Fever",Department.PHYSICIAN);

		//allergy

		ArrayList<String> allergyList = new ArrayList<>();
		allergyList.add("sneezing");
		allergyList.add(" itchy and watery eyes");
		allergyList.add("runny nose");
		allergyList.add("sore throat");
		allergyList.add("coughing");
		diseaseMap.put("Allergy",allergyList);
		diseaseDepartmentMap.put("Allergy",Department.PHYSICIAN);

		//anemia

		ArrayList<String> anemiaList = new ArrayList<>();
		anemiaList.add("weakness");
		anemiaList.add("fatigue");
		anemiaList.add("Appear pale");
		anemiaList.add("rapid heart rate");
		anemiaList.add("shortness of breath");
		diseaseMap.put("Anemia",anemiaList);
		diseaseDepartmentMap.put("Anemia",Department.PHYSICIAN);

		//anxiety

		ArrayList<String> anxietyList = new ArrayList<>();
		anxietyList.add("sweating");
		anxietyList.add("irritability");
		anxietyList.add("stress");
		anxietyList.add("rapid heart rate");
		diseaseMap.put("Anxiety",anxietyList);
		diseaseDepartmentMap.put("Anxiety",Department.PHYSICIAN);


		//appendicitis

		ArrayList<String> appendicitisList = new ArrayList<>();
		appendicitisList.add("abdominal pain");
		appendicitisList.add("loss of appetite");
		appendicitisList.add("nausea");
		appendicitisList.add("vomiting");
		appendicitisList.add("constipation or diarrhea");
		diseaseMap.put("Appendicitis",appendicitisList);
		diseaseDepartmentMap.put("Appendicitis",Department.SURGERY);

		//bladderinfection

		ArrayList<String> bladderinfectionList = new ArrayList<>();
		bladderinfectionList.add("severe pain");
		bladderinfectionList.add("discomfort");
		bladderinfectionList.add("burning when trying to urinate");
		diseaseMap.put("Bladder Infection",bladderinfectionList);
		diseaseDepartmentMap.put("Bladder Infection",Department.SKIN);



		//VaginalYeastInfection

		ArrayList<String> vaginalYeastInfectionList = new ArrayList<>();
		vaginalYeastInfectionList.add("irritation");
		vaginalYeastInfectionList.add("redness");       
		vaginalYeastInfectionList.add("swelling");
		vaginalYeastInfectionList.add("vaginal burning");
		vaginalYeastInfectionList.add("vaginal discharge");
		vaginalYeastInfectionList.add("vaginal itching");
		vaginalYeastInfectionList.add("burning when trying to urinate");                
		vaginalYeastInfectionList.add("painful intercourse");
		diseaseMap.put("Vaginal Yeast Infection",vaginalYeastInfectionList);
		diseaseDepartmentMap.put("Vaginal Yeast Infection",Department.SKIN);


		//UrinaryTractInfection (UTI)

		ArrayList<String> urinaryTractInfectionList = new ArrayList<>(); 
		urinaryTractInfectionList.add("fever");
		urinaryTractInfectionList.add("burning when trying to urinate");
		urinaryTractInfectionList.add("blood in urine");
		urinaryTractInfectionList.add("cloudy urine");      
		urinaryTractInfectionList.add("frequent urination");
		urinaryTractInfectionList.add("urinary urgency"); 
		urinaryTractInfectionList.add("rectal pain");
		diseaseMap.put("Urinary Tract Infection",urinaryTractInfectionList);
		diseaseDepartmentMap.put("Urinary Tract Infection",Department.NEPHROLOGY);



		//StomachUlcer

		ArrayList<String> stomachUlcerList = new ArrayList<>(); 
		stomachUlcerList.add("black stools");
		stomachUlcerList.add("indigestion");
		stomachUlcerList.add("loss of appetite");
		stomachUlcerList.add("upper abdominal pain");
		stomachUlcerList.add("Weight loss");
		diseaseMap.put("Stomach Ulcer",stomachUlcerList);
		diseaseDepartmentMap.put("Stomach Ulcer",Department.SURGERY);



		//Stroke 

		ArrayList<String> strokeList = new ArrayList<>(); 
		strokeList.add("weakness");
		strokeList.add("vomiting");
		strokeList.add("vision changes");
		strokeList.add("vertigo");
		strokeList.add("tingling");
		strokeList.add("paralysis");
		strokeList.add("numbness");
		strokeList.add("nausea");
		strokeList.add("gait disturbances");
		strokeList.add("double vision");
		strokeList.add("dizziness"); 
		strokeList.add("difficulty speaking"); 
		strokeList.add("change in consciousness");
		strokeList.add("confusion"); 
		diseaseMap.put("Stroke",strokeList);
		diseaseDepartmentMap.put("Stroke",Department.PHYSICIAN);



		//strepThroatSymptoms

		ArrayList<String> strepThroatSymptomsList = new ArrayList<>(); 
		strepThroatSymptomsList.add("fever");
		strepThroatSymptomsList.add("headache");
		strepThroatSymptomsList.add("nausea");
		strepThroatSymptomsList.add("painful lymph nodes");
		strepThroatSymptomsList.add("sore throat");
		strepThroatSymptomsList.add("rash");
		strepThroatSymptomsList.add("vomiting");
		strepThroatSymptomsList.add("white patches in the throat");
		diseaseMap.put("Strep Throat Symptoms",strepThroatSymptomsList);   
		diseaseDepartmentMap.put("Strep Throat Symptoms",Department.ENT);


		//sinusitis

		ArrayList<String> sinusitisList = new ArrayList<>(); 
		sinusitisList.add("fever");
		sinusitisList.add("headache");
		sinusitisList.add("jaw pain");
		sinusitisList.add("nausea");
		sinusitisList.add("sneezing");
		sinusitisList.add("sore throat"); 
		sinusitisList.add("facial swelling");
		sinusitisList.add("facial pain");
		sinusitisList.add("earache");
		sinusitisList.add("cough");
		sinusitisList.add("bad breath");
		diseaseMap.put("Sinusitis",sinusitisList); 
		diseaseDepartmentMap.put("Sinusitis",Department.PULMONARY);

		//pregnancy

		ArrayList<String> pregnancyList = new ArrayList<>(); 
		pregnancyList.add("absent menstrual periods");
		pregnancyList.add("back pain");
		pregnancyList.add("breast pain");
		pregnancyList.add("breast swelling");
		pregnancyList.add("frequent urination");
		pregnancyList.add("headache");
		pregnancyList.add("mood changes");
		pregnancyList.add("nausea");
		pregnancyList.add("tiredness");
		pregnancyList.add("vomiting");  
		diseaseMap.put("Pregnancy",pregnancyList); 
		diseaseDepartmentMap.put("Pregnancy",Department.GYNAECOLOGY);


		//pneumonia 

		ArrayList<String> pneumoniaList = new ArrayList<>(); 
		pneumoniaList.add("purplish skin");
		pneumoniaList.add("chest pain");
		pneumoniaList.add("chills");
		pneumoniaList.add("cough");
		pneumoniaList.add("fatigue");
		pneumoniaList.add("fever");
		pneumoniaList.add("headache");
		pneumoniaList.add("muscle aches");
		pneumoniaList.add("nasal congestion");
		pneumoniaList.add("weakness");
		pneumoniaList.add("sore throat");  
		diseaseMap.put("Pneumonia",pneumoniaList); 
		diseaseDepartmentMap.put("Pneumonia",Department.PULMONARY);


		//pancreaticCancer

		ArrayList<String> pancreaticCancerList = new ArrayList<>(); 
		pancreaticCancerList.add("abdominal pain");
		pancreaticCancerList.add("back pain");
		pancreaticCancerList.add("bloating");
		pancreaticCancerList.add("dark urine");
		pancreaticCancerList.add("diarrhea");
		pancreaticCancerList.add("loss of appetite");
		pancreaticCancerList.add("fatigue");
		pancreaticCancerList.add("painful lymph nodes");
		pancreaticCancerList.add("jaundice");
		pancreaticCancerList.add("pale stools");
		pancreaticCancerList.add("vomiting");
		pancreaticCancerList.add("weight loss"); 
		diseaseMap.put("Pancreatic Cancer",pancreaticCancerList); 
		diseaseDepartmentMap.put("Pancreatic Cancer",Department.ONCOLOGY);



		//ovarianCancer

		ArrayList<String> ovarianCancerList = new ArrayList<>(); 
		ovarianCancerList.add("abdominal pain");
		ovarianCancerList.add("abdominal swelling");    
		ovarianCancerList.add("bloating");
		ovarianCancerList.add("constipation"); 
		ovarianCancerList.add("early satiety");   
		ovarianCancerList.add("fatigue");
		ovarianCancerList.add("frequent urination");
		ovarianCancerList.add("indigestion");
		ovarianCancerList.add("leg swelling");
		ovarianCancerList.add("nausea");
		ovarianCancerList.add("pelvic pain");
		diseaseMap.put("Ovarian Cancer", ovarianCancerList);
		diseaseDepartmentMap.put("Ovarian Cancer",Department.ONCOLOGY);



		//multipleSclerosis

		ArrayList<String> multipleSclerosisList = new ArrayList<>(); 
		multipleSclerosisList.add("confusion");
		multipleSclerosisList.add("vision changes");
		multipleSclerosisList.add("burning");
		multipleSclerosisList.add("depression");
		multipleSclerosisList.add("dizziness");
		multipleSclerosisList.add("change in consciousness");
		multipleSclerosisList.add("eye pain");
		multipleSclerosisList.add("fatigue");
		multipleSclerosisList.add("heat intolerance");
		multipleSclerosisList.add("loss of bladder control");
		multipleSclerosisList.add("mania");
		multipleSclerosisList.add("mood changes");
		multipleSclerosisList.add("muscle pain");
		multipleSclerosisList.add("muscle spasms");
		multipleSclerosisList.add("numbness");
		multipleSclerosisList.add("vision changes");
		multipleSclerosisList.add("tingling");
		diseaseMap.put("Multiple Sclerosis", multipleSclerosisList);
		diseaseDepartmentMap.put("Multiple Sclerosis",Department.PHYSICIAN);



		//infectiousMononucleosis


		ArrayList<String> infectiousMononucleosisList = new ArrayList<>();
		infectiousMononucleosisList.add("enlarged liver");
		infectiousMononucleosisList.add("enlarged spleen");
		infectiousMononucleosisList.add("fatigue");
		infectiousMononucleosisList.add("fever");
		infectiousMononucleosisList.add("loss of appetite");
		infectiousMononucleosisList.add("malaise");
		infectiousMononucleosisList.add("rash");
		infectiousMononucleosisList.add("sore throat");
		infectiousMononucleosisList.add("painful lymph nodes");
		infectiousMononucleosisList.add("swollen upper eyelids");
		infectiousMononucleosisList.add("swollen tonsils");
		infectiousMononucleosisList.add("whitish coating on tonsils");
		diseaseMap.put("Infectious Mononucleosis", infectiousMononucleosisList);
		diseaseDepartmentMap.put("Infectious Mononucleosis",Department.PHYSICIAN);


		//menopause

		ArrayList<String> menopauseList = new ArrayList<>();
		menopauseList.add("acne");
		menopauseList.add("fatigue");
		menopauseList.add("flushing");
		menopauseList.add("changes in skin color");
		menopauseList.add("incontinence");
		menopauseList.add("irritation");
		menopauseList.add("memory problems");
		menopauseList.add("mood changes");
		menopauseList.add("night sweats");
		menopauseList.add("painful intercourse");
		menopauseList.add("stress");
		menopauseList.add("frequent urination");
		menopauseList.add("vaginal irritation");
		menopauseList.add("weight gain");
		diseaseMap.put("Menopause ", menopauseList);
		diseaseDepartmentMap.put("Menopause",Department.GYNAECOLOGY);


		//lymeDisease 


		ArrayList<String> lymeDiseaseList = new ArrayList<>();
		lymeDiseaseList.add("chills");
		lymeDiseaseList.add("dizziness");
		lymeDiseaseList.add("fever");
		lymeDiseaseList.add("headache");
		lymeDiseaseList.add("joint pain");
		lymeDiseaseList.add("memory problems");
		lymeDiseaseList.add("muscle pains");
		lymeDiseaseList.add("numbness");
		lymeDiseaseList.add("rash");
		lymeDiseaseList.add("palpitations");
		lymeDiseaseList.add("painful lymph nodes");
		lymeDiseaseList.add("tingling"); 
		diseaseMap.put("Lyme Disease", lymeDiseaseList);
		diseaseDepartmentMap.put("Lyme Disease",Department.PHYSICIAN);


		//systemicLupusErythematosus


		ArrayList<String> systemicLupusErythematosusList = new ArrayList<>();
		systemicLupusErythematosusList.add("chest pain");
		systemicLupusErythematosusList.add("coma");
		systemicLupusErythematosusList.add("easy bruising");
		systemicLupusErythematosusList.add("fatigue");
		systemicLupusErythematosusList.add("frequent infections");
		systemicLupusErythematosusList.add("hair loss");
		systemicLupusErythematosusList.add("joint pains");
		systemicLupusErythematosusList.add("loss of appetite");
		systemicLupusErythematosusList.add("mouth ulcers");
		systemicLupusErythematosusList.add("muscle pains");
		systemicLupusErythematosusList.add("numbness");
		systemicLupusErythematosusList.add("photosensitivity");
		systemicLupusErythematosusList.add("psychosis");
		systemicLupusErythematosusList.add("seizures");
		systemicLupusErythematosusList.add("rash");
		systemicLupusErythematosusList.add("tingling");
		systemicLupusErythematosusList.add("weakness");
		diseaseMap.put("Systemic Lupus Erythematosus", systemicLupusErythematosusList); 
		diseaseDepartmentMap.put("Systemic Lupus Erythematosus",Department.PULMONARY);


		//Cervical Cancer

		ArrayList<String> CervicalList = new ArrayList<>(); 
		CervicalList.add("Abnormal Vaginal Bleeding");
		CervicalList.add("Bleeding After Sexual Intercourse");
		CervicalList.add("Bleeding Between Periods");
		CervicalList.add("Heavy Menstrual Periods");
		CervicalList.add("Painful Intercourse");
		CervicalList.add("Pelvic Pain");
		CervicalList.add("Vaginal Discharge");
		diseaseMap.put("Cervical Cancer", CervicalList) ;
		diseaseDepartmentMap.put("Cervical Cancer",Department.ONCOLOGY);


		//Common Cold

		ArrayList<String> CommoncoldList = new ArrayList<>(); 
		CommoncoldList.add("cough");
		CommoncoldList.add("stuffy or runny nose");
		CommoncoldList.add("scratchy or sore throat");
		CommoncoldList.add("sneezing");
		diseaseMap.put("Common Cold", CommoncoldList) ;
		diseaseDepartmentMap.put("Common Cold",Department.PHYSICIAN);


		//colon cncer

		ArrayList<String> ColoncancerList = new ArrayList<>(); 
		ColoncancerList.add("Abdominal Cramping");
		ColoncancerList.add("Abdominal Mass");
		ColoncancerList.add("Abdominal Pain");
		ColoncancerList.add("Abdominal Tenderness");
		ColoncancerList.add("Bloating");
		ColoncancerList.add("Blood in Stool");
		ColoncancerList.add("Constipation");
		ColoncancerList.add("Dark Stool");
		ColoncancerList.add("Diarrhea");
		ColoncancerList.add("Fatigue");
		ColoncancerList.add("Shortness of Breath");
		ColoncancerList.add("Weakness");
		ColoncancerList.add("Weight Loss");
		diseaseMap.put("Colon Cancer", ColoncancerList) ;
		diseaseDepartmentMap.put("Colon Cancer",Department.ONCOLOGY);

		//depression

		ArrayList<String> depressionList = new ArrayList<>(); 
		depressionList.add("feelings of worthlessness");
		depressionList.add("hopelessness");
		depressionList.add("helplessness");
		depressionList.add("guilt");
		depressionList.add("lack of interest in daily activities");
		depressionList.add("irritability");
		depressionList.add("loss of energy");
		depressionList.add("loss of appetite");
		depressionList.add("sleep problems");
		depressionList.add("self-loathing");
		depressionList.add("thoughts of suicide");
		diseaseMap.put("Depression", depressionList) ;
		diseaseDepartmentMap.put("depression",Department.PHYSICIAN);


		//diabetes

		ArrayList<String> diabetesList = new ArrayList<>(); 
		diabetesList.add("Blurred Vision");
		diabetesList.add("Dehydration");
		diabetesList.add("Dry Mouth");
		diabetesList.add("Fatigue");
		diabetesList.add("Frequent Urination");
		diabetesList.add("Hunger");
		diabetesList.add("Increased Susceptibility to Infection");
		diabetesList.add("Increased Thirst");
		diabetesList.add("Itching Skin");
		diabetesList.add("Nausea");
		diabetesList.add("Slow-Healing Sores");
		diabetesList.add("Vaginal Infections");
		diabetesList.add("Vomiting");
		diabetesList.add("Weight Gain");
		diabetesList.add("Weight Loss");
		diabetesList.add("Yeast Infections");
		diseaseMap.put("diabetes", diabetesList) ;
		diseaseDepartmentMap.put("diabetes",Department.PHYSICIAN);

		//early pregnancy

		ArrayList<String> earlypregnancyList = new ArrayList<>(); 
		earlypregnancyList.add("absence of menstruation and weight gain");
		earlypregnancyList.add("mood swings");
		earlypregnancyList.add("increased urination");
		earlypregnancyList.add("headaches");
		earlypregnancyList.add("low backache");
		earlypregnancyList.add("breast tenderness");
		earlypregnancyList.add("darkened areolas");
		earlypregnancyList.add("fatigue");
		earlypregnancyList.add("nausea");
		earlypregnancyList.add("Nausea");
		earlypregnancyList.add("implantation bleeding");
		diseaseMap.put("Early Pregnency", earlypregnancyList) ;
		diseaseDepartmentMap.put("Early Pregnency",Department.GYNAECOLOGY);

		//heart attack

		ArrayList<String> heartattackList = new ArrayList<>(); 
		heartattackList.add("sweat");
		heartattackList.add("chest pain");
		diseaseMap.put("Heart Attack", heartattackList) ;
		diseaseDepartmentMap.put("Heart Attack",Department.CARDIOLOGY);

		//high blood pressure(hypertension)

		ArrayList<String> hypertensionList = new ArrayList<>(); 
		hypertensionList.add("Blood in the Urine");
		hypertensionList.add("Blurred Vision");
		hypertensionList.add("Chest Pain");
		hypertensionList.add("Decreased Urine Output");
		hypertensionList.add("Dizziness");
		hypertensionList.add("Headache");
		hypertensionList.add("Leg Swelling");
		hypertensionList.add("Nosebleeds");
		hypertensionList.add("Shortness of Breath");
		diseaseMap.put("Hyper Tension", hypertensionList) ;
		diseaseDepartmentMap.put("Hyper Tension",Department.PHYSICIAN);

		//HIV Infection

		ArrayList<String> HIVList = new ArrayList<>(); 
		HIVList.add("Diarrhea");
		HIVList.add("Fatigue");
		HIVList.add("Fever");
		HIVList.add("Fungal Infection of the Mouth and Esophagus");
		HIVList.add("Fungal Nails");
		HIVList.add("Leukoplakia of the Tongue");
		HIVList.add("Malignancies");
		HIVList.add("Mouth Sores");
		HIVList.add("Sore Throat");
		HIVList.add("Swollen Lymph Nodes");
		HIVList.add("Vaginal Candidiasis");
		HIVList.add("Vomiting");
		HIVList.add("Weight Loss");
		diseaseMap.put("HIV", HIVList) ;
		diseaseDepartmentMap.put("HIV",Department.PHYSICIAN);


		//Hyperthermia

		ArrayList<String> HyperthermiaList = new ArrayList<>(); 
		HyperthermiaList.add("Coma");
		HyperthermiaList.add("Confusion");
		HyperthermiaList.add("Dizziness");
		HyperthermiaList.add("Elevated Heart Rate");
		HyperthermiaList.add("Fainting");
		HyperthermiaList.add("Fever");
		HyperthermiaList.add("Headache");
		HyperthermiaList.add("Muscle Cramps");
		HyperthermiaList.add("Nausea");
		HyperthermiaList.add("Sweat");
		HyperthermiaList.add("Rash");
		HyperthermiaList.add("Reddened Skin");
		HyperthermiaList.add("Vomiting");
		HyperthermiaList.add("Weakness");
		diseaseMap.put("Hyperthermia", HyperthermiaList) ;
		diseaseDepartmentMap.put("Hyperthermia",Department.PHYSICIAN);

		//Irritable Bowel Syndrome (IBS)

		ArrayList<String> IBSList = new ArrayList<>(); 
		IBSList.add("Abdominal Distension");
		IBSList.add("Abdominal Pain");
		IBSList.add("Bloating");
		IBSList.add("Change in Stool Consistency");
		IBSList.add("Constipation");
		IBSList.add("Diarrhea");
		IBSList.add("Flatulence");
		IBSList.add("Mucus in Stool");
		IBSList.add("Watery Stool");
		diseaseMap.put("Irritable Bowel Syndrome (IBS)", IBSList) ;
		diseaseDepartmentMap.put("Irritable Bowel Syndrome (IBS)",Department.PHYSICIAN);

		//Kidney Stone

		ArrayList<String> KidneyStoneList = new ArrayList<>(); 
		KidneyStoneList.add("Blood in Urine");
		KidneyStoneList.add("Abdominal Pain");
		KidneyStoneList.add("Flank Pain");
		KidneyStoneList.add("Low Back Pain");
		KidneyStoneList.add("Nausea");
		KidneyStoneList.add("Scrotal Pain");
		KidneyStoneList.add("Sweat");
		KidneyStoneList.add("Vomiting");
		KidneyStoneList.add("Testicular Pain");
		diseaseMap.put("Kidney Stone", KidneyStoneList) ;
		diseaseDepartmentMap.put("Kidney Stone",Department.NEPHROLOGY);

		//Leukemia

		ArrayList<String> LeukemiaList = new ArrayList<>(); 
		LeukemiaList.add("Bleeding Gums");
		LeukemiaList.add("Bone Pain");
		LeukemiaList.add("Loss of Muscle Control");
		LeukemiaList.add("Easy Bruising");
		LeukemiaList.add("Nausea");
		LeukemiaList.add("Fever");
		LeukemiaList.add("Sweat");
		LeukemiaList.add("Vomiting");
		LeukemiaList.add("Headache");
		LeukemiaList.add("Enlarged Liver");
		LeukemiaList.add("Enlarged Spleen");
		LeukemiaList.add("Fatigue");
		LeukemiaList.add("Joint Pain");
		LeukemiaList.add("Night Sweats");
		LeukemiaList.add("Seizures");
		LeukemiaList.add("Swollen Lymph Nodes");
		LeukemiaList.add("Weight Loss");
		diseaseMap.put("Leukemia", LeukemiaList) ;
		diseaseDepartmentMap.put("Leukemia",Department.ONCOLOGY);


		//lungCancer

		ArrayList<String> lungCancerList = new ArrayList<>();
		lungCancerList.add("blood in sputus");
		lungCancerList.add("chest pain");
		lungCancerList.add("cough"); 
		lungCancerList.add("depression"); 
		lungCancerList.add("fatigue"); 
		lungCancerList.add("headache");
		lungCancerList.add("hoarseness");
		lungCancerList.add("numbness");
		lungCancerList.add("seizures");
		lungCancerList.add("shortness of breath");
		lungCancerList.add("shoulder pain");
		lungCancerList.add("tingling");
		lungCancerList.add("wheezing");
		lungCancerList.add("weakness");
		lungCancerList.add("weight loss");          
		diseaseMap.put("Lung Cancer ", lungCancerList) ;
		diseaseDepartmentMap.put("Lung Cancer",Department.ONCOLOGY);

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
	
	
	public static Department getDepartmentNameFromDisease(String diseaseName){
		return diseaseDepartmentMap.get(diseaseName);
	}
	public static void main(String[] args) {
		
		Set<String> set = new HashSet<>();
		for (Map.Entry<String, ArrayList<String>> entry : diseaseMap.entrySet())
		{

			String diseaseName = entry.getKey();
//			System.out.println(diseaseName);
			ArrayList<String> diseaseSymptoms = entry.getValue();
			
			int count = 0;
			for(String symptom :diseaseSymptoms){
				set.add(symptom);
			}
		}
		System.out.println(set);
	}
}
