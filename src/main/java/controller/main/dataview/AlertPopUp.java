package controller.main.dataview;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.Optional;

public class AlertPopUp {
    // todo write comment
    public static Optional<ButtonType> showDeleteAlert(String dataType) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Remove");
        alert.setHeaderText("Are you sure you want to delete selected " + dataType + " ? It can not be undone.");
        //alert.setContentText(null);
        alert.initStyle(StageStyle.UTILITY);
        return alert.showAndWait();
    }
}
