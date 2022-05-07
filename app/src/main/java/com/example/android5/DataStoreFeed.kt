package com.example.android5

import com.example.android5.model.Feed

object DataStoreFeed{


    fun getDataSet(): List<Feed> {
        return listOf(
            Feed(name = "Phòng công tác sinh viên",
                hour = "1 giờ trước",
                link_home_web = "hcmus.edu.vn",
                status = "Nhà trường thông báo kết quả Điểm rèn luyện DỰ KIẾN HK1/2021-2022 của sinh viên hệ chính quy và hướng dẫn tiếp nhận khiếu nại như sau: 1. Vì lý do tình hình dịch bệnh, các sinh viên phải học trực tuyến trong suốt HK1/2021-2022",
                title_status = "Thông báo kết quả dự kiến Điểm rèn luyện sinh viên HK1/2021-2022"
            ),
            Feed(name = "Phòng đào tạo",
                hour = "2 giờ trước",
                link_home_web = "hcmus.edu.vn",
                status = "Với mục tiêu đảm bảo và không ngừng cải tiến chất lượng đào tạo cũng như nắm bắt được tình hình việc làm và những khó khăn của các bạn trong thời gian đầu khi ra trường, Nhà trường tổ chức \"Khảo sát tình hình việc làm của cựu sinh viên sau 01 năm tốt nghiệp\". Đây là một trong những hoạt động thường xuyên của Nhà trường để cập nhật tình hình các bạn cựu sinh viên và đánh giá lại chất lượng đào tạo của Nhà trường.",
                title_status = "Kế hoạch khảo sát việc học, việc làm đối với sinh viên tốt nghiệp đợt tháng 3, 4/2022"
            ),
            Feed(name = "Phòng khảo thí và đảm bảo chất lượng",
                hour = "3 giờ trước",
                link_home_web = "hcmus.edu.vn",
                status = "Để đảm bảo và cải tiến liên tục chất lượng đào tạo như đã cam kết, trường Đại học Bách Khoa đã tạo ra các kênh thu thập thông tin phản hồi cũng như lắng nghe ý kiến đóng góp từ các bên liên quan, trong đó những thông tin phản hồi của cựu sinh viên luôn được nhà trường quan tâm.",
                title_status = "V/v khảo sát sinh viên tốt nghiệp đợt T3, T4/2022"
            )
        )
    }
}

