package org.openawt.svg.transforms;

import java.util.ArrayList;
import java.util.List;

public class Compound extends Transform{
	private List<Transform> subTransforms;
	
	public Compound(List<Transform> subTransforms){
		this.subTransforms = subTransforms;
	}
	
	public Compound(){
		subTransforms = new ArrayList<Transform>();
	}
	
	public void add(Transform transform){
		subTransforms.add(transform);
	}
	
	@Override
	public String serialize() {
		String output = "";
		for(int i=0; i<subTransforms.size(); i++){
			if(i>0)
				output+=" ";
			output+=subTransforms.get(i).serialize();
		}
		return output;
	}
	
}
