package co.edu.uniquindio.SOLID.Controlador;

import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ItemEntradaDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Service.Fachadas.InventarioFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {

    @FXML private ComboBox<ProveedorDTO> cmbProveedores;
    @FXML private TitledPane tpCrearProveedor;
    @FXML private TextField txtProvNit;
    @FXML private TextField txtProvNombre;
    @FXML private TextField txtProvContacto;
    @FXML private TextField txtProvEmail;
    @FXML private TextField txtProvTelefono;
    @FXML private ComboBox<ProductoDTO> cmbProductoEntrada;
    @FXML private Spinner<Integer> spnCantidadEntrada;
    @FXML private Label lblResultadoEntrada;
    @FXML private TableView<ProductoDTO> tblProductosInv;
    @FXML private TableColumn<ProductoDTO, String> colInvSku;
    @FXML private TableColumn<ProductoDTO, String> colInvNombre;
    @FXML private TableColumn<ProductoDTO, Number> colInvPrecio;
    @FXML private TableColumn<ProductoDTO, Number> colInvStock;

    private ObservableList<ProveedorDTO> proveedores;
    private ObservableList<ProductoDTO> productos;
    private final InventarioFacade facade = new InventarioFacade();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        proveedores = FXCollections.observableArrayList(facade.listarProveedores());
        productos = FXCollections.observableArrayList(facade.listarProductos());
        
        if (cmbProveedores != null) {
            cmbProveedores.setItems(proveedores);
            cmbProveedores.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
                if (seleccionado != null) {
                    if (txtProvNit != null) txtProvNit.setText(seleccionado.getNit());
                    if (txtProvNombre != null) txtProvNombre.setText(seleccionado.getNombre());
                    if (txtProvContacto != null) txtProvContacto.setText(seleccionado.getContacto());
                    if (txtProvEmail != null) txtProvEmail.setText(seleccionado.getEmail());
                    if (txtProvTelefono != null) txtProvTelefono.setText(seleccionado.getTelefono());
                }
            });
        }
        if (cmbProductoEntrada != null) {
            cmbProductoEntrada.setItems(productos);
        }
        if (spnCantidadEntrada != null) {
            spnCantidadEntrada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        }
        if (tblProductosInv != null) {
            colInvSku.setCellValueFactory(cd -> cd.getValue().skuProperty());
            colInvNombre.setCellValueFactory(cd -> cd.getValue().nombreProperty());
            colInvPrecio.setCellValueFactory(cd -> cd.getValue().precioProperty());
            colInvStock.setCellValueFactory(cd -> cd.getValue().stockProperty());
            tblProductosInv.setItems(productos);
        }
        if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(false);
    }

    @FXML
    void mostrarCrearProveedor() {
        if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(!tpCrearProveedor.isExpanded());
    }

    @FXML
    void crearProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        String nombre = txtProvNombre != null ? txtProvNombre.getText() : null;
        String contacto = txtProvContacto != null ? txtProvContacto.getText() : "";
        String email = txtProvEmail != null ? txtProvEmail.getText() : "";
        String telefono = txtProvTelefono != null ? txtProvTelefono.getText() : "";
        
        // Validaciones de campos
        if (nit == null || nit.trim().isEmpty()) {
            mostrarError("El NIT es obligatorio");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            return;
        }
        
        ProveedorDTO dto = new ProveedorDTO(nit, nombre, contacto, email, telefono, true);
        String resp = facade.crearProveedor(dto);
        if (!"OK".equals(resp)) { mostrarError(resp); return; }
        recargarProveedores();
        if (cmbProveedores != null) cmbProveedores.getSelectionModel().selectFirst();
        if (lblResultadoEntrada != null) lblResultadoEntrada.setText("Proveedor creado: " + nombre);
        if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(false);
        if (txtProvNit != null) txtProvNit.clear();
        if (txtProvNombre != null) txtProvNombre.clear();
        if (txtProvContacto != null) txtProvContacto.clear();
        if (txtProvEmail != null) txtProvEmail.clear();
        if (txtProvTelefono != null) txtProvTelefono.clear();
    }

    @FXML
    void actualizarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        String nombre = txtProvNombre != null ? txtProvNombre.getText() : null;
        String contacto = txtProvContacto != null ? txtProvContacto.getText() : null;
        String email = txtProvEmail != null ? txtProvEmail.getText() : null;
        String telefono = txtProvTelefono != null ? txtProvTelefono.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        ProveedorDTO dto = new ProveedorDTO(nit, nombre != null ? nombre : "", contacto != null ? contacto : "", email != null ? email : "", telefono != null ? telefono : "", true);
        String resp = facade.actualizarProveedor(dto);
        if (!"OK".equals(resp)) { mostrarError(resp); return; }
        recargarProveedores();
    }

    @FXML
    void eliminarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        String resp = facade.eliminarProveedor(nit);
        if (!"OK".equals(resp)) { mostrarError(resp); return; }
        recargarProveedores();
    }

    @FXML
    void activarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        String resp = facade.activarProveedor(nit);
        if (!"OK".equals(resp)) { mostrarError(resp); return; }
        recargarProveedores();
    }

    @FXML
    void inactivarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        String resp = facade.inactivarProveedor(nit);
        if (!"OK".equals(resp)) { mostrarError(resp); return; }
        recargarProveedores();
    }

    @FXML
    void confirmarEntradaInventario() {
        ProveedorDTO proveedor = cmbProveedores != null ? cmbProveedores.getValue() : null;
        ProductoDTO prod = cmbProductoEntrada != null ? cmbProductoEntrada.getValue() : null;
        Integer cant = spnCantidadEntrada != null ? spnCantidadEntrada.getValue() : 0;
        
        // Validaciones de campos
        if (proveedor == null) {
            mostrarError("Seleccione un proveedor");
            return;
        }
        if (prod == null) {
            mostrarError("Seleccione un producto");
            return;
        }
        if (cant == null || cant <= 0) {
            mostrarError("Cantidad inválida");
            return;
        }
        
        EntradaInventarioDTO entradaDTO = new EntradaInventarioDTO("ENT-" + System.currentTimeMillis(), java.time.LocalDateTime.now(), proveedor.getNit());
        entradaDTO.getListEntradasDTO().add(new ItemEntradaDTO(prod.getSku(), String.valueOf(cant)));
        String resp = facade.registrarEntrada(entradaDTO);
        if (!"OK".equals(resp)) { mostrarError(resp); return; }
        productos.setAll(facade.listarProductos());
        if (lblResultadoEntrada != null) lblResultadoEntrada.setText("Entrada confirmada.");
        if (tblProductosInv != null) tblProductosInv.refresh();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void recargarProveedores() {
        proveedores.setAll(facade.listarProveedores());
        if (cmbProveedores != null) cmbProveedores.setItems(proveedores);
    }
}


