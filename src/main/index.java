package main;

import java.awt.EventQueue;

import Admin.Admin;
import CustomerPanels.Customer;
import PartnerPanels.Partner;

public class index {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame=new Customer("U002");
					//Login frame=new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
