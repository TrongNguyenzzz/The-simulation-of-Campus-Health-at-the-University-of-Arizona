/*=================================================================================
|   Assignment:  Program 4: Database Design and Implementation
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
|  Description:  This program inserts all records from the 8 tables in prog4.
|   Techniques:  Use SQL's insert command.
*================================================================================*/

/* Insert Student */
INSERT INTO adlogan.student VALUES(1, 'Lionel Messi', TO_DATE('2000-05-05', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(2, 'Cristiano Ronaldo', TO_DATE('1999-12-06', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(3, 'Romelu Lukaku', TO_DATE('2001-03-23', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(4, 'Marco Reus', TO_DATE('2002-11-09', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(5, 'Eden Hazard', TO_DATE('1998-06-15', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(6, 'Karim Benzema', TO_DATE('2000-01-04', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(7, 'Sadio Mane', TO_DATE('2001-10-09', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(8, 'Sergio Ramos', TO_DATE('1999-07-20', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(9, 'David De Gea', TO_DATE('1999-04-05', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(10, 'Christian Pulisic', TO_DATE('2002-06-14', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(11, 'Luka Modric', TO_DATE('1999-12-01', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(12, 'Casemiro', TO_DATE('1998-06-07', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(13, 'Thomas Muller', TO_DATE('2000-10-12', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(14, 'Iker Casilas', TO_DATE('2001-06-13', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(15, 'Xavi', TO_DATE('1999-06-22', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(16, 'Iniesta', TO_DATE('1998-03-24', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(17, 'Roberto Firmino', TO_DATE('2000-08-30', 'yyyy-mm-dd'), 'Yes');
INSERT INTO adlogan.student VALUES(18, 'Gerard Pique', TO_DATE('1999-09-22', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(19, 'Ederson', TO_DATE('2002-03-04', 'yyyy-mm-dd'), 'No');
INSERT INTO adlogan.student VALUES(20, 'Kai Havertz', TO_DATE('2000-07-15', 'yyyy-mm-dd'), 'Yes');

/* Insert UEmployee */

/* Below 3 are also students */
INSERT INTO adlogan.uemployee VALUES(1, 'Lionel Messi', TO_DATE('2000-05-05', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(2, 'Cristiano Ronaldo', TO_DATE('1999-12-06', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(3, 'Romelu Lukaku', TO_DATE('2001-03-23', 'yyyy-mm-dd'));

INSERT INTO adlogan.uemployee VALUES(4, 'Zidance', TO_DATE('2002-11-09', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(5, 'Kaka', TO_DATE('1998-06-15', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(6, 'Pele', TO_DATE('2000-01-04', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(7, 'Maradona', TO_DATE('2001-10-09', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(8, 'Frank Lampard', TO_DATE('1999-07-20', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(9, 'John Terry', TO_DATE('1999-04-05', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(10, 'Rio Ferdinand', TO_DATE('2002-06-14', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(11, 'Didier Drogba', TO_DATE('1999-12-01', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(12, 'Sergio Busquets', TO_DATE('1998-06-07', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(13, 'Jorginho', TO_DATE('1950-10-12', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(14, 'Ngolo Kante', TO_DATE('2001-06-13', 'yyyy-mm-dd'));

/* Below 3 are also students */
INSERT INTO adlogan.uemployee VALUES(15, 'Gerard Pique', TO_DATE('1999-09-22', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(16, 'Ederson', TO_DATE('2002-03-04', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(17, 'Kai Havertz', TO_DATE('2000-07-15', 'yyyy-mm-dd'));

INSERT INTO adlogan.uemployee VALUES(18, 'Kevin De Bruyne', TO_DATE('1999-06-22', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(19, 'Paul Scholes', TO_DATE('1998-03-24', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(20, 'Gareth Bale', TO_DATE('2000-08-30', 'yyyy-mm-dd'));

INSERT INTO adlogan.uemployee VALUES(21, 'Ronaldinho', TO_DATE('1960-03-24', 'yyyy-mm-dd'));
INSERT INTO adlogan.uemployee VALUES(22, 'Kylian Mbappe', TO_DATE('2000-08-30', 'yyyy-mm-dd'));


/* Insert Patient */
INSERT INTO adlogan.patient VALUES(1, 'Karim Benzema', 6, NULL, TO_DATE('2000-01-04', 'yyyy-mm-dd'));
INSERT INTO adlogan.patient VALUES(2, 'Sadio Mane', 7, NULL, TO_DATE('2001-10-09', 'yyyy-mm-dd'));

INSERT INTO adlogan.patient VALUES(3, 'Lionel Messi', 1, 1, TO_DATE('2000-05-05', 'yyyy-mm-dd'));
INSERT INTO adlogan.patient VALUES(4, 'Cristiano Ronaldo', 2, 2, TO_DATE('1999-12-06', 'yyyy-mm-dd'));
INSERT INTO adlogan.patient VALUES(5, 'Romelu Lukaku', 3, 3, TO_DATE('2001-03-23', 'yyyy-mm-dd'));

INSERT INTO adlogan.patient VALUES(6, 'Jorginho', NULL, 13, TO_DATE('1950-10-12', 'yyyy-mm-dd'));
INSERT INTO adlogan.patient VALUES(7, 'Ngolo Kante', NULL, 14, TO_DATE('2001-06-13', 'yyyy-mm-dd'));

INSERT INTO adlogan.patient VALUES(8, 'Gerard Pique', 18, 15, TO_DATE('1999-09-22', 'yyyy-mm-dd'));
INSERT INTO adlogan.patient VALUES(9, 'Ederson', 19, 16, TO_DATE('2002-03-04', 'yyyy-mm-dd'));
INSERT INTO adlogan.patient VALUES(10, 'Kai Havertz', 20, 17, TO_DATE('2000-07-15', 'yyyy-mm-dd'));

INSERT INTO adlogan.patient VALUES(11, 'Ronaldinho', NULL, 21, TO_DATE('1960-03-24', 'yyyy-mm-dd'));
INSERT INTO adlogan.patient VALUES(12, 'Kylian Mbappe', NULL, 22, TO_DATE('2000-08-30', 'yyyy-mm-dd'));

/* Insert CHStaff */
INSERT INTO adlogan.CHStaff VALUES(1, 'Rio Ferdinand', 'General Medicine', 10);
INSERT INTO adlogan.CHStaff VALUES(2, 'Kaka', 'General Medicine', 5);
INSERT INTO adlogan.CHStaff VALUES(3, 'Didier Drogba', 'CAPS', 11);
INSERT INTO adlogan.CHStaff VALUES(4, 'Sergio Busquets', 'Immunization', 12);
INSERT INTO adlogan.CHStaff VALUES(5, 'Romelu Lukaku', 'CAPS', 3);
INSERT INTO adlogan.CHStaff VALUES(6, 'Paul Scholes', 'Immunization', 19);
INSERT INTO adlogan.CHStaff VALUES(7, 'Frank Lampard', 'Laboratory and Testing', 8);
INSERT INTO adlogan.CHStaff VALUES(8, 'John Terry', 'Immunization', 9);
INSERT INTO adlogan.CHStaff VALUES(9, 'Zidance', 'Immunization', 4);
INSERT INTO adlogan.CHStaff VALUES(10, 'Pele', 'Immunization', 6);
INSERT INTO adlogan.CHStaff VALUES(11, 'Maradona', 'Immunization', 7);

/* Insert Shift */
INSERT INTO adlogan.Shift VALUES(1, TO_TIMESTAMP ('2022-05-01 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-01 16:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(1, TO_TIMESTAMP ('2022-05-03 13:00:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-03 15:00:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(2, TO_TIMESTAMP ('2022-05-02 12:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(2, TO_TIMESTAMP ('2022-05-05 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-05 11:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(3, TO_TIMESTAMP ('2022-05-02 08:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 15:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(3, TO_TIMESTAMP ('2022-05-03 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-03 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(4, TO_TIMESTAMP ('2022-05-04 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-04 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(4, TO_TIMESTAMP ('2022-05-05 08:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-05 16:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(5, TO_TIMESTAMP ('2022-05-02 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(5, TO_TIMESTAMP ('2022-05-04 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-04 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(6, TO_TIMESTAMP ('2022-05-02 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(6, TO_TIMESTAMP ('2022-05-03 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-03 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(7, TO_TIMESTAMP ('2022-05-01 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-01 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(7, TO_TIMESTAMP ('2022-05-05 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-05 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(8, TO_TIMESTAMP ('2022-05-02 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(8, TO_TIMESTAMP ('2022-05-04 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-04 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(9, TO_TIMESTAMP ('2022-05-01 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-01 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(9, TO_TIMESTAMP ('2022-05-04 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-04 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO adlogan.Shift VALUES(10, TO_TIMESTAMP ('2022-05-01 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-01 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO adlogan.Shift VALUES(10, TO_TIMESTAMP ('2022-05-03 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-03 14:10:10', 'YYYY-MM-DD HH24:MI:SS'));

/* Insert VaxLog */
INSERT INTO adlogan.VaxLog VALUES(1, 6, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(2, 6, 'COVID-19', 2);
INSERT INTO adlogan.VaxLog VALUES(3, 6, 'COVID-19', 3);
INSERT INTO adlogan.VaxLog VALUES(4, 6, 'COVID-19', 4);

INSERT INTO adlogan.VaxLog VALUES(5, 2, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(6, 2, 'COVID-19', 2);
INSERT INTO adlogan.VaxLog VALUES(7, 2, 'COVID-19', 3);

INSERT INTO adlogan.VaxLog VALUES(8, 3, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(9, 3, 'COVID-19', 2);

INSERT INTO adlogan.VaxLog VALUES(10, 4, 'COVID-19', 1);

INSERT INTO adlogan.VaxLog VALUES(11, 5, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(12, 5, 'COVID-19', 2);
INSERT INTO adlogan.VaxLog VALUES(13, 5, 'COVID-19', 3);

INSERT INTO adlogan.VaxLog VALUES(14, 1, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(15, 1, 'COVID-19', 2);
INSERT INTO adlogan.VaxLog VALUES(16, 1, 'COVID-19', 3);

INSERT INTO adlogan.VaxLog VALUES(17, 7, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(18, 7, 'COVID-19', 2);

INSERT INTO adlogan.VaxLog VALUES(19, 11, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(20, 11, 'COVID-19', 2);
INSERT INTO adlogan.VaxLog VALUES(21, 11, 'COVID-19', 3);

INSERT INTO adlogan.VaxLog VALUES(22, 12, 'COVID-19', 1);
INSERT INTO adlogan.VaxLog VALUES(23, 12, 'COVID-19', 2);
INSERT INTO adlogan.VaxLog VALUES(24, 12, 'COVID-19', 3);

/* Insert Appointment */
INSERT INTO adlogan.Appointment VALUES(1, 6, TO_TIMESTAMP ('2022-03-15 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-15 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 9, 'No', 20.0, 'Complete', NULL, NULL, 1);
INSERT INTO adlogan.Appointment VALUES(2, 6, TO_TIMESTAMP ('2022-04-01 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-01 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 9, 'No', 20.0, 'Complete', NULL, NULL, 2);
INSERT INTO adlogan.Appointment VALUES(3, 6, TO_TIMESTAMP ('2022-04-15 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-15 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 9, 'No', 20.0, 'Complete', NULL, NULL, 3);
INSERT INTO adlogan.Appointment VALUES(4, 6, TO_TIMESTAMP ('2022-05-02 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 9, 'No', 20.0, 'Complete', NULL, NULL, 4);

INSERT INTO adlogan.Appointment VALUES(5, 2, TO_TIMESTAMP ('2022-03-16 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-16 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 5);
INSERT INTO adlogan.Appointment VALUES(6, 2, TO_TIMESTAMP ('2022-04-02 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-02 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 6);
INSERT INTO adlogan.Appointment VALUES(7, 2, TO_TIMESTAMP ('2022-04-16 13:10:10', 'YYYY-MM-DD HH24:MI:SS'),
TO_TIMESTAMP ('2022-04-16 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 7);

INSERT INTO adlogan.Appointment VALUES(8, 3, TO_TIMESTAMP ('2022-03-17 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-17 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 11, 'No', 20.0, 'Complete', NULL, NULL, 8);
INSERT INTO adlogan.Appointment VALUES(9, 3, TO_TIMESTAMP ('2022-04-03 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-03 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 11, 'No', 20.0, 'Complete', NULL, NULL, 9);

INSERT INTO adlogan.Appointment VALUES(10, 4, TO_TIMESTAMP ('2022-03-18 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-18 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 9, 'No', 20.0, 'Complete', NULL, NULL, 10);

INSERT INTO adlogan.Appointment VALUES(11, 5, TO_TIMESTAMP ('2022-03-19 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-19 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 11);
INSERT INTO adlogan.Appointment VALUES(12, 5, TO_TIMESTAMP ('2022-04-04 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-04 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 12);
INSERT INTO adlogan.Appointment VALUES(13, 5, TO_TIMESTAMP ('2022-04-17 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-17 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 13);

INSERT INTO adlogan.Appointment VALUES(14, 1, TO_TIMESTAMP ('2022-03-20 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-20 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 4, 'No', 20.0, 'Complete', NULL, NULL, 14);
INSERT INTO adlogan.Appointment VALUES(15, 1, TO_TIMESTAMP ('2022-04-05 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-05 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 4, 'No', 20.0, 'Complete', NULL, NULL, 15);
INSERT INTO adlogan.Appointment VALUES(16, 1, TO_TIMESTAMP ('2022-04-18 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-18 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 4, 'No', 20.0, 'Complete', NULL, NULL, 16);

INSERT INTO adlogan.Appointment VALUES(17, 7, TO_TIMESTAMP ('2022-03-21 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-21 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 11, 'No', 20.0, 'Complete', NULL, NULL, 17);
INSERT INTO adlogan.Appointment VALUES(18, 7, TO_TIMESTAMP ('2022-04-06 13:10:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-06 14:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 11, 'No', 20.0, 'Complete', NULL, NULL, 18);

INSERT INTO adlogan.Appointment VALUES(19, 8, TO_TIMESTAMP ('2022-05-02 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'General Medicine', 1, 'No', 20.0, 'Complete', NULL, NULL, NULL);
INSERT INTO adlogan.Appointment VALUES(20, 9, TO_TIMESTAMP ('2022-05-02 10:00:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 12:00:10', 'YYYY-MM-DD HH24:MI:SS'), 'CAPS', 5, 'No', 20.0, 'Complete', NULL, NULL, NULL);
INSERT INTO adlogan.Appointment VALUES(21, 10, TO_TIMESTAMP ('2022-05-02 12:00:20', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-05-02 14:00:20', 'YYYY-MM-DD HH24:MI:SS'), 'Laboratory and Testing', 7, 'No', 20.0, 'Complete', NULL, NULL, NULL);

INSERT INTO adlogan.Appointment VALUES(22, 11, TO_TIMESTAMP ('2022-03-22 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-22 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 19);
INSERT INTO adlogan.Appointment VALUES(23, 11, TO_TIMESTAMP ('2022-04-07 10:00:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-07 12:00:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Complete', NULL, NULL, 20);
INSERT INTO adlogan.Appointment VALUES(24, 11, TO_TIMESTAMP ('2022-04-19 12:00:20', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-19 14:00:20', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 10, 'No', 20.0, 'Incomplete', NULL, NULL, 21);

INSERT INTO adlogan.Appointment VALUES(25, 12, TO_TIMESTAMP ('2022-03-23 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-03-23 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 11, 'No', 20.0, 'Complete', NULL, NULL, 22);
INSERT INTO adlogan.Appointment VALUES(26, 12, TO_TIMESTAMP ('2022-04-08 10:00:10', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-08 12:00:10', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 11, 'No', 20.0, 'Complete', NULL, NULL, 23);
INSERT INTO adlogan.Appointment VALUES(27, 12, TO_TIMESTAMP ('2022-04-20 12:00:20', 'YYYY-MM-DD HH24:MI:SS'), 
TO_TIMESTAMP ('2022-04-20 14:00:20', 'YYYY-MM-DD HH24:MI:SS'), 'Immunization', 11, 'No', 20.0, 'Incomplete', NULL, NULL, 24);

/* Insert Bill */
INSERT INTO adlogan.Bill VALUES(1, 6, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(2, 6, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(3, 6, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(4, 6, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(5, 2, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(6, 2, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(7, 2, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(8, 3, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(9, 3, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(10, 4, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(11, 5, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(12, 5, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(13, 5, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(14, 1, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(15, 1, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(16, 1, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(17, 7, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(18, 7, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(19, 8, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(20, 9, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(21, 10, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(22, 8, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(23, 9, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(24, 10, 20.0, 0, 20.0);

INSERT INTO adlogan.Bill VALUES(25, 8, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(26, 9, 20.0, 0, 20.0);
INSERT INTO adlogan.Bill VALUES(27, 10, 20.0, 0, 20.0);