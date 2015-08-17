import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.regex.*;

public class LessonNineteen{
	
	
	public static void main(String[] args){
		
		String fileName="/home/bo/workspace/492/src/profile.txt";
		try 
		{
			BufferedReader br = new BufferedReader( new FileReader(fileName));
			PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
			String strLine = null;
			StringTokenizer st = null;
			int lineNumber = 0, tokenNumber = 0;
			
			

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
					String str = "Name of the instructor:  ";
					String name = "Profile";
					//regexChecker("(?<=instructor:  )[A-Z][a-z]*\\s[A-Z][a-z]*|([0-9]+.[0-9]+(?=[)]))|(?<==)[0-9]*.[0-9]*",strLine,out);	
					regexChecker("((^[(])[0-9]+.[0-9]+(?=[)]))|(?<==)[0-9]*.[0-9]*",strLine,out);//(?<=instructor:  )[A-Z][a-z]*\\s[A-Z][a-z]*|
					//regexChecker("[A-Z][a-z]*\\s[A-Z][a-z]*",strLine,out);
					//regexChecker("[A-Z][a-z]*",strLine,out);
					
				}
			
			
				//regexChecker("([0-9]*.[0-9]*(?=[)]))|(?<==)[0-9]*.[0-9]*",strLine);
				//reset token number
				tokenNumber = 0;
				
			}
			
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
		
	}
	
	/***************************************************************************************
	 *
	 * @param theRegex
	 * @param str2Check
	 * @param out 
	 * @throws FileNotFoundException 
	 ****************************************************************************************/
		public static void regexChecker(String theRegex, String str2Check, PrintWriter out) throws FileNotFoundException 
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
		
		while ( regexMatcher.find() )
		{	
			
			if (regexMatcher.group().length() != 0)
			{
				//System.out.println(regexMatcher.group().trim());
			
				regex = regexMatcher.group().trim().toString();
				//quest = Double.parseDouble(regex);
				// You can get the starting and ending indexs
				System.out.println(regex);
				out.println(regex);
				if(regex.equals("8.1") | regex.equals("8.2") | regex.equals("8.3") | regex.equals("8.4") | regex.equals("8.5") | regex.equals("8.6")| regex.equals("8.7") | regex.equals("8.8") | regex.equals("8.9") | regex.equals("8.10"))
				{
					for(int i = 0;i<8;i++)
					{
						out.println("null");
					}
				}
				
				
				
				//System.out.println( "Start Index: " + regexMatcher.start());
				//System.out.println( "Start Index: " + regexMatcher.end());
				
				//System.out.println("Question numbers are " +quest);
				
			}	
		}
		
		
		
		//System.out.println("QUEST IS " + quest);
	
	
		
		
	}
	
	public static void regexReplace(String str2Replace){
		
		// REGEX that matches 1 or more white space
		
		Pattern replace = Pattern.compile("\\s+");
		
		// This doesn't really apply, but this is how you ignore case
		// Pattern replace = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		
		// trim the string t prepare it for a replace
		
		Matcher regexMatcher = replace.matcher(str2Replace.trim());
		
		// replaceAll replaces all white space with commas
		
		System.out.println(regexMatcher.replaceAll(", "));
		
	}
	
}