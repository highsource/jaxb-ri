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

import java.io.*;

/**
 *
 * @author Yan GAO.
 *         All rights reserved.
 */
public abstract class XjcAntTaskTestBase extends XjcTestBase {
  protected File script;

  public abstract String getBuildScript();

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    script = FileUtils.copy(projectDir, getBuildScript(), XjcAntTaskTestBase.class.getResourceAsStream("resources/" + getBuildScript()));
  }

  @Override
  protected void tearDown() throws Exception {
    if (tryDelete) {
      script.delete();
    }
    super.tearDown();
  }

  static boolean is9() {
    return System.getProperty("java.version").startsWith("9");
  }
}
