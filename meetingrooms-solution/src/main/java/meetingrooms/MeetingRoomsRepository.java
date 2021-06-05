package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

    List<MeetingRoom> meetingRooms();

    List<String> meetingRoomsOrderByName();

    List<String> meetingRoomsOrderByNameReverse();

    void areas();
}
