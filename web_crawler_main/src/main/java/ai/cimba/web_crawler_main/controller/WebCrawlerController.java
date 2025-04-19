package ai.cimba.web_crawler_main.controller;

import mylib.CrawlLog;
import mylib.Main;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebCrawlerController {
    @GetMapping("/logs")
    public List<CrawlLog> getAllLogs(){
        List<CrawlLog> crawls = Main.getAllLogsAsJava();
//        System.out.println(crawls);
        return crawls;
    }
    @PostMapping("/crawl")
    public String setLog(@RequestParam(name = "url") String url){
//        System.out.println(url);
        return Main.crawl(url);
    }
}
