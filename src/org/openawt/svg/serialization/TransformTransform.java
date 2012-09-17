package org.openawt.svg.serialization;

import org.simpleframework.xml.transform.Transform;

public class TransformTransform implements Transform<org.openawt.svg.transforms.Transform>{

	@Override
	public org.openawt.svg.transforms.Transform read(String value) throws Exception {
		return org.openawt.svg.transforms.Transform.parse(value);
	}

	@Override
	public String write(org.openawt.svg.transforms.Transform trans) throws Exception {
		return trans.serialize();
	}

}
