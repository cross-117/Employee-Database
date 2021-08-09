
public class manager extends employee{

//	protected String fname;
//	protected String lname;
//	protected int empID;
	protected int basePay;
	public manager() {


	}

	public manager(String fname, String lname, int number, int basePay){

		//System.out.println("Constructing an Employee");
		this.fname = fname;
		this.lname = lname;
		this.basePay = basePay;
		super.empID = number;
		super.position = "manager";


	}

	public int computeMPay(int autosSold, int bonus) {

		int monthlyPay = basePay + (bonus * autosSold);

		return monthlyPay;
	}
	
}
