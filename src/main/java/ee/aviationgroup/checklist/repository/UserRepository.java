package ee.aviationgroup.checklist.repository;


import ee.aviationgroup.checklist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean userExists(String username) {
        Integer count = jdbcTemplate.queryForObject("select count(id) from user where username = ?", new Object[]{username}, Integer.class);
        return count != null && count > 0;
    }
    public User getUserByUsername(String username) {
        List<User> users = jdbcTemplate.query(
                "select * from `user` where `username` = ?",
                new Object[]{username},
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"))
        );
        return users.size() > 0 ? users.get(0) : null;
    }

}

