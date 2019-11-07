package org.fh.api.pojo.wxorder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PayCallBack {
    @XStreamAlias("appid")
    private String appid;
    @XStreamAlias("bank_type")
    private String bank_type;
    @XStreamAlias("cash_fee")
    private String cash_fee;
    @XStreamAlias("fee_type")
    private String fee_type;
    @XStreamAlias("is_subscribe")
    private String is_subscribe;
    @XStreamAlias("mch_id")
    private String mch_id;
    @XStreamAlias("nonce_str")
    private String nonce_str;
    @XStreamAlias("openid")
    private String openid;
    @XStreamAlias("out_trade_no")
    private String out_trade_no;
    @XStreamAlias("result_code")
    private String result_code;
    @XStreamAlias("return_code")
    private String return_code;
    @XStreamAlias("sign")
    private String sign;
    @XStreamAlias("time_end")
    private String time_end;
    @XStreamAlias("total_fee")
    private BigDecimal total_fee;
    @XStreamAlias("trade_type")
    private String trade_type;
    @XStreamAlias("transaction_id")
    private String transaction_id;

}
