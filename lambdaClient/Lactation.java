package lambdaClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * POJO for a single lactation of any length for simple conversion to and from JSON with function for conversion to a
 * com.milkbot.Lactation.
 **/

public class Lactation {
	private String idKey;
	private BreedEnum breed = BreedEnum.H;// default
	private int parity = 3; // default
	private List<MilkPt> points = new ArrayList<MilkPt>();
	// private MilkUnit mu = new MilkUnit(MUEnum.KG);
	// NB MilkUnit is set at the LactationList level

	public enum BreedEnum {
		H("H"), J("J"), BS("BS"), G("G"), X("X");
		private String value;

		BreedEnum(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	public Lactation() {
		super();
	}

	public Lactation(String idKey, BreedEnum breed, int parity,
			List<MilkPt> points) {
		this();
		this.idKey = idKey;
		if (breed != null) // see default
			this.breed = breed;
		if (parity > 0) // see default
			this.parity = parity;
		this.points = points;
	}

	public String getIdKey() {
		return idKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public BreedEnum getBreed() {
		return breed;
	}

	public void setBreed(BreedEnum breed) {
		if (breed != null) // see default
			this.breed = breed;
	}

	public int getParity() {
		return parity;
	}

	public void setParity(int parity) {
		if (parity > 0) // see default
			this.parity = parity;
	}

	public List<MilkPt> getPoints() {
		return points;
	}

	public void setPoints(List<MilkPt> points) {
		this.points = points;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Lactation lactation = (Lactation) o;
		return Objects.equals(this.idKey, lactation.idKey)
				&& Objects.equals(this.breed, lactation.breed)
				&& Objects.equals(this.parity, lactation.parity)
				&& Objects.equals(this.points, lactation.points);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idKey, breed, parity, points);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Lactation {\n");
		sb.append("    idKey: ").append(toIndentedString(idKey)).append("\n");
		sb.append("    breed: ").append(toIndentedString(breed)).append("\n");
		sb.append("    parity: ").append(toIndentedString(parity)).append("\n");
		sb.append("    points: ").append(toIndentedString(points)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
