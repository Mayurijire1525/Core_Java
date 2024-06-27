package com.app.tester;
import java.sql.*;
import java.util.Scanner;

import static com.app.utils.DBUtils.openConnection;
//user login
public class TestPreparedStatement {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				// get db connection
				Connection cn=openConnection();
				//create pre-compiled stmt,to hold SQL in=?
				PreparedStatement pst=cn.prepareStatement("select *from users where email=? and password=?")){
				
				System.out.println("Enter email and password");
				//set IN params:public vooid setType(int paramIndex,Type value)throws SQLException
				pst.setString(1,sc.next());//email 1st->? position
				pst.setString(2,sc.next());//pass  2nd->? position
				//exec query
				try(ResultSet rst=pst.executeQuery()){
					if(rst.next())
						//ResultSet method:Type(int colPos/String colName)throws SQLException
						System.out.println("Login successful ! Hello,"+rst.getString(2)+" "+rst.getString(3));//2 &3 col position as it appears in resultset
					    //System.out.println("Login successful ! Hello,"+rst.getString("first_name")+" "+rst.getString(3));
					else
						System.out.println("Invalid login, email and password Invalid!!!!!");
				}//rst.close()
		
		}//JVM : pst.close(),cn.close() cleaning up of DB resources
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
