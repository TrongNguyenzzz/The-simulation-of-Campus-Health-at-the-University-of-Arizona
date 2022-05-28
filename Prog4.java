/*=============================================================================
 |   Assignment:  Program #4: Database Design and Implementation
 |       Author:  Anh Nguyen Phung (anhnguyenphung@email.arizona.edu)
 |				  Trong Nguyen
 |
 |       Grader:  Haris Riaz, Aayush Pinto
 |
 |       Course:  CSC 460 - Spring 22
 |   Instructor:  L. McCann
 |     Due Date:  May 2, 2022, at the beginning of class
 |
 |  Description:
 |	  Algorithm:  None
 |
 |     Language:  Java, Version 16 or higher
 | Ex. Packages:  None
 |  Requirement:  Oracle JDBC driver needed to be added to the CLASSPATH
 |	environment variable every time logging in to lectura. The program needed
 |	to be run on lectura.
 |
 | Deficiencies:  None
 *===========================================================================*/

import java.sql.*; // For access to the SQL interaction methods
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Prog4 {

	public static void main(String[] args) {
		// This is the declaration of the String queriesMenu which gives the
		// users options to choose between 4 possible queries.
		final String welcomeMsg = "Welcome to the database, which option do "
			+ "you want to do? \nPLease type 0 to stop the service, type 1 "
			+ "to work with the current queries and type 2 for Record "
			+ "Operations!";

		final String oracleURL = // Magic lectura -> aloe access spell
			"jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";

		String username = null, // Oracle DBMS username
			password = null; // Oracle DBMS password

		if (args.length == 2) { // get username/password from cmd line args
			username = args[0];
			password = args[1];
		} else {
			System.out.println("\nUsage:  java JDBC <username> <password>\n"
				+ "    where <username> is your Oracle DBMS"
				+ " username,\n    and <password> is your Oracle"
				+ " password (not your system password).\n");
			System.exit(-1);
		}

		// load the (Oracle) JDBC driver by initializing its base
		// class, 'oracle.jdbc.OracleDriver'.

		try {

			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.err.println("*** ClassNotFoundException:  "
				+ "Error loading Oracle JDBC driver.  \n"
				+ "\tPerhaps the driver is not on the Classpath?");
			System.exit(-1);

		}

		// make and return a database connection to the user's
		// Oracle database

		Connection dbconn = null;

		try {
			dbconn = DriverManager.getConnection(oracleURL, username, password);

		} catch (SQLException e) {

			System.err.println(
				"*** SQLException:  " + "Could not open JDBC connection.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

		// Send the query to the DBMS, and get and display the results

		// Start doing the query
		// Print out the menu of possible queries and wait for input
		// from the users

		Scanner readInput = new Scanner(System.in);
		;

		Statement stmt = null;
		ResultSet answer = null;

		try {
			System.out.println();
			System.out.println(welcomeMsg);
			String input = readInput.nextLine();
			int bug = 0;

			// Type 0 to stop the database working
			// Type 1 to do the queries
			// Type 2 to do the operations (Insert, delete, update)

			while (!input.equals("0") && !input.equals("1")
				&& !input.equals("2")) {
				System.out.println(
					"Please re-enter the valid number " + "from 0 - 2");
				input = readInput.nextLine();
			}
			while (!input.equals("0")) { // If the users don't want to stop
				if (input.equals("1")) { // If users want to do queries
					System.out.println("You want to do the query options. "
						+ "Which query do you want to do?");
					String inputQuery = readInput.nextLine();

					// After the users enter the query that they want
					// Check for valid query since we have 5 queries only

					while (Integer.valueOf(inputQuery) > 5
						|| Integer.valueOf(inputQuery) < 1) {
						System.out.println(
							"Please enter valid query " + "from 1 - 5");
						inputQuery = readInput.nextLine();
					}

					if (inputQuery.equals("1")) {
						// Check for the input from the users and see if
						// it is valid
						System.out.println("Please enter your desired date: ");
						String dateTime = readInput.nextLine();
						while (dateValidation(dateTime) == false) {
							System.out.println("Please enter date in valid "
								+ "form (YYYY-MM-DD): ");
							dateTime = readInput.nextLine();
						}

						System.out.println("The chosen date: " + dateTime);

						System.out.println("This the result for query 1");
						handleQuery1(dbconn, dateTime);
					}
					if (inputQuery.equals("2")) {
						// Check for the input from the users and see if
						// it is valid
						System.out.println("Please enter your desired date: ");
						String dateTime = readInput.nextLine();
						while (dateValidation(dateTime) == false) {
							System.out.println("Please enter date in valid "
								+ "form (YYYY-MM-DD): ");
							dateTime = readInput.nextLine();
						}

						System.out.println("The chosen date: " + dateTime);

						System.out.println("This is the result for query 2");
						handleQuery2(dbconn, dateTime);
					}
					if (inputQuery.equals("3")) {
						// Check for the input from the users and see if
						//it is valid
						System.out.println("Please enter your desired date: ");
						String dateTime = readInput.nextLine();
						while (dateValidation(dateTime) == false) {
							System.out.println("Please enter date in valid "
								+ "form (YYYY-MM-DD): ");
							dateTime = readInput.nextLine();
						}

						System.out.println("The chosen date: " + dateTime);

						System.out.println("This is the result for query 3");
						handleQuery3(dbconn, dateTime);
					}
					if (inputQuery.equals("4")) {
						System.out.println("This is the result for query 4");
						handleQuery4(dbconn);
					}
					if (inputQuery.equals("5")) {
						// Check for the input from the users and see if
						// it is valid
						System.out.println("Please enter dose number of "
							+ "COVID-19 (1, 2, 3, or 4): ");
						String doseNumStr = readInput.nextLine();
						while (doseNumValidation(doseNumStr) == false) {
							System.out.println("Please enter date in valid "
								+ "form (YYYY-MM-DD): ");
							doseNumStr = readInput.nextLine();
						}
						System.out.println("This is the result for query 5");
						handleQuery5(dbconn, Integer.valueOf(doseNumStr));
					}
				}
				if (input.equals("2")) {
					System.out.println("This is the Record operations options."
						+ " Which operations do you want to do? \n"
						+ "Type 1 for Insertion \n" + "Type 2 for Deletion \n"
						+ "Type 3 for Update \n");

					// Receive the input from users for which operations t
					// hey want
					String inputOperation = readInput.nextLine();

					// Checking if the users type in the right input (only
					// from 1 - 3)
					while (Integer.valueOf(inputOperation) < 1
						|| Integer.valueOf(inputOperation) > 3) {
						System.out.println("Hey please only choose operations "
							+ "from 1 - 3");
						inputOperation = readInput.nextLine();
					}

					// Check for the operation input from the users
					if (inputOperation.equals("1")) {

						// Get the studentID and employeeID from the users
						System.out.println("Please enter your studentID (if "
							+ "not applicable then please enter -1):  ");
						String studentIdInput = readInput.nextLine();
						// Check valid input from user
						while (!isNumeric(studentIdInput)) {
							System.out.println(
								"Please enter a valid studentID (if not "
									+ "applicable then please enter -1):  ");
							studentIdInput = readInput.nextLine();
						}

						System.out.println("Please enter your employeeID (if "
							+ "not applicable then please enter -1):  ");
						String employeeIdInput = readInput.nextLine();
						while (!isNumeric(employeeIdInput)) {
							System.out.println(
								"Please enter a valid employeeID (if not "
									+ "applicable then please enter -1):  ");
							employeeIdInput = readInput.nextLine();
						}

						System.out
							.println("Please enter which type of data that you "
								+ "want to insert\nType 1 to Add Patient\n"
								+ "Type 2 to Add Employee\nType 3 to Add "
								+ "Appointment\n");
						String insertType = readInput.nextLine();

						if (Integer.valueOf(insertType) == 1) {
							// The users want to insert patient
							handleInsertPatient(dbconn, stmt, answer,
								studentIdInput, employeeIdInput);

						}

						else if (Integer.valueOf(insertType) == 2) {
							// The user want to insert employee

							// Receive employee's name
							System.out.println(
								"Please enter the employee's " + "full name: ");
							String employeeName = readInput.nextLine();

							// Receive employee's date of birth
							System.out.println("Please enter the employee's "
								+ "birthdate in this form (YYYY-MM-DD): ");
							String employeeBirth = readInput.nextLine();

							while (!dateValidation(employeeBirth)) {
								System.out.println("Please enter the valid "
									+ "birthdate for the employee in this "
									+ "form (YYYY-MM-DD): ");
								employeeBirth = readInput.nextLine();
							}
							handleInsertEmployee(dbconn, stmt, answer,
								employeeName, employeeBirth);
						}

						else if (Integer.valueOf(insertType) == 3) {
							// The users want to insert an appointment

							// Receive input of the appt from the users
							System.out.println(
								"Please enter the patient's " + "ID: ");
							String patientID = readInput.nextLine();
							System.out.println("Please enter your desired "
								+ "date in this format (YYYY-MM-DD): ");
							String date = readInput.nextLine();
							// Validate the date
							while (!dateValidation(date)) {
								System.out.println("Please enter the valid "
									+ "date in the correct format (YYYY-MM-DD):");
								date = readInput.nextLine();
							}

							// Get start time and end time from the users
							System.out.println("Please enter your start time "
								+ "in this format(HH:MM:SS):");
							String start = readInput.nextLine();
							while (!validTime(start)) {
								System.out.println("Please re-enter your "
									+ "start time in this format(HH:MM:SS):");
								start = readInput.nextLine();
							}
							System.out.println("Please enter your end time "
								+ "is this format(HH:MM:SS):");
							String end = readInput.nextLine();
							while (!validTime(end)) {
								System.out.println("Please re-enter your end "
									+ "time in this format(HH:MM:SS):");
								end = readInput.nextLine();
							}

							while (validStartEnd(end, start) != 1) {
								System.out.println("Your end time is after "
									+ "the start time. Please re-enter "
									+ "valid time!");
								System.out.println("Please re-enter your "
									+ "start time in this format(HH:MM:SS):");
								start = readInput.nextLine();
								while (!validTime(start)) {
									System.out.println("Please re-enter your "
										+ "start time in this format(HH:MM:SS):");
									start = readInput.nextLine();
								}
								System.out.println("Please enter your end "
									+ "time is this format(HH:MM:SS):");
								end = readInput.nextLine();
								while (!validTime(end)) {
									System.out.println("Please re-enter your "
										+ "end time in this format(HH:MM:SS):");
									end = readInput.nextLine();
								}
							}

							System.out.println("Please enter your service "
								+ "type (One of 4 options below): \n"
								+ "General Medicine\n" + "CAPS\n"
								+ "Laboratory and Testing\n" + "Immunization");
							String serviceType = readInput.nextLine();
							while (!serviceType.equals("General Medicine")
								&& !serviceType.equals("CAPS")
								&& !serviceType.equals("Laboratory and Testing")
								&& !serviceType.equals("Immunization")) {
								System.out.println(
									"Please enter a valid " + "service type: ");
								serviceType = readInput.nextLine();
							}

							// Get the virus name if applicable
							String virusName = "NULL";
							if (serviceType.equals("Immunization")) {
								System.out.println(
									"Please enter the name " + "of virus: ");
								virusName = readInput.nextLine();
							}

							// Receive Servere
							System.out.println("Please enter 1 if the "
								+ "patient has severe illness, otherwise 0: ");
							String severe = readInput.nextLine();
							while ((Integer.valueOf(severe) != 0)
								&& (Integer.valueOf(severe) != 1)) {
								System.out.println("Please enter only 1 "
									+ "or 0 for severe illness: ");
								severe = readInput.nextLine();
							}

							handleInsertAppt(dbconn, stmt, answer, patientID,
								date, start, end, serviceType, severe,
								virusName);

						}
					}

					if (inputOperation.equals("2")) {
						System.out.println(
							"Which type of data that you want to delete?\n"
								+ "Type 1 to delete Patient\n"
								+ "Type 2 to delete Employee\n"
								+ "Type 3 to delete Appointment");

						String dataDelete = readInput.nextLine();
						while (Integer.valueOf(dataDelete) < 1
							|| Integer.valueOf(dataDelete) > 3) {
							System.out.println(
								"Please only enter type of data you want to "
								+ "delete from 1-3!");
							dataDelete = readInput.nextLine();
						}

						if (Integer.valueOf(dataDelete) == 1) {
							// Receive ID of Patient from users
							System.out.println(
								"Please enter the Patient's ID that you want "
								+ "to delete: ");
							String patientID = readInput.nextLine();
							while (!isInteger(patientID)) {
								System.out.println(
									"Please re-enter a valid Patient's ID "
									+ "that you want to delete: ");
								patientID = readInput.nextLine();
							}
							handleDeletePatient(dbconn,
								Integer.valueOf(patientID));
						} else if (Integer.valueOf(dataDelete) == 2) {
							// Receive ID of Employee from users
							System.out.println(
								"Please enter the Employee's ID that you "
								+ "want to delete: ");
							String employeeID = readInput.nextLine();
							while (!isInteger(employeeID)) {
								System.out.println(
									"Please re-enter a valid Employee's ID "
									+ "that you want to delete: ");
								employeeID = readInput.nextLine();
							}
							handleDeleteEmployee(dbconn,
								Integer.valueOf(employeeID));
						} else if (Integer.valueOf(dataDelete) == 3) {
							// Receive ID of Appointment from users
							System.out.println(
								"Please enter the Appointment's ID that you "
								+ "want to delete: ");
							String aptID = readInput.nextLine();
							while (!isInteger(aptID)) {
								System.out.println(
									"Please re-enter a valid Appointment's ID "
									+ "that you want to delete: ");
								aptID = readInput.nextLine();
							}
							handleDeleteAppointment(dbconn,
								Integer.valueOf(aptID));
						}
						System.out.println("Deletion operation is completed!");

					}
					if (inputOperation.equals("3")) {
						System.out.println(
							"Which type of data that you want to update?\n"
								+ "Type 1 to update Patient\n"
								+ "Type 2 to update Employee\n"
								+ "Type 3 to update Appointment\n"
								+ "Type 4 to update service type of a staff "
								+ "member\n");

						String dataUpdate = readInput.nextLine();
						while (Integer.valueOf(dataUpdate) < 1
							|| Integer.valueOf(dataUpdate) > 4) {
							System.out.println(
								"Please only enter type of data you want to "
								+ "update from 1-4!");
							dataUpdate = readInput.nextLine();
						}
						if (Integer.valueOf(dataUpdate) == 1) {
							// Receive ID of Employee from users
							System.out.println(
								"Please enter the Patient's ID that you want "
								+ "to update: ");
							String patientID = readInput.nextLine();

							// Receive employee's name
							System.out.println(
								"Please enter the updated patient's full "
								+ "name: ");
							String patientName = readInput.nextLine();

							// Receive employee's date of birth
							System.out.println(
								"Please enter the updated patient's birthdate "
								+ "in this form (YYYY-MM-DD): ");
							String patientBirth = readInput.nextLine();

							while (!dateValidation(patientBirth)) {
								System.out.println(
									"Please enter the valid birthdate for the "
									+ "employee in this form (YYYY-MM-DD): ");
								patientBirth = readInput.nextLine();
							}
							updatePatient(dbconn, stmt, answer, patientID,
								patientName, patientBirth);
						} else if (Integer.valueOf(dataUpdate) == 2) {
							// Receive ID of Employee from users
							System.out.println(
								"Please enter the Employee's ID that you want "
								+ "to update: ");
							String employeeID = readInput.nextLine();

							// Receive employee's name
							System.out.println(
								"Please enter the updated employee's full "
								+ "name: ");
							String employeeName = readInput.nextLine();

							// Receive employee's date of birth
							System.out.println(
								"Please enter the updated employee's "
								+ "birthdate in this form (YYYY-MM-DD): ");
							String employeeBirth = readInput.nextLine();

							while (!dateValidation(employeeBirth)) {
								System.out.println(
									"Please enter the valid birthdate for "
									+ "the employee in this"
									+ " form (YYYY-MM-DD): ");
								employeeBirth = readInput.nextLine();
							}

							updateEmployee(dbconn, stmt, answer, employeeID,
								employeeName, employeeBirth);
						} else if (Integer.valueOf(dataUpdate) == 3) {

							// Receive input of the appt from the users
							System.out
								.println("Please enter the appointment's "
									+ "ID: ");
							String aptID = readInput.nextLine();
							System.out.println(
								"Please enter your desired date in this "
								+ "format (YYYY-MM-DD): ");
							String date = readInput.nextLine();
							// Validate the date
							while (!dateValidation(date)) {
								System.out.println(
									"Please enter the valid date in the "
									+ "correct format (YYYY-MM-DD):");
								date = readInput.nextLine();
							}

							// Get start time and end time from the users
							System.out.println(
								"Please enter your updated start time in "
								+ "this format(HH:MM:SS):");
							String start = readInput.nextLine();
							while (!validTime(start)) {
								System.out.println(
									"Please re-enter your updated start "
									+ "time in this format(HH:MM:SS):");
								start = readInput.nextLine();
							}
							System.out.println(
								"Please enter your updated end time is "
								+ "this format(HH:MM:SS):");
							String end = readInput.nextLine();
							while (!validTime(end)) {
								System.out.println(
									"Please re-enter your updated end time "
									+ "in this format(HH:MM:SS):");
								end = readInput.nextLine();
							}

							while (validStartEnd(end, start) != 1) {
								System.out.println(
									"Your end time is after the start time. "
									+ "Please re-enter valid time!");
								System.out.println(
									"Please re-enter your start time in this "
									+ "format(HH:MM:SS):");
								start = readInput.nextLine();
								while (!validTime(start)) {
									System.out.println(
										"Please re-enter your start time in "
										+ "this format(HH:MM:SS):");
									start = readInput.nextLine();
								}
								System.out.println(
									"Please enter your end time is this "
									+ "format(HH:MM:SS):");
								end = readInput.nextLine();
								while (!validTime(end)) {
									System.out.println(
										"Please re-enter your end time in "
										+ "this format(HH:MM:SS):");
									end = readInput.nextLine();
								}
							}

							System.out.println(
								"Please enter your service type (One of 4 "
									+ "options below): \n"
									+ "General Medicine\n" + "CAPS\n"
									+ "Laboratory and Testing\n"
									+ "Immunization");
							String serviceType = readInput.nextLine();
							while (!serviceType.equals("General Medicine")
								&& !serviceType.equals("CAPS")
							&& !serviceType.equals("Laboratory and Testing")
								&& !serviceType.equals("Immunization")) {
								System.out.println(
									"Please enter a valid service type: ");
								serviceType = readInput.nextLine();
							}

							// Receive Servere
							System.out.println(
								"Please enter 1 if the patient has severe "
								+ "illness, otherwise 0: ");
							String severe = readInput.nextLine();
							while ((Integer.valueOf(severe) != 0)
								&& (Integer.valueOf(severe) != 1)) {
								System.out.println(
									"Please enter only 1 or 0 for severe "
									+ "illness: ");
								severe = readInput.nextLine();
							}

							// Current status
							System.out.println(
								"Please enter the current status of the "
									+ "appointment: \n"
									+ "1 for Complete\n" + "2 for Incomplete\n"
									+ "3 for Cancelled");
							String curr_status = readInput.nextLine();
							while (Integer.valueOf(curr_status) < 1
								|| Integer.valueOf(curr_status) > 3) {
								System.out.println(
									"Please indicate the valid current status "
									+ "from 1-3!");
								curr_status = readInput.nextLine();
							}
							updateAppointment(dbconn, stmt, answer, aptID,
								date, start, end, serviceType, severe,
								Integer.valueOf(curr_status), readInput);
						} else {

							// Receive ID of Staff from users
							System.out.println(
								"Please enter the Staff's ID that you want"
								+ " to update: ");
							String staffID = readInput.nextLine();
							while (!isInteger(staffID)) {
								System.out.println(
									"Please re-enter a valid Staff's ID that "
									+ "you want to update: ");
								staffID = readInput.nextLine();
							}
							System.out.println(
								"Please enter the service type you want to "
								+ "update (One of 4 options below): \n"
									+ "General Medicine\n" + "CAPS\n"
									+ "Laboratory and Testing\n"
									+ "Immunization");
							String serviceType = readInput.nextLine();
							while (!serviceType.equals("General Medicine")
								&& !serviceType.equals("CAPS")
							&& !serviceType.equals("Laboratory and Testing")
								&& !serviceType.equals("Immunization")) {
								System.out.println(
									"Please enter a valid service type: ");
								serviceType = readInput.nextLine();
							}

							handleUpdateService(dbconn,
								Integer.valueOf(staffID), serviceType);
						}

					}
				}

				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println(welcomeMsg);
				input = readInput.nextLine();
			}

			System.out.println();

			// Shut down the connection to the DBMS.
			if (bug == 1) {
				stmt.close();
			}
			dbconn.close();

		} catch (SQLException e) {
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleInsertPatient
	|
	|  Purpose: Handle insert a patient into the database.
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: Insert operation functions properly
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	studentID -- a string representing the student ID
	|	employeeID -- a string representing the employee ID
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleInsertPatient(Connection dbconn, Statement stmt,
		ResultSet answer, String studentID, String employeeID) {
		// Check if patient is valid with correct student id and employee id
		String studentName = "0";
		String employeeName = "0";
		int flag = 0;
		if (!studentID.equals("-1") && !employeeID.equals("-1")) {
			// If the paitent is student then check if exists on Student table
			// Get the name of the student/patient
			String queryStdName = "SELECT FULLNAME FROM adlogan.Student "
				+ "WHERE StudentID = " + studentID;
			String queryEmpName = "SELECT FULLNAME FROM adlogan.UEmployee "
				+ "WHERE EmpID = " + employeeID;
			try {
				int findFlag = 0;
				stmt = dbconn.createStatement();
				answer = stmt.executeQuery(queryStdName);
				if (answer != null) {
					while (answer.next()) {
						findFlag += 1;
						studentName = answer.getString("FULLNAME");
					}
				}
				answer = stmt.executeQuery(queryEmpName);
				if (answer != null) {
					while (answer.next()) {
						findFlag += 1;
						employeeName = answer.getString("FULLNAME");
					}
				}

				if (!studentName.equals(employeeName) || findFlag == 0) {
					System.out.println(
						"Your studentID and EmployeeID does not match! "
						+ "Please re-enter the valid name!");
					flag = 1;
				}

				// if the name matchs then insert the patient to database
				// String queryAdd = "INSERT INTO adlogan.Patient VALUES("
				else {
					if (flag != 1 && findFlag != 0) {
						String queryGetMax = "SELECT MAX(PATIENTID) "
							+ "FROM adlogan.Patient";
						String queryGetBirth = "";
						if (!studentID.equals("-1")) {
							queryGetBirth = "SELECT BIRTHDATE FROM "
								+ "adlogan.Student WHERE StudentID = "
								+ Integer.valueOf(studentID);
						}
						answer = stmt.executeQuery(queryGetMax);
						int curr_id = 1;
						if (answer != null) {
							while (answer.next()) {
								curr_id = answer.getInt("MAX(PATIENTID)");
							}
						}

						answer = stmt.executeQuery(queryGetBirth);
						Date birth = null;
						if (answer != null) {
							while (answer.next()) {
								birth = answer.getDate("BIRTHDATE");
							}
						}

						// Check if the patient is already in the table or not
						String queryExist = "";
						if (!studentID.equals("-1")) {
							queryExist = "SELECT * FROM adlogan.Patient "
								+ "WHERE fullname = '"
								+ studentName + "'";
						} else {
							queryExist = "SELECT * FROM adlogan.Patient "
								+ "WHERE fullname = '"
								+ employeeName + "'";
						}
						answer = stmt.executeQuery(queryExist);
						int count = 0;
						if (answer != null) {
							while (answer.next()) {
								count += 1;
							}
						}

						// if that patient already in table then does not add
						if (count > 0) {
							System.out.println(
								"This patient has been added already!");
							return;
						} else {
							curr_id += 1;
							String queryAdd = "INSERT INTO adlogan.Patient "
								+ "VALUES (" + curr_id + " , '" + studentName
								+ "', " + studentID + ", " + employeeID
								+ ", TO_DATE('" + birth + "', 'YYYY-MM-DD')"
								+ ")";
							answer = stmt.executeQuery(queryAdd);
							System.out.println(
								"The patient has been added to the database");
						}
					} else {
						System.out.println(
							"The patient does not exists in the database of "
							+ "student and employee");
					}

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(
					"*** SQLException:  " + "Could not fetch query results.");
				System.err.println("\tMessage:   " + e.getMessage());
				System.err.println("\tSQLState:  " + e.getSQLState());
				System.err.println("\tErrorCode: " + e.getErrorCode());
				System.exit(-1);
			}
		} else {
			insertPatientHelper(dbconn, stmt, answer, studentID, employeeID);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method insertPatientHelper
	|
	|  Purpose: A helper function for inserting a patient into a database
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: None
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	studentID -- a string representing the student ID
	|	employeeID -- a string representing the employee ID
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void insertPatientHelper(Connection dbconn, Statement stmt,
		ResultSet answer, String studentID, String employeeID) {
		String name = "";
		if (!studentID.equals("-1")) {
			name = "SELECT FULLNAME FROM adlogan.Student WHERE StudentID = "
				+ studentID;
		} else {
			name = "SELECT FULLNAME FROM adlogan.UEmployee WHERE EmpID = "
				+ employeeID;
		}
		try {
			int findFlag = 0;
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(name);
			String patientName = "";
			if (answer != null) {
				while (answer.next()) {
					findFlag += 1;
					patientName = answer.getString("FULLNAME");
				}
			}

			if (findFlag > 0) {
				String queryGetMax = "SELECT MAX(PATIENTID) "
					+ "FROM adlogan.Patient";
				String queryGetBirth = "";
				if (!studentID.equals("-1")) {
					queryGetBirth = "SELECT BIRTHDATE FROM adlogan.Student "
						+ "WHERE StudentID = " + Integer.valueOf(studentID);
				} else {
					queryGetBirth = "SELECT BIRTHDATE FROM adlogan.UEmployee "
						+ "WHERE StudentID = " + Integer.valueOf(employeeID);
				}
				answer = stmt.executeQuery(queryGetMax);
				int curr_id = 1;
				if (answer != null) {
					while (answer.next()) {
						curr_id = answer.getInt("MAX(PATIENTID)");
					}
				}

				answer = stmt.executeQuery(queryGetBirth);
				Date birth = null;
				if (answer != null) {
					while (answer.next()) {
						birth = answer.getDate("BIRTHDATE");
					}
				}

				// Check if the patient is already in the table or not
				String queryExist = "";
				queryExist = "SELECT * FROM adlogan.Patient WHERE FULLNAME = '"
					+ patientName + "'";
				answer = stmt.executeQuery(queryExist);
				int count = 0;
				if (answer != null) {
					while (answer.next()) {
						count += 1;
					}
				}

				// if that patient already in the table then does not add
				if (count > 0) {
					System.out.println("This patient has been added already!");
					return;
				} else {
					if (studentID.equals("-1")) {
						studentID = "NULL";
					} else {
						employeeID = "NULL";
					}
					curr_id += 1;
					String queryAdd = "INSERT INTO adlogan.Patient VALUES ("
						+ curr_id + " , '" + patientName + "', " + studentID
						+ ", " + employeeID + ", TO_DATE('" + birth
						+ "', 'YYYY-MM-DD')" + ")";
					answer = stmt.executeQuery(queryAdd);
					System.out
						.println("The patient has been added to the database");
				}
			} else {
				System.out.println(
					"The patient does not exists in the Student and "
					+ "Employee database!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleInsertEmployee
	|
	|  Purpose: Handle insert an employee into a database
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: Insert operation functions properly
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	employeeName -- a string representing an employee's name
	|	employeeBirth -- a string representing an employee's date of birth
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleInsertEmployee(Connection dbconn, Statement stmt,
		ResultSet answer, String employeeName, String employeeBirth) {
		// Get the max current_id of the Employee table
		String queryGetMax = "SELECT MAX(EMPID) FROM adlogan.UEmployee";
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryGetMax);
			int curr_id = 0;
			if (answer != null) {
				while (answer.next()) {
					curr_id = answer.getInt("MAX(EMPID)");
				}
			}
			curr_id += 1;
			String queryAddEmp = "INSERT INTO adlogan.UEmployee VALUES("
				+ curr_id + ", '" + employeeName + "', " + "TO_DATE('"
				+ employeeBirth + "', 'YYYY-MM-DD')" + ")";
			answer = stmt.executeQuery(queryAddEmp);
			System.out.println("The employee has been added to the database!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method updateEmployee
	|
	|  Purpose: Handle update an employee into a database
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: Update operation functions properly
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	empID -- an int representing the employee ID
	|	employeeName -- a string representing an employee name
	|	employeeBirth -- a string representing an employee date of birth
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void updateEmployee(Connection dbconn, Statement stmt,
		ResultSet answer, String empID, String employeeName,
		String employeeBirth) {
		// Check if that employee in the table or not
		String checkExists = "SELECT * FROM adlogan.UEmployee WHERE EMPID = "
			+ Integer.valueOf(empID);
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(checkExists);
			int count = 0;
			if (answer != null) {
				while (answer.next()) {
					count += 1;
				}
			}

			if (count == 0) {
				System.out.println(
					"This employee does not exists. Please use add "
					+ "Employee Operations!");
			} else {
				String queryUpdate = "UPDATE adlogan.UEmployee SET FULLNAME = '"
					+ employeeName + "', " + "BIRTHDATE = " + "TO_DATE('"
					+ employeeBirth + "', 'YYYY-MM-DD')" + " WHERE EMPID = "
					+ Integer.valueOf(empID);
				answer = stmt.executeQuery(queryUpdate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method updatePatient
	|
	|  Purpose: Handle update a patient into a database
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: Update operation functions properly
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	patientID -- a string representing the patient ID
	|	name -- a string representing a patient's name
	|	birth -- a string representing a patient's date of birth
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void updatePatient(Connection dbconn, Statement stmt,
		ResultSet answer, String patientID, String name, String birth) {
		// Check if that patient is in the database or not
		String checkExists = "SELECT * FROM adlogan.Patient WHERE PatientID = "
			+ Integer.valueOf(patientID);
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(checkExists);
			int count = 0;
			if (answer != null) {
				while (answer.next()) {
					count += 1;
				}
			}

			if (count == 0) {
				System.out.println(
					"This patient does not exists. Please use add "
					+ "Patient Operations!");
			} else {
				String queryUpdatePatient = "UPDATE adlogan.Patient SET "
					+ "FULLNAME = '"
					+ name + "', " + "BIRTHDATE = " + "TO_DATE('" + birth
					+ "', 'YYYY-MM-DD')" + " WHERE PATIENTID = "
					+ Integer.valueOf(patientID);
				answer = stmt.executeQuery(queryUpdatePatient);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleInsertAppt
	|
	|  Purpose: Handle insert an appointment into a database
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: Insert operation functions properly
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	patientID -- an int representing the patient ID
	|	date -- a string representing the date of appointment
	|	start -- a string representing the start time
	|	end -- a string representing the end time
	|	serviceType -- a string representing the service
	|	severe -- a string representing if the illness is severe or not
	|	virusName -- a string representing a virus name
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static int handleInsertAppt(Connection dbconn, Statement stmt,
		ResultSet answer, String patientID, String date, String start,
		String end, String serviceType, String severe, String virusName) {

		// Need to check if the patientID is valid or not
		String queryGetPatient = "SELECT * FROM adlogan.Patient WHERE "
			+ "PATIENTID = " + patientID;
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryGetPatient);
			int count = 0;
			if (answer != null) {
				while (answer.next()) {
					count += 1;
				}
			}

			if (count == 0) {
				System.out.println(
					"This patient does not exists. Please enter a valid "
					+ "patientID!");
				return -1;
			} else {
				String startTime = date + " " + start;
				String endTime = date + " " + end;
				String startCheck = "TO_TIMESTAMP ('" + startTime
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
				String endCheck = "TO_TIMESTAMP ('" + endTime
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
				String queryCheckDate = "SELECT PATIENTID FROM "
					+ "adlogan.Appointment WHERE (StartTime <= "
					+ startCheck + "AND " + startCheck + " <= EndTime) OR ("
					+ startCheck + " <= StartTime AND " + endCheck
					+ " >= StartTime)";
				answer = stmt.executeQuery(queryCheckDate);
				int counter = 0;
				if (answer != null) {
					while (answer.next()) {
						counter += 1;
					}
				}

				if (counter > 0) {
					System.out.println(
						"There is an another appointment at this time so "
						+ "you will need to reschedule!");
					return -1;
				}
				// If there is no time conflict with that appointment
				else {

					// Get the current appt_id
					String queryGetId = "SELECT MAX(APTID) FROM "
						+ "adlogan.Appointment";
					answer = stmt.executeQuery(queryGetId);
					int curr_id = 0;
					while (answer.next()) {
						curr_id = answer.getInt("MAX(APTID)");
					}
					curr_id += 1;
					// Get staff ID
					String queryGetStaff = "SELECT MIN(STAFFID) FROM "
						+ "adlogan.CHStaff WHERE SERVICETYPE = '"
						+ serviceType + "'";
					answer = stmt.executeQuery(queryGetStaff);
					int staffID = 0;
					while (answer.next()) {
						staffID = answer.getInt("MIN(STAFFID)");
					}
					String walkin = "No";
					Double cost = 20.0;
					String status = "Incomplete";

					int vaxID = -1;
					int temp = 0;
					if (serviceType.equals("Immunization")) {
						// Get the next vax ID
						String queryGetVax = "SELECT MAX(VAXID) "
							+ "FROM adlogan.VAXLOG";
						answer = stmt.executeQuery(queryGetVax);
						vaxID = 0;
						while (answer.next()) {
							vaxID = answer.getInt("MAX(VAXID)");
						}
						vaxID += 1;

						// Create the next VAXID in Vaxlog
						String queryFindDose = "SELECT MAX(DOSENUM) "
							+ "FROM adlogan.VAXLOG WHERE PATIENTID = "
							+ patientID + " AND VIRUSNAME = '" + virusName
							+ "'";
						answer = stmt.executeQuery(queryFindDose);
						Integer curr_dose = 0;
						while (answer.next()) {
							curr_dose = answer.getInt("MAX(DOSENUM)");
						}
						curr_dose += 1;

						if (curr_dose > 3 && virusName.equals("COVID-19")) {
							System.out.println(
							"You cannot have more COVID-19 appointment "
							+ "since you are under 50 and already "
							+ "complete 3rd");
							temp = 1;
						} else {
							// Query to add to VAXLOG
							String queryVaxLog = "INSERT INTO adlogan.VAXLOG "
								+ "VALUES ("
								+ vaxID + ", " + patientID + ", '" + virusName
								+ "', " + curr_dose + ")";

							answer = stmt.executeQuery(queryVaxLog);
						}
					}

					// Query to add appointment
					if (temp == 0) {
						String queryAddApt = "";
						if (serviceType.equals("Immunization")) {
							queryAddApt = "INSERT INTO adlogan.Appointment "
								+ "VALUES(" + curr_id + ", " + patientID
								+ ", "  + startCheck
								+ ", " + endCheck + ", '" + serviceType + "', "
								+ staffID + ", '" + walkin + "', " + cost
								+ ", '" + status + "', " + "NULL, '" + severe
								+ "', " + vaxID + ")";
						} else {
							queryAddApt = "INSERT INTO adlogan.Appointment "
								+ "VALUES(" + curr_id + ", " + patientID + ", "
								+ startCheck
								+ ", " + endCheck + ", '" + serviceType + "', "
								+ staffID + ", '" + walkin + "', " + cost
								+ ", '" + status + "', " + "NULL, '" + severe
								+ "', " + "NULL)";
						}
						answer = stmt.executeQuery(queryAddApt);
						System.out.println(
							"The appointment has been added to the "
							+ "database! ");

						// Create a bill log for the appointment

						String queryGetBill = "SELECT MAX(BILLID) "
							+ "FROM adlogan.BILL";
						answer = stmt.executeQuery(queryGetBill);
						int curr_bill = 0;
						while (answer.next()) {
							curr_bill = answer.getInt("MAX(BILLID)");
						}
						curr_bill += 1;
						String queryAddBill = "INSERT INTO adlogan.Bill "
							+ "VALUES (" + curr_bill + ", " + patientID
							+ ", 20.0, 0, 20.0)";
						answer = stmt.executeQuery(queryAddBill);
						System.out.println("The bill is sent to the account");
					}
				}
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
		return -1;
	}

	/*-------------------------------------------------------------------------
	|  Method updateAppointment
	|
	|  Purpose: Handle update an appointment into a database
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: Update operation functions properly
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	aptID -- a string representing the appointment ID
	|	patientID -- an int representing the patient ID
	|	date -- a string representing the date of appointment
	|	start -- a string representing the start time
	|	end -- a string representing the end time
	|	serviceType -- a string representing the service
	|	severe -- a string representing if the illness is severe or not
	|	virusName -- a string representing a virus name
	|	int status
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void updateAppointment(Connection dbconn, Statement stmt,
		ResultSet answer, String aptID, String date, String start, String end,
		String serviceType, String severe, int status, Scanner readInput) {
		// Check if the patient ID exists or not
		String queryCheckId = "SELECT * FROM adlogan.Appointment WHERE "
			+ "APTID = " + aptID;
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryCheckId);
			int count = 0;
			if (answer != null) {
				while (answer.next()) {
					count += 1;
				}
			}

			if (count == 0) {
				System.out.println("This appointment ID does not exist!");
			} else {
				String startTime = date + " " + start;
				String endTime = date + " " + end;
				String startCheck = "TO_TIMESTAMP ('" + startTime
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
				String endCheck = "TO_TIMESTAMP ('" + endTime
					+ "', 'YYYY-MM-DD HH24:MI:SS')";

				String queryCheckDate = "SELECT PATIENTID FROM "
					+ "adlogan.Appointment WHERE (StartTime <= "
					+ startCheck + "AND " + startCheck + " <= EndTime) OR ("
					+ startCheck + " <= StartTime AND " + endCheck
					+ " >= StartTime)";
				answer = stmt.executeQuery(queryCheckDate);
				int counter = 0;
				if (answer != null) {
					while (answer.next()) {
						counter += 1;
					}
				}

				if (counter > 0) {
					System.out.println(
						"There is an another appointment at this time so "
						+ "you will need to reschedule!");
				}

				int cancel = 0;
				// Check status of the appointment
				String curr_status = "";
				if (status == 1) {
					curr_status = "Complete";
				} else if (status == 2) {
					curr_status = "Incomplete";
				} else {
					curr_status = "Cancelled";
					cancel = 1;
				}

				String queryUpdateApt;
				// If the appointment is not cancelled
				if (cancel == 0) {
					queryUpdateApt = "UPDATE adlogan.Appointment "
						+ "SET StartTime = "
						+ startCheck + ", EndTime = " + endCheck
						+ ", ServiceType = '" + serviceType
						+ "', CurStatus = '"
						+ curr_status + "', Severe = '" + severe + "' "
						+ "WHERE APTID = " + aptID;
				} else {
					queryUpdateApt = "UPDATE adlogan.Appointment "
						+ "SET StartTime = "
						+ startCheck + ", EndTime = " + endCheck
						+ ", ServiceType = '" + serviceType
						+ "', CurStatus = '"
						+ curr_status
						+ "', CancelTime = CURRENT_TIMESTAMP , Severe = '"
						+ severe + "' " + "WHERE APTID = " + aptID;
				}
				answer = stmt.executeQuery(queryUpdateApt);

				// If cancel the bill then update the bill
				if (curr_status.equals("No Show")
					&& serviceType.equals("Immunization")) {
					// Get the bill
					String queryGetBill = "SELECT MAX(BILLID) FROM adlogan.BILL";
					answer = stmt.executeQuery(queryGetBill);
					int curr_bill = 0;
					while (answer.next()) {
						curr_bill = answer.getInt("MAX(BILLID)");
					}
					curr_bill += 1;

					// Get PatientID
					String getPatientID = "SELECT PATIENTID FROM "
						+ "adlogan.Appointment WHERE APTID = " + aptID;
					answer = stmt.executeQuery(getPatientID);
					int patientID = 0;
					while (answer.next()) {
						patientID = answer.getInt("PATIENTID");
					}

					String queryAddBill = "INSERT INTO adlogan.Bill VALUES ("
						+ curr_bill + ", " + patientID + ", 0, 25.0, 25.0)";
					answer = stmt.executeQuery(queryAddBill);
					System.out.println(
						"The bill charged for no-show fee is sent to "
						+ "the account ");
				}

				if (curr_status.equals("Complete")
					&& serviceType.equals("Immunization")) {
					// Get the number of the dose
					String covid = "COVID-19";
					String getPatientID = "SELECT PATIENTID FROM "
						+ "adlogan.Appointment WHERE APTID = " + aptID;
					answer = stmt.executeQuery(getPatientID);
					int patientID = 0;
					while (answer.next()) {
						patientID = answer.getInt("PATIENTID");
					}
					String queryGetMaxDose = "SELECT MAX(DOSENUM) "
						+ "FROM adlogan.Vaxlog, adlogan.Appointment WHERE a"
						+ "dlogan.Vaxlog.PATIENTID = "
						+ patientID + " AND VIRUSNAME = '" + covid
						+ "' AND adlogan.Appointment.CURSTATUS = 'Complete'";
					System.out.println(queryGetMaxDose);
					answer = stmt.executeQuery(queryGetMaxDose);

					int doseNum = 1;
					while (answer.next()) {
						doseNum = answer.getInt("MAX(DOSENUM)");
					}
					System.out
						.println("This is the current max dose: " + doseNum);
					// if current_max dose number is 3 then check age
					if (doseNum == 3) {
						String queryGetDOB = "SELECT BIRTHDATE "
							+ "FROM adlogan.Patient WHERE PATIENTID = "
							+ patientID;
						answer = stmt.executeQuery(queryGetDOB);
						Date DOB = null;
						while (answer.next()) {
							DOB = answer.getDate("BIRTHDATE");
						}
						// Check if the date of birth is over 50 or not
						String queryGetAge = "select months_between"
							+ "(TRUNC(sysdate), to_date('"
							+ DOB + "','YYYY-MM-DD'))/12 as age from dual";
						answer = stmt.executeQuery(queryGetAge);
						Double age = 0.0;
						while (answer.next()) {
							age = answer.getDouble("AGE");
						}
						if (age > 50.0) {
							System.out.println(
							"Thank you for completing the 3rd dose, "
							+ "please enter the time you want to"
							+ "schedule the 4th dose since you are over 50!");

							// Receive information for 4th dose from the user
							String date4th = "";
							System.out.println(
							"Please enter the day you want to schedule "
							+ "your 4th dose in this format(YYYY-MM-DD): ");
							date4th = readInput.nextLine();
							while (!dateValidation(date4th)) {
								System.out.println(
									"Please re-enter the day in this correct "
									+ "format(YYYY-MM-DD): ");
								date4th = readInput.nextLine();
							}

							// Get start time and end time from the users
							System.out.println(
								"Please enter your 4th dose start time in "
								+ "this format(HH:MM:SS):");
							String start4th = readInput.nextLine();
							while (!validTime(start4th)) {
								System.out.println(
									"Please re-enter your 4th dose start "
									+ "time in this format(HH:MM:SS):");
								start4th = readInput.nextLine();
							}
							System.out.println(
								"Please enter your 4th dose end time is "
								+ "this format(HH:MM:SS):");
							String end4th = readInput.nextLine();
							while (!validTime(end4th)) {
								System.out.println(
									"Please re-enter your 4th dose end time "
									+ "in this format(HH:MM:SS):");
								end4th = readInput.nextLine();
							}

							while (validStartEnd(end4th, start4th) != 1) {
								System.out.println(
									"Your end time is after the start "
									+ "time. Please re-enter valid time!");
								System.out.println(
									"Please re-enter your start time in "
									+ "this format(HH:MM:SS):");
								start4th = readInput.nextLine();
								while (!validTime(start4th)) {
									System.out.println(
										"Please re-enter your start time "
										+ "in this format(HH:MM:SS):");
									start = readInput.nextLine();
								}
								System.out.println(
									"Please enter your end time is "
									+ "this format(HH:MM:SS):");
								end = readInput.nextLine();
								while (!validTime(end4th)) {
									System.out.println(
										"Please re-enter your end time "
										+ "in this format(HH:MM:SS):");
									end4th = readInput.nextLine();
								}
							}
							handleScheduleImmunization(dbconn, stmt, answer,
								patientID, date4th, start4th, end4th);

						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleScheduleImmunization
	|
	|  Purpose: Handle schedule an immunization
	|
	|  Pre-condition: dbconn is not null.
	|
	|  Post-condition: Update operation functions properly
	|
	|  Parameters
	|	dbconn -- a Connection object representing the connection in JDBC
	|	stmt -- a Statement object to execute the query
	|	answer -- a ResultSet object to store the result of the query
	|	patientID -- an int representing the patient ID
	|	date -- a string representing the date of immunization
	|	start -- a string representing the start time
	|	end -- a string representing the end time
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleScheduleImmunization(Connection dbconn,
		Statement stmt, ResultSet answer, int patientID, String date,
		String start, String end) {
		handleInsertAppt(dbconn, stmt, answer, String.valueOf(patientID), date,
			start, end, "Immunization", "0", "COVID-19");
	}

	/*-------------------------------------------------------------------------
	|  Method validTime
	|
	|  Purpose: Check if a string is a valid time or not
	|
	|  Pre-condition: None
	|
	|  Post-condition: None
	|
	|  Parameters
	|	time -- a string to check if it is a valid time or not
	|
	|  Returns: True if a string is a valid time, False otherwise
	*------------------------------------------------------------------------*/
	private static boolean validTime(String time) {
		// The correct format time (HH:MM:SS)
		if (time.length() != 8) {
			return false;
		}
		char checka = time.charAt(2);
		char checkb = time.charAt(5);
		if ((checka != ':') || (checkb != ':')) {
			return false;
		}
		String hour = time.substring(0, 2);
		String minute = time.substring(3, 5);
		String second = time.substring(6, time.length() - 1);
		return (isNumeric(hour) && isNumeric(minute) && isNumeric(second));
	}

	/*-------------------------------------------------------------------------
	|  Method validStartEnd
	|
	|  Purpose: Check if a start time is less than an end time
	|
	|  Pre-condition: None
	|
	|  Post-condition: None
	|
	|  Parameters
	|	startTime -- a string representing the start time
	|	endTime -- a string representing the end time
	|
	|  Returns: True if start time is less than end time, False otherwise
	*------------------------------------------------------------------------*/
	private static int validStartEnd(String startTime, String endTime) {
		LocalTime time1 = LocalTime.parse(startTime);
		LocalTime time2 = LocalTime.parse(endTime);
		return (time1.compareTo(time2));
	}

	/*-------------------------------------------------------------------------
	|  Method doseNumValidation
	|
	|  Purpose: Check if a string is a valid dose number (1, 2, 3, or 4)
	|
	|  Pre-condition: None
	|
	|  Post-condition: None
	|
	|  Parameters
	|	dosestrNum -- a string to check if it is a valid dose number or not
	|
	|  Returns: True if a string is a valid dose number, false otherwise.
	*------------------------------------------------------------------------*/
	private static boolean doseNumValidation(String doseNumStr) {
		int doseNum;
		try {
			doseNum = Integer.valueOf(doseNumStr);
		} catch (Exception e) {
			return false;
		}

		if (doseNum >= 1 && doseNum <= 4) {
			return true;
		}
		return false;
	}

	/*-------------------------------------------------------------------------
	|  Method dateValidation
	|
	|  Purpose: Check if a string is a date in format YYYY-MM-DD
	|
	|  Pre-condition: None
	|
	|  Post-condition: None
	|
	|  Parameters
	|	time -- a string to check if it is a valid date in correct format
	|
	|  Returns: True if a string is a valid date in correct format, false
	|	otherwise.
	*------------------------------------------------------------------------*/
	private static boolean dateValidation(String time) {
		// Valid date should be in form: YYYY-MM-DD
		if (time.length() != 10) {
			return false;
		}
		Character checka = time.charAt(4);
		Character checkb = time.charAt(7);
		if (!checka.equals('-') && !checkb.equals('-')) {
			return false;
		}

		String year = time.substring(0, 4);
		String month = time.substring(5, 7);
		String date = time.substring(8, time.length() - 1);

		return (isNumeric(year) && isNumeric(month) && isNumeric(date));
	}

	/*-------------------------------------------------------------------------
	|  Method isNumeric
	|
	|  Purpose: Check if a string is a valid integer
	|
	|  Pre-condition: None
	|
	|  Post-condition: None
	|
	|  Parameters
	|	strNum -- a string to check if it is an integer or not
	|
	|  Returns: True if a string is a valid integer, false otherwise.
	*------------------------------------------------------------------------*/
	private static boolean isInteger(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int num = Integer.valueOf(strNum);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*-------------------------------------------------------------------------
	|  Method isNumeric
	|
	|  Purpose: Check if a string is a valid number
	|
	|  Pre-condition: None
	|
	|  Post-condition: None
	|
	|  Parameters
	|	strNum -- a string to check if it is a number or not
	|
	|  Returns: True if a string is a valid number, false otherwise.
	*------------------------------------------------------------------------*/
	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/*-------------------------------------------------------------------------
	|  Method handleDeleteAppointment
	|
	|  Purpose: Handle delete the appointment given the appointment ID
	|
	|  Pre-condition: dbconn is not null, aptID is valid
	|
	|  Post-condition: Delete operation functions properly
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	aptID -- an int representing an appointment ID
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleDeleteAppointment(Connection dbconn, int aptID) {
		String query; // for storing the query

		// for executing and storing the result of the query
		Statement stmt = null;
		ResultSet answer = null;

		try {

			stmt = dbconn.createStatement();

			// query to delete the appointment given the appointment ID
			query = "DELETE FROM adlogan.Appointment WHERE AptID = " + aptID;
			answer = stmt.executeQuery(query);

			if (answer != null) {
				System.out.println("Delete successfully!");
			}
			System.out.println();

			stmt.close();

		} catch (SQLException e) {
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}

	}

	/*-------------------------------------------------------------------------
	|  Method handleDeleteEmployee
	|
	|  Purpose: Handle delete an employee given the employee ID
	|
	|  Pre-condition: dbconn is not null, empID is valid
	|
	|  Post-condition: Delete operation functions properly
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	empID -- an int representing an employee ID
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleDeleteEmployee(Connection dbconn, int empID) {
		String query; // for storing query

		// for executing and storing the result of the query
		Statement stmt = null;
		ResultSet answer = null;

		try {

			stmt = dbconn.createStatement();

			// query to delete the employee given the employee ID
			query = "DELETE FROM adlogan.UEmployee WHERE EmpID = " + empID;
			answer = stmt.executeQuery(query);

			if (answer != null) {
				System.out.println("Delete successfully!");
			}
			System.out.println();

			stmt.close();

		} catch (SQLException e) {
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleDeletePatient
	|
	|  Purpose: Handle delete a patient given the patient ID
	|
	|  Pre-condition: dbconn is not null, patientID is valid
	|
	|  Post-condition: Delete operation functions properly
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	patientID -- an int representing a patient ID
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleDeletePatient(Connection dbconn, int patientID) {
		String query; // for storing query

		// for executing and storing the result of the query
		Statement stmt = null;
		ResultSet answer = null;

		try {

			stmt = dbconn.createStatement();

			// query to delete the patient given the patient ID
			query = "DELETE FROM adlogan.Patient WHERE PatientID = "
				+ patientID;
			answer = stmt.executeQuery(query);

			if (answer != null) {
				System.out.println("Delete successfully!");
			}

			System.out.println();

			stmt.close();

		} catch (SQLException e) {
			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleUpdateService
	|
	|  Purpose: Handle delete a service type given the patient ID
	|
	|  Pre-condition: dbconn is not null, staffID and service are valid
	|
	|  Post-condition: Update operation functions properly
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	staffID -- an int representing a staff ID
	|	service -- a string representing the service type
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleUpdateService(Connection dbconn, int staffID,
		String service) {

		String query; // for storing the query

		// for executing and storing the result of the query
		Statement stmt = null;
		ResultSet answer = null;

		try {

			stmt = dbconn.createStatement();

			// update the service type given the staff ID
			query = "UPDATE adlogan.CHStaff SET ServiceType = " + service
				+ " WHERE StaffID = " + staffID;
			answer = stmt.executeQuery(query);

			if (answer != null) {
				System.out.println("Update successfully!");
			}
			System.out.println();

			stmt.close();

		} catch (SQLException e) {

			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/*-------------------------------------------------------------------------
	|  Method handleQuery1
	|
	|  Purpose: Handle the first query option, which is "Print a list of
	|	patients who have their 2nd, 3rd or 4th doses of the COVID-19 vaccine
	|	scheduled by a certain date (given that the date is entered by user)"
	|
	|  Pre-condition: dbconn and date are not null
	|
	|  Post-condition: Correct output is displayed for the user
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	date -- a string representing the input date
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleQuery1(Connection dbconn, String date) {

		// for processing a current record
		int curVaxID, curDoseNum, curPatientID;
		String query; // for storing the query

		// keep track of all scheduled vaccine doses before the input date
		List<Integer> vaxIDs = new ArrayList<>();
		// keep track of all scheduled vaccine 2nd doses before the input date
		List<Integer> secondDoses = new ArrayList<>();
		// keep track of all scheduled vaccine 3rd doses before the input date
		List<Integer> thirdDoses = new ArrayList<>();
		// keep track of all scheduled vaccine 4th doses before the input date
		List<Integer> fourthDoses = new ArrayList<>();

		// for executing and storing the result of the query
		Statement stmt = null;
		ResultSet answer = null;

		try {

			stmt = dbconn.createStatement();

			query = "SELECT VaxID FROM adlogan.Appointment WHERE StartTime "
				+ "<= TO_TIMESTAMP('" + date + " 23:59:59', 'YYYY-MM-DD "
				+ "HH24:MI:SS') AND CurStatus <> 'Cancelled' "
				+ "AND CurStatus <> 'No Show'";

			answer = stmt.executeQuery(query);
			if (answer != null) {
				// get all scheduled vaccine doses before the input date and
				// add to the corresponding list
				while (answer.next()) {

					curVaxID = answer.getInt("VaxID");
					if (!answer.wasNull()) {
						vaxIDs.add(curVaxID);
					}
				}
			}

			for (Integer vaxID : vaxIDs) {
				query = "SELECT PatientID, DoseNum FROM adlogan.VaxLog WHERE "
					+ "VaxID = " + vaxID + " AND VirusName = 'COVID-19'";

				answer = stmt.executeQuery(query);
				if (answer != null && answer.next()) {
					curPatientID = answer.getInt("PatientID");
					curDoseNum = answer.getInt("DoseNum");

					// add the patient ID to the corresponding list based on
					// dose number
					if (curDoseNum == 2) {
						secondDoses.add(curPatientID);
					} else if (curDoseNum == 3) {
						thirdDoses.add(curPatientID);
					} else if (curDoseNum == 4) {
						fourthDoses.add(curPatientID);
					}
				}
			}

			// print out the list of patients who have their 2nd doses of the
			// COVID-19 vaccine by the input date
			if (secondDoses.size() == 0) {
				System.out.println("There are no patients who have their 2nd "
					+ "doses of the COVID-19 vaccine by " + date + "!");
			} else {
				System.out.println("List of paitents who have their 2nd dose "
					+ "of the COVID-19 vaccine by " + date + ":");
				System.out.println("PatientID\t\t\tFull Name\t\t\tDate of "
					+ "Birth\n---------\t\t\t---------\t\t\t-------------");

				for (Integer patientID : secondDoses) {
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + patientID;
					answer = stmt.executeQuery(query);
					if (answer != null && answer.next()) {

						System.out.print(answer.getInt("PatientID") + "\t\t\t");
						System.out
							.print(answer.getString("FullName") + "\t\t\t");
						System.out.println(answer.getDate("BirthDate"));

						System.out.println();
					}
				}
			}
			System.out.println();
			// print out the list of patients who have their 3rd doses of the
			// COVID-19 vaccine by the input date
			if (thirdDoses.size() == 0) {
				System.out.println("There are no patients who have their 3rd "
					+ "doses of the COVID-19 vaccine by " + date + "!");
			} else {
				System.out.println("List of paitents who have their 3rd dose "
					+ "of the COVID-19 vaccine by " + date + ":");
				System.out.println("PatientID\t\t\tFull Name\t\t\tDate of "
					+ "Birth\n---------\t\t\t---------\t\t\t-------------");

				for (Integer patientID : thirdDoses) {
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + patientID;
					answer = stmt.executeQuery(query);
					if (answer != null && answer.next()) {
						System.out.print(answer.getInt("PatientID") + "\t\t\t");
						System.out
							.print(answer.getString("FullName") + "\t\t\t");
						System.out.println(answer.getDate("BirthDate"));

					}
				}
			}
			System.out.println();

			// print out the list of patients who have their 4th doses of the
			// COVID-19 vaccine by the input date
			if (fourthDoses.size() == 0) {
				System.out.println("There are no patients who have their 4th "
					+ "doses of the COVID-19 vaccine by " + date + "!");
			} else {
				System.out.println("List of paitents who have their 4th dose "
					+ "of the COVID-19 vaccine by " + date + ":");
				System.out.println("PatientID\t\t\tFull Name\t\t\tDate of "
					+ "Birth\n---------\t\t\t---------\t\t\t-------------");

				for (Integer patientID : fourthDoses) {
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + patientID;
					answer = stmt.executeQuery(query);
					if (answer != null && answer.next()) {

						System.out.print(answer.getInt("PatientID") + "\t\t\t");
						System.out
							.print(answer.getString("FullName") + "\t\t\t");
						System.out.println(answer.getDate("BirthDate"));

					}
				}
			}

			System.out.println();

			stmt.close();

		} catch (SQLException e) {

			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/*-------------------------------------------------------------------------
	|  Method handleQuery2
	|
	|  Purpose: Handle the second query option, which is "Given a certain
	|	date, output which patients had a non–walk–in appointment. Sort in
	|	order by appointment time and group by type of service."
	|
	|  Pre-condition: dbconn and date are not null
	|
	|  Post-condition: Correct output is displayed for the user
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	date -- a string representing the input date
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleQuery2(Connection dbconn, String date) {

		// for executing and storing the result of the query
		Statement stmt = null;
		ResultSet answer = null;

		// for storing a query and processing a current record
		String query, curService;
		int curPatientID;

		// for storing the list of patients who have a specific type of service
		List<Integer> medicine = new ArrayList<>();
		List<Integer> caps = new ArrayList<>();
		List<Integer> labTesting = new ArrayList<>();
		List<Integer> immunization = new ArrayList<>();

		try {

			stmt = dbconn.createStatement();

			query = "SELECT PatientID, ServiceType FROM adlogan.Appointment "
				+ "WHERE StartTime >= TO_TIMESTAMP('" + date + " 00:00:00', "
				+ "'YYYY-MM-DD HH24:MI:SS') AND StartTime <= TO_TIMESTAMP('"
				+ date + " 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AND CurStatus "
				+ "<> 'Cancelled' AND CurStatus <> 'No Show' "
				+ "AND WalkIn = 'No' ORDER BY StartTime, EndTime";

			answer = stmt.executeQuery(query);

			if (answer != null) {

				// get all patients that had non-walk-in appointments on the
				// input date
				while (answer.next()) {
					curService = answer.getString("ServiceType");
					curPatientID = answer.getInt("PatientID");

					// add the current patient ID to the corresponding list
					// based on the service
					if (curService.equals("General Medicine")) {
						medicine.add(curPatientID);
					} else if (curService.equals("CAPS")) {
						caps.add(curPatientID);
					} else if (curService.equals("Laboratory and Testing")) {
						labTesting.add(curPatientID);
					} else if (curService.equals("Immunization")) {
						immunization.add(curPatientID);
					}
				}
			}
			System.out.println();

			// print all patients who had non-walk-in appointments on the
			// input date of the general medicine service
			System.out.println("----- General Medicine -----");
			if (medicine.size() == 0) {
				System.out.println("There are no patients that have "
					+ "non-walk-in appointments on " + date);
			} else {
				System.out.println("List of paitents who have non-walk-in "
					+ "appointments on " + date + ":");
				System.out.println("PatientID\t\t\tFull Name\t\t\tDate of "
					+ "Birth\n---------\t\t\t---------\t\t\t-------------");

				for (Integer patientID : medicine) {
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + patientID;
					answer = stmt.executeQuery(query);
					if (answer != null && answer.next()) {
						System.out.print(answer.getInt("PatientID") + "\t\t\t");
						System.out
							.print(answer.getString("FullName") + "\t\t\t");
						System.out.println(answer.getDate("BirthDate"));

					}
				}
			}

			// print all patients who had non-walk-in appointments on the
			// input date of the CAPS service
			System.out.println("\n----- Counseling And Psychological Services "
				+ "(CAPS) -----");
			if (caps.size() == 0) {
				System.out.println("There are no patients that have "
					+ "non-walk-in appointments on " + date);
			} else {
				System.out.println("List of paitents who have non-walk-in "
					+ "appointments on " + date + ":");
				System.out.println("PatientID\t\t\tFull Name\t\t\tDate of "
					+ "Birth\n---------\t\t\t---------\t\t\t-------------");

				for (Integer patientID : caps) {
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + patientID;
					answer = stmt.executeQuery(query);
					if (answer != null && answer.next()) {
						System.out.print(answer.getInt("PatientID") + "\t\t\t");
						System.out
							.print(answer.getString("FullName") + "\t\t\t");
						System.out.println(answer.getDate("BirthDate"));

					}
				}

			}

			// print all patients who had non-walk-in appointments on the
			// input date of the laboratory & testing service
			System.out.println("\n----- Laboratory & Testing -----");
			if (labTesting.size() == 0) {
				System.out.println("There are no patients that have "
					+ "non-walk-in appointments on " + date);
			} else {
				System.out.println("List of paitents who have non-walk-in "
					+ "appointments on " + date + ":");
				System.out.println("PatientID\t\t\tFull Name\t\t\tDate of "
					+ "Birth\n---------\t\t\t---------\t\t\t-------------");
				for (Integer patientID : labTesting) {
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + patientID;
					answer = stmt.executeQuery(query);
					if (answer != null && answer.next()) {
						System.out.print(answer.getInt("PatientID") + "\t\t\t");
						System.out
							.print(answer.getString("FullName") + "\t\t\t");
						System.out.println(answer.getDate("BirthDate"));

					}
				}
			}

			// print all patients who had non-walk-in appointments on the
			// input date of the immunization service
			System.out.println("\n----- Immunization -----");
			if (immunization.size() == 0) {
				System.out.println("There are no patients that have "
					+ "non-walk-in appointments on " + date);
			} else {
				System.out.println("List of paitents who have non-walk-in "
					+ "appointments on " + date + ":");
				System.out.println("PatientID\t\t\tFull Name\t\t\tDate of "
					+ "Birth\n---------\t\t\t---------\t\t\t-------------");
				for (Integer patientID : immunization) {
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + patientID;
					answer = stmt.executeQuery(query);
					if (answer != null && answer.next()) {
						System.out.print(answer.getInt("PatientID") + "\t\t\t");
						System.out
							.print(answer.getString("FullName") + "\t\t\t");
						System.out.println(answer.getDate("BirthDate"));

					}
				}
			}

			System.out.println();

			stmt.close();

		} catch (SQLException e) {

			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleQuery3
	|
	|  Purpose: Handle the third query option, which is "Print the schedule of
	|	staff given a certain date (input by the user). A schedule contains the
	|	list of staff members working that day (including those who were working
	|	that day as usual and those who were working to handle an appointment)
	|	and a staff member’s working hours (start and stop times)."
	|
	|  Pre-condition: dbconn and date are not null
	|
	|  Post-condition: Correct output is displayed for the user
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	date -- a string representing the input date
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleQuery3(Connection dbconn, String date) {

		String query; // for storing the query
		int curStaffID; // for processing the current query

		// for executing and storing the result of the query
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet answer1 = null;
		ResultSet answer2 = null;

		try {

			stmt1 = dbconn.createStatement();
			stmt2 = dbconn.createStatement();

			System.out.println("----- Schedule of staff on " + date + " -----");

			// get schedule of staff who have shifts on that day
			query = "SELECT StaffID, ShiftStart, ShiftEnd FROM adlogan.Shift "
				+ "WHERE ShiftStart >= TO_TIMESTAMP('" + date + " 00:00:00', "
				+ "'YYYY-MM-DD HH24:MI:SS') AND ShiftStart <= TO_TIMESTAMP('"
				+ date + " 23:59:59', 'YYYY-MM-DD HH24:MI:SS')";

			answer1 = stmt1.executeQuery(query);

			System.out.println(
				"\n*** Schedule of staff who have shifts on " + date + " ***");
			System.out.println("StaffID\t\t\tFull Name\t\t\tService Type"
				+ "\t\t\tStart Time\t\t\tEnd Time");
			System.out.println("-------\t\t\t---------\t\t\t------------"
				+ "\t\t\t----------\t\t\t--------");

			if (answer1 != null) {
				while (answer1.next()) {
					curStaffID = answer1.getInt("StaffID");

					// get the info of the staff based on the staff ID
					query = "SELECT FullName, ServiceType FROM adlogan.CHStaff"
						+ " WHERE StaffID = " + curStaffID;
					answer2 = stmt2.executeQuery(query);

					if (answer2 != null && answer2.next()) {

						System.out.println(curStaffID + "\t\t\t"
							+ answer2.getString("FullName") + "\t\t\t"
							+ answer2.getString("ServiceType") + "\t\t\t"
							+ answer1.getTimestamp("ShiftStart") + "\t\t\t"
							+ answer1.getTimestamp("ShiftEnd"));
					}
				}
			}

			// get schedule of staff who handle appointments on that day
			query = "SELECT StaffID, StartTime, EndTime FROM "
				+ "adlogan.Appointment WHERE StartTime >= TO_TIMESTAMP('" + date
				+ " 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND "
				+ "StartTime <= TO_TIMESTAMP('" + date + " 23:59:59', "
				+ "'YYYY-MM-DD HH24:MI:SS') AND CurStatus <> 'Cancelled'";

			answer1 = stmt1.executeQuery(query);

			System.out.println("\n*** Schedule of staff who have appointments"
				+ " on " + date + " ***");
			System.out.println("StaffID\t\t\tFull Name\t\t\tService Type"
				+ "\t\t\tStart Time\t\t\tEnd Time");
			System.out.println("-------\t\t\t---------\t\t\t------------"
				+ "\t\t\t----------\t\t\t--------");

			if (answer1 != null) {
				while (answer1.next()) {
					curStaffID = answer1.getInt("StaffID");

					// get the info of the staff based on the staff ID
					query = "SELECT FullName, ServiceType FROM "
						+ "adlogan.CHStaff WHERE StaffID = " + curStaffID;
					answer2 = stmt2.executeQuery(query);

					if (answer2 != null && answer2.next()) {

						System.out.println(curStaffID + "\t\t\t"
							+ answer2.getString("FullName") + "\t\t\t"
							+ answer2.getString("ServiceType") + "\t\t\t"
							+ answer1.getTimestamp("StartTime") + "\t\t\t"
							+ answer1.getTimestamp("EndTime"));
					}
				}
			}

			System.out.println();

			stmt1.close();
			stmt2.close();

		} catch (SQLException e) {

			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleQuery4
	|
	|  Purpose: Handle the fourth query option, which is "Print the vaccine
	|	statistics of the two categories of patients (student, employees). The
	|	statistics include the count of patients that have completed all 4
	|	doses of a vaccine series, the count of patients that have received
	|	three doses, but not the 4th, the count of patients that have received
	|	two doses but not the 3rd, and the count of patients who have only
	|	received the first dose."
	|
	|  Pre-condition: dbconn is not null
	|
	|  Post-condition: Correct output is displayed for the user
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleQuery4(Connection dbconn) {

		String query; // for storing the query
		int curPatientID; // for processing the current query

		// for executing and storing the result of the query
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet answer1 = null;
		ResultSet answer2 = null;

		// store the count of different dose numbers for students and employees
		int firstDoseStudent = 0;
		int firstDoseEmployee = 0;
		int secondDoseStudent = 0;
		int secondDoseEmployee = 0;
		int thirdDoseStudent = 0;
		int thirdDoseEmployee = 0;
		int fourthDoseStudent = 0;
		int fourthDoseEmployee = 0;

		try {

			stmt1 = dbconn.createStatement();
			stmt2 = dbconn.createStatement();

			// count all patients who completed all 4 doses
			query = "SELECT PatientID FROM adlogan.VaxLog WHERE DoseNum = 4 "
				+ "AND VirusName = 'COVID-19' AND VaxID IN (SELECT VaxID FROM "
				+ "adlogan.Appointment WHERE CurStatus = 'Complete')";
			answer1 = stmt1.executeQuery(query);
			if (answer1 != null) {
				while (answer1.next()) {
					curPatientID = answer1.getInt("PatientID");

					query = "SELECT StudentID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is a student to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("StudentID") != 0) {
						fourthDoseStudent += 1;
					}

					query = "SELECT EmpID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is an employee to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("EmpID") != 0) {
						fourthDoseEmployee += 1;
					}

				}
			}

			// count all patients who completed the 3rd dose but not 4th dose
			query = "SELECT PatientID FROM adlogan.VaxLog WHERE DoseNum = 3 "
				+ "AND VirusName = 'COVID-19' AND VaxID IN (SELECT VaxID "
				+ "FROM adlogan.Appointment WHERE CurStatus = 'Complete') "
				+ "AND PatientID NOT IN (SELECT PatientID FROM adlogan.VaxLog "
				+ "WHERE DoseNum = 4 AND VirusName = 'COVID-19' AND VaxID IN "
				+ "(SELECT VaxID FROM adlogan.Appointment WHERE CurStatus = "
				+ "'Complete'))";
			answer1 = stmt1.executeQuery(query);
			if (answer1 != null) {
				while (answer1.next()) {
					curPatientID = answer1.getInt("PatientID");

					query = "SELECT StudentID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is a student to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("StudentID") != 0) {
						thirdDoseStudent += 1;
					}

					query = "SELECT EmpID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is an employee to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("EmpID") != 0) {
						thirdDoseEmployee += 1;
					}

				}
			}

			// count all patients who completed the 2nd dose but not 3rd dose
			query = "SELECT PatientID FROM adlogan.VaxLog WHERE DoseNum = 2 "
				+ "AND VirusName = 'COVID-19' AND VaxID IN (SELECT VaxID "
				+ "FROM adlogan.Appointment WHERE CurStatus = 'Complete') "
				+ "AND PatientID NOT IN (SELECT PatientID FROM adlogan.VaxLog "
				+ "WHERE DoseNum = 3 AND VirusName = 'COVID-19' AND VaxID IN "
				+ "(SELECT VaxID FROM adlogan.Appointment WHERE CurStatus = "
				+ "'Complete'))";
			answer1 = stmt1.executeQuery(query);
			if (answer1 != null) {
				while (answer1.next()) {
					curPatientID = answer1.getInt("PatientID");

					query = "SELECT StudentID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is a student to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("StudentID") != 0) {
						secondDoseStudent += 1;
					}

					query = "SELECT EmpID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is an employee to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("EmpID") != 0) {
						secondDoseEmployee += 1;
					}

				}
			}

			// count all patients who completed the 1st dose but not 2nd dose
			query = "SELECT PatientID FROM adlogan.VaxLog WHERE DoseNum = 1 "
				+ "AND VirusName = 'COVID-19' AND VaxID IN (SELECT VaxID "
				+ "FROM adlogan.Appointment WHERE CurStatus = 'Complete') "
				+ "AND PatientID NOT IN (SELECT PatientID FROM adlogan.VaxLog "
				+ "WHERE DoseNum = 2 AND VirusName = 'COVID-19' AND VaxID IN "
				+ "(SELECT VaxID FROM adlogan.Appointment WHERE CurStatus = "
				+ "'Complete'))";
			answer1 = stmt1.executeQuery(query);
			if (answer1 != null) {
				while (answer1.next()) {
					curPatientID = answer1.getInt("PatientID");

					query = "SELECT StudentID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is a student to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("StudentID") != 0) {
						firstDoseStudent += 1;
					}

					query = "SELECT EmpID FROM adlogan.Patient WHERE "
						+ "PatientID = " + curPatientID;
					answer2 = stmt2.executeQuery(query);

					// check if the patient is an employee to add to the
					// corresponding count
					if (answer2 != null && answer2.next()
						&& answer2.getInt("EmpID") != 0) {
						firstDoseEmployee += 1;
					}

				}
			}

			// print out all statistics to the user
			System.out.println(
				"----- COVID-19 Vaccine Statistics of Student " + "-----");
			System.out.println("The number of students who completed all 4 "
				+ "doses: " + fourthDoseStudent);
			System.out.println("The number of students who received three "
				+ "doses, but not the 4th: " + thirdDoseStudent);
			System.out.println("The number of students who received two "
				+ "doses, but not the 3rd: " + secondDoseStudent);
			System.out.println("The number of students who only received the "
				+ "first dose: " + firstDoseStudent);

			System.out.println();

			System.out.println(
				"----- COVID-19 Vaccine Statistics of Employee " + "-----");
			System.out.println("The number of employees who completed all 4 "
				+ "doses: " + fourthDoseEmployee);
			System.out.println("The number of employees who received three "
				+ "doses, but not the 4th: " + thirdDoseEmployee);
			System.out.println("The number of employees who received two "
				+ "doses, but not the 3rd: " + secondDoseEmployee);
			System.out.println("The number of employees who only received the "
				+ "first dose: " + firstDoseEmployee);

			stmt1.close();
			stmt2.close();

		} catch (SQLException e) {

			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}

	/*-------------------------------------------------------------------------
	|  Method handleQuery5
	|
	|  Purpose: Handle the fifth query option, which is "Given the dose number
	|	of COVID-19 (1, 2, 3, or 4), list all of the patients who completed
	|	the vaccine of that dose number."
	|
	|  Pre-condition: dbconn is not null, doseNum is valid
	|
	|  Post-condition: Correct output is displayed for the user
	|
	|  Parameters:
	|	dbconn -- a Connection object representing the connection in JDBC
	|	doseNum -- an int representing the dose number
	|
	|  Returns: Void
	*------------------------------------------------------------------------*/
	private static void handleQuery5(Connection dbconn, int doseNum) {

		String query; // for storing the query
		int curPatientID, count; // for processing the current query

		// for executing and storing the result of the query
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet answer1 = null;
		ResultSet answer2 = null;

		try {

			stmt1 = dbconn.createStatement();
			stmt2 = dbconn.createStatement();

			System.out.println("--- List of Patient who completed COVID-19 "
				+ "vaccine Dose Number " + doseNum + " ---");

			// get all patient IDs that belong to the patients who completed
			// COVID-19 vaccine having input dose num
			query = "SELECT PatientID FROM adlogan.VaxLog WHERE DoseNum = "
				+ doseNum + " AND VirusName = 'COVID-19' AND VaxID "
				+ "IN (SELECT VaxID FROM adlogan.Appointment WHERE "
				+ "CurStatus = 'Complete')";

			answer1 = stmt1.executeQuery(query);

			System.out.println("Patient ID\t\t\tFull Name\t\t\tDate of Birth");
			System.out.println("----------\t\t\t---------\t\t\t-------------");

			// count the number of patients who completed a specific dose
			count = 0;
			if (answer1 != null) {

				while (answer1.next()) {
					curPatientID = answer1.getInt("PatientID");

					// fetch the info of the patient given the patient ID
					query = "SELECT PatientID, FullName, BirthDate FROM "
						+ "adlogan.Patient WHERE PatientID = " + curPatientID;

					answer2 = stmt2.executeQuery(query);

					if (answer2 != null && answer2.next()) {
						System.out.println(curPatientID + "\t\t\t\t"
							+ answer2.getString("FullName") + "\t\t\t\t"
							+ answer2.getDate("BirthDate"));
						count++;

					}

				}
			}
			System.out.println("\nThere are " + count + " people who "
				+ "completed the dose number " + doseNum);

			stmt1.close();
			stmt2.close();

		} catch (SQLException e) {

			System.err.println(
				"*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

}
