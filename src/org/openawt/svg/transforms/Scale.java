package org.openawt.svg.transforms;

public class Scale extends Transform{
	private Float uniform;
	private Float x,y;
	
	public Scale(Float uniform){
		this.uniform = uniform;
	}
	
	public Scale(Float x, Float y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String serialize() {
		if(uniform!=null)
			return String.format("scale(%.2f)", uniform);
		else
			return String.format("scale(%.2f %.2f)",x,y);
	}
	
}
