package com.beastbot.formula;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.beastbot.common.CommonObjects;
import com.beastbot.list.Amazevalues;
import com.beastbot.list.FormulaData;
import com.beastbot.list.SquadScripts;
import com.beastbot.list.Tradeinfo;
import com.beastbot.list.getListcommon;

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
					Amaze();
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
	
	public void Amaze()
	{
		try
		{
			//Start Formula First Condition
			if(ltt.after(st))
			{
				//L1 & R1 LOGIC FLOW IMPLEMENT IN L1R1Flow()
				if (way.equalsIgnoreCase("ST"))
				{
					//for the first iteration setting low and high and finding low and high till
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
					L1Flow();
					R1Flow();
				}
				if (way.toUpperCase().contains("L"))
				{
					L2Flow();
					L3Flow();
					L4Flow();
				}
				if (way.toUpperCase().contains("R"))
				{
					R2Flow();
					R3Flow();
					R4Flow();
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
	public void L1Flow()
	{
		try
		{
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
					strorderid = placeorder(identity, qty,"BUY");
					Tradeinfo ti=new Tradeinfo(identity, "F1", "BUY", "L1", ltt, strorderid, ltp);
					CommonObjects.GlobalTradeInfo.add(ti);
					way = "L1";
					nextline = low + (low*(x/100));
					baseline = low;
				}
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void L2Flow()
	{
		try
		{
		  if(ltt.after(et))
		  {
			  points = points + 0.5;
			  String strorderid =null;
			  if (c>=1)
			  {
				  // SELLING PARTIAL CODE
				  strorderid = placeorder(identity, (int) (qty/2),"SELL");
			  }
			  else
			  {
				  strorderid = placeorder(identity, qty,"SELL");
			  }
			  Tradeinfo ti=new Tradeinfo(identity, "F1", "SELL", "L2", ltt, strorderid, ltp);
			  CommonObjects.GlobalTradeInfo.add(ti);
			  way = "L2";
			  isend = true;
		  }
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		
	}
	public void L3Flow()
	{
		try
		{
			if (ltp < baseline)
			{
				if (c==0)
				{
					String strorderid =null;
					strorderid = placeorder(identity, qty,"SELL");
					points = points - 2;
					Tradeinfo ti=new Tradeinfo(identity, "F1", "SELL", "L3", ltt, strorderid, ltp);
					CommonObjects.GlobalTradeInfo.add(ti);
					way = "L3";
				}
				else
				{
					String strorderid =null;
					strorderid = placeorder(identity, (int)(qty/2),"SELL");
					points = points - 1;
					Tradeinfo ti=new Tradeinfo(identity, "F1", "SELL", "L3", ltt, strorderid, ltp);
					CommonObjects.GlobalTradeInfo.add(ti);
					way = "L3";
				}
				c = c-1;
				r = r+1;
				if (c == -1)
				{
					lc = lc+1;
				}
				if (lc == lcount)
				{
					isend = true;
				}
				if (r == round)
				{
					isend = true;
				}
				c = 0;
				L5Flow();
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void L4Flow()
	{
		try
		{
			if (ltp >= (nextline + (nextline*(y/100))))
			{
				baseline = nextline;
				nextline = (nextline + (nextline*(y/100)));
				points=points+1;
				c=c+1;
				if(c == 1)
				{
					String strorderid =null;
					strorderid = placeorder(identity, (int) (qty/2),"SELL");
					points = points +1;
					Tradeinfo ti=new Tradeinfo(identity, "F1", "SELL", "L4", ltt, strorderid, ltp);
					CommonObjects.GlobalTradeInfo.add(ti);
					way ="L4";
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
	public void L5Flow()
	{
		try
		{
			if(ltp > (baseline + (baseline*(x/100))))
			{
				if (ltt.after(mt))
				{
					isend = true;
				}
				else
				{
				String strorderid =null;
				strorderid = placeorder(identity, qty,"BUY");
				Tradeinfo ti=new Tradeinfo(identity, "F1", "BUY", "L5", ltt, strorderid, ltp);
				CommonObjects.GlobalTradeInfo.add(ti);
				way="L5";
				nextline = (baseline + (baseline*(x/100)));
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
	public void R1Flow()
	{
		try
		{
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
					strorderid = placeorder(identity, qty,"SELL");
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
	public void R2Flow()
	{
		try
		{
			if(ltt.after(et))
			{
				points = points + 0.5;	
				String strorderid= null;
				if (c >=1 )
				{
					strorderid = placeorder(identity, (int) (qty/2),"BUY");
				}
				else
				{
					strorderid = placeorder(identity, qty,"BUY");
				}
				Tradeinfo ti=new Tradeinfo(identity, "F1", "SELL", "R2", ltt, strorderid, ltp);
				CommonObjects.GlobalTradeInfo.add(ti);
				way = "R2";
				isend = true;
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void R3Flow()
	{
		try
		{
			if (ltp > baseline)
			{
				if (c==0)
				{
					String strorderid =null;
					strorderid = placeorder(identity, qty,"BUY");
					Tradeinfo ti=new Tradeinfo(identity, "F1", "BUY", "R3", ltt, strorderid, ltp);
					way = "R3";
					CommonObjects.GlobalTradeInfo.add(ti);
					points = points-2;
				}
				else
				{
					String strorderid = null;
					strorderid = placeorder(identity, (int) (qty/2),"BUY");
					Tradeinfo ti=new Tradeinfo(identity, "F1", "BUY", "R3", ltt, strorderid, ltp);
					way = "R3";
					CommonObjects.GlobalTradeInfo.add(ti);
					points = points-1;
				}
				c = c-1;
				r = r-1;
				if (c == -1)
				{
					lc= lc +1;
				}
				if (lc == lcount)
				{
					isend = true;
				}
				if (r == round)
				{
					isend = true;
				}
				R5Flow();
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void R4Flow()
	{
		try
		{
			if (ltp <= (nextline - (nextline*y/100)))
			{
				baseline =nextline;
				nextline = (nextline - (nextline*y/100));
				points =points +1;
				c=c+1;
				if(c==1)
				{
					String strorderid;
					strorderid = placeorder(identity, (int) (qty/2),"BUY");
					Tradeinfo ti=new Tradeinfo(identity, "F1", "BUY", "R4", ltt, strorderid, ltp);
					way = "R4";
					CommonObjects.GlobalTradeInfo.add(ti);
					points = points+1;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void R5Flow()
	{
		try
		{
			if(ltp < (baseline - (baseline*(x/100))))
			{
				String strorderid= null;
				if (ltt.after(mt))
				{
					isend =  true;
				}
				else
				{
					strorderid = placeorder(identity, qty,"BUY");
					Tradeinfo ti=new Tradeinfo(identity, "F1", "BUY", "R5", ltt, strorderid, ltp);
					CommonObjects.GlobalTradeInfo.add(ti);
					way = "R5";
					nextline = (baseline - (baseline*(x/100)));
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
			if (tradeswitch == true)
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
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally
		{
			
		}
		return clientid;
	}
	
}
