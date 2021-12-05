package ensf614project.src.app;

import ensf614project.src.controller.ModelController;
import ensf614project.src.controller.ViewController;
import ensf614project.src.model.OrdinaryUser;
import ensf614project.src.view.CancelView;
import ensf614project.src.view.LoginView;
import ensf614project.src.view.MainMenuView;
import ensf614project.src.view.PaymentView;
import ensf614project.src.view.SeatView;
import ensf614project.src.view.SignUpView;
import ensf614project.src.view.TheaterView;

public class MovieApp {
	private OrdinaryUser user;
	private CancelView cancelView; 
	private LoginView loginView;
	private MainMenuView mainMenuView;
	private PaymentView paymentView;
	private SignUpView signupView;
	
	private ModelController modelController;
	private ViewController viewController;
	

	public MovieApp() {
		this.user = (OrdinaryUser) OrdinaryUser.getInstance();
		this.cancelView = new CancelView(); 
		this.loginView = new LoginView();
		this.mainMenuView = new MainMenuView();
		this.paymentView = new PaymentView();
		this.signupView = new SignUpView();
		
		this.modelController = new ModelController(user);
		
		this.viewController = new ViewController(cancelView, loginView, mainMenuView, paymentView, signupView, modelController);
	}
	
	public void start() {
		this.viewController.start();
	}
	

	public static void main(String[] args) {
		MovieApp app = new MovieApp();
		app.start();

	}

}
