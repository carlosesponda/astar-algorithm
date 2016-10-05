package aStar;

import java.util.ArrayList;




public class Node 
{
	/**
	 * Neighbors of this Node
	 */
	public ArrayList<Node> Neighbors;
	
	/**
	 * Distances to Neighbors
	 */
	public ArrayList<Integer> Distances;
	
	/**
	 * Sum of g cost and h cost
	 */
	int f;
	
	/**
	 * Cost to get to current node from starting node.
	 */
	int g;
	
	/**
	 * Estimated direct distance from end node to current node.
	 */
	int h;
	
	/**
	 * Node that path followed to get to current node.
	 */
	
	int X,Y;
	
	Node Parent;
	
	boolean Staircase;
	//Indices of Directions
	//0 is North
	//1 is East
	//2 is South
	//3 is West
	public void setParent(Node n){
		
		Parent = n;
	}
	private String Name;
	
	/**
	 * Set Name of Node when instantiated.
	 * @param Name
	 */
	public Node(String Name) {
		Neighbors = new ArrayList<Node>(6);
		Distances = new ArrayList<Integer>(6);
		this.Name = Name;
	}
   
	/**
	 * Get's Neighbor of this node indicated by direction.
	 * @param Dir - Direction of Node you want to get (0-3)
	 * @return Neighbor in direction Dir
	 */
	public Node GetNeighbor(int Dir){
		
		return Neighbors.get(Dir);
	}

	/** Get's list of Neighbors of this node
	 * @return Neighbors
	 */
	public ArrayList<Node> GetNeighbors(){
		return Neighbors;
	}
	
	/**
	 * Get's list of Distances to the Neighbors connected to this node
	 * @return Distances
	 */
	public ArrayList<Integer> GetDistances(){
		return Distances;
		
	}
		
	/**
	 * Get's Distance from this node to specified Neighbor
	 * @param Neighbor
	 * @return Distance to specified Neighbor
	 */
	public Integer DistanceToNeighbor(Node Neighbor){
		
		if(!Neighbors.contains(Neighbor))
			return null;
		int IndexOfNeighbor = Neighbors.indexOf(Neighbor);
		
		return Distances.get(IndexOfNeighbor);
		
	}
	public void resetNode(){
		f = g = h = 0;
		Parent = null;
		Neighbors = null;
		Distances = null;
		Neighbors = new ArrayList<Node>(6);
		Distances = new ArrayList<Integer>(6);
	}
	public void SetPosition(int Xpos, int Ypos){
		X=Xpos;
		Y=Ypos;
		
	}
	
	public String toString(){
		return Name;
	}

	public boolean equals(Node _node){
		
		return _node.Name.equals(this.Name);
		
	}
}