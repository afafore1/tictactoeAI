import ailogic.DecisionTree;
import game.Game;
import game.GamePlayer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ayomitunde on 1/23/2017.
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    static String userInput = "";
    GamePlayer gamePlayer = null;
    Game game = null;
    char player = ' ';

    public GUI() {
        initComponents();
        gamePlayer = new GamePlayer();
        game = new Game();
        player = Math.random() > .5 ? game.getPlayero() : game.getPlayerx();
        DecisionTree.aiCharacter = Game.switchPlayer(player);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn01 = new javax.swing.JButton();
        btn00 = new javax.swing.JButton();
        btn02 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn01MouseClicked(evt);
            }
        });

        btn00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn00MouseClicked(evt);
            }
        });

        btn02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn02MouseClicked(evt);
            }
        });

        btn10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn10MouseClicked(evt);
            }
        });

        btn11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn11MouseClicked(evt);
            }
        });

        btn12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn12MouseClicked(evt);
            }
        });

        btn20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn20MouseClicked(evt);
            }
        });

        btn21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn21MouseClicked(evt);
            }
        });

        btn22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn22MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn00, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn01, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn00, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn01, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void setText(javax.swing.JButton btn)
    {
        btn.setFont(new Font("Arial", Font.BOLD, 40));
        if(btn.getText() == "") btn.setText(String.valueOf(player));

    }

    private void setAIText(javax.swing.JButton btn, String value)
    {
        btn.setFont(new Font("Arial", Font.BOLD, 40));
        btn.setText(value);
    }

    private void play(String computerInput)
    {
        if(computerInput != null)
        {
            switch (computerInput)
            {
                case "00":
                    setAIText(btn00, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "01":
                    setAIText(btn01, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "02":
                    setAIText(btn02, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "10":
                    setAIText(btn10, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "11":
                    setAIText(btn11, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "12":
                    setAIText(btn12, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "20":
                    setAIText(btn20, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "21":
                    setAIText(btn21, String.valueOf(DecisionTree.aiCharacter));
                    break;
                case "22":
                    setAIText(btn22, String.valueOf(DecisionTree.aiCharacter));
                    break;
                default:
                    break;
            }
        }
        if(game.getWinner(game.getTable()) != ' ')
        {
            if(Game.getWinner(game.getTable()) == player)
            {
                JOptionPane.showMessageDialog(null, "AI Lost!!");
            }else
            {
                JOptionPane.showMessageDialog(null, "AI Won!!");
            }
            System.exit(1);
        }
    }

    private void btn00MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn00);
        play(gamePlayer.playAI(game, player, "00"));
    }

    private void btn01MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn01);
        play(gamePlayer.playAI(game, player, "01"));
    }

    private void btn02MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn02);
        play(gamePlayer.playAI(game, player, "02"));
    }

    private void btn10MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn10);
       play( gamePlayer.playAI(game, player, "10"));
    }

    private void btn11MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn11);
        play(gamePlayer.playAI(game, player, "11"));
    }

    private void btn12MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn12);
        play(gamePlayer.playAI(game, player, "12"));
    }

    private void btn20MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn20);
        play(gamePlayer.playAI(game, player, "20"));
    }

    private void btn21MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn21);
        play(gamePlayer.playAI(game, player, "21"));
    }

    private void btn22MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        setText(btn22);
        play(gamePlayer.playAI(game, player, "22"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify
    public static javax.swing.JButton btn00;
    public static javax.swing.JButton btn01;
    public static javax.swing.JButton btn02;
    public static javax.swing.JButton btn10;
    public static javax.swing.JButton btn11;
    public static javax.swing.JButton btn12;
    public static javax.swing.JButton btn20;
    public static javax.swing.JButton btn21;
    public static javax.swing.JButton btn22;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
}
