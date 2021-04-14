package pl.henszke.service;

public interface ShortUrlGenerator {
    String create(String longUrl);
    boolean isCreationWithGivenKeySuccessful(String longUrl, String shortUrl);
    String resolve(String shortUrl);
}
