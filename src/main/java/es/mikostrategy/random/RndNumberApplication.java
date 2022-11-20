package es.mikostrategy.random;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.mikostrategy.random.utils.RandomNumberUtils;

@RequestMapping("/api/v1")
@SpringBootApplication
public class RndNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(RndNumberApplication.class, args);
	}

	@GetMapping("/rnd")
	public ResponseEntity<?> getRndNumber(@RequestParam("array_length") //@Min(1)
										  Integer arrayLength,
										  @RequestParam(defaultValue = "6", name = "number_length", required = false)
										  Integer numberLength) {
		Objects.requireNonNull(arrayLength);
		// Fixme: Validates negative numbers

		return new ResponseEntity<>(RandomNumberUtils
				.generateRndNumbers()
				.apply(arrayLength, numberLength), HttpStatus.OK);
	}

}
