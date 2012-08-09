package org.openawt.svg.serialization;

import org.openawt.BasicStroke;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class BasicStrokeConverter implements Converter<BasicStroke>{
	private final String 
	ATTR_END_CAP = "end_cap",
	ATTR_LINE_WIDTH = "line_width",
	ATTR_LINE_JOIN = "line_join",
	ATTR_MITER_LIMIT = "miter_limit",
	EL_DASH_ARRAY = "dash_array",
	ATTR_DASH_PHASE = "phase";

	private enum EndCap{
		butt,
		round,
		square
	}

	private enum LineJoin{
		bevel,
		miter,
		round
	}

	@Override
	public BasicStroke read(InputNode node) throws Exception {
		//End Cap
		Integer endCap = getEndCap(node);

		//Line Join
		Integer lineJoin = getLineJoin(node);

		Float lineWidth = Float.valueOf(node.getAttribute(ATTR_LINE_WIDTH).getValue());

		Float miterLimit = Float.valueOf(node.getAttribute(ATTR_MITER_LIMIT).getValue());

		InputNode dashArrayNode = node.getNext(EL_DASH_ARRAY);

		Float dashPhase = null;
		float[] dashArray = new float[0];
		if(dashArrayNode != null){
			dashArray = getDashArray(dashArrayNode);
			dashPhase = Float.valueOf(dashArrayNode.getAttribute(ATTR_DASH_PHASE).getValue());
		}

		if(endCap==null || lineJoin==null){
			return new BasicStroke(lineWidth);
		}
		else{
			if(miterLimit==null){
				return new BasicStroke(lineWidth,endCap,lineJoin);
			}
			else{
				if(dashPhase == null || dashArray.length == 0){
					return new BasicStroke(lineWidth,endCap,lineJoin,miterLimit);
				}
				else{
					return new BasicStroke(lineWidth,endCap,lineJoin,miterLimit,dashArray,dashPhase);
				}
			}
		}
	}

	@Override
	public void write(OutputNode node, BasicStroke stroke) throws Exception {
		//End Cap
		setEndCap(node,stroke.getEndCap());
		//Join
		setLineJoin(node,stroke.getLineJoin());
		//Dash
		setDash(node,stroke.getDashArray(),stroke.getDashPhase());

		//Line Width
		float lineWidth = stroke.getLineWidth();
		node.setAttribute(ATTR_LINE_WIDTH, lineWidth+"");

		float miterLimit = stroke.getMiterLimit();
		node.setAttribute(ATTR_MITER_LIMIT, Float.toString(miterLimit));


	}


	//End cap methods
	private Integer getEndCap(InputNode node) throws Exception{
		EndCap endCap = EndCap.valueOf(node.getAttribute(ATTR_END_CAP).getValue());
		switch(endCap){
		case butt:
			return BasicStroke.CAP_BUTT;
		case round:
			return BasicStroke.CAP_ROUND;
		case square:
			return BasicStroke.CAP_SQUARE;
		}
		return null;
	}

	private void setEndCap(OutputNode node, int endCap){
		EndCap endCapVal = null;
		switch(endCap){
		case BasicStroke.CAP_BUTT:
			endCapVal = EndCap.butt;
			break;
		case BasicStroke.CAP_ROUND:
			endCapVal = EndCap.round;
			break;
		case BasicStroke.CAP_SQUARE:
			endCapVal = EndCap.square;
			break;
		}
		if(endCapVal!=null)
			node.setAttribute(ATTR_END_CAP, endCapVal.toString());
	}


	private Integer getLineJoin(InputNode node) throws Exception{
		LineJoin join = LineJoin.valueOf(node.getAttribute(ATTR_LINE_JOIN).getValue());
		switch(join){
		case bevel:
			return BasicStroke.JOIN_BEVEL;
		case miter:
			return BasicStroke.JOIN_MITER;
		case round:
			return BasicStroke.JOIN_ROUND;
		}
		return null;
	}

	private void setLineJoin(OutputNode node, int lineJoin){
		LineJoin join = null;
		switch(lineJoin){
		case BasicStroke.JOIN_BEVEL:
			join = LineJoin.bevel;
			break;
		case BasicStroke.JOIN_MITER:
			join = LineJoin.miter;
			break;
		case BasicStroke.JOIN_ROUND:
			join = LineJoin.round;
			break;
		}
		if(join!=null)
			node.setAttribute(ATTR_LINE_JOIN,join.toString());
	}

	private float[] getDashArray(InputNode node) throws Exception{
		String[] parts = node.getValue().split(",");
		float[] dashArray = new float[parts.length];
		for(int i=0; i<parts.length; i++){
			dashArray[i] = Float.valueOf(parts[i]);
		}
		return dashArray;
	}


	private void setDash(OutputNode node, float[] dashArray, float dashPhase) throws Exception {
		if(dashArray!=null){
			OutputNode dashNode = node.getChild(EL_DASH_ARRAY);
			dashNode.setData(true);
			dashNode.setName(EL_DASH_ARRAY);
			dashNode.setAttribute(ATTR_DASH_PHASE, Float.toString(dashPhase));
			String value = "";
			for(int i=0; i<dashArray.length; i++){
				value += Float.toString(dashArray[i]);
				if(i < dashArray.length -1)
					value += ",";
			}
			dashNode.setValue(value);
		}
	}
}
