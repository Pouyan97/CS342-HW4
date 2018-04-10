/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	MCMAQuestion Class. 
*	Responsible for creating the Multiple choice Multiple Answer Questions. 
*/

import java.io.PrintWriter;
import java.util.*;
public class MCMAQuestion extends MCQuestion{
	protected double baseCredit;
	protected ArrayList<Answer> studentAnswer;
	
	
	
	
	public MCMAQuestion(String text, double maxValue) {
		super(text, maxValue);
	}
	
	//Constructor making the exam through the file passsed in
	public MCMAQuestion(Scanner sc){
		answers = new ArrayList<MCAnswer>();
		studentAnswer = new ArrayList<Answer>();
		try{
			maxValue = sc.nextDouble();
		}
		catch(InputMismatchException e){
			System.err.println("InputMismatchException: no maxVal for question "
		    		+ "\nINSERT A DOUBLE FOR THE MAXVALUE.\n");
			return;
		}
		sc.nextLine();
		super.text = sc.nextLine();
		baseCredit = sc.nextDouble();
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
		MCMAAnswer a;
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
		 a = new MCMAAnswer(text, credit);
		 return a;
	}
	////GET NEW ANSWER
	//to make an answer through user input.
	public Answer getNewAnswer(String text, double creditIfSelected){
		MCSAAnswer a = new MCSAAnswer(text, creditIfSelected);
		
		return a;
	}
	
	
	////GET ANSWER FROM STUDENT
	//get answers that student is selecting
	public void getAnswerFromStudent(){
		System.out.println("Please enter your answers for Question: \n\t"+text);
		Scanner sc = ScannerFactory.getKeyboardScanner();
		//selecting the answer that was inputted.
		String input = sc.nextLine();
		boolean isRight = false;
		while(!input.equalsIgnoreCase("q")){
			for(int i=0; i< answers.size(); i++){
	
				if(input.equalsIgnoreCase(((MCAnswer)answers.get(i)).text))
				{
					//	answers.get(0).setSelected(true);
					studentAnswer.add(answers.get(i));
					isRight = true;
				}
			}
			if(isRight == false){
				System.out.println("ERROR Answer not in the Question\n Try AGAIN");
			}
			System.out.println("If you are done enter q / Q");
			input = sc.nextLine();
		}
	}
	/////////score the question.
	public double getValue() {
		double score = 0;
		double baseScore = maxValue*baseCredit;
		for(int i = 0; i < studentAnswer.size(); i++ ){
			score += super.getValue((MCAnswer)studentAnswer.get(i));
		}
		//score = maxValue * getCredit(rightAnswer, studentAnswer);
		return (score + baseScore);
	}
	
	//////////save the question and its answers to a file
	public void save (PrintWriter pw){
		pw.println("MCMAQuestion");
		pw.print(maxValue+"\n");
		pw.print(text+"\n");
		pw.print(baseCredit + "\n");
		pw.print(answers.size()+"\n");
		for(int i =0; i< answers.size(); i++) {
			answers.get(i).save(pw);
		}	
	}
	/////Save student answers in a file given
	public void saveStudentAnswer(PrintWriter pw){
		pw.println("MCMAAnswer");
		pw.println(studentAnswer.size());
		for(int i =0; i< studentAnswer.size(); i++) {
			pw.print(((MCAnswer)studentAnswer.get(i)).creditIfSelected+" ");
			pw.println(((MCAnswer)studentAnswer.get(i)).text);
		}	
	}
	
	/////////Load the answers onto the class
	public void restoreStudentAnswer(Scanner sc){
		sc.nextLine();
		int size = sc.nextInt();
		double c;
		String input;
		for(int i=0; i< size; i++){
				c = sc.nextDouble();
				sc.next();
				input = sc.nextLine();
					MCAnswer a = new MCAnswer(input, c);
					studentAnswer.add(a);
		}
	}
}

