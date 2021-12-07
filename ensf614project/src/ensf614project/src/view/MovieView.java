package ensf614project.src.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MovieView extends JFrame {
	
	private ArrayList<JButton> movies;	
	
	public MovieView() {}
	
	public MovieView(ArrayList<String> movieList) {

		movies = new ArrayList<JButton>();
		this.setMovieListButtons(movieList);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(0, 128, 128));

		for(JButton m: movies) {
			m.setPreferredSize(new Dimension(300, 50));
			menu.add(m);
		}
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("Center", menu);
		
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
	
	public ArrayList<JButton> getMoviesButtons() {
		return movies;
	}
	
	public void selectMovieButtonListener(ActionListener actionListener) {
		if (movies != null) {
			for(JButton m: movies) {
				m.addActionListener(actionListener);
			}
		}
		
	}

}
