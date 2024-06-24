package laboratoriotresjava;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.util.*;
import javax.swing.JPanel;

public class VentanaSecundaria extends javax.swing.JDialog {

    public VentanaSecundaria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        randomizarColores();
        posicionarRobotInicial();
        setLocationRelativeTo(null);
        PanelMatriz.setFocusable(true); // Permitir que el panel obtenga el foco
        PanelMatriz.requestFocusInWindow(); // Solicitar el foco para el panel

        PanelMatriz.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanelMatrizKeyPressed(evt);
            }
        });
    }
    List<JLabel> labels = new ArrayList<>();
    ArrayList<JLabel> colorBlanco = new ArrayList();
    ArrayList<JLabel> colorRojo = new ArrayList();
    ArrayList<JLabel> colorVerde = new ArrayList();

    private final Color[] colores = {Color.WHITE, Color.RED, Color.GREEN};

  private void posicionarRobotInicial() {
        if (Robot.getParent() != null) {
            Robot.getParent().remove(Robot);
        }

        PanelMatriz.setLayout(null);
        PanelMatriz.add(Robot);

        // Inicializar el robot en (0,0)
        JLabel initialLabel = getLabelAt(0, 0);
        if (initialLabel != null) {
            Robot.setBounds(initialLabel.getBounds());
        }

        Robot.setVisible(true);
        PanelMatriz.setComponentZOrder(Robot, 0);
        PanelMatriz.revalidate();
        PanelMatriz.repaint();
    }

    private void randomizarColores() {
        Random rand = new Random();
        labels.clear();
        colorBlanco.clear();
        colorRojo.clear();
        colorVerde.clear();

        for (java.awt.Component comp : PanelMatriz.getComponents()) {
            if (comp instanceof JLabel) {
                labels.add((JLabel) comp);
            }
        }

        // Pinta la celda (0,0) de azul
        JLabel firstLabel = getLabelAt(0, 0);
        if (firstLabel != null) {
            firstLabel.setBackground(Color.BLUE);
        }

        int totalLabels = labels.size();
        int greenLabels = totalLabels * 50 / 100;
        int redLabels = totalLabels * 15 / 100;
        int whiteLabels = totalLabels * 35 / 100;

        Collections.shuffle(labels);

        int greenCount = 0;
        int redCount = 0;
        int whiteCount = 0;

        for (JLabel label : labels) {
            // Salta la celda (0,0)
            if (label == firstLabel) continue;

            if (greenCount < greenLabels) {
                label.setBackground(Color.GREEN);
                greenCount++;
                colorVerde.add(label);
            } else if (redCount < redLabels) {
                label.setBackground(Color.RED);
                redCount++;
                colorRojo.add(label);
            } else if (whiteCount < whiteLabels) {
                label.setBackground(Color.WHITE);
                whiteCount++;
                colorBlanco.add(label);
            } else {
                int randomIndex = rand.nextInt(colores.length);
                label.setBackground(colores[randomIndex]);

                if (colores[randomIndex] == Color.GREEN) {
                    colorVerde.add(label);
                } else if (colores[randomIndex] == Color.RED) {
                    colorRojo.add(label);
                } else if (colores[randomIndex] == Color.WHITE) {
                    colorBlanco.add(label);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        PanelFila = new javax.swing.JPanel();
        lblNumeracion1 = new javax.swing.JLabel();
        lblNumeracion3 = new javax.swing.JLabel();
        lblNumeracion4 = new javax.swing.JLabel();
        lblNumeracion5 = new javax.swing.JLabel();
        lblNumeracion6 = new javax.swing.JLabel();
        lblNumeracion8 = new javax.swing.JLabel();
        lblNumeracion9 = new javax.swing.JLabel();
        lblNumeracion10 = new javax.swing.JLabel();
        PanelColumna = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PanelMatriz = new javax.swing.JPanel();
        SieteCero = new javax.swing.JLabel();
        UnoCuatro = new javax.swing.JLabel();
        SeisCero = new javax.swing.JLabel();
        CincoCero = new javax.swing.JLabel();
        CuatroCero = new javax.swing.JLabel();
        UnoCero = new javax.swing.JLabel();
        TresCero = new javax.swing.JLabel();
        DosDos = new javax.swing.JLabel();
        UnoSeis = new javax.swing.JLabel();
        UnoCinco = new javax.swing.JLabel();
        DosUno = new javax.swing.JLabel();
        UnoSiete = new javax.swing.JLabel();
        DosCuatro = new javax.swing.JLabel();
        DosTres = new javax.swing.JLabel();
        DosSeis = new javax.swing.JLabel();
        DosCinco = new javax.swing.JLabel();
        TresDos = new javax.swing.JLabel();
        DosSiete = new javax.swing.JLabel();
        CeroCero = new javax.swing.JLabel();
        CeroTres = new javax.swing.JLabel();
        TresUno = new javax.swing.JLabel();
        TresCuatro = new javax.swing.JLabel();
        TresTres = new javax.swing.JLabel();
        CuatroDos = new javax.swing.JLabel();
        TresSeis = new javax.swing.JLabel();
        TresCinco = new javax.swing.JLabel();
        CuatroUno = new javax.swing.JLabel();
        CeroSiete = new javax.swing.JLabel();
        TresSiete = new javax.swing.JLabel();
        CuatroCuatro = new javax.swing.JLabel();
        CuatroTres = new javax.swing.JLabel();
        CincoDos = new javax.swing.JLabel();
        CuatroSeis = new javax.swing.JLabel();
        CuatroCinco = new javax.swing.JLabel();
        CincoUno = new javax.swing.JLabel();
        CuatroSiete = new javax.swing.JLabel();
        DosCero = new javax.swing.JLabel();
        CincoCuatro = new javax.swing.JLabel();
        CincoTres = new javax.swing.JLabel();
        UnoTres = new javax.swing.JLabel();
        SeisDos = new javax.swing.JLabel();
        CincoSeis = new javax.swing.JLabel();
        CincoCinco = new javax.swing.JLabel();
        SeisUno = new javax.swing.JLabel();
        CincoSiete = new javax.swing.JLabel();
        CeroCuatro = new javax.swing.JLabel();
        CeroSeis = new javax.swing.JLabel();
        SeisCuatro = new javax.swing.JLabel();
        SeisTres = new javax.swing.JLabel();
        SeisSeis = new javax.swing.JLabel();
        SeisCinco = new javax.swing.JLabel();
        SieteDos = new javax.swing.JLabel();
        SieteUno = new javax.swing.JLabel();
        SieteCuatro = new javax.swing.JLabel();
        SeisSiete = new javax.swing.JLabel();
        CeroCinco = new javax.swing.JLabel();
        SieteTres = new javax.swing.JLabel();
        SieteSeis = new javax.swing.JLabel();
        SieteCinco = new javax.swing.JLabel();
        CeroDos = new javax.swing.JLabel();
        SieteSiete = new javax.swing.JLabel();
        CeroUno = new javax.swing.JLabel();
        UnoUno = new javax.swing.JLabel();
        UnoDos = new javax.swing.JLabel();
        PanelArriba = new javax.swing.JPanel();
        lblInformacionMovimiento = new javax.swing.JLabel();
        PanelDerecha = new javax.swing.JPanel();
        PosicionRecorridas = new javax.swing.JLabel();
        lblPosicionesLimpiadas = new javax.swing.JLabel();
        lblPosicionDelRobot = new javax.swing.JLabel();
        btnReiniciarMatriz = new javax.swing.JButton();
        lblNoSePudoMoverPor = new javax.swing.JLabel();
        lblXYContador = new javax.swing.JLabel();
        lblPosicionRecorrida = new javax.swing.JLabel();
        lblPosicionLimpiada = new javax.swing.JLabel();
        Robot = new javax.swing.JLabel();
        lblMostrarRazonNoSeMueve = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        PanelFila.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNumeracion1.setText("0");
        PanelFila.add(lblNumeracion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 55, 30));

        lblNumeracion3.setText("1");
        PanelFila.add(lblNumeracion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 55, 30));

        lblNumeracion4.setText("2");
        PanelFila.add(lblNumeracion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 55, 30));

        lblNumeracion5.setText("3");
        PanelFila.add(lblNumeracion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 55, 30));

        lblNumeracion6.setText("4");
        PanelFila.add(lblNumeracion6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 55, 30));

        lblNumeracion8.setText("5");
        PanelFila.add(lblNumeracion8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 55, 30));

        lblNumeracion9.setText("7");
        PanelFila.add(lblNumeracion9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 40, 30));

        lblNumeracion10.setText("6");
        PanelFila.add(lblNumeracion10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 55, 30));

        PanelColumna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("0");
        PanelColumna.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 18, -1));

        jLabel2.setText("1");
        PanelColumna.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setText("7");
        PanelColumna.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 10, 20));

        jLabel4.setText("2");
        PanelColumna.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 10, 20));

        jLabel5.setText("3");
        PanelColumna.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 10, 20));

        jLabel6.setText("4");
        PanelColumna.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 10, 20));

        jLabel7.setText("5");
        PanelColumna.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 10, 20));

        jLabel8.setText("6");
        PanelColumna.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 10, 20));

        PanelMatriz.setLayout(new java.awt.GridLayout(8, 8));

        SieteCero.setBackground(new java.awt.Color(153, 153, 153));
        SieteCero.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCero.setOpaque(true);
        PanelMatriz.add(SieteCero);

        UnoCuatro.setBackground(new java.awt.Color(153, 153, 153));
        UnoCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCuatro.setOpaque(true);
        PanelMatriz.add(UnoCuatro);

        SeisCero.setBackground(new java.awt.Color(153, 153, 153));
        SeisCero.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCero.setOpaque(true);
        PanelMatriz.add(SeisCero);

        CincoCero.setBackground(new java.awt.Color(153, 153, 153));
        CincoCero.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCero.setOpaque(true);
        PanelMatriz.add(CincoCero);

        CuatroCero.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCero.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCero.setOpaque(true);
        PanelMatriz.add(CuatroCero);

        UnoCero.setBackground(new java.awt.Color(153, 153, 153));
        UnoCero.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCero.setOpaque(true);
        PanelMatriz.add(UnoCero);

        TresCero.setBackground(new java.awt.Color(153, 153, 153));
        TresCero.setBorder(new javax.swing.border.MatteBorder(null));
        TresCero.setOpaque(true);
        PanelMatriz.add(TresCero);

        DosDos.setBackground(new java.awt.Color(153, 153, 153));
        DosDos.setBorder(new javax.swing.border.MatteBorder(null));
        DosDos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosDos.setOpaque(true);
        PanelMatriz.add(DosDos);

        UnoSeis.setBackground(new java.awt.Color(153, 153, 153));
        UnoSeis.setBorder(new javax.swing.border.MatteBorder(null));
        UnoSeis.setOpaque(true);
        PanelMatriz.add(UnoSeis);

        UnoCinco.setBackground(new java.awt.Color(153, 153, 153));
        UnoCinco.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCinco.setOpaque(true);
        PanelMatriz.add(UnoCinco);

        DosUno.setBackground(new java.awt.Color(153, 153, 153));
        DosUno.setBorder(new javax.swing.border.MatteBorder(null));
        DosUno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosUno.setOpaque(true);
        PanelMatriz.add(DosUno);

        UnoSiete.setBackground(new java.awt.Color(153, 153, 153));
        UnoSiete.setBorder(new javax.swing.border.MatteBorder(null));
        UnoSiete.setOpaque(true);
        PanelMatriz.add(UnoSiete);

        DosCuatro.setBackground(new java.awt.Color(153, 153, 153));
        DosCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        DosCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosCuatro.setOpaque(true);
        PanelMatriz.add(DosCuatro);

        DosTres.setBackground(new java.awt.Color(153, 153, 153));
        DosTres.setBorder(new javax.swing.border.MatteBorder(null));
        DosTres.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosTres.setOpaque(true);
        PanelMatriz.add(DosTres);

        DosSeis.setBackground(new java.awt.Color(153, 153, 153));
        DosSeis.setBorder(new javax.swing.border.MatteBorder(null));
        DosSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosSeis.setOpaque(true);
        PanelMatriz.add(DosSeis);

        DosCinco.setBackground(new java.awt.Color(153, 153, 153));
        DosCinco.setBorder(new javax.swing.border.MatteBorder(null));
        DosCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosCinco.setOpaque(true);
        PanelMatriz.add(DosCinco);

        TresDos.setBackground(new java.awt.Color(153, 153, 153));
        TresDos.setBorder(new javax.swing.border.MatteBorder(null));
        TresDos.setOpaque(true);
        PanelMatriz.add(TresDos);

        DosSiete.setBackground(new java.awt.Color(153, 153, 153));
        DosSiete.setBorder(new javax.swing.border.MatteBorder(null));
        DosSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosSiete.setOpaque(true);
        PanelMatriz.add(DosSiete);

        CeroCero.setBackground(new java.awt.Color(153, 153, 153));
        CeroCero.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCero.setOpaque(true);
        PanelMatriz.add(CeroCero);

        CeroTres.setBackground(new java.awt.Color(153, 153, 153));
        CeroTres.setBorder(new javax.swing.border.MatteBorder(null));
        CeroTres.setOpaque(true);
        PanelMatriz.add(CeroTres);

        TresUno.setBackground(new java.awt.Color(153, 153, 153));
        TresUno.setBorder(new javax.swing.border.MatteBorder(null));
        TresUno.setOpaque(true);
        PanelMatriz.add(TresUno);

        TresCuatro.setBackground(new java.awt.Color(153, 153, 153));
        TresCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        TresCuatro.setOpaque(true);
        PanelMatriz.add(TresCuatro);

        TresTres.setBackground(new java.awt.Color(153, 153, 153));
        TresTres.setBorder(new javax.swing.border.MatteBorder(null));
        TresTres.setOpaque(true);
        PanelMatriz.add(TresTres);

        CuatroDos.setBackground(new java.awt.Color(153, 153, 153));
        CuatroDos.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroDos.setOpaque(true);
        PanelMatriz.add(CuatroDos);

        TresSeis.setBackground(new java.awt.Color(153, 153, 153));
        TresSeis.setBorder(new javax.swing.border.MatteBorder(null));
        TresSeis.setOpaque(true);
        PanelMatriz.add(TresSeis);

        TresCinco.setBackground(new java.awt.Color(153, 153, 153));
        TresCinco.setBorder(new javax.swing.border.MatteBorder(null));
        TresCinco.setOpaque(true);
        PanelMatriz.add(TresCinco);

        CuatroUno.setBackground(new java.awt.Color(153, 153, 153));
        CuatroUno.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroUno.setOpaque(true);
        PanelMatriz.add(CuatroUno);

        CeroSiete.setBackground(new java.awt.Color(153, 153, 153));
        CeroSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CeroSiete.setOpaque(true);
        PanelMatriz.add(CeroSiete);

        TresSiete.setBackground(new java.awt.Color(153, 153, 153));
        TresSiete.setBorder(new javax.swing.border.MatteBorder(null));
        TresSiete.setOpaque(true);
        PanelMatriz.add(TresSiete);

        CuatroCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCuatro.setOpaque(true);
        PanelMatriz.add(CuatroCuatro);

        CuatroTres.setBackground(new java.awt.Color(153, 153, 153));
        CuatroTres.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroTres.setOpaque(true);
        PanelMatriz.add(CuatroTres);

        CincoDos.setBackground(new java.awt.Color(153, 153, 153));
        CincoDos.setBorder(new javax.swing.border.MatteBorder(null));
        CincoDos.setOpaque(true);
        PanelMatriz.add(CincoDos);

        CuatroSeis.setBackground(new java.awt.Color(153, 153, 153));
        CuatroSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroSeis.setOpaque(true);
        PanelMatriz.add(CuatroSeis);

        CuatroCinco.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCinco.setOpaque(true);
        PanelMatriz.add(CuatroCinco);

        CincoUno.setBackground(new java.awt.Color(153, 153, 153));
        CincoUno.setBorder(new javax.swing.border.MatteBorder(null));
        CincoUno.setOpaque(true);
        PanelMatriz.add(CincoUno);

        CuatroSiete.setBackground(new java.awt.Color(153, 153, 153));
        CuatroSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroSiete.setOpaque(true);
        PanelMatriz.add(CuatroSiete);

        DosCero.setBackground(new java.awt.Color(153, 153, 153));
        DosCero.setBorder(new javax.swing.border.MatteBorder(null));
        DosCero.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosCero.setOpaque(true);
        PanelMatriz.add(DosCero);

        CincoCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CincoCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCuatro.setOpaque(true);
        PanelMatriz.add(CincoCuatro);

        CincoTres.setBackground(new java.awt.Color(153, 153, 153));
        CincoTres.setBorder(new javax.swing.border.MatteBorder(null));
        CincoTres.setOpaque(true);
        PanelMatriz.add(CincoTres);

        UnoTres.setBackground(new java.awt.Color(153, 153, 153));
        UnoTres.setBorder(new javax.swing.border.MatteBorder(null));
        UnoTres.setOpaque(true);
        PanelMatriz.add(UnoTres);

        SeisDos.setBackground(new java.awt.Color(153, 153, 153));
        SeisDos.setBorder(new javax.swing.border.MatteBorder(null));
        SeisDos.setOpaque(true);
        PanelMatriz.add(SeisDos);

        CincoSeis.setBackground(new java.awt.Color(153, 153, 153));
        CincoSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CincoSeis.setOpaque(true);
        PanelMatriz.add(CincoSeis);

        CincoCinco.setBackground(new java.awt.Color(153, 153, 153));
        CincoCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCinco.setOpaque(true);
        PanelMatriz.add(CincoCinco);

        SeisUno.setBackground(new java.awt.Color(153, 153, 153));
        SeisUno.setBorder(new javax.swing.border.MatteBorder(null));
        SeisUno.setOpaque(true);
        PanelMatriz.add(SeisUno);

        CincoSiete.setBackground(new java.awt.Color(153, 153, 153));
        CincoSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CincoSiete.setOpaque(true);
        PanelMatriz.add(CincoSiete);

        CeroCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CeroCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCuatro.setOpaque(true);
        PanelMatriz.add(CeroCuatro);

        CeroSeis.setBackground(new java.awt.Color(153, 153, 153));
        CeroSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CeroSeis.setOpaque(true);
        PanelMatriz.add(CeroSeis);

        SeisCuatro.setBackground(new java.awt.Color(153, 153, 153));
        SeisCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCuatro.setOpaque(true);
        PanelMatriz.add(SeisCuatro);

        SeisTres.setBackground(new java.awt.Color(153, 153, 153));
        SeisTres.setBorder(new javax.swing.border.MatteBorder(null));
        SeisTres.setOpaque(true);
        PanelMatriz.add(SeisTres);

        SeisSeis.setBackground(new java.awt.Color(153, 153, 153));
        SeisSeis.setBorder(new javax.swing.border.MatteBorder(null));
        SeisSeis.setOpaque(true);
        PanelMatriz.add(SeisSeis);

        SeisCinco.setBackground(new java.awt.Color(153, 153, 153));
        SeisCinco.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCinco.setOpaque(true);
        PanelMatriz.add(SeisCinco);

        SieteDos.setBackground(new java.awt.Color(153, 153, 153));
        SieteDos.setBorder(new javax.swing.border.MatteBorder(null));
        SieteDos.setOpaque(true);
        PanelMatriz.add(SieteDos);

        SieteUno.setBackground(new java.awt.Color(153, 153, 153));
        SieteUno.setBorder(new javax.swing.border.MatteBorder(null));
        SieteUno.setOpaque(true);
        PanelMatriz.add(SieteUno);

        SieteCuatro.setBackground(new java.awt.Color(153, 153, 153));
        SieteCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCuatro.setOpaque(true);
        PanelMatriz.add(SieteCuatro);

        SeisSiete.setBackground(new java.awt.Color(153, 153, 153));
        SeisSiete.setBorder(new javax.swing.border.MatteBorder(null));
        SeisSiete.setOpaque(true);
        PanelMatriz.add(SeisSiete);

        CeroCinco.setBackground(new java.awt.Color(153, 153, 153));
        CeroCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCinco.setOpaque(true);
        PanelMatriz.add(CeroCinco);

        SieteTres.setBackground(new java.awt.Color(153, 153, 153));
        SieteTres.setBorder(new javax.swing.border.MatteBorder(null));
        SieteTres.setOpaque(true);
        PanelMatriz.add(SieteTres);

        SieteSeis.setBackground(new java.awt.Color(153, 153, 153));
        SieteSeis.setBorder(new javax.swing.border.MatteBorder(null));
        SieteSeis.setOpaque(true);
        PanelMatriz.add(SieteSeis);

        SieteCinco.setBackground(new java.awt.Color(153, 153, 153));
        SieteCinco.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCinco.setOpaque(true);
        PanelMatriz.add(SieteCinco);

        CeroDos.setBackground(new java.awt.Color(153, 153, 153));
        CeroDos.setBorder(new javax.swing.border.MatteBorder(null));
        CeroDos.setOpaque(true);
        PanelMatriz.add(CeroDos);

        SieteSiete.setBackground(new java.awt.Color(153, 153, 153));
        SieteSiete.setBorder(new javax.swing.border.MatteBorder(null));
        SieteSiete.setOpaque(true);
        PanelMatriz.add(SieteSiete);

        CeroUno.setBackground(new java.awt.Color(153, 153, 153));
        CeroUno.setBorder(new javax.swing.border.MatteBorder(null));
        CeroUno.setOpaque(true);
        PanelMatriz.add(CeroUno);

        UnoUno.setBackground(new java.awt.Color(153, 153, 153));
        UnoUno.setBorder(new javax.swing.border.MatteBorder(null));
        UnoUno.setOpaque(true);
        PanelMatriz.add(UnoUno);

        UnoDos.setBackground(new java.awt.Color(153, 153, 153));
        UnoDos.setBorder(new javax.swing.border.MatteBorder(null));
        UnoDos.setOpaque(true);
        PanelMatriz.add(UnoDos);

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(PanelColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelFila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(PanelColumna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelFila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblInformacionMovimiento.setText("Debe de utilizar las flechas del teclado para poder controlar al robot");

        javax.swing.GroupLayout PanelArribaLayout = new javax.swing.GroupLayout(PanelArriba);
        PanelArriba.setLayout(PanelArribaLayout);
        PanelArribaLayout.setHorizontalGroup(
            PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelArribaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInformacionMovimiento)
                .addGap(66, 66, 66))
        );
        PanelArribaLayout.setVerticalGroup(
            PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelArribaLayout.createSequentialGroup()
                .addGap(0, 93, Short.MAX_VALUE)
                .addComponent(lblInformacionMovimiento))
        );

        PosicionRecorridas.setText("Posiciones recorridas:");

        lblPosicionesLimpiadas.setText("Posiciones limpiadas:");

        lblPosicionDelRobot.setText("Posicion del robot:");

        btnReiniciarMatriz.setText("Reniciar Matriz");
        btnReiniciarMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarMatrizActionPerformed(evt);
            }
        });

        lblNoSePudoMoverPor.setText("No se pudo mover por:");

        lblXYContador.setText("0,0");
        lblXYContador.setToolTipText("");
        lblXYContador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblPosicionRecorrida.setText("        0");
        lblPosicionRecorrida.setToolTipText("");
        lblPosicionRecorrida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblPosicionLimpiada.setText("        0");
        lblPosicionLimpiada.setToolTipText("");
        lblPosicionLimpiada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        Robot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Robot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/robot-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout PanelDerechaLayout = new javax.swing.GroupLayout(PanelDerecha);
        PanelDerecha.setLayout(PanelDerechaLayout);
        PanelDerechaLayout.setHorizontalGroup(
            PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechaLayout.createSequentialGroup()
                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDerechaLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(PosicionRecorridas))
                    .addGroup(PanelDerechaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPosicionRecorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPosicionLimpiada, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDerechaLayout.createSequentialGroup()
                                .addComponent(lblXYContador)
                                .addGap(21, 21, 21))))
                    .addGroup(PanelDerechaLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMostrarRazonNoSeMueve, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblPosicionDelRobot)
                                    .addComponent(lblPosicionesLimpiadas))
                                .addComponent(lblNoSePudoMoverPor)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                                    .addComponent(btnReiniciarMatriz)
                                    .addGap(2, 2, 2))))))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Robot, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        PanelDerechaLayout.setVerticalGroup(
            PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PosicionRecorridas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionRecorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionesLimpiadas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionLimpiada, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionDelRobot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblXYContador, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoSePudoMoverPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMostrarRazonNoSeMueve, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Robot, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReiniciarMatriz)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(PanelDerecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReiniciarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarMatrizActionPerformed
        randomizarColores();
    }//GEN-LAST:event_btnReiniciarMatrizActionPerformed
    private int currentRow = 0;
    private int currentCol = 0;
    String message = "un cuadro rojo";

    private void PanelMatrizKeyPressed(java.awt.event.KeyEvent evt) {
        int newRow = currentRow;
        int newCol = currentCol;

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                newRow = Math.max(0, currentRow - 1);
                break;
            case KeyEvent.VK_DOWN:
                newRow = Math.min(7, currentRow + 1);
                break;
            case KeyEvent.VK_LEFT:
                newCol = Math.max(0, currentCol - 1);
                break;
            case KeyEvent.VK_RIGHT:
                newCol = Math.min(7, currentCol + 1);
                break;
        }

        JLabel targetLabel = getLabelAt(newRow, newCol);
        if (targetLabel != null) {
            if (targetLabel.getBackground() != Color.RED) {
                moveRobotTo(newRow, newCol);
                handleGreenLabel(newRow, newCol);
                updatePosicionLabel();
            } else {
                updateRazonNoSeMovio();
            }
        } else {
            System.out.println("Target label not found");
        }
    }

    private void moveRobotTo(int row, int col) {
        JLabel targetLabel = getLabelAt(row, col);
        if (targetLabel != null) {
            Robot.setBounds(targetLabel.getBounds());
            currentRow = row;
            currentCol = col;
        }
    }

    private void handleGreenLabel(int row, int col) {
        JLabel label = getLabelAt(row, col);
        if (label != null && label.getBackground() == Color.GREEN) {
            label.setBackground(Color.WHITE);
            colorVerde.remove(label);
            colorBlanco.add(label);
//            System.out.println("Green label changed to white");
        }
    }

    private JLabel getLabelAt(int row, int col) {
        int index = row * 8 + col;
        Component comp = PanelMatriz.getComponent(index);
        if (comp instanceof JLabel) {
            return (JLabel) comp;
        }
        return null;
    }

    private void updatePosicionLabel() {
        lblXYContador.setText(currentCol + "," + currentRow);
    }

    private void updateRazonNoSeMovio() {
        lblMostrarRazonNoSeMueve.setText(message);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaSecundaria dialog = new VentanaSecundaria(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CeroCero;
    private javax.swing.JLabel CeroCinco;
    private javax.swing.JLabel CeroCuatro;
    private javax.swing.JLabel CeroDos;
    private javax.swing.JLabel CeroSeis;
    private javax.swing.JLabel CeroSiete;
    private javax.swing.JLabel CeroTres;
    private javax.swing.JLabel CeroUno;
    private javax.swing.JLabel CincoCero;
    private javax.swing.JLabel CincoCinco;
    private javax.swing.JLabel CincoCuatro;
    private javax.swing.JLabel CincoDos;
    private javax.swing.JLabel CincoSeis;
    private javax.swing.JLabel CincoSiete;
    private javax.swing.JLabel CincoTres;
    private javax.swing.JLabel CincoUno;
    private javax.swing.JLabel CuatroCero;
    private javax.swing.JLabel CuatroCinco;
    private javax.swing.JLabel CuatroCuatro;
    private javax.swing.JLabel CuatroDos;
    private javax.swing.JLabel CuatroSeis;
    private javax.swing.JLabel CuatroSiete;
    private javax.swing.JLabel CuatroTres;
    private javax.swing.JLabel CuatroUno;
    private javax.swing.JLabel DosCero;
    private javax.swing.JLabel DosCinco;
    private javax.swing.JLabel DosCuatro;
    private javax.swing.JLabel DosDos;
    private javax.swing.JLabel DosSeis;
    private javax.swing.JLabel DosSiete;
    private javax.swing.JLabel DosTres;
    private javax.swing.JLabel DosUno;
    private javax.swing.JPanel PanelArriba;
    private javax.swing.JPanel PanelColumna;
    private javax.swing.JPanel PanelDerecha;
    private javax.swing.JPanel PanelFila;
    private javax.swing.JPanel PanelMatriz;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JLabel PosicionRecorridas;
    private javax.swing.JLabel Robot;
    private javax.swing.JLabel SeisCero;
    private javax.swing.JLabel SeisCinco;
    private javax.swing.JLabel SeisCuatro;
    private javax.swing.JLabel SeisDos;
    private javax.swing.JLabel SeisSeis;
    private javax.swing.JLabel SeisSiete;
    private javax.swing.JLabel SeisTres;
    private javax.swing.JLabel SeisUno;
    private javax.swing.JLabel SieteCero;
    private javax.swing.JLabel SieteCinco;
    private javax.swing.JLabel SieteCuatro;
    private javax.swing.JLabel SieteDos;
    private javax.swing.JLabel SieteSeis;
    private javax.swing.JLabel SieteSiete;
    private javax.swing.JLabel SieteTres;
    private javax.swing.JLabel SieteUno;
    private javax.swing.JLabel TresCero;
    private javax.swing.JLabel TresCinco;
    private javax.swing.JLabel TresCuatro;
    private javax.swing.JLabel TresDos;
    private javax.swing.JLabel TresSeis;
    private javax.swing.JLabel TresSiete;
    private javax.swing.JLabel TresTres;
    private javax.swing.JLabel TresUno;
    private javax.swing.JLabel UnoCero;
    private javax.swing.JLabel UnoCinco;
    private javax.swing.JLabel UnoCuatro;
    private javax.swing.JLabel UnoDos;
    private javax.swing.JLabel UnoSeis;
    private javax.swing.JLabel UnoSiete;
    private javax.swing.JLabel UnoTres;
    private javax.swing.JLabel UnoUno;
    private javax.swing.JButton btnReiniciarMatriz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblInformacionMovimiento;
    private javax.swing.JLabel lblMostrarRazonNoSeMueve;
    private javax.swing.JLabel lblNoSePudoMoverPor;
    private javax.swing.JLabel lblNumeracion1;
    private javax.swing.JLabel lblNumeracion10;
    private javax.swing.JLabel lblNumeracion3;
    private javax.swing.JLabel lblNumeracion4;
    private javax.swing.JLabel lblNumeracion5;
    private javax.swing.JLabel lblNumeracion6;
    private javax.swing.JLabel lblNumeracion8;
    private javax.swing.JLabel lblNumeracion9;
    private javax.swing.JLabel lblPosicionDelRobot;
    private javax.swing.JLabel lblPosicionLimpiada;
    private javax.swing.JLabel lblPosicionRecorrida;
    private javax.swing.JLabel lblPosicionesLimpiadas;
    private javax.swing.JLabel lblXYContador;
    // End of variables declaration//GEN-END:variables
}
