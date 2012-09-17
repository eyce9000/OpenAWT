package org.openawt.svg;

import java.io.Serializable;

import org.openawt.Color;
import org.openawt.svg.annotations.CSSProperty;
import org.simpleframework.xml.Attribute;

public class Style implements Cloneable,Serializable{
	@CSSProperty(name="stroke")
	private Color stroke = null;
	@CSSProperty
	private Float strokeWidth = null;
	@CSSProperty
	private Color fill = null;
	@CSSProperty(name="font-size")
	private Float fontSize;
	@CSSProperty(name="font-family")
	private String fontFamily;
	
	
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
	
	/**
	 * @return the fontSize
	 */
	public Float getFontSize() {
		return fontSize;
	}
	/**
	 * @param fontSize the fontSize to set
	 */
	public Style setFontSize(Float fontSize) {
		this.fontSize = fontSize;
		return this;
	}
	/**
	 * @return the fontFamily
	 */
	public String getFontFamily() {
		return fontFamily;
	}
	/**
	 * @param fontFamily the fontFamily to set
	 */
	public Style setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
		return this;
	}
	
	public Object clone(){
		return new Style(this);
	}
}
