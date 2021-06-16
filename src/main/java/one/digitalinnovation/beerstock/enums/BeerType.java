package one.digitalinnovation.beerstock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeerType {
	
	LAGER("Lager"),
	MALZBIER("Malzbier"),
	WITBIER("Witbier"),
	WEISS("Weiss"),
	ALE("Ale"),
	IPA("IPA"),
	ATOUT("Stout");
	
	private final String description;

}
