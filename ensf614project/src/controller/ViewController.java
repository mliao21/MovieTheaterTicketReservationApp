package ensf614project.src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private SignUpView signupView;
	private SeatView seatView;
	private TheaterView theaterView;
	private ModelController modelController;
	
	public ViewController(CancelView cancelView, LoginView loginView, MainMenuView mainMenuView,
			PaymentView paymentView, SignUpView signupView, SeatView seatView, TheaterView theaterView,
			ModelController modelController) {
		super();
		this.cancelView = cancelView;
		this.loginView = loginView;
		this.mainMenuView = mainMenuView;
		this.paymentView = paymentView;
		this.signupView = signupView;
		this.theaterView = theaterView;
		this.seatView = seatView;
		this.modelController = modelController;
	}
	
	
	
	public void start() {
		this.cancelView.getFrame().setVisible(false);
		this.loginView.getFrame().setVisible(false);
		this.paymentView.getFrame().setVisible(false);
		this.signupView.getFrame().setVisible(false);
		this.seatView.setVisible(false);
		this.theaterView.setVisible(false);
		this.mainMenuView.getFrame().setVisible(true);
		loadListeners();
	}
	
	
	public void loadListeners() {
		this.mainMenuView.addLoginButtonListener(new menuLoginViewListener());
		
		
		
	}
	
	class menuLoginViewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Login pressed");
			mainMenuView.getFrame().setVisible(false);
			loginView.getFrame().setVisible(true);
			
			
			
			
			
		}
		
	}
	
	
	
}
