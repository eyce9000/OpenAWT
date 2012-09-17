package org.openawt.svg.transforms;

import java.util.ArrayList;
import java.util.List;

public abstract class Transform {
	private enum Type{
		ROTATE,
		TRANSLATE,
		SCALE
	}
	public abstract String serialize();
	
	public static Transform parse(String value){
		List<Transform> transforms = new ArrayList<Transform>();
		String[] parts = value.split("(?<=\\))");
		for(String part:parts){
			part = part.trim();
			if(part.length() > 0){
				transforms.add(parseSingle(part));
			}
		}
		if(transforms.size() > 1)
			return new Compound(transforms);
		else 
			return transforms.get(0);
	}
	private static Transform parseSingle(String value){
		String[] parts = value.split("\\(");
		String name = parts[0];
		String[] args = parts[1].split("\\)")[0].split("\\s+");
		switch(Type.valueOf(name.toUpperCase())){
		case ROTATE:{
			return new Rotate(Float.parseFloat(args[0]));
		}
		case TRANSLATE:{
			if(args.length == 1)
				return new Translate(Float.parseFloat(args[0]));
			else
				return new Translate(Float.parseFloat(args[0]),
						Float.parseFloat(args[1]));
		}
		case SCALE:{
			if(args.length == 1)
				return new Scale(Float.parseFloat(args[0]));
			else
				return new Scale(Float.parseFloat(args[0]),
						Float.parseFloat(args[1]));
		}
		}
		return null;
	}
	
	
	
	
}
