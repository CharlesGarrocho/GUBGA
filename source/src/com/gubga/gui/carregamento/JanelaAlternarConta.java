package com.gubga.gui.carregamento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.gubga.gui.Janela;
import com.gubga.gui.banlist.JanelaBanList;
import com.gubga.gui.carregamento.eventos.TratadorEventosDialogoCarregamento;

public class JanelaAlternarConta extends Janela {

	private static final long serialVersionUID = 1L;
	private JanelaBanList janelaBanList;
	private JScrollPane scrollPane;
	private JTable tabelaUsuarios;
	private JTextField campoTextoDiretorio;
	private JButton botaoCarregar, botaoLimpar, botaoCancelar, botaoDiretorio;
	private JPanel painelNorte, painelCentro, painelSul, painelDiretorio;

	public JanelaAlternarConta(JanelaBanList janelaBanList) {
		super();
		this.janelaBanList = janelaBanList;
		criarElementos();
		customizarElementos();
		configurarEventos();
		adicionarElementos();
		definirPropriedades(janelaBanList, "Alternar Conta de Usuario", null);
	}

	@Override
	protected void adicionarElementos() {
		
		painelDiretorio.add(campoTextoDiretorio);
		painelDiretorio.add(botaoDiretorio);
		
		painelNorte.add(painelDiretorio);
		
		painelCentro.add(scrollPane);
		
		painelSul.add(botaoCarregar);
		painelSul.add(botaoLimpar);
		painelSul.add(botaoCancelar);
		
		JPanel bgPanel = new Painel(new ImageIcon(getResource("teste.jpeg")));
        bgPanel.setLayout(new BorderLayout());

        bgPanel.add(painelNorte, BorderLayout.NORTH);
        bgPanel.add(painelCentro, BorderLayout.CENTER);
        bgPanel.add(painelSul, BorderLayout.SOUTH);
        
        setContentPane(bgPanel);
	}

	@Override
	protected void configurarEventos() {
		TratadorEventosDialogoCarregamento tratadorEventos = new TratadorEventosDialogoCarregamento(this);
		botaoDiretorio.addActionListener(tratadorEventos);
		botaoCancelar.addActionListener(tratadorEventos);
		botaoCarregar.addActionListener(tratadorEventos);
		botaoLimpar.addActionListener(tratadorEventos);
		tabelaUsuarios.addMouseListener(tratadorEventos);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				getThis().dispose();
			}
		});
	}

	@Override
	protected void criarElementos() {
		painelNorte = new JPanel();
		painelCentro = new JPanel();
		painelSul = new JPanel();
		painelDiretorio = new JPanel();
		
		campoTextoDiretorio = new JTextField(29);
		
		botaoCarregar = new JButton(new ImageIcon(Janela.getResource("popular.png")));
		botaoCancelar = new JButton(new ImageIcon(Janela.getResource("cancelar.png")));
		botaoDiretorio = new JButton(new ImageIcon(Janela.getResource("abrir.png")));
		botaoLimpar = new JButton(new ImageIcon(Janela.getResource("limpar.png")));
		
		String colunasTabela[] = {"Selecione Abaixo uma Conta do Garena Plus"},
		dadosTabela[][] = new String[0][0];

		tabelaUsuarios = new JTable(new DefaultTableModel(dadosTabela, colunasTabela)){
			private static final long serialVersionUID = 5727320816550514929L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
				if (colIndex == getColumn("Selecione Abaixo uma Conta do Garena Plus").getModelIndex()){
					return false;
				}
				else
					return true;
			};
		};
		scrollPane = new JScrollPane(tabelaUsuarios);
	}

	@Override
	protected void customizarElementos() {
		
		painelNorte.setOpaque(false);
		painelCentro.setOpaque(false);
		painelSul.setOpaque(false);
		painelDiretorio.setOpaque(false);
		
		painelNorte.setLayout(new FlowLayout());
		painelCentro.setLayout(new FlowLayout());
		painelSul.setLayout(new FlowLayout());
		painelDiretorio.setLayout(new FlowLayout());
		
		campoTextoDiretorio.setText("Diret�rio do Garena Plus.");
		
		campoTextoDiretorio.setPreferredSize(new Dimension(110, 28));
		campoTextoDiretorio.setEditable(false);

		tabelaUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botaoCarregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botaoCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botaoDiretorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botaoLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		tabelaUsuarios.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		tabelaUsuarios.setSelectionBackground(Color.BLACK);
		tabelaUsuarios.setSelectionForeground(Color.GREEN);
		tabelaUsuarios.setFocusable(false);
		
		scrollPane.setPreferredSize(new Dimension(315, 280));
		scrollPane.setBackground(new Color(0,0,0,0));
		scrollPane.setFocusable(false);
		
		tabelaUsuarios.setDragEnabled(false);
		tabelaUsuarios.getTableHeader().setReorderingAllowed(false);
		tabelaUsuarios.setFont(new Font("Tahoma",Font.PLAIN,12));

		JTableHeader header = tabelaUsuarios.getTableHeader();
		header.setResizingAllowed(false);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		botaoCarregar.setText("Carregar    ");
		botaoCancelar.setText("Cancelar    ");
		botaoLimpar.setText("Limpar    ");
		
		botaoCarregar.setBorder(null);
		botaoCancelar.setBorder(null);
		botaoLimpar.setBorder(null);
		
		botaoDiretorio.setToolTipText("Selecione o Diret�rio da Pasta do Garena Plus.");
		botaoCarregar.setToolTipText("Carregar Contas Existentes da Pasta do Garena Plus.");
		botaoCancelar.setToolTipText("Sair do GUBGA.");
		botaoLimpar.setToolTipText("Limpar Tabela de Usu�rios do Garena Plus.");
		tabelaUsuarios.setToolTipText("D� um Duplo Clique na Conta a Ser Utilizada.");
	}

	public JanelaAlternarConta getThis() {
		return this;
	}

	public JTable getTabelaUsuarios() {
		return tabelaUsuarios;
	}

	public JTextField getCampoTextoDiretorio() {
		return campoTextoDiretorio;
	}

	public JButton getBotaoCarregar() {
		return botaoCarregar;
	}

	public JButton getBotaoLimpar() {
		return botaoLimpar;
	}

	public JButton getBotaoSair() {
		return botaoCancelar;
	}

	public JButton getBotaoDiretorio() {
		return botaoDiretorio;
	}

	public JanelaBanList getJanelaPrincipal() {
		return janelaBanList;
	}
}

class Painel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image bg;
	
	public Painel(ImageIcon imagem) {
		this.bg = imagem.getImage();
	}
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}