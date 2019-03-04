package com.uvgouapp.model.other;


import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/16
 * - @Description:  省市县
 */
public class CityBean implements IPickerViewData {

    /**
     * ID : 110000
     * n : 北京市
     * c : [{"ID":"110000","n":"北京市","d":[{"ID":"110101","n":"东城区"},{"ID":"110102","n":"西城区"},{"ID":"110105","n":"朝阳区"},{"ID":"110106","n":"丰台区"},{"ID":"110107","n":"石景山区"},{"ID":"110108","n":"海淀区"},{"ID":"110109","n":"门头沟区"},{"ID":"110111","n":"房山区"},{"ID":"110112","n":"通州区"},{"ID":"110113","n":"顺义区"},{"ID":"110114","n":"昌平区"},{"ID":"110115","n":"大兴区"},{"ID":"110116","n":"怀柔区"},{"ID":"110117","n":"平谷区"},{"ID":"110228","n":"密云县"},{"ID":"110229","n":"延庆县"}]}]
     */

    private String ID;
    private String n;
    private List<CBean> c;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public List<CBean> getC() {
        return c;
    }

    public void setC(List<CBean> c) {
        this.c = c;
    }

    @Override
    public String getPickerViewText() {
        return this.n;
    }

    public static class CBean {
        /**
         * ID : 110000
         * n : 北京市
         * d : [{"ID":"110101","n":"东城区"},{"ID":"110102","n":"西城区"},{"ID":"110105","n":"朝阳区"},{"ID":"110106","n":"丰台区"},{"ID":"110107","n":"石景山区"},{"ID":"110108","n":"海淀区"},{"ID":"110109","n":"门头沟区"},{"ID":"110111","n":"房山区"},{"ID":"110112","n":"通州区"},{"ID":"110113","n":"顺义区"},{"ID":"110114","n":"昌平区"},{"ID":"110115","n":"大兴区"},{"ID":"110116","n":"怀柔区"},{"ID":"110117","n":"平谷区"},{"ID":"110228","n":"密云县"},{"ID":"110229","n":"延庆县"}]
         */

        private String ID;
        private String n;
        private List<DBean> d;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public List<DBean> getD() {
            return d;
        }

        public void setD(List<DBean> d) {
            this.d = d;
        }

        public static class DBean {
            /**
             * ID : 110101
             * n : 东城区
             */

            private String ID;
            private String n;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }
        }
    }
}
