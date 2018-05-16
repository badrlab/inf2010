import java.util.ArrayList;

public class Node {

	public int ordre;
	public Node parent;

	private int valeur;
	private ArrayList<Node> enfants;

	public Node(int valeur) {
		this.valeur = valeur;
		ordre = 0;
		this.parent = null;
		enfants = new ArrayList<Node>();
	}

	public Node(int valeur, Node parent) {
		ordre = 0;
		this.valeur = valeur;
		this.parent = parent;
		enfants = new ArrayList<Node>();
	}

	public int getVal() {
		return valeur;
	}

	public ArrayList<Node> getEnfants() {
		return enfants;
	}

	public void addEnfant(Node enfant) {
		enfants.add(enfant);
	}

	public boolean removeEnfant(Node enfant) {
		if (enfants.contains(enfant)) {
			enfants.remove(enfant);
			return true;
		}
		return false;
	}

	public void removeEnfants(ArrayList<Node> enfants) {
		this.enfants.removeAll(enfants);
	}

	public Node fusion(Node autre) throws DifferentOrderTrees {
		
        if (autre != null && ordre == autre.ordre ){
        	if (valeur > autre.valeur){
        		autre.parent = this;
        		ordre++;
        		enfants.add(autre);
        		return this;
        	}
        	else{	
            	parent = autre;
            	autre.ordre++;
            	autre.enfants.add(this);
        		return parent;
        	}
        }
        else throw new DifferentOrderTrees();
	}

	private void moveUp() {
		
		ArrayList<Node> enfants = this.enfants;
    	Node parent = this.parent;
    	int ordre= this.ordre;
    	
    	this.parent= parent.parent;
    	parent.parent=this;
    	
    	this.ordre = parent.ordre;
    	parent.ordre = ordre;
    	
    	this.enfants = parent.enfants;
    	this.enfants.add(parent);
    	this.enfants.remove(this);
    	parent.enfants = enfants;
    	if(this.parent != null) {
    		this.parent.enfants.add(this);
    		this.parent.enfants.remove(parent);
    	}
	}

	public ArrayList<Node> delete() {

		while(this.parent != null) this.moveUp();
		for(int i = 0; i < this.enfants.size(); i++) this.enfants.get(i).parent = null;
		return this.enfants;
	}

	public Node findValue(int valeur) {
		
		if (this.valeur == valeur)
			return this;
		else if ( this.valeur > valeur) {
			for ( int i = 0; i < this.enfants.size(); i++ ) {
				Node temp = this.enfants.get(i).findValue(valeur);
				if ( temp != null ) return temp;
			}
		}
		return null;
	}

	public ArrayList<Integer> getElementsSorted() {
		
    	ArrayList<Integer> valeurs = new ArrayList<Integer>();
    	valeurs.add(valeur);
    	ArrayList<Node> nodeList = getEnfants();
    	while(nodeList.size() > 0){
            Node smallestNode = nodeList.get(0);
            for(Node n : nodeList) if(n.getVal() > smallestNode.getVal()) smallestNode = n;
            valeurs.add(smallestNode.getVal());
            nodeList.remove(smallestNode);
            nodeList.addAll(smallestNode.getEnfants());
    	}
    	return valeurs;
    }

	public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + valeur);
        for (int i = 0; i < enfants.size() - 1; i++) {
            enfants.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (enfants.size() > 0) {
            enfants.get(enfants.size() - 1)
                    .print(prefix + (isTail ?"    " : "|   "), true);
        }
    }
}
