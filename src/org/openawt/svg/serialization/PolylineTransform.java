
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
package org.openawt.svg.serialization;

import org.openawt.geom.Polyline2D;
import org.simpleframework.xml.transform.Transform;

public class PolylineTransform implements Transform<Polyline2D>{

	@Override
	public Polyline2D read(String in) throws Exception {
		float[] xPoints;
		float[] yPoints;
		
		String[] points = in.trim().split("\\s");
		
		xPoints = new float[points.length];
		yPoints = new float[points.length];
		
		for(int i=0; i<points.length; i++){
			String point = points[i];
			
			String[] parts = point.split(",");
			if(parts.length == 2){
				float x = Float.parseFloat(parts[0]);
				float y = Float.parseFloat(parts[1]);
				xPoints[i] = x;
				yPoints[i] = y;
			}
		}
		
		return new Polyline2D(xPoints,yPoints,yPoints.length);
	}

	@Override
	public String write(Polyline2D poly) throws Exception {
		float[] xPoints = poly.xpoints;
		float[] yPoints = poly.ypoints;
		int nPoints = poly.npoints;
		
		String value = "";
		
		for(int i=0; i<nPoints; i++){
			value += xPoints[i]+","+yPoints[i];
			if(i<nPoints-1){
				value += " ";
			}
		}
		return value;
	}

}
