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


package local_project.jobetl_0_1;

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
 * Job: JobETL Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class JobETL implements TalendJob {

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
	private final String jobName = "JobETL";
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
				JobETL.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(JobETL.this, new Object[] { e , currentComponent, globalMap});
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
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_JobETL = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_JobETL = new byte[0];

	
			    public String Horodateur;

				public String getHorodateur () {
					return this.Horodateur;
				}
				
			    public String Confirmation_Appel;

				public String getConfirmation_Appel () {
					return this.Confirmation_Appel;
				}
				
			    public String Confirmation_E_mail;

				public String getConfirmation_E_mail () {
					return this.Confirmation_E_mail;
				}
				
			    public String Email;

				public String getEmail () {
					return this.Email;
				}
				
			    public String Prenom;

				public String getPrenom () {
					return this.Prenom;
				}
				
			    public String Nom;

				public String getNom () {
					return this.Nom;
				}
				
			    public String Genre;

				public String getGenre () {
					return this.Genre;
				}
				
			    public java.util.Date Date_de_naissance;

				public java.util.Date getDate_de_naissance () {
					return this.Date_de_naissance;
				}
				
			    public String Pays;

				public String getPays () {
					return this.Pays;
				}
				
			    public String Situation_Profetionnelle;

				public String getSituation_Profetionnelle () {
					return this.Situation_Profetionnelle;
				}
				
			    public String Profession;

				public String getProfession () {
					return this.Profession;
				}
				
			    public Integer Votre_age;

				public Integer getVotre_age () {
					return this.Votre_age;
				}
				
			    public String Telelphone;

				public String getTelelphone () {
					return this.Telelphone;
				}
				
			    public String Niveau_d_etudes;

				public String getNiveau_d_etudes () {
					return this.Niveau_d_etudes;
				}
				
			    public String Avez_vous_une_experience_avec_la_gestion_de_projet;

				public String getAvez_vous_une_experience_avec_la_gestion_de_projet () {
					return this.Avez_vous_une_experience_avec_la_gestion_de_projet;
				}
				
			    public String Votre_specialite;

				public String getVotre_specialite () {
					return this.Votre_specialite;
				}
				
			    public String Etablissement;

				public String getEtablissement () {
					return this.Etablissement;
				}
				
			    public String Avez_vous_deja_participe_au_programmes_ODC;

				public String getAvez_vous_deja_participe_au_programmes_ODC () {
					return this.Avez_vous_deja_participe_au_programmes_ODC;
				}
				
			    public String categorieAge;

				public String getCategorieAge () {
					return this.categorieAge;
				}
				
			    public String isStaurate;

				public String getIsStaurate () {
					return this.isStaurate;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
					this.Date_de_naissance = readDate(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
						this.Votre_age = readInteger(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
					this.categorieAge = readString(dis);
					
					this.isStaurate = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
					this.Date_de_naissance = readDate(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
						this.Votre_age = readInteger(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
					this.categorieAge = readString(dis);
					
					this.isStaurate = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// java.util.Date
				
						writeDate(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// Integer
				
						writeInteger(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.categorieAge,dos);
					
					// String
				
						writeString(this.isStaurate,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// java.util.Date
				
						writeDate(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// Integer
				
						writeInteger(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.categorieAge,dos);
					
					// String
				
						writeString(this.isStaurate,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Horodateur="+Horodateur);
		sb.append(",Confirmation_Appel="+Confirmation_Appel);
		sb.append(",Confirmation_E_mail="+Confirmation_E_mail);
		sb.append(",Email="+Email);
		sb.append(",Prenom="+Prenom);
		sb.append(",Nom="+Nom);
		sb.append(",Genre="+Genre);
		sb.append(",Date_de_naissance="+String.valueOf(Date_de_naissance));
		sb.append(",Pays="+Pays);
		sb.append(",Situation_Profetionnelle="+Situation_Profetionnelle);
		sb.append(",Profession="+Profession);
		sb.append(",Votre_age="+String.valueOf(Votre_age));
		sb.append(",Telelphone="+Telelphone);
		sb.append(",Niveau_d_etudes="+Niveau_d_etudes);
		sb.append(",Avez_vous_une_experience_avec_la_gestion_de_projet="+Avez_vous_une_experience_avec_la_gestion_de_projet);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_vous_deja_participe_au_programmes_ODC="+Avez_vous_deja_participe_au_programmes_ODC);
		sb.append(",categorieAge="+categorieAge);
		sb.append(",isStaurate="+isStaurate);
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

public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_JobETL = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_JobETL = new byte[0];

	
			    public String Horodateur;

				public String getHorodateur () {
					return this.Horodateur;
				}
				
			    public String Confirmation_Appel;

				public String getConfirmation_Appel () {
					return this.Confirmation_Appel;
				}
				
			    public String Confirmation_E_mail;

				public String getConfirmation_E_mail () {
					return this.Confirmation_E_mail;
				}
				
			    public String Email;

				public String getEmail () {
					return this.Email;
				}
				
			    public String Prenom;

				public String getPrenom () {
					return this.Prenom;
				}
				
			    public String Nom;

				public String getNom () {
					return this.Nom;
				}
				
			    public String Genre;

				public String getGenre () {
					return this.Genre;
				}
				
			    public java.util.Date Date_de_naissance;

				public java.util.Date getDate_de_naissance () {
					return this.Date_de_naissance;
				}
				
			    public String Pays;

				public String getPays () {
					return this.Pays;
				}
				
			    public String Situation_Profetionnelle;

				public String getSituation_Profetionnelle () {
					return this.Situation_Profetionnelle;
				}
				
			    public String Profession;

				public String getProfession () {
					return this.Profession;
				}
				
			    public Integer Votre_age;

				public Integer getVotre_age () {
					return this.Votre_age;
				}
				
			    public String Telelphone;

				public String getTelelphone () {
					return this.Telelphone;
				}
				
			    public String Niveau_d_etudes;

				public String getNiveau_d_etudes () {
					return this.Niveau_d_etudes;
				}
				
			    public String Avez_vous_une_experience_avec_la_gestion_de_projet;

				public String getAvez_vous_une_experience_avec_la_gestion_de_projet () {
					return this.Avez_vous_une_experience_avec_la_gestion_de_projet;
				}
				
			    public String Votre_specialite;

				public String getVotre_specialite () {
					return this.Votre_specialite;
				}
				
			    public String Etablissement;

				public String getEtablissement () {
					return this.Etablissement;
				}
				
			    public String Avez_vous_deja_participe_au_programmes_ODC;

				public String getAvez_vous_deja_participe_au_programmes_ODC () {
					return this.Avez_vous_deja_participe_au_programmes_ODC;
				}
				
			    public String categorieAge;

				public String getCategorieAge () {
					return this.categorieAge;
				}
				
			    public String isStaurate;

				public String getIsStaurate () {
					return this.isStaurate;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
					this.Date_de_naissance = readDate(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
						this.Votre_age = readInteger(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
					this.categorieAge = readString(dis);
					
					this.isStaurate = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
					this.Date_de_naissance = readDate(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
						this.Votre_age = readInteger(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
					this.categorieAge = readString(dis);
					
					this.isStaurate = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// java.util.Date
				
						writeDate(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// Integer
				
						writeInteger(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.categorieAge,dos);
					
					// String
				
						writeString(this.isStaurate,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// java.util.Date
				
						writeDate(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// Integer
				
						writeInteger(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
					// String
				
						writeString(this.categorieAge,dos);
					
					// String
				
						writeString(this.isStaurate,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Horodateur="+Horodateur);
		sb.append(",Confirmation_Appel="+Confirmation_Appel);
		sb.append(",Confirmation_E_mail="+Confirmation_E_mail);
		sb.append(",Email="+Email);
		sb.append(",Prenom="+Prenom);
		sb.append(",Nom="+Nom);
		sb.append(",Genre="+Genre);
		sb.append(",Date_de_naissance="+String.valueOf(Date_de_naissance));
		sb.append(",Pays="+Pays);
		sb.append(",Situation_Profetionnelle="+Situation_Profetionnelle);
		sb.append(",Profession="+Profession);
		sb.append(",Votre_age="+String.valueOf(Votre_age));
		sb.append(",Telelphone="+Telelphone);
		sb.append(",Niveau_d_etudes="+Niveau_d_etudes);
		sb.append(",Avez_vous_une_experience_avec_la_gestion_de_projet="+Avez_vous_une_experience_avec_la_gestion_de_projet);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_vous_deja_participe_au_programmes_ODC="+Avez_vous_deja_participe_au_programmes_ODC);
		sb.append(",categorieAge="+categorieAge);
		sb.append(",isStaurate="+isStaurate);
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
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_JobETL = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_JobETL = new byte[0];

	
			    public String Horodateur;

				public String getHorodateur () {
					return this.Horodateur;
				}
				
			    public String Confirmation_Appel;

				public String getConfirmation_Appel () {
					return this.Confirmation_Appel;
				}
				
			    public String Confirmation_E_mail;

				public String getConfirmation_E_mail () {
					return this.Confirmation_E_mail;
				}
				
			    public String Email;

				public String getEmail () {
					return this.Email;
				}
				
			    public String Prenom;

				public String getPrenom () {
					return this.Prenom;
				}
				
			    public String Nom;

				public String getNom () {
					return this.Nom;
				}
				
			    public String Genre;

				public String getGenre () {
					return this.Genre;
				}
				
			    public java.util.Date Date_de_naissance;

				public java.util.Date getDate_de_naissance () {
					return this.Date_de_naissance;
				}
				
			    public String Pays;

				public String getPays () {
					return this.Pays;
				}
				
			    public String Situation_Profetionnelle;

				public String getSituation_Profetionnelle () {
					return this.Situation_Profetionnelle;
				}
				
			    public String Profession;

				public String getProfession () {
					return this.Profession;
				}
				
			    public Integer Votre_age;

				public Integer getVotre_age () {
					return this.Votre_age;
				}
				
			    public String Telelphone;

				public String getTelelphone () {
					return this.Telelphone;
				}
				
			    public String Niveau_d_etudes;

				public String getNiveau_d_etudes () {
					return this.Niveau_d_etudes;
				}
				
			    public String Avez_vous_une_experience_avec_la_gestion_de_projet;

				public String getAvez_vous_une_experience_avec_la_gestion_de_projet () {
					return this.Avez_vous_une_experience_avec_la_gestion_de_projet;
				}
				
			    public String Votre_specialite;

				public String getVotre_specialite () {
					return this.Votre_specialite;
				}
				
			    public String Etablissement;

				public String getEtablissement () {
					return this.Etablissement;
				}
				
			    public String Avez_vous_deja_participe_au_programmes_ODC;

				public String getAvez_vous_deja_participe_au_programmes_ODC () {
					return this.Avez_vous_deja_participe_au_programmes_ODC;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
					this.Date_de_naissance = readDate(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
						this.Votre_age = readInteger(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
					this.Date_de_naissance = readDate(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
						this.Votre_age = readInteger(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// java.util.Date
				
						writeDate(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// Integer
				
						writeInteger(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// java.util.Date
				
						writeDate(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// Integer
				
						writeInteger(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Horodateur="+Horodateur);
		sb.append(",Confirmation_Appel="+Confirmation_Appel);
		sb.append(",Confirmation_E_mail="+Confirmation_E_mail);
		sb.append(",Email="+Email);
		sb.append(",Prenom="+Prenom);
		sb.append(",Nom="+Nom);
		sb.append(",Genre="+Genre);
		sb.append(",Date_de_naissance="+String.valueOf(Date_de_naissance));
		sb.append(",Pays="+Pays);
		sb.append(",Situation_Profetionnelle="+Situation_Profetionnelle);
		sb.append(",Profession="+Profession);
		sb.append(",Votre_age="+String.valueOf(Votre_age));
		sb.append(",Telelphone="+Telelphone);
		sb.append(",Niveau_d_etudes="+Niveau_d_etudes);
		sb.append(",Avez_vous_une_experience_avec_la_gestion_de_projet="+Avez_vous_une_experience_avec_la_gestion_de_projet);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_vous_deja_participe_au_programmes_ODC="+Avez_vous_deja_participe_au_programmes_ODC);
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
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_JobETL = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_JobETL = new byte[0];

	
			    public String Horodateur;

				public String getHorodateur () {
					return this.Horodateur;
				}
				
			    public String Confirmation_Appel;

				public String getConfirmation_Appel () {
					return this.Confirmation_Appel;
				}
				
			    public String Confirmation_E_mail;

				public String getConfirmation_E_mail () {
					return this.Confirmation_E_mail;
				}
				
			    public String Email;

				public String getEmail () {
					return this.Email;
				}
				
			    public String Prenom;

				public String getPrenom () {
					return this.Prenom;
				}
				
			    public String Nom;

				public String getNom () {
					return this.Nom;
				}
				
			    public String Genre;

				public String getGenre () {
					return this.Genre;
				}
				
			    public Integer Date_de_naissance;

				public Integer getDate_de_naissance () {
					return this.Date_de_naissance;
				}
				
			    public String Pays;

				public String getPays () {
					return this.Pays;
				}
				
			    public String Situation_Profetionnelle;

				public String getSituation_Profetionnelle () {
					return this.Situation_Profetionnelle;
				}
				
			    public String Profession;

				public String getProfession () {
					return this.Profession;
				}
				
			    public String Votre_age;

				public String getVotre_age () {
					return this.Votre_age;
				}
				
			    public String Telelphone;

				public String getTelelphone () {
					return this.Telelphone;
				}
				
			    public String Niveau_d_etudes;

				public String getNiveau_d_etudes () {
					return this.Niveau_d_etudes;
				}
				
			    public String Avez_vous_une_experience_avec_la_gestion_de_projet;

				public String getAvez_vous_une_experience_avec_la_gestion_de_projet () {
					return this.Avez_vous_une_experience_avec_la_gestion_de_projet;
				}
				
			    public String Votre_specialite;

				public String getVotre_specialite () {
					return this.Votre_specialite;
				}
				
			    public String Etablissement;

				public String getEtablissement () {
					return this.Etablissement;
				}
				
			    public String Avez_vous_deja_participe_au_programmes_ODC;

				public String getAvez_vous_deja_participe_au_programmes_ODC () {
					return this.Avez_vous_deja_participe_au_programmes_ODC;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
						this.Date_de_naissance = readInteger(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
					this.Votre_age = readString(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
						this.Date_de_naissance = readInteger(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
					this.Votre_age = readString(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// Integer
				
						writeInteger(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// String
				
						writeString(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// Integer
				
						writeInteger(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// String
				
						writeString(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Horodateur="+Horodateur);
		sb.append(",Confirmation_Appel="+Confirmation_Appel);
		sb.append(",Confirmation_E_mail="+Confirmation_E_mail);
		sb.append(",Email="+Email);
		sb.append(",Prenom="+Prenom);
		sb.append(",Nom="+Nom);
		sb.append(",Genre="+Genre);
		sb.append(",Date_de_naissance="+String.valueOf(Date_de_naissance));
		sb.append(",Pays="+Pays);
		sb.append(",Situation_Profetionnelle="+Situation_Profetionnelle);
		sb.append(",Profession="+Profession);
		sb.append(",Votre_age="+Votre_age);
		sb.append(",Telelphone="+Telelphone);
		sb.append(",Niveau_d_etudes="+Niveau_d_etudes);
		sb.append(",Avez_vous_une_experience_avec_la_gestion_de_projet="+Avez_vous_une_experience_avec_la_gestion_de_projet);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_vous_deja_participe_au_programmes_ODC="+Avez_vous_deja_participe_au_programmes_ODC);
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
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_JobETL = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_JobETL = new byte[0];

	
			    public String Horodateur;

				public String getHorodateur () {
					return this.Horodateur;
				}
				
			    public String Confirmation_Appel;

				public String getConfirmation_Appel () {
					return this.Confirmation_Appel;
				}
				
			    public String Confirmation_E_mail;

				public String getConfirmation_E_mail () {
					return this.Confirmation_E_mail;
				}
				
			    public String Email;

				public String getEmail () {
					return this.Email;
				}
				
			    public String Prenom;

				public String getPrenom () {
					return this.Prenom;
				}
				
			    public String Nom;

				public String getNom () {
					return this.Nom;
				}
				
			    public String Genre;

				public String getGenre () {
					return this.Genre;
				}
				
			    public Integer Date_de_naissance;

				public Integer getDate_de_naissance () {
					return this.Date_de_naissance;
				}
				
			    public String Pays;

				public String getPays () {
					return this.Pays;
				}
				
			    public String Situation_Profetionnelle;

				public String getSituation_Profetionnelle () {
					return this.Situation_Profetionnelle;
				}
				
			    public String Profession;

				public String getProfession () {
					return this.Profession;
				}
				
			    public String Votre_age;

				public String getVotre_age () {
					return this.Votre_age;
				}
				
			    public String Telelphone;

				public String getTelelphone () {
					return this.Telelphone;
				}
				
			    public String Niveau_d_etudes;

				public String getNiveau_d_etudes () {
					return this.Niveau_d_etudes;
				}
				
			    public String Avez_vous_une_experience_avec_la_gestion_de_projet;

				public String getAvez_vous_une_experience_avec_la_gestion_de_projet () {
					return this.Avez_vous_une_experience_avec_la_gestion_de_projet;
				}
				
			    public String Votre_specialite;

				public String getVotre_specialite () {
					return this.Votre_specialite;
				}
				
			    public String Etablissement;

				public String getEtablissement () {
					return this.Etablissement;
				}
				
			    public String Avez_vous_deja_participe_au_programmes_ODC;

				public String getAvez_vous_deja_participe_au_programmes_ODC () {
					return this.Avez_vous_deja_participe_au_programmes_ODC;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_JobETL.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobETL.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobETL = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_JobETL, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobETL, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
						this.Date_de_naissance = readInteger(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
					this.Votre_age = readString(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobETL) {

        	try {

        		int length = 0;
		
					this.Horodateur = readString(dis);
					
					this.Confirmation_Appel = readString(dis);
					
					this.Confirmation_E_mail = readString(dis);
					
					this.Email = readString(dis);
					
					this.Prenom = readString(dis);
					
					this.Nom = readString(dis);
					
					this.Genre = readString(dis);
					
						this.Date_de_naissance = readInteger(dis);
					
					this.Pays = readString(dis);
					
					this.Situation_Profetionnelle = readString(dis);
					
					this.Profession = readString(dis);
					
					this.Votre_age = readString(dis);
					
					this.Telelphone = readString(dis);
					
					this.Niveau_d_etudes = readString(dis);
					
					this.Avez_vous_une_experience_avec_la_gestion_de_projet = readString(dis);
					
					this.Votre_specialite = readString(dis);
					
					this.Etablissement = readString(dis);
					
					this.Avez_vous_deja_participe_au_programmes_ODC = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// Integer
				
						writeInteger(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// String
				
						writeString(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Horodateur,dos);
					
					// String
				
						writeString(this.Confirmation_Appel,dos);
					
					// String
				
						writeString(this.Confirmation_E_mail,dos);
					
					// String
				
						writeString(this.Email,dos);
					
					// String
				
						writeString(this.Prenom,dos);
					
					// String
				
						writeString(this.Nom,dos);
					
					// String
				
						writeString(this.Genre,dos);
					
					// Integer
				
						writeInteger(this.Date_de_naissance,dos);
					
					// String
				
						writeString(this.Pays,dos);
					
					// String
				
						writeString(this.Situation_Profetionnelle,dos);
					
					// String
				
						writeString(this.Profession,dos);
					
					// String
				
						writeString(this.Votre_age,dos);
					
					// String
				
						writeString(this.Telelphone,dos);
					
					// String
				
						writeString(this.Niveau_d_etudes,dos);
					
					// String
				
						writeString(this.Avez_vous_une_experience_avec_la_gestion_de_projet,dos);
					
					// String
				
						writeString(this.Votre_specialite,dos);
					
					// String
				
						writeString(this.Etablissement,dos);
					
					// String
				
						writeString(this.Avez_vous_deja_participe_au_programmes_ODC,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Horodateur="+Horodateur);
		sb.append(",Confirmation_Appel="+Confirmation_Appel);
		sb.append(",Confirmation_E_mail="+Confirmation_E_mail);
		sb.append(",Email="+Email);
		sb.append(",Prenom="+Prenom);
		sb.append(",Nom="+Nom);
		sb.append(",Genre="+Genre);
		sb.append(",Date_de_naissance="+String.valueOf(Date_de_naissance));
		sb.append(",Pays="+Pays);
		sb.append(",Situation_Profetionnelle="+Situation_Profetionnelle);
		sb.append(",Profession="+Profession);
		sb.append(",Votre_age="+Votre_age);
		sb.append(",Telelphone="+Telelphone);
		sb.append(",Niveau_d_etudes="+Niveau_d_etudes);
		sb.append(",Avez_vous_une_experience_avec_la_gestion_de_projet="+Avez_vous_une_experience_avec_la_gestion_de_projet);
		sb.append(",Votre_specialite="+Votre_specialite);
		sb.append(",Etablissement="+Etablissement);
		sb.append(",Avez_vous_deja_participe_au_programmes_ODC="+Avez_vous_deja_participe_au_programmes_ODC);
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
out1Struct row2 = out1;








	
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
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out1");
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
		workbookSettings_tFileOutputExcel_1.setEncoding("UTF-8");

		writeableWorkbook_tFileOutputExcel_1 = new jxl.write.biff.WritableWorkbookImpl(
			new java.io.BufferedOutputStream(new java.io.FileOutputStream(fileName_tFileOutputExcel_1)),
			true,
			workbookSettings_tFileOutputExcel_1);

		// Supprimer la feuille par défaut si elle existe
		if (writeableWorkbook_tFileOutputExcel_1.getSheet("outdataset") != null) {
			writeableWorkbook_tFileOutputExcel_1.removeSheet(0);
		}

		// Créer la feuille Beneficiaires
		writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.createSheet("Beneficiaires", 0);

// Assurez-vous d'utiliser writableSheet_tFileOutputExcel_1 pour toutes les opérations d'écriture suivantes

        //modif start
        int startRowNum_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1.getRows();
		//modif end

		int[] fitWidth_tFileOutputExcel_1 = new int[20];
		for(int i_tFileOutputExcel_1=0;i_tFileOutputExcel_1<20;i_tFileOutputExcel_1++){
		    int fitCellViewSize_tFileOutputExcel_1=writableSheet_tFileOutputExcel_1.getColumnView(i_tFileOutputExcel_1).getSize();
			fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1]=fitCellViewSize_tFileOutputExcel_1/256;
			if(fitCellViewSize_tFileOutputExcel_1%256!=0){
				fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1]+=1;
			}
		}

						final jxl.write.WritableCellFormat cell_format_Date_de_naissance_tFileOutputExcel_1=new jxl.write.WritableCellFormat(new jxl.write.DateFormat("dd/MM/yyyy"));


		if (startRowNum_tFileOutputExcel_1 == 0){
	//modif end
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(0, nb_line_tFileOutputExcel_1, "Horodateur"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[0]=fitWidth_tFileOutputExcel_1[0]>10?fitWidth_tFileOutputExcel_1[0]:10;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(1, nb_line_tFileOutputExcel_1, "Confirmation_Appel"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[1]=fitWidth_tFileOutputExcel_1[1]>18?fitWidth_tFileOutputExcel_1[1]:18;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(2, nb_line_tFileOutputExcel_1, "Confirmation_E_mail"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[2]=fitWidth_tFileOutputExcel_1[2]>19?fitWidth_tFileOutputExcel_1[2]:19;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(3, nb_line_tFileOutputExcel_1, "Email"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[3]=fitWidth_tFileOutputExcel_1[3]>5?fitWidth_tFileOutputExcel_1[3]:5;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(4, nb_line_tFileOutputExcel_1, "Prenom"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[4]=fitWidth_tFileOutputExcel_1[4]>6?fitWidth_tFileOutputExcel_1[4]:6;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(5, nb_line_tFileOutputExcel_1, "Nom"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[5]=fitWidth_tFileOutputExcel_1[5]>3?fitWidth_tFileOutputExcel_1[5]:3;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(6, nb_line_tFileOutputExcel_1, "Genre"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[6]=fitWidth_tFileOutputExcel_1[6]>5?fitWidth_tFileOutputExcel_1[6]:5;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(7, nb_line_tFileOutputExcel_1, "Date_de_naissance"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[7]=fitWidth_tFileOutputExcel_1[7]>17?fitWidth_tFileOutputExcel_1[7]:17;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(8, nb_line_tFileOutputExcel_1, "Pays"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[8]=fitWidth_tFileOutputExcel_1[8]>4?fitWidth_tFileOutputExcel_1[8]:4;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(9, nb_line_tFileOutputExcel_1, "Situation_Profetionnelle"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[9]=fitWidth_tFileOutputExcel_1[9]>24?fitWidth_tFileOutputExcel_1[9]:24;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(10, nb_line_tFileOutputExcel_1, "Profession"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[10]=fitWidth_tFileOutputExcel_1[10]>10?fitWidth_tFileOutputExcel_1[10]:10;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(11, nb_line_tFileOutputExcel_1, "Votre_age"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[11]=fitWidth_tFileOutputExcel_1[11]>9?fitWidth_tFileOutputExcel_1[11]:9;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(12, nb_line_tFileOutputExcel_1, "Telelphone"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[12]=fitWidth_tFileOutputExcel_1[12]>10?fitWidth_tFileOutputExcel_1[12]:10;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(13, nb_line_tFileOutputExcel_1, "Niveau_d_etudes"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[13]=fitWidth_tFileOutputExcel_1[13]>15?fitWidth_tFileOutputExcel_1[13]:15;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(14, nb_line_tFileOutputExcel_1, "Avez_vous_une_experience_avec_la_gestion_de_projet"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[14]=fitWidth_tFileOutputExcel_1[14]>50?fitWidth_tFileOutputExcel_1[14]:50;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(15, nb_line_tFileOutputExcel_1, "Votre_specialite"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[15]=fitWidth_tFileOutputExcel_1[15]>16?fitWidth_tFileOutputExcel_1[15]:16;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(16, nb_line_tFileOutputExcel_1, "Etablissement"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[16]=fitWidth_tFileOutputExcel_1[16]>13?fitWidth_tFileOutputExcel_1[16]:13;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(17, nb_line_tFileOutputExcel_1, "Avez_vous_deja_participe_au_programmes_ODC"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[17]=fitWidth_tFileOutputExcel_1[17]>42?fitWidth_tFileOutputExcel_1[17]:42;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(18, nb_line_tFileOutputExcel_1, "categorieAge"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[18]=fitWidth_tFileOutputExcel_1[18]>12?fitWidth_tFileOutputExcel_1[18]:12;
		//modif start
			writableSheet_tFileOutputExcel_1.addCell(new jxl.write.Label(19, nb_line_tFileOutputExcel_1, "isStaurate"
			));
		//modif end
		fitWidth_tFileOutputExcel_1[19]=fitWidth_tFileOutputExcel_1[19]>10?fitWidth_tFileOutputExcel_1[19]:10;
		nb_line_tFileOutputExcel_1 ++;
		headerIsInserted_tFileOutputExcel_1 = true;
	}


 



/**
 * [tFileOutputExcel_1 begin ] stop
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
	String categorieAge;
	String isStaurate;
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
	String normalized;
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

	
	
		local_project.jobencodage_0_1.JobEncodage childJob_tRunJob_1 = new local_project.jobencodage_0_1.JobEncodage();
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
			           		
				           		row1.Horodateur = item_tRunJob_1[0];
			           		
		           		}else{
			           		row1.Horodateur = null;
		           		}
							
						if(1 < item_tRunJob_1.length){				
			           		
				           		row1.Confirmation_Appel = item_tRunJob_1[1];
			           		
		           		}else{
			           		row1.Confirmation_Appel = null;
		           		}
							
						if(2 < item_tRunJob_1.length){				
			           		
				           		row1.Confirmation_E_mail = item_tRunJob_1[2];
			           		
		           		}else{
			           		row1.Confirmation_E_mail = null;
		           		}
							
						if(3 < item_tRunJob_1.length){				
			           		
				           		row1.Email = item_tRunJob_1[3];
			           		
		           		}else{
			           		row1.Email = null;
		           		}
							
						if(4 < item_tRunJob_1.length){				
			           		
				           		row1.Prenom = item_tRunJob_1[4];
			           		
		           		}else{
			           		row1.Prenom = null;
		           		}
							
						if(5 < item_tRunJob_1.length){				
			           		
				           		row1.Nom = item_tRunJob_1[5];
			           		
		           		}else{
			           		row1.Nom = null;
		           		}
							
						if(6 < item_tRunJob_1.length){				
			           		
				           		row1.Genre = item_tRunJob_1[6];
			           		
		           		}else{
			           		row1.Genre = null;
		           		}
							
						if(7 < item_tRunJob_1.length){				
			           		
			           			row1.Date_de_naissance = ParserUtils.parseTo_Integer(item_tRunJob_1[7]);
			           		
		           		}else{
			           		row1.Date_de_naissance = null;
		           		}
							
						if(8 < item_tRunJob_1.length){				
			           		
				           		row1.Pays = item_tRunJob_1[8];
			           		
		           		}else{
			           		row1.Pays = null;
		           		}
							
						if(9 < item_tRunJob_1.length){				
			           		
				           		row1.Situation_Profetionnelle = item_tRunJob_1[9];
			           		
		           		}else{
			           		row1.Situation_Profetionnelle = null;
		           		}
							
						if(10 < item_tRunJob_1.length){				
			           		
				           		row1.Profession = item_tRunJob_1[10];
			           		
		           		}else{
			           		row1.Profession = null;
		           		}
							
						if(11 < item_tRunJob_1.length){				
			           		
				           		row1.Votre_age = item_tRunJob_1[11];
			           		
		           		}else{
			           		row1.Votre_age = null;
		           		}
							
						if(12 < item_tRunJob_1.length){				
			           		
				           		row1.Telelphone = item_tRunJob_1[12];
			           		
		           		}else{
			           		row1.Telelphone = null;
		           		}
							
						if(13 < item_tRunJob_1.length){				
			           		
				           		row1.Niveau_d_etudes = item_tRunJob_1[13];
			           		
		           		}else{
			           		row1.Niveau_d_etudes = null;
		           		}
							
						if(14 < item_tRunJob_1.length){				
			           		
				           		row1.Avez_vous_une_experience_avec_la_gestion_de_projet = item_tRunJob_1[14];
			           		
		           		}else{
			           		row1.Avez_vous_une_experience_avec_la_gestion_de_projet = null;
		           		}
							
						if(15 < item_tRunJob_1.length){				
			           		
				           		row1.Votre_specialite = item_tRunJob_1[15];
			           		
		           		}else{
			           		row1.Votre_specialite = null;
		           		}
							
						if(16 < item_tRunJob_1.length){				
			           		
				           		row1.Etablissement = item_tRunJob_1[16];
			           		
		           		}else{
			           		row1.Etablissement = null;
		           		}
							
						if(17 < item_tRunJob_1.length){				
			           		
				           		row1.Avez_vous_deja_participe_au_programmes_ODC = item_tRunJob_1[17];
			           		
		           		}else{
			           		row1.Avez_vous_deja_participe_au_programmes_ODC = null;
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
					

    // Assignation des valeurs d'entrée à la sortie
row3.Horodateur = row1.Horodateur;
row3.Confirmation_Appel = row1.Confirmation_Appel;
row3.Confirmation_E_mail = row1.Confirmation_E_mail;
row3.Email = row1.Email;
row3.Prenom = row1.Prenom;
row3.Nom = row1.Nom;
row3.Genre = row1.Genre;
row3.Date_de_naissance = row1.Date_de_naissance;
row3.Pays = row1.Pays;
row3.Situation_Profetionnelle = row1.Situation_Profetionnelle;
row3.Profession = row1.Profession;
row3.Votre_age = row1.Votre_age;
row3.Telelphone = row1.Telelphone;
row3.Niveau_d_etudes = row1.Niveau_d_etudes;
row3.Avez_vous_une_experience_avec_la_gestion_de_projet = row1.Avez_vous_une_experience_avec_la_gestion_de_projet;
row3.Votre_specialite = row1.Votre_specialite;
row3.Etablissement = row1.Etablissement;
row3.Avez_vous_deja_participe_au_programmes_ODC = row1.Avez_vous_deja_participe_au_programmes_ODC;

// Code pour corriger les domaines d'email
String email = row1.Email;
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
row3.Email = correctedEmail;

// Normalisation du numéro de téléphone
String phone = row1.Telelphone;
if (phone != null && !phone.trim().isEmpty()) {
    phone = phone.trim();  // Supprimer les espaces avant et après
    phone = phone.replaceAll("[^0-9]", "");  // Garder uniquement les chiffres
    // Garder les 9 derniers chiffres après +212
    if (phone.length() > 9) {
        phone = phone.substring(phone.length() - 9);  // Garder les 9 derniers chiffres
    }
    row3.Telelphone = "0" + phone;  // Ajouter 0 devant
} else {
    row3.Telelphone = "";
}

// Parcours de tous les champs de la ligne de sortie pour appliquer l'encodage
for (java.lang.reflect.Field field : row3.getClass().getDeclaredFields()) {
    field.setAccessible(true); // Permet d'accéder aux champs privés si nécessaire
    try {
        Object value = field.get(row3);
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
            field.set(row3, correctedValue);
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
        
Var__tMap_2__Struct Var = Var__tMap_2;
Var.normalized = row3.Niveau_d_etudes == null ? "" : StringHandling.EREPLACE(StringHandling.TRIM(row3.Niveau_d_etudes), " ", "").toLowerCase() ;// ###############################
        // ###############################
        // # Output tables

out2 = null;


// # Output table : 'out2'
out2_tmp.Horodateur = row3.Horodateur ;
out2_tmp.Confirmation_Appel = row3.Confirmation_Appel ;
out2_tmp.Confirmation_E_mail = row3.Confirmation_E_mail ;
out2_tmp.Email = row3.Email ;
out2_tmp.Prenom = row3.Prenom ;
out2_tmp.Nom = row3.Nom ;
out2_tmp.Genre = row3.Genre ;
out2_tmp.Date_de_naissance = (
    row3.Date_de_naissance != null && String.valueOf(row3.Date_de_naissance).matches("^\\d{5}$") ?
        TalendDate.addDate(TalendDate.parseDate("yyyy-MM-dd", "1899-12-30"), Integer.parseInt(String.valueOf(row3.Date_de_naissance)), "dd") :
 
    row3.Date_de_naissance != null && String.valueOf(row3.Date_de_naissance).matches("^\\d{4}/\\d{2}/\\d{2}$") ?
        TalendDate.parseDate("yyyy/MM/dd", String.valueOf(row3.Date_de_naissance)) :
 
    row3.Date_de_naissance != null && String.valueOf(row3.Date_de_naissance).matches("^\\d{2}/\\d{2}/\\d{4}$") ?
        TalendDate.parseDate("dd/MM/yyyy", String.valueOf(row3.Date_de_naissance)) :
 
    row3.Date_de_naissance != null && String.valueOf(row3.Date_de_naissance).matches("^\\d{2}-\\d{2}-\\d{4}$") ?
        TalendDate.parseDate("dd-MM-yyyy", String.valueOf(row3.Date_de_naissance)) :
 
    row3.Date_de_naissance != null && String.valueOf(row3.Date_de_naissance).matches("^\\d{4}-\\d{2}-\\d{2}$") ?
        TalendDate.parseDate("yyyy-MM-dd", String.valueOf(row3.Date_de_naissance)) :
 
    row3.Date_de_naissance != null && String.valueOf(row3.Date_de_naissance).matches("^\\d{2}_\\d{2}_\\d{4}$") ?
        TalendDate.parseDate("dd_MM_yyyy", String.valueOf(row3.Date_de_naissance)) :
 
    null
) ;
out2_tmp.Pays = row3.Pays ;
out2_tmp.Situation_Profetionnelle = (row3.Situation_Profetionnelle == null) ? "inconnu" :
  ((row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*sans emploi.*") || 
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*à la recherche .*") ||
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*a la recherche .*") ||
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*unemployed.*") ||
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*looking for a job.*")) ? "Sans Emploi" :
  ((row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*etudiant.*") || 
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*étudiant.*") || 
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*étudiant.e.*") || 
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*jeune diplômé.*") || 
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*jeune diplome.*") ||
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*ingénieur .*") ||
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*student.*") ||
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*young graduate.*")) ? "Etudiant" :
  ((row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*stagiaire.*") || 
    row3.Situation_Profetionnelle.toLowerCase().trim().matches(".*intern.*")) ? "Employe" : 
  "Autre"))) ;
out2_tmp.Profession = row3.Profession ;
out2_tmp.Votre_age = row3.Votre_age != null && row3.Votre_age instanceof String 
    ? Integer.parseInt(((String) row3.Votre_age).replaceAll("[^0-9]", "")) 
    : null ;
out2_tmp.Telelphone = row3.Telelphone ;
out2_tmp.Niveau_d_etudes = row3.Niveau_d_etudes == null ? "Sans Bac" :
(
    // Classe "Bac"
    (Var.normalized.contains("bac") &&
     !Var.normalized.contains("bac+2") && 
     !Var.normalized.contains("bac+3") && 
     !Var.normalized.contains("bac+4") && 
     !Var.normalized.contains("bac+5") && 
     !Var.normalized.contains("bac+6") && 
     !Var.normalized.contains("bac+7") && 
     !Var.normalized.contains("bac+8") && 
     !Var.normalized.contains("bac+9")) || 
    Var.normalized.contains("bac+1") ||
    Var.normalized.contains("1er") ||
    Var.normalized.contains("diplo") ||
    Var.normalized.contains("étudiant") ||
    Var.normalized.contains("etudiant") ||
    Var.normalized.contains("univer")
) ? "Bac" :
(
    // Sans Bac
    Var.normalized.contains("niveaubac") ||
    Var.normalized.contains("sansbac") ||
    Var.normalized.contains("inconnu") ||
    Var.normalized.contains("sansdiplom") ||
    Var.normalized.contains("sansdiplôme") ||
    Var.normalized.contains("lyc") ||
    Var.normalized.contains("secondaryschool")
) ? "Sans Bac" :
(
    // Bac+2
    Var.normalized.contains("bac+2") ||
    Var.normalized.contains("dut") ||
    Var.normalized.contains("deust") ||
    Var.normalized.contains("prépa") ||
    Var.normalized.contains("prepa") ||
    (Var.normalized.contains("2") && 
     (Var.normalized.contains("année") || 
      Var.normalized.contains("annee") || 
      Var.normalized.contains("ann"))) ||
    Var.normalized.contains("techn")
) ? "Bac+2" :
(
    // Bac+3/+4
    Var.normalized.contains("bac+3") ||
    Var.normalized.contains("bac+4") ||
    Var.normalized.contains("licence") ||
    ((Var.normalized.contains("3") || Var.normalized.contains("4") || 
      Var.normalized.contains("troisieme") || Var.normalized.contains("quatrieme")) &&
     (Var.normalized.contains("année") || Var.normalized.contains("annee") || Var.normalized.contains("ann")))
) ? "Bac+3" :
(
    // Bac+5 et plus
    Var.normalized.matches(".*(bac\\+[5-9]|master|ing[ée]nieur|doctorat|phd).*") ||
    (Var.normalized.contains("5") && 
     (Var.normalized.contains("année") || Var.normalized.contains("annee") || Var.normalized.contains("ann")))
) ? "Bac+5" :
"Sans Bac" ;
out2_tmp.Avez_vous_une_experience_avec_la_gestion_de_projet = row3.Avez_vous_une_experience_avec_la_gestion_de_projet ;
out2_tmp.Votre_specialite = row3.Votre_specialite ;
out2_tmp.Etablissement = row3.Etablissement ;
out2_tmp.Avez_vous_deja_participe_au_programmes_ODC = row3.Avez_vous_deja_participe_au_programmes_ODC ;
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
Var.categorieAge = (out2.Votre_age != null) ? 
    (out2.Votre_age < 15 ? "<15" : 
    (out2.Votre_age >= 15 && out2.Votre_age <= 24 ? "[15-24]" : 
    (out2.Votre_age >= 25 && out2.Votre_age <= 34 ? "[25-34]" : ">=35"))) 
    : "[15-24]" ;
Var.isStaurate = (row3.Avez_vous_deja_participe_au_programmes_ODC == null) ? "false" :
    (row3.Avez_vous_deja_participe_au_programmes_ODC.contains("Deux fois") ||
     row3.Avez_vous_deja_participe_au_programmes_ODC.contains("deux fois") ||
     row3.Avez_vous_deja_participe_au_programmes_ODC.equals("[2]") ||
     row3.Avez_vous_deja_participe_au_programmes_ODC.contains("(+)")) ? "true" : "false" ;// ###############################
        // ###############################
        // # Output tables

out1 = null;


// # Output table : 'out1'
out1_tmp.Horodateur = out2.Horodateur ;
out1_tmp.Confirmation_Appel = out2.Confirmation_Appel ;
out1_tmp.Confirmation_E_mail = out2.Confirmation_E_mail ;
out1_tmp.Email = out2.Email ;
out1_tmp.Prenom = out2.Prenom ;
out1_tmp.Nom = out2.Nom ;
out1_tmp.Genre = out2.Genre ;
out1_tmp.Date_de_naissance = out2.Date_de_naissance ;
out1_tmp.Pays = out2.Pays ;
out1_tmp.Situation_Profetionnelle = out2.Situation_Profetionnelle ;
out1_tmp.Profession = out2.Profession ;
out1_tmp.Votre_age = out2.Votre_age ;
out1_tmp.Telelphone = out2.Telelphone ;
out1_tmp.Niveau_d_etudes = out2.Niveau_d_etudes ;
out1_tmp.Avez_vous_une_experience_avec_la_gestion_de_projet = out2.Avez_vous_une_experience_avec_la_gestion_de_projet ;
out1_tmp.Votre_specialite = out2.Votre_specialite ;
out1_tmp.Etablissement = out2.Etablissement ;
out1_tmp.Avez_vous_deja_participe_au_programmes_ODC = out2.Avez_vous_deja_participe_au_programmes_ODC ;
out1_tmp.categorieAge = Var.categorieAge ;
out1_tmp.isStaurate = Var.isStaurate ;
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
	 * [tFileOutputExcel_1 main ] start
	 */

	

	
	
	currentComponent="tFileOutputExcel_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out1"
						
						);
					}
					

								   				
	    				if(out1.Horodateur != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 0;
					

					
						jxl.write.WritableCell cell_0_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Horodateur
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_0_tFileOutputExcel_1);
							int currentWith_0_tFileOutputExcel_1 = cell_0_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[0]=fitWidth_tFileOutputExcel_1[0]>currentWith_0_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[0]:currentWith_0_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Confirmation_Appel != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 1;
					

					
						jxl.write.WritableCell cell_1_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Confirmation_Appel
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_1_tFileOutputExcel_1);
							int currentWith_1_tFileOutputExcel_1 = cell_1_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[1]=fitWidth_tFileOutputExcel_1[1]>currentWith_1_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[1]:currentWith_1_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Confirmation_E_mail != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 2;
					

					
						jxl.write.WritableCell cell_2_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Confirmation_E_mail
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_2_tFileOutputExcel_1);
							int currentWith_2_tFileOutputExcel_1 = cell_2_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[2]=fitWidth_tFileOutputExcel_1[2]>currentWith_2_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[2]:currentWith_2_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Email != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 3;
					

					
						jxl.write.WritableCell cell_3_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Email
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_3_tFileOutputExcel_1);
							int currentWith_3_tFileOutputExcel_1 = cell_3_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[3]=fitWidth_tFileOutputExcel_1[3]>currentWith_3_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[3]:currentWith_3_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Prenom != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 4;
					

					
						jxl.write.WritableCell cell_4_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Prenom
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_4_tFileOutputExcel_1);
							int currentWith_4_tFileOutputExcel_1 = cell_4_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[4]=fitWidth_tFileOutputExcel_1[4]>currentWith_4_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[4]:currentWith_4_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Nom != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 5;
					

					
						jxl.write.WritableCell cell_5_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Nom
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_5_tFileOutputExcel_1);
							int currentWith_5_tFileOutputExcel_1 = cell_5_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[5]=fitWidth_tFileOutputExcel_1[5]>currentWith_5_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[5]:currentWith_5_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Genre != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 6;
					

					
						jxl.write.WritableCell cell_6_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Genre
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_6_tFileOutputExcel_1);
							int currentWith_6_tFileOutputExcel_1 = cell_6_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[6]=fitWidth_tFileOutputExcel_1[6]>currentWith_6_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[6]:currentWith_6_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Date_de_naissance != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 7;
					

					
						jxl.write.WritableCell cell_7_tFileOutputExcel_1 = new jxl.write.DateTime(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Date_de_naissance, cell_format_Date_de_naissance_tFileOutputExcel_1
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_7_tFileOutputExcel_1);
							int currentWith_7_tFileOutputExcel_1 = cell_7_tFileOutputExcel_1.getContents().trim().length();
							currentWith_7_tFileOutputExcel_1=12;
							fitWidth_tFileOutputExcel_1[7]=fitWidth_tFileOutputExcel_1[7]>currentWith_7_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[7]:currentWith_7_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Pays != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 8;
					

					
						jxl.write.WritableCell cell_8_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Pays
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_8_tFileOutputExcel_1);
							int currentWith_8_tFileOutputExcel_1 = cell_8_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[8]=fitWidth_tFileOutputExcel_1[8]>currentWith_8_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[8]:currentWith_8_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Situation_Profetionnelle != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 9;
					

					
						jxl.write.WritableCell cell_9_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Situation_Profetionnelle
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_9_tFileOutputExcel_1);
							int currentWith_9_tFileOutputExcel_1 = cell_9_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[9]=fitWidth_tFileOutputExcel_1[9]>currentWith_9_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[9]:currentWith_9_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Profession != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 10;
					

					
						jxl.write.WritableCell cell_10_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Profession
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_10_tFileOutputExcel_1);
							int currentWith_10_tFileOutputExcel_1 = cell_10_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[10]=fitWidth_tFileOutputExcel_1[10]>currentWith_10_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[10]:currentWith_10_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Votre_age != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 11;
					

					
						jxl.write.WritableCell cell_11_tFileOutputExcel_1 = new jxl.write.Number(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Votre_age
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_11_tFileOutputExcel_1);
							int currentWith_11_tFileOutputExcel_1 = String.valueOf(((jxl.write.Number)cell_11_tFileOutputExcel_1).getValue()).trim().length();
							currentWith_11_tFileOutputExcel_1=currentWith_11_tFileOutputExcel_1>10?10:currentWith_11_tFileOutputExcel_1;
							fitWidth_tFileOutputExcel_1[11]=fitWidth_tFileOutputExcel_1[11]>currentWith_11_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[11]:currentWith_11_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Telelphone != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 12;
					

					
						jxl.write.WritableCell cell_12_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Telelphone
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_12_tFileOutputExcel_1);
							int currentWith_12_tFileOutputExcel_1 = cell_12_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[12]=fitWidth_tFileOutputExcel_1[12]>currentWith_12_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[12]:currentWith_12_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Niveau_d_etudes != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 13;
					

					
						jxl.write.WritableCell cell_13_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Niveau_d_etudes
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_13_tFileOutputExcel_1);
							int currentWith_13_tFileOutputExcel_1 = cell_13_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[13]=fitWidth_tFileOutputExcel_1[13]>currentWith_13_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[13]:currentWith_13_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Avez_vous_une_experience_avec_la_gestion_de_projet != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 14;
					

					
						jxl.write.WritableCell cell_14_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Avez_vous_une_experience_avec_la_gestion_de_projet
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_14_tFileOutputExcel_1);
							int currentWith_14_tFileOutputExcel_1 = cell_14_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[14]=fitWidth_tFileOutputExcel_1[14]>currentWith_14_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[14]:currentWith_14_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Votre_specialite != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 15;
					

					
						jxl.write.WritableCell cell_15_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Votre_specialite
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_15_tFileOutputExcel_1);
							int currentWith_15_tFileOutputExcel_1 = cell_15_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[15]=fitWidth_tFileOutputExcel_1[15]>currentWith_15_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[15]:currentWith_15_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Etablissement != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 16;
					

					
						jxl.write.WritableCell cell_16_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Etablissement
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_16_tFileOutputExcel_1);
							int currentWith_16_tFileOutputExcel_1 = cell_16_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[16]=fitWidth_tFileOutputExcel_1[16]>currentWith_16_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[16]:currentWith_16_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.Avez_vous_deja_participe_au_programmes_ODC != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 17;
					

					
						jxl.write.WritableCell cell_17_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.Avez_vous_deja_participe_au_programmes_ODC
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_17_tFileOutputExcel_1);
							int currentWith_17_tFileOutputExcel_1 = cell_17_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[17]=fitWidth_tFileOutputExcel_1[17]>currentWith_17_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[17]:currentWith_17_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.categorieAge != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 18;
					

					
						jxl.write.WritableCell cell_18_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.categorieAge
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_18_tFileOutputExcel_1);
							int currentWith_18_tFileOutputExcel_1 = cell_18_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[18]=fitWidth_tFileOutputExcel_1[18]>currentWith_18_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[18]:currentWith_18_tFileOutputExcel_1+2;
	    				} 
					
								   				
	    				if(out1.isStaurate != null) {
    				
					
//modif start
					
						columnIndex_tFileOutputExcel_1 = 19;
					

					
						jxl.write.WritableCell cell_19_tFileOutputExcel_1 = new jxl.write.Label(columnIndex_tFileOutputExcel_1, startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,
					
//modif end
								out1.isStaurate
							);
//modif start					
							//If we keep the cell format from the existing cell in sheet
							
							
//modif ends							
							writableSheet_tFileOutputExcel_1.addCell(cell_19_tFileOutputExcel_1);
							int currentWith_19_tFileOutputExcel_1 = cell_19_tFileOutputExcel_1.getContents().trim().length();
							fitWidth_tFileOutputExcel_1[19]=fitWidth_tFileOutputExcel_1[19]>currentWith_19_tFileOutputExcel_1?fitWidth_tFileOutputExcel_1[19]:currentWith_19_tFileOutputExcel_1+2;
	    				} 
					
    			nb_line_tFileOutputExcel_1++;
				
 
     row2 = out1;


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




   				
	    		if(row2.Horodateur != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Horodateur)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Confirmation_Appel != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Confirmation_Appel)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Confirmation_E_mail != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Confirmation_E_mail)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Email != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Email)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Prenom != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Prenom)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Nom != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Nom)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Genre != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Genre)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Date_de_naissance != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
								FormatterUtils.format_Date(row2.Date_de_naissance, "dd/MM/yyyy")				
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Pays != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Pays)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Situation_Profetionnelle != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Situation_Profetionnelle)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Profession != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Profession)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Votre_age != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Votre_age)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Telelphone != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Telelphone)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Niveau_d_etudes != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Niveau_d_etudes)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.Avez_vous_une_experience_avec_la_gestion_de_projet != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Avez_vous_une_experience_avec_la_gestion_de_projet)							
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
    			


   				
	    		if(row2.Avez_vous_deja_participe_au_programmes_ODC != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.Avez_vous_deja_participe_au_programmes_ODC)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.categorieAge != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.categorieAge)							
				);


							
	    		} //  			

    			strBuffer_tLogRow_1.append("|");
    			


   				
	    		if(row2.isStaurate != null) { //              
                    							
       
				strBuffer_tLogRow_1.append(
				                String.valueOf(row2.isStaurate)							
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out1");
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
        final JobETL JobETLClass = new JobETL();

        int exitCode = JobETLClass.runJobInTOS(args);

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
            java.io.InputStream inContext = JobETL.class.getClassLoader().getResourceAsStream("local_project/jobetl_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = JobETL.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : JobETL");
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
 *     165482 characters generated by Talend Open Studio for Data Integration 
 *     on the 28 avril 2025 à 10:30:01 WEST
 ************************************************************************************************/