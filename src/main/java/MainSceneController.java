import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import java.util.Optional;
import javafx.application.Platform;


public class MainSceneController {

    @FXML
    private Label label00, label01, label02, label03, label04;
    @FXML
    private Label label10, label11, label12, label13, label14;
    @FXML
    private Label label20, label21, label22, label23, label24;
    @FXML
    private Label label30, label31, label32, label33, label34;
    @FXML
    private Label label40, label41, label42, label43, label44;
    @FXML
    private Label label50, label51, label52, label53, label54;
    @FXML
    private Label messageLabel;

    @FXML
    private Button buttonQ, buttonW, buttonE, buttonR, buttonT, buttonY, buttonU, buttonI, buttonO, buttonP,
            buttonÅ, buttonA, buttonS, buttonD, buttonF, buttonG, buttonH, buttonJ, buttonK, buttonL,
            buttonÖ, buttonÄ, buttonEnter, buttonZ, buttonX, buttonC, buttonV, buttonB, buttonN, buttonM,
            buttonBackSpace;

    private Label[][] board = new Label[6][5];

    @FXML
    private Button cheatButton;
    @FXML
    private Button addWordButton;

    private Map<String, Button> letterToButton = new HashMap<>();
    private int currentRow = 0;
    private int currentCol = 0;
    private WordCheck wc;
    private String hiddenWord;
    private List<PauseTransition> activeTransitions = new ArrayList<>();


    @FXML
    public void initialize() {
        board[0][0] = label00;
        board[0][1] = label01;
        board[0][2] = label02;
        board[0][3] = label03;
        board[0][4] = label04;
        board[1][0] = label10;
        board[1][1] = label11;
        board[1][2] = label12;
        board[1][3] = label13;
        board[1][4] = label14;
        board[2][0] = label20;
        board[2][1] = label21;
        board[2][2] = label22;
        board[2][3] = label23;
        board[2][4] = label24;
        board[3][0] = label30;
        board[3][1] = label31;
        board[3][2] = label32;
        board[3][3] = label33;
        board[3][4] = label34;
        board[4][0] = label40;
        board[4][1] = label41;
        board[4][2] = label42;
        board[4][3] = label43;
        board[4][4] = label44;
        board[5][0] = label50;
        board[5][1] = label51;
        board[5][2] = label52;
        board[5][3] = label53;
        board[5][4] = label54;
        letterToButton.put("Q", buttonQ);
        letterToButton.put("W", buttonW);
        letterToButton.put("E", buttonE);
        letterToButton.put("R", buttonR);
        letterToButton.put("T", buttonT);
        letterToButton.put("Y", buttonY);
        letterToButton.put("U", buttonU);
        letterToButton.put("I", buttonI);
        letterToButton.put("O", buttonO);
        letterToButton.put("P", buttonP);
        letterToButton.put("Å", buttonÅ);
        letterToButton.put("A", buttonA);
        letterToButton.put("S", buttonS);
        letterToButton.put("D", buttonD);
        letterToButton.put("F", buttonF);
        letterToButton.put("G", buttonG);
        letterToButton.put("H", buttonH);
        letterToButton.put("J", buttonJ);
        letterToButton.put("K", buttonK);
        letterToButton.put("L", buttonL);
        letterToButton.put("Ö", buttonÖ);
        letterToButton.put("Ä", buttonÄ);
        letterToButton.put("Z", buttonZ);
        letterToButton.put("X", buttonX);
        letterToButton.put("C", buttonC);
        letterToButton.put("V", buttonV);
        letterToButton.put("B", buttonB);
        letterToButton.put("N", buttonN);
        letterToButton.put("M", buttonM);
        try {
            wc = new WordCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }
        hiddenWord = wc.getWord().toLowerCase();
    }

    @FXML
    private void handleLetterInput(ActionEvent event) {
        if (currentCol < 5) {
            Button button = (Button) event.getSource();
            String letter = button.getText();
            board[currentRow][currentCol].setText(letter.toUpperCase());
            currentCol++;
        }
    }

    @FXML
    private void handleBackspace(ActionEvent event) {
        if (currentCol > 0) {
            currentCol--;
            board[currentRow][currentCol].setText("");
        }
    }

    @FXML
    private void handleEnter(ActionEvent event) {
        if (currentCol == 5) {
            StringBuilder guessBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                guessBuilder.append(board[currentRow][i].getText());
            }

            String guessedWord = guessBuilder.toString().toLowerCase();

            if (!wc.getList().contains(guessedWord)) {
                messageLabel.setText("Ordet finns inte i vår ordbank. Försök igen!");
                return;
            }
            messageLabel.setText("");
            applyColorWithDelay(currentRow, false, guessedWord);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                if (guessedWord.equals(hiddenWord)) {
                    messageLabel.setText("Rätt ord! Bra jobbat!");

                    Platform.runLater(() -> {
                        boolean playAgain = AskPlayAgain();
                        if (playAgain) {
                            resetGame();
                        } else {
                            Stage stage = (Stage) messageLabel.getScene().getWindow();
                            stage.close();
                        }
                    });
                } else if (currentRow == 5) {
                    messageLabel.setText("Du förlorade! Rätt ord var: " + hiddenWord.toUpperCase());
                    Platform.runLater(() -> {
                        boolean playAgain = AskPlayAgain();
                        if (playAgain) {
                            resetGame();
                        } else {
                            Stage stage = (Stage) messageLabel.getScene().getWindow();
                            stage.close();
                        }
                    });
                } else {
                    messageLabel.setText("Fel ord, försök igen!");
                }

                currentRow++;
                currentCol = 0;
            });
            pause.play();
        }
    }

    @FXML
    private void handleCheat(ActionEvent event) {
        messageLabel.setText(hiddenWord.toUpperCase());
    }

    @FXML
    private void handleAddWord(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Lägg till ett ord i ordbanken");
        dialog.setContentText("Fem bokstäver, gärna ett riktigt ord");
        dialog.setHeaderText(null);
        dialog.showAndWait().ifPresent(word -> {
            String cleanedWord = word.trim().toLowerCase();
            try {
                if (wc.contains(cleanedWord)) {
                    messageLabel.setText("Ordet \"" + word + "\" finns redan med.");
                } else if (wc.addWord(cleanedWord)) {
                    messageLabel.setText("Ordet \"" + word + "\" har sparats!");
                } else {
                    messageLabel.setText("Ordet måste vara 5 bokstäver");
                }
            } catch (IOException e) {
                messageLabel.setText("Fel uppstod");
                e.printStackTrace();
            }
        });
    }

    private void updateKeyboardColors(String guessedWord) {
        for (int i = 0; i < 5; i++) {
            final int col = i;

            String letter = guessedWord.substring(i, i + 1);
            Label currentLabel = board[currentRow][i];

            String upperLetter = letter.toUpperCase();
            Button keyButton = letterToButton.get(upperLetter);
            String hiddenLetter = String.valueOf(hiddenWord.charAt(i));

            if (keyButton != null && letter.equals(hiddenLetter)) {
                keyButton.setStyle("-fx-background-color: #6aaa64; -fx-text-fill: white;");
            }
        }

        for (int i = 0; i < 5; i++) {
            String letter = guessedWord.substring(i, i + 1);
            String upperLetter = letter.toUpperCase();
            Button keyButton = letterToButton.get(upperLetter);

            if (keyButton != null && hiddenWord.contains(letter)) {
                String currentStyle = keyButton.getStyle();
                // Om knappen inte redan är grön
                if (!currentStyle.contains("#6aaa64")) {
                    keyButton.setStyle("-fx-background-color: #c9b458; -fx-text-fill: white;");
                }
            }
        }
        
        for (int i = 0; i < 5; i++) {
            String letter = guessedWord.substring(i, i + 1);
            String upperLetter = letter.toUpperCase();
            Button keyButton = letterToButton.get(upperLetter);

            if (keyButton != null && !hiddenWord.contains(letter)) {
                String currentStyle = keyButton.getStyle();
                if (!currentStyle.contains("#6aaa64") && !currentStyle.contains("#c9b458")) {
                    keyButton.setStyle("-fx-background-color: #787c7e; -fx-text-fill: white;");
                }
            }
        }
    }

    private void applyColorWithDelay(int row, boolean correctGuess, String guessedWord) {
        activeTransitions.clear();
        for (int i = 0; i < 5; i++) {
            final int col = i;

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5 * (col + 1)));

            pause.setOnFinished(e -> {
                String letter = board[row][col].getText().toLowerCase();

                if (correctGuess) {
                    board[row][col].setStyle("-fx-background-color: #6aaa64; -fx-text-fill: white;");
                } else {
                    if (letter.equals(String.valueOf(hiddenWord.charAt(col)))) {
                        board[row][col].setStyle("-fx-background-color: #6aaa64; -fx-text-fill: white;");
                    } else if (hiddenWord.contains(letter)) {
                        board[row][col].setStyle("-fx-background-color: #c9b458; -fx-text-fill: white;");
                    } else {
                        board[row][col].setStyle("-fx-background-color: #787c7e; -fx-text-fill: white;");
                    }
                }
            });
            activeTransitions.add(pause);
            pause.play();
        }

        PauseTransition finalPause = new PauseTransition(Duration.seconds(0.5 * 5 + 0.1));
        finalPause.setOnFinished(e -> updateKeyboardColors(guessedWord));
        activeTransitions.add(finalPause);
        finalPause.play();
    }

    private void resetGame() {
        for (PauseTransition pt : activeTransitions) {
            pt.stop();
        }
        activeTransitions.clear();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                board[row][col].setText("");
                board[row][col].setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1;");
            }
        }

        for (Button btn: letterToButton.values()) {
                btn.setStyle("");
        }

        messageLabel.setText("Välkommen till ett nytt spel!");

        currentRow = 0;
        currentCol = 0;

        hiddenWord = wc.pickNewWord().toLowerCase();
    }

    private boolean AskPlayAgain() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Play Again?");
        alert.setHeaderText("Vill du spela igen?");
        alert.setContentText("Välj Ja för att spela igen, eller Nej för att avsluta.");

        ButtonType yesButton = new ButtonType("Ja");
        ButtonType noButton = new ButtonType("Nej", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }
}