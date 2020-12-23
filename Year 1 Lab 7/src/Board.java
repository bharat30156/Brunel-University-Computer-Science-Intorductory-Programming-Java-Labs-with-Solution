import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 
 * @author Bharat
 *
 */
 class Board 
 {
	 
	 public static final int FREE = 0, COMPUTER = 1, PLAYER = 2, BLOCKED = 3;
	 private final int[][] board;
	 int size, win;
	 
	 public Board(int s, int w)
	 {
		 size = s;
		 win = w;
		 board = new int[size][];
		 for (int i = 0; i < size; i++)
		 {
			 board[i]= new int[size];
		 }
	 }
	 
	 public int get(int i, int j)
	 {
		 return board[i][j];
	 }
	 
	 public void set (int i, int j, int m) 
	 {
		 board[i][j] = m;
	 }
	 
	 // Return FREE (Game Not Finish), COMPUTER (compute win),
	 // PLAYER (Player win), BLOCKED (No More Moves Available)
	 public int checkWon()
	 {
		 int mark, number;
		 for(int i = 0; i < size; i++)
		 {
			 for (int j = 0; j < size; j++)
			 {
				 if (board[i][j] == FREE)
				 {
					 continue;
				 }
				 mark = board[i][j];
				 //For Horizontal
				 number = 1;
				 for (int k = 1; k < win; k++)
				 {
					 if (k + j >= size)
					 {
						 break;
					 }
					 if (board[i][j + k] == mark)
					 {
						 number++;
					 }
					 if (number == win)
					 {
						 return mark;
					 }
				 }
				 //For vertical
				 number = 1;
				 for(int k = 1; k < win; k++)
				 {
					 if ( k + i >= size)
					 {
						 break;
					 }
					 if (board[i + k][j] == mark)
					 {
						 number++;
					 }
					 if (number == win)
					 {
						 return mark;
					 }
				 }
				 //For Right Down
				 number = 1;
				 for (int k = 1; k < win; k++)
				 {
					if(k + i >= size || k + j >= size)
					{
						break;
					}
					if (board[i + k][j + k] == mark)
					{
						number++;
					}
					if (number == win)
					{
						return mark;
					}
				 }
				 //For left Down
				 number = 1;
				 for (int k = 1; k < win; k++)
				 {
					 if (i + k >= size || j - k < 0)
					 {
						 break;
					 }
					 if (board[i + k][j - k] == mark)
					 {
						 number++;
					 }
					 if (number == win)
					 {
						 return mark;
					 }
				 }
			 }
		 }
		 for (int[] bb : board)
		 {
			 for (int b : bb)
			 {
				 if (b == FREE)
				 {
					 return FREE;
				 }
			 }
		 }
		 return BLOCKED;
	 }
	 public int getSize()
	 {
		 return size;
	 }
 }
 
 //*******************************
 class Move
 {
	 public int row, col;
	 
	 public Move(int r, int c)
	 {
		 row = r;
		 col = c;
	 }
 }
 
 //*******************************
 abstract class Strategy
 {
	 public abstract Move calculateNextMove (Board b);
 }
 
 //*******************************
 class RandomStrategy extends Strategy 
 {
	 public Move calculateNextMove (Board b)
	 {
		 int size = b.getSize();
		 Random r = new Random(System.currentTimeMillis());
		 int[][] visited = new int [size][size];
		 int square = size * size;
		 while (square > 0)
		 {
			 int hit = r.nextInt(square);
			 for (int i = 0; i < size; i++)
			 {
				 for (int j = 0; j < size; j++)
				 {
					 if (b.get(i, j) != Board.FREE)
					 {
						 continue;
					 }
					 hit--;
					 if(hit < 0)
					 {
						 return new Move (i, j);
					 }
				 }
			 }
			 square--;
		 }
		 return null;
	 }
	 
 }
 
//************************************
 class SmartStrategy extends Strategy 
 {
	 public Move calculateNextMove(Board b) 
	 {
		 int size = b.getSize();
		 Move move;
	     int hit;
	     //For Horizontal control
	     for (int k = 1; k < 3; k++) 
	     {
	         // k = 1 -> COMPUTER
	         // K = 2 -> PLAYER
	    	 for (int i = 0; i < size; i++) 
	         {
	        	 hit = 0;
	             hit = 0;
	             move = null;
	             for (int j = 0; j < size; j++) 
	             {
	            	 if (b.get(i, j) == k) 
	            	 {
	            		 hit++;
	                 }
	                 if (b.get(i, j) == Board.FREE) 
	                 {
	                     move = new Move(i, j);
	                 }
	             }
	             if (hit == size - 1 && move != null) 
	             {
	            	 return move;
	             }
	          }
	       }
	       //For Vertical control
	       for (int k = 1; k < 3; k++) 
	       {
	    	   // k = 1 -> COMPUTER
	           // K = 2 -> PLAYER
	           for (int i = 0; i < size; i++) 
	           {
	        	   hit = 0;
	               move = null;
	               for (int j = 0; j < size; j++) 
	               {
	            	   if (b.get(j, i) == k) 
	            	   {
	            		   hit++;
	                   }
	                   if (b.get(j, i) == Board.FREE) 
	                   {
	                       move = new Move(j, i);
	                   }
	                }
	                if (hit == size - 1 && move != null) 
	                {
	                    return move;
	                }
	            }
	        }
	        //For Left to right down control
	        for (int k = 1; k < 3; k++) 
	        {
	        	// k = 1 -> COMPUTER
	            // K = 2 -> PLAYER
	            hit = 0;
	            hit = 0;
	            move = null;
	            for (int j = 0; j < size; j++) 
	            {
	                if (b.get(j, j) == k) 
	                {
	                    hit++;
	                }
	                if (b.get(j, j) == Board.FREE) 
	                {
	                    move = new Move(j, j);
	                }
	            }
	            if (hit == size - 1 && move != null) 
	            {
	                return move;
	            }

	        }
	        // Right to left-down control
	        int a = 0;
	        for (int k = 1; k < 3; k++) 
	        {
	            // k = 1 -> COMPUTER
	            // K = 2 -> PLAYER

	            hit = 0;
	            move = null;
	            for (int j = size-1; j >= 0; j--) 
	            {
	                if (b.get(j, a) == k) 
	                {
	                    hit++;
	                }
	                if (b.get(j, a) == Board.FREE) 
	                {
	                    move = new Move(j, a);
	                }
	            }
	            if (hit == size - 1 && move != null) 
	            {
	                return move;
	            }
	            a++;

	        }
	        Random r = new Random(System.currentTimeMillis());
	        int[][] visited = new int[size][size];
	        int square = size * size;
	        while (square > 0) 
	        {
	            hit = r.nextInt(square);
	            for (int i = 0; i < size; ++i) 
	            {
	                for (int j = 0; j < size; ++j) 
	                {
	                    if (b.get(i, j) != Board.FREE) 
	                    {
	                        continue;
	                    }
	                    --hit;
	                    if (hit < 0) 
	                    {
	                        return new Move(i, j);
	                    }
	                }
	            }
	            --square;
	        }
	        return null;
	  }
 }
 
 //***********************************
 class MyButton extends JButton 
 {
	 public int row, col;
	 public MyButton(String text, int r, int c) 
	 {
		 super(text);
		 row = r;
		 col = c;
	 }
 }

//************************************** 
 class Game extends JFrame 
 {
	 private final Board board;
	 private final Strategy str = new SmartStrategy();
	 MyButton buttons[][];
	    
	 public Game(Board b) 
	 {
		 board = b;
	     int s = board.getSize();
	     buttons = new MyButton[s][s];
	     ActionListener al = new MyActionListener();
	     getContentPane().setLayout(new GridLayout(s, s));
	     for (int i = 0; i < s; ++i) 
	     {
	    	 for (int j = 0; j < s; ++j) 
	    	 {
	    		 buttons[i][j] = new MyButton("   ", i, j);
	    		 buttons[i][j].setBackground(Color.WHITE);
	    		 buttons[i][j].setBorder(new LineBorder(Color.BLACK));;
	             buttons[i][j].addActionListener(al);
	             add(buttons[i][j]);
	         }
	     }
	     setLocationRelativeTo(null);
	     pack();
	     int a = JOptionPane.showConfirmDialog(rootPane, "Computer is first?","Who is first",JOptionPane.YES_NO_OPTION);
	     System.out.println(a);
	     if (a == 0) 
	     {
	    	 computerMove(al);
	     }
	 }
	    
	 private class MyActionListener implements ActionListener 
	 {
		 public void actionPerformed(ActionEvent evt) 
		 {
			 MyButton bt;
			 bt = (MyButton) (evt.getSource());
			 bt.setText(" O ");
			 bt.setForeground(Color.ORANGE);
			 JOptionPane.showMessageDialog(null, new JLabel(
					 "<html><h2><font color='blue'>PLAYER MADE THE MOVE</font></h2></html>"));
	         bt.setFont(new Font("Arial", Font.PLAIN, 70));
	         board.set(bt.row, bt.col, Board.PLAYER);
	         bt.removeActionListener(this);
	         check();
      
	         //Computer MOVE
	          computerMove(this);
	     }
	 }
	 public void computerMove(ActionListener myActionListener)
	 {
		 MyButton bt;
		 Move m = str.calculateNextMove(board);
		 bt = buttons[m.row][m.col];
		 bt.setText(" X ");
		 bt.setForeground(Color.DARK_GRAY);
		 JOptionPane.showMessageDialog(null, new JLabel(
				 "<html><h2><font color='blue'>COMPUTER MADE THE MOVE</font></h2></html>"));
		 bt.setFont(new Font("Arial", Font.PLAIN, 70));
		 bt.removeActionListener(myActionListener);
		 board.set(bt.row, bt.col, Board.COMPUTER);
		 check();
	 }
	 private void check()
	 {
		 int check = board.checkWon();
		 switch(check)
		 {
		 case Board.COMPUTER:
			 JOptionPane.showMessageDialog(null, new JLabel(
					 "<html><h2><font color='red'>COMPUTER WON THE MATCH !!</font></h2></html>"));
			 System.exit(0);
			 break;
		 case Board.PLAYER:
			 JOptionPane.showMessageDialog(null, new JLabel(
					 "<html><h2><font color='red'>PLAYER WON THE MATCH !!</font></h2></html>"));
			 System.exit(0);
			 break;
		 case Board.BLOCKED:
			 JOptionPane.showMessageDialog(null, new JLabel(
					 "<html><h2><font color='red'>DRAW NOBODY WON THE MATCH !!</font></h2></html>"));
			 System.exit(0);
			 break;
		 }
	 }
 }
 
 
