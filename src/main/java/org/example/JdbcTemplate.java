package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* JdbcTemplate 은 라이브러리로 구성
 * - sql 과 같이 변경되는 항목은 외부에서 받아 옴
 */
public class JdbcTemplate {
    public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setter(pstmt);

            pstmt.executeUpdate();
        } finally {
            if(pstmt != null) {
                pstmt.close();
            }

            if(con != null) {
                con.close();
            }
        }
    }


    public Object executeQuery(String userId, String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        // 조회된 값을 가져옴
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setter(pstmt);

            rs = pstmt.executeQuery();

            Object obj = null;
            if(rs.next()) {
                return rowMapper.map(rs);
            }

            return obj;
        } finally {
            // 자원 해제 수행
            if(rs != null) {
                rs.close();
            }

            if(pstmt != null) {
                pstmt.close();
            }

            if(con != null) {
                con.close();
            }
        }
    }
}
