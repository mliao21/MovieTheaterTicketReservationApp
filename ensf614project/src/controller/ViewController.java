package ensf614project.src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ensf614project.src.view.*;

public class ViewController {
	private CancelView cancelView; 
	private LoginView loginView;
	private MainMenuView mainMenuView;
	private PaymentView paymentView;
	private SignUpView signupView;
	private SeatView seatView;
	private TheaterView theaterView;
	private MovieView movieView;
	private ShowTimeView showTimeView;
	private AdminMovieView adminMovieView;
	private ModelController modelController;
	private String selectedMovie;
	private String selectedTheater;
	private String selectedShowTime;
	private String selectedSeat;
	private int selectedShowTimeID;
	
	public ViewController(CancelView cancelView, LoginView loginView, MainMenuView mainMenuView,
			PaymentView paymentView, SignUpView signupView, SeatView seatView, TheaterView theaterView,
			MovieView movieView, ShowTimeView showTimeView, AdminMovieView adminMovieView, ModelController modelController) {
		super();
		this.cancelView = cancelView;
		this.loginView = loginView;
		this.mainMenuView = mainMenuView;
		this.paymentView = paymentView;
		this.signupView = signupView;
		this.theaterView = theaterView;
		this.seatView = seatView;
		this.movieView = movieView;
		this.showTimeView = showTimeView;
		this.adminMovieView = adminMovieView;
		this.modelController = modelController;
	}
	
	public void start() {
		this.cancelView.getFrame().setVisible(false);
		this.loginView.getFrame().setVisible(false);
		this.paymentView.getFrame().setVisible(false);
		this.signupView.getFrame().setVisible(false);
		this.seatView.setVisible(false);
		this.theaterView.setVisible(false);
		this.movieView.setVisible(false);
		this.showTimeView.setVisible(false);
		this.mainMenuView.getFrame().setVisible(true);
		loadListeners();
	}
	
	public void startAdmin() {
		this.adminMovieView.setVisible(true);
		this.adminMovieView.addButtonListener(new addNewMovieButtonListener());
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
		this.signupView.addMainMenuButtonListener(new signupMainMenuButtonListener());
		
		// Adding paymentView ActionListeners
		this.paymentView.addSubmitButtonListener(new paymentSubmitButtonListener());
		
		// Adding cancelView ActionListeners
		this.cancelView.addCancelButtonListener(new cancelButtonListener());
		this.cancelView.addMainMenuButtonListener(new cancelMainMenuButtonListener());
		
		// Adding movieView ActionListeners
		this.movieView.selectMovieButtonListener(new selectMovieButtonListener());
		this.movieView.searchMainMenuButtonListener(new searchMainMenuButtonListener());
		
		// Adding theaterView ActionListeners
		this.theaterView.selectTheaterButtonListener(new selectTheaterButtonListener());
		
		// Adding showtimeView ActionListeners
		this.showTimeView.selectShowTimeButtonListener(new selectShowTimeButtonListener());
		
		// Adding seatView ActionListeners
		this.seatView.selectSeatButtonListener(new selectSeatButtonListener());

	}
	
	class menuLoginViewListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Login pressed");
			mainMenuView.getFrame().setVisible(false);
			loginView.getFrame().setVisible(true);	
		}
	}
	
	class menuRegisterButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainMenuView.getFrame().setVisible(false);
			signupView.getFrame().setVisible(true);	
			signupView.setFirstName(null);
			signupView.setLastName(null);
			signupView.setEmailAddress(null);
			signupView.setPassword(null);
			signupView.setCcn(null);
		}
	}
	
	class menuCancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainMenuView.getFrame().setVisible(false);
			cancelView.getFrame().setVisible(true);	
		}
	}
	
	class menuSearchMovieButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainMenuView.getFrame().setVisible(false);
			movieView = new MovieView(modelController.getMovieNameList());
			movieView.setVisible(true);
			movieView.selectMovieButtonListener(new selectMovieButtonListener());
		}
	}
	
	class menuTicketButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainMenuView.getFrame().setVisible(false);
			movieView = new MovieView(modelController.getMovieNameList());
			movieView.setVisible(true);
			movieView.selectMovieButtonListener(new selectMovieButtonListener());
		}
	}
	
	// LoginView ButtonListeners
	
	class loginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String emailAddress;
			String password;
			try {
				emailAddress = loginView.getEmailAddress();
				password = loginView.getPassword();
				
				boolean response = modelController.login(emailAddress, password);
				
				if(response == true) {
					JOptionPane.showMessageDialog(null,"Logged in" );
					loginView.getFrame().setVisible(false);
					mainMenuView.getFrame().setVisible(true);
				} else if (response == false) {
					JOptionPane.showMessageDialog(null,"Invalid input" );
					loginView.setEmailAddress(null);
					loginView.setPassword(null);
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}			
		}
	}
	
	class loginMainMenuButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			loginView.getFrame().setVisible(false);
			mainMenuView.getFrame().setVisible(true);
		}
	}
	
	// signupView ButtonListeners
	class signupRegisterButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String firstName;
			String lastName;
			String emailAddress;
			String password;
			String ccnumber;
			boolean response;
			
			try {
				firstName = signupView.getFirstName();
				lastName = signupView.getLastName();
				emailAddress = signupView.getEmailAddress();
				password = signupView.getPassword();
				ccnumber = signupView.getCcn();
				
				response = modelController.registerUser(firstName, lastName,
						ccnumber, emailAddress, password);
				
				if (response == true) {
					JOptionPane.showMessageDialog(null,"Registered!");
					signupView.getFrame().setVisible(false);
					mainMenuView.getFrame().setVisible(true);
					
				} else if (response == false) {
					JOptionPane.showMessageDialog(null,"Invalid input" );
					signupView.setFirstName(null);
					signupView.setLastName(null);
					signupView.setEmailAddress(null);
					signupView.setPassword(null);
					signupView.setCcn(null);
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
		}
	}
	
	class signupMainMenuButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// go back to main menu
			signupView.getFrame().setVisible(false);
			mainMenuView.getFrame().setVisible(true);
		}
	}
	
	// paymentView ButtonListeners
	
	class paymentSubmitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int price = 20;
			String couponCode = "";
			String email = "";
			String creditCard = "";
			int seatInstance = modelController.searchSeatInstanceID(selectedShowTimeID, selectedSeat);
			
			paymentView.getFrame().setVisible(false);
			if (couponCode.length() == 0) {
				modelController.createTicket(selectedShowTimeID, seatInstance, price, "SOLD", email, creditCard);
			}
			else {
				modelController.createTicket(selectedShowTimeID, seatInstance, price, "SOLD", email, creditCard, couponCode);
			}
			JOptionPane.showMessageDialog(null, "Successfull Payment. Thank you for choosing us ;D!");
			mainMenuView.getFrame().setVisible(true);
			paymentView.setCouponLabel(null);
			paymentView.setEmailLabel(null);
			paymentView.setCardNumberLabel(null);
		}
	}
	
	// cancelView ButtonListeners
	
	class cancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ticketNumber;
			try {
				ticketNumber = cancelView.getTicketNumber();

				String check = modelController.refundTicket(Integer.parseInt(ticketNumber), modelController.isOrdinaryUser());
				if (check != null) {
					JOptionPane.showMessageDialog(null,check);
					cancelView.getFrame().setVisible(false);
					mainMenuView.getFrame().setVisible(true);
				}
				cancelView.setEmailAddress(null);
				cancelView.setTicketNumber(null);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class cancelMainMenuButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cancelView.getFrame().setVisible(false);
			mainMenuView.getFrame().setVisible(true);	
		}
	}
	
	class selectMovieButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedMovie = e.getActionCommand();
			movieView.setVisible(false);
			theaterView = new TheaterView(modelController.getTheaterNameList());
			theaterView.setVisible(true);
			theaterView.selectTheaterButtonListener(new selectTheaterButtonListener());
		}
	}
	
	class searchMainMenuButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("hello");
			movieView.setVisible(false);
			mainMenuView.getFrame().setVisible(true);
		}
	}
	
	class selectTheaterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedTheater = e.getActionCommand();
			theaterView.setVisible(false);
			ArrayList<String> showTimesList = modelController.getAvailableShowTimesList(selectedMovie, selectedTheater);
			if (showTimesList.size() == 0) {
				JOptionPane.showMessageDialog(null, "There are no show times in this theatre. Please try again!");
				theaterView.setVisible(true);
			}
			else {
				showTimeView = new ShowTimeView(showTimesList);
				showTimeView.setVisible(true);
				showTimeView.selectShowTimeButtonListener(new selectShowTimeButtonListener());
			}
		}
	}
	
	class selectShowTimeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedShowTime = e.getActionCommand();
			showTimeView.setVisible(false);
			selectedShowTimeID = modelController.searchShowTimeID(selectedMovie, selectedTheater, selectedShowTime);
			ArrayList<Integer> statuses = modelController.getSeatsStatuses(selectedShowTimeID);
			if (statuses.size() == 0) {
				JOptionPane.showMessageDialog(null, "Tickets are sold out... Please try again!");
				showTimeView.setVisible(true);
			}
			else {
				seatView = new SeatView(statuses);
				seatView.setVisible(true);
				seatView.selectSeatButtonListener(new selectSeatButtonListener());
			}
		}
	}
	
	class selectSeatButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedSeat = e.getActionCommand();
			seatView.setVisible(false);
			if (modelController.isOrdinaryUser()) {
				paymentView.getFrame().setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Successfull Payment. Thank you for choosing us ;D!");
			}
		}
	}
	
	class addNewMovieButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String movie = adminMovieView.getMovie();
			int theaterId = adminMovieView.getTheaterId();
			int cleaningTime = adminMovieView.getCleaningTime();
			String openingDate = adminMovieView.getOpeningDate();
			String endDate = adminMovieView.getEndDate();
			adminMovieView.setVisible(false);
			modelController.addMovies(movie, theaterId, cleaningTime, openingDate, endDate);
			JOptionPane.showMessageDialog(null, "Movie added successfully into the database!");
		}
	}

}
