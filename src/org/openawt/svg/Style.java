
/*******************************************************************************
 *  Revision History:<br>
 *  SRL Member - File created
 *
 *  <p>
 *  <pre>
 *  This work is released under the BSD License:
 *  (C) 2011 Sketch Recognition Lab, Texas A&M University (hereafter SRL @ TAMU)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the Sketch Recognition Lab, Texas A&M University 
 *        nor the names of its contributors may be used to endorse or promote 
 *        products derived from this software without specific prior written 
 *        permission.
 *  
 *  THIS SOFTWARE IS PROVIDED BY SRL @ TAMU ``AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL SRL @ TAMU BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  </pre>
 *  
 *******************************************************************************/

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
