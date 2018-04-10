import java.io.PrintWriter;
import java.util.Scanner;

/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	SAAnswer Class. 
*	Responsible for creating the Short answer Answer. 
*/
public class SAAnswer extends Answer{

	protected String text;
	
	////CONSTRUCTOR
	public SAAnswer(String text){
		this.text = text;
	}
	public SAAnswer(Scanner sc){
		this.text = sc.nextLine();
		
	}
	
	//PRINT
	public void print() {
		System.out.println(text);
	}

	//GET CREDIT
	public double getCredit(Answer rightAnswer) {
		//CHecking to see if its right variable
		if( rightAnswer instanceof SAAnswer){
			//setting a string to the text of right answer
			String answer = ((SAAnswer)rightAnswer).text;
			//comparing the values.
			if (answer.equalsIgnoreCase(text))
				return 1.0;
		}
		return 0;
	}
	//Save the TEXT
	public void save(PrintWriter pw){
		pw.println(text);
	}

}
