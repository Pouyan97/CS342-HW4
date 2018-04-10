import java.io.PrintWriter;
import java.util.Scanner;

/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	Question Class. 
*	Responsible for creating the Questions. 
*/

public abstract class Question {

	/////////Private variables of Question///////
	
	//text to store the text
	protected String text;
	//to initialize rightAnswer
	protected Answer rightAnswer;
	//to initialize the student answer
	protected Answer studentAnswer;
	//value of the question
	protected double maxValue;


	////Constructor to initialize everything
	///and also set the text
	public Question(){
		
	}
	protected Question(String text, double maxValue)
	{
		this.text = text;
		this.maxValue = maxValue;
	}
	public Question(Scanner sc){
		
	}

		//////Print the question with its answers////
	public void print(){
		System.out.println(text);
	}

	//////Set Right Answer/////////////////
	public void setRightAnswer(Answer ans){
		rightAnswer = ans;
	}

	//////GET NEW ANSWER/////////////////
	public abstract Answer getNewAnswer();

	//////get Answer From Student///////////////
	public abstract void getAnswerFromStudent();

	//get Value returns the overall
	//value of the question base on answer
	public abstract double getValue();
	public abstract void save (PrintWriter pw);
	
	/////////////////////////////////////////////
	////////SAVE THE ANSWERS INTO A FILE/////////
	////////////////////////////////////////////
	public void saveStudentAnswer(PrintWriter pw){
		/////find the right answer format
		////if single choice answer:
		if(studentAnswer instanceof MCAnswer){
			pw.println("MCSAAnswer");
			pw.print(((MCAnswer)studentAnswer).creditIfSelected+" ");
			pw.println(((MCAnswer)studentAnswer).text);
		}
		////if short answer:
		if(studentAnswer instanceof SAAnswer){
			pw.println("SAAnswer");
			pw.println(((SAAnswer)studentAnswer).text);
		}
		if(studentAnswer instanceof NumAnswer){
			pw.println("NumAnswer");
			pw.println(((NumAnswer)studentAnswer).ans);
		}
	}
	
	
	
	/////////////////////////////////////////////
	////////read THE ANSWERS from A FILE/////////
	////////////////////////////////////////////
	public void restoreStudentAnswer(Scanner sc){
		String input = sc.nextLine();
		//////choose the answer format
		
		if(input.equalsIgnoreCase("SAAnswer")){
			String s=sc.nextLine();
			SAAnswer a = new SAAnswer(s);
			studentAnswer = a;
		}
		else if(input.equalsIgnoreCase("MCSAAnswer"))
		{
			double c = sc.nextDouble();
			sc.next();
			MCSAAnswer a = new MCSAAnswer(sc.nextLine(), c);
			studentAnswer = a;
		}
		else if(input.equalsIgnoreCase("MCMAAnswer"))
		{
			if(this instanceof MCMAQuestion)
			{
				((MCMAQuestion)this).restoreStudentAnswer(sc);
			}
		}
		else if(input.equalsIgnoreCase("NumAnswer")){
			NumAnswer a = new NumAnswer(sc.nextDouble());
			studentAnswer= a;
			sc.nextLine();
		}

	}
	
}























/////////////////////////////////////////////
/////////adding an answer to the arraylist///
/////////////////////////////////////////////
/*	public void addAnswer(Answer ans) {
if(numAnswers == 0)
{
System.out.println("Cannot Add more answers.");
return;
}
answers.add(ans);
numAnswers--;

}*/
////////////////////////////////////////////
//////selecting answer and /////////////////
//////unselcting all other answers//////////
////////////////////////////////////////////
/*public void selectAnswer(int pos) {

//if answer chosen is not in bound the quit
if(answers.get(pos-1) == null || pos > 5)
return;

// unselect every answer 
for(int i =0; i< answers.size(); i++) {
answers.get(i).setSelected(false);
}
//select the selected answer and print the chosen answer
answers.get(pos-1).setSelected(true);	
System.out.println("\nAnswer selected for \""+ this.text +"\" is ");
answers.get(pos-1).print();
}
*/
///////////////////////////////////////////////////
/////////Unselect the answer chosen if in Bound////
///////////////////////////////////////////////////
/*	public void unselectAnswer(int pos) {

if(answers.get(pos-1) != null || pos > 5)
answers.get(pos-1).setSelected(false);
}*/

/////////////////////////////////////////////
/////////Using collection class shuffle//////
/////////////////////////////////////////////
/*	public void reorderAnswers() {
for(int i =0; i< answers.size(); i++) {
answers.get(i).setSelected(false);
}
Collections.shuffle(answers);
}
*/
/////////////////////////////////////////////
///////Get total for grading.//////////////
/////////////////////////////////////////////
/*	public double getTotal() {
double sum = 0; 
for(int i =0; i< answers.size(); i++) {
sum += answers.get(i).getTotal();
}
return sum;
}*/



/*		System.out.println("Please enter your answer; ");
Scanner sc = new Scanner(System.in);
studentAnswer = new Answer(sc.nextLine());

}*/