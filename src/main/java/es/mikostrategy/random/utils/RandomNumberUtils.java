package es.mikostrategy.random.utils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

@FunctionalInterface
public interface RandomNumberUtils<T, V, R> extends BiFunction<Integer, Integer, Integer[]> {

	public static Function<? super Double, ? extends String> formatNumber = d -> {
		return d.toString().replaceFirst("(^[0-9]{4})([.])([0-9].*$)", "$1");
	};

	public static <T, V, R> RandomNumberUtils<T, V, R> generateRndNumbers() {
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
				//final int boundLength = String.valueOf(bound).length();

				if (result < 1_000_000_000 && tempResult.length() < 9) {
					result = Integer.parseInt(
							StringUtils.rightPad(tempResult, 9, String.valueOf(new Random().nextInt(9))));
				} else {
					/*if (tempResult.length() < numberLength) {
						result = BigDecimal.valueOf(
								StringUtils.rightPad(tempResult, numberLength, String.valueOf(new Random().nextInt(9))));

						return BigDecimal.ONE;
					}*/
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