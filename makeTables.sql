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
|                   @ makeTables.sql
+-----------------------------------------------------------------------------------
|  Description:  This program creates the 8 tables for prog4. Each table is created
|                with the attributes shown in design.pdf and constraints designed
|                by the team. After the tables are created, select permissions are 
|                granted to the public and special permissions are granted to the
|                team members.
|   Techniques:  Attribute constraints are based on their actual necessity in a
|                Campus Health database. For example primary key attributes and 
|                attributes needed for calculations (like appointment cost) cannot
|                be null etc.
*================================================================================*/

CREATE TABLE Student (
   StudentID            INTEGER NOT NULL,
   FullName             varchar2(100) NOT NULL, -- Assuming all names are 100 characters or less
   BirthDate            DATE NOT NULL, 
   HasInsurance         varchar2(3) NOT NULL,
   PRIMARY KEY (StudentID),
   CONSTRAINT yes_or_no_hasinsurance
        CHECK (HasInsurance IN ('Yes', 'No'))
);

CREATE TABLE UEmployee (
    EmpID               INTEGER NOT NULL,
    FullName            varchar2(100) NOT NULL, -- Assuming all names are 100 characters or less
    BirthDate           DATE NOT NULL,
    PRIMARY KEY (EmpID)
);

CREATE TABLE Patient (
    PatientID           INTEGER NOT NULL, 
    FullName            varchar2(100) NOT NULL, -- Assuming all names are 100 characters or less
    StudentID           INTEGER NULL, -- can be null
    EmpID               INTEGER NULL, -- can be null
    BirthDate           DATE NOT NULL,
    PRIMARY KEY (PatientID),
    FOREIGN KEY (StudentID) REFERENCES Student
        ON DELETE CASCADE,
    FOREIGN KEY (EmpID) REFERENCES UEmployee
        ON DELETE CASCADE
);

CREATE TABLE VaxLog (
    VaxID               INTEGER NOT NULL, 
    PatientID           INTEGER NOT NULL, 
    VirusName           varchar2(100) NOT NULL, -- Assuming all viruses are 100 characters or less
    DoseNum             INTEGER NOT NULL, 
    PRIMARY KEY (VaxID),
    FOREIGN KEY (PatientID) REFERENCES Patient
        ON DELETE CASCADE
);

CREATE TABLE CHStaff (
    StaffID             INTEGER NOT NULL, 
    FullName            varchar2(100) NOT NULL, -- Assuming all names are 100 characters or less
    ServiceType         varchar2(30) NOT NULL, 
    EmpID               INTEGER NOT NULL,
    PRIMARY KEY (StaffID),
    FOREIGN KEY (EmpID) REFERENCES UEmployee
        ON DELETE CASCADE,
    CONSTRAINT valid_services 
        CHECK (ServiceType IN ('General Medicine', 'CAPS', 'Laboratory and Testing', 'Immunization'))
);

CREATE TABLE Shift (
    StaffID             INTEGER NOT NULL, 
    ShiftStart          TIMESTAMP NOT NULL,
    ShiftEnd            TIMESTAMP NOT NULL,
    PRIMARY KEY (StaffID, ShiftStart)
);

CREATE TABLE Bill (
    BillID              INTEGER NOT NULL,
    PatientID           INTEGER NOT NULL, 
    ApptFee             DECIMAL(10,2) NOT NULL,
    NoShowFee           DECIMAL(10,2) NOT NULL,
    Total               DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (BillID),
    FOREIGN KEY (PatientID) REFERENCES Patient
        ON DELETE CASCADE
);

CREATE TABLE Appointment (
    AptID               INTEGER NOT NULL,
    PatientID           INTEGER NOT NULL, 
    StartTime           TIMESTAMP NOT NULL, 
    EndTime             TIMESTAMP NOT NULL,
    ServiceType         varchar2(30) NOT NULL, 
    StaffID             INTEGER NOT NULL,
    WalkIn              varchar2(3) NOT NULL,
    Cost                DECIMAL(10,2) NOT NULL,
    CurStatus           varchar2(10) NOT NULL,
    CancelTime          TIMESTAMP NULL, -- can be null
    Severe              varchar2(3) NULL, -- can be null
    VaxID               INTEGER NULL, -- can be null
    PRIMARY KEY (AptID),
    FOREIGN KEY (PatientID) REFERENCES Patient
        ON DELETE CASCADE,   
    FOREIGN KEY (StaffID) REFERENCES CHStaff
        ON DELETE CASCADE,
    FOREIGN KEY (VaxID) REFERENCES VaxLog
        ON DELETE CASCADE,
    CONSTRAINT valid_services_appointment 
        CHECK (ServiceType IN ('General Medicine', 'CAPS', 'Laboratory and Testing', 'Immunization')),
    CONSTRAINT yes_or_no_walkin
        CHECK (WalkIn IN ('Yes', 'No')),
    CONSTRAINT yes_or_no_severe
        CHECK (Severe IN ('Yes', 'No', NULL)),
    CONSTRAINT valid_status_appointment
        CHECK (CurStatus IN ('Complete', 'Incomplete', 'Cancelled', 'No Show')) 
);

GRANT ALL PRIVILEGES ON Patient     TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;
GRANT ALL PRIVILEGES ON Appointment TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;
GRANT ALL PRIVILEGES ON Student     TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;
GRANT ALL PRIVILEGES ON UEmployee   TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;
GRANT ALL PRIVILEGES ON VaxLog      TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;
GRANT ALL PRIVILEGES ON CHStaff     TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;
GRANT ALL PRIVILEGES ON Shift       TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;
GRANT ALL PRIVILEGES ON Bill        TO danenorville, anhnguyenphung, trongnguyen, hriaz, aayushpinto;