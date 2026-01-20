package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEP = File.separator;
    private static final String DEFAULT_FILE = "output.txt";

    private File file = new File(HOME + SEP + DEFAULT_FILE);

    public File getCurrentFile() {
        return this.file;
    }

    public void setCurrentFile(final File newCurrentFile) {
        if (newCurrentFile.getParentFile().exists()) {
            file = newCurrentFile;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder");
        }
    }

    public void setCurrentFile(final String newCurrentFile) {
        setCurrentFile(new File(newCurrentFile));
    }

    public void save(final String output) throws IOException {
        try (PrintStream out = new PrintStream(file, StandardCharsets.UTF_8)) {
            out.print(output);
        }
    }
    
    public String getCurrentFilePath() {
        return this.file.getPath();
    }
}
