package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
    User map(ResultSet resultSet) throws SQLException;
}
