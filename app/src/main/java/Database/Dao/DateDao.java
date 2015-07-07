package Database.Dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import static Database.DBContent.DateTableObject.*;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class DateDao<DateTableObject,Integer> extends BaseSHomeDao<DateTableObject,Integer> {

    public DateDao(ConnectionSource connectionSource,
                   Class<DateTableObject> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<DateTableObject> getLastDates(Long quantity) throws SQLException {
        QueryBuilder<DateTableObject, Integer> queryBuilder = queryBuilder();
        queryBuilder.orderBy(ID_COLUMN_NAME,false).limit(quantity);
        return query(queryBuilder.prepare());
    }
}
