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

import static com.kodilla.Configurator.*;
import static com.kodilla.GamePlatform.*;

class LogPanel {

    private GridPane grid;
    private double numberOfRows = 15;
    private double numberOfColumns = 5;

    LogPanel(GridPane grid) {
        this.grid = grid;
    }

    GridPane newLogPanel() {
        setRowsAndColumns(grid, numberOfRows, numberOfColumns);
        addLabels();
        addTextFields();
        addButtons();

        return grid;
    }

    private String encrypt(String textToEncrypt) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(textToEncrypt.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void addLabels() {
        Label logPanelLabel = createLabel("LOG PANEL", "LogPanelLabel", FONT25);
        GridPane.setHalignment(logPanelLabel, HPos.CENTER);
        grid.add(logPanelLabel, 2, 0);

        Label loginLabel = createLabel("LOGIN", "LoginLabel", FONT20);
        GridPane.setHalignment(loginLabel, HPos.CENTER);
        grid.add(loginLabel, 2, 1);

        Label LoginWarningsLabel = createLabel("", "LoginWarningsLabel", FONT15, true);
        GridPane.setHalignment(LoginWarningsLabel, HPos.CENTER);
        grid.add(LoginWarningsLabel, 2, 4);

        Label registrationLabel = createLabel("OR REGISTER", "RegistrationLabel", FONT20);
        GridPane.setHalignment(registrationLabel, HPos.CENTER);
        grid.add(registrationLabel, 2, 5);

        Label registrationWarningsLabel = createLabel("", "RegistrationWarningsLabel", FONT15, true, Pos.CENTER, 600);
        GridPane.setHalignment(registrationWarningsLabel, HPos.CENTER);
        grid.add(registrationWarningsLabel, 1, 8, 3, 1);

        Label playAsGuestLabel = createLabel("OR PLAY AS GUEST", "PlayAsGuestLabel", FONT20);
        GridPane.setHalignment(playAsGuestLabel, HPos.CENTER);
        grid.add(playAsGuestLabel, 2, 9);
    }

    private void addTextFields() {
        TextField loginPlayerNameField = createTextField("LoginPlayerNameField");
        grid.add(loginPlayerNameField, 1, 2);

        PasswordField loginPasswordField = createPasswordField("LoginPasswordField");
        grid.add(loginPasswordField, 3, 2);

        TextField registrationPlayerNameField = createTextField("RegistrationPlayerNameField", 250);
        GridPane.setHalignment(registrationPlayerNameField, HPos.LEFT);
        grid.add(registrationPlayerNameField, 1, 6);

        PasswordField registrationPasswordField = createPasswordField("RegistrationPasswordField", 250);
        GridPane.setHalignment(registrationPasswordField, HPos.CENTER);
        grid.add(registrationPasswordField, 2, 6);

        PasswordField confirmRegistrationPasswordField = createPasswordField("ConfirmRegistrationPasswordField", 250);
        confirmRegistrationPasswordField.setPromptText("Confirm Password");
        GridPane.setHalignment(confirmRegistrationPasswordField, HPos.RIGHT);
        grid.add(confirmRegistrationPasswordField, 3, 6);
    }

    private void addButtons() {
        Button playAsGuestButton = createButton("PLAY AS GUEST", "PlayAsGuestButton", FONT30, (e) -> playAsGuestButtonAction());
        grid.add(playAsGuestButton, 2, 10);
        GridPane.setHalignment(playAsGuestButton, HPos.CENTER);

        Button loginButton = createButton("LOGIN", "LoginButton", FONT30, (e) -> loginButtonAction());
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setValignment(loginButton, VPos.BASELINE);
        grid.add(loginButton, 2, 3);

        Button registerButton = createButton("REGISTER", "RegisterButton", FONT30, (e) -> registerButtonAction());
        GridPane.setHalignment(registerButton, HPos.CENTER);
        grid.add(registerButton, 2, 7);
    }

    private void playAsGuestButtonAction() {
        GamePlatform.setPlayerName("GUEST");
        clearGrid(grid, numberOfRows, numberOfColumns);
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
            clearGrid(grid, numberOfRows, numberOfColumns);
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