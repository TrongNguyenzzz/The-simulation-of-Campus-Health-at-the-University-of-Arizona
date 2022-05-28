# Arizona Campus Health simulation 
Compile/Run:
    Log in to Lectura with the following command:
        ssh <netid>@lectura.cs.arizona.edu
    Enter your password when prompted.

    Add the Oracle JDBC driver to your CLASSPATH environment variable:
        export CLASSPATH=/usr/lib/oracle/19.8/client64/lib/ojdbc8.jar:${CLASSPATH}

    Log in to Oracle aloe with the following command:
        sqlpl <netid>@oracle.aloe
    Enter your password when prompted.

    -OPTIONAL- We will turn in the assignment with the tables 
    already populated, but if you want to test and see that our sql 
    scripts work, run with the following commands:
        @ dropTables.sql
        @ makeTables.sql
        @ Insert.sql
    
    Compile our JDBC program with the following command:
        javac Prog4.java
    Run our JDBC program with the following command:
        java Prog4 <Oracle Aloe Username> <Oracle Aloe Password>
    Follow prompts.
