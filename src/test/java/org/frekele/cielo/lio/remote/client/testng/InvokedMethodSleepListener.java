package org.frekele.cielo.lio.remote.client.testng;

import org.testng.IInvokedMethod;
import org.testng.ITestResult;

public class InvokedMethodSleepListener extends InvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        super.afterInvocation(method, testResult);
        //After Invocation Sleep 1 seg, for prevent (HTTP 429 Unknown Code).
        this.sleep(1);
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
