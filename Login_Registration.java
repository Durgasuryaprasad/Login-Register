import java.sql.*;
import java.util.Scanner;

public class Login_Registration {

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "dsp");
		Scanner s = new Scanner(System.in);
		System.out.println("select Register/Login");
		String input = s.next();
		switch(input) {
		case "Register":
			String query = "insert into UserDetails51 values(?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			System.out.println("Enter name:");
			String name = s.next();
			pst.setString(1, name);
			System.out.println("Enter password");
			String password = s.next();
			pst.setString(2, password);
			System.out.println("Enter First name");
			String FirstName = s.next();
			pst.setString(3, FirstName);
			System.out.println("Enter Last Name");
			String LastName = s.next();
			pst.setString(4, LastName);
			System.out.println("Enter city");
			String city = s.next();
			pst.setString(5, city);
			System.out.println("Enter email");
			String mail = s.next();
			pst.setString(6, mail);
			System.out.println("Enter phone number");
			int phoneNo = s.nextInt();
			pst.setInt(7, phoneNo);
			pst.executeUpdate();
			System.out.println("Registered Successfully");
			break;
			
		case "Login":
			String query1 = "select name,password from UserDetails51";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			System.out.println("Enter name");
			String name1 = s.next();

			System.out.println("Enter password");
			String password1 = s.next();
			int count=0;

			while (rs.next()) {
				
				String a = rs.getString(1);
				String b = rs.getString(2);
				
				if (a.equals(name1) && b.equals(password1)) {
					count++;
					System.out.println("Welcome " + rs.getString(2));
					System.out.println("To ViewProfile");
					System.out.println("EditProfile");
					System.out.println("ViewUser");
					System.out.println("DeleteUser");
					System.out.println("Exit");
					String check = s.next();
					
					switch(check) {
					case "ViewProfile":
						System.out.println(" Your Profile is______________________");
						break;
						
					case "EditProfile":
						System.out.println("City or Mail or Phoneno or Logout");
						String check1 = s.next();
						switch(check1) {
						case "city":
							System.out.println("Enter city name to update");
							String city1 = s.next();
							city1 = "'" + city1 + "'";
							a = "'" + a + "'";
							String query2 = ("update UserDetails51 set city=" + city1 + " where name=" + a);
							PreparedStatement pst1 = con.prepareStatement(query2);
							pst1.executeUpdate();
							System.out.println("Updated successfully");
							break;
							
						case "mail":
							System.out.println("Enter mail to update");
							String mail1 = s.next();
							a = "'" + a + "'";
							mail1 = "'" + mail1 + "'";
							String query3 = ("update UserDetails51 set mail=" + mail1 + " where name=" + a);
							PreparedStatement pst2 = con.prepareStatement(query3);
							pst2.executeUpdate();
							System.out.println("Successfully Updated");
							break;
							
						case "phoneno":
							System.out.println("Enter phone number to update");
							int phoneno1 = s.nextInt();
							a = "'" + a + "'";
							String query4 = ("update UserDetails51 set phoneNo=" + phoneno1 + " where name=" + a);
							PreparedStatement pst3 = con.prepareStatement(query4);
							pst3.executeUpdate();
							System.out.println("Updated successfully");
							break;
							
						case "Logout":
							System.out.println("You are logged out");
							break;
							
						default:
                            System.out.println("Invalid choice");

						}
						
					case "ViewUser":
					    a = "'" + a + "'";
					    String query2 = "SELECT * FROM UserDetails51 WHERE name = " + a;
					    PreparedStatement pst4 = con.prepareStatement(query2);
					        ResultSet rs1 = pst4.executeQuery();

					        while (rs1.next()) {
					            System.out.println("User Details:");
					            System.out.println("Name: " + rs1.getString("name"));
					            //System.out.println("Password: " + rs1.getString("password"));
					            System.out.println("First Name: " + rs1.getString("firstname"));
					            System.out.println("Last Name: " + rs1.getString("lastname"));
					            System.out.println("City: " + rs1.getString("city"));
					            System.out.println("Email: " + rs1.getString("mail"));
					            System.out.println("Phone Number: " + rs1.getInt("phoneNo"));
					        } 
					    
					    break;

						 
						 
					case "DeleteUser":
						a="'"+a+"'";
						String query3 = ("delete from UserDetails51 where name="+a);
						PreparedStatement pst5 = con.prepareStatement(query3);
						pst5.executeUpdate();
						System.out.println("User is deleted successfully");
						break;
						
						
					case "Exit":
						System.out.println("You are exit");
						break;
						
					default:
                        System.out.println("Invalid choice");
						
							}
					}
				
			}

			
			if(count==0) {
				System.out.println("Invalid name/password");
			}
			}

		
		con.close();

	}

}

	
