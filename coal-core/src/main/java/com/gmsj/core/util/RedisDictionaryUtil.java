package com.gmsj.core.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisDictionaryUtil {
	
	private static final Map<String, Object> dictionary;
	static {
		dictionary = new ConcurrentHashMap<String, Object>();
		dictionary.put( "user.gender.1", "男" );
		dictionary.put( "user.gender.2", "女" );
		dictionary.put( "user.gender.3", "未知" );
	}
	
	public static final <T> T get( String key, Class<T> type ) {
		if ( dictionary.containsKey( key ) ) {
			return type.cast( dictionary.get( key ) );
		}
		if ( type == Float.class ) return type.cast( 0F );
		if ( type == Double.class ) return type.cast( 0D );
		if ( type == Integer.class ) return type.cast( 0 );
		return type.cast( "" );
	}
}
