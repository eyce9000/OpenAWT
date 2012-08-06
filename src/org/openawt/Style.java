package org.openawt;

import org.openawt.annotations.CSSProperty;

public class Style {
	@CSSProperty(name="stroke")
	private Color stroke = null;
	@CSSProperty
	private Float strokeWidth = null;
	@CSSProperty
	private Color fill = null;
	public Style(){
		
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
	
}
