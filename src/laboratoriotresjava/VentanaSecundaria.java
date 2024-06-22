package laboratoriotresjava;

import java.awt.Color;
import javax.swing.JLabel;
import java.util.*;

public class VentanaSecundaria extends javax.swing.JDialog {

    public VentanaSecundaria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        randomizarColores();
        setLocationRelativeTo(null);
    }

    ArrayList<JLabel> colorBlanco = new ArrayList();
    ArrayList<JLabel> colorRojo = new ArrayList();
    ArrayList<JLabel> colorVerde = new ArrayList();

    private final Color[] colores = {Color.WHITE, Color.RED, Color.GREEN};

    private void randomizarColores() {
        List<JLabel> labels = new ArrayList<>();
        Random rand = new Random();

        // Agrega todas las etiquetas al arreglo excepto lblMatriz3
        for (java.awt.Component comp : PanelMatriz.getComponents()) {
            if (comp instanceof JLabel && comp != CeroCero) {
                labels.add((JLabel) comp);
            }
        }

        // Calcula el numero de etiquetas de cada color
        int totalLabels = labels.size();
        int greenLabels = totalLabels * 50 / 100;
        int redLabels = totalLabels * 15 / 100;
        int whiteLabels = totalLabels * 35 / 100;

        // Mezcla la lista de etiquetas aleatoriamente
        Collections.shuffle(labels);

        // Asigna colores segun las cantidades calculadas
        int greenCount = 0;
        int redCount = 0;
        int whiteCount = 0;

        for (JLabel label : labels) {
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
                // Si se han asignado todas las etiquetas requeridas, se asigna un color aleatorio de los definidos
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
        // Asegura que lblMatriz3 siempre sea azul
        CeroCero.setBackground(Color.BLUE);
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
        Robot = new javax.swing.JLabel();
        DosCero = new javax.swing.JLabel();
        SieteCero = new javax.swing.JLabel();
        UnoCuatro = new javax.swing.JLabel();
        SeisCero = new javax.swing.JLabel();
        CincoCero = new javax.swing.JLabel();
        CuatroCero = new javax.swing.JLabel();
        UnoCero = new javax.swing.JLabel();
        TresCero = new javax.swing.JLabel();
        CeroCero = new javax.swing.JLabel();
        UnoTres = new javax.swing.JLabel();
        UnoDos = new javax.swing.JLabel();
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
        TresUno = new javax.swing.JLabel();
        TresCuatro = new javax.swing.JLabel();
        TresTres = new javax.swing.JLabel();
        CuatroDos = new javax.swing.JLabel();
        TresSeis = new javax.swing.JLabel();
        TresCinco = new javax.swing.JLabel();
        CuatroUno = new javax.swing.JLabel();
        TresSiete = new javax.swing.JLabel();
        CuatroCuatro = new javax.swing.JLabel();
        CuatroTres = new javax.swing.JLabel();
        CincoDos = new javax.swing.JLabel();
        CuatroSeis = new javax.swing.JLabel();
        CuatroCinco = new javax.swing.JLabel();
        CincoUno = new javax.swing.JLabel();
        CuatroSiete = new javax.swing.JLabel();
        CincoCuatro = new javax.swing.JLabel();
        CincoTres = new javax.swing.JLabel();
        SeisDos = new javax.swing.JLabel();
        CincoSeis = new javax.swing.JLabel();
        CincoCinco = new javax.swing.JLabel();
        SeisUno = new javax.swing.JLabel();
        CincoSiete = new javax.swing.JLabel();
        SeisCuatro = new javax.swing.JLabel();
        SeisTres = new javax.swing.JLabel();
        SeisSeis = new javax.swing.JLabel();
        SeisCinco = new javax.swing.JLabel();
        SieteDos = new javax.swing.JLabel();
        SieteUno = new javax.swing.JLabel();
        SieteCuatro = new javax.swing.JLabel();
        SeisSiete = new javax.swing.JLabel();
        SieteTres = new javax.swing.JLabel();
        SieteSeis = new javax.swing.JLabel();
        SieteCinco = new javax.swing.JLabel();
        CeroDos = new javax.swing.JLabel();
        SieteSiete = new javax.swing.JLabel();
        CeroUno = new javax.swing.JLabel();
        CeroCuatro = new javax.swing.JLabel();
        CeroTres = new javax.swing.JLabel();
        CeroCinco = new javax.swing.JLabel();
        CeroSiete = new javax.swing.JLabel();
        CeroSeis = new javax.swing.JLabel();
        UnoUno = new javax.swing.JLabel();
        PanelArriba = new javax.swing.JPanel();
        PanelDerecha = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

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
        PanelFila.add(lblNumeracion9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 55, 30));

        lblNumeracion10.setText("6");
        PanelFila.add(lblNumeracion10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 55, 30));

        PanelColumna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("0");
        PanelColumna.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 18, -1));

        jLabel2.setText("1");
        PanelColumna.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setText("7");
        PanelColumna.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 10, 20));

        jLabel4.setText("2");
        PanelColumna.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 10, 20));

        jLabel5.setText("3");
        PanelColumna.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 10, 20));

        jLabel6.setText("4");
        PanelColumna.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 10, 20));

        jLabel7.setText("5");
        PanelColumna.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 10, 20));

        jLabel8.setText("6");
        PanelColumna.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 10, 20));

        PanelMatriz.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Robot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Robot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/robot-removebg-preview.png"))); // NOI18N
        PanelMatriz.add(Robot, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        DosCero.setBackground(new java.awt.Color(0, 153, 51));
        DosCero.setBorder(new javax.swing.border.MatteBorder(null));
        DosCero.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosCero.setOpaque(true);
        PanelMatriz.add(DosCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 60, 50));

        SieteCero.setBackground(new java.awt.Color(153, 153, 153));
        SieteCero.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCero.setOpaque(true);
        PanelMatriz.add(SieteCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 60, 50));

        UnoCuatro.setBackground(new java.awt.Color(255, 51, 51));
        UnoCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCuatro.setOpaque(true);
        PanelMatriz.add(UnoCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 60, 50));

        SeisCero.setBackground(new java.awt.Color(153, 153, 153));
        SeisCero.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCero.setOpaque(true);
        PanelMatriz.add(SeisCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 60, 50));

        CincoCero.setBackground(new java.awt.Color(153, 153, 153));
        CincoCero.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCero.setOpaque(true);
        PanelMatriz.add(CincoCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 60, 50));

        CuatroCero.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCero.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCero.setOpaque(true);
        PanelMatriz.add(CuatroCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 60, 50));

        UnoCero.setBackground(new java.awt.Color(255, 51, 51));
        UnoCero.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCero.setOpaque(true);
        PanelMatriz.add(UnoCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 60, 50));

        TresCero.setBackground(new java.awt.Color(153, 153, 153));
        TresCero.setBorder(new javax.swing.border.MatteBorder(null));
        TresCero.setOpaque(true);
        PanelMatriz.add(TresCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 60, 50));

        CeroCero.setBackground(new java.awt.Color(0, 153, 153));
        CeroCero.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCero.setOpaque(true);
        PanelMatriz.add(CeroCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        UnoTres.setBackground(new java.awt.Color(255, 51, 51));
        UnoTres.setBorder(new javax.swing.border.MatteBorder(null));
        UnoTres.setOpaque(true);
        PanelMatriz.add(UnoTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 60, 50));

        UnoDos.setBackground(new java.awt.Color(255, 51, 51));
        UnoDos.setBorder(new javax.swing.border.MatteBorder(null));
        UnoDos.setOpaque(true);
        PanelMatriz.add(UnoDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 60, 50));

        DosDos.setBackground(new java.awt.Color(0, 153, 51));
        DosDos.setBorder(new javax.swing.border.MatteBorder(null));
        DosDos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosDos.setOpaque(true);
        PanelMatriz.add(DosDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 60, 50));

        UnoSeis.setBackground(new java.awt.Color(255, 51, 51));
        UnoSeis.setBorder(new javax.swing.border.MatteBorder(null));
        UnoSeis.setOpaque(true);
        PanelMatriz.add(UnoSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 60, 50));

        UnoCinco.setBackground(new java.awt.Color(255, 51, 51));
        UnoCinco.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCinco.setOpaque(true);
        PanelMatriz.add(UnoCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 60, 50));

        DosUno.setBackground(new java.awt.Color(0, 153, 51));
        DosUno.setBorder(new javax.swing.border.MatteBorder(null));
        DosUno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosUno.setOpaque(true);
        PanelMatriz.add(DosUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 60, 50));

        UnoSiete.setBackground(new java.awt.Color(255, 51, 51));
        UnoSiete.setBorder(new javax.swing.border.MatteBorder(null));
        UnoSiete.setOpaque(true);
        PanelMatriz.add(UnoSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 60, 46));

        DosCuatro.setBackground(new java.awt.Color(0, 153, 51));
        DosCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        DosCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosCuatro.setOpaque(true);
        PanelMatriz.add(DosCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 60, 50));

        DosTres.setBackground(new java.awt.Color(0, 153, 51));
        DosTres.setBorder(new javax.swing.border.MatteBorder(null));
        DosTres.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosTres.setOpaque(true);
        PanelMatriz.add(DosTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 60, 50));

        DosSeis.setBackground(new java.awt.Color(0, 153, 51));
        DosSeis.setBorder(new javax.swing.border.MatteBorder(null));
        DosSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosSeis.setOpaque(true);
        PanelMatriz.add(DosSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 60, 50));

        DosCinco.setBackground(new java.awt.Color(0, 153, 51));
        DosCinco.setBorder(new javax.swing.border.MatteBorder(null));
        DosCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosCinco.setOpaque(true);
        PanelMatriz.add(DosCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 60, 50));

        TresDos.setBackground(new java.awt.Color(153, 153, 153));
        TresDos.setBorder(new javax.swing.border.MatteBorder(null));
        TresDos.setOpaque(true);
        PanelMatriz.add(TresDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 60, 50));

        DosSiete.setBackground(new java.awt.Color(0, 153, 51));
        DosSiete.setBorder(new javax.swing.border.MatteBorder(null));
        DosSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DosSiete.setOpaque(true);
        PanelMatriz.add(DosSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 60, 46));

        TresUno.setBackground(new java.awt.Color(153, 153, 153));
        TresUno.setBorder(new javax.swing.border.MatteBorder(null));
        TresUno.setOpaque(true);
        PanelMatriz.add(TresUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 60, 50));

        TresCuatro.setBackground(new java.awt.Color(153, 153, 153));
        TresCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        TresCuatro.setOpaque(true);
        PanelMatriz.add(TresCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 60, 50));

        TresTres.setBackground(new java.awt.Color(153, 153, 153));
        TresTres.setBorder(new javax.swing.border.MatteBorder(null));
        TresTres.setOpaque(true);
        PanelMatriz.add(TresTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 60, 50));

        CuatroDos.setBackground(new java.awt.Color(153, 153, 153));
        CuatroDos.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroDos.setOpaque(true);
        PanelMatriz.add(CuatroDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 60, 50));

        TresSeis.setBackground(new java.awt.Color(153, 153, 153));
        TresSeis.setBorder(new javax.swing.border.MatteBorder(null));
        TresSeis.setOpaque(true);
        PanelMatriz.add(TresSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 60, 50));

        TresCinco.setBackground(new java.awt.Color(153, 153, 153));
        TresCinco.setBorder(new javax.swing.border.MatteBorder(null));
        TresCinco.setOpaque(true);
        PanelMatriz.add(TresCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 60, 50));

        CuatroUno.setBackground(new java.awt.Color(153, 153, 153));
        CuatroUno.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroUno.setOpaque(true);
        PanelMatriz.add(CuatroUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 60, 50));

        TresSiete.setBackground(new java.awt.Color(153, 153, 153));
        TresSiete.setBorder(new javax.swing.border.MatteBorder(null));
        TresSiete.setOpaque(true);
        PanelMatriz.add(TresSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 60, 46));

        CuatroCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCuatro.setOpaque(true);
        PanelMatriz.add(CuatroCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 60, 50));

        CuatroTres.setBackground(new java.awt.Color(153, 153, 153));
        CuatroTres.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroTres.setOpaque(true);
        PanelMatriz.add(CuatroTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 60, 50));

        CincoDos.setBackground(new java.awt.Color(153, 153, 153));
        CincoDos.setBorder(new javax.swing.border.MatteBorder(null));
        CincoDos.setOpaque(true);
        PanelMatriz.add(CincoDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 60, 50));

        CuatroSeis.setBackground(new java.awt.Color(153, 153, 153));
        CuatroSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroSeis.setOpaque(true);
        PanelMatriz.add(CuatroSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 60, 50));

        CuatroCinco.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCinco.setOpaque(true);
        PanelMatriz.add(CuatroCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 60, 50));

        CincoUno.setBackground(new java.awt.Color(153, 153, 153));
        CincoUno.setBorder(new javax.swing.border.MatteBorder(null));
        CincoUno.setOpaque(true);
        PanelMatriz.add(CincoUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 60, 50));

        CuatroSiete.setBackground(new java.awt.Color(153, 153, 153));
        CuatroSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroSiete.setOpaque(true);
        PanelMatriz.add(CuatroSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 60, 46));

        CincoCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CincoCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCuatro.setOpaque(true);
        PanelMatriz.add(CincoCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 60, 50));

        CincoTres.setBackground(new java.awt.Color(153, 153, 153));
        CincoTres.setBorder(new javax.swing.border.MatteBorder(null));
        CincoTres.setOpaque(true);
        PanelMatriz.add(CincoTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 60, 50));

        SeisDos.setBackground(new java.awt.Color(153, 153, 153));
        SeisDos.setBorder(new javax.swing.border.MatteBorder(null));
        SeisDos.setOpaque(true);
        PanelMatriz.add(SeisDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 60, 50));

        CincoSeis.setBackground(new java.awt.Color(153, 153, 153));
        CincoSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CincoSeis.setOpaque(true);
        PanelMatriz.add(CincoSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 60, 50));

        CincoCinco.setBackground(new java.awt.Color(153, 153, 153));
        CincoCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCinco.setOpaque(true);
        PanelMatriz.add(CincoCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 60, 50));

        SeisUno.setBackground(new java.awt.Color(153, 153, 153));
        SeisUno.setBorder(new javax.swing.border.MatteBorder(null));
        SeisUno.setOpaque(true);
        PanelMatriz.add(SeisUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 60, 50));

        CincoSiete.setBackground(new java.awt.Color(153, 153, 153));
        CincoSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CincoSiete.setOpaque(true);
        PanelMatriz.add(CincoSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 60, 46));

        SeisCuatro.setBackground(new java.awt.Color(153, 153, 153));
        SeisCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCuatro.setOpaque(true);
        PanelMatriz.add(SeisCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 60, 50));

        SeisTres.setBackground(new java.awt.Color(153, 153, 153));
        SeisTres.setBorder(new javax.swing.border.MatteBorder(null));
        SeisTres.setOpaque(true);
        PanelMatriz.add(SeisTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 60, 50));

        SeisSeis.setBackground(new java.awt.Color(153, 153, 153));
        SeisSeis.setBorder(new javax.swing.border.MatteBorder(null));
        SeisSeis.setOpaque(true);
        PanelMatriz.add(SeisSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 60, 50));

        SeisCinco.setBackground(new java.awt.Color(153, 153, 153));
        SeisCinco.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCinco.setOpaque(true);
        PanelMatriz.add(SeisCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 60, 50));

        SieteDos.setBackground(new java.awt.Color(153, 153, 153));
        SieteDos.setBorder(new javax.swing.border.MatteBorder(null));
        SieteDos.setOpaque(true);
        PanelMatriz.add(SieteDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 60, 50));

        SieteUno.setBackground(new java.awt.Color(153, 153, 153));
        SieteUno.setBorder(new javax.swing.border.MatteBorder(null));
        SieteUno.setOpaque(true);
        PanelMatriz.add(SieteUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 60, 50));

        SieteCuatro.setBackground(new java.awt.Color(153, 153, 153));
        SieteCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCuatro.setOpaque(true);
        PanelMatriz.add(SieteCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 60, 50));

        SeisSiete.setBackground(new java.awt.Color(153, 153, 153));
        SeisSiete.setBorder(new javax.swing.border.MatteBorder(null));
        SeisSiete.setOpaque(true);
        PanelMatriz.add(SeisSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 60, 46));

        SieteTres.setBackground(new java.awt.Color(153, 153, 153));
        SieteTres.setBorder(new javax.swing.border.MatteBorder(null));
        SieteTres.setOpaque(true);
        PanelMatriz.add(SieteTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 60, 50));

        SieteSeis.setBackground(new java.awt.Color(153, 153, 153));
        SieteSeis.setBorder(new javax.swing.border.MatteBorder(null));
        SieteSeis.setOpaque(true);
        PanelMatriz.add(SieteSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 60, 50));

        SieteCinco.setBackground(new java.awt.Color(153, 153, 153));
        SieteCinco.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCinco.setOpaque(true);
        PanelMatriz.add(SieteCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 60, 50));

        CeroDos.setBackground(new java.awt.Color(0, 153, 153));
        CeroDos.setBorder(new javax.swing.border.MatteBorder(null));
        CeroDos.setOpaque(true);
        PanelMatriz.add(CeroDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 60, 50));

        SieteSiete.setBackground(new java.awt.Color(153, 153, 153));
        SieteSiete.setBorder(new javax.swing.border.MatteBorder(null));
        SieteSiete.setOpaque(true);
        PanelMatriz.add(SieteSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 60, 46));

        CeroUno.setBackground(new java.awt.Color(0, 153, 153));
        CeroUno.setBorder(new javax.swing.border.MatteBorder(null));
        CeroUno.setOpaque(true);
        PanelMatriz.add(CeroUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 60, 50));

        CeroCuatro.setBackground(new java.awt.Color(0, 153, 153));
        CeroCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCuatro.setOpaque(true);
        PanelMatriz.add(CeroCuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 60, 50));

        CeroTres.setBackground(new java.awt.Color(0, 153, 153));
        CeroTres.setBorder(new javax.swing.border.MatteBorder(null));
        CeroTres.setOpaque(true);
        PanelMatriz.add(CeroTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 60, 50));

        CeroCinco.setBackground(new java.awt.Color(0, 153, 153));
        CeroCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCinco.setOpaque(true);
        PanelMatriz.add(CeroCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 60, 50));

        CeroSiete.setBackground(new java.awt.Color(0, 153, 153));
        CeroSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CeroSiete.setOpaque(true);
        PanelMatriz.add(CeroSiete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 60, 46));

        CeroSeis.setBackground(new java.awt.Color(0, 153, 153));
        CeroSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CeroSeis.setOpaque(true);
        PanelMatriz.add(CeroSeis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 60, 50));

        UnoUno.setBackground(new java.awt.Color(255, 51, 51));
        UnoUno.setBorder(new javax.swing.border.MatteBorder(null));
        UnoUno.setOpaque(true);
        PanelMatriz.add(UnoUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 60, 50));

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
                .addContainerGap()
                .addComponent(PanelFila, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelColumna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout PanelArribaLayout = new javax.swing.GroupLayout(PanelArriba);
        PanelArriba.setLayout(PanelArribaLayout);
        PanelArribaLayout.setHorizontalGroup(
            PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelArribaLayout.setVerticalGroup(
            PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel9.setText("Posiciones recorridas:");

        jLabel10.setText("Posiciones limpiadas:");

        jLabel11.setText("Posicion del robot:");

        jButton1.setText("Reniciar Matriz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setText("No se pudo mover por:");

        jLabel13.setText("        0,0");
        jLabel13.setToolTipText("");
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel14.setText("        0");
        jLabel14.setToolTipText("");
        jLabel14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel15.setText("        0");
        jLabel15.setToolTipText("");
        jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout PanelDerechaLayout = new javax.swing.GroupLayout(PanelDerecha);
        PanelDerecha.setLayout(PanelDerechaLayout);
        PanelDerechaLayout.setHorizontalGroup(
            PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechaLayout.createSequentialGroup()
                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelDerechaLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addGroup(PanelDerechaLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(jLabel11))))
                        .addGroup(PanelDerechaLayout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDerechaLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel10))
                        .addGroup(PanelDerechaLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDerechaLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDerechaLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel12))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        PanelDerechaLayout.setVerticalGroup(
            PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelDerecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(PanelArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(PanelDerecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        randomizarColores();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblNumeracion1;
    private javax.swing.JLabel lblNumeracion10;
    private javax.swing.JLabel lblNumeracion3;
    private javax.swing.JLabel lblNumeracion4;
    private javax.swing.JLabel lblNumeracion5;
    private javax.swing.JLabel lblNumeracion6;
    private javax.swing.JLabel lblNumeracion8;
    private javax.swing.JLabel lblNumeracion9;
    // End of variables declaration//GEN-END:variables
}
