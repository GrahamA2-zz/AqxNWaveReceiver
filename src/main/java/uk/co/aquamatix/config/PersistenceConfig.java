package uk.co.aquamatix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.co.aquamatix.repository.MemoryModemRepo;
import uk.co.aquamatix.repository.ModemRepository;

@Configuration
public class PersistenceConfig {

	@Bean
	ModemRepository modemRepository() {
		return new MemoryModemRepo();
	}
}
