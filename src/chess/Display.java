package chess;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pieces.Piece;

public class Display {
	

	public static JPanel board;
	public static JPanel wdetails;
	public static JPanel bdetails;
	public static JPanel wcombopanel;
	public static JPanel bcombopanel;
	public static JPanel controlPanel,WhitePlayer,BlackPlayer,temp,displayTime,showPlayer,time;
	public static JSplitPane split;
	public static JLabel label,mov;
	public static JLabel CHNC; //// stattic already
	public static Container content;
	public static JComboBox<String> wcombo,bcombo;
	public static JScrollPane wscroll,bscroll;
	public static JSlider timeSlider;
	public static BufferedImage image;
	public static Button start,wselect,bselect,WNewPlayer,BNewPlayer;
	
	
	public Display(){
		
		board=new JPanel(new GridLayout(8,8));
		board.setMinimumSize(new Dimension(800,700));
		wdetails=new JPanel(new GridLayout(3,3));
		bdetails=new JPanel(new GridLayout(3,3));
		wcombopanel=new JPanel();
		bcombopanel=new JPanel();
		timeSlider = new JSlider();
		board=new JPanel(new GridLayout(8,8));
		wdetails=new JPanel(new GridLayout(3,3));
		bdetails=new JPanel(new GridLayout(3,3));
		bcombopanel=new JPanel();
		wcombopanel=new JPanel();
		
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		controlPanel=new JPanel();
		controlPanel.setLayout(new GridLayout(3,3));
		controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP,TitledBorder.CENTER, new Font("Lucida Calligraphy",Font.PLAIN,20), Color.ORANGE));
	
		
	}
	
	public void cellsInit(MouseListener main)
	{
		Cell cell;
		Piece P;
		Main.boardState=new Cell[8][8];
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{	

				P=Main.pieces.getAppropriatePiece(i,j);
				cell=new Cell(i,j,P);
				cell.addMouseListener(main);
				Display.board.add(cell);
				Main.boardState[i][j]=cell;
			}
	}
	
	public void setContent(JFrame main)
	{
		Display.content=main.getContentPane();
		Display.content.setBackground(Color.black);
		Display.content.setLayout(new BorderLayout());
	}
	
	public void setLeftLayout(){
		Display.temp=new JPanel(){
			private static final long serialVersionUID = 1L;
			     
			@Override
		    public void paintComponent(Graphics g) {
				  try {
			          Display.image = ImageIO.read(this.getClass().getResource("clash.jpg"));
			       } catch (IOException ex) {
			            System.out.println("not found");
			       }
			   
				g.drawImage(Display.image, 0, 0, null);
			}         
	    };

		Display.temp.setMinimumSize(new Dimension(800,700));
		Display.controlPanel.setMinimumSize(new Dimension(285,700));
		Display.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,Display.temp, Display.controlPanel);
		
	    Display.content.add(Display.split);

	}
	
	public void endDisplay(String winner){
		JOptionPane.showMessageDialog(Display.board,"Checkmate!!!\n"+winner+" wins");
		WhitePlayer.remove(wdetails);
		BlackPlayer.remove(bdetails);
		displayTime.remove(label);
		
		displayTime.add(start);
		showPlayer.remove(mov);
		showPlayer.remove(CHNC);
		showPlayer.revalidate();
		showPlayer.add(timeSlider);
		
		split.remove(board);
		split.add(temp);
		WNewPlayer.enable();
		BNewPlayer.enable();
		wselect.enable();
		bselect.enable();

	}
	
	public void disabledTime(){
		displayTime.disable();
	}
	public void changeTurn( String move){
		CHNC.setText(move);
		showPlayer.add(CHNC);
	}
	
	public void removePlayer(){
		showPlayer.remove(CHNC);
	}
	
	public void playerDisplayInit(){
		Display.showPlayer=new JPanel(new FlowLayout());  
		Display.showPlayer.add(Display.timeSlider);
		JLabel setTime=new JLabel("Set Timer(in mins):"); 
		Display.start=new Button("Start");
		Display.start.setBackground(Color.black);
		Display.start.setForeground(Color.white);
	    Display.start.addActionListener(new START());
		Display.start.setPreferredSize(new Dimension(120,40));
		setTime.setFont(new Font("Arial",Font.BOLD,16));
		Display.label = new JLabel("Time Starts now", JLabel.CENTER);
		Display.label.setFont(new Font("SERIF", Font.BOLD, 30));
	    Display.displayTime=new JPanel(new FlowLayout());
	    Display.time=new JPanel(new GridLayout(3,3));
	    Display.time.add(setTime);
	    Display.time.add(Display.showPlayer);
	    Display.displayTime.add(Display.start);
	    Display.time.add(Display.displayTime);
	    Display.controlPanel.add(Display.time);
		Display.board.setMinimumSize(new Dimension(800,700));
	
	}
    public void timeSliderInit(){
		timeSlider.setMinimum(1);
		timeSlider.setMaximum(15);
		timeSlider.setValue(1);
		timeSlider.setMajorTickSpacing(2);
		timeSlider.setPaintLabels(true);
		timeSlider.setPaintTicks(true);
		timeSlider.addChangeListener( new TimeChange());
		
	}


public void playerBoxInit (){
	
	WhitePlayer=new JPanel();
	WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.RED));
	WhitePlayer.setLayout(new BorderLayout());
	
	BlackPlayer=new JPanel();
	BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.BLUE));
    BlackPlayer.setLayout(new BorderLayout());
	
    JPanel whitestats=new JPanel(new GridLayout(3,3));
	JPanel blackstats=new JPanel(new GridLayout(3,3));
	wcombo=new JComboBox<String>(Main.WNames);
	bcombo=new JComboBox<String>(Main.BNames);
	wscroll=new JScrollPane(wcombo);
	bscroll=new JScrollPane(bcombo);
	wcombopanel.setLayout(new FlowLayout());
	bcombopanel.setLayout(new FlowLayout());
	wselect=new Button("Select");
	bselect=new Button("Select");
	wselect.addActionListener(new SelectHandler(0));
	bselect.addActionListener(new SelectHandler(1));
	WNewPlayer=new Button("New Player");
	BNewPlayer=new Button("New Player");
	WNewPlayer.addActionListener(new Handler(0));
	BNewPlayer.addActionListener(new Handler(1));
	wcombopanel.add(wscroll);
	wcombopanel.add(wselect);
	wcombopanel.add(WNewPlayer);
	bcombopanel.add(bscroll);
	bcombopanel.add(bselect);
	bcombopanel.add(BNewPlayer);
	WhitePlayer.add(wcombopanel,BorderLayout.NORTH);
	BlackPlayer.add(bcombopanel,BorderLayout.NORTH);
	whitestats.add(new JLabel("Name   :"));
	whitestats.add(new JLabel("Played :"));
	whitestats.add(new JLabel("Won    :"));
	blackstats.add(new JLabel("Name   :"));
	blackstats.add(new JLabel("Played :"));
	blackstats.add(new JLabel("Won    :"));
	WhitePlayer.add(whitestats,BorderLayout.WEST);
	BlackPlayer.add(blackstats,BorderLayout.WEST);
	controlPanel.add(WhitePlayer);
	controlPanel.add(BlackPlayer);

}

class TimeChange implements ChangeListener
	{
	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		Main.timeRemaining=timeSlider.getValue()*60;
	}
}

class SelectHandler implements ActionListener
{
	private int color;
	
	SelectHandler(int i)
	{
		color=i;
	}
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			Main.tempPlayer=null;
			String n=(color==0)?Main.wname:Main.bname;
			JComboBox<String> jc=(color==0)?wcombo:bcombo;
			JComboBox<String> ojc=(color==0)?bcombo:wcombo;
			ArrayList<Player> pl=(color==0)?Main.wplayer:Main.bplayer;
			//ArrayList<Player> otherPlayer=(color==0)?bplayer:wplayer;
			ArrayList<Player> opl=Player.fetch_players();
			if(opl.isEmpty())
				return;
			JPanel det=(color==0)?Display.wdetails:Display.bdetails;
			JPanel PL=(color==0)?Display.WhitePlayer:Display.BlackPlayer; 
			if(Main.selected==true)
				det.removeAll();
			n=(String)jc.getSelectedItem();
			Iterator<Player> it=pl.iterator();
			Iterator<Player> oit=opl.iterator();
			while(it.hasNext())
			{	
				Player p=it.next();
				if(p.name().equals(n))
					{Main.tempPlayer=p;
					break;}
			}
			while(oit.hasNext())
			{	
				Player p=oit.next();
				if(p.name().equals(n))
					{opl.remove(p);
					break;}
			}
			
			if(Main.tempPlayer==null)
				return;
			if(color==0)
				Main.White=Main.tempPlayer;
			else
				Main.Black=Main.tempPlayer;
			Main.bplayer=opl;
			ojc.removeAllItems();
			for (Player s:opl)
				ojc.addItem(s.name());
			det.add(new JLabel(" "+Main.tempPlayer.name()));
			det.add(new JLabel(" "+Main.tempPlayer.gamesplayed()));
			det.add(new JLabel(" "+Main.tempPlayer.gameswon()));
			
			PL.revalidate();
			PL.repaint();
			PL.add(det);
			Main.selected=true;
		}
		
	}
	


class Handler implements ActionListener{
	private int color;
	Handler(int i)
	{
		color=i;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String n=(color==0)?Main.wname:Main.bname;
		JPanel j=(color==0)?Display.WhitePlayer:Display.BlackPlayer;
		ArrayList<Player> N=Player.fetch_players();
		Iterator<Player> it=N.iterator();
		JPanel det=(color==0)?Display.wdetails:Display.bdetails;
		n=JOptionPane.showInputDialog(j,"Enter your name");
			
			if(n!=null)
			{
			
			while(it.hasNext())
			{
				if(it.next().name().equals(n))
				{JOptionPane.showMessageDialog(j,"Player exists");
				return;}
			}
	
				if(n.length()!=0)
				{Player tem= Player.createPlayer(n);
				tem.Update_Player();
				if(color==0)
					Main.White=tem;
				else
					Main.Black=tem;
				}
				else return;
			}
		else
			return;
		det.removeAll();
		det.add(new JLabel(" "+n));
		det.add(new JLabel(" 0"));
		det.add(new JLabel(" 0"));
		j.revalidate();
		j.repaint();
		j.add(det);
		Main.selected=true;
	}
	}

class START implements ActionListener
{

@SuppressWarnings("deprecation")
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
	if(Main.White==null||Main.Black==null)
		{JOptionPane.showMessageDialog(Display.controlPanel, "Fill in the details");
		return;}
	Main.White.updateGamesPlayed();
	Main.White.Update_Player();
	Main.Black.updateGamesPlayed();
	Main.Black.Update_Player();
	Display.WNewPlayer.disable();
	Display.BNewPlayer.disable();
	Display.wselect.disable();
	Display.bselect.disable();
	Display.split.remove(Display.temp);
	Display.split.add(Display.board);
	Display.showPlayer.remove(Display.timeSlider);
	Display.mov=new JLabel("Move:");
	Display.mov.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
	Display.mov.setForeground(Color.red);
	Display.showPlayer.add(Display.mov);
	Display.CHNC=new JLabel(Main.move);
	Display.CHNC.setFont(new Font("Comic Sans MS",Font.BOLD,20));
	Display.CHNC.setForeground(Color.blue);
	Display.showPlayer.add(Display.CHNC);
	Display.displayTime.remove(Display.start);
	Display.displayTime.add(Display.label);
	Main.timer=new Time(Display.label);
	Main.timer.start();
}
}


}
