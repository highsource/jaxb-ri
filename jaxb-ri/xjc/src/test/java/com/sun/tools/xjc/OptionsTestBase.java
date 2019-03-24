package com.sun.tools.xjc;

import java.io.IOException;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.model.Model;

public abstract class OptionsTestBase extends XjcTestBase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		buildDir.mkdirs();
	}
	
	protected abstract String[] getArguments();

	public void testOptions() throws BadCommandLineException, IOException {
		Options options = new Options();
		options.parseArguments(getArguments());
		ConsoleErrorReporter receiver = new ConsoleErrorReporter();
		JCodeModel codeModel = new JCodeModel();
		Model model = ModelLoader.load(options, codeModel, receiver);
		model.generateCode(options, receiver);
		com.sun.codemodel.CodeWriter cw = options.createCodeWriter();
		model.codeModel.build(cw);
		checkCodeModel(codeModel);
	}
	
	protected void checkCodeModel(JCodeModel codeModel) {
	}
}
