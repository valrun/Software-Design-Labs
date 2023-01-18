package statistics

import clock.Clock
import java.time.Duration
import java.time.Instant

class HourlyEventsStatistic(
    private val clock: Clock
) : EventsStatistic {
    private val initialTime = clock.now()
    private val statistic = mutableMapOf<EventName, MutableList<Instant>>()

    private val perSec = 60.0
    private val perMin = 60.0

    override fun incEvent(eventName: EventName) {
        statistic.putIfAbsent(eventName, mutableListOf())
        statistic[eventName]!! += clock.now()
    }

    override fun getEventStatisticByName(eventName: EventName): Double {
        val now = clock.now()
        val hour = Duration.ofHours(1)

        val events = statistic[eventName]
            ?.filter { now - hour < it && it <= now }
            ?: emptyList()
        return events.size / perMin
    }

    override fun getAllEventStatistic(): Map<EventName, Double> {
        val now = clock.now()
        val hour = Duration.ofHours(1)
        val result = mutableMapOf<EventName, Double>()

        for (eventName in statistic.keys) {
            val events = statistic[eventName]
                ?.filter { now - hour < it && it <= now }
                ?: emptyList()

            if (events.isNotEmpty()) {
                result[eventName] = events.size / perMin
            }
        }

        return result
    }

    override fun printStatistic() {
        val nowSec = clock.now().epochSecond
        val initSec = initialTime.epochSecond
        val totalHours = (nowSec - initSec) / perSec / perMin

        for (eventName in statistic.keys) {
            val events = statistic[eventName] ?: emptyList()
            val rpm = events.size / totalHours
            println("Event: $eventName  ### RPM: $rpm")
        }
    }
}
