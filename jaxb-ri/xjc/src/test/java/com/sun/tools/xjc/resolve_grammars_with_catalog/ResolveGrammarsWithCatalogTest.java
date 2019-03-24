package com.sun.tools.xjc.resolve_grammars_with_catalog;

import java.io.File;

import org.junit.Assert;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.FileUtils;
import com.sun.tools.xjc.OptionsTestBase;
import com.sun.tools.xjc.XjcTaskTest;

public class ResolveGrammarsWithCatalogTest extends OptionsTestBase {

	private File schema;
	private File catalog;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		schema = FileUtils.copy(projectDir, "schema.xsd",
				XjcTaskTest.class.getResourceAsStream("resources/resolve_grammars_with_catalog/schema.xsd"));
		catalog = FileUtils.copy(projectDir, "catalog.cat",
				XjcTaskTest.class.getResourceAsStream("resources/resolve_grammars_with_catalog/catalog.cat"));
	}

	@Override
	protected void tearDown() throws Exception {
		if (tryDelete) {
			schema.delete();
		}
		super.tearDown();
	}

	@Override
	protected String[] getArguments() {
		return new String[] { 
				"-catalog",
				catalog.getAbsolutePath(),
				"http://www.ab.org/schema.xsd",
				// schema.getAbsolutePath(),
				"-d", buildDir.getAbsolutePath() };
	}

	@Override
	protected void checkCodeModel(JCodeModel codeModel) {
		super.checkCodeModel(codeModel);
		Assert.assertNotNull("The class must have already be created.", codeModel._getClass("a.AType"));
	}
}
