package com.zxy.hrbcu.meeting.constant;

/**
 * Created by zxy on 2018/2/28.
 *
 */
public class Constant {
    //列表每页条数
    public static final int PER_PAGE_SIZE = 10;

    //会议标识
    public static final String MEETING_MAIN_ID = "1";

    //用户类别
    //参会人员
    public static final int USER_TYPE_JOIN = 1;
    //管理员
    public static final int USER_TYPE_ADMIN = 2;

    //状态
    public static final int STATE_DELETE = -1;
    public static final int STATE_INVALID = 0;
    public static final int STATE_AVAILABLE = 1;

    //选中
    public static final String CHECKED = "1";
    public static final String NO_CHECKED = "0";

    //住宿要求
    public static final String bed_require_Standard_room = "1";
    public static final String bed_require_Single_room = "2";
    public static final String bed_require_no = "3";

    /**
    *机场/火车站
     * 1-太平机场
     * 2-哈站
     * 3-哈西
     * 4-哈东
     * 5-哈北
     */
    public static final String tp_airport = "1";
    public static final String hrbin_train_station = "2";
    public static final String hrbin_west_train_station = "3";
    public static final String hrbin_east_train_station = "4";
    public static final String hrbin_north_train_station = "5";


}
