/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 *
 * @author Carl
 */
public class LineTest{

    public LineTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Tests the selection method for lines functionalities correctness.
     * @author yourName
     */
    @Test
    public void testLineSelect() throws Exception {
        System.out.println("Line Selecting Test");

        DrawingPanel drawingPanel = new DrawingPanel();
        
        System.out.println("Creating two line, one horizontal, one vertical");
        
        Point verticalPoint = new Point(3,4);
        Point middlePoint = new Point(3, 8);
        Point horizontalPoint = new Point(10,8);

        Dimension dimension = new Dimension(1,1);

        Color red = Color.red;

        Line verticalLine = new Line(middlePoint, verticalPoint, dimension, red);
        Line horizontalLine = new Line(middlePoint, horizontalPoint, dimension, red);

        System.out.println("Picking two points such that one close to vertical line and one far from both");
        
        boolean expVerticalResult = true;
        boolean expHorizontalResult = false;
        
        boolean verticalResult = drawingPanel.isNearLine(verticalLine, 2, 5);
        boolean horizontalResult = drawingPanel.isNearLine(horizontalLine, 10, 10);

        assertEquals(expVerticalResult, verticalResult); //is near test for vertical line
        assertEquals(expHorizontalResult, horizontalResult);//is near test for horizontal line

        //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
    }

}
