package Database.Dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public abstract class BaseClimateDao<T,Integer> extends BaseSHomeDao<T,Integer> {

    protected BaseClimateDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }


}
