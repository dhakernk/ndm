import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


/**
 * @author NKDHAKER
 *
 */
public class Incdr {
	
	private String callig_Party_Number;
	private String CALL_START_DATE;
	private String CALL_START_TIME;
	private String ORIGINATION_LOCATION_INFO;
	private String CHECK_CALL_TYPE;
	private String Charged_duration;
	private String ACCOUNT_VALUE_BEFORE_CALL;
	private String CHARGED_AMOUNT;
	private String ACCOUNT_VALUE_AFTER_CALL;
	private String ROAMING_TYPE; 
	private String SERVICE_OFFERING;
	private String SERVICE_CLASS;
	
	int hh,mm,ss;
	float AVB,AVA;
	
	static int count_data,count_smo,count_moc,total_records;
	static int time=000000;
	static int Check_Call_Type=00;
	float inter,sec;
	static int count_rec;
	static int check_repeat_rec, check_No_of_Time_repeat_rec;	
	int Rept_rec, No_of_time_Rept_recs;
	static boolean check=true,check2=true;
	String Starting_No;
	String Call_Type;
	
		public String getCallig_Party_Number() {
			
			return callig_Party_Number;
		}
	//CALLING_PARTY_NUMBER
				
		String RandomMobNo(int n) throws IOException
		{
			String mobile_no;
			String [] arrr= null;
			String s;
			int l = 0;
			long LOWER_RANGE = 0; //assign lower range value
			long  UPPER_RANGE = 2000000000; //assign upper range value
			
			Random Integer_No = new Random();
			long randomValue =  (long)(Integer_No.nextDouble()*(UPPER_RANGE - LOWER_RANGE)*100000);
			FileReader Fr= new FileReader("E://eclipse/Comviva_File/Mobile_No_Prefix");
			BufferedReader BR= new BufferedReader(Fr);
				s= BR.readLine();
				arrr=s.split(",");
				l=arrr.length;
				Fr.close();
			 mobile_no= arrr[Integer_No.nextInt(l)]+randomValue;
			 return  mobile_no.substring(0,n);
		}

		public void setCallig_Party_Number(int callig_Party_Number) throws IOException {
			String Temp=RandomMobNo(callig_Party_Number);
			this.callig_Party_Number = Temp;
		}
	
		public String getCALL_START_DATE() {
			return CALL_START_DATE;
		}
	
		public void setCALL_START_DATE(int date) {
			CALL_START_DATE = String.valueOf(date);
		}
		
		public String StartTime(float interval)
		{
			Random asd= new Random();
			if(check)
			{
				sec =asd.nextInt((int)interval);
				check = false;

			}else{
				if(No_of_time_Rept_recs>0)
				{
					if(check_No_of_Time_repeat_rec!=No_of_time_Rept_recs)
					{
						sec=(count_rec-1)*interval+0;	
						--count_rec;
						++check_No_of_Time_repeat_rec;	
					}else{
						if(Rept_rec>=0)
						{
							if(check_repeat_rec==Rept_rec){
								++count_rec;
							}
							check_No_of_Time_repeat_rec=0;
							if(check_repeat_rec<Rept_rec){
								if(check2){
									sec=(count_rec-1)*interval+0;
									++check_repeat_rec;
									check2=false;
								}else{
									if(check_repeat_rec!=Rept_rec){
										sec=(count_rec-1)*interval+0;
										++check_repeat_rec;
										check2=true;
									}
								}
							}else{
								--count_rec;
								sec=(count_rec-1)*interval+0;
								check_No_of_Time_repeat_rec=1;
							}	
						}
					}
					
				}else{
					if(Rept_rec>=0)
					{
						if(check_repeat_rec<Rept_rec){
							if(check2){
								sec=(count_rec)*interval+sec;
								++check_repeat_rec;
								check2=false;
							}else{
								if(check_repeat_rec!=Rept_rec){
									sec=(count_rec)*interval+sec;
									++check_repeat_rec;
									check2=true;
								}
							}
						}	
					}else{
						sec=(count_rec)*interval+sec;
					}
				}
				
			}		
			//    86,400
			if(sec/3600>=0){
				hh=(int) (sec/3600);
				sec=sec%3600;
			}
			if(sec/60>=0){
				mm=(int) (sec/60);
				ss=(int) (sec%60);
			}
			if(hh==24){
				hh=00;
			}
			String p=String.valueOf(time=hh*10000+mm*100+ss);
			//System.out.println(" HH: "+hh+" MM: "+mm+" SS: "+ss);
			int l= p.length();
			while(l<=5)
			{
				p="0"+p;
				l++;
			}
			return p;
		}

		public String getCALL_START_TIME() {
			return CALL_START_TIME;
		}
		
		public void setCALL_START_TIME(int No_of_rec) {
			Random Rand =new Random();
			String ar[]={"DATA","DATA","DATA","SMO","MOC"};
			Call_Type=ar[Check_Call_Type =Rand.nextInt(5)];
			float inter = 86400.0f/(No_of_rec*1.0f);
			if(inter<=1)
			{
				inter=1;
			}
			if(No_of_rec/86400>=0){
				Rept_rec=(No_of_rec%86400);
				No_of_time_Rept_recs=(int) (No_of_rec/86400);		
			}
			
			CALL_START_TIME = StartTime(inter);
		}

		public String getORIGINATION_LOCATION_INFO() {
			
			return ORIGINATION_LOCATION_INFO;
		}

		public void setORIGINATION_LOCATION_INFO() throws IOException {
			int count_line=1,m = 0;
			String s,arrr[] = null;
			Random random = new Random();
			FileReader Fr= new FileReader("E://eclipse/Comviva_File/Mobile_No_Prefix");
			BufferedReader BR= new BufferedReader(Fr);
				while(count_line!=4)
					{
					s= BR.readLine();
					arrr=s.split(",");
					m=arrr.length;
					++count_line;
					}
				Fr.close();
			String temp=arrr[random.nextInt(m)];
			String str=temp;//=(randomValue);
			int l=1;//=str.length();
				do{		
						str = str+String.valueOf(random.nextInt(10));
						l++;
				}while(l <= 10);
				if(Check_Call_Type<3)
				{
					ORIGINATION_LOCATION_INFO = "            ";
				}else if(Check_Call_Type ==3){
					ORIGINATION_LOCATION_INFO = String.valueOf(temp);
				}else{
					ORIGINATION_LOCATION_INFO = str;
				}

			//ORIGINATION_LOCATION_INFO = str;
		}

		
		public String getCALL_TYPE_CALLED_PARTY() {
			return CHECK_CALL_TYPE;
		}

		
		public void setCALL_TYPE_CALLED_PARTY(int length_of_no) throws IOException {
			String TMP = "";
				
		//CALLED_PARTY_NUMBER	
			
			int count_line=1,m = 0;
			String s = "",arrr[] = null;
			Random random = new Random();
			FileReader Fr= new FileReader("E://eclipse/Comviva_File/Mobile_No_Prefix");
			BufferedReader BR= new BufferedReader(Fr);
				while(count_line!=3)
					{
					s= BR.readLine();
					++count_line;
					}
				arrr=s.split(",");
				m=arrr.length;
				Fr.close();
			
			
			String CALLED_PARTY_NUMBER =arrr[random.nextInt(m)]+ (String.valueOf(RandomMobNo(length_of_no))).substring(2,length_of_no);
			if(Check_Call_Type<3)
			{
				TMP="    "+",\t";
			}else {
				TMP=CALLED_PARTY_NUMBER+",\t";
			}
			
			
		//CALL_TYPE	
			total_records++;
			if(Check_Call_Type==4){
				count_moc++;
			}else if(Check_Call_Type==3){
				count_smo++;
			}else{
				count_data++;
			}
			if(total_records%10==0)
			{
				if(count_data!=3*count_moc){
					TMP=TMP+Call_Type;
					total_records--;
				}else if(count_data!=3*count_smo){
					TMP=TMP+Call_Type;
					total_records--;
				}else{
					TMP=TMP+Call_Type;
				}
			}else{
				TMP=TMP+Call_Type;
			}
			CHECK_CALL_TYPE = TMP;
		}

		
		private int RandomFloat(int x){
			int no;
			Random obt= new Random();
			no = obt.nextInt(x+1);
			return no;
		}
		public String getCharged_duration() {
			return Charged_duration;
		}

		
		public void setCharged_duration() {
			Charged_duration = String.valueOf(RandomFloat(300));
		}
		
		public String getA_V_B(){
			
			return ACCOUNT_VALUE_BEFORE_CALL;
		}

		public void setA_V_B(int max) {
			float Account_Value_Before=RandomFloat(20000);
			ACCOUNT_VALUE_BEFORE_CALL = String.valueOf (AVB=Account_Value_Before/100);
			AVA=(float)(RandomFloat((int)Account_Value_Before)+1)/100;
		}

		public String getCHARGED_AMOUNT(){
			return CHARGED_AMOUNT;
		}

		public void setCHARGED_AMOUNT(){
			CHARGED_AMOUNT = String.valueOf(RandomFloat((int)(AVB-AVA)));
		}

		
		public String getA_V_A() {
			
			return ACCOUNT_VALUE_AFTER_CALL;
		}

		
		public void setA_V_A() {
			ACCOUNT_VALUE_AFTER_CALL = String.valueOf(AVA);
		}

		
		public String getROAMING_TYPE() {
			return ROAMING_TYPE;
		}

		
		public void setROAMING_TYPE() {
			ROAMING_TYPE = "C1";
		}

		private int RandomInt(int max_no){
			Random Integer_No = new Random();
			int return_random_no= Integer_No.nextInt(max_no);
			return return_random_no;
		}
		public String getSERVICE_OFFERING() {
			return SERVICE_OFFERING;
		}

		public void setSERVICE_OFFERING() {
			SERVICE_OFFERING = String.valueOf(RandomInt(32767));
		}

		
		public String getSERVICE_CLASS() {
			return SERVICE_CLASS;
		}
		

		public void setSERVICE_CLASS() {
			SERVICE_CLASS = String.valueOf(RandomInt(32767));
		}

}
