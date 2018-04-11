/*
 *
 *	Pouyan Pourmirjafari
 *	spourm2
 *
 *	Answer Class. 
 *	Responsible for creating the answers. 
 */
import java.io.PrintWriter;
import java.util.Scanner;


//EDITTED
//change apr 11 


public abstract class Answer {
	

	//Answer constructor which intializes the private 
	//values and sets the text to the passed variable
	protected Answer(){
		
	}
	public Answer(Scanner sc){
		
		
	}
	//Responsible for printing the answers
	public abstract void print();

	//returns the value of the answer based on it being selected or not.
	public abstract double getCredit(Answer rightAnswer);
	
	public abstract void save(PrintWriter pw);
	
}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
///////THE SECTION BLOW IS THE HW1 PORTION OF THE CODE THAT IS UNUSED/////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

/*	private String text;
	//selected value for the answer
	private double selValue;
	//unselected value for the answer
	private double unselValue;
	//bool keeping track of the chosen answer
	private boolean selected;
	//total value of the answer.
	private double totalVal;
*/




/*	public Answer(String text){
		this.text = text;
		selValue = 0;
		unselValue =0;
		selected = false;
		totalVal =0;
}
 */





/*	//if answer is selected the answer flag is true else its false
public void setSelected(Boolean bool) {
	if (bool == true ){
		selected = true;
	}
	else 
		selected = false;
}

//Returns the flag selected which shows if its selected or not
public boolean getSelected() {
	return selected;
}

//setting the value for the answer, whether its selected or not
public void setValue(double sel, double unsel) {
	this.selValue = sel;
	this.unselValue = unsel;
	totalVal = sel + unsel;

}

//get total is responsible of getting the total score for the answer.
public double getTotal() {
	return totalVal;
}
*/
