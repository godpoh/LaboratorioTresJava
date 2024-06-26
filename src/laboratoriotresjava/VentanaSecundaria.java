package laboratoriotresjava;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VentanaSecundaria extends javax.swing.JDialog {

    private int matrixCount = 0;
    private int LineaActual = 0;
    private int cuadrosVerdesTotales = 0;
    private int ColumnaActual = 0;
    private int contadorPasos = 0;
    private int contadorCambioVerdeABlanco = 0;

    String message = "un cuadro rojo";

    private KeyListener globalKeyListener;
    private JPanel RobotPanel;

    private final Color[] colores = {Color.WHITE, Color.RED, Color.GREEN};
    List<JLabel> labels = new ArrayList<>();
    ArrayList<JLabel> colorBlanco = new ArrayList();
    ArrayList<JLabel> colorRojo = new ArrayList();
    ArrayList<JLabel> colorVerde = new ArrayList();

    public VentanaSecundaria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        randomizarColores();
        setLocationRelativeTo(null);

        addPanelMatrizListener();

        // configuracion del robot panel
        RobotPanel = new javax.swing.JPanel();
        RobotPanel.setLayout(null);
        RobotPanel.setBounds(0, 0, getWidth(), getHeight());
        RobotPanel.setOpaque(false);

        RobotPanel.add(Robot);
        RobotPanel.setComponentZOrder(Robot, 0);
        getContentPane().add(RobotPanel, 0);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RobotPanel.setBounds(0, 0, getWidth(), getHeight());
                actualizarPosicionRobot();
            }
        });

        // inicializa la posicion del robot
        LineaActual = 0;
        ColumnaActual = 0;

        revalidate();
        repaint();

        actualizarPosicionRobot();

        setupKeyListener();
        setRobotImageIcon();
        setupSpinnerListeners();
        setupGlobalKeyListener();
        guardarInfoActualMatriz();

        hacerSpinnerSoloLeible(spinnerMatrices);
        hacerSpinnerSoloLeible(spinnerX);
        hacerSpinnerSoloLeible(spinnerY);
    }

    private void setRobotImageIcon() {
        int cellWidth = PanelMatriz.getWidth() / 8;
        int cellHeight = PanelMatriz.getHeight() / 8;
        Robot.setSize(cellWidth, cellHeight);
        Robot.setOpaque(false);
    }

    private void actualizarPosicionRobot() {
        int cellWidth = PanelMatriz.getWidth() / 8;
        int cellHeight = PanelMatriz.getHeight() / 8;
        int x = ColumnaActual * cellWidth;
        int y = LineaActual * cellHeight;

        // obtiene la posicion de panelmatriz en relativo con el robotpanel
        Point panelPosition = SwingUtilities.convertPoint(PanelMatriz, 0, 0, RobotPanel);

        Robot.setBounds(panelPosition.x + x, panelPosition.y + y, cellWidth, cellHeight);

        // trae al robot al frente
        Robot.getParent().setComponentZOrder(Robot, 0);

        RobotPanel.revalidate();
        RobotPanel.repaint();
    }

    private void moverRobot(int keyCode) {
        int nuevaLinea = LineaActual;
        int nuevaCol = ColumnaActual;
        boolean hitBorder = false;

        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (LineaActual == 0) {
                    hitBorder = true;
                    message = "No puede pasar: Techo del salon";
                } else {
                    nuevaLinea = LineaActual - 1;
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (LineaActual == 7) {
                    hitBorder = true;
                    message = "No puede pasar: Piso del salon";
                } else {
                    nuevaLinea = LineaActual + 1;
                }
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (ColumnaActual == 0) {
                    hitBorder = true;
                    message = "No puede pasar: Pared izquierda del salon";
                } else {
                    nuevaCol = ColumnaActual - 1;
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (ColumnaActual == 7) {
                    hitBorder = true;
                    message = "No puede pasar: Pared derecha del salon";
                } else {
                    nuevaCol = ColumnaActual + 1;
                }
                break;
        }

        if (hitBorder) {
            updateRazonNoSeMovio();
            return;
        }

        JLabel currentLabel = obtenerLabelA(LineaActual, ColumnaActual);
        JLabel newLabel = obtenerLabelA(nuevaLinea, nuevaCol);

        if (newLabel != null) {
            Color backgroundColor = newLabel.getBackground();
            if (backgroundColor == Color.RED) {
                message = "No puede pasar: obstaculo(cuadro rojo)";
                updateRazonNoSeMovio();
                return; // No mover el robot si hay un obstáculo
            } else if (backgroundColor == Color.GREEN) {
                if (currentLabel != null && currentLabel.getBackground() == Color.WHITE) {
                    currentLabel.setBackground(Color.WHITE);
                    colorVerde.remove(currentLabel);
                    colorBlanco.add(currentLabel);
                    updateCambiosVerdeABlanco();
                    message = "Cuadro verde limpiado correctamente";
                    updateRazonNoSeMovio(); // Actualizar mensaje solo si se limpia un cuadro verde
                }
            } else {
                message = ""; // limpiar mensaje si no hay obstaculo ni cuadro verde
                updateRazonNoSeMovio();
            }
        } else {
            message = ""; // limpiar mensaje si no hay label en la nueva posición
            updateRazonNoSeMovio();
        }

        if (nuevaLinea != LineaActual || nuevaCol != ColumnaActual) {
            LineaActual = nuevaLinea;
            ColumnaActual = nuevaCol;
            actualizarPosicionRobot();
            contadorPasos++;
            updateMovimientos();
            updatePosicionLabel();
            manejarLabelVerde(LineaActual, ColumnaActual);

            actualizarInfoMatrizActual();

            Robot.getParent().setComponentZOrder(Robot, 0);
            Robot.getParent().repaint();
        }
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

        int totalLabels = labels.size();
        int greenLabels = totalLabels * 50 / 100;
        int redLabels = totalLabels * 15 / 100;
        int whiteLabels = totalLabels - greenLabels - redLabels - 1; // -1 por el label azul

        List<JLabel> LabelsBarajados = new ArrayList<>(labels.subList(1, totalLabels));
        Collections.shuffle(LabelsBarajados);

        labels.get(0).setBackground(Color.BLUE);

        int contadorVerde = 0;
        int contadorRojo = 0;
        int contadorBlanco = 0;

        for (int i = 0; i < LabelsBarajados.size(); i++) {
            JLabel label = LabelsBarajados.get(i);
            Color colorAsignado;

            if (contadorVerde < greenLabels) {
                colorAsignado = Color.GREEN;
                contadorVerde++;
                colorVerde.add(label);
            } else if (contadorRojo < redLabels) {
                colorAsignado = Color.RED;
                contadorRojo++;
                colorRojo.add(label);
            } else if (contadorBlanco < whiteLabels) {
                colorAsignado = Color.WHITE;
                contadorBlanco++;
                colorBlanco.add(label);
            } else {
                colorAsignado = colores[rand.nextInt(colores.length)];
                if (colorAsignado == Color.GREEN) {
                    colorVerde.add(label);
                } else if (colorAsignado == Color.RED) {
                    colorRojo.add(label);
                } else if (colorAsignado == Color.WHITE) {
                    colorBlanco.add(label);
                }
            }

            label.setBackground(colorAsignado);

        }
        cuadrosVerdesTotales = contadorVerde; // inicializa todos los cuadros verdes
        updateSuciaLabel(); // actualiza el porcentaje actual de los cuadros sucios
        updateLblSucioLimpioObstaculo();

    }

    private String obtenerEstadoLabel(int row, int col) {
        JLabel label = obtenerLabelA(row, col);
        if (label != null) {
            Color backgroundColor = label.getBackground();
            if (backgroundColor == Color.RED) {
                return "Obstaculo";
            } else if (backgroundColor == Color.WHITE) {
                return "Limpio";
            } else if (backgroundColor == Color.GREEN) {
                return "Sucio";
            } else if (backgroundColor == Color.BLUE) {
                return "Inicio";
            }
        }
        return "Desconocido";
    }

    private void updateLblSucioLimpioObstaculo() {
        int x = (int) spinnerX.getValue();
        int y = (int) spinnerY.getValue();
        String estado = obtenerEstadoLabel(y, x);
        lblSucioLimpioObstaculo.setText(estado);
    }

    private void manejarLabelVerde(int row, int col) {
        JLabel label = obtenerLabelA(row, col);
        if (label != null && label.getBackground() == Color.GREEN) {
            label.setBackground(Color.WHITE);
            colorVerde.remove(label);
            colorBlanco.add(label);
            contadorCambioVerdeABlanco++;
            updateCambiosVerdeABlanco();

            updateSuciaLabel();

            actualizarInfoMatrizActual();

            Robot.getParent().setComponentZOrder(Robot, 0);
            Robot.getParent().repaint();
        }
    }

    private JLabel obtenerLabelA(int Flecha, int Columna) {
        int indice = Flecha * 8 + Columna;
        Component comp = PanelMatriz.getComponent(indice);
        if (comp instanceof JLabel) {
            return (JLabel) comp;
        }
        return null;
    }

    private void updateSuciaLabel() {
        int cuadrosVerdesRestantes = colorVerde.size();
        int porcentajeRestante = (int) ((cuadrosVerdesRestantes / (double) cuadrosVerdesTotales) * 100 / 2);
        jLabel10.setText(porcentajeRestante + "%");
        if (porcentajeRestante == 0) {
            JOptionPane.showMessageDialog(this, "Felicidades, has limpiado por completo el salon!");
        }
    }

    private void hacerSpinnerSoloLeible(JSpinner spinner) {
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        JTextField textField = editor.getTextField();
        textField.setEditable(false);
    }

    private void guardarInfoActualMatriz() {
        String xyEspaciosLimpios = obtenerEspaciosLimpios();
        String xyEspaciosSucios = obtenerEspaciosSucios();
        String xyEspaciosObstaculos = obtenerEspaciosObstaculos();
        int cantidadPosicionesRecorridas = contadorPasos;
        int porcentajeSuciedad = calcularPorcentajeSuciedad();

        AlmacenamientoObjecto info = new AlmacenamientoObjecto(xyEspaciosLimpios, xyEspaciosSucios, xyEspaciosObstaculos, cantidadPosicionesRecorridas, porcentajeSuciedad);
        matrixCount++;
        AlmacenamientoObjecto.informacionMatricesEIndices.put(matrixCount, info);

        // actualizar el spinner con el nuevo indice de matriz
        spinnerMatrices.setModel(new SpinnerNumberModel(matrixCount, 1, matrixCount, 1));
    }

    private void updateCambiosVerdeABlanco() {
        lblPosicionLimpiada.setText(contadorCambioVerdeABlanco + "");
    }

    private void updatePosicionLabel() {
        lblXYContador.setText(ColumnaActual + "," + LineaActual);
    }

    private void updateRazonNoSeMovio() {
        lblMostrarRazonNoSeMueve.setText(message);
    }

    private void updateMovimientos() {
        lblPosicionRecorrida.setText(contadorPasos + "");
    }

    private String obtenerEspaciosLimpios() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JLabel label = obtenerLabelA(i, j);
                if (label != null && label.getBackground() == Color.WHITE) {
                    sb.append(j).append(",").append(i).append(" | ");
                }
            }
        }
        return sb.toString();
    }

    private String obtenerEspaciosSucios() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JLabel label = obtenerLabelA(i, j);
                if (label != null && label.getBackground() == Color.GREEN) {
                    sb.append(j).append(",").append(i).append(" | ");
                }
            }
        }
        return sb.toString();
    }

    private String obtenerEspaciosObstaculos() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JLabel label = obtenerLabelA(i, j);
                if (label != null && label.getBackground() == Color.RED) {
                    sb.append(j).append(",").append(i).append(" | ");
                }
            }
        }
        return sb.toString();
    }

    private int calcularPorcentajeSuciedad() {
        int cuadrosTotales = 64;
        int cuadrosSucios = colorVerde.size();
        return (cuadrosSucios * 100) / cuadrosTotales;
    }

    private void actualizarInfoMatrizActual() {
        if (matrixCount > 0) {
            AlmacenamientoObjecto currentInfo = AlmacenamientoObjecto.informacionMatricesEIndices.get(matrixCount);
            if (currentInfo != null) {
                currentInfo.setCantidadPosicionesRecorridass(contadorPasos);
                currentInfo.setPorcentajeSuciedadd(calcularPorcentajeSuciedad());
            }
        }
    }

    private void setupSpinnerListeners() {
        ChangeListener spinnerListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateLblSucioLimpioObstaculo();
                VentanaSecundaria.this.requestFocusInWindow();
            }
        };

        spinnerX.addChangeListener(spinnerListener);
        spinnerY.addChangeListener(spinnerListener);

        FocusAdapter focusAdapter = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                VentanaSecundaria.this.requestFocusInWindow();
            }
        };

        spinnerX.addFocusListener(focusAdapter);
        spinnerY.addFocusListener(focusAdapter);
    }

    private void setupGlobalKeyListener() {
        globalKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moverRobot(e.getKeyCode());
            }
        };

        this.addKeyListener(globalKeyListener);
        this.setFocusable(true);

        addKeyListenerToAllComponents(this);
    }

    private void addKeyListenerToAllComponents(Container container) {
        for (Component component : container.getComponents()) {
            component.addKeyListener(globalKeyListener);
            if (component instanceof Container) {
                addKeyListenerToAllComponents((Container) component);
            }
        }
    }

    private void setupKeyListener() {
        this.requestFocusInWindow();
    }

    private void addPanelMatrizListener() {
        PanelMatriz.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                actualizarPosicionRobot();
            }

            public void componentResized(java.awt.event.ComponentEvent evt) {
                actualizarPosicionRobot();
            }
        });
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
        CeroCero = new javax.swing.JLabel();
        UnoCero = new javax.swing.JLabel();
        DosCero = new javax.swing.JLabel();
        TresCero = new javax.swing.JLabel();
        CuatroCero = new javax.swing.JLabel();
        CincoCero = new javax.swing.JLabel();
        SeisCero = new javax.swing.JLabel();
        SieteCero = new javax.swing.JLabel();
        CeroUno = new javax.swing.JLabel();
        UnoUno = new javax.swing.JLabel();
        DosUno = new javax.swing.JLabel();
        TresUno = new javax.swing.JLabel();
        CuatroUno = new javax.swing.JLabel();
        CincoUno = new javax.swing.JLabel();
        SeisUno = new javax.swing.JLabel();
        SieteUno = new javax.swing.JLabel();
        CeroDos = new javax.swing.JLabel();
        UnoDos = new javax.swing.JLabel();
        DosDos = new javax.swing.JLabel();
        TresDos = new javax.swing.JLabel();
        CuatroDos = new javax.swing.JLabel();
        CincoDos = new javax.swing.JLabel();
        SeisDos = new javax.swing.JLabel();
        SieteDos = new javax.swing.JLabel();
        CeroTres = new javax.swing.JLabel();
        UnoTres = new javax.swing.JLabel();
        DosTres = new javax.swing.JLabel();
        TresTres = new javax.swing.JLabel();
        CuatroTres = new javax.swing.JLabel();
        CincoTres = new javax.swing.JLabel();
        SeisTres = new javax.swing.JLabel();
        SieteTres = new javax.swing.JLabel();
        CeroCuatro = new javax.swing.JLabel();
        UnoCuatro = new javax.swing.JLabel();
        DosCuatro = new javax.swing.JLabel();
        TresCuatro = new javax.swing.JLabel();
        CuatroCuatro = new javax.swing.JLabel();
        CincoCuatro = new javax.swing.JLabel();
        SeisCuatro = new javax.swing.JLabel();
        SieteCuatro = new javax.swing.JLabel();
        CeroCinco = new javax.swing.JLabel();
        UnoCinco = new javax.swing.JLabel();
        DosCinco = new javax.swing.JLabel();
        TresCinco = new javax.swing.JLabel();
        CuatroCinco = new javax.swing.JLabel();
        CincoCinco = new javax.swing.JLabel();
        SeisCinco = new javax.swing.JLabel();
        SieteCinco = new javax.swing.JLabel();
        CeroSeis = new javax.swing.JLabel();
        UnoSeis = new javax.swing.JLabel();
        DosSeis = new javax.swing.JLabel();
        TresSeis = new javax.swing.JLabel();
        CuatroSeis = new javax.swing.JLabel();
        CincoSeis = new javax.swing.JLabel();
        SeisSeis = new javax.swing.JLabel();
        SieteSeis = new javax.swing.JLabel();
        CeroSiete = new javax.swing.JLabel();
        UnoSiete = new javax.swing.JLabel();
        DosSiete = new javax.swing.JLabel();
        TresSiete = new javax.swing.JLabel();
        CuatroSiete = new javax.swing.JLabel();
        CincoSiete = new javax.swing.JLabel();
        SeisSiete = new javax.swing.JLabel();
        SieteSiete = new javax.swing.JLabel();
        PanelArriba = new javax.swing.JPanel();
        lblInformacionMovimiento = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        spinnerMatrices = new javax.swing.JSpinner();
        btnListaEspaciosSucios = new javax.swing.JButton();
        btnListaEspaciosConObstaculos = new javax.swing.JButton();
        btnCantidadPosicionesRecorridas = new javax.swing.JButton();
        btnPorcentajeSuciedad = new javax.swing.JButton();
        btnListaEspaciosLimpios = new javax.swing.JButton();
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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        spinnerY = new javax.swing.JSpinner();
        spinnerX = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        lblSucioLimpioObstaculo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        PanelFila.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNumeracion1.setText("0");
        PanelFila.add(lblNumeracion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 55, 30));

        lblNumeracion3.setText("1");
        PanelFila.add(lblNumeracion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 30, 30));

        lblNumeracion4.setText("2");
        PanelFila.add(lblNumeracion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 55, 30));

        lblNumeracion5.setText("3");
        PanelFila.add(lblNumeracion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 55, 30));

        lblNumeracion6.setText("4");
        PanelFila.add(lblNumeracion6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 55, 30));

        lblNumeracion8.setText("5");
        PanelFila.add(lblNumeracion8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 55, 30));

        lblNumeracion9.setText("7");
        PanelFila.add(lblNumeracion9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 40, 30));

        lblNumeracion10.setText("6");
        PanelFila.add(lblNumeracion10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 55, 30));

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

        PanelMatriz.setLayout(new java.awt.GridLayout(8, 8, 1, 1));

        CeroCero.setBackground(new java.awt.Color(153, 153, 153));
        CeroCero.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroCero.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroCero.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroCero.setOpaque(true);
        CeroCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroCero);

        UnoCero.setBackground(new java.awt.Color(153, 153, 153));
        UnoCero.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoCero.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoCero.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoCero.setOpaque(true);
        UnoCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoCero);

        DosCero.setBackground(new java.awt.Color(153, 153, 153));
        DosCero.setBorder(new javax.swing.border.MatteBorder(null));
        DosCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosCero.setMaximumSize(new java.awt.Dimension(0, 0));
        DosCero.setMinimumSize(new java.awt.Dimension(0, 0));
        DosCero.setOpaque(true);
        DosCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosCero);

        TresCero.setBackground(new java.awt.Color(153, 153, 153));
        TresCero.setBorder(new javax.swing.border.MatteBorder(null));
        TresCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresCero.setMaximumSize(new java.awt.Dimension(0, 0));
        TresCero.setMinimumSize(new java.awt.Dimension(0, 0));
        TresCero.setOpaque(true);
        TresCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresCero);

        CuatroCero.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCero.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroCero.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroCero.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroCero.setOpaque(true);
        CuatroCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroCero);

        CincoCero.setBackground(new java.awt.Color(153, 153, 153));
        CincoCero.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoCero.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoCero.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoCero.setOpaque(true);
        CincoCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoCero);

        SeisCero.setBackground(new java.awt.Color(153, 153, 153));
        SeisCero.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisCero.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisCero.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisCero.setOpaque(true);
        SeisCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisCero);

        SieteCero.setBackground(new java.awt.Color(153, 153, 153));
        SieteCero.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteCero.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteCero.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteCero.setOpaque(true);
        SieteCero.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteCero);

        CeroUno.setBackground(new java.awt.Color(153, 153, 153));
        CeroUno.setBorder(new javax.swing.border.MatteBorder(null));
        CeroUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroUno.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroUno.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroUno.setOpaque(true);
        CeroUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroUno);

        UnoUno.setBackground(new java.awt.Color(153, 153, 153));
        UnoUno.setBorder(new javax.swing.border.MatteBorder(null));
        UnoUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoUno.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoUno.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoUno.setOpaque(true);
        UnoUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoUno);

        DosUno.setBackground(new java.awt.Color(153, 153, 153));
        DosUno.setBorder(new javax.swing.border.MatteBorder(null));
        DosUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosUno.setMaximumSize(new java.awt.Dimension(0, 0));
        DosUno.setMinimumSize(new java.awt.Dimension(0, 0));
        DosUno.setOpaque(true);
        DosUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosUno);

        TresUno.setBackground(new java.awt.Color(153, 153, 153));
        TresUno.setBorder(new javax.swing.border.MatteBorder(null));
        TresUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresUno.setMaximumSize(new java.awt.Dimension(0, 0));
        TresUno.setMinimumSize(new java.awt.Dimension(0, 0));
        TresUno.setOpaque(true);
        TresUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresUno);

        CuatroUno.setBackground(new java.awt.Color(153, 153, 153));
        CuatroUno.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroUno.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroUno.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroUno.setOpaque(true);
        CuatroUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroUno);

        CincoUno.setBackground(new java.awt.Color(153, 153, 153));
        CincoUno.setBorder(new javax.swing.border.MatteBorder(null));
        CincoUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoUno.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoUno.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoUno.setOpaque(true);
        CincoUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoUno);

        SeisUno.setBackground(new java.awt.Color(153, 153, 153));
        SeisUno.setBorder(new javax.swing.border.MatteBorder(null));
        SeisUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisUno.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisUno.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisUno.setOpaque(true);
        SeisUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisUno);

        SieteUno.setBackground(new java.awt.Color(153, 153, 153));
        SieteUno.setBorder(new javax.swing.border.MatteBorder(null));
        SieteUno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteUno.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteUno.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteUno.setOpaque(true);
        SieteUno.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteUno);

        CeroDos.setBackground(new java.awt.Color(153, 153, 153));
        CeroDos.setBorder(new javax.swing.border.MatteBorder(null));
        CeroDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroDos.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroDos.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroDos.setOpaque(true);
        CeroDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroDos);

        UnoDos.setBackground(new java.awt.Color(153, 153, 153));
        UnoDos.setBorder(new javax.swing.border.MatteBorder(null));
        UnoDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoDos.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoDos.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoDos.setOpaque(true);
        UnoDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoDos);

        DosDos.setBackground(new java.awt.Color(153, 153, 153));
        DosDos.setBorder(new javax.swing.border.MatteBorder(null));
        DosDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosDos.setMaximumSize(new java.awt.Dimension(0, 0));
        DosDos.setMinimumSize(new java.awt.Dimension(0, 0));
        DosDos.setOpaque(true);
        DosDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosDos);

        TresDos.setBackground(new java.awt.Color(153, 153, 153));
        TresDos.setBorder(new javax.swing.border.MatteBorder(null));
        TresDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresDos.setMaximumSize(new java.awt.Dimension(0, 0));
        TresDos.setMinimumSize(new java.awt.Dimension(0, 0));
        TresDos.setOpaque(true);
        TresDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresDos);

        CuatroDos.setBackground(new java.awt.Color(153, 153, 153));
        CuatroDos.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroDos.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroDos.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroDos.setOpaque(true);
        CuatroDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroDos);

        CincoDos.setBackground(new java.awt.Color(153, 153, 153));
        CincoDos.setBorder(new javax.swing.border.MatteBorder(null));
        CincoDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoDos.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoDos.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoDos.setOpaque(true);
        CincoDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoDos);

        SeisDos.setBackground(new java.awt.Color(153, 153, 153));
        SeisDos.setBorder(new javax.swing.border.MatteBorder(null));
        SeisDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisDos.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisDos.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisDos.setOpaque(true);
        SeisDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisDos);

        SieteDos.setBackground(new java.awt.Color(153, 153, 153));
        SieteDos.setBorder(new javax.swing.border.MatteBorder(null));
        SieteDos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteDos.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteDos.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteDos.setOpaque(true);
        SieteDos.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteDos);

        CeroTres.setBackground(new java.awt.Color(153, 153, 153));
        CeroTres.setBorder(new javax.swing.border.MatteBorder(null));
        CeroTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroTres.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroTres.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroTres.setOpaque(true);
        CeroTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroTres);

        UnoTres.setBackground(new java.awt.Color(153, 153, 153));
        UnoTres.setBorder(new javax.swing.border.MatteBorder(null));
        UnoTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoTres.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoTres.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoTres.setOpaque(true);
        UnoTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoTres);

        DosTres.setBackground(new java.awt.Color(153, 153, 153));
        DosTres.setBorder(new javax.swing.border.MatteBorder(null));
        DosTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosTres.setMaximumSize(new java.awt.Dimension(0, 0));
        DosTres.setMinimumSize(new java.awt.Dimension(0, 0));
        DosTres.setOpaque(true);
        DosTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosTres);

        TresTres.setBackground(new java.awt.Color(153, 153, 153));
        TresTres.setBorder(new javax.swing.border.MatteBorder(null));
        TresTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresTres.setMaximumSize(new java.awt.Dimension(0, 0));
        TresTres.setMinimumSize(new java.awt.Dimension(0, 0));
        TresTres.setOpaque(true);
        TresTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresTres);

        CuatroTres.setBackground(new java.awt.Color(153, 153, 153));
        CuatroTres.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroTres.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroTres.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroTres.setOpaque(true);
        CuatroTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroTres);

        CincoTres.setBackground(new java.awt.Color(153, 153, 153));
        CincoTres.setBorder(new javax.swing.border.MatteBorder(null));
        CincoTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoTres.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoTres.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoTres.setOpaque(true);
        CincoTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoTres);

        SeisTres.setBackground(new java.awt.Color(153, 153, 153));
        SeisTres.setBorder(new javax.swing.border.MatteBorder(null));
        SeisTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisTres.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisTres.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisTres.setOpaque(true);
        SeisTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisTres);

        SieteTres.setBackground(new java.awt.Color(153, 153, 153));
        SieteTres.setBorder(new javax.swing.border.MatteBorder(null));
        SieteTres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteTres.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteTres.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteTres.setOpaque(true);
        SieteTres.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteTres);

        CeroCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CeroCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroCuatro.setOpaque(true);
        CeroCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroCuatro);

        UnoCuatro.setBackground(new java.awt.Color(153, 153, 153));
        UnoCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoCuatro.setOpaque(true);
        UnoCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoCuatro);

        DosCuatro.setBackground(new java.awt.Color(153, 153, 153));
        DosCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        DosCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        DosCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        DosCuatro.setOpaque(true);
        DosCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosCuatro);

        TresCuatro.setBackground(new java.awt.Color(153, 153, 153));
        TresCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        TresCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        TresCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        TresCuatro.setOpaque(true);
        TresCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresCuatro);

        CuatroCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroCuatro.setOpaque(true);
        CuatroCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroCuatro);

        CincoCuatro.setBackground(new java.awt.Color(153, 153, 153));
        CincoCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoCuatro.setOpaque(true);
        CincoCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoCuatro);

        SeisCuatro.setBackground(new java.awt.Color(153, 153, 153));
        SeisCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisCuatro.setOpaque(true);
        SeisCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisCuatro);

        SieteCuatro.setBackground(new java.awt.Color(153, 153, 153));
        SieteCuatro.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCuatro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteCuatro.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteCuatro.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteCuatro.setOpaque(true);
        SieteCuatro.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteCuatro);

        CeroCinco.setBackground(new java.awt.Color(153, 153, 153));
        CeroCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CeroCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroCinco.setOpaque(true);
        CeroCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroCinco);

        UnoCinco.setBackground(new java.awt.Color(153, 153, 153));
        UnoCinco.setBorder(new javax.swing.border.MatteBorder(null));
        UnoCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoCinco.setOpaque(true);
        UnoCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoCinco);

        DosCinco.setBackground(new java.awt.Color(153, 153, 153));
        DosCinco.setBorder(new javax.swing.border.MatteBorder(null));
        DosCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        DosCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        DosCinco.setOpaque(true);
        DosCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosCinco);

        TresCinco.setBackground(new java.awt.Color(153, 153, 153));
        TresCinco.setBorder(new javax.swing.border.MatteBorder(null));
        TresCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        TresCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        TresCinco.setOpaque(true);
        TresCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresCinco);

        CuatroCinco.setBackground(new java.awt.Color(153, 153, 153));
        CuatroCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroCinco.setOpaque(true);
        CuatroCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroCinco);

        CincoCinco.setBackground(new java.awt.Color(153, 153, 153));
        CincoCinco.setBorder(new javax.swing.border.MatteBorder(null));
        CincoCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoCinco.setOpaque(true);
        CincoCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoCinco);

        SeisCinco.setBackground(new java.awt.Color(153, 153, 153));
        SeisCinco.setBorder(new javax.swing.border.MatteBorder(null));
        SeisCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisCinco.setOpaque(true);
        SeisCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisCinco);

        SieteCinco.setBackground(new java.awt.Color(153, 153, 153));
        SieteCinco.setBorder(new javax.swing.border.MatteBorder(null));
        SieteCinco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteCinco.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteCinco.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteCinco.setOpaque(true);
        SieteCinco.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteCinco);

        CeroSeis.setBackground(new java.awt.Color(153, 153, 153));
        CeroSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CeroSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroSeis.setOpaque(true);
        CeroSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroSeis);

        UnoSeis.setBackground(new java.awt.Color(153, 153, 153));
        UnoSeis.setBorder(new javax.swing.border.MatteBorder(null));
        UnoSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoSeis.setOpaque(true);
        UnoSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoSeis);

        DosSeis.setBackground(new java.awt.Color(153, 153, 153));
        DosSeis.setBorder(new javax.swing.border.MatteBorder(null));
        DosSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        DosSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        DosSeis.setOpaque(true);
        DosSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosSeis);

        TresSeis.setBackground(new java.awt.Color(153, 153, 153));
        TresSeis.setBorder(new javax.swing.border.MatteBorder(null));
        TresSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        TresSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        TresSeis.setOpaque(true);
        TresSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresSeis);

        CuatroSeis.setBackground(new java.awt.Color(153, 153, 153));
        CuatroSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroSeis.setOpaque(true);
        CuatroSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroSeis);

        CincoSeis.setBackground(new java.awt.Color(153, 153, 153));
        CincoSeis.setBorder(new javax.swing.border.MatteBorder(null));
        CincoSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoSeis.setOpaque(true);
        CincoSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoSeis);

        SeisSeis.setBackground(new java.awt.Color(153, 153, 153));
        SeisSeis.setBorder(new javax.swing.border.MatteBorder(null));
        SeisSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisSeis.setOpaque(true);
        SeisSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisSeis);

        SieteSeis.setBackground(new java.awt.Color(153, 153, 153));
        SieteSeis.setBorder(new javax.swing.border.MatteBorder(null));
        SieteSeis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteSeis.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteSeis.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteSeis.setOpaque(true);
        SieteSeis.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteSeis);

        CeroSiete.setBackground(new java.awt.Color(153, 153, 153));
        CeroSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CeroSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CeroSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        CeroSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        CeroSiete.setOpaque(true);
        CeroSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CeroSiete);

        UnoSiete.setBackground(new java.awt.Color(153, 153, 153));
        UnoSiete.setBorder(new javax.swing.border.MatteBorder(null));
        UnoSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UnoSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        UnoSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        UnoSiete.setOpaque(true);
        UnoSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(UnoSiete);

        DosSiete.setBackground(new java.awt.Color(153, 153, 153));
        DosSiete.setBorder(new javax.swing.border.MatteBorder(null));
        DosSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DosSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        DosSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        DosSiete.setOpaque(true);
        DosSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(DosSiete);

        TresSiete.setBackground(new java.awt.Color(153, 153, 153));
        TresSiete.setBorder(new javax.swing.border.MatteBorder(null));
        TresSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TresSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        TresSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        TresSiete.setOpaque(true);
        TresSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(TresSiete);

        CuatroSiete.setBackground(new java.awt.Color(153, 153, 153));
        CuatroSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CuatroSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuatroSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        CuatroSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        CuatroSiete.setOpaque(true);
        CuatroSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CuatroSiete);

        CincoSiete.setBackground(new java.awt.Color(153, 153, 153));
        CincoSiete.setBorder(new javax.swing.border.MatteBorder(null));
        CincoSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CincoSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        CincoSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        CincoSiete.setOpaque(true);
        CincoSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(CincoSiete);

        SeisSiete.setBackground(new java.awt.Color(153, 153, 153));
        SeisSiete.setBorder(new javax.swing.border.MatteBorder(null));
        SeisSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SeisSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        SeisSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        SeisSiete.setOpaque(true);
        SeisSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SeisSiete);

        SieteSiete.setBackground(new java.awt.Color(153, 153, 153));
        SieteSiete.setBorder(new javax.swing.border.MatteBorder(null));
        SieteSiete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SieteSiete.setMaximumSize(new java.awt.Dimension(0, 0));
        SieteSiete.setMinimumSize(new java.awt.Dimension(0, 0));
        SieteSiete.setOpaque(true);
        SieteSiete.setPreferredSize(new java.awt.Dimension(0, 0));
        PanelMatriz.add(SieteSiete);

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
                .addContainerGap(42, Short.MAX_VALUE))
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

        lblInformacionMovimiento.setText("Debe utilizar W, arriba, S, abajo, D, derecha, A, izquierda");

        jLabel14.setText("Informacion sobre cada matriz:");

        spinnerMatrices.setModel(new javax.swing.SpinnerNumberModel());

        btnListaEspaciosSucios.setText("Lista de espacios sucios");
        btnListaEspaciosSucios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaEspaciosSuciosActionPerformed(evt);
            }
        });

        btnListaEspaciosConObstaculos.setText("Lista de espacios con obstáculos");
        btnListaEspaciosConObstaculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaEspaciosConObstaculosActionPerformed(evt);
            }
        });

        btnCantidadPosicionesRecorridas.setText("Cantidad de posiciones recorridas");
        btnCantidadPosicionesRecorridas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCantidadPosicionesRecorridasActionPerformed(evt);
            }
        });

        btnPorcentajeSuciedad.setText(" Porcentaje de suciedad");
        btnPorcentajeSuciedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorcentajeSuciedadActionPerformed(evt);
            }
        });

        btnListaEspaciosLimpios.setText("Lista de espacios limpios");
        btnListaEspaciosLimpios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaEspaciosLimpiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelArribaLayout = new javax.swing.GroupLayout(PanelArriba);
        PanelArriba.setLayout(PanelArribaLayout);
        PanelArribaLayout.setHorizontalGroup(
            PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelArribaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblInformacionMovimiento)
                .addGap(142, 142, 142))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelArribaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelArribaLayout.createSequentialGroup()
                        .addComponent(btnCantidadPosicionesRecorridas)
                        .addGap(32, 32, 32)
                        .addComponent(btnListaEspaciosSucios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE))
                    .addGroup(PanelArribaLayout.createSequentialGroup()
                        .addComponent(btnListaEspaciosLimpios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spinnerMatrices, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)))
                .addGroup(PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnListaEspaciosConObstaculos)
                    .addComponent(btnPorcentajeSuciedad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelArribaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(230, 230, 230))
        );
        PanelArribaLayout.setVerticalGroup(
            PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelArribaLayout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerMatrices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPorcentajeSuciedad)
                    .addComponent(btnListaEspaciosLimpios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListaEspaciosSucios)
                    .addComponent(btnListaEspaciosConObstaculos)
                    .addComponent(btnCantidadPosicionesRecorridas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
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

        lblNoSePudoMoverPor.setText("Movimientos:");

        lblXYContador.setText("0,0");
        lblXYContador.setToolTipText("");
        lblXYContador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblPosicionRecorrida.setText("0");
        lblPosicionRecorrida.setToolTipText("");
        lblPosicionRecorrida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblPosicionLimpiada.setText("0");
        lblPosicionLimpiada.setToolTipText("");
        lblPosicionLimpiada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        Robot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Robot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/robotfoto.png"))); // NOI18N

        lblMostrarRazonNoSeMueve.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jLabel9.setText("Porcentaje de suciedad:");

        spinnerY.setModel(new javax.swing.SpinnerNumberModel(0, 0, 7, 1));

        spinnerX.setModel(new javax.swing.SpinnerNumberModel(0, 0, 7, 1));

        jLabel11.setText("Obtener estado");

        lblSucioLimpioObstaculo.setText(" ");

        jLabel12.setText("X");

        jLabel13.setText("Y");

        javax.swing.GroupLayout PanelDerechaLayout = new javax.swing.GroupLayout(PanelDerecha);
        PanelDerecha.setLayout(PanelDerechaLayout);
        PanelDerechaLayout.setHorizontalGroup(
            PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechaLayout.createSequentialGroup()
                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMostrarRazonNoSeMueve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelDerechaLayout.createSequentialGroup()
                        .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelDerechaLayout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PosicionRecorridas)
                                        .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblPosicionesLimpiadas)
                                            .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblNoSePudoMoverPor)
                                                .addComponent(lblPosicionDelRobot)))))
                                .addGroup(PanelDerechaLayout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnReiniciarMatriz)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12))))
                            .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addGroup(PanelDerechaLayout.createSequentialGroup()
                                    .addComponent(spinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(spinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                        .addComponent(lblXYContador)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                        .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Robot, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                        .addComponent(lblSucioLimpioObstaculo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
            .addGroup(PanelDerechaLayout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                        .addComponent(lblPosicionRecorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechaLayout.createSequentialGroup()
                        .addComponent(lblPosicionLimpiada, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
        );
        PanelDerechaLayout.setVerticalGroup(
            PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PosicionRecorridas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionRecorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionesLimpiadas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionLimpiada, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPosicionDelRobot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblXYContador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoSePudoMoverPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMostrarRazonNoSeMueve, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSucioLimpioObstaculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Robot, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReiniciarMatriz)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        // Reset the matrix and related variables
        randomizarColores();
        LineaActual = 0;
        ColumnaActual = 0;
        contadorPasos = 0;
        contadorCambioVerdeABlanco = 0;
        actualizarPosicionRobot();
        updateMovimientos();
        updateCambiosVerdeABlanco();
        updatePosicionLabel();
        updateSuciaLabel();
        updateLblSucioLimpioObstaculo();

        this.requestFocusInWindow();
        guardarInfoActualMatriz();
    }//GEN-LAST:event_btnReiniciarMatrizActionPerformed

    private void btnListaEspaciosSuciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaEspaciosSuciosActionPerformed
        int selectedMatrix = (int) spinnerMatrices.getValue();
        AlmacenamientoObjecto info = AlmacenamientoObjecto.informacionMatricesEIndices.get(selectedMatrix);
        if (info != null) {
            String message = "Espacios sucios INICIALES(X,Y) para la matriz " + selectedMatrix + ":\n" + info.getXyEspaciosSucioss();
            JOptionPane.showMessageDialog(this, message, "Espacios Sucios", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No hay informacion disponible para la matriz " + selectedMatrix, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnListaEspaciosSuciosActionPerformed

    private void btnListaEspaciosLimpiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaEspaciosLimpiosActionPerformed
        int selectedMatrix = (int) spinnerMatrices.getValue();
        AlmacenamientoObjecto info = AlmacenamientoObjecto.informacionMatricesEIndices.get(selectedMatrix);
        if (info != null) {
            String message = "Espacios limpios INICIALES(X,Y) para la matriz " + selectedMatrix + ":\n" + info.getXyEspaciosLimpioss();
            JOptionPane.showMessageDialog(this, message, "Espacios Limpios", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No hay informacion disponible para la matriz " + selectedMatrix, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnListaEspaciosLimpiosActionPerformed

    private void btnCantidadPosicionesRecorridasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCantidadPosicionesRecorridasActionPerformed
        int selectedMatrix = (int) spinnerMatrices.getValue();
        if (selectedMatrix == matrixCount) {
            // si es the matriz actual, muestra el valor actual
            String message = "Cantidad de posiciones recorridas para la matriz actual:\n" + contadorPasos;
            JOptionPane.showMessageDialog(this, message, "Posiciones Recorridas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            AlmacenamientoObjecto info = AlmacenamientoObjecto.informacionMatricesEIndices.get(selectedMatrix);
            if (info != null) {
                String message = "Cantidad de posiciones recorridas para la matriz " + selectedMatrix + ":\n" + info.getCantidadPosicionesRecorridass();
                JOptionPane.showMessageDialog(this, message, "Posiciones Recorridas", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay información disponible para la matriz " + selectedMatrix, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCantidadPosicionesRecorridasActionPerformed

    private void btnListaEspaciosConObstaculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaEspaciosConObstaculosActionPerformed
        int selectedMatrix = (int) spinnerMatrices.getValue();
        AlmacenamientoObjecto info = AlmacenamientoObjecto.informacionMatricesEIndices.get(selectedMatrix);
        if (info != null) {
            String message = "Espacios con obstculos INICIALES(X,Y) para la matriz " + selectedMatrix + ":\n" + info.getXyEspaciosObstaculoss();
            JOptionPane.showMessageDialog(this, message, "Espacios con Obstaculos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No hay informacion disponible para la matriz " + selectedMatrix, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnListaEspaciosConObstaculosActionPerformed

    private void btnPorcentajeSuciedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorcentajeSuciedadActionPerformed
        int selectedMatrix = (int) spinnerMatrices.getValue();
        if (selectedMatrix == matrixCount) {
            // si es the matriz actual, muestra el valor actual del porcentaje
            int currentPercentage = calcularPorcentajeSuciedad();
            String message = "Porcentaje de suciedad para la matriz actual:\n" + currentPercentage + "%";
            JOptionPane.showMessageDialog(this, message, "Porcentaje de Suciedad", JOptionPane.INFORMATION_MESSAGE);
        } else {
            AlmacenamientoObjecto info = AlmacenamientoObjecto.informacionMatricesEIndices.get(selectedMatrix);
            if (info != null) {
                String message = "Porcentaje de suciedad para la matriz " + selectedMatrix + ":\n" + info.getPorcentajeSuciedadd() + "%";
                JOptionPane.showMessageDialog(this, message, "Porcentaje de Suciedad", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay información disponible para la matriz " + selectedMatrix, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnPorcentajeSuciedadActionPerformed

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
    private javax.swing.JButton btnCantidadPosicionesRecorridas;
    private javax.swing.JButton btnListaEspaciosConObstaculos;
    private javax.swing.JButton btnListaEspaciosLimpios;
    private javax.swing.JButton btnListaEspaciosSucios;
    private javax.swing.JButton btnPorcentajeSuciedad;
    private javax.swing.JButton btnReiniciarMatriz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JLabel lblSucioLimpioObstaculo;
    private javax.swing.JLabel lblXYContador;
    private javax.swing.JSpinner spinnerMatrices;
    private javax.swing.JSpinner spinnerX;
    private javax.swing.JSpinner spinnerY;
    // End of variables declaration//GEN-END:variables
}
