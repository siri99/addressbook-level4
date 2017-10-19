package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import seedu.address.logic.commands.SortCommand;

import org.junit.Test;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_valid_arguments() throws Exception {

        // FilterType: Name
        final String validFilterType_Name = "name";
        String userInput = SortCommand.COMMAND_WORD + " " + validFilterType_Name;
        SortCommand expectedCommand = new SortCommand(validFilterType_Name);
        assertParseSuccess(parser, userInput, expectedCommand);

        // FilterType: Default
        final String validFilterType_Default = "default";
        userInput = SortCommand.COMMAND_WORD + " " + validFilterType_Default;
        expectedCommand = new SortCommand(validFilterType_Default);
        assertParseSuccess(parser, userInput, expectedCommand);

        // No filterType argument: Should set to default
        userInput = SortCommand.COMMAND_WORD + " ";
        expectedCommand = new SortCommand(validFilterType_Default);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalid_arguments() throws Exception {
        final String invalidFilterType = "abc";
        String userInput = SortCommand.COMMAND_WORD + " " + invalidFilterType;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // nothing at all
        assertParseFailure(parser, userInput, expectedMessage);
    }
}