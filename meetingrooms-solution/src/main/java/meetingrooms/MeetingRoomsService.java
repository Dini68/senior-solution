package meetingrooms;

import java.util.List;

public class MeetingRoomsService {

    public MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int length) {
        meetingRoomsRepository.save(name, width, length);
    }

    public List<MeetingRoom> meetingRooms() {
        return meetingRoomsRepository.findAll();
    }
}
