package uk.co.aquamatix.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.aquamatix.NWaveModem;

public class MemoryModemRepo implements ModemRepository {

	static Map<String, NWaveModem> modems = new HashMap<String, NWaveModem>();
	private static Logger LOGGER;

	static {
		LOGGER = LoggerFactory.getLogger(MemoryModemRepo.class);
		modems.put("52360", new NWaveModem("52360"));
		modems.put("52507", new NWaveModem("52507"));
		modems.put("55979", new NWaveModem("55979"));
		modems.put("57290", new NWaveModem("57290"));
		modems.put("57290", new NWaveModem("57290"));
	}

	@Override
	public Collection<NWaveModem> getAllModems() {
		return Collections.unmodifiableCollection(modems.values());
	}

	@Override
	public NWaveModem getModem(String deviceID) {
		return modems.get(deviceID);
	}

	@Override
	public NWaveModem addModem(NWaveModem modem) {
		return modems.put(modem.getDeviceID(), modem);
	}

	@Override
	public boolean containsModem(String deviceID) {
		return modems.containsKey(deviceID);
	}
}
