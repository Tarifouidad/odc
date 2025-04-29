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


package local_project.jobencodage_0_1;

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
 * Job: JobEncodage Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class JobEncodage implements TalendJob {

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
			
			if(input_file != null){
				
					this.setProperty("input_file", input_file.toString());
				
			}
			
		}
		
		//if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if(NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

public String input_file;
public String getInput_file(){
	return this.input_file;
}
	}
	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.
	public ContextProperties getContext() {
		return this.context;
	}
	private final String jobVersion = "0.1";
	private final String jobName = "JobEncodage";
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
				JobEncodage.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(JobEncodage.this, new Object[] { e , currentComponent, globalMap});
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
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_JobEncodage = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[0];

	
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
			if(length > commonByteArray_LOCAL_PROJECT_JobEncodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobEncodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_JobEncodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobEncodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobEncodage) {

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

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobEncodage) {

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
    final static byte[] commonByteArrayLock_LOCAL_PROJECT_JobEncodage = new byte[0];
    static byte[] commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[0];

	
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
			if(length > commonByteArray_LOCAL_PROJECT_JobEncodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobEncodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length, utf8Charset);
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
			if(length > commonByteArray_LOCAL_PROJECT_JobEncodage.length) {
				if(length < 1024 && commonByteArray_LOCAL_PROJECT_JobEncodage.length == 0) {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[1024];
				} else {
   					commonByteArray_LOCAL_PROJECT_JobEncodage = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length);
			strReturn = new String(commonByteArray_LOCAL_PROJECT_JobEncodage, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobEncodage) {

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

		synchronized(commonByteArrayLock_LOCAL_PROJECT_JobEncodage) {

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
		

 
	final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:RNNXCyTIvLDLPx/EnUj0S1uOQnwu0IgKkCJcOQ==");
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

		Object source_tFileInputExcel_1 = context.input_file;
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
    	for(org.apache.poi.ss.usermodel.Sheet sheet_tFileInputExcel_1 : workbook_tFileInputExcel_1){
   			sheetList_tFileInputExcel_1.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet_tFileInputExcel_1);
    	}
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
					int tempRowLength_tFileInputExcel_1 = 18;
				
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
				curColName_tFileInputExcel_1 = "Horodateur";

				row1.Horodateur = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Horodateur = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 1;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Confirmation_Appel";

				row1.Confirmation_Appel = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Confirmation_Appel = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 2;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Confirmation_E_mail";

				row1.Confirmation_E_mail = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Confirmation_E_mail = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 3;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Email";

				row1.Email = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Email = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 4;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Prenom";

				row1.Prenom = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Prenom = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 5;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Nom";

				row1.Nom = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Nom = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 6;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Genre";

				row1.Genre = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Genre = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 7;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Date_de_naissance";

				row1.Date_de_naissance = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.Date_de_naissance = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 8;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Pays";

				row1.Pays = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Pays = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 9;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Situation_Profetionnelle";

				row1.Situation_Profetionnelle = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Situation_Profetionnelle = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 10;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Profession";

				row1.Profession = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Profession = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 11;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Votre_age";

				row1.Votre_age = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Votre_age = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 12;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Telelphone";

				row1.Telelphone = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Telelphone = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 13;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Niveau_d_etudes";

				row1.Niveau_d_etudes = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Niveau_d_etudes = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 14;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Avez_vous_une_experience_avec_la_gestion_de_projet";

				row1.Avez_vous_une_experience_avec_la_gestion_de_projet = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Avez_vous_une_experience_avec_la_gestion_de_projet = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 15;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Votre_specialite";

				row1.Votre_specialite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Votre_specialite = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 16;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Etablissement";

				row1.Etablissement = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Etablissement = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 17;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "Avez_vous_deja_participe_au_programmes_ODC";

				row1.Avez_vous_deja_participe_au_programmes_ODC = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.Avez_vous_deja_participe_au_programmes_ODC = null;
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
					

    // Affectation des colonnes de row1 à row2
row2.Horodateur = row1.Horodateur;
row2.Confirmation_Appel = row1.Confirmation_Appel;
row2.Confirmation_E_mail = row1.Confirmation_E_mail;
row2.Email = row1.Email;
row2.Prenom = row1.Prenom;
row2.Nom = row1.Nom;
row2.Genre = row1.Genre;
row2.Date_de_naissance = row1.Date_de_naissance;
row2.Pays = row1.Pays;
row2.Situation_Profetionnelle = row1.Situation_Profetionnelle;
row2.Profession = row1.Profession;
row2.Votre_age = row1.Votre_age;
row2.Telelphone = row1.Telelphone;
row2.Niveau_d_etudes = row1.Niveau_d_etudes;
row2.Avez_vous_une_experience_avec_la_gestion_de_projet = row1.Avez_vous_une_experience_avec_la_gestion_de_projet;
row2.Votre_specialite = row1.Votre_specialite;
row2.Etablissement = row1.Etablissement;
row2.Avez_vous_deja_participe_au_programmes_ODC = row1.Avez_vous_deja_participe_au_programmes_ODC;

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
					



String[] row_tBufferOutput_1=new String[]{"","","","","","","","","","","","","","","","","","",};		
	    if(row2.Horodateur != null){
	        
	            row_tBufferOutput_1[0] = row2.Horodateur;
	                        			    
	    }else{
	    	row_tBufferOutput_1[0] = null;
	    }
	    if(row2.Confirmation_Appel != null){
	        
	            row_tBufferOutput_1[1] = row2.Confirmation_Appel;
	                        			    
	    }else{
	    	row_tBufferOutput_1[1] = null;
	    }
	    if(row2.Confirmation_E_mail != null){
	        
	            row_tBufferOutput_1[2] = row2.Confirmation_E_mail;
	                        			    
	    }else{
	    	row_tBufferOutput_1[2] = null;
	    }
	    if(row2.Email != null){
	        
	            row_tBufferOutput_1[3] = row2.Email;
	                        			    
	    }else{
	    	row_tBufferOutput_1[3] = null;
	    }
	    if(row2.Prenom != null){
	        
	            row_tBufferOutput_1[4] = row2.Prenom;
	                        			    
	    }else{
	    	row_tBufferOutput_1[4] = null;
	    }
	    if(row2.Nom != null){
	        
	            row_tBufferOutput_1[5] = row2.Nom;
	                        			    
	    }else{
	    	row_tBufferOutput_1[5] = null;
	    }
	    if(row2.Genre != null){
	        
	            row_tBufferOutput_1[6] = row2.Genre;
	                        			    
	    }else{
	    	row_tBufferOutput_1[6] = null;
	    }
	    if(row2.Date_de_naissance != null){
	        
	            row_tBufferOutput_1[7] = String.valueOf(row2.Date_de_naissance);
	                        			    
	    }else{
	    	row_tBufferOutput_1[7] = null;
	    }
	    if(row2.Pays != null){
	        
	            row_tBufferOutput_1[8] = row2.Pays;
	                        			    
	    }else{
	    	row_tBufferOutput_1[8] = null;
	    }
	    if(row2.Situation_Profetionnelle != null){
	        
	            row_tBufferOutput_1[9] = row2.Situation_Profetionnelle;
	                        			    
	    }else{
	    	row_tBufferOutput_1[9] = null;
	    }
	    if(row2.Profession != null){
	        
	            row_tBufferOutput_1[10] = row2.Profession;
	                        			    
	    }else{
	    	row_tBufferOutput_1[10] = null;
	    }
	    if(row2.Votre_age != null){
	        
	            row_tBufferOutput_1[11] = row2.Votre_age;
	                        			    
	    }else{
	    	row_tBufferOutput_1[11] = null;
	    }
	    if(row2.Telelphone != null){
	        
	            row_tBufferOutput_1[12] = row2.Telelphone;
	                        			    
	    }else{
	    	row_tBufferOutput_1[12] = null;
	    }
	    if(row2.Niveau_d_etudes != null){
	        
	            row_tBufferOutput_1[13] = row2.Niveau_d_etudes;
	                        			    
	    }else{
	    	row_tBufferOutput_1[13] = null;
	    }
	    if(row2.Avez_vous_une_experience_avec_la_gestion_de_projet != null){
	        
	            row_tBufferOutput_1[14] = row2.Avez_vous_une_experience_avec_la_gestion_de_projet;
	                        			    
	    }else{
	    	row_tBufferOutput_1[14] = null;
	    }
	    if(row2.Votre_specialite != null){
	        
	            row_tBufferOutput_1[15] = row2.Votre_specialite;
	                        			    
	    }else{
	    	row_tBufferOutput_1[15] = null;
	    }
	    if(row2.Etablissement != null){
	        
	            row_tBufferOutput_1[16] = row2.Etablissement;
	                        			    
	    }else{
	    	row_tBufferOutput_1[16] = null;
	    }
	    if(row2.Avez_vous_deja_participe_au_programmes_ODC != null){
	        
	            row_tBufferOutput_1[17] = row2.Avez_vous_deja_participe_au_programmes_ODC;
	                        			    
	    }else{
	    	row_tBufferOutput_1[17] = null;
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
        final JobEncodage JobEncodageClass = new JobEncodage();

        int exitCode = JobEncodageClass.runJobInTOS(args);

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
            java.io.InputStream inContext = JobEncodage.class.getClassLoader().getResourceAsStream("local_project/jobencodage_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = JobEncodage.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
                        context.setContextType("input_file", "id_String");
                        if(context.getStringValue("input_file") == null) {
                            context.input_file = null;
                        } else {
                            context.input_file=(String) context.getProperty("input_file");
                        }
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
        if (parentContextMap != null && !parentContextMap.isEmpty()) {if (parentContextMap.containsKey("input_file")) {
                context.input_file = (String) parentContextMap.get("input_file");
            }
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
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : JobEncodage");
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
 *     84297 characters generated by Talend Open Studio for Data Integration 
 *     on the 28 avril 2025 à 10:30:01 WEST
 ************************************************************************************************/