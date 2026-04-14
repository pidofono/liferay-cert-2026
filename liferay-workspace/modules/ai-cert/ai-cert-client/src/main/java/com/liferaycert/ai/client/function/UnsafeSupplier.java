package com.liferaycert.ai.client.function;

import jakarta.annotation.Generated;

/**
 * @author pablo.iglesias
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}