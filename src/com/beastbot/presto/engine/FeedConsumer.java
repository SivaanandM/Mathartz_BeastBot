package com.beastbot.presto.engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.sft.feedprovider.MarketDataProvider;





public class FeedConsumer extends Thread{

	private FeedBlockingQueue feedBlockingQueue = null;
	Calendar c = Calendar.getInstance();
	Date date = new Date("12/31/1979 23:59:59");
	SimpleDateFormat monthyearDayCon = new SimpleDateFormat(
			"yyyyMMdd hh:mm:ss a");
	
	public FeedConsumer() {
		// TODO Auto-generated constructor stub
		TimeZone istTime = TimeZone.getTimeZone("IST");
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
			String [] secids=new String[2];	
			secids[0]="2885";
			secids[1]="45127";
			for (MarketDataProvider mdp : s) {	
				for (int i=0 ; i<2;i++)
				{
					if(symbolMap.containsKey(secids[i]))
					{
						System.out.println(secids[i] +" CALLED : ");
						System.out.println("------LTP: " + ((int) mdp.getLastTradePrice(secids[i]) / 100));
						long time = mdp.getLastTradeTime(secids[i]);
						Date date1 = new Date((time * 1000) + c.getTimeInMillis());
						System.out.println("------LTT: date: " + monthyearDayCon.format(date1));
					}
				}
			}
		}
	}
	public void callReliance(){
		System.out.println("Reliance Called");
	}
	public void callNifty(){
		System.out.println("NIFTY Called");
	}
}
