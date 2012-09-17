package org.openawt.svg.serialization;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

public class RegisterMatcher implements Matcher {

	private Map<Class,Transform> transforms = new HashMap<Class,Transform>();
	
	public void register(Class type, Transform transform){
		transforms.put(type, transform);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Transform match(Class type) throws Exception {
		return transforms.get(type);
	}
	
	public RegisterMatcher extend(RegisterMatcher matcher){
		RegisterMatcher combined = new RegisterMatcher();
		combined.transforms = new HashMap<Class,Transform>(matcher.transforms);
		combined.transforms.putAll(transforms);
		return combined;
	}
}
