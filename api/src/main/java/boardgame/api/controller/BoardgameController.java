package boardgame.api.controller;

import boardgame.api.model.Boardgame;
import boardgame.api.repository.BoardgameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/boardgames")
@CrossOrigin(origins = "http://localhost:4200") // allow Ember dev server
public class BoardgameController {

    private final BoardgameRepository repo;

    public BoardgameController(BoardgameRepository repo) {
        this.repo = repo;
    }

    // GET /api/boardgames  -> return { "boardgames": [ {id, title}, ... ] }
    @GetMapping
    public Map<String, List<Boardgame>> getAll() {
        List<Boardgame> list = repo.findAll();
        return Collections.singletonMap("boardgames", list);
    }

    // GET /api/boardgames/{id} -> { "boardgame": {...} }
    @GetMapping("/{id}")
    public Map<String, Boardgame> getOne(@PathVariable Long id) {
        Boardgame b = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return Collections.singletonMap("boardgame", b);
    }

    // POST /api/boardgames  payload: { "boardgame": { "title": "X" } }
    @PostMapping
    public Map<String, Boardgame> create(@RequestBody Map<String, Map<String, Object>> incoming) {
        Map<String, Object> payload = incoming.get("boardgame");
        String title = (String)payload.get("title");
        Boardgame saved = repo.save(new Boardgame(title));
        return Collections.singletonMap("boardgame", saved);
    }

    // PATCH /api/boardgames/{id} payload: { "boardgame": { "title": "new" } }
    @PatchMapping("/{id}")
    public Map<String, Boardgame> update(@PathVariable Long id, @RequestBody Map<String, Map<String, Object>> incoming) {
        Boardgame b = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        Map<String, Object> payload = incoming.get("boardgame");
        if (payload.containsKey("title")) {
            b.setTitle((String)payload.get("title"));
        }
        Boardgame saved = repo.save(b);
        return Collections.singletonMap("boardgame", saved);
    }

    // DELETE /api/boardgames/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}