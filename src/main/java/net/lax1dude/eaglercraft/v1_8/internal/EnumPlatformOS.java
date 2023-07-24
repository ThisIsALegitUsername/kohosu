package net.lax1dude.eaglercraft.v1_8.internal;


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
public enum EnumPlatformOS {
	WINDOWS("Windows"), MACOS("MacOS"), LINUX("Linux"),
	CHROMEBOOK_LINUX("ChromeOS"), OTHER("Unknown");

	private final String name;


	private EnumPlatformOS(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public static EnumPlatformOS getFromJVM(String osNameProperty) {
		osNameProperty = osNameProperty.toLowerCase();
		if(osNameProperty.contains("chrome")) {
			return CHROMEBOOK_LINUX;
		}else if(osNameProperty.contains("linux")) {
			return LINUX;
		}else if(osNameProperty.contains("windows") || osNameProperty.contains("win32")) {
			return WINDOWS;
		}else if(osNameProperty.contains("macos") || osNameProperty.contains("osx")) {
			return MACOS;
		}else {
			return OTHER;
		}
	}

	public static EnumPlatformOS getFromUA(String ua) {
		ua = " " + ua.toLowerCase();
		if(ua.contains(" cros")) {
			return CHROMEBOOK_LINUX;
		}else if(ua.contains(" linux")) {
			return LINUX;
		}else if(ua.contains(" windows") || ua.contains(" win32") || ua.contains(" win64")) {
			return WINDOWS;
		}else if(ua.contains(" macos") || ua.contains(" osx")) {
			return MACOS;
		}else {
			return OTHER;
		}
	}

}
