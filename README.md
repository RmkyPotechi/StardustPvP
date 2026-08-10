# StardustPvP

A Minecraft 1.8.9 PvP-focused client/mod project built around performance, responsiveness, and legitimate client-side features.

## Current core

- Forge 1.8.9 / 11.15.1.2318 target
- Centralized client configuration
- Allocation-free frame-time sampling on the hot path
- FPS / frame-time / latency diagnostics HUD
- Read-only server ping diagnostics
- Raw LWJGL mouse-delta support is designed as a non-invasive input layer
- Minimum View Bobbing is a first-class client setting
- Optional particle/performance settings are isolated so they can be benchmarked safely
- Java 8 GitHub Actions build pipeline

## OptiFine

StardustPvP is designed to work alongside a legally obtained OptiFine installation. OptiFine itself is not redistributed in this repository. Performance features are kept modular so OptiFine's renderer can remain the primary rendering implementation where present.

## Performance philosophy

Do not optimize by adding work to every frame. Hot paths should avoid allocations, unnecessary reflection, packet rewriting, and repeated expensive lookups. Performance changes should be benchmarkable and reversible.

The project will prioritize frame-time consistency and 1% lows rather than chasing an inflated average FPS number.

## Fair-play policy

StardustPvP will **not** implement combat automation or other cheat functionality. This includes KillAura, AutoClicker, AimAssist, Reach modification, Velocity modification, scaffold automation, inventory automation, or packet manipulation intended to gain an unfair advantage.

Diagnostic displays such as CPS, ping, FPS, frame time, hitboxes, and server-provided information are allowed because they do not automate gameplay.

## Development target

- Minecraft: 1.8.9
- Forge: 11.15.1.2318
- Java: 8
