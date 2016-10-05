/**
 * 
 */
package aStar;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.



public class Astar{
	static Building Building;
	public Astar() throws IOException{
	Building = new Building();  //--- IS THIS NECCESARY?
			
	}
	//static List<Node> nodes=Building.KHall.Floor2.GetNodes();
	
//	public static void main(String[] args) {
//			//Map draw = new Map();
//			//draw.applet = false;
//			//draw.run(draw);
//			System.out.println(AStar(nodes.get(5),nodes.get(11)));
//		}
		
	/**
	 * Returns shortest path from Start to End
	 * @param Start
	 * @param End
	 * @return Path - ArrayList of Nodes in shortest path
	 */
	public static List<Node> AStar(Node Start, Node End){
		
			List<Node> openList = new ArrayList<Node>(Arrays.asList(Start)); //List of nodes to analyze
			List<Node> closedList = new ArrayList<Node>(); //List of nodes which have been analyzed
			List<Node> Path = new ArrayList<Node>(); //List of nodes in final path

			Node Current;
			boolean NeighborIsBetter;
			Start.setParent(null);
			while(openList.size()>0) //Run loop until openList is fully analyzed
			{
				Current=openList.get(0);
				for(int I=0;I<openList.size();I++) //Find node with smallest F cost, set as Current
					if(openList.get(I).f<=Current.f)
						Current=openList.get(I);

				for (Node Neighbor : Current.Neighbors) //Traverse Current Node's Neighbors
				{
					
					
					if(Neighbor!=null) //Make sure Node exists
					{
						//System.out.println("Current: "+Current+" Neighbor: "+Neighbor+" End: "+End);
						if(Neighbor.equals(End)) //If Neighbor is our goal node, simply construct the path and we're done
						{
							
							return constructPath(Current);
						}
						if(closedList.contains(Neighbor)) //If Neighbor is in closedList, it has already been analyzed, and we can skip it
							continue;

						//Set g, h, f costs
						Neighbor.g = Current.g + Current.DistanceToNeighbor(Neighbor); //g cost of current plus distance to current neighbor being analyzed
						Neighbor.h = 0; //Change when implementing heuristics
						Neighbor.f = Neighbor.g + Neighbor.h;
						
						if(!openList.contains(Neighbor)){ //If Neighbor is not in openList, add to openList so it can be analyzed alone
							openList.add(Neighbor);
							NeighborIsBetter = true;
						}
						else if(Neighbor.f < Current.f) //Neighbor is closer to goal
							NeighborIsBetter = true;
						else
							NeighborIsBetter = false;
						
						if(NeighborIsBetter) //Store whether Neighbor is connected to Current Node so that data can be used when constructing path
						{
								Neighbor.Parent = (Current);
						}
						
					}	
				}
				
				//Current has been analyzed, so put it in the closedList
				openList.remove(Current);
				closedList.add(Current);
				
				
			}
			
			
			return Path;
		}
	
	/**
	 * Moves backwards from end node to start node by tracing through the nodes' parents.
	 * Called only in AStar function.
	 * @param node - Last Node analyzed (end node)
	 * @return Shortest Path
	 */
	private static List<Node> constructPath(Node node) {
		List<Node> Path1 = new ArrayList<Node>();
		
		while(!(node.Parent == null)) //Adds a node to the path, then adds it's parent, then adds it's parent... until the node being analyzed has no parent
		{
			Path1.add(0, node);
			node=node.Parent;
		}
	
		return Path1;
		
	}

	
}	

