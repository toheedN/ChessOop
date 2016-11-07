package pieces;

public class Pieces {

    private Rook wr01;
    private Rook wr02;
    private Rook br01;
    private Rook br02;
    private Knight wk01;
    private Knight wk02;
    private Knight bk01;
    private Knight bk02;
    private Bishop wb01;
    private Bishop wb02;
    private Bishop bb01;
    private Bishop bb02;
    private Pawn[] wp;
    private Pawn[] bp;
    private Queen wq;
    private Queen bq;
    private King wk;
    private King bk;

    public Pieces() {

        setWr01(new Rook("WR01", "White_Rook.png", 0));
        setWr02(new Rook("WR02", "White_Rook.png", 0));
        setBr01(new Rook("BR01", "Black_Rook.png", 1));
        setBr02(new Rook("br02", "Black_Rook.png", 1));
        setWk01(new Knight("WK01", "White_Knight.png", 0));
        setWk02(new Knight("WK02", "White_Knight.png", 0));
        setBk01(new Knight("BK01", "Black_Knight.png", 1));
        setBk02(new Knight("BK02", "Black_Knight.png", 1));
        setWb01(new Bishop("WB01", "White_Bishop.png", 0));
        setWb02(new Bishop("WB02", "White_Bishop.png", 0));
        setBb01(new Bishop("BB01", "Black_Bishop.png", 1));
        setBb02(new Bishop("BB02", "Black_Bishop.png", 1));
        setWq(new Queen("WQ", "White_Queen.png", 0));
        setBq(new Queen("BQ", "Black_Queen.png", 1));
        setWk(new King("WK", "White_King.png", 0, 7, 3));
        setBk(new King("BK", "Black_King.png", 1, 0, 3));
        setWp(new Pawn[8]);
        setBp(new Pawn[8]);
        for (int i = 0; i < 8; i++) {
            getWp()[i] = new Pawn("WP0" + (i + 1), "White_Pawn.png", 0);
            getBp()[i] = new Pawn("BP0" + (i + 1), "Black_Pawn.png", 1);
        }
    }
    
    
    public void updatePieces(){
    	
    	
    	
    	
        setWr01(new Rook("WR01", "Whiterook.png", 0));
        setWr02(new Rook("WR02", "Whiterook.png", 0));
        setBr01(new Rook("BR01", "Blackrook.png", 1));
        setBr02(new Rook("br02", "Blackrook.png", 1));
        setWk01(new Knight("WK01", "Whiteknight.png", 0));
        setWk02(new Knight("WK02", "Whiteknight.png", 0));
        setBk01(new Knight("BK01", "Blackknight.png", 1));
        setBk02(new Knight("BK02", "Blackknight.png", 1));
        setWb01(new Bishop("WB01", "Whitebishop.png", 0));
        setWb02(new Bishop("WB02", "Whitebishop.png", 0));
        setBb01(new Bishop("BB01", "Blackbishop.png", 1));
        setBb02(new Bishop("BB02", "BlackBishop.png", 1));
        setWq(new Queen("WQ", "Whitequeen.png", 0));
        setBq(new Queen("BQ", "Blackqueen.png", 1));
        setWk(new King("WK", "Whiteking.png", 0, 7, 3));
        setBk(new King("BK", "Blackking.png", 1, 0, 3));
        setWp(new Pawn[8]);
        setBp(new Pawn[8]);
        for (int i = 0; i < 8; i++) {
            getWp()[i] = new Pawn("WP0" + (i + 1), "Whitepawn.png", 0);
            getBp()[i] = new Pawn("BP0" + (i + 1), "Blackpawn.png", 1);
        }
        
    }

    public Piece getAppropriatePiece(int i, int j) {

        Piece P = null;

        if (i == 0 && j == 0)
            P = getBr01();
        else if (i == 0 && j == 7)
            P = getBr02();
        else if (i == 7 && j == 0)
            P = getWr01();
        else if (i == 7 && j == 7)
            P = getWr02();
        else if (i == 0 && j == 1)
            P = getBk01();
        else if (i == 0 && j == 6)
            P = getBk02();
        else if (i == 7 && j == 1)
            P = getWk01();
        else if (i == 7 && j == 6)
            P = getWk02();
        else if (i == 0 && j == 2)
            P = getBb01();
        else if (i == 0 && j == 5)
            P = getBb02();
        else if (i == 7 && j == 2)
            P = getWb01();
        else if (i == 7 && j == 5)
            P = getWb02();
        else if (i == 0 && j == 3)
            P = getBk();
        else if (i == 0 && j == 4)
            P = getBq();
        else if (i == 7 && j == 3)
            P = getWk();
        else if (i == 7 && j == 4)
            P = getWq();
        else if (i == 1)
            P = getBp()[j];
        else if (i == 6)
            P = getWp()[j];
        return P;
    }

    public King returnKing(boolean isWhite) {
        if (isWhite)
            return getWk();
        else
            return getBk();
    }

    public Rook getWr01() {
        return wr01;
    }

    public void setWr01(Rook wr01) {
        this.wr01 = wr01;
    }

    public Rook getWr02() {
        return wr02;
    }

    public void setWr02(Rook wr02) {
        this.wr02 = wr02;
    }

    public Rook getBr01() {
        return br01;
    }

    public void setBr01(Rook br01) {
        this.br01 = br01;
    }

    public Rook getBr02() {
        return br02;
    }

    public void setBr02(Rook br02) {
        this.br02 = br02;
    }

    public Knight getWk01() {
        return wk01;
    }

    public void setWk01(Knight wk01) {
        this.wk01 = wk01;
    }

    public Knight getWk02() {
        return wk02;
    }

    public void setWk02(Knight wk02) {
        this.wk02 = wk02;
    }

    public Knight getBk01() {
        return bk01;
    }

    public void setBk01(Knight bk01) {
        this.bk01 = bk01;
    }

    public Knight getBk02() {
        return bk02;
    }

    public void setBk02(Knight bk02) {
        this.bk02 = bk02;
    }

    public Bishop getWb01() {
        return wb01;
    }

    public void setWb01(Bishop wb01) {
        this.wb01 = wb01;
    }

    public Bishop getWb02() {
        return wb02;
    }

    public void setWb02(Bishop wb02) {
        this.wb02 = wb02;
    }

    public Bishop getBb01() {
        return bb01;
    }

    public void setBb01(Bishop bb01) {
        this.bb01 = bb01;
    }

    public Bishop getBb02() {
        return bb02;
    }

    public void setBb02(Bishop bb02) {
        this.bb02 = bb02;
    }

    public Pawn[] getWp() {
        return wp;
    }

    public void setWp(Pawn[] wp) {
        this.wp = wp;
    }

    public Pawn[] getBp() {
        return bp;
    }

    public void setBp(Pawn[] bp) {
        this.bp = bp;
    }

    public Queen getWq() {
        return wq;
    }

    public void setWq(Queen wq) {
        this.wq = wq;
    }

    public Queen getBq() {
        return bq;
    }

    public void setBq(Queen bq) {
        this.bq = bq;
    }

    public King getWk() {
        return wk;
    }

    public void setWk(King wk) {
        this.wk = wk;
    }

    public King getBk() {
        return bk;
    }

    public void setBk(King bk) {
        this.bk = bk;
    }
}
