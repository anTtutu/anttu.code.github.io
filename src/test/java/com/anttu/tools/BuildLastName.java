package com.anttu.tools;

import java.util.Random;

/**
 * 描述
 *
 * @ClassName：BuildLastName
 * @Description：名称
 * @author：Anttu
 * @Date：22/11/2022 16:14
 */
public class BuildLastName {
    private Random random;

    public BuildLastName() {
        super();
        random = new Random();
    }

    // 男名
    private String boyName = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    // 女名
    private String girlName = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";

    private int index;

    private String name_sex = "";

    public String insideLastName(int sex) {
        String str = "";
        int length = boyName.length();
        if (sex == 0) {
            str = girlName;
            length = girlName.length();
            name_sex = "女";
        } else if (sex == 1) {
            str = boyName;
            length = boyName.length();
            name_sex = "男";
        }

        index = getNum(0, length - 1);
        //获得一个随机的名字
        return str.substring(index, index + 1);
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
