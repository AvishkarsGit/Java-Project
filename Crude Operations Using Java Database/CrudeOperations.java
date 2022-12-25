import java.sql.*;
import java.io.*;

public class CrudeOperations {
    public static void main(String[] args) throws Exception {
        int ch;
        int id, salary;
        String name;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/crude";
        String uname = "root";
        String pass = "system";
        Connection con = DriverManager.getConnection(url, uname, pass);
        PreparedStatement ps;
        Statement st;
        ResultSet rs;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("******** DATABASE MENUS **********");
            System.out.println(" 1. INSERT ROWS");
            System.out.println(" 2. UPDATE ROWS");
            System.out.println(" 3. DELETE ROWS ");
            System.out.println(" 4. DISPLAY ROWS");
            System.out.println(" 5. EXIT");
            System.out.println(" Choose :");
            ch = Integer.parseInt(br.readLine());


            switch (ch) {
                case 1:
                    System.out.println("Enter EMP ID:");
                    id = Integer.parseInt(br.readLine());
                    System.out.println("Enter EMP NAME:");
                    name = br.readLine();
                    System.out.println("Enter EMP SALARY:");
                    salary = Integer.parseInt(br.readLine());
                    String insert = "insert into emp values(?,?,?)";
                    ps = con.prepareStatement(insert);
                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setInt(3, salary);
                    ps.executeUpdate();

                    System.out.println("ROWS INSERTED...");

                    break;
                case 2:
                    st = con.createStatement();
                    rs = st.executeQuery("select * from emp");
                    System.out.println("******* OLD DATA ******");
                    System.out.println("+--------+---------------+-------------+");
                    System.out.printf("|  %-5s |    %-10s |   %-10s| ","ID","NAME","salary");
                    System.out.println();
                    System.out.println("+--------+---------------+-------------+");

                    while (rs.next()) {
                        System.out.printf("|  %-5d |    %-10s |   %-10d| ",rs.getInt(1) , rs.getString(2), rs.getInt(3));
                        System.out.println();
                    }
                    System.out.println("+--------+---------------+-------------+");

                    System.out.println("Enter EMP ID to Update:");
                    id = Integer.parseInt(br.readLine());
                    System.out.println("\tWHAT DO YOU WANT TO UPDATE");
                    System.out.println("\t----------------------------------");
                    System.out.println("\tPRESS 1 TO UPDATE NAME");
                    System.out.println("\tPRESS 2 TO UPDATE ID");
                    System.out.println("\tPRESS 3 TO UPDATE SALARY");
                    System.out.println("\t----------------------------------");
                    System.out.print("\t CHOOSE:");
                    int choice = Integer.parseInt(br.readLine());

                    if (choice == 1) {

                        System.out.println("Enter New Name:");
                        name = br.readLine();
                        String update1 = "update emp set Name = '" + name + "' where ID = " + id;
                        ps = con.prepareStatement(update1);
                        ps.executeUpdate();
                        System.out.println("Name Updated successfully..");
                    } else if (choice == 2) {
                        System.out.println("Enter New ID:");
                        id = Integer.parseInt(br.readLine());
                        String update2 = "update emp set ID = '" + id + "' where ID = " + id;
                        ps = con.prepareStatement(update2);
                        ps.executeUpdate();
                        System.out.println("ID Updated successfully..");
                    } else if (choice == 3) {
                        System.out.println("Enter New Salary:");
                        salary = Integer.parseInt(br.readLine());
                        String update3 = "update emp set Salary = '" + salary + "' where ID = " + id;
                        ps = con.prepareStatement(update3);
                        ps.executeUpdate();
                        System.out.println("Salary Updated successfully..");
                    } else {
                        System.out.println("Enter valid choice!!");
                    }

                    break;
                case 3:

                    System.out.println("****** Your Previous Data ******");
                    String query1 = "select * from emp";
                    st = con.createStatement();
                    rs = st.executeQuery(query1);
                    System.out.println("+--------+---------------+-------------+");
                    System.out.printf("|  %-5s |    %-10s |   %-10s| ","ID","NAME","salary");
                    System.out.println();
                    System.out.println("+--------+---------------+-------------+");

                    while (rs.next()) {
                        System.out.printf("|  %-5d |    %-10s |   %-10d| ",rs.getInt(1) , rs.getString(2), rs.getInt(3));
                        System.out.println();
                    }
                    System.out.println("+--------+---------------+-------------+");
                    System.out.println("Enter Emp ID to delete the data:");
                    id = Integer.parseInt(br.readLine());
                    String delete = "delete from emp where ID = " + id;
                    ps = con.prepareStatement(delete);
                    ps.executeUpdate();
                    System.out.println("Record deleted successfully...");
                    break;
                case 4:
                    String query = "select * from emp";
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    System.out.println("+--------+---------------+-------------+");
                    System.out.printf("|  %-5s |    %-10s |   %-10s| ","ID","NAME","salary");
                    System.out.println();
                    System.out.println("+--------+---------------+-------------+");

                    while (rs.next()) {
                        System.out.printf("|  %-5d |    %-10s |   %-10d| ",rs.getInt(1) , rs.getString(2), rs.getInt(3));
                        System.out.println();
                    }
                    System.out.println("+--------+---------------+-------------+");
                    break;
                case 5:
                    System.out.println("Thanks for using our software");
                    con.close();
                    break;

            }
        }while(ch != 5);

    }
}
