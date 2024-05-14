package br.dev.andregurgel.linkshortener.api.shortener;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class ShortenerResource {

    private final ShortenerService shortenerService;

    public ShortenerResource(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @GetMapping("{shortenerCode}")
    public void redirect(@PathVariable String shortenerCode, HttpServletResponse response) throws IOException {
        var urlToRedirect = shortenerService.prepareUrlToRedirect(shortenerCode);
        response.sendRedirect(urlToRedirect);
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<ShortenerResponseRecord> insert(@RequestBody ShortenerRequestRecord shortenerRequestRecord) {
        return ResponseEntity.ok(shortenerService.insert(shortenerRequestRecord.url()));
    }
}
