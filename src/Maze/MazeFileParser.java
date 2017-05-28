package Maze;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rian De Rous
 */
public  class MazeFileParser  {
private List<String> mazeAsText;
private int width;
private int height;
private  MazePart[][] mz;
    
    
    public MazeFileParser(String filename){
        try {
            mazeAsText = Files.readAllLines(Paths.get(filename));
            mz = new MazePart[getMazeWidth()][getMazeHeight()];
        } catch (IOException ex) {
            Logger.getLogger(MazeFileParser.class.getName()).log(Level.SEVERE, null, ex);
        }	
    }
    
    public final int getMazeWidth(){
        width = mazeAsText.get(0).length();
        return width;
    }
    
    public final int getMazeHeight(){
        height= mazeAsText.size();
        return height;
    }
    
    public  MazePart[][] parse(){
       for(int y = 0; y<height;y++){
           for(int x = 0; x<width; x++ ){
               if('X' == mazeAsText.get(y).charAt(x)){
                   mz[x][y]=(MazePart.WALL);
               } else {
                   mz[x][y]=(MazePart.UNDISCOVERED);
               }  
           }
       }
       return mz;
    }
    
  
}
