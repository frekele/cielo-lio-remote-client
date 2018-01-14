package org.frekele.cielo.lio.remote.client;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println(this.getTestClassAndMethodName(method) + " Before invocation.");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println(this.getTestClassAndMethodName(method) + " After invocation.");
        long duration = testResult.getEndMillis() - testResult.getStartMillis();
        System.out.println(this.getTestClassAndMethodName(method) + " Duration: [ " + duration + "ms ]");
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
}
