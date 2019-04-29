package uk.co.aquamatix;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import uk.co.aquamatix.repository.ModemRepository;

public class SiteControllerIntegrationTest {

	MockMvc mockMvc;

	@Mock
	ModemRepository repo;

	@InjectMocks
	SiteController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver())
				.build();
	}

	private InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Test
	public void rootUrlPopulatesViewModel() throws Exception {
		mockMvc.perform(get("/")).andDo(print())
				.andExpect(model().attributeExists("modems"));
	}

}
