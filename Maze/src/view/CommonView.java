package view;

import controller.Controller;

public abstract class CommonView implements View{

	Controller controller;
	
	public CommonView(Controller c) {
		setController(c);
	}
	
	

	public Controller getController() {
		return controller;
	}

	public void setController(Controller c) {
		this.controller = c;
	}
}
