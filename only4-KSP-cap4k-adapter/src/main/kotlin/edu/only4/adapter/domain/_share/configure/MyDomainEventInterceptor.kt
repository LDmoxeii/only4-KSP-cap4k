package edu.only4.adapter.domain._share.configure

import com.only4.cap4k.ddd.core.domain.event.DomainEventInterceptor
import com.only4.cap4k.ddd.core.domain.event.EventRecord
import java.time.LocalDateTime

class MyDomainEventInterceptor : DomainEventInterceptor {
    override fun onAttach(eventPayload: Any, entity: Any, schedule: LocalDateTime) {}

    override fun onDetach(eventPayload: Any, entity: Any) {}

    override fun prePersist(event: EventRecord) {}

    override fun postPersist(event: EventRecord) {}

    override fun preRelease(event: EventRecord) {}

    override fun postRelease(event: EventRecord) {}

    override fun onException(throwable: Throwable, event: EventRecord) {}

}
