package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Sorts all persons in the address book by indicated format.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_WORD_ALIAS = "so";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the address book in ascending order by an "
            + "indicated format. "
            + "Example: " + COMMAND_WORD + " (no parameter: default sorts by name)\n"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SUCCESS = "Sorted successfully, Listing all persons below";

    public static final String MESSAGE_ARGUMENTS = "Filter type: %1$s";

    private String filterType;

    public SortCommand (String filterType) {
        //Filter type can be null to signify default listing
        this.filterType = filterType;
    }

    @Override
    public CommandResult execute() throws CommandException {
        model.sortFilteredPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand e = (SortCommand) other;
        return filterType.equals(e.filterType);
    }
}
