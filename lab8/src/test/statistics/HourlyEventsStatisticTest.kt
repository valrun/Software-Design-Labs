package statistics

import clock.SettableClock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.Instant

internal class HourlyEventsStatisticTest {

    @Test
    fun getEventStatisticByNameTest() {
        val now = Instant.now()
        val oneHourAgo = now - Duration.ofMinutes(59)
        val clock = SettableClock(oneHourAgo)

        val events = mapOf("event1" to 1, "event2" to 2, "event3" to 3)
        val statistic = HourlyEventsStatistic(clock)

        events.forEach { (eventName, times) ->
            repeat(times) { statistic.incEvent(eventName) }
        }

        clock.setNow(now)
        for ((eventName, times) in events.entries) {
            assertEquals(
                times / 60.0,
                statistic.getEventStatisticByName(eventName),
                "Event $eventName was $times times per last hour"
            )
        }
    }

    @Test
    fun getEventStatisticByNameTestOldEvent() {
        val now = Instant.now()
        val oneHourAgo = now - Duration.ofMinutes(59)
        val twoHourAgo = oneHourAgo - Duration.ofHours(1)
        val clock = SettableClock(twoHourAgo)

        val oldEvents = mapOf("event1" to 1, "event2" to 2, "event3" to 3)
        val events = mapOf("event1" to 4, "event5" to 5)
        val statistic = HourlyEventsStatistic(clock)

        oldEvents.forEach { (eventName, times) ->
            repeat(times) { statistic.incEvent(eventName) }
        }

        clock.setNow(oneHourAgo)
        events.forEach { (eventName, times) ->
            repeat(times) { statistic.incEvent(eventName) }
        }

        clock.setNow(now)
        for ((eventName, _) in oldEvents.entries) {
            if (eventName !in events) {
                assertEquals(
                    0.0,
                    statistic.getEventStatisticByName(eventName),
                    "Event $eventName was 2 hour ago, not last hour"
                )
            }
        }
        for ((eventName, times) in events.entries) {
            assertEquals(
                times / 60.0,
                statistic.getEventStatisticByName(eventName),
                "Event $eventName was $times times per last hour"
            )
        }
    }

    @Test
    fun getAllEventStatisticTest() {
        val now = Instant.now()
        val oneHourAgo = now - Duration.ofMinutes(59)
        val clock = SettableClock(oneHourAgo)

        val events = mapOf("event1" to 1, "event2" to 2, "event3" to 3)
        val statistic = HourlyEventsStatistic(clock)

        events.forEach { (eventName, times) ->
            repeat(times) { statistic.incEvent(eventName) }
        }

        clock.setNow(now)
        val stat = statistic.getAllEventStatistic()
        assertEquals(events.size, stat.size, "AllEventStatistic has wrong size")
        for ((eventName, times) in events.entries) {
            assertEquals(
                times / 60.0,
                stat[eventName],
                "Event $eventName was $times times per last hour"
            )
        }
    }

    @Test
    fun getAllEventStatisticTestOldEvent() {
        val now = Instant.now()
        val oneHourAgo = now - Duration.ofMinutes(59)
        val twoHourAgo = oneHourAgo - Duration.ofHours(1)
        val clock = SettableClock(twoHourAgo)

        val oldEvents = mapOf("event1" to 1, "event2" to 2, "event3" to 3)
        val events = mapOf("event1" to 4, "event5" to 5)
        val statistic = HourlyEventsStatistic(clock)

        oldEvents.forEach { (eventName, times) ->
            repeat(times) { statistic.incEvent(eventName) }
        }

        clock.setNow(oneHourAgo)
        events.forEach { (eventName, times) ->
            repeat(times) { statistic.incEvent(eventName) }
        }

        clock.setNow(now)
        val stat = statistic.getAllEventStatistic()
        assertEquals(events.size, stat.size, "AllEventStatistic has wrong size")
        for ((eventName, _) in oldEvents.entries) {
            if (eventName !in events) {
                assertEquals(
                    false,
                    eventName in stat,
                    "Event $eventName was 2 hour ago, not last hour"
                )
            }
        }
        for ((eventName, times) in events.entries) {
            assertEquals(
                times / 60.0,
                stat[eventName],
                "Event $eventName was $times times per last hour"
            )
        }
    }
}
