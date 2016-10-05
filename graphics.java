package aStar;


import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;




@SuppressWarnings("unused")
public class graphics implements Runnable, KeyListener, WindowListener, ImageObserver {
	public final String TITLE = "AStar";
	public final Dimension SIZE = new Dimension(1500, 801);
	final Classroom Initial = new Classroom("None",null);
	public Classroom End, Start;
	
	Floor CurrentFloor;
	public Frame frame;
	//public graphics panel;
	private boolean isRunning, isDone;
	List<Node> Path ;
	List<Node> PathToDraw ;
	List<ArrayList<Node>> Paths;
	Button DrawPath;
	Button Reset;
	Button SwitchFloor;
	private Image imgBuffer;
	private Image img;
	//private boolean returnPressed;
	private ArrayList<Classroom> Classrooms;
	
	int ImageX = 20;
	int ImageY = 85;
	public graphics() throws IOException{
		Path = new ArrayList<Node>();
		
		Paths = new ArrayList<ArrayList<Node>>();
		Paths.add(new ArrayList<Node>());
		Paths.add(new ArrayList<Node>());
		PathToDraw = Paths.get(0);
		DrawPath = new Button("Draw Path");
		Reset = new Button("Reset");
		SwitchFloor = new Button("Switch Floor");
		new Astar();
		Building Building = new Building();
		CurrentFloor = Astar.Building.KHall.Floor1;
		Classrooms = Building.KHall.Floor1.GetRooms();
		
		
		
		loadImage("K1xxBaseMap.jpg");
		End = Initial;
		Start = Initial;
		frame = new Frame();
		//panel = new graphics();
		//frame.add(panel);
		frame.addKeyListener(this);
		frame.addWindowListener(this);
		frame.setSize(SIZE);
		frame.setTitle(TITLE);
		isRunning = true;
		//returnPressed = false;
		isDone = false;
		frame.setVisible(true);
		frame.setLayout(null);
		
		frame.add(DrawPath);
		DrawPath.setBounds(800, 300, 100, 100);
		frame.add(Reset);
		Reset.setBounds(800, 400, 100, 100);
		frame.add(SwitchFloor);
		SwitchFloor.setBounds(800,500,100,100);
		
		CreateRoomButtons(Classrooms);
		
		DrawPath.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				int StartIndex = 0;
				
				if(Start!=Initial && End!=Initial)
				{
					//new Astar();
					Path = Astar.AStar(Start.NearNode, End.NearNode);
					//Path.add(0,Start.NearNode);
					
					if((Start.NearNode==End.NearNode))
					{
						Path.clear();
					
						Path.add(Start.ActualNode);
						Path.add(End.ActualNode);
					}
					else{
						Path.add(0, Start.NearNode);
						Path.add(0,Start.ActualNode);
						Path.add(End.NearNode);
						Path.add(End.ActualNode);
					}
						
						//System.out.println(Path);
					
					for(int I=0; I<Path.size();I++)
					{
						if(Path.get(I).toString().substring(0, 2).equals("K1"))
							Paths.get(0).add(Path.get(I));
						else
							Paths.get(1).add(Path.get(I));
					}
					
					//System.out.println(Paths);
					
						if(CurrentFloor == Astar.Building.KHall.Floor1)
							PathToDraw = Paths.get(0);
						if(CurrentFloor == Astar.Building.KHall.Floor2)
							PathToDraw = Paths.get(1);
					
					
				}
				//}
				
				
				
				try {
					new Astar();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		Reset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Start = End = null;
				Start = Initial.clone();
				End = Initial.clone();
				draw();
				Paths.get(0).clear();
				Paths.get(1).clear();
				Path.clear();
				//PathToDraw.clear();
			}
			
		});
		
		SwitchFloor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RemoveRoomButtons(Classrooms);
				if(CurrentFloor == Astar.Building.KHall.Floor1){
					CurrentFloor = Astar.Building.KHall.Floor2;
					Classrooms = Astar.Building.KHall.Floor2.GetRooms();
					loadImage("K2xxBaseMap.jpg");
					PathToDraw = Paths.get(1);
					ImageX = 100;
					ImageY = 120;
				}
				else
				{
					CurrentFloor =  Astar.Building.KHall.Floor1;
					Classrooms = Astar.Building.KHall.Floor1.GetRooms();
					loadImage("K1xxBaseMap.jpg");
					PathToDraw = Paths.get(0);
					ImageX = 20;
					ImageY = 85;
				}
				draw();
				
				CreateRoomButtons(Classrooms);
			}
			
		});
		
		
		imgBuffer = frame.createImage(SIZE.width, SIZE.height);
	}
	
	public void loadImage(String Path)
	{
		try
		{
			img = ImageIO.read(this.getClass().getResource(Path));
			
		}
		catch(Exception e) {e.printStackTrace(); }
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int Key; 
		Key = arg0.getKeyCode();
		if(Key == KeyEvent.VK_ENTER){
			
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning){
			draw();
			try{Thread.sleep(50);}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		isDone = false;
	}
	
	private void draw() {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) imgBuffer.getGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, SIZE.width, SIZE.height);
		g2d.drawImage(img, ImageX, ImageY, this);
		//img.paintIcon(frame, g2d, 100, 100);
		
		//g2d.drawImage((Image)img.getImage(), 100, 100, this);
		Font f = new Font("Ariel", Font.BOLD, 25);
		Font f2 = new Font("Ariel", Font.BOLD , 12);
		g2d.setFont(f);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Starting Classroom: ",800,200);

		g2d.setColor(Color.WHITE);
		g2d.fillRect(1200, 200, 200, 30);
		g2d.fillRect(1200, 230, 200, 30);

		g2d.setColor(Color.BLACK);
		g2d.drawString(Start.toString(), 1200, 200);
		g2d.drawString("Ending Classroom: ",800,230);
		g2d.drawString(End.toString(), 1200,230);
		
		g2d.setColor(Color.BLUE);
		Stroke stroke = new BasicStroke(2f);
		g2d.setStroke(stroke);
		
		//g2d.drawLine(Start.X, Start.Y, PathToDraw.get(0).X, PathToDraw.get(0).Y);
		for(int I=0;I<PathToDraw.size()-1;I++)
			g2d.drawLine(PathToDraw.get(I).X, PathToDraw.get(I).Y, PathToDraw.get(I+1).X, PathToDraw.get(I+1).Y);
		
			//System.out.println("Drawing");
		//g2d.drawLine(PathToDraw.get(PathToDraw.size()-1).X, PathToDraw.get(PathToDraw.size()-1).Y, End.X, End.Y);
		
		
		//Draw Nodes
		Color DarkRed = new Color (230,115,0);
		g2d.setColor(DarkRed);
		g2d.setFont(f2);
		for(int I=0;I<CurrentFloor.GetNodes().size();I++)
			g2d.drawString(CurrentFloor.GetNodes().get(I).toString(),CurrentFloor.GetNodes().get(I).X, CurrentFloor.GetNodes().get(I).Y-5);
		g2d.setColor(Color.RED);
		for(int I=0;I<CurrentFloor.GetNodes().size();I++)
			g2d.drawRect(CurrentFloor.GetNodes().get(I).X -3 ,CurrentFloor.GetNodes().get(I).Y -3, 6, 6);
		g2d = (Graphics2D) frame.getGraphics();
		g2d.drawImage(imgBuffer, 0,  0, SIZE.width, SIZE.height, 0, 0, SIZE.width, SIZE.height, null);
		g2d.dispose();
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		while(true){
			if(isDone){
				System.exit(0);
			}
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		frame.setVisible(false);
		isRunning = false;
		frame.dispose();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	public void CreateRoomButtons(ArrayList<Classroom> Rooms){
		for(final Classroom room : Rooms){
			
			frame.add(room.Press);
			room.Press.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					if(Start == Initial)
						Start = room;
					else 
						End = room;
				
					

					
				}
				
			});

		}
	}

	public void RemoveRoomButtons(ArrayList<Classroom> Rooms){
		for(final Classroom room : Rooms)
			frame.remove(room.Press);
	}
	
}
