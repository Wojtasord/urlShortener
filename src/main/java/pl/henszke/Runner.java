package pl.henszke;

import org.apache.commons.validator.UrlValidator;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import pl.henszke.service.UrlShService;

import java.util.concurrent.Callable;


@Command(name = "urlsh", description = "Create a random and unique short URL for a given input URL", mixinStandardHelpOptions = true, version = "urlsh 1.0")
public class Runner implements Callable<String>
{
    @Option(names = "-i", description = "URL to be shortened")
    private String originalUrl;

    @Option(names="-o",description = "Shorten a given URL to a URL with a specified alias")
    private String alias;

    @Option(names = "-s", description = "Shorten a given URL to a URL with a specified alias")
    private String shortenedUrl;


    private UrlShService urlShService = new UrlShService();


    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new Runner()).execute(args);
        System.exit(exitCode);
    }

    public String call() throws Exception {

        if (originalUrl != null && alias == null) {
            System.out.println(urlShService.handleShortUrlCreation(originalUrl));
        }
        if (alias != null && originalUrl != null) {
            System.out.println(urlShService.handleAlias(alias, originalUrl));
        }
        if(shortenedUrl!=null){
            System.out.println(urlShService.handleOriginalUrlToShort(shortenedUrl));
        }

        return "success";
    }
}
