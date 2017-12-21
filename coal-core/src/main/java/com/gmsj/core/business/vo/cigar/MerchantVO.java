package com.gmsj.core.business.vo.cigar;

import com.gmsj.core.business.model.TablePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author wmy
 * @version $$Id: ${file_name}, v 0.1 ${date} ${time} wmy Exp $$
 * @ClassName： ${type_name}
 * @Description：
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MerchantVO extends TablePage {

    private Long Id;

    private String userName;

    private String idCardNumber;

    private String phone;

    private String storeName;

    private String storeAddress;

    private String storeNumber;

    private boolean isAuth;

    private BigDecimal creditLine;
}
