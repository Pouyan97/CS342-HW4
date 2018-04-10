import java.io.PrintWriter;
import java.util.Scanner;

public class NumAnswer extends Answer{
	protected double ans;
	
	public NumAnswer(){
		super();
	}
	
	public NumAnswer(double ans) {
		this.ans = ans;
	}
	
	public NumAnswer(Scanner sc){
		ans = Double.parseDouble(sc.nextLine());
	}
	
	public void print() {
		System.out.println(ans);
	}

	public double getCredit(Answer rightAnswer) {
		return this.getCredit(rightAnswer, 0);
	}
	public double getCredit(Answer rightAnswer, double tolerance) {
		boolean right = false;
		NumAnswer rightAns = (NumAnswer)rightAnswer;
		
		right = (rightAns.ans - tolerance) <= this.ans;
		right = right && (rightAns.ans + tolerance) >= this.ans;
		
		if (right)
			return 1.0;
		else
			return 0;
	}

	public void save(PrintWriter pw) {
		// TODO Auto-generated method stub
		pw.println(ans);
	}

}
