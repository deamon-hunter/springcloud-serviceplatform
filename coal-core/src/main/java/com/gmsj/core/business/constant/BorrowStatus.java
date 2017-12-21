package com.gmsj.core.business.constant;

/**
 * @author Ovrille
 * @date 2017/10/13
 */
public enum BorrowStatus {
    BORROW_CANCEL("已取消", 0),
    BORROW_COMMITED("已提交", 1),
    BORROW_SYSTEM_PASS("系统自动审批通过", 2),
    BORROW_ARTIFICIAL_NO_PASS("人工审批未通过", 3),
    BORROW_APPROVE_SUCCESS("用户借款审核成功", 4),
    BORROW_LENDING("已放款", 5);


    private String name;
    private int index;

    private BorrowStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * 根据状态值获取状态名称
     *
     * @param index
     * @return
     */
    public static String getName(Integer index) {
        String returnName = "";
        for (BorrowStatus state : values()) {
            if (index == state.index) {
                returnName = state.name;
            }
        }
        return returnName;
    }
}
