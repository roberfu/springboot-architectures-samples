package cl.springmachine.hexagonal.core.service;

import org.springframework.stereotype.Service;

import cl.springmachine.hexagonal.core.domain.pokemon.Pokemon;
import cl.springmachine.hexagonal.core.usecases.CreatePokemonUseCase;
import cl.springmachine.hexagonal.ports.outbound.PokemonRepositoryPort;

@Service
public class CreatePokemonUseCaseImpl implements CreatePokemonUseCase {

	private final PokemonRepositoryPort repositoryPort;

	public CreatePokemonUseCaseImpl(PokemonRepositoryPort repositoryPort) {
		this.repositoryPort = repositoryPort;
	}

	@Override
	public Integer createPokemon(Pokemon pokemon) {
		return repositoryPort.savePokemon(pokemon);
	}
}
