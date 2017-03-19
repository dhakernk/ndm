import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


/**
 * @author NKDHAKER
 *
 */
public class Business_Logic {

	private FileWriter FWobj;
	private BufferedWriter BWobj;
				

	//Open A  FIle
		void openfile(int date1,int record,int file_no){
		try{
			String fullpath="E:\\Eclipse\\Comviva_File\\src\\output\\";
			String fileName= String.valueOf(date1)+"-"+String.valueOf(record)+"-"+Integer.toString(file_no)+".csv";
			File file_obj= new File(fullpath,fileName); 
			if(!file_obj.exists())
			{ try {  file_obj.createNewFile(); 
				}catch(IOException e){
                e.printStackTrace();
				}
			}
			FWobj = new FileWriter(file_obj.getAbsoluteFile());
			BWobj =new BufferedWriter(FWobj);
			BWobj.write("CALLING_PARTY_NUMBER,\tCALL_START_DATE,\tCALL_START_TIME,\tORIGINATION_LOCATION_INFO,\tCALLED_PARTY_NUMBER,\tCALL_TYPE,\tCHARGED_DURATION,\tACCOUNT_VALUE_BEFORE_CALL,\tCHARGED_AMOUNT,\tACCOUNT_VALUE_AFTER_CALL,\tROAMING_TYPE,\tSERVICE_OFFERING,\tSERVICE_CLASS\n");
			}catch(Exception e){
			System.out.println("File Can't Open ERROR: "+e);
		}
	}
	//Write File
		void WriteFile(int date,int No_of_rec,int length_of_no){
		try {	
		String TMP;
		Incdr incdr = new Incdr();
		incdr.setCallig_Party_Number(length_of_no);
		TMP=incdr.getCallig_Party_Number()+",\t";
		
		
	//CALL_START_DATE	
		incdr.setCALL_START_DATE(date);
		TMP= TMP+incdr.getCALL_START_DATE()+",\t";
		
	//CALL_START_TIME	
		incdr.setCALL_START_TIME(No_of_rec);
		TMP= TMP+incdr.getCALL_START_TIME()+",\t";
		
	//ORIGINATION_LOCATION_INFO
		incdr.setORIGINATION_LOCATION_INFO();
		TMP= TMP+incdr.getORIGINATION_LOCATION_INFO()+",\t";
		
		
	//CALLED_PARTY_NUMBER
		incdr.setCALL_TYPE_CALLED_PARTY(length_of_no);
		TMP= TMP+incdr.getCALL_TYPE_CALLED_PARTY()+",\t";
		
	//CHARGED_DURATION
		incdr.setCharged_duration();
		TMP= TMP+incdr.getCharged_duration()+",\t";
	
	//ACCOUNT_VALUE_BEFORE_CALL
		incdr.setA_V_B(20000);
		TMP= TMP+incdr.getA_V_B()+",\t";
	//CHARGED_AMOUNT
		incdr.setCHARGED_AMOUNT();
		TMP= TMP+incdr.getCHARGED_AMOUNT()+",\t";

	//ACCOUNT_VALUE_AFTER_CALL	
		incdr.setA_V_A();
		TMP= TMP+incdr.getA_V_A()+",\t";
			
	//ROAMING_TYPE	
		incdr.setROAMING_TYPE();
		TMP= TMP+incdr.getROAMING_TYPE()+",\t";
				
	//SERVICE_OFFERING
		incdr.setSERVICE_OFFERING();
		TMP= TMP+incdr.getSERVICE_OFFERING()+",\t";
		
	//SERVICE_CLASS
		incdr.setSERVICE_CLASS();
		TMP= TMP+incdr.getSERVICE_CLASS()+",\t";
		BWobj.write(TMP+"\n");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	//Close FIle
		void CloseFile(){
		try {
			BWobj.close();
			FWobj.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}