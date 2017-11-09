# Jacob
###### \build\reports\jacoco\coverage\html\seedu.address.ui\CommandBox.java.html
``` html
    /**
     * handles button events given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleUndoButtonAction(ActionEvent buttonEvent) {
        try {
<span class="nc" id="L178">            CommandResult commandResult = logic.execute(&quot;undo&quot;);</span>
<span class="nc" id="L179">            logger.info(&quot;Result: &quot; + commandResult.feedbackToUser);</span>
<span class="nc" id="L180">            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));</span>
<span class="nc" id="L181">        } catch (CommandException | ParseException e) {</span>
            // handle command failure
<span class="nc" id="L183">            logger.info(&quot;Delete call failed on index undo&quot;);</span>
<span class="nc" id="L184">            raise(new NewResultAvailableEvent(e.getMessage()));</span>
<span class="nc" id="L185">        }</span>

<span class="nc" id="L187">    }</span>

    /**
     * handles button events given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleRedoButtonAction(ActionEvent buttonEvent) {
        try {
<span class="nc" id="L196">            CommandResult commandResult = logic.execute(&quot;redo&quot;);</span>
<span class="nc" id="L197">            logger.info(&quot;Result: &quot; + commandResult.feedbackToUser);</span>
<span class="nc" id="L198">            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));</span>
<span class="nc" id="L199">        } catch (CommandException | ParseException e) {</span>
            // handle command failure
<span class="nc" id="L201">            logger.info(&quot;Delete call failed on index redo&quot;);</span>
<span class="nc" id="L202">            raise(new NewResultAvailableEvent(e.getMessage()));</span>
<span class="nc" id="L203">        }</span>

<span class="nc" id="L205">    }</span>
```
###### \build\reports\jacoco\coverage\html\seedu.address.ui\EditWindow.java.html
``` html
package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.NewResultAvailableEvent;
import seedu.address.commons.util.FxViewUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Controller for a help page
 */
public class EditWindow extends UiPart&lt;Region&gt; {

<span class="nc" id="L26">    private static final Logger logger = LogsCenter.getLogger(EditWindow.class);</span>
    private static final String ICON = &quot;/images/edit_icon.png&quot;;
    private static final String FXML = &quot;EditWindow.fxml&quot;;
    private static final String TITLE = &quot;Edit&quot;;

    private final int cardNum;
    private final Logic logic;

    @FXML
    private TextField nameField;
    @FXML
    private TextField tagsField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField scoreField;
    @FXML
    private Button edit;

    private final Stage dialogStage;

    public EditWindow(Logic inlogic, int card) {
<span class="nc" id="L52">        super(FXML);</span>
<span class="nc" id="L53">        logic = inlogic;</span>
<span class="nc" id="L54">        cardNum = card;</span>
<span class="nc" id="L55">        Scene scene = new Scene(getRoot());</span>
        //Null passed as the parent stage to make it non-modal.
<span class="nc" id="L57">        dialogStage = createDialogStage(TITLE, null, scene);</span>
<span class="nc" id="L58">        dialogStage.setMaxHeight(500);</span>
<span class="nc" id="L59">        dialogStage.setMaxWidth(800);</span>
<span class="nc" id="L60">        FxViewUtil.setStageIcon(dialogStage, ICON);</span>
<span class="nc" id="L61">    }</span>

    /**
     * Shows the edit window.
     * @throws IllegalStateException
     */
    public void show() {
<span class="nc" id="L68">        logger.fine(&quot;Showing edit window to modify person.&quot;);</span>
<span class="nc" id="L69">        dialogStage.showAndWait();</span>
<span class="nc" id="L70">    }</span>

    /**
     * handles button event given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleEditSubmitAction(ActionEvent buttonEvent) {
        try {
<span class="nc" id="L79">            String commandText = &quot;edit &quot; + cardNum;</span>
<span class="nc bnc" id="L80" title="All 2 branches missed.">            if (nameField.getText().length() != 0) {</span>
<span class="nc" id="L81">                commandText = commandText + &quot; n/&quot; + nameField.getText();</span>
            }
<span class="nc bnc" id="L83" title="All 2 branches missed.">            if (phoneField.getText().length() != 0) {</span>
<span class="nc" id="L84">                commandText = commandText + &quot; p/&quot; + phoneField.getText();</span>
            }
<span class="nc bnc" id="L86" title="All 2 branches missed.">            if (emailField.getText().length() != 0) {</span>
<span class="nc" id="L87">                commandText = commandText + &quot; e/&quot; + emailField.getText();</span>
            }
<span class="nc bnc" id="L89" title="All 2 branches missed.">            if (addressField.getText().length() != 0) {</span>
<span class="nc" id="L90">                commandText = commandText + &quot; a/&quot; + addressField.getText();</span>
            }
<span class="nc bnc" id="L92" title="All 2 branches missed.">            if (tagsField.getText().length() != 0) {</span>
<span class="nc" id="L93">                commandText = commandText + &quot; t/&quot; + tagsField.getText();</span>
            }
<span class="nc bnc" id="L95" title="All 2 branches missed.">            if (scoreField.getText().length() != 0) {</span>
<span class="nc" id="L96">                commandText = commandText + &quot; s/&quot; + scoreField.getText();</span>
            }
<span class="nc" id="L98">            CommandResult commandResult = logic.execute(commandText);</span>
            //Stage stage = (Stage) edit.getScene().getWindow();
            //stage.close(); //TODO: Get the window to close on editing.
<span class="nc" id="L101">            logger.info(&quot;Result: &quot; + commandResult.feedbackToUser);</span>
<span class="nc" id="L102">            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));</span>
<span class="nc" id="L103">        } catch (CommandException | ParseException e) {</span>
            // handle command failure
<span class="nc" id="L105">            logger.info(&quot;Edit call failed&quot;);</span>
<span class="nc" id="L106">            raise(new NewResultAvailableEvent(e.getMessage()));</span>
<span class="nc" id="L107">        }</span>
<span class="nc" id="L108">    }</span>
}
</pre><div class="footer"><span class="right">Created with <a href="http://www.eclemma.org/jacoco">JaCoCo</a> 0.7.1.201405082137</span></div></body></html>
```
###### \build\reports\jacoco\coverage\html\seedu.address.ui\PersonCard.java.html
``` html
    public PersonCard(ReadOnlyPerson person, int displayedIndex, Logic inlogic) {
<span class="fc" id="L73">        super(FXML);</span>
<span class="fc" id="L74">        this.person = person;</span>
<span class="fc" id="L75">        logic = inlogic;</span>
```
###### \build\reports\jacoco\coverage\html\seedu.address.ui\PersonCard.java.html
``` html
<span class="fc" id="L77">        cardNum = displayedIndex;</span>
<span class="fc" id="L78">        id.setText(cardNum + &quot;. &quot;);</span>
<span class="fc" id="L79">        initTags(person);</span>
<span class="fc" id="L80">        bindListeners(person);</span>
<span class="fc" id="L81">    }</span>

    /**
     * Provides a consistent color based on the string of a tag's value
     * ie, the same color will return for every call using 'friend' or any other tag.
     */

    private String mapTagToColor(String tagValue) {
<span class="fc bfc" id="L89" title="All 2 branches covered.">        if (!colorMapping.containsKey(tagValue)) {</span>
<span class="fc" id="L90">            colorMapping.put(tagValue, colors[tagValue.length() % colors.length]);</span>
        }
<span class="fc" id="L92">        return colorMapping.get(tagValue);</span>
    }

    /**
     * Binds the individual UI elements to observe their respective {@code Person} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyPerson person) {
<span class="fc" id="L100">        name.textProperty().bind(Bindings.convert(person.nameProperty()));</span>
<span class="fc" id="L101">        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));</span>
```
###### \build\reports\jacoco\coverage\html\seedu.address.ui\PersonCard.java.html
``` html

    /**
     * handles button events given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleDeleteButtonAction(ActionEvent buttonEvent) {
        try {
<span class="nc" id="L158">            CommandResult commandResult = new CommandResult(&quot;&quot;);</span>
<span class="nc" id="L159">            String justIndex = id.getText().substring(0, id.getText().length() - 2);</span>
<span class="nc" id="L160">            String delCommand = &quot;delete &quot; + justIndex;</span>
```
###### \build\reports\jacoco\coverage\html\seedu.address.ui\PersonCard.java.html
``` html
<span class="nc" id="L170">            logger.info(&quot;Result: &quot; + commandResult.feedbackToUser);</span>
<span class="nc" id="L171">            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));</span>
<span class="nc" id="L172">        } catch (CommandException | ParseException e) {</span>
            // handle command failure
<span class="nc" id="L174">            logger.info(&quot;Delete call failed on index &quot; + id.getText());</span>
<span class="nc" id="L175">            raise(new NewResultAvailableEvent(e.getMessage()));</span>

<span class="nc" id="L177">        }</span>
<span class="nc" id="L178">    }</span>

    /**
     * handles edit button presses, triggering a new window.
     * @param buttonEvent
     */
    @FXML
    private void handleEditButtonAction(ActionEvent buttonEvent) {
```
###### \build\reports\jacoco\coverage\html\seedu.address.ui\PersonCard.java.html
``` html
<span class="nc" id="L193">            EditWindow editWindow = new EditWindow(logic, cardNum);</span>
<span class="nc" id="L194">            editWindow.show();</span>
        }
<span class="nc" id="L196">        logger.info(&quot;Result: &quot; + commandResult.feedbackToUser);</span>
<span class="nc" id="L197">        raise(new NewResultAvailableEvent(commandResult.feedbackToUser));</span>
```
###### \build\resources\main\view\CommandBox.fxml
``` fxml
  <Button onAction='#handleUndoButtonAction' id="undo" prefWidth="120.0" minWidth="90.0" text="Undo"/>
  <Button onAction='#handleRedoButtonAction' id="redo" prefWidth="120.0" minWidth="90.0" text="Redo"/>
```
###### \build\resources\main\view\EditWindow.fxml
``` fxml

<?import javafx.scene.control.TextField?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.StackPane?>
<?import javafx.scene.layout.VBox?>

<StackPane fx:id="helpWindowRoot" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
  <VBox xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
    <TextField fx:id="nameField" prefWidth="200.0" promptText="Name"/>
    <TextField fx:id="tagsField" prefWidth="200.0" promptText="Tags"/>
    <TextField fx:id="phoneField" prefWidth="200.0" promptText="Phone"/>
    <TextField fx:id="addressField" prefWidth="200.0" promptText="Address"/>
    <TextField fx:id="emailField" prefWidth="200.0" promptText="Email"/>
    <TextField fx:id="scoreField" prefWidth="200.0" promptText="Group Score"/>
    <Button onAction='#handleEditSubmitAction' id="edit" prefWidth="120.0" minWidth="90.0" text="Edit"/>
  </VBox>
</StackPane>
```
###### \build\resources\main\view\PersonListCard.fxml
``` fxml

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.ColumnConstraints?>
<?import javafx.scene.layout.FlowPane?>
<?import javafx.scene.layout.GridPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.Region?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.Button?>

<HBox id="cardPane" fx:id="cardPane" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
  <GridPane HBox.hgrow="ALWAYS">
    <columnConstraints>
      <ColumnConstraints hgrow="SOMETIMES" minWidth="10" prefWidth="150" />
    </columnConstraints>
    <VBox alignment="CENTER_LEFT" minHeight="105" GridPane.columnIndex="0">
      <padding>
        <Insets top="5" right="5" bottom="5" left="15" />
      </padding>
      <HBox spacing="5" alignment="CENTER_LEFT">
        <Label fx:id="id" styleClass="cell_big_label">
          <minWidth>
            <!-- Ensures that the label text is never truncated -->
            <Region fx:constant="USE_PREF_SIZE" />
          </minWidth>
        </Label>
        <Label fx:id="name" text="\$first" styleClass="cell_big_label" />
        <Region prefHeight="10.0" prefWidth="1.0" HBox.hgrow="ALWAYS" />
        <Button onAction='#handleDeleteButtonAction' fx:id="delete" text="X"/>
      </HBox>
      <FlowPane fx:id="tags" />
      <Label fx:id="phone" styleClass="cell_small_label" text="\$phone" />
```
###### \out\production\resources\view\CommandBox.fxml
``` fxml
  <Button onAction='#handleUndoButtonAction' id="undo" prefWidth="120.0" minWidth="90.0" text="Undo"/>
  <Button onAction='#handleRedoButtonAction' id="redo" prefWidth="120.0" minWidth="90.0" text="Redo"/>
```
###### \out\production\resources\view\EditWindow.fxml
``` fxml

<?import javafx.scene.control.TextField?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.StackPane?>
<?import javafx.scene.layout.VBox?>

<StackPane fx:id="helpWindowRoot" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
  <VBox xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
    <TextField fx:id="nameField" prefWidth="200.0" promptText="Name"/>
    <TextField fx:id="tagsField" prefWidth="200.0" promptText="Tags"/>
    <TextField fx:id="phoneField" prefWidth="200.0" promptText="Phone"/>
    <TextField fx:id="addressField" prefWidth="200.0" promptText="Address"/>
    <TextField fx:id="emailField" prefWidth="200.0" promptText="Email"/>
    <TextField fx:id="scoreField" prefWidth="200.0" promptText="Group Score"/>
    <Button onAction='#handleEditSubmitAction' id="edit" prefWidth="120.0" minWidth="90.0" text="Edit"/>
  </VBox>
</StackPane>
```
###### \out\production\resources\view\PersonListCard.fxml
``` fxml

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.ColumnConstraints?>
<?import javafx.scene.layout.FlowPane?>
<?import javafx.scene.layout.GridPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.Region?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.Button?>

<HBox id="cardPane" fx:id="cardPane" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
  <GridPane HBox.hgrow="ALWAYS">
    <columnConstraints>
      <ColumnConstraints hgrow="SOMETIMES" minWidth="10" prefWidth="150" />
    </columnConstraints>
    <VBox alignment="CENTER_LEFT" minHeight="105" GridPane.columnIndex="0">
      <padding>
        <Insets top="5" right="5" bottom="5" left="15" />
      </padding>
      <HBox spacing="5" alignment="CENTER_LEFT">
        <Label fx:id="id" styleClass="cell_big_label">
          <minWidth>
            <!-- Ensures that the label text is never truncated -->
            <Region fx:constant="USE_PREF_SIZE" />
          </minWidth>
        </Label>
        <Label fx:id="name" text="\$first" styleClass="cell_big_label" />
        <Region prefHeight="10.0" prefWidth="1.0" HBox.hgrow="ALWAYS" />
        <Button onAction='#handleDeleteButtonAction' fx:id="delete" text="X"/>
      </HBox>
      <FlowPane fx:id="tags" />
      <Label fx:id="phone" styleClass="cell_small_label" text="\$phone" />
```
###### \src\main\java\seedu\address\ui\CommandBox.java
``` java
    /**
     * handles button events given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleUndoButtonAction(ActionEvent buttonEvent) {
        try {
            CommandResult commandResult = logic.execute("undo");
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));
        } catch (CommandException | ParseException e) {
            // handle command failure
            logger.info("Delete call failed on index undo");
            raise(new NewResultAvailableEvent(e.getMessage()));
        }

    }

    /**
     * handles button events given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleRedoButtonAction(ActionEvent buttonEvent) {
        try {
            CommandResult commandResult = logic.execute("redo");
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));
        } catch (CommandException | ParseException e) {
            // handle command failure
            logger.info("Delete call failed on index redo");
            raise(new NewResultAvailableEvent(e.getMessage()));
        }

    }
```
###### \src\main\java\seedu\address\ui\EditWindow.java
``` java
package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.NewResultAvailableEvent;
import seedu.address.commons.util.FxViewUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Controller for a help page
 */
public class EditWindow extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(EditWindow.class);
    private static final String ICON = "/images/edit_icon.png";
    private static final String FXML = "EditWindow.fxml";
    private static final String TITLE = "Edit";

    private final int cardNum;
    private final Logic logic;

    @FXML
    private TextField nameField;
    @FXML
    private TextField tagsField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField scoreField;
    @FXML
    private Button edit;

    private final Stage dialogStage;

    public EditWindow(Logic inlogic, int card) {
        super(FXML);
        logic = inlogic;
        cardNum = card;
        Scene scene = new Scene(getRoot());
        //Null passed as the parent stage to make it non-modal.
        dialogStage = createDialogStage(TITLE, null, scene);
        dialogStage.setMaxHeight(500);
        dialogStage.setMaxWidth(800);
        FxViewUtil.setStageIcon(dialogStage, ICON);
    }

    /**
     * Shows the edit window.
     * @throws IllegalStateException
     */
    public void show() {
        logger.fine("Showing edit window to modify person.");
        dialogStage.showAndWait();
    }

    /**
     * handles button event given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleEditSubmitAction(ActionEvent buttonEvent) {
        try {
            String commandText = "edit " + cardNum;
            if (nameField.getText().length() != 0) {
                commandText = commandText + " n/" + nameField.getText();
            }
            if (phoneField.getText().length() != 0) {
                commandText = commandText + " p/" + phoneField.getText();
            }
            if (emailField.getText().length() != 0) {
                commandText = commandText + " e/" + emailField.getText();
            }
            if (addressField.getText().length() != 0) {
                commandText = commandText + " a/" + addressField.getText();
            }
            if (tagsField.getText().length() != 0) {
                commandText = commandText + " t/" + tagsField.getText();
            }
            if (scoreField.getText().length() != 0) {
                commandText = commandText + " s/" + scoreField.getText();
            }
            CommandResult commandResult = logic.execute(commandText);
            //Stage stage = (Stage) edit.getScene().getWindow();
            //stage.close(); //TODO: Get the window to close on editing.
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));
        } catch (CommandException | ParseException e) {
            // handle command failure
            logger.info("Edit call failed");
            raise(new NewResultAvailableEvent(e.getMessage()));
        }
    }
}
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java
    public PersonCard(ReadOnlyPerson person, int displayedIndex, Logic inlogic) {
        super(FXML);
        this.person = person;
        logic = inlogic;
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java
        cardNum = displayedIndex;
        id.setText(cardNum + ". ");
        initTags(person);
        bindListeners(person);
    }

    /**
     * Provides a consistent color based on the string of a tag's value
     * ie, the same color will return for every call using 'friend' or any other tag.
     */

    private String mapTagToColor(String tagValue) {
        if (!colorMapping.containsKey(tagValue)) {
            colorMapping.put(tagValue, colors[tagValue.length() % colors.length]);
        }
        return colorMapping.get(tagValue);
    }

    /**
     * Binds the individual UI elements to observe their respective {@code Person} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyPerson person) {
        name.textProperty().bind(Bindings.convert(person.nameProperty()));
        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java

    /**
     * handles button events given to it by the fxml doc that it is set as controller for by the constructor in UiPart
     * @param buttonEvent
     */
    @FXML
    private void handleDeleteButtonAction(ActionEvent buttonEvent) {
        try {
            CommandResult commandResult = new CommandResult("");
            String justIndex = id.getText().substring(0, id.getText().length() - 2);
            String delCommand = "delete " + justIndex;
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));
        } catch (CommandException | ParseException e) {
            // handle command failure
            logger.info("Delete call failed on index " + id.getText());
            raise(new NewResultAvailableEvent(e.getMessage()));

        }
    }

    /**
     * handles edit button presses, triggering a new window.
     * @param buttonEvent
     */
    @FXML
    private void handleEditButtonAction(ActionEvent buttonEvent) {
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java
            EditWindow editWindow = new EditWindow(logic, cardNum);
            editWindow.show();
        }
        logger.info("Result: " + commandResult.feedbackToUser);
        raise(new NewResultAvailableEvent(commandResult.feedbackToUser));
```
###### \src\main\resources\view\CommandBox.fxml
``` fxml
  <Button onAction='#handleUndoButtonAction' id="undo" prefWidth="120.0" minWidth="90.0" text="Undo"/>
  <Button onAction='#handleRedoButtonAction' id="redo" prefWidth="120.0" minWidth="90.0" text="Redo"/>
```
###### \src\main\resources\view\EditWindow.fxml
``` fxml

<?import javafx.scene.control.TextField?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.StackPane?>
<?import javafx.scene.layout.VBox?>

<StackPane fx:id="helpWindowRoot" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
  <VBox xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
    <TextField fx:id="nameField" prefWidth="200.0" promptText="Name"/>
    <TextField fx:id="tagsField" prefWidth="200.0" promptText="Tags"/>
    <TextField fx:id="phoneField" prefWidth="200.0" promptText="Phone"/>
    <TextField fx:id="addressField" prefWidth="200.0" promptText="Address"/>
    <TextField fx:id="emailField" prefWidth="200.0" promptText="Email"/>
    <TextField fx:id="scoreField" prefWidth="200.0" promptText="Group Score"/>
    <Button onAction='#handleEditSubmitAction' id="edit" prefWidth="120.0" minWidth="90.0" text="Edit"/>
  </VBox>
</StackPane>
```
###### \src\main\resources\view\PersonListCard.fxml
``` fxml

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.ColumnConstraints?>
<?import javafx.scene.layout.FlowPane?>
<?import javafx.scene.layout.GridPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.Region?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.Button?>

<HBox id="cardPane" fx:id="cardPane" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
  <GridPane HBox.hgrow="ALWAYS">
    <columnConstraints>
      <ColumnConstraints hgrow="SOMETIMES" minWidth="10" prefWidth="150" />
    </columnConstraints>
    <VBox alignment="CENTER_LEFT" minHeight="105" GridPane.columnIndex="0">
      <padding>
        <Insets top="5" right="5" bottom="5" left="15" />
      </padding>
      <HBox spacing="5" alignment="CENTER_LEFT">
        <Label fx:id="id" styleClass="cell_big_label">
          <minWidth>
            <!-- Ensures that the label text is never truncated -->
            <Region fx:constant="USE_PREF_SIZE" />
          </minWidth>
        </Label>
        <Label fx:id="name" text="\$first" styleClass="cell_big_label" />
        <Region prefHeight="10.0" prefWidth="1.0" HBox.hgrow="ALWAYS" />
        <Button onAction='#handleDeleteButtonAction' fx:id="delete" text="X"/>
      </HBox>
      <FlowPane fx:id="tags" />
      <Label fx:id="phone" styleClass="cell_small_label" text="\$phone" />
```
