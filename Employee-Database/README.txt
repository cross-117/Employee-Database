DIRECTIONS FOR RUNNING empmain

Directions for adding .jar file and setting up database
.jar file and SQL lite executable is in project folder

for .jar file
project tab --> properties --> java build path(selection on left) --> Add External JARs(button on right)
When it brings up on the file browser, chose the program project and find the sqlite-jdbc-3.16.1.jar file and click OK

for setting up the database
once you write to the database and you have properly installed(run executable application in program folder) application
open the application and click the little database icon with the green plus sign(or ctrl O) press the green plus sign
for find file(after you've written to file from program) and you are now able to view the sql database that you just 
created with the eclipse program.

1. Copy over ALL java classes into new java project in eclipse
2. txt file should be copied in Referenced Libraries
3. txt file(I will include 2 different txt files to run and check out)
4. run program, chose option 2 or 3, read txt file using scanner or bufferedReader
this option will input txt file data into program data structures.
5. Options now, you may add employee(1), list employees(7), write employees to database(5), 
generate payroll(8) or clear data structures(9)
6. IMPORTANT after every 'add employee' you must write to employee file
or write to database AND clear data structures or it will double every employee in your data structures.


IMPORTANT: employee text file must be named 'xyzEmployees.txt'