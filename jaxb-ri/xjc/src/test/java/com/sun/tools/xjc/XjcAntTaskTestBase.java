/*
 * Copyright (c) 2017, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.sun.tools.xjc;

import junit.framework.TestCase;
import java.io.*;

/**
 *
 * @author Yan GAO.
 *         All rights reserved.
 */
public abstract class XjcAntTaskTestBase extends TestCase {
  protected File projectDir;
  protected File srcDir;
  protected File buildDir;
  protected File script;
  protected boolean tryDelete = false;

  public abstract String getBuildScript();

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    projectDir = new File(System.getProperty("java.io.tmpdir"), getClass().getSimpleName() + "-" + getName());
    if (projectDir.exists() && projectDir.isDirectory()) {
      FileUtils.delDirs(projectDir);
    }
    srcDir = new File(projectDir, "src");
    buildDir = new File(projectDir, "build");
    assertTrue("project dir created", projectDir.mkdirs());
    script = FileUtils.copy(projectDir, getBuildScript(), XjcAntTaskTestBase.class.getResourceAsStream("resources/" + getBuildScript()));
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    if (tryDelete) {
      FileUtils.delDirs(srcDir, buildDir);
      script.delete();
      assertTrue("project dir exists", projectDir.delete());
    }
  }

  static boolean is9() {
    return System.getProperty("java.version").startsWith("9");
  }
}
