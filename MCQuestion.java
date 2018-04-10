/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	MCQuestion Class. 
*	Responsible for creating the multiple choice Questions. 
*/

import java.io.PrintWriter;
import java.util.*;

public abstract class MCQuestion extends Question{
	
	protected ArrayList<MCAnswer> answers;
	
	protected MCQuestion(String text, double maxValue) {
		super(text, maxValue);		
		answers = new ArrayList<MCAnswer>();
	}

	protected MCQuestion(){
		
	}
	protected MCQuestion(Scanner sc){
		
	}
	public void print(){
		System.out.println(text);
		char choice = 'A';
		for(int i =0; i< answers.size(); i++) {
			System.out.print( choice +") "); answers.get(i).print();
			choice++;
		}
	}
	
	/////////////////////////////////////////////
	////adding an answer to the arraylist///
	/////////////////////////////////////////////
	public void addAnswer(MCAnswer ans) {

		answers.add(ans);
	}
	
	/////////////////////////////////////////////
	/////////Using collection class shuffle//////
	/////////////////////////////////////////////
	public void reorderAnswers() {
			Collections.shuffle(answers);
	}
	
	//////GETTING THE VALUE FOR THE QUESTION
	public double getValue(MCAnswer ans){
		if (ans instanceof MCAnswer){
			return maxValue * ans.getCredit(ans);
		}
		return 0;
	}
	
	//////SAVING THE QUESTIONS AND ANSWERS IN A FILE
	public void save (PrintWriter pw){
		pw.println(maxValue);
		pw.println(text);
		pw.println(answers.size());
		for(int i =0; i< answers.size(); i++) {
			answers.get(i).save(pw);
		}
	}
}
