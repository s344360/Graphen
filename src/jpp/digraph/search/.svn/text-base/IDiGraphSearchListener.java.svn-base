/*
 * 
 */
package jpp.digraph.search;

import java.util.List;

import jpp.digraph.graph.IEdge;
import jpp.digraph.graph.INode;

// TODO: Auto-generated Javadoc
/**
 * Interface fuer einen Listener.
 *
 * @param <N> Knotentyp
 * @param <E> Pfeiltyp
 * @see IDiGraphSearchEvent
 */
public interface IDiGraphSearchListener<N extends INode, E extends IEdge<N>> {

    /**
     * Callback-Methode wenn ein Knoten im Algorithmus expandiert wird.
     * 
     * @param node
     *            Knoten
     * @param way
     *            gelaufener Weg
     */
    public void onExpandNode(N node, List<E> way);

}
