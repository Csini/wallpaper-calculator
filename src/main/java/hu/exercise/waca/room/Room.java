package hu.exercise.waca.room;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Room {

	private static final Logger LOG = LogManager.getLogger(Room.class);

	private int length;

	private int width;

	private int height;

	private Integer neededWallpaper;
	
	//cube
	public Room(int size) throws RoomException {
		this(size, size, size);
	}

	public Room(int length, int width, int height) throws RoomException {

		this.length = length;
		this.width = width;
		this.height = height;

		if (width <= 0) {
			throw new RoomException("invalid width: " + width);
		}
		if (length <= 0) {
			throw new RoomException("invalid length: " + length);
		}
		if (height <= 0) {
			throw new RoomException("invalid height: " + height);
		}
	}

	public int getNeededWallpaper() {

		// cache
		if (this.neededWallpaper != null) {
			return this.neededWallpaper;
		}

		// smallest side
		int extra = Math.min(Math.min(width * height, height * length), length * width);

		// 2\*l\*w + 2\*w\*h + 2\*h\*l
		int temp = 2 * length * width + 2 * width * height + 2 * height * length + extra;

		if (LOG.isDebugEnabled()) {
			LOG.debug(this + " needs " + temp);
		}
		this.neededWallpaper = temp;
		return this.neededWallpaper;
	}

	public boolean isCubic() {
		// euqality is transitive
		return ((length == width) && (length == height));
	}

	@Override
	public String toString() {
		return "Room [length=" + length + ", width=" + width + ", height=" + height + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + length;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Room other = (Room) obj;
		if (height != other.height) {
			return false;
		}
		if (length != other.length) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		return true;
	}

}
