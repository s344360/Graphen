/*
 * 
 */
package jpp.digraph.graph;

// TODO: Auto-generated Javadoc
/**
 * Interface fuer einen Pfeil zwischen zwei Knoten vom Typ N.
 *
 * @param <N> Typ der Knoten
 */
public interface IEdge<N extends INode> {
    
    /**
     * Gibt die Beschreibung eines Pfeils zurueck.
     * 
     * @return Beschreibung
     */
    public String getDescription();
    
    /**
     * Gibt den Startknoten zurueck.
     * 
     * @return Startknoten
     */
    public N getSource();
    
    /**
     * Gibt den Zielknoten zurueck.
     * 
     * @return Zielknoten
     */
    public N getTarget();
}