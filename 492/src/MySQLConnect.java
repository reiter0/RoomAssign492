
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;


public class MySQLConnect {

/**
*
* @param args
*/
public static void main(String[] args) {
// TODO Auto-generated method stub
String fileName="/home/bo/workspace/492/src/test.txt";
try {
BufferedReader br = new BufferedReader( new FileReader(fileName));
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
System.out.println("Line # " + lineNumber +
", Token # " + tokenNumber
+ ", Token : "+ st.nextToken());
}

//reset token number
tokenNumber = 0;

}
}




catch (FileNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

} 