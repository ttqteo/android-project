package com.example.android5

import com.example.android5.model.Noti
import com.example.android5.model.Schedule

object DataStoreSchedule {
    fun getDataSet(): List<Schedule> {
        return listOf(
            Schedule(subject_id = "ETC10209",
                    subject_name = "Thực hành thiết kế SoC",
                    class_group = "Nhung_TH",
                    schedule = "T2(T3-T4)"),
            Schedule(subject_id = "ETC10211",
                subject_name = "Tập sự 2-3 tháng",
                class_group = "MTNhung",
                schedule = "T5(15-16)"),
            Schedule(subject_id = "ETC10212",
                subject_name = "Lập trình ứng dụng trên thiết bị di động",
                class_group = "Nhung_THNhung",
                schedule = "T5(1-3)-P.E302"),
            Schedule(subject_id = "ETC10213",
                subject_name = "Thực hành Lập trình ứng dụng trên thiết bị di động",
                class_group = "Nhung_TH",
                schedule = "T7(13-14)"),
            Schedule(subject_id = "ETC10214",
                subject_name = "Hệ thống nhúng",
                class_group = "Nhung",
                schedule = "T3(1-3)-P.E302")
        )
    }
}