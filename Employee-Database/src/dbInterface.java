
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class dbInterface {

	public static void clearDB() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:employee.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DROP TABLE IF EXISTS memployees");
			statement.executeUpdate("DROP TABLE IF EXISTS semployees");
			statement.executeUpdate("DROP TABLE IF EXISTS hemployees");
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) { // Use SQLException class instead.
				System.err.println(e);
			}
		}
	}

	public static void writeDB() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:employee.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DROP TABLE IF EXISTS memployees");
			statement.executeUpdate("DROP TABLE IF EXISTS semployees");
			statement.executeUpdate("DROP TABLE IF EXISTS hemployees");
			statement.executeUpdate("CREATE TABLE memployees (id INTEGER, lname STRING,fname STRING, basepay INTEGER)");
			statement.executeUpdate(
					"CREATE TABLE semployees (id INTEGER, lname STRING,fname STRING, autossold INTEGER, basepay INTEGER)");
			statement.executeUpdate(
					"CREATE TABLE hemployees (id INTEGER, lname STRING,fname STRING, hourlywage INTEGER, hours INTEGER)");
			// int ids [] = {1,2,3,4,5};
			// String names [] = {"Peter","Pallar","William","Paul","James
			// Bond"};

			/*
			 * System.out.println("\nThere are " + managerList.size() +
			 * " managers with bonus multiplied by the " +
			 * formatter.format(mgr.computeMPay(findAutosSold())) +
			 * " autos sold.");
			 */
			Iterator<manager> mitr = empmain.managerList.iterator();
			while (mitr.hasNext()) {
				manager mgr = (manager) mitr.next();

				statement.executeUpdate("INSERT INTO memployees values(' " + mgr.empID + "',' " + mgr.lname + "', '"
						+ mgr.fname + "',' " + mgr.basePay + "')");
			}

			Iterator<sales> sitr = empmain.salesList.iterator();
			while (sitr.hasNext()) {
				sales sales = (sales) sitr.next();

				statement.executeUpdate("INSERT INTO semployees values(' " + sales.empID + "',' " + sales.lname + "', '"
						+ sales.fname + "',' " + sales.autosSold + "',' " + sales.basePay + "')");
			}

			Iterator<hourlyEmployee> hitr = empmain.hourlyList.iterator();
			while (hitr.hasNext()) {
				hourlyEmployee hourly = (hourlyEmployee) hitr.next();

				statement.executeUpdate("INSERT INTO hemployees values(' " + hourly.empID + "',' " + hourly.lname
						+ "', '" + hourly.fname + "',' " + hourly.hourlyWage + "',' " + hourly.hours + "')");
			}

			//
			//
			// //statement.executeUpdate("UPDATE person SET name='Peter' WHERE
			// id='1'");
			// //statement.executeUpdate("DELETE FROM person WHERE id='1'");
			//
			// ResultSet resultSet = statement.executeQuery("SELECT * from
			// person");
			// while(resultSet.next())
			// {
			// // iterate & read the result set
			// System.out.println("name = " + resultSet.getString("name"));
			// System.out.println("id = " + resultSet.getInt("id"));
			// }
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) { // Use SQLException class instead.
				System.err.println(e);
			}
		}
	}

 	public static void readDB() {
		 
    }

	public static void main(String[] args) throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:employee.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DROP TABLE IF EXISTS memployees");
			statement.executeUpdate("DROP TABLE IF EXISTS semployees");
			statement.executeUpdate("DROP TABLE IF EXISTS hemployees");
			statement.executeUpdate("CREATE TABLE memployees (id INTEGER, lname STRING,fname STRING, basepay INTEGER)");
			statement.executeUpdate(
					"CREATE TABLE semployees (id INTEGER, lname STRING,fname STRING, autossold INTEGER, basepay INTEGER)");
			statement.executeUpdate(
					"CREATE TABLE hemployees (id INTEGER, lname STRING,fname STRING, hourlywage INTEGER, hours INTEGER)");
			int ids[] = { 1, 2, 3, 4, 5 };
			String names[] = { "Peter", "Pallar", "William", "Paul", "James Bond" };
			Iterator<manager> mitr = empmain.managerList.iterator();

			/*
			 * System.out.println("\nThere are " + managerList.size() +
			 * " managers with bonus multiplied by the " +
			 * formatter.format(mgr.computeMPay(findAutosSold())) +
			 * " autos sold.");
			 */
			// while (mitr.hasNext()) {
			// manager mgr = (manager) mitr.next();
			//
			// statement.executeUpdate("INSERT INTO person values('
			// "+mgr.empID+"',' "+mgr.lname+"', '"+mgr.fname+"','
			// "+mgr.basePay+"')");
			// }
			//
			//
			// //statement.executeUpdate("UPDATE person SET name='Peter' WHERE
			// id='1'");
			// //statement.executeUpdate("DELETE FROM person WHERE id='1'");
			//
			// ResultSet resultSet = statement.executeQuery("SELECT * from
			// person");
			// while(resultSet.next())
			// {
			// // iterate & read the result set
			// System.out.println("name = " + resultSet.getString("name"));
			// System.out.println("id = " + resultSet.getInt("id"));
			// }
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) { // Use SQLException class instead.
				System.err.println(e);
			}
		}
	}

   
}
