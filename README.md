# lyles_sutherland_challenge
This is my proposed solution for the LylesSutherland graduate software developer challenge.

To run in eclipse:

Open a workspace and select to import a project.
In the import options: https://i.imgur.com/EGnZHV4.png
Select 'select archive file,' and select the .zip from this repository. Then select 'Finish.'

To run tests:

There are two test files: 'BookingTests.java' and 'ChallengeTests.java'. You can run these by selecting them in the package explorer and clicking the play icon or py pressing F11
https://i.imgur.com/T37VFhj.png

To use the API:

Open Challenge.java in the package explorer:
Select 'run configurations' https://i.imgur.com/plNlMMX.png

Run as a java application: https://i.imgur.com/UVa2LkF.png
Under arguments enter the file location of the input csv file, 5 are provided with the java files. https://i.imgur.com/dnUFsYl.png
Select 'apply' and 'run'

Output is generated in results.txt

Possible extensions to the application:

Take in the number of available employees as an argument and solve their timetables appropriately, or output the required number of employees required to accommodate all customers moving in, and output the associated timetables. 

Allow for 'move-ins' to be done at other times than the half hour increments, as well as include a way to calculate the time required to travel to different locations (weighted graph) to make more efficient use of employee time. 

Perform analytics for the reasons why bookings have to be rescheduled, I included an attribute in the Booking class for this purpose when finding what bookings occur on the last day of the month. 
