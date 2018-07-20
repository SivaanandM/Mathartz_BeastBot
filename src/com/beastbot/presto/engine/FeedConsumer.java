package com.beastbot.presto.engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.beastbot.common.CommonObjects;
import com.beastbot.formula.FormulaAmaze;
import com.sft.feedprovider.MarketDataProvider;





public class FeedConsumer extends Thread{

	private FeedBlockingQueue feedBlockingQueue = null;
	FormulaAmaze objFamaze;
	Calendar c = Calendar.getInstance();
	Date date = new Date("12/31/1979 23:59:59");
	SimpleDateFormat monthyearDayCon = new SimpleDateFormat(
			"yyyyMMdd hh:mm:ss a");
	
	public FeedConsumer() {
		// TODO Auto-generated constructor stub
		TimeZone istTime = TimeZone.getTimeZone("IST");
		objFamaze = new FormulaAmaze();
		monthyearDayCon.setTimeZone(istTime);
		c.setTime(date);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		feedBlockingQueue=FeedBlockingQueue.getInstance();
		while(true)
		{
			Map<String, MarketDataProvider> symbolMap = feedBlockingQueue.take();
			Collection<MarketDataProvider> s = symbolMap.values();
			
			for (MarketDataProvider mdp : s) {	
				for (int i=0 ; i<CommonObjects.Globaluniqueheadid.length;i++)
				{
					if(symbolMap.containsKey(CommonObjects.Globaluniqueheadid[i][0]))
					{
						String[] fids;
						for (int k=0 ; k < CommonObjects.Globaltradlinemap.length; k++)
						{
							if ( CommonObjects.Globaltradlinemap[k][0].equals(CommonObjects.Globaluniqueheadid[i][0]))
							{
								fids = CommonObjects.Globaltradlinemap[k][1].split(":");
								//mdp.getLastTradeTime(CommonObjects.Globaluniqueheadid[i][0]);
								//Date date1 = new Date((mdp.getLastTradeTime(CommonObjects.Globaluniqueheadid[i][0]) * 1000) + c.getTimeInMillis());
								System.out.println("hi - "+ mdp.getLastTradePrice(CommonObjects.Globaluniqueheadid[i][0])/100);
								objFamaze.FormulaAmazeDriver(fids, (mdp.getLastTradePrice(CommonObjects.Globaluniqueheadid[i][0])/100), 
										new Date((mdp.getLastTradeTime(CommonObjects.Globaluniqueheadid[i][0]) * 1000) + c.getTimeInMillis()));
								break;
							}
						}
					}
				}
			}
		}
	}
}
