package Maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rian De Rous
 */
public class Maze {

    private final MazePart[][] mz;
    private final int width;
    private final int heigth;

    public Maze(String filename) {
        MazeFileParser mfp = new MazeFileParser(filename);
        width = mfp.getMazeWidth();
        heigth = mfp.getMazeHeight();
        mz = mfp.parse();

    }

    public Maze() {
        String path = "maze.txt";
        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fc.setFileFilter(filter);
        fc.setCurrentDirectory(new File(System.getProperty("java.class.path")));

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().toString();
            System.out.println(path);

        }
        MazeFileParser mfp = new MazeFileParser(path);
        width = mfp.getMazeWidth();
        heigth = mfp.getMazeHeight();
        mz = mfp.parse();
    }

    public MazePart[][] getMazePart() {
        return mz;
    }

    public boolean isUndiscoverd(Point p) {
        return (mz[p.x][p.y] == MazePart.UNDISCOVERED);
    }

    public MazePart getMazePart(Point p) {
      
        return mz[p.x][p.y];

    }
    
    public MazePart getMazePart(int x, int y){
       // System.out.println("get MazePart (" + x + "," + y +")");
        return getMazePart(new Point(x,y));
    }

    public void setMazePart(int x, int y, MazePart mazepart) {
        mz[x][y] = mazepart;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return heigth;
    }

    public  void printMaze(Graphics g) {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < heigth; y++) {
                switch (mz[x][y]) {
                    case WALL:
                        g.setColor(Color.BLACK);
                        break;
                    case CORRECTPATH:
                        g.setColor(Color.GREEN);
                        break;
                    case UNKNOWN:
                        g.setColor(Color.ORANGE);
                    case INCORRECTPATH:
                        g.setColor(Color.PINK);
                        break;
                    case UNDISCOVERED:
                    default:
                        g.setColor(Color.LIGHT_GRAY);
                }
                int boxsize = 1000/width;
                g.fillRect(boxsize * x +5, boxsize * y + 5,boxsize,boxsize);
               /* StringBuilder sb = new StringBuilder();
                sb.append("(").append(x).append(",").append(y).append(")");
                g.setColor(Color.WHITE);
                g.drawString(sb.toString(), (2*x+1)*boxsize/2,(2*y+1)*boxsize/2);
                */
                
            }

        }
    }
}
