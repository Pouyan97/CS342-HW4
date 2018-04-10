import java.io.PrintWriter;
import java.util.Scanner;

/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	MCAnswer Class. 
*	Responsible for creating the multiple choice answers. 
*/
public  class MCAnswer extends Answer{
	//answer title
	protected String text; 
	//is it selected?
	//protected boolean selected;
	//what is the credit for it?
	protected double creditIfSelected;
	
	//CONSTRUCTORS
	public MCAnswer(){
		
	}
	public MCAnswer(String title, double creditIfSelected) {
		this.text = title;
		this.creditIfSelected = creditIfSelected;
		//this.selected = false;
	}
	public MCAnswer(Scanner sc){
		creditIfSelected = sc.nextDouble();
		sc.skip(" ");
		text = sc.nextLine();
	}
	//PRINTING THE TEXT
	public void print() {
		System.out.println(text);
	}		

	//SET Selection obtion
	//public void setSelected(boolean selected){
//			this.selected = selected;
	//}
	public double getCredit(Answer rightAnswer){
		return creditIfSelected;
	}
	//save the answer
	public void save(PrintWriter pw){
		pw.print(creditIfSelected);
		pw.println(" "+text);
	}

}
