/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	Exam Class. 
*	Responsible for creating the Exam. 
*/

import java.io.PrintWriter;
import java.util.*;

public class Exam {

	/////////////////////////////////////////////
	/////////Private variables of Exam///////////
	/////////////////////////////////////////////
	//String to store the text
	private String text;
	//Arraylist of Questions to store different questions.
	private ArrayList<Question> questions;


	/////////////////////////////////////////////
	////Constructor to initialize everything////
	//////and also set the text and arraylist///
	///////////////////////////////////////////
	public Exam(String text) {
		this.text = text;
		questions = new ArrayList<Question>();
	}
	
	/////////////////////////////////////////////
	//////Saving the exam in a file passed//////
	/////////////////////////////////////////////
	//Constructor looping through the file and saving//
	
	public Exam(Scanner sc){
		//allocate mem for the list
		questions = new ArrayList<Question>();
		text = sc.nextLine();
		//while loop going to the end of the file
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if(line.equals("\n")){

			}
			else if(line.equalsIgnoreCase("MCSAQuestion")){
				MCSAQuestion MCSAQ = new MCSAQuestion(sc);
				addQuestion(MCSAQ);
			}
			else if(line.equalsIgnoreCase("SAQuestion")){
				SAQuestion SAQ = new SAQuestion(sc);
				addQuestion(SAQ);
			}
			else if(line.equalsIgnoreCase("MCMAQuestion")){
				MCMAQuestion MCMAQ = new MCMAQuestion(sc);
				addQuestion(MCMAQ);
			}
			else if(line.equalsIgnoreCase("NumQuestion")){
				NumQuestion NUMQ = new NumQuestion(sc);
				addQuestion(NUMQ);
			}
		}
	}
	
	/////////////////////////////////////////////
	/////adding a question to the arraylist///////
	/////////////////////////////////////////////
	public void addQuestion(Question q) {
		questions.add(q);
		
	}
	
	/////////////////////////////////////////////
	//////Print the Exam with its questions////
	/////////////////////////////////////////////
	public void print() {
		System.out.println( text + "\n");

		for(int i =0; i< questions.size(); i++) {
			System.out.println("\nQuestion " + (i+1) +  ") ");
			questions.get(i).print();
		}
	}

	/////////////////////////////////////////////
	//////Get the refrence of question to ///////
	//////access its methods to edit them.///////
	/////////////////////////////////////////////	
/*	public Question getQuestion(int position) {

		return questions.get(position-1);
	}*/
	
	/////////////////////////////////////////////
	/////////Using collection class to shuffle///
	/////////////////////////////////////////////
	public void reorderQuestions() {
		Collections.shuffle(questions);
		//////Shuffle questions and answers.
		for(int i =0; i< questions.size(); i++) {
			if (questions.get(i) instanceof MCQuestion)
				((MCQuestion) questions.get(i)).reorderAnswers();
		}

	}
	public void reorderMCAnswers(int position){
		if (position <= 0){
			for(int i =0; i< questions.size(); i++) {
				if (questions.get(i) instanceof MCQuestion)
					((MCQuestion) questions.get(i)).reorderAnswers();
			}
		}
		else {
			if(questions.get(position-1) instanceof MCQuestion){
				((MCQuestion) questions.get(position-1)).reorderAnswers();

			}
		}
	}


	/////////////////////////////////////////////
	//////if less than zero answer everything//////
	/////////////////////////////////////////////
	public void getAnswerFromStudent(int position){
		if (position <= 0){
			for(int i =0; i< questions.size(); i++) {
				questions.get(i).getAnswerFromStudent();
			}
		}
		else
			questions.get(position-1).getAnswerFromStudent();
	}

	/////////////////////////////////////////////
	/////////get Value returns the overall///////
	////value of the question base on answer/////
	/////////////////////////////////////////////
	public double getValue() {
		//temp val to return
		double score = 0;

		System.out.println("\n GRADING..... \n");

		/////Printing out the score for every question.////
		for(int i =0; i< questions.size(); i++) {
			score += questions.get(i).getValue();
		}
		///////////////////////////////////////
		//Printing out the score student got///
		//the total value of the whole exam////
		///and the grade the student receives//
		///////////////////////////////////////
		System.out.println("Score :  "+score);
		return score;		
	}
	public void reportQuestionValue(){
		double score = 0;
		double total = 0;
		String format = "|%1$-12s |%2$-7s|\n";
		System.out.println(" _____________________ ");
		//System.out.println("|             |       |");
		System.out.format(format," Questions "," Score");
		for(int i =0; i< questions.size(); i++) {
			System.out.format(format, " Question "+ (i+1) ," "+ questions.get(i).getValue());
			total += questions.get(i).maxValue;
			score += questions.get(i).getValue();
		}
		format = "|%1$-12s =%2$-7s|\n";
		System.out.println("|_____________________|");
		System.out.format(format," Total ", " "+ score);
		System.out.format(format," Exam Total ",  " "+total);
		System.out.format(format," Grade "," " +  String.format("%.2f",(score/total)*100));
		System.out.println(" ---------------------");
	}
	/////////////////////////////////////////////
	//////Saving the exam in a file passed//////
	/////////////////////////////////////////////
	public void save (PrintWriter pw){
		pw.println(text);
		for(int i =0; i< questions.size(); i++) {
			pw.print("\n");
			questions.get(i).save(pw);
		}
				
	}
	/////////////////////////////////////////////
	//////Saving the answers in a file passed//////
	/////////////////////////////////////////////
	public void saveStudentAnswers(PrintWriter pw){
		for(int i =0; i< questions.size(); i++) {
			questions.get(i).saveStudentAnswer(pw);
			pw.print("\n");
		}
	}
	
	/////////////////////////////////////////////
	//////loading the answers from a file passed//////
	/////////////////////////////////////////////
	public void restoreStudentAnswers(Scanner sc){
		String input = "";
		String studentName = sc.nextLine();
		input = sc.nextLine();
		
		for(int i =0; i< questions.size(); i++) {
			questions.get(i).restoreStudentAnswer(sc);
			sc.nextLine();
		}
	}
}