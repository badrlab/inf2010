package PolyNet;

public class PolyNetMain {
    public static void main(String[] args)
    {
        boolean isTest1Correct = test1();
        boolean isTest2Correct = test2();
        boolean isTest3Correct = test3();

        if (isTest1Correct && isTest2Correct && isTest3Correct) {
            System.out.println("PolyNet : Tous les tests passent!");
        }
    }

    private static void connect(PolyNetNode node1, PolyNetNode node2, int distance)
    {
        node1.addConnection(node2, distance);
        node2.addConnection(node1, distance);
    }

    private static boolean test1()
    {
        // Arrange
        PolyNetNode parisNode = new PolyNetNode();
        PolyNetNode brusselsNode = new PolyNetNode();
        PolyNetNode londonNode = new PolyNetNode();
        PolyNetNode berlinNode = new PolyNetNode();
        PolyNetNode milanNode = new PolyNetNode();
        PolyNetNode vienaNode = new PolyNetNode();
        PolyNetNode madridNode = new PolyNetNode();

        connect(parisNode, madridNode, 127);
        connect(parisNode, londonNode, 46);
        connect(parisNode, brusselsNode, 31);
        connect(parisNode, milanNode, 85);
        connect(parisNode, berlinNode, 105);
        connect(parisNode, vienaNode, 123);
        connect(madridNode, londonNode, 172);
        connect(londonNode, brusselsNode, 37);
        connect(brusselsNode, berlinNode, 77);
        connect(vienaNode, milanNode, 87);

        PolyNetNode[] nodes = {parisNode, brusselsNode, londonNode,
                               berlinNode, milanNode, vienaNode, madridNode};

        PolyNet network = new PolyNet(nodes);

        int expectedCableLength = 444;

        // Act
        int actualCableLength = network.computeTotalCableLength();

        // Asset
        boolean isCorrect = actualCableLength == expectedCableLength;
        if (!isCorrect) {
            System.out.println("ERREUR (PolyNet) - Test #1: La longueur de cable attendue est de "
                               + expectedCableLength + " mais la valeur obtenue est " + actualCableLength);
        }

        return isCorrect;
    }

    private static boolean test2()
    {
        // Arrange
        PolyNetNode montrealNode = new PolyNetNode();
        PolyNetNode newYorkNode = new PolyNetNode();
        PolyNetNode ottawaNode = new PolyNetNode();
        PolyNetNode quebecNode = new PolyNetNode();
        PolyNetNode sherbrookeNode = new PolyNetNode();
        PolyNetNode torontoNode = new PolyNetNode();
        PolyNetNode troisRivieresNode = new PolyNetNode();

        connect(montrealNode, newYorkNode, 8);
        connect(montrealNode, ottawaNode, 2);
        connect(montrealNode, quebecNode, 4);
        connect(montrealNode, sherbrookeNode, 2);
        connect(montrealNode, torontoNode, 6);
        connect(montrealNode, troisRivieresNode, 3);
        connect(newYorkNode, sherbrookeNode, 10);
        connect(newYorkNode, torontoNode, 9);
        connect(ottawaNode, torontoNode, 7);
        connect(quebecNode, sherbrookeNode, 6);
        connect(quebecNode, troisRivieresNode, 4);

        PolyNetNode[] nodes = {montrealNode, newYorkNode, ottawaNode,
                quebecNode, sherbrookeNode, torontoNode, troisRivieresNode};

        PolyNet network = new PolyNet(nodes);

        int expectedCableLength = 25;

        // Act
        int actualCableLength = network.computeTotalCableLength();

        // Asset
        boolean isCorrect = actualCableLength == expectedCableLength;
        if (!isCorrect) {
            System.out.println("ERREUR (PolyNet) - Test #2: La longueur de cable attendue est de "
                               + expectedCableLength + " mais la valeur obtenue est " + actualCableLength);
        }

        return isCorrect;
    }

    private static boolean test3()
    {
        // Arrange
        PolyNetNode montrealNode = new PolyNetNode();
        PolyNetNode newYorkNode = new PolyNetNode();
        PolyNetNode ottawaNode = new PolyNetNode();
        PolyNetNode quebecNode = new PolyNetNode();
        PolyNetNode sherbrookeNode = new PolyNetNode();
        PolyNetNode torontoNode = new PolyNetNode();
        PolyNetNode troisRivieresNode = new PolyNetNode();

        connect(montrealNode, ottawaNode, 15);
        connect(montrealNode, quebecNode, 10);
        connect(newYorkNode, ottawaNode, 6);
        connect(newYorkNode, torontoNode, 8);
        connect(ottawaNode, torontoNode, 3);
        connect(quebecNode, sherbrookeNode, 7);
        connect(quebecNode, troisRivieresNode, 2);
        connect(sherbrookeNode, troisRivieresNode, 5);

        PolyNetNode[] nodes = {montrealNode, newYorkNode, ottawaNode,
                quebecNode, sherbrookeNode, torontoNode, troisRivieresNode};

        PolyNet network = new PolyNet(nodes);

        int expectedCableLength = 41;

        // Act
        int actualCableLength = network.computeTotalCableLength();

        // Asset
        boolean isCorrect = actualCableLength == expectedCableLength;
        if (!isCorrect) {
            System.out.println("ERREUR (PolyNet) - Test #3: La longueur de cable attendue est de "
                               + expectedCableLength + " mais la valeur obtenue est " + actualCableLength);
        }

        return isCorrect;
    }
}
