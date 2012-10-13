
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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.openawt.Color;
import org.openawt.svg.NumberUnit;
import org.openawt.svg.Style;
import org.openawt.svg.annotations.CSSProperty;
import org.simpleframework.xml.stream.HyphenStyle;
import org.simpleframework.xml.transform.Transform;

public class StyleTransform implements Transform<Style>{
	private static HashMap<String,Field> nameToField = new HashMap<String,Field>();
	private static HashMap<Field,String> fieldToName = new HashMap<Field,String>();
	private static HyphenStyle hyphenate = new HyphenStyle();
	static {
		Field[] fields = Style.class.getDeclaredFields();
		for(int i=0;i<fields.length; i++){
			Field field = fields[i];
			if(field.isAnnotationPresent(CSSProperty.class)){
				CSSProperty prop = field.getAnnotation(CSSProperty.class);
				String name = prop.name();
				if(name.length()==0)
					name = field.getName();
				
				name= hyphenate.getAttribute(name);

				nameToField.put(name, field);
				fieldToName.put(field, name);

			}
		}
	}

	@Override
	public Style read(String input) throws Exception {
		Style style = new Style();
		String[] declarations = input.split(";");

		for(String declaration:declarations){
			String[] parts = declaration.split(":");
			if(parts.length==2){
				String name = parts[0];
				String value = parts[1];
				Field field = nameToField.get(name);
				if(field!=null){
					boolean wasPrivate = field.isAccessible();
					field.setAccessible(true);
					if(field.getType()==String.class){
						field.set(style, value);
					}
					else if(field.getType() == Float.class){
						field.set(style, Float.parseFloat(value));
					}
					else if(field.getType() == Color.class){
						field.set(style, Color.parse(value));
					}
					else{
						throw new IllegalArgumentException("Unable to deserialize class:"+field.getType());
					}
					field.setAccessible(wasPrivate);
				}
			}
		}
		return style;
	}

	@Override
	public String write(Style theStyle) throws Exception {
		String output = "";

		HashMap<String,String> values = new HashMap<String,String>();

		for(Field field:fieldToName.keySet()){

			boolean wasPrivate = field.isAccessible();
			field.setAccessible(true);
			{
				String out = null;
				Object value = field.get(theStyle);
				if(value!=null){
					if(value instanceof Color){
						if(((Color) value).getAlpha() == 0){
							out = "none";
						}
						else{
							out = value.toString();
						}
					}
					else{
						out = value.toString();
					}
					values.put(fieldToName.get(field),out);
				}
			}
			field.setAccessible(wasPrivate);
		}


		for(String key:values.keySet()){
			output+=key+":"+values.get(key)+";";
		}
		return output;
	}

}
