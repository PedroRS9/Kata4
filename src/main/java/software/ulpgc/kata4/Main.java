package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite")){
            IrisLoader loader = new SqliteIrisLoader(connection);
            List<Iris> irises = loader.loadAll();
            for(Iris iris : irises){
                System.out.println(iris);
            }
        }
    }
}
