/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author emi
 */
public class ImageUtils {

        /** Logger for this class and subclasses */
        private static Log log = LogFactory.getLog("ImageUtils");
    
    
        public  static boolean convert(File in, File out, int width, int height, int quality) {

            String size = "";
            if(height>0){
                size = width + "x" + height;
            }else{
                size = "" + width;
            }
            
            return convert(in, out, size, quality);
        }
        
        
        public  static boolean convert(File in, File out, String size, int quality) {
            
            return convert(in, out, size, quality, false);
        }
        
	/**
	 * Uses a Runtime.exec()to use imagemagick to perform the given conversion
	 * operation. Returns true on success, false on failure. Does not check if
	 * either file exists.
	 *
	 * @param in Description of the Parameter
	 * @param out Description of the Parameter
	 * @param newSize Description of the Parameter
	 * @param quality Description of the Parameter
	 * @return Description of the Return Value
	 */
	public  static boolean convert(File in, File out, String size, int quality, boolean resize_to_fit) {
		System.out.println("convert(" + in.getPath()+ ", " + out.getPath()
                                    + ", width x height:" + size + ", " + quality);

		if (quality < 0 || quality > 100) {
			quality = 75;
		}

		ArrayList command = new ArrayList(10);

		// note: CONVERT_PROG is a class variable that stores the location of ImageMagick’s convert command
		// it might be something like "/usr/local/magick/bin/convert" or something else, depending on where you installed it.
                
                String CONVERT_PROG = "";
                
                if(StringUtils.containsIgnoreCase(System.getProperty("os.name"), "windows")){
                    CONVERT_PROG = "C:\\Programmi\\ImageMagick\\convert";
                }else{
                    CONVERT_PROG = "/opt/ImageMagick/bin/convert";
                }
                
		command.add(CONVERT_PROG);
		//command.add("-geometry");
		command.add("-thumbnail");
                if(resize_to_fit){
                    command.add(size+"^");
                    command.add("-gravity");
                    command.add("center");
                    command.add("-crop");
                    command.add(size+"+0+0");
                }else{
                    command.add(size+">");
                }
//                if(height>0){
//                    command.add(width + "x" + height);
//                }else{
//                    command.add("" + width);
//                }
                
                command.add("-quality");
		command.add("" + quality);
		command.add(in.getAbsolutePath());
		command.add(out.getAbsolutePath());

		System.out.println(command);

		return exec((String[])command.toArray(new String[1]));
	}

	/**
	 * Tries to exec the command, waits for it to finsih, logs errors if exit
	 * status is nonzero, and returns true if exit status is 0 (success).
	 *
	 * @param command Description of the Parameter
	 * @return Description of the Return Value
	 */
	private static boolean exec(String[] command) {
		Process proc;

		try {
			//System.out.println("Trying to execute command " + Arrays.asList(command));
			proc = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			System.out.println("IOException while trying to execute " + command);
			return false;
		}

		//System.out.println("Got process object, waiting to return.");

		int exitStatus;

		while (true) {
			try {
				exitStatus = proc.waitFor();
				break;
			} catch (java.lang.InterruptedException e) {
				System.out.println("Interrupted: Ignoring and waiting");
			}
		}
		if (exitStatus != 0) {
			System.out.println("Error executing command: " + exitStatus);
		}
		return (exitStatus == 0);
	}
        
}
