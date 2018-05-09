package space.qyvlik.jmeter.hello;

import org.apache.jmeter.config.Argument;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.ArrayList;
import java.util.List;

public class HelloJmeterTester extends AbstractJavaSamplerClient {

    private String name;
    private UseCaseTester useCaseTester;

    public static void main(String[] args) {
        System.out.println("hello HelloJmeterTester");
    }

    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();

        List<Argument> argumentList = new ArrayList();

        Argument name = new Argument("name",
                "hello-jmeter",
                "",
                "this is the name argument");

        argumentList.add(name);

        arguments.setArguments(argumentList);

        return arguments;
    }

    @Override
    public void setupTest(JavaSamplerContext jsc) {
        name = jsc.getParameter("name", "");
        useCaseTester = new UseCaseTester();
    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        // SampleResult not thread safe
        // declare SampleResult as local
        SampleResult results = new SampleResult();

        results.sampleStart();

        String result = useCaseTester.get();

        results.sampleEnd();

        results.setResponseData("hello: " + name + ", result:" + result, "UTF-8");

        if (result == null || result.equalsIgnoreCase("failure")) {
            results.setSuccessful(false);
        } else {
            results.setSuccessful(true);
        }

        return results;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {

        }
    }
}
