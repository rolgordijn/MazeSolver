/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Maze.Maze;
import Maze.MazePart;
import Maze.MazeSolver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Rian De Rous
 */
public class MazeSolverDrawing extends javax.swing.JPanel implements ActionListener {

    Timer timer ;
    MazeSolver solver;
    Maze mz;
    boolean isrunning = false;
    Point p;

    /**
     * Creates new form MazeSolverDrawing
     *
     * @throws java.lang.InterruptedException
     */
    public MazeSolverDrawing() throws InterruptedException {
        initComponents();
        mz = new Maze();
        solver = new MazeSolver(mz);
        new Thread(solver).start();
        timer = new Timer(5, this); 
        timer.start();
        timer.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ev) {

        if (!isrunning) {

            isrunning = true;

        }
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        mz.printMaze(g);

    }

    public void solve() throws InterruptedException {
        p = solver.getEntryPoint(mz);
        do {
            System.out.println("p is" + p.toString());
            p = solver.nextStep(p, MazePart.UNDISCOVERED, MazePart.CORRECTPATH);
            repaint();

        } while (p.x != 0 && p.y != 0 && p.y + 1 != mz.getHeight() && mz.getWidth() != p.x + 1);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
