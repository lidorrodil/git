package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public abstract class CommonController implements Controller {

	Model m;
	View v;

	HashMap<String, Command> commandMap;

	public CommonController() {
		commandMap = new HashMap<String, Command>();
		initCommands(commandMap);
	}

	public abstract void initCommands(HashMap<String, Command> commandMap);

	public HashMap<String, Command> getCommandMap() {
		return commandMap;
	}

	public void setCommandMap(HashMap<String, Command> commandMap) {
		this.commandMap = commandMap;
	}

	public void setModel(Model m) {
		this.m = m;
	}

	public void setView(View v) {
		this.v = v;

	}

}
