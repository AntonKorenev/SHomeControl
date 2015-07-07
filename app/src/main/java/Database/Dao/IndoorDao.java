package Database.Dao;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class IndoorDao<IndoorTableObject,Integer> extends BaseClimateDao<IndoorTableObject,Integer> {

    public IndoorDao(ConnectionSource connectionSource,
                     Class<IndoorTableObject> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

}
