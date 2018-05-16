import java.util.ArrayList;
import java.util.Hashtable;

public class Monceau {
	ArrayList<Node> arbres;
	

	public Monceau() {
		arbres = new ArrayList<Node>();
	}

	public void fusion(Monceau autre) 
	{
		Monceau retenue = new Monceau();
		ArrayList<Node> finalTrees = new ArrayList<Node>();
		int order = 0;

		while(this.arbres.size() !=0 || autre.arbres.size() != 0 )
		{
			ArrayList<Node> nodesOfSameOrder = new ArrayList<Node>(); //will contain nodes of a same specifif order at each iteration
			int counter = 0; //will count the number of nodes of same order

			//System.out.println("Is there a node of order : " + order + " in this ?");
			Node nodeThis = popNodeOfOrder(this.arbres, order);
			//System.out.println("Is there a node of order : " + order + " in autre ?");
			Node nodeAutre = popNodeOfOrder(autre.arbres, order);
			//System.out.println("Is there a node of order : " + order + " in retenue ?");
			Node nodeRetenue = popNodeOfOrder(retenue.arbres, order);

			if (nodeThis != null)
				nodesOfSameOrder.add(nodeThis);
			if (nodeAutre != null)
				nodesOfSameOrder.add(nodeAutre);
			if (nodeRetenue!= null)
				nodesOfSameOrder.add(nodeRetenue);

			counter = nodesOfSameOrder.size();
			try {
				switch (counter)
				{
					case 0:
						break;

					case 1:
						finalTrees.add(nodesOfSameOrder.get(0));
						break;

					case 2:
						retenue.arbres.add(nodesOfSameOrder.get(0).fusion(nodesOfSameOrder.get(1)));
						break;
					
					case 3:
						retenue.arbres.add(nodesOfSameOrder.get(0).fusion(nodesOfSameOrder.get(1)));
						finalTrees.add(nodesOfSameOrder.get(2));
						break;

					default :
						break;
				}
			}catch(DifferentOrderTrees e){}

			order ++;
		}
		
		if (retenue.arbres.size() != 0)
		{
			finalTrees.add(retenue.arbres.get(0)); //it could remains one fusioned node in retenue
		}

		if (this.arbres.size() != 0) //normally it would never be the case ...
		{
			this.arbres.removeAll(this.arbres); 
		}

		for (Node node : finalTrees)
		{
			this.arbres.add(node);
		}

	}


	public void insert(int val) 
	{
        Node node = new Node(val);
        Monceau monceau = new Monceau();
        monceau.arbres.add(node);
        this.fusion(monceau);
	}

	public boolean delete(int val) 
	{
		boolean check = false;
		for(int i = 0; i < this.arbres.size(); i++) {
			Node tmp=this.arbres.get(i).findValue(val);
			if ( tmp != null ) {
				ArrayList<Node> array = tmp.delete();
				this.arbres.remove(i);
				Monceau monceau = new Monceau();
				monceau.arbres = array;
				this.fusion(monceau);
				check = true;
				this.delete(val);
				break;
			}
		}
		return check;
	}

	public void print() 
	{
		for (Node node : arbres) {
			System.out.println("\n\nordre : " + node.ordre);
			node.print();
		}
	}
	
	public Node popNodeOfOrder(ArrayList<Node> tree, int order)
	{
		for (Node node : tree)
		{
			if (node.ordre == order)
			{
				//System.out.println("Found a node of order : " + order + " !");
				tree.remove(node); //we don't need this node anymore
				//System.out.println("Node removed !");
				return node;
			}
		}
		return null; //there is 1 or 0 node of a specific order in a heap
	}
	


}
