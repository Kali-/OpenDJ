/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at legal-notices/CDDLv1_0.txt
 * or http://forgerock.org/license/CDDLv1.0.html.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at legal-notices/CDDLv1_0.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2014 ForgeRock AS.
 */
package com.forgerock.opendj.cli;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("javadoc")
public class UtilsTestCase extends CliTestCase {

    @Test(expectedExceptions = ClientException.class)
    public void testInvalidJavaVersion() throws ClientException {
        final String original = System.getProperty("java.specification.version");
        System.setProperty("java.specification.version", "1.5");
        try {
            Utils.checkJavaVersion();
        } finally {
            System.setProperty("java.specification.version", original);
        }
    }

    @Test()
    public void testValidJavaVersion() throws ClientException {
        Utils.checkJavaVersion();
    }

    @Test()
    public void testCanWriteOnNewFile() throws ClientException, IOException {
        final File f = File.createTempFile("tempFile", ".txt");
        f.deleteOnExit();
        assertTrue(f.exists());
        assertTrue(Utils.canWrite(f.getPath()));
    }

    @Test()
    public void testCannotWriteOnNewFile() throws ClientException, IOException {
        final File f = File.createTempFile("tempFile", ".txt");
        f.setReadOnly();
        f.deleteOnExit();
        assertTrue(f.exists());
        assertFalse(Utils.canWrite(f.getPath()));
    }
}
