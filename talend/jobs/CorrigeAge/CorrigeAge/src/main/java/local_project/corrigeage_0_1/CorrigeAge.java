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


package local_project.corrigeage_0_1;

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
import java.util.Calendar;
import java.text.SimpleDateFormat;


@SuppressWarnings("unused")

/**
 * Job: CorrigeAge Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class CorrigeAge implements TalendJob {

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
	private final String jobName = "CorrigeAge";
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
				CorrigeAge.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(CorrigeAge.this, new Object[] { e , currentComponent, globalMap});
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

			public void tRunJob_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tJavaRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFileOutputExcel_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tLogRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRunJob_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_CorrigeAge = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[0];

	
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
				
			    public java.util.Date birthDay;

				public java.util.Date getBirthDay () {
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
				
			    public Integer Votre_Age;

				public Integer getVotre_Age () {
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
				
			    public Boolean Avez_Vous_deja_participer_au_programmes_ODC;

				public Boolean getAvez_Vous_deja_participer_au_programmes_ODC () {
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
				
			    public String dateDebut;

				public String getDateDebut () {
					return this.dateDebut;
				}
				
			    public String dateFin;

				public String getDateFin () {
					return this.dateFin;
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.Avez_Vous_deja_participer_au_programmes_ODC = null;
           				} else {
           			    	this.Avez_Vous_deja_participer_au_programmes_ODC = dis.readBoolean();
           				}
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.dateDebut = readString(dis);
					
					this.dateFin = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.Avez_Vous_deja_participer_au_programmes_ODC = null;
           				} else {
           			    	this.Avez_Vous_deja_participer_au_programmes_ODC = dis.readBoolean();
           				}
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.dateDebut = readString(dis);
					
					this.dateFin = readString(dis);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// Boolean
				
						if(this.Avez_Vous_deja_participer_au_programmes_ODC == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeBoolean(this.Avez_Vous_deja_participer_au_programmes_ODC);
		            	}
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.dateDebut,dos);
					
					// String
				
						writeString(this.dateFin,dos);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// Boolean
				
						if(this.Avez_Vous_deja_participer_au_programmes_ODC == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeBoolean(this.Avez_Vous_deja_participer_au_programmes_ODC);
		            	}
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.dateDebut,dos);
					
					// String
				
						writeString(this.dateFin,dos);
					
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
		sb.append(",birthDay="+String.valueOf(birthDay));
		sb.append(",country="+country);
		sb.append(",Nom_Prenom="+Nom_Prenom);
		sb.append(",Votre_Age="+String.valueOf(Votre_Age));
		sb.append(",Categorie_d_age="+Categorie_d_age);
		sb.append(",Votre_niveau_d_etudes="+Votre_niveau_d_etudes);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_Vous_deja_participer_au_programmes_ODC="+String.valueOf(Avez_Vous_deja_participer_au_programmes_ODC));
		sb.append(",linkedIn="+linkedIn);
		sb.append(",userCV="+userCV);
		sb.append(",dateDebut="+dateDebut);
		sb.append(",dateFin="+dateFin);
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

public static class out3Struct implements routines.system.IPersistableRow<out3Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_CorrigeAge = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[0];

	
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
				
			    public java.util.Date birthDay;

				public java.util.Date getBirthDay () {
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
				
			    public Integer Votre_Age;

				public Integer getVotre_Age () {
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
				
			    public Boolean Avez_Vous_deja_participer_au_programmes_ODC;

				public Boolean getAvez_Vous_deja_participer_au_programmes_ODC () {
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
				
			    public String dateDebut;

				public String getDateDebut () {
					return this.dateDebut;
				}
				
			    public String dateFin;

				public String getDateFin () {
					return this.dateFin;
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.Avez_Vous_deja_participer_au_programmes_ODC = null;
           				} else {
           			    	this.Avez_Vous_deja_participer_au_programmes_ODC = dis.readBoolean();
           				}
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.dateDebut = readString(dis);
					
					this.dateFin = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.Avez_Vous_deja_participer_au_programmes_ODC = null;
           				} else {
           			    	this.Avez_Vous_deja_participer_au_programmes_ODC = dis.readBoolean();
           				}
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.dateDebut = readString(dis);
					
					this.dateFin = readString(dis);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// Boolean
				
						if(this.Avez_Vous_deja_participer_au_programmes_ODC == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeBoolean(this.Avez_Vous_deja_participer_au_programmes_ODC);
		            	}
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.dateDebut,dos);
					
					// String
				
						writeString(this.dateFin,dos);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// Boolean
				
						if(this.Avez_Vous_deja_participer_au_programmes_ODC == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeBoolean(this.Avez_Vous_deja_participer_au_programmes_ODC);
		            	}
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.dateDebut,dos);
					
					// String
				
						writeString(this.dateFin,dos);
					
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
		sb.append(",birthDay="+String.valueOf(birthDay));
		sb.append(",country="+country);
		sb.append(",Nom_Prenom="+Nom_Prenom);
		sb.append(",Votre_Age="+String.valueOf(Votre_Age));
		sb.append(",Categorie_d_age="+Categorie_d_age);
		sb.append(",Votre_niveau_d_etudes="+Votre_niveau_d_etudes);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_Vous_deja_participer_au_programmes_ODC="+String.valueOf(Avez_Vous_deja_participer_au_programmes_ODC));
		sb.append(",linkedIn="+linkedIn);
		sb.append(",userCV="+userCV);
		sb.append(",dateDebut="+dateDebut);
		sb.append(",dateFin="+dateFin);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(out3Struct other) {

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

public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_CorrigeAge = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[0];

	
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
				
			    public java.util.Date birthDay;

				public java.util.Date getBirthDay () {
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
				
			    public Integer Votre_Age;

				public Integer getVotre_Age () {
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
				
			    public Boolean Avez_Vous_deja_participer_au_programmes_ODC;

				public Boolean getAvez_Vous_deja_participer_au_programmes_ODC () {
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
				
			    public String dateDebut;

				public String getDateDebut () {
					return this.dateDebut;
				}
				
			    public String dateFin;

				public String getDateFin () {
					return this.dateFin;
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.Avez_Vous_deja_participer_au_programmes_ODC = null;
           				} else {
           			    	this.Avez_Vous_deja_participer_au_programmes_ODC = dis.readBoolean();
           				}
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.dateDebut = readString(dis);
					
					this.dateFin = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.Avez_Vous_deja_participer_au_programmes_ODC = null;
           				} else {
           			    	this.Avez_Vous_deja_participer_au_programmes_ODC = dis.readBoolean();
           				}
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
					this.dateDebut = readString(dis);
					
					this.dateFin = readString(dis);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// Boolean
				
						if(this.Avez_Vous_deja_participer_au_programmes_ODC == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeBoolean(this.Avez_Vous_deja_participer_au_programmes_ODC);
		            	}
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.dateDebut,dos);
					
					// String
				
						writeString(this.dateFin,dos);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
					// String
				
						writeString(this.Categorie_d_age,dos);
					
					// String
				
						writeString(this.Votre_niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// Boolean
				
						if(this.Avez_Vous_deja_participer_au_programmes_ODC == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeBoolean(this.Avez_Vous_deja_participer_au_programmes_ODC);
		            	}
					
					// String
				
						writeString(this.linkedIn,dos);
					
					// String
				
						writeString(this.userCV,dos);
					
					// String
				
						writeString(this.dateDebut,dos);
					
					// String
				
						writeString(this.dateFin,dos);
					
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
		sb.append(",birthDay="+String.valueOf(birthDay));
		sb.append(",country="+country);
		sb.append(",Nom_Prenom="+Nom_Prenom);
		sb.append(",Votre_Age="+String.valueOf(Votre_Age));
		sb.append(",Categorie_d_age="+Categorie_d_age);
		sb.append(",Votre_niveau_d_etudes="+Votre_niveau_d_etudes);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_Vous_deja_participer_au_programmes_ODC="+String.valueOf(Avez_Vous_deja_participer_au_programmes_ODC));
		sb.append(",linkedIn="+linkedIn);
		sb.append(",userCV="+userCV);
		sb.append(",dateDebut="+dateDebut);
		sb.append(",dateFin="+dateFin);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(out1Struct other) {

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

public static class out2Struct implements routines.system.IPersistableRow<out2Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_CorrigeAge = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[0];

	
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
				
			    public java.util.Date birthDay;

				public java.util.Date getBirthDay () {
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
				
			    public Integer Votre_Age;

				public Integer getVotre_Age () {
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_Vous_deja_participer_au_programmes_ODC = readString(dis);
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.birthDay = readDate(dis);
					
					this.country = readString(dis);
					
					this.Nom_Prenom = readString(dis);
					
						this.Votre_Age = readInteger(dis);
					
					this.Categorie_d_age = readString(dis);
					
					this.Votre_niveau_d_etudes = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_Vous_deja_participer_au_programmes_ODC = readString(dis);
					
					this.linkedIn = readString(dis);
					
					this.userCV = readString(dis);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
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
					
					// java.util.Date
				
						writeDate(this.birthDay,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.Nom_Prenom,dos);
					
					// Integer
				
						writeInteger(this.Votre_Age,dos);
					
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
		sb.append(",birthDay="+String.valueOf(birthDay));
		sb.append(",country="+country);
		sb.append(",Nom_Prenom="+Nom_Prenom);
		sb.append(",Votre_Age="+String.valueOf(Votre_Age));
		sb.append(",Categorie_d_age="+Categorie_d_age);
		sb.append(",Votre_niveau_d_etudes="+Votre_niveau_d_etudes);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_Vous_deja_participer_au_programmes_ODC="+Avez_Vous_deja_participer_au_programmes_ODC);
		sb.append(",linkedIn="+linkedIn);
		sb.append(",userCV="+userCV);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(out2Struct other) {

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

public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_CorrigeAge = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[0];

	
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
				
			    public String correctedEmail;

				public String getCorrectedEmail () {
					return this.correctedEmail;
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.correctedEmail = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.correctedEmail = readString(dis);
					
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
					
					// String
				
						writeString(this.correctedEmail,dos);
					
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
					
					// String
				
						writeString(this.correctedEmail,dos);
					
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
		sb.append(",correctedEmail="+correctedEmail);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

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
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_CorrigeAge = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[0];

	
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
				
			    public String correctedEmail;

				public String getCorrectedEmail () {
					return this.correctedEmail;
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_CorrigeAge.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_CorrigeAge.length == 0) {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_CorrigeAge = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_CorrigeAge, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.correctedEmail = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_CorrigeAge) {

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
					
					this.correctedEmail = readString(dis);
					
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
					
					// String
				
						writeString(this.correctedEmail,dos);
					
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
					
					// String
				
						writeString(this.correctedEmail,dos);
					
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
		sb.append(",correctedEmail="+correctedEmail);
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
public void tRunJob_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRunJob_1_SUBPROCESS_STATE", 0);

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
row3Struct row3 = new row3Struct();
out2Struct out2 = new out2Struct();
out1Struct out1 = new out1Struct();
out3Struct out3 = new out3Struct();
out3Struct row2 = out3;









	
	/**
	 * [tLogRow_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tLogRow_1", false);
		start_Hash.put("tLogRow_1", System.currentTimeMillis());
		
	
	currentComponent="tLogRow_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row2");
					}
				
		int tos_count_tLogRow_1 = 0;
		

	///////////////////////
	
		final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
		java.io.PrintStream consoleOut_tLogRow_1 = null;	

 		StringBuilder strBuffer_tLogRow_1 = null;
		int nb_line_tLogRow_1 = 0;
///////////////////////    			



 



/**
 * [tLogRow_1 begin ] stop
 */



	
	/**
	 * [tFileOutputExcel_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tFileOutputExcel_1", false);
		start_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());
		
	
	currentComponent="tFileOutputExcel_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out3");
					}
				
		int tos_count_tFileOutputExcel_1 = 0;
		


		
		int columnIndex_tFileOutputExcel_1 = 0;
		boolean headerIsInserted_tFileOutputExcel_1 = false;
		
		
		int nb_line_tFileOutputExcel_1 = 0;

		String fileName_tFileOutputExcel_1="outdataset.xlsx";
		java.io.File file_tFileOutputExcel_1 = new java.io.File(fileName_tFileOutputExcel_1);
		boolean isFileGenerated_tFileOutputExcel_1 = true;
//create directory only if not exists
          java.io.File parentFile_tFileOutputExcel_1 = file_tFileOutputExcel_1.getParentFile();
          if (parentFile_tFileOutputExcel_1 != null && !parentFile_tFileOutputExcel_1.exists()) {
        	
             parentFile_tFileOutputExcel_1.mkdirs();
        	
          }

		jxl.write.WritableWorkbook writeableWorkbook_tFileOutputExcel_1 = null;
		jxl.write.WritableSheet writableSheet_tFileOutputExcel_1 = null;

		jxl.WorkbookSettings workbookSettings_tFileOutputExcel_1 = new jxl.WorkbookSettings();
        workbookSettings_tFileOutputExcel_1.setEncoding("ISO-8859-15");
		writeableWorkbook_tFileOutputExcel_1 = new jxl.write.biff.WritableWorkbookImpl(
            		new java.io.BufferedOutputStream(new java.io.FileOutputStream(fileName_tFileOutputExcel_1)),
            		true,
            		workbookSettings_tFileOutputExcel_1);

        writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.getSheet("Sheet1");
        if(writableSheet_tFileOutputExcel_1 == null){
        	writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.createSheet("Sheet1", writeableWorkbook_tFileOutputExcel_1.getNumberOfSheets());
		}


        //modif start
        int startRowNum_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1.getRows();
		//modif end

		int[] fitWidth_tFileOutputExcel_1 = new int[25];
		for(int i_tFileOutputExcel_1=0;i_tFileOutputExcel_1<25;i_tFileOutputExcel_1++){
		    int fitCellViewSize_tFileOutputExcel_1=writableSheet_tFileOutputExcel_1.getColumnView(i_tFileOutputExcel_1).getSize();
			fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1]=fitCellViewSize_tFileOutputExcel_1/256;
			if(fitCellViewSize_tFileOutputExcel_1%256!=0){
				fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1]+=1;
			}
		}

						final jxl.write.WritableCellFormat cell_format_birthDay_tFileOutputExcel_1=new jxl.write.WritableCellFormat(new jxl.write.DateFormat("dd/MM/yyyy"));


		if (startRowNum_tFileOutputExcel_1 == 0){
	//modif end
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(0, nb_line_tFileOutputExcel_1, "Annee"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[0]=fitWidth_tFileOutputExcel_1[0]>5?fitWidth_tFileOutputExcel_1[0]:5;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(1, nb_line_tFileOutputExcel_1, "EDC_FabLab"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[1]=fitWidth_tFileOutputExcel_1[1]>10?fitWidth_tFileOutputExcel_1[1]:10;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(2, nb_line_tFileOutputExcel_1, "FORMATION"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[2]=fitWidth_tFileOutputExcel_1[2]>9?fitWidth_tFileOutputExcel_1[2]:9;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(3, nb_line_tFileOutputExcel_1, "DATE"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[3]=fitWidth_tFileOutputExcel_1[3]>4?fitWidth_tFileOutputExcel_1[3]:4;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(4, nb_line_tFileOutputExcel_1, "ETAT"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[4]=fitWidth_tFileOutputExcel_1[4]>4?fitWidth_tFileOutputExcel_1[4]:4;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(5, nb_line_tFileOutputExcel_1, "Profil_recommande_pour_Job_Dating"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[5]=fitWidth_tFileOutputExcel_1[5]>33?fitWidth_tFileOutputExcel_1[5]:33;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(6, nb_line_tFileOutputExcel_1, "EMAIL"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[6]=fitWidth_tFileOutputExcel_1[6]>5?fitWidth_tFileOutputExcel_1[6]:5;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(7, nb_line_tFileOutputExcel_1, "First_Name"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[7]=fitWidth_tFileOutputExcel_1[7]>10?fitWidth_tFileOutputExcel_1[7]:10;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(8, nb_line_tFileOutputExcel_1, "Last_Name"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[8]=fitWidth_tFileOutputExcel_1[8]>9?fitWidth_tFileOutputExcel_1[8]:9;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(9, nb_line_tFileOutputExcel_1, "gender"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[9]=fitWidth_tFileOutputExcel_1[9]>6?fitWidth_tFileOutputExcel_1[9]:6;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(10, nb_line_tFileOutputExcel_1, "profession"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[10]=fitWidth_tFileOutputExcel_1[10]>10?fitWidth_tFileOutputExcel_1[10]:10;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(11, nb_line_tFileOutputExcel_1, "Votre_numero_de_telephone"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[11]=fitWidth_tFileOutputExcel_1[11]>25?fitWidth_tFileOutputExcel_1[11]:25;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(12, nb_line_tFileOutputExcel_1, "birthDay"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[12]=fitWidth_tFileOutputExcel_1[12]>8?fitWidth_tFileOutputExcel_1[12]:8;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(13, nb_line_tFileOutputExcel_1, "country"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[13]=fitWidth_tFileOutputExcel_1[13]>7?fitWidth_tFileOutputExcel_1[13]:7;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(14, nb_line_tFileOutputExcel_1, "Nom_Prenom"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[14]=fitWidth_tFileOutputExcel_1[14]>10?fitWidth_tFileOutputExcel_1[14]:10;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(15, nb_line_tFileOutputExcel_1, "Votre_Age"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[15]=fitWidth_tFileOutputExcel_1[15]>9?fitWidth_tFileOutputExcel_1[15]:9;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(16, nb_line_tFileOutputExcel_1, "Categorie_d_age"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[16]=fitWidth_tFileOutputExcel_1[16]>15?fitWidth_tFileOutputExcel_1[16]:15;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(17, nb_line_tFileOutputExcel_1, "Votre_niveau_d_etudes"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[17]=fitWidth_tFileOutputExcel_1[17]>21?fitWidth_tFileOutputExcel_1[17]:21;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(18, nb_line_tFileOutputExcel_1, "Votre_specialite"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[18]=fitWidth_tFileOutputExcel_1[18]>16?fitWidth_tFileOutputExcel_1[18]:16;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(19, nb_line_tFileOutputExcel_1, "Etablissement"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[19]=fitWidth_tFileOutputExcel_1[19]>13?fitWidth_tFileOutputExcel_1[19]:13;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(20, nb_line_tFileOutputExcel_1, "Avez_Vous_deja_participer_au_programmes_ODC"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[20]=fitWidth_tFileOutputExcel_1[20]>43?fitWidth_tFileOutputExcel_1[20]:43;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(21, nb_line_tFileOutputExcel_1, "linkedIn"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[21]=fitWidth_tFileOutputExcel_1[21]>8?fitWidth_tFileOutputExcel_1[21]:8;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(22, nb_line_tFileOutputExcel_1, "userCV"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[22]=fitWidth_tFileOutputExcel_1[22]>6?fitWidth_tFileOutputExcel_1[22]:6;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(23, nb_line_tFileOutputExcel_1, "dateDebut"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[23]=fitWidth_tFileOutputExcel_1[23]>9?fitWidth_tFileOutputExcel_1[23]:9;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(24, nb_line_tFileOutputExcel_1, "dateFin"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[24]=fitWidth_tFileOutputExcel_1[24]>7?fitWidth_tFileOutputExcel_1[24]:7;
		nb_line_tFileOutputExcel_1 ++;
		headerIsInserted_tFileOutputExcel_1 = true;
	}


 



/**
 * [tFileOutputExcel_1 begin ] stop
 */



	
	/**
	 * [tMap_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_3", false);
		start_Hash.put("tMap_3", System.currentTimeMillis());
		
	
	currentComponent="tMap_3";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out1");
					}
				
		int tos_count_tMap_3 = 0;
		




// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_3__Struct  {
	String id_Benificiaire;
}
Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
out3Struct out3_tmp = new out3Struct();
// ###############################

        
        



        









 



/**
 * [tMap_3 begin ] stop
 */



	
	/**
	 * [tMap_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_1", false);
		start_Hash.put("tMap_1", System.currentTimeMillis());
		
	
	currentComponent="tMap_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out2");
					}
				
		int tos_count_tMap_1 = 0;
		




// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
	String dateDebut;
	String dateFin;
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
out1Struct out1_tmp = new out1Struct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
 */



	
	/**
	 * [tMap_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_2", false);
		start_Hash.put("tMap_2", System.currentTimeMillis());
		
	
	currentComponent="tMap_2";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row3");
					}
				
		int tos_count_tMap_2 = 0;
		




// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_2__Struct  {
}
Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
out2Struct out2_tmp = new out2Struct();
// ###############################

        
        



        









 



/**
 * [tMap_2 begin ] stop
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
	 * [tRunJob_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tRunJob_1", false);
		start_Hash.put("tRunJob_1", System.currentTimeMillis());
		
	
	currentComponent="tRunJob_1";

	
		int tos_count_tRunJob_1 = 0;
		


 



/**
 * [tRunJob_1 begin ] stop
 */
	
	/**
	 * [tRunJob_1 main ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	
	java.util.List<String> paraList_tRunJob_1 = new java.util.ArrayList<String>();
	
	        				paraList_tRunJob_1.add("--father_pid="+pid);
	      			
	        				paraList_tRunJob_1.add("--root_pid="+rootPid);
	      			
	        				paraList_tRunJob_1.add("--father_node=tRunJob_1");
	      			
	        				paraList_tRunJob_1.add("--context=Default");
	      			
		if(enableLogStash){
			paraList_tRunJob_1.add("--audit.enabled="+enableLogStash);
		}
		
	//for feature:10589
	
		paraList_tRunJob_1.add("--stat_port=" + portStats);
	

	if(resuming_logs_dir_path != null){
		paraList_tRunJob_1.add("--resuming_logs_dir_path=" + resuming_logs_dir_path);
	}
	String childResumePath_tRunJob_1 = ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path);
	String tRunJobName_tRunJob_1 = ResumeUtil.getRighttRunJob(resuming_checkpoint_path);
	if("tRunJob_1".equals(tRunJobName_tRunJob_1) && childResumePath_tRunJob_1 != null){
		paraList_tRunJob_1.add("--resuming_checkpoint_path=" + ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path));
	}
	paraList_tRunJob_1.add("--parent_part_launcher=JOB:" + jobName + "/NODE:tRunJob_1");
	
	java.util.Map<String, Object> parentContextMap_tRunJob_1 = new java.util.HashMap<String, Object>();

	
		
		context.synchronizeContext();
		java.util.Enumeration<?> propertyNames_tRunJob_1 = context.propertyNames();
		while (propertyNames_tRunJob_1.hasMoreElements()) {
			String key_tRunJob_1 = (String) propertyNames_tRunJob_1.nextElement();
			Object value_tRunJob_1 = (Object) context.get(key_tRunJob_1);
			if(value_tRunJob_1!=null) {  
				paraList_tRunJob_1.add("--context_param " + key_tRunJob_1 + "=" + value_tRunJob_1);
			} else {
				paraList_tRunJob_1.add("--context_param " + key_tRunJob_1 + "=" + NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY);
			}
			
		}
		

	Object obj_tRunJob_1 = null;

	
	
		local_project.encodage_0_1.encodage childJob_tRunJob_1 = new local_project.encodage_0_1.encodage();
	    // pass DataSources
	    java.util.Map<String, routines.system.TalendDataSource> talendDataSources_tRunJob_1 = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
	            .get(KEY_DB_DATASOURCES);
	    if (null != talendDataSources_tRunJob_1) {
	        java.util.Map<String, javax.sql.DataSource> dataSources_tRunJob_1 = new java.util.HashMap<String, javax.sql.DataSource>();
	        for (java.util.Map.Entry<String, routines.system.TalendDataSource> talendDataSourceEntry_tRunJob_1 : talendDataSources_tRunJob_1
			        .entrySet()) {
	            dataSources_tRunJob_1.put(talendDataSourceEntry_tRunJob_1.getKey(),
	                    talendDataSourceEntry_tRunJob_1.getValue().getRawDataSource());
	        }
	        childJob_tRunJob_1.setDataSources(dataSources_tRunJob_1);
	    }
		  
			childJob_tRunJob_1.parentContextMap = parentContextMap_tRunJob_1;
		  
		
		String[][] childReturn_tRunJob_1 = childJob_tRunJob_1.runJob((String[]) paraList_tRunJob_1.toArray(new String[paraList_tRunJob_1.size()]));
		
            if(childJob_tRunJob_1.getErrorCode() == null){
                globalMap.put("tRunJob_1_CHILD_RETURN_CODE", childJob_tRunJob_1.getStatus() != null && ("failure").equals(childJob_tRunJob_1.getStatus()) ? 1 : 0);
            }else{
                globalMap.put("tRunJob_1_CHILD_RETURN_CODE", childJob_tRunJob_1.getErrorCode());
            }
            if (childJob_tRunJob_1.getExceptionStackTrace() != null) {
                globalMap.put("tRunJob_1_CHILD_EXCEPTION_STACKTRACE", childJob_tRunJob_1.getExceptionStackTrace());
            }
                    errorCode = childJob_tRunJob_1.getErrorCode();
                if (childJob_tRunJob_1.getErrorCode() != null || ("failure").equals(childJob_tRunJob_1.getStatus())) {
                    java.lang.Exception ce_tRunJob_1 = childJob_tRunJob_1.getException();
                    throw new RuntimeException("Child job running failed.\n" + ((ce_tRunJob_1!=null) ? (ce_tRunJob_1.getClass().getName() + ": " + ce_tRunJob_1.getMessage()) : ""));
                }
			for (String[] item_tRunJob_1 : childReturn_tRunJob_1) { 
				if(childJob_tRunJob_1.hastBufferOutputComponent() || true){
			    			
						if(0 < item_tRunJob_1.length){				
			           		
			           			row1.Annee = ParserUtils.parseTo_Integer(item_tRunJob_1[0]);
			           		
		           		}else{
			           		row1.Annee = null;
		           		}
							
						if(1 < item_tRunJob_1.length){				
			           		
				           		row1.EDC_FabLab = item_tRunJob_1[1];
			           		
		           		}else{
			           		row1.EDC_FabLab = null;
		           		}
							
						if(2 < item_tRunJob_1.length){				
			           		
				           		row1.FORMATION = item_tRunJob_1[2];
			           		
		           		}else{
			           		row1.FORMATION = null;
		           		}
							
						if(3 < item_tRunJob_1.length){				
			           		
				           		row1.DATE = item_tRunJob_1[3];
			           		
		           		}else{
			           		row1.DATE = null;
		           		}
							
						if(4 < item_tRunJob_1.length){				
			           		
				           		row1.ETAT = item_tRunJob_1[4];
			           		
		           		}else{
			           		row1.ETAT = null;
		           		}
							
						if(5 < item_tRunJob_1.length){				
			           		
				           		row1.Profil_recommande_pour_Job_Dating = item_tRunJob_1[5];
			           		
		           		}else{
			           		row1.Profil_recommande_pour_Job_Dating = null;
		           		}
							
						if(6 < item_tRunJob_1.length){				
			           		
				           		row1.EMAIL = item_tRunJob_1[6];
			           		
		           		}else{
			           		row1.EMAIL = null;
		           		}
							
						if(7 < item_tRunJob_1.length){				
			           		
				           		row1.First_Name = item_tRunJob_1[7];
			           		
		           		}else{
			           		row1.First_Name = null;
		           		}
							
						if(8 < item_tRunJob_1.length){				
			           		
				           		row1.Last_Name = item_tRunJob_1[8];
			           		
		           		}else{
			           		row1.Last_Name = null;
		           		}
							
						if(9 < item_tRunJob_1.length){				
			           		
				           		row1.gender = item_tRunJob_1[9];
			           		
		           		}else{
			           		row1.gender = null;
		           		}
							
						if(10 < item_tRunJob_1.length){				
			           		
				           		row1.profession = item_tRunJob_1[10];
			           		
		           		}else{
			           		row1.profession = null;
		           		}
							
						if(11 < item_tRunJob_1.length){				
			           		
				           		row1.Votre_numero_de_telephone = item_tRunJob_1[11];
			           		
		           		}else{
			           		row1.Votre_numero_de_telephone = null;
		           		}
							
						if(12 < item_tRunJob_1.length){				
			           		
				           		row1.birthDay = item_tRunJob_1[12];
			           		
		           		}else{
			           		row1.birthDay = null;
		           		}
							
						if(13 < item_tRunJob_1.length){				
			           		
				           		row1.country = item_tRunJob_1[13];
			           		
		           		}else{
			           		row1.country = null;
		           		}
							
						if(14 < item_tRunJob_1.length){				
			           		
				           		row1.Nom_Prenom = item_tRunJob_1[14];
			           		
		           		}else{
			           		row1.Nom_Prenom = null;
		           		}
							
						if(15 < item_tRunJob_1.length){				
			           		
				           		row1.Votre_Age = item_tRunJob_1[15];
			           		
		           		}else{
			           		row1.Votre_Age = null;
		           		}
							
						if(16 < item_tRunJob_1.length){				
			           		
				           		row1.Categorie_d_age = item_tRunJob_1[16];
			           		
		           		}else{
			           		row1.Categorie_d_age = null;
		           		}
							
						if(17 < item_tRunJob_1.length){				
			           		
				           		row1.Votre_niveau_d_etudes = item_tRunJob_1[17];
			           		
		           		}else{
			           		row1.Votre_niveau_d_etudes = null;
		           		}
							
						if(18 < item_tRunJob_1.length){				
			           		
				           		row1.Votre_specialite = item_tRunJob_1[18];
			           		
		           		}else{
			           		row1.Votre_specialite = null;
		           		}
							
						if(19 < item_tRunJob_1.length){				
			           		
				           		row1.Etablissement = item_tRunJob_1[19];
			           		
		           		}else{
			           		row1.Etablissement = null;
		           		}
							
						if(20 < item_tRunJob_1.length){				
			           		
				           		row1.Avez_Vous_deja_participer_au_programmes_ODC = item_tRunJob_1[20];
			           		
		           		}else{
			           		row1.Avez_Vous_deja_participer_au_programmes_ODC = null;
		           		}
							
						if(21 < item_tRunJob_1.length){				
			           		
				           		row1.linkedIn = item_tRunJob_1[21];
			           		
		           		}else{
			           		row1.linkedIn = null;
		           		}
							
						if(22 < item_tRunJob_1.length){				
			           		
				           		row1.userCV = item_tRunJob_1[22];
			           		
		           		}else{
			           		row1.userCV = null;
		           		}
							
						if(23 < item_tRunJob_1.length){				
			           		
				           		row1.Column23 = item_tRunJob_1[23];
			           		
		           		}else{
			           		row1.Column23 = null;
		           		}
							
						if(24 < item_tRunJob_1.length){				
			           		
				           		row1.Column24 = item_tRunJob_1[24];
			           		
		           		}else{
			           		row1.Column24 = null;
		           		}
							
						if(25 < item_tRunJob_1.length){				
			           		
				           		row1.Column25 = item_tRunJob_1[25];
			           		
		           		}else{
			           		row1.Column25 = null;
		           		}
							
						if(26 < item_tRunJob_1.length){				
			           		
				           		row1.Column26 = item_tRunJob_1[26];
			           		
		           		}else{
			           		row1.Column26 = null;
		           		}
							
						if(27 < item_tRunJob_1.length){				
			           		
				           		row1.Column27 = item_tRunJob_1[27];
			           		
		           		}else{
			           		row1.Column27 = null;
		           		}
							
						if(28 < item_tRunJob_1.length){				
			           		
				           		row1.Column28 = item_tRunJob_1[28];
			           		
		           		}else{
			           		row1.Column28 = null;
		           		}
							
						if(29 < item_tRunJob_1.length){				
			           		
				           		row1.Column29 = item_tRunJob_1[29];
			           		
		           		}else{
			           		row1.Column29 = null;
		           		}
							
						if(30 < item_tRunJob_1.length){				
			           		
				           		row1.Column30 = item_tRunJob_1[30];
			           		
		           		}else{
			           		row1.Column30 = null;
		           		}
							
						if(31 < item_tRunJob_1.length){				
			           		
				           		row1.Column31 = item_tRunJob_1[31];
			           		
		           		}else{
			           		row1.Column31 = null;
		           		}
							
						if(32 < item_tRunJob_1.length){				
			           		
				           		row1.Column32 = item_tRunJob_1[32];
			           		
		           		}else{
			           		row1.Column32 = null;
		           		}
							
						if(33 < item_tRunJob_1.length){				
			           		
				           		row1.Column33 = item_tRunJob_1[33];
			           		
		           		}else{
			           		row1.Column33 = null;
		           		}
							
						if(34 < item_tRunJob_1.length){				
			           		
				           		row1.Column34 = item_tRunJob_1[34];
			           		
		           		}else{
			           		row1.Column34 = null;
		           		}
							
						if(35 < item_tRunJob_1.length){				
			           		
				           		row1.Column35 = item_tRunJob_1[35];
			           		
		           		}else{
			           		row1.Column35 = null;
		           		}
							
						if(36 < item_tRunJob_1.length){				
			           		
				           		row1.Column36 = item_tRunJob_1[36];
			           		
		           		}else{
			           		row1.Column36 = null;
		           		}
							
						if(37 < item_tRunJob_1.length){				
			           		
				           		row1.Column37 = item_tRunJob_1[37];
			           		
		           		}else{
			           		row1.Column37 = null;
		           		}
							
						if(38 < item_tRunJob_1.length){				
			           		
				           		row1.Column38 = item_tRunJob_1[38];
			           		
		           		}else{
			           		row1.Column38 = null;
		           		}
							
						if(39 < item_tRunJob_1.length){				
			           		
				           		row1.Column39 = item_tRunJob_1[39];
			           		
		           		}else{
			           		row1.Column39 = null;
		           		}
							
						if(40 < item_tRunJob_1.length){				
			           		
				           		row1.Column40 = item_tRunJob_1[40];
			           		
		           		}else{
			           		row1.Column40 = null;
		           		}
							
						if(41 < item_tRunJob_1.length){				
			           		
				           		row1.Column41 = item_tRunJob_1[41];
			           		
		           		}else{
			           		row1.Column41 = null;
		           		}
							
						if(42 < item_tRunJob_1.length){				
			           		
				           		row1.Column42 = item_tRunJob_1[42];
			           		
		           		}else{
			           		row1.Column42 = null;
		           		}
							
						if(43 < item_tRunJob_1.length){				
			           		
				           		row1.Column43 = item_tRunJob_1[43];
			           		
		           		}else{
			           		row1.Column43 = null;
		           		}
							
						if(44 < item_tRunJob_1.length){				
			           		
				           		row1.Column44 = item_tRunJob_1[44];
			           		
		           		}else{
			           		row1.Column44 = null;
		           		}
							
						if(45 < item_tRunJob_1.length){				
			           		
				           		row1.correctedEmail = item_tRunJob_1[45];
			           		
		           		}else{
			           		row1.correctedEmail = null;
		           		}
					
				}
		

 


	tos_count_tRunJob_1++;

/**
 * [tRunJob_1 main ] stop
 */
	
	/**
	 * [tRunJob_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	

 



/**
 * [tRunJob_1 process_data_begin ] stop
 */

	
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
row3.Annee = row1.Annee;
row3.EDC_FabLab = row1.EDC_FabLab;
row3.FORMATION = row1.FORMATION;
row3.DATE = row1.DATE;
row3.ETAT = row1.ETAT;
row3.Profil_recommande_pour_Job_Dating = row1.Profil_recommande_pour_Job_Dating;
row3.EMAIL = row1.EMAIL;
row3.First_Name = row1.First_Name;
row3.Last_Name = row1.Last_Name;
row3.gender = row1.gender;
row3.profession = row1.profession;
row3.Votre_numero_de_telephone = row1.Votre_numero_de_telephone;
row3.birthDay = row1.birthDay;
row3.country = row1.country;
row3.Nom_Prenom = row1.Nom_Prenom;
row3.Votre_Age = row1.Votre_Age;
row3.Categorie_d_age = row1.Categorie_d_age;
row3.Votre_niveau_d_etudes = row1.Votre_niveau_d_etudes;
row3.Votre_specialite = row1.Votre_specialite;
row3.Etablissement = row1.Etablissement;
row3.Avez_Vous_deja_participer_au_programmes_ODC = row1.Avez_Vous_deja_participer_au_programmes_ODC;
row3.linkedIn = row1.linkedIn;
row3.userCV = row1.userCV;

// Code pour tJavaRow - Correction automatique des domaines d'email
String email = row1.EMAIL;
String correctedEmail = email;

if (email != null && email.contains("@")) {
    // Extraction du nom d'utilisateur et du domaine
    String[] parts = email.split("@");
    String username = parts[0];
    String domain = parts[1].toLowerCase();
    
    // Table de correspondance des domaines incorrects vers corrects
    java.util.Map<String, String> domainCorrections = new java.util.HashMap<String, String>();
    domainCorrections.put("gmal.com", "gmail.com");
    domainCorrections.put("gmil.com", "gmail.com");
    domainCorrections.put("gmail.co", "gmail.com");
    domainCorrections.put("gmail.cm", "gmail.com");
    domainCorrections.put("gmai.com", "gmail.com");
    domainCorrections.put("hotmal.com", "hotmail.com");
    domainCorrections.put("hotmal.fr", "hotmail.fr");
    domainCorrections.put("hotmil.com", "hotmail.com");
    domainCorrections.put("hotmial.com", "hotmail.com");
    domainCorrections.put("yaho.com", "yahoo.com");
    domainCorrections.put("yaho.fr", "yahoo.fr");
    domainCorrections.put("yahou.com", "yahoo.com");
    domainCorrections.put("yahou.fr", "yahoo.fr");
    domainCorrections.put("outlook.co", "outlook.com");
    domainCorrections.put("outlok.com", "outlook.com");
    
    // Vérification et correction du domaine
    if (domainCorrections.containsKey(domain)) {
        String correctedDomain = domainCorrections.get(domain);
        correctedEmail = username + "@" + correctedDomain;
        System.out.println("Correction de domaine: " + domain + " -> " + correctedDomain);
    }
}

// Affecter l'email corrigé à la sortie
row3.correctedEmail = correctedEmail;

// Normalisation du numéro de téléphone
String phone = row1.Votre_numero_de_telephone;  // Remplacer par le nom exact de la colonne
if (phone != null && !phone.trim().isEmpty()) {
    phone = phone.trim();  // Supprimer les espaces avant et après
    phone = phone.replaceAll("[^0-9]", "");  // Garder uniquement les chiffres


    // Garder les 9 derniers chiffres après +212
    if (phone.length() > 9) {
        phone = phone.substring(phone.length() - 9);  // Garder les 9 derniers chiffres
    }

    row3.Votre_numero_de_telephone = "0" + phone;  // Ajouter +212
} else {
    row3.Votre_numero_de_telephone = "";
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
	 * [tMap_2 main ] start
	 */

	

	
	
	currentComponent="tMap_2";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row3"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_2 = false;
		  boolean mainRowRejected_tMap_2 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
        // ###############################
        // # Output tables

out2 = null;


// # Output table : 'out2'
out2_tmp.Annee = row3.Annee ;
out2_tmp.EDC_FabLab = row3.EDC_FabLab ;
out2_tmp.FORMATION = row3.FORMATION ;
out2_tmp.DATE = row3.DATE ;
out2_tmp.ETAT = row3.ETAT != null ? 
    (row3.ETAT.equals("Confirmé / Présent") ? "Confirmé / Présent" :
    row3.ETAT.equals("Confirmé / Absent") ? "Confirmé / Absent" :
    row3.ETAT.equals("Confirmé / Abandonné") || 
    row3.ETAT.equals("Présent / Abandonné") ||
    row3.ETAT.equals("Confirmé / Abondonné") ? "Confirmé / Abandonné" :
    row3.ETAT.equals("Absence justifiée") || 
    row3.ETAT.equals("Absent / Justifié") ? "Absent / Justifié" :
    row3.ETAT.equals("No Confirme") || 
    row3.ETAT.equals("Non Confirme") ? "Non Confirmé" :
    row3.ETAT.equals("Présent / Attention") ? "Présent / Attention" :
    row3.ETAT) : "Non Confirmé" ;
out2_tmp.Profil_recommande_pour_Job_Dating = row3.Profil_recommande_pour_Job_Dating ;
out2_tmp.EMAIL = row3.EMAIL ;
out2_tmp.First_Name = row3.First_Name ;
out2_tmp.Last_Name = row3.Last_Name ;
out2_tmp.gender = row3.gender ;
out2_tmp.profession = (row3.profession == null) ? "inconnu" :
  ((row3.profession.toLowerCase().trim().matches(".*sans emploi.*") || 
    row3.profession.toLowerCase().trim().matches(".*à la recherche .*") ||
     row3.profession.toLowerCase().trim().matches(".*a la recherche .*") ||
    row3.profession.toLowerCase().trim().matches(".*unemployed.*") ||
    row3.profession.toLowerCase().trim().matches(".*looking for a job.*")) ? "Sans Emploi" :
  ((row3.profession.toLowerCase().trim().matches(".*etudiant.*") || 
    row3.profession.toLowerCase().trim().matches(".*étudiant.*") || 
    row3.profession.toLowerCase().trim().matches(".*étudiant.e.*") || 
    row3.profession.toLowerCase().trim().matches(".*jeune diplômé.*") || 
    row3.profession.toLowerCase().trim().matches(".*jeune diplome.*") ||
    row3.profession.toLowerCase().trim().matches(".*ingénieur .*") ||
    row3.profession.toLowerCase().trim().matches(".*student.*") ||
    row3.profession.toLowerCase().trim().matches(".*young graduate.*")) ? "Etudiant" :
  ((row3.profession.toLowerCase().trim().matches(".*stagiaire.*") || 
    row3.profession.toLowerCase().trim().matches(".*intern.*")) ? "Employe" : 
  "Autre"))) ;
out2_tmp.Votre_numero_de_telephone = row3.Votre_numero_de_telephone ;
out2_tmp.birthDay = (
    row3.birthDay != null && row3.birthDay.matches("^\\d{5}$") ? 
        TalendDate.addDate(TalendDate.parseDate("yyyy-MM-dd", "1899-12-30"), Integer.parseInt(row3.birthDay), "dd") :

    row3.birthDay != null && row3.birthDay.matches("^\\d{4}/\\d{2}/\\d{2}$") ? 
        TalendDate.parseDate("yyyy/MM/dd", row3.birthDay) :

    row3.birthDay != null && row3.birthDay.matches("^\\d{2}/\\d{2}/\\d{4}$") ? 
        TalendDate.parseDate("dd/MM/yyyy", row3.birthDay) :

    row3.birthDay != null && row3.birthDay.matches("^\\d{2}-\\d{2}-\\d{4}$") ? 
        TalendDate.parseDate("dd-MM-yyyy", row3.birthDay) :

    row3.birthDay != null && row3.birthDay.matches("^\\d{4}-\\d{2}-\\d{2}$") ? 
        TalendDate.parseDate("yyyy-MM-dd", row3.birthDay) :

    row1.birthDay != null && row1.birthDay.matches("^\\d{2}_\\d{2}_\\d{4}$") ? 
        TalendDate.parseDate("dd_MM_yyyy", row1.birthDay) :

    row3.birthDay != null && row1.birthDay.matches("^\\d{4}\\\\\\d{2}\\\\\\d{2}$") ? 
        TalendDate.parseDate("yyyy\\MM\\dd", row3.birthDay) :

    null
) ;
out2_tmp.country = row3.country ;
out2_tmp.Nom_Prenom = row3.Nom_Prenom = row3.First_Name + " " + row3.Last_Name ;
out2_tmp.Votre_Age = row3.Votre_Age != null && row3.Votre_Age instanceof String 
    ? Integer.parseInt(((String) row3.Votre_Age).replaceAll("[^0-9]", "")) 
    : null ;
out2_tmp.Categorie_d_age = row3.Categorie_d_age ;
out2_tmp.Votre_niveau_d_etudes = row3.Votre_niveau_d_etudes == null ? "Sans Bac" :
(
    // Classe "Bac" qui inclut "Bac" et "Bac+1"
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac") &&
    !StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac+") ||
StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac+1")||
StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("1er")||
StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("diplo")||
StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("étudiant") ||
StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("etudiant" ) ||
StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("univer")

) ? "Bac" :
(
    // Classe "Sans Bac" (Niveau Bac, Inconnu, Étudiant sans diplôme)
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("niveaubac") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("sansbac") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("inconnu") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("sansdiplom") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("sansdiplôme")||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("lyc")||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("secondaryschool")
) ? "Sans Bac" :
(
    // Classe "Bac+2" (Bac+2, DUT, DEUST, Prépa, 2ème Année d'Université)
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac+2") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("dut") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("deust") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("prépa") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("prepa") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("2ème") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("2éme") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("2eme") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("2") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("deuxièmeannée") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("techn")
) ? "Bac+2" :
(
    // Classe "Bac+3" (Licence, Licence Professionnelle, Bac+3)
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac+3") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac+4") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("licence") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("licenceprofessionnelle") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("3èmeannée") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("3emeannee") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("3") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("troisiemeannee")||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("4èmeannée") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("4emeannee") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("4eannee") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("quatriemeannee")
) ? "Bac+3" :
(
   StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().matches(".*(bac\\+([5-9]|[1-9][0-9])|master|ingénieur|ingenieur|doctorat|phd|[5-9]e?me?année|cinquiemeannée).*") ||
    //StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac+5") ||
    //StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("bac+7") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("master") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("ingénieur") ||
    StringHandling.EREPLACE(StringHandling.TRIM(row3.Votre_niveau_d_etudes), " ", "").toLowerCase().contains("ingenieur") 
) ? "Bac+5" :
"Sans Bac"; ;
out2_tmp.Votre_specialite = row3.Votre_specialite ;
out2_tmp.Etablissement = row3.Etablissement ;
out2_tmp.Avez_Vous_deja_participer_au_programmes_ODC = row3.Avez_Vous_deja_participer_au_programmes_ODC ;
out2_tmp.linkedIn = row3.linkedIn ;
out2_tmp.userCV = row3.userCV ;
out2 = out2_tmp;
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_2 = false;










 


	tos_count_tMap_2++;

/**
 * [tMap_2 main ] stop
 */
	
	/**
	 * [tMap_2 process_data_begin ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 process_data_begin ] stop
 */
// Start of branch "out2"
if(out2 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out2"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_1 = false;
		  boolean mainRowRejected_tMap_1 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;
Var.dateDebut = // Expression tMap pour formater la date début
(out2.DATE != null) ?
    (
        // D'abord, supprimer tous les espaces dans la chaîne
        (out2.DATE.replaceAll("\\s+", "").contains("/")) ?
            (
   // Cas 1: format sans espace "jj/mm-jj/mm" (ex: "01/07-15/09")
(out2.DATE.replaceAll("\\s+", "").matches("\\d+/\\d+-\\d+/\\d+")) ?
    out2.DATE.replaceAll("\\s+", "").split("-")[0] + (out2.Annee != null ? "/" + out2.Annee : "") :
                
                // Cas 2: format sans espace "jj-jj/mm" (ex: "01-15/09")
                (out2.DATE.replaceAll("\\s+", "").contains("-") && out2.DATE.replaceAll("\\s+", "").contains("/")) ?
                    out2.DATE.replaceAll("\\s+", "").split("-")[0] + "/" + 
                    out2.DATE.replaceAll("\\s+", "").split("/")[1] + (out2.Annee != null ? "/" + out2.Annee : "") :
                
                // Cas 3: format simple "jj/mm"
                out2.DATE.replaceAll("\\s+", "").split("-")[0].split("/")[0] + "/" + 
                out2.DATE.replaceAll("\\s+", "").split("-")[0].split("/")[1] + (out2.Annee != null ? "/" + out2.Annee : "")
            ) :
            // Cas 4: format avec espace "jj/mm jj/mm" (ex: "01/07 15/09")
(out2.DATE.trim().matches("\\d+/\\d+\\s+\\d+/\\d+")) ?
    out2.DATE.trim().split("\\s+")[0] + (out2.Annee != null ? "/" + out2.Annee : "") :
            
        // Autres cas: garder la date telle quelle
        out2.DATE.trim()
    ) : null ;
Var.dateFin = // Expression tMap pour formater la date fin
(out2.DATE != null) ?
    (
        // D'abord, vérifier si la chaîne contient "/"
        (out2.DATE.contains("/")) ?
            (
                // Cas 1: format sans espace "jj/mm-jj/mm" (ex: "01/07-15/09")
                (out2.DATE.replaceAll("\\s+", "").matches("\\d+/\\d+-\\d+/\\d+")) ?
                    out2.DATE.replaceAll("\\s+", "").split("-")[1] + (out2.Annee != null ? "/" + out2.Annee : "") :
                
                // Cas 2: format sans espace "jj-jj/mm" (ex: "01-15/09")
                (out2.DATE.replaceAll("\\s+", "").contains("-") && out2.DATE.replaceAll("\\s+", "").contains("/")) ?
                    out2.DATE.replaceAll("\\s+", "").split("-")[1] + (out2.Annee != null ? "/" + out2.Annee : "") :
                
                // Cas 3: format simple "jj/mm" (pas de date de fin distincte)
                (!out2.DATE.replaceAll("\\s+", "").contains("-") && !out2.DATE.contains(" ")) ?
                    out2.DATE.replaceAll("\\s+", "") + (out2.Annee != null ? "/" + out2.Annee : "/2024") :
                
                // Cas 4: format avec espace "jj/mm jj/mm" (ex: "01/07 15/09")
                (out2.DATE.trim().matches("\\d+/\\d+\\s+\\d+/\\d+")) ?
                    out2.DATE.trim().split("\\s+")[1] + (out2.Annee != null ? "/" + out2.Annee : "/2024") :
                
                // Autres cas: garder la date telle quelle et ajouter année
                out2.DATE.trim() + (out2.Annee != null ? "/" + out2.Annee : "/2024")
            ) :
            // Si pas de "/", garder la date telle quelle et ajouter année
            out2.DATE.trim() + (out2.Annee != null ? "/" + out2.Annee : "/2024")
    ) : null ;// ###############################
        // ###############################
        // # Output tables

out1 = null;


// # Output table : 'out1'
out1_tmp.Annee = out2.Annee ;
out1_tmp.EDC_FabLab = out2.EDC_FabLab ;
out1_tmp.FORMATION = out2.FORMATION ;
out1_tmp.DATE = out2.DATE ;
out1_tmp.ETAT = out2.ETAT ;
out1_tmp.Profil_recommande_pour_Job_Dating = (row1.ETAT != null && 
 (row1.ETAT.trim().equals("Confirmé / Présent") || row1.ETAT.trim().equals("Absence justifiée")) && 
 row1.profession != null && 
 (row1.profession.equals("Etudiant") || row1.profession.equals("Sans Emploi")) &&
 row1.Votre_niveau_d_etudes != null && 
 (row1.Votre_niveau_d_etudes.equals("Bac+2") || row1.Votre_niveau_d_etudes.equals("Bac+3") || row1.Votre_niveau_d_etudes.equals("Bac+5")))
? "Je recommande" 
// Condition pour "Neutre"
: (row1.ETAT != null && 
   (row1.ETAT.trim().equals("Confirmé / Présent") || row1.ETAT.trim().equals("Absence justifiée")) && 
   row1.profession != null && 
   (row1.profession.equals("Etudiant") || row1.profession.equals("Sans Emploi")) &&
   row1.Votre_niveau_d_etudes != null && 
   (row1.Votre_niveau_d_etudes.equals("Bac") || row1.Votre_niveau_d_etudes.equals("Sans Bac")))
  ? "Neutre" 
  // Condition pour "Je ne recommande pas"
  : (row1.ETAT != null && 
     (row1.ETAT.trim().equals("Confirmé / Abandonné") || row1.ETAT.trim().equals("Confirmé / Absent")))
    ? "Je ne recommande pas" 
    // Valeur par défaut : conserver la valeur existante
    : row1.Profil_recommande_pour_Job_Dating ;
out1_tmp.EMAIL = out2.EMAIL ;
out1_tmp.First_Name = out2.First_Name ;
out1_tmp.Last_Name = out2.Last_Name ;
out1_tmp.gender = out2.gender ;
out1_tmp.profession = out2.profession ;
out1_tmp.Votre_numero_de_telephone = out2.Votre_numero_de_telephone ;
out1_tmp.birthDay = (out2.birthDay == null && out2.Annee != null && out2.Votre_Age != null) ? 
    TalendDate.addDate(TalendDate.parseDate("yyyy-MM-dd", out2.Annee + "-" + 
        (out2.Votre_Age % 2 == 0 ? "01-01" : "07-01")), 
        -out2.Votre_Age, "yyyy") 
    : 
    out2.birthDay ;
out1_tmp.country = out2.country ;
out1_tmp.Nom_Prenom = out2.Nom_Prenom ;
out1_tmp.Votre_Age = out2.Votre_Age ;
out1_tmp.Categorie_d_age = out2.Categorie_d_age  =(out2.Votre_Age != null) ? 
    (out2.Votre_Age < 15 ? "<15" : 
    (out2.Votre_Age >= 15 && out2.Votre_Age <= 24 ? "[15-24]" : 
    (out2.Votre_Age >= 25 && out2.Votre_Age <= 34 ? "[25-34]" : ">=35"))) 
    : "Inconnu"; ;
out1_tmp.Votre_niveau_d_etudes = out2.Votre_niveau_d_etudes ;
out1_tmp.Votre_specialite = out2.Votre_specialite ;
out1_tmp.Etablissement = out2.Etablissement ;
out1_tmp.Avez_Vous_deja_participer_au_programmes_ODC = (out2.Avez_Vous_deja_participer_au_programmes_ODC == null) ? false : 
(out2.Avez_Vous_deja_participer_au_programmes_ODC.equals("Deux fois et (+)") || 
 out2.Avez_Vous_deja_participer_au_programmes_ODC.equals("Oui, Deux fois et (+)") || 
 out2.Avez_Vous_deja_participer_au_programmes_ODC.equals("[2]")) ;
out1_tmp.linkedIn = out2.linkedIn ;
out1_tmp.userCV = out2.userCV ;
out1_tmp.dateDebut = Var.dateDebut ;
out1_tmp.dateFin = Var.dateFin ;
out1 = out1_tmp;
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_1 = false;










 


	tos_count_tMap_1++;

/**
 * [tMap_1 main ] stop
 */
	
	/**
	 * [tMap_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_begin ] stop
 */
// Start of branch "out1"
if(out1 != null) { 



	
	/**
	 * [tMap_3 main ] start
	 */

	

	
	
	currentComponent="tMap_3";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out1"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_3 = false;
		  boolean mainRowRejected_tMap_3 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_3__Struct Var = Var__tMap_3;
Var.id_Benificiaire = TalendDate.formatDate("yyyyMMddHHmmssSSS", TalendDate.getCurrentDate()) 
              + "_" + (out1.First_Name != null ? out1.First_Name.hashCode() : 0)
; ;// ###############################
        // ###############################
        // # Output tables

out3 = null;


// # Output table : 'out3'
out3_tmp.Annee = out1.Annee ;
out3_tmp.EDC_FabLab = out1.EDC_FabLab ;
out3_tmp.FORMATION = out1.FORMATION ;
out3_tmp.DATE = out1.DATE ;
out3_tmp.ETAT = out1.ETAT ;
out3_tmp.Profil_recommande_pour_Job_Dating = (out1.ETAT != null && 
 (out1.ETAT.trim().equals("Confirmé / Présent") || out1.ETAT.trim().equals("Absence justifiée")) && 
 out1.profession != null && 
 (out1.profession.equals("Etudiant") || out1.profession.equals("Sans Emploi")) &&
 out1.Votre_niveau_d_etudes != null && 
 (out1.Votre_niveau_d_etudes.equals("Bac+2") || out1.Votre_niveau_d_etudes.equals("Bac+3") || out1.Votre_niveau_d_etudes.equals("Bac+5")))
? "Je recommande" 
// Condition pour "Neutre"
: (out1.ETAT != null && 
   (out1.ETAT.trim().equals("Confirmé / Présent") || out1.ETAT.trim().equals("Absence justifiée")) && 
   out1.profession != null && 
   (out1.profession.equals("Etudiant") || out1.profession.equals("Sans Emploi")) &&
   out1.Votre_niveau_d_etudes != null && 
   (out1.Votre_niveau_d_etudes.equals("Bac") || out1.Votre_niveau_d_etudes.equals("Sans Bac")))
  ? "Neutre" 
  // Condition pour "Je ne recommande pas"
  : (out1.ETAT != null && 
     (out1.ETAT.trim().equals("Confirmé / Abandonné") || out1.ETAT.trim().equals("Confirmé / Absent")))
    ? "Je ne recommande pas" 
    // Valeur par défaut : conserver la valeur existante
    : out1.Profil_recommande_pour_Job_Dating ;
out3_tmp.EMAIL = out1.EMAIL ;
out3_tmp.First_Name = out1.First_Name ;
out3_tmp.Last_Name = out1.Last_Name ;
out3_tmp.gender = out1.gender ;
out3_tmp.profession = out1.profession ;
out3_tmp.Votre_numero_de_telephone = out1.Votre_numero_de_telephone ;
out3_tmp.birthDay = out1.birthDay ;
out3_tmp.country = out1.country ;
out3_tmp.Nom_Prenom = out1.Nom_Prenom ;
out3_tmp.Votre_Age = out1.Votre_Age ;
out3_tmp.Categorie_d_age = out1.Categorie_d_age == null || out1.Categorie_d_age.isEmpty() ? 
    (String)globalMap.get("median_categorie_age") : 
    out1.Categorie_d_age ;
out3_tmp.Votre_niveau_d_etudes = out1.Votre_niveau_d_etudes ;
out3_tmp.Votre_specialite = out1.Votre_specialite ;
out3_tmp.Etablissement = out1.Etablissement ;
out3_tmp.Avez_Vous_deja_participer_au_programmes_ODC = out1.Avez_Vous_deja_participer_au_programmes_ODC ;
out3_tmp.linkedIn = out1.linkedIn ;
out3_tmp.userCV = out1.userCV ;
out3_tmp.dateDebut = out1.dateDebut ;
out3_tmp.dateFin = out1.dateFin ;
out3 = out3_tmp;
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_3 = false;










 


	tos_count_tMap_3++;

/**
 * [tMap_3 main ] stop
 */
	
	/**
	 * [tMap_3 process_data_begin ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

 



/**
 * [tMap_3 process_data_begin ] stop
 */
// Start of branch "out3"
if(out3 != null) { 



	
	/**
	 * [tFileOutputExcel_1 main ] start
	 */

	

	
	
	currentComponent="tFileOutputExcel_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out3"
						
						);
					}
					

								   				
	    				if(out3.Annee != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 0;
					

					
						jxl.write.WritableCell cell_0_tFileOutputExcel_1 = new jxl.write.Number(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Annee
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_0_tFileOutputExcel_1);
							int currentWith_0_tFileOutputExcel_1 = String.valueOf(((jxl.write.Number)cell_0_tFileOutputExcel_1).getValue()).trim().length();
							currentWith_0_tFileOutputExcel_1=currentWith_0_tFileOutputExcel_1>10?10:currentWith_0_tFileOutputExcel_1;
							fitWidth_tFileOutputExcel_1[0]=fitWidth_tFileOutputExcel_1[0]>currentWith_0_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[0]:currentWith_0_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.EDC_FabLab != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 1;
					

					
						jxl.write.WritableCell cell_1_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.EDC_FabLab
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_1_tFileOutputExcel_1);
							int currentWith_1_tFileOutputExcel_1 = cell_1_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[1]=fitWidth_tFileOutputExcel_1[1]>currentWith_1_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[1]:currentWith_1_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.FORMATION != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 2;
					

					
						jxl.write.WritableCell cell_2_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.FORMATION
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_2_tFileOutputExcel_1);
							int currentWith_2_tFileOutputExcel_1 = cell_2_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[2]=fitWidth_tFileOutputExcel_1[2]>currentWith_2_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[2]:currentWith_2_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.DATE != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 3;
					

					
						jxl.write.WritableCell cell_3_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.DATE
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_3_tFileOutputExcel_1);
							int currentWith_3_tFileOutputExcel_1 = cell_3_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[3]=fitWidth_tFileOutputExcel_1[3]>currentWith_3_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[3]:currentWith_3_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.ETAT != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 4;
					

					
						jxl.write.WritableCell cell_4_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.ETAT
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_4_tFileOutputExcel_1);
							int currentWith_4_tFileOutputExcel_1 = cell_4_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[4]=fitWidth_tFileOutputExcel_1[4]>currentWith_4_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[4]:currentWith_4_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Profil_recommande_pour_Job_Dating != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 5;
					

					
						jxl.write.WritableCell cell_5_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Profil_recommande_pour_Job_Dating
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_5_tFileOutputExcel_1);
							int currentWith_5_tFileOutputExcel_1 = cell_5_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[5]=fitWidth_tFileOutputExcel_1[5]>currentWith_5_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[5]:currentWith_5_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.EMAIL != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 6;
					

					
						jxl.write.WritableCell cell_6_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.EMAIL
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_6_tFileOutputExcel_1);
							int currentWith_6_tFileOutputExcel_1 = cell_6_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[6]=fitWidth_tFileOutputExcel_1[6]>currentWith_6_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[6]:currentWith_6_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.First_Name != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 7;
					

					
						jxl.write.WritableCell cell_7_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.First_Name
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_7_tFileOutputExcel_1);
							int currentWith_7_tFileOutputExcel_1 = cell_7_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[7]=fitWidth_tFileOutputExcel_1[7]>currentWith_7_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[7]:currentWith_7_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Last_Name != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 8;
					

					
						jxl.write.WritableCell cell_8_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Last_Name
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_8_tFileOutputExcel_1);
							int currentWith_8_tFileOutputExcel_1 = cell_8_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[8]=fitWidth_tFileOutputExcel_1[8]>currentWith_8_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[8]:currentWith_8_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.gender != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 9;
					

					
						jxl.write.WritableCell cell_9_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.gender
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_9_tFileOutputExcel_1);
							int currentWith_9_tFileOutputExcel_1 = cell_9_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[9]=fitWidth_tFileOutputExcel_1[9]>currentWith_9_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[9]:currentWith_9_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.profession != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 10;
					

					
						jxl.write.WritableCell cell_10_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.profession
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_10_tFileOutputExcel_1);
							int currentWith_10_tFileOutputExcel_1 = cell_10_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[10]=fitWidth_tFileOutputExcel_1[10]>currentWith_10_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[10]:currentWith_10_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Votre_numero_de_telephone != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 11;
					

					
						jxl.write.WritableCell cell_11_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Votre_numero_de_telephone
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_11_tFileOutputExcel_1);
							int currentWith_11_tFileOutputExcel_1 = cell_11_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[11]=fitWidth_tFileOutputExcel_1[11]>currentWith_11_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[11]:currentWith_11_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.birthDay != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 12;
					

					
						jxl.write.WritableCell cell_12_tFileOutputExcel_1 = new jxl.write.DateTime(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.birthDay, cell_format_birthDay_tFileOutputExcel_1
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_12_tFileOutputExcel_1);
							int currentWith_12_tFileOutputExcel_1 = cell_12_tFileOutputExcel_1.getContents().trim().length();
							currentWith_12_tFileOutputExcel_1=12;
							fitWidth_tFileOutputExcel_1[12]=fitWidth_tFileOutputExcel_1[12]>currentWith_12_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[12]:currentWith_12_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.country != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 13;
					

					
						jxl.write.WritableCell cell_13_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.country
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_13_tFileOutputExcel_1);
							int currentWith_13_tFileOutputExcel_1 = cell_13_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[13]=fitWidth_tFileOutputExcel_1[13]>currentWith_13_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[13]:currentWith_13_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Nom_Prenom != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 14;
					

					
						jxl.write.WritableCell cell_14_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Nom_Prenom
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_14_tFileOutputExcel_1);
							int currentWith_14_tFileOutputExcel_1 = cell_14_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[14]=fitWidth_tFileOutputExcel_1[14]>currentWith_14_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[14]:currentWith_14_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Votre_Age != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 15;
					

					
						jxl.write.WritableCell cell_15_tFileOutputExcel_1 = new jxl.write.Number(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Votre_Age
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_15_tFileOutputExcel_1);
							int currentWith_15_tFileOutputExcel_1 = String.valueOf(((jxl.write.Number)cell_15_tFileOutputExcel_1).getValue()).trim().length();
							currentWith_15_tFileOutputExcel_1=currentWith_15_tFileOutputExcel_1>10?10:currentWith_15_tFileOutputExcel_1;
							fitWidth_tFileOutputExcel_1[15]=fitWidth_tFileOutputExcel_1[15]>currentWith_15_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[15]:currentWith_15_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Categorie_d_age != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 16;
					

					
						jxl.write.WritableCell cell_16_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Categorie_d_age
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_16_tFileOutputExcel_1);
							int currentWith_16_tFileOutputExcel_1 = cell_16_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[16]=fitWidth_tFileOutputExcel_1[16]>currentWith_16_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[16]:currentWith_16_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Votre_niveau_d_etudes != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 17;
					

					
						jxl.write.WritableCell cell_17_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Votre_niveau_d_etudes
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_17_tFileOutputExcel_1);
							int currentWith_17_tFileOutputExcel_1 = cell_17_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[17]=fitWidth_tFileOutputExcel_1[17]>currentWith_17_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[17]:currentWith_17_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Votre_specialite != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 18;
					

					
						jxl.write.WritableCell cell_18_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Votre_specialite
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_18_tFileOutputExcel_1);
							int currentWith_18_tFileOutputExcel_1 = cell_18_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[18]=fitWidth_tFileOutputExcel_1[18]>currentWith_18_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[18]:currentWith_18_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Etablissement != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 19;
					

					
						jxl.write.WritableCell cell_19_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Etablissement
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_19_tFileOutputExcel_1);
							int currentWith_19_tFileOutputExcel_1 = cell_19_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[19]=fitWidth_tFileOutputExcel_1[19]>currentWith_19_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[19]:currentWith_19_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.Avez_Vous_deja_participer_au_programmes_ODC != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 20;
					

					
						jxl.write.WritableCell cell_20_tFileOutputExcel_1 = new jxl.write.Boolean(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.Avez_Vous_deja_participer_au_programmes_ODC
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_20_tFileOutputExcel_1);
							int currentWith_20_tFileOutputExcel_1 = cell_20_tFileOutputExcel_1.getContents().trim().length();
							currentWith_20_tFileOutputExcel_1=5;
							fitWidth_tFileOutputExcel_1[20]=fitWidth_tFileOutputExcel_1[20]>currentWith_20_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[20]:currentWith_20_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.linkedIn != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 21;
					

					
						jxl.write.WritableCell cell_21_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.linkedIn
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_21_tFileOutputExcel_1);
							int currentWith_21_tFileOutputExcel_1 = cell_21_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[21]=fitWidth_tFileOutputExcel_1[21]>currentWith_21_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[21]:currentWith_21_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.userCV != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 22;
					

					
						jxl.write.WritableCell cell_22_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.userCV
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_22_tFileOutputExcel_1);
							int currentWith_22_tFileOutputExcel_1 = cell_22_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[22]=fitWidth_tFileOutputExcel_1[22]>currentWith_22_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[22]:currentWith_22_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.dateDebut != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 23;
					

					
						jxl.write.WritableCell cell_23_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.dateDebut
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_23_tFileOutputExcel_1);
							int currentWith_23_tFileOutputExcel_1 = cell_23_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[23]=fitWidth_tFileOutputExcel_1[23]>currentWith_23_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[23]:currentWith_23_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out3.dateFin != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 24;
					

					
						jxl.write.WritableCell cell_24_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out3.dateFin
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_24_tFileOutputExcel_1);
							int currentWith_24_tFileOutputExcel_1 = cell_24_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[24]=fitWidth_tFileOutputExcel_1[24]>currentWith_24_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[24]:currentWith_24_tFileOutputExcel_1+2;
	    				} 
					
    			nb_line_tFileOutputExcel_1++;
				
 
     row2 = out3;


	tos_count_tFileOutputExcel_1++;

/**
 * [tFileOutputExcel_1 main ] stop
 */
	
	/**
	 * [tFileOutputExcel_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tFileOutputExcel_1";

	

 



/**
 * [tFileOutputExcel_1 process_data_begin ] stop
 */

	
	/**
	 * [tLogRow_1 main ] start
	 */

	

	
	
	currentComponent="tLogRow_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
						);
					}
					
///////////////////////		
						



				strBuffer_tLogRow_1 = new StringBuilder();




   				
	    		if(row2.Annee != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Annee)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.EDC_FabLab != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.EDC_FabLab)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.FORMATION != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.FORMATION)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.DATE != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.DATE)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.ETAT != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.ETAT)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Profil_recommande_pour_Job_Dating != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Profil_recommande_pour_Job_Dating)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.EMAIL != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.EMAIL)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.First_Name != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.First_Name)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Last_Name != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Last_Name)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.gender != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.gender)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.profession != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.profession)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Votre_numero_de_telephone != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Votre_numero_de_telephone)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.birthDay != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
								FormatterUtils.format_Date(row2.birthDay, "dd/MM/yyyy")				
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.country != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.country)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Nom_Prenom != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Nom_Prenom)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Votre_Age != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Votre_Age)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Categorie_d_age != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Categorie_d_age)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Votre_niveau_d_etudes != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Votre_niveau_d_etudes)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Votre_specialite != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Votre_specialite)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Etablissement != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Etablissement)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Avez_Vous_deja_participer_au_programmes_ODC != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Avez_Vous_deja_participer_au_programmes_ODC)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.linkedIn != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.linkedIn)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.userCV != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.userCV)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.dateDebut != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.dateDebut)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.dateFin != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.dateFin)							
				);


							
	    		} //  			
 

                    if (globalMap.get("tLogRow_CONSOLE")!=null)
                    {
                    	consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
                    }
                    else
                    {
                    	consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
                    	globalMap.put("tLogRow_CONSOLE",consoleOut_tLogRow_1);
                    }
                    consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
                    consoleOut_tLogRow_1.flush();
                    nb_line_tLogRow_1++;
//////

//////                    
                    
///////////////////////    			

 


	tos_count_tLogRow_1++;

/**
 * [tLogRow_1 main ] stop
 */
	
	/**
	 * [tLogRow_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tLogRow_1";

	

 



/**
 * [tLogRow_1 process_data_begin ] stop
 */
	
	/**
	 * [tLogRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tLogRow_1";

	

 



/**
 * [tLogRow_1 process_data_end ] stop
 */



	
	/**
	 * [tFileOutputExcel_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tFileOutputExcel_1";

	

 



/**
 * [tFileOutputExcel_1 process_data_end ] stop
 */

} // End of branch "out3"




	
	/**
	 * [tMap_3 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

 



/**
 * [tMap_3 process_data_end ] stop
 */

} // End of branch "out1"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
 */

} // End of branch "out2"




	
	/**
	 * [tMap_2 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 process_data_end ] stop
 */



	
	/**
	 * [tJavaRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tJavaRow_1";

	

 



/**
 * [tJavaRow_1 process_data_end ] stop
 */


	
		} // C_01
	
	
	/**
	 * [tRunJob_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	

 



/**
 * [tRunJob_1 process_data_end ] stop
 */
	
	/**
	 * [tRunJob_1 end ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	

 

ok_Hash.put("tRunJob_1", true);
end_Hash.put("tRunJob_1", System.currentTimeMillis());




/**
 * [tRunJob_1 end ] stop
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
	 * [tMap_2 end ] start
	 */

	

	
	
	currentComponent="tMap_2";

	


// ###############################
// # Lookup hashes releasing
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row3");
			  	}
			  	
 

ok_Hash.put("tMap_2", true);
end_Hash.put("tMap_2", System.currentTimeMillis());




/**
 * [tMap_2 end ] stop
 */

	
	/**
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out2");
			  	}
			  	
 

ok_Hash.put("tMap_1", true);
end_Hash.put("tMap_1", System.currentTimeMillis());




/**
 * [tMap_1 end ] stop
 */

	
	/**
	 * [tMap_3 end ] start
	 */

	

	
	
	currentComponent="tMap_3";

	


// ###############################
// # Lookup hashes releasing
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out1");
			  	}
			  	
 

ok_Hash.put("tMap_3", true);
end_Hash.put("tMap_3", System.currentTimeMillis());




/**
 * [tMap_3 end ] stop
 */

	
	/**
	 * [tFileOutputExcel_1 end ] start
	 */

	

	
	
	currentComponent="tFileOutputExcel_1";

	

		writeableWorkbook_tFileOutputExcel_1.write();
		writeableWorkbook_tFileOutputExcel_1.close();
		if(headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0){
			nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 -1;
		}
		globalMap.put("tFileOutputExcel_1_NB_LINE",nb_line_tFileOutputExcel_1);
		
		

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out3");
			  	}
			  	
 

ok_Hash.put("tFileOutputExcel_1", true);
end_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());




/**
 * [tFileOutputExcel_1 end ] stop
 */

	
	/**
	 * [tLogRow_1 end ] start
	 */

	

	
	
	currentComponent="tLogRow_1";

	


//////
//////
globalMap.put("tLogRow_1_NB_LINE",nb_line_tLogRow_1);

///////////////////////    			

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row2");
			  	}
			  	
 

ok_Hash.put("tLogRow_1", true);
end_Hash.put("tLogRow_1", System.currentTimeMillis());




/**
 * [tLogRow_1 end ] stop
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
	 * [tRunJob_1 finally ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	

 



/**
 * [tRunJob_1 finally ] stop
 */

	
	/**
	 * [tJavaRow_1 finally ] start
	 */

	

	
	
	currentComponent="tJavaRow_1";

	

 



/**
 * [tJavaRow_1 finally ] stop
 */

	
	/**
	 * [tMap_2 finally ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 finally ] stop
 */

	
	/**
	 * [tMap_1 finally ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 finally ] stop
 */

	
	/**
	 * [tMap_3 finally ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

 



/**
 * [tMap_3 finally ] stop
 */

	
	/**
	 * [tFileOutputExcel_1 finally ] start
	 */

	

	
	
	currentComponent="tFileOutputExcel_1";

	

 



/**
 * [tFileOutputExcel_1 finally ] stop
 */

	
	/**
	 * [tLogRow_1 finally ] start
	 */

	

	
	
	currentComponent="tLogRow_1";

	

 



/**
 * [tLogRow_1 finally ] stop
 */


















				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRunJob_1_SUBPROCESS_STATE", 1);
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
        final CorrigeAge CorrigeAgeClass = new CorrigeAge();

        int exitCode = CorrigeAgeClass.runJobInTOS(args);

        System.exit(exitCode);
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
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
            java.io.InputStream inContext = CorrigeAge.class.getClassLoader().getResourceAsStream("local_project/corrigeage_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = CorrigeAge.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
errorCode = null;tRunJob_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tRunJob_1) {
globalMap.put("tRunJob_1_SUBPROCESS_STATE", -1);

e_tRunJob_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : CorrigeAge");
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
 *     237781 characters generated by Talend Open Studio for Data Integration 
 *     on the 17 avril 2025 à 14:40:29 WEST
 ************************************************************************************************/