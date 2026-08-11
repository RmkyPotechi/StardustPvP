package com.stardustpvp.core;

/**
 * Central, allocation-free coordinator for Stardust's optional performance
 * work. The pipeline is deliberately conservative: it can suppress cosmetic
 * work, but never input, packets, gameplay logic, or authoritative physics.
 */
public final class PerformancePipeline {
    private final AdaptivePerformance adaptive;
    private final RenderWorkBudget renderBudget;
    private final MemoryMonitor memory;
    private final MemoryPressurePolicy memoryPolicy;
    private final InputRenderPriority inputPriority;
    private final InputLatencyController inputLatency;

    private long frameId;
    private MemoryPressurePolicy.Level memoryLevel = MemoryPressurePolicy.Level.NORMAL;

    public PerformancePipeline(AdaptivePerformance adaptive,
                               RenderWorkBudget renderBudget,
                               MemoryMonitor memory,
                               MemoryPressurePolicy memoryPolicy,
                               InputRenderPriority inputPriority,
                               InputLatencyController inputLatency) {
        this.adaptive = adaptive;
        this.renderBudget = renderBudget;
        this.memory = memory;
        this.memoryPolicy = memoryPolicy;
        this.inputPriority = inputPriority;
        this.inputLatency = inputLatency;
    }

    public void beginFrame(long nowNs, double frameMs) {
        frameId++;
        renderBudget.beginFrame(frameId);
        adaptive.sample(frameMs);
        if (memory.sample(nowNs)) {
            memoryLevel = memoryPolicy.evaluate(memory.getUsedRatio());
        }
    }

    public boolean allowParticle(long nowNs) {
        if (inputPriority.deferOptionalWork(nowNs, inputLatency.mouseAgeNs(nowNs))) return false;
        if (memoryPolicy.reduceCosmetics(memoryLevel)) return false;
        return renderBudget.allowParticle() && adaptive.allowCosmeticWork(2);
    }

    public boolean allowCosmetic(long nowNs, int qualityLevel) {
        if (inputPriority.deferOptionalWork(nowNs, inputLatency.mouseAgeNs(nowNs))) return false;
        if (memoryPolicy.reduceCosmetics(memoryLevel) && qualityLevel > 0) return false;
        return renderBudget.allowCosmetic() && adaptive.allowCosmeticWork(qualityLevel);
    }

    public MemoryPressurePolicy.Level getMemoryLevel() {
        return memoryLevel;
    }

    public int getAdaptiveLevel() {
        return adaptive.getLevel();
    }
}
