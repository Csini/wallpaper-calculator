package hu.exercise.waca.room;

public class RoomException extends Exception{

	public RoomException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomException(String message) {
		super(message);
	}

	public RoomException(Throwable cause) {
		super(cause);
	}

}
