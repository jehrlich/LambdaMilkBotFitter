package lambdaClient;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lambdaClient.Lactation.BreedEnum;

/*  Copyright (C) 2008 DairySight LLC
 * type com.amazonaws.ClientConfiguration cannot be resolved. It is indirectly referenced from required .class files
	- The type com.amazonaws.AmazonWebServiceClient cannot be resolved. It is indirectly referenced from required .class files
	- The type com.amazonaws.auth.AWSCredentialsProvider cannot be resolved. It is indirectly referenced from 
	 required .class files
	- The type com.amazonaws.auth.AWSCredentials cannot be resolved. It is indirectly referenced from required .class files
	
 * Unless required by applicable law or agreed to in writing, this software  is distributed on an 
 * "AS IS" BASIS,  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

/**
 * @author jimehrlich
 *
 */
public class MBFitClient {
	// AWS credentials 
	private static String ACCESS_KEY_ID = "**replace with real ID**";
	private static String SECRET_ACCESS_KEY = "**replace with real secret**";
	private static Regions AWS_REGION = Regions.EU_WEST_1; // Ireland// US_EAST_1;//

	/**
	 * 
	 */
	public MBFitClient() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * create an example list of lactations for demo and testing
	 */
	public static LactationList createTestInput() {
		List<Lactation> lactations = new ArrayList<Lactation>();
		List<MilkPt> p1 = new ArrayList<MilkPt>();
		p1.add(new MilkPt(22, 45));
		p1.add(new MilkPt(72, 65.2f));
		p1.add(new MilkPt(92, 45.12345f));
		lactations.add(
				new Lactation("Jersey P2, datapoints p1", BreedEnum.J, 2, p1));
		// reuse same points with Holstein
		// since Bayesian priors are based on breed and parity, the different priors will
		// lead to different fitted parameters
		lactations.add(new Lactation("Holstein P2, datapoints p1", BreedEnum.H,
				2, p1));
		// and changing parity also influences the fit
		lactations.add(new Lactation("Holstein P3, datapoints p1", BreedEnum.H,
				3, p1));
		List<MilkPt> p2 = new ArrayList<MilkPt>();
		p2.add(new MilkPt(27, 22));
		p2.add(new MilkPt(72, 36));
		p2.add(new MilkPt(92, 21));
		p2.add(new MilkPt(122, 21));
		lactations.add(new Lactation("Holstein P2, datapoints p2", BreedEnum.H,
				2, p2));
		// you can also specify milk production in a fractional day
		// points below should be the same per-day milk as p2 above
		List<MilkPt> p2b = new ArrayList<MilkPt>();
		p2b.add(new MilkPt(27, 11, 0.5f)); // i.e. milk per 12 hr -> 22kg/d
		p2b.add(new MilkPt(42, 9, 0.25f)); // or per 6 hr ->36kg/d
		p2b.add(new MilkPt(62, 441));
		p2b.add(new MilkPt(82, 35));
		p2b.add(new MilkPt(92, 33));
		p2b.add(new MilkPt(122, 28));
		p2b.add(new MilkPt(132, 26));
		p2b.add(new MilkPt(152, 26));
		p2b.add(new MilkPt(192, 23));
		p2b.add(new MilkPt(222, 23));
		p2b.add(new MilkPt(245, 21));
		p2b.add(new MilkPt(300, 21));
		// more lactations, raise limit for stress testing
		for (int i = 0; i < 1; i++) { // tested up to i<10000 -->148 sec
			lactations.add(new Lactation("Holstein P2, datapoints p2b",
					BreedEnum.H, 2, p2b));
		}

		LactationList input = new LactationList("testlist");
		input.setLactations(lactations);
		input.setMilkUnit(MilkUnit.KG); // this also is the default
		return input;
	}

	/**
	 * For testing JSON serialization or creating test input
	 * 
	 * @param o
	 *            a POJO for serialization
	 * @return JSON String or StcakTrace String in case of error
	 */
	public static String toJson(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return e.getStackTrace().toString();
		}
	}

	public static <T> T fromJson(String json, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (T) mapper.readValue(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		AWSLambdaClient lambda = new AWSLambdaClient(
				new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		lambda.configureRegion(AWS_REGION);
		// service binds this client to AWS lambda function using MBLactationFitterService
		MBLactationFitterService service = LambdaInvokerFactory
				.build(MBLactationFitterService.class, lambda);
		// fit example lactations list
		MBParamsList fit = null;
		LactationList input = createTestInput();
		// for testing
		System.out.println(toJson(input));
		fit = service.fitLactation(input);
		// System.out.println(fit); // lambda function output
		System.out.println(fit.getMbp().size() + " lactations fitted in "
				+ (System.currentTimeMillis() - start) / 1000 + " seconds."); // lambda function output
	}

}
