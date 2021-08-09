
public class hourlyEmployee extends employee{

	int hours, hourlyWage;
	
	public hourlyEmployee(String fname, String lname, int number, int hourlyWage, int hours) {
		this.fname = fname;
		this.lname = lname;
		this.hours = hours;
		this.hourlyWage = hourlyWage;
	    super.empID = number;
		super.position = "hourlyEmployee";
	
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	
	
}
