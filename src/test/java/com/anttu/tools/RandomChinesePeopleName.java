package com.anttu.tools;

import java.util.ArrayList;

/**
 * 描述
 *
 * @ClassName：RandomChinesePeopleName
 * @Description：随机中文人名
 * @author：Anttu
 * @Date：22/11/2022 16:06
 */
public class RandomChinesePeopleName {

    /**
     * 这里是生成一个人名
     */
    public static String getRandomHumanName(int sex) {
        String firstName = new BuildFirstName().insideFirstName();
        String lastName = new BuildLastName().insideLastName(sex);
        return firstName + lastName;
    }

    /**
     * 随机生成多个人名，通过list实现
     */
    public static ArrayList getMultiRandomHumanNames(int index) {

        ArrayList lineName = new ArrayList();

        BuildFirstName buildFirstName = new BuildFirstName();

        BuildLastName buildLastName = new BuildLastName();

        for (int i = 0; i < index; i++) {
            lineName.add(buildFirstName.insideFirstName() + buildLastName.insideLastName(1));
        }
        return lineName;
    }

    public static void main(String[] args) {
        ArrayList lineName = RandomChinesePeopleName.getMultiRandomHumanNames(200);
        for (int i = 0; i < lineName.size(); i++) {
            System.out.print(lineName.get(i) + "\t");

            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }
}
