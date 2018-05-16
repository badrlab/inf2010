import java.util.Random;
import java.util.Stack;

public class SortStackMain 
{
	static final int COUNT = 30;
	static final int MAX_VALUE = 1000;
	
	public static void main(String[] args) 
	{
		boolean sortIsGood = true;
		
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < COUNT; i++)
			stack.push(generator.nextInt(MAX_VALUE));
		
		stack = sortStack(stack);
		
		boolean countIsGood = size(stack) == COUNT;
			
		int tmp = stack.pop();
		
		while(!stack.isEmpty())
		{
			System.out.print(tmp +", ");
			if(tmp > stack.peek())
				sortIsGood = false;
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(!countIsGood)
			System.out.println("Erreur: il manque des elements dans la pile");
		else if(!sortIsGood)
			System.out.println("Erreur: le trie a echoue");
		else
			System.out.println("It's all good");
	}
    
	private static int size(Stack<Integer> stack) {
		int size=0;
		for(int s :stack) 
			size++;
		return size;
    }
    
	static Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> tmpStack = new Stack<Integer>();
        while(!stack.isEmpty()) {
            int tmp = stack.pop();
            while(!tmpStack.isEmpty() && tmpStack.peek() < tmp) 
                stack.push(tmpStack.pop());
            tmpStack.push(tmp);
        }
        return tmpStack;
	}
}
