package ai.cimba.web_crawler_main.controller;

import mylib.CrawlLog;
import mylib.Main;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebCrawlerController {
    @GetMapping("/logs")
    public List<CrawlLog> getAllLogs(){
        List<CrawlLog> crawls = Main.getAllLogsAsJava();
//        System.out.println(crawls);
        return crawls;
    }
    @PostMapping("/crawl")
    public ResponseEntity<String> setLog(@RequestParam(name = "url") String url) {
        try {
            String result = Main.crawl(url);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Crawling failed: " + e.getMessage());
        }
    }

}
