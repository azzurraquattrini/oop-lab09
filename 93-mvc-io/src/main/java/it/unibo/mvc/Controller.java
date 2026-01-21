package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    void setNextStringToPrint(String string);

    String getNextStringToPrint();

    void printCurrentString();

    List<String> getPrintedStringsHistory();

}
