/*=================================================================================
|   Assignment:  Program 4
|      Authors:  Andrew Logan adlogan, Trong Nguyen trongnguyen,
|                Dane Norville danenorville, Anh Phung anhnguyenphung
|       Course:  CSC460 Database Design, Spring 2022
|   Instructor:  Dr. McCann
| Sect. Leader:  TAs: Aayush Bernard Pinto and Haris Riaz
|     Due Date:  Mon May 2, 12:30pm, at the beginning of class
|     Language:  SQL (Oracle aloe)
|  Compile/Run:  In the Oracle aloe driver run with
|                   @ dropTables.sql
+-----------------------------------------------------------------------------------
|  Description:  This program delete all records from the 8 tables in prog4. This
|       program's main use is to allow the team to rerun makeTables.sql with a 
|       blank slate.
|   Techniques:  Use SQL's delete command.
*================================================================================*/

DELETE FROM adlogan.Patient;
DELETE FROM adlogan.Appointment;
DELETE FROM adlogan.Student;
DELETE FROM adlogan.UEmployee;
DELETE FROM adlogan.VaxLog;
DELETE FROM adlogan.CHStaff;
DELETE FROM adlogan.Shift;
DELETE FROM adlogan.Bill;