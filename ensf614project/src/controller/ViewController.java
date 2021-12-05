package ensf614project.src.controller;

import ensf614project.src.view.CancelView;
import ensf614project.src.view.LoginView;
import ensf614project.src.view.MainMenuView;
import ensf614project.src.view.PaymentView;
import ensf614project.src.view.SeatView;
import ensf614project.src.view.SignUpView;
import ensf614project.src.view.TheaterView;

public class ViewController {
	private CancelView cancelView; 
	private LoginView loginView;
	private MainMenuView mainMenuView;
	private PaymentView paymentView;
	private SeatView seatView;
	private SignUpView signupView;
	private TheaterView theaterView;
	private ModelController modelController;
	
	
	
	public ViewController(CancelView cancelView, LoginView loginView, MainMenuView mainMenuView,
			PaymentView paymentView, SignUpView signupView,
			ModelController modelController) {
		super();
		this.cancelView = cancelView;
		this.loginView = loginView;
		this.mainMenuView = mainMenuView;
		this.paymentView = paymentView;
		
		this.signupView = signupView;
		
		this.modelController = modelController;
	}



	public void start() {
		this.cancelView.setVisible(false);
		this.loginView.setVisible(false);
		this.paymentView.setVisible(false);
		this.signupView.setVisible(false);
		this.seatView.setVisible(false);
		this.theaterView.setVisible(false);
		this.mainMenuView.setVisible(true);
	}
	
	
	
	
	

}
