package com.pablo.acs.local.auth.service.infrastructure.repository;

import com.pablo.acs.local.auth.service.domain.user.IdentifyMethodType;
import com.pablo.acs.local.auth.service.domain.user.query.UserIdentifyMethodFinderRepository;
import com.pablo.acs.local.auth.service.domain.user.query.projection.UserIdentifyMethodProjection;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Repository
public class UserIdentifyMethodFinderJdbcRepository implements UserIdentifyMethodFinderRepository {

    private final JdbcOperations jdbcOperations;

    private static String SELECT_IDENTIFICATION_METHODS_OF_USER =
            "SELECT" +
            "   UIM.USER_ID" +
            ",  UIM.IDENTIFIER" +
            ",  UIM.IDENTIFICATION_METHOD_ID" +
            "   FROM USERS_IDENTIFICATION_METHODS UIM" +
            "   WHERE 1 = 1";


    public UserIdentifyMethodFinderJdbcRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private Collection<UserIdentifyMethodProjection> query(final String query, final Object[] params) {
        return jdbcOperations.query(
                query,
                (resultSet, i) -> new UserIdentifyMethodProjection(
                        resultSet.getLong("USER_ID"),
                        resultSet.getInt("IDENTIFICATION_METHOD_ID"),
                        resultSet.getBytes("IDENTIFIER")
                ),
                params);
    }

    @Override
    public Collection<UserIdentifyMethodProjection> findAll() {
        return query(SELECT_IDENTIFICATION_METHODS_OF_USER, Collections.emptyList().toArray());
    }

    @Override
    public Collection<UserIdentifyMethodProjection> findByLastUpdate(final LocalDateTime lastUpdate) {
        final String query = createQuery(lastUpdate);
        final Object[] params = createParams(lastUpdate);
        return query(query, params);
    }

    private Object[] createParams(final LocalDateTime lastUpdate) {
        return createParams(null, lastUpdate);
    }

    private String createQuery(final LocalDateTime lastUpdate) {
        return createQuery(null, lastUpdate);
    }


    @Override
    public Collection<UserIdentifyMethodProjection> findByIdentifyMethod(final IdentifyMethodType identifyMethod) {
        final String query = createQuery(identifyMethod);
        final Object[] params = createParams(identifyMethod);
        return query(query, params);
    }

    @Override
    public Collection<UserIdentifyMethodProjection> findByIdentifyMethodAndLastUpdate(
            final IdentifyMethodType identifyMethod, final LocalDateTime lastUpdate) {
        final String query = createQuery(identifyMethod, lastUpdate);
        final Object[] params = createParams(identifyMethod, lastUpdate);
        return query(query, params);
    }

    private Object[] createParams(final IdentifyMethodType identifyMethod) {
        return createParams(identifyMethod, null);
    }

    private String createQuery(final IdentifyMethodType identifyMethod) {
        return createQuery(identifyMethod, null);
    }

    private Object[] createParams(final IdentifyMethodType identifyMethod, final LocalDateTime lastUpdate) {
        final Collection<Object> params = new ArrayList();
        if (identifyMethod != null) {
            params.add(identifyMethod.getId());
        }
        if (lastUpdate != null) {
            params.add(lastUpdate);
        }
        return params.toArray();
    }

    private String createQuery(final IdentifyMethodType identifyMethod, final LocalDateTime lastUpdate) {
        final StringBuilder sb = new StringBuilder(SELECT_IDENTIFICATION_METHODS_OF_USER);
        if (identifyMethod != null) {
            sb.append(" AND UIM.IDENTIFICATION_METHOD_ID = ?");
        }
        if (lastUpdate != null) {
            sb.append(" AND UIM.LAST_UPDATE > ?");
        }
        return sb.toString();
    }

}
