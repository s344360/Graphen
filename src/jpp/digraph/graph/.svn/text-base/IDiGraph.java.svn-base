/*
 * 
 */
package jpp.digraph.graph;

import java.util.Collection;

import jpp.digraph.exceptions.EdgeNotExistsException;
import jpp.digraph.exceptions.InvalidEdgeException;
import jpp.digraph.exceptions.NodeNotExistsException;

// TODO: Auto-generated Javadoc
/**
 * Interface eines gerichteten Graphen.
 *
 * @param <N> Knotentyp des Graphen
 * @param <E> Pfeiltyp des Graphen
 */
public interface IDiGraph<N extends INode, E extends IEdge<N>> {

    /**
     * Gibt die Anzahl der Knoten im Graphen zurueck.
     * 
     * @return Knotenanzahl
     */
    public int getNodeCount();
    
    /**
     * Gibt die Anzahl der Pfeile im Graphen zurueck.
     * 
     * @return Pfeilanzahl
     */
    public int getEdgeCount();
    
    /**
     * Gibt die Knoten des Graphen zurueck.
     * 
     * @return Knoten
     */
    public Collection<N> getNodes();
    
    /** 
     *  
     * Gibt die Pfeile des Graphen zurueck.
     * 
     * @return Pfeile
     */
    public Collection<E> getEdges();
    
    /**
     * Setzt die Knoten des Graphen. Hierzu werden erst alle Knoten
     * und Pfeile aus dem Graphen geloescht, und dann die neuen 
     * Knoten aus der Collection gesetzt.
     * 
     * @param nodes Neue Knoten
     */
    public void setNodes(Collection<N> nodes);
    
    /**
     * Setzt die Pfeile des Graphen. Hierzu werden erst alle Pfeile
     * aus dem Graphen geloescht, und dann die neuen Pfeile aus der
     * Collection gesetzt.
     * 
     * @param edges Neue Pfeile
     * @throws InvalidEdgeException Ein Pfeil passt nicht zu den 
     * Knoten im Graphen
     */
    public void setEdges(Collection<E> edges) throws InvalidEdgeException;
    
    /**
     * Setzten der Knoten und Pfeile des Graphen. Hierzu werden erst alle
     * Knoten und Pfeile aus dem Graphen geloescht, und dann die neuen 
     * Knoten und Pfeile aus den Collections gesetzt.
     * 
     * @param nodes Neue Knoten
     * @param edges Neue Pfeile
     * @throws InvalidEdgeException Ein Pfeil passt nicht zu den 
     * Knoten im Graphen
     */
    public void setGraph(Collection<N> nodes, Collection<E> edges)
            throws InvalidEdgeException;
    
    /**
     * Loescht alle Knoten aus dem Graphen, und somit auch alle Pfeile.
     */
    public void removeNodes();
    
    /**
     * Loescht alle Pfeile aus dem Graphen.
     */
    public void removeEdges();
    
    /**
     * Loescht alle Knoten und Pfeile aus dem Graphen.
     */
    public void removeGraph();
    
    /**
     * Prueft ob ein Knoten im Graph enthalten ist.
     * 
     * @param node Knoten
     * @return Wahrheitswert
     */
    public boolean containsNode(N node);
    
    /**
     * Prueft ob eine Pfeil im Graphen enthalten ist. 
     * @param edge Pfeil
     * @return Wahrheitswert
     */
    public boolean containsEdge(E edge);
    
    /**
     * Prueft ob zwischen zwei Knoten ein Pfeil im Graph existiert.
     * 
     * @param source Startknoten
     * @param target Zielknoten
     * @return Wahrheitswert
     */
    public boolean containsEdge(N source, N target);
    
    /**
     * Fuegt einen Knoten zum Graphen hinzu.
     * 
     * @param node Knoten
     */
    public void addNode(N node);
    
    /**
     * Fuegt einen Pfeil zum Graphen hinzu.
     * 
     * @param edge Pfeil
     * @throws InvalidEdgeException Pfeil passt nicht zu den Knoten im Graphen
     */
    public void addEdge(E edge) throws InvalidEdgeException;
    
    /**
     * Loescht einen Knoten und die mit ihm verbundenen Pfeile aus dem Graphen.
     * 
     * @param node Knoten
     * @throws NodeNotExistsException Knoten existiert nicht im Graphen
     */
    public void removeNode(N node) throws NodeNotExistsException;
    
    /**
     * Loescht einen Pfeil aus dem Graphen.
     * 
     * @param edge Pfeil
     * @throws InvalidEdgeException Pfeil passt nicht zu den Knoten im Graphen
     * @throws EdgeNotExistsException Pfeil existiert nicht im Graphen
     */
    public void removeEdge(E edge) throws
            InvalidEdgeException, EdgeNotExistsException;
    
    /**
     * Gibt alle Vorgaengerknoten eines Knotens zurueck.
     * 
     * @param node Knoten
     * @return Vorgaengerknoten
     * @throws NodeNotExistsException Knoten existiert nicht im Graphen
     */
    public Collection<N> getPredecessors(N node) throws NodeNotExistsException;
    
    /**
     * Gibt alle Pfeile von Vorgaengerknoten zum Knoten zurueck.
     * 
     * @param node Knoten
     * @return Pfeile
     * @throws NodeNotExistsException Knoten existiert nicht im Graphen
     */
    public Collection<E> getPredecessorEdges(N node)
            throws NodeNotExistsException;
    
    /**
     * Gibt alle Nachfolgerknoten eines Knoten zurueck.
     * 
     * @param node Knoten
     * @return Nachfolgerknoten
     * @throws NodeNotExistsException Knoten existiert nicht im Graphen
     */
    public Collection<N> getSuccessors(N node) throws NodeNotExistsException;
    
    /**
     * Gibt alle Pfeile vom Knoten zu Nachfolgerknoten zurueck.
     * 
     * @param node Knoten
     * @return Pfeile
     * @throws NodeNotExistsException Knoten existiert nicht im Graphen
     */
    public Collection<E> getSuccessorEdges(N node)
            throws NodeNotExistsException;
    
    /**
     * Gibt alle Pfeile von einem Knoten zum Naechsten zurueck.
     * 
     * @param source Startknoten
     * @param target Zielknoten
     * @return Pfeile
     * @throws NodeNotExistsException Ein Knoten existiert nicht im Graphen
     */
    public Collection<E> getEdgesBetween(N source, N target)
            throws NodeNotExistsException;
    
}