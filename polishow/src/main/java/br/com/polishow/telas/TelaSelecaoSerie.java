package br.com.polishow.telas;

import javax.swing.*;
import java.awt.*;

public class TelaSelecaoSerie extends JDialog {

    public TelaSelecaoSerie(JFrame parent) {
        super(parent, "Selecione a Série", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btn1 = new JButton("1ª Série");
        JButton btn2 = new JButton("2ª Série");
        JButton btn3 = new JButton("3ª Série");

        btn1.addActionListener(e -> {
            new TelaDesempenhoAluno(parent, "1").setVisible(true);
            dispose();
        });
        btn2.addActionListener(e -> {
            new TelaDesempenhoAluno(parent, "2").setVisible(true);
            dispose();
        });
        btn3.addActionListener(e -> {
            new TelaDesempenhoAluno(parent, "3").setVisible(true);
            dispose();
        });

        add(btn1);
        add(btn2);
        add(btn3);
    }
}
