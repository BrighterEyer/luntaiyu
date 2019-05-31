package com.zhangzhao.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class T {
    static Random random = new Random();
    static {
        random.setSeed(System.currentTimeMillis());
    }
    public static double [] getMoney(double money, int num){
        Random r = new Random();
        DecimalFormat format = new DecimalFormat(".##");

        double middle = Double.parseDouble(format.format(money/num));
        double [] dou = new double[num];
        double redMoney = 0;
        double nextMoney = money;
        double sum = 0;
        int index = 0;
        for(int i=num;i>0;i--){
            if(i == 1){
                dou[index] = nextMoney;
            }else{
                while(true){
                    String str = format.format(r.nextDouble()*nextMoney);
                    redMoney = Double.parseDouble(str);
                    if(redMoney>0 && redMoney < middle){
                        break;
                    }
                }
                nextMoney = Double.parseDouble(format.format(nextMoney - redMoney));
                sum = sum + redMoney;
                dou[index] = redMoney;
                middle = Double.parseDouble(format.format(nextMoney/(i-1)));
                index++;
            }
        }
        return dou;
    }

    public static void main(String[] args) {
//        List<String> lis2t = new ArrayList<>(Arrays.asList("hello welcome", "world hello", "hello world","hello world welcome"));
//        lis2t.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList());
//        long max = 5;
//        long min = 1;
//
//        long[] result = T.generate(20, 6, max, min);
//        long total = 0;
//        for (int i = 0; i < result.length; i++) {
//            System.out.println("result[" + i + "]:" + result[i]);
//            total += result[i];
//        }
//        //检查生成的红包的总额是否正确
//        System.out.println("total:" + total);

//		//统计每个钱数的红包数量，检查是否接近正态分布
//		int count[] = new int[(int) max + 1];
//		for (int i = 0; i < result.length; i++) {
//			count[(int) result[i]] += 1;
//		}
//
//		for (int i = 0; i < count.length; i++) {
//			System.out.println("" + i + "  " + count[i]);
//		}
//        double[] doubles = getMoney(20, 6);
//        for (double d:doubles){
//            System.out.println(" ---------- " + d);
//        }
//        DecimalFormat format = new DecimalFormat("#.##");
//        Random r = new Random();
//        double redMoney = 0;
//        double nextMoney = 5;
//        while(true){
//            String str = format.format(r.nextDouble()*5);
//            System.out.println(" -----str----- " + str);
//            redMoney = Double.valueOf(str);
//            if(redMoney>0 && redMoney < 3){
//                break;
//            }
//        }
//        nextMoney = Double.parseDouble(format.format(nextMoney - redMoney));
//        System.out.println(" ---------- " + redMoney);
//        System.out.println(" ---------- " + nextMoney);
//        for (int i=0;i<10;i++){
//            System.out.println(" ---------- " + format.format(r.nextDouble()*5));
//        }
//        System.out.println(" ---------- " + redMoney(5,3));
//        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        // 参数：1、任务体 2、首次执行的延时时间
//        //      3、任务执行间隔 4、间隔时间单位
//        service.schedule(()->System.out.println("task ScheduledExecutorService "+new Date()), 3, TimeUnit.SECONDS);
        System.out.println(" ---------- " + (double)18L/20);

    }

    public static double redMoney(int maxMoney,int redMoney){
        DecimalFormat format = new DecimalFormat("#.##");
        Random r = new Random();
        double money = 0;
        while(true){
            String str = format.format(r.nextDouble()*maxMoney);
            money = Double.valueOf(str);
            if(money>0 && money < redMoney){
                break;
            }
        }
        return money;
    }

    /**
     * 生产min和max之间的随机数，但是概率不是平均的，从min到max方向概率逐渐加大。
     * 先平方，然后产生一个平方值范围内的随机数，再开方，这样就产生了一种“膨胀”再“收缩”的效果。
     *
     * @param min
     * @param max
     * @return
     */
    static long xRandom(long min, long max) {
        return sqrt(nextLong(sqr(max - min)));
    }

    /**
     *
     * @param total
     *            红包总额150000
     * @param count
     *            红包个数1000
     * @param max
     *            每个小红包的最大额5
     * @param min
     *            每个小红包的最小额1
     * @return 存放生成的每个小红包的值的数组
     */
    public static long[] generate(long total, int count, long max, long min) { //20 6 5 1
        System.out.println(" ---------- " + total);
        long[] result = new long[count];//6

        long average = total / count;//3

        long a = average - min;//2
        long b = max - min;//4

        //
        //这样的随机数的概率实际改变了，产生大数的可能性要比产生小数的概率要小。
        //这样就实现了大部分红包的值在平均数附近。大红包和小红包比较少。
        long range1 = sqr(average - min);//2
        long range2 = sqr(max - average);//3

        for (int i = 0; i < result.length; i++) {
            //因为小红包的数量通常是要比大红包的数量要多的，因为这里的概率要调换过来。
            //当随机数>平均值，则产生小红包
            //当随机数<平均值，则产生大红包
            if (nextLong(min, max) > average) {//5-1+1+1=6>3
                // 在平均线上减钱
//				long temp = min + sqrt(nextLong(range1));
                long temp = min + xRandom(min, average);
                result[i] = temp;
                total -= temp;
            } else {
                // 在平均线上加钱
//				long temp = max - sqrt(nextLong(range2));
                long temp = max - xRandom(average, max);
                result[i] = temp;
                total -= temp;
            }
        }
        // 如果还有余钱，则尝试加到小红包里，如果加不进去，则尝试下一个。
        while (total > 0) {
            for (int i = 0; i < result.length; i++) {
                if (total > 0 && result[i] < max) {
                    result[i]++;
                    total--;
                }
            }
        }
        // 如果钱是负数了，还得从已生成的小红包中抽取回来
        while (total < 0) {
            for (int i = 0; i < result.length; i++) {
                if (total < 0 && result[i] > min) {
                    result[i]--;
                    total++;
                }
            }
        }
        return result;
    }

    static long sqrt(long n) {
        // 改进为查表？
        return (long) Math.sqrt(n);
    }

    static long sqr(long n) {
        // 查表快，还是直接算快？
        return n * n;
    }

    static long nextLong(long n) {
        return random.nextInt((int) n);
    }

    static long nextLong(long min, long max) {
        return random.nextInt((int) (max - min + 1)) + min;
    }
}
