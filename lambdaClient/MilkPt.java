package lambdaClient;

import java.util.Objects;

/**
 * Measured milk production data point.
 **/

public class MilkPt {

	private int dim;
	private float milk; // milk units defined in lactation
	private Float dayFraction = null; // default, null treated as equivalent to 1.0

	public MilkPt() {
		super();
	}

	public MilkPt(int dim, float milk) {
		this();
		this.dim = dim;
		this.milk = milk; // dayFraction defaults to null = 1
	}

	public MilkPt(int dim, float milk, Float dayFrac) {
		this();
		this.dim = dim;
		setDayFraction(dayFrac);// DRY bounds checking
		setMilk(milk); // DRY use dayFraction
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}

	public Float getMilk() {
		return milk;
	}

	public void setMilk(float milk) {
		if (dayFraction == null)
			this.milk = milk;
		else
			this.milk = milk / dayFraction;
	}

	public Float getDayFraction() {
		return dayFraction;
	}

	public void setDayFraction(Float dayFraction) {
		if (dayFraction != null && (dayFraction == 1 || dayFraction <= 0)) // set null <=0 or = 1 but allow > 1 e.g. EOD milking
			dayFraction = null;
		this.dayFraction = dayFraction;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MilkPt milkPt = (MilkPt) o;
		return Objects.equals(this.dim, milkPt.dim)
				&& Objects.equals(this.milk, milkPt.milk)
				&& Objects.equals(this.dayFraction, milkPt.dayFraction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dim, milk, dayFraction);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MilkPt {\n");

		sb.append("    dim: ").append(toIndentedString(dim)).append("\n");
		sb.append("    milk: ").append(toIndentedString(milk)).append("\n");
		sb.append("    dayFraction: ").append(toIndentedString(dayFraction))
				.append("\n");
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
