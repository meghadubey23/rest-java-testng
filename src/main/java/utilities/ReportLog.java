package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportLog {

    private static final Logger logger = LoggerFactory.getLogger(ReportLog.class);

    public ReportLog() {

    }

    public static void log(String text) {
        logger.info(text);
    }

    public static void error(String text) {
        logger.error(text);
    }
}
