package Vista;

import Acciones.Exportar;
import Acciones.VentaControlador;
import Extras.DatoProducto;
import Extras.Ticket;
import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class ViewManual extends JFrame {

    // ── Controlador ──────────────────────────────────────────────────────────
    private VentaControlador ventaControlador = new VentaControlador();
    private ArrayList<Producto> productosActuales = new ArrayList<>();

    // ── Componentes ──────────────────────────────────────────────────────────
    private JComboBox<String> cmbCategoria  = new JComboBox<>();
    private JComboBox<String> cmbProducto   = new JComboBox<>();
    private JLabel    lblImagen    = new JLabel("", SwingConstants.CENTER);
    private JLabel    lblStock     = new JLabel("Stock: ");
    private JSpinner  spnCantidad  = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    private JCheckBox chkVendido   = new JCheckBox("Vendido");
    private JTextField txtNombre    = new JTextField(12);
    private JTextField txtApellidos = new JTextField(12);
    private JTextField txtEdad      = new JTextField(4);
    private JTable    tblCarrito   = new JTable();
    private JLabel lblSubtotal = new JLabel("Subtotal: $0.00");
    private JLabel lblIVA      = new JLabel("IVA 16%: $0.00");
    private JLabel lblTotal    = new JLabel("Total:    $0.00");
    private JButton btnAgregar  = new JButton("Agregar");
    private JButton btnEliminar = new JButton("Eliminar");
    private JButton btnTicket   = new JButton("Ver Ticket");
    private JButton btnExportar = new JButton("Exportar");
    private JButton btnNueva    = new JButton("Nueva Venta");

    // ── Constructor ──────────────────────────────────────────────────────────
    public ViewManual() {
        setTitle("Tienda de Abarrotes - Manual");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // armar los paneles en sus zonas
        add(panelCliente(),                BorderLayout.NORTH);
        add(new JScrollPane(tblCarrito),   BorderLayout.CENTER);
        add(panelProductos(),              BorderLayout.EAST);
        add(panelSur(),                    BorderLayout.SOUTH);

        // conectar tabla y datos
        tblCarrito.setModel((TableModel) ventaControlador.getVentaTabla());
        for (String cat : DatoProducto.getCategorias()) {
            cmbCategoria.addItem(cat);
        }
        cargarProductos();
        agregarEventos();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ── Panel NORTE: datos del cliente (FlowLayout) ───────────────────────────
    private JPanel panelCliente() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del cliente"));

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellidos:"));
        panel.add(txtApellidos);
        panel.add(new JLabel("Edad:"));
        panel.add(txtEdad);

        return panel;
    }

    // ── Panel ESTE: selección de productos (BoxLayout vertical) ──────────────
    private JPanel panelProductos() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Productos"));
        panel.setPreferredSize(new Dimension(200, 0));

        lblImagen.setPreferredSize(new Dimension(160, 130));
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        panel.add(Box.createVerticalStrut(6));
        panel.add(centrar(new JLabel("Categoría:")));
        panel.add(centrar(cmbCategoria));
        panel.add(Box.createVerticalStrut(4));
        panel.add(centrar(new JLabel("Producto:")));
        panel.add(centrar(cmbProducto));
        panel.add(Box.createVerticalStrut(8));
        panel.add(centrar(lblImagen));
        panel.add(Box.createVerticalStrut(6));
        panel.add(centrar(lblStock));
        panel.add(Box.createVerticalStrut(6));
        panel.add(centrar(new JLabel("Cantidad:")));
        panel.add(centrar(spnCantidad));
        panel.add(Box.createVerticalStrut(4));
        panel.add(centrar(chkVendido));
        panel.add(Box.createVerticalStrut(8));
        panel.add(centrar(btnAgregar));

        return panel;
    }

    // ── Panel SUR: totales y botones (BorderLayout) ───────────────────────────
    private JPanel panelSur() {
        JPanel panel = new JPanel(new BorderLayout(8, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Resumen"));

        // totales a la izquierda
        JPanel totales = new JPanel(new GridLayout(3, 1, 2, 2));
        totales.add(lblSubtotal);
        totales.add(lblIVA);
        lblTotal.setFont(lblTotal.getFont().deriveFont(Font.BOLD, 14f));
        totales.add(lblTotal);
        panel.add(totales, BorderLayout.WEST);

        // botones a la derecha
        JPanel botones = new JPanel(new GridLayout(2, 2, 6, 6));
        botones.add(btnEliminar);
        botones.add(btnTicket);
        botones.add(btnExportar);
        botones.add(btnNueva);
        panel.add(botones, BorderLayout.EAST);

        return panel;
    }

    // ── Métodos auxiliares ────────────────────────────────────────────────────
    private boolean cargando = false;

    private void cargarProductos() {
        if (cmbCategoria.getSelectedItem() == null) return;
        cargando = true;
        cmbProducto.removeAllItems();
        String categoria = (String) cmbCategoria.getSelectedItem();
        productosActuales = DatoProducto.getProductos(categoria);
        for (Producto p : productosActuales) {
            cmbProducto.addItem(p.getNombre());
        }
        cargando = false;
        mostrarImagen();
    }

    private void mostrarImagen() {
        if (cargando) return;
        int indice = cmbProducto.getSelectedIndex();
        if (indice < 0) return;
        Producto p = productosActuales.get(indice);
        lblStock.setText("Stock: " + p.getStock());
        try {
            URL url = new URL(p.getImagen());
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setText("");
        } catch (Exception e) {
            lblImagen.setIcon(null);
            lblImagen.setText("Sin imagen");
        }
    }

    private void actualizarTotales() {
        lblSubtotal.setText(String.format("Subtotal: $%.2f", ventaControlador.getSubtotal()));
        lblIVA.setText(String.format("IVA 16%%: $%.2f", ventaControlador.getIVA()));
        lblTotal.setText(String.format("Total:    $%.2f", ventaControlador.getTotal()));
    }

    // ── Eventos de botones ────────────────────────────────────────────────────
    private void agregarEventos() {

        cmbCategoria.addActionListener(e -> cargarProductos());
        cmbProducto.addActionListener(e -> mostrarImagen());

        btnAgregar.addActionListener(e -> {
            int indice = cmbProducto.getSelectedIndex();
            if (indice < 0) return;
            Producto p = productosActuales.get(indice);
            int cantidad = (int) spnCantidad.getValue();
            if (cantidad > p.getStock()) {
                JOptionPane.showMessageDialog(this,
                        "Stock insuficiente. Disponible: " + p.getStock());
                return;
            }
            ventaControlador.agregarProducto(p, cantidad);
            chkVendido.setSelected(p.getStock() == 0);
            actualizarTotales();
            mostrarImagen();
        });

        btnEliminar.addActionListener(e -> {
            int fila = tblCarrito.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla.");
                return;
            }
            ventaControlador.eliminarProducto(fila);
            actualizarTotales();
            mostrarImagen();
        });

        btnTicket.addActionListener(e -> {
            Cliente cliente = new Cliente(
                    txtNombre.getText(),
                    txtApellidos.getText(),
                    txtEdad.getText().isEmpty() ? 0 : Integer.parseInt(txtEdad.getText())
            );
            ventaControlador.getVenta().setCliente(cliente);
            String texto = Ticket.construirTicket(ventaControlador.getVenta());
            JTextArea area = new JTextArea(texto);
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(420, 320));
            JOptionPane.showMessageDialog(this, scroll, "Vista previa del ticket",
                    JOptionPane.PLAIN_MESSAGE);
        });

        btnExportar.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new java.io.File("ticket.txt"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                boolean ok = Exportar.exportarTicketTxt(
                        ventaControlador.getVenta(),
                        fc.getSelectedFile().getAbsolutePath()
                );
                JOptionPane.showMessageDialog(this, ok ? "Archivo guardado!" : "Error al guardar.");
            }
        });

        btnNueva.addActionListener(e -> {
            ventaControlador.nuevaVenta();
            txtNombre.setText("");
            txtApellidos.setText("");
            txtEdad.setText("");
            chkVendido.setSelected(false);
            actualizarTotales();
            mostrarImagen();
        });
    }

    // helper para centrar componentes en BoxLayout
    private JPanel centrar(JComponent comp) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
        p.add(comp);
        return p;
    }

    // ── Main ─────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewManual());
    }
}