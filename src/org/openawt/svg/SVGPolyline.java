
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

import java.util.List;

import org.openawt.geom.Line2D;
import org.openawt.geom.Point2D;
import org.openawt.geom.Polygon2D;
import org.openawt.geom.Polyline2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;

public class SVGPolyline implements SVGShape<Polyline2D>{
	
	private Style style;
	private Polyline2D poly;
	private Transform transform;
	
	public SVGPolyline(float[] xpoints, float[] ypoints, int npoints){
		poly = new Polyline2D(xpoints,ypoints,npoints);
	}
	
	public SVGPolyline(@Attribute(name="points")Polyline2D poly){
		this.poly = poly;
	}
	
	public SVGPolyline(List<Point2D> points){
		float[] xpts = new float[points.size()], ypts = new float[points.size()];
		for(int i=0; i<points.size(); i++){
			xpts[i] = (float)points.get(i).getX();
			ypts[i] = (float)points.get(i).getY();
		}
		poly = new Polyline2D(xpts,ypts,points.size());
	}
	
	@Override
	@Attribute(required = false)
	public Style getStyle() {
		return this.style;
	}

	@Override
	@Attribute(required = false)
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	@Attribute(name="points")
	public Polyline2D getShape() {
		return poly;
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
