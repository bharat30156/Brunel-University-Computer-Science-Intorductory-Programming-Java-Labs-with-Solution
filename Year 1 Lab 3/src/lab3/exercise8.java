package lab3;

import java.util.Scanner;

public class exercise8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		while (true)
		{
			System.out.println("Type '*' to Stop the program");
			System.out.print("Enter any character:- ");
			char ch = s.next().charAt(0);
		 

			String D = String.valueOf(ch);
			if (new String(D).equals("*"))
			{
				System.out.println("Special character \n");
				System.out.println("Thanks for using my program");
				break;
			}
		   

			if (ch >= 'A' && ch <= 'Z')
			{
				System.out.println("Is a Uppercase Letter \n");  
			}  
			else if (ch >= 'a' && ch <= 'z')
			{
				System.out.println("Is a lowercase Letter \n" );  
			}
			else if (ch >= '0' && ch <= '9')  
			{
				System.out.println("Is a Number \n" );
			}  
			else
			{
		System.out.println("Is a Character \n" );
		}
 		   
		

		}
	}

}

