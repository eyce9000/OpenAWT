
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
import org.openawt.geom.GeneralPath;
import org.openawt.geom.Path2D;
import org.openawt.geom.PathIterator;
import org.openawt.svg.serialization.PathIteratorTransform;
import org.openawt.svg.transforms.Transform;
import org.simpleframework.xml.Attribute;

public class SVGPath implements SVGShape<Path2D>{
	private Path2D path;
	private Style style;
	private Transform transform;
	public SVGPath(){
		path = new GeneralPath();
	}
	public SVGPath(Path2D path){
		this.path = path;
	}
	public SVGPath(@Attribute(name="d")String serializedFormat) throws Exception{
		this(new PathIteratorTransform().read(serializedFormat));
	}
	public SVGPath(PathIterator pi){
		path = new GeneralPath();
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
	}

	@Attribute(name="d")
	public String getSerializedPath() throws Exception{
		return new PathIteratorTransform().write(getShape());
	}
	
	
	@Override
	@Attribute(required=false)
	public Style getStyle() {
		return this.style;
	}

	@Override
	@Attribute(required=false)
	public void setStyle(Style style) {
		this.style = style;
	}
	@Override
	public Path2D getShape() {
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
