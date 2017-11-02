//@@author Linus
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

import java.net.URL;

/**
 * Represents the view change in the browser panel
 */
public class BrowserJumpToHomePage extends BaseEvent{

    private URL url;

    public BrowserJumpToHomePage(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public URL getHomeUrl() {
        return url;
    }

}
