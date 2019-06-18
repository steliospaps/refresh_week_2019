package com.github.steliospaps.refresh2019.chatapiapp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Util {
	private Util() {}
	
	public static <T> List<T> iterableToList(Iterable<T> i){
		return StreamSupport.stream(
				i.spliterator(),
				false)
			.collect(Collectors.toList());
	}

}
