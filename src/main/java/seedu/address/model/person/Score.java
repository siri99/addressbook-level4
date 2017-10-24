package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

public class Score {

    public static final String MESSAGE_SCORE_CONSTRAINTS =
            "The score should be a number between 0 and 9, with 9 being the best score and 0 the worst.";

    public static final String SCORE_VALIDATION_REGEX = "\\d";
    public final String value;

    public Score(String score) throws IllegalValueException{
        requireNonNull(score);
        String trimmedScore = score.trim();
        if(!isValidScore(score)){
            throw new IllegalValueException(MESSAGE_SCORE_CONSTRAINTS);
        }
        this.value=trimmedScore;
    }

    public boolean isValidScore(String value){
        return value.matches(SCORE_VALIDATION_REGEX);
    }

    


}
