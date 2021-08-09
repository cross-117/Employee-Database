/*
 * 
 *
 *   Abstract classes may or may not contain abstract methods, 
 *   i.e., methods without body ( public void get(); )
 *
 *   But, if a class has at least one abstract method, 
 *   then the class must be declared abstract.
 *
 *   If a class is declared abstract, it cannot be instantiated.
 *
 *   To use an abstract class, you have to inherit it from another class, 
 *   provide implementations to the abstract methods in it.
 *
 *   If you inherit an abstract class, you have to provide implementations 
 *   to all the abstract methods in it.
 * 
 */

public abstract class employee {

	protected String fname;
	protected String lname;
	protected String position;
	protected int empID;
	protected int basePay;

	public employee() {

	}

	public employee(String fname, String lname, String position, int number, int basePay) {
		super();
		/* The below println should never be seen since this abstract class is never instantiated. */
		System.out.println("Constructing an Employee");
		this.fname = fname;
		this.lname = lname;
		this.position = position;
		this.empID = number;
		this.basePay = basePay;
	}

	public int computeMPay(int hourly, int hours) {

		int wage = hourly * hours;

		return wage;
	}

}
