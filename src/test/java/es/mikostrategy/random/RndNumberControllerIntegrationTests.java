package es.mikostrategy.random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RndNumberControllerIntegrationTests {

	private static final String ARRAY_LENGTH = "array-length";
	private static final String NUMBER_LENGTH = "number-length";
	static final String METHOD_NAME = "getRndNumber";
	//final String errorMessageGreaterThanOne = "must be greater than or equal to 1";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenParamsPresentAndOk_ThenReturn200() throws Exception {
		final String numberOfArrayItems = "4";
		final String numberOfCharPerItems = "5";

		mockMvc.perform(get("/api/v1/rnd")
						.param(ARRAY_LENGTH,numberOfArrayItems)
						.param(NUMBER_LENGTH,numberOfCharPerItems))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(Integer.parseInt(numberOfArrayItems))))
				.andExpect(jsonPath("$.length()").value(numberOfArrayItems))
				.andDo(print());
	}

	@Test
	public void whenArrayLengthIsLessThanMinimum_ThenReturn500() {
		final String numberOfArrayItems = "0";
		final String numberOfCharPerItems = "4";
		final String paramName = "arrayLength";
		final String errorMessageRegExp = "(" + METHOD_NAME + ")(\\.)(" + paramName + ")(: )(.*)";
		final String errorLessThanOne = "The minimum value of the field 'array-length' must be greater than or equal to 1";

		try {
			mockMvc.perform(get("/api/v1/rnd")
							.param(ARRAY_LENGTH, numberOfArrayItems)
							.param(NUMBER_LENGTH, numberOfCharPerItems))
					.andExpect(status().isInternalServerError())
					.andExpect(content().string(errorLessThanOne))
					.andDo(print());
		} catch (Exception e) {
			assertThat(e, is(instanceOf(NestedServletException.class)));
			assertEquals(errorLessThanOne, e.getCause().getMessage().replaceAll(errorMessageRegExp,"$5"));
		}
	}

	@Test
	public void whenArrayLengthIsGreaterThanMaximum_ThenReturn500() {
		final String numberOfArrayItems = "21";
		final String numberOfCharPerItems = "5";
		final String paramName = "arrayLength";
		final String errorMessageRegExp = "(" + METHOD_NAME + ")(\\.)(" + paramName + ")(: )(.*)";
		final String errorLessThanOne = "The maximum value of the field 'number-length' must be less than 20";

		try {
			mockMvc.perform(get("/api/v1/rnd")
							.param(ARRAY_LENGTH, numberOfArrayItems)
							.param(NUMBER_LENGTH, numberOfCharPerItems))
					.andExpect(status().isInternalServerError())
					.andExpect(content().string(errorLessThanOne))
					.andDo(print());
		} catch (Exception e) {
			assertThat(e, is(instanceOf(NestedServletException.class)));
			assertEquals(errorLessThanOne, e.getCause().getMessage().replaceAll(errorMessageRegExp,"$5"));
		}
	}

	@Test
	public void whenNumberLengthIsLessThanMinimum_ThenReturn500() {
		final String numberOfArrayItems = "2";
		final String numberOfCharPerItems = "0";
		final String paramName = "numberLength";
		final String errorMessageRegExp = "(" + METHOD_NAME + ")(\\.)(" + paramName + ")(: )(.*)";
		final String errorLessThanOne = "The minimum value of the field 'number-length' must be greater than or equal to 1";

		try {
			mockMvc.perform(get("/api/v1/rnd")
							.param(ARRAY_LENGTH, numberOfArrayItems)
							.param(NUMBER_LENGTH, numberOfCharPerItems))
					.andExpect(status().isInternalServerError())
					.andExpect(content().string(errorLessThanOne))
					.andDo(print());
		} catch (Exception e) {
			assertThat(e, is(instanceOf(NestedServletException.class)));
			assertEquals(errorLessThanOne, e.getCause().getMessage().replaceAll(errorMessageRegExp,"$5"));
		}
	}

	@Test
	public void whenNumberLengthIsGreaterThanMaximum_ThenReturn500() {
		final String numberOfArrayItems = "2";
		final String numberOfCharPerItems = "10";
		final String paramName = "numberLength";
		final String errorMessageRegExp = "(" + METHOD_NAME + ")(\\.)(" + paramName + ")(: )(.*)";
		final String errorLessThanOne = "The maximum value of the field 'number-length' must be less than 9";

		try {
			mockMvc.perform(get("/api/v1/rnd")
							.param(ARRAY_LENGTH, numberOfArrayItems)
							.param(NUMBER_LENGTH, numberOfCharPerItems))
					.andExpect(status().isInternalServerError())
					.andExpect(content().string(errorLessThanOne))
					.andDo(print());
		} catch (Exception e) {
			assertThat(e, is(instanceOf(NestedServletException.class)));
			assertEquals(errorLessThanOne, e.getCause().getMessage().replaceAll(errorMessageRegExp,"$5"));
		}
	}

}
