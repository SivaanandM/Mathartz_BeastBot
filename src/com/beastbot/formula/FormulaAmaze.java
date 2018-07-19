package com.beastbot.formula;

import com.beastbot.common.CommonObjects;
import com.beastbot.list.Amazevalues;
import com.beastbot.list.FormulaData;
import com.beastbot.list.getListcommon;

public class FormulaAmaze {
	//amaze execution related data
	int identity,c,lc,r;
	String headsecid,playersecid,way;
	double ltp, points,low,high,nextline,baseline;
	long ltt;
	//Amaze formula inputs data
	double x,y;
	String st,mt,et;
	int lcount, round, qty;
	Boolean tradeswitch;
	
	getListcommon listcom = new getListcommon();
	
	public void FormulaAmazeDriver(String [] ids, double LTP, long LTT)
	{
		try
		{
			for (String id : ids) 
			{
				identity = Integer.valueOf(id);
				ltp= LTP;
				ltt=LTT;
				AssignvVariables();
			}
		}
		catch(Exception ex)
		{
			
		}
		finally 
		{
			
		}
	}
	public void AssignvVariables()
	{
		try
		{
			Amazevalues fvalue = listcom.getAmazeValuesByID(identity, CommonObjects.GlobalAmazeValues);
			c = fvalue.getc();
			lc = fvalue.getlc();
			r = fvalue.getr();
			headsecid = fvalue.getheadsecid() ;
			playersecid = fvalue.getplayersecid();
			way= fvalue.getway();
			points = fvalue.getpoints();
			low = fvalue.getlow();
			high = fvalue.gethigh();
			nextline = fvalue.getnextline();
			baseline = fvalue.getbaseline();
			
			FormulaData finput = listcom.getFormulaDataByID(identity,  CommonObjects.GlobalAmazeFormulaList);
			
			x =finput.getX();
			y = finput.getY();
			st = finput.getST();
			mt = finput.getMT();
			et = finput.getET();
			lcount = (int) finput.getLcount();
			round = (int) finput.getRound();
			qty = finput.getQty();
			tradeswitch = finput.getTradeswitch();
				
			
		}
		catch(Exception ex)
		{
			
		}
		finally 
		{
			
		}
	}
	
	public void DoAlgo()
	{
		try
		{
			
		}
		catch(Exception ex)
		{
			
		}
		finally 
		{
			
		}
	}
	
}
