 //Name: Anup Bagali        DAte: 10/4/18
//
// 
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
 
import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.display.GridDisplay;
 
/**
 *  Environment-Based Applications:<br>
 *
 *    The NQueens class implements the N Queens problem.
 *
 *  @author Your Name (based on a template provided by Alyce Brady)
 *  @version 1 September 2002
 **/
 
public class NQueens
{
   // Instance Variables: Encapsulated data for EACH NQueens problem
   private Grid board;
   private GridDisplay display;
 
 // constructor
 
   /** Constructs an object that solves the N Queens Problem.
    *    @param n the number of queens to be placed on an
    *              <code>n</code> x <code>n</code> board
    *    @param d an object that knows how to display an 
    *              <code>n</code> x <code>n</code> board and
    *              the queens on it
    **/
   public NQueens(int n, GridDisplay d)
   {
      board = new BoundedGrid(n, n);
      display = d;
      display.setGrid(board);
      display.showGrid();
   }
 
 // methods
 
   /** Returns the number of queens to be placed on the board. **/
   public int numQueens()
   {
      return board.numRows();
   }
 
   /** Solves (or attempts to solve) the N Queens Problem. **/
   public boolean solve()
   {
      if(placeQueen(0)){
         display.showGrid();
         return true;
      }
      else{
         return false;
      } 
   }
 
   /** Attempts to place the <code>q</code>th queen on the board.
    *  (Precondition: <code>0 <= q < numQueens()</code>)
    *    @param q index of next queen to place
    **/
   private boolean placeQueen(int q)
   {
      // Queen q is placed in row q.  The only question is
      // which column she will be in.  Try them in turn.
      // Whenever we find a column that could work, put her
      // there and see if we can place the rest of the queens.
 
      if(q==numQueens())
         return true;
 
 
         for(int i=0;i<=numQueens()-1;i++){
            if(locationIsOK(new Location(q,i))){
               addQueen(new Location(q,i));
               display.showGrid();
               if(placeQueen(q+1))
                  return true;
 
            }
            removeQueen(new Location(q,i));
            display.showGrid();
         }
         return false;
 
 
   }
 
   /** Determines whether a queen can be placed at the specified
    *  location.
    *    @param loc  the location to test
    **/
   private boolean locationIsOK(Location loc)
   {
      // Verify that another queen can't attack this location.
      // (Only queens in previous rows have been placed.)
      int currentRow = loc.row();
      int currentCol = loc.col();
 
      for(int i =0;i<=board.numRows();i++){
         if(board.objectAt(new Location(i,currentCol)) != null)
            return false;
      }
 
      int r = currentRow; int c = currentCol;
      while(r>=0 && c>=0){
         if(board.objectAt(new Location(r,c)) != null)
            return false;
         r--;
         c--;
      }
      r = currentRow; c = currentCol;
      while(r>=0 && c<=board.numCols()){
         if(board.objectAt(new Location(r,c)) != null)
            return false;
         r--;
         c++;
      }
      r = currentRow; c = currentCol;
      while(r<=board.numRows() && c>=0){
         if(board.objectAt(new Location(r,c)) != null)
            return false;
         r++;
         c--;
      }
      r = currentRow; c = currentCol;
      while(r<=board.numRows() && c<=board.numCols()){
         if(board.objectAt(new Location(r,c)) != null)
            return false;
         r++;
         c++;
      }
 
      return true;
   }
 
   /** Adds a queen to the specified location.
    *    @param loc  the location where the queen should be placed
    **/
   private void addQueen(Location loc)
   {
      new Queen(board, loc);      // queens add themselves to the board
   }
 
   /** Removes a queen from the specified location.
    *    @param loc  the location where the queen should be removed
    **/
   private void removeQueen(Location loc)
   {
      board.remove(loc);
   }
 
}
 