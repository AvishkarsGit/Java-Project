import java.sql.*;
import java.io.*;
class FirstJDBC
{
	public static void main(String args[])
	{
		try
		{					

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/java";
			String username="root";
			String password="system";
			Connection con=DriverManager.getConnection(url,username,password);

			PreparedStatement pstm;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int choice;
			System.out.println("********************************");
			System.out.println("**** Crime Record Management ***");
			System.out.println("********************************");
			System.out.print("Enter USER NAME:");
			String uname=br.readLine();
			System.out.print("Enter PASSWORD:");
			String pass=br.readLine();
			if("Avishkar".equals(uname) && "pass@123".equals(pass))
			{	
				System.out.println("\nLoggin Successefully...");
				do
				{
					
					System.out.println("\n   MENUS ");
					System.out.println("---------------------------");	
					System.out.println("1.Add new Criminal");
					System.out.println("2.Display all record");
					System.out.println("3.Search Record");
					System.out.println("4.Delete Record");
					System.out.println("5.Update Record");
					System.out.println("6.Exit");
					System.out.print("Enter your choice:");
					choice=Integer.parseInt(br.readLine());
					switch(choice)
					{
						case 1:
						System.out.println("Enter Criminal Name:");
						String name=br.readLine();
						System.out.println("Enter Criminal crime:");
						String crime=br.readLine();
						String q="insert into criminal(name,crime) values (?,?)";
						pstm = con.prepareStatement(q);
						pstm.setString(1,name);
						pstm.setString(2,crime);
						pstm.executeUpdate();
						System.out.println("------------------------------------------------");
						System.out.println("    Record Saved Successefully....");
						System.out.println("------------------------------------------------");
						break;
						case 2:
							Statement statement=con.createStatement();
							String p="Select * from criminal";
							ResultSet rs = statement.executeQuery(p);
							System.out.println("-----------------------------------------------------------------------");
							System.out.printf("%-10s %-20s %-15s\n","ID","NAME","CRIME");
							System.out.println("-----------------------------------------------------------------------");							while(rs.next())
							{
								System.out.printf("%-10s %-20s %-15s\n",rs.getInt(1),rs.getString(2),rs.getString(3));
							}
							System.out.println("-----------------------------------------------------------------------");
							break;
						case 3:
						try
						{
								System.out.print("Enter criminal ID to search:");
								int ID =Integer.parseInt(br.readLine());
						 
								String s="SELECT * FROM CRIMINAL WHERE ID = '"+ID+"'";
								statement = con.createStatement();
								rs = statement.executeQuery(s);
								while(rs.next()) 
								{
								ID = rs.getInt("ID");
								name = rs.getString("name");
								crime = rs.getString("crime");
							System.out.printf("%-10s %-20s %-15s\n ","ID","NAME","CRIME");
							System.out.println("----------------------------------------------------------"); 
							System.out.printf("%-10d %-20s %-15s\n",rs.getInt(1),rs.getString(2),rs.getString(3));
							System.out.println("----------------------------------------------------------"); 
								}
						}catch(Exception e)
						{
							System.out.println(e);
						}
							break;
						case 4:
							try
						{
								System.out.print("Enter criminal ID to search:");
								int ID =Integer.parseInt(br.readLine());
								
								String d="delete from criminal where ID='"+ID+"'";
								pstm = con.prepareStatement(d);
								int row = pstm.executeUpdate(d);
								if(row>0) {
									System.out.println("Record Deleted Successefully ");
									System.out.println("--------------------------------------------------------------------");
								}
								else {
									System.out.println("No such Record Exist");
									System.out.println("-------------------------------------------------------------------");
								}
						}catch(Exception e)
						{
							System.out.println(e);
						}
							break;
						case 5:
						try{
								System.out.print("Enter criminal ID to Update Record:");
								int ID =Integer.parseInt(br.readLine());
								
								String s="SELECT * FROM CRIMINAL WHERE ID = '"+ID+"'";
								statement = con.createStatement();
								rs = statement.executeQuery(s);
								while(rs.next()) {
								ID = rs.getInt("ID");
								name = rs.getString("name");
								crime = rs.getString("crime");
								System.out.printf("%-10s %-20s %-15s\n ","ID","NAME","CRIME");
								System.out.println("----------------------------------------------------------"); 
								System.out.printf("%-10d %-20s %-15s\n",rs.getInt(1),rs.getString(2),rs.getString(3));
								System.out.println("----------------------------------------------------------"); 
								}
								System.out.println("1.Update Name");
								System.out.println("2.Update Crime:");
								int ch=Integer.parseInt(br.readLine());
								if(ch == 1)
								{
									System.out.println("Enter Updated Criminal Name:");
									name=br.readLine();
									String sql="update criminal set name='"+name+"' where ID = "+ID;
								
									pstm=con.prepareStatement(sql);
									int row = pstm.executeUpdate();
									if(row>0)	
									{
										System.out.println("Updated Successefully...");
									}
									else
									{
										System.out.println("Something went wrong..");
									}	
								}
								else if(ch ==  2)
								{
									System.out.println("Enter Updated Criminal crime:");
									crime=br.readLine();
									String sql="update criminal set crime='"+crime+"' where ID = "+ID;
								
									pstm=con.prepareStatement(sql);
									int row = pstm.executeUpdate();
									if(row>0)	
									{
										System.out.println("Updated Successefully...");
									}
									else
									{
										System.out.println("Something went wrong..");
									}
								}
								else
								{
									System.out.println("Enter valide choice....!");
								}
								

						}catch(Exception e)
						{
							System.out.println(e);
						}
						break;
								
						case 6:System.out.println("Thanks");
							break;	
						default:System.out.println("Enter Valid choice !!");
					}
				}while(choice!=6);	
				con.close();
		}	
		else
		{
			System.out.println("Invalid UserName or Password..");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
