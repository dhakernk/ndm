import java.io.*;
import java.util.*;

class doimp {
	private Scanner x;
	private Formatter in;
	String  s;
	String sorted_array[][] = new String[4][3];
	public String[][] getSorted_array() {
		return sorted_array;
	}

	public void setSorted_array(String[][] sorted_array) {
		this.sorted_array = sorted_array;
	}
	static int i=0;
	void open_file()
	{
		try {
			File y= new File("Fred.txt");
			x = new Scanner(y);
		}catch(Exception e)
		{
			System.out.println("can't read this file");
			
		}
	}
	
	void display()
	{
		for(int a=0; a < sorted_array.length; a++)
		{
			for(int b=0; b < 3; b++)
			{
				System.out.print(sorted_array[a][b]+ "\t");
			}
			System.out.println();
		}
	}
	
	void do_sort_name(){
		//System.out.println(sorted_array.length);
		for(int a=0; a <sorted_array.length ; a++)
		{
		//	System.out.println("value of A is " +a);
			for(int b=0; b<a ;b++ )
			{
			//	System.out.println("value of B is " +b);
				if((sorted_array[a][0].compareTo(sorted_array[b][0])<0))
				{
					String tmp[]=new String[4];
					//swapping
					tmp=sorted_array[a];
					sorted_array[a]=sorted_array[b];
					sorted_array[b]=tmp;
				}
			}
		}
	}
	
	void do_sort_age(){
		 for(int a=0; a <sorted_array.length ; a++)
			{
			//	System.out.println("value of A is " +a);
				for(int b=0; b<a ;b++ )
				{
				//	System.out.println("value of B is " +b);
					
					if((Integer.parseInt(sorted_array[a][1])) <= (Integer.parseInt(sorted_array[b][1])))
					{
						String tmp[]=new String[4];
						//swapping
						tmp=sorted_array[a];
						sorted_array[a]=sorted_array[b];
						sorted_array[b]=tmp;
					}
				}
			}
	 }
	
	void read(){
		while(x.hasNext()){
			s= x.next();
			System.out.print(s +"\t\t");
			String display[]= s.split(",");
			System.out.println(display[0]);
			//System.out.println("value of i is " + i);
				sorted_array[i]= display;	
			i++; //do_sort(display[0]);
		}
	 }
	
	void close_file(){
		 x.close();
	 }
	
	public void closefile(){
		in.close();
	}

	void CreateFile(){
		try{
			File xy= new File("Write.txt");
			 in= new Formatter(xy);
		}catch(Exception e){
			System.out.print("File Creation Error"+e);
		}
	}
	
	void add_record(String arr[][])
	{
		//int count=arr.length;
//			int i=0;
			//in.format("%s,%d,%ld", arr[0],arr[1],arr[2]);
		System.out.println(in);
		in.format("%d,%s,%s", 20,"Narendra","mahi");
		in.close();
		
		}
	void do_sort(int c){
		for(int a=0; a <sorted_array.length ; a++)
		{
			for(int b=0; b<a ;b++ )
			{
				if(c==0){//String Sorting
					if((sorted_array[a][0].compareTo(sorted_array[b][0])<0))
					{
						String tmp[]=new String[4];
						tmp=sorted_array[a];
						sorted_array[a]=sorted_array[b];
						sorted_array[b]=tmp;
					}
					
				}else if(c==1){//Age Sorting
					if((Integer.parseInt(sorted_array[a][1])) <= (Integer.parseInt(sorted_array[b][1])))
					{
						String tmp[]=new String[4];
						tmp=sorted_array[a];
						sorted_array[a]=sorted_array[b];
						sorted_array[b]=tmp;
					}	
				}
			}
		}
		
	}
}
public class Read_file{
	public static void main(String []args){
		doimp obj =new doimp();
		obj.open_file();
		obj.read();
		obj.CreateFile();
		
		//obj.close_file();
		//obj.do_sort(0); //obj.do_sort_name();
		//obj.display();
		//obj.do_sort(1);//obj.do_sort_age();
		//obj.display();
//		obj.add_record(sorted_array);
		
	}
}