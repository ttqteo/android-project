package com.example.android5

import com.example.android5.model.Feed
import com.example.android5.model.Noti

object DataStoreNoti{


    fun getDataSet(): List<Noti> {
        return listOf(
            Noti(deparment_noti = "Phòng Công tác sinh viên mới ra thông báo mới. Bấm vào để xem.",
                title_noti = "Thông báo kết quả dự kiến Điểm rèn luyện sinh viên HK1/2021-2022",
                time_noti = "9:50 07-04-2022",
            ),
            Noti(deparment_noti = "Phòng Đào tạo mới ra thông báo mới. Bấm vào để xem.",
                title_noti = "Kế hoạch khảo sát việc học, việc làm đối với sinh viên tốt nghiệp đợt tháng 3, 4/2022",
                time_noti = "8:50 07-04-2022",
            ),
            Noti(deparment_noti = "Phòng Khảo thí và đảm bảo chất lượng mới ra thông báo mới. Bấm vào để xem.",
                title_noti = "V/v khảo sát sinh viên tốt nghiệp đợt T3, T4/2022",
                time_noti = "7:50 07-04-2022",
            )
        )
    }
}