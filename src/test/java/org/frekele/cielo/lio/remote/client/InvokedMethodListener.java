package org.frekele.cielo.lio.remote.client;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println(this.getTestClassAndMethodName(method) + " Start method --->");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        long duration = testResult.getEndMillis() - testResult.getStartMillis();
        System.out.println(this.getTestClassAndMethodName(method) + " Duration: [ " + duration + "ms ];");
        System.out.println(this.getTestClassAndMethodName(method) + " <--- End method.\n");
        //After Invocation Sleep 1 seg, for prevent (HTTP 429 Unknown Code).
        this.sleep(1);
    }

    private String getTestClassAndMethodName(IInvokedMethod method) {
        return this.getTestClassName(method) + "[" + this.getMethodName(method) + "]";
    }

    private String getTestClassName(IInvokedMethod method) {
        return method.getTestResult().getInstanceName();
    }

    private String getMethodName(IInvokedMethod method) {
        return method.getTestMethod().getMethodName();
    }

    private void sleep(long seconds) {
        try {
            long millis = seconds * 1000;
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }
}
