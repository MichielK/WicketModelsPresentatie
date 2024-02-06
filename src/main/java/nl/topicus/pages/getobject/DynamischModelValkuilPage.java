package nl.topicus.pages.getobject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import com.google.common.base.Stopwatch;

import nl.topicus.pages.BasePage;
import nl.topicus.pages.readonly.ReadOnlyModelPage;

public class DynamischModelValkuilPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private Stopwatch stopwatchTotalTime = Stopwatch.createStarted();

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		System.out.println("============");

		addListView();
	}

	private void addListView()
	{
		IModel< ? extends List<String>> dynamischModel = this::getStringList;

		// Reminder - regel 34 is de verkorte notatie van (functioneel identiek aan):
		// IModel< ? extends List<String>> dynamischModel = new IModel<List<String>>()
		// {
		// 		private static final long serialVersionUID = 1L;
		//
		// 		@Override
		// 		public List<String> getObject()
		// 		{
		// 			return getStringList();
		// 		}
		// };

		// De simpele oplossing is:
		// LoadableDetachableModel.of(this::getStringList);
		add(new ListView<String>("demoListView", dynamischModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<String> item)
			{
				item.add(new Label("label", item.getModel()));
			}
		});
	}

	private List<String> getStringList()
	{
		Stopwatch stopwatch = Stopwatch.createStarted();

		List<String> list = new ArrayList<>();

		// doe 10x een zware operatie van 100ms - dat zou totaal ~1 seconde moeten kosten
		for (int i = 0; i < 10; i++)
		{
			doeZwareOperatie();
			list.add("Nummer " + (i + 1));
		}

		System.out.println("getObject aangeroepen - duurde ~" + stopwatch.elapsed(TimeUnit.SECONDS)
			+ " seconde(n)");
		return list;
	}

	// Simuleer een zware operatie zoals een berekening of een "zware" database-call
	// duurt ~100 milliseconden
	private void doeZwareOperatie()
	{
		try
		{
			int aantalMilliseconden = 100;
			Thread.sleep(aantalMilliseconden);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void onDetach()
	{
		super.onDetach();
		System.out.println(
			"Totaal duurde het genereren van de pagina ~" + stopwatchTotalTime.elapsed(TimeUnit.SECONDS) + " seconden");
		System.out.println("============");
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return ReadOnlyModelPage.class;
	}
}
