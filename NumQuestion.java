import java.io.PrintWriter;
import java.util.Scanner;

public class NumQuestion extends Question{
	public double tolerance;

	public NumQuestion() {
		super();
		tolerance = 0;
	}
	
	public NumQuestion(String text,double maxValue,double tolerance){
		super(text, maxValue);
		this.tolerance = tolerance;
	}
	public NumQuestion(Scanner sc){
		sc.skip("");
		maxValue = Double.parseDouble(sc.nextLine());
		text = sc.nextLine();
		rightAnswer = new NumAnswer(sc);
		tolerance = Double.parseDouble(sc.nextLine());
	}
	
	@Override
	public Answer getNewAnswer() {
		// TODO Auto-generated method stub
		NumAnswer ans = new NumAnswer(0);
		return ans;
	}

	@Override
	public void getAnswerFromStudent() {
		System.out.println("Please enter your answer for question"+text+ ": ");
		Scanner sc = ScannerFactory.getKeyboardScanner();
		double studentAns = sc.nextDouble();
		studentAnswer = new NumAnswer(studentAns);
		sc.nextLine();
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return maxValue * ((NumAnswer)studentAnswer).getCredit(rightAnswer, tolerance);
	}

	@Override
	public void save(PrintWriter pw) {
		// TODO Auto-generated method stub
		pw.println(this.getClass().getSimpleName());
		pw.println(maxValue);
		pw.println(text);
		
		rightAnswer.save(pw);
		pw.println(tolerance);
		
		pw.println();
	}
}
