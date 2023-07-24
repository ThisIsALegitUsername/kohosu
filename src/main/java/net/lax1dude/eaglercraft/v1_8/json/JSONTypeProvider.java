package net.lax1dude.eaglercraft.v1_8.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;

import net.lax1dude.eaglercraft.v1_8.json.impl.JSONDataParserReader;
import net.lax1dude.eaglercraft.v1_8.json.impl.JSONDataParserStream;
import net.lax1dude.eaglercraft.v1_8.json.impl.JSONDataParserString;
//import net.lax1dude.eaglercraft.v1_8.json.impl.SoundMapDeserializer;

/**
 * Copyright (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 *
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 *
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 *
 * (please read the 'LICENSE' file this repo's root directory for more info)
 *
 */
public class JSONTypeProvider {

	private static final Map<Class<?>,JSONTypeSerializer<?,?>> serializers = new HashMap();
	private static final Map<Class<?>,JSONTypeDeserializer<?,?>> deserializers = new HashMap();

	private static final List<JSONDataParserImpl> parsers = new ArrayList();

	public static <J> J serialize(Object object) throws JSONException {
		JSONTypeSerializer<Object,J> ser = (JSONTypeSerializer<Object,J>) serializers.get(object.getClass());
		if(ser == null) {
			for(Entry<Class<?>,JSONTypeSerializer<?,?>> etr : serializers.entrySet()) {
				if(etr.getKey().isInstance(object)) {
					ser = (JSONTypeSerializer<Object,J>)etr.getValue();
					break;
				}
			}
		}
		if(ser != null) {
			return ser.serializeToJson(object);
		}else {
			throw new JSONException("Could not find a serializer for " + object.getClass().getSimpleName());
		}
	}

	public static <O> O deserialize(Object object, Class<O> clazz) throws JSONException {
		return deserializeNoCast(parse(object), clazz);
	}

	public static <O> O deserializeNoCast(Object object, Class<O> clazz) throws JSONException {
		JSONTypeDeserializer<Object,O> ser = (JSONTypeDeserializer<Object,O>) deserializers.get(clazz);
		if(ser != null) {
			return (O)ser.deserializeFromJson(object);
		}else {
			throw new JSONException("Could not find a deserializer for " + object.getClass().getSimpleName());
		}
	}

	public static <O,J> JSONTypeSerializer<O,J> getSerializer(Class<O> object) {
		return (JSONTypeSerializer<O,J>)serializers.get(object);
	}

	public static <J,O> JSONTypeDeserializer<J,O> getDeserializer(Class<O> object) {
		return (JSONTypeDeserializer<J,O>)deserializers.get(object);
	}

	public static Object parse(Object object) {
		for(int i = 0, l = parsers.size(); i < l; ++i) {
			JSONDataParserImpl parser = parsers.get(i);
			if(parser.accepts(object)) {
				return parser.parse(object);
			}
		}
		return object;
	}

	public static void registerType(Class<?> clazz, Object obj) {
		boolean valid = false;
		if(obj instanceof JSONTypeSerializer<?,?>) {
			serializers.put(clazz, (JSONTypeSerializer<?,?>)obj);
			valid = true;
		}
		if(obj instanceof JSONTypeDeserializer<?,?>) {
			deserializers.put(clazz, (JSONTypeDeserializer<?,?>)obj);
			valid = true;
		}
		if(!valid) {
			throw new IllegalArgumentException("Object " + obj.getClass().getSimpleName() + " is not a JsonSerializer or JsonDeserializer object");
		}
	}

	public static void registerParser(JSONDataParserImpl obj) {
		parsers.add(obj);
	}

	static {

		registerParser(new JSONDataParserString());
		registerParser(new JSONDataParserReader());
		registerParser(new JSONDataParserStream());

	}

}
