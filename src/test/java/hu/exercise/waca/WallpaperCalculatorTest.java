package hu.exercise.waca;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hu.exercise.waca.room.Room;
import hu.exercise.waca.room.RoomException;
import hu.exercise.waca.room.RoomReport;

public class WallpaperCalculatorTest {

	@Test
	public void test_sample_input_calculateSum() throws IOException, URISyntaxException {
		String inputFileName = "sample-input.txt";

		Assertions.assertEquals(1591812, WallpaperCalculator.calculateSum(inputFileName));
	}

	@Test
	public void test_sample_input_findDuplicates() throws IOException, URISyntaxException, RoomException {
		String inputFileName = "sample-input.txt";

		Set<Room> expectedDuplicates = new HashSet<>(Arrays.asList(new Room[] { new Room(15, 26, 22),
				new Room(8, 28, 29), new Room(22, 27, 12), new Room(6, 18, 15), new Room(17, 25, 1),
				new Room(15, 10, 7), new Room(8, 8, 16), new Room(17, 15, 2), new Room(2, 25, 8), new Room(6, 8, 12),
				new Room(4, 3, 23), new Room(22, 3, 1), new Room(7, 3, 4)

		}));

		Assertions.assertEquals(expectedDuplicates, WallpaperCalculator.findDuplicates(inputFileName));
	}

	@Test
	public void test_sample_input_findCubes() throws IOException, URISyntaxException, RoomException {
		String inputFileName = "sample-input.txt";

		List<Room> expectedCubes = Arrays
				.asList(new Room[] { new Room(28), new Room(15), new Room(12), new Room(9), new Room(7) });

		Assertions.assertEquals(expectedCubes, WallpaperCalculator.findCubes(inputFileName));
	}

	@Test
	public void test_sample_input_runFullReport() throws IOException, URISyntaxException, RoomException {
		String inputFileName = "sample-input.txt";

		RoomReport expectedRoomReport = new RoomReport(inputFileName);

		Set<Room> expectedDuplicates = new HashSet<>(Arrays.asList(new Room[] { new Room(15, 26, 22),
				new Room(8, 28, 29), new Room(22, 27, 12), new Room(6, 18, 15), new Room(17, 25, 1),
				new Room(15, 10, 7), new Room(8, 8, 16), new Room(17, 15, 2), new Room(2, 25, 8), new Room(6, 8, 12),
				new Room(4, 3, 23), new Room(22, 3, 1), new Room(7, 3, 4)

		}));

		List<Room> expectedCubes = Arrays
				.asList(new Room[] { new Room(28), new Room(15), new Room(12), new Room(9), new Room(7) });

		expectedRoomReport.setSum(1591812);
		expectedRoomReport.setDuplicates(expectedDuplicates);
		expectedRoomReport.setCubes(expectedCubes);

		Assertions.assertEquals(expectedRoomReport, WallpaperCalculator.runFullReport(inputFileName));
	}

	@Test
	public void test_error_input_calculateSum() throws IOException, URISyntaxException {
		String inputFileName = "error-input.txt";
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			WallpaperCalculator.calculateSum(inputFileName);
		});
	}
}
