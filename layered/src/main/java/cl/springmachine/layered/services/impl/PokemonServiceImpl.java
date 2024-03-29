package cl.springmachine.layered.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.springmachine.layered.dtos.PokemonDto;
import cl.springmachine.layered.entities.PokemonEntity;
import cl.springmachine.layered.repositories.PokemonRepository;
import cl.springmachine.layered.services.PokeApiService;
import cl.springmachine.layered.services.PokemonService;

@Service
public class PokemonServiceImpl implements PokemonService {

	private final PokemonRepository pokemonRepository;

	private final PokeApiService pokeApiService;

	public PokemonServiceImpl(PokemonRepository pokemonRepository, PokeApiService pokeApiService) {
		this.pokemonRepository = pokemonRepository;
		this.pokeApiService = pokeApiService;
	}

	@Override
	@Transactional
	public Integer createPokemon(PokemonDto request) {
		PokemonDto pokemonDto = pokeApiService.getPokemonInfo(request.getName());
		return pokemonRepository.save(new PokemonEntity(null, pokemonDto.getName(), pokemonDto.getPokedexNumber()))
				.getId();
	}

	@Override
	public PokemonDto readPokemon(Integer pokemonId) {
		Optional<PokemonEntity> optional = pokemonRepository.findById(pokemonId);
		return optional.map(pokemonEntity -> PokemonDto.builder().id(pokemonEntity.getId())
				.name(pokemonEntity.getName()).pokedexNumber(pokemonEntity.getPokedexNumber()).build()).orElse(null);
	}

	@Override
	@Transactional
	public void deletePokemon(Integer id) {
		pokemonRepository.deleteById(id);
	}
}
