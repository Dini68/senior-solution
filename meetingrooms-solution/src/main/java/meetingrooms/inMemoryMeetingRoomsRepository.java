package meetingrooms;

import java.util.ArrayList;
import java.util.List;

public class inMemoryMeetingRoomsRepository implements MeetingRoomsRepository{

    private List<MeetingRoom> meetingRooms = new ArrayList<>();


    @Override
    public void save(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<MeetingRoom> findAll() {
        return new ArrayList<>(meetingRooms);
    }
}
