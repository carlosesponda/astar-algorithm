package aStar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Building {

	//List <Hall> Halls= new ArrayList<Hall>();
	
	Hall KHall;
	NodeLayout Layout;
	
	
	private Floor K1Floor;
	private Floor K2Floor;
	
	public Building() throws IOException
	{
		Layout = new NodeLayout();
		K1Floor = new Floor("K1",Layout.K1Nodes,Layout.K1Rooms);
		K2Floor = new Floor("K2",Layout.K2Nodes,Layout.K2Rooms);
		
		KHall = new Hall('K',K1Floor,K2Floor);
	}
	
	public void out(){
	}

}
