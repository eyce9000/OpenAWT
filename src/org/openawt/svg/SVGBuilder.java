
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

import org.openawt.Shape;
import org.openawt.geom.Ellipse2D;
import org.openawt.geom.Line2D;
import org.openawt.geom.Path2D;
import org.openawt.geom.Polygon2D;
import org.openawt.geom.Polyline2D;
import org.openawt.geom.RectangularShape;

public class SVGBuilder {
	public static SVGShape buildSVGShape(Shape shape){
		SVGShape svgShape;
		if(shape instanceof Ellipse2D){
			Ellipse2D ellipse = (Ellipse2D)shape;
			if(Math.abs(ellipse.getHeight()-ellipse.getWidth())<.00001){
				return new SVGCircle(ellipse.getCenterX(),ellipse.getCenterY(),ellipse.getWidth()/2);
			}
			return new SVGEllipse(ellipse);
		}
		else if(shape instanceof RectangularShape){
			return new SVGRectangle((RectangularShape) shape);
		}
		else if(shape instanceof Polygon2D){
			return new SVGPolygon((Polygon2D) shape);
		}
		else if(shape instanceof Polyline2D){
			return new SVGPolyline((Polyline2D) shape);
		}
		else if(shape instanceof Line2D){
			return new SVGLine((Line2D) shape);
		}
		else if(shape instanceof Path2D){
			return new SVGPath((Path2D)shape);
		}
		else{
			return new SVGPath(shape.getPathIterator(null));
		}
	}
}
