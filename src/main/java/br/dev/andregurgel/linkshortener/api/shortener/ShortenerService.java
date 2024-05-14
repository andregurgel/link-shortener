package br.dev.andregurgel.linkshortener.api.shortener;

import br.dev.andregurgel.linkshortener.api.config.GlobalProperties;
import br.dev.andregurgel.linkshortener.api.config.exceptions.InvalidOrNotFoundShortenerCodeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Repository
@Log4j2
public class ShortenerService {

    private final ShortenerRepository shortenerRepository;

    public ShortenerService(ShortenerRepository shortenerRepository) {
        this.shortenerRepository = shortenerRepository;
    }

    @Scheduled(cron = "0 59 23 * * ?")
    public void clearNotUsedShorteners() {
        log.info("Executando exclusão de links encurtados que não foram utilizados no último dia.");
        var shortenerList = findAll();
        var lastDay = LocalDateTime.now().minusDays(1);

        var shortenerListToDelete = shortenerList.stream()
                .filter(objeto -> objeto.getUpdatedAt().isBefore(lastDay))
                .toList();

        if (!shortenerListToDelete.isEmpty()) {
            shortenerListToDelete.forEach(shortener -> delete(shortener.getId()));
            log.info(String.format("Exclusão realizada com sucesso, no total foram excluidos %d links.", shortenerListToDelete.size()));
        } else {
            log.info("Nenhum item para ser excluído hoje.");
        }
    }

    public List<Shortener> findAll() {
        return shortenerRepository.findAll();
    }

    public Shortener findByShortenedCode(String shortenedCode) {
        return shortenerRepository.findByShortenedCode(shortenedCode)
                .orElseThrow(EntityNotFoundException::new);
    }

    public ShortenerResponseRecord insert(String urlToShort) {
        var shortener = new Shortener();
        shortener.setUrl(urlToShort);
        shortener.setShortenedCode(generateRandomString());
        shortener.setUses(0);
        shortener.setUpdatedAt(LocalDateTime.now());

        var savedShortener = shortenerRepository.save(shortener);
        return new ShortenerResponseRecord(GlobalProperties.backendUrl + "/" + savedShortener.getShortenedCode());
    }

    public void delete(Long id) {
        shortenerRepository.deleteById(id);
    }

    public String prepareUrlToRedirect(String shortenerCode) {
        try {
            var shortener = findByShortenedCode(shortenerCode);
            shortener.setUses(shortener.getUses() + 1);
            shortener.setUpdatedAt(LocalDateTime.now());
            shortenerRepository.save(shortener);

            return shortener.getUrl();
        } catch (EntityNotFoundException e) {
            throw new InvalidOrNotFoundShortenerCodeException();
        }
    }

    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
