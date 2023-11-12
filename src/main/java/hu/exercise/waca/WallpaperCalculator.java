package hu.exercise.waca;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hu.exercise.waca.room.Room;
import hu.exercise.waca.room.RoomException;
import hu.exercise.waca.room.RoomReport;

public class WallpaperCalculator {

	private static final Logger LOG = LogManager.getLogger(WallpaperCalculator.class);

	public static void main(String[] args) throws IOException, URISyntaxException {
		runFullReport(args[0]);
	}

	/**
	 * calculates number of total square feet of wallpaper the company should order
	 * for all rooms from input, lists all rooms from input that have a cubic shape
	 * (order by total needed wallpaper descending), lists all rooms from input that
	 * are appearing more than once (order is irrelevant)
	 * 
	 * @param inputFileName File contains list of room dimensions as input
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static RoomReport runFullReport(String inputFileName) throws IOException, URISyntaxException {

		RoomReport roomReport = new RoomReport(inputFileName);

		List<Room> rooms = readRooms(inputFileName);
		roomReport.setSum(_calculateSum(rooms.stream()));
		roomReport.setDuplicates(_findDuplicates(rooms.stream()));
		roomReport.setCubes(_findCubes(rooms.stream()));

		return roomReport;
	}

	/**
	 * calculates number of total square feet of wallpaper the company should order
	 * for all rooms from input
	 * 
	 * @param inputFileName File contains list of room dimensions as input
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static int calculateSum(String inputFileName) throws IOException, URISyntaxException {

		List<Room> rooms = readRooms(inputFileName);
		return _calculateSum(rooms.stream());
	}

	/**
	 * lists all rooms from input that are appearing more than once (order is
	 * irrelevant)
	 * 
	 * @param inputFileName File contains list of room dimensions as input
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static Set<Room> findDuplicates(String inputFileName) throws IOException, URISyntaxException {

		List<Room> rooms = readRooms(inputFileName);
		return _findDuplicates(rooms.stream());
	}

	/**
	 * lists all rooms from input that have a cubic shape (order by total needed
	 * wallpaper descending)
	 * 
	 * @param inputFileName File contains list of room dimensions as input
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static List<Room> findCubes(String inputFileName) throws IOException, URISyntaxException {

		List<Room> rooms = readRooms(inputFileName);
		return _findCubes(rooms.stream());
	}

	private static int _calculateSum(Stream<Room> rooms) throws IOException {

		int sum = rooms.map(room -> room.getNeededWallpaper()).collect(Collectors.summingInt(n -> n));

		if (LOG.isDebugEnabled()) {
			LOG.debug("sum:" + sum);
		}
		return sum;
	}

	private static Set<Room> _findDuplicates(Stream<Room> rooms) throws IOException {

		Set<Room> orderedDullpicateRooms = rooms
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toSet());

		if (LOG.isDebugEnabled()) {
			LOG.debug("orderedDullpicateRooms: " + orderedDullpicateRooms);
		}
		return orderedDullpicateRooms;
	}

	private static List<Room> _findCubes(Stream<Room> rooms) throws IOException {

		List<Room> cubicRooms = rooms.filter(room -> room.isCubic())
				.sorted(Comparator.comparingInt(Room::getNeededWallpaper).reversed()).collect(Collectors.toList());

		if (LOG.isDebugEnabled()) {
			LOG.debug("cubicRooms: " + cubicRooms);
		}
		return cubicRooms;
	}

	private static List<Room> readRooms(String inputFileName) throws IOException, URISyntaxException {

		Path inputPath = Paths.get(WallpaperCalculator.class.getClassLoader().getResource(inputFileName).toURI());
		
//		List<String> roomsWithError;
		List<Room> rooms;

		try (Scanner s = new Scanner(inputPath)) {
			rooms = s.findAll("(.*\\R){1,1}").map(mr -> {

				String group = mr.group();
				try {
					String[] dimensions = group.split("x");

					if (dimensions.length != 3) {
						LOG.error(group);
						throw new IllegalArgumentException("invalid dimensions: " + Arrays.asList(dimensions));
					}

					return new Room(Integer.valueOf(dimensions[0]), Integer.valueOf(dimensions[1]),
							Integer.valueOf(dimensions[2].trim()));
				} catch (RoomException e) {
					LOG.error(group, e);
//					roomsWithError.add(group);
//					return null;
					throw new IllegalArgumentException(e);
				}
			})
//					.filter(room -> room != null)
					.collect(Collectors.toList());

		}
		return rooms;
	}

}
