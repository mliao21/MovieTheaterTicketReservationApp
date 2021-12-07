package ensf614project.src.app;

import ensf614project.src.controller.*;
import ensf614project.src.model.*;
import ensf614project.src.view.*;

public class AdminApp {
	private OrdinaryUser user;
	private AdminMovieView adminMovieView;
	private ModelController modelController;
	private ViewController viewController;
	
	public AdminApp() {
		this.user = (OrdinaryUser) OrdinaryUser.getInstance();
		this.adminMovieView = new AdminMovieView();
		this.modelController = new ModelController(user);
		this.viewController = new ViewController(adminMovieView, modelController);
	}
	
	public void start() {
		this.viewController.startAdmin();
	}
	
	public static void main(String[] args) {
		AdminApp app = new AdminApp();
		app.start();
	}

}
