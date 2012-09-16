/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model;

/**
 * <p>Defines const for the entities.</p>
 *
 * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public final class ModelConsts {

    /**
     * <p>Creates new instance of {@link ModelConsts}.</p>
     *
     * <p>Private constructor prevents instantiation outside this class.</p>
     */
    private ModelConsts() {
        // empty constructor
    }

    /**
     * <p>Defines user roles.</p>
     *
     * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    public static final class UserRoles {

        /**
         * <p>Defines admin role.</p>
         */
        public static final int ADMIN = 1;

        /**
         * <p>Defines user role.</p>
         */
        public static final int USER = 2;

        /**
         * <p>Creates new instance of {@link UserRoles}.</p>
         *
         * <p>Private constructor prevents instantiation outside this class.</p>
         */
        private UserRoles() {
            // empty constructor
        }
    }

    /**
     * <p>Defines user role names.</p>
     *
     * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    public static final class UserRoleNames {

        /**
         * <p>Defines admin role.</p>
         */
        public static final String ADMIN = "ROLE_ADMIN";

        /**
         * <p>Defines user role.</p>
         */
        public static final String USER = "ROLE_USER";

        /**
         * <p>Creates new instance of {@link UserRoles}.</p>
         *
         * <p>Private constructor prevents instantiation outside this class.</p>
         */
        private UserRoleNames() {
            // empty constructor
        }
    }

    /**
     * <p>Defines service types.</p>
     *
     * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    public static final class ServiceTypes {

        /**
         * <p>Defines web service type.</p>
         */
        public static final int WEB_SERVICE = 3;

        /**
         * <p>Defines rest type.</p>
         */
        public static final int REST = 4;

        /**
         * <p>Creates new instance of {@link ServiceTypes}.</p>
         *
         * <p>Private constructor prevents instantiation outside this class.</p>
         */
        private ServiceTypes() {
            // empty constructor
        }
    }

    /**
     * <p>Defines authentication source types.</p>
     *
     * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    public static final class DataSourceTypes
    {
        /**
         * <p>Defines database type.</p>
         */
        public static final int DATABASE = 5;

        /**
         * <p>Defines LDAP type.</p>
         */
        public static final int LDAP = 6;

        /**
         * <p>Defines CAS type.</p>
         */
        public static final int CAS = 7;

        /**
         * <p>Creates new instance of {@link DataSourceTypes}.</p>
         *
         * <p>Private constructor prevents instantiation outside this class.</p>
         */
        private DataSourceTypes() {
            // empty constructor
        }
    }

    /**
     * <p>Defines security types.</p>
     *
     * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    public static final class SecurityTypes {

        /**
         * <p>Defines WebService authentication with plain password.</p>
         */
        public static final int WS_PASSWORD = 8;

        /**
         * <p>Defines WebService authentication with digest password.</p>
         */
        public static final int WS_DIGEST = 9;

        /**
         * <p>Defines WebService signing.</p>
         */
        public static final int WS_SIGNING = 10;

        /**
         * <p>Defines WebService encryption.</p>
         */
        public static final int WS_ENCRYPTION = 11;

        /**
         * <p>Defines HTTP basic authentication.</p>
         */
        public static final int HTTP_BASIC = 12;

        /**
         * <p>Defines HTTP digest authentication.</p>
         */
        public static final int HTTP_DIGEST = 13;

        /**
         * <p>Defines OAuth authentication.</p>
         */
        public static final int OAUTH_AUTHENTICATION = 14;

        /**
         * <p>Creates new instance of {@link SecurityTypes}.</p>
         *
         * <p>Private constructor prevents instantiation outside this class.</p>
         */
        private SecurityTypes() {
            // empty constructor
        }
    }
}
