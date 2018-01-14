package org.frekele.cielo.lio.remote.client.junit;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Tag("timed")
@ExtendWith(TimingExtension.class)
public interface TimeExecutionLogger {

}
