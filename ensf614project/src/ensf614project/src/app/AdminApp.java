package ensf614project.src.app;

import ensf614project.src.controller.ModelController;
import ensf614project.src.controller.ViewController;
import ensf614project.src.model.OrdinaryUser;
import ensf614project.src.view.AdminMovieView;
import ensf614project.src.view.CancelView;
import ensf614project.src.view.LoginView;
import ensf614project.src.view.MainMenuView;
import ensf614project.src.view.MovieView;
import ensf614project.src.view.PaymentView;
import ensf614project.src.view.SeatView;
import ensf614project.src.view.ShowTimeView;
import ensf614project.src.view.SignUpView;
import ensf614project.src.view.TheaterView;

public class AdminApp {
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
	
	public AdminApp() {
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
		this.viewController.startAdmin();
	}

	public static void main(String[] args) {
		AdminApp app = new AdminApp();
		app.start();
	}

}
