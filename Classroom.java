package aStar;

import java.awt.Button;


public class Classroom{

	/**
	 * Node Nearest to this Classroom
	 */
	public Node NearNode;
	/**
	 * Node assigned to this Classroom
	 */
	public Node ActualNode;
	
	public String Name;
	
	public Button Press;
	
	public int X,Y;
	
	public Classroom clone(){
		return this;
	}
	
	public void setButton(String _Name){
		Press = new Button(_Name);
	}
	
	public Classroom(String _Name, Node Nearest){
		NearNode = Nearest;
		Name = _Name;
		setButton(Name);
		ActualNode = new Node(Name);
	}
	
	public void SetPosition(int Xpos, int Ypos)
	{
		X=Xpos;
		Y=Ypos;
		Press.setBounds(X,Y,50,30);
		ActualNode.SetPosition(X+25, Y+15);
		
		
	}
	
	public void setName(String N){
		Name = N;
	}
	public void setNear(Node N){
		NearNode = N;
	}
	
	public Node	GetNode(){
		return NearNode;
	}
	
	
	public String toString(){
		return Name;
		
	}
}
