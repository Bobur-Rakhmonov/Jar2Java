package com.bobur.jar2java.decompiler.cfr;

import android.os.Environment;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.benf.cfr.reader.Main;
import java.util.ArrayList;

public class DecompileUtil {
  public static boolean fallbackMode = false;
  private static ArrayList<String> strList = new ArrayList<>();

  public static boolean isFallbackMode() {
    return fallbackMode;
  }

  public static String readFile(String path) {
    StringBuilder sb = new StringBuilder();
    FileReader fr = null;
    try {
      fr = new FileReader(new File(path));

      char[] buff = new char[1024];
      int length = 0;

      while ((length = fr.read(buff)) > 0) {
        sb.append(new String(buff, 0, length));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fr != null) {
        try {
          fr.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return sb.toString();
  }

    public static void listDir(String path, ArrayList<String> list) {
        File dir = new File(path);
        if (!dir.exists() || dir.isFile()) return;

        File[] listFiles = dir.listFiles();
        if (listFiles == null || listFiles.length <= 0) return;

        if (list == null) return;
        list.clear();
        for (File file : listFiles) {
            list.add(file.getAbsolutePath());
        }
    }

  public static String runCFR(String _path) {
    String str = _path;
    String str2 = Environment.getExternalStorageDirectory() + "/Jar2Java";
    new File(str2).delete();
    Main.main(new String[] {str, "--outputdir", str2});
    return "success to decompile - /Jar2Java/";
}

  public static void setFallbackMode(boolean z) {
    fallbackMode = z;
  }
}
