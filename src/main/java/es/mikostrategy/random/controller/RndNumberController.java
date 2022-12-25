package es.mikostrategy.random.controller;

import es.mikostrategy.random.utils.RandomNumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/api/v1")
public class RndNumberController {

    @GetMapping("/rnd")
    public ResponseEntity<?> getRndNumber(@Min(value = 1, message = "The minimum value of the field 'array-length' must be greater than or equal to 1")
                                          @Max(value = 20, message = "The maximum value of the field 'number-length' must be less than 20")
                                          @RequestParam("array-length")
                                          Integer arrayLength,
                                          @Min(value = 1, message = "The minimum value of the field 'number-length' must be greater than or equal to 1")
                                          @Max(value = 9, message = "The maximum value of the field 'number-length' must be less than 9")
                                          @RequestParam(defaultValue = "6", name = "number-length", required = false)
                                          Integer numberLength) {
        Objects.requireNonNull(arrayLength);

        return new ResponseEntity<>(RandomNumberUtils
                .generateRndNumbers()
                .apply(arrayLength, numberLength), HttpStatus.OK);
    }

}
