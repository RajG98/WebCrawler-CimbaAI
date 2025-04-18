package ai.cimba.web_crawler_main.controller;

import mylib.CrawlLog;
import mylib.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebCrawlerController {
    @GetMapping("/history")
    public List<CrawlLog> getAllLogs(){
        List<CrawlLog> crawls = Main.getAllLogsAsJava();

        return crawls;
    }
}
