package org.openawt.svg.transforms;

public class Rotate extends Transform{
	private Float degrees;
	public Rotate(Float degrees){
		this.degrees = degrees;
	}
	@Override
	public String serialize() {
		return String.format("rotate(%.4f)",degrees);
	}
}

