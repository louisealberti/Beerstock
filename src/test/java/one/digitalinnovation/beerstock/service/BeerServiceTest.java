package one.digitalinnovation.beerstock.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {
	
	private static final long INVALID_BERR_ID = 1L;
	
	@Mock
	private BeerRepository beerRepository;
	
	private BeerMapper beerMapper = BeerMapper.INSTANCE;
	
	@InjectMocks
	private BeerService beerService;
	
	@Test
	void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
		
		//given
		BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();;
		Beer expectedSavedBeer = beerMapper.toModel(expectedBeerDTO);
		
		// when
		when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());
		when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);
		
		//then
		BeerDTO createdBeerDTO = beerService.createBeer(expectedBeerDTO);
		
		assertThat(createdBeerDTO.getId(), is(equalTo(expectedBeerDTO.getId())));
		assertThat(createdBeerDTO.getName(), is(equalTo(expectedBeerDTO.getName())));
		assertThat(createdBeerDTO.getQuantity(), is(equalTo(expectedBeerDTO.getQuantity())));
		assertThat(createdBeerDTO.getQuantity(), is(greaterThan(2)));
	}
	
	@Test
	void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown () {
		
		BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();;
		Beer duplicatedBeer = beerMapper.toModel(expectedBeerDTO);
		
		// when
		when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));
		
		// then
		assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));

	}

}
