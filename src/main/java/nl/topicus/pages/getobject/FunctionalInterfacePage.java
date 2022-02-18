package nl.topicus.pages.getobject;

import java.util.Random;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import nl.topicus.pages.BasePage;

public class FunctionalInterfacePage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private Label statischLabel, dynamischLabel;

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
		// snappen - ik hoop dat door de vorige pagina het duidelijk(er) geworden is:
		add(statischLabel = new Label("staticLabel", getRandom()));
		add(dynamischLabel = new Label("dynamicLabel", () -> getRandom()));

		addClickBehaviour();
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
