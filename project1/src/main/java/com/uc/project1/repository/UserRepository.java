package com.uc.project1.repository;

import com.uc.project1.dto.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, email, name FROM users WHERE email = ?";
        return jdbc.query(sql, ps -> ps.setString(1, email),
                        (rs, rowNum) -> new User(rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("name")))
                .stream().findFirst();
    }
}
