package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> stringHistory = new LinkedList<>();
    private String nextString;


    @Override
    public void setNextStringToPrint(String string) {
        this.nextString = Objects.requireNonNull(string, "This method does not accept null values");
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public void printCurrentString() {
        if (nextString == null) {
            throw new IllegalStateException("There is no string to print");
        }
        System.out.println(nextString);
        stringHistory.add(nextString);
        
    }

    @Override
    public List<String> getPrintedStringsHistory() {
        return Collections.unmodifiableList(stringHistory);
    }

}
