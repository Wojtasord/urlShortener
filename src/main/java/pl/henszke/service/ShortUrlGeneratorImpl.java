package pl.henszke.service;

import java.util.HashMap;
import java.util.UUID;

public class ShortUrlGeneratorImpl implements ShortUrlGenerator {

    private HashMap<String, String> longToShortUrlMap;
    private int urlLenght;

    public ShortUrlGeneratorImpl() {
        longToShortUrlMap = new HashMap<String, String>();
        urlLenght = 7;
    }

    public ShortUrlGeneratorImpl(HashMap<String, String> longToShortUrlMap, int urlLenght) {
        this.longToShortUrlMap = longToShortUrlMap;
        this.urlLenght = urlLenght;
    }

    @Override
    public String create(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)){
            return longToShortUrlMap.get(longUrl);
        }
        String shortUrl;
        do{
            String tmpUuidString = UUID.randomUUID().toString();
            int biggestPossibleIndex = tmpUuidString.length() - 1 - urlLenght;
            int uuidBottomIndex = (int) (Math.random() * biggestPossibleIndex);
            shortUrl = tmpUuidString.substring(uuidBottomIndex,uuidBottomIndex+urlLenght);
        }while(longToShortUrlMap.containsValue(shortUrl));
        longToShortUrlMap.put(longUrl,shortUrl);
        return shortUrl;
    }

    public boolean isCreationWithGivenKeySuccessful(String longUrl, String shortUrl) {
        if(longToShortUrlMap.containsValue(shortUrl)){
            return false;
        }
        longToShortUrlMap.put(longUrl,shortUrl);
        return true;
    }

    @Override
    public String resolve(String shortUrl) {
        String longUrl = longToShortUrlMap.get(shortUrl);
        if(longUrl == null){
            return new String("");
        }
        return longUrl;
    }
}
