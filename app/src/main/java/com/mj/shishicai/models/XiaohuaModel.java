package com.mj.shishicai.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinru on 2017/12/3.
 */

public class XiaohuaModel implements Serializable{
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allNum":933,"allPages":47,"contentlist":[{"ct":"　2015-07-10 05:54:00.000","text":"　　男生拉着女生沮丧着脸说，再给我一次机会，求求你！说好不提分手的！<br />\r\n　　女生甩开男孩的手说：你TM现在在我心里就是个菩萨，除了拜拜我什么都不想做。","title":"你TM现在在我心里就是个菩萨"}],"currentPage":1,"maxResult":20}
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

    public static class ShowapiResBodyBean  implements Serializable{
        /**
         * allNum : 933
         * allPages : 47
         * contentlist : [{"ct":"　2015-07-10 05:54:00.000","text":"　　男生拉着女生沮丧着脸说，再给我一次机会，求求你！说好不提分手的！<br />\r\n　　女生甩开男孩的手说：你TM现在在我心里就是个菩萨，除了拜拜我什么都不想做。","title":"你TM现在在我心里就是个菩萨"}]
         * currentPage : 1
         * maxResult : 20
         */

        private int allNum;
        private int allPages;
        private int currentPage;
        private int maxResult;
        private List<ContentlistBean> contentlist;

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }

        public static class ContentlistBean  implements Serializable{
            /**
             * ct : 　2015-07-10 05:54:00.000
             * text : 　　男生拉着女生沮丧着脸说，再给我一次机会，求求你！说好不提分手的！<br />
             　　女生甩开男孩的手说：你TM现在在我心里就是个菩萨，除了拜拜我什么都不想做。
             * title : 你TM现在在我心里就是个菩萨
             */

            private String ct;
            private String text;
            private String title;

            public String getCt() {
                return ct;
            }

            public void setCt(String ct) {
                this.ct = ct;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
