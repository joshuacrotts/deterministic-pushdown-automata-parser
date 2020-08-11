package view;

import dpda.Transition;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import model.CircleComponent;

/**
 * This class is the panel for the main view of the window; it shows the
 * transitions, and states for the corresponding DPDA.
 *
 * @author Joshua
 */
public class DrawingPanel extends JPanel {

  private final MainWindow mainFrame;
  private final StackView stackView;

  private ArrayList<CircleComponent> circles;

  private final int Y_TRANSITION_OFFSET = 20;
  private final double CIRCLE_RADIUS = 250;

  public DrawingPanel(MainWindow frame) {
    this.mainFrame = frame;
    this.stackView = new StackView(mainFrame);
    this.addCircles(this.mainFrame.getDpda().getNumberOfStates());
    super.setBackground(Color.WHITE);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawTransitions(g);

    for (int i = 0; i < this.circles.size(); i++) {
      this.circles.get(i).paintComponent(g);
    }
    this.stackView.paintComponent(g);
  }

  public void drawTransitions(Graphics g) {
    for (int i = 0; i < this.mainFrame.getDpda().getNumberOfStates(); i++) {
      List<Transition> transitions = this.mainFrame.getDpda().getStateObject(i + 1).getTransitions();
      if (transitions == null) {
        return;
      }
      
      // If we have more than one transition on a state, then we need to offset
      // it vertically.
      for (int t = 0; t < transitions.size(); t++) {
        if (transitions.size() > 1 && transitions.get(t).getYOffset() == 0 && t != 0) {
          transitions.get(t).setYOffset(transitions.get(t).getYOffset() + Y_TRANSITION_OFFSET);
        }
        transitions.get(t).paintComponent(g);
      }
    }
  }

  public ArrayList<CircleComponent> getCircles() {
    return this.circles;
  }

  private void addCircles(int n) {
    this.circles = new ArrayList<>();

    double angleFactor = 2 * Math.PI / n;
    double originX = mainFrame.getCircleCenterX();
    double originY = mainFrame.getCircleCenterY();
    double radius = CIRCLE_RADIUS;
    double angle;

    for (int i = 0; i < n; i++) {
      angle = i * angleFactor;
      double x = originX + radius * Math.cos(angle);
      double y = originY + radius * Math.sin(angle);
      this.circles.add(new CircleComponent(this, (int) x, (int) y,
              this.mainFrame.getDpda().getStateObject(i + 1)));
    }
  }
}
