
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

import javax.vecmath.Vector2d;

import org.openawt.geom.Line2D;
import org.openawt.geom.Path2D;
import org.openawt.geom.PathIterator;
import org.openawt.geom.Point2D;
import org.simpleframework.xml.transform.Transform;


public class PathIteratorTransform implements Transform<Path2D>{

	@Override
	public Path2D read(String data) throws Exception {
		String[] instructions = data.split("(?=[a-zA-Z])");
		Path2D.Float path = new Path2D.Float();
		PathCommand lastCommand = null;
		for(String instruction:instructions){
			if(instruction.length()>0){
				PathCommand command = PathCommand.parse(instruction.trim());
				command.apply(lastCommand, path);
				lastCommand = command;
			}
		}
		return path;
	}

	@Override
	public String write(Path2D path) throws Exception {
		String value = "";
		PathIterator pi = path.getPathIterator(null);
		for(;!pi.isDone();pi.next()){
			float[] coords = new float[6];
			switch (pi.currentSegment(coords)){
			case PathIterator.SEG_CLOSE:
				value+="Z";
				break;
			case PathIterator.SEG_CUBICTO:
				value+=String.format("C%f %f %f %f %f %f",coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
				break;
			case PathIterator.SEG_LINETO:
				value+= String.format("L%f %f",coords[0], coords[1]);
				break;
			case PathIterator.SEG_MOVETO:
				value+= String.format("M%f %f",coords[0], coords[1]);
				break;
			case PathIterator.SEG_QUADTO:
				value+= String.format("Q%f %f %f %f",coords[0], coords[1], coords[2], coords[3]);
				break;
			}
		}
		return value;
	}

}

class PathCommand{
	private float[] args;
	private Command command;
	private Point2D lastControlPoint;
	private Point2D previousPoint;

	enum Command{
		M,m,
		Z,z,
		L,l,
		H,h,
		V,v,
		C,c,
		S,s,
		Q,q,
		T,t,
		A,a
	}

	public void apply(PathCommand lastCommand, Path2D.Float path){
		//Quad to extrapolate control point 
		if(lastCommand!=null){
			lastControlPoint = lastCommand.lastControlPoint;
			previousPoint = lastCommand.previousPoint;
		}


		switch(command){
		//Move Relative
		case m:{
			for(int i=0; i<args.length; i+=2){
				previousPoint = path.getCurrentPoint();
				path.moveTo(path.getCurrentPoint().getX()+args[i], path.getCurrentPoint().getY()+args[i+1]);
			}
			break;
		}
		//Move Absolute
		case M:{
			for(int i=0; i<args.length; i+=2){
				previousPoint = path.getCurrentPoint();
				path.moveTo(args[i], args[i+1]);
			}
			break;
		}


		//Close Path
		case z:
		case Z:{
			previousPoint = path.getCurrentPoint();
			path.closePath();
			break;
		}


		//Line to Relative
		case l:{
			for(int i=0; i<args.length; i+=2){
				previousPoint = path.getCurrentPoint();
				path.lineTo(path.getCurrentPoint().getX()+args[i], path.getCurrentPoint().getY()+args[i+1]);
			}
			break;
		}
		//Line to Absolute
		case L:{
			for(int i=0; i<args.length; i+=2){
				previousPoint = path.getCurrentPoint();
				path.lineTo(args[i], args[i+1]);
			}
			break;
		}


		//Line to Horizontal Relative
		case h:{
			for(int i=0; i<args.length; i++){
				previousPoint = path.getCurrentPoint();
				path.lineTo(path.getCurrentPoint().getX()+args[i], path.getCurrentPoint().getY());
			}
			break;
		}
		//Line to Horizontal Absolute
		case H:{
			for(int i=0; i<args.length; i++){
				previousPoint = path.getCurrentPoint();
				path.lineTo(args[i], path.getCurrentPoint().getY());
			}
			break;
		}


		//Line to Vertical Relative
		case v:{
			for(int i=0; i<args.length; i++){
				previousPoint = path.getCurrentPoint();
				path.lineTo(path.getCurrentPoint().getX(), path.getCurrentPoint().getY()+args[i]);
			}
			break;
		}
		case V:{
			for(int i=0; i<args.length; i++){
				previousPoint = path.getCurrentPoint();
				path.lineTo(path.getCurrentPoint().getX(), args[i]);
			}
			break;
		}


		//Curve to relative
		case c:{
			for(int i=0; i<args.length; i+=6){
				previousPoint = path.getCurrentPoint();
				float x = (float)path.getCurrentPoint().getX();
				float y = (float)path.getCurrentPoint().getY();
				path.curveTo(x+args[i], y+args[i+1], x+args[i+2], y+args[i+3], x+args[i+4], y+args[i+5]);
			}
			break;
		}
		//Curve to absolute
		case C:{
			for(int i=0; i<args.length; i+=6){
				previousPoint = path.getCurrentPoint();
				float x = 0;
				float y = 0;
				path.curveTo(x+args[i], y+args[i+1], x+args[i+2], y+args[i+3], x+args[i+4], y+args[i+5]);
			}
			break;
		}


		//Curve to from current relative
		case s:{
			for(int i=0; i<args.length; i+=4){
				previousPoint = path.getCurrentPoint();
				float x = (float)path.getCurrentPoint().getX();
				float y = (float)path.getCurrentPoint().getY();
				path.curveTo(x, y, x+args[i], y+args[i+1], x+args[i+2], y+args[i+3]);
			}
			break;
		}
		//Curve to from current absolute
		case S:{
			for(int i=0; i<args.length; i+=4){
				previousPoint = path.getCurrentPoint();
				float x = 0;
				float y = 0;
				path.curveTo((float)path.getCurrentPoint().getX(), (float)path.getCurrentPoint().getY(),
						x+args[i+0], y+args[i+1], x+args[i+2], y+args[i+3]);
			}
			break;
		}


		//Quad to relative
		case q:{
			for(int i=0; i<args.length; i+=4){
				previousPoint = path.getCurrentPoint();
				float x = (float)path.getCurrentPoint().getX();
				float y = (float)path.getCurrentPoint().getY();
				path.quadTo(x+args[i], y+args[i+1], x+args[i+2], y+args[i+3]);
				lastControlPoint = new Point2D.Float(x+args[i], y+args[i+1]);
			}
			break;
		}
		//Quad to absolute
		case Q:{
			for(int i=0; i<args.length; i+=4){
				previousPoint = path.getCurrentPoint();
				float x = 0;
				float y = 0;
				path.quadTo(x+args[i], y+args[i+1], x+args[i+2], y+args[i+3]);
				lastControlPoint = new Point2D.Float(x+args[i], y+args[i+1]);
			}
			break;
		}

		//Quad to relative using extrapolated control point
		/*
		case t:{
			for(int i=0; i<args.length; i+=2){
				Point2D current = path.getCurrentPoint();
				float x = (float)path.getCurrentPoint().getX();
				float y = (float)path.getCurrentPoint().getY();
				if(lastControlPoint==null)
					lastControlPoint = (Point2D)current.clone();
				Point2D next = new Point2D.Float(x+args[i],y+args[i+1]);
				Point2D previous = (Point2D)previousPoint.clone();
				previousPoint = current;
				Point2D controlPoint = interpolateControlPoint(current,previous,next,lastControlPoint);
				path.quadTo(controlPoint.getX(), controlPoint.getY(), next.getX(), next.getY());
			}
		}
		case T:{
			for(int i=0; i<args.length; i+=2){
				Point2D current = path.getCurrentPoint();
				float x = 0;
				float y = 0;
				if(lastControlPoint==null)
					lastControlPoint = (Point2D)current.clone();
				Point2D next = new Point2D.Float(x+args[i],y+args[i+1]);
				Point2D previous = (Point2D)previousPoint.clone();
				previousPoint = current;
				Point2D controlPoint = interpolateControlPoint(current,previous,next,lastControlPoint);
				path.quadTo(controlPoint.getX(), controlPoint.getY(), next.getX(), next.getY());
			}
		}*/
		}
	}


	public static PathCommand parse(String commandGroup){
		PathCommand pathCommand = new PathCommand();
		pathCommand.command = Command.valueOf(commandGroup.substring(0, 1));

		String[] parts = commandGroup.substring(1, commandGroup.length()).split("\\s");
		pathCommand.args = new float[parts.length];
		for(int i=0; i<parts.length; i++){
			if(parts[i].length()>0)
				pathCommand.args[i] = Float.parseFloat(parts[i]);
		}
		return pathCommand;
	}

	public static Point2D interpolateControlPoint(Point2D current, Point2D previous, Point2D next, Point2D lastControlPoint){
		Line2D.Float prevLine = new Line2D.Float(previous, current);
		double prevLineLength = length(prevLine);
		Line2D.Float nextLine = new Line2D.Float(current,next);
		double nextLineLength = length(nextLine);
		Line2D.Float controlVector = new Line2D.Float(previous,lastControlPoint);
		Point2D controlProj = project(controlVector,prevLine);
		Line2D.Float projectedControlVector = new Line2D.Float(previous,controlProj);
		double distanceToLine = length(new Line2D.Float(controlProj,lastControlPoint));

		double percentage = length(projectedControlVector) / length(prevLine);

		//Create the new point
		double scale = nextLineLength/prevLineLength;
		//Get its position on the next line
		Point2D nextLineUnit = unitVector(nextLine);
		double xDifference = -1 * nextLineUnit.getX() * nextLineLength * percentage;
		double yDifference = -1 * nextLineUnit.getY() * nextLineLength * percentage;
		Point2D nextControlBase = new Point2D.Float((float)(next.getX() + xDifference),(float)( next.getY()+yDifference));
		Point2D nextControlUnit = negate(perpendicular(nextLineUnit));
		return new Point2D.Float((float)(nextControlBase.getX()+distanceToLine*scale*nextControlUnit.getX()),
				(float)(nextControlBase.getY()+distanceToLine*scale*nextControlUnit.getY()));

	}
	public static Point2D negate(Point2D pt){
		return new Point2D.Double(pt.getX()*-1,pt.getY()*-1);
	}
	public static Point2D perpendicular(Point2D pt){
		return new Point2D.Double(-1*pt.getX(),pt.getY());
	}
	public static Point2D perpendicular(Line2D line){
		Point2D unit = unitVector(line);
		return new Point2D.Double(-1*unit.getX(),unit.getY());
	}
	public static Point2D add(Point2D p1, Point2D p2){
		return new Point2D.Double(p1.getX()+p2.getX(),p1.getY()+p2.getY());
	}
	public static Point2D unitVector(Line2D line){
		double length = length(line);
		return new Point2D.Double((line.getX2()-line.getX1())/length,(line.getY2()-line.getY1())/length);
	}
	public static double length(Line2D line){
		return Math.sqrt(lengthSquared(line));
	}
	public static double lengthSquared(Line2D line ){
		double dx = line.getX2()-line.getX1();
		double dy = line.getY2()-line.getY1();
		return dx*dx + dy* dy;
	}
	public static Point2D project(Line2D a, Line2D b){
		double[] aVec = new double[2];
		double[] bVec = new double[2];

		aVec[0] = a.getX2()-a.getX1();
		aVec[1] = a.getY2()-a.getY1();

		bVec[0] = b.getX2()-b.getX1();
		bVec[1] = b.getY2()-b.getY1();

		double dotProduct = aVec[0]*bVec[0] + aVec[1]*bVec[1];
		double aL = Math.sqrt(aVec[0]*aVec[0] + aVec[1]*aVec[1]);
		double bL = Math.sqrt(bVec[0]*bVec[0] + bVec[1]*bVec[1]);
		double[] bUnitVec = new double[2];
		bUnitVec[0] = bVec[0]/bL;
		bUnitVec[1] = bVec[0]/bL;

		double cosTheta = dotProduct / (aL*bL);

		return new Point2D.Double(
				a.getX1()+aL*cosTheta*bUnitVec[0],
				a.getY1()+aL*cosTheta*bUnitVec[1]);

	}

}
