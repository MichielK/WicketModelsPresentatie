package nl.topicus.pages.getobject;

import java.util.Random;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import nl.topicus.pages.BasePage;

public class DynamischModelPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private Label statischLabel, dynamischLabel;

	@Override
	// onInitialize wordt eenmalig bij het initialiseren van de pagina aangeroepen
	protected void onInitialize()
	{
		super.onInitialize();

		addStatischLabel();
		addDynamischLabel();
		addClickBehaviour();
	}

	private void addStatischLabel()
	{
		String random = getRandom();
		add(statischLabel = new Label("statischLabel", Model.of(random)));

		// Ajax is dus: vervang dynamisch een deel van de HTML.
		// Wicket gebruikt bij het vervangen van het component in de browser het markup-id. Om dit
		// te laten werken moet Wicket wel het markup-id opnemen in het HTML-element.
		statischLabel.setOutputMarkupId(true);
	}

	private void addDynamischLabel()
	{
		add(dynamischLabel = new Label("dynamischLabel", new Model<String>()
		{
			private static final long serialVersionUID = 1L;

			// Elke keer dat het object gerenderd wordt, word de getObject-methode van het model
			// aangeroepen door het component (in dit geval een label-component)
			@Override
			public String getObject()
			{
				return getRandom();
			}
		}));
		dynamischLabel.setOutputMarkupId(true);
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
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return FunctionalInterfacePage.class;
	}
}
