package org.frekele.cielo.lio.remote.client.auth;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class EnvironmentCieloLioEnumTest {

    @Test
    public void testNewInstance() throws Exception {
        EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.valueOf("PRODUCTION");
        assertNotEquals(null, environment);
        environment = EnvironmentCieloLioEnum.valueOf("SANDBOX");
        assertNotEquals(null, environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<EnvironmentCieloLioEnum> listAll = EnvironmentCieloLioEnum.getAll();
        assertNotEquals(null, listAll);
        assertEquals(listAll.size(), 2);
        assertEquals(listAll.contains(EnvironmentCieloLioEnum.PRODUCTION), true);
        assertEquals(listAll.contains(EnvironmentCieloLioEnum.SANDBOX), true);
    }
}
