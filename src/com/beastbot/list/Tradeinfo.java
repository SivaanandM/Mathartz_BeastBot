package com.beastbot.list;

public class Tradeinfo {
	int id;
	String fname, ordertype, way, fst, clientorderid;
	double price;
	
	public Tradeinfo(int id, String fname, String ordertype, String way, String fst, String clientorderid, double price)
	{
		this.id = id;
		this.fname = fname;
		this.ordertype = fname;
		this.way= way;
		this.fst = fst;
		this.clientorderid = clientorderid;
		this.price = price ;
	}
	public int getId()
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
	public String getfst()
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
	    return new String[]{String.valueOf(id),fname,ordertype, way,fst,clientorderid ,String.valueOf(price)};
	}
}
