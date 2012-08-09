package org.openawt.svg;

import org.openawt.Color;
import org.openawt.svg.annotations.CSSProperty;

public class Style implements Cloneable{
	@CSSProperty(name="stroke")
	private Color stroke = null;
	@CSSProperty
	private Float strokeWidth = null;
	@CSSProperty
	private Color fill = null;
	public Style(){
		
	}
	public Style(Style style){
		this.stroke = style.stroke;
		this.strokeWidth = style.strokeWidth;
		this.fill = style.fill;
	}
	/**
	 * @return the stroke
	 */
	public Color getStroke() {
		return stroke;
	}
	/**
	 * @param stroke the stroke to set
	 */
	public Style setStroke(Color stroke) {
		this.stroke = stroke;
		return this;
	}
	/**
	 * @return the strokeWidth
	 */
	public Float getStrokeWidth() {
		return strokeWidth;
	}
	/**
	 * @param strokeWidth the strokeWidth to set
	 */
	public Style setStrokeWidth(Float strokeWidth) {
		this.strokeWidth = strokeWidth;
		return this;
	}
	/**
	 * @return the fill
	 */
	public Color getFill() {
		return fill;
	}
	/**
	 * @param fill the fill to set
	 */
	public Style setFill(Color fill) {
		this.fill = fill;
		return this;
	}
	
	public Object clone(){
		return new Style(this);
	}
}
