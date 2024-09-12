package com.strajag.custom_frame;

import java.io.File;

public class Tools {

    public static void deleteFolder(File folder) throws Exception {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (null != files) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        if (!file.delete()) { throw new Exception("Failed to delete file - " + file); }
                    }
                }
            }
        }
        if (!folder.delete()) { throw new Exception("Failed to delete folder - " + folder); }
    }

    public static int[] toIntArray(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < intArray.length; i++) { intArray[i] = Integer.parseInt(stringArray[i]); }
        return intArray;
    }

    public static String toStringLine(String[] stringArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringArray.length; i++) {
            if (i != 0) { stringBuilder.append(","); }
            stringBuilder.append(stringArray[i]);
        }
        return stringBuilder.toString();
    }

    public static String toStringLine(int[] intArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            if (i != 0) { stringBuilder.append(","); }
            stringBuilder.append(intArray[i]);
        }
        return stringBuilder.toString();
    }
}
