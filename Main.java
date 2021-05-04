import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    private static int undoCounter;
    /**
     * This method is run when the program starts
     *
     * @param args arguments passed to the program
     */
    public static void main(String[] args) {
        JFrame start = new JFrame("Mancala Starting Screen");
        start.setLayout(new FlowLayout());
        JTextArea jta = new JTextArea("Pick a board color");
        Button blue = new Button("Blue");
        Button red = new Button("Green");
        Button black = new Button("Black");
        start.add(jta);
        start.add(black);
        start.add(blue);
        start.add(red);
        start.setSize(777, 300);
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        JFrame frame = new JFrame("Mancala");
        frame.setSize(777, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MancalaBoard mb = new MancalaBoard();
        View v = new View(mb);
        frame.add(v);
        mb.attach(v);
        //Undo button on south panel
        JPanel panel = new JPanel();
        JButton undoButton = new JButton("Undo");
        undoCounter=0;
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mb.stonesMoved()==true) {
                    if (undoCounter == 3) {
                        mb.switchTurn();
                        undoCounter = 0;
                    }
                    else if (undoCounter < 3) {
                        undoCounter++;
                        v.getPreviousBoardState();
                    }
                }
            }});
        //Confirm Move button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mb.switchTurn();
            }
        });

        panel.add(confirmButton, BorderLayout.WEST);
        panel.add(undoButton,BorderLayout.EAST);
        frame.add(panel,BorderLayout.SOUTH);

        black.addActionListener(e -> {
                    BlackBoard b = new BlackBoard();
                    v.setDesign(b);
                    int x = start.getX();
                    int y = start.getY();
                    frame.setLocation(x, y);
                    frame.setVisible(true);
                    start.setVisible(false);
                }
        );
        blue.addActionListener(e -> {
                    BlueBoard bl = new BlueBoard();
                    v.setDesign(bl);
                    int x = start.getX();
                    int y = start.getY();
                    frame.setLocation(x, y);
                    frame.setVisible(true);
                    start.setVisible(false);
                }
        );
        red.addActionListener(e -> {
                    GreenBoard gb = new GreenBoard();
                    v.setDesign(gb);
                    int x = start.getX();
                    int y = start.getY();
                    frame.setLocation(x, y);
                    frame.setVisible(true);
                    start.setVisible(false);
                }
        );
    }
}