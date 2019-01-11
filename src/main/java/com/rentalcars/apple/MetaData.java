package com.rentalcars.apple;

public class MetaData {
	private String color;
	private String notes;

	public MetaData() {
		
	}
	
	public MetaData(String color, String notes) {
		this.color = color;
		this.notes = notes;
	}
	
	public void setColor(String color) {
		this.color = color;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getColor() {
		return color;
	}

	public String getNotes() {
		return notes;
	}
	@Override
	public String toString() {
		return "MetaData [color=" + color + ", notes=" + notes + "]";
	}
	
}
