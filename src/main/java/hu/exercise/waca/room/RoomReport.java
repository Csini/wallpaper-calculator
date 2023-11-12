package hu.exercise.waca.room;

import java.util.List;
import java.util.Set;

public class RoomReport {
	private int sum;
	private Set<Room> duplicates;
	private List<Room> cubes;

	private String inputFileName;

	public RoomReport(String inputFileName) {
		super();
		this.inputFileName = inputFileName;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Set<Room> getDuplicates() {
		return duplicates;
	}

	public void setDuplicates(Set<Room> duplicates) {
		this.duplicates = duplicates;
	}

	public List<Room> getCubes() {
		return cubes;
	}

	public void setCubes(List<Room> cubes) {
		this.cubes = cubes;
	}

	public String getInputFileName() {
		return inputFileName;
	}

	@Override
	public String toString() {
		return "RoomReport [inputFileName=" + inputFileName + ", sum=" + sum + ", duplicates=" + duplicates + ", cubes="
				+ cubes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inputFileName == null) ? 0 : inputFileName.hashCode());
		result = prime * result + sum;
		result = prime * result + ((cubes == null) ? 0 : cubes.hashCode());
		result = prime * result + ((duplicates == null) ? 0 : duplicates.hashCode());
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
		RoomReport other = (RoomReport) obj;
		if (inputFileName == null) {
			if (other.inputFileName != null) {
				return false;
			}
		} else if (!inputFileName.equals(other.inputFileName)) {
			return false;
		}
		if (sum != other.sum) {
			return false;
		}
		if (cubes == null) {
			if (other.cubes != null) {
				return false;
			}
		} else if (!cubes.equals(other.cubes)) {
			return false;
		}
		if (duplicates == null) {
			if (other.duplicates != null) {
				return false;
			}
		} else if (!duplicates.equals(other.duplicates)) {
			return false;
		}
		return true;
	}

}
