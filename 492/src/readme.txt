ROOM ASSIGNMENT INTERFACE
Step #1: Make sure you have MySQL, Make sure mysql database url is correct path
	
Step #2: Create a MySQL database using:
create table roomNum(CRN VARCHAR(5), Subj VARCHAR(4), Crse VARCHAR(3), Sec VARCHAR(3), Cmp VARCHAR(3), Cred VARCHAR(20),Part_of_Term  VARCHAR(30), Title VARCHAR(40), DayZ VARCHAR(10), TIME VARCHAR(20), Cap VARCHAR(3), Act VARCHAR(3), Rem VARCHAR(3), Instructor VARCHAR(40), DateZ VARCHAR(12), Location VARCHAR(10), Attribute VARCHAR(30));

Step #2a: Prep the text file for input
In sched.txt, remove top lines up until the data header row.
Remove any non data lines at the bottom of the file, including blank lines.

Step #2b: Make sure file paths are correct in code.

Step #3: Run automateImport only ONCE
Each time you run automateImport you will lose changes elsewhere.

Step #4: Set up triggers to check times don't overlap
delimiter //
create trigger roomNum_no_overlap_update
before update on roomNum
for each row
begin
  if exists (select * from roomNum
	     where ID != new.ID
	     and Location = new.Location
	     and DayZ = new.DayZ
             and Start_time <= new.End_time
             and End_time >= new.Start_time) then
    signal sqlstate '45000' SET MESSAGE_TEXT = 'Overlaps with existing data';
  end if;
end;//
delimiter ;

Step #5: Create the Logfile
CREATE TABLE roomNum_history LIKE roomNum;
ALTER TABLE roomNum_history MODIFY COLUMN ID int(11) NOT NULL, 
   DROP PRIMARY KEY, ENGINE = MyISAM, ADD action VARCHAR(8) DEFAULT 'insert' FIRST, 
   ADD revision INT(6) NOT NULL AUTO_INCREMENT AFTER action,
   ADD dt_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER revision,
   ADD PRIMARY KEY (ID, revision);
DROP TRIGGER IF EXISTS roomNum__ai;
DROP TRIGGER IF EXISTS roomNum__au;
DROP TRIGGER IF EXISTS roomNum__bd;
CREATE TRIGGER roomNum__ai AFTER INSERT ON roomNum FOR EACH ROW
    INSERT INTO roomNum_history SELECT 'insert', NULL, NOW(), d.* 
    FROM roomNum AS d WHERE ID = NEW.ID;

CREATE TRIGGER roomNum__au AFTER UPDATE ON roomNum FOR EACH ROW
    INSERT INTO roomNum_history SELECT 'update', NULL, NOW(), d.*
    FROM roomNum AS d WHERE d.ID = NEW.ID;

CREATE TRIGGER roomNum__bd BEFORE DELETE ON roomNum FOR EACH ROW
    INSERT INTO roomNum_history SELECT 'delete', NULL, NOW(), d.* 
    FROM roomNum AS d WHERE d.ID = OLD.ID;

Step #6: Run scheduler
You will be presented an interface. 
After choosing a location and hitting 'OK' the table will be visible and you can freely make changes and view class sections. 
Double click a cell you wish to edit and click elsewhere or press 'Enter' to finalize the change. 
If you want to add a class, you must include a unique Course Reference Number (CRN)
To delete a section, enter its unique CRN into the prompt and hit 'OK'.




STUDENT EVALUATIONS
Step #1: Create the table
create table profile(instructor VARCHAR(100),courseName VARCHAR(100),question VARCHAR(6),nRed INT,avRed FLOAT,mdRed FLOAT,devRed FLOAT,nBlue INT,avBlue FLOAT,mdBlue FLOAT,devBlue FLOAT,nGreen INT,avGreen FLOAT,mdGreen FLOAT,devGreen FLOAT);

Step #2: Prepare the input file by pruning it of unnecessary blank lines. Also some lines containing the headers of columns run together and must be separated. 
example:
Profile 

Subunit: Name of the instructor:  Engineering & Computing Robert Barton  
Name of the course: (Name of the survey)  General Applicat Programming (CSCE102-010-012)  
Comparative line: Compilation:  CSCE Profile Fall 2014  Comparative line: Compilation:  COLLEGE Fall 2014 ALL PROFILES  

Should Be:
Profile 

Subunit:  Engineering & Computing
Name of the instructor:  Robert Barton  
Name of the course: (Name of the survey)  General Applicat Programming (CSCE102-010-012)  
Comparative line: Compilation:  CSCE Profile Fall 2014  Comparative line: Compilation:  COLLEGE Fall 2014 ALL PROFILES  


Step #3: Run studentEval
This will transfer all the data into a MySQL database called 'profile' where it can easily be viewed and searched. 

