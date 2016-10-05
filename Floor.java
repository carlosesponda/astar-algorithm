package aStar;

import java.util.ArrayList;
import java.util.Arrays;





public class Floor {

	

	
	/**
	 * List of Nodes Contained in Hall.
	 */
	private ArrayList <Node> Nodes;
	private ArrayList <Classroom> Rooms;
	private String Name;
	//private NodeLayout Layout = new NodeLayout();
	
	
	/**
	 * Creates Nodes and specifies Neighbors of each Node.
	 */
	public Floor(String _Name, ArrayList<Node> _Nodes, ArrayList<Classroom> _Rooms){
		Nodes = _Nodes;
		Rooms = _Rooms;
		Name = _Name;
		//Init();

	}

	/**
	 * @return List of this floor's nodes.
	 */
	public ArrayList<Node> GetNodes(){
		return Nodes;
		
	}

	
	/**
	 * @return List of this floor's rooms.
	 */
	public ArrayList<Classroom> GetRooms(){
		return Rooms;
		
	}
	
	public String toString(){
		return Name;
	}
	
	

	
	
	
}

