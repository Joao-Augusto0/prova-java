package viewers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.ProcessaManutencao;
import models.Manutencao;

public class FormManutencoes extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel id, data, equipamento, custo, tempo, rotulos;
	private JTextField tfId, tfdata, tfequipamento, tfcusto, tftempo;
	private JScrollPane rolagem;
	private JTextArea verResultados;
	private JButton create, read, update, delete;
	private String imgIco = "";
	private String[] imagens = { "", "" };
	private ImageIcon icon;
	private int autoId = ProcessaManutencao.manutencoes.size() + 1;
	private String texto = "";

	public FormManutencoes() {
		setTitle("Manutencao");
		setBounds(100, 100, 800, 600);
		setIconImage(new ImageIcon(imgIco).getImage());
		painel = new JPanel();
		painel.setBackground(new Color(0, 255, 127));
		setContentPane(painel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		id = new JLabel("Id:");
		id.setBounds(20, 20, 120, 30);
		painel.add(id);
		data = new JLabel("data:");
		data.setBounds(20, 55, 120, 30);
		painel.add(data);
		equipamento = new JLabel("equipamento:");
		equipamento.setBounds(20, 90, 120, 30);
		painel.add(equipamento);
		custo = new JLabel("custo:");
		custo.setBounds(20, 125, 120, 30);
		painel.add(custo);
		tempo = new JLabel("tempo:");
		tempo.setBounds(20, 165, 120, 30);
		painel.add(tempo);

		rotulos = new JLabel("Id |data | equipamento | custo | tempo:");
		rotulos.setBounds(20, 310, 500, 30);
		painel.add(rotulos);

		tfId = new JTextField(String.format("%d", autoId));
		tfId.setEditable(false);
		tfId.setBounds(140, 25, 140, 30);
		painel.add(tfId);

		tfdata = new JTextField();
		tfdata.setBounds(140, 60, 255, 30);
		painel.add(tfdata);

		tfequipamento = new JTextField();
		tfequipamento.setBounds(140, 92, 255, 30);
		painel.add(tfequipamento);

		tfcusto = new JTextField();
		tfcusto.setBounds(140, 125, 255, 30);
		painel.add(tfcusto);

		tftempo = new JTextField();
		tftempo.setBounds(140, 165, 255, 30);
		painel.add(tftempo);

		verResultados = new JTextArea();
		verResultados.setEditable(false);
		verResultados.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		preencherAreaDeTexto();
		rolagem = new JScrollPane(verResultados);
		rolagem.setBounds(20, 340, 740, 200);
		painel.add(rolagem);

//		imagem = new JLabel();
//		imagem.setBounds(405, 60, 350, 240);
//		imagem.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
//		alternarImagens(0);
//		painel.add(imagem);

		create = new JButton("Cadastrar");
		read = new JButton("Buscar");
		update = new JButton("Atualizar");
		delete = new JButton("Excluir");

		create.setBounds(285, 25, 110, 30);
		read.setBounds(405, 25, 110, 30);
		update.setBounds(525, 25, 110, 30);
		delete.setBounds(645, 25, 110, 30);

		update.setEnabled(false);
		delete.setEnabled(false);

		painel.add(create);
		painel.add(read);
		painel.add(update);
		painel.add(delete);

		create.addActionListener(this);
		read.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
	}

//	private void alternarImagens(int indice) {
//		icon = new ImageIcon(new ImageIcon(imagens[indice]).getImage().getScaledInstance(350, 240, 100));
//		imagem.setIcon(icon);
//	}

	private void cadastrar() {
		if (tfId.getText().length() != 0 && tfdata.getText().length() != 0 && tfequipamento.getText().length() != 0 && tfcusto.getText().length() != 0
				&& tftempo.getText().length() != 0) {

			ProcessaManutencao.manutencoes.add(new Manutencao(autoId, tfdata.getText(), tfequipamento.getText(),
					tfcusto.getText(), tftempo.getText()));
			autoId++;
			preencherAreaDeTexto();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
	}

	private void limparCampos() {
		tfdata.setText(null);
		tfequipamento.setText(null);
		tfcusto.setText(null);
		tftempo.setText(null);
	}

	private void preencherAreaDeTexto() {
		texto = ""; // Limpar a ?rea de texto antes de preenher
		for (Manutencao m : ProcessaManutencao.manutencoes) {
			texto += m.toString();
		}
		verResultados.setText(texto);
	}

	private void alterar() {
		int id = Integer.parseInt(tfId.getText());
		Manutencao m = new Manutencao(id, imgIco, imgIco, imgIco, imgIco);
		int indice = ProcessaManutencao.manutencoes.indexOf(m);
		if (tfdata.getText().length() != 0 && tfequipamento.getText().length() != 0 && tfcusto.getText().length() != 0
				&& tftempo.getText().length() != 0) {

			ProcessaManutencao.manutencoes.set(indice,
					new Manutencao(id, tfdata.getText(), tfequipamento.getText(), tftempo.getText()));
			preencherAreaDeTexto();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
		tfId.setText(String.format("%d", autoId));
		ProcessaManutencao.salvar();
	}

	private void excluir() {
		int id = Integer.parseInt(tfId.getText());
		Manutencao m = new Manutencao(id, imgIco, imgIco, imgIco, imgIco);
		int indice = ProcessaManutencao.manutencoes.indexOf(m);
		ProcessaManutencao.manutencoes.remove(indice);
		preencherAreaDeTexto();
		limparCampos();
		create.setEnabled(true);
		update.setEnabled(false);
		delete.setEnabled(false);
		tfId.setText(String.format("%d", autoId));
		ProcessaManutencao.salvar();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == create) {
			cadastrar();
		}

		if (e.getSource() == update) {
			alterar();
		}
		if (e.getSource() == delete) {
			excluir();
		}
	}
	
	public static void main(String[] agrs) throws ParseException {
		// PetProcess.carregarTestes();
		ProcessaManutencao.abrir();
		new FormManutencoes().setVisible(true);
	}
}