
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

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

public abstract class SVGShapeCollection implements Iterable<SVGShape>{

	@ElementListUnion({
		@ElementList(entry="g",inline=true,type=SVGGroup.class,required=false),
		@ElementList(entry="circle",inline=true,type=SVGCircle.class,required=false),
		@ElementList(entry="rect",inline=true,type=SVGRectangle.class,required=false),
		@ElementList(entry="ellipse",inline=true,type=SVGEllipse.class,required=false),
		@ElementList(entry="line",inline=true,type=SVGLine.class,required=false),
		@ElementList(entry="polyline",inline=true,type=SVGPolyline.class,required=false),
		@ElementList(entry="polygon",inline=true,type=SVGPolygon.class,required=false),
		@ElementList(entry="path",inline=true,type=SVGPath.class,required=false)
	})
	
	protected List<SVGShape> shapes;
	
	
	public SVGShapeCollection(){
		shapes = new ArrayList<SVGShape>();
	}
	
	public SVGShapeCollection addShape(SVGShape shape){
		shapes.add(shape);
		return this;
	}
	
	public boolean removeShape( SVGShape shape){
		return shapes.remove(shape);
	}

	@Override
	public Iterator<SVGShape> iterator() {
		return shapes.iterator();
	}
}
