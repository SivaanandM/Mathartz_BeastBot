package com.beastbot.list;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BeastViewList 
{
  int id;
  String headdisplay, playerdisplay;
  String F1Point, F2Point, F3Point, F4Point, F5Point;
  
  public BeastViewList(int id, String headdisplay, String F1Point, String F2Point, String F3Point, String F4Point, String F5Point, String playerdisplay)
  {
	  this.id = id;
	  this.headdisplay = headdisplay;
	  this.F1Point = F1Point;
	  this.F2Point = F2Point;
	  this.F3Point = F3Point;
	  this.F4Point = F4Point;
	  this.F5Point = F5Point;
	  this.playerdisplay = playerdisplay;
  }
  public int getid()
  {
		return id;
  }
  public String getHeadDisplay()
  {
		return headdisplay;
  }
  public String getF1Point()
  {
		return F1Point;
  }
  public String getF2Point()
  {
		return F2Point;
  }
  public String getF3Point()
  {
		return F3Point;
  }
  public String getF4Point()
  {
		return F4Point;
  }
  public String getF5Point()
  {
		return F5Point;
  }
  public String getPlayerDisplay()
  {
		return playerdisplay;
  }
  public String[] getData()
  {
    return new String[]{String.valueOf(id),headdisplay,F1Point,F2Point, F3Point, F4Point, F5Point, playerdisplay};
  }
  public Object getposition() {
	// TODO Auto-generated method stub
	return new String[]{String.valueOf(id),headdisplay,F1Point,F2Point, F3Point, F4Point, F5Point, playerdisplay};
  }
  
}
