package com.tcc.printmobile.model;

import java.io.Serializable;

public abstract class File implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean colorful;
	private Boolean landscape;
	private byte[] byteOfObj;
	private int copies;

	public File() {
		super();
	}

	public File(Boolean colorful, Boolean landscape, int copies) {
		this();
		this.colorful = colorful;
		this.landscape = landscape;
		this.copies = copies;
	}

	public Boolean getColorful() {
		return colorful;
	}

	public void setColorful(Boolean colorful) {
		this.colorful = colorful;
	}

	public Boolean getLandscape() {
		return landscape;
	}

	public void setLandscape(Boolean landscape) {
		this.landscape = landscape;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public byte[] getByteOfObj() {
		return byteOfObj;
	}

	public void setByteOfObj(byte[] byteOfObj) {
		this.byteOfObj = byteOfObj;
	}

	@Override
	public String toString(){
		return "colorful: '" + getColorful() + "', landscape: '" + getLandscape() + "', copies: '" + getCopies() + "'";
	}

}