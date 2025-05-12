import java.awt.*;
import javax.swing.*;

public class EditarPergunta{

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Editar Pergunta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(null); // Layout absoluto para facilitar posicionamento

        // Título
        JLabel titulo = new JLabel("Edite uma pergunta");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(255, 191, 0));
        titulo.setBounds(140, 20, 300, 30);
        frame.add(titulo);

        // Subtítulo
        JLabel subtitulo = new JLabel("Escolha a matéria e selecione a pergunta que deseja editar");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setBounds(60, 60, 400, 20);
        frame.add(subtitulo);

        // ComboBox de Matéria
        JComboBox<String> materiaBox = new JComboBox<>(new String[] {"Matemática", "Português", "História"});
        materiaBox.setBounds(150, 100, 200, 40);
        materiaBox.setBackground(new Color(187, 48, 48));
        materiaBox.setForeground(Color.WHITE);
        materiaBox.setFont(new Font("SansSerif", Font.BOLD, 16));
        frame.add(materiaBox);

        // Botão Carregar Perguntas
        JButton carregarBtn = new JButton("Carregar Perguntas");
        carregarBtn.setBounds(150, 160, 200, 40);
        carregarBtn.setBackground(new Color(245, 166, 35));
        carregarBtn.setForeground(Color.WHITE);
        carregarBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        frame.add(carregarBtn);

        // Botão Salvar
        JButton salvarBtn = new JButton("Salvar");
        salvarBtn.setBounds(200, 240, 100, 40);
        salvarBtn.setBackground(new Color(32, 90, 255));
        salvarBtn.setForeground(Color.WHITE);
        salvarBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        frame.add(salvarBtn);

        // Estilizar fundo
        frame.getContentPane().setBackground(new Color(14, 24, 61));

        frame.setLocationRelativeTo(null); // Centralizar
        frame.setVisible(true);
    }
}

