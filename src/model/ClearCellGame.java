package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame  extends Game{
	private Random random;
	private int strategy;
	private int score;
	
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}

	public int getScore() {
		return score;
	}

	public boolean isGameOver() {
		for (int col = 0 ; col < getMaxCols() ; col++) {
			if(getBoardCell(getMaxRows()-1, col) != BoardCell.EMPTY) {
				return true;
			}
		}
		return false;
	}

	public void nextAnimationStep() {
		if (this.isGameOver() == false) {
			for(int row = getMaxRows()-2; row >= 0; row--){
				for(int col = 0; col < getMaxCols(); col++){
					setBoardCell(row+1, col, this.getBoardCell(row, col));
				}
			}
			for (int col = 0 ; col < getMaxCols() ; col++) {
				setBoardCell(0, col, BoardCell.getNonEmptyRandomBoardCell(random));
			}
		}
	}

	public void processCell(int rowIndex, int colIndex) {
		if(board[rowIndex][colIndex] != BoardCell.EMPTY && this.isGameOver() == false){

			int row1 = rowIndex-1;
			while(row1 >= 0 && board[row1][colIndex].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[row1][colIndex] = BoardCell.EMPTY;
				row1--;
				score++;
			}

			row1 = rowIndex+1; 
			while(row1 < getMaxRows() && board[row1][colIndex].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[row1][colIndex] = BoardCell.EMPTY;
				row1++;
				score++;
			}
			
			int col1 = colIndex-1;
			while(col1 >= 0 && board[rowIndex][col1].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[rowIndex][col1] = BoardCell.EMPTY;
				col1--;
				score++;
			}

			int col2 = colIndex+1;
			while(col2 < getMaxCols() && board[rowIndex][col2].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[rowIndex][col2] = BoardCell.EMPTY;
				col2++;
				score++;
			}
			
			int row3 = rowIndex-1;
			int col3 = colIndex-1; 
			while(row3 >= 0 && col3 >= 0 && board[row3][col3].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[row3][col3] = BoardCell.EMPTY;
				row3--;
				col3--;
				score++;
			}

			int row4 = rowIndex-1;
			int col4 = colIndex+1; 
			while(row4 >= 0 && col4 < getMaxCols() && board[row4][col4].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[row4][col4] = BoardCell.EMPTY;
				row4--;
				col4++;
				score++;
			}

			int row5 = rowIndex+1;
			int col5 = colIndex-1; 
			while(row5 < getMaxRows() && col5 >= 0 && board[row5][col5].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[row5][col5] = BoardCell.EMPTY;
				row5++;
				col5--;
				score++;
			}

			int row6 = rowIndex+1;
			int col6 = colIndex+1; 
			while(row6 < getMaxRows() && col6 < getMaxCols() && board[row6][col6].getColor() == board[rowIndex][colIndex].getColor()){ 
				board[row6][col6] = BoardCell.EMPTY;
				row6++;
				col6++;
				score++;
			}

			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
		}
		collapse();
	}

	private boolean isRowEmpty(int row){
		int count = 0;
		for(int col = 0; col < getMaxCols(); col++){
			if(getBoardCell(row, col) == BoardCell.EMPTY){
				count++;
			}
		}
		if(count == getMaxCols()){  
			return true;
		}
		return false;
	}
	private void collapse() {
		for(int row = getMaxRows()-2; row > 0; row--){
			if(isRowEmpty(row)){
				for(int row2 = row; row2 < getMaxRows()-1; row2++){
					for(int col = 0; col < getMaxCols(); col++){
						board[row2][col] = board[row2+1][col];
						board[row2+1][col] = BoardCell.EMPTY;
					}
				}
			}
		}
	}
}
