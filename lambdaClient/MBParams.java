package lambdaClient;

import java.util.Objects;

/**
 * Parameter set for the MilkBot(R) model ((1 - E^((offset - t)/ramp)/2)*scale)/E^(decay*t) where t is time since calving (DIM, in
 * days). and E is Euler&#39;s number
 **/

public class MBParams {

	private String idKey = null;
	private float scaleInMU;// NB UNLIKE com.milkbot.data.Params, this is in units specified by MilkUnit
	private float ramp;
	private float offset;
	private float decay;
	private int ptCt;
	private float stdError;
	private MilkUnit mu; // keep this in MBParams rather than list because list might be partitioned by user so redundancy is
							// warranted.

	public MBParams() {
		super();
	}

	/**
	 * an identifier to key fitted parameters to a lactation with the same id_key in the request. For this reason, id_key values
	 * should be unique within a LactationList.
	 **/
	public MBParams(String idKey) {
		super();
		this.idKey = idKey;
	}

	public String getIdKey() {
		return idKey;
	}

	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}

	public float getScale() {
		return scaleInMU;
	}

	public void setScale(float scale) {
		this.scaleInMU = scale;
	}

	public Float getRamp() {
		return ramp;
	}

	public void setRamp(Float ramp) {
		this.ramp = ramp;
	}

	public Float getOffset() {
		return offset;
	}

	public void setOffset(Float offset) {
		this.offset = offset;
	}

	public Float getDecay() {
		return decay;
	}

	public void setDecay(Float decay) {
		this.decay = decay;
	}

	public int getPtCt() {
		return ptCt;
	}

	public void setPtCt(int ptCt) {
		this.ptCt = ptCt;
	}

	public Float getStdError() {
		return stdError;
	}

	public void setStdError(Float stdError) {
		this.stdError = stdError;
	}

	public MilkUnit getMu() {
		return mu;
	}

	public void setMu(MilkUnit milkUnit) {
		this.mu = milkUnit;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MBParams mBParams = (MBParams) o;
		return Objects.equals(this.idKey, mBParams.idKey)
				&& Objects.equals(this.scaleInMU, mBParams.scaleInMU)
				&& Objects.equals(this.ramp, mBParams.ramp)
				&& Objects.equals(this.offset, mBParams.offset)
				&& Objects.equals(this.decay, mBParams.decay)
				&& Objects.equals(this.ptCt, mBParams.ptCt)
				&& Objects.equals(this.stdError, mBParams.stdError)
				&& Objects.equals(this.mu, mBParams.mu);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idKey, scaleInMU, ramp, offset, decay, ptCt,
				stdError, mu);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MBParams {\n");

		sb.append("    idKey: ").append(toIndentedString(idKey)).append("\n");
		sb.append("    scale: ").append(toIndentedString(scaleInMU))
				.append("\n");
		sb.append("    ramp: ").append(toIndentedString(ramp)).append("\n");
		sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
		sb.append("    decay: ").append(toIndentedString(decay)).append("\n");
		sb.append("    ptCt: ").append(toIndentedString(ptCt)).append("\n");
		sb.append("    stdError: ").append(toIndentedString(stdError))
				.append("\n");
		sb.append("    milkUnit: ").append(toIndentedString(mu)).append("\n");
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
