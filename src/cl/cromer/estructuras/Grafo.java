package cl.cromer.estructuras;

import java.util.*;

/**
 * Clase de grafos. Se utiliza dos algoritmos para dirigido y no dirigido.
 *
 * @author Chris Cromer
 */
public class Grafo {
	/**
	 * Esta clase contiene los arbolTipo de grafoNoDirigido.
	 *
	 * @author Chris Cromer
	 */
	final static public class Tipos {
		/**
		 * Tipo de grafo dirigido.
		 */
		static final public int DIRIGIDO = 0;

		/**
		 * Tipo de grafo no dirigido.
		 */
		static final public int NO_DIRIGIDO = 1;

		/**
		 * Tipo de grafo dirigido con peso.
		 */
		static final public int PESO = 2;

		/**
		 * El tipo que está elegido.
		 */
		final private int tipo;

		/**
		 * Inicilizar el tipo.
		 *
		 * @param tipo int: Tipo de grafoNoDirigido, {@value #DIRIGIDO} o {@value #NO_DIRIGIDO}
		 */
		public Tipos(int tipo) {
			switch (tipo) {
				case DIRIGIDO:
					this.tipo = DIRIGIDO;
					break;
				case NO_DIRIGIDO:
					this.tipo = NO_DIRIGIDO;
					break;
				case PESO:
					this.tipo = PESO;
					break;
				default:
					this.tipo = NO_DIRIGIDO;
			}
		}

		/**
		 * Devolver el tipo.
		 *
		 * @return int: El tipo de grafoNoDirigido.
		 */
		public int getTipo() {
			return tipo;
		}
	}

	/**
	 * Author: Keith Schwarz (htiek@cs.stanford.edu)
	 */
	@SuppressWarnings({"unused"})
	static final public class NoDirigido<T> implements Iterable<T> {
		/* A map from nodes in the graph to sets of outgoing edges.  Each
		 * set of edges is represented by a map from edges to doubles.
		 */
		private final Map<T, Set<T>> mGraph = new HashMap<>();

		/**
		 * Adds a new node to the graph.  If the node already exists, this
		 * function is a no-op.
		 *
		 * @param node The node to add.
		 * @return Whether or not the node was added.
		 */
		public boolean addNode(T node) {
        /* If the node already exists, don't do anything. */
			if (mGraph.containsKey(node))
				return false;

        /* Otherwise, add the node with an empty set of outgoing edges. */
			mGraph.put(node, new HashSet<>());
			return true;
		}

		/**
		 * Remove a node from the graph.
		 *
		 * @param node The node to remove.
		 * @return Whether or not the node was removed.
		 */
		public boolean removeNode(T node) {
        /* If the node already exists, don't do anything. */
			if (!mGraph.containsKey(node))
				return false;

        /* Otherwise, remove the node. */
			mGraph.remove(node);
			return true;
		}

		/**
		 * Given a node, returns whether that node exists in the graph.
		 *
		 * @param node The node in question.
		 * @return Whether that node eixsts in the graph.
		 */
		public boolean nodeExists(T node) {
			return mGraph.containsKey(node);
		}

		/**
		 * Given two nodes, adds an arc of that length between those nodes.  If
		 * either endpoint does not exist in the graph, throws a
		 * NoSuchElementException.
		 *
		 * @param one The first node.
		 * @param two The second node.
		 * @throws NoSuchElementException If either the start or destination nodes
		 *                                do not exist.
		 */
		public void addEdge(T one, T two) {
        /* Confirm both endpoints exist. */
			if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
				throw new NoSuchElementException("Both nodes must be in the graph.");

        /* Add the edge in both directions. */
			mGraph.get(one).add(two);
			mGraph.get(two).add(one);
		}

		/**
		 * Removes the edge between the indicated endpoints from the graph.  If the
		 * edge does not exist, this operation is a no-op.  If either endpoint does
		 * not exist, this throws a NoSuchElementException.
		 *
		 * @param one The start node.
		 * @param two The destination node.
		 * @throws NoSuchElementException If either node is not in the graph.
		 */
		public void removeEdge(T one, T two) {
        /* Confirm both endpoints exist. */
			if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
				throw new NoSuchElementException("Both nodes must be in the graph.");

        /* Remove the edges from both adjacency lists. */
			mGraph.get(one).remove(two);
			mGraph.get(two).remove(one);
		}

		/**
		 * Given two endpoints, returns whether an edge exists between them.  If
		 * either endpoint does not exist in the graph, throws a
		 * NoSuchElementException.
		 *
		 * @param one The first endpoint.
		 * @param two The second endpoint.
		 * @return Whether an edge exists between the endpoints.
		 * @throws NoSuchElementException If the endpoints are not nodes in the
		 *                                graph.
		 */
		public boolean edgeExists(T one, T two) {
        /* Confirm both endpoints exist. */
			if (!mGraph.containsKey(one) || !mGraph.containsKey(two))
				throw new NoSuchElementException("Both nodes must be in the graph.");

        /* Graph is symmetric, so we can just check either endpoint. */
			return mGraph.get(one).contains(two);
		}

		/**
		 * Given a node in the graph, returns an immutable view of the edges
		 * leaving that node.
		 *
		 * @param node The node whose edges should be queried.
		 * @return An immutable view of the edges leaving that node.
		 * @throws NoSuchElementException If the node does not exist.
		 */
		public Set<T> edgesFrom(T node) {
        /* Check that the node exists. */
			Set<T> arcs = mGraph.get(node);
			if (arcs == null)
				throw new NoSuchElementException("Source node does not exist.");

			return Collections.unmodifiableSet(arcs);
		}

		/**
		 * Returns whether a given node is contained in the graph.
		 *
		 * @param node The node to test for inclusion.
		 * @return Whether that node is contained in the graph.
		 */
		public boolean containsNode(T node) {
			return mGraph.containsKey(node);
		}

		/**
		 * Returns an iterator that can traverse the nodes in the graph.
		 *
		 * @return An iterator that traverses the nodes in the graph.
		 */
		public Iterator<T> iterator() {
			return mGraph.keySet().iterator();
		}

		/**
		 * Returns the number of nodes in the graph.
		 *
		 * @return The number of nodes in the graph.
		 */
		public int size() {
			return mGraph.size();
		}

		/**
		 * Returns whether the graph is empty.
		 *
		 * @return Whether the graph is empty.
		 */
		public boolean isEmpty() {
			return mGraph.isEmpty();
		}

		/**
		 * Returns a human-readable representation of the graph.
		 *
		 * @return A human-readable representation of the graph.
		 */
		public String toString() {
			return mGraph.toString();
		}
	}

	/*
	 * JBoss, Home of Professional Open Source Copyright 2006, Red Hat Middleware
	 * LLC, and individual contributors by the @authors tag. See the copyright.txt
	 * in the distribution for a full listing of individual contributors.
	 *
	 * This is free software; you can redistribute it and/or modify it under the
	 * terms of the GNU Lesser General Public License as published by the Free
	 * Software Foundation; either version 2.1 of the License, or (at your option)
	 * any later version.
	 *
	 * This software is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
	 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
	 * details.
	 *
	 * You should have received a copy of the GNU Lesser General Public License
	 * along with this software; if not, write to the Free Software Foundation,
	 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
	 * site: http://www.fsf.org.
	 */
	@SuppressWarnings({"unchecked", "unused"})
	static final public class Dirigido<T> {
		/** Color used to mark unvisited nodes */
		public static final int VISIT_COLOR_WHITE = 1;

		/** Color used to mark nodes as they are first visited in DFS order */
		public static final int VISIT_COLOR_GREY = 2;

		/** Color used to mark nodes after descendants are completely visited */
		public static final int VISIT_COLOR_BLACK = 3;

		/** Vector of graph verticies */
		private List<Vertex<T>> verticies;

		/** Vector of edges in the graph */
		private List<Edge<T>> edges;

		/** The vertex identified as the root of the graph */
		private Vertex<T> rootVertex;

		/**
		 * Construct a new graph without any vertices or edges
		 */
		public Dirigido() {
			verticies = new ArrayList<>();
			edges = new ArrayList<>();
		}

		/**
		 * Are there any verticies in the graph
		 *
		 * @return true if there are no verticies in the graph
		 */
		public boolean isEmpty() {
			return verticies.size() == 0;
		}

		/**
		 * Add a vertex to the graph
		 *
		 * @param v
		 *          the Vertex to add
		 * @return true if the vertex was added, false if it was already in the graph.
		 */
		public boolean addVertex(Vertex<T> v) {
			boolean added = false;
			if (!verticies.contains(v)) {
				added = verticies.add(v);
			}
			return added;
		}

		/**
		 * Get the vertex count.
		 *
		 * @return the number of verticies in the graph.
		 */
		public int size() {
			return verticies.size();
		}

		/**
		 * Get the root vertex
		 *
		 * @return the root vertex if one is set, null if no vertex has been set as
		 *         the root.
		 */
		public Vertex<T> getRootVertex() {
			return rootVertex;
		}

		/**
		 * Set a root vertex. If root does no exist in the graph it is added.
		 *
		 * @param root -
		 *          the vertex to set as the root and optionally add if it does not
		 *          exist in the graph.
		 */
		public void setRootVertex(Vertex<T> root) {
			this.rootVertex = root;
			if (!verticies.contains(root))
				this.addVertex(root);
		}

		/**
		 * Get the given Vertex.
		 *
		 * @param n
		 *          the index [0, size()-1] of the Vertex to access
		 * @return the nth Vertex
		 */
		public Vertex<T> getVertex(int n) {
			return verticies.get(n);
		}

		/**
		 * Get the graph verticies
		 *
		 * @return the graph verticies
		 */
		public List<Vertex<T>> getVerticies() {
			return this.verticies;
		}

		/**
		 * Insert a directed, weighted Edge into the graph.
		 *
		 * @param from -
		 *          the Edge starting vertex
		 * @param to -
		 *          the Edge ending vertex
		 * @param cost -
		 *          the Edge weight/cost
		 * @return true if the Edge was added, false if from already has this Edge
		 * @throws IllegalArgumentException
		 *           if from/to are not verticies in the graph
		 */
		public boolean addEdge(Vertex<T> from, Vertex<T> to, int cost) throws IllegalArgumentException {
			if (!verticies.contains(from))
				throw new IllegalArgumentException("from is not in graph");
			if (!verticies.contains(to))
				throw new IllegalArgumentException("to is not in graph");

			Edge<T> e = new Edge<>(from, to, cost);
			if (from.findEdge(to) != null)
				return false;
			else {
				from.addEdge(e);
				to.addEdge(e);
				edges.add(e);
				return true;
			}
		}

		/**
		 * Insert a bidirectional Edge in the graph
		 *
		 * @param from -
		 *          the Edge starting vertex
		 * @param to -
		 *          the Edge ending vertex
		 * @param cost -
		 *          the Edge weight/cost
		 * @return true if edges between both nodes were added, false otherwise
		 * @throws IllegalArgumentException
		 *           if from/to are not verticies in the graph
		 */
		public boolean insertBiEdge(Vertex<T> from, Vertex<T> to, int cost)
				throws IllegalArgumentException {
			return addEdge(from, to, cost) && addEdge(to, from, cost);
		}

		/**
		 * Get the graph edges
		 *
		 * @return the graph edges
		 */
		public List<Edge<T>> getEdges() {
			return this.edges;
		}

		/**
		 * Remove a vertex from the graph
		 *
		 * @param v
		 *          the Vertex to remove
		 * @return true if the Vertex was removed
		 */
		public boolean removeVertex(Vertex<T> v) {
			if (!verticies.contains(v))
				return false;

			verticies.remove(v);
			if (v == rootVertex)
				rootVertex = null;

			// Remove the edges associated with v
			for (int n = 0; n < v.getOutgoingEdgeCount(); n++) {
				Edge<T> e = v.getOutgoingEdge(n);
				v.remove(e);
				Vertex<T> to = e.getTo();
				to.remove(e);
				edges.remove(e);
			}
			for (int n = 0; n < v.getIncomingEdgeCount(); n++) {
				Edge<T> e = v.getIncomingEdge(n);
				v.remove(e);
				Vertex<T> predecessor = e.getFrom();
				predecessor.remove(e);
			}
			return true;
		}

		/**
		 * Remove an Edge from the graph
		 *
		 * @param from -
		 *          the Edge starting vertex
		 * @param to -
		 *          the Edge ending vertex
		 * @return true if the Edge exists, false otherwise
		 */
		public boolean removeEdge(Vertex<T> from, Vertex<T> to) {
			Edge<T> e = from.findEdge(to);
			if (e == null)
				return false;
			else {
				from.remove(e);
				to.remove(e);
				edges.remove(e);
				return true;
			}
		}

		/**
		 * Clear the mark state of all verticies in the graph by calling clearMark()
		 * on all verticies.
		 *
		 * @see Vertex#clearMark()
		 */
		public void clearMark() {
			verticies.forEach(Vertex::clearMark);
		}

		/**
		 * Clear the mark state of all edges in the graph by calling clearMark() on
		 * all edges.
		 */
		public void clearEdges() {
			edges.forEach(Edge::clearMark);
		}

		/**
		 * Perform a depth first serach using recursion.
		 *
		 * @param <E> Exception
		 *
		 * @param v -
		 *          the Vertex to start the search from
		 *
		 * @param visitor -
		 *          the vistor to inform prior to
		 * @throws E
		 *           if visitor.visit throws an exception
		 */
		public <E extends Exception> void depthFirstSearch(Vertex<T> v, VisitorEX<T, E> visitor) throws E {
			if (visitor != null)
				visitor.visit(this, v);
			v.visit();
			for (int i = 0; i < v.getOutgoingEdgeCount(); i++) {
				Edge<T> e = v.getOutgoingEdge(i);
				if (!e.getTo().visited()) {
					depthFirstSearch(e.getTo(), visitor);
				}
			}
		}

		/**
		 * Perform a breadth first search of this graph, starting at v.
		 *
		 * @param v -
		 *          the search starting point
		 * @param visitor -
		 *          the vistor whose vist method is called prior to visting a vertex.
		 */
		public void breadthFirstSearch(Vertex<T> v, final Visitor<T> visitor) {
			VisitorEX<T, RuntimeException> wrapper = (g, v1) -> {
				if (visitor != null)
					visitor.visit(g, v1);
			};
			this.breadthFirstSearch(v, wrapper);
		}

		/**
		 * Perform a breadth first search of this graph, starting at v. The vist may
		 * be cut short if visitor throws an exception during a vist callback.
		 *
		 * @param <E> -
		 *          exception
		 * @param v -
		 *          the search starting point
		 * @param visitor -
		 *          the vistor whose vist method is called prior to visting a vertex.
		 * @throws E
		 *           if vistor.visit throws an exception
		 */
		public <E extends Exception> void breadthFirstSearch(Vertex<T> v, VisitorEX<T, E> visitor)
				throws E {
			LinkedList<Vertex<T>> q = new LinkedList<>();

			q.add(v);
			if (visitor != null)
				visitor.visit(this, v);
			v.visit();
			while (!q.isEmpty()) {
				v = q.removeFirst();
				for (int i = 0; i < v.getOutgoingEdgeCount(); i++) {
					Edge<T> e = v.getOutgoingEdge(i);
					Vertex<T> to = e.getTo();
					if (!to.visited()) {
						q.add(to);
						if (visitor != null)
							visitor.visit(this, to);
						to.visit();
					}
				}
			}
		}

		/**
		 * Find the spanning tree using a DFS starting from v.
		 *
		 * @param v -
		 *          the vertex to start the search from
		 * @param visitor -
		 *          visitor invoked after each vertex is visited and an edge is added
		 *          to the tree.
		 */
		public void dfsSpanningTree(Vertex<T> v, DFSVisitor<T> visitor) {
			v.visit();
			if (visitor != null)
				visitor.visit(this, v);

			for (int i = 0; i < v.getOutgoingEdgeCount(); i++) {
				Edge<T> e = v.getOutgoingEdge(i);
				if (!e.getTo().visited()) {
					if (visitor != null)
						visitor.visit(this, v, e);
					e.mark();
					dfsSpanningTree(e.getTo(), visitor);
				}
			}
		}

		/**
		 * Search the verticies for one with name.
		 *
		 * @param name -
		 *          the vertex name
		 * @return the first vertex with a matching name, null if no matches are found
		 */
		public Vertex<T> findVertexByName(String name) {
			Vertex<T> match = null;
			for (Vertex<T> v : verticies) {
				if (name.equals(v.getName())) {
					match = v;
					break;
				}
			}
			return match;
		}

		/**
		 * Search the verticies for one with data.
		 *
		 * @param data -
		 *          the vertex data to match
		 * @param compare -
		 *          the comparator to perform the match
		 * @return the first vertex with a matching data, null if no matches are found
		 */
		public Vertex<T> findVertexByData(T data, Comparator<T> compare) {
			Vertex<T> match = null;
			for (Vertex<T> v : verticies) {
				if (compare.compare(data, v.getData()) == 0) {
					match = v;
					break;
				}
			}
			return match;
		}

		/**
		 * Search the graph for cycles. In order to detect cycles, we use a modified
		 * depth first search called a colored DFS. All nodes are initially marked
		 * white. When a node is encountered, it is marked grey, and when its
		 * descendants are completely visited, it is marked black. If a grey node is
		 * ever encountered, then there is a cycle.
		 *
		 * @return the edges that form cycles in the graph. The array will be empty if
		 *         there are no cycles.
		 */
		public Edge<T>[] findCycles() {
			ArrayList<Edge<T>> cycleEdges = new ArrayList<>();
			// Mark all verticies as white
			for (int n = 0; n < verticies.size(); n++) {
				Vertex<T> v = getVertex(n);
				v.setMarkState(VISIT_COLOR_WHITE);
			}
			for (int n = 0; n < verticies.size(); n++) {
				Vertex<T> v = getVertex(n);
				visit(v, cycleEdges);
			}

			Edge<T>[] cycles = new Edge[cycleEdges.size()];
			cycleEdges.toArray(cycles);
			return cycles;
		}

		private void visit(Vertex<T> v, ArrayList<Edge<T>> cycleEdges) {
			v.setMarkState(VISIT_COLOR_GREY);
			int count = v.getOutgoingEdgeCount();
			for (int n = 0; n < count; n++) {
				Edge<T> e = v.getOutgoingEdge(n);
				Vertex<T> u = e.getTo();
				if (u.getMarkState() == VISIT_COLOR_GREY) {
					// A cycle Edge<T>
					cycleEdges.add(e);
				} else if (u.getMarkState() == VISIT_COLOR_WHITE) {
					visit(u, cycleEdges);
				}
			}
			v.setMarkState(VISIT_COLOR_BLACK);
		}

		public String toString() {
			StringBuilder tmp = new StringBuilder("Dirigido[");
			verticies.forEach(tmp::append);
			tmp.append(']');
			return tmp.toString();
		}

	}

	/**
	 * A directed, weighted edge in a graph
	 *
	 * @author Scott.Stark@jboss.org
	 * @version $Revision$
	 * @param <T> Generic object
	 */
	@SuppressWarnings("unused")
	static final public class Edge<T> {
		private Vertex<T> from;

		private Vertex<T> to;

		private int cost;

		private boolean mark;

		/**
		 * Create a zero cost edge between from and to
		 *
		 * @param from
		 *          the starting vertex
		 * @param to
		 *          the ending vertex
		 */
		public Edge(Vertex<T> from, Vertex<T> to) {
			this(from, to, 0);
		}

		/**
		 * Create an edge between from and to with the given cost.
		 *
		 * @param from
		 *          the starting vertex
		 * @param to
		 *          the ending vertex
		 * @param cost
		 *          the cost of the edge
		 */
		public Edge(Vertex<T> from, Vertex<T> to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
			mark = false;
		}

		/**
		 * Get the ending vertex
		 *
		 * @return ending vertex
		 */
		public Vertex<T> getTo() {
			return to;
		}

		/**
		 * Get the starting vertex
		 *
		 * @return starting vertex
		 */
		public Vertex<T> getFrom() {
			return from;
		}

		/**
		 * Get the cost of the edge
		 *
		 * @return cost of the edge
		 */
		public int getCost() {
			return cost;
		}

		/**
		 * Set the mark flag of the edge
		 *
		 */
		public void mark() {
			mark = true;
		}

		/**
		 * Clear the edge mark flag
		 *
		 */
		public void clearMark() {
			mark = false;
		}

		/**
		 * Get the edge mark flag
		 *
		 * @return edge mark flag
		 */
		public boolean isMarked() {
			return mark;
		}

		/**
		 * String rep of edge
		 *
		 * @return string rep with from/to vertex names and cost
		 */
		public String toString() {
			return "Edge[from: " + from.getName() +
			       ",to: " +
			       to.getName() +
			       ", cost: " +
			       cost +
			       "]";
		}
	}

	/**
	 * A named graph vertex with optional data.
	 *
	 * @author Scott.Stark@jboss.org
	 * @version $Revision$
	 * @param <T> Generic object
	 */
	@SuppressWarnings({"unchecked", "unused"})
	static final public class Vertex<T> {
		private List<Edge<T>> incomingEdges;

		private List<Edge<T>> outgoingEdges;

		private String name;

		private boolean mark;

		private int markState;

		private T data;

		/**
		 * Calls this(null, null).
		 */
		public Vertex() {
			this(null, null);
		}

		/**
		 * Create a vertex with the given name and no data
		 *
		 * @param n -
		 *          return n
		 */
		public Vertex(String n) {
			this(n, null);
		}

		/**
		 * Create a Vertex with name n and given data
		 *
		 * @param n -
		 *          name of vertex
		 * @param data -
		 *          data associated with vertex
		 */
		public Vertex(String n, T data) {
			incomingEdges = new ArrayList<>();
			outgoingEdges = new ArrayList<>();
			name = n;
			mark = false;
			this.data = data;
		}

		/**
		 * @return the possibly null name of the vertex
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the possibly null data of the vertex
		 */
		public T getData() {
			return this.data;
		}

		/**
		 * @param data
		 *          The data to set.
		 */
		public void setData(T data) {
			this.data = data;
		}

		/**
		 * Add an edge to the vertex. If edge.from is this vertex, its an outgoing
		 * edge. If edge.to is this vertex, its an incoming edge. If neither from or
		 * to is this vertex, the edge is not added.
		 *
		 * @param e -
		 *          the edge to add
		 * @return true if the edge was added, false otherwise
		 */
		public boolean addEdge(Edge<T> e) {
			if (e.getFrom() == this)
				outgoingEdges.add(e);
			else if (e.getTo() == this)
				incomingEdges.add(e);
			else
				return false;
			return true;
		}

		/**
		 * Add an outgoing edge ending at to.
		 *
		 * @param to -
		 *          the destination vertex
		 * @param cost
		 *          the edge cost
		 */
		public void addOutgoingEdge(Vertex<T> to, int cost) {
			Edge<T> out = new Edge<>(this, to, cost);
			outgoingEdges.add(out);
		}

		/**
		 * Add an incoming edge starting at from
		 *
		 * @param from -
		 *          the starting vertex
		 * @param cost
		 *          the edge cost
		 */
		public void addIncomingEdge(Vertex<T> from, int cost) {
			Edge<T> out = new Edge<>(this, from, cost);
			incomingEdges.add(out);
		}

		/**
		 * Check the vertex for either an incoming or outgoing edge mathcing e.
		 *
		 * @param e
		 *          the edge to check
		 * @return true it has an edge
		 */
		public boolean hasEdge(Edge<T> e) {
			if (e.getFrom() == this) {
				return incomingEdges.contains(e);
			}
			else {
				return e.getTo() == this && outgoingEdges.contains(e);
			}
		}

		/**
		 * Remove an edge from this vertex
		 *
		 * @param e -
		 *          the edge to remove
		 * @return true if the edge was removed, false if the edge was not connected
		 *         to this vertex
		 */
		public boolean remove(Edge<T> e) {
			if (e.getFrom() == this)
				incomingEdges.remove(e);
			else if (e.getTo() == this)
				outgoingEdges.remove(e);
			else
				return false;
			return true;
		}

		/**
		 *
		 * @return the count of incoming edges
		 */
		public int getIncomingEdgeCount() {
			return incomingEdges.size();
		}

		/**
		 * Get the ith incoming edge
		 *
		 * @param i
		 *          the index into incoming edges
		 * @return ith incoming edge
		 */
		public Edge<T> getIncomingEdge(int i) {
			return incomingEdges.get(i);
		}

		/**
		 * Get the incoming edges
		 *
		 * @return incoming edge list
		 */
		public List getIncomingEdges() {
			return this.incomingEdges;
		}

		/**
		 *
		 * @return the count of incoming edges
		 */
		public int getOutgoingEdgeCount() {
			return outgoingEdges.size();
		}

		/**
		 * Get the ith outgoing edge
		 *
		 * @param i
		 *          the index into outgoing edges
		 * @return ith outgoing edge
		 */
		public Edge<T> getOutgoingEdge(int i) {
			return outgoingEdges.get(i);
		}

		/**
		 * Get the outgoing edges
		 *
		 * @return outgoing edge list
		 */
		public List getOutgoingEdges() {
			return this.outgoingEdges;
		}

		/**
		 * Search the outgoing edges looking for an edge whose's edge.to == dest.
		 *
		 * @param dest
		 *          the destination
		 * @return the outgoing edge going to dest if one exists, null otherwise.
		 */
		public Edge<T> findEdge(Vertex<T> dest) {
			for (Edge<T> e : outgoingEdges) {
				if (e.getTo() == dest)
					return e;
			}
			return null;
		}

		/**
		 * Search the outgoing edges for a match to e.
		 *
		 * @param e -
		 *          the edge to check
		 * @return e if its a member of the outgoing edges, null otherwise.
		 */
		public Edge<T> findEdge(Edge<T> e) {
			if (outgoingEdges.contains(e))
				return e;
			else
				return null;
		}

		/**
		 * What is the cost from this vertext to the dest vertex.
		 *
		 * @param dest -
		 *          the destination vertex.
		 * @return Return Integer.MAX_VALUE if we have no edge to dest, 0 if dest is
		 *         this vertex, the cost of the outgoing edge otherwise.
		 */
		public int cost(Vertex<T> dest) {
			if (dest == this)
				return 0;

			Edge<T> e = findEdge(dest);
			int cost = Integer.MAX_VALUE;
			if (e != null)
				cost = e.getCost();
			return cost;
		}

		/**
		 * Is there an outgoing edge ending at dest.
		 *
		 * @param dest -
		 *          the vertex to check
		 * @return true if there is an outgoing edge ending at vertex, false
		 *         otherwise.
		 */
		public boolean hasEdge(Vertex<T> dest) {
			return (findEdge(dest) != null);
		}

		/**
		 * Has this vertex been marked during a visit
		 *
		 * @return true is visit has been called
		 */
		public boolean visited() {
			return mark;
		}

		/**
		 * Set the vertex mark flag.
		 *
		 */
		public void mark() {
			mark = true;
		}

		/**
		 * Set the mark state to state.
		 *
		 * @param state
		 *          the state
		 */
		public void setMarkState(int state) {
			markState = state;
		}

		/**
		 * Get the mark state value.
		 *
		 * @return the mark state
		 */
		public int getMarkState() {
			return markState;
		}

		/**
		 * Visit the vertex and set the mark flag to true.
		 *
		 */
		public void visit() {
			mark();
		}

		/**
		 * Clear the visited mark flag.
		 *
		 */
		public void clearMark() {
			mark = false;
		}

		/**
		 * @return a string form of the vertex with in and out edges.
		 */
		public String toString() {
			StringBuilder tmp = new StringBuilder("Vertex(");
			tmp.append(name);
			tmp.append(", data=");
			tmp.append(data);
			tmp.append("), in:[");
			for (int i = 0; i < incomingEdges.size(); i++) {
				Edge<T> e = incomingEdges.get(i);
				if (i > 0)
					tmp.append(',');
				tmp.append('{');
				tmp.append(e.getFrom().name);
				tmp.append(',');
				tmp.append(e.getCost());
				tmp.append('}');
			}
			tmp.append("], out:[");
			for (int i = 0; i < outgoingEdges.size(); i++) {
				Edge<T> e = outgoingEdges.get(i);
				if (i > 0)
					tmp.append(',');
				tmp.append('{');
				tmp.append(e.getTo().name);
				tmp.append(',');
				tmp.append(e.getCost());
				tmp.append('}');
			}
			tmp.append(']');
			return tmp.toString();
		}
	}

	/**
	 * A graph visitor interface.
	 *
	 * @author Scott.Stark@jboss.org
	 * @version $Revision$
	 * @param <T> Generic object
	 */
	public interface Visitor<T> {
		/**
		 * Called by the graph traversal methods when a vertex is first visited.
		 *
		 * @param g -
		 *          the graph
		 * @param v -
		 *          the vertex being visited.
		 */
		void visit(Dirigido<T> g, Vertex<T> v);
	}

	/**
	 * A graph visitor interface that can throw an exception during a visit
	 * callback.
	 *
	 * @author Scott.Stark@jboss.org
	 * @version $Revision$
	 * @param <T> Generic object
	 * @param <E> Exception
	 */
	public interface VisitorEX<T, E extends Exception> {
		/**
		 * Called by the graph traversal methods when a vertex is first visited.
		 *
		 * @param g -
		 *          the graph
		 * @param v -
		 *          the vertex being visited.
		 * @throws E
		 *           exception for any error
		 */
		void visit(Dirigido<T> g, Vertex<T> v) throws E;
	}

	/**
	 * A spanning tree visitor callback interface
	 *
	 * @author Scott.Stark@jboss.org
	 * @version $Revision$
	 * @param <T> Generic object
	 */
	public interface DFSVisitor<T> {
		/**
		 * Called by the graph traversal methods when a vertex is first visited.
		 *
		 * @param g -
		 *          the graph
		 * @param v -
		 *          the vertex being visited.
		 */
		void visit(Dirigido<T> g, Vertex<T> v);

		/**
		 * Used dfsSpanningTree to notify the visitor of each outgoing edge to an
		 * unvisited vertex.
		 *
		 * @param g -
		 *          the graph
		 * @param v -
		 *          the vertex being visited
		 * @param e -
		 *          the outgoing edge from v
		 */
		void visit(Dirigido<T> g, Vertex<T> v, Edge<T> e);
	}
}