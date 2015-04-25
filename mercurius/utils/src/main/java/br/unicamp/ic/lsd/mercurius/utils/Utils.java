package br.unicamp.ic.lsd.mercurius.utils;

import java.io.File;
import java.io.IOException;

public class Utils {

	public static void deleteDirectory(File file) throws IOException {
		if (file.isDirectory()) {
			if (file.list().length == 0) {
				file.delete();
			} else {
				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					deleteDirectory(fileDelete);
				}
				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

}
