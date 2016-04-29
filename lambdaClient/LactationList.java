package lambdaClient;

import java.util.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of lactations with a name and MilkUnit for milk measurement (default kg).
 **/

public class LactationList {

	private String name;
	private List<Lactation> lactations = new ArrayList<Lactation>();
	private MilkUnit mu = MilkUnit.KG; // default to kg milk units
	// private PopulationPriors priors = null;

	public LactationList() {
		super();
	}

	public LactationList(String name) {
		this();
		this.name = name;
	}

	public List<Lactation> getLactations() {
		return lactations;
	}

	public void setLactations(List<Lactation> lactations) {
		this.lactations = lactations;
	}

	public MilkUnit getMilkUnit() {
		return mu;
	}

	public void setMilkUnit(MilkUnit mu) {
		this.mu = mu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LactationList lactationList = (LactationList) o;
		return Objects.equals(this.lactations, lactationList.lactations)
				&& Objects.equals(this.name, lactationList.name)
				&& Objects.equals(this.mu, lactationList.mu);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, lactations, mu);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LactationList {\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    lactations: ").append(toIndentedString(lactations))
				.append("\n");
		sb.append("    milk_unit: ").append(toIndentedString(mu)).append("\n");
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
