package pieces;

public class Pieces {

	private Rook wr01,wr02,br01,br02;
	private Knight wk01,wk02,bk01,bk02;
	private Bishop wb01,wb02,bb01,bb02;
	private Pawn wp[],bp[];
	private Queen wq,bq;
	private King wk,bk;
	
	public Piece getAppropriatePiece(int i , int j){
		
		Piece P=null;

		if(i==0&&j==0)
			P=br01;
		else if(i==0&&j==7)
			P=br02;
		else if(i==7&&j==0)
			P=wr01;
		else if(i==7&&j==7)
			P=wr02;
		else if(i==0&&j==1)
			P=bk01;
		else if (i==0&&j==6)
			P=bk02;
		else if(i==7&&j==1)
			P=wk01;
		else if (i==7&&j==6)
			P=wk02;
		else if(i==0&&j==2)
			P=bb01;
		else if (i==0&&j==5)
			P=bb02;
		else if(i==7&&j==2)
			P=wb01;
		else if(i==7&&j==5)
			P=wb02;
		else if(i==0&&j==3)
			P=bk;
		else if(i==0&&j==4)
			P=bq;
		else if(i==7&&j==3)
			P=wk;
		else if(i==7&&j==4)
			P=wq;
		else if(i==1)
		P=bp[j];
		else if(i==6)
			P=wp[j];
		return P;
	}
	
	public King returnKing(boolean isWhite){
		if (isWhite)
			return wk;
		else
			return bk;
	}
public Pieces(){
		
		wr01=new Rook("WR01","White_Rook.png",0);
		wr02=new Rook("WR02","White_Rook.png",0);
		br01=new Rook("BR01","Black_Rook.png",1);
		br02=new Rook("br02","Black_Rook.png",1);
		wk01=new Knight("WK01","White_Knight.png",0);
		wk02=new Knight("WK02","White_Knight.png",0);
		bk01=new Knight("BK01","Black_Knight.png",1);
		bk02=new Knight("BK02","Black_Knight.png",1);
		wb01=new Bishop("WB01","White_Bishop.png",0);
		wb02=new Bishop("WB02","White_Bishop.png",0);
		bb01=new Bishop("BB01","Black_Bishop.png",1);
		bb02=new Bishop("BB02","Black_Bishop.png",1);
		wq=new Queen("WQ","White_Queen.png",0);
		bq=new Queen("BQ","Black_Queen.png",1);
		wk=new King("WK","White_King.png",0,7,3);
		bk=new King("BK","Black_King.png",1,0,3);
		wp=new Pawn[8];
		bp=new Pawn[8];
		for(int i=0;i<8;i++)
		{
			wp[i]=new Pawn("WP0"+(i+1),"White_Pawn.png",0);
			bp[i]=new Pawn("BP0"+(i+1),"Black_Pawn.png",1);
		}
	}
}
