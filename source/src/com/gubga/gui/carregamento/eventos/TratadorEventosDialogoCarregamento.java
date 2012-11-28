package com.gubga.gui.carregamento.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.gubga.gui.Janela;
import com.gubga.gui.carregamento.DialogoAlternarConta;

public class TratadorEventosDialogoCarregamento extends MouseAdapter implements ActionListener {

	private DefaultTableModel modeloTabela;
	private DialogoAlternarConta dialogoAlternarConta;

	public TratadorEventosDialogoCarregamento(DialogoAlternarConta dialogoAlternarConta) {

		super();
		this.dialogoAlternarConta = dialogoAlternarConta;
	}

	public void actionPerformed(ActionEvent evento) {
		
		// Caso o evento tenha ocorrido no botao Diretorio.
		if (evento.getSource() == dialogoAlternarConta.getBotaoDiretorio()) {
			String diretorio = Janela.dialogoAbrirDiretorio(dialogoAlternarConta, "Selecione o Diret�rio da Pasta do Garena Plus.");
			if (diretorio != null)
				dialogoAlternarConta.getCampoTextoDiretorio().setText(diretorio);
			else
				dialogoAlternarConta.getCampoTextoDiretorio().setText("Diret�rio do Garena Plus.");
		}
		
		// Caso o evento tenha ocorrido no botao Sair.
		else if (evento.getSource() == dialogoAlternarConta.getBotaoSair()) {
			dialogoAlternarConta.dispose();
		}
		
		// Caso o evento tenha ocorrido no botao Limpar.
		else if (evento.getSource() == dialogoAlternarConta.getBotaoLimpar()) {

			modeloTabela = ((DefaultTableModel)(dialogoAlternarConta.getTabelaUsuarios().getModel()));
			modeloTabela.setNumRows(0);
		}
		
		// Caso o evento tenha ocorrido no botao Carregar.
		else if (evento.getSource() == dialogoAlternarConta.getBotaoCarregar()) {
			pesquisarUsuarios();
		}
	}

	private void pesquisarUsuarios() {
		String diretorio = dialogoAlternarConta.getCampoTextoDiretorio().getText();
		if (diretorio.equalsIgnoreCase("Diret�rio do Garena Plus."))
			JOptionPane.showMessageDialog(dialogoAlternarConta, "O Diret�rio do Garena N�o Foi Selecionado.", "Erro ao Abrir Diret�rio", JOptionPane.ERROR_MESSAGE);
		else {
			File d2 = new File(diretorio + "/room/user/");
			if (!d2.exists())
				JOptionPane.showMessageDialog(dialogoAlternarConta, "O Diret�rio Selecionado N�o � Um Diret�rio do Garena Plus.", "Erro de Diret�rio Inv�lido", JOptionPane.ERROR_MESSAGE);
			else {
				String userIds[] = d2.list();
				System.out.println(d2.getAbsolutePath());
				modeloTabela = ((DefaultTableModel)(dialogoAlternarConta.getTabelaUsuarios().getModel()));
				modeloTabela.setNumRows(0);
				Object[] linha = new Object[1];
				for (String user : userIds) {
					linha[0] = user;
					modeloTabela.addRow(linha);
				}
			}
		}
	}

	public void mouseClicked(MouseEvent evento) {
		if (evento.getClickCount() == 2 && evento.getSource() == dialogoAlternarConta.getTabelaUsuarios()) {
			int selecionado = dialogoAlternarConta.getTabelaUsuarios().getSelectedRow();
			String diretorio = dialogoAlternarConta.getCampoTextoDiretorio().getText();
			String usuario = dialogoAlternarConta.getTabelaUsuarios().getValueAt(selecionado, 0).toString();
			dialogoAlternarConta.getJanelaPrincipal().setPathUser(diretorio + "/room/user/" + usuario + "/ban.dat");
			dialogoAlternarConta.dispose();
		}
	}
}
