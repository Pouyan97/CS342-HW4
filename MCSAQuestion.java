/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	MCSAQuestion Class. 
*	Responsible for creating the Multiple choice Single Answer Questions. 
*/
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MCSAQuestion extends MCQuestion{
		
	////Constructor to initialize everything
	///and also set the text
	public MCSAQuestion(String text, double maxValue) {
		super(text, maxValue);
	}
	
	public MCSAQuestion(Scanner sc){
		answers = new ArrayList<MCAnswer>();

		try{
			maxValue = sc.nextDouble();
		}
		catch(InputMismatchException e){
			System.err.println("InputMismatchException: no maxVal for question"
		    		+ "\nINSERT A DOUBLE FOR THE MAXVALUE.\n");
			return;
		}
		sc.nextLine();
		super.text = sc.nextLine();
		int size = sc.nextInt();
		sc.nextLine();

		for(int i =0; i<size; i++){
			MCAnswer A = new MCAnswer(sc);
			addAnswer(A);
		}
		
	}
	
	////GET NEW ANSWER
	//to make an answer through user input.
	public Answer getNewAnswer(){
		
		MCSAAnswer a;
		double credit = 0;
		
		//getting input
		System.out.println("Please make an MCanswer for question:\n\t "+text);
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String text = sc.nextLine();
		System.out.println("Please enter the value(0.0 - 1.0) for answer: \"" + text + "\"");		
		
		//TRY AND CATCH BLOCK
		//To catch inputs that are not numbers.
		try{
			credit = sc.nextDouble();
		}
		catch(InputMismatchException e){
		    System.err.println("InputMismatchException: PLEASE MAKE THE ANSWER"
		    		+ " AGAIN, and \nINSERT A DOUBLE FOR THE VALUE.\n");
		    getNewAnswer();
		}
		//making the answer
		 a = new MCSAAnswer(text, credit);
		 return a;
	}

	
	////GET NEW ANSWER
	//to make an answer through user input of parameters.
	public Answer getNewAnswer(String text, double creditIfSelected){
		MCSAAnswer a = new MCSAAnswer(text, creditIfSelected);
		return a;
	}
	
	
	////GET ANSWER FROM STUDENT
	//get answers that student is selecting
	public void getAnswerFromStudent(){
		System.out.println("Please enter your answer for Question: \n\t"+text);
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String input = sc.nextLine();
		//inputting the answer that was inputted.
		for(int i=0; i< answers.size(); i++){
			if(input.equalsIgnoreCase(((MCAnswer)answers.get(i)).text))
			{
				studentAnswer = answers.get(i);
				return;
			}
		}
		System.out.println("ERROR Answer not in the Question\n Try AGAIN");
		this.getAnswerFromStudent();
			
	}

	//get Value returns the overall
	//value of the question base on answer
	public double getValue() {

		return super.getValue((MCAnswer)studentAnswer);
	}
	
//save the question and its answers.
	public void save (PrintWriter pw){
		pw.print("MCSAQuestion\n");
		super.save(pw);
	}
}

