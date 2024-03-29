package nl.topicus.pages.getobject;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import nl.topicus.pages.BasePage;
import nl.topicus.pages.readwrite.Leerling;

public class FunctionalInterfacePage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private Label statischLabel, dynamischLabel;

	@SuppressWarnings("unused")
	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		// Dit noemen we een anonymous-inner-class
		// Een anonymous-inner-class heeft redelijk veel boilerplate-code
		IModel<String> modelOne = new IModel<String>()
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

		IModel<String> modelTwo = () -> { return getRandom(); };
		IModel<String> modelThree = () -> getRandom();
		IModel<String> modelFour = this::getRandom;

		// Bovenstaande 4 model-notaties werken dus functioneel _exact_ hetzelfde - zijn 1-op-1 uitwisselbaar

		// en door de syntactic sugar is het lastig om het verschil tussen deze 2 te zien en te
		// snappen - ik hoop dat door de vorige pagina het duidelijk(er) geworden is:
		add(statischLabel = new Label("staticLabel", getRandom()));
		add(dynamischLabel = new Label("dynamicLabel", () -> getRandom()));

		addClickBehaviour();
		
		// Voorbeeld van een andere functional interface - worden bijvoorbeeld vaak in streams gebruikt:
		Function<Boolean, String> function = b -> b.toString(); // == return b.toString();
		Predicate<Leerling> filter = leerling -> leerling != null;
	}

	private String getRandom()
	{
		return Integer.toString(new Random().nextInt());
	}

	private void addClickBehaviour()
	{
		WebMarkupContainer wmc = new WebMarkupContainer("clickContainer");
		wmc.add(new AjaxEventBehavior("click")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target)
			{
				// "AjaxRequestTarget" geeft aan dat je met een Ajax-request te maken hebt.
				// Je intentie is dus om maar een deel van je scherm opnieuw te renderen - in dit
				// geval wil je _2_ componenten opnieuw renderen.
				target.add(statischLabel, dynamischLabel);
			}
		});
		add(wmc);
		statischLabel.setOutputMarkupId(true);
		dynamischLabel.setOutputMarkupId(true);
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return DynamischModelWrapUpPage.class;
	}
}
