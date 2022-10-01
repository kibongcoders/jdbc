package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Connection 가져오기 테스트
 */
@Slf4j
public class DBConnectionUtilTest {

    /**
     * Connection 가져오기 테스트
     */
    @Test
    public void getConnection(){
        Connection connection = DBConnectionUtil.getConnection();
        assertThat(connection).isNotNull();
    }
}
