/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author Wagner
 * 
 */
package Tela;

import Lexico.AnalizadorLexico;
import Models.Token;
import Sintatico.AnalizadorSintatico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.Color;
import java.awt.Font;

public class EditorCompilador extends javax.swing.JFrame {

    Token token = new Token();
    Stack<Token> lista = new Stack<Token>();
    ImageIcon icon = new ImageIcon("@stack.png");
    private DefaultTableModel modeloTable;
    public int resultadoJanelas;
    public String EnderecoArquivo, EnderecoTemporario, bufferIn;
    public String data;

    public void setTitle(String title) {
        super.setTitle("Compilador");

    }

    public EditorCompilador() {
        ImageIcon img = new ImageIcon("img/compilador.png");
        initComponents();
        setLocationRelativeTo(null);
        getListaMunuAtt(lista);
        setResizable(false);
        setIconImage(img.getImage());
        // Area_Texto.setForeground(Color.BLUE);
        Area_Texto.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jScrollPane2 = new javax.swing.JScrollPane();
        Area_Texto = new javax.swing.JTextArea();
        Nome_Programa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLbResult = new javax.swing.JLabel();
        SairIcon = new javax.swing.JButton();
        AbrirIcon = new javax.swing.JButton();
        SalvarIcon = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();
        Analisar = new javax.swing.JButton();
        NewDoc = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextConsole = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Abrir = new javax.swing.JCheckBoxMenuItem();
        Salvar = new javax.swing.JMenuItem();
        LogOut = new javax.swing.JMenu();
        Sair = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spear");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Area_Texto.setColumns(20);
        Area_Texto.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        Area_Texto.setRows(20);
        Area_Texto.setTabSize(4);
        // Area_Texto.setText(
        // "Program testeproc1;\n Var\n X, y, z :integer; \n Procedure P;\n Var\n A
        // :integer;\n Begin\n Readln(a);\n If a=x then\n z:=z+x\n Else begin\n
        // Z:=z-x;\n Call p;\n End;\n End;\n Begin\n Z:=0;\n Readln(x,y);\n If x>y
        // then\n Call p\n Else\n Z:=z+x+y;\n Writeln(z);\nEnd.");
        Area_Texto.setToolTipText("");
        Area_Texto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Area_Texto.setText("");
        jScrollPane2.setViewportView(Area_Texto);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 450, 450));

        Nome_Programa.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Nome_Programa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Nome_Programa.setText("Compilador Unesc");
        getContentPane().add(Nome_Programa, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 280, 30));

        Table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null }
                },
                new String[] {
                        "CÓDIGO", "TOKEN", "LINHA"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        Table.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(Table);
        Table.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 300, 450));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 260, 50));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, -1, 20));

        jLbResult.setText("Console");
        getContentPane().add(jLbResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 80, 20));

        SairIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tela/icons8-log-out-24.png"))); // NOI18N
        SairIcon.setAutoscrolls(true);
        SairIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SairIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SairIcon.setToolTipText("Sair do programa");
        SairIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairIconActionPerformed(evt);
            }
        });
        getContentPane().add(SairIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 40, 40));

        AbrirIcon
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tela/icons8-open-file-folder-24 (1).png"))); // NOI18N
        AbrirIcon.setAutoscrolls(true);
        AbrirIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AbrirIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AbrirIcon.setToolTipText("Abrir arquivo");
        AbrirIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirIconActionPerformed(evt);
            }
        });
        getContentPane().add(AbrirIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 40));

        SalvarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tela/icons8-salvar-24.png"))); // NOI18N
        SalvarIcon.setAutoscrolls(true);
        SalvarIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SalvarIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SalvarIcon.setToolTipText("Salvar arquivo");
        SalvarIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarIconActionPerformed(evt);
            }
        });
        getContentPane().add(SalvarIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 40, 40));

        Limpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tela/icons8-cross-mark-button-24.png"))); // NOI18N
        Limpar.setAutoscrolls(true);
        Limpar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Limpar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Limpar.setToolTipText("Limpar arquivo");
        Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimparActionPerformed(evt);
            }
        });
        getContentPane().add(Limpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 40, 40));

        Analisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tela/icons8-play-24.png"))); // NOI18N
        Analisar.setAutoscrolls(true);
        Analisar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Analisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Analisar.setToolTipText("Analisar arquivo");
        Analisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnalisarActionPerformed(evt);
            }
        });
        getContentPane().add(Analisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 40, 40));

        NewDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tela/icons8-documento-24.png"))); // NOI18N
        NewDoc.setAutoscrolls(true);
        NewDoc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NewDoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewDoc.setToolTipText("Novo arquivo");
        NewDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewDocActionPerformed(evt);
            }
        });
        getContentPane().add(NewDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 40, 40));

        TextConsole.setColumns(20);
        TextConsole.setRows(5);
        TextConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextConsole.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TextConsoleAncestorAdded(evt);
            }

            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }

            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane3.setViewportView(TextConsole);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 800, 110));

        jMenu1.setText("Arquivo");

        Abrir.setSelected(false);
        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        jMenu1.add(Abrir);

        Salvar.setText("Salvar");
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });
        jMenu1.add(Salvar);

        jMenuBar1.add(jMenu1);

        LogOut.setText("Sair");

        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });
        LogOut.add(Sair);

        jMenuBar1.add(LogOut);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SairActionPerformed
        Fechar();

    }// GEN-LAST:event_SairActionPerformed

    private void SairIconActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SairIconActionPerformed
        Fechar();
    }// GEN-LAST:event_SairIconActionPerformed

    private void AbrirIconActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_AbrirIconActionPerformed

        String message = "Tem certeza?\nEssa ação vai limpar a Tela e a Tabela do Analisador?";
        String title = "Confirmação";
        int resposta = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            ((DefaultTableModel) Table.getModel()).setNumRows(0);
            Table.updateUI();
            Area_Texto.setText("");

            JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(openFile);
            resultadoJanelas = JFileChooser.OPEN_DIALOG;

            if (JFileChooser.APPROVE_OPTION == resultadoJanelas) {
                EnderecoArquivo = openFile.getSelectedFile().toString();
                AbrirArquivo();
            }

        }
    }// GEN-LAST:event_AbrirIconActionPerformed

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SalvarActionPerformed

        JFileChooser saveFile = new JFileChooser();
        saveFile.showSaveDialog(saveFile);
        resultadoJanelas = JFileChooser.OPEN_DIALOG;

        if (JFileChooser.APPROVE_OPTION == resultadoJanelas) {
            EnderecoArquivo = saveFile.getSelectedFile().toString();
            SalvarArquivo();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

            TextConsole.setForeground(Color.GREEN);
            TextConsole.append("Arquivo salvo com sucesso!\n");
        }
    }// GEN-LAST:event_SalvarActionPerformed

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_AbrirActionPerformed

        String message = "Tem certeza?\nEssa ação vai limpar a Tela e a Tabela do Analisador?";
        String title = "Confirmação";
        int resposta = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            ((DefaultTableModel) Table.getModel()).setNumRows(0);
            Table.updateUI();
            Area_Texto.setText("");

            JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(openFile);
            resultadoJanelas = JFileChooser.OPEN_DIALOG;

            if (JFileChooser.APPROVE_OPTION == resultadoJanelas) {
                EnderecoArquivo = openFile.getSelectedFile().toString();
                AbrirArquivo();
            }

        }

    }// GEN-LAST:event_AbrirActionPerformed

    private void SalvarIconActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SalvarIconActionPerformed

        JFileChooser saveFile = new JFileChooser();
        saveFile.showSaveDialog(saveFile);
        resultadoJanelas = JFileChooser.OPEN_DIALOG;

        if (JFileChooser.APPROVE_OPTION == resultadoJanelas) {
            EnderecoArquivo = saveFile.getSelectedFile().toString();
            SalvarArquivo();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

            TextConsole.setForeground(Color.GREEN);
            TextConsole.append("Arquivo salvo com sucesso!\n");
        }
    }// GEN-LAST:event_SalvarIconActionPerformed

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_LimparActionPerformed

        String message = "Tem certeza que deseja Limpar o texto e a Tabela do analisador?";
        String title = "Confirmação";
        int resposta = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            ((DefaultTableModel) Table.getModel()).setNumRows(0);
            Table.updateUI();

            Area_Texto.setText("");

            TextConsole.setForeground(Color.GREEN);
            TextConsole.append("Compilador clear!\n");
        }
    }// GEN-LAST:event_LimparActionPerformed

    private void AnalisarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_AnalisarActionPerformed

        // TextConsole.append("Programa inicializado\n");
        lista.clear();
        getListaMunuAtt(lista);
        String texto = String.valueOf(Area_Texto.getText());
        AnalizadorLexico cc = new AnalizadorLexico();

        String[] FullText = texto.split("\n");

        List<String> linhas = new ArrayList<String>();

        for (String line : FullText) {
            linhas.add(line);
        }
        // lista = cc.getPalavra(texto);
        lista = cc.getPalavra(linhas);

        if (cc.erroLexico == true) {
            TextConsole.setForeground(Color.RED);

            TextConsole.append("Ocorreu um Erro lexico verifique!\n");
        } else {
            TextConsole.setForeground(Color.GREEN);
            // TextConsole.append("Compilado com Sucesso!\n");
        }

        getListaMunuAtt(lista);
        new AnalizadorSintatico().analisar(lista);

    }// GEN-LAST:event_AnalisarActionPerformed

    private void NewDocActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NewDocActionPerformed
        String message = "Ao criar um novo arquivo será eliminado o texto e a Tabela do analisador.\nTem certeza que deseja efetuar essa oeração?\nCertifique-se de gravar o arquivo atual antes";
        String title = "Confirmação";
        int resposta = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            ((DefaultTableModel) Table.getModel()).setNumRows(0);
            Table.updateUI();

            Area_Texto.setText("");
            TextConsole.setForeground(Color.GREEN);
            TextConsole.append("Novo arquivo criado com sucesso!\n");
        }
    }// GEN-LAST:event_NewDocActionPerformed

    private void TextConsoleAncestorAdded(javax.swing.event.AncestorEvent evt) {// GEN-FIRST:event_TextConsoleAncestorAdded
        // TODO add your handling code here:
    }// GEN-LAST:event_TextConsoleAncestorAdded

    public void getListaMunuAtt(Stack<Token> lista) {

        modeloTable = (DefaultTableModel) Table.getModel();
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }

        for (int i = 0; i < lista.size(); i++) {
            Token tkn = new Token();
            tkn = (Token) lista.get(i);
            modeloTable.addRow(new Object[] { tkn.getCodigo(), tkn.getNome(), tkn.getLinha() });
        }
        Table.setModel(modeloTable);
    }

    private void AbrirArquivo() {
        try {
            FileReader fileReader = new FileReader(EnderecoArquivo);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((data = reader.readLine()) != null) {
                Area_Texto.append(data + "\n");
            }
            setTitle(EnderecoArquivo);
            fileReader.close();
            reader.close();

            TextConsole.setForeground(Color.GREEN);
            TextConsole.append("Arquivo aberto com sucesso!\n");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
            TextConsole.setForeground(Color.GREEN);
            TextConsole.append("Erro ao abrir o arquivo!\n");
        }
    }

    private void Fechar() {
        String message = "Tem certeza que deseja sair do analisador?";
        String title = "Confirmação";
        int resposta = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            TextConsole.setForeground(Color.GREEN);
            TextConsole.append("Compilador encerrado\n");
            System.exit(0);
        }

    }

    private void SalvarArquivo() {
        try {
            FileWriter escrever;
            escrever = new FileWriter(new File(EnderecoArquivo) + ".txt");
            escrever.write(Area_Texto.getText());
            escrever.close();
            TextConsole.setForeground(Color.GREEN);
            // TextConsole.append("Arquivo salvo com sucesso!\n");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("Não foi possível alterar o LookAndFeel");
            e.printStackTrace();
        }

        new EditorCompilador().setVisible(true); // Create and show the GUI.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem Abrir;
    private javax.swing.JButton AbrirIcon;
    private javax.swing.JButton Analisar;
    private javax.swing.JTextArea Area_Texto;
    private javax.swing.JButton Limpar;
    private javax.swing.JMenu LogOut;
    private javax.swing.JButton NewDoc;
    private javax.swing.JLabel Nome_Programa;
    private javax.swing.JMenuItem Sair;
    private javax.swing.JButton SairIcon;
    private javax.swing.JMenuItem Salvar;
    private javax.swing.JButton SalvarIcon;
    private javax.swing.JTable Table;
    private javax.swing.JTextArea TextConsole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLbResult;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}
