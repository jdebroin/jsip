/*
 * Conditions Of Use
 *
 * This software was developed by employees of the National Institute of
 * Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 15 Untied States Code Section 105, works of NIST
 * employees are not subject to copyright protection in the United States
 * and are considered to be in the public domain.  As a result, a formal
 * license is not needed to use the software.
 *
 * This software is provided by NIST as a service and is expressly
 * provided "AS IS."  NIST MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
 * OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
 * AND DATA ACCURACY.  NIST does not warrant or make any representations
 * regarding the use of the software or the results thereof, including but
 * not limited to the correctness, accuracy, reliability or usefulness of
 * the software.
 *
 * Permission to use this software is contingent upon your acceptance
 * of the terms of this agreement.
 *
 */
/***************************************************************************
 * Product of NIST/ITL Advanced Networking Technologies Division (ANTD).    *
 ***************************************************************************/

package gov.nist.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A wrapper around log4j that is used for logging debug and errors. You can
 * replace this file if you want to change the way in which messages are logged.
 *
 * @version 1.0
 *
 * @author Vladimir Ralev
 *
 */

public class CommonLoggerSLF4J implements StackLogger {
    
    /**
     * The logger to which we will write our logging output.
     */
    protected Logger logger;

    public CommonLoggerSLF4J() {
        
    }

    /**
     * log a stack trace. This helps to look at the stack frame.
     */
    public void logStackTrace() {
        this.logStackTrace(TRACE_DEBUG);

    }

    public void logStackTrace(int traceLevel) {
        
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            StackTraceElement[] ste = new Exception().getStackTrace();
            // Skip the log writer frame and log all the other stack frames.
            for (int i = 1; i < ste.length; i++) {
                String callFrame = "[" + ste[i].getFileName() + ":"
                        + ste[i].getLineNumber() + "]";
                pw.print(callFrame);
            }
            pw.close();
            String stackTrace = sw.getBuffer().toString();
            logger.debug(stackTrace);

    }

    /**
     * Get the line count in the log stream.
     *
     * @return
     */
    public int getLineCount() {
        return 0;
    }

    /**
     * Get the logger.
     *
     * @return
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Log an exception.
     *
     * @param ex
     */
    public void logException(Throwable ex) {

        logger.error("Error", ex);
    }

    /**
     * Log a message into the log file.
     *
     * @param message
     *            message to log into the log file.
     */
    public void logDebug(String message) {
        logger.debug(message);

    }
    
    /**
     * Log a message into the log file.
     *
     * @param message
     *            message to log into the log file.
     */
    public void logTrace(String message) {
    	logger.debug(message);
    }

    /**
     * Get the trace level for the stack.
     */
    public int getTraceLevel() {
        if (logger.isInfoEnabled())
            return TRACE_INFO;
        else if (logger.isDebugEnabled())
            return TRACE_DEBUG;
        else if (logger.isErrorEnabled())
            return TRACE_ERROR;
        else if (logger.isWarnEnabled())
            return TRACE_WARN;
        else if (logger.isTraceEnabled())
            return TRACE_TRACE;
        else
            return TRACE_ERROR;
    }

    /**
     * Log an error message.
     *
     * @param message --
     *            error message to log.
     */
    public void logFatalError(String message) {
        logger.error(message);

    }

    /**
     * Log an error message.
     *
     * @param message --
     *            error message to log.
     *
     */
    public void logError(String message) {
        logger.error(message);

    }

    public CommonLoggerSLF4J(String name) {
    	this.logger = LoggerFactory.getLogger(name);
    }
    
    public void setStackProperties(Properties configurationProperties) {

        // Do nothing (can't do anything here, this method is called only for legacy)
        
    }

    /**
     * @return flag to indicate if logging is enabled.
     */
    public boolean isLoggingEnabled() {

        return logger.isInfoEnabled();
    }

    /**
     * Return true/false if loging is enabled at a given level.
     *
     * @param logLevel
     */
    public boolean isLoggingEnabled(int logLevel) {
        switch (logLevel) {
            case TRACE_INFO:
                return logger.isInfoEnabled();
            case TRACE_DEBUG:
                return logger.isDebugEnabled();
            case TRACE_ERROR:
                return logger.isErrorEnabled();
            case TRACE_WARN:
                return logger.isWarnEnabled();
            case TRACE_TRACE:
                return logger.isTraceEnabled();
            case TRACE_FATAL:
                return logger.isErrorEnabled();
            default:
                return logger.isErrorEnabled();
        }
    }


    /**
     * Log an error message.
     *
     * @param message
     * @param ex
     */
    public void logError(String message, Exception ex) {
        Logger logger = this.getLogger();
        logger.error(message, ex);

    }

    /**
     * Log a warning mesasge.
     *
     * @param string
     */
    public void logWarning(String string) {
        getLogger().warn(string);

    }

    /**
     * Log an info message.
     *
     * @param string
     */
    public void logInfo(String string) {
        getLogger().info(string);
    }

    /**
     * Disable logging altogether.
     *
     */
    public void disableLogging() {
        // Do nothing
    }

    /**
     * Enable logging (globally).
     */
    public void enableLogging() {
        // Do nothing

    }

	public String getLoggerName() {
	    if ( this.logger != null ) {
	        return logger.getName();
	    } else {
	        return null;
	    }
	}

	public void setBuildTimeStamp(String buildTimeStamp) {
		logger.info("Build timestamp: " + buildTimeStamp);
	}

  
}
