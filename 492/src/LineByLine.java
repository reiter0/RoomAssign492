import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.*;

public class LineByLine
{
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://L-1D39-492/roomAssign";

	   //  Database credentials
	   static final String USER = "java";
	   static final String PASS = "mysql";
	
	public static void main(String[] args)
	{
		//Connection conn = null;
		//Statement stmt = null;
		String test1;
		// TODO Auto-generated method stub
		String afterOut="/home/cody/workspace/492/output.txt";
		String fileName="/home/cody/workspace/492/src/temp.txt";
		try 
		{
			BufferedReader br = new BufferedReader( new FileReader(fileName));
			PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
			String strLine = null;
			StringTokenizer st = null;
			int lineNumber = 0, tokenNumber = 0;
			ArrayList<String> list2 = new ArrayList<String>();
			

			while( (fileName = br.readLine()) != null)
			{
				lineNumber++;

				//break comma separated line using ","
				st = new StringTokenizer(fileName, ",");
				

				while(st.hasMoreTokens())
				{
					//display csv values
					tokenNumber++;
					System.out.println("Line # " + lineNumber); //+
							//", Token # " + tokenNumber
							//+ ", Token : "+ st.nextToken());
					strLine = st.nextToken(); 
					//test1=regexChecker("([0-9]+.[0-9]+(?=[)]))|(?<==)[0-9]*.[0-9]*",strLine,out);	
					test1=regexChecker("([0-9]+.[0-9]+(?=[)]))",strLine,out);
					if(test1 != null)
					{
						list2.add(test1);
					}
					
				}
				
			
				
				//regexChecker("([0-9]*.[0-9]*(?=[)]))|(?<==)[0-9]*.[0-9]*",strLine);
				//reset token number
				tokenNumber = 0;
			}
			/*for(int i =0; i< list2.size();i++)
			{
				System.out.println("List2 " + list2.get(i));
			}*/
			/***************************************************************************/
			
			/***************************************************************************/
			out.close();
			
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	/*	
	*/	
	}
	
	/***************************************************************************************
	 *
	 * @param theRegex
	 * @param str2Check
	 * @param out 
	 * @throws FileNotFoundException 
	 ****************************************************************************************/
		public static String regexChecker(String theRegex, String str2Check, PrintWriter out) throws FileNotFoundException 
		{
			double quest = 0.0;
		// You define your regular expression (REGEX) using Pattern
		String regex = null;
		Pattern checkRegex = Pattern.compile(theRegex);
		
		
		// Creates a Matcher object that searches the String for
		// anything that matches the REGEX
		
		Matcher regexMatcher = checkRegex.matcher( str2Check );
		
		// Cycle through the positive matches and print them to screen
		// Make sure string isn't empty and trim off any whitespace
		int k =0;
		while ( regexMatcher.find() ){
			if (regexMatcher.group().length() != 0 && k<4)
			{
				//System.out.println(regexMatcher.group().trim());
				regex = regexMatcher.group().trim().toString();
				// You can get the starting and ending indexs
				System.out.println(regex);
				out.println(regex);
				
				System.out.println( "Start Index: " + regexMatcher.start());
				System.out.println( "Start Index: " + regexMatcher.end());
				
				connection(regex,k);
				
			}
		}
		System.out.println();
		
		return regex;
		}
		public static void connection(String regex,int k)
		{	
			String ques = regex;
			Connection conn = null;
			Statement stmt = null;
			ArrayList<String> list = new ArrayList<String>(); 
			 list.add("Ques");
			 list.add("nRed");
			 list.add("averageRed");
			 list.add("mdRed");
			try{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");

			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      System.out.println("Connected database successfully...");
			      
			      //STEP 4: Execute a query
			      System.out.println("Inserting records into the table...");
			      stmt = conn.createStatement();
			      
			      //String sql = "INSERT INTO women( Year,BCSCE,BNSF,MCSCE,MNSF,DCSCE,DNSF) " +
			                   //"VALUES('2022/23','20%','19%','15%','32%','11%','22%');";
			      //String sql = "LOAD DATA LOCAL INFILE \"/home/bo/workspace/492/output.txt\" INTO TABLE profile FIELDS TERMINATED BY '\\n'";
			     
			      //System.out.println(list.get(k));
			      String sql = "INSERT INTO profile("+list.get(k) +")" + "VALUES(" + ques +");";
			      stmt.executeUpdate(sql);
			      k++;
			     
			      
			      System.out.println("Inserted records into the table...");
			      
			   }catch(SQLException se)
			   {
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e)
			   {
			      //Handle errors for Class.forName
			      e.printStackTrace(); int i = 0;
			   }finally
			   {
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se)
			      {
			      }// do nothing
			      try
			      {
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se)
			      {
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   
			   System.out.println("Goodbye!");
		}
	
	
}