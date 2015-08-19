/*
 * Cody Reiter, Bo Aye, John Phillips
 * File: studentEval.java
 * Description: Class dedicated to reading in and placing the data
 * 				from Student Evaluations into a MySQL database
 * Date last modified: 8/18/2015
 */

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

import javax.swing.JFrame;

public class studentEval 
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://L-1D39-492/roomAssign";

	//  Database credentials
	static final String USER = "java";
	static final String PASS = "mysql";
	
	public static void main(String[] args)
	{
		String test1;
		String fileName="/home/bo/workspace/492/src/profile.txt";
		try 
		{
			BufferedReader br = new BufferedReader( new FileReader(fileName));
			PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
			String strLine = null;
			String[] tempList = new String[15];
			String question = null;
			ArrayList<String> tempLine = new ArrayList<String>();
			Connection conn = null;
			Statement stmt = null;

			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				// Open a connection
				System.out.println("Connecting to a selected database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Connected database successfully...");

				// Execute a query
				System.out.println("Inserting records into the table...");
				stmt = conn.createStatement();
				String trun = "truncate profile";
			    stmt.executeUpdate(trun);
				
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
//			String convertValue1 = "ALTER TABLE profile MODIFY nRed VARCHAR(6),MODIFY avRed VARCHAR(6),MODIFY mdRed VARCHAR(6),MODIFY devRed VARCHAR(6),MODIFY nBlue VARCHAR(6),MODIFY avBlue VARCHAR(6),MODIFY mdBlue VARCHAR(6),MODIFY devBlue VARCHAR(6),MODIFY nGreen VARCHAR(6),MODIFY avGreen VARCHAR(6),MODIFY mdGreen VARCHAR(6),MODIFY devGreen VARCHAR(6);";
//			stmt.executeUpdate(convertValue1);
			while( (test1 = br.readLine()) != null)
			{	
				String instructorRegex = "(Name of the instructor: ) ([a-zA-Z]* [a-zA-Z]*)";//Sys						tem.out.println(
				String courseRegex = "(Name of the course: \\(Name of the survey\\) ) (.*)";
				String questionRegex = "([0-9]\\.[0-9]0?\\))";
				String nRegex = "(n=)([0-9]*) (av.=)([0-9]\\.[0-9]*) (md=)([0-9]\\.[0-9]*) (dev.=)([0-9]\\.[0-9]*)";
				//String avRegex = "(av=)([0-9]*)";

				//Gets the professor's name
				Pattern p = Pattern.compile(instructorRegex);
				Matcher m = p.matcher(test1);
				while (m.find()) 
				{
					//System.out.println(m.group(2));
					if(m.group(2) != null)
					{	
						tempList[0] = m.group(2);
					}	
				}			
				
				//Gets the course name
				p = Pattern.compile(courseRegex);
				m = p.matcher(test1);
				while (m.find()) 
				{
					//System.out.println(m.group(2));
					if(m.group(2) != null)
					{	
						tempList[1] = m.group(2);
					}	
				}
				//Gets the question number
				p = Pattern.compile(questionRegex);
				m = p.matcher(test1);
				while (m.find()) 
				{	
					if(m.group(1) != null)
					{	
						//System.out.println(m.group(1));
						tempList[2] = m.group(1);
					}	
				}
				//Gets the data and matches it to the correct column
				p = Pattern.compile(nRegex);
				m = p.matcher(test1);
				while (m.find()) 
				{
					//System.out.println(m.group(2));
					
					if(tempList[3] != null)
					{
						if(tempList[7] != null)
						{
							tempList[11] = m.group(2);
						}
						else
						{
							tempList[7] = m.group(2);
						}
					}
					else
					{
						tempList[3] = m.group(2);
					}
					
					//System.out.println(m.group(4));
					
					if(tempList[4] != null)
					{
						if(tempList[8] != null)
						{
							tempList[12] = m.group(4);
						}
						else
						{
							tempList[8]	= m.group(4);
						}
					}
					else
					{
						tempList[4] = m.group(4);
					}
					
					//System.out.println(m.group(6));
					
					if(tempList[5] != null)
					{
						if(tempList[9] != null)
						{
							tempList[13] = m.group(6);
						}
						else
						{
							tempList[9] = m.group(6);
						}
					}
					else
					{
						tempList[5] = m.group(6);
					}
					
					//System.out.println(m.group(8));
					
					if(tempList[6] != null)
					{
						if(tempList[10] != null)
						{
							tempList[14] = m.group(8);
						}
						else
						{
							tempList[10] = m.group(8);
						}
					}
					else
					{
						tempList[6] = m.group(8);
					}
				}
//				String sql = "INSERT INTO profile(instructor,courseName,question,nRed,avRed,mdRed,devRed,nBlue,avBlue,mdBlue,devBlue,nGreen,avGreen,mdGreen,devGreen) " +
//					 	 "VALUES('" + tempList[0] + "','" + tempList[1] + "','" + tempList[2] + "','" + tempList[3] + "','" + tempList[4] + "','" + tempList[5] + "','" + tempList[6] + "','" + tempList[7] + "','" + tempList[8] + "','" + tempList[9] + "','" + tempList[10] + "','" + tempList[11] + "','" + tempList[12] + "','" + tempList[13] + "','" + tempList[14]+"');";
			
				//Only do if array is full
				//If 8.*, only do if tempList[6]
				//Sql insert statement
				//Clear the array

				if(tempList[14] != null)
				{
					String sql = "INSERT INTO profile(instructor,courseName,question,nRed,avRed,mdRed,devRed,nBlue,avBlue,mdBlue,devBlue,nGreen,avGreen,mdGreen,devGreen) " +
							"VALUES('" + tempList[0] + "','" + tempList[1] + "','" + tempList[2] + "','" + Integer.parseInt(tempList[3]) + "','" + Float.parseFloat(tempList[4]) + "','" + Float.parseFloat(tempList[5]) + "','" + Float.parseFloat(tempList[6]) + "','" + Integer.parseInt(tempList[7]) + "','" + Float.parseFloat(tempList[8]) + "','" + Float.parseFloat(tempList[9]) + "','" + Float.parseFloat(tempList[10]) + "','" + Integer.parseInt(tempList[11]) + "','" + Float.parseFloat(tempList[12]) + "','" + Float.parseFloat(tempList[13]) + "','" + Float.parseFloat(tempList[14])+"');";
					stmt.executeUpdate(sql);
					for (int jk = 2; jk < 15; jk++)
					{
						tempList[jk] = null;
					}
					
				}
				if(tempList[3] != null)
				{
					if((tempList[2].equals("8.1)") | tempList[2].equals("8.2)") | tempList[2].equals("8.3)") | tempList[2].equals("8.4)") | tempList[2].equals("8.5)") | tempList[2].equals("8.6)") | tempList[2].equals("8.7)") | tempList[2].equals("8.8)") | tempList[2].equals("8.9)") | tempList[2].equals("8.10)")) && tempList[7] == null)
					{
						for (int umm = 3; umm < 15; umm++)
						{
							if(tempList[umm] == ("") | tempList[umm] == null)
							{
								tempList[umm] = "0";
							}
						}
						String sql = "INSERT INTO profile(instructor,courseName,question,nRed,avRed,mdRed,devRed,nBlue,avBlue,mdBlue,devBlue,nGreen,avGreen,mdGreen,devGreen) " +
								"VALUES('" + tempList[0] + "','" + tempList[1] + "','" + tempList[2] + "','" + Integer.parseInt(tempList[3]) + "','" + Float.parseFloat(tempList[4]) + "','" + Float.parseFloat(tempList[5]) + "','" + Float.parseFloat(tempList[6]) + "','" + Integer.parseInt(tempList[7]) + "','" + Float.parseFloat(tempList[8]) + "','" + Float.parseFloat(tempList[9]) + "','" + Float.parseFloat(tempList[10]) + "','" + Integer.parseInt(tempList[11]) + "','" + Float.parseFloat(tempList[12]) + "','" + Float.parseFloat(tempList[13]) + "','" + Float.parseFloat(tempList[14])+"');";
						stmt.executeUpdate(sql);
						for (int jk = 2; jk < 15; jk++)
						{
							tempList[jk] = null;
						}
					}
				}			
			}
//			String convertValue2 = "ALTER TABLE profile MODIFY nRed INTEGER,MODIFY avRed FLOAT,MODIFY mdRed FLOAT,MODIFY devRed FLOAT,MODIFY nBlue INTEGER,MODIFY avBlue FLOAT,MODIFY mdBlue FLOAT,MODIFY devBlue FLOAT,MODIFY nGreen INTEGER,MODIFY avGreen FLOAT,MODIFY mdGreen FLOAT,MODIFY devGreen FLOAT;";
//			stmt.executeUpdate(convertValue2);
//			for (int i = 0; i < 15; i++)
////			{
////				System.out.println(tempList[i]);
////			}
			System.out.println("Done");
			conn.close();
		}
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	} // main(String[])
} // studentEval