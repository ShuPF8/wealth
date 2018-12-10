package com.spf.wealth.utils;

import com.alibaba.fastjson.JSONArray;
import com.spf.wealth.model.DwdModel;
import com.spf.wealth.model.XuanHaoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.*;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-11-22 15:10
 */
public class LotteryHandleUtil {

    private static Logger logger = LogManager.getLogger(LotteryHandleUtil.class);

    public static List<String> doNum(XuanHaoVo vo) {
        logger.info("------------------------------------ 开始做号 ------------------------------------------");
        List<String> list = new ArrayList<>();
        String dm = vo.getDm();
        // 胆
        if (StringUtils.isNotBlank(dm)) {
            String[] dms = dm.split("[,\\n\\s]");
            if (vo.getTwom() == 2) {
                for (int i = 0; i < dms.length; i++) {
                    String valueI = dms[i];
                    for (int k = 0; k < dms.length; k++) {
                        String valueK = dms[k];
                        if (Integer.valueOf(valueI) == Integer.valueOf(valueK)) {
                            continue;
                        }
                        list.add(valueI + valueK);
                    }
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    if (!dm.contains(i+"")) {
                        for (int k = 0; k < dms.length; k++) {
                            list.add(i + "" + dms[k]);
                        }
                    } else {
                        for (int j = 0; j < 10; j++) {
                            list.add(i + "" + j);
                        }
                    }
                }
            }
            logger.info("做号胆码： {}", dm);
        }

        if (list.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    list.add(i + "" + j);
                }
            }
        }

        // 和
        List<String> result = new ArrayList<>();
        String he = vo.getHe();
        if (StringUtils.isNotBlank(he)) {
            List<String> hes = Arrays.asList(he.split("[,\\n\\s]"));
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                Integer one = Integer.valueOf(str.substring(0,1));
                Integer two = Integer.valueOf(str.substring(1,2));
                String he2 = one + two + "";
                if (!hes.contains(he2)) {
                    result.add(str);
                }
            }
            list.clear();
            list.addAll(result);
            result.clear();

            logger.info("做号过滤和值： {}", he);
        }

        //夸
        String kua = vo.getKua();
        if (StringUtils.isNotBlank(kua)) {
            List<String> kuas = Arrays.asList(kua.split("[,\\n\\s]"));
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                Integer one = Integer.valueOf(str.substring(0,1));
                Integer two = Integer.valueOf(str.substring(1,2));
                String he2 = Math.abs(two - one) + "";
                if (!kuas.contains(he2)) {
                    result.add(str);
                }
            }
            list.clear();
            list.addAll(result);
            result.clear();
            logger.info("做号过滤跨度： {}", kua);
        }

        // 和尾
        String hw = vo.getHw();
        if (StringUtils.isNotBlank(hw)) {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                Integer one = Integer.valueOf(str.substring(0,1));
                Integer two = Integer.valueOf(str.substring(1,2));
                String he2 = one + two + "";
                he2 = he2.length() == 1 ? he2 : he2.substring(1,2);
                if (!hw.contains(he2)) {
                    result.add(str);
                }
            }
            list.clear();
            list.addAll(result);
            result.clear();
            logger.info("做号过滤和尾： {}", hw);
        }

        //十位定杀
        String sd = vo.getSd();
        if (StringUtils.isNotBlank(sd)) {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                String one = str.substring(0,1);
                if (!sd.contains(one)) {
                    result.add(str);
                }
            }
            list.clear();
            list.addAll(result);
            result.clear();
            logger.info("做号过滤{}位： {}", vo.getQhLocation() == 1 ? "万" : "十", sd);
        }
        //个位定杀
        String gd = vo.getGd();
        if (StringUtils.isNotBlank(gd)) {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                String two = str.substring(1,2);
                if (!gd.contains(two)) {
                    result.add(str);
                }
            }
            list.clear();
            list.addAll(result);
            result.clear();
            logger.info("做号过滤{}位： {}", vo.getQhLocation() == 1 ?  "千" : "个", gd);
        }

        logger.info("size:{}, num: {}",list.size(),  list);
        return list;
    }

    /**
     * 获取单个位置胆码
     * @param datas
     * @param dwdModel
     * @param next 1 本期热号 2下期热号
     */
    public static String screenDm(JSONArray datas,DwdModel dwdModel, int next) {
        int size =datas.size() - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = size; i >= (30 - dwdModel.getRmCount()); i-- ) { //从20期中选热码
            String kjNum = datas.getJSONArray(i).getString(1);
            int dwNum =Integer.valueOf(kjNum.substring(dwdModel.getLocation() - 1, dwdModel.getLocation()));
            switch (dwNum) {
                case 0:
                    map.put(0, map.get(0) == null ? 1 : map.get(0) + 1);
                    break;
                case 1:
                    map.put(1, map.get(1) == null ? 1 : map.get(1) + 1);
                    break;
                case 2:
                    map.put(2, map.get(2) == null ? 1 : map.get(2) + 1);
                    break;
                case 3:
                    map.put(3, map.get(3) == null ? 1 : map.get(3) + 1);
                    break;
                case 4:
                    map.put(4, map.get(4) == null ? 1 : map.get(4) + 1);
                    break;
                case 5:
                    map.put(5, map.get(5) == null ? 1 : map.get(5) + 1);
                    break;
                case 6:
                    map.put(6, map.get(6) == null ? 1 : map.get(6) + 1);
                    break;
                case 7:
                    map.put(7, map.get(7) == null ? 1 : map.get(7) + 1);
                    break;
                case 8:
                    map.put(8, map.get(8) == null ? 1 : map.get(8) + 1);
                    break;
                case 9:
                    map.put(9, map.get(9) == null ? 1 : map.get(9) + 1);
                    break;
            }
        }
        // 排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            //根据value降序排序
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });

        List<Integer> list1 = new ArrayList<>();
        int length = dwdModel.getLength();
        if (list.size() > length) {
            for (int i = 0; i < length; i++) {
                Map.Entry<Integer, Integer> entry = list.get(i);
                list1.add(entry.getKey());
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : list) {
                list1.add(entry.getKey());
            }
        }

        String tzNum = "";
        Collections.sort(list1);
        for (Integer integer : list1) {
            tzNum += integer;
        }
        if (next == 1) {
            dwdModel.setTzNum(tzNum);
        } else if (next == 2) {
            dwdModel.setNextTzNum(tzNum);
        }

        logger.info("位置: {}, 胆码: {}", dwdModel.getLocation(), tzNum);
        return tzNum;
    }

    /**
     *  综合筛选胆码（前二 后二）
     * @param datas
     * @param dwdModel
     * @return
     */
    public static String zhScreenDm(JSONArray datas, DwdModel dwdModel) {
        String oneStr = "", twoStr = "";
        if (dwdModel.getQhLocation() == 1) {
            dwdModel.setLocation(1);
            oneStr = screenDm(datas, dwdModel, 1);
            dwdModel.setLocation(2);
            twoStr = screenDm(datas, dwdModel, 1);
        } else {
            dwdModel.setLocation(4);
            oneStr = screenDm(datas, dwdModel, 1);
            dwdModel.setLocation(5);
            twoStr = screenDm(datas, dwdModel, 1);
        }

        List<String> list = new ArrayList<>();

        // 先添加相同的胆码
        for (int i = 0; i < oneStr.length(); i++) {
            String strI = oneStr.substring(i, i + 1);
            for (int j = 0; j < twoStr.length(); j++) {
                String strJ = twoStr.substring(j, j + 1);
                if (strI.equals(strJ)) {
                    oneStr = oneStr.replace(strI, " ");
                    twoStr = twoStr.replace(strI, " ");
                    list.add(strI);
                    break;
                }
            }
        }

        oneStr = oneStr.replaceAll(" ", "");
        twoStr = twoStr.replaceAll(" ", "");
        for (int i = 0; i < twoStr.length(); i++) {
            oneStr += twoStr.substring(i, i + 1);
        }

        int cLength = dwdModel.getLength() - list.size();
        Random random = new Random();
        for (int i = 0; i < cLength; i++) {
            String str = "";
            do {
                int index = random.nextInt(oneStr.length());
                str = oneStr.substring(index, index + 1);
            } while (list.contains(str));
            list.add(str);
        }

        Collections.sort(list);

        String resultStr = "";
        for (int i = 0; i < list.size(); i++) {
            resultStr += list.get(i) + ",";
        }
        resultStr = resultStr.substring(0, resultStr.length() - 1);

        logger.info("位置：{}， 综合胆码：{}",dwdModel.getQhLocation(),  resultStr);

        return resultStr;
    }


    /**
     * 筛选和值(6 - 14)
     * @param datas
     * @param dwdModel
     * @return
     */
    public static String screenHe(JSONArray datas,DwdModel dwdModel) {
        String resultStr = "";
        int size =datas.size() - 1;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{
            put(6,0);put(7,0);put(8,0);put(9,0);put(10,0);put(11,0);
            put(12,0);put(13,0);put(14,0);
        }};
        for (int i = size; i >= (30 - dwdModel.getRmCount()); i-- ) {
            String kjNum = datas.getJSONArray(i).getString(1);
            int he = 0;
            if (dwdModel.getQhLocation() == 5) {
                int one =Integer.valueOf(kjNum.substring(3, 4));
                int two =Integer.valueOf(kjNum.substring(4, 5));
                he = one + two;
            } else {
                int one =Integer.valueOf(kjNum.substring(0, 1));
                int two =Integer.valueOf(kjNum.substring(1, 2));
                he = one + two;
            }
            switch (he) {
                case 6:
                    map.put(6, map.get(6) + 1);
                    break;
                case 7:
                    map.put(7, map.get(7) + 1);
                    break;
                case 8:
                    map.put(8, map.get(8) + 1);
                    break;
                case 9:
                    map.put(9, map.get(9) + 1);
                    break;
                case 10:
                    map.put(10, map.get(10) + 1);
                    break;
                case 11:
                    map.put(11, map.get(11) + 1);
                    break;
                case 12:
                    map.put(12, map.get(12) + 1);
                    break;
                case 13:
                    map.put(13, map.get(13) + 1);
                    break;
                case 14:
                    map.put(14, map.get(14) + 1);
                    break;
            }
        }

        // 排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            //根据value升序排序
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            if (entry.getValue() == 0) {
                count++;
            }
        }

        if ((9-count) > dwdModel.getHeNum()) {
            for (int i = 0; i < count; i++) {
                list.remove(0);
            }
        } else {
            for (int i = 0; i < count - 1; i++) {
                list.remove(0);
            }
        }

        for (int i = 0; i < dwdModel.getHeNum(); i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            resultStr += entry.getKey() + ",";
        }
        resultStr = resultStr.substring(0, resultStr.length() - 1);

        logger.info("位置：{}， 杀和值：{}", dwdModel.getLocation(), resultStr);
        return resultStr;
    }

    /**
     *  过滤和尾
     * @param datas
     * @param dwdModel
     * @return
     */
    public static String screenHew(JSONArray datas,DwdModel dwdModel) {
        int size =datas.size() - 1;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{
            put(0,0);put(1,0);put(2,0);put(3,0);put(4,0);
            put(4,0);put(6,0);put(7,0);put(8,0);put(9,0);
        }};
        for (int i = size; i >= (30 - dwdModel.getRmCount()); i-- ) { //从20期中选热码
            String kjNum = datas.getJSONArray(i).getString(1);
            Integer dqHew = 0;
            if (dwdModel.getQhLocation() == 5) {
                Integer one = Integer.valueOf(kjNum.substring(3,4));
                Integer two = Integer.valueOf(kjNum.substring(4,5));
                dqHew = one + two;
            } else {
                Integer one = Integer.valueOf(kjNum.substring(0,1));
                Integer two = Integer.valueOf(kjNum.substring(1,2));
                dqHew = one + two;
            }
            dqHew = dqHew > 9 ? Integer.valueOf(String.valueOf(dqHew).substring(1,2)) : dqHew;

            switch (dqHew) {
                case 0:
                    map.put(0, map.get(0) == null ? 1 : map.get(0) + 1);
                    break;
                case 1:
                    map.put(1, map.get(1) == null ? 1 : map.get(1) + 1);
                    break;
                case 2:
                    map.put(2, map.get(2) == null ? 1 : map.get(2) + 1);
                    break;
                case 3:
                    map.put(3, map.get(3) == null ? 1 : map.get(3) + 1);
                    break;
                case 4:
                    map.put(4, map.get(4) == null ? 1 : map.get(4) + 1);
                    break;
                case 5:
                    map.put(5, map.get(5) == null ? 1 : map.get(5) + 1);
                    break;
                case 6:
                    map.put(6, map.get(6) == null ? 1 : map.get(6) + 1);
                    break;
                case 7:
                    map.put(7, map.get(7) == null ? 1 : map.get(7) + 1);
                    break;
                case 8:
                    map.put(8, map.get(8) == null ? 1 : map.get(8) + 1);
                    break;
                case 9:
                    map.put(9, map.get(9) == null ? 1 : map.get(9) + 1);
                    break;
            }
        }
        // 排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            //根据value升序排序
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });

        String hew = "";
        for (int i = 0; i < dwdModel.getHwNum(); i++) {
            hew += list.get(i).getKey();
        }

        logger.info("过滤和尾: {}", dwdModel.getLocation(), hew);
        return hew;
    }

    /**
     *  一键智能做号
     * @param dwdModel
     * @return
     */
    public static String brainPowerDoNum(JSONArray datas, DwdModel dwdModel) throws Exception {
        String dm = zhScreenDm(datas, dwdModel);
        String he = screenHe(datas, dwdModel);
        XuanHaoVo xuanHaoVo = new XuanHaoVo(dm, he, "0");

        if (dwdModel.getQhLocation() == 5) {
            dwdModel.setLocation(4);
            String oneDs = screenDs(datas, dwdModel);
            xuanHaoVo.setSd(oneDs);
            dwdModel.setLocation(5);
            String twoDs = screenDs(datas, dwdModel);
            xuanHaoVo.setGd(twoDs);
        } else {
            dwdModel.setLocation(1);
            String oneDs = screenDs(datas, dwdModel);
            xuanHaoVo.setSd(oneDs);
            dwdModel.setLocation(2);
            String twoDs = screenDs(datas, dwdModel);
            xuanHaoVo.setGd(twoDs);
        }
        String hw = screenHew(datas, dwdModel);
        xuanHaoVo.setHw(hw);

        xuanHaoVo.setQhLocation(dwdModel.getQhLocation());
        List<String> list = LotteryHandleUtil.doNum(xuanHaoVo);
        String str = "";
        for (String s : list) {
            str += s + " ";
        }

        return str;
    }

    /**
     * 单个位置定杀
     * @param datas
     * @param dwdModel
     * @return
     */
    private static String screenDs(JSONArray datas,DwdModel dwdModel) {
        String ds = "";
        String dm = screenDm(datas, dwdModel, 1);
        List<String> arr = Arrays.asList(dm.split(","));

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            if (!arr.contains(i+"")) {
                list.add(i+"");
            }
        }


        List<String> ouNum = new ArrayList<>();
        List<String> jiNum = new ArrayList<>();

        for (String str : list) {
            if (Integer.valueOf(str) % 2 == 0) {
                ouNum.add(str);
            } else {
                jiNum.add(str);
            }
        }

        // 当前期开奖号码
        String kjNum = datas.getJSONArray(29).getString(1);
        int dwNum =Integer.valueOf(kjNum.substring(dwdModel.getLocation() - 1, dwdModel.getLocation()));
        boolean evenNum = dwNum % 2 == 0; //是否是偶数
        int dsNum = dwdModel.getDsNum();
        if (evenNum) { //是偶数就优先定杀奇数
            if (jiNum.size() >= dsNum) {
                for (int i = 0; i < dsNum; i++) {
                    ds += jiNum.get(i);
                }
            } else {
                for (int i = 0; i < jiNum.size(); i++) {
                    ds += jiNum.get(i);
                }
                for (int i = 0; i < dsNum - jiNum.size(); i ++) {
                    ds += ouNum.get(i);
                }
            }
        } else {
            if (ouNum.size() >= dsNum) {
                for (int i = 0; i < dsNum; i++) {
                    ds += ouNum.get(i);
                }
            } else {
                for (int i = 0; i < ouNum.size(); i++) {
                    ds += ouNum.get(i);
                }
                for (int i = 0; i < dsNum - ouNum.size(); i ++) {
                    ds += jiNum.get(i);
                }
            }
        }

        logger.info("位置: {}, 定杀: {}", dwdModel.getLength(), ds);
        return ds;
    }

    @Test
    public void test () {
        XuanHaoVo vo = new XuanHaoVo();
        vo.setDm("0,1,2,3,4,5,7,8");
        vo.setTwom(2);
        // vo.setHe("8,10");
        //vo.setKua("0");
//        vo.setHw("2");
//        vo.setSd("1");
//        vo.setGd("3");
        doNum(vo);
    }

}
