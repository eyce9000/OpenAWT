
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

import org.openawt.geom.Line2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@SuppressWarnings("serial")
@Root(name="line")
public class SVGLine implements SVGShape<Line2D>{
	private Line2D line;
	private Style style;
	private Transform transform;
	
	public SVGLine(
			@Attribute(name="x1") double x1,
			@Attribute(name="y1") double y1,
			@Attribute(name="x2") double x2,
			@Attribute(name="y2") double y2){
		line = new Line2D.Double((float)x1,(float)y1,(float)x2,(float)y2);
	}
	
	public SVGLine(Line2D line){
		this.line = line;
	}

	
	@Attribute(name="x1")
	public double getX1(){
		return line.getX1();
	}
	
	@Attribute(name="y1")
	public double getY1(){
		return line.getY1();
	}

	@Attribute(name="x2")
	public double getX2(){
		return line.getX2();
	}

	@Attribute(name="y2")
	public double getY2(){
		return line.getY2();
	}
	
	@Override
	public Style getStyle() {
		return this.style;
	}

	@Override
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	public Line2D getShape() {
		return line;
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
