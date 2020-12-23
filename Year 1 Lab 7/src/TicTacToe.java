import javax.swing.JOptionPane;

public class TicTacToe {

	public static void main(String[] args) {
		try{
			//String a = JOptionPane.showInputDialog("Please Enter the Size Of Grid");
			int size = 4;//Integer.valueOf(a);
			
			Board board = new Board(size, size);
			Game game = new Game(board);
			game.setVisible(true);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Size of Grid is not Correct");
		}

	}

}
