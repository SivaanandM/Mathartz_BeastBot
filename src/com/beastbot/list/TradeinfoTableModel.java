package com.beastbot.list;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TradeinfoTableModel extends AbstractTableModel
{
	private List<Tradeinfo> values ;
	private String[] columns ;
	public TradeinfoTableModel(List<Tradeinfo> infos)
	{
		super();
		values = infos;
		columns = new String [] {"ORDER TYPE" , "WAY", "FST", "PRICE"};
	}
	
	@Override
	public int getColumnCount() {
		return columns.length ;
	}

	@Override
	public int getRowCount() {
		return values.size();
	}
	public String getColumnName(int col) 
	{
		return columns[col] ;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Tradeinfo list = values.get(row);
	    switch(col) {
	      case 0: return list.getOrdertype();
	      case 1: return list.getway();
	      case 2: return list.getfst();
	      case 3: return list.getPrice();
	      default: return null;
	    }
	}
	 @Override
	public void fireTableDataChanged() 
	{
	        super.fireTableDataChanged();
	}
}
