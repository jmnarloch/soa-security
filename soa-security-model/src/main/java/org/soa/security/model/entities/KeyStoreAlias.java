package org.soa.security.model.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * <p>Represents the key store alias.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class KeyStoreAlias extends IdentifiableEntity {

    /**
     * <p>Represents the alias name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String name;

    /**
     * <p>Creates new instance of {@link KeyStoreAlias}.</p>
     */
    public KeyStoreAlias() {
        // empty constructor
    }

    /**
     * <p>Retrieves the alias name.</p>
     *
     * @return the alias name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Sets the alias name.</p>
     *
     * @param name the alias name
     */
    public void setName(String name) {
        this.name = name;
    }
}
