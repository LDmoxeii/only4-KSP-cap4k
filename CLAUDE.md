# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

This is a Gradle-based Kotlin JVM project with multiple modules. Use the Gradle wrapper for all operations:

- `./gradlew build` - Build all modules
- `./gradlew check` - Run all checks including tests
- `./gradlew test` - Run tests across all modules
- `./gradlew :only4-KSP-cap4k-start:bootRun` - Start the Spring Boot application
- `./gradlew clean` - Clean all build outputs

Individual module testing:

- `./gradlew :only4-KSP-cap4k-domain:test` - Test domain module
- `./gradlew :only4-KSP-cap4k-application:test` - Test application module
- `./gradlew :only4-KSP-cap4k-adapter:test` - Test adapter module

## Architecture Overview

This project follows Domain-Driven Design (DDD) with Clean Architecture principles, implemented as a multi-module Kotlin
project using the cap4k framework.

### Module Structure

- **only4-KSP-cap4k-domain**: Core domain logic containing aggregates, entities, domain events, and factories
- **only4-KSP-cap4k-application**: Application services, command handlers, query handlers, and domain event subscribers
- **only4-KSP-cap4k-adapter**: Infrastructure layer with REST controllers, JPA repositories, and external integrations
- **only4-KSP-cap4k-start**: Spring Boot application entry point and configuration

### Package Structure

All packages use the `edu.only` namespace:

- Domain aggregates: `edu.only.ksp.domain.aggregates`
- Application commands/queries: `edu.only.ksp.application.commands`, `edu.only.ksp.application.queries`
- REST APIs: `edu.only.ksp.adapter.portal.api`
- Infrastructure: `edu.only.ksp.adapter.domain.repositories`

### Key Patterns

1. **CQRS with Mediator**: Commands and queries are handled through the cap4k Mediator pattern
2. **Domain Events**: Aggregates publish domain events that are handled by application subscribers
3. **Aggregate Design**: Domain entities follow DDD aggregate patterns with behavior methods
4. **Repository Pattern**: JPA repositories are abstracted through interfaces in the domain layer

### Framework Dependencies

- **cap4k-ddd-starter**: Core DDD framework providing Mediator, annotations, and domain event support
- **Spring Boot 3.1.12**: Web framework and dependency injection
- **Kotlin 2.1.20**: Primary language with Spring and JPA plugins
- **QueryDSL & Blaze Persistence**: For complex queries
- **MySQL with Druid**: Database and connection pooling

### Code Generation

The project uses KSP (Kotlin Symbol Processing) and includes cap4k code generation. Domain aggregates use `@Aggregate`
annotations for code generation.

## Development Notes

- Java 17 is required (configured via Kotlin JVM toolchain)
- The project uses Gradle build cache and configuration cache for performance
- All JPA entities include soft delete functionality via `@SQLDelete` and `@Where` annotations
- Domain events are automatically published through the cap4k framework
- REST endpoints use POST for all operations with request/response DTOs
