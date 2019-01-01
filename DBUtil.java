package com.ibm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	public static String singleDataQuery(String query) throws SQLException {
		Connection c=DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries","foodsonfinger_atoz","welcome@123");
		Statement s=c.createStatement();
		String text=null;
		//SELECT name from as_category where name="Fruits" - if data exists value else null.
		//SELECT tab_id,name FROM as_tabs ORDER BY tab_id DESC
		//SELECT * from as_coupons
		
		ResultSet rs=s.executeQuery(query);
		while(rs.next()) {
			text=rs.getString(1);//validating name alone
			//System.out.println("database check for added element:"+rs.getString("name"));
		}
		return text;
		//Assert.assertEquals("Grains", rs.getString("name"));
	}
	public  static Object[] lineDataQuery(String query, int[] cols) throws SQLException {
		Object[] data=new Object[cols.length];
		Connection c=DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries",
												"foodsonfinger_atoz",
												"welcome@123");
		
		Statement s=c.createStatement();
		//SELECT * from as_banner where name="Pavithra"
		ResultSet rs=s.executeQuery(query);
		int i=0;
		if(rs.next()){
			for(int col:cols)
			{
			data[i]=rs.getObject(col);
			i++;
			}
		}
		return data;
	}
	
	public static int countQuery(String query) throws SQLException {
		int count=0;
		Connection c=DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries","foodsonfinger_atoz","welcome@123");
		Statement s=c.createStatement();
		//select count(name) from as_coupons
		ResultSet rs=s.executeQuery(query);
		while(rs.next()) {
			count=rs.getInt(1);
		}
		return count;
	
		//Assert.assertEquals("Grains", rs.getString("name"));
	}
	/*
	public ArrayList<ArrayList<Object>> tableDataQuery(String query) throws SQLException{
		Connection c=DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries","foodsonfinger_atoz","welcome@123");
		Statement s=c.createStatement();
		ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
		String[] queries=query.split(",");
		ResultSet rs=s.executeQuery(query);
		ArrayList<Object> temp = null;
		while(rs.next()) {
			//data.add(rs.getString(1), rs.getString(2), rs.getString(3));
			for(int i=0;i<queries.length;i++)
			{
			temp.add(rs.getObject(i));
			}
			data.add(temp);
			temp.clear();
		}
		return data;
		//Assert.assertEquals("Grains", rs.getString("name"));
	}
*/
}
