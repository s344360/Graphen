/*
 * 
 */
package jpp.simplegraph;        

// TODO: Auto-generated Javadoc
/**
 * Interface fuer einen einfachen gerichteten Graphen.
 * 
 * Grundlage des Graphen ist hierbei eine Adajazenzmatrix mit
 * folgenden Eigenschaften:
 *   - Sie muss quadratisch sein
 *   - In der Diagonalen steht immer der Wert 0 (Schlingenfrei),
 *     also matrix[X][X] == 0
 *   - Wenn ein Pfeil von X nach Y mit dem Wert Z existiert,
 *     dann gilt matrix[X][Y] == Z und Z > 0
 *   - Wenn kein Pfeil von X nach Y existiert,
 *     dann gilt matrix[X][Y] == -1
 */
public interface ISimpleGraph {
    
    /**
     * Gibt die Adjazenzmatrix (Kopie) des Graphen zurueck.
     * 
     * @return Adajazenzmatrix 
     */
    public double[][] getMatrix();
    
    /**
     * Setzt den Graphen anhand der Adjazenzmatrix (Kopie).
     *
     * Entspricht die Adjazenzmatrix nicht den oben genannten
     * Eigenschaften, so wird ein leerer Graph erzeugt.
     *
     * @param matrix Adjazenzmatrix
     */
    public void setMatrix(double[][] matrix);
    
    /**
     * Gibt die Anzahl der Knoten des Graphen zurueck.
     * 
     * @return Knotenanzahl
     */
    public int getNodeCount();
    
    /**
     * Gibt die Anzahl der Pfeile des Graphen zurueck.
     * 
     * @return Pfeilanzahl
     */
    public int getEdgeCount();
    
    /**
     * Gibt die Vorgaengerknoten eines Knoten in aufsteigender Reihenfolge zurueck.
     * 
     * Existiert der Knoten nicht im Graphen, so wird ein leeres
     * Array zurueck gegeben.
     *
     * @param node Knoten
     * @return Array mit Vorgaengerknoten
     */
    public int[] getPredecessors(int node);
    
    /**
     * Gibt die Nachfolgerknoten eines Knoten in aufsteigender Reihenfolge zurueck.
     * 
     * Existiert der Knoten nicht im Graphen, so wird ein leeres
     * Array zurueck gegeben.
     *
     * @param node Knoten
     * @return Array mit Nachfolgerknoten
     */
    public int[] getSuccessors(int node);
    
    /**
     * Fuegt einen neuen Pfeil ein. 
     * Existierende Pfeile werden ueberschrieben.
     * 
     * @param source Startknoten
     * @param target Zielknoten
     * @param cost Kosten
     * @return true wenn erfolgreich
     */
    public boolean addEdge(int source, int target, double cost);
    
    /**
     * Loescht einen Pfeil.
     * 
     * @param source Startknoten
     * @param target Zielknoten
     * @return true wenn erfolgreich
     */
    public boolean removeEdge(int source, int target);
    
    /**
     * Prueft ob ein Knoten im Graphen vorhanden.
     * 
     * @param node Knoten
     * @return Wahrheitswert
     */
    public boolean containsNode(int node);
    
    /**
     * Prueft ob ein Pfeil im Graphen.
     * 
     * @param source Startknoten
     * @param target Zielknoten
     * @return Wahrheitswert
     */
    public boolean containsEdge(int source, int target);
    
    /**
     * Gibt die Kosten eines Pfeils zurueck.
     * 
     * @param source Startknoten
     * @param target Zielknoten
     * @return Kosten (-1.0 wenn kein Pfeil existiert)
     */
    public double getEdgeCost(int source, int target);
}
