
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

import org.openawt.geom.RectangularShape;

import org.openawt.geom.Rectangle2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="rect")
public class SVGRectangle implements SVGShape<Rectangle2D>{
	private Style style;
	private Rectangle2D rect;
	
	@Attribute(required=false)
	private Double rx = null, ry = null;
	private Transform transform;
	
	public SVGRectangle(
			@Attribute(name="x") double x, 
			@Attribute(name="y") double y,  
			@Attribute(name="width") double width,  
			@Attribute(name="height") double height){
		rect = new Rectangle2D.Double(x,y,width,height);
	}
	
	public SVGRectangle(RectangularShape shape){
		this(shape.getX(),shape.getY(),shape.getWidth(),shape.getHeight());
	}
	public SVGRectangle(Rectangle2D rect){
		this.rect = rect;
	}
	
	public SVGRectangle(double x, double y, double width, double height, double roundingX, double roundingY){
		this(x,y,width,height);
		this.rx = roundingX;
		this.ry = roundingY;
	}
	
	
	@Attribute(name="x")
	public double getX(){
		return (double)rect.getX();
	}
	
	@Attribute(name="y")
	public double getY(){
		return (double)rect.getY();
	}

	@Attribute(name="width")
	public double getWidth(){
		return (double)rect.getWidth();
	}

	@Attribute(name="height")
	public double getHeight(){
		return (double)rect.getHeight();
	}
	
	@Override
	@Attribute
	public Style getStyle() {
		return style;
	}

	@Override
	@Attribute
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	public Rectangle2D getShape() {
		return rect;
	}

	@Override
	@Attribute(required = false)
	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	@Override
	@Attribute(required = false)
	public Transform getTransform() {
		return this.transform;
	}
}
