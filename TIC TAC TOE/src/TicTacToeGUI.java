import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {

    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 500;
    char[][] ticTacToe = new char[3][3];
    boolean play = false;
    boolean player1 = true;
    JButton[] buttons = new JButton[9];
    int turn;

    TicTacToeGUI() {
        play = true;

        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLayout(new GridLayout(3, 3, 10, 10));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("JetBrains Mono", Font.BOLD, 60));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

        this.setVisible(true);
        this.setLocationRelativeTo(null);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToe[i][j] = '.';
            }
        }
    }

    public void check(int r, int c, char ch, int x, int y) {
        for (int i = 0; i < 3; i++) {
            if (ticTacToe[r][i] == ticTacToe[r][c]) {
                x++;
            }
            if (ticTacToe[i][c] == ticTacToe[r][c]) {
                y++;
            }
        }
        if (x == 3 || y == 3) {
            play = false;
        }
        if (play) {
            x = 0;
            y = 0;
            if ((r + c) % 2 == 0) {
                if (r == 0) {
                    if (c == 0) {
                        for (int i = 0, j = 0; i < 3 && j < 3; i++, j++) {
                            if (ticTacToe[i][j] == ticTacToe[r][c]) {
                                x++;
                            }
                        }
                    }
                } else if (r == 1) {
                    for (int i = 0, j = 0; i < 3 && j < 3; i++, j++) {
                        if (ticTacToe[i][j] == ticTacToe[r][c]) {
                            x++;
                        }
                    }
                    for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
                        if (ticTacToe[i][j] == ticTacToe[r][c]) {
                            y++;
                        }
                    }
                } else {
                    if (c == 0) {
                        for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
                            if (ticTacToe[i][j] == ticTacToe[r][c]) {
                                y++;
                            }
                        }
                    } else {
                        for (int i = 0, j = 0; i < 3 && j < 3; i++, j++) {
                            if (ticTacToe[i][j] == ticTacToe[r][c]) {
                                x++;
                            }
                        }
                    }
                }
            }
        }

        if (x == 3 || y == 3) {
            play = false;
        }
    }

    public void update(int i, char ch) {
        if (i == 0) {
            ticTacToe[0][0] = ch;
            check(0, 0, ch, 0, 0);
        } else if (i <= 2) {
            ticTacToe[0][i] = ch;
            check(0, i, ch, 0, 0);
        } else if (i <= 5) {
            ticTacToe[1][i - 3] = ch;
            check(1, i - 3, ch, 0, 0);
        } else {
            ticTacToe[2][i - 2 * 3] = ch;
            check(2, i - 2 * 3, ch, 0, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play && turn != 9) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i] && player1) {
                    turn++;
                    buttons[i].setText("X");
                    update(i, 'X');
                    if (!play) {
                        JOptionPane.showMessageDialog(null, "game over", "Game over", JOptionPane.INFORMATION_MESSAGE);
                    }
                    player1 = false;
                    break;
                }
                if (e.getSource() == buttons[i] && !player1) {
                    turn++;
                    buttons[i].setText("O");
                    update(i, 'O');
                    if (!play) {
                        JOptionPane.showMessageDialog(null, "game over", "Game over", JOptionPane.INFORMATION_MESSAGE);
                    }
                    player1 = true;
                    break;
                }
            }
        }
        else if (turn == 9) {
            JOptionPane.showMessageDialog(null, "tie", "Game over", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
