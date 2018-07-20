package com.beastbot.list;

import java.util.Date;

public class Tradeinfo {
	int id;
	String fname, ordertype, way, clientorderid;
	Date fst;
	double price;
	
	public Tradeinfo(int id, String fname, String ordertype, String way, Date fst, String clientorderid, double price)
	{
		this.id = id;
		this.fname = fname;
		this.ordertype = fname;
		this.way= way;
		this.fst = fst;
		this.clientorderid = clientorderid;
		this.price = price ;
	}
	public int getid()
	{
		return id;
	}
	public String getFname()
	{
		return fname;
	}
	public String getOrdertype()
	{
		return ordertype;
	}
	public String getway()
	{
		return way;
	}
	public Date getfst()
	{
		return fst;
	}
	public String getclientorderid()
	{
		return clientorderid;
	}
	public double getPrice()
	{
		return price;
	}
	public String[] getData()
	{
	    return new String[]{String.valueOf(id),fname,ordertype, way,fst.toString(),clientorderid ,String.valueOf(price)};
	}
}
