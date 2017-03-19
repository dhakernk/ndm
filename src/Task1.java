import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



/**
 * @author NKDHAKER
 *
 */

public class Task1 {
	static boolean input_check;
	static int choice = 0, M_no;
	static String Temp;
	
	public static boolean check_date(String Date){
		int dd,mm,yy;
		if(Date.length()!=8)
		{
			return true;
		}else{
			GregorianCalendar cale= new GregorianCalendar();
			int[] NonLeapYear={31,28,31,30,31,30,31,31,30,31,30,31};
			int[] LeapYear={31,29,31,30,31,30,31,31,30,31,30,31};
			
			try{
				yy=Integer.parseInt(Date.substring(0,4));
				mm=Integer.parseInt(Date.substring(4,6));
				dd=Integer.parseInt(Date.substring(6,8));
			}catch(NumberFormatException e){
				System.out.println("ERROR : Number Format Exception"+ e+" " );
				return true;
			}
			cale.setTimeInMillis(System.currentTimeMillis());
			int currYear= cale.get(GregorianCalendar.YEAR);
			
			int currMonth= cale.get(GregorianCalendar.MONTH)+1;
			
			int currDay= cale.get(GregorianCalendar.DAY_OF_MONTH);
			//System.out.println("currYear "+currYear+"currMonth "+currMonth+"currDay "+currDay);
			//System.out.println(" YY "+yy+" MM "+mm+" dd "+dd);
			int NoOfDays;
			if(yy<1700||yy>currYear){
				return true;
			}else if( mm<1  ||  mm>12  ){
				return true;
			}else{
				boolean isLeapYear= cale.isLeapYear(yy);
				if(isLeapYear)
					NoOfDays=LeapYear[mm-1];
				else
					NoOfDays=NonLeapYear[mm-1];

				if(dd<1 || dd>NoOfDays){
					return true;
				}
				if(currYear==yy){
					if(currMonth>mm){
						return false;
					}else if(currMonth==mm)
					{
						if(currDay>dd)
						{
							return false;
						}else{
							return true;
						}
					}else{
						return true;
					}
				}
			}	
			return false;
		}
	}
	//Input No. of Digit in a Mobile No.
	static int Mobile_No_Length(){
		System.out.println("Enter length Of Mobile No :");
		BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
		try {
			do{
				//input  validation 
				Temp=br1.readLine();
				if(check_input(Temp))
				{
					if(Integer.parseInt(Temp)>10)
					System.out.println("Please Enter Valid length Of Mobile No :");
				}
				else{
					if(Integer.parseInt(Temp)<8)
					{
						Temp="8";
					}
				}
			}while(check_input(Temp) );
									
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		M_no=Integer.parseInt(Temp);
		return M_no;
	}
	
	static boolean check_choice(String input_string){
		if(input_string.matches("^[1-2]+$")){
			return false;
		}else{
			return true;	
		}
	}
	
	static boolean check_input(String input){
		if(input.matches("^[0-9]+$"))
		{
			int number=Integer.parseInt(input);
			if(number>0){
				return false;	
			}else{
				return true;
			}	
		}else{
			return true;	
		}
	}
	
	static void FileByDate(int date,int No_of_rec,int fil_n,int digit) throws IOException{
		Business_Logic obej = new Business_Logic();
		int Total_Records =No_of_rec*fil_n;		
		do{
			obej.openfile(date,No_of_rec,fil_n);
			for(int i=0;i<No_of_rec;i++)
			{
				obej.WriteFile(date,Total_Records,digit);	
			}
			obej.CloseFile();
			System.out.println("File NO:"+fil_n);
			fil_n--;
		}while(fil_n!=0);
	}
	
	static void FileByMonth(String month,int No_of_rec,int File_n,int digit) throws IOException{
		int max_day, DateForFile;
		String date;
		int arr1[]={31,28,31,30,31,30,31,31,30,31,30,31};
		int arr2[]={31,29,31,30,31,30,31,31,30,31,30,31};
		String year=month.substring(0,4);
		String l=month.substring(4, 6);
		
		if(Integer.parseInt(year)%4==0)
		{
			max_day=arr2[Integer.parseInt(l)-1];
		}else
		{
			max_day=arr1[Integer.parseInt(l)-1];
		}
		
		String today_date = null;
		//System.out.println("Year "+year+"Month "+l+"Maxday "+max_day+"Input date"+month);
		for(int i=1;i<=max_day;i++)
		{
				date=month.substring(0,6)+Integer.toString(i);
				today_date=month.substring(0,6)+String.valueOf(i);
				//System.out.print("Today0: "+date);
				while(date.length()!=8)
				{
					date=month.substring(0,6)+"0"+String.valueOf(i);
					today_date=month.substring(0, 6)+"0"+String.valueOf(i);
					//System.out.print("Today: "+today_date);
				}
				//System.out.print("Today2: "+today_date);
				//System.out.println(check_date(today_date));
				if(!check_date(today_date))
				{
					//System.out.print("input date is correct"+today_date+"Date"+date);
					DateForFile=Integer.parseInt(date);
					//System.out.println("today_date"+today_date);
					System.out.println("DATE:"+date);
					FileByDate(DateForFile, No_of_rec, File_n, digit);
				}
		}
	}
	
	public static void main(String []args) throws IOException{
		BufferedReader BRin;
		String  record_no, Input_choice;
		int file_n;
		int rec = 0;
		System.out.println("Check details in File 'Mobile_No_Prefix'\n");
		BRin= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Chose Your operation:\n1: File BY Date:\n2: File BY Month");
		//read Input choice
		do{
			//input choice validation 
			Input_choice=BRin.readLine();
			if(!check_choice(Input_choice))
			{
				choice=Integer.valueOf(Input_choice);
			}else{
				System.out.println("Invalid option please ENTER 1 or 2");
			}
			
		}while(check_choice(Input_choice)||(choice>2));
		choice=Integer.valueOf(Input_choice);
		
		
		
		//TAKE INPUT OF NO. OF RECORDS TO BE WRITE IN A FILE;
		System.out.println("Enter No. of Records per File:");
		do{
			//input  validation 
			record_no=BRin.readLine();
			if(check_input(record_no))
			{
				System.out.println("Please Enter Valid No. of Records per File");
			}
		}while(check_input(record_no) );
		rec= Integer.parseInt(record_no);

		
	

		//TAKE INPUT OF NO. OF FILES TO BE GENERATE IN A DAY;
		System.out.println("Enter No. of File's For A Day");
		String file_no;
		do{
			//input  validation 
			file_no=BRin.readLine();
			if(check_input(file_no))
			{
				System.out.println("Please Enter Valid No. of File's For a day");
			}
		}while(check_input(file_no) );
		file_n= Integer.parseInt(file_no);
		
		M_no=Mobile_No_Length();
		
		
		
		//EXECUTE ANY TWO CASE
		switch(choice)
		{
			case 1:	System.out.println("Enter A Date in YYYYMMDD Format:");
					String input_date = BRin.readLine();
					while(check_date(input_date)){
						System.out.println("Please Enter A VALID Date in YYYYMMDD Format:");
						input_date = BRin.readLine();
					}
					int de=Integer.parseInt(input_date);
					System.out.println("Date is:"+input_date);
					FileByDate(de,rec,file_n,M_no);
					break;
			case 2:	System.out.println("Enter A month in YYYYMM Format:");
					String input_month = BRin.readLine();
					input_month= input_month+"01";
					while(check_date(input_month)){
						System.out.println("Please Enter A VALID Date in YYYYMM Format:");
						input_month = BRin.readLine();
						input_month= input_month+"01";	
					}
					//input_month=input_month.substring(0,6);
					System.out.println("month is:"+input_month);
					FileByMonth(input_month,rec,file_n,M_no);
					break;
			default:
				System.err.print("Invalide input");
		}	
	}	
}