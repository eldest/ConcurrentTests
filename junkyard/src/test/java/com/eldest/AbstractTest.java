package com.eldest;

import com.eldest.utils.log.SimpleLogger;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

public abstract class AbstractTest {
    protected static SimpleLogger log = new SimpleLogger(AbstractTest.class);

    @Rule
    public TestName name = new TestName();

    //------------------------------- static -------------------------------

    /*static {
        try {
            System.setProperty("app.config", ClassLoader.getSystemResource("wellinfo.ini").getFile());
        } catch (Exception ex) {
            log.error(ex);
        }
    }*/

    //------------------------------- f -------------------------------

    protected static void drawLine() {
        drawLine("-");
    }

    protected static void drawLine(String title) {
        System.out.print(String.format("%n//------------------------------- %s -------------------------------%n", title));
    }

    protected static void drawLine(TestName name) {
        drawLine(name.getMethodName());
    }

    //------------------------------- resource -------------------------------

    protected static URL getResource(String name) {
        return ClassLoader.getSystemResource(name);
    }

    protected static InputStream getResourceStream(String name) {
        return ClassLoader.getSystemResourceAsStream(name);
    }

    protected static String getResourceFileName(String name) {
        return getResource(name).getFile();
    }

    protected static File getResourceFile(String name) {
        return new File(getResourceFileName(name));
    }

    protected static Path getResourcePath(String name) {
        return getResourceFile(name).toPath();
    }
}
