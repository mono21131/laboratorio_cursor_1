package edu.saludo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Aplicacion de escritorio que cubre el requerimiento "Solicitar saludo".
 *
 * Flujo:
 * 1) El estudiante hace clic en Solicitar saludo.
 * 2) El sistema solicita nombre, edad y hora (AM/PM).
 * 3) El estudiante ingresa lo solicitado.
 * 4) El sistema saluda al estudiante por su nombre y menciona su edad.
 */
public class SaludoApp extends Application {

    private static final double ANCHO = 620;
    private static final double ALTO = 440;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Solicitar saludo");
        stage.setMinWidth(480);
        stage.setMinHeight(380);
        mostrarInicio(stage);
        stage.show();
    }

    private void mostrarInicio(Stage stage) {
        Label titulo = new Label("Solicitar saludo");
        titulo.getStyleClass().add("titulo");

        Label descripcion = new Label(
                "Posibilita que el estudiante sea saludado por su nombre mencionando su edad.");
        descripcion.getStyleClass().add("descripcion");
        descripcion.setWrapText(true);
        descripcion.setMaxWidth(420);

        Button solicitar = new Button("Solicitar saludo");
        solicitar.getStyleClass().add("boton-principal");
        solicitar.setDefaultButton(true);
        solicitar.setOnAction(event -> mostrarFormulario(stage));

        HBox acciones = new HBox(12, solicitar, botonSalir());
        acciones.setAlignment(Pos.CENTER);

        VBox raiz = new VBox(18, titulo, descripcion, acciones);
        raiz.setAlignment(Pos.CENTER);
        raiz.getStyleClass().add("panel");

        stage.setScene(escena(raiz));
    }

    private void mostrarFormulario(Stage stage) {
        Label titulo = new Label("Datos del estudiante");
        titulo.getStyleClass().add("titulo");

        Label indicacion = new Label("Ingrese nombre, edad y hora (AM/PM).");
        indicacion.getStyleClass().add("descripcion");

        TextField campoNombre = new TextField();
        campoNombre.setPromptText("Ejemplo: Juan");

        TextField campoEdad = new TextField();
        campoEdad.setPromptText("Ejemplo: 20");

        Spinner<Integer> spinnerHora = new Spinner<>(1, 12, 8);
        spinnerHora.setEditable(true);
        spinnerHora.setPrefWidth(90);

        ComboBox<String> comboPeriodo = new ComboBox<>();
        comboPeriodo.getItems().addAll("AM", "PM");
        comboPeriodo.setValue("AM");
        comboPeriodo.setPrefWidth(90);

        HBox cajaHora = new HBox(10, spinnerHora, comboPeriodo);
        cajaHora.setAlignment(Pos.CENTER_LEFT);

        GridPane formulario = new GridPane();
        formulario.setHgap(16);
        formulario.setVgap(14);
        formulario.setMaxWidth(480);
        formulario.setAlignment(Pos.CENTER);

        ColumnConstraints colEtiqueta = new ColumnConstraints();
        colEtiqueta.setHalignment(HPos.RIGHT);
        colEtiqueta.setMinWidth(Region.USE_PREF_SIZE);
        colEtiqueta.setHgrow(Priority.NEVER);

        ColumnConstraints colCampo = new ColumnConstraints();
        colCampo.setHgrow(Priority.ALWAYS);
        colCampo.setFillWidth(true);
        colCampo.setMinWidth(220);

        formulario.getColumnConstraints().addAll(colEtiqueta, colCampo);
        GridPane.setHgrow(campoNombre, Priority.ALWAYS);
        GridPane.setHgrow(campoEdad, Priority.ALWAYS);
        GridPane.setHgrow(cajaHora, Priority.ALWAYS);
        campoNombre.setMaxWidth(Double.MAX_VALUE);
        campoEdad.setMaxWidth(Double.MAX_VALUE);

        formulario.add(etiquetaCampo("Nombre"), 0, 0);
        formulario.add(campoNombre, 1, 0);
        formulario.add(etiquetaCampo("Edad"), 0, 1);
        formulario.add(campoEdad, 1, 1);
        formulario.add(etiquetaCampo("Hora (AM/PM)"), 0, 2);
        formulario.add(cajaHora, 1, 2);

        Button saludar = new Button("Enviar y saludar");
        saludar.getStyleClass().add("boton-principal");
        saludar.setDefaultButton(true);
        saludar.setOnAction(event -> {
            String nombre = campoNombre.getText() == null ? "" : campoNombre.getText().trim();
            String edadTexto = campoEdad.getText() == null ? "" : campoEdad.getText().trim();
            Integer hora = spinnerHora.getValue();
            String periodo = comboPeriodo.getValue();

            String error = validar(nombre, edadTexto, hora, periodo);
            if (error != null) {
                mostrarAlerta("Datos incompletos", error);
                return;
            }

            int edad = Integer.parseInt(edadTexto);
            mostrarSaludo(stage, nombre, edad, hora, periodo);
        });

        Button volver = new Button("Volver");
        volver.getStyleClass().add("boton-secundario");
        volver.setOnAction(event -> mostrarInicio(stage));

        HBox acciones = new HBox(12, volver, saludar);
        acciones.setAlignment(Pos.CENTER);

        VBox raiz = new VBox(18, titulo, indicacion, formulario, acciones);
        raiz.setAlignment(Pos.CENTER);
        raiz.getStyleClass().add("panel");

        stage.setScene(escena(raiz));
    }

    private void mostrarSaludo(Stage stage, String nombre, int edad, int hora, String periodo) {
        String momento = "AM".equals(periodo) ? "Buenos días" : "Buenas tardes";

        Label titulo = new Label("Saludo");
        titulo.getStyleClass().add("titulo");

        Label saludo = new Label(
                momento + ", " + nombre + ". Tienes " + edad + " años.");
        saludo.getStyleClass().add("saludo");
        saludo.setWrapText(true);
        saludo.setMaxWidth(440);

        Label detalleHora = new Label(
                "Hora registrada: " + hora + " " + periodo + ".");
        detalleHora.getStyleClass().add("descripcion");

        Button otro = new Button("Solicitar otro saludo");
        otro.getStyleClass().add("boton-principal");
        otro.setOnAction(event -> mostrarFormulario(stage));

        Button inicio = new Button("Volver al inicio");
        inicio.getStyleClass().add("boton-secundario");
        inicio.setOnAction(event -> mostrarInicio(stage));

        HBox acciones = new HBox(12, inicio, otro, botonSalir());
        acciones.setAlignment(Pos.CENTER);

        VBox raiz = new VBox(18, titulo, saludo, detalleHora, acciones);
        raiz.setAlignment(Pos.CENTER);
        raiz.getStyleClass().add("panel");

        stage.setScene(escena(raiz));
    }

    private static String validar(String nombre, String edadTexto, Integer hora, String periodo) {
        if (nombre.isBlank()) {
            return "El nombre es obligatorio.";
        }
        if (edadTexto.isBlank()) {
            return "La edad es obligatoria.";
        }
        try {
            int edad = Integer.parseInt(edadTexto);
            if (edad <= 0 || edad > 120) {
                return "La edad debe ser un número entre 1 y 120.";
            }
        } catch (NumberFormatException ex) {
            return "La edad debe ser un número entero.";
        }
        if (hora == null || hora < 1 || hora > 12) {
            return "La hora debe estar entre 1 y 12.";
        }
        if (periodo == null || periodo.isBlank()) {
            return "Debe indicar si la hora es AM o PM.";
        }
        return null;
    }

    private static Button botonSalir() {
        Button salir = new Button("Salir");
        salir.getStyleClass().add("boton-salir");
        salir.setOnAction(event -> Platform.exit());
        return salir;
    }

    private static Label etiquetaCampo(String texto) {
        Label etiqueta = new Label(texto);
        etiqueta.getStyleClass().add("etiqueta");
        etiqueta.setMinWidth(Region.USE_PREF_SIZE);
        etiqueta.setWrapText(false);
        return etiqueta;
    }

    private static Scene escena(VBox raiz) {
        raiz.setPadding(new Insets(32));
        Scene scene = new Scene(raiz, ANCHO, ALTO);
        var css = SaludoApp.class.getResource("/estilo.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }
        return scene;
    }

    private static void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
