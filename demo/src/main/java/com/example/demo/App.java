package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Application start class
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        //Loading page
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-page.fxml"));
        App.stage = stage;
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Employee Management");
        Image image = new Image("C:\\Users\\ngede\\OneDrive\\Desktop\\CS\\CS196\\HRDatabase\\JavaHRDatabase\\demo\\src\\main\\java\\com\\example\\demo\\pictures\\icon.png");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * setting root file for fxml loader
     * @param fxml  file name
     * @throws IOException  input/output exception
     */
    public static void setRoot(String fxml) throws IOException {
        //Method to set scene root
        scene.setRoot(loadFXML(fxml));
        stage.setMaximized(true);

    }

    /**
     * load fxml from in other class files
     * @param fxml file name
     * @return  fxmlloader for the file
     * @throws IOException  input output error
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * opening of the new window
     * @param pageFxml page file name
     * @param pageTitle title for new window
     * @return new Stage with title pageTitle
     */
    public static Stage newWindow(String pageFxml, String pageTitle){
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(pageFxml+".fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(pageTitle);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            Image image = new Image("C:\\Users\\ngede\\OneDrive\\Desktop\\CS\\CS196\\HRDatabase\\JavaHRDatabase\\demo\\src\\main\\resources\\com\\example\\demo\\pictures\\icon.png");
            stage.getIcons().add(image);
            return stage;
            // Hide this current window (if this is what you want)
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * launching the application
     * @param args arguments
     */
    public static void main(String[] args) {
        launch();
    }
}