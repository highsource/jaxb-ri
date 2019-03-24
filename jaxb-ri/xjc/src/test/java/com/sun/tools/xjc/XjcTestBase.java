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

import java.io.File;

import junit.framework.TestCase;

public abstract class XjcTestBase extends TestCase {

	protected File projectDir;
	protected File srcDir;
	protected File buildDir;
	protected boolean tryDelete = false;

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
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (tryDelete) {
			FileUtils.delDirs(srcDir, buildDir);
			assertTrue("project dir exists", projectDir.delete());
		}
	}
}
