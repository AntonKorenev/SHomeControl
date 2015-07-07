package Database.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public abstract class BaseSHomeDao<T,Integer> extends BaseDaoImpl<T,Integer> {

    protected BaseSHomeDao(ConnectionSource connectionSource,
                             Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<T> getAllDataClasses() throws SQLException{
        return this.queryForAll();
    }


    public void clear() throws SQLException {
        DeleteBuilder<T,Integer> db = this.deleteBuilder();
        db.delete();
    }
}
