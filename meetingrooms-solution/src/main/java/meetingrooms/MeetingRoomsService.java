package meetingrooms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MeetingRoomsService {

    public MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int length) {
        meetingRoomsRepository.save(name, width, length);
    }

    public List<MeetingRoom> meetingRooms() {
        return meetingRoomsRepository.meetingRooms();
    }

    public List<String> meetingRoomsOrderByName() {
        return meetingRoomsRepository.meetingRoomsOrderByName();
    }

    public List<String> meetingRoomsOrderByNameReverse() {
        return meetingRoomsRepository.meetingRoomsOrderByNameReverse();
    }

    public List<String> printEvenNames() {
        List<String> ordered = meetingRoomsRepository.meetingRoomsOrderByName();

        return IntStream.range(0, ordered.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> ordered.get(i))
                .collect(Collectors.toList());
    }
}
