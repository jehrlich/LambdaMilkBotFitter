#Java client for fitting lactations using the MilkBotFitter AWS Lambda function

This is a proof of concept project using the [AWS Lambda Function service](https://aws.amazon.com/lambda/). MBFitCLient.java creates a POJO LactationList object which is serialized to JSON and sent to the AWS Lambda service. The MilkBotFitter lambda service deserializes the LactationList, fits each lactation in the list, and returns a corresponding list of MilkBot parameter sets serialized as JSON. See the other Java classes in this package for the data model for LactationList and the returned MBParamsList objects. There are also some convenience methods in MBFitClient.java for creating test lactations and testing JSON serialization. 

##Credentials
To invoke the MilkBotFitter function from your server you will need AWS credentials with appropriate privileges, which I will be happy to supply if you email me at jehrlich@dairyvets.com. Credentials consist of ID and  access keys to be entered at the top of the file MBFitCLient.java replacing the dummy *** below. There are other ways to supply credentials described in the AWS documentation. The AWS region also needs to be specified as below.

    private static String ACCESS_KEY_ID = "****************";
    private static String SECRET_ACCESS_KEY = "************";
    private static Regions AWS_REGION = Regions.EU_WEST_1;

Please ask me for as many sets of credentials as you need, using each set of credentials for only one project. This is so I can keep some track of how the fitter is being used.

##other SDKs
Amazon AWS supplies many [other SDKs](https://aws.amazon.com/tools/) you may use to build a Lambda client. I have only used the Java SDK. I'm not sure whether all SDKs support a LambdaClient directly.  

##Cognito
AWS also supplies a service to provide temporary access tokens for Lambda access. This is the approach that should be used if you are developing an app for users and want to include MilkBot fitting, because your AWS credentials should remain protected on your server and not exposed within client-side app. Cntact me if you want help setting this up.

##Limitations
I suppose that at some point available memory will limit how many lactations (and how many points per lactation) can be fitted in one function call. I have tested with 10,000 lactations of 12 points each, which took 148 seconds to execute and return.

MilkBotFitter should be thread safe. It's OK to call it multiple times without waiting for the first call to return.

##Future enhancements
Fitting is a Bayesian process using prior expectations for parameter values based on breed and parity norms. These norms can be expected to vary between regions, times, and herds, so fitting accuracy (especially with sparse data sets) will be enhanced by customizing prior expectations. This will not be hard to implement if it is wanted.
