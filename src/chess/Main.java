package chess;

import pieces.King;
import pieces.Pieces;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Ashish Kedia and Adarsh Mohata
 */


/**
 * This is the Main Class of our project.
 * All GUI Elements are declared, initialized and used in this class itself.
 * It is inherited from the JFrame Class of Java's Swing Library. 
 *
 */
public class Main extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;
    private static final int Height = 700;
    private static final int Width = 1110;
    //Variable Declaration
    private static Pieces pieces;
    private static Display display;
    private static Cell c;
    private static Cell previous;
    private static int chance = 0;
    private static Cell[][] boardState;
    private static ArrayList<Cell> destinationlist = new ArrayList<Cell>();
    private static Player White = Player.createPlayer("null");
    private static Player Black = Player.createPlayer("null");
    private static Time timer;
    private static Main Mainboard;
    private static boolean selected = false;
    private static boolean end = false;
    private static ArrayList<Player> wplayer;
    private static ArrayList<Player> bplayer;
    private static ArrayList<String> Wnames = new ArrayList<String>();
    private static ArrayList<String> Bnames = new ArrayList<String>();
    private static String wname = null;
    private static String bname = null;
    private static String winner = null;
    private static String move;
    private static Player tempPlayer;
    private static String[] WNames = {};
    private static String[] BNames = {};
    private static int timeRemaining = 60;


    //Constructor
    private Main() {
        setSize(getWidth(), getHeight());
        setTitle("Chess");
        setTimeRemaining(60);
        setMove("White");
        setWname(null);
        setBname(null);
        setWinner(null);
        setWnames(new ArrayList<String>());
        setBnames(new ArrayList<String>());
        ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
        this.setIconImage(img.getImage());

        //Time Slider Details
        getDisplay().timeSliderInit();


        //Fetching Details of all Players
        setWplayer(Player.fetch_players());
        Iterator<Player> witr = getWplayer().iterator();
        while (witr.hasNext())
            getWnames().add(witr.next().name());

        setBplayer(Player.fetch_players());
        Iterator<Player> bitr = getBplayer().iterator();
        while (bitr.hasNext())
            getBnames().add(bitr.next().name());
        setWNames(getWnames().toArray(getWNames()));
        setBNames(getBnames().toArray(getBNames()));

        getDisplay().setContent(this);

        //Defining the Player Box in Control Panel
        getDisplay().playerBoxInit();

        //Defining all the Cells
        getDisplay().cellsInit(this);

        getDisplay().playerDisplayInit();

        //The Left Layout When Game is inactive
        getDisplay().setLeftLayout();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        //variable initialization
        //	Pieces.variableInitialization();
        setPieces(new Pieces());
        setDisplay(new Display());
        //Setting up the Display.board
        SettingBoard();

    }

    private static void SettingBoard() {
        setMainboard(new Main());
        getMainboard().setVisible(true);
        getMainboard().setResizable(false);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static Pieces getPieces() {
        return pieces;
    }

    public static void setPieces(Pieces pieces) {
        Main.pieces = pieces;
    }

    public static Display getDisplay() {
        return display;
    }

    public static void setDisplay(Display display) {
        Main.display = display;
    }

    public static Cell getC() {
        return c;
    }

    public static void setC(Cell c) {
        Main.c = c;
    }

    public static Cell getPrevious() {
        return previous;
    }

    public static void setPrevious(Cell previous) {
        Main.previous = previous;
    }

    public static int getChance() {
        return chance;
    }

    public static void setChance(int chance) {
        Main.chance = chance;
    }

    public static Cell[][] getBoardState() {
        return boardState;
    }

    public static void setBoardState(Cell[][] boardState) {
        Main.boardState = boardState;
    }

    public static ArrayList<Cell> getDestinationlist() {
        return destinationlist;
    }

    public static void setDestinationlist(ArrayList<Cell> destinationlist) {
        Main.destinationlist = destinationlist;
    }

    public static Player getWhite() {
        return White;
    }

    public static void setWhite(Player white) {
        White = white;
    }

    public static Player getBlack() {
        return Black;
    }

    public static void setBlack(Player black) {
        Black = black;
    }

    public static Time getTimer() {
        return timer;
    }

    public static void setTimer(Time timer) {
        Main.timer = timer;
    }

    public static Main getMainboard() {
        return Mainboard;
    }

    public static void setMainboard(Main mainboard) {
        Mainboard = mainboard;
    }

    public static boolean isSelected() {
        return selected;
    }

    public static void setSelected(boolean selected) {
        Main.selected = selected;
    }

    public static boolean isEnd() {
        return end;
    }

    public static void setEnd(boolean end) {
        Main.end = end;
    }

    public static ArrayList<Player> getWplayer() {
        return wplayer;
    }

    public static void setWplayer(ArrayList<Player> wplayer) {
        Main.wplayer = wplayer;
    }

    public static ArrayList<Player> getBplayer() {
        return bplayer;
    }

    public static void setBplayer(ArrayList<Player> bplayer) {
        Main.bplayer = bplayer;
    }

    public static ArrayList<String> getWnames() {
        return Wnames;
    }

    public static void setWnames(ArrayList<String> wnames) {
        Wnames = wnames;
    }

    public static ArrayList<String> getBnames() {
        return Bnames;
    }

    public static void setBnames(ArrayList<String> bnames) {
        Bnames = bnames;
    }

    public static String getWname() {
        return wname;
    }

    public static void setWname(String wname) {
        Main.wname = wname;
    }

    public static String getBname() {
        return bname;
    }

    public static void setBname(String bname) {
        Main.bname = bname;
    }

    public static String getWinner() {
        return winner;
    }

    public static void setWinner(String winner) {
        Main.winner = winner;
    }

    public static String getMove() {
        return move;
    }

    public static void setMove(String move) {
        Main.move = move;
    }

    public static Player getTempPlayer() {
        return tempPlayer;
    }

    public static void setTempPlayer(Player tempPlayer) {
        Main.tempPlayer = tempPlayer;
    }

    public static String[] getWNames() {
        return WNames;
    }

    public static void setWNames(String[] WNames) {
        Main.WNames = WNames;
    }

    public static String[] getBNames() {
        return BNames;
    }

    public static void setBNames(String[] BNames) {
        Main.BNames = BNames;
    }

    public static int getTimeRemaining() {
        return timeRemaining;
    }

    public static void setTimeRemaining(int timeRemaining) {
        Main.timeRemaining = timeRemaining;
    }

    @Override
    public int getHeight() {
        return Height;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    // A function to change the chance from White Player to Black Player or vice verse
    // It is made public because it is to be accessed in the Time Class
    public void changechance() {
        if (getBoardState()[getKing(getChance()).getx()][getKing(getChance()).gety()].ischeck()) {
            setChance(getChance() ^ 1);
            sound.playSound("checkmate.wav");
            gameend();
        }
        if (!getDestinationlist().isEmpty())
            cleandestinations(getDestinationlist());
        if (getPrevious() != null)
            getPrevious().deselect();
        setPrevious(null);
        setChance(getChance() ^ 1);
        if (!isEnd() && getTimer() != null) {
            getTimer().reset();
            getTimer().start();
            getDisplay().removePlayer();
            changeTurn();

        }
    }

    private void changeTurn() {
        if (getMove() == "White")
            setMove("Black");
        else
            setMove("White");

        getDisplay().changeTurn(getMove());
    }

    //A function to retrieve the Black King or White King
    private King getKing(int color) {
        boolean isWhite = color == 0;
        return getPieces().returnKing(isWhite);
    }

    //A function to clean the highlights of possible destination cells
    private void cleandestinations(ArrayList<Cell> destlist)      //Function to clear the last Display.move's destinations
    {
        ListIterator<Cell> it = destlist.listIterator();
        while (it.hasNext())
            it.next().removepossibledestination();
    }

    //A function that indicates the possible Display.moves by highlighting the Cells
    private void highlightdestinations(ArrayList<Cell> destlist) {
        ListIterator<Cell> it = destlist.listIterator();
        while (it.hasNext())
            it.next().setpossibledestination();
    }

    private Cell[][] exceptionCheckKing(Cell newboardstate[][], boolean kingInDanger) {

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                try {
                    newboardstate[i][j] = new Cell(getBoardState()[i][j]);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    if (kingInDanger)
                        System.out.println("There is a problem with cloning !!");
                }
            }
        return newboardstate;
    }

    private Cell[][] checkForKingDanger(Cell newboardstate[][], Cell tempc, Cell fromcell) {
        if (newboardstate[tempc.x][tempc.y].getpiece() != null)
            newboardstate[tempc.x][tempc.y].removePiece();
        newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
        return newboardstate;
    }


    //Function to check if the king will be in danger if the given Display.move is made

    private boolean willkingbeindanger(Cell fromcell, Cell tocell) {
        Cell newboardstate[][] = new Cell[8][8];
        newboardstate = exceptionCheckKing(newboardstate, true);
        newboardstate = checkForKingDanger(newboardstate, tocell, fromcell);

        if (newboardstate[tocell.x][tocell.y].getpiece() instanceof King) {
            ((King) (newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
            ((King) (newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
        }
        newboardstate[fromcell.x][fromcell.y].removePiece();
        if (((King) (newboardstate[getKing(getChance()).getx()][getKing(getChance()).gety()].getpiece())).isindanger(newboardstate))
            return true;
        else
            return false;
    }

    //A function to eliminate the possible moves that will put the King in danger
    private ArrayList<Cell> filterdestination(ArrayList<Cell> destlist, Cell fromcell) {
        ArrayList<Cell> newlist = new ArrayList<Cell>();
        Cell newboardstate[][] = new Cell[8][8];
        ListIterator<Cell> it = destlist.listIterator();
        int x, y;
        while (it.hasNext()) {
            newboardstate = exceptionCheckKing(newboardstate, false);

            Cell tempc = it.next();
            newboardstate = checkForKingDanger(newboardstate, tempc, fromcell);

            x = getKing(getChance()).getx();
            y = getKing(getChance()).gety();
            if (newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King) {
                ((King) (newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
                ((King) (newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
                x = tempc.x;
                y = tempc.y;
            }
            newboardstate[fromcell.x][fromcell.y].removePiece();
            if ((!((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate)))
                newlist.add(tempc);
        }
        return newlist;
    }

    //A Function to filter the possible moves when the king of the current player is under Check
    private ArrayList<Cell> incheckfilter(ArrayList<Cell> destlist, Cell fromcell, int color) {
        ArrayList<Cell> newlist = new ArrayList<Cell>();
        Cell newboardstate[][] = new Cell[8][8];
        ListIterator<Cell> it = destlist.listIterator();
        int x, y;
        while (it.hasNext()) {
            newboardstate = exceptionCheckKing(newboardstate, false);

            Cell tempc = it.next();
            newboardstate = checkForKingDanger(newboardstate, tempc, fromcell);

            x = getKing(color).getx();
            y = getKing(color).gety();
            if (newboardstate[tempc.x][tempc.y].getpiece() instanceof King) {
                ((King) (newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
                ((King) (newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
                x = tempc.x;
                y = tempc.y;
            }
            newboardstate[fromcell.x][fromcell.y].removePiece();
            if ((!((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate)))
                newlist.add(tempc);
        }
        return newlist;
    }

    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkmate(int color) {
        ArrayList<Cell> dlist = new ArrayList<Cell>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getBoardState()[i][j].getpiece() != null && getBoardState()[i][j].getpiece().getcolor() == color) {
                    dlist.clear();
                    dlist = getBoardState()[i][j].getpiece().move(getBoardState(), i, j);
                    dlist = incheckfilter(dlist, getBoardState()[i][j], color);
                    if (dlist.size() != 0)
                        return false;
                }
            }
        }
        return true;
    }


    @SuppressWarnings("deprecation")
    private void gameend() {
        cleandestinations(getDestinationlist());
        getDisplay().disabledTime();
        getTimer().stop();
        if (getPrevious() != null)
            getPrevious().removePiece();
        if (getChance() == 0) {
            getWhite().updateGamesWon();
            getWhite().Update_Player();
            setWinner(getWhite().name());
        } else {
            getBlack().updateGamesWon();
            getBlack().Update_Player();
            setWinner(getBlack().name());
        }
        getDisplay().endDisplay(getWinner());

        setEnd(true);
        getMainboard().disable();
        getMainboard().dispose();
        setMainboard(new Main());
        getMainboard().setVisible(true);
        getMainboard().setResizable(false);
    }


    public void initializeClick() {
        getC().select();
        setPrevious(getC());
        getDestinationlist().clear();
        setDestinationlist(getC().getpiece().move(getBoardState(), getC().x, getC().y));
        if (getC().getpiece() instanceof King)
            setDestinationlist(filterdestination(getDestinationlist(), getC()));
        else {
            if (getBoardState()[getKing(getChance()).getx()][getKing(getChance()).gety()].ischeck())
                setDestinationlist(new ArrayList<Cell>(filterdestination(getDestinationlist(), getC())));
            else if (!getDestinationlist().isEmpty() && willkingbeindanger(getC(), getDestinationlist().get(0)))
                getDestinationlist().clear();
        }
        highlightdestinations(getDestinationlist());
    }


    public void setKingPieceCoordinates() {

        ((King) getC().getpiece()).setx(getC().x);
        ((King) getC().getpiece()).sety(getC().y);

    }


    //These are the abstract function of the parent class. Only relevant method here is the On-Click Fuction
    //which is called when the user clicks on a particular cell
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        setC((Cell) arg0.getSource());
        if (getPrevious() == null) {
            if (getC().getpiece() != null) {
                if (getC().getpiece().getcolor() != getChance())
                    return;
                initializeClick();
            }
        } else {
            if (getC().x == getPrevious().x && getC().y == getPrevious().y) {
                getC().deselect();
                cleandestinations(getDestinationlist());
                getDestinationlist().clear();
                setPrevious(null);
            } else if (getC().getpiece() == null || getPrevious().getpiece().getcolor() != getC().getpiece().getcolor()) {
                if (getC().ispossibledestination()) {
                    if (getC().getpiece() != null)
                    {
                        getC().removePiece();
                    	sound.playSound("kill_piece.wav");
                    }
                    else
                    {
                    	sound.playSound("move_piece.wav");
                    }
                    getC().setPiece(getPrevious().getpiece());
                    if (getPrevious().ischeck())
                        getPrevious().removecheck();
                    getPrevious().removePiece();
                    if (getKing(getChance() ^ 1).isindanger(getBoardState())) {
                        getBoardState()[getKing(getChance() ^ 1).getx()][getKing(getChance() ^ 1).gety()].setcheck();
                        if (checkmate(getKing(getChance() ^ 1).getcolor())) {
                            sound.playSound("checkmate.wav");
                            getPrevious().deselect();
                            if (getPrevious().getpiece() != null)
                                getPrevious().removePiece();
                            gameend();
                        }
                    }
                    if (!getKing(getChance()).isindanger(getBoardState()))
                        getBoardState()[getKing(getChance()).getx()][getKing(getChance()).gety()].removecheck();
                    if (getC().getpiece() instanceof King) {
                        setKingPieceCoordinates();
                    }
                    changechance();
                    if (!isEnd()) {
                        getTimer().reset();
                        getTimer().start();
                    }
                }
                if (getPrevious() != null) {
                    getPrevious().deselect();
                    setPrevious(null);
                }
                cleandestinations(getDestinationlist());
                getDestinationlist().clear();
            } else if (getPrevious().getpiece().getcolor() == getC().getpiece().getcolor()) {
                getPrevious().deselect();
                cleandestinations(getDestinationlist());
                initializeClick();
            }


        }
        if (getC().getpiece() != null && getC().getpiece() instanceof King) {
            setKingPieceCoordinates();
        }
    }

    //Other Irrelevant abstract function. Only the Click Event is captured.
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }


}