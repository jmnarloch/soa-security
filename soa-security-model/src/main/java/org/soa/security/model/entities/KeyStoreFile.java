package org.soa.security.model.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 * <p>Represents the key store file.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class KeyStoreFile extends IdentifiableEntity {

    /**
     * <p>Represents the key store content.</p>
     */
    @Lob
    @NotNull
    private byte[] content;

    /**
     * <p>Creates new instance of {@link KeyStoreFile} class.</p>
     */
    public KeyStoreFile() {
        // empty constructor
    }

    /**
     * <p>Retrieves the key store content.</p>
     *
     * @return the key store content.
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * <p>Retrieves the key store content.</p>
     *
     * @param content the key store content
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
}
