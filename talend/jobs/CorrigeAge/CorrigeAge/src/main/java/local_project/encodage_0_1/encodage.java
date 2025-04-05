// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.


package local_project.encodage_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;
 




	//the import part of tJavaRow_1
	//import java.util.List;


@SuppressWarnings("unused")

/**
 * Job: encodage Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class encodage implements TalendJob {

protected static void logIgnoredError(String message, Throwable cause) {
       System.err.println(message);
       if (cause != null) {
               cause.printStackTrace();
       }

}


	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
	
	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	
	private final static String utf8Charset = "UTF-8";
	//contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String,String> propertyTypes = new java.util.HashMap<>();
		
		public PropertiesWithType(java.util.Properties properties){
			super(properties);
		}
		public PropertiesWithType(){
			super();
		}
		
		public void setContextType(String key, String type) {
			propertyTypes.put(key,type);
		}
	
		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}
	
	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();
	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties){
			super(properties);
		}
		public ContextProperties(){
			super();
		}

		public void synchronizeContext(){
			
		}
		
		//if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if(NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}
	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.
	public ContextProperties getContext() {
		return this.context;
	}
	private final String jobVersion = "0.1";
	private final String jobName = "encodage";
	private final String projectName = "LOCAL_PROJECT";
	public Integer errorCode = null;
	private String currentComponent = "";
	
		private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
        private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
	
		private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
		public  final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
	

private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";
	
	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(), new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}
	
	public void setDataSourceReferences(List serviceReferences) throws Exception{
		
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();
		
		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils.getServices(serviceReferences,  javax.sql.DataSource.class).entrySet()) {
                    dataSources.put(entry.getKey(), entry.getValue());
                    talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}


private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

public String getExceptionStackTrace() {
	if ("failure".equals(this.getStatus())) {
		errorMessagePS.flush();
		return baos.toString();
	}
	return null;
}

private Exception exception;

public Exception getException() {
	if ("failure".equals(this.getStatus())) {
		return this.exception;
	}
	return null;
}

private class TalendException extends Exception {

	private static final long serialVersionUID = 1L;

	private java.util.Map<String, Object> globalMap = null;
	private Exception e = null;
	private String currentComponent = null;
	private String virtualComponentName = null;
	
	public void setVirtualComponentName (String virtualComponentName){
		this.virtualComponentName = virtualComponentName;
	}

	private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
		this.currentComponent= errorComponent;
		this.globalMap = globalMap;
		this.e = e;
	}

	public Exception getException() {
		return this.e;
	}

	public String getCurrentComponent() {
		return this.currentComponent;
	}

	
    public String getExceptionCauseMessage(Exception e){
        Throwable cause = e;
        String message = null;
        int i = 10;
        while (null != cause && 0 < i--) {
            message = cause.getMessage();
            if (null == message) {
                cause = cause.getCause();
            } else {
                break;          
            }
        }
        if (null == message) {
            message = e.getClass().getName();
        }   
        return message;
    }

	@Override
	public void printStackTrace() {
		if (!(e instanceof TalendException || e instanceof TDieException)) {
			if(virtualComponentName!=null && currentComponent.indexOf(virtualComponentName+"_")==0){
				globalMap.put(virtualComponentName+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			}
			globalMap.put(currentComponent+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
		}
		if (!(e instanceof TDieException)) {
			if(e instanceof TalendException){
				e.printStackTrace();
			} else {
				e.printStackTrace();
				e.printStackTrace(errorMessagePS);
				encodage.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(encodage.this, new Object[] { e , currentComponent, globalMap});
					break;
				}
			}

			if(!(e instanceof TDieException)){
			}
		} catch (Exception e) {
			this.e.printStackTrace();
		}
		}
	}
}

			public void tFileInputExcel_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tJavaRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tBufferOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_encodage = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_encodage = new byte[0];

	
			    public Integer Annee;

				public Integer getAnnee () {
					return this.Annee;
				}
				
			    public String EDC_FabLab;

				public String getEDC_FabLab () {
					return this.EDC_FabLab;
				}
				
			    public String FORMATION;

				public String getFORMATION () {
					return this.FORMATION;
				}
				
			    public String DATE;

				public String getDATE () {
					return this.DATE;
				}
				
			    public String ETAT;

				public String getETAT () {
					return this.ETAT;
				}
				
			    public String Profil_recommande_pour_Job_Dating;

				public String getProfil_recommande_pour_Job_Dating () {
					return this.Profil_recommande_pour_Job_Dating;
				}
				
			    public String EMAIL;

				public String getEMAIL () {
					return this.EMAIL;
				}
				
			    public String First_Name;

				public String getFirst_Name () {
					return this.First_Name;
				}
				
			    public String Last_Name;

				public String getLast_Name () {
					return this.Last_Name;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String profession;

				public String getProfession () {
					return this.profession;
				}
				
			    public String Votre_numero_de_telephone;

				public String getVotre_numero_de_telephone () {
					return this.Votre_numero_de_telephone;
				}
				
			    public String birthDay;

				public String getBirthDay () {
					return this.birthDay;
				}
				
			    public String country;

				public String getCountry () {
					return this.country;
				}
				
			    public String Nom_Prenom;

				public String getNom_Prenom () {
					return this.Nom_Prenom;
				}
				
			    public String Votre_Age;

				public String getVotre_Age () {
					return this.Votre_Age;
				}
				
			    public String Categorie_d_age;

				public String getCategorie_d_age () {
					return this.Categorie_d_age;
				}
				
			    public String Votre_niveau_d_etudes;

				public String getVotre_niveau_d_etudes () {
					return this.Votre_niveau_d_etudes;
				}
				
			    public String Votre_specialite;

				public String getVotre_specialite () {
					return this.Votre_specialite;
				}
				
			    public String Etablissement;

				public String getEtablissement () {
					return this.Etablissement;
				}
				
			    public String Avez_Vous_deja_participer_au_programmes_ODC;

				public String getAvez_Vous_deja_participer_au_programmes_ODC () {
					return this.Avez_Vous_deja_participer_au_programmes_ODC;
				}
				
			    public String linkedIn;

				public String getLinkedIn () {
					return this.linkedIn;
				}
				
			    public String userCV;

				public String getUserCV () {
					return this.userCV;
				}
				
			    public String Column23;

				public String getColumn23 () {
					return this.Column23;
				}
				
			    public String Column24;

				public String getColumn24 () {
					return this.Column24;
				}
				
			    public String Column25;

				public String getColumn25 () {
					return this.Column25;
				}
				
			    public String Column26;

				public String getColumn26 () {
					return this.Column26;
				}
				
			    public String Column27;

				public String getColumn27 () {
					return this.Column27;
				}
				
			    public String Column28;

				public String getColumn28 () {
					return this.Column28;
				}
				
			    public String Column29;

				public String getColumn29 () {
					return this.Column29;
				}
				
			    public String Column30;

				public String getColumn30 () {
					return this.Column30;
				}
				
			    public String Column31;

				public String getColumn31 () {
					return this.Column31;
				}
				
			    public String Column32;

				public String getColumn32 () {
					return this.Column32;
				}
				
			    public String Column33;

				public String getColumn33 () {
					return this.Column33;
				}
				
			    public String Column34;

				public String getColumn34 () {
					return this.Column34;
				}
				
			    public String Column35;

				public String getColumn35 () {
					return this.Column35;
				}
				
			    public String Column36;

				public String getColumn36 () {
					return this.Column36;
				}
				
			    public String Column37;

				public String getColumn37 () {
					return this.Column37;
				}
				
			    public String Column38;

				public String getColumn38 () {
					return this.Column38;
				}
				
			    public String Column39;

				public String getColumn39 () {
					return this.Column39;
				}
				
			    public String Column40;

				public String getColumn40 () {
					return this.Column40;
				}
				
			    public String Column41;

				public String getColumn41 () {
					return this.Column41;
				}
				
			    public String Column42;

				public String getColumn42 () {
					return this.Column42;
				}
				
			    public String Column43;

				public String getColumn43 () {
					return this.Column43;
				}
				
			    public String Column44;

				public String getColumn44 () {
					return this.Column44;
				}
				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_encodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_encodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_encodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_encodage, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_encodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_encodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_encodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_encodage, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_encodage) {

        	try {

        		int length = 0;
		
						this.Annee = readInteger(dis);
					
					this.EDC_FabLab = readString(dis);
					
					this.FORMATION = readString(dis);
					
					this.DATE = readString(dis);
					
					this.ETAT = readString(dis);
					
					this.Profil_recommande_pour_Job_Dating = readString(dis);
					
					this.EMAIL = readString(dis);
					
					this.First_Name = readString(dis);
					
					this.Last_Name = readString(dis);
					
					this.gender = readString(dis);
					
					this.profession = readString(dis);
					
					this.Votre_numero_de_telephone = readString(dis);
					
					this.birthDay = readString(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
					this.Votre_Age = readString(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_Vous_deja_participer_au_programmes_ODC = readString(dis);
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.Column23 = readString(dis);
					
					this.Column24 = readString(dis);
					
					this.Column25 = readString(dis);
					
					this.Column26 = readString(dis);
					
					this.Column27 = readString(dis);
					
					this.Column28 = readString(dis);
					
					this.Column29 = readString(dis);
					
					this.Column30 = readString(dis);
					
					this.Column31 = readString(dis);
					
					this.Column32 = readString(dis);
					
					this.Column33 = readString(dis);
					
					this.Column34 = readString(dis);
					
					this.Column35 = readString(dis);
					
					this.Column36 = readString(dis);
					
					this.Column37 = readString(dis);
					
					this.Column38 = readString(dis);
					
					this.Column39 = readString(dis);
					
					this.Column40 = readString(dis);
					
					this.Column41 = readString(dis);
					
					this.Column42 = readString(dis);
					
					this.Column43 = readString(dis);
					
					this.Column44 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_encodage) {

        	try {

        		int length = 0;
		
						this.Annee = readInteger(dis);
					
					this.EDC_FabLab = readString(dis);
					
					this.FORMATION = readString(dis);
					
					this.DATE = readString(dis);
					
					this.ETAT = readString(dis);
					
					this.Profil_recommande_pour_Job_Dating = readString(dis);
					
					this.EMAIL = readString(dis);
					
					this.First_Name = readString(dis);
					
					this.Last_Name = readString(dis);
					
					this.gender = readString(dis);
					
					this.profession = readString(dis);
					
					this.Votre_numero_de_telephone = readString(dis);
					
					this.birthDay = readString(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
					this.Votre_Age = readString(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_Vous_deja_participer_au_programmes_ODC = readString(dis);
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.Column23 = readString(dis);
					
					this.Column24 = readString(dis);
					
					this.Column25 = readString(dis);
					
					this.Column26 = readString(dis);
					
					this.Column27 = readString(dis);
					
					this.Column28 = readString(dis);
					
					this.Column29 = readString(dis);
					
					this.Column30 = readString(dis);
					
					this.Column31 = readString(dis);
					
					this.Column32 = readString(dis);
					
					this.Column33 = readString(dis);
					
					this.Column34 = readString(dis);
					
					this.Column35 = readString(dis);
					
					this.Column36 = readString(dis);
					
					this.Column37 = readString(dis);
					
					this.Column38 = readString(dis);
					
					this.Column39 = readString(dis);
					
					this.Column40 = readString(dis);
					
					this.Column41 = readString(dis);
					
					this.Column42 = readString(dis);
					
					this.Column43 = readString(dis);
					
					this.Column44 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.Annee,dos);
					
					// String
				
						writeString(this.EDC_FabLab,dos);
					
					// String
				
						writeString(this.FORMATION,dos);
					
					// String
				
						writeString(this.DATE,dos);
					
					// String
				
						writeString(this.ETAT,dos);
					
					// String
				
						writeString(this.Profil_recommande_pour_Job_Dating,dos);
					
					// String
				
						writeString(this.EMAIL,dos);
					
					// String
				
						writeString(this.First_Name,dos);
					
					// String
				
						writeString(this.Last_Name,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.profession,dos);
					
					// String
				
						writeString(this.Votre_numero_de_telephone,dos);
					
					// String
				
						writeString(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// String
				
						writeString(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_Vous_deja_participer_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.Column23,dos);
					
					// String
				
						writeString(this.Column24,dos);
					
					// String
				
						writeString(this.Column25,dos);
					
					// String
				
						writeString(this.Column26,dos);
					
					// String
				
						writeString(this.Column27,dos);
					
					// String
				
						writeString(this.Column28,dos);
					
					// String
				
						writeString(this.Column29,dos);
					
					// String
				
						writeString(this.Column30,dos);
					
					// String
				
						writeString(this.Column31,dos);
					
					// String
				
						writeString(this.Column32,dos);
					
					// String
				
						writeString(this.Column33,dos);
					
					// String
				
						writeString(this.Column34,dos);
					
					// String
				
						writeString(this.Column35,dos);
					
					// String
				
						writeString(this.Column36,dos);
					
					// String
				
						writeString(this.Column37,dos);
					
					// String
				
						writeString(this.Column38,dos);
					
					// String
				
						writeString(this.Column39,dos);
					
					// String
				
						writeString(this.Column40,dos);
					
					// String
				
						writeString(this.Column41,dos);
					
					// String
				
						writeString(this.Column42,dos);
					
					// String
				
						writeString(this.Column43,dos);
					
					// String
				
						writeString(this.Column44,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.Annee,dos);
					
					// String
				
						writeString(this.EDC_FabLab,dos);
					
					// String
				
						writeString(this.FORMATION,dos);
					
					// String
				
						writeString(this.DATE,dos);
					
					// String
				
						writeString(this.ETAT,dos);
					
					// String
				
						writeString(this.Profil_recommande_pour_Job_Dating,dos);
					
					// String
				
						writeString(this.EMAIL,dos);
					
					// String
				
						writeString(this.First_Name,dos);
					
					// String
				
						writeString(this.Last_Name,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.profession,dos);
					
					// String
				
						writeString(this.Votre_numero_de_telephone,dos);
					
					// String
				
						writeString(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// String
				
						writeString(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_Vous_deja_participer_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.Column23,dos);
					
					// String
				
						writeString(this.Column24,dos);
					
					// String
				
						writeString(this.Column25,dos);
					
					// String
				
						writeString(this.Column26,dos);
					
					// String
				
						writeString(this.Column27,dos);
					
					// String
				
						writeString(this.Column28,dos);
					
					// String
				
						writeString(this.Column29,dos);
					
					// String
				
						writeString(this.Column30,dos);
					
					// String
				
						writeString(this.Column31,dos);
					
					// String
				
						writeString(this.Column32,dos);
					
					// String
				
						writeString(this.Column33,dos);
					
					// String
				
						writeString(this.Column34,dos);
					
					// String
				
						writeString(this.Column35,dos);
					
					// String
				
						writeString(this.Column36,dos);
					
					// String
				
						writeString(this.Column37,dos);
					
					// String
				
						writeString(this.Column38,dos);
					
					// String
				
						writeString(this.Column39,dos);
					
					// String
				
						writeString(this.Column40,dos);
					
					// String
				
						writeString(this.Column41,dos);
					
					// String
				
						writeString(this.Column42,dos);
					
					// String
				
						writeString(this.Column43,dos);
					
					// String
				
						writeString(this.Column44,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Annee="+String.valueOf(Annee));
		sb.append(",EDC_FabLab="+EDC_FabLab);
		sb.append(",FORMATION="+FORMATION);
		sb.append(",DATE="+DATE);
		sb.append(",ETAT="+ETAT);
		sb.append(",Profil_recommande_pour_Job_Dating="+Profil_recommande_pour_Job_Dating);
		sb.append(",EMAIL="+EMAIL);
		sb.append(",First_Name="+First_Name);
		sb.append(",Last_Name="+Last_Name);
		sb.append(",gender="+gender);
		sb.append(",profession="+profession);
		sb.append(",Votre_numero_de_telephone="+Votre_numero_de_telephone);
		sb.append(",birthDay="+birthDay);
		sb.append(",country="+country);
		sb.append(",Nom_Prenom="+Nom_Prenom);
		sb.append(",Votre_Age="+Votre_Age);
		sb.append(",Categorie_d_age="+Categorie_d_age);
		sb.append(",Votre_niveau_d_etudes="+Votre_niveau_d_etudes);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_Vous_deja_participer_au_programmes_ODC="+Avez_Vous_deja_participer_au_programmes_ODC);
		sb.append(",linkedIn="+linkedIn);
		sb.append(",userCV="+userCV);
		sb.append(",Column23="+Column23);
		sb.append(",Column24="+Column24);
		sb.append(",Column25="+Column25);
		sb.append(",Column26="+Column26);
		sb.append(",Column27="+Column27);
		sb.append(",Column28="+Column28);
		sb.append(",Column29="+Column29);
		sb.append(",Column30="+Column30);
		sb.append(",Column31="+Column31);
		sb.append(",Column32="+Column32);
		sb.append(",Column33="+Column33);
		sb.append(",Column34="+Column34);
		sb.append(",Column35="+Column35);
		sb.append(",Column36="+Column36);
		sb.append(",Column37="+Column37);
		sb.append(",Column38="+Column38);
		sb.append(",Column39="+Column39);
		sb.append(",Column40="+Column40);
		sb.append(",Column41="+Column41);
		sb.append(",Column42="+Column42);
		sb.append(",Column43="+Column43);
		sb.append(",Column44="+Column44);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row2Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_encodage = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_encodage = new byte[0];

	
			    public Integer Annee;

				public Integer getAnnee () {
					return this.Annee;
				}
				
			    public String EDC_FabLab;

				public String getEDC_FabLab () {
					return this.EDC_FabLab;
				}
				
			    public String FORMATION;

				public String getFORMATION () {
					return this.FORMATION;
				}
				
			    public String DATE;

				public String getDATE () {
					return this.DATE;
				}
				
			    public String ETAT;

				public String getETAT () {
					return this.ETAT;
				}
				
			    public String Profil_recommande_pour_Job_Dating;

				public String getProfil_recommande_pour_Job_Dating () {
					return this.Profil_recommande_pour_Job_Dating;
				}
				
			    public String EMAIL;

				public String getEMAIL () {
					return this.EMAIL;
				}
				
			    public String First_Name;

				public String getFirst_Name () {
					return this.First_Name;
				}
				
			    public String Last_Name;

				public String getLast_Name () {
					return this.Last_Name;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String profession;

				public String getProfession () {
					return this.profession;
				}
				
			    public String Votre_numero_de_telephone;

				public String getVotre_numero_de_telephone () {
					return this.Votre_numero_de_telephone;
				}
				
			    public String birthDay;

				public String getBirthDay () {
					return this.birthDay;
				}
				
			    public String country;

				public String getCountry () {
					return this.country;
				}
				
			    public String Nom_Prenom;

				public String getNom_Prenom () {
					return this.Nom_Prenom;
				}
				
			    public String Votre_Age;

				public String getVotre_Age () {
					return this.Votre_Age;
				}
				
			    public String Categorie_d_age;

				public String getCategorie_d_age () {
					return this.Categorie_d_age;
				}
				
			    public String Votre_niveau_d_etudes;

				public String getVotre_niveau_d_etudes () {
					return this.Votre_niveau_d_etudes;
				}
				
			    public String Votre_specialite;

				public String getVotre_specialite () {
					return this.Votre_specialite;
				}
				
			    public String Etablissement;

				public String getEtablissement () {
					return this.Etablissement;
				}
				
			    public String Avez_Vous_deja_participer_au_programmes_ODC;

				public String getAvez_Vous_deja_participer_au_programmes_ODC () {
					return this.Avez_Vous_deja_participer_au_programmes_ODC;
				}
				
			    public String linkedIn;

				public String getLinkedIn () {
					return this.linkedIn;
				}
				
			    public String userCV;

				public String getUserCV () {
					return this.userCV;
				}
				
			    public String Column23;

				public String getColumn23 () {
					return this.Column23;
				}
				
			    public String Column24;

				public String getColumn24 () {
					return this.Column24;
				}
				
			    public String Column25;

				public String getColumn25 () {
					return this.Column25;
				}
				
			    public String Column26;

				public String getColumn26 () {
					return this.Column26;
				}
				
			    public String Column27;

				public String getColumn27 () {
					return this.Column27;
				}
				
			    public String Column28;

				public String getColumn28 () {
					return this.Column28;
				}
				
			    public String Column29;

				public String getColumn29 () {
					return this.Column29;
				}
				
			    public String Column30;

				public String getColumn30 () {
					return this.Column30;
				}
				
			    public String Column31;

				public String getColumn31 () {
					return this.Column31;
				}
				
			    public String Column32;

				public String getColumn32 () {
					return this.Column32;
				}
				
			    public String Column33;

				public String getColumn33 () {
					return this.Column33;
				}
				
			    public String Column34;

				public String getColumn34 () {
					return this.Column34;
				}
				
			    public String Column35;

				public String getColumn35 () {
					return this.Column35;
				}
				
			    public String Column36;

				public String getColumn36 () {
					return this.Column36;
				}
				
			    public String Column37;

				public String getColumn37 () {
					return this.Column37;
				}
				
			    public String Column38;

				public String getColumn38 () {
					return this.Column38;
				}
				
			    public String Column39;

				public String getColumn39 () {
					return this.Column39;
				}
				
			    public String Column40;

				public String getColumn40 () {
					return this.Column40;
				}
				
			    public String Column41;

				public String getColumn41 () {
					return this.Column41;
				}
				
			    public String Column42;

				public String getColumn42 () {
					return this.Column42;
				}
				
			    public String Column43;

				public String getColumn43 () {
					return this.Column43;
				}
				
			    public String Column44;

				public String getColumn44 () {
					return this.Column44;
				}
				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_encodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_encodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_encodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_encodage, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_encodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_encodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_encodage = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_encodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_encodage, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_encodage) {

        	try {

        		int length = 0;
		
						this.Annee = readInteger(dis);
					
					this.EDC_FabLab = readString(dis);
					
					this.FORMATION = readString(dis);
					
					this.DATE = readString(dis);
					
					this.ETAT = readString(dis);
					
					this.Profil_recommande_pour_Job_Dating = readString(dis);
					
					this.EMAIL = readString(dis);
					
					this.First_Name = readString(dis);
					
					this.Last_Name = readString(dis);
					
					this.gender = readString(dis);
					
					this.profession = readString(dis);
					
					this.Votre_numero_de_telephone = readString(dis);
					
					this.birthDay = readString(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
					this.Votre_Age = readString(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_Vous_deja_participer_au_programmes_ODC = readString(dis);
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.Column23 = readString(dis);
					
					this.Column24 = readString(dis);
					
					this.Column25 = readString(dis);
					
					this.Column26 = readString(dis);
					
					this.Column27 = readString(dis);
					
					this.Column28 = readString(dis);
					
					this.Column29 = readString(dis);
					
					this.Column30 = readString(dis);
					
					this.Column31 = readString(dis);
					
					this.Column32 = readString(dis);
					
					this.Column33 = readString(dis);
					
					this.Column34 = readString(dis);
					
					this.Column35 = readString(dis);
					
					this.Column36 = readString(dis);
					
					this.Column37 = readString(dis);
					
					this.Column38 = readString(dis);
					
					this.Column39 = readString(dis);
					
					this.Column40 = readString(dis);
					
					this.Column41 = readString(dis);
					
					this.Column42 = readString(dis);
					
					this.Column43 = readString(dis);
					
					this.Column44 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_encodage) {

        	try {

        		int length = 0;
		
						this.Annee = readInteger(dis);
					
					this.EDC_FabLab = readString(dis);
					
					this.FORMATION = readString(dis);
					
					this.DATE = readString(dis);
					
					this.ETAT = readString(dis);
					
					this.Profil_recommande_pour_Job_Dating = readString(dis);
					
					this.EMAIL = readString(dis);
					
					this.First_Name = readString(dis);
					
					this.Last_Name = readString(dis);
					
					this.gender = readString(dis);
					
					this.profession = readString(dis);
					
					this.Votre_numero_de_telephone = readString(dis);
					
					this.birthDay = readString(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
					this.Votre_Age = readString(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_Vous_deja_participer_au_programmes_ODC = readString(dis);
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.Column23 = readString(dis);
					
					this.Column24 = readString(dis);
					
					this.Column25 = readString(dis);
					
					this.Column26 = readString(dis);
					
					this.Column27 = readString(dis);
					
					this.Column28 = readString(dis);
					
					this.Column29 = readString(dis);
					
					this.Column30 = readString(dis);
					
					this.Column31 = readString(dis);
					
					this.Column32 = readString(dis);
					
					this.Column33 = readString(dis);
					
					this.Column34 = readString(dis);
					
					this.Column35 = readString(dis);
					
					this.Column36 = readString(dis);
					
					this.Column37 = readString(dis);
					
					this.Column38 = readString(dis);
					
					this.Column39 = readString(dis);
					
					this.Column40 = readString(dis);
					
					this.Column41 = readString(dis);
					
					this.Column42 = readString(dis);
					
					this.Column43 = readString(dis);
					
					this.Column44 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.Annee,dos);
					
					// String
				
						writeString(this.EDC_FabLab,dos);
					
					// String
				
						writeString(this.FORMATION,dos);
					
					// String
				
						writeString(this.DATE,dos);
					
					// String
				
						writeString(this.ETAT,dos);
					
					// String
				
						writeString(this.Profil_recommande_pour_Job_Dating,dos);
					
					// String
				
						writeString(this.EMAIL,dos);
					
					// String
				
						writeString(this.First_Name,dos);
					
					// String
				
						writeString(this.Last_Name,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.profession,dos);
					
					// String
				
						writeString(this.Votre_numero_de_telephone,dos);
					
					// String
				
						writeString(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// String
				
						writeString(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_Vous_deja_participer_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.Column23,dos);
					
					// String
				
						writeString(this.Column24,dos);
					
					// String
				
						writeString(this.Column25,dos);
					
					// String
				
						writeString(this.Column26,dos);
					
					// String
				
						writeString(this.Column27,dos);
					
					// String
				
						writeString(this.Column28,dos);
					
					// String
				
						writeString(this.Column29,dos);
					
					// String
				
						writeString(this.Column30,dos);
					
					// String
				
						writeString(this.Column31,dos);
					
					// String
				
						writeString(this.Column32,dos);
					
					// String
				
						writeString(this.Column33,dos);
					
					// String
				
						writeString(this.Column34,dos);
					
					// String
				
						writeString(this.Column35,dos);
					
					// String
				
						writeString(this.Column36,dos);
					
					// String
				
						writeString(this.Column37,dos);
					
					// String
				
						writeString(this.Column38,dos);
					
					// String
				
						writeString(this.Column39,dos);
					
					// String
				
						writeString(this.Column40,dos);
					
					// String
				
						writeString(this.Column41,dos);
					
					// String
				
						writeString(this.Column42,dos);
					
					// String
				
						writeString(this.Column43,dos);
					
					// String
				
						writeString(this.Column44,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.Annee,dos);
					
					// String
				
						writeString(this.EDC_FabLab,dos);
					
					// String
				
						writeString(this.FORMATION,dos);
					
					// String
				
						writeString(this.DATE,dos);
					
					// String
				
						writeString(this.ETAT,dos);
					
					// String
				
						writeString(this.Profil_recommande_pour_Job_Dating,dos);
					
					// String
				
						writeString(this.EMAIL,dos);
					
					// String
				
						writeString(this.First_Name,dos);
					
					// String
				
						writeString(this.Last_Name,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.profession,dos);
					
					// String
				
						writeString(this.Votre_numero_de_telephone,dos);
					
					// String
				
						writeString(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// String
				
						writeString(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_Vous_deja_participer_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.Column23,dos);
					
					// String
				
						writeString(this.Column24,dos);
					
					// String
				
						writeString(this.Column25,dos);
					
					// String
				
						writeString(this.Column26,dos);
					
					// String
				
						writeString(this.Column27,dos);
					
					// String
				
						writeString(this.Column28,dos);
					
					// String
				
						writeString(this.Column29,dos);
					
					// String
				
						writeString(this.Column30,dos);
					
					// String
				
						writeString(this.Column31,dos);
					
					// String
				
						writeString(this.Column32,dos);
					
					// String
				
						writeString(this.Column33,dos);
					
					// String
				
						writeString(this.Column34,dos);
					
					// String
				
						writeString(this.Column35,dos);
					
					// String
				
						writeString(this.Column36,dos);
					
					// String
				
						writeString(this.Column37,dos);
					
					// String
				
						writeString(this.Column38,dos);
					
					// String
				
						writeString(this.Column39,dos);
					
					// String
				
						writeString(this.Column40,dos);
					
					// String
				
						writeString(this.Column41,dos);
					
					// String
				
						writeString(this.Column42,dos);
					
					// String
				
						writeString(this.Column43,dos);
					
					// String
				
						writeString(this.Column44,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Annee="+String.valueOf(Annee));
		sb.append(",EDC_FabLab="+EDC_FabLab);
		sb.append(",FORMATION="+FORMATION);
		sb.append(",DATE="+DATE);
		sb.append(",ETAT="+ETAT);
		sb.append(",Profil_recommande_pour_Job_Dating="+Profil_recommande_pour_Job_Dating);
		sb.append(",EMAIL="+EMAIL);
		sb.append(",First_Name="+First_Name);
		sb.append(",Last_Name="+Last_Name);
		sb.append(",gender="+gender);
		sb.append(",profession="+profession);
		sb.append(",Votre_numero_de_telephone="+Votre_numero_de_telephone);
		sb.append(",birthDay="+birthDay);
		sb.append(",country="+country);
		sb.append(",Nom_Prenom="+Nom_Prenom);
		sb.append(",Votre_Age="+Votre_Age);
		sb.append(",Categorie_d_age="+Categorie_d_age);
		sb.append(",Votre_niveau_d_etudes="+Votre_niveau_d_etudes);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_Vous_deja_participer_au_programmes_ODC="+Avez_Vous_deja_participer_au_programmes_ODC);
		sb.append(",linkedIn="+linkedIn);
		sb.append(",userCV="+userCV);
		sb.append(",Column23="+Column23);
		sb.append(",Column24="+Column24);
		sb.append(",Column25="+Column25);
		sb.append(",Column26="+Column26);
		sb.append(",Column27="+Column27);
		sb.append(",Column28="+Column28);
		sb.append(",Column29="+Column29);
		sb.append(",Column30="+Column30);
		sb.append(",Column31="+Column31);
		sb.append(",Column32="+Column32);
		sb.append(",Column33="+Column33);
		sb.append(",Column34="+Column34);
		sb.append(",Column35="+Column35);
		sb.append(",Column36="+Column36);
		sb.append(",Column37="+Column37);
		sb.append(",Column38="+Column38);
		sb.append(",Column39="+Column39);
		sb.append(",Column40="+Column40);
		sb.append(",Column41="+Column41);
		sb.append(",Column42="+Column42);
		sb.append(",Column43="+Column43);
		sb.append(",Column44="+Column44);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row1Struct row1 = new row1Struct();
row2Struct row2 = new row2Struct();





	
	/**
	 * [tBufferOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tBufferOutput_1", false);
		start_Hash.put("tBufferOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tBufferOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row2");
					}
				
		int tos_count_tBufferOutput_1 = 0;
		

 



/**
 * [tBufferOutput_1 begin ] stop
 */



	
	/**
	 * [tJavaRow_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tJavaRow_1", false);
		start_Hash.put("tJavaRow_1", System.currentTimeMillis());
		
	
	currentComponent="tJavaRow_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row1");
					}
				
		int tos_count_tJavaRow_1 = 0;
		

int nb_line_tJavaRow_1 = 0;

 



/**
 * [tJavaRow_1 begin ] stop
 */



	
	/**
	 * [tFileInputExcel_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tFileInputExcel_1", false);
		start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());
		
	
	currentComponent="tFileInputExcel_1";

	
		int tos_count_tFileInputExcel_1 = 0;
		

 
	final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:s+P4gdgGQH6Aa3y+e23a5QwK8ZE8XSr/MebvyA==");
        String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
        if (password_tFileInputExcel_1.isEmpty()){
            password_tFileInputExcel_1 = null;
        }
			class RegexUtil_tFileInputExcel_1 {

		    	public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName, boolean useRegex) {

			        java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

			        if(useRegex){//this part process the regex issue

				        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
				        for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
				            String sheetName = sheet.getSheetName();
				            java.util.regex.Matcher matcher = pattern.matcher(sheetName);
				            if (matcher.matches()) {
				            	if(sheet != null){
				                	list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
				                }
				            }
				        }

			        }else{
			        	org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook.getSheet(oneSheetName);
		            	if(sheet != null){
		                	list.add(sheet);
		                }

			        }

			        return list;
			    }

			    public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
			    	java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list =  new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
			    	org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook.getSheetAt(index);
	            	if(sheet != null){
	                	list.add(sheet);
	                }
			    	return list;
			    }

			}
		RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

		Object source_tFileInputExcel_1 = "dataset.xlsx";
		org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

		if(source_tFileInputExcel_1 instanceof String){
			workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory.create(new java.io.File((String)source_tFileInputExcel_1), password_tFileInputExcel_1, true);
		} else if(source_tFileInputExcel_1 instanceof java.io.InputStream) {
     		workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory.create((java.io.InputStream)source_tFileInputExcel_1, password_tFileInputExcel_1);
		} else{
			workbook_tFileInputExcel_1 = null;
			throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
		}
		try {

		java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
        sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "EDC et FabLab ", false));
        sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Sheet2", false));
    	if(sheetList_tFileInputExcel_1.size() <= 0){
            throw new RuntimeException("Special sheets not exist!");
        }

		java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
		for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
			if(sheet_FilterNull_tFileInputExcel_1!=null && sheetList_FilterNull_tFileInputExcel_1.iterator()!=null && sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()){
				sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
			}
		}
		sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
	if(sheetList_tFileInputExcel_1.size()>0){
		int nb_line_tFileInputExcel_1 = 0;

        int begin_line_tFileInputExcel_1 = 1;

        int footer_input_tFileInputExcel_1 = 0;

        int end_line_tFileInputExcel_1=0;
        for(org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1:sheetList_tFileInputExcel_1){
			end_line_tFileInputExcel_1+=(sheet_tFileInputExcel_1.getLastRowNum()+1);
        }
        end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
        int limit_tFileInputExcel_1 = -1;
        int start_column_tFileInputExcel_1 = 1-1;
        int end_column_tFileInputExcel_1 = -1;

        org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
        org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0);
        int rowCount_tFileInputExcel_1 = 0;
        int sheetIndex_tFileInputExcel_1 = 0;
        int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum()+1);

		//for the number format
        java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat("#.####################################");
        char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols().getDecimalSeparator();
		
        for(int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++){

        	int emptyColumnCount_tFileInputExcel_1 = 0;

        	if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
        		break;
        	}

            while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
                rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
                sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(++sheetIndex_tFileInputExcel_1);
                currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum()+1);
            }
            globalMap.put("tFileInputExcel_1_CURRENT_SHEET",sheet_tFileInputExcel_1.getSheetName());
            if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
                row_tFileInputExcel_1 = sheet_tFileInputExcel_1.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
            }
		    row1 = null;
					int tempRowLength_tFileInputExcel_1 = 45;
				
				int columnIndex_tFileInputExcel_1 = 0;
			
			String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
			int excel_end_column_tFileInputExcel_1;
			if(row_tFileInputExcel_1==null){
				excel_end_column_tFileInputExcel_1=0;
			}else{
				excel_end_column_tFileInputExcel_1=row_tFileInputExcel_1.getLastCellNum();
			}
			int actual_end_column_tFileInputExcel_1;
			if(end_column_tFileInputExcel_1 == -1){
				actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
			}
			else{
				actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 >	excel_end_column_tFileInputExcel_1 ? excel_end_column_tFileInputExcel_1 : end_column_tFileInputExcel_1;
			}
			org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
			for(int i=0;i<tempRowLength_tFileInputExcel_1;i++){
				if(i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1){
					org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1.getCell(i + start_column_tFileInputExcel_1);
					if(cell_tFileInputExcel_1!=null){
					switch (cell_tFileInputExcel_1.getCellType()) {
                        case STRING:
                            temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getRichStringCellValue().getString();
                            break;
                        case NUMERIC:
                            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell_tFileInputExcel_1)) {
									temp_row_tFileInputExcel_1[i] =cell_tFileInputExcel_1.getDateCellValue().toString();
                            } else {
                                temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1.format(cell_tFileInputExcel_1.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            temp_row_tFileInputExcel_1[i] =String.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
                            break;
                        case FORMULA:
        					switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
                                case STRING:
                                    temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getRichStringCellValue().getString();
                                    break;
                                case NUMERIC:
                                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell_tFileInputExcel_1)) {
											temp_row_tFileInputExcel_1[i] =cell_tFileInputExcel_1.getDateCellValue().toString();
                                    } else {
	                                    ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(cell_tFileInputExcel_1.getNumericCellValue());
										temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1.getStringValue();
                                    }
                                    break;
                                case BOOLEAN:
                                    temp_row_tFileInputExcel_1[i] =String.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
                                    break;
                                default:
                            		temp_row_tFileInputExcel_1[i] = "";
                            }
                            break;
                        default:
                            temp_row_tFileInputExcel_1[i] = "";
                        }
                	}
                	else{
                		temp_row_tFileInputExcel_1[i]="";
                	}

				}else{
					temp_row_tFileInputExcel_1[i]="";
				}
			}
			boolean whetherReject_tFileInputExcel_1 = false;
			row1 = new row1Struct();
			int curColNum_tFileInputExcel_1 = -1;
			String curColName_tFileInputExcel_1 = "";
			try{
							columnIndex_tFileInputExcel_1 = 0;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Annee";

				row1.Annee = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.Annee = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 1;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "EDC_FabLab";

				row1.EDC_FabLab = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.EDC_FabLab = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 2;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "FORMATION";

				row1.FORMATION = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.FORMATION = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 3;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "DATE";

				row1.DATE = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.DATE = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 4;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "ETAT";

				row1.ETAT = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.ETAT = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 5;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Profil_recommande_pour_Job_Dating";

				row1.Profil_recommande_pour_Job_Dating = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Profil_recommande_pour_Job_Dating = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 6;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "EMAIL";

				row1.EMAIL = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.EMAIL = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 7;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "First_Name";

				row1.First_Name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.First_Name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 8;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Last_Name";

				row1.Last_Name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Last_Name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 9;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "gender";

				row1.gender = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.gender = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 10;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "profession";

				row1.profession = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.profession = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 11;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Votre_numero_de_telephone";

				row1.Votre_numero_de_telephone = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Votre_numero_de_telephone = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 12;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "birthDay";

				row1.birthDay = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.birthDay = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 13;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "country";

				row1.country = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.country = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 14;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Nom_Prenom";

				row1.Nom_Prenom = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Nom_Prenom = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 15;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Votre_Age";

				row1.Votre_Age = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Votre_Age = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 16;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Categorie_d_age";

				row1.Categorie_d_age = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Categorie_d_age = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 17;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Votre_niveau_d_etudes";

				row1.Votre_niveau_d_etudes = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Votre_niveau_d_etudes = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 18;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Votre_specialite";

				row1.Votre_specialite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Votre_specialite = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 19;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Etablissement";

				row1.Etablissement = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Etablissement = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 20;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Avez_Vous_deja_participer_au_programmes_ODC";

				row1.Avez_Vous_deja_participer_au_programmes_ODC = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Avez_Vous_deja_participer_au_programmes_ODC = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 21;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "linkedIn";

				row1.linkedIn = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.linkedIn = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 22;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "userCV";

				row1.userCV = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.userCV = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 23;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column23";

				row1.Column23 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column23 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 24;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column24";

				row1.Column24 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column24 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 25;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column25";

				row1.Column25 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column25 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 26;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column26";

				row1.Column26 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column26 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 27;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column27";

				row1.Column27 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column27 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 28;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column28";

				row1.Column28 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column28 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 29;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column29";

				row1.Column29 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column29 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 30;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column30";

				row1.Column30 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column30 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 31;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column31";

				row1.Column31 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column31 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 32;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column32";

				row1.Column32 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column32 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 33;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column33";

				row1.Column33 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column33 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 34;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column34";

				row1.Column34 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column34 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 35;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column35";

				row1.Column35 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column35 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 36;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column36";

				row1.Column36 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column36 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 37;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column37";

				row1.Column37 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column37 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 38;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column38";

				row1.Column38 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column38 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 39;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column39";

				row1.Column39 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column39 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 40;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column40";

				row1.Column40 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column40 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 41;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column41";

				row1.Column41 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column41 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 42;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column42";

				row1.Column42 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column42 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 43;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column43";

				row1.Column43 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column43 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 44;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Column44";

				row1.Column44 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Column44 = null;
				emptyColumnCount_tFileInputExcel_1++;
			}

				nb_line_tFileInputExcel_1++;
				
			}catch(java.lang.Exception e){
globalMap.put("tFileInputExcel_1_ERROR_MESSAGE",e.getMessage());
			whetherReject_tFileInputExcel_1 = true;
					 System.err.println(e.getMessage());
					 row1 = null;
			}


		



 



/**
 * [tFileInputExcel_1 begin ] stop
 */
	
	/**
	 * [tFileInputExcel_1 main ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 


	tos_count_tFileInputExcel_1++;

/**
 * [tFileInputExcel_1 main ] stop
 */
	
	/**
	 * [tFileInputExcel_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 process_data_begin ] stop
 */
// Start of branch "row1"
if(row1 != null) { 



	
	/**
	 * [tJavaRow_1 main ] start
	 */

	

	
	
	currentComponent="tJavaRow_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row1"
						
						);
					}
					

    //Code généré selon les schémas d'entrée et de sortie
row2.Annee = row1.Annee;
row2.EDC_FabLab = row1.EDC_FabLab;
row2.FORMATION = row1.FORMATION;
row2.DATE = row1.DATE;
row2.ETAT = row1.ETAT;
row2.Profil_recommande_pour_Job_Dating = row1.Profil_recommande_pour_Job_Dating;
row2.EMAIL = row1.EMAIL;
row2.First_Name = row1.First_Name;
row2.Last_Name = row1.Last_Name;
row2.gender = row1.gender;
row2.profession = row1.profession;
row2.Votre_numero_de_telephone = row1.Votre_numero_de_telephone;
row2.birthDay = row1.birthDay;
row2.country = row1.country;
row2.Nom_Prenom = row1.Nom_Prenom;
row2.Votre_Age = row1.Votre_Age;
row2.Categorie_d_age = row1.Categorie_d_age;
row2.Votre_specialite = row1.Votre_specialite;
row2.Etablissement = row1.Etablissement;
row2.Avez_Vous_deja_participer_au_programmes_ODC = row1.Avez_Vous_deja_participer_au_programmes_ODC;
row2.Votre_niveau_d_etudes = row1.Votre_niveau_d_etudes;

// Parcours de tous les champs de la ligne de sortie (row2) pour appliquer l'encodage
for (java.lang.reflect.Field field : row2.getClass().getDeclaredFields()) {
    field.setAccessible(true); // Permet d'accéder aux champs privés si nécessaire

    try {
        Object value = field.get(row2);
        if (value instanceof String) { 
            String correctedValue = ((String) value)
                .replace("Ã‰", "É")
                .replace("ã‰", "é")
                .replace("Ã¨", "è")
                .replace("Ã©", "é")
                .replace("Ã ", "à")
                .replace("Ã¢", "â")
                .replace("Ã®", "î")
                .replace("Ã´", "ô")
                .replace("Ã»", "û")
                .replace("Ã§", "ç");

            field.set(row2, correctedValue);
        }
    } catch (IllegalAccessException e) {
        System.err.println("Erreur lors de l'accès au champ : " + field.getName() + " - " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Erreur générale : " + e.getMessage());
    }
}

    nb_line_tJavaRow_1++;   

 


	tos_count_tJavaRow_1++;

/**
 * [tJavaRow_1 main ] stop
 */
	
	/**
	 * [tJavaRow_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tJavaRow_1";

	

 



/**
 * [tJavaRow_1 process_data_begin ] stop
 */

	
	/**
	 * [tBufferOutput_1 main ] start
	 */

	

	
	
	currentComponent="tBufferOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
						);
					}
					



String[] row_tBufferOutput_1=new String[]{"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",};		
	    if(row2.Annee != null){
	        
	            row_tBufferOutput_1[0] = String.valueOf(row2.Annee);
	                        			    
	    }else{
	    	row_tBufferOutput_1[0] = null;
	    }
	    if(row2.EDC_FabLab != null){
	        
	            row_tBufferOutput_1[1] = row2.EDC_FabLab;
	                        			    
	    }else{
	    	row_tBufferOutput_1[1] = null;
	    }
	    if(row2.FORMATION != null){
	        
	            row_tBufferOutput_1[2] = row2.FORMATION;
	                        			    
	    }else{
	    	row_tBufferOutput_1[2] = null;
	    }
	    if(row2.DATE != null){
	        
	            row_tBufferOutput_1[3] = row2.DATE;
	                        			    
	    }else{
	    	row_tBufferOutput_1[3] = null;
	    }
	    if(row2.ETAT != null){
	        
	            row_tBufferOutput_1[4] = row2.ETAT;
	                        			    
	    }else{
	    	row_tBufferOutput_1[4] = null;
	    }
	    if(row2.Profil_recommande_pour_Job_Dating != null){
	        
	            row_tBufferOutput_1[5] = row2.Profil_recommande_pour_Job_Dating;
	                        			    
	    }else{
	    	row_tBufferOutput_1[5] = null;
	    }
	    if(row2.EMAIL != null){
	        
	            row_tBufferOutput_1[6] = row2.EMAIL;
	                        			    
	    }else{
	    	row_tBufferOutput_1[6] = null;
	    }
	    if(row2.First_Name != null){
	        
	            row_tBufferOutput_1[7] = row2.First_Name;
	                        			    
	    }else{
	    	row_tBufferOutput_1[7] = null;
	    }
	    if(row2.Last_Name != null){
	        
	            row_tBufferOutput_1[8] = row2.Last_Name;
	                        			    
	    }else{
	    	row_tBufferOutput_1[8] = null;
	    }
	    if(row2.gender != null){
	        
	            row_tBufferOutput_1[9] = row2.gender;
	                        			    
	    }else{
	    	row_tBufferOutput_1[9] = null;
	    }
	    if(row2.profession != null){
	        
	            row_tBufferOutput_1[10] = row2.profession;
	                        			    
	    }else{
	    	row_tBufferOutput_1[10] = null;
	    }
	    if(row2.Votre_numero_de_telephone != null){
	        
	            row_tBufferOutput_1[11] = row2.Votre_numero_de_telephone;
	                        			    
	    }else{
	    	row_tBufferOutput_1[11] = null;
	    }
	    if(row2.birthDay != null){
	        
	            row_tBufferOutput_1[12] = row2.birthDay;
	                        			    
	    }else{
	    	row_tBufferOutput_1[12] = null;
	    }
	    if(row2.country != null){
	        
	            row_tBufferOutput_1[13] = row2.country;
	                        			    
	    }else{
	    	row_tBufferOutput_1[13] = null;
	    }
	    if(row2.Nom_Prenom != null){
	        
	            row_tBufferOutput_1[14] = row2.Nom_Prenom;
	                        			    
	    }else{
	    	row_tBufferOutput_1[14] = null;
	    }
	    if(row2.Votre_Age != null){
	        
	            row_tBufferOutput_1[15] = row2.Votre_Age;
	                        			    
	    }else{
	    	row_tBufferOutput_1[15] = null;
	    }
	    if(row2.Categorie_d_age != null){
	        
	            row_tBufferOutput_1[16] = row2.Categorie_d_age;
	                        			    
	    }else{
	    	row_tBufferOutput_1[16] = null;
	    }
	    if(row2.Votre_niveau_d_etudes != null){
	        
	            row_tBufferOutput_1[17] = row2.Votre_niveau_d_etudes;
	                        			    
	    }else{
	    	row_tBufferOutput_1[17] = null;
	    }
	    if(row2.Votre_specialite != null){
	        
	            row_tBufferOutput_1[18] = row2.Votre_specialite;
	                        			    
	    }else{
	    	row_tBufferOutput_1[18] = null;
	    }
	    if(row2.Etablissement != null){
	        
	            row_tBufferOutput_1[19] = row2.Etablissement;
	                        			    
	    }else{
	    	row_tBufferOutput_1[19] = null;
	    }
	    if(row2.Avez_Vous_deja_participer_au_programmes_ODC != null){
	        
	            row_tBufferOutput_1[20] = row2.Avez_Vous_deja_participer_au_programmes_ODC;
	                        			    
	    }else{
	    	row_tBufferOutput_1[20] = null;
	    }
	    if(row2.linkedIn != null){
	        
	            row_tBufferOutput_1[21] = row2.linkedIn;
	                        			    
	    }else{
	    	row_tBufferOutput_1[21] = null;
	    }
	    if(row2.userCV != null){
	        
	            row_tBufferOutput_1[22] = row2.userCV;
	                        			    
	    }else{
	    	row_tBufferOutput_1[22] = null;
	    }
	    if(row2.Column23 != null){
	        
	            row_tBufferOutput_1[23] = row2.Column23;
	                        			    
	    }else{
	    	row_tBufferOutput_1[23] = null;
	    }
	    if(row2.Column24 != null){
	        
	            row_tBufferOutput_1[24] = row2.Column24;
	                        			    
	    }else{
	    	row_tBufferOutput_1[24] = null;
	    }
	    if(row2.Column25 != null){
	        
	            row_tBufferOutput_1[25] = row2.Column25;
	                        			    
	    }else{
	    	row_tBufferOutput_1[25] = null;
	    }
	    if(row2.Column26 != null){
	        
	            row_tBufferOutput_1[26] = row2.Column26;
	                        			    
	    }else{
	    	row_tBufferOutput_1[26] = null;
	    }
	    if(row2.Column27 != null){
	        
	            row_tBufferOutput_1[27] = row2.Column27;
	                        			    
	    }else{
	    	row_tBufferOutput_1[27] = null;
	    }
	    if(row2.Column28 != null){
	        
	            row_tBufferOutput_1[28] = row2.Column28;
	                        			    
	    }else{
	    	row_tBufferOutput_1[28] = null;
	    }
	    if(row2.Column29 != null){
	        
	            row_tBufferOutput_1[29] = row2.Column29;
	                        			    
	    }else{
	    	row_tBufferOutput_1[29] = null;
	    }
	    if(row2.Column30 != null){
	        
	            row_tBufferOutput_1[30] = row2.Column30;
	                        			    
	    }else{
	    	row_tBufferOutput_1[30] = null;
	    }
	    if(row2.Column31 != null){
	        
	            row_tBufferOutput_1[31] = row2.Column31;
	                        			    
	    }else{
	    	row_tBufferOutput_1[31] = null;
	    }
	    if(row2.Column32 != null){
	        
	            row_tBufferOutput_1[32] = row2.Column32;
	                        			    
	    }else{
	    	row_tBufferOutput_1[32] = null;
	    }
	    if(row2.Column33 != null){
	        
	            row_tBufferOutput_1[33] = row2.Column33;
	                        			    
	    }else{
	    	row_tBufferOutput_1[33] = null;
	    }
	    if(row2.Column34 != null){
	        
	            row_tBufferOutput_1[34] = row2.Column34;
	                        			    
	    }else{
	    	row_tBufferOutput_1[34] = null;
	    }
	    if(row2.Column35 != null){
	        
	            row_tBufferOutput_1[35] = row2.Column35;
	                        			    
	    }else{
	    	row_tBufferOutput_1[35] = null;
	    }
	    if(row2.Column36 != null){
	        
	            row_tBufferOutput_1[36] = row2.Column36;
	                        			    
	    }else{
	    	row_tBufferOutput_1[36] = null;
	    }
	    if(row2.Column37 != null){
	        
	            row_tBufferOutput_1[37] = row2.Column37;
	                        			    
	    }else{
	    	row_tBufferOutput_1[37] = null;
	    }
	    if(row2.Column38 != null){
	        
	            row_tBufferOutput_1[38] = row2.Column38;
	                        			    
	    }else{
	    	row_tBufferOutput_1[38] = null;
	    }
	    if(row2.Column39 != null){
	        
	            row_tBufferOutput_1[39] = row2.Column39;
	                        			    
	    }else{
	    	row_tBufferOutput_1[39] = null;
	    }
	    if(row2.Column40 != null){
	        
	            row_tBufferOutput_1[40] = row2.Column40;
	                        			    
	    }else{
	    	row_tBufferOutput_1[40] = null;
	    }
	    if(row2.Column41 != null){
	        
	            row_tBufferOutput_1[41] = row2.Column41;
	                        			    
	    }else{
	    	row_tBufferOutput_1[41] = null;
	    }
	    if(row2.Column42 != null){
	        
	            row_tBufferOutput_1[42] = row2.Column42;
	                        			    
	    }else{
	    	row_tBufferOutput_1[42] = null;
	    }
	    if(row2.Column43 != null){
	        
	            row_tBufferOutput_1[43] = row2.Column43;
	                        			    
	    }else{
	    	row_tBufferOutput_1[43] = null;
	    }
	    if(row2.Column44 != null){
	        
	            row_tBufferOutput_1[44] = row2.Column44;
	                        			    
	    }else{
	    	row_tBufferOutput_1[44] = null;
	    }
	globalBuffer.add(row_tBufferOutput_1);	
	
 


	tos_count_tBufferOutput_1++;

/**
 * [tBufferOutput_1 main ] stop
 */
	
	/**
	 * [tBufferOutput_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tBufferOutput_1";

	

 



/**
 * [tBufferOutput_1 process_data_begin ] stop
 */
	
	/**
	 * [tBufferOutput_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tBufferOutput_1";

	

 



/**
 * [tBufferOutput_1 process_data_end ] stop
 */



	
	/**
	 * [tJavaRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tJavaRow_1";

	

 



/**
 * [tJavaRow_1 process_data_end ] stop
 */

} // End of branch "row1"




	
	/**
	 * [tFileInputExcel_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 process_data_end ] stop
 */
	
	/**
	 * [tFileInputExcel_1 end ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

			}
			
			
			
			globalMap.put("tFileInputExcel_1_NB_LINE",nb_line_tFileInputExcel_1);
			
				}
			
		} finally { 
				
  				if(!(source_tFileInputExcel_1 instanceof java.io.InputStream)){
  					workbook_tFileInputExcel_1.getPackage().revert();
  				}
				
		}	
		

 

ok_Hash.put("tFileInputExcel_1", true);
end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());




/**
 * [tFileInputExcel_1 end ] stop
 */

	
	/**
	 * [tJavaRow_1 end ] start
	 */

	

	
	
	currentComponent="tJavaRow_1";

	

globalMap.put("tJavaRow_1_NB_LINE",nb_line_tJavaRow_1);
				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row1");
			  	}
			  	
 

ok_Hash.put("tJavaRow_1", true);
end_Hash.put("tJavaRow_1", System.currentTimeMillis());




/**
 * [tJavaRow_1 end ] stop
 */

	
	/**
	 * [tBufferOutput_1 end ] start
	 */

	

	
	
	currentComponent="tBufferOutput_1";

	

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row2");
			  	}
			  	
 

ok_Hash.put("tBufferOutput_1", true);
end_Hash.put("tBufferOutput_1", System.currentTimeMillis());




/**
 * [tBufferOutput_1 end ] stop
 */






				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tFileInputExcel_1 finally ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 finally ] stop
 */

	
	/**
	 * [tJavaRow_1 finally ] start
	 */

	

	
	
	currentComponent="tJavaRow_1";

	

 



/**
 * [tJavaRow_1 finally ] stop
 */

	
	/**
	 * [tBufferOutput_1 finally ] start
	 */

	

	
	
	currentComponent="tBufferOutput_1";

	

 



/**
 * [tBufferOutput_1 finally ] stop
 */






				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}
	
    public String resuming_logs_dir_path = null;
    public String resuming_checkpoint_path = null;
    public String parent_part_launcher = null;
    private String resumeEntryMethodName = null;
    private boolean globalResumeTicket = false;

    public boolean watch = false;
    // portStats is null, it means don't execute the statistics
    public Integer portStats = null;
    public int portTraces = 4334;
    public String clientHost;
    public String defaultClientHost = "localhost";
    public String contextStr = "Default";
    public boolean isDefaultContext = true;
    public String pid = "0";
    public String rootPid = null;
    public String fatherPid = null;
    public String fatherNode = null;
    public long startTime = 0;
    public boolean isChildJob = false;
    public String log4jLevel = "";
    
    private boolean enableLogStash;

    private boolean execStat = true;

    private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
        protected java.util.Map<String, String> initialValue() {
            java.util.Map<String,String> threadRunResultMap = new java.util.HashMap<String, String>();
            threadRunResultMap.put("errorCode", null);
            threadRunResultMap.put("status", "");
            return threadRunResultMap;
        };
    };


    protected PropertiesWithType context_param = new PropertiesWithType();
    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

    public String status= "";
    

    public static void main(String[] args){
        final encodage encodageClass = new encodage();

        int exitCode = encodageClass.runJobInTOS(args);

        System.exit(exitCode);
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = (String[][])globalBuffer.toArray(new String[globalBuffer.size()][]);

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
        		hastBufferOutput = true;
    	
        return hastBufferOutput;
    }

    public int runJobInTOS(String[] args) {
	   	// reset status
	   	status = "";
	   	
        String lastStr = "";
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--context_param")) {
                lastStr = arg;
            } else if (lastStr.equals("")) {
                evalParam(arg);
            } else {
                evalParam(lastStr + " " + arg);
                lastStr = "";
            }
        }
        enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

    	
    	

        if(clientHost == null) {
            clientHost = defaultClientHost;
        }

        if(pid == null || "0".equals(pid)) {
            pid = TalendString.getAsciiRandomString(6);
        }

        if (rootPid==null) {
            rootPid = pid;
        }
        if (fatherPid==null) {
            fatherPid = pid;
        }else{
            isChildJob = true;
        }

        if (portStats != null) {
            // portStats = -1; //for testing
            if (portStats < 0 || portStats > 65535) {
                // issue:10869, the portStats is invalid, so this client socket can't open
                System.err.println("The statistics socket port " + portStats + " is invalid.");
                execStat = false;
            }
        } else {
            execStat = false;
        }
        boolean inOSGi = routines.system.BundleUtils.inOSGi();

        if (inOSGi) {
            java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

            if (jobProperties != null && jobProperties.get("context") != null) {
                contextStr = (String)jobProperties.get("context");
            }
        }

        try {
            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead.
            java.io.InputStream inContext = encodage.class.getClassLoader().getResourceAsStream("local_project/encodage_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = encodage.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
            }
            if (inContext != null) {
                try {
                    //defaultProps is in order to keep the original context value
                    if(context != null && context.isEmpty()) {
	                defaultProps.load(inContext);
	                context = new ContextProperties(defaultProps);
                    }
                } finally {
                    inContext.close();
                }
            } else if (!isDefaultContext) {
                //print info and job continue to run, for case: context_param is not empty.
                System.err.println("Could not find the context " + contextStr);
            }

            if(!context_param.isEmpty()) {
                context.putAll(context_param);
				//set types for params from parentJobs
				for (Object key: context_param.keySet()){
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
            }
            class ContextProcessing {
                private void processContext_0() {
                } 
                public void processAllContext() {
                        processContext_0();
                }
            }

            new ContextProcessing().processAllContext();
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }

        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {
        }

        //Resume: init the resumeUtil
        resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
        resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
        resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
        //Resume: jobStart
        resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","","","",resumeUtil.convertToJsonText(context,parametersToEncrypt));

if(execStat) {
    try {
        runStat.openSocket(!isChildJob);
        runStat.setAllPID(rootPid, fatherPid, pid, jobName);
        runStat.startThreadStat(clientHost, portStats);
        runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
    } catch (java.io.IOException ioException) {
        ioException.printStackTrace();
    }
}



	
	    java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
	    globalMap.put("concurrentHashMap", concurrentHashMap);
	

    long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long endUsedMemory = 0;
    long end = 0;

    startTime = System.currentTimeMillis();


this.globalResumeTicket = true;//to run tPreJob





this.globalResumeTicket = false;//to run others jobs

try {
errorCode = null;tFileInputExcel_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tFileInputExcel_1) {
globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

e_tFileInputExcel_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : encodage");
        }



if (execStat) {
    runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
    runStat.stopThreadStat();
}
    int returnCode = 0;


    if(errorCode == null) {
         returnCode = status != null && status.equals("failure") ? 1 : 0;
    } else {
         returnCode = errorCode.intValue();
    }
    resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","" + returnCode,"","","");

    return returnCode;

  }

    // only for OSGi env
    public void destroy() {


    }














    private java.util.Map<String, Object> getSharedConnections4REST() {
        java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();






        return connections;
    }

    private void evalParam(String arg) {
        if (arg.startsWith("--resuming_logs_dir_path")) {
            resuming_logs_dir_path = arg.substring(25);
        } else if (arg.startsWith("--resuming_checkpoint_path")) {
            resuming_checkpoint_path = arg.substring(27);
        } else if (arg.startsWith("--parent_part_launcher")) {
            parent_part_launcher = arg.substring(23);
        } else if (arg.startsWith("--watch")) {
            watch = true;
        } else if (arg.startsWith("--stat_port=")) {
            String portStatsStr = arg.substring(12);
            if (portStatsStr != null && !portStatsStr.equals("null")) {
                portStats = Integer.parseInt(portStatsStr);
            }
        } else if (arg.startsWith("--trace_port=")) {
            portTraces = Integer.parseInt(arg.substring(13));
        } else if (arg.startsWith("--client_host=")) {
            clientHost = arg.substring(14);
        } else if (arg.startsWith("--context=")) {
            contextStr = arg.substring(10);
            isDefaultContext = false;
        } else if (arg.startsWith("--father_pid=")) {
            fatherPid = arg.substring(13);
        } else if (arg.startsWith("--root_pid=")) {
            rootPid = arg.substring(11);
        } else if (arg.startsWith("--father_node=")) {
            fatherNode = arg.substring(14);
        } else if (arg.startsWith("--pid=")) {
            pid = arg.substring(6);
        } else if (arg.startsWith("--context_type")) {
            String keyValue = arg.substring(15);
			int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.setContextType(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }

            }

		} else if (arg.startsWith("--context_param")) {
            String keyValue = arg.substring(16);
            int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }
            }
        } else if (arg.startsWith("--log4jLevel=")) {
            log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {//for trunjob call
		    final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
    }
    
    private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

    private final String[][] escapeChars = {
        {"\\\\","\\"},{"\\n","\n"},{"\\'","\'"},{"\\r","\r"},
        {"\\f","\f"},{"\\b","\b"},{"\\t","\t"}
        };
    private String replaceEscapeChars (String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0],currIndex);
				if (index>=0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
    }

    public Integer getErrorCode() {
        return errorCode;
    }


    public String getStatus() {
        return status;
    }

    ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 *     120657 characters generated by Talend Open Studio for Data Integration 
 *     on the 24 mars 2025 à 11:51:50 WET
 ************************************************************************************************/