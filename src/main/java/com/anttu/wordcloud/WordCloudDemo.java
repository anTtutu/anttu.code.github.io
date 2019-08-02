package com.anttu.wordcloud;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 描述
 *
 * @ClassName: WordCloudDemo
 * @Description: java词云，demo，需要依赖2个包，参考pom
 * @Author: hk
 * @Date: 2019/8/2 19:40
 */
public class WordCloudDemo
{
    public static void main (String[] args)
    {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());


        // 可以直接从文件中读取
        //final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(getInputStream("text/chinese_language.txt"));

        final List<WordFrequency> wordFrequencies = new ArrayList<>();

        String [] books = {"Spring实战","Spring源码深度解析","SpringBoot实战",
                "SpringBoot2精髓","一步一步学SpringBoot2","Spring微服务实战",
                "Head First Java","Java并发编程实战","深入理解Java 虚拟机",
                "Head First Design","effective java","J2EE development without EJB",
                "TCP/IP卷一"," 计算机网络：自顶向下","图解HTTP和图解TCP/IP",
                "计算机网络","深入理解计算机系统","现代操作系统",
                "Linux内核设计与实现","Unix网络编程","数据结构与算法",
                "算法导论","数据结构与算法（Java版）","算法图解，啊哈算法",
                "剑指offer","LeetCode"," Java编程思想",
                "Java核心技术卷一","深入理解JVM虚拟机","Java并发编程实战",
                " Java并发编程艺术","Java性能调优指南","Netty权威指南",
                "深入JavaWeb技术内幕","How Tomcat Works","Tomcat架构解析",
                "Spring实战","Spring源码深度解析","Spring MVC学习指南",
                "Maven实战","sql必知必会","深入浅出MySQL",
                "Spring cloud微服务实战","SpringBoot与Docker微服务实战","深入理解SpringBoot与微服务架构"};

        //加入分词并随机生成权重，每次生成得图片都不一样
        for (String book : books)
        {
            wordFrequencies.add(new WordFrequency(book,new Random().nextInt(books.length)));
        }


        //此处不设置会出现中文乱码
        Font font = new Font("STSong-Light", 2, 18);

        final Dimension dimension = new Dimension(900, 900);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackground(new CircleBackground(255));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 42));
        //设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));

        wordCloud.setKumoFont(new KumoFont(font));
        //背景是白色
        wordCloud.setBackgroundColor(new Color(255, 255, 255));
        //因为我这边是生成一个圆形,这边设置圆的半径
        wordCloud.setBackground(new CircleBackground(255));

        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("e://3.png");
    }
}
