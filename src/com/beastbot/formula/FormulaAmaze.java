package com.beastbot.formula;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.beastbot.common.CommonObjects;
import com.beastbot.list.Amazevalues;
import com.beastbot.list.FormulaData;
import com.beastbot.list.SquadScripts;
import com.beastbot.list.Tradeinfo;
import com.beastbot.list.getListcommon;
import com.beastbot.presto.presto_commons;
import com.beastbot.ui.TradeInsight;

public class FormulaAmaze {
	//amaze execution related data
	int identity,c,lc,r;
	String headsecid,playersecid,way;
	double ltp, points,low,high,nextline,baseline;
	Date ltt;
	//Amaze formula inputs data
	double x,y;
	Date st,mt,et;
	int lcount, round, qty;
	Boolean tradeswitch, isend;
	public static SimpleDateFormat datefmt=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	
//	Calendar calen = Calendar.getInstance();
//	Date date = new Date("12/31/1979 23:59:59");
//	SimpleDateFormat monthyearDayCon = new SimpleDateFormat(
//			"yyyyMMdd hh:mm:ss a");
	
	getListcommon listcom = new getListcommon();
	
	public void FormulaAmazeDriver(String [] ids, double LTP, Date LTT)
	{
		try
		{
			ltp= LTP;
			ltt= LTT;
			for (String id : ids) 
			{
				identity = Integer.valueOf(id);
				AssignvVariables();
				if (!isend)
				{
					DoAlgo();
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
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
			st = finput.getSTDate();
			mt = finput.getMTDate();
			et = finput.getETDate();
			lcount = (int) finput.getLcount();
			round = (int) finput.getRound();
			qty = finput.getQty();
			tradeswitch = finput.getTradeswitch();
			isend = finput.getIsend();
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally 
		{
			
		}
	}
	
	public void DoAlgo()
	{
		try
		{
			//Start Formula First Condition
			if(ltt.after(st))
			{
				//L1 & R1 LOGIC FLOW IMPLEMENT IN L1R1Flow()
				if (way.toUpperCase().equals("START"))
				{
					L1R1Flow();
				}
				
				
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally 
		{
			
		}
	}
	
	public void L1R1Flow()
	{
		try
		{
			//for the first iteration setting low and high and finding low and high till 
			//it enter first logic box
			if ((low == 0 ) && (high == 0))
			{
				low = ltp;
				high = ltp;
			}
			else
			{
				if (ltp > high)
				{
					high = ltp;
				}
				if (ltp < low)
				{
					low =ltp;
				}
			}
			/**
			 * L1 Box Implementation  
			 * 
			 */
			if (ltp > (low + (low*(x/100))))
			{
				//If last trade time is greater then MT then ending in if loop
				if (ltt.after(mt))
				{
					// setting this variable to to end formula 
					isend = true;
				}
				else
				{
					//Entering Market By BUY command
					String strorderid =null;
					if (tradeswitch == true)
					{
						strorderid = placeorder(identity, qty,"BUY");
					}
					Tradeinfo ti=new Tradeinfo(identity, "F1", "BUY", "L1", ltt, strorderid, ltp);
					CommonObjects.GlobalTradeInfo.add(ti);
					way = "L1";
					nextline = low + (low*(x/100));
					baseline = low;
				}
			}
			/**
			 * R1 Box Implementation  
			 * 
			 */
			if(ltp < (high - (high*(x/100))))
			{
				//If last trade time is greater then MT then ending in if loop
				if (ltt.after(mt))
				{
					// setting this variable to to end formula 
					isend = true;
				}
				else
				{
					//Entering Market By SELL command
					String strorderid =null;
					if (tradeswitch == true)
					{
						strorderid = placeorder(identity, qty,"SELL");
					}
					Tradeinfo ti=new Tradeinfo(identity, "F1", "SELL", "R1", ltt, strorderid, ltp);
					CommonObjects.GlobalTradeInfo.add(ti);
					way = "R1";
					nextline = high - (high*(x/100));
					baseline = high;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public String placeorder(int id,int quant, String orderside)
	{
		String clientid = null;
		try
		{
			SquadScripts pl= listcom.getSquadScriptsByID(id, CommonObjects.GlobalSquadScript);
			String sectype;
			if(pl.getPExchange().equalsIgnoreCase("NSEFO"))
			{
				sectype="FO";
			}
			else
			{
				sectype="CM";
			}
			String expdate = pl.getPExpdd()+"-"+com.beastbot.presto.Date.getmonthvalue(pl.getPExpmonthyear().substring(2, 5))+"-"+pl.getPExpmonthyear().substring(2, 5);
			clientid = CommonObjects.objpresto.userPlaceOrderNSE("omnesys", sectype, pl.getPSymbol(), pl.getplayersecid(), expdate, "FA9749", String.valueOf(quant), "0.0", "0.0", pl.getPOpttype(), pl.getPStrike(),
					"MARKET", "Presto_Mathsartz_Strategy", "Testing Order", "DAY", orderside);
				
		}
		catch(Exception ex)
		{
			
		}
		finally
		{
			
		}
		return clientid;
	}
	
}
