package com.johnny.boot.openshift.util;

import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;

import com.google.common.collect.Lists;

public class BeanHelper {

	private static DozerBeanMapper dozer = new DozerBeanMapper();

	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	public static <T> List<T> mapList(Collection sourceList,
			Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}

	public static void ignoreFieldCopy(final Object source,
			final Object destinationObject, final String... fields) {
		DozerBeanMapper d = new DozerBeanMapper();
		d.addMapping(new BeanMappingBuilder() {

			@Override
			protected void configure() {
				TypeMappingBuilder x = mapping(destinationObject.getClass(),
						destinationObject.getClass());
				for (String field : fields) {
					x.exclude(field);
				}

			}

		});
		d.map(source, destinationObject);

	}
}