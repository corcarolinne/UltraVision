package model;

public abstract class Title {
	
	// properties
	protected String titleName;
	protected int yearOfRelease;
	protected String format;
	protected int price;
	protected boolean isAvailable;
	
	// constructor
	public Title(String titleName, int yearOfRelease, String format, int price, boolean isAvailable) {
		this.titleName = titleName;
		this.yearOfRelease = yearOfRelease;
		this.format = format;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	// getters and setters 
	public String getTitleName() {
		return this.titleName;
	}

	public int getYearOfRelease() {
		return this.yearOfRelease;
	}

	public String getFormat() {
		return this.format;
	}

	public int getPrice() {
		return this.price;
	}

	public boolean isAvailable() {
		return this.isAvailable;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
}
