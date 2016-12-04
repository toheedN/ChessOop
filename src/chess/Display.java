package chess;

import pieces.Coordinates;
import pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Display {


    private static JPanel board;
    private static JPanel wdetails;
    private static JPanel bdetails;
    private static JPanel wcombopanel;
    private static JPanel bcombopanel;
    private static JPanel controlPanel;
    private static JPanel WhitePlayer;
    private static JPanel BlackPlayer;
    private static JPanel temp;
    private static JPanel displayTime;
    private static JPanel showPlayer;
    private static JPanel savebox;
    private static JPanel time;
    private static JSplitPane split;
    private static JLabel label;
    private static JLabel mov;
    private static JLabel CHNC; //// static already
    private static Container content;
    private static JComboBox<String> wcombo;
    private static JComboBox<String> bcombo;
    
    private static JScrollPane wscroll;
    private static JScrollPane bscroll;
    private static JSlider timeSlider;
    private static BufferedImage image;
    private static Button start;
    private static Button wselect;
    private static Button bselect;
    private static Button WNewPlayer;
    private static Button BNewPlayer;
    private static Button savegame;
    private static Button loadgame;
    private static Button deltgame;
    private static JComboBox<String> saveCombo;
    
    private static JScrollPane saveslot;
    private static SavedGame savedGames;
    

    public Display() {

        setBoard(new JPanel(new GridBagLayout()));
        getBoard().setMinimumSize(new Dimension(900, 700));
        setWdetails(new JPanel(new GridLayout(3, 3)));
        setBdetails(new JPanel(new GridLayout(3, 3)));
        setWcombopanel(new JPanel());
        setBcombopanel(new JPanel());
        setTimeSlider(new JSlider());
        setBoard(new JPanel(new GridLayout(8, 8)));
        setWdetails(new JPanel(new GridLayout(3, 3)));
        setBdetails(new JPanel(new GridLayout(3, 3)));
        setBcombopanel(new JPanel());
        setWcombopanel(new JPanel());

        getBoard().setBorder(BorderFactory.createLoweredBevelBorder());
        setControlPanel(new JPanel());
        getControlPanel().setLayout(new GridLayout(3, 3));
        getControlPanel().setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP, TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));


    }

    public static void setSaveBox(JPanel arg)
    {
    	Display.savebox = arg;
    }
    public static JPanel getSaveBox()
    {
    	return Display.savebox;
    }
    
    public static JPanel getBoard() {
        return board;
    }

    public static void setBoard(JPanel board) {
        Display.board = board;
    }

    public static JPanel getWdetails() {
        return wdetails;
    }

    public static void setWdetails(JPanel wdetails) {
        Display.wdetails = wdetails;
    }

    public static JPanel getBdetails() {
        return bdetails;
    }

    public static void setBdetails(JPanel bdetails) {
        Display.bdetails = bdetails;
    }

    public static JPanel getWcombopanel() {
        return wcombopanel;
    }

    public static void setWcombopanel(JPanel wcombopanel) {
        Display.wcombopanel = wcombopanel;
    }

    public static JPanel getBcombopanel() {
        return bcombopanel;
    }

    public static void setBcombopanel(JPanel bcombopanel) {
        Display.bcombopanel = bcombopanel;
    }

    public static JPanel getControlPanel() {
        return controlPanel;
    }

    public static void setControlPanel(JPanel controlPanel) {
        Display.controlPanel = controlPanel;
    }

    public static JPanel getWhitePlayer() {
        return WhitePlayer;
    }

    public static void setWhitePlayer(JPanel whitePlayer) {
        WhitePlayer = whitePlayer;
    }

    public static JPanel getBlackPlayer() {
        return BlackPlayer;
    }

    public static void setBlackPlayer(JPanel blackPlayer) {
        BlackPlayer = blackPlayer;
    }

    public static JPanel getTemp() {
        return temp;
    }

    public static void setTemp(JPanel temp) {
        Display.temp = temp;
    }

    public static JPanel getDisplayTime() {
        return displayTime;
    }

    public static void setDisplayTime(JPanel displayTime) {
        Display.displayTime = displayTime;
    }

    public static JPanel getShowPlayer() {
        return showPlayer;
    }

    public static void setShowPlayer(JPanel showPlayer) {
        Display.showPlayer = showPlayer;
    }

    public static JPanel getTime() {
        return time;
    }

    public static void setTime(JPanel time) {
        Display.time = time;
    }

    public static JSplitPane getSplit() {
        return split;
    }

    public static void setSplit(JSplitPane split) {
        Display.split = split;
    }

    public static JLabel getLabel() {
        return label;
    }

    public static void setLabel(JLabel label) {
        Display.label = label;
    }

    public static JLabel getMov() {
        return mov;
    }

    public static void setMov(JLabel mov) {
        Display.mov = mov;
    }

    public static JLabel getCHNC() {
        return CHNC;
    }

    public static void setCHNC(JLabel CHNC) {
        Display.CHNC = CHNC;
    }

    public static Container getContent() {
        return content;
    }

    public void setContent(JFrame main) {
        Display.setContent(main.getContentPane());
        Display.getContent().setBackground(Color.black);
        Display.getContent().setLayout(new BorderLayout());
    }

    public static void setContent(Container content) {
        Display.content = content;
    }

    public static JComboBox<String> getWcombo() {
        return wcombo;
    }

    public static void setWcombo(JComboBox<String> wcombo) {
        Display.wcombo = wcombo;
    }

    public static JComboBox<String> getBcombo() {
        return bcombo;
    }

    public static void setBcombo(JComboBox<String> bcombo) {
        Display.bcombo = bcombo;
    }

    public static JScrollPane getWscroll() {
        return wscroll;
    }

    public static void setWscroll(JScrollPane wscroll) {
        Display.wscroll = wscroll;
    }

    public static JScrollPane getBscroll() {
        return bscroll;
    }

    public static void setBscroll(JScrollPane bscroll) {
        Display.bscroll = bscroll;
    }

    public static JSlider getTimeSlider() {
        return timeSlider;
    }

    public static void setTimeSlider(JSlider timeSlider) {
        Display.timeSlider = timeSlider;
    }

    public static BufferedImage getImage() {
        return image;
    }

    public static void setImage(BufferedImage image) {
        Display.image = image;
    }

    public static Button getStart() {
        return start;
    }

    public static void setStart(Button start) {
        Display.start = start;
    }

    public static Button getWselect() {
        return wselect;
    }

    public static void setWselect(Button wselect) {
        Display.wselect = wselect;
    }

    public static Button getBselect() {
        return bselect;
    }

    public static void setBselect(Button bselect) {
        Display.bselect = bselect;
    }

    public static Button getWNewPlayer() {
        return WNewPlayer;
    }

    public static void setWNewPlayer(Button WNewPlayer) {
        Display.WNewPlayer = WNewPlayer;
    }

    public static Button getBNewPlayer() {
        return BNewPlayer;
    }

    public static void setBNewPlayer(Button BNewPlayer) {
        Display.BNewPlayer = BNewPlayer;
    }
    public static void setSaveGameButton(Button savegame){
    	Display.savegame = savegame;
    }
    public static Button getSaveGameButton(){
    	return Display.savegame;
    }
    
    public static void setLoadGameButton(Button loadgame){
    	Display.loadgame = loadgame;
    }
    public static Button getLoadGameButton(){
    	return Display.loadgame;
    }

    public static Button getDeltGameButton() {
		return deltgame;
	}

	public static void setDeltGameButton(Button deltgame) {
		Display.deltgame = deltgame;
	}
   public static SavedGame getSavedGames() {
        return savedGames;
    }

    public static void setSavedGames(SavedGame savedGames) {
        savedGames = savedGames;
    }

	public void cellsInit(MouseListener main) {
        Cell cell;
        Piece P;
        Main.setBoardState(new Cell[8][8]);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {

                P = Main.getPieces().create(i, j);
                cell = new Cell(i, j, P);
                cell.addMouseListener(main);
                Display.getBoard().add(cell);
                Main.getBoardState()[i][j] = cell;
            }
    }
	
	public void cellsInit(MouseListener main, Coordinates[][] coordinates) {
        Cell cell;
        Piece P;
        Main.setBoardState(new Cell[8][8]);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {

                P = Main.getPieces().create(i, j);
                cell = new Cell(coordinates[i][j].x, coordinates[i][j].y, P);
                cell.addMouseListener(main);
                Display.getBoard().add(cell);
                Main.getBoardState()[i][j] = cell;
            }
    }

    public void setLeftLayout() {
        Display.setTemp(new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                try {
                    Display.setImage(ImageIO.read(this.getClass().getResource("clash.jpg")));
                } catch (IOException ex) {
                    System.out.println("not found");
                }
                g.drawImage(Display.getImage(), 0, 0, null);
            }
        });

        Display.getTemp().setMinimumSize(new Dimension(800, 700));
        Display.getControlPanel().setMinimumSize(new Dimension(285, 700));
        Display.setSplit(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, Display.getTemp(), Display.getControlPanel()));

        Display.getContent().add(Display.getSplit());
        Display.getSplit().setEnabled(false);

    }

    public void endDisplay(String winner) {
        JOptionPane.showMessageDialog(Display.getBoard(), "Checkmate!!!\n" + winner + " wins");
        getWhitePlayer().remove(getWdetails());
        getBlackPlayer().remove(getBdetails());
        getDisplayTime().remove(getLabel());

        getDisplayTime().add(getStart());
        getShowPlayer().remove(getMov());
        getShowPlayer().remove(getCHNC());
        getShowPlayer().revalidate();
        getShowPlayer().add(getTimeSlider());

        getSplit().remove(getBoard());
        getSplit().add(getTemp());
        getWNewPlayer().enable();
        getBNewPlayer().enable();
        getWselect().enable();
        getBselect().enable();

    }

    public void disabledTime() {
        getDisplayTime().disable();
    }

    public void changeTurn(String move) {
        getCHNC().setText(move);
        getShowPlayer().add(getCHNC());
    }

    public void removePlayer() {
        getShowPlayer().remove(getCHNC());
    }

    public void playerDisplayInit() {
    	String[] saveSlots = {"1","2","3","4","5"};
        Display.setShowPlayer(new JPanel(new FlowLayout()));
        Display.getShowPlayer().add(Display.getTimeSlider());
        JLabel setTime = new JLabel("Set Timer (in mins):");
        Display.setStart(new Button("Start"));
        Display.getStart().setBackground(Color.black);
        Display.getStart().setForeground(Color.white);
        Display.getStart().addActionListener(new ActionCommandAdapter(new START()));
        Display.getStart().setPreferredSize(new Dimension(120, 40));
        setTime.setFont(new Font("Arial", Font.BOLD, 16));
        Display.setLabel(new JLabel("Time Starts now", JLabel.CENTER));
        Display.getLabel().setFont(new Font("SERIF", Font.BOLD, 30));
        Display.setDisplayTime(new JPanel(new FlowLayout()));
        Display.setTime(new JPanel(new GridLayout(3, 3)));
        Display.getTime().add(setTime);
        Display.getTime().add(Display.getShowPlayer());
        Display.getDisplayTime().add(Display.getStart());
        Display.getTime().add(Display.getDisplayTime());
        Display.getControlPanel().add(Display.getTime());
        
        Display.setSaveBox(new JPanel());
        //Display.getSaveBox().setLayout(new FlowLayout(4, 1, 1));
        Display.getSaveBox().setBorder(BorderFactory.createTitledBorder(null, "Save Game", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.MAGENTA));
        Display.setSaveCombo(new JComboBox<String> (saveSlots));
        Display.setSaveslot(new JScrollPane(Display.getSaveCombo()));
        Display.setSaveGameButton(new Button("Save Game"));
        Display.setLoadGameButton(new Button("Load Game"));
        Display.setDeltGameButton(new Button("Delete Game"));
        Display.getSaveGameButton().setPreferredSize(new Dimension(80, 25));
        Display.getLoadGameButton().setPreferredSize(new Dimension(80, 25));
        Display.getDeltGameButton().setPreferredSize(new Dimension(80, 25));
        
        Display.getSaveGameButton().addActionListener(new ActionCommandAdapter(new SaveActionListener()));
        Display.getLoadGameButton().addActionListener(new ActionCommandAdapter(new LoadActionCommand()));
        Display.getDeltGameButton().addActionListener(new ActionCommandAdapter(new DeltActionCommand()));
        
        Display.getSaveBox().add(Display.getSaveslot());
        Display.getSaveBox().add(Display.getSaveGameButton());
        Display.getSaveBox().add(Display.getLoadGameButton());
        Display.getSaveBox().add(Display.getDeltGameButton());
        Display.getControlPanel().add(Display.getSaveBox());
        Display.getBoard().setMinimumSize(new Dimension(800, 700));
        Display.getBoard().setMaximumSize(new Dimension(800, 700));

    }

    public void timeSliderInit() {
        getTimeSlider().setMinimum(1);
        getTimeSlider().setMaximum(15);
        getTimeSlider().setValue(1);
        getTimeSlider().setMajorTickSpacing(2);
        getTimeSlider().setPaintLabels(true);
        getTimeSlider().setPaintTicks(true);
        getTimeSlider().addChangeListener(new TimeChange());

    }


    public void playerBoxInit() {

        setWhitePlayer(new JPanel());
        getWhitePlayer().setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.RED));
        getWhitePlayer().setLayout(new BorderLayout());

        setBlackPlayer(new JPanel());
        getBlackPlayer().setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.BLUE));
        getBlackPlayer().setLayout(new BorderLayout());

        JPanel whitestats = new JPanel(new GridLayout(3, 3));
        JPanel blackstats = new JPanel(new GridLayout(3, 3));
        setWcombo(new JComboBox<String>(Main.getWNames()));
        setBcombo(new JComboBox<String>(Main.getBNames()));
        setWscroll(new JScrollPane(getWcombo()));
        setBscroll(new JScrollPane(getBcombo()));
        getWcombopanel().setLayout(new FlowLayout());
        getBcombopanel().setLayout(new FlowLayout());
        setWselect(new Button("Select"));
        setBselect(new Button("Select"));
        getWselect().addActionListener(new SelectHandler(0));
        getBselect().addActionListener(new SelectHandler(1));
        setWNewPlayer(new Button("New Player"));
        setBNewPlayer(new Button("New Player"));
        getWNewPlayer().addActionListener(new Handler(0));
        getBNewPlayer().addActionListener(new Handler(1));
        getWcombopanel().add(getWscroll());
        getWcombopanel().add(getWselect());
        getWcombopanel().add(getWNewPlayer());
        getBcombopanel().add(getBscroll());
        getBcombopanel().add(getBselect());
        getBcombopanel().add(getBNewPlayer());
        getWhitePlayer().add(getWcombopanel(), BorderLayout.NORTH);
        getBlackPlayer().add(getBcombopanel(), BorderLayout.NORTH);
        whitestats.add(new JLabel("Name   :"));
        whitestats.add(new JLabel("Played :"));
        whitestats.add(new JLabel("Won    :"));
        blackstats.add(new JLabel("Name   :"));
        blackstats.add(new JLabel("Played :"));
        blackstats.add(new JLabel("Won    :"));
        getWhitePlayer().add(whitestats, BorderLayout.WEST);
        getBlackPlayer().add(blackstats, BorderLayout.WEST);
        getControlPanel().add(getWhitePlayer());
        getControlPanel().add(getBlackPlayer());

    }

    public static JScrollPane getSaveslot() {
		return saveslot;
	}

	public static void setSaveslot(JScrollPane saveslot) {
		Display.saveslot = saveslot;
	}

	private static JComboBox<String> getSaveCombo() {
		return saveCombo;
	}

	private static void setSaveCombo(JComboBox<String> saveCombo) {
		Display.saveCombo = saveCombo;
	}

	class TimeChange implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {
            Main.setTimeRemaining(getTimeSlider().getValue() * 60);
        }
    }

    class SelectHandler implements ActionListener {
        private int color;

        SelectHandler(int i) {
            color = i;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            Main.setTempPlayer(null);
            String n = (color == 0) ? Main.getWname() : Main.getBname();
            JComboBox<String> jc = (color == 0) ? getWcombo() : getBcombo();
            JComboBox<String> ojc = (color == 0) ? getBcombo() : getWcombo();
            ArrayList<Player> pl = (color == 0) ? Main.getWplayer() : Main.getBplayer();
            //ArrayList<Player> otherPlayer=(color==0)?bplayer:wplayer;
            ArrayList<Player> opl = Player.fetch_players();
            if (opl.isEmpty())
                return;
            JPanel det = (color == 0) ? Display.getWdetails() : Display.getBdetails();
            JPanel PL = (color == 0) ? Display.getWhitePlayer() : Display.getBlackPlayer();
            if (Main.isSelected())
                det.removeAll();
            n = (String) jc.getSelectedItem();
            Iterator<Player> it = pl.iterator();
            Iterator<Player> oit = opl.iterator();
            while (it.hasNext()) {
                Player p = it.next();
                if (p.name().equals(n)) {
                    Main.setTempPlayer(p);
                    break;
                }
            }
            while (oit.hasNext()) {
                Player p = oit.next();
                if (p.name().equals(n)) {
                    opl.remove(p);
                    break;
                }
            }

            if (Main.getTempPlayer() == null)
                return;
            if (color == 0)
                Main.setWhite(Main.getTempPlayer());
            else
                Main.setBlack(Main.getTempPlayer());
            Main.setBplayer(opl);
            ojc.removeAllItems();
            for (Player s : opl)
                ojc.addItem(s.name());
            det.add(new JLabel(" " + Main.getTempPlayer().name()));
            det.add(new JLabel(" " + Main.getTempPlayer().gamesplayed()));
            det.add(new JLabel(" " + Main.getTempPlayer().gameswon()));

            PL.revalidate();
            PL.repaint();
            PL.add(det);
            Main.setSelected(true);
        }

    }


    class Handler implements ActionListener {
        private int color;

        Handler(int i) {
            color = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String n = (color == 0) ? Main.getWname() : Main.getBname();
            JPanel j = (color == 0) ? Display.getWhitePlayer() : Display.getBlackPlayer();
            ArrayList<Player> N = Player.fetch_players();
            Iterator<Player> it = N.iterator();
            JPanel det = (color == 0) ? Display.getWdetails() : Display.getBdetails();
            n = JOptionPane.showInputDialog(j, "Enter your name");

            if (n != null) {

                while (it.hasNext()) {
                    if (it.next().name().equals(n)) {
                        JOptionPane.showMessageDialog(j, "Player exists");
                        return;
                    }
                }

                if (n.length() != 0) {
                    Player tem = Player.createPlayer(n);
                    tem.Update_Player();
                    if (color == 0)
                        Main.setWhite(tem);
                    else
                        Main.setBlack(tem);
                } else return;
            } else
                return;
            det.removeAll();
            det.add(new JLabel(" " + n));
            det.add(new JLabel(" 0"));
            det.add(new JLabel(" 0"));
            j.revalidate();
            j.repaint();
            j.add(det);
            Main.setSelected(true);
        }
    }

    class START implements Command{

        @SuppressWarnings("deprecation")
        @Override
        public void execute() {
            // TODO Auto-generated method stub

            if (Main.getWhite().getName() == "null" || Main.getBlack().getName() == "null") {
                JOptionPane.showMessageDialog(Display.getControlPanel(), "Fill in the details");
                return;
            }
            Main.getWhite().updateGamesPlayed();
            Main.getWhite().Update_Player();
            Main.getBlack().updateGamesPlayed();
            Main.getBlack().Update_Player();
            Display.getWNewPlayer().disable();
            Display.getBNewPlayer().disable();
            Display.getWselect().disable();
            Display.getBselect().disable();
            Display.getSplit().remove(Display.getTemp());
            Display.getSplit().add(Display.getBoard());
            Display.getShowPlayer().remove(Display.getTimeSlider());

            Display.setMov(new JLabel("Move:"));
            Display.getMov().setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            Display.getMov().setForeground(Color.red);
            Display.getShowPlayer().add(Display.getMov());
            Display.setCHNC(new JLabel(Main.getMove()));
            Display.getCHNC().setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            Display.getCHNC().setForeground(Color.blue);
            Display.getShowPlayer().add(Display.getCHNC());
            Display.getDisplayTime().remove(Display.getStart());
            Display.getDisplayTime().add(Display.getLabel());
            Main.setTimer(new Time(Display.getLabel()));
            Main.getTimer().start();
        }
    }

    class SaveActionListener implements Command
    {
		@Override
		public void execute() {
			try {
				JComboBox<String> sv = Display.getSaveCombo();
				Main.saveFile("out" + sv.getSelectedItem() + ".txt");
				JOptionPane.showMessageDialog(Display.getControlPanel(), "Game Saved");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(Display.getControlPanel(), "Game not saved");
				e1.printStackTrace();
			}
		}
    }
    
    class LoadActionCommand implements Command
    {
		@Override
		public void execute() {
			Thread thread = new Thread("New Thread") {
				
			      public void run(){
			    	  try {
							JComboBox<String> sv = Display.getSaveCombo();
							Main.loadFileArray("out" + sv.getSelectedItem() + ".txt");
							JOptionPane.showMessageDialog(Display.getControlPanel(), "Game Loaded");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(Display.getControlPanel(), "No Game to Load");
							e1.printStackTrace();
						}
			      }
			   };

			   thread.start();
		}
    }
    
    class DeltActionCommand implements Command
    {
		@Override
		public void execute() {
			JOptionPane.showMessageDialog(Display.getControlPanel(), "No game till now");
		}
    }
}
