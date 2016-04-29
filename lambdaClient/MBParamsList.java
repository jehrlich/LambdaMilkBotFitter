package lambdaClient;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * A list of mbparams.
 **/

public class MBParamsList {
	private String name;
	private List<MBParams> mbp = new ArrayList<MBParams>();

	public MBParamsList() {
		super();
	}

	public MBParamsList(String name, List<MBParams> mbp) {
		this();
		this.name = name;
		this.setMbp(mbp);
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
		MBParamsList lactationList = (MBParamsList) o;
		return Objects.equals(this.mbp, lactationList.mbp)
				&& Objects.equals(this.name, lactationList.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, mbp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MBParamsList {\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    mbparams: ").append(toIndentedString(mbp)).append("\n");
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

	public List<MBParams> getMbp() {
		return mbp;
	}

	public void setMbp(List<MBParams> mbp) {
		this.mbp = mbp;
	}
}
