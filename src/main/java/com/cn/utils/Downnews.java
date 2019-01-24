package com.cn.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * 对网站新闻的爬取..
 * @DATE:2019-01-24
 * @Author:Delta
 */
public class Downnews {
    public static void main(String[] args) throws Exception {
        // 第一步：访问页面
        String url = "https://news.sina.com.cn/c/2019-01-24/doc-ihrfqzka0599621.shtml";
        Document document = Jsoup.connect(url).get();
        // 第二步：解析页面
        Elements titleElements = document.select(" .main-title");
        String title = titleElements.text();
        Elements elements = document.select(" .article-content");
        String content = elements.text();

        Elements elements_date = document.select(" .date");
        String date=elements_date.text();

        Elements elements_source = document.select(" .source");
        String source=elements_source.text();
        Elements elements_author = document.select(" .show_author");
        String author=elements_author.text();

        //图片新闻
        ImageDown imageDown = new ImageDown();
        String htmlurl=imageDown.getHtml(url);
        List<String>listimage = imageDown.getImageUrl(htmlurl);
        List<String> list = imageDown.getImageSrc(listimage);
        imageDown.Download(list);

        // 第三步：打印
        System.out.println("title:" + title);
        System.out.println("content:" + content);
        System.out.println("date:" + date);
        System.out.println("author:" + author);
        System.out.println("source:" + source);
        for (String image_str:list){
            System.out.println("image_url:"+image_str);
        }
    }
}
