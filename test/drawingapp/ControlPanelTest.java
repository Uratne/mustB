package drawingapp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class ControlPanelTest {

    public ControlPanelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * This test will check the initial values of the control panel are as they should be.
     * @author yourName
     */
    @Test
    public void controlPanelInitTest() throws Exception {

        System.out.println("Test for initiation of the panels in the control panel. ");

        String expFigureDataPanelInit = "Circle";
        String expFigurePanelInit = "Circle";
        String expActionPanelInit = "None";
        String expCheckBoxInit = "Red";

        FigurePanel figurePanel;
        ActionPanel actionPanel = null;
        FigureDataPanel figureDataPanel;
        CheckBoxPanel checkBoxPanel;

        DrawingPanel drawingPanel = new DrawingPanel();

        figureDataPanel = new FigureDataPanel();
        figurePanel = new FigurePanel(drawingPanel,actionPanel,figureDataPanel);
        actionPanel = new ActionPanel(drawingPanel,figurePanel);
        checkBoxPanel = new CheckBoxPanel(drawingPanel);

        figurePanel.setActionPanel(actionPanel);

        String figureDataPanelInit = figureDataPanel.getCurrentActionLabel();
        String figurePanelInit = (String) figurePanel.getSelectedItem();
        String actionPanelInit = (String) actionPanel.getSelectedItem();
        String checkBoxInit = checkBoxPanel.getSelectedColor();

        assertEquals(expFigureDataPanelInit, figureDataPanelInit);
        assertEquals(expFigurePanelInit, figurePanelInit);
        assertEquals(expActionPanelInit, actionPanelInit);
        assertEquals(expCheckBoxInit, checkBoxInit);
    }
}
