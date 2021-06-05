package meetingrooms;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariadbMeetingRoomsRepository implements MeetingRoomsRepository{

    private JdbcTemplate jdbcTemplate;

    public MariadbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource;
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();

            flyway.migrate();
            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException se) {
            throw new IllegalStateException("Can not create data source", se);
        }
    }

    @Override
    public void save(String name, int width, int length) {
        jdbcTemplate.update("insert into meeting_rooms(room_name, width, length) values(?,?,?)", name, width, length);
    }

    @Override
    public List<MeetingRoom> meetingRooms() {
        return jdbcTemplate.query("select * from meeting_rooms",
                (rs, i) -> new MeetingRoom(
                        rs.getLong("id"),
                        rs.getString("room_name"),
                        rs.getInt("width"),
                        rs.getInt("length")));
    }

    @Override
    public List<String> meetingRoomsOrderByName() {
        return jdbcTemplate.query("select room_name from meeting_rooms order by room_name",
                (rs, i) -> rs.getString("room_name"));
    }

    @Override
    public List<String> meetingRoomsOrderByNameReverse() {
        return jdbcTemplate.query("select * from meeting_rooms order by room_name desc",
                (rs, i) -> rs.getString("room_name"));
    }

    @Override
    public void areas() {
        ;
    }

}
