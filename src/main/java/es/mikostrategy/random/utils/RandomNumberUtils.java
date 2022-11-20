package es.mikostrategy.random.utils;


import java.math.BigDecimal;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@FunctionalInterface
public interface RandomNumberUtils extends BiFunction<Integer, Integer, Integer[]> {

	public static Function<? super Double, ? extends String> formatNumber = d -> {
		return d.toString().replaceFirst("(^[0-9]{4})([.])([0-9].*$)", "$1");
	};

	public static RandomNumberUtils generateRndNumbers() {
		return (arrayLength, numberLength) -> {
			Function<Integer, Integer> rnd = count -> {
				Integer bound = null;

				if (numberLength == null) {
					bound = 999999;
				} else {
					bound = BigDecimal.valueOf(Math.pow(10, numberLength)).intValue() - 1;
				}

				int result = new Random().nextInt(bound);
				final String tempResult = String.valueOf(result);
				final int boundLength = String.valueOf(bound).length();

				if (tempResult.length() < boundLength) {
					result = Integer.parseInt(StringUtils.rightPad(tempResult, boundLength, String.valueOf(new Random().nextInt(9))));
				}

				return result;
			};

			return Stream.iterate(0, n -> n + 1)
					.limit(arrayLength)
					.map(rnd)
					.distinct()
					.toArray(Integer[]::new);
		};
	}

}