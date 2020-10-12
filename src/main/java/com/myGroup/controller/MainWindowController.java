package com.myGroup.controller;

import com.myGroup.view.TIFFCreator;


import ij.ImagePlus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;

import java.io.File;
import java.io.IOException;


public class MainWindowController {

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane anchorRootPane;

    @FXML
    void OpenButtonAction() {

        System.out.println("Open Button Clicked!!!");

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(imageView.getScene().getWindow());

        TIFFCreator tiffCreator = new TIFFCreator(selectedFile.getPath());
        Image image = tiffCreator.getBufferedImage();

        imageView.setImage(image);

        ImageJ imageJ = new ImageJ();
        Dataset dataset = null;

        try {
            dataset = imageJ.scifio().datasetIO().open( selectedFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imageName = selectedFile.getName();

        Img imp2 = dataset.getImgPlus().getImg();
        ImagePlus imp =  ImageJFunctions.wrap(imp2 ,imageName);


        int chCount = (int) dataset.getChannels();
        int nSlices = imp.getNSlices();

        System.out.println("file name selected: " + imageName + "\n"  +
                nSlices + " slices in the one image" + "\n" +
                "Number of channels " + chCount);


        // creating the open option window
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ShowResultsWindow.fxml"));
        ShowResultsWindowController showResultsWindowController = new ShowResultsWindowController();
        fxmlLoader.setController(showResultsWindowController);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showResultsWindowController.setLabelImageName(imageName);
        showResultsWindowController.setLabelSlicesNumber(nSlices);
        showResultsWindowController.setLabelChannelsNumber(chCount);
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        stage.showAndWait();

    }

    @FXML
    void imageScrollAction(ScrollEvent event) {
        double zoom_fac = 1.05;

        if(event.getDeltaY() < 0) {
            zoom_fac = 2.0 - zoom_fac;
        }

        Scale newScale = new Scale();
        newScale.setPivotX(anchorRootPane.getLayoutX() + (anchorRootPane.getLayoutX()/2));
        newScale.setPivotY(anchorRootPane.getLayoutY() + (anchorRootPane.getLayoutY()/2));
        newScale.setX( imageView.getScaleX() * zoom_fac );
        newScale.setY(imageView.getScaleY() * zoom_fac );

        imageView.getTransforms().add(newScale);

        event.consume();
    }
}
