package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

    List<MeetingRoom> findAll();
}
