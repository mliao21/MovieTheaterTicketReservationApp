package ensf614project.src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CancelView;
import view.LoginView;
import view.MainMenuView;
import view.PaymentView;
import view.SignUpView;
import view.TheaterView;
import view.SeatView;

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
		
		// Adding mainMenuView ActionListeners
		this.mainMenuView.addLoginButtonListener(new menuLoginViewListener());
		this.mainMenuView.addRegisterButtonListener(new menuRegisterButtonListener());
		this.mainMenuView.addBuyTicketButtonListener(new menuTicketButtonListener());
		this.mainMenuView.addCancelTicketButtonListener(new menuCancelButtonListener());
		this.mainMenuView.addSearchMovieButtonListener(new menuSearchMovieButtonListener());
		
		// Adding loginView ActionListeners 
		
		this.loginView.addLoginButtonListener(new loginButtonListener());
		this.loginView.addMainMenuButtonListener(new loginMainMenuButtonListener());
		
		// Adding signupView ActionListeners
		
		this.signupView.addRegisterButtonListener(new signupRegisterButtonListener());
		this.signupView.addEnterPaymentButtonListener(new signupEnterPaymentButtonListener());
		this.signupView.addMainMenuButtonListener(new signupMainMenuButtonListener());
		
		// Adding paymentView ActionListeners
		this.paymentView.addSubmitButtonListener(new paymentSubmitButtonListener());
		
		// Adding cancelView ActionListeners
		
		this.cancelView.addCancelButtonListener(new cancelButtonListener());
		this.cancelView.addMainMenuButtonListener(new cancelMainMenuButtonListener());
		
		
		
		
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
	
	class menuRegisterButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	// MainMenuView ButtonListeners
	
	class menuCancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class menuSearchMovieButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class menuTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	// LoginView ButtonListeners
	
	class loginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class loginMainMenuButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	// signupView ButtonListeners
	class signupRegisterButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class signupEnterPaymentButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class signupMainMenuButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	// paymentView ButtonListeners
	
	class paymentSubmitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	// cancelView ButtonListeners
	
	class cancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class cancelMainMenuButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	

}
