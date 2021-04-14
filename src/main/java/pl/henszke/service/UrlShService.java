package pl.henszke.service;

import org.apache.commons.validator.UrlValidator;

public class UrlShService {
    private ShortUrlGenerator generator;
    private UrlValidator urlValidator;
    private String domain;

    public UrlShService() {
        generator = new ShortUrlGeneratorImpl();
        urlValidator = new UrlValidator(new String[]{"http", "https"});
        domain = "http://smrtcd.rs/";
    }

    public UrlShService(ShortUrlGenerator generator, UrlValidator urlValidator, String domain) {
        this.generator = generator;
        this.urlValidator = urlValidator;
        this.domain = domain;
    }

    public String handleShortUrlCreation(String originalUrl) {
        if(!urlValidator.isValid(originalUrl)){
            return "Error invalid URL";
        }
        return domain + generator.create(originalUrl);
    }

    public String handleAlias(String alias, String originalUrl) {
        if(generator.isCreationWithGivenKeySuccessful(originalUrl,alias)){
            return domain + alias;
        }
        return "Error: Short URL already in use";
    }

    public String handleOriginalUrlToShort(String shortenedUrl) {
        String key = shortenedUrl.replace(domain, "");
        String originalUrl = generator.resolve(key);
        if(originalUrl.isEmpty()){
            return "Original Url doesnt exist";
        }
        return originalUrl;
    }
}
