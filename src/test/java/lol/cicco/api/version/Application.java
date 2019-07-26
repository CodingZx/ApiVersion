package lol.cicco.api.version;

import lol.cicco.api.version.annotation.ApiVersion;
import lol.cicco.api.version.config.ApiVersionProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Import(ApiVersionProperties.class)
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/version")
    public String versionDefault() {
        return "version-default";
    }

    @GetMapping("/version")
    @ApiVersion(version = "1.0.1")
    public String version1() {
        return "version-1.0.1";
    }

    @GetMapping("/version")
    @ApiVersion(version = "2.0.1")
    public String version2() {
        return "version-2.0.1";
    }

    @GetMapping("/version")
    @ApiVersion(version = "5.0.1.6")
    public String version3() {
        return "version-5.0.1.6";
    }
}
