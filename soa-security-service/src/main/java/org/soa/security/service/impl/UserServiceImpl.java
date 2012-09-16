package org.soa.security.service.impl;

import org.soa.security.dao.SoaDAOException;
import org.soa.security.dao.UserDAO;
import org.soa.security.model.ModelConsts;
import org.soa.security.model.dto.UserDTO;
import org.soa.security.model.entities.UserInfo;
import org.soa.security.service.SoaServiceException;
import org.soa.security.service.UserService;
import org.soa.security.service.mapper.Mappers;
import org.soa.security.service.mapper.impl.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>A default implementation of {@link UserService}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * <p>Instance of {@link UserDAO} used for persisting users.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <p>Instance of {@link UserMapper} used for converting the entities.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * <p>Creates new instance of {@link UserServiceImpl} class.</p>
     */
    public UserServiceImpl() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(long userId, UserDTO user) throws SoaServiceException {

        try {
            validateUserPermission(userId);

            return userDAO.save(userMapper.mapToEntity(user));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when saving the user.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long userId, UserDTO user) throws SoaServiceException {

        try {
            validateUserPermission(userId);

            userDAO.update(userMapper.mapToEntity(user));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when updating the user.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll(long userId) throws SoaServiceException {

        try {
            validateUserPermission(userId);

            return Mappers.mapEntityCollection(userMapper, userDAO.getAll());
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the users.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO get(long userId, long id) throws SoaServiceException {

        try {
            validateUserPermission(userId);

            return userMapper.mapToDto(userDAO.get(id));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the user.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO getByUserName(String userName) throws SoaServiceException {

        try {

            return userMapper.mapToDto(userDAO.getByUserName(userName));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the user.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long userId, long id) throws SoaServiceException {

        try {
            validateUserPermission(userId);

            userDAO.delete(id);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when deleting the user.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public boolean isUserExists(String userName) throws SoaServiceException {

        try {
            return userDAO.getByUserName(userName) != null;
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the user.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void enableUser(long userId, long id, boolean enable) throws SoaServiceException {

        try {
            validateUserPermission(userId);

            UserInfo userInfo = userDAO.get(id);
            userInfo.setEnabled(enable);

            userDAO.update(userInfo);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when updating the user.", e);
        }
    }


    /**
     * <p>Validates that the user may access the user information.</p>
     *
     * @param userId the user id
     *
     * @throws SoaDAOException     if any error occurs
     * @throws SoaServiceException if the user may not access the given resource
     */
    private void validateUserPermission(long userId) throws SoaDAOException, SoaServiceException {

        UserInfo user = userDAO.get(userId);

        if (user.getUserRole().getId() != ModelConsts.UserRoles.ADMIN) {
            throw new SoaServiceException("Unauthorized to access the specified resource.");
        }
    }
}
