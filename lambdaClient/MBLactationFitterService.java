package lambdaClient;

import com.amazonaws.services.lambda.invoke.LambdaFunction;
/**
 * Interface for Lambda service
 * functionName should be an existing Lambda function of that name which will be 
 * bound to the AWSLambdaClient client
 *  by LambdaInvokerFactory.build(MBLactationFitterService.class, client); 
 *  
 *  The function is then called as fitLactation(LactationList input) 
 *  * 
 * @author jimehrlich
 *
 */

public interface MBLactationFitterService {
	  @LambdaFunction(functionName="MilkBotFitter")
	  MBParamsList fitLactation(LactationList input);
}
