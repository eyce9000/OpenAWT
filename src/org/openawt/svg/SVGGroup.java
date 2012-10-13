
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openawt.Rectangle;
import org.openawt.Shape;
import org.openawt.geom.AffineTransform;
import org.openawt.geom.GeneralPath;
import org.openawt.geom.PathIterator;
import org.openawt.geom.Point2D;
import org.openawt.geom.Rectangle2D;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root(name="g")
public class SVGGroup extends SVGShapeCollection implements SVGShape<Shape>{
	private Style style;
	private Transform transform;


	@Override
	@Attribute(required=false)
	public Style getStyle() {
		return style;
	}

	@Override
	@Attribute(required=false)
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	public Shape getShape() {
		GeneralPath path = new GeneralPath();
		for(SVGShape shape:this){
			if(shape!=null)
				path.append(shape.getShape(), false);
		}
		return path;
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
