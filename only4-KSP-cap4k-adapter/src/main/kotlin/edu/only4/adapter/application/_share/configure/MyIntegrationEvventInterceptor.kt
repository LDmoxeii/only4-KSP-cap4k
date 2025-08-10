package edu.only4.adapter.application._share.configure

import com.only4.cap4k.ddd.core.application.event.IntegrationEventInterceptor
import com.only4.cap4k.ddd.core.domain.event.EventRecord
import java.time.LocalDateTime


class MyIntegrationEvventInterceptor : IntegrationEventInterceptor {
    override fun onAttach(eventPayload: Any, schedule: LocalDateTime) {}

    override fun onDetach(eventPayload: Any) {}

    override fun prePersist(event: EventRecord) {}

    override fun postPersist(event: EventRecord) {}

    override fun preRelease(event: EventRecord) {}

    override fun postRelease(event: EventRecord) {}

    override fun onException(throwable: Throwable, event: EventRecord) {}
}
