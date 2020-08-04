package ru.henridellal.emerald;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;

public class MyCache {
	static public final int MODE_READ = 0;
	static public final int MODE_WRITE = 1;
	
	static public String genFilename(Context c, String name) {
		File dir = c.getCacheDir();
		return dir.getPath() + "/" + name + ".MyCache"; 
	}

	public static File getCustomIconFile(Context c, String component) {
		return new File(c.getFilesDir(),
				Uri.encode(component)+".png");
	}
	public static File getCustomIconFile(Context c, BaseData data) {
		return new File(c.getFilesDir(),
				getIconFileName(data, ".png"));
	}
	public static String getShortcutIconFileName(String uri) {
		return ((Integer)uri.hashCode()).toString() + ".png";
	}
	public static String getIconFileName(String id) {
		return Uri.encode(id)+".icon.png";
	}
	public static String getIconFileName(BaseData data) {
		return getIconFileName(data, ".icon.png");
	}
	public static String getIconFileName(BaseData data, String postfix) {
		String component = data.getComponent();
		if (component != null) {
			return Uri.encode(component)+postfix;
		} else {
			return ((Integer)data.hashCode()).toString() + postfix;
		}
	}
	public static File getShortcutIconFile(Context c, String uri) {
		return new File(c.getFilesDir(), getShortcutIconFileName(uri));
	}
	public static File getIconFile(Context c, String component) {
		return new File(c.getFilesDir(), getIconFileName(component));
	}
	public static File getIconFile(Context c, BaseData data) {
		return new File(c.getFilesDir(), getIconFileName(data));
	}
	
	public static void deleteIcon(Context c, AppData app) {
		if (app == null)
			return;
		getIconFile(c, app).delete();
	}
	/* removes icons of deleted apps */
	public static void cleanIcons(Context c, ArrayList<BaseData> data) {
		File[] dirs = c.getFilesDir().listFiles();
		for (File f : dirs) {
			boolean deleteFile = true;
			for (BaseData a : data) {
				if ((getIconFileName(a)).equals(f.getName())) {
					deleteFile = false;	
					break;
				}
			}
			if (deleteFile && f.getName().contains(".icon.png")) {
				f.delete();
			}
		}
	}

	public static void deleteIcons(Context c) {
		File[] dirs = c.getFilesDir().listFiles();
		
		for (File f : dirs) {
			String name = f.getName();
			if (name.endsWith(".icon.png")) {
				f.delete();
			}
		}

	}
}
