package nl.topicus.pages;

import java.util.Random;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import nl.topicus.BasePage;

public class FunctionalInterfacePage extends BasePage
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		// Dit is een anonymous-inner-class
		// Een anonymous-inner-class heeft redelijk veel boilerplate-code
		IModel<String> modelOne = new Model<String>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject()
			{
				return getRandom();
			}
		};

		// Daarom heeft Java wat "syntactic sugar" toegevoegd om dit wat korter te noteren. Deze
		// "syntactic sugar" kan op _alle_ functional-interfaces toegepast worden:
		IModel<String> modelTwo = () -> getRandom();
		IModel<String> modelThree = this::getRandom;

		// Bovenstaande 3 models werken dus _exact_ hetzelfde.

		// en door de syntactic sugar is het lastig om het verschil tussen deze 2 te zien en te
		// snappen - ik hoop dat door de vorige pagina het duidelijk(er) geworden is.
		add(new Label("staticLabel", getRandom()));
		add(new Label("dynamicLabel", () -> getRandom()));

	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return null;
	}

	private String getRandom()
	{
		return Integer.toString(new Random().nextInt());
	}
}
