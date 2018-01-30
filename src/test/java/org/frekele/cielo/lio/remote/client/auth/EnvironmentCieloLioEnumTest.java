package org.frekele.cielo.lio.remote.client.auth;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Listeners(InvokedMethodListener.class)
public class EnvironmentCieloLioEnumTest {

    @Test
    public void testNewInstance() throws Exception {
        EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.fromValue("PRODUCTION");
        assertNotEquals(null, environment);
        environment = EnvironmentCieloLioEnum.fromValue("SANDBOX");
        assertNotEquals(null, environment);
        environment = EnvironmentCieloLioEnum.fromValue(null);
        assertNull(environment);
        environment = EnvironmentCieloLioEnum.fromValue("");
        assertNull(environment);
        environment = EnvironmentCieloLioEnum.fromValue("XXXXXXXXXX");
        assertNull(environment);
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
