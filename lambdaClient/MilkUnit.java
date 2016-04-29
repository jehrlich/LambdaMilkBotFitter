package lambdaClient;

import java.util.Objects;

/**
 * Unit of measurement used for scale and milk weights, defaulting to kg.
 **/

public class MilkUnit {
	private MUEnum mue = MUEnum.KG; // default kg
	public static float LB_PER_KG = 2.20462f;
	public static float KG_PER_LITER = 1.03f;
	public static MilkUnit KG = new MilkUnit(MUEnum.KG);
	public static MilkUnit LB = new MilkUnit(MUEnum.LB);
	public static MilkUnit L = new MilkUnit(MUEnum.L);
	public static MilkUnit G = new MilkUnit(MUEnum.G);

	public enum MUEnum {
		LB("lb"), KG("kg"), L("l"), G("g");
		private String value;

		MUEnum(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}

		/*
		 * Multiply recorded scale value by this factor to convert to Kg.
		 */
		public static float getToKgFactor(MUEnum bu) {
			switch (bu) {
			case LB:
				return 1 / LB_PER_KG;
			case KG:
				return 1f;
			case L:
				return KG_PER_LITER;
			case G:
				return .001f;
			default:
				return 1f;
			}
		}

		public static float getToLbFactor(MUEnum bu) {
			switch (bu) {
			case LB:
				return 1f;
			case KG:
				return LB_PER_KG;
			case L:
				return LB_PER_KG / KG_PER_LITER;
			case G:
				return LB_PER_KG / 1000;
			default:
				return 1f;
			}
		}

	}

	public MilkUnit() {
		super();
	}

	public MilkUnit(MUEnum name) {
		this();
		setMue(name);
	}



	public MUEnum getMue() {
		return mue;
	}

	public void setMue(MUEnum bu) {
		this.mue = bu;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MilkUnit milkUnit = (MilkUnit) o;
		return Objects.equals(this.mue, milkUnit.mue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mue);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MilkUnit {\n");

		sb.append("    name: ").append(toIndentedString(mue)).append("\n");
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
