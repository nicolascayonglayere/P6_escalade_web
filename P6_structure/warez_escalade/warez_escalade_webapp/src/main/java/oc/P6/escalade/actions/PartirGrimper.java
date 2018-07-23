package oc.P6.escalade.actions;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class PartirGrimper extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> listDiff= new ArrayList<String>() ;
	
	
	
	public String input() {
		String[] vDiff = {"1", "2", "3", "4a", "4b", "4c", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b", "7c", "8a", "8b", "8c", "9a", "9b", "9c" }; 		
		for (int i = 0; i < vDiff.length ; i++)
			listDiff.add(vDiff[i]);
		return ActionSupport.SUCCESS;
	}
	
	public String execute() {
		return ActionSupport.SUCCESS;
	}

	public ArrayList<String> getListDiff() {
		return listDiff;
	}

	public void setListDiff(ArrayList<String> listDiff) {
		this.listDiff = listDiff;
	}




}
