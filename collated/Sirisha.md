# Sirisha
###### \src\main\java\seedu\address\commons\util\StringUtil.java
``` java
    /**
     * Returns true if the {@code sentence} contains any part of the {@code word}.
     *   Ignores case, and full word match is not required, word just has to be a substring.
     *   <br>examples:<pre>
     *       containsSubstringOfWord("ABc def", "abc") == true
     *       containsSubstringOfWord("ABc def", "DEF") == true
     *       containsSubstringOfWord("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word with minimum 2 characters
     */
    public static boolean containsSubstringOfWord(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        if (preppedWord.length() >= 2) {
            for (String wordInSentence : wordsInPreppedSentence) {
                if (wordInSentence.toLowerCase().contains(preppedWord.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
```
###### \src\main\java\seedu\address\commons\util\StringUtil.java
``` java

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
```
###### \src\main\java\seedu\address\logic\commands\FavCommand.java
``` java
package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Adds a person identified using the index into the Favourite list.
 */
public class FavCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "fav";
    public static final String COMMAND_WORD_ALIAS = "fv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a person identified by the index number used to the Favourite list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAV_PERSON_SUCCESS = "Added person to Favourite list: %1$s";

    private final Index targetIndex;

    public FavCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToFav = lastShownList.get(targetIndex.getZeroBased());
        try {
            model.addFavouritePerson(personToFav);
        } catch (DuplicatePersonException e) {
            assert false : "The target person is already in the favourite list";
        }

        return new CommandResult(String.format(MESSAGE_FAV_PERSON_SUCCESS, personToFav));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavCommand // instanceof handles nulls
                && this.targetIndex.equals(((FavCommand) other).targetIndex)); // state check
    }
}
```
###### \src\main\java\seedu\address\logic\commands\FavCommand.java
``` java

```
###### \src\main\java\seedu\address\logic\commands\FavouriteListCommand.java
``` java
package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FAVOURITE_PERSONS;

/**
 * Displays the Favourite list.
 */

public class FavouriteListCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "favlist";
    public static final String COMMAND_WORD_ALIAS = "fl";

    public static final String MESSAGE_SUCCESS = "Listed all favourite contacts";

    @Override
    public CommandResult executeUndoableCommand() {
        model.changeListTo(COMMAND_WORD);
        model.updateFilteredFavouritePersonList(PREDICATE_SHOW_ALL_FAVOURITE_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
```
###### \src\main\java\seedu\address\logic\commands\FavouriteListCommand.java
``` java

```
###### \src\main\java\seedu\address\logic\commands\SortCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

/**
* Sorts all persons in the address book by indicated format.
*/
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_WORD_ALIAS = "so";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the address book in alphabetical "
            + "order of names \n Example: \" + COMMAND_WORD";

    public static final String MESSAGE_SUCCESS = "Sorted successfully, Listing all persons below";

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
        return false;
    }
}
```
###### \src\main\java\seedu\address\logic\commands\SortCommand.java
``` java

```
###### \src\main\java\seedu\address\logic\commands\UnfavCommand.java
``` java
package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Removes a person identified using it's last displayed index from the favourite list.
 */
public class UnfavCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "unfav";
    public static final String COMMAND_WORD_ALIAS = "unfv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove a person identified by the index number from the Favourite list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNFAV_PERSON_SUCCESS = "Removed person from favourite list: %1$s";

    private final Index targetIndex;

    public UnfavCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredFavouritePersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToUnfav = lastShownList.get(targetIndex.getZeroBased());

        try {
            model.removeFavouritePerson(personToUnfav);
        } catch (PersonNotFoundException e) {
            assert false : "The selected person is not in favourite list";
        }

        return new CommandResult(String.format(MESSAGE_UNFAV_PERSON_SUCCESS, personToUnfav));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnfavCommand // instanceof handles nulls
                && this.targetIndex.equals(((UnfavCommand) other).targetIndex)); // state check
    }

}
```
###### \src\main\java\seedu\address\logic\commands\UnfavCommand.java
``` java

```
###### \src\main\java\seedu\address\logic\Logic.java
``` java
    /** Returns an unmodifiable view of the filtered list of favourited persons */
    ObservableList<ReadOnlyPerson> getFilteredFavoritePersonList();

    String getCurrentList();
```
###### \src\main\java\seedu\address\logic\Logic.java
``` java

    /** Returns the list of input entered by the user, encapsulated in a {@code ListElementPointer} object */
    ListElementPointer getHistorySnapshot();
}
```
###### \src\main\java\seedu\address\logic\LogicManager.java
``` java
    @Override
    public ObservableList<ReadOnlyPerson> getFilteredFavoritePersonList() {
        return model.getFilteredFavouritePersonList();
    }

    @Override
    public String getCurrentList() {
        return model.getCurrentList();
    }
```
###### \src\main\java\seedu\address\logic\LogicManager.java
``` java

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
```
###### \src\main\java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case SortCommand.COMMAND_WORD:
        case SortCommand.COMMAND_WORD_ALIAS:
            return new SortCommand();
```
###### \src\main\java\seedu\address\logic\parser\AddressBookParser.java
``` java

        case DeleteCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD_ALIAS:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD_ALIAS:
            return new ClearCommand();

```
###### \src\main\java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case FavCommand.COMMAND_WORD:
        case FavCommand.COMMAND_WORD_ALIAS:
            return new FavCommandParser().parse(arguments);

        case UnfavCommand.COMMAND_WORD:
        case UnfavCommand.COMMAND_WORD_ALIAS:
            return new UnfavCommandParser().parse(arguments);
```
###### \src\main\java\seedu\address\logic\parser\AddressBookParser.java
``` java

        case FindCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD_ALIAS:
            return new FindCommandParser().parse(arguments);

```
###### \src\main\java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case FavouriteListCommand.COMMAND_WORD:
        case FavouriteListCommand.COMMAND_WORD_ALIAS:
            return new FavouriteListCommand();
```
###### \src\main\java\seedu\address\logic\parser\AddressBookParser.java
``` java

        case ListCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD_ALIAS:
            return new ListCommand();

        case HistoryCommand.COMMAND_WORD:
        case HistoryCommand.COMMAND_WORD_ALIAS:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_WORD_ALIAS:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_WORD_ALIAS:
            return new RedoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
```
###### \src\main\java\seedu\address\logic\parser\FavCommandParser.java
``` java
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.FavCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FavCommand object
 */

public class FavCommandParser implements Parser<FavCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavCommand
     * and returns an FavCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */


    public FavCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavCommand.MESSAGE_USAGE));
        }
    }
}
```
###### \src\main\java\seedu\address\logic\parser\FavCommandParser.java
``` java

```
###### \src\main\java\seedu\address\logic\parser\UnfavCommandParser.java
``` java
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.UnfavCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new UnfavCommand object
 */

public class UnfavCommandParser implements Parser<UnfavCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnfavCommand
     * and returns an UnfavCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */

    public UnfavCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnfavCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavCommand.MESSAGE_USAGE));
        }
    }
}
```
###### \src\main\java\seedu\address\logic\parser\UnfavCommandParser.java
``` java

```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java
    public void setFavouritePersons(List<? extends ReadOnlyPerson> persons) throws DuplicatePersonException {
        this.favouritePersons.setPersons(persons);
    }
```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java

    public void setTags(Set<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        try {
            setPersons(newData.getPersonList());
        } catch (DuplicatePersonException e) {
            assert false : "AddressBooks should not have duplicate persons";
        }

```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java
        try {
            setFavouritePersons(newData.getFavouritePersonList());
        } catch (DuplicatePersonException e) {
            assert false : "AddressBooks should not have duplicate persons";
        }
```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java

        setTags(new HashSet<>(newData.getTagList()));
        syncMasterTagListWith(persons);
    }

    //// person-level operations

    /**
     * Adds a person to the address book.
     * Also checks the new person's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the person to point to those in {@link #tags}.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(ReadOnlyPerson p) throws DuplicatePersonException {
        Person newPerson = new Person(p);
        syncMasterTagListWith(newPerson);
        // TODO: the tags master list will be updated even though the below line fails.
        // This can cause the tags master list to have additional tags that are not tagged to any person
        // in the person list.
        persons.add(newPerson);
    }

```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java
    /** Adds favourite person to addressBook
     * Also updates new tags found (if any)
     */
    public void addFavouritePerson(ReadOnlyPerson p) throws DuplicatePersonException {
        Person newPerson = new Person(p);
        syncMasterTagListWith(newPerson);
        favouritePersons.add(newPerson);
    }
```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java

    /**
     * Replaces the given person {@code target} in the list with {@code editedReadOnlyPerson}.
     * {@code AddressBook}'s tag list will be updated with the tags of {@code editedReadOnlyPerson}.
     *
     * Also replaces the given person {@code target} in the Favourite list with {@code editedReadOnlyPerson}
     * if same person is listed on Favourite list too.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *                                  another existing person in the list.
     * @throws PersonNotFoundException  if {@code target} could not be found in the list.
     * @see #syncMasterTagListWith(Person)
     */
    public void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedReadOnlyPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedReadOnlyPerson);

        Person editedPerson = new Person(editedReadOnlyPerson);
        syncMasterTagListWith(editedPerson);
        // TODO: the tags master list will be updated even though the below line fails.
        // This can cause the tags master list to have additional tags that are not tagged to any person
        // in the person list.
        persons.setPerson(target, editedPerson);
        /* Line makes sure that Favourite List is synced with main list, if a person is
         * edited from the main list and is also present on the Favourite List,
         * the person will be automatically edited from fav list too
         */
        if (favouritePersons.contains(target)) {
            favouritePersons.setPerson(target, editedPerson);
        }
    }

```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java
    /**
     * Sorts the list of people in the address book.
     */
    public void sortPersons() {
        //this.persons.sortPersons();
        persons.sortPersons();
    }
```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java

    /**
     * Ensures that every tag in this person:
     * - exists in the master list {@link #tags}
     * - points to a Tag object in the master list
     */
    private void syncMasterTagListWith(Person person) {
        final UniqueTagList personTags = new UniqueTagList(person.getTags());
        tags.mergeFrom(personTags);

        // Create map with values = tag object references in the master list
        // used for checking person tag references
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        tags.forEach(tag -> masterTagObjects.put(tag, tag));

        // Rebuild the list of person tags to point to the relevant tags in the master tag list.
        final Set<Tag> correctTagReferences = new HashSet<>();
        personTags.forEach(tag -> correctTagReferences.add(masterTagObjects.get(tag)));
        person.setTags(correctTagReferences);
    }

    /**
     * Ensures that every tag in these persons:
     * - exists in the master list {@link #tags}
     * - points to a Tag object in the master list
     *
     * @see #syncMasterTagListWith(Person)
     */
    private void syncMasterTagListWith(UniquePersonList persons) {
        persons.forEach(this::syncMasterTagListWith);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * Also removes {@code key} from the Favourite List if same person is listed on Fav list too.
     * @throws PersonNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removePerson(ReadOnlyPerson key) throws PersonNotFoundException {

        if (persons.remove(key)) {
            /* Line makes sure that Favourite List is synced with main list, if a person is
             * deleted from the main list and is also present on the Favourite List,
             * the person will be automatically deleted from fav list too
             */
            if (favouritePersons.contains(key)) {
                favouritePersons.remove(key);
            }
            return true; // returns true if Person is in the main list irrespective of Fav list
        } else {
            throw new PersonNotFoundException();
        }
    }

```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java
    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws PersonNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removeFavouritePerson(ReadOnlyPerson key) throws PersonNotFoundException {
        if (favouritePersons.remove(key)) {
            return true;
        } else {
            throw new PersonNotFoundException();
        }
    }
```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java

    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asObservableList().size() + " persons, " + favouritePersons.asObservableList().size()
                + " favourite persons, " + tags.asObservableList().size() + " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<ReadOnlyPerson> getPersonList() {
        return persons.asObservableList();
    }

```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java
    @Override
    public ObservableList<ReadOnlyPerson> getFavouritePersonList() {
        return favouritePersons.asObservableList();
    }
```
###### \src\main\java\seedu\address\model\AddressBook.java
``` java

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.persons.equals(((AddressBook) other).persons)
                && this.favouritePersons.equals(((AddressBook) other).favouritePersons)
                && this.tags.equalsOrderInsensitive(((AddressBook) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        //return Objects.hash(persons, tags);
        return Objects.hash(persons, favouritePersons, tags);
    }
}

```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java
        filteredFavouritePersons = new FilteredList<>(this.addressBook.getFavouritePersonList());
```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java
        this.currentList = "list";
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        addressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(addressBook));
    }

    @Override
    public synchronized void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException {
        addressBook.removePerson(target);
        indicateAddressBookChanged();
    }

```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java
    @Override
    public synchronized void removeFavouritePerson(ReadOnlyPerson person) throws PersonNotFoundException {
        addressBook.removeFavouritePerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }
```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java

    @Override
    public synchronized void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java
    @Override
    public synchronized void addFavouritePerson(ReadOnlyPerson person) throws DuplicatePersonException {
        addressBook.addFavouritePerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }
```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java

    @Override
    public void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireAllNonNull(target, editedPerson);

        addressBook.updatePerson(target, editedPerson);
        indicateAddressBookChanged();
    }

```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java
    @Override
    public void changeListTo(String listName) {
        raise(new ChangeInternalListEvent(listName));
    }

    @Override
    public String getCurrentList() {
        return currentList;
    }

    @Override
    public void setCurrentList(String currentList) {
        this.currentList =  currentList;
    }
```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code ReadOnlyPerson} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
        setCurrentList("list");
        return FXCollections.unmodifiableObservableList(filteredPersons);
    }

```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java
    @Override
    public ObservableList<ReadOnlyPerson> getFilteredFavouritePersonList() {
        setCurrentList("favlist");
        return FXCollections.unmodifiableObservableList(filteredFavouritePersons);
    }
```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java

    @Override
    public void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java
    @Override
    public void updateFilteredFavouritePersonList(Predicate<ReadOnlyPerson> predicate) {
        requireNonNull(predicate);
        filteredFavouritePersons.setPredicate(predicate);
    }

    @Override
    public void sortFilteredPersonList() {
        addressBook.sortPersons();
        indicateAddressBookChanged();
    }
```
###### \src\main\java\seedu\address\model\ModelManager.java
``` java

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && filteredPersons.equals(other.filteredPersons);
    }

}
```
###### \src\main\java\seedu\address\model\person\NameContainsKeywordsPredicate.java
``` java
        Set<String> s1 = new HashSet<String>(keywords);
        String[] tagList = person.getOnlyTags().split(" ");
        Set<String> s2 = new HashSet<String>(Arrays.asList(tagList));

        return ((keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)))
                || (!Collections.disjoint(s2, s1))) || (keywords.stream().anyMatch(keyword ->
                StringUtil.containsSubstringOfWord(person.getName().fullName, keyword)));
```
###### \src\main\java\seedu\address\model\person\NameContainsKeywordsPredicate.java
``` java
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
```
###### \src\main\java\seedu\address\model\person\UniquePersonList.java
``` java
    /**
     * Sorts the  list of people in alphabhetical order of names.
     */
    public void sortPersons() {

        Comparator<ReadOnlyPerson> personComparator = new Comparator<ReadOnlyPerson>() {

            public int compare(ReadOnlyPerson person1, ReadOnlyPerson person2) {

                String personName1 = person1.getName().toString();
                String personName2 = person2.getName().toString();

                return personName1.compareTo(personName2);
            }

        };

        FXCollections.sort(internalList, personComparator);
    }
```
###### \src\main\java\seedu\address\model\person\UniquePersonList.java
``` java

    public void setPersons(UniquePersonList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setPersons(List<? extends ReadOnlyPerson> persons) throws DuplicatePersonException {
        final UniquePersonList replacement = new UniquePersonList();
        for (final ReadOnlyPerson person : persons) {
            replacement.add(new Person(person));
        }
        setPersons(replacement);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ReadOnlyPerson> asObservableList() {
        return FXCollections.unmodifiableObservableList(mappedList);
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && this.internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
```
###### \src\main\java\seedu\address\storage\XmlSerializableAddressBook.java
``` java
        favouritePersons.addAll(src.getFavouritePersonList().stream().map(XmlAdaptedPerson::new)
                .collect(Collectors.toList()));
```
###### \src\main\java\seedu\address\storage\XmlSerializableAddressBook.java
``` java
        tags.addAll(src.getTagList().stream().map(XmlAdaptedTag::new).collect(Collectors.toList()));
    }

    @Override
    public ObservableList<ReadOnlyPerson> getPersonList() {
        final ObservableList<ReadOnlyPerson> persons = this.persons.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                //TODO: better error handling
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(persons);
    }

```
###### \src\main\java\seedu\address\storage\XmlSerializableAddressBook.java
``` java
    @Override
    public ObservableList<ReadOnlyPerson> getFavouritePersonList() {
        final ObservableList<ReadOnlyPerson> favouritePersons = this.favouritePersons.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                //TODO: better error handling
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(favouritePersons);
    }
```
###### \src\main\java\seedu\address\storage\XmlSerializableAddressBook.java
``` java

    @Override
    public ObservableList<Tag> getTagList() {
        final ObservableList<Tag> tags = this.tags.stream().map(t -> {
            try {
                return t.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                //TODO: better error handling
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(tags);
    }

}
```
###### \src\main\java\seedu\address\ui\ChangeInternalListEvent.java
``` java
package seedu.address.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * Creates an event to change the Internal List
 */
public class ChangeInternalListEvent extends BaseEvent {

    //private String listName;
    private final String listName;

    public ChangeInternalListEvent(String listName) {
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
```
###### \src\main\java\seedu\address\ui\ChangeInternalListEvent.java
``` java

```
###### \src\main\java\seedu\address\ui\CommandBox.java
``` java
    /**
     * handles button events given to it by the fxml document for which it is set as controller by
     * a constructor in UiPart. handleFavListButton event handles the event when the Favlist button
     * containing a 'star' icon to represent a fav list is clicked.
     * @param buttonEvent
     */
    @FXML
    private void handleFavlistButtonAction(ActionEvent buttonEvent) {
        try {
            CommandResult commandResult = logic.execute("favlist");
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));
        } catch (CommandException | ParseException e) {
            // handling command failure
            logger.info("Delete call failed on index favlist");
            raise(new NewResultAvailableEvent(e.getMessage()));
        }
    }
```
###### \src\main\java\seedu\address\ui\CommandBox.java
``` java
}
```
###### \src\main\java\seedu\address\ui\MainWindow.java
``` java
    /**
     * Changes displayed list accordingly
     */
    void fillInnerPartsWithIndicatedList(String listname) {
        switch(listname) {
        case "fl":
        case "favlist":
            personListPanel = new PersonListPanel(logic.getFilteredFavoritePersonList(), logic);
            break;

        default:
            personListPanel = new PersonListPanel(logic.getFilteredPersonList(), logic);
        }
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }
```
###### \src\main\java\seedu\address\ui\MainWindow.java
``` java

    void hide() {
        primaryStage.hide();
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    /**
     * Sets the given image as the icon of the main window.
     * @param iconSource e.g. {@code "/images/help_icon.png"}
     */
    private void setIcon(String iconSource) {
        FxViewUtil.setStageIcon(primaryStage, iconSource);
    }

    /**
     * Sets the default size based on user preferences.
     */
    private void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    private void setWindowMinSize() {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Returns the current size and the position of the main Window.
     */
    GuiSettings getCurrentGuiSetting() {
        return new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
    }

    /**
     * Opens the help window.
     */
    @FXML
    public void handleHelp() {
        HelpWindow helpWindow = new HelpWindow();
        helpWindow.show();
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        raise(new ExitAppRequestEvent());
    }


    /**
     * Changes the theme color to dark of the program
     */
    @FXML
    private void handleDarkTheme() {

        int size = scene.getRoot().getStylesheets().size();

        for (int i = 0; i < size; i++) {
            scene.getRoot().getStylesheets().remove(0);
        }

        scene.getRoot().getStylesheets().add("view/DarkTheme.css");
        scene.getRoot().getStylesheets().add("view/Extensions.css");

    }

    /**
     * Changes the theme color to light of the program
     */
    @FXML
    private void handleLightTheme() {

        int size = scene.getRoot().getStylesheets().size();
        for (int i = 0; i < size; i++) {

            scene.getRoot().getStylesheets().remove(0);

        }
        scene.getRoot().getStylesheets().add("view/LightTheme.css");

    }



    public PersonListPanel getPersonListPanel() {
        return this.personListPanel;
    }

    void releaseResources() {
        browserPanel.freeResources();
    }

    @Subscribe
    private void handleShowHelpEvent(ShowHelpRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleHelp();
    }
}
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java
            if (logic.getCurrentList().contains("favlist")) {
                commandResult = new CommandResult("Delete command does not work in favourite list");;
            } else {
                commandResult = logic.execute(delCommand);
            }
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java

```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java
        CommandResult commandResult = new CommandResult("");
        if (logic.getCurrentList().contains("favlist")) {
            commandResult = new CommandResult("Edit command does not work in favourite list");;
```
###### \src\main\java\seedu\address\ui\PersonCard.java
``` java

```
###### \src\main\java\seedu\address\ui\UiManager.java
``` java
    @Subscribe
    private void handleChangeInternalListEvent(ChangeInternalListEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.fillInnerPartsWithIndicatedList(event.getListName());
    }
```
###### \src\main\java\seedu\address\ui\UiManager.java
``` java
}

```
###### \src\test\java\seedu\address\logic\commands\AddCommandTest.java
``` java
        @Override
        public void sortFilteredPersonList() {
            fail("This method should not be called.");
        }

        @Override
        public void addFavouritePerson(ReadOnlyPerson target) {
            fail("This method should not be called.");
        }

        @Override
        public void removeFavouritePerson(ReadOnlyPerson target) {
            fail("This method should not be called.");
        }

        @Override
        public void changeListTo(String listName) {
            fail("This method should not be called.");
        }

        @Override
        public void setCurrentList(String currentList)  {
            fail("This method should not be called.");
        }

        @Override
        public String getCurrentList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredFavouritePersonList(Predicate<ReadOnlyPerson> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<ReadOnlyPerson> getFilteredFavouritePersonList() {
            fail("This method should not be called.");
            return null;
        }
```
###### \src\test\java\seedu\address\logic\commands\AddCommandTest.java
``` java

        @Override
        public void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
                throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate) {
            fail("This method should not be called.");
        }
    }

    /**
     * A Model stub that always throw a DuplicatePersonException when trying to add a person.
     */
    private class ModelStubThrowingDuplicatePersonException extends ModelStub {
        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            throw new DuplicatePersonException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            personsAdded.add(new Person(person));
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
```
###### \src\test\java\seedu\address\logic\commands\FavCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code FavCommand}.
 */
public class FavCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        ReadOnlyPerson personToFav = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FavCommand favCommand = prepareCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavCommand.MESSAGE_FAV_PERSON_SUCCESS, personToFav);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addFavouritePerson(personToFav);

        assertCommandSuccess(favCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() throws Exception {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FavCommand favCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(favCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws Exception {
        showFirstPersonOnly(model);

        ReadOnlyPerson personToFav = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FavCommand favCommand = prepareCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavCommand.MESSAGE_FAV_PERSON_SUCCESS, personToFav);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addFavouritePerson(personToFav);

        assertCommandSuccess(favCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFirstPersonOnly(model);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        FavCommand favCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(favCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FavCommand favFirstCommand = new FavCommand(INDEX_FIRST_PERSON);
        FavCommand favSecondCommand = new FavCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(favFirstCommand.equals(favFirstCommand));

        // same values -> returns true
        FavCommand favFirstCommandCopy = new FavCommand(INDEX_FIRST_PERSON);
        assertTrue(favFirstCommand.equals(favFirstCommandCopy));

        // different types -> returns false
        assertFalse(favFirstCommand.equals(1));

        // null -> returns false
        assertFalse(favFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(favFirstCommand.equals(favSecondCommand));
    }

    /**
         * Returns a {@code FavCommand} with the parameter {@code index}.
     */
    private FavCommand prepareCommand(Index index) {
        FavCommand favCommand = new FavCommand(index);
        favCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return favCommand;
    }
}
```
###### \src\test\java\seedu\address\logic\commands\FavCommandTest.java
``` java

```
###### \src\test\java\seedu\address\logic\commands\FavouriteListCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class FavouriteListCommandTest {

    private Model model;
    private Model expectedModel;
    private FavouriteListCommand favlistCommand;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        favlistCommand = new FavouriteListCommand();
        favlistCommand.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(favlistCommand, model, FavouriteListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFirstPersonOnly(model);
    }
}
```
###### \src\test\java\seedu\address\logic\commands\FavouriteListCommandTest.java
``` java

```
###### \src\test\java\seedu\address\logic\commands\SortCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() throws Exception {
        assertCommandSuccess(prepareCommand(), model, SortCommand.MESSAGE_SUCCESS, model);

    }

    @Test
    public void equals() {
        final SortCommand standardCommand = new SortCommand();

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different filterTypes -> returns false
        assertFalse(standardCommand.equals(new SortCommand()));
    }

    /**
     * Returns a {@code SortCommand}
     */
    private SortCommand prepareCommand() {
        SortCommand sortCommand = new SortCommand();
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return sortCommand;
    }
}
```
###### \src\test\java\seedu\address\logic\commands\SortCommandTest.java
``` java

```
###### \src\test\java\seedu\address\logic\commands\UnfavCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code UnfavCommand}.
 */
public class UnfavCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*
    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        ReadOnlyPerson personToUnfav = model.getFilteredFavouritePersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavCommand unfavCommand = prepareCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavCommand.MESSAGE_FAV_PERSON_SUCCESS, personToUnfav);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeFavouritePerson(personToUnfav);

        assertCommandSuccess(unfavCommand, model, expectedMessage, expectedModel);
    }
    */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() throws Exception {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnfavCommand unfavCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(unfavCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /*
    @Test
    public void execute_validIndexFilteredList_success() throws Exception {
        ReadOnlyPerson personToFav = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FavCommand favCommand = prepareFavCommand(INDEX_FIRST_PERSON);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addFavouritePerson(personToFav);
        showFirstFavouritePersonOnly(model);

        ReadOnlyPerson personToUnfav = model.getFilteredFavouritePersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavCommand unfavCommand = prepareCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavCommand.MESSAGE_FAV_PERSON_SUCCESS, personToUnfav);

        //Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeFavouritePerson(personToUnfav);
        showNoPerson(expectedModel);

        assertCommandSuccess(unfavCommand, model, expectedMessage, expectedModel);
    } */

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFirstPersonOnly(model);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnfavCommand unfavCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(unfavCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnfavCommand unfavFirstCommand = new UnfavCommand(INDEX_FIRST_PERSON);
        UnfavCommand unfavSecondCommand = new UnfavCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(unfavFirstCommand.equals(unfavFirstCommand));

        // same values -> returns true
        UnfavCommand unfavFirstCommandCopy = new UnfavCommand(INDEX_FIRST_PERSON);
        assertTrue(unfavFirstCommand.equals(unfavFirstCommandCopy));

        // different types -> returns false
        assertFalse(unfavFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unfavFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unfavFirstCommand.equals(unfavSecondCommand));
    }

    /**
     * Returns a {@code UnfavCommand} with the parameter {@code index}.
     */
    private UnfavCommand prepareCommand(Index index) {
        UnfavCommand unfavCommand = new UnfavCommand(index);
        unfavCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return unfavCommand;
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredFavouritePersonList(p -> false);

        assert model.getFilteredFavouritePersonList().isEmpty();
    }

    /**
     * Returns a {@code FavCommand} with the parameter {@code index}.
     */
    private FavCommand prepareFavCommand(Index index) {
        FavCommand favCommand = new FavCommand(index);
        favCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return favCommand;
    }
}
```
###### \src\test\java\seedu\address\logic\commands\UnfavCommandTest.java
``` java

```
