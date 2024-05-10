package be.bstorm.specification.controllers;

import be.bstorm.specification.models.entities.PlayerEntity;
import be.bstorm.specification.repositories.PlayerRepository;
import be.bstorm.specification.utils.request.SearchParam;
import be.bstorm.specification.utils.specifications.SearchSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping(path = {"/players"})
    public ResponseEntity<List<PlayerEntity>> readAll(
            @RequestParam Map<String, String> params
    )  {
        List<SearchParam<PlayerEntity>> searchParams = SearchParam.create(params);
        if (searchParams.isEmpty()) {
            return ResponseEntity.ok(this.playerRepository.findAll());
        }
        return ResponseEntity.ok(this.playerRepository.findAll(
                Specification.anyOf(searchParams.stream().map(SearchSpecification::search).toList())
        ));
    }
}
