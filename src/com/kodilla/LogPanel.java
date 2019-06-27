package com.kodilla;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static com.kodilla.GamePlatform.*;
import static javafx.scene.paint.Color.WHITE;

class LogPanel {

    private GridPane grid;
    private double numberOfRows = 15;
    private double numberOfColumns = 5;


    LogPanel(GridPane grid) {
        this.grid = grid;
    }

    GridPane newLogPanel() {
        setRowsAndColumns();
        addLabels();
        addTextFields();
        addButtons();

        return grid;

    }


    private void setRowsAndColumns() {

        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(100.0 / numberOfRows);
        }
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(100.0 / numberOfColumns);
        }
    }

    private void resetRowsAndColumns() {

        for (int i = 0; i < numberOfRows; i++) {
            grid.getRowConstraints().get(i).setPercentHeight(0);
        }
        for (int i = 0; i < numberOfColumns; i++) {
            grid.getColumnConstraints().get(i).setPercentWidth(0);
        }
    }

    private void clearGrid() {
        while (grid.getChildren().size() != 0) {
            grid.getChildren().remove(grid.getChildren().get(0));
        }
        resetRowsAndColumns();
    }

    private String encrypt(String textToEncrypt) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(textToEncrypt.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private void addLabels() {

        Label logPanelLabel = new Label();
        logPanelLabel.setText("LOG PANEL");
        logPanelLabel.setId("LogPanelLabel");
        logPanelLabel.setFont(FONT25);
        logPanelLabel.setMinWidth(300);
        logPanelLabel.setTextFill(WHITE);
        logPanelLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(logPanelLabel, HPos.CENTER);
        grid.add(logPanelLabel, 2, 0);

        Label loginLabel = new Label();
        loginLabel.setText("LOGIN");
        loginLabel.setId("LoginLabel");
        loginLabel.setFont(FONT20);
        loginLabel.setMinWidth(300);
        loginLabel.setTextFill(WHITE);
        loginLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(loginLabel, HPos.CENTER);
        grid.add(loginLabel, 2, 1);

        Label LoginWarningsLabel = new Label();
        LoginWarningsLabel.setText("");
        LoginWarningsLabel.setId("LoginWarningsLabel");
        LoginWarningsLabel.setFont(FONT15);
        LoginWarningsLabel.setMinWidth(300);
        LoginWarningsLabel.setWrapText(true);
        LoginWarningsLabel.setTextFill(WHITE);
        LoginWarningsLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(LoginWarningsLabel, HPos.CENTER);
        grid.add(LoginWarningsLabel, 2, 4);

        Label registrationLabel = new Label();
        registrationLabel.setText("OR REGISTER");
        registrationLabel.setId("RegistrationLabel");
        registrationLabel.setFont(FONT20);
        registrationLabel.setMinWidth(300);
        registrationLabel.setTextFill(WHITE);
        registrationLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(registrationLabel, HPos.CENTER);
        grid.add(registrationLabel, 2, 5);

        Label registrationWarningsLabel = new Label();
        registrationWarningsLabel.setText("");
        registrationWarningsLabel.setId("RegistrationWarningsLabel");
        registrationWarningsLabel.setFont(FONT15);
        registrationWarningsLabel.setMinWidth(600);
        registrationWarningsLabel.setWrapText(true);
        registrationWarningsLabel.setTextFill(WHITE);
        registrationWarningsLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(registrationWarningsLabel, HPos.CENTER);
        grid.add(registrationWarningsLabel, 1, 8, 3, 1);

        Label playAsGuestLabel = new Label();
        playAsGuestLabel.setText("OR PLAY AS GUEST");
        playAsGuestLabel.setId("PlayAsGuestLabel");
        playAsGuestLabel.setFont(FONT20);
        playAsGuestLabel.setMinWidth(300);
        playAsGuestLabel.setTextFill(WHITE);
        playAsGuestLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(playAsGuestLabel, HPos.CENTER);
        grid.add(playAsGuestLabel, 2, 9);
    }

    private void addTextFields() {

        TextField loginPlayerNameField = new TextField();
        loginPlayerNameField.setPromptText("Player Name");
        loginPlayerNameField.setId("LoginPlayerNameField");
        grid.add(loginPlayerNameField, 1, 2);

        PasswordField loginPasswordField = new PasswordField();
        loginPasswordField.setPromptText("Password");
        loginPasswordField.setId("LoginPasswordField");
        grid.add(loginPasswordField, 3, 2);

        TextField registrationPlayerNameField = new TextField();
        registrationPlayerNameField.setText("");
        registrationPlayerNameField.setPromptText("Player Name");
        registrationPlayerNameField.setId("RegistrationPlayerNameField");
        registrationPlayerNameField.setMaxWidth(250);
        GridPane.setHalignment(registrationPlayerNameField, HPos.LEFT);
        grid.add(registrationPlayerNameField, 1, 6);

        PasswordField registrationPasswordField = new PasswordField();
        registrationPasswordField.setText("");
        registrationPasswordField.setPromptText("Password");
        registrationPasswordField.setId("RegistrationPasswordField");
        registrationPasswordField.setMaxWidth(250);
        GridPane.setHalignment(registrationPasswordField, HPos.CENTER);
        grid.add(registrationPasswordField, 2, 6);

        PasswordField confirmRegistrationPasswordField = new PasswordField();
        confirmRegistrationPasswordField.setText("");
        confirmRegistrationPasswordField.setPromptText("Confirm Password");
        confirmRegistrationPasswordField.setId("ConfirmRegistrationPasswordField");
        confirmRegistrationPasswordField.setMaxWidth(250);
        GridPane.setHalignment(confirmRegistrationPasswordField, HPos.RIGHT);
        grid.add(confirmRegistrationPasswordField, 3, 6);
    }

    private void addButtons() {

        Button playAsGuestButton = new Button("PLAY AS GUEST");
        playAsGuestButton.setId("PlayAsGuestButton");
        playAsGuestButton.setFont(FONT30);
        playAsGuestButton.setOnAction((e) -> playAsGuestButtonAction());
        grid.add(playAsGuestButton, 2, 10);

        Button loginButton = new Button("LOGIN");
        loginButton.setId("LoginButton");
        loginButton.setFont(FONT30);
        loginButton.setOnAction((e) -> loginButtonAction());
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setValignment(loginButton, VPos.BASELINE);
        grid.add(loginButton, 2, 3);

        Button registerButton = new Button("REGISTER");
        registerButton.setId("RegisterButton");
        registerButton.setFont(FONT30);
        registerButton.setOnAction((e) -> registerButtonAction());
        GridPane.setHalignment(registerButton, HPos.CENTER);
        grid.add(registerButton, 2, 7);
    }

    private void playAsGuestButtonAction() {
        GamePlatform.setPlayerName("GUEST");
        clearGrid();
        MainMenu menu = new MainMenu(grid);
        grid = menu.newMainMenu();
    }

    private void loginButtonAction() {
        String inputLoginPlayer = ((TextField) (grid.lookup("#LoginPlayerNameField"))).getText();
        String inputLoginPassword = encrypt(((TextField) (grid.lookup("#LoginPasswordField"))).getText());
        boolean rightPlayerAndPassword = false;

        ((Label) (grid.lookup("#RegistrationWarningsLabel"))).setText("");

        for (Map.Entry entry : GamePlatform.getPlayersMap().entrySet()) {
            if (entry.getKey().equals(inputLoginPlayer) && entry.getValue().equals(inputLoginPassword)) {
                rightPlayerAndPassword = true;
                break;
            }

        }
        if (!rightPlayerAndPassword) {
            ((Label) (grid.lookup("#LoginWarningsLabel"))).setText("Wrong Player Name or password!");
        } else {
            setLoggedIn(true);
            setPlayerName(inputLoginPlayer);
            clearGrid();
            MainMenu menu = new MainMenu(grid);
            grid = menu.newMainMenu();
        }
    }

    private void registerButtonAction() {
        String inputRegistrationPlayer = ((TextField) (grid.lookup("#RegistrationPlayerNameField"))).getText();
        String inputRegistrationPassword = encrypt(((TextField) (grid.lookup("#RegistrationPasswordField"))).getText());
        String inputConfirmPassword = encrypt(((TextField) (grid.lookup("#ConfirmRegistrationPasswordField"))).getText());

        ((Label) (grid.lookup("#LoginWarningsLabel"))).setText("");

        boolean notUniquePlayerName = false;
        for (Map.Entry entry : getPlayersMap().entrySet()) {
            if (entry.getKey().equals(inputRegistrationPlayer)) {
                notUniquePlayerName = true;
            }
        }
        if (inputRegistrationPlayer.equals("")
                || ((TextField) (grid.lookup("#RegistrationPasswordField"))).getText().equals("")
                || (((TextField) (grid.lookup("#ConfirmRegistrationPasswordField"))).getText().equals(""))) {
            ((Label) (grid.lookup("#RegistrationWarningsLabel"))).setText("All fields must be filled in");
        } else if ((inputRegistrationPlayer.toUpperCase()).equals("GUEST")) {
            ((Label) (grid.lookup("#RegistrationWarningsLabel"))).setText("Player Name GUEST is reserved for... GUESTS!");
        } else if (inputRegistrationPlayer.contains(";")) {
            ((Label) (grid.lookup("#RegistrationWarningsLabel"))).setText("Player name contains illegal character ';'.");
        } else if (notUniquePlayerName) {
            ((Label) (grid.lookup("#RegistrationWarningsLabel"))).setText("Player Name already exist!");
        } else if (!inputRegistrationPassword.equals(inputConfirmPassword)) {
            ((Label) (grid.lookup("#RegistrationWarningsLabel"))).setText("Password and its confirmation are different!");
        } else {
            getPlayersMap().put(inputRegistrationPlayer, inputConfirmPassword);
            saveMap(getPlayersMap(), getPlayersMapFile());
            ((Label) (grid.lookup("#RegistrationWarningsLabel"))).setText("Registration SUCCESS!\nNow you can login with your player name and password.");
            ((TextField) (grid.lookup("#RegistrationPlayerNameField"))).setText("");
            ((TextField) (grid.lookup("#RegistrationPasswordField"))).setText("");
            ((TextField) (grid.lookup("#ConfirmRegistrationPasswordField"))).setText("");
        }


    }


}
