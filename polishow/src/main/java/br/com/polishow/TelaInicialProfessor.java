package br.com.polishow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaInicialProfessor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicialProfessor().criarTela());
    }

    public void criarTela() {
        JFrame frame = new JFrame("Tela Inicial do Professor");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        

        frame.setVisible(true);
    }
}

