# student-wellness-m2
Desktop Application - Wellness Management System
--DB CONNECTION
To run the app:
Make sure derby.jar is included in the lib/ folder and added to project libraries. Derby Network Server must be running before launching the app.

--CREATE TABLES
Run CreateTables script

--ADD DUMMY DATA
Run DatabaseInitialiser script

--TO RUN CREATETABLES SCRIPT IN CMND PROMPT:
(cd student-wellness-appm2\src)
WHEN IN SRC FOLDER OF PROJECT DIR - TO RECOMPILE:
javac -cp ../lib/derby.jar student/wellness/m2/*.java
THEN:
java -cp ".;../lib/derby.jar" student.wellness.m2.CreateTables

--TO RUN DATABASEINITIALISER SCRIPT FROM CMND PROMPT:
(cd student-wellness-appm2\src)
WHEN IN SRC FOLDER OF PROJECT DIR - TO RECOMPILE:
javac -cp ../lib/derby.jar student/wellness/m2/*.java
THEN:
java -cp ".;../lib/derby.jar" student.wellness.m2.DatabaseInitialiser