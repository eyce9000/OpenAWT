
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
