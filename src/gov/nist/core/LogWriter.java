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

import java.util.Properties;

import org.slf4j.LoggerFactory;

/**
 * A wrapper around log4j that is used for logging debug and errors. You can
 * replace this file if you want to change the way in which messages are logged.
 *
 * @version 1.2
 *
 * @author M. Ranganathan <br/>
 * @author M.Andrews
 * @author Jeroen van Bemmel
 * @author Jean Deruelle
 *
 */

public class LogWriter extends CommonLoggerSLF4J {

    public LogWriter() {
    }

    /**
     * The stack name.
     */
    private String stackName;

    /**
     * Name of the log file in which the trace is written out (default is
     * /tmp/sipserverlog.txt)
     */
    private String logFileName = null;

    private Properties configurationProperties;

    public void setStackProperties(Properties configurationProperties) {

        this.configurationProperties = configurationProperties;

        String logLevel = configurationProperties
                .getProperty("gov.nist.javax.sip.TRACE_LEVEL");

        this.logFileName = configurationProperties
                .getProperty("gov.nist.javax.sip.DEBUG_LOG");

        this.stackName = configurationProperties
                .getProperty("javax.sip.STACK_NAME");

        String category = configurationProperties
                                .getProperty("gov.nist.javax.sip.LOG4J_LOGGER_NAME", this.stackName);


        logger = LoggerFactory.getLogger(category);
        
    }

  
}
