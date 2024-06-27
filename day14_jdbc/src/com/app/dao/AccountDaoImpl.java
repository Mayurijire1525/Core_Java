package com.app.dao;

import java.sql.*;
import java.sql.Connection;

import static com.app.utils.DBUtils.*;

@SuppressWarnings("unused")
public class AccountDaoImpl implements AccountDao {
	//state
	private Connection cn;
	private CallableStatement cst1;
	
    //def cotr
	public AccountDaoImpl() throws SQLException {
		//get db cn from DB utils
		cn=openConnection();
		//create CST to invoke stored pro
		cst1=cn.prepareCall("{call  transfer_funds_proc(?,?,?,?,?}");
		//register out
		cst1.registerOutParameter(4,Types.DOUBLE);
		cst1.registerOutParameter(5,Types.DOUBLE);
	}


	@Override
	public String transferFunds(int srcAcNo, int destAcNo, double amount) throws SQLException {
		//1.set IN params
		cst1.setInt(1, srcAcNo);
		cst1.setInt(2, destAcNo);
		cst1.setDouble(3, amount);
		
		//2.execute pro
		
		cst1.execute();
		return "Updated src balance"+cst1.getDouble(4)
		+"Dest_balance"+cst1.getDouble(5);
		
		}

	public void cleanUp() throws SQLException{
		if(cst1!=null)
			cst1.close();
		closeConnection();
	}
}
