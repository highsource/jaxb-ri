/*
 * Copyright (c) 2011, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.sun.tools.xjc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileUtils {
	
	private FileUtils() {
		// Make this utility class non-instantiable
	}

	public static void delDirs(File... dirs) {
	    for (File dir : dirs) {
	        if (!dir.exists()) {
	            continue;
	        }
	        if (dir.isDirectory()) {
	            for (File f : dir.listFiles()) {
	                delDirs(f);
	            }
	            dir.delete();
	        } else {
	            dir.delete();
	        }
	    }
	}

	public static File copy(File dest, String name, InputStream is) throws FileNotFoundException, IOException {
	    return copy(dest, name, is, null);
	  }

	private static File copy(File dest, String name, InputStream is, String targetEncoding)
	      throws FileNotFoundException, IOException {
	    File destFile = new File(dest, name);
	    OutputStream os = new BufferedOutputStream(new FileOutputStream(destFile));
	    Writer w = targetEncoding != null ?
	        new OutputStreamWriter(os, targetEncoding) : new OutputStreamWriter(os);
	    byte[] b = new byte[4096];
	    int len = -1;
	    while ((len = is.read(b)) > 0) {
	      w.write(new String(b), 0, len);
	    }
	    w.flush();
	    w.close();
	    is.close();
	    return destFile;
	  }
}