package chess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


/**
 * This is the Player Class
 * It provides the functionality of keeping track of all the users
 * Objects of this class is updated and written in the Game's Data Files after every Game
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private static playerfile userfile;
    private String name;
    private Integer gamesplayed;
    private Integer gameswon;

    //Constructor
    private Player(String name) {
        this.setName(name.trim());
        setGamesplayed(new Integer(0));
        setGameswon(new Integer(0));
        setUserfile(new playerfile());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static playerfile getUserfile() {
        return userfile;
    }

    public static void setUserfile(playerfile userfile) {
        Player.userfile = userfile;
    }

    public static ArrayList<Player> fetch_players()         //Function to fetch the list of the players
    {
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            input = add_players_from_game_data(players);
        } catch (FileNotFoundException e) {
            players.clear();
            return players;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read the required Game files !!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return players;
    }

    public static ObjectInputStream add_players_from_game_data(ArrayList<Player> players)
            throws IOException, FileNotFoundException, ClassNotFoundException {
        ObjectInputStream input;
        File infile = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
        input = new ObjectInputStream(new FileInputStream(infile));
        try {
            while (true) {
                players.add(getNextPlayer(input));
            }
        } catch (EOFException e) {
            input.close();
        }
        return input;
    }

    public static Player getNextPlayer(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Player tempplayer;
        tempplayer = (Player) input.readObject();
        return tempplayer;
    }

    public static Player createPlayer(String name) {
        return new Player(name);
    }

    //Name Getter
    public String name() {
        return getName();
    }

    //Returns the number of games played
    public Integer gamesplayed() {
        return getGamesplayed();
    }

    //Returns the number of games won
    public Integer gameswon() {
        return getGameswon();
    }

    //Calculates the win percentage of the player
    public Integer winpercent() {
        return new Integer((getGameswon() * 100) / getGamesplayed());
    }

    //Increments the number of games played
    public void updateGamesPlayed() {
        setGamesplayed(getGamesplayed() + 1);
    }

    //Increments the number of games won
    public void updateGamesWon() {
        setGameswon(getGameswon() + 1);
    }

    public void Update_Player()            //Function to update the statistics of a player
    {

        getUserfile().set_file_paths();
        try {
            getUserfile().make_iofile_objects();
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start");
            System.exit(0);
        }
        try {
            getUserfile().file_the_player_data(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGamesplayed() {
        return gamesplayed;
    }

    public void setGamesplayed(Integer gamesplayed) {
        this.gamesplayed = gamesplayed;
    }

    public Integer getGameswon() {
        return gameswon;
    }

    public void setGameswon(Integer gameswon) {
        this.gameswon = gameswon;
    }

    public class playerfile {
        private String input_file_name;
        private String output_file_name;
        private File inputfile;
        private File outputfile;

        public playerfile() {
            input_file_name = "";
            output_file_name = "";
            inputfile = new File("");
            outputfile = new File("");
        }

        public void make_iofile_objects() {
            inputfile = new File(input_file_name);
            outputfile = new File(output_file_name);
        }

        public void set_file_paths() {
            input_file_name = System.getProperty("user.dir") + File.separator + "chessgamedata.dat";
            output_file_name = System.getProperty("user.dir") + File.separator + "tempfile.dat";
        }

        public void file_the_player_data(Player user)
                throws IOException, FileNotFoundException, ClassNotFoundException {
            ObjectOutputStream output;
            boolean playerexists;
            output = new ObjectOutputStream(new FileOutputStream(outputfile));

            if (output_file_absent()) {
                create_new_output_file();
            }
            if (input_file_absent()) {
                output.writeObject(user);
            } else {
                playerexists = write_player_in_game_data(user, output);
                if (!playerexists) {
                    output.writeObject(user);
                }
            }
            inputfile.delete();
            output.close();
            File newf = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            if (!outputfile.renameTo(newf)) {
                System.out.println("File Renameing Unsuccessful");
            }
        }

        public boolean write_player_in_game_data(Player user, ObjectOutputStream output)
                throws IOException, FileNotFoundException, ClassNotFoundException {
            ObjectInputStream input;
            Player next_player;
            boolean playerexists;
            input = new ObjectInputStream(new FileInputStream(inputfile));
            playerexists = false;
            try {
                while (true) {
                    next_player = getNextPlayer(input);
                    if (user_is_in_game_data(user, next_player)) {
                        output.writeObject(user);
                        playerexists = true;
                    } else {
                        output.writeObject(next_player);
                    }
                }
            } catch (EOFException e) {
                input.close();
            }
            return playerexists;
        }

        public boolean user_is_in_game_data(Player user, Player next_player) {
            return next_player.name().equals(user.name());
        }

        public void create_new_output_file() throws IOException {
            outputfile.createNewFile();
        }

        public boolean input_file_absent() {
            return !inputfile.exists();
        }

        public boolean output_file_absent() {
            return !outputfile.exists();
        }
    }
}
