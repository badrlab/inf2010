import java.util.ArrayList;

public class main {

	public static void main(String[] args) {

		// On cree des noeuds
		Node a, b, c, d, e, f, g, h;
		a = new Node(3);
		b = new Node(4);
		c = new Node(5);
		d = new Node(7);
		e = new Node(8);
		f = new Node(9);
		g = new Node(12);
		h = new Node(13);

		// TESTS DE FUSION

		System.out.println("TEST DE L'INSERTION ET DE LA FUSION DE NODES\n");

		try {

			a = a.fusion(b); // 3-4
		
			// Decommenter pour tester l'emission d'exception
			// a = a.fusion(c); // 3-4-5 -> EXCEPTION (ordres differents)
			c = c.fusion(f); // 5-9
			c = c.fusion(a); // 3-4-5-9
			g = g.fusion(h); // 12-13
			d = d.fusion(e); // 7-8
			d = d.fusion(g); // 7-8-12-13
			c = c.fusion(d); // 3-4-5-9-7-8-12-13


		} catch (DifferentOrderTrees exception) {
			System.out.println(exception);
			return;
		}

		// TEST DE PRINT

		c.print();

		System.out.println();

		// TEST DE FINDVALUE
		System.out.println("TEST DE NODE.FINDVALUE()\n");

		// On cherche les 15 premiers entiers
		for (int i = 0; i < 15; i++) {

			// On cherche l'entier.
			Node node = c.findValue(i);

			if (node != null) // l'entier a été trouvé dans l'arbre
				System.out.print(node.getVal() + " ");
			else // l'entier n'est pas dans l'arbre
				System.out.print("-1 ");

		}

		System.out.println();

		// TEST DE DELETE
		System.out.println("\nTEST DE NODE.DELETE()\n");

		// On delete le noeud 5.
		ArrayList<Node> liste = c.findValue(5).delete();
		// On affiche les arbres restants.
		for (Node tree : liste) {
			tree.print();
			System.out.print("\n\n");
		}

		// TEST DE GET SORTED ELEMENTS
		System.out.println("TEST DU SORT\n");

		ArrayList<Integer> noeudsTries = h.getElementsSorted();
		for (int valeur : noeudsTries) {
			System.out.println(valeur + " ");
		}

		// TEST DU MONCEAU : INSERT
		System.out.println("\nTEST DE MONCEAU.INSERT(), FUSION() ET PRINT()\n");

		Monceau monceau = new Monceau();
		monceau.insert(9);
		monceau.insert(5);
		monceau.insert(17);
		monceau.insert(21);
		monceau.insert(99);
		monceau.insert(12);
		monceau.insert(77);
		monceau.insert(12);
		monceau.insert(23);
		monceau.insert(23);
		monceau.insert(24);
		monceau.insert(33);
		monceau.insert(53);

		// Affichage du monceau
		monceau.print();

		// // TEST DU MONCEAU : DELETE
		System.out.println("\nTEST DE MONCEAU.DELETE()\n");

		System.out.println("Suppression de 1 :" + monceau.delete(1));
		System.out.println("Suppression de 12 :" + monceau.delete(12));

		System.out.println("Resultat :");
		monceau.print();

	}
}
