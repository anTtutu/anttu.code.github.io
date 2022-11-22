package com.anttu.tools;

import java.util.Random;

/**
 * 描述
 *
 * @ClassName：RandomCompanyName
 * @Description：随机公司名称测试工具，java 下 faker 不成熟
 * @author：Anttu
 * @Date：22/11/2022 15:58
 */
public class RandomChineseCompanyName {
    /**
     * 获取中文随机公司名
     *
     * @return
     */
    public static String getRandomCompanyName() {
        // 省市名
        String[] cityPro = {
                "安康市", "安庆市", "安顺市", "安阳市", "鞍山市", "巴彦淖尔市", "巴中市", "白城市", "白山市", "白银市", "百色市", "蚌埠市", "包头市", "宝鸡市", "保定市", "保山市", "北海市", "本溪市", "滨州市", "沧州市", "昌都地区", "长春市", "长沙市", "长治市", "常德市", "常州市", "巢湖市", "朝阳市", "潮州市", "郴州市", "成都市", "承德市", "池州市", "赤峰市", "崇左市", "滁州市", "达州市", "大连市", "大庆市", "大同市", "丹东市", "德阳市", "德州市", "定西市", "东莞市", "东营市", "鄂尔多斯市", "鄂州市", "防城港市", "佛山市", "福州市", "抚顺市", "抚州市", "阜新市", "阜阳市", "甘南州", "赣州市", "固原市", "广安市", "广元市", "广州市", "贵港市", "贵阳市", "桂林市", "哈尔滨市", "哈密地区", "海北藏族自治州", "海东地区", "海口市", "邯郸市", "汉中市", "杭州市", "毫州市", "合肥市", "河池市", "河源市", "菏泽市", "贺州市", "鹤壁市", "鹤岗市", "黑河市", "衡水市", "衡阳市", "呼和浩特市", "呼伦贝尔市", "湖州市", "葫芦岛市", "怀化市", "淮安市", "淮北市", "淮南市", "黄冈市", "黄山市", "黄石市", "惠州市", "鸡西市", "吉安市", "吉林市", "济南市", "济宁市", "佳木斯市", "嘉兴市", "嘉峪关市", "江门市", "焦作市", "揭阳市", "金昌市", "金华市", "锦州市", "晋城市", "晋中市", "荆门市", "荆州市", "景德镇市", "九江市", "酒泉市", "开封市", "克拉玛依市", "昆明市", "拉萨市", "来宾市", "莱芜市", "兰州市", "廊坊市", "乐山市", "丽江市", "丽水市", "连云港市", "辽阳市", "辽源市", "聊城市", "临沧市", "临汾市", "临沂市", "柳州市", "六安市", "六盘水市", "龙岩市", "陇南市", "娄底市", "泸州市", "吕梁市", "洛阳市", "漯河市", "马鞍山市", "茂名市", "眉山市", "梅州市", "绵阳市",
                "牡丹江市", "内江市", "南昌市", "南充市", "南京市", "南宁市", "南平市", "南通市", "南阳市", "宁波市", "宁德市", "攀枝花市", "盘锦市", "平顶山市", "平凉市", "萍乡市", "莆田市", "濮阳市", "普洱市", "七台河市", "齐齐哈尔市", "钦州市", "秦皇岛市", "青岛市", "清远市", "庆阳市", "曲靖市", "衢州市", "泉州市", "日照市", "三门峡市", "三明市", "三亚市", "汕头市", "汕尾市", "商洛市", "商丘市", "上饶市", "韶关市", "邵阳市", "绍兴市", "深圳市", "沈阳市", "十堰市", "石家庄市", "石嘴山市", "双鸭山市", "朔州市", "四平市", "松原市", "苏州市", "宿迁市", "宿州市", "绥化市", "随州市", "遂宁市", "台州市", "太原市", "泰安市", "泰州市", "唐山市", "天水市", "铁岭市", "通化市", "通辽市", "铜川市", "铜陵市", "铜仁市", "吐鲁番地区", "威海市", "潍坊市", "渭南市", "温州市", "乌海市", "乌兰察布市", "乌鲁木齐市", "无锡市", "吴忠市", "芜湖市", "梧州市", "武汉市", "武威市", "西安市", "西宁市", "锡林郭勒盟", "厦门市", "咸宁市", "咸阳市", "湘潭市", "襄樊市", "孝感市", "忻州市", "新乡市", "新余市", "信阳市", "兴安盟", "邢台市", "徐州市", "许昌市", "宣城市", "雅安市", "烟台市", "延安市", "盐城市", "扬州市", "阳江市", "阳泉市", "伊春市", "伊犁哈萨克自治州", "宜宾市", "宜昌市", "宜春市", "益阳市", "银川市", "鹰潭市", "营口市", "永州市", "榆林市", "玉林市", "玉溪市", "岳阳市", "云浮市", "运城市", "枣庄市", "湛江市", "张家界市", "张家口市", "张掖市", "漳州市", "昭通市", "肇庆市", "镇江市", "郑州市", "中山市", "中卫市", "舟山市", "周口市", "株洲市", "珠海市", "驻马店市", "资阳市", "淄博市", "自贡市", "遵义市", "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省"
        };
        // 公司名
        String[] name = {
                "春信", "贵丰", "东弘", "同富", "飞庆", "万康", "万鼎", "隆高", "久协", "德高", "公盈", "春谦", "皇贵", "伟荣", "旺利", "辉圣", "广安", "合亨", "吉如", "华飞", "元正", "瑞丰", "聚兴", "长福", "元优", "多乾", "巨久", "德祥", "隆安", "鑫德", "乾广", "伟复", "久多", "耀顺", "同福", "东昌", "洪亚", "耀佳", "昌益", "欣丰", "乾美", "长隆", "如福", "圣耀", "洪升", "合寿", "辉浩", "裕顺", "伟汇", "发富", "茂宏", "盈信", "宝佳", "东恒", "中久", "欣茂", "凯源", "台盈", "祥升", "满昌", "康泰", "同富", "高生", "元晶", "进长", "复优", "华成", "耀发", "贵义", "茂乾", "宝高", "优泰", "益瑞", "谦源", "长富", "润恒", "吉乾", "仁义", "益聚", "泰贵", "鑫协", "协多", "源耀", "贵昌", "禄协", "圣本", "庆兴", "鑫协", "正浩", "仁益", "高晶", "泰公", "多成", "通发", "同满", "乾升", "禄宏", "伟裕", "光贵", "正飞", "百亚", "乾福", "乾安", "伟捷", "春禄", "美厚", "富泰", "顺义", "益捷", "泰润", "凯佳", "盈捷", "厚荣", "大福", "耀协", "润美", "鑫广", "如德", "长公", "进正", "元康", "荣协", "久泰", "升顺", "鑫广", "如德", "进源", "国豪", "建辉", "睿渊", "韵文", "旭尧", "炎彬", "云舟", "俊材", "冠霖", "瑾瑜", "伟泽", "皓轩", "鑫磊", "浩宇", "文昊", "韵舟", "靖琪", "绍辉", "志强", "幽朋", "风桦", "智渊", "苑博", "菲凡", "越泽", "明杰", "博超", "长翔", "俊驰", "天佑", "鑫鹏", "泰宇", "文博", "晋鹏", "彤彤", "瑞纳", "佩凤", "营久", "洲铭", "华久", "万先", "莱仕", "本铁", "木欧", "利太", "创光", "成百", "圆长", "扬广", "恒宏", "光典", "清星", "士玛", "湖奇", "豪西", "玉日", "领生", "贸卓", "迎方", "悦艾", "驰来", "苏富", "霸清", "至达", "丝元", "巨营", "振超", "悦创", "克贵", "正迈", "全拓", "皇顺", "汉理", "圣特", "发傲", "速奇", "诺妙", "拓克", "百磊", "码用", "佩爱", "基同", "阳彩", "本创", "雷利", "富腾", "辰生", "耀顺", "财正", "来览", "领鑫", "子妙", "博川", "天扬", "事纳",
                "洲赛", "环霸", "典利", "缘韦", "高理", "运斯", "新超", "胜克", "成创", "辰洋", "森精", "长世", "特西", "顺越", "诗具", "凌京", "大威", "中浩", "时方", "达集", "扬鑫", "耀讯", "仕嘉", "赛莱", "志宏", "坚曼", "特福", "冠奇", "迎跃", "威振", "士江", "具远", "世跃", "驰浩", "德金", "太赛", "运亿", "能德", "贸生", "诚界", "志裕", "曼惠", "智银", "悦圣", "正邦", "盛开", "欣铁", "宜安", "识川", "信明", "海卓", "时思", "江晖", "迎金", "拓明", "太安", "通飞", "元名", "豪欣", "微频", "良邦", "振速", "创辰", "尚智", "阳相", "金集", "丝川", "白事", "卓森", "尔诚", "发久", "英坚", "茂泰", "微银", "航坚", "来巨", "志日", "卓启", "啸理", "川欧", "子辉", "纳全", "腾庆", "语博", "辰东", "腾聚", "用苏", "圣讯", "玉大", "展来", "坚微", "贵览", "森航", "春实", "悦旭", "湖原", "久具", "洁丝", "冠语", "方西", "方凤", "丰火", "飞生", "荣银", "佩良", "航微", "盈集", "皇健", "凡茂", "恒集", "展丝", "圆圆", "立爱", "展顺", "纳子", "思胜", "京川", "鸿特", "联顿", "典彩", "雅嘉", "贝汇", "信顿", "涛月", "洁湖", "成丰", "识环", "信博", "达迪", "泰铭", "精来", "泰亿", "茂欧", "尼电", "好丝", "时梦", "航相", "嘉复", "汉优", "双莱"
        };
        // 行业名
        String[] industry = {
                "技术开发", "技术转让", "技术服务", "计算机维修及维护服务", "弱电工程设计安装", "综合网络布线", "系统集成", "网页设计与安装", "电脑平面设计", "美术设计制作", "电脑图文设计", "制作", "绘图", "网络技术开发", "技术转让", "技术咨询", "技术服务", "电子科技", "技术转让及咨询服务", "安防技术", "企业管理咨询", "企业策划", "商务咨询", "商务服务", "酒店管理咨询", "翻译服务", "航空服务", "票务", "房地产信息（投资）咨询", "文化咨询", "教育信息咨询", "二手车鉴定评估", "金融", "保险", "证券", "投资", "旅游", "餐饮", "娱乐", "休闲", "购物", "造纸", "纸品", "印刷", "包装", "广告", "会展", "商务办公", "咨询业", "IT", "通信电子", "互联网", "房地产", "建筑业", "交通", "运输", "物流", "仓储", "政府", "非盈利机构", "生产", "加工", "制造", "医疗", "护理", "美容", "保健", "卫生", "媒体", "出版", "影视", "文化传播", "电气", "电力", "水力", "航空", "航天研究与制造", "家居", "室内设计", "装饰装潢", "通信", "电信", "网路设备", "电子技术", "半导体", "集成电路", "基金", "证券", "期货", "投资", "检验", "检测", "认证", "礼品", "工艺美术", "奢饰品", "媒体", "出版", "影视", "文化传播"
        };
        String suffix = "有限公司";

        Random random = new Random();
        int cityProNum = random.nextInt(cityPro.length);
        int nameNum = random.nextInt(name.length);
        int industryNum = random.nextInt(industry.length);

        return cityPro[cityProNum] + name[nameNum] + industry[industryNum] + suffix;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.println(getRandomCompanyName());
        }
    }
}
