package com.xiao.mywangyi.bean;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/9/14.
 */

public class Bean {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-09-14 12:18","title":"养荷兰猪需谨慎？恐携带感染病菌","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/dcf1334bce40452e945d8efa01b2b6c620170914121741.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0914/12/CU9U3OVC0038804U.html"},{"ctime":"2017-09-13 12:17","title":"医联体实现社区\u201c拍片\u201d专家诊断","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/24768bf64fa848c2b71b633989cdb52920170913121538.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0913/12/CU7BMCJ40038804U.html"},{"ctime":"2017-09-12 12:19","title":"医联体建设全面铺开 让医疗资源动起来","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/9f0392d986e940bba09d2c8a6cb812d720170912121856.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0912/12/CU4PD73G0038804U.html"},{"ctime":"2017-09-11 10:02","title":"美国家科学院报告支持基因编辑人类胚胎","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/9838ee117b334b09ac2604577bacc37020170911100103.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0911/10/CU1V4KH40038804U.html"},{"ctime":"2017-09-11 09:52","title":"关注：儿童食品使用添加剂安全吗？","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/b2ab2177dd084b01a3a9e0745f54c0c220170911094949.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0911/09/CU1UI7FA0038804U.html"},{"ctime":"2017-09-08 12:27","title":"健不健康靠伴侣！另一半对健康这么重要","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/84f768716d224053a72a37f8291716d620170908122708.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0908/12/CTQG9ENE0038804U.html"},{"ctime":"2017-09-07 12:11","title":"白露养生 从争\u201c蜂\u201d吃\u201c醋\u201d开始","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/03f696519c9b4f97900a64c5780ce38120170907121034.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0907/12/CTNSUJOJ0038804U.html"},{"ctime":"2017-09-06 12:35","title":"KTV下午场包厢被老人占领 唱歌成养生手段","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/57a5a6f7ff0d42e4a6930e903cbcefb920170906123404.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0906/12/CTLBTKG00038804U.html"},{"ctime":"2017-09-05 12:34","title":"研究：新型疫苗或可对抗毒品成瘾问题","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/a4c505778a764b45be0533338e8b3b0520170905123417.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0905/12/CTIPFUK20038804U.html"},{"ctime":"2017-09-04 12:10","title":"北京医改新政 提升患者的就医体验","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/7120e9f5d7d44e0c9a7f3701c5c683fd20170904120841.png_150x100x1x85.jpg","url":"http://jiankang.163.com/17/0904/12/CTG5MTQB0038804U.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-09-14 12:18
         * title : 养荷兰猪需谨慎？恐携带感染病菌
         * description : 网易健康
         * picUrl : http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/dcf1334bce40452e945d8efa01b2b6c620170914121741.png_150x100x1x85.jpg
         * url : http://jiankang.163.com/17/0914/12/CU9U3OVC0038804U.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
