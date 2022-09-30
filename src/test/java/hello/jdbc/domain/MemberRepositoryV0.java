package hello.jdbc.domain;

import hello.jdbc.connection.DBConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {

    public Member save (Member member) throws Exception{

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO MEMBER(member_id, money) VALUES(?,?)";


        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,member.getMember_id());
            preparedStatement.setInt(2, member.getMoney());
            preparedStatement.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }

    }

    public Member getById(String member_id) throws Exception{

        String sql = "SELECT member_id, money FROM MEMBER WHERE member_id = ?";

        Connection con = null;
        PreparedStatement prmpt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            prmpt = con.prepareStatement(sql);
            prmpt.setString(1,member_id);
            rs = prmpt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setMember_id(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else{
                throw  new NoSuchElementException("member not found memberId=" + member_id);
            }

        }catch (SQLException e){
            log.error("error", e);
            throw e;
        }finally {
            close(con, prmpt, rs);
        }
    }

    private void close(Connection con, Statement statement, ResultSet rs){

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error" , e);
            }
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                log.info("error" , e);
            }
        }

        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error" , e);
            }
        }
    }

    public void update(int money, String memberId) throws SQLException{

        String sql = "UPDATE MEMBER SET money = ? WHERE member_id = ?";
        Connection con = null;
        PreparedStatement prmpst = null;

        try {
            con = getConnection();
            prmpst = con.prepareStatement(sql);
            prmpst.setInt(1, money);
            prmpst.setString(2, memberId);
            int resultInt = prmpst.executeUpdate();
            log.info("resultSize = {}", resultInt);
        } catch (SQLException e) {
            log.error("error" , e);
            throw e;
        } finally {
            close(con, prmpst, null);
        }

    }

    public void delete(String member_id) throws SQLException{
        String sql = "DELETE FROM MEMBER WHERE member_id = ?";

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, member_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("error", e);
            throw e;
        } finally {
            close(con, preparedStatement, null);
        }
    }

    private Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}
