package com.mj.shishicai.models;

import java.io.Serializable;
import java.util.List;

/**
 * author: Rea.x
 * date: 2017/12/5.
 */

public class ManhuaModel implements Serializable {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"pagebean":{"contentlist":[{"id":"/weimanhua/kbmh/94615.html","link":"http://heibaimanhua.com/weimanhua/kbmh/94615.html","thumbnailList":["http://img03.store.sogou.com/net/a/04/link?appid=100520120&w=249&url=http://img.heibaimanhua.com/wp-content/uploads/2016/04/27/20160427_57201f7baf89a.jpg_x"],"time":"2小时前","title":"人性实验《什么是真爱？》-黑白漫话"}],"currentPage":1,"hasMorePage":true,"maxResult":"50"},"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean implements Serializable {
        /**
         * pagebean : {"contentlist":[{"id":"/weimanhua/kbmh/94615.html","link":"http://heibaimanhua.com/weimanhua/kbmh/94615.html","thumbnailList":["http://img03.store.sogou.com/net/a/04/link?appid=100520120&w=249&url=http://img.heibaimanhua.com/wp-content/uploads/2016/04/27/20160427_57201f7baf89a.jpg_x"],"time":"2小时前","title":"人性实验《什么是真爱？》-黑白漫话"}],"currentPage":1,"hasMorePage":true,"maxResult":"50"}
         * ret_code : 0
         */

        private PagebeanBean pagebean;
        private int ret_code;

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanBean implements Serializable {
            /**
             * contentlist : [{"id":"/weimanhua/kbmh/94615.html","link":"http://heibaimanhua.com/weimanhua/kbmh/94615.html","thumbnailList":["http://img03.store.sogou.com/net/a/04/link?appid=100520120&w=249&url=http://img.heibaimanhua.com/wp-content/uploads/2016/04/27/20160427_57201f7baf89a.jpg_x"],"time":"2小时前","title":"人性实验《什么是真爱？》-黑白漫话"}]
             * currentPage : 1
             * hasMorePage : true
             * maxResult : 50
             */

            private int currentPage;
            private boolean hasMorePage;
            private String maxResult;
            private List<ContentlistBean> contentlist;

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public boolean isHasMorePage() {
                return hasMorePage;
            }

            public void setHasMorePage(boolean hasMorePage) {
                this.hasMorePage = hasMorePage;
            }

            public String getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(String maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean implements Serializable {
                /**
                 * id : /weimanhua/kbmh/94615.html
                 * link : http://heibaimanhua.com/weimanhua/kbmh/94615.html
                 * thumbnailList : ["http://img03.store.sogou.com/net/a/04/link?appid=100520120&w=249&url=http://img.heibaimanhua.com/wp-content/uploads/2016/04/27/20160427_57201f7baf89a.jpg_x"]
                 * time : 2小时前
                 * title : 人性实验《什么是真爱？》-黑白漫话
                 */

                private String id;
                private String link;
                private String time;
                private String title;
                private List<String> thumbnailList;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<String> getThumbnailList() {
                    return thumbnailList;
                }

                public void setThumbnailList(List<String> thumbnailList) {
                    this.thumbnailList = thumbnailList;
                }
            }
        }
    }
}
