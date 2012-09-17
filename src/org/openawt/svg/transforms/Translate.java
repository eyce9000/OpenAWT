package org.openawt.svg.transforms;

public class Translate extends Transform{
	private Float uniform;
	private Float x,y;
	
	public Translate(Float uniform){
		this.uniform = uniform;
	}
	
	public Translate(Float x, Float y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String serialize() {
		if(uniform!=null)
			return String.format("translate(%.2f)",uniform);
		else
			return String.format("translate(%.2f %.2f)",x,y);
	}
}


