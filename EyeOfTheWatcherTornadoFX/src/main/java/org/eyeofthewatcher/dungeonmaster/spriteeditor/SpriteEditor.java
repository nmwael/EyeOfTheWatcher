package org.eyeofthewatcher.dungeonmaster.spriteeditor;/**
 * Created by nmw on 09-06-2017.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.io.IOException;
import java.util.concurrent.Executors;

@Component
public class SpriteEditor extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }


    @Activate
    public void startBundle() {

        Executors.defaultThreadFactory().newThread(() -> {
            Thread.currentThread().setContextClassLoader(
                    this.getClass().getClassLoader());
            launch();
        }).start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(SpriteEditor.class.getResource("SpriteEditor.fxml"));
        rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

/*
            BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

            DependencyManager dm = new DependencyManager(bc);

            dm.add(dm.createComponent()
                    .setInterface(StageService.class.getName(), null)
                    .setImplementation(new StageServiceImpl(primaryStage)));
*/
    }

    @Deactivate
    public void stopBundle() {
        Platform.exit();
    }
}

