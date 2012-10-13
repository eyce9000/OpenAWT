
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

package org.openawt.draw.awt;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import org.openawt.Color;
import org.openawt.Shape;
import org.openawt.geom.PathIterator;
import org.openawt.svg.SVGGroup;
import org.openawt.svg.SVGShape;
import org.openawt.svg.Style;


public class ShapePainter {
	public static void drawGroup(Graphics2D g2d, SVGGroup group, Style override){
		if(override==null)
			override = group.getStyle();
		for(SVGShape shape:group){
			if(group.getStyle()!=null)
				shape.setStyle(group.getStyle());
			draw(g2d,shape,override);
		}
	}
	public static void draw(Graphics2D g2d, SVGShape shape, Style override){
		if(shape==null)
			return;
		if(shape instanceof SVGGroup){
			drawGroup(g2d,(SVGGroup)shape,override);
		}
		else{
			Style style = override;
			if(style==null){ //Override style is null
				style = shape.getStyle();
			}
			if(style==null){ //Shape style is null, set it to a black stroke
				style = new Style().setStroke(Color.BLACK).setFill(Color.NONE).setStrokeWidth(1f);
			}
			org.openawt.Color fillColor = style.getFill();
			if(fillColor!=null && !fillColor.isClear()){
				setColor(g2d,fillColor);
				fill(g2d,shape.getShape());
			}

			org.openawt.Color strokeColor = style.getStroke();
			Float strokeWidth = style.getStrokeWidth();

			if(strokeColor!=null){
				setColor(g2d,strokeColor);
			}

			if(strokeWidth != null)
				g2d.setStroke(new BasicStroke(strokeWidth));

			draw(g2d, shape.getShape());
		}
	}
	public static void draw(Graphics2D g2d, Shape s){
		if(s!=null){
			PathIterator pi = s.getPathIterator(null);
			java.awt.geom.GeneralPath path = toAwtPath(pi);
			g2d.draw(path);
		}
	}
	public static void fill(Graphics2D g2d, Shape s){
		if(s!=null){
			PathIterator pi = s.getPathIterator(null);
			java.awt.geom.GeneralPath path = toAwtPath(pi);
			g2d.fill(path);
		}
	}
	public static void setColor(Graphics2D g2d, Color color){
		g2d.setColor(new java.awt.Color(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha()));
	}
	public static java.awt.geom.GeneralPath toAwtPath(PathIterator pi){
		java.awt.geom.GeneralPath path = new java.awt.geom.GeneralPath();
		path.setWindingRule(pi.getWindingRule());
		for(;!pi.isDone();pi.next()){
			float[] coords = new float[6];
			switch (pi.currentSegment(coords)){
			case PathIterator.SEG_CLOSE:
				path.closePath();
				break;
			case PathIterator.SEG_CUBICTO:
				path.curveTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
				break;
			case PathIterator.SEG_LINETO:
				path.lineTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_MOVETO:
				path.moveTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_QUADTO:
				path.quadTo(coords[0], coords[1], coords[2], coords[3]);
				break;
			}
		}
		return path;
	}
}
