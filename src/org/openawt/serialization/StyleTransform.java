package org.openawt.serialization;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.openawt.Color;
import org.openawt.Style;
import org.openawt.annotations.CSSProperty;
import org.simpleframework.xml.stream.HyphenStyle;
import org.simpleframework.xml.transform.Transform;

public class StyleTransform implements Transform<Style>{
	private HashMap<String,Field> nameToField = new HashMap<String,Field>();
	private HashMap<Field,String> fieldToName = new HashMap<Field,String>();
	private HyphenStyle hyphenate = new HyphenStyle();
	public StyleTransform(){
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
				Object value = field.get(theStyle);
				if(value!=null){

					values.put(fieldToName.get(field),value.toString());
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
