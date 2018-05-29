/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class FileUtil {

    public static void writeLine(String fileName, String lineToWrite) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName));
            fos.write(lineToWrite.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
