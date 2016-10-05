package aStar;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;


public class NodeLayout {
//Node declaration and formatting
	

	ArrayList <Node> K1Nodes;
	ArrayList <Node> K2Nodes;
	
	ArrayList <Classroom> K1Rooms;
	ArrayList <Classroom> K2Rooms;
	
	Node CurrentNode;


	
	public NodeLayout() throws IOException{
		
		//System.out.println();
	    K1Nodes = InitK1Nodes();
		K1Rooms = InitK1Rooms(K1Nodes);
		K2Nodes = InitK2Nodes();
		K2Rooms = InitK2Rooms(K2Nodes);		
		

		InitStaircaseNodes();
	}
	
	

	//0 is North
	//1 is East
	//2 is South
	//3 is West
	//4 is Up
	//5 is Down
	
	
	@SuppressWarnings("resource")
	private ArrayList<Node> InitK1Nodes() throws NumberFormatException, IOException{

		ArrayList<Node> Nodes = new ArrayList<Node>();
		
		//Add Nodes
		File file = new File("Floor1Nodes.txt");
	    BufferedReader reader = null;
	    reader = new BufferedReader(new FileReader(file));
	    String text = "";
	    while (!(text = reader.readLine()).equals("-")) {
			CurrentNode = new Node(text);
			CurrentNode.X = (int) Double.parseDouble(reader.readLine());
			CurrentNode.Y = (int) Double.parseDouble(reader.readLine());
			if(CurrentNode!=null)
		   		Nodes.add(CurrentNode);
		   
	    }
	    reader.close();
	   
		//Hardcoding Positions
		Nodes.get(0).SetPosition(300, 590);
		Nodes.get(1).SetPosition(300, 490);
		Nodes.get(2).SetPosition(360, 490);
		Nodes.get(3).SetPosition(565, 590);
		Nodes.get(4).SetPosition(565, 410);
		Nodes.get(5).SetPosition(360, 300);
		Nodes.get(6).SetPosition(565, 280);
		Nodes.get(7).SetPosition(360, 140);
		Nodes.get(8).SetPosition(280, 140);
		Nodes.get(9).SetPosition(195, 140);
		Nodes.get(10).SetPosition(195, 300);
		Nodes.get(11).SetPosition(195, 490);

		//Set Staircases
		Nodes.get(9).Staircase = true;
		Nodes.get(7).Staircase = true;

		//Add Neighbors
		File file2 = new File("Floor1Neighbors.txt");
		BufferedReader reader2 = null;
	    //StringBuffer contents = new StringBuffer();
	    reader2 = new BufferedReader(new FileReader(file2));
	    text = "";
	    for(int I = 0; I<Nodes.size();I++)
	    {
	    	CurrentNode = GetWithName(reader2.readLine(),Nodes);
	    	CurrentNode.Neighbors = new ArrayList<Node>(Arrays.asList(GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes)));

	    }
	    
	    reader2.close();
		
		//Add Distances
	    
	    for(int I = 0; I<Nodes.size();I++)
	    {
	    	CurrentNode = Nodes.get(I);
	    	CurrentNode.Distances = new ArrayList<Integer>(6);
	    	for(int J = 0;J<6;J++)
	    		CurrentNode.Distances.add(DistanceBetween(CurrentNode,CurrentNode.GetNeighbor(J)));
	    }

		
		return Nodes;
	}
	@SuppressWarnings("resource")
	private ArrayList<Classroom> InitK1Rooms(ArrayList<Node> Nodes) throws NumberFormatException, IOException{
		
		ArrayList<Classroom> Rooms = new ArrayList<Classroom>();
		
		File file = new File("Floor1Rooms.txt");
	    BufferedReader reader = null;
	    reader = new BufferedReader(new FileReader(file));
	    String text = "";
	    while (!(text = reader.readLine()).equals("-")) {
			Rooms.add(new Classroom(text,GetWithName(reader.readLine(),K1Nodes)));
			Rooms.get(Rooms.size()-1).SetPosition(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
		   
	    }
	    
	    reader.close();
	    
		/*Rooms.add(new Classroom("K100",K1Nodes.get(0)));
		Rooms.add(new Classroom("K128",K1Nodes.get(0)));
		Rooms.add(new Classroom("K130",K1Nodes.get(11)));
		Rooms.add(new Classroom("K106",K1Nodes.get(2)));
		Rooms.add(new Classroom("K110",K1Nodes.get(5)));
		Rooms.add(new Classroom("K112",K1Nodes.get(5)));
		Rooms.add(new Classroom("K109",K1Nodes.get(5)));
		Rooms.add(new Classroom("K113",K1Nodes.get(7)));
		Rooms.add(new Classroom("K114",K1Nodes.get(7)));
		Rooms.add(new Classroom("K119",K1Nodes.get(9)));
		Rooms.add(new Classroom("K120",K1Nodes.get(9)));
		Rooms.add(new Classroom("K124",K1Nodes.get(10)));
		Rooms.add(new Classroom("K125",K1Nodes.get(10)));
		Rooms.add(new Classroom("K126",K1Nodes.get(11)));
		Rooms.add(new Classroom("K132",K1Nodes.get(4)));
		Rooms.add(new Classroom("K134",K1Nodes.get(6)));*/
		
		Rooms.get(0).SetPosition(318,532);
		Rooms.get(1).SetPosition(106,530);
		Rooms.get(2).SetPosition(221,515);
		Rooms.get(3).SetPosition(390,420);
		Rooms.get(4).SetPosition(390,335);
		Rooms.get(5).SetPosition(390,280);
		Rooms.get(6).SetPosition(294,334);
		Rooms.get(7).SetPosition(294,190);
		Rooms.get(8).SetPosition(385,182);
		Rooms.get(9).SetPosition(225,190);
		Rooms.get(10).SetPosition(126,199);
		Rooms.get(11).SetPosition(126,315);
		Rooms.get(12).SetPosition(225,340);
		Rooms.get(13).SetPosition(125,405);
		Rooms.get(14).SetPosition(467,448);
		Rooms.get(15).SetPosition(482,343);

		
		return Rooms;
	
	}
	@SuppressWarnings("resource")
	private ArrayList<Node> InitK2Nodes() throws NumberFormatException, IOException{
		
		ArrayList<Node> Nodes = new ArrayList<Node>();
		
		//Add Nodes
		File file = new File("Floor2Nodes.txt");
	    //StringBuffer contents = new StringBuffer();
	    BufferedReader reader = null;
	    reader = new BufferedReader(new FileReader(file));
	    String text = "";
	    while (!(text = reader.readLine()).equals("-")) {
			CurrentNode = new Node(text);
			CurrentNode.X = (int) Double.parseDouble(reader.readLine());
			CurrentNode.Y = (int) Double.parseDouble(reader.readLine());
			if(CurrentNode!=null)
		   		Nodes.add(CurrentNode);
		
	    }
	   
	   reader.close();

		//Hardcoding Positions
		Nodes.get(0).SetPosition(330, 500);
		Nodes.get(1).SetPosition(330, 442);
		Nodes.get(2).SetPosition(390, 440);
		Nodes.get(3).SetPosition(450, 442);
		Nodes.get(4).SetPosition(530, 442);
		Nodes.get(5).SetPosition(390, 325);
		Nodes.get(6).SetPosition(550, 352);
		Nodes.get(7).SetPosition(390, 200);
		Nodes.get(8).SetPosition(550, 200);
		Nodes.get(9).SetPosition(390, 175);
		Nodes.get(10).SetPosition(235, 175);
		Nodes.get(11).SetPosition(235, 315);
		Nodes.get(12).SetPosition(240, 442);
		Nodes.get(13).SetPosition(210, 442);
		Nodes.get(14).SetPosition(330, 575);
		
		//Set Staircases
		Nodes.get(9).Staircase = true;
		Nodes.get(10).Staircase = true;
		
		
		//Add Neighbors
		File file2 = new File("Floor2Neighbors.txt");
		BufferedReader reader2 = null;
	    //StringBuffer contents = new StringBuffer();
	    reader2 = new BufferedReader(new FileReader(file2));
	    text = "";
	    for(int I = 0; I<Nodes.size();I++)
	    {
	    	CurrentNode = GetWithName(reader2.readLine(),Nodes);
	    	CurrentNode.Neighbors = new ArrayList<Node>(Arrays.asList(GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes), GetWithName(reader2.readLine(),Nodes)));
	    	
	    	/*for(int J = 0; J<6;J++)
	    		System.out.println(reader.readLine());*/
	    }
	    
	    reader2.close();

	    
	    for(int I = 0; I<Nodes.size();I++)
	    {
	    	CurrentNode = Nodes.get(I);
	    	CurrentNode.Distances = new ArrayList<Integer>(6);
	    	for(int J = 0;J<6;J++)
	    		CurrentNode.Distances.add(DistanceBetween(CurrentNode,CurrentNode.GetNeighbor(J)));

	    }
		
		return Nodes;
		
	}
	private ArrayList<Classroom> InitK2Rooms(ArrayList<Node> Nodes) throws NumberFormatException, IOException{
		
		ArrayList<Classroom> Rooms = new ArrayList<Classroom>();
		
		File file = new File("Floor2Rooms.txt");
	    BufferedReader reader = null;
	    reader = new BufferedReader(new FileReader(file));
	    String text = "";
	    while (!(text = reader.readLine()).equals("-")) {
			Rooms.add(new Classroom(text,GetWithName(reader.readLine(),K2Nodes)));
			Rooms.get(Rooms.size()-1).SetPosition(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
		   
	    }
	    
	    reader.close();
		
		Rooms.get(0).SetPosition(238,500);
		Rooms.get(1).SetPosition(180,460);
		Rooms.get(2).SetPosition(170,370);
		Rooms.get(3).SetPosition(160,230);
		Rooms.get(4).SetPosition(258,215);
		Rooms.get(5).SetPosition(325,215);
		Rooms.get(6).SetPosition(420,230);
		Rooms.get(7).SetPosition(485,140);
		Rooms.get(8).SetPosition(480,300);
		Rooms.get(9).SetPosition(473,380);
		Rooms.get(10).SetPosition(412,380);
		Rooms.get(11).SetPosition(362,500);
		Rooms.get(12).SetPosition(325,355);
		Rooms.get(13).SetPosition(258,346);
		Rooms.get(14).SetPosition(412,295);
		Rooms.get(15).SetPosition(150,525);
		Rooms.get(16).SetPosition(430,500);
		
		return Rooms;
	
	}
	private void InitStaircaseNodes(){
		K1Nodes.get(9).Neighbors.set(4, K2Nodes.get(10));
		K1Nodes.get(9).Distances.set(4, 5);
		K2Nodes.get(10).Neighbors.set(5, K1Nodes.get(9));
		K2Nodes.get(10).Distances.set(5, 5);
		
		K1Nodes.get(7).Neighbors.set(4, K2Nodes.get(9));
		K1Nodes.get(7).Distances.set(4, 5);
		K2Nodes.get(9).Neighbors.set(5, K1Nodes.get(7));
		K2Nodes.get(9).Distances.set(5, 5);
		
	}
	private Node GetWithName(String Name, ArrayList<Node> _Nodes){

		for(Node node: _Nodes)
			if(Name.equals(node.toString()))
				return node;
		return null;
		
		
	}
	private int DistanceBetween(Node Node1, Node Node2){
		if(Node1 == null || Node2 == null)
			return 0;
		return (int) Math.sqrt(Math.pow(Node2.X-Node1.X, 2)+Math.pow(Node2.Y-Node1.Y, 2));
	}
}
