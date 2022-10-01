package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {

    /**
     *
     * Connection 얻기
     *
     * @return Connection
     */
    public static Connection getConnection(){

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //DriverManager 통해 DB와 Connection
            log.info("get connection={}, class={}", connection, connection.getClass()); //커넥션 로그
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
