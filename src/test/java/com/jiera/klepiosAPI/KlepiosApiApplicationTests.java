package com.jiera.klepiosAPI;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class KlepiosApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetPatients() throws Exception {
		mockMvc.perform(get("/api/patients"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("Chuck")));
	}
}
