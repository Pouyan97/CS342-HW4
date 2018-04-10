import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
*
*	Pouyan Pourmirjafari
*	spourm2
*
*	ExamTester Class. 
*	Responsible for testing the classes. 
*
*	The print statements are used to explain each step 
*
*/


public class ExamTester {

	public static void main(String[] args) {
		int i, j;
				String nl = "_________________________________________________________";
		System.out.println(nl+"\n\n\t\tNAME: Pouyan Pourmirjafari\n"+
					nl+"\n\t\tNETID: SPOURM2 \n"+ nl +
					"\n\t\tHomework 4: \n" + nl);

		
		System.out.println("\nFirst you input a file name for the exam\n"
				+ "\n"
				+ "After the exam is printed out you can answer by inputing \n"
				+ "the text corresponding to your answer for MC questions \n"
				+ "and you can answer SAQuestions by typing your answer.\n\n"
				+ "Also for the Student Answer Sheet I need you to input a name.\n"
				+ "\n\t\t\tTHANK YOU &"
				+ "\n\t\t\tGOOD LUCK!!\n");
		
		
		try 
		{
			Exam E = new Exam("EMPTY EXAM");
			Scanner scanner;
			scanner = ScannerFactory.getKeyboardScanner();
			
			System.out.println("Enter the Exam file name:");
			
			File file = new File(scanner.nextLine());
			Scanner fileReader = new Scanner(file);
			E = new Exam(fileReader);
			E.print();

			System.out.println(nl);
			System.out.println("_____________________EXAM RE-ORDERED_____________________");
			System.out.println("___________________EXAM Copied to File___________________");
			System.out.println(nl);

			E.reorderQuestions();
			E.print();


			File newFile = new File("Copy.txt");
			PrintWriter pw = new PrintWriter(newFile);
			E.save(pw);
			pw.close();

			System.out.println(nl);
			System.out.println("_____________________GETTING ANSWERS_____________________");
			System.out.println("_________________Answers Copying to File_________________");
			System.out.println(nl);		
			E.getAnswerFromStudent(-1);
			File newFile2 = new File("Answer.txt");
			PrintWriter pw2 = new PrintWriter(newFile2);
			
			System.out.println(nl);	
			System.out.println("\t___________________DONE___________________");
			System.out.println(nl);	
			
			
			System.out.println("\n\nEnter the Student name for the Answer file:");
			String studentName = scanner.nextLine();
			pw2.println(studentName+"\n");
			E.saveStudentAnswers(pw2);
			pw2.close();

			E = null;
			newFile = new File("Copy.txt");
			fileReader = new Scanner(newFile);
			E = new Exam(fileReader);
			//fileReader.close(); 
			newFile2 = new File("Answer.txt");
			Scanner fileReader2 = new Scanner(newFile2);
			E.restoreStudentAnswers(fileReader2);
			System.out.println(nl);
			System.out.println("______________________Loading Exam_______________________");
			System.out.println("_____________________Loading ANSWERS_____________________");
			System.out.println(nl);		
			//GRADING
			E.getValue();
			E.reportQuestionValue();
			scanner.close();
		}
		catch (FileNotFoundException e) 
		{
			System.err.println("PLEASE TRY AGAIN, WRONG FILE NAME:\n\n"
					+ "The program is Restarting.");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			main(args);
			
		}
		/////////////////////////////////////////////
		/////////making MCSAQuestions and MCSAAnswers////////
		/////////////////////////////////////////////
/*		MCSAQuestion q1 = new MCSAQuestion("What is your name?",5.0);
		MCSAAnswer a = new MCSAAnswer("ali", 0);
		MCSAAnswer b = new MCSAAnswer("areian", 0);
		MCSAAnswer c = new MCSAAnswer("pouyan", 1.0);
		MCSAAnswer d = new MCSAAnswer("azam", 0);
		MCSAAnswer e = new MCSAAnswer("sam", 0);		
		q1.setRightAnswer(c);
		//total = 5

		q1.addAnswer(a);
		q1.addAnswer(b);
		q1.addAnswer(c);
		q1.addAnswer(d);
		q1.addAnswer(e);
		
		MCSAQuestion q2 = new MCSAQuestion("What is your lastname?", 5);
		//////////////////////
		//HARD CODED ANSWERS//
		/////////////////////
		MCSAAnswer a2 = new MCSAAnswer("asghar",0);
		MCSAAnswer b2 = new MCSAAnswer("akbar",1.0);
		MCSAAnswer c2 = new MCSAAnswer("gholam",0);
		MCSAAnswer d2 = new MCSAAnswer("shabnam",0);
		MCSAAnswer e2 = new MCSAAnswer("ya khoda",0);
				q2.setRightAnswer(b2);
		//total = 5
		q2.addAnswer(a2);
		q2.addAnswer(b2);
		q2.addAnswer(c2);
		q2.addAnswer(d2);
		q2.addAnswer(e2);
		 
		for(i = 0; i<5; i++){
			MCSAAnswer a2 = (MCSAAnswer) q2.getNewAnswer();
			q2.addAnswer(a2);
		}
		
		
		SAQuestion q3 = new SAQuestion("What is LeBron James height? (format eg: 6'8)", 10.0);
		SAAnswer a3 = new SAAnswer("6'8");
		q3.setRightAnswer(a3);
		//total = 10


		SAQuestion q4 = new SAQuestion("Who is your Instructor?",10.0);

		SAAnswer a4= (SAAnswer)q4.getNewAnswer();
		q4.setRightAnswer(a4);

		/////////////////////////////////////////////
		///////adding the MCSAQuestions to exam//////////
		/////////////////////////////////////////////
		E.addQuestion(q1);
		E.addQuestion(q2);
		E.addQuestion(q3);
		E.addQuestion(q4);
		
		////////////////////////////////////////////////
		//Print the exam
		/////////////////////////////////////////////
		System.out.println("\t\t<<EXAM STARTED>>\n"+nl);
		E.print();
		
		System.out.println(nl);
		////////////////////////////////////////////
		//select MCSAAnswers
		/////////////////////////////////////////////
		E.getAnswerFromStudent(-1);
		/////////////////////////////////////////////
		//dividing and grading the exam..
		/////////////////////////////////////////////
		System.out.println(nl);
		E.getValue();
		E.reportQuestionValue();

		//////////////////////////////////////////////////////
		//Changing up some MCSAAnswers to test the get value// 
		//////////////////////////////////////////////////////
		System.out.println(nl+"\nNow Changing the Answer of the MCSAAnswers.\n"+nl);
		E.getAnswerFromStudent(2);
		/////////////////////////////////////////////
		//dividing and grading the exam..
		/////////////////////////////////////////////
		E.getValue();
		E.reportQuestionValue();


		/////////////////////////////////////////////
		////making new exams that are shuffled../////
		/////////////////////////////////////////////
		System.out.println("\n\t\t////////////////////");
		System.out.println("\t\tSHUFFLING EXAMS NOW.");
		System.out.println("\t\t///////////////////");


		////////////////////////////////////////////
		////// for loop making A different exams////
		/////////////////////////////////////////////
		for(i = 0; i<2;i++){
			System.out.println(nl + "\n" + nl + "\n" + nl);
			E.reorderQuestions();
			//Reordering a random question MCAnswers.
			E.reorderMCAnswers(i);				
			E.print();

			/////Choosing getting Answers//////
			E.getAnswerFromStudent(-1);
			///////////////////////////
			/////grading the exam..////
			///////////////////////////			
			System.out.println(nl);
			E.getValue();
			E.reportQuestionValue();
		}
 */
	}
		
}
