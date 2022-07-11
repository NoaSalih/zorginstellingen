package com.zorginstellingen.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AESTest {
    /**
     * Method under test: {@link AES#setKey(String)}
     */
    @Test
    void testSetKey() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by setKey(String)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        AES.setKey("My Key");
    }

    /**
     * Method under test: {@link AES#encrypt(String, String)}
     */
    @Test
    void testEncrypt() {
        assertEquals("2JTzzqRuvoJbwhj7qwP3bA==", AES.encrypt("Str To Encrypt", "Secret"));
    }

    /**
     * Method under test: {@link AES#decrypt(String, String)}
     */
    @Test
    void testDecrypt() {
        assertNull(AES.decrypt("Str To Decrypt", "Secret"));
        assertNull(AES.decrypt("AES", "Secret"));
        assertEquals("", AES.decrypt("", "Secret"));
    }
}

