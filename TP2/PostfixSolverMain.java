import java.io.*;
import java.util.Stack;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
		Stack<Double> stack = new Stack<Double>();
		
		String s = "25 5 2 * + 15 3 / 5 - +";
		//L'expression est separee en tokens selon les espaces
		for(String token : s.split("\\s")) 
		{
			switch(token) {
			case "+":
				stack.push(stack.pop()+ stack.pop());
				break;
			case "-":
				stack.push(stack.pop()- stack.pop());
				break;
			case "*":
				stack.push(stack.pop()* stack.pop());
				break;
			case "/":
				Double i =stack.pop();
				stack.push(stack.pop()/ i);
				break;
			default:
				stack.push(Double.parseDouble(token));
			}
		}
     //stack.push((double) 35);
		System.out.println("25 + 5*2 + 15/3 - 5 = "+stack.peek());
		if(stack.peek() == 35)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: mauvais resultat");
     }
}
