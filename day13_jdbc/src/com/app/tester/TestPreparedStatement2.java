package com.app.tester;
import java.sql.*;
import java.util.Scanner;

import static com.app.utils.DBUtils.openConnection;
//user login
public class TestPreparedStatement2 {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				// get db connection
				Connection cn=openConnection();
				//create pre-compiled stmt,to hold SQL in=?
				PreparedStatement pst=cn.prepareStatement("select *from users where role=? and dob between ? and ?")){
				
				System.out.println("Enter role and dob");
				//set IN params:public vooid setType(int paramIndex,Type value)throws SQLException
				pst.setString(1,sc.next());//role
				pst.setDate(2,Date.valueOf(sc.next()));//start dob
				pst.setDate(3,Date.valueOf(sc.next()));//end dob
				//exec query
				try(ResultSet rst=pst.executeQuery()){
					while(rst.next())
						//ResultSet method:Type(int colPos/String colName)throws SQLException
//						System.out.println("Login successful ! Hello,"+rst.getString("first_name")+" "+rst.getString("last_name")+" "+
//						rst.getString("role")+" "+rst.getDate("dob"));//2 &3 col position as it appears in resultset
						
//						OR
						
						
					    System.out.println("Login successful ! Hello,"+rst.getString(2)+" "+rst.getString(3)+" "+
							rst.getString(8)+" "+rst.getDate(6));
					    
				}//rst.close()
		
		}//JVM : pst.close(),cn.close() cleaning up of DB resources
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
