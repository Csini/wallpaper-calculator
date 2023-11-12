package hu.exercise.waca.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomTest {

	@Test
	public void test_needed_wallpaper_123() throws RoomException {
		Room room = new Room(1, 2, 3);

		Assertions.assertEquals(24, room.getNeededWallpaper());
	}

	@Test
	public void test_needed_wallpaper_115() throws RoomException {
		Room room = new Room(1, 1, 5);

		Assertions.assertEquals(23, room.getNeededWallpaper());
	}

	@Test
	public void test_needed_wallpaper_203() throws RoomException {

		Assertions.assertThrows(RoomException.class, () -> {

			Room room = new Room(2, 0, 3);
			room.getNeededWallpaper();
			
		});
	}
}
