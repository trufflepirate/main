package seedu.address.ui;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.NewResultAvailableEvent;
import seedu.address.logic.ListElementPointer;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private static final Pattern LOGIN_COMMAND_FORMAT = Pattern
        .compile("login(\\s+)(?<username>\\S+)(\\s)(?<password>\\S+)(?<arguments>.*)");
    private static final String PASSWORD_FORMAT = "\\S";
    //TODO: remove this when the acutal password can be stored
    private static final String PLACEHOLDER_PASSWORD = "SAIFBUTT";


    private final Logger logger = LogsCenter.getLogger(CommandBox.class);
    private final Logic logic;
    private ListElementPointer historySnapshot;
    //TODO: implement the actual storing of the passwords
    private String actualPassword;

    @FXML
    private TextField commandTextField;

    public CommandBox(Logic logic) {
        super(FXML);
        this.logic = logic;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        historySnapshot = logic.getHistorySnapshot();
    }

    /**
     * Handles the key press event, {@code keyEvent}.
     */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
        case UP:
            // As up and down buttons will alter the position of the caret,
            // consuming it causes the caret's position to remain unchanged
            keyEvent.consume();

            navigateToPreviousInput();
            break;
        case DOWN:
            keyEvent.consume();
            navigateToNextInput();
            break;
        case ENTER:
            keyEvent.consume();
            handleCommandEntered();
            break;
        case SPACE:
            //keyEvent.consume();
            //loginCheck();
            //break;
        default:
            loginCheck();
            // let JavaFx handle the keypress
        }
    }

    /**
     * Helper function to check if current text in textfield resembles the login command.
     */
    private void loginCheck() {
        final Matcher matcher = LOGIN_COMMAND_FORMAT.matcher(commandTextField.getText());
        if (matcher.matches()) {
            handlePasswordKeypresses(matcher);
        }
    }

    /**
     * handler to handle keypresses when the function resembles a login command
     */
    private void handlePasswordKeypresses(Matcher matcher) {
        final String hiddenPassword = matcher.group("password").replaceAll(PASSWORD_FORMAT, "*");
        String maskedCommandTextField = matcher.replaceAll("login$1$2$3" + hiddenPassword + "$5");
        commandTextField.replaceText(0, commandTextField.getLength(), maskedCommandTextField);
        commandTextField.positionCaret(commandTextField.getText().length());
    }


    //TODO:Implement working password shower. PLEASE DISABLE FOR TESTING OF OTHER FEATURES.

    /**
     * Handles the MouseEvent for the Cursor entering the text field, {@code mouseEvent}.
     */
    @FXML
    private void handleMouseEntered(MouseEvent mouseEvent) {
        actualPassword = commandTextField.getText();
        commandTextField.replaceText(0, commandTextField.getLength(), PLACEHOLDER_PASSWORD);
        commandTextField.positionCaret(commandTextField.getText().length());
    }

    /**
     * Handles the MouseEvent for the Cursor leaving the text field, {@code mouseEvent}.
     */
    @FXML
    private void handleMouseExited(MouseEvent mouseEvent) {
        commandTextField.replaceText(0, commandTextField.getLength(), actualPassword);
        commandTextField.positionCaret(commandTextField.getText().length());
    }

    /**
     * Updates the text field with the previous input in {@code historySnapshot},
     * if there exists a previous input in {@code historySnapshot}
     */
    private void navigateToPreviousInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasPrevious()) {
            return;
        }

        replaceText(historySnapshot.previous());
    }

    /**
     * Updates the text field with the next input in {@code historySnapshot},
     * if there exists a next input in {@code historySnapshot}
     */
    private void navigateToNextInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasNext()) {
            return;
        }

        replaceText(historySnapshot.next());
    }

    /**
     * Sets {@code CommandBox}'s text field with {@code text} and
     * positions the caret to the end of the {@code text}.
     */
    private void replaceText(String text) {
        commandTextField.setText(text);
        commandTextField.positionCaret(commandTextField.getText().length());
    }

    /**
     * Handles the Enter button pressed event.
     */
    private void handleCommandEntered() {
        try {
            CommandResult commandResult = logic.execute(commandTextField.getText());
            initHistory();
            historySnapshot.next();
            // process result of the command
            commandTextField.setText("");
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));

        } catch (CommandException | ParseException e) {
            initHistory();
            // handle command failure
            setStyleToIndicateCommandFailure();
            logger.info("Invalid command: " + commandTextField.getText());
            raise(new NewResultAvailableEvent(e.getMessage()));
        }
    }

    /**
     * Initializes the history snapshot.
     */
    private void initHistory() {
        historySnapshot = logic.getHistorySnapshot();
        // add an empty string to represent the most-recent end of historySnapshot, to be shown to
        // the user if she tries to navigate past the most-recent end of the historySnapshot.
        historySnapshot.add("");
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

}
