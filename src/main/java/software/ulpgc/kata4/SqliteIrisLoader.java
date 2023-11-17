package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteIrisLoader implements IrisLoader{

    private final Connection connection;
    private final static String queryAllSql = "SELECT SepalLengthCm, SepalWidthCm, PetalLengthCm, PetalWidthCm, Species FROM iris";

    public SqliteIrisLoader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Iris> loadAll() {
        try {
            return load(resultSetOf(queryAllSql));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Iris> load(ResultSet resultSet) throws SQLException {
        List<Iris> irises = new ArrayList<>();
        while(resultSet.next()){
            irises.add(irisFrom(resultSet));
        }
        return irises;
    }

    private Iris irisFrom(ResultSet resultSet) throws SQLException {
        return new Iris(
            resultSet.getDouble("SepalLengthCm"),
            resultSet.getDouble("SepalWidthCm"),
            resultSet.getDouble("PetalLengthCm"),
            resultSet.getDouble("PetalWidthCm"),
            resultSet.getString("Species")
        );
    }

    private ResultSet resultSetOf(String queryAllSql) throws SQLException {
        return connection.createStatement().executeQuery(queryAllSql);
    }
}
