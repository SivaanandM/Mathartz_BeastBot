package com.beastbot.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.beastbot.common.DbFuncs;
import com.beastbot.list.Scriptsdetail;

import javax.swing.JButton;

public class Crawler {

	private JFrame frmcrawler;
	private JTextField txtsymbol;
	private JTextField txtfutmmm;
	private JTextField txtstrike;
	private JTextField txtoptdd;
	private JTextField txtoptmm;
	private JTextField txtoptyy;
	JPanel pnlopt, pnlfut;
	private String operation, side;
	private int seedid;
	JComboBox<String> cmbinstrument;
	JComboBox<String> cmbright;
	DbFuncs dbobj;
	private JTextField txtfutyy;
	String [] centraldate ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crawler window = new Crawler("add","player",16);
					window.frmcrawler.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Crawler(String opera, String forside, int identity) 
	{
		operation = opera;
		side = forside;
		seedid = identity;
		dbobj = new DbFuncs();
		centraldate = dbobj.loadCentralizedDate();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmcrawler = new JFrame();
		frmcrawler.setTitle(side.toUpperCase() + " - " + operation.toUpperCase());
		frmcrawler.setBounds(100, 100, 375, 435);
		frmcrawler.getContentPane().setLayout(null);
		frmcrawler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmcrawler.getContentPane().setBackground(new Color(51, 51, 51));
		frmcrawler.setVisible(true);
		
		JPanel innerpanel = new JPanel();
		innerpanel.setBounds(10, 53, 338, 329);
		innerpanel.setBackground(new Color(80,75,78));
		frmcrawler.getContentPane().add(innerpanel);
		innerpanel.setLayout(null);
		
		JLabel label = new JLabel("INST-TYPE");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Verdana", Font.PLAIN, 18));
		label.setBounds(31, 10, 116, 49);
		innerpanel.add(label);
		
		cmbinstrument = new JComboBox<String>();
		cmbinstrument.setModel(new DefaultComboBoxModel(new String[] {"OPT", "FUT"}));
		cmbinstrument.setFont(new Font("Verdana", Font.PLAIN, 18));
		cmbinstrument.setBounds(149, 19, 156, 31);
		innerpanel.add(cmbinstrument);
		cmbinstrument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbinstrument.getSelectedItem() == "OPT")
				{
					pnlopt.setVisible(true);
					pnlfut.setVisible(false);
				}
				else
				{
					pnlopt.setVisible(false);
					pnlfut.setVisible(true);
				}
			}
		});
		
		JLabel lblSymbol = new JLabel("SYMBOL");
		lblSymbol.setHorizontalAlignment(SwingConstants.LEFT);
		lblSymbol.setForeground(Color.WHITE);
		lblSymbol.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSymbol.setBounds(31, 59, 82, 49);
		innerpanel.add(lblSymbol);
		
		txtsymbol = new JTextField();
		txtsymbol.setText("NIFTY");
		txtsymbol.setHorizontalAlignment(SwingConstants.CENTER);
		txtsymbol.setForeground(new Color(255, 220, 135));
		txtsymbol.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtsymbol.setColumns(10);
		txtsymbol.setCaretColor(Color.WHITE);
		txtsymbol.setBackground(new Color(36, 34, 29));
		txtsymbol.setBounds(149, 61, 156, 42);
		txtsymbol.addKeyListener(new KeyAdapter() {

			  public void keyTyped(KeyEvent e) {
			    char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			  }

			});
		innerpanel.add(txtsymbol);
		
		pnlfut = new JPanel();
		pnlfut.setBackground(new Color(80,75,78));
		pnlfut.setBorder(null);
		
		pnlfut.setBounds(20, 106, 297, 57);
		pnlfut.setVisible(false);
		innerpanel.add(pnlfut);
		pnlfut.setLayout(null);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(10, 5, 97, 36);
		lblMonth.setHorizontalAlignment(SwingConstants.LEFT);
		lblMonth.setForeground(Color.WHITE);
		lblMonth.setFont(new Font("Verdana", Font.PLAIN, 16));
		pnlfut.add(lblMonth);
		
		
		
		txtfutmmm = new JTextField(centraldate[1]);
		txtfutmmm.setBounds(129, 6, 105, 43);
		txtfutmmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtfutmmm.setForeground(new Color(255, 220, 135));
		txtfutmmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtfutmmm.setColumns(10);
		txtfutmmm.setCaretColor(Color.WHITE);
		txtfutmmm.setBackground(new Color(36, 34, 29));
		txtfutmmm.addKeyListener(new KeyAdapter() {

			  public void keyTyped(KeyEvent e) {
			    char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			  }

			});
		pnlfut.add(txtfutmmm);
		
		txtfutyy = new JTextField(centraldate[2]);
		txtfutyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtfutyy.setForeground(new Color(255, 220, 135));
		txtfutyy.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtfutyy.setColumns(10);
		txtfutyy.setCaretColor(Color.WHITE);
		txtfutyy.setBackground(new Color(36, 34, 29));
		txtfutyy.setBounds(237, 5, 50, 44);
		pnlfut.add(txtfutyy);
		
		pnlopt = new JPanel();
		pnlopt.setBounds(20, 106, 297, 160);
		pnlopt.setBackground(new Color(80,75,78));
		pnlopt.setVisible(true);
		pnlopt.setBorder(null);
		innerpanel.add(pnlopt);
		pnlopt.setLayout(null);
		
		JLabel label_2 = new JLabel("INST-TYPE");
		label_2.setBounds(10, 9, 96, 23);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		pnlopt.add(label_2);
		
		cmbright = new JComboBox<String>();
		cmbright.setModel(new DefaultComboBoxModel(new String[] {"CE", "PE"}));
		cmbright.setBounds(128, 6, 155, 29);
		cmbright.setFont(new Font("Verdana", Font.PLAIN, 18));
		pnlopt.add(cmbright);
		
		JLabel lblStrike = new JLabel("STRIKE");
		lblStrike.setBounds(10, 59, 67, 21);
		lblStrike.setHorizontalAlignment(SwingConstants.LEFT);
		lblStrike.setForeground(Color.WHITE);
		lblStrike.setFont(new Font("Verdana", Font.PLAIN, 16));
		pnlopt.add(lblStrike);
		
		txtstrike = new JTextField();
		txtstrike.setBounds(128, 46, 155, 44);
		txtstrike.setHorizontalAlignment(SwingConstants.CENTER);
		txtstrike.setForeground(new Color(255, 220, 135));
		txtstrike.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtstrike.setColumns(10);
		txtstrike.setCaretColor(Color.WHITE);
		txtstrike.setBackground(new Color(36, 34, 29));
		pnlopt.add(txtstrike);
		
		JLabel lblDate = new JLabel("DATE");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblDate.setBounds(10, 114, 108, 21);
		pnlopt.add(lblDate);
		
		
		
		txtoptdd = new JTextField(centraldate[0]);
		txtoptdd.setEnabled(false);
		txtoptdd.setHorizontalAlignment(SwingConstants.CENTER);
		txtoptdd.setForeground(new Color(255, 220, 135));
		txtoptdd.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtoptdd.setColumns(10);
		txtoptdd.setCaretColor(Color.WHITE);
		txtoptdd.setBackground(new Color(36, 34, 29));
		txtoptdd.setBounds(128, 101, 50, 44);
		pnlopt.add(txtoptdd);
		
		txtoptmm = new JTextField(centraldate[1]);
		txtoptmm.setEnabled(false);
		txtoptmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtoptmm.setForeground(new Color(255, 220, 135));
		txtoptmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtoptmm.setColumns(10);
		txtoptmm.setCaretColor(Color.WHITE);
		txtoptmm.setBackground(new Color(36, 34, 29));
		txtoptmm.setBounds(180, 101, 50, 44);
		pnlopt.add(txtoptmm);
		
		txtoptyy = new JTextField(centraldate[2]);
		txtoptyy.setEnabled(false);
		txtoptyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtoptyy.setForeground(new Color(255, 220, 135));
		txtoptyy.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtoptyy.setColumns(10);
		txtoptyy.setCaretColor(Color.WHITE);
		txtoptyy.setBackground(new Color(36, 34, 29));
		txtoptyy.setBounds(233, 101, 50, 44);
		pnlopt.add(txtoptyy);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				if (side.toUpperCase().equals("HEAD"))
				{
					if (operation.toUpperCase().equals("ADD"))
					{
						HeadAdd();
					}
					else if (operation.toUpperCase().equals("EDIT"))
					{
						HeadEdit(seedid);
					}
				}
				else if (side.toUpperCase().equals("PLAYER"))
				{
					if (operation.toUpperCase().equals("ADD"))
					{
						PlayerAdd(seedid);
					}
					else if (operation.toUpperCase().equals("EDIT"))
					{
						PlayerEdit(seedid);
					}
				}
			}
		});
		btnSave.setBounds(20, 280, 297, 38);
		innerpanel.add(btnSave);
		
		JLabel lblcaption = new JLabel(operation.toUpperCase());
		lblcaption.setHorizontalAlignment(SwingConstants.CENTER);
		lblcaption.setForeground(new Color(255, 220, 135));
		lblcaption.setFont(new Font("Verdana", Font.BOLD, 18));
		lblcaption.setBounds(0, 11, 349, 43);
		frmcrawler.getContentPane().add(lblcaption);
		
	}
	
	public void HeadAdd()
	{
		try
		{
			int identity;
			String strheaddisplay="";
			List<Scriptsdetail> headdata = null;
			if (cmbinstrument.getSelectedItem()=="FUT")
			{
				// Getting Contract Detail for Head FUT
				headdata = dbobj.getContractdata(null, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+ txtsymbol.getText()+"'"
						+ " and INSTRUMENT='FUTIDX' and EXPMMMYY='"+txtfutmmm.getText()+txtfutyy.getText()+"';");
				strheaddisplay = headdata.get(0).getSymbol() +"-"+headdata.get(0).getExpmmmdd();
			}
			else if (cmbinstrument.getSelectedItem()=="OPT")
			{
				// Getting Contract Detail for Head OPT
				headdata = dbobj.getContractdata(null, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+ txtsymbol.getText()+"'"
						+ " and INSTRUMENT='OPTIDX' and STRIKE ='"+txtstrike.getText()+"' and OPTTYPE='"+cmbright.getSelectedItem()+"' and EXPDD='"+txtoptdd.getText()+"' and EXPMMMYY='"+txtoptmm.getText()+txtoptyy.getText()+"';");
				strheaddisplay =  headdata.get(0).getSymbol() +"-"+headdata.get(0).getOpttype()+"-"+ headdata.get(0).getStrike()+"-"+headdata.get(0).getExpdd()+headdata.get(0).getExpmmmdd();
			}
			if ((headdata != null) && (headdata.size() > 0))
			{
				 identity = dbobj.getIdentityforInsert(null, "INSERT INTO TBL_TRADE_LINE (HEADID, HEADDISPLAY, HEADSYMBOL) VALUES "
						+ "('"+headdata.get(0).getSecid()+"','"+strheaddisplay+"','"+headdata.get(0).getSymbol()+"' );");
				 dbobj.executeNonQuery(null, "INSERT INTO TBL_BEAST_VIEW (ID,HEADDISPLAY) VALUES ("+identity+",'"+strheaddisplay+"');");
				JOptionPane.showMessageDialog(frmcrawler,"Head Added to Trade Line Identity : "+String.valueOf(identity), "INFO",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				//give warning message box
				JOptionPane.showMessageDialog(frmcrawler,"No Record Found for given search from Crawler DB, \n Kindly Refresh from Presto Contracts Crawler !! ", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void HeadEdit(int id)
	{
		try
		{
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void PlayerAdd(int id)
	{
		try
		{
			
			String strplayerdisplay="";
			List<Scriptsdetail> playerdata = null;
			if (cmbinstrument.getSelectedItem()=="FUT")
			{
				// Getting Contract Detail for PLAYER FUT
				playerdata = dbobj.getContractdata(null, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+ txtsymbol.getText()+"'"
						+ " and INSTRUMENT='FUTIDX' and EXPMMMYY='"+txtfutmmm.getText()+txtfutyy.getText()+"';");
				strplayerdisplay = playerdata.get(0).getSymbol() +"-"+playerdata.get(0).getExpmmmdd();
			}
			else if (cmbinstrument.getSelectedItem()=="OPT")
			{
				// Getting Contract Detail for Player OPT
				playerdata = dbobj.getContractdata(null, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+ txtsymbol.getText()+"'"
						+ " and INSTRUMENT='OPTIDX' and STRIKE ='"+txtstrike.getText()+"' and OPTTYPE='"+cmbright.getSelectedItem()+"' and EXPDD='"+txtoptdd.getText()+"' and EXPMMMYY='"+txtoptmm.getText()+txtoptyy.getText()+"';");
				strplayerdisplay =  playerdata.get(0).getSymbol() +"-"+playerdata.get(0).getOpttype()+"-"+ playerdata.get(0).getStrike()+"-"+playerdata.get(0).getExpdd()+playerdata.get(0).getExpmmmdd();
			}
			if ((playerdata != null) && (playerdata.size() > 0))
			{
				dbobj.executeNonQuery(null, "UPDATE TBL_TRADE_LINE SET PLAYERID='"+playerdata.get(0).getSecid()+"', PLAYERDISPLAY='"+strplayerdisplay+"', SYMBOL='"+playerdata.get(0).getSymbol()+"', EXCHANGE ='"+playerdata.get(0).getExchange()+"', INSTRUMENT='"+playerdata.get(0).getInstrument()+"', LOTSIZE='"+playerdata.get(0).getLotsize()+"', "
						+ "TICKSIZE='"+playerdata.get(0).getTicksize()+"', EXPDD='"+playerdata.get(0).getExpdd()+"', EXPMMMDD='"+playerdata.get(0).getExpmmmdd()+"',OPTTYPE='"+playerdata.get(0).getOpttype()+"', STRIKE='"+playerdata.get(0).getStrike()+"' WHERE ID = "+seedid+"");
				dbobj.executeNonQuery(null, "UPDATE TBL_TRADE_LINE SET PLAYERDISPLAY='"+strplayerdisplay+"' where ID="+seedid+"");
				JOptionPane.showMessageDialog(frmcrawler,"Player Updated to Trade Line Identity : "+String.valueOf(id), "INFO",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				//give warning message box
				JOptionPane.showMessageDialog(frmcrawler,"No Record Found for given search from Crawler DB, \n Kindly Refresh from Presto Contracts Crawler !! ", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void PlayerEdit(int id)
	{
		try
		{
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
}
