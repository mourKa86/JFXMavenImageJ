package com.myGroup.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowResultsWindowController {
    @FXML
    private Label labelImageName;

    @FXML
    private Label labelSlicesNumber;

    @FXML
    private Label labelChannelsNumber;

    //setters
    public void setLabelImageName(String ImageName) {
        this.labelImageName.setText(ImageName);
    }

    public void setLabelSlicesNumber(int SlicesNumber) {
        this.labelSlicesNumber.setText(String.valueOf(SlicesNumber));
    }

    public void setLabelChannelsNumber(int ChannelsNumber) {
        this.labelChannelsNumber.setText(String.valueOf(ChannelsNumber));
    }
}
