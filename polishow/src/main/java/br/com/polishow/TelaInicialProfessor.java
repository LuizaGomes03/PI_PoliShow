package br.com.polishow;

import javax.swing.*;
import java.awt.*;
// feito até agora apenas para testar o botão voltar, pode mudar totalmente
public class TelaInicialProfessor {

    public void criarTela() {
        JFrame frame = new JFrame("Tela Inicial do Professor");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Bem-vindo, Professor!");
        label.setBounds(250, 250, 300, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(label);

        frame.setVisible(true);
    }
}
