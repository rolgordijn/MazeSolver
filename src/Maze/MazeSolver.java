package Maze;

import java.awt.Point;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rian De Rous
 */
public class MazeSolver implements Runnable {
   int steps, fails; 
    Maze maze;
    Stack<Point> junctions;
    MazePart next;
    Stack<Point> route;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        junctions = new Stack();
        route = new Stack();
    }

    
    
    public Point getEntryPoint(Maze maze) {
        int x = 0;
        int y = 0;
        Point p = null;
        while (x < maze.getWidth() & y < maze.getHeight()) {

            if (maze.getMazePart(0, y) == MazePart.UNDISCOVERED) {

                p = new Point(0, y);
            }
            if (maze.getMazePart(maze.getWidth() - 1, y) == MazePart.UNDISCOVERED) {

                p = new Point(maze.getWidth() - 1, y);
            }
            if (maze.getMazePart(x, 0) == MazePart.UNDISCOVERED) {
                p = new Point(x, 0);
            }

            if (maze.getMazePart(x, maze.getHeight() - 1) == MazePart.UNDISCOVERED) {

                p = new Point(x, maze.getHeight() - 1);
            }
            x++;
            y++;

            if (p != null) {
                System.out.println("found entry at (" + p.x + ";" + p.y + ")");
                maze.setMazePart(p.x, p.y, MazePart.CORRECTPATH);
                DefaultMutableTreeNode top = new DefaultMutableTreeNode(p);

                return p;
            }

        }
        return null;
    }

    public Point nextStep(Point p, MazePart part, MazePart nextPart) {
        steps++;
        route.push(p);
        isJunction(p);
        // System.out.println("finding undiscovered area near" + p.toString());
        MazePart mp = null;
        Point next = new Point(p.x, p.y);
        boolean succes = false;
        if (p.x + 1 < maze.getWidth()) {

            mp = maze.getMazePart(p.x + 1, p.y);
            next.x = p.x + 1;
            succes = (maze.getMazePart(next) == part);

        }
        if (p.x - 1 >= 0 & !succes) {
            mp = maze.getMazePart(p.x - 1, p.y);
            //    System.out.println("mazepart found of type " + mp.toString());
            next.x = p.x - 1;
            succes = (maze.getMazePart(next) == part);
        }
        if (p.y + 1 < maze.getWidth() & !succes) {

            mp = maze.getMazePart(p.x, p.y + 1);
            //  System.out.println("mazepart found of type " + mp.toString());
            next.x = p.x;
            next.y = p.y + 1;
            succes = (maze.getMazePart(next) == part);
        }
        if (p.y - 1 >= 0 & !succes) {

            mp = maze.getMazePart(p.x, p.y - 1);
            //     System.out.println("mazepart found of type " + mp.toString());
            next.x = p.x;
            next.y = p.y - 1;

        }
//        System.out.println("");

        if (mp == part) {
            maze.setMazePart(next.x, next.y, nextPart);
            return next;
        } else {
            return invalidPath(p);

        }

    }

    public boolean isJunction(Point p, MazePart part) {

        int dir = 0;
        if (p.x + 1 < maze.getWidth()) {
            if (maze.getMazePart(p.x + 1, p.y) == part) {
                dir++;
            }
        }

        if (p.x - 1 >= 0) {
            if (maze.getMazePart(p.x - 1, p.y) == part) {
                dir++;
            }
        }
        if (p.y + 1 < maze.getHeight()) {
            if (maze.getMazePart(p.x, p.y + 1) == part) {
                dir++;
            }
        }
        if ((p.y - 1) >= 0) {
            if (maze.getMazePart(p.x, p.y - 1) == part) {
                dir++;
            }
        }
        if (dir > 1) {
            junctions.push(p);
            System.out.println("junctions size" + junctions.size());
            System.out.println("this point is a junction " + p.toString());

        }

        return (dir > 1);
    }

    public boolean isJunction(Point p) {

        int dir = 0;
        if (p.x + 1 < maze.getWidth()) {
            if (maze.getMazePart(p.x + 1, p.y) == MazePart.UNDISCOVERED) {
                dir++;
            }
        }

        if (p.x - 1 >= 0) {
            if (maze.getMazePart(p.x - 1, p.y) == MazePart.UNDISCOVERED) {
                dir++;
            }
        }
        if (p.y + 1 < maze.getHeight()) {
            if (maze.getMazePart(p.x, p.y + 1) == MazePart.UNDISCOVERED) {
                dir++;
            }
        }
        if ((p.y - 1) >= 0) {
            if (maze.getMazePart(p.x, p.y - 1) == MazePart.UNDISCOVERED) {
                dir++;
            }
        }
        if (dir > 1) {
            junctions.push(p);
            System.out.println("junctions size" + junctions.size());
            System.out.println("this point is a junction " + p.toString());

        }

        return (dir > 1);
    }

    public void solve() throws InterruptedException {
        Point p = getEntryPoint(maze);
        do {
            p = nextStep(p, MazePart.UNDISCOVERED, MazePart.CORRECTPATH);
           

        } while (p.x != 0 && p.y != 0 && p.y + 1 != maze.getHeight() && maze.getWidth() != p.x + 1);
        
        
        
    }

    private Point invalidPath(Point end) {
        fails++;
        System.out.println("path is invalid, returning to " + junctions.peek().toString());
            
        
        /*if (isJunction(junctions.peek(), MazePart.CORRECTPATH)) {
            return junctions.peek();
        } else {
            return junctions.pop();
        }*/
        
        return junctions.pop();
    }

    @Override
    public void run() {
        Point p = getEntryPoint(maze);
        do {
            System.out.println("p is" + p.toString());
            p = nextStep(p, MazePart.UNDISCOVERED, MazePart.CORRECTPATH);
           
            try {
               Thread.sleep(50,500);
            } catch (InterruptedException ex) {
                Logger.getLogger(MazeSolver.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (p.x != 0 && p.y != 0 && p.y + 1 != maze.getHeight() && maze.getWidth() != p.x + 1);
        String sb = "uitgang gevonden na " + steps + "stappen en " + fails + " foute wegen";
        JOptionPane.showMessageDialog(null, sb);
    }

}
