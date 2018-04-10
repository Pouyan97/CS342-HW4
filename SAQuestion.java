/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	SAQuestion Class. 
*	Responsible for creating the Short Answer Questions. 
*/
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SAQuestion extends Question{

	// Constructor Calls parent constructor
	protected SAQuestion(String text, double maxValue) {
		super(text, maxValue);
	}

	public SAQuestion(Scanner sc) {

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
		SAAnswer A = new SAAnswer(sc);
		rightAnswer = A;
		
	}

	////GET NEW ANSWER
	//to make an answer through user input.
	public Answer getNewAnswer() {
		System.out.println("Please enter your SAanswer for Question: \n\t\""+text+"\"");
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String text = sc.nextLine();
		SAAnswer a = new SAAnswer(text);
		rightAnswer = a;
		 return a;
	}
	////GET NEW ANSWER
	//to make an answer through given parameter.
	 public Answer getNewAnswer(String text){
			SAAnswer a = new SAAnswer(text);
			rightAnswer = a;
			return a;
		 
	 }
	 
	////GET ANSWER FROM STUDENT
	//get answer given by student
	public void getAnswerFromStudent() {
		System.out.println("Please enter your answer for Question: \n\t\""+text+"\"");
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String text = sc.nextLine();		
		SAAnswer a = new SAAnswer(text);
		studentAnswer = a;
	}

	//Get the total value that contributes to this question.
	public double getValue() {
		double score = 0;
		score = maxValue * studentAnswer.getCredit(rightAnswer);
		return score;
	}
	//SAVE THE TEXT AND THE ANSWER
	public void save (PrintWriter pw){
		pw.println("SAQuestion");
		pw.println(maxValue);
		pw.println(text);
		rightAnswer.save(pw);
	}

}
