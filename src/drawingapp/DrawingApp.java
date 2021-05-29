package drawingapp;

import javax.swing.*;

public class DrawingApp {

    public static void main(String[] args) {
        System.out.println("Starting drawing application...");
        DrawingFrame frame = new DrawingFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

