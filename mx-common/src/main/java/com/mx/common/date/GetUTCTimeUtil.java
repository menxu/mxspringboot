package com.mx.common.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by menxu on 18/6/28.
 */
public class GetUTCTimeUtil {
    /**
     * 获取当前UTC时间
     * @return
     */
    public static LocalDateTime getUTCTime() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }


    public static LocalDateTime getZoneTime(ZonedDateTime now,String timeZone){
        ZoneId newYokZoneId = ZoneId.of(timeZone);
        return now.withZoneSameInstant(newYokZoneId).toLocalDateTime();
    }

    public static LocalDateTime getBeijingTime(LocalDateTime time,String timeZone){
        ZonedDateTime now=ZonedDateTime.of(time, ZoneId.of(timeZone));
        LocalDateTime beijing =now.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        return beijing.plusHours(8);
    }
}
