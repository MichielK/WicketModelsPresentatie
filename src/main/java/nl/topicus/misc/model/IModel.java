package nl.topicus.misc.model;

import org.apache.wicket.model.IDetachable;

@FunctionalInterface
public interface IModel<T> extends IDetachable
{
	// Enige abstracte methode in IModel
	T getObject();

	default void setObject(final T object)
	{
		throw new UnsupportedOperationException(
			"Override this method to support setObject(Object)");
	}

	@Override
	default void detach()
	{
	}
}
