package com.tcc.printmobile.model;

public class Pdf extends File {
	private static final long serialVersionUID = 1L;
	
	private String intervalPage;

	public String getIntervalPage() {
		return intervalPage;
	}

	public void setIntervalPage(String intervalPage) {
		this.intervalPage = intervalPage;
	}
	
	public Pdf(Boolean colorful, Boolean landscape, Long copies, String intervalPage) {		
		super(colorful, landscape, copies);
		this.intervalPage = intervalPage;
	}
}
