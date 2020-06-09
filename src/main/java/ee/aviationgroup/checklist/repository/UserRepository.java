package ee.aviationgroup.checklist.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public boolean userExists (String username){
//        Integer count = jdbcTemplate.queryForObject("select count(id) from user where username = ?", new Object[] {isername})
//    }
//
//}
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public boolean userExists(String username) {
//        Integer count = jdbcTemplate.queryForObject(
//                "select count(id) from user where username = ?",
//                new Object[]{username},
//                Integer.class
//        );
//        return count != null && count > 0;
    }