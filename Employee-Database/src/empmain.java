//import java.io.Buffer;

/* Writing and reading file using FileReader/FileWriter and buffer */
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
/* Writing and reading to file using scanner */
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * 
 * Employee positions
 * 		Manager ( base pay plus bonuses for targets met, keep array of bonus money per month )
 * 		Sales ( base pay plus commissions, keep array of cars sold per month )
 * 		Mechanic ( hourly pay by number of hours worked, keep wage and array of hours worked )
 * 		Other ( hourly pay by number of hours worked, keep wage and array of hours worked )
 * 
 * Main class variables and constants
 * 		empfileloc			location of file of employees
 * 		mgmtBonus			Bonus for managers if quotas are reached, added to base pay
 * 		salesCommission		Commission for each auto sold, added to base pay
 * 		mgmtBasePay
 * 		salesBasePay
 * 
 * XYZ Employee file format, each entry is on a new line
 * 		?Manager base salary
 * 		?Sales personnel base salary
 * 		Employee ID
 * 		Position
 * 		Lastname
 * 		Firstname
 * 		Pay increment depending on position
 * 			Managers = no entry here, increment is based on totat qty of autos sold
 * 			Sales = number or automobiles sold
 * 			Hourly = hours worked for that month and hourly wage
 * 
 * When a employee position class is instantiated with the following
 * 		Employee first and last names
 * 		Employee ID
 * 		Managers	Need nothing extra
 * 		Sales		Autos sold
 * 		Other		Hourly wage
 */

public class empmain {

	// public static ArrayList<employee> employeeList = new ArrayList<>();
	public static ArrayList<hourlyEmployee> hourlyList = new ArrayList<>();
	public static ArrayList<manager> managerList = new ArrayList<>();
	public static ArrayList<sales> salesList = new ArrayList<>();
	/* May need to double backslash \\ if using absolute path */
	final static String empfileloc = "xyzEmployees.txt";
	final static String empfilelocLinux = "xyzEmployees.txt";
	final static String empfilelocWindows = "xyzEmployees.txt";

	private static int mgmtBonus = 1000;
	private static int salesCommission = 250;
	private static int mgmtBasePay = 4000;
	private static int salesBasePay = 1000;

	/**
	 * Read file of employees
	 * 
	 * @param args
	 */
	private static void readFileScanner(String empfileloc) throws IOException {

		String employeeType, fname, lname;
		int employeeID, autosSold, hours, hourlyWage;

		try {
			File file = new File(empfileloc);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				employeeType = sc.nextLine();
				employeeID = sc.nextInt();
				sc.nextLine();
				lname = sc.nextLine();
				fname = sc.nextLine();

				/* System.out.println(sc.nextLine()); */
				switch (employeeType) {
				case "manager": {
					mgmtBasePay = sc.nextInt();
					sc.nextLine();
					System.out.println("Manager employee " + fname + " " + lname + " employee ID " + employeeID + " read.");
					manager newEmployee = new manager(fname, lname, employeeID, mgmtBasePay);
					managerList.add(newEmployee);
					break;
				}
				case "sales": {
					autosSold = sc.nextInt();
					sc.nextLine();
					salesBasePay = sc.nextInt();
					sc.nextLine();
					System.out.println("Sales employee " + fname + " " + lname + " employee ID " + employeeID + " read.");
					System.out.println(fname + " has " + autosSold + " sales.");
					sales newEmployee = new sales(fname, lname, employeeID, salesBasePay, autosSold);
					salesList.add(newEmployee);
					break;
				}
				case "hourlyEmployee": {
					hourlyWage = sc.nextInt();
					sc.nextLine();
					hours = sc.nextInt();
					sc.nextLine();
					System.out.println(
							"Hourly employee " + fname + " " + lname + " employee ID " + employeeID + " read.");
					System.out.println(
							fname + " recieves " + hourlyWage + " dollars hourly and worked " + hours + " hours this month.");
					hourlyEmployee newEmployee = new hourlyEmployee(fname, lname, employeeID, hourlyWage, hours);
					hourlyList.add(newEmployee);
					break;
				}
				default: {
					System.out.println("This is not a valid employee type.");
				}
				System.out.println("\n");
				} /* End case */

			} /* End while */

			sc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		managerList.toString();
		salesList.toString();
		hourlyList.toString();
	}

	/**
	 * Read file of employees
	 */
	public static void readBufferedFile(String empfileloc) {

		// This will reference one line at a time
		String line, lname, fname, position;  
		int employeeID, autoSales, hours, hourlyWage;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = 
					new FileReader(empfileloc);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {


				employeeID = Integer.parseInt(bufferedReader.readLine());
				//position = bufferedReader.readLine();
				lname = bufferedReader.readLine();
				fname = bufferedReader.readLine();

				switch(line){

				case "manager":

					mgmtBasePay = Integer.parseInt(bufferedReader.readLine());
					manager newManager = new manager(fname, lname, employeeID, mgmtBasePay);
					System.out.println("Manager " + fname + " " + lname + " employee ID " + employeeID + " basepay "+mgmtBasePay+" read.");
					managerList.add(newManager);
					//System.out.print("managers: "+ managerList.size()+"\n");
					break;

				case "sales":


					autoSales = Integer.parseInt(bufferedReader.readLine());
					salesBasePay = Integer.parseInt(bufferedReader.readLine());
					sales newSales = new sales(fname, lname, employeeID, autoSales, salesBasePay);
					System.out.println("Sales " + fname + " " + lname + " employee ID " + employeeID +  " basepay "+salesBasePay+" read.");
					salesList.add(newSales);
					//System.out.print("sales: "+ salesList.size()+"\n");
					break;

				case "hourlyEmployee":


					hourlyWage = Integer.parseInt(bufferedReader.readLine());
					hours = Integer.parseInt(bufferedReader.readLine());
					hourlyEmployee newHourly = new hourlyEmployee(fname, lname, employeeID, hours, hourlyWage);
					System.out.println("Hourly " + fname + " " + lname + " employee ID " + employeeID +" hours and hourlyWage "+ hours+", "+hourlyWage+" read.");
					hourlyList.add(newHourly);
					//System.out.print("hourly: "+hourlyList.size()+"\n");
					break;

				default: System.out.println("Not a valid type of employee.");

				}

				//				if(bufferedReader.readLine() == "manager" || bufferedReader.readLine() == "sales" || bufferedReader.readLine() == "other"){
				//					System.out.print(line+" ");
				//				}else{
				//					System.out.print(line+" ");
				//				}

			}   

			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							empfileloc + "'");                
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ empfileloc + "'");                  

		}
	}

	/**
	 * Write file of employees
	 * @param args
	 * @throws IOException
	 */
	private static void writeEmpFile(String empfileloc) throws IOException {
		String choice;
		File file = new File(empfileloc);
		Scanner input = new Scanner(System.in);

		/*
		 * If file exists ask before obliterating
		 */

		if (file.exists()) {
			System.out.println("The file " + empfileloc + " exists.");
			System.out.println("Do you want to overwrite or not? y\\yes or n\\no");
			choice = input.nextLine();
			if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
				return; /*
				 * Choosing not to write over the file, exiting method
				 */
			} else if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
				System.out.println("Writing over file.");
			} else {
				System.out.println("Sorry, don't understand response, ");
				return;
			}
		}

		FileWriter fWriter = new FileWriter(empfileloc);

		try {
			/*
			 * Could use iterator of arraylist instead of for loop.
			 * 
			 * traverse elements of ArrayList object Iterator itr=al.iterator();
			 * 
			 * while(itr.hasNext()){ Student st=(Student)itr.next();
			 * System.out.println(st.rollno+" "+st.name+" "+st.age); }
			 * 
			 */
			for (int i = 0; i < managerList.size(); i++) {
				fWriter.write(managerList.get(i).position);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(managerList.get(i).empID));
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(managerList.get(i).lname);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(managerList.get(i).fname);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(managerList.get(i).basePay));
				fWriter.write(System.getProperty("line.separator"));
			}
			for (int i = 0; i < salesList.size(); i++) {
				fWriter.write(salesList.get(i).position);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(salesList.get(i).empID));
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(salesList.get(i).lname);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(salesList.get(i).fname);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(salesList.get(i).autosSold));
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(salesList.get(i).basePay));
				fWriter.write(System.getProperty("line.separator"));
			}
			for (int i = 0; i < hourlyList.size(); i++) {
				fWriter.write(hourlyList.get(i).position);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(hourlyList.get(i).empID));
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(hourlyList.get(i).lname);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(hourlyList.get(i).fname);
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(hourlyList.get(i).hourlyWage));
				fWriter.write(System.getProperty("line.separator"));
				fWriter.write(Integer.toString(hourlyList.get(i).hours));
				fWriter.write(System.getProperty("line.separator"));
			}

			fWriter.close();

		} catch (Exception e) {
			System.out.println("Could not create file");
		}
	}

	/*
	 * Method to list employees of the company
	 */
	private static void listEmployees() {

		System.out.println("List the employees");
		if ((managerList.size() + salesList.size() + hourlyList.size()) == 0) {
			System.out.println("There are no employees.");
			return;

		}
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		Iterator<manager> mitr = managerList.iterator();

		System.out.println("\n" + managerList.size() + " managers");

		while (mitr.hasNext()) {
			manager mgr = (manager) mitr.next();
			System.out.println(mgr.lname + ", " + mgr.fname + " employee ID " + mgr.empID);
		}

		Iterator<sales> sitr = salesList.iterator();
		System.out.println("\n" + salesList.size() + " sales persons");
		while (sitr.hasNext()) {
			sales sales = (sales) sitr.next();
			System.out.println(sales.lname + ", " + sales.fname + " employee ID " + sales.empID);
		}

		Iterator<hourlyEmployee> hitr = hourlyList.iterator();
		System.out.println("\n" + hourlyList.size() + " hourly employees");
		while (hitr.hasNext()) {
			hourlyEmployee hourly = (hourlyEmployee) hitr.next();
			System.out.println(hourly.lname + ", " + hourly.fname + " employee ID " + hourly.empID);
		}
	}

	/*
	 * Method to list payroll for company
	 */
	private static void payRoll() {

		int mgmtTotal = 0;
		int salesTotal = 0;
		int hourlyTotal = 0;

		System.out.println();
		System.out.println("=========================================================================");
		System.out.println("Company XYZ Monthly Payroll");
		System.out.println("=========================================================================");

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		Iterator<manager> mitr = managerList.iterator();

		/*
		 * System.out.println("\nThere are " + managerList.size() +
		 * " managers with bonus multiplied by the " +
		 * formatter.format(mgr.computeMPay(findAutosSold())) + " autos sold.");
		 */
		// System.out.println("------------------------------------------------------------------------");
		System.out.printf("%25s\n", "Managers Payroll");
		System.out.println("=========================");
		System.out.printf("%12s%20s,%20s%20s\n", "Employee ID", "Last Name", "First Name", "Monthly Wage");
		System.out.println("-------------------------------------------------------------------------");
		while (mitr.hasNext()) {
			manager mgr = (manager) mitr.next();
			// System.out.println(mgr.lname + ", " + mgr.fname + " employee ID "
			// + mgr.empID
			// + formatter.format(mgr.computeMPay(findAutosSold(), mgmtBonus)));

			System.out.printf("%12d%20s,%20s%20s\n", mgr.empID, mgr.lname, mgr.fname,
					formatter.format(mgr.computeMPay(findAutosSold(), mgmtBonus)));
			mgmtTotal = mgmtTotal + mgr.computeMPay(findAutosSold(), mgmtBonus);
		}
		System.out.printf("%50s%23s \n", "Manager total wages: ",formatter.format(mgmtTotal));
		System.out.println(findAutosSold() + " autos were sold, managers bonus for each auto sold is "
				+ formatter.format(mgmtBonus));

		Iterator<sales> sitr = salesList.iterator();
		// System.out.println("\n" + salesList.size() + " sales persons");
		System.out.printf("\n\n%25s\n", "Sales Employee Payroll");
		System.out.println("=========================");
		System.out.printf("%12s%20s,%20s%20s\n", "Employee ID", "Last Name", "First Name", "Monthly Wage");
		System.out.println("-------------------------------------------------------------------------");
		while (sitr.hasNext()) {
			sales sales = (sales) sitr.next();
			// System.out.println(sales.lname + ", " + sales.fname + " employee
			// ID " + sales.empID
			// + formatter.format(sales.computeMPay(salesCommission)));

			System.out.printf("%12d%20s,%20s%20s\n", sales.empID, sales.lname, sales.fname,
					formatter.format(sales.computeMPay(salesCommission)));
			salesTotal = salesTotal + sales.computeMPay(findAutosSold());
			// System.out.println("-------------------------------------------------------------------------");
		}
		System.out.printf("%50s%23s \n", "Sales total wages: ",formatter.format(salesTotal));
		System.out.println(findAutosSold() + " autos were sold, sales wage is basePay * total auto sold is "
				+ formatter.format(salesTotal));
		Iterator<hourlyEmployee> hitr = hourlyList.iterator();
		// System.out.println("\n" + hourlyList.size() + " hourly employees");
		System.out.printf("\n\n%25s\n", "Hourly Employee Payroll");
		System.out.println("=========================");
		System.out.printf("%12s%20s,%20s%20s\n", "Employee ID", "Last Name", "First Name", "Monthly Wage");
		while (hitr.hasNext()) {
			hourlyEmployee hourly = (hourlyEmployee) hitr.next();
			// System.out.println(hourly.lname + ", " + hourly.fname + "
			// employee ID " + hourly.empID
			// + formatter.format(hourly.computeMPay(hourly.hourlyWage,
			// hourly.hours)));
			// System.out.println("-------------------------------------------------------------------------");
			System.out.printf("%12d%20s,%20s%20s\n", hourly.empID, hourly.lname, hourly.fname,
					formatter.format(hourly.computeMPay(hourly.hourlyWage, hourly.hours)));
		}
		System.out.println("=========================================================================");
	}

	/*
	 * Method to find next largest employee number so new employees can use
	 * largest + 1
	 */
	private static int findNext() {
		int num = 0;
//		Iterator<manager> mitr = managerList.iterator();
//		while (mitr.hasNext()) {
//			if (num < mitr.next().empID) {
//				num = mitr.next().empID;
//				//return num;
//			}
//		}
//
//		Iterator<sales> sitr = salesList.iterator();
//		while (sitr.hasNext()) {
//			if (num < sitr.next().empID) {
//				num = sitr.next().empID;
//				//return num;
//			}
//		}
//
//		Iterator<hourlyEmployee> hitr = hourlyList.iterator();
//		while (hitr.hasNext()) {
//			if (num < hitr.next().empID) {
//				num = hitr.next().empID;
//				//return num;
//			}
//		}
//		System.out.println("Largest employee number is " + num);
		return num;
	}
	
	/*
	 * Method to clear text file database
	 */
	private static void clearFile(){
		
		
		
	}

	/*
	 * Method to find the total autos sold, managers bonus is based off of this
	 */
	private static int findAutosSold() {
		int num = 0;

		/*
		 * Call collection in this case array lists iterator method In this case
		 * sitr is the iterator object
		 */
		Iterator<sales> sitr = salesList.iterator();

		// Call the hasNext method of the iterator object to see if this is
		// another object in the list
		while (sitr.hasNext()) {
			num = num + sitr.next().autosSold;
		}

		// System.out.println("Total number of automobiles sold " + num);
		return num;
	}

	/*
	 * Clear data structures
	 */
	private static void clearData() {
		managerList.clear();
		salesList.clear();
		hourlyList.clear();
	}

	/*
	 * 
	 * empmain main class
	 * 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		int choice;
		String firstname, lastname;
		String position = "";
		int employeeID = 0;
		boolean exitloop = false;
		int autosSold;
		int hourlyWage;
		int hours;
		Scanner input = new Scanner(System.in);

		do {
			autosSold = 0;
			hourlyWage = 0;
			exitloop = false;
			managerList.toString();
			salesList.toString();
			hourlyList.toString();

			System.out.println("\nChoose Option.");
			System.out.println("1) Add employee.");
			System.out.println("2) Read employee file using ReadBuffered.");
			System.out.println("3) Read employee file using Scanner.");
			/* Only write file if we have objects in the employee array */
			System.out.println("4) Write employee file.");
			System.out.println("5) Write employees to database.");
			System.out.println("6) Read employees from database.");
			System.out.println("7) List employees.");
			System.out.println("8) Generate payroll.");
			System.out.println("9) Clear data structures.");
			System.out.println("10) Clear data database.");
			System.out.println("11) Clear text file database.");
			System.out.println("12) Exit Program");
			choice = input.nextInt();
			switch (choice) {
			/*
			 * Add an employee
			 * 
			 */
			case 1: {
				System.out.println("Adding employee " + "\n" + "Ok I need to ...");
				System.out.println("What is the employees first name? ");
				firstname = input.next();
				System.out.println(firstname);
				System.out.println("What is the employees last name? ");
				lastname = input.next();
				System.out.println(lastname);

				System.out.println("What Position, Choose Option");
				System.out.println("1) Manager");
				System.out.println("2) Sales");
				System.out.println("3) Hourly");
				choice = input.nextInt();
				if (choice == 1) {
					position = "Manager";
					/*
					 * Create new manager object
					 */
					employeeID++;
					manager newEmployee = new manager(firstname, lastname, employeeID, mgmtBasePay);
					managerList.add(newEmployee);
				} else if (choice == 2) {
					position = "Sales";
					boolean choiceOK = false;
					do {
						System.out.println("\n" + "How many autos did slipper sales person sell?");
						autosSold = input.nextInt();
						if (autosSold > 0 || autosSold < 100) {
							System.out.println("\n" + "Whoa, greate job!");
							choiceOK = true;
						} else {
							System.out.println("\n" + "Unacceptable, enter again.");
						}
					} while (!choiceOK);
					/*
					 * Create new sales person object
					 */
					employeeID++;
					sales newEmployee = new sales(firstname, lastname, employeeID, salesBasePay, autosSold);
					salesList.add(newEmployee);
				} else if (choice == 3) {
					position = "Hourly";
					boolean choiceOK = false;
					do {
						System.out.println("\n" + "What is the hourly wage?");
						hourlyWage = input.nextInt();
						if (hourlyWage > 0 || hourlyWage < 20) {
							/*
							 * System.out.println("\n" + "Whoa, greate job!");
							 */
							choiceOK = true;
						} else {
							System.out.println("\n" + "Unacceptable, enter again.");
						}
					} while (!choiceOK);
					choiceOK = false;
					do {
						System.out.println("\n" + "How many hours worked?");
						hours = input.nextInt();
						if (hours >= 0 || hours < 200) {
							/* System.out.println("\n" + "OK"); */
							choiceOK = true;
						} else {
							System.out.println("\n" + "Unacceptable, enter again.");
						}
					} while (!choiceOK);

					/*
					 * Create new other object
					 */
					employeeID++;
					hourlyEmployee newEmployee = new hourlyEmployee(firstname, lastname, employeeID, hourlyWage, hours);
					hourlyList.add(newEmployee);
				} else {
					System.out.println("\n" + "Sorry that is not one of the options try again.");
					break;
				}

				System.out.println("\n" + "Employee is a " + position);

				System.out.println("\n" + "Generating new employee number");
				System.out.println("\n" + lastname + ", " + firstname);
				System.out.println("\n" + position);
				System.out.println("\n" + "Employee ID " + employeeID);

				/*
				 * Call new employee id generator and print message stating what
				 * it is.
				 */

				break;

			}
			/*
			 * 
			 * Read employee file using Scanner
			 * 
			 */
			case 2: {
				System.out.println("Read employee file using BufferedRead");
				readBufferedFile(empfileloc);
				/*
				 * Read Employee first name Employee second name Employee
				 * position
				 * 
				 */

				employeeID = findNext();
				break;
			}
			/*
			 * 
			 * Read employee file using Scanner
			 * 
			 */
			case 3: {
				System.out.println("Read employee file using Scanner");
				readFileScanner(empfileloc);
				employeeID = findNext();
				break;
			}
			case 4: {
				System.out.println("Writing employee file");
				// try {
				writeEmpFile(empfileloc);
				// } catch (IOException e) {
				// System.out.println("File not found");
				// e.printStackTrace();
				// }
				break;
			}
			case 5: {
				System.out.println("Writing employees to database");
				dbInterface.writeDB();
				break;
			}
			case 6: {
				
				System.out.println("Reading employees from database");
				dbInterface.readDB();
				break;
			}


			case 7: {
				/* System.out.println("List employees"); */
				listEmployees();
				break;
			}
			case 8: {
				// System.out.println("Generate Payroll");
				payRoll();
				break;
			}
			case 9: {
				System.out.println("Clearing data structures");
				clearData();
				break;
			}
			case 10: {
				System.out.println("Clearing data database");
				dbInterface.clearDB();
				break;
			}
			case 11: {
				System.out.println("Clearing text file database");
				clearFile();
				break;
			}
			case 12: {
				System.out.println("Exiting Program");
				exitloop = true;
				break;
			}
			default:
				System.out.println("Not one of the choices, Try again.");
			}

		} while (!exitloop);

		input.close();

	}

}
