/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.authentication.impl;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.authentication.UserDataSource;
import org.soa.security.core.authentication.UserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Represents a database user data source.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class DatabaseUserDataSource extends AbstractUserDataSource implements UserDataSource {

    /**
     * <p>Represents the default query used for authenticating the users.</p>
     */
    private String DEFAULT_QUERY = "select usr.Name, usr.Password from UserInfo usr where usr.Name = ?";

    /**
     * <p>Represents the connection string.</p>
     */
    private String connectionUrl;

    /**
     * <p>Represents the user name.</p>
     */
    private String userName;

    /**
     * <p>Represents the user password.</p>
     */
    private String userPassword;

    /**
     * <p>Represents the user query.</p>
     */
    private String userQuery;

    /**
     * <p>Creates new instance of {@link DatabaseUserDataSource} class.</p>
     */
    public DatabaseUserDataSource(String connectionUrl, String userName, String userPassword, String userQuery) {

        this.connectionUrl = connectionUrl;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userQuery = DEFAULT_QUERY;

        if (userQuery != null && userQuery.length() > 0) {

            this.userQuery = userQuery;
        }
    }

    /**
     * <p>Retrieves the database connection.</p>
     *
     * @return the database connection
     */
    protected Connection getConnection() throws SQLException {

        Connection connection;

        if (userName != null) {

            connection = DriverManager.getConnection(connectionUrl, userName, userPassword);
        } else {

            connection = DriverManager.getConnection(connectionUrl);
        }
        connection.setAutoCommit(false);

        return connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UserInfo getUserInfo(String login) throws ServiceExecutionException {

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        UserInfo userInfo = null;
        int index;

        try {
            index = 1;
            connection = getConnection();
            statement = connection.prepareStatement(userQuery);
            statement.setString(index++, login);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                index = 1;
                userInfo = new UserInfo();
                userInfo.setLogin(resultSet.getString(index++));
                userInfo.setPassword(resultSet.getString(index++));
            }

            return userInfo;
        } catch (SQLException e) {

            throw new ServiceExecutionException("An error occurred when retrieving the user information from database.",
                    e);
        }
    }
}
