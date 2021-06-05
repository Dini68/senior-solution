package meetingrooms;

import java.text.Collator;
import java.util.*;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository{

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<MeetingRoom> meetingRooms() {
        return new ArrayList<>(meetingRooms);
    }

    @Override
    public List<String> meetingRoomsOrderByName() {
        List<String> result = new ArrayList();
        for (MeetingRoom mr: meetingRooms) {
            result.add(mr.getName());
        }
        Collections.sort(result, Collator.getInstance(new Locale("hu", "HU")));
        return result;
    }

    @Override
    public List<String> meetingRoomsOrderByNameReverse() {
        List<String> result = new ArrayList();
        for (MeetingRoom mr: meetingRooms) {
            result.add(mr.getName());
        }
        Collections.sort(result, Collator.getInstance(new Locale("hu", "HU")).reversed());
        return result;
    }

    @Override
    public void areas() {
        ;
    }

}
