package uk.co.aquamatix.repository;

import java.util.Collection;

import uk.co.aquamatix.NWaveModem;

public interface ModemRepository {

	Collection<NWaveModem> getAllModems();

	NWaveModem getModem(String deviceID);

	boolean containsModem(String deviceID);

	NWaveModem addModem(NWaveModem modem);
}
