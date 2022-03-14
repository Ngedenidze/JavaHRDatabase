package com.example.demo;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class Alerts {

    /**
     * wrong input type alerts
     * @param title alert title
     * @param headerText alert headers
     * @param contextText alert context
     */
    public static void throwInfoAlert(String title,String headerText, String contextText) {
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    /**
     * wrong input type alerts
     * @param title alert title
     * @param headerText alert headers
     * @param contextText alert context
     */
    public static void warningInfoAlert(String title,String headerText, String contextText) {
        Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    /**
     * wrong input type alerts
     * @param title alert title
     * @param headerText alert headers
     * @param contextText alert context
     */
    public static void errorInfoAlert(String title,String headerText, String contextText) {
        Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    /**
     * wrong input type alerts
     * @param title alert title
     * @param headerText alert headers
     * @param contextText alert context
     */
    public static Alert confirmAlert(String title,String headerText, String contextText) {
        Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        return  alert;
        /*This Is how to use alert
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }*/
    }


}
