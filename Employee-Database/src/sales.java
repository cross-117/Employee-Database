
public class sales extends employee{

	int autosSold;
	int basePay;
	
	public sales(String fname, String lname, int number, int basePay, int autosSold) {

		this.fname = fname;
		this.lname = lname;
		this.basePay = basePay;
		this.autosSold = autosSold;
		super.empID = number;
		super.position = "sales";
		
	}

	
	public int computeMPay(int autosSold) {

		int monthlyPay = basePay * autosSold;

		return monthlyPay;
	}
	
	
}
