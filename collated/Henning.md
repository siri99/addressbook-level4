# Henning
###### \build\reports\jacoco\coverage\html\seedu.address.logic.parser\ParserUtil.java.html
``` html
    public static Optional&lt;Score&gt; parseScore(Optional&lt;String&gt; score) throws IllegalValueException {
<span class="fc" id="L103">        requireNonNull(score);</span>
<span class="fc bfc" id="L104" title="All 2 branches covered.">        return score.isPresent() ? Optional.of(new Score(score.get())) : Optional.empty();</span>
    }
```
###### \build\reports\jacoco\coverage\html\seedu.address.model.person\Person.java.html
``` html
    public void setScore(Score score) {
<span class="fc" id="L128">        this.score.set(requireNonNull(score));</span>
<span class="fc" id="L129">    }</span>

    @Override
    public ObjectProperty&lt;Score&gt; scoreProperty() {
<span class="fc" id="L133">        return score;</span>
    }

    @Override
    public Score getScore() {
<span class="fc" id="L138">        return score.get();</span>
    }

```
###### \build\reports\jacoco\coverage\html\seedu.address.model.person\ReadOnlyPerson.java.html
``` html
<span class="fc" id="L62">                .append(&quot; &quot;)</span>
<span class="fc" id="L63">                .append(getScore())</span>
```
###### \build\reports\jacoco\coverage\html\seedu.address.model.person\Score.java.html
``` html
import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a persons score in the address book.
 * Guarantees: immutable; is valid.
 */
public class Score {

    public static final String MESSAGE_SCORE_CONSTRAINTS =
            &quot;The score should be a number between 0 and 9, with 9 being the best score and 0 the worst.&quot;;

    public static final String SCORE_VALIDATION_REGEX = &quot;\\d&quot;;
    public final String value;

<span class="fc" id="L17">    public Score(String score) throws IllegalValueException {</span>
<span class="pc bpc" id="L18" title="1 of 2 branches missed.">        if (score.matches(&quot;\0&quot;)) {</span>
<span class="nc" id="L19">            this.value = &quot;&quot;;</span>
        } else {
<span class="fc" id="L21">            String filteredScore = score.replaceAll(&quot;[^\\d]&quot;, &quot;&quot;);</span>
<span class="fc" id="L22">            String trimmedScore = filteredScore.trim();</span>
<span class="fc bfc" id="L23" title="All 2 branches covered.">            if (!isValidScore(trimmedScore)) {</span>
<span class="fc" id="L24">                throw new IllegalValueException(MESSAGE_SCORE_CONSTRAINTS);</span>
            }
<span class="fc" id="L26">            this.value = &quot;Group score: &quot; + trimmedScore;</span>
        }
<span class="fc" id="L28">    }</span>

    public boolean isValidScore(String value) {
<span class="fc" id="L31">        return value.matches(SCORE_VALIDATION_REGEX);</span>
    }

    @Override
    public String toString() {
<span class="fc" id="L36">        return value;</span>
    }


    @Override
    public boolean equals(Object other) {
<span class="nc bnc" id="L42" title="All 6 branches missed.">        return other == this || (other instanceof Score &amp;&amp; this.value.equals(((Score) other).value));</span>
    }

    @Override
    public int hashCode() {
<span class="nc" id="L47">        return value.hashCode();</span>
    }
}
```
###### \build\resources\main\view\PersonListCard.fxml
``` fxml
      <Label fx:id="score" styleClass="cell_small_label"  text="\$score" />
```
###### \out\production\resources\view\PersonListCard.fxml
``` fxml
      <Label fx:id="score" styleClass="cell_small_label"  text="\$score" />
```
###### \src\main\java\seedu\address\logic\parser\ParserUtil.java
``` java
    public static Optional<Score> parseScore(Optional<String> score) throws IllegalValueException {
        requireNonNull(score);
        return score.isPresent() ? Optional.of(new Score(score.get())) : Optional.empty();
    }
```
###### \src\main\java\seedu\address\model\person\Person.java
``` java
    public void setScore(Score score) {
        this.score.set(requireNonNull(score));
    }

    @Override
    public ObjectProperty<Score> scoreProperty() {
        return score;
    }

    @Override
    public Score getScore() {
        return score.get();
    }

```
###### \src\main\java\seedu\address\model\person\ReadOnlyPerson.java
``` java
                .append(" ")
                .append(getScore())
```
###### \src\main\java\seedu\address\model\person\Score.java
``` java
import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a persons score in the address book.
 * Guarantees: immutable; is valid.
 */
public class Score {

    public static final String MESSAGE_SCORE_CONSTRAINTS =
            "The score should be a number between 0 and 9, with 9 being the best score and 0 the worst.";

    public static final String SCORE_VALIDATION_REGEX = "\\d";
    public final String value;

    public Score(String score) throws IllegalValueException {
        if (score.matches("\0")) {
            this.value = "";
        } else {
            String filteredScore = score.replaceAll("[^\\d]", "");
            String trimmedScore = filteredScore.trim();
            if (!isValidScore(trimmedScore)) {
                throw new IllegalValueException(MESSAGE_SCORE_CONSTRAINTS);
            }
            this.value = "Group score: " + trimmedScore;
        }
    }

    public boolean isValidScore(String value) {
        return value.matches(SCORE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }


    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Score && this.value.equals(((Score) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
```
###### \src\main\resources\view\PersonListCard.fxml
``` fxml
      <Label fx:id="score" styleClass="cell_small_label"  text="\$score" />
```
