package ensf614project.src.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

public class MovieView extends JFrame {
	
	private ArrayList<JButton> movies;	
	
	public MovieView() {

		movies = new ArrayList<JButton>();
		this.setMovieListButtons(getMovieDB());
		
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(0, 1));
		menu.setBackground(new Color(0, 128, 128));

		
		for(JButton t: movies) {
			t.setPreferredSize(new Dimension(300, 50));
			menu.add(t);
		}
		
		JScrollPane scrollPane = new JScrollPane(menu,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("Center", scrollPane);
		
		setTitle("MOVIES AVAILABLE");
		setSize(400, 400);
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
		
	public void setMovieListButtons(ArrayList<String> movielist) {
		for(String m: movielist) {
			this.movies.add(new JButton (m));
		}
	}
	
	public ArrayList<String> getMovieDB() {
		
		ArrayList<String> movieDB = new ArrayList<String>();
		movieDB.add("Titanic");
		movieDB.add("The Matrix");
		movieDB.add("Lord of the Rings");
		movieDB.add("Harry Potter");
		movieDB.add("The Avengers 1");
		movieDB.add("The Avengers 2");
		movieDB.add("The Avengers 3");
		return movieDB;
	}

	public static void main(String[] args) {
		MovieView theView = new MovieView();
		theView.setVisible(true);
	}

}
