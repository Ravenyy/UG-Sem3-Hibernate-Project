package org.hibernate.HibernateProjekt;

import org.hibernate.HibernateProjekt.apk.Apk;
import org.hibernate.HibernateProjekt.scene.ApkScene;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {
    private Apk apk = new Apk();

    public static void main(String[] args) {
        launch(Runner.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setResizable(false);
        stage.setScene(new ApkScene(this));
        stage.show();
    }

    public Apk getApk() {
        return apk;
    }
}