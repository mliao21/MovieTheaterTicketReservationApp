package ensf614project.src.app;

import ensf614project.src.controller.*;
import ensf614project.src.model.*;
import ensf614project.src.view.*;

public class MovieApp {
	private OrdinaryUser user;
	private CancelView cancelView; 
	private LoginView loginView;
	private MainMenuView mainMenuView;
	private PaymentView paymentView;
	private SignUpView signupView;
	private SeatView seatView;
	private TheaterView theaterView;
	private MovieView movieView;
	private AdminMovieView adminMovieView;
	private ModelController modelController;
	private ShowTimeView showTimeView;
	private ViewController viewController;
	

	public MovieApp() {
		this.user = (OrdinaryUser) OrdinaryUser.getInstance();
		this.cancelView = new CancelView(); 
		this.loginView = new LoginView();
		this.mainMenuView = new MainMenuView();
		this.paymentView = new PaymentView();
		this.signupView = new SignUpView();
		this.theaterView = new TheaterView();
		this.seatView = new SeatView();
		this.movieView = new MovieView();
		this.showTimeView = new ShowTimeView();
		this.adminMovieView = new AdminMovieView();
		this.modelController = new ModelController(user);
		this.viewController = new ViewController(cancelView, loginView, mainMenuView, paymentView, signupView, 
				seatView, theaterView, movieView, showTimeView, adminMovieView, modelController);
	}
	
	public void start() {
		this.viewController.start();
	}
	
	public static void main(String[] args) {
		LoadData loadData = new LoadData();
		loadData.insertRUs();
		loadData.insertMovies();
		loadData.sellTickets();
		loadData.refundTickets();
		loadData.insertCoupons();

		MovieApp app = new MovieApp();
		app.start();
	}

}
