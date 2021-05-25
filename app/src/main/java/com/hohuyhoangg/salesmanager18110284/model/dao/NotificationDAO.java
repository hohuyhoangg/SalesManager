package com.hohuyhoangg.salesmanager18110284.model.dao;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dto.NotificationDTO;

import java.util.ArrayList;

public class NotificationDAO {

    private static NotificationDAO instance = null;

    private NotificationDAO() {
    }

    public static NotificationDAO getInstance() {
        if (instance == null) {
            instance = new NotificationDAO();
        }
        return instance;
    }
    public ArrayList<NotificationDTO> initData() {
        ArrayList<NotificationDTO> result = new ArrayList<>();
        result.add(new NotificationDTO("Đừng bỏ lỡ ưu đãi!!!","Ly Giữ nhiệt Lock - Lock ... còn trong giỏ hàng kìa chốt nhanh kẻo hết đơn.", R.drawable.image1_notification));
        result.add(new NotificationDTO("Đừng bỏ lỡ ưu đãi!!!","Những ưu đãi đang chở bạn phía sau.", R.drawable.image6_notification));
        result.add(new NotificationDTO("12 giờ: Quà tặng 0đ + Voucher 500K!!!","Giờ vàng - Hàng quốc tế sale 50%", R.drawable.image4_notification));
        result.add(new NotificationDTO("18 giờ: Loạt mã Freeship 0đ mở đợt mới!!!","Flash Sale cực HOT mỗi 30 phút. Số lượng có hạn - vào ngay kẻo hết", R.drawable.image2_notification));
        result.add(new NotificationDTO("Mua đúng đơn - Nhận đúng hàng","Tham gia chiến dịch nói KHÔNG với LỪA ĐẢO cùng bí quyết '2 - KHÔNG - 2 CÓ' KHÔNG nhận hàng có mã đơn lạ. KHÔNG" +
                "nhận quà không rõ chương trình hoặc có thu thêm phí. Kiểm tra và chỉ nhận hàng có mã đơn trùng với mã đơn đã đặt trên hệ thống của Taki. Kiểm tra và nhận quà từ chương trình mà bạn có" +
                "tham gia. Mọi thắc mắc hãy gọi đến tổng đài của Taki 18001506. Bấm ngay xem bí quyết kiểm tra đơn hàng, nhận hàng chuẩn", R.drawable.image3_notification));
        result.add(new NotificationDTO("15 giờ: Quà tặng 0đ + Voucher 100K!!!","Giờ vàng - Hàng quốc tế sale 50%", R.drawable.image4_notification));
        result.add(new NotificationDTO("hoangho1147@gmail.com đừng bỏ lỡ Voiucher 100K","Kết hợp mã Freeship 0đ trong ví ngay thôi nào.", R.drawable.image5_notification));
        return result;
    }
}
