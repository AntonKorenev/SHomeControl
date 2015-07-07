package Database.Dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class OutdoorDao<OutdoorTableObject, Integer> extends BaseClimateDao<OutdoorTableObject,Integer> {

    public OutdoorDao(ConnectionSource connectionSource,
                      Class<OutdoorTableObject> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}
